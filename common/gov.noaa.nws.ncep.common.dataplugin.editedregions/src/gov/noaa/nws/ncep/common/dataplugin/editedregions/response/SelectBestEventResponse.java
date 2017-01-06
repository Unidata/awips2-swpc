package gov.noaa.nws.ncep.common.dataplugin.editedregions.response;

import com.raytheon.uf.common.serialization.annotations.DynamicSerializeElement;

import gov.noaa.nws.ncep.common.dataplugin.editedregions.exception.EditedRegionsException;
import gov.noaa.nws.ncep.common.dataplugin.editedregions.request.SelectBestEventRequest;
import gov.noaa.nws.ncep.common.dataplugin.editedregions.request.intf.IRequest;
import gov.noaa.nws.ncep.common.dataplugin.editedregions.response.intf.IResponse;
import gov.noaa.nws.ncep.common.dataplugin.editedregions.results.intf.IResults;

/**
 * Class representing the response returned when an
 * instance of a SelectBestEventCommand is executed.
 * 
 * <pre>
 * 
 * SOFTWARE HISTORY
 * 
 * Date         Ticket#    Engineer    Description
 * ------------ ---------- ----------- --------------------------
 * Mar 12, 2016 R9583      jtravis     Initial creation
 * 
 * </pre>
 * 
 * @author jtravis
 * @version 1.0
 */
public class SelectBestEventResponse extends BaseResponse<SelectBestEventResponse> implements IResponse {

	/**
	 *  Holds error that occurred during command execution
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
    private SelectBestEventRequest request;
	
	/**
	 * 
	 */
	public SelectBestEventResponse() {
		// TODO Auto-generated constructor stub
	}

	/* (non-Javadoc)
	 * @see gov.noaa.nws.ncep.common.dataplugin.editedevents.response.intf.IResponse#getError()
	 */
	@Override
	public EditedRegionsException getError() {
		return this.error;
	}

	/* (non-Javadoc)
	 * @see gov.noaa.nws.ncep.common.dataplugin.editedevents.response.intf.IResponse#setError(gov.noaa.nws.ncep.common.dataplugin.editedevents.exception.EditedEventsException)
	 */
	@Override
	public void setError(EditedRegionsException exception) {
		this.error = exception;
	}

	/* (non-Javadoc)
	 * @see gov.noaa.nws.ncep.common.dataplugin.editedevents.response.intf.IResponse#getResults()
	 */
	@Override
	public IResults getResults() {
		return this.results;
	}

	/* (non-Javadoc)
	 * @see gov.noaa.nws.ncep.common.dataplugin.editedevents.response.intf.IResponse#setResults(gov.noaa.nws.ncep.common.dataplugin.editedevents.results.intf.IResults)
	 */
	@Override
	public void setResults(IResults results) {
        // TODO complete this

        if (this.results == null) {
            this.results = results;
        }
	}

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
	public SelectBestEventRequest getRequest() {
		return this.request;
	}

	/* (non-Javadoc)
	 * @see gov.noaa.nws.ncep.common.dataplugin.editedevents.response.intf.IResponse#setRequest(gov.noaa.nws.ncep.common.dataplugin.editedevents.request.intf.IRequest)
	 */
	@Override
	public void setRequest(IRequest request) {
		this.request = (SelectBestEventRequest) request;
	}

	/* (non-Javadoc)
	 * @see gov.noaa.nws.ncep.common.dataplugin.editedevents.response.intf.IResponse#hasErrors()
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
