package gov.noaa.nws.ncep.common.dataplugin.editedregions.response;

import com.raytheon.uf.common.serialization.annotations.DynamicSerialize;
import com.raytheon.uf.common.serialization.annotations.DynamicSerializeElement;

import gov.noaa.nws.ncep.common.dataplugin.editedregions.exception.EditedRegionsException;
import gov.noaa.nws.ncep.common.dataplugin.editedregions.request.UpdateConsensusRequest;
import gov.noaa.nws.ncep.common.dataplugin.editedregions.request.intf.IRequest;
import gov.noaa.nws.ncep.common.dataplugin.editedregions.response.intf.IResponse;
import gov.noaa.nws.ncep.common.dataplugin.editedregions.results.UpdateConsensusResults;
import gov.noaa.nws.ncep.common.dataplugin.editedregions.results.intf.IResults;

/**
 * The response class holding the results obtained from the execution of the
 * Update Consensus command
 * 
 * <pre>
 *
 * SOFTWARE HISTORY
 *
 * Date         Ticket#    Engineer    Description
 * ------------ ---------- ----------- --------------------------
 * Apr 01, 2016 R9583       alockleigh     Initial creation
 *
 * </pre>
 *
 * @author sgurung
 * @version 1.0
 */
@DynamicSerialize
public class UpdateConsensusResponse
        extends BaseResponse<UpdateConsensusResponse> implements IResponse {

    /**
     * The error
     */
    @DynamicSerializeElement
    private EditedRegionsException error = null;

    /**
     * The original request
     */
    @DynamicSerializeElement
    private UpdateConsensusRequest request = null;

    /**
     * The results from the AddBinCommand
     */
    @DynamicSerializeElement
    private UpdateConsensusResults results = null;

    /**
     * Time in milliseconds to complete round trip of client --> request -->
     * gateway --> command --> response --> client
     */
    @DynamicSerializeElement
    private long responseTime;

    /**
     * The time in milliseconds it took to execute all tasks required to satisfy
     * the request. Its the time it took for the command to execute.
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

    /*
     * (non-Javadoc)
     * 
     * @see
     * gov.noaa.nws.ncep.common.dataplugin.editedevents.response.intf.IResponse#
     * getError()
     */
    @Override
    public EditedRegionsException getError() {
        return this.error;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * gov.noaa.nws.ncep.common.dataplugin.editedevents.response.intf.IResponse#
     * setError(gov.noaa.nws.ncep.common.dataplugin.editedevents.exception.
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
     * gov.noaa.nws.ncep.common.dataplugin.editedevents.response.intf.IResponse#
     * setMessage(java.lang.String)
     */
    @Override
    public void setMessage(String message) {
        this.message = message;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * gov.noaa.nws.ncep.common.dataplugin.editedevents.response.intf.IResponse#
     * getResults()
     */
    @Override
    public IResults getResults() {
        return results;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * gov.noaa.nws.ncep.common.dataplugin.editedevents.response.intf.IResponse#
     * setResults(gov.noaa.nws.ncep.common.dataplugin.editedevents.results.intf.
     * IResults)
     */
    @Override
    public void setResults(IResults results) {
        this.results = (UpdateConsensusResults) results;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * gov.noaa.nws.ncep.common.dataplugin.editedevents.response.intf.IResponse#
     * getResponseTime()
     */
    @Override
    public long getResponseTime() {
        return this.responseTime;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * gov.noaa.nws.ncep.common.dataplugin.editedevents.response.intf.IResponse#
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
     * @see
     * gov.noaa.nws.ncep.common.dataplugin.editedevents.response.intf.IResponse#
     * getProcessingTime()
     */
    @Override
    public long getProcessingTime() {

        return this.processingTime;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * gov.noaa.nws.ncep.common.dataplugin.editedevents.response.intf.IResponse#
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
     * @see
     * gov.noaa.nws.ncep.common.dataplugin.editedevents.response.intf.IResponse#
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
     * gov.noaa.nws.ncep.common.dataplugin.editedevents.response.intf.IResponse#
     * setRequest(gov.noaa.nws.ncep.common.dataplugin.editedevents.request.intf.
     * IRequest)
     */
    @Override
    public void setRequest(IRequest request) {
        this.request = (UpdateConsensusRequest) request;
    }

    /**
     * @return the responseTimeSet
     */
    public boolean isResponseTimeSet() {
        return responseTimeSet;
    }

    /**
     * @param responseTimeSet
     *            the responseTimeSet to set
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
     * @param processingTimeSet
     *            the processingTimeSet to set
     */
    public void setProcessingTimeSet(boolean processingTimeSet) {
        this.processingTimeSet = processingTimeSet;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * gov.noaa.nws.ncep.common.dataplugin.editedevents.response.intf.IResponse#
     * hasErrors()
     */
    @Override
    public boolean hasErrors() {

        if (this.error == null) {
            return false;
        } else {
            return true;
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * gov.noaa.nws.ncep.common.dataplugin.editedevents.response.intf.IResponse#
     * hasResults()
     */
    @Override
    public boolean hasResults() {
        if (this.results == null) {
            return false;
        } else {
            return true;
        }
    }

}
