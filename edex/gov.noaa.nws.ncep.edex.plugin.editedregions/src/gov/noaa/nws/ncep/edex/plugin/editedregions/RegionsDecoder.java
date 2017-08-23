package gov.noaa.nws.ncep.edex.plugin.editedregions;

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

import gov.noaa.nws.ncep.common.dataplugin.editedregions.RegionReport;
import gov.noaa.nws.ncep.common.dataplugin.editedregions.exception.EditedRegionsException;
import gov.noaa.nws.ncep.common.dataplugin.editedregions.util.EditedRegionsUtil;
import gov.noaa.nws.ncep.common.dataplugin.editedregions.xml.RegionReportDataSet;
import gov.noaa.nws.ncep.edex.plugin.editedregions.dao.RegionReportsDao;
import gov.noaa.nws.ncep.edex.plugin.editedregions.util.RegionsDecoderUtil;

/**
 * Decoder class for processing Region Reports
 * 
 * @author jtravis
 * @version 1.0
 */
public class RegionsDecoder {

    /**
     * Logger
     */
    private static final IUFStatusHandler statusHandler = UFStatus
            .getHandler(RegionsDecoder.class);

    /**
     * DAO class for Edited Regions Region Reports
     */
    private RegionReportsDao dao;

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
            dao = new RegionReportsDao();
        } catch (PluginException e) {
            statusHandler.handle(Priority.PROBLEM, "Error creating RegionsDao",
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
                        "Error getting an instance of RegionsDecoder", e);
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
    public PluginDataObject[] decode(File inputFile)
            throws DecoderException, PluginException {

        PluginDataObject[] pdos = null;
        InputStream is = null;
        JAXBContext ctx = null;

        try {
            is = new FileInputStream(inputFile);

            // Validate the input file against the schema (schema name is
            // provided in the input file)
            boolean validationResult = EditedRegionsUtil
                    .validateXmlFileAgainstSchema(inputFile);
            if (!validationResult) {
                return new PluginDataObject[0];
            }

            // Unmarshall the input xml file
            ctx = JAXBContext.newInstance(RegionReportDataSet.class,
                    RegionReport.class);

            RegionReportDataSet dataSet = null;

            if (ctx != null && is != null) {
                Unmarshaller um = ctx.createUnmarshaller();
                if (um != null) {
                    Object result = um.unmarshal(is);
                    if (result instanceof RegionReportDataSet)
                        dataSet = (RegionReportDataSet) result;
                }
            }

            if (dataSet != null) {

                List<RegionReport> reports = dataSet.getRegionReports();

                int reportCount = reports.size();

                if (reportCount <= 0) {
                    return new PluginDataObject[0];
                }

                pdos = RegionsDecoderUtil.convertRegionReportsPDOs(reports);

            }

        } catch (FileNotFoundException e) {
            statusHandler.handle(Priority.PROBLEM,
                    "Error reading file: " + inputFile, e);
            e.printStackTrace();
            return new PluginDataObject[0];

        } catch (EditedRegionsException e) {
            statusHandler.handle(Priority.PROBLEM, e.getMessage(), e);
            e.printStackTrace();
            return new PluginDataObject[0];

        } catch (JAXBException e) {
            statusHandler.handle(Priority.PROBLEM,
                    "Error unmarshalling file: " + inputFile, e);
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
    public RegionReportsDao getDao() {
        return dao;
    }

    /**
     * @param eventsDao
     *            the eventsDao to set
     */
    public void setDao(RegionReportsDao eventsDao) {
        this.dao = eventsDao;
    }
}
