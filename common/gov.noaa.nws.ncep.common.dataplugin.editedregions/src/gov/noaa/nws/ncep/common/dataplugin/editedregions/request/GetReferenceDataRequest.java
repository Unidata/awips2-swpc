package gov.noaa.nws.ncep.common.dataplugin.editedregions.request;

import java.util.Hashtable;

import com.raytheon.uf.common.serialization.annotations.DynamicSerialize;
import com.raytheon.uf.common.serialization.annotations.DynamicSerializeElement;

import gov.noaa.nws.ncep.common.dataplugin.editedregions.request.intf.IRequest;

/**
 * Class for allowing the client to request the back-end to
 * obtain the reference data that is used for client side validation
 * 
 * @author jtravis
 * @version 1.0
 */
@DynamicSerialize
public class GetReferenceDataRequest extends
        Request<GetReferenceDataRequest> implements IRequest {

    /**
     * The Requests ID
     */
    @DynamicSerializeElement
    private final long id;
    
    private Hashtable<String, Hashtable<String, String>> results = 
    		new Hashtable<String, Hashtable<String, String>>();
    
	/**
	 *  Constructor
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
     * @see
     * gov.noaa.nws.ncep.common.dataplugin.editedevents.request.IRequest#isValid
     * ()
     */
    @Override
    public boolean isValid() {
    	boolean valid = true;
        
    	// for this request there is no input parameter that must be
    	// supplied by the client for the request to be performed so there
    	// is nothing to validate against.  Just return true.
    	
//    	// the request is valid only if at least one report type is being
//    	// requested.
//        if (this.obtainAssignedReports || this.obtainUnassignedReports) {
//            valid = true;
//        }

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
    	;;
    }

//	/**
//	 * @return the results
//	 */
//	public Hashtable<String, Hashtable<String, String>> getResults() {
//		return this.results;
//	}
//
//	/**
//	 * TODO this method may not be needed since the client will not supply a hashtable to store results
//	 * 
//	 * @param results the results to set
//	 */
//	public void setResults(Hashtable<String, Hashtable<String, String>> results) {
////		this.results = results;
//	}
    
}
