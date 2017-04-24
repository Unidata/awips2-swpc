/**
 * This code has unlimited rights, and is provided "as is" by the National Centers 
 * for Environmental Prediction, without warranty of any kind, either expressed or implied, 
 * including but not limited to the implied warranties of merchantability and/or fitness 
 * for a particular purpose.
 * 
 * 
 * This code has been developed by the NCEP-SIB for use in the AWIPS2 system.
 * 
 */
package gov.noaa.nws.ncep.edex.plugin.editedregions;

import gov.noaa.nws.ncep.common.dataplugin.editedevents.exception.EditedEventsException;
import gov.noaa.nws.ncep.common.dataplugin.editedevents.util.EditedEventsConstants;
import gov.noaa.nws.ncep.common.dataplugin.editedevents.util.EditedEventsUtil;
import gov.noaa.nws.ncep.common.dataplugin.editedevents.xml.GoesXrayDataSet;
import gov.noaa.nws.ncep.common.dataplugin.editedevents.xml.GoesXrayEvent;
import gov.noaa.nws.ncep.edex.plugin.editedevents.dao.EventsDao;
import gov.noaa.nws.ncep.edex.plugin.editedevents.util.EventsDecoderUtil;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import com.raytheon.edex.exception.DecoderException;
import com.raytheon.uf.common.dataplugin.PluginDataObject;
import com.raytheon.uf.common.dataplugin.PluginException;
import com.raytheon.uf.common.status.IUFStatusHandler;
import com.raytheon.uf.common.status.UFStatus;
import com.raytheon.uf.common.status.UFStatus.Priority;

/**
 * Decoder class for processing events
 * 
 * <pre>
 * 
 * SOFTWARE HISTORY
 * 
 * Date         Ticket#    Engineer    Description
 * ------------ ---------- ----------- --------------------------
 * Nov 9, 2015  R9583        sgurung     Initial creation
 * 
 * </pre>
 * 
 * @author sgurung
 * @version 1.0
 */
public class RegionsDecoder {

    private static final IUFStatusHandler statusHandler = UFStatus
            .getHandler(RegionsDecoder.class);

    /**
     * DAO class for Edited Events
     */
    private EventsDao dao;

    /**
     * An instance of this decoder class
     */
    private static RegionsDecoder instance = null;

    /**
     * @throws DecoderException
     */
    public RegionsDecoder() throws DecoderException {
        instance = this;
        try {
            dao = new EventsDao(EditedEventsConstants.PLUGIN_NAME);

        } catch (PluginException e) {
            statusHandler.handle(Priority.PROBLEM, "Error creating EventsDao",
                    e);
        }
    }

    /**
     * @return instance
     */
    public static RegionsDecoder getInstance() {
        if (instance != null)
            return instance;
        else {
            try {
                instance = new RegionsDecoder();
            } catch (DecoderException e) {
                statusHandler.handle(Priority.PROBLEM,
                        "Error getting an instance of EventsDecoder", e);
            }
            return instance;
        }
    }

    /**
     * Decode the input xml files containing event reports
     * 
     * @param inputFile
     *            : XML file
     * @return PluginDataObject[]
     * @throws DecoderException
     * @throws PluginException
     */
    // @SuppressWarnings("resource")
    public PluginDataObject[] decode(File inputFile) throws DecoderException,
            PluginException {

        PluginDataObject[] pdos = null;
        InputStream is = null;
        JAXBContext ctx = null;

        try {
            is = new FileInputStream(inputFile);

            // Validate the input file against the schema (schema name is
            // provided in the input file)
            boolean validationResult = EditedEventsUtil
                    .validateXmlFileAgainstSchema(inputFile);
            if (!validationResult) {
                return new PluginDataObject[0];
            }

            // Unmarshall the input xml file
            ctx = JAXBContext.newInstance(GoesXrayDataSet.class,
                    GoesXrayEvent.class);

            GoesXrayDataSet gxrds = null;

            if (ctx != null && is != null) {
                Unmarshaller um = ctx.createUnmarshaller();
                if (um != null) {
                    Object result = um.unmarshal(is);
                    if (result instanceof GoesXrayDataSet)
                        gxrds = (GoesXrayDataSet) result;
                }
            }

            if (gxrds != null) {

                List<GoesXrayEvent> xrayEvents = gxrds.getXrayEvents();

                int xrayEventsSize = xrayEvents.size();
                if (xrayEventsSize <= 0)
                    return new PluginDataObject[0];

                pdos = RegionsDecoderUtil
                        .convertGoesXrayEventsToGoesXrayPDOs(xrayEvents);

            }

        } catch (FileNotFoundException e) {
            statusHandler.handle(Priority.PROBLEM, "Error reading file: "
                    + inputFile, e);
            e.printStackTrace();
            return new PluginDataObject[0];

        } catch (EditedEventsException e) {
            statusHandler.handle(Priority.PROBLEM, e.getMessage(), e);
            e.printStackTrace();
            return new PluginDataObject[0];

        } catch (JAXBException e) {
            statusHandler.handle(Priority.PROBLEM, "Error unmarshalling file: "
                    + inputFile, e);
            e.printStackTrace();
            return new PluginDataObject[0];

        } finally {

            try {
                is.close();
            } catch (IOException e) {
                statusHandler.handle(Priority.PROBLEM, e.getLocalizedMessage(),
                        e);
            }
        }

        return pdos;
    }

    /**
     * @return the eventsDao
     */
    public EventsDao getDao() {
        return dao;
    }

    /**
     * @param eventsDao
     *            the eventsDao to set
     */
    public void setDao(EventsDao eventsDao) {
        this.dao = eventsDao;
    }
}
