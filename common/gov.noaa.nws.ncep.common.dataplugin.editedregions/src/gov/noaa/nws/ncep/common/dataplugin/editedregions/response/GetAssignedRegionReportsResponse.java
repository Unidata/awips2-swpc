/**
 * This code has unlimited rights, and is provided "as is" by the National Centers 
 * for Environmental Prediction, without warranty of any kind, either expressed or implied, 
 * including but not limited to the implied warranties of merchantability and/or fitness 
 * for a particular purpose.
 * 
 * This code has been developed by the NCEP-SIB for use in the AWIPS2 system.
 * 
 **/
package gov.noaa.nws.ncep.common.dataplugin.editedregions.response;

import com.raytheon.uf.common.serialization.annotations.DynamicSerialize;
import com.raytheon.uf.common.serialization.annotations.DynamicSerializeElement;

import gov.noaa.nws.ncep.common.dataplugin.editedregions.exception.EditedRegionsException;
import gov.noaa.nws.ncep.common.dataplugin.editedregions.request.GetAssignedRegionReportsRequest;
import gov.noaa.nws.ncep.common.dataplugin.editedregions.request.intf.IRequest;
import gov.noaa.nws.ncep.common.dataplugin.editedregions.response.intf.IResponse;
import gov.noaa.nws.ncep.common.dataplugin.editedregions.results.GetBinsResults;
import gov.noaa.nws.ncep.common.dataplugin.editedregions.results.intf.IResults;

/**
 * The response class holding the results obtained
 * from the execution of the GetAssignedRegionReportsCommand
 * 
 * 
 * @author 
 * @version 1.0
 */
@DynamicSerialize
public class GetAssignedRegionReportsResponse extends BaseResponse<GetAssignedRegionReportsResponse> implements
		IResponse {

	/**
	 * The original request
	 */
	@DynamicSerializeElement
	private GetAssignedRegionReportsRequest request = null;

	/**
	 * The error
	 */
	@DynamicSerializeElement
	private EditedRegionsException error = null;

	/**
	 * The results from the GetBinsCommand
	 */
	@DynamicSerializeElement
	private GetBinsResults results = null;

	/**
	 * The time in milliseconds to complete round trip of client --> request -->
	 * gateway --> command --> response --> client
	 */
	@DynamicSerializeElement
	private long responseTime;

	/**
	 * The time in milliseconds to execute the command
	 */
	@DynamicSerializeElement
	private long processingTime;

	/**
	* 
	*/
	public GetAssignedRegionReportsResponse() {
		// TODO Auto-generated constructor stub
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * gov.noaa.nws.ncep.common.dataplugin.editedevents.response.IResponse#getError
	 * ()
	 */
	@Override
	public EditedRegionsException getError() {
		return this.error;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * gov.noaa.nws.ncep.common.dataplugin.editedevents.response.IResponse#setError
	 * (gov.noaa.nws.ncep.common.dataplugin.editedevents.exception.
	 * EditedEventsException)
	 */
	@Override
	public void setError(EditedRegionsException exception) {
		this.error = exception;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see gov.noaa.nws.ncep.common.dataplugin.editedevents.response.IResponse#
	 * getResults()
	 */
	@Override
	public IResults getResults() {
		return this.results;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * gov.noaa.nws.ncep.common.dataplugin.editedevents.response.intf.IResponse
	 * #setResults
	 * (gov.noaa.nws.ncep.common.dataplugin.editedevents.results.intf.IResults)
	 */
	@Override
	public void setResults(IResults results) {
		this.results = (GetBinsResults) results;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see gov.noaa.nws.ncep.common.dataplugin.editedevents.response.IResponse#
	 * getResponseTime()
	 */
	@Override
	public long getResponseTime() {
		return this.responseTime;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see gov.noaa.nws.ncep.common.dataplugin.editedevents.response.IResponse#
	 * setResponseTime(long)
	 */
	@Override
	public void setResponseTime(long timeInMillis) {
		this.responseTime = timeInMillis;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see gov.noaa.nws.ncep.common.dataplugin.editedevents.response.IResponse#
	 * getProcessingTime()
	 */
	@Override
	public long getProcessingTime() {
		return this.processingTime;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see gov.noaa.nws.ncep.common.dataplugin.editedevents.response.IResponse#
	 * setProcessingTime(long)
	 */
	@Override
	public void setProcessingTime(long timeInMillis) {
		this.processingTime = timeInMillis;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see gov.noaa.nws.ncep.common.dataplugin.editedevents.response.IResponse#
	 * getRequest()
	 */
	@Override
	public IRequest getRequest() {
		return this.request;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * gov.noaa.nws.ncep.common.dataplugin.editedevents.response.IResponse#hasErrors
	 * ()
	 */
	@Override
	public boolean hasErrors() {
		if (this.error == null) {
			return false;
		} else {
			return true;
		}
	}

	@Override
	public void setRequest(IRequest request) {
		this.request = (GetAssignedRegionReportsRequest) request;
	}

	/* (non-Javadoc)
     * @see gov.noaa.nws.ncep.common.dataplugin.editedevents.response.intf.IResponse#hasResults()
     */
    @Override
	public boolean hasResults() {
        if (this.results == null) {
            return false;
        } else {
            return true;
        }
	}
    
	/* (non-Javadoc)
	 * @see gov.noaa.nws.ncep.common.dataplugin.editedevents.response.intf.IResponse#setMessage(java.lang.String)
	 */
	@Override
	public void setMessage(String message) {
		this.message = message;
	}

}
