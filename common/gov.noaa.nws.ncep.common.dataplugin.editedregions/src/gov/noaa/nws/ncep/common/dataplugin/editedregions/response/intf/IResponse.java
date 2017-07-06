package gov.noaa.nws.ncep.common.dataplugin.editedregions.response.intf;

import gov.noaa.nws.ncep.common.dataplugin.editedregions.exception.EditedRegionsException;
import gov.noaa.nws.ncep.common.dataplugin.editedregions.request.intf.IRequest;
import gov.noaa.nws.ncep.common.dataplugin.editedregions.results.intf.IResults;

/**
 * Common interface for a Response classes
 *
 * <pre>
 * 
 * SOFTWARE HISTORY
 * 
 * Date         Ticket#    Engineer    Description
 * ------------ ---------- ----------- --------------------------
 * Mar 22, 2016 R9583      jtravis     Initial creation
 * 
 * </pre>
 * 
 * @author jtravis
 * @version 1.0
 */
public interface IResponse {

    public EditedRegionsException getError();

    public void setError(EditedRegionsException exception);

    public void setMessage(String message);

    /**
     * Obtain the results produced when executing the command
     * 
     * @return IResults
     */
    public IResults getResults();

    /**
     * @param results
     */
    public void setResults(IResults results);

    /**
     * @return long
     */
    public long getResponseTime();

    /**
     * @param timeInMillis
     */
    public void setResponseTime(long timeInMillis);

    /**
     * @return long
     */
    public long getProcessingTime();

    /**
     * @param timeInMillis
     */
    public void setProcessingTime(long timeInMillis);

    /**
     * @return IRequest
     */
    public IRequest getRequest();

    /**
     * @param request
     */
    public void setRequest(IRequest request);

    /**
     * @return boolean
     */
    public boolean hasErrors();

    /**
     * Check if the command has results
     * 
     * @return boolean
     */
    public boolean hasResults();

}