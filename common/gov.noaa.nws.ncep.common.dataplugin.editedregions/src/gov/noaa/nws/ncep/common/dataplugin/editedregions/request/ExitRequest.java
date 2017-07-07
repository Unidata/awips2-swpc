package gov.noaa.nws.ncep.common.dataplugin.editedregions.request;

import com.raytheon.uf.common.serialization.annotations.DynamicSerialize;
import com.raytheon.uf.common.serialization.annotations.DynamicSerializeElement;

import gov.noaa.nws.ncep.common.dataplugin.editedregions.request.intf.IRequest;

/**
 * Class for allowing the client to request the back-end to exit the ER
 * application and perform necessary steps
 * 
 * <pre>
 * 
 * SOFTWARE HISTORY
 * 
 * Date         Ticket#    Engineer    Description
 * ------------ ---------- ----------- --------------------------
 * Fev 17, 2016  R9583      sgurung     Initial creation
 * 
 * </pre>
 * 
 * @author sgurung
 * @version 1.0
 */
@DynamicSerialize
public class ExitRequest extends Request<ExitRequest> implements IRequest {

    /**
     * The request id
     */
    @DynamicSerializeElement
    private final long id;

    /**
     * The start of the date time range to process events
     */
    @DynamicSerializeElement
    private long beginDTTM = 0L;

    /**
     * 
     */
    public ExitRequest() {
        this.id = System.currentTimeMillis();
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * gov.noaa.nws.ncep.common.dataplugin.editedevents.request.intf.IRequest#
     * getId()
     */
    @Override
    public long getId() {
        return this.id;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * gov.noaa.nws.ncep.common.dataplugin.editedevents.request.intf.IRequest#
     * setId(long)
     */
    @Override
    public void setId(long Id) {
        // The id is set when the request is initialized
        // but the system still requires the method for
        // serialization / deserialization through the gateway.
        //
        // thus, this method does not actually change the id
        //
        // TODO - verify this
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * gov.noaa.nws.ncep.common.dataplugin.editedevents.request.intf.IRequest#
     * isValid()
     */
    @Override
    public boolean isValid() {
        return true;
    }

    /**
     * Get the start date time range used for filtering which events should be
     * processed
     * 
     * @return long
     */
    public long getBeginDTTM() {
        return this.beginDTTM;
    }

    /**
     * Set the start date time range for filtering which events should be
     * processed
     * 
     * @param beginDTTM
     */
    public void setBeginDTTM(long beginDTTM) {
        this.beginDTTM = beginDTTM;
    }

}
