package gov.noaa.nws.ncep.common.dataplugin.editedevents.request;

import com.raytheon.uf.common.serialization.annotations.DynamicSerialize;
import com.raytheon.uf.common.serialization.annotations.DynamicSerializeElement;

import gov.noaa.nws.ncep.common.dataplugin.editedevents.request.intf.IRequest;

/**
 * Request to run Edited Events diagnostic tests
 * 
 * 
 * <pre>
 * 
 * SOFTWARE HISTORY
 * 
 * Date         Ticket#    Engineer    Description
 * ------------ ---------- ----------- --------------------------
 * Apr 27, 2016  R9583     jtravis     Initial creation
 * 
 * </pre>
 * 
 * @author jtravis
 * @version 1.0
 */
@DynamicSerialize
public class SystemDiagnosticsRequest extends Request<SystemDiagnosticsRequest> implements IRequest {

    /**
     * The Requests ID
     */
    @DynamicSerializeElement
    private final long id;
	
	/**
	 * Default Constructor
	 */
	public SystemDiagnosticsRequest() {
		this.id = System.currentTimeMillis();
	}

	/* (non-Javadoc)
	 * @see gov.noaa.nws.ncep.common.dataplugin.editedevents.request.intf.IRequest#getId()
	 */
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
    	;;
	}

	/* (non-Javadoc)
	 * @see gov.noaa.nws.ncep.common.dataplugin.editedevents.request.intf.IRequest#isValid()
	 */
	@Override
	public boolean isValid() {
		// nothing to validate against
		return true;
	}

}
