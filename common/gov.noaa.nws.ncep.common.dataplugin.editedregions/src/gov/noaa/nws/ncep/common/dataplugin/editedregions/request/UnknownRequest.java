package gov.noaa.nws.ncep.common.dataplugin.editedregions.request;

import gov.noaa.nws.ncep.common.dataplugin.editedregions.request.intf.IRequest;

/**
 * Request class used if the request supplied to the gateway is null or the
 * request is of a type that the RequestHandler does not recognize.
 * 
 * 
 * <pre>
 * 
 * SOFTWARE HISTORY
 * 
 * Date         Ticket#    Engineer    Description
 * ------------ ---------- ----------- --------------------------
 * Mar 12, 2016  R9583     jtravis     Initial creation
 * 
 * </pre>
 * 
 * @author jtravis
 * @version 1.0
 */
public class UnknownRequest extends Request<UnknownRequest>
        implements IRequest {

    private long id = 0L;

    /**
     * 
     */
    public UnknownRequest() {
        this.id = System.currentTimeMillis();
    }

    @Override
    public long getId() {
        return this.id;
    }

    @Override
    public void setId(long Id) {
        // The id is set when the request is initialized
        // but the system still requires the method for
        // serialization / deserialization through the gateway.
        //
        // thus, this method does not actually change the id
        //
        // TODO - verify this
        ;
        ;
    }

    @Override
    public boolean isValid() {
        return true;
    }
}
