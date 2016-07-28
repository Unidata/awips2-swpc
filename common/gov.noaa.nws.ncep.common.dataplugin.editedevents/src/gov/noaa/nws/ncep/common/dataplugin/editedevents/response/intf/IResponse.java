package gov.noaa.nws.ncep.common.dataplugin.editedevents.response.intf;


import gov.noaa.nws.ncep.common.dataplugin.editedevents.exception.EditedEventsException;
import gov.noaa.nws.ncep.common.dataplugin.editedevents.request.intf.IRequest;
import gov.noaa.nws.ncep.common.dataplugin.editedevents.results.intf.IResults;

/**
 * Common interface for a Response classes
 *
 *  <pre>
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

	public EditedEventsException getError();
	public void setError(EditedEventsException exception);
	public void setMessage(String message);
	
	/**
	 * Obtain the results produced when executing the command
	 * 
	 * @return IResults
	 */
	public IResults getResults();
	
	public void setResults(IResults results);
	public long getResponseTime();
	public void setResponseTime(long timeInMillis);
	public long getProcessingTime();
	public void setProcessingTime(long timeInMillis);
	public IRequest getRequest();
	public void setRequest(IRequest request);
	public boolean hasErrors();
	
	/**
	 * Check if the command has results
	 * 
	 * @return boolean
	 */
	public boolean hasResults();
	
}