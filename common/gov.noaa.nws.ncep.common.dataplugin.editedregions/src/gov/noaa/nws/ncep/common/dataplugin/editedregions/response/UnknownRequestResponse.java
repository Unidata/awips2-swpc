package gov.noaa.nws.ncep.common.dataplugin.editedregions.response;

import com.raytheon.uf.common.serialization.annotations.DynamicSerialize;
import com.raytheon.uf.common.serialization.annotations.DynamicSerializeElement;

import gov.noaa.nws.ncep.common.dataplugin.editedregions.exception.EditedRegionsException;
import gov.noaa.nws.ncep.common.dataplugin.editedregions.request.intf.IRequest;
import gov.noaa.nws.ncep.common.dataplugin.editedregions.response.intf.IResponse;
import gov.noaa.nws.ncep.common.dataplugin.editedregions.results.intf.IResults;

/**
 * The response returned to the caller if the request made
 * was not recognized.
 * 
 *
 *  <pre>
 * 
 * SOFTWARE HISTORY
 * 
 * Date         Ticket#    Engineer    Description
 * ------------ ---------- ----------- --------------------------
 * Mar 22, 2016  R9583     jtravis     Initial creation
 * 
 * </pre>
 * 
 * @author jtravis
 * @version 1.0
 */
@DynamicSerialize
public class UnknownRequestResponse extends BaseResponse<UnknownRequestResponse> implements IResponse {

    /**
     * The time in milliseconds to complete round trip of request / response
     */
    @DynamicSerializeElement
    private long responseTime;

    /**
     * The time in milliseconds to execute the command
     */
    @DynamicSerializeElement
    private long processingTime;

    /**
     * The original Request
     */
    @DynamicSerializeElement
    private IRequest request;
	
	/**
	 * Default Constructor
	 */
	public UnknownRequestResponse() {}

	/* (non-Javadoc)
	 * @see gov.noaa.nws.ncep.common.dataplugin.editedevents.response.intf.IResponse#getResponseTime()
	 */
	@Override
	public long getResponseTime() {
		return this.responseTime;
	}

	/* (non-Javadoc)
	 * @see gov.noaa.nws.ncep.common.dataplugin.editedevents.response.intf.IResponse#setResponseTime(long)
	 */
	@Override
	public void setResponseTime(long timeInMillis) {
		this.responseTime = timeInMillis;
	}

	/* (non-Javadoc)
	 * @see gov.noaa.nws.ncep.common.dataplugin.editedevents.response.intf.IResponse#getProcessingTime()
	 */
	@Override
	public long getProcessingTime() {
		return this.processingTime;
	}

	/* (non-Javadoc)
	 * @see gov.noaa.nws.ncep.common.dataplugin.editedevents.response.intf.IResponse#setProcessingTime(long)
	 */
	@Override
	public void setProcessingTime(long timeInMillis) {
		this.processingTime = timeInMillis;
	}

	/* (non-Javadoc)
	 * @see gov.noaa.nws.ncep.common.dataplugin.editedevents.response.intf.IResponse#getRequest()
	 */
	@Override
	public IRequest getRequest() {
		return this.request;
	}

	/* (non-Javadoc)
	 * @see gov.noaa.nws.ncep.common.dataplugin.editedevents.response.intf.IResponse#setRequest(gov.noaa.nws.ncep.common.dataplugin.editedevents.request.intf.IRequest)
	 */
	@Override
	public void setRequest(IRequest request) {
		this.request = request;
	}

	/* (non-Javadoc)
	 * @see gov.noaa.nws.ncep.common.dataplugin.editedevents.response.iR9583ntf.IResponse#hasErrors()
	 */
	@Override
	public boolean hasErrors() {
        return false;
	}

	/* (non-Javadoc)
	 * @see gov.noaa.nws.ncep.common.dataplugin.editedevents.response.intf.IResponse#getError()
	 */
	@Override
	public EditedRegionsException getError() {
		return null;
	}

	/* (non-Javadoc)
	 * @see gov.noaa.nws.ncep.common.dataplugin.editedevents.response.intf.IResponse#setError(gov.noaa.nws.ncep.common.dataplugin.editedevents.exception.EditedEventsException)
	 */
	@Override
	public void setError(EditedRegionsException exception) {
		;; // do nothing as there will be on exception
	}

	/* (non-Javadoc)
	 * @see gov.noaa.nws.ncep.common.dataplugin.editedevents.response.intf.IResponse#getResults()
	 */
	@Override
	public IResults getResults() {
		return null; // just return null as there are no results for this response type
	}

	/* (non-Javadoc)
	 * @see gov.noaa.nws.ncep.common.dataplugin.editedevents.response.intf.IResponse#setResults(java.util.Vector)
	 */
	@Override
	public void setResults(IResults results) {
		;; // do nothing as there are no results for this response type
	}

	/**
	 * Get the message associated with the response;
	 * 
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}

	/* (non-Javadoc)
     * @see gov.noaa.nws.ncep.common.dataplugin.editedevents.response.intf.IResponse#hasResults()
     */
    @Override
	public boolean hasResults() {
    	//TODO
        return false;
	}
    
	/* (non-Javadoc)
	 * @see gov.noaa.nws.ncep.common.dataplugin.editedevents.response.intf.IResponse#setMessage(java.lang.String)
	 */
	@Override
	public void setMessage(String message) {
		this.message = message;
	}

}
