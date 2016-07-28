package gov.noaa.nws.ncep.common.dataplugin.editedevents.response;

import gov.noaa.nws.ncep.common.dataplugin.editedevents.exception.EditedEventsException;
import gov.noaa.nws.ncep.common.dataplugin.editedevents.request.AddEventRequest;
import gov.noaa.nws.ncep.common.dataplugin.editedevents.request.intf.IRequest;
import gov.noaa.nws.ncep.common.dataplugin.editedevents.response.intf.IResponse;
import gov.noaa.nws.ncep.common.dataplugin.editedevents.results.AddEventResults;
import gov.noaa.nws.ncep.common.dataplugin.editedevents.results.intf.IResults;

import com.raytheon.uf.common.serialization.annotations.DynamicSerialize;
import com.raytheon.uf.common.serialization.annotations.DynamicSerializeElement;

/**
 * The response class holding the results obtained
 * from the execution of the AddEventCommand 
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
@DynamicSerialize
public class AddEventResponse extends BaseResponse<AddEventResponse> implements
        IResponse {

	/**
	 * The error
	 */
	@DynamicSerializeElement
	private EditedEventsException error = null;
	
	/**
	 * The original request
	 */
	@DynamicSerializeElement
	private AddEventRequest request = null;
	
	/**
	 * The results from the AddEventCommand
	 */
	@DynamicSerializeElement
	private AddEventResults results = null;
	
	/**
	 * Time in milliseconds to complete round
     * trip of client --> request --> gateway --> command --> response --> client
	 */
	@DynamicSerializeElement
	private long responseTime;
	
	/**
	 * The time in milliseconds it took to execute all
	 * tasks required to satisfy the request.  Its the
	 * time it took for the command to execute.
	 */
	@DynamicSerializeElement
	private long processingTime;
	
	@DynamicSerializeElement
	private boolean responseTimeSet = false;
	
	@DynamicSerializeElement
	private boolean processingTimeSet = false;
	
    /**
	 * 
	 */
    public AddEventResponse() {
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
    public EditedEventsException getError() {
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
    public void setError(EditedEventsException exception) {
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
     * @see gov.noaa.nws.ncep.common.dataplugin.editedevents.response.IResponse#
     * setResults(java.util.Vector)
     */
    @Override
    public void setResults(IResults results) {
        this.results = (AddEventResults) results;
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
    	if (!this.responseTimeSet) {
    		this.responseTime = timeInMillis;
    		this.processingTimeSet = true;
    	}
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
    	if (!this.processingTimeSet) {
    		this.processingTime = timeInMillis;
    		this.processingTimeSet = true;
    	}
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
	 * @see gov.noaa.nws.ncep.common.dataplugin.editedevents.response.intf.IResponse#setRequest(gov.noaa.nws.ncep.common.dataplugin.editedevents.request.intf.IRequest)
	 */
	@Override
	public void setRequest(IRequest request) {
		this.request = (AddEventRequest) request;
	}

	/**
	 * @return the responseTimeSet
	 */
	public boolean isResponseTimeSet() {
		return responseTimeSet;
	}

	/**
	 * @param responseTimeSet the responseTimeSet to set
	 */
	public void setResponseTimeSet(boolean responseTimeSet) {
		this.responseTimeSet = responseTimeSet;
	}

	/**
	 * @return the processingTimeSet
	 */
	public boolean isProcessingTimeSet() {
		return processingTimeSet;
	}

	/**
	 * @param processingTimeSet the processingTimeSet to set
	 */
	public void setProcessingTimeSet(boolean processingTimeSet) {
		this.processingTimeSet = processingTimeSet;
	}

	/* (non-Javadoc)
	 * @see gov.noaa.nws.ncep.common.dataplugin.editedevents.response.intf.IResponse#setMessage(java.lang.String)
	 */
	@Override
	public void setMessage(String message) {
		this.message = message;
	}

}
