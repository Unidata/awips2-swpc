package gov.noaa.nws.ncep.common.dataplugin.editedregions.response;

import com.raytheon.uf.common.serialization.annotations.DynamicSerialize;
import com.raytheon.uf.common.serialization.annotations.DynamicSerializeElement;

import gov.noaa.nws.ncep.common.dataplugin.editedregions.exception.EditedRegionsException;
import gov.noaa.nws.ncep.common.dataplugin.editedregions.request.ProcessEventsRequest;
import gov.noaa.nws.ncep.common.dataplugin.editedregions.request.intf.IRequest;
import gov.noaa.nws.ncep.common.dataplugin.editedregions.response.intf.IResponse;
import gov.noaa.nws.ncep.common.dataplugin.editedregions.results.intf.IResults;

/**
 * Response class used to hold all information produced the 
 * UpdateBinCommand is executed
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
public class UpdateBinResponse extends BaseResponse<UpdateBinResponse> implements IResponse {

	/**
	 *  Holds error that occured during command execution
	 */
	@DynamicSerializeElement
    private EditedRegionsException error = null;

    /**
     * The results
     */
    @DynamicSerializeElement
    private IResults results = null;

    /**
     * Time in milliseconds to complete round trip
     * of request / response
     */
    @DynamicSerializeElement
    private long responseTime;

    /**
     * Time in milliseconds to execute the command
     */
    @DynamicSerializeElement
    private long processingTime;

    /**
     * The original request
     */
    @DynamicSerializeElement
    private ProcessEventsRequest request;

    /**
	 * 
	 */
    public UpdateBinResponse() {
        // TODO Auto-generated constructor stub
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
     * setResults()
     * 
     * Currently no results are provided when executing the
     * UpdateBinCommand so calls to UpdateBinResponse.setResults()
     * always sets the results value to null
     * 
     */
    @Override
    public void setResults(IResults results) {
        this.results = null;
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
    public ProcessEventsRequest getRequest() {
        return this.request;
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
	 * @see gov.noaa.nws.ncep.common.dataplugin.editedevents.response.intf.IResponse#setRequest(gov.noaa.nws.ncep.common.dataplugin.editedevents.request.intf.IRequest)
	 */
	@Override
	public void setRequest(IRequest request) {
		this.request = (ProcessEventsRequest) request;
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
