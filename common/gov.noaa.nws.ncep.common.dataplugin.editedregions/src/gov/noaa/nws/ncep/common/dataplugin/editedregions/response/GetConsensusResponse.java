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
import gov.noaa.nws.ncep.common.dataplugin.editedregions.request.GetConsensusRequest;
import gov.noaa.nws.ncep.common.dataplugin.editedregions.request.intf.IRequest;
import gov.noaa.nws.ncep.common.dataplugin.editedregions.response.intf.IResponse;
import gov.noaa.nws.ncep.common.dataplugin.editedregions.results.GetConsensusFinalResults;
import gov.noaa.nws.ncep.common.dataplugin.editedregions.results.GetConsensusTodaysResults;
import gov.noaa.nws.ncep.common.dataplugin.editedregions.results.GetConsensusYesterdaysResults;
import gov.noaa.nws.ncep.common.dataplugin.editedregions.results.intf.IResults;

/**
 * The response class holding the results obtained from the execution of the
 * GetRegions command
 * 
 * 
 * @author
 * @version 1.0
 */
@DynamicSerialize
public class GetConsensusResponse extends BaseResponse<GetConsensusResponse>
        implements IResponse {

    /**
     * The original request
     */
    @DynamicSerializeElement
    private GetConsensusRequest request = null;

    /**
     * The error
     */
    @DynamicSerializeElement
    private EditedRegionsException error = null;

    /**
     * Yesterdays consensus results
     */
    @DynamicSerializeElement
    private GetConsensusYesterdaysResults yesterdaysConsensusResults = null;

    /**
     * Todays consensus results
     */
    @DynamicSerializeElement
    private GetConsensusTodaysResults todaysConsensusResults = null;

    /**
     * Final consensus results
     */
    @DynamicSerializeElement
    private GetConsensusFinalResults finalConsensusResults = null;

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
    public GetConsensusResponse() {
        // TODO Auto-generated constructor stub
    }

    /*
     * (non-Javadoc)
     * 
     * @see gov.noaa.nws.ncep.common.dataplugin.editedevents.response.IResponse#
     * getError ()
     */
    @Override
    public EditedRegionsException getError() {
        return this.error;
    }

    /*
     * (non-Javadoc)
     * 
     * @see gov.noaa.nws.ncep.common.dataplugin.editedevents.response.IResponse#
     * setError (gov.noaa.nws.ncep.common.dataplugin.editedevents.exception.
     * EditedEventsException)
     */
    @Override
    public void setError(EditedRegionsException exception) {
        this.error = exception;
    }

    /*
     * (non-Javadoc)
     * 
     * TODO this method does not retrieve any value as there are multiple result
     * types TODO use getter specific to the result type
     * 
     * TODO this must be corrected...its bad design!
     * 
     * @see gov.noaa.nws.ncep.common.dataplugin.editedevents.response.IResponse#
     * getResults()
     */
    @Override
    public IResults getResults() {
        return null;
    }

    public IResults getYesterdaysConsensusResults() {
        return this.yesterdaysConsensusResults;
    }

    public IResults getTodaysConsensusResults() {
        return this.yesterdaysConsensusResults;
    }

    public IResults getFinalConsensusResults() {
        return this.yesterdaysConsensusResults;
    }

    /*
     * (non-Javadoc)
     * 
     * TODO this method does not set any value as there are multiple result
     * types TODO use setter specific to the result type
     * 
     * TODO correct this method as this is bad design!
     * 
     * @see
     * gov.noaa.nws.ncep.common.dataplugin.editedevents.response.intf.IResponse
     * #setResults
     * (gov.noaa.nws.ncep.common.dataplugin.editedevents.results.intf.IResults)
     */
    @Override
    public void setResults(IResults results) {
        // possibly use reflection and then call the appropriate setter listed
        // below
        ;
        ;
    }

    public void setYesterdaysConsensusResults(IResults results) {
        this.yesterdaysConsensusResults = (GetConsensusYesterdaysResults) results;
    }

    public void setTodaysConsesusResults(IResults results) {
        this.todaysConsensusResults = (GetConsensusTodaysResults) results;
    }

    public void setFinalResults(IResults results) {
        this.finalConsensusResults = (GetConsensusFinalResults) results;
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
     * @see gov.noaa.nws.ncep.common.dataplugin.editedevents.response.IResponse#
     * hasErrors ()
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
        this.request = (GetConsensusRequest) request;
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

        if (this.yesterdaysConsensusResults == null
                && this.todaysConsensusResults == null
                && this.finalConsensusResults == null) {

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
     * setMessage(java.lang.String)
     */
    @Override
    public void setMessage(String message) {
        this.message = message;
    }

}
