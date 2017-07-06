package gov.noaa.nws.ncep.common.dataplugin.editedregions.request;

import com.raytheon.uf.common.serialization.annotations.DynamicSerialize;
import com.raytheon.uf.common.serialization.comm.IServerRequest;

/**
 * 
 * <pre>
 * 
 * SOFTWARE HISTORY 
 * 
 * Date         Ticket#    Engineer    Description
 * ------------ ---------- ----------- --------------------------
 * Jan 14, 2016  R9583     jtravis     Initial creation
 * 
 * </pre>
 * 
 * @author jtravis
 * @version 1.0
 */
@DynamicSerialize
public class Request<T> implements IServerRequest {

    /**
     * 
     */
    public Request() {
    }

}
