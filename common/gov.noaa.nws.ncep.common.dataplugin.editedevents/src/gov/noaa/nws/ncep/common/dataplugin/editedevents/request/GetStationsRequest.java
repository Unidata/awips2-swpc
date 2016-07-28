/**
 * This code has unlimited rights, and is provided "as is" by the National Centers 
 * for Environmental Prediction, without warranty of any kind, either expressed or implied, 
 * including but not limited to the implied warranties of merchantability and/or fitness 
 * for a particular purpose.
 * 
 * This code has been developed by the NCEP-SIB for use in the AWIPS2 system.
 * 
 **/
package gov.noaa.nws.ncep.common.dataplugin.editedevents.request;

import gov.noaa.nws.ncep.common.dataplugin.editedevents.request.intf.IRequest;

import com.raytheon.uf.common.serialization.annotations.DynamicSerialize;
import com.raytheon.uf.common.serialization.annotations.DynamicSerializeElement;

/**
 * Class for allowing the client to request the back-end to obtain 
 * the list of stations
 * 
 * <pre>
 *
 * SOFTWARE HISTORY
 *
 * Date         Ticket#    Engineer    Description
 * ------------ ---------- ----------- --------------------------
 * Feb 01, 2016  R9583      sgurung     Initial creation
 *
 * </pre>
 *
 * @author sgurung
 * @version 1.0	
 */

@DynamicSerialize
public class GetStationsRequest extends Request<GetStationsRequest> implements
IRequest {

	/**
	* The Request ID
	*/
	@DynamicSerializeElement
	private final long id;
	
	/**
	* The start of the date time range to process events
	*/
	@DynamicSerializeElement
	private String stationType;	
	
	/**
	* 
	*/
	public GetStationsRequest() {
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
		// TODO Auto-generated method stub
		return true;
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
		;;
	}

	/**
	 * @return the stationType
	 */
	public String getStationType() {
		return stationType;
	}

	/**
	 * @param stationType the stationType to set
	 */
	public void setStationType(String stationType) {
		this.stationType = stationType;
	}
		
}

