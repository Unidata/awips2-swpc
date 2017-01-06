package gov.noaa.nws.ncep.common.dataplugin.editedregions.response;

import com.raytheon.uf.common.serialization.annotations.DynamicSerialize;
import com.raytheon.uf.common.serialization.annotations.DynamicSerializeElement;

import gov.noaa.nws.ncep.common.dataplugin.editedregions.exception.EditedRegionsException;
import gov.noaa.nws.ncep.common.dataplugin.editedregions.request.AddBinRequest;
import gov.noaa.nws.ncep.common.dataplugin.editedregions.request.intf.IRequest;
import gov.noaa.nws.ncep.common.dataplugin.editedregions.response.intf.IResponse;
import gov.noaa.nws.ncep.common.dataplugin.editedregions.results.AddBinResults;
import gov.noaa.nws.ncep.common.dataplugin.editedregions.results.intf.IResults;

/**
 * The response class holding the results obtained
 * from the execution of the AddBinCommand 
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
public class AddBinResponse extends BaseResponse<AddBinResponse> implements
        IResponse {

	/**
	 * The error
	 */
	@DynamicSerializeElement
	private EditedRegionsException error = null;
	
	/**
	 * The original request
	 */
	@DynamicSerializeElement
	private AddBinRequest request = null;
	
	/**
	 * The results from the AddBinCommand
	 */
	@DynamicSerializeElement
	private AddBinResults results = null;
	
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

	/**
	 * 
	 */
	@DynamicSerializeElement
	private boolean responseTimeSet = false;
	
	/**
	 * 
	 */
	@DynamicSerializeElement
	private boolean processingTimeSet = false;

	
    /**
	 * 
	 */
    public AddBinResponse() {
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
     * EditedBinsException)
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
        return results;
    }

    /*
     * (non-Javadoc)
     * 
     * @see gov.noaa.nws.ncep.common.dataplugin.editedevents.response.IResponse#
     * setResults(java.util.Vector)
     */
    @Override
    public void setResults(IResults results) {
        this.results = (AddBinResults) results;
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
		if (!this.processingTimeSet) {
    		this.processingTime = timeInMillis;
    		this.processingTimeSet = true;
    	}		
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
		this.request = (AddBinRequest) request;
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
	 * @see gov.noaa.nws.ncep.common.dataplugin.editedevents.response.intf.IResponse#setMessage(java.lang.String)
	 */
	@Override
	public void setMessage(String message) {
		this.message = message;
	}
}
