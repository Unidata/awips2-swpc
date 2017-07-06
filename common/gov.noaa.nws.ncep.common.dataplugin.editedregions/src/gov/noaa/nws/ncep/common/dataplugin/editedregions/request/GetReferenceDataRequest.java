package gov.noaa.nws.ncep.common.dataplugin.editedregions.request;

import com.raytheon.uf.common.serialization.annotations.DynamicSerialize;
import com.raytheon.uf.common.serialization.annotations.DynamicSerializeElement;

import gov.noaa.nws.ncep.common.dataplugin.editedregions.request.intf.IRequest;

/**
 * Class for allowing the client to request the back-end to obtain the reference
 * data that is used for client side validation
 * 
 * @author jtravis
 * @version 1.0
 */
@DynamicSerialize
public class GetReferenceDataRequest extends Request<GetReferenceDataRequest>
        implements IRequest {

    /**
     * The Requests ID
     */
    @DynamicSerializeElement
    private final long id;

    /**
     * Constructor
     */
    public GetReferenceDataRequest() {
        this.id = System.currentTimeMillis();
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * gov.noaa.nws.ncep.common.dataplugin.editedevents.request.IRequest#getID()
     */
    @Override
    public long getId() {
        return this.id;
    }

    /*
     * (non-Javadoc)
     * 
     * TODO currently no validation performed as the request requires no
     * parameters which could be used for validation revisit in ER v2.0
     * 
     * @see
     * gov.noaa.nws.ncep.common.dataplugin.editedevents.request.IRequest#isValid
     * ()
     */
    @Override
    public boolean isValid() {
        boolean valid = true;

        // for this request there is no input parameter that must be
        // supplied by the client for the request to be performed so there
        // is nothing to validate against. Just return true.

        // // the request is valid only if at least one report type is being
        // // requested.
        // if (this.obtainAssignedReports || this.obtainUnassignedReports) {
        // valid = true;
        // }

        return valid;
    }

    @Override
    public void setId(long ID) {
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

}
