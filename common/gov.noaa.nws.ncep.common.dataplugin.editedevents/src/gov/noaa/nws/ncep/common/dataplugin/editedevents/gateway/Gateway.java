package gov.noaa.nws.ncep.common.dataplugin.editedevents.gateway;

import gov.noaa.nws.ncep.common.dataplugin.editedevents.exception.EditedEventsException;
import gov.noaa.nws.ncep.common.dataplugin.editedevents.request.Request;
import gov.noaa.nws.ncep.common.dataplugin.editedevents.response.BaseResponse;

import com.raytheon.uf.common.serialization.comm.RequestRouter;

/**
 * The Gateway class provides the means for clients to interact
 * with the back-end
 * 
 * <pre>
 * 
 * SOFTWARE HISTORY
 * 
 * Date         Ticket#    Engineer    Description
 * ------------ ---------- ----------- --------------------------
 * Nov 7, 2015  R9583      jtravis     Initial creation
 * 
 * </pre>
 * 
 * @author jtravis
 * @version 1.0
 */
public class Gateway {

	/**
	 * Holds reference to the singleton gateway instance
	 */
	private static Gateway gateway = null;
	
	/**
	 *  Private Constructor to force singleton
	 */
	private Gateway() {}
	
	/**
	 * Creates a new instance of the Gateway if one does not
	 * already exists else returns the existing Gateway
	 * 
	 * @return Gateway
	 */
	public static Gateway getInstance() {
		
		if (gateway == null) {
			Gateway.gateway = new Gateway();
		}
		
		return Gateway.gateway;
		
	}
	

	/**
	 * Method to used by any client to send a request to the back-end for
	 * processing
	 * 
	 * @param request
	 * @return Response<U>
	 * 
	 * @throws EditedEventsException
	 */
	@SuppressWarnings("unchecked")
	public <U extends BaseResponse<U>,T> U submit(Request<T> request) throws EditedEventsException {
	
		U response = null;

		try {
			
			response = (U) RequestRouter.route(request);
			
		} catch (Exception e) {
			
			EditedEventsException eex = new EditedEventsException("ERROR - Gateway - Exception Occured While Routing Request To RequestRouter", e);
			eex.setStackTrace(e.getStackTrace());
			
			throw eex;
		}
		
		return response;
		
	}
	
}
