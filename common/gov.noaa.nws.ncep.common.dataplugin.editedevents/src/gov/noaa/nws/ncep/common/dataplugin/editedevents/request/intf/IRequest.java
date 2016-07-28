package gov.noaa.nws.ncep.common.dataplugin.editedevents.request.intf;

import com.raytheon.uf.common.serialization.comm.IServerRequest;

/**
 * Interface for which all requests must implement
 * 
 * <pre>
 * 
 * SOFTWARE HISTORY
 * 
 * Date         Ticket#    Engineer    Description
 * ------------ ---------- ----------- --------------------------
 * Feb 12, 2016  R9583     jtravis     Initial creation
 * 
 * </pre>
 * 
 * @author jtravis
 * @version 1.0
 */
public interface IRequest extends IServerRequest {

    /**
     * Obtain the requests unique id;
     * 
     * @return long
     */
    public long getId();

    /**
     * Set the request's ID
     */
    public void setId(long Id);

    /**
     * Confirm that the request is valid
     * 
     * @return boolean
     */
    public boolean isValid();
  
}
