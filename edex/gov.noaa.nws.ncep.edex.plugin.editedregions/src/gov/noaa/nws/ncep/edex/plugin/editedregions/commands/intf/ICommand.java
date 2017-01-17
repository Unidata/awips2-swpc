package gov.noaa.nws.ncep.edex.plugin.editedregions.commands.intf;

import gov.noaa.nws.ncep.common.dataplugin.editedevents.exception.EditedEventsException;
import gov.noaa.nws.ncep.common.dataplugin.editedevents.request.intf.IRequest;
import gov.noaa.nws.ncep.common.dataplugin.editedevents.response.intf.IResponse;

/**
 * The Command Interface
 * 
 * <pre>
 * 
 * SOFTWARE HISTORY
 * 
 * Date         Ticket#    Engineer    Description
 * ------------ ---------- ----------- --------------------------
 * Dec 19, 2016  R9583     jtravis     Initial creation
 * 
 * </pre>
 * 
 * @author jtravis
 * @version 1.0
 */
public interface ICommand {

	/**
	 * Obtain the error that occurred when executing the command
	 * 
	 * @return EditedEventsException
	 */
	public EditedEventsException getError();
		
	/**
	 * Check if the command has an error
	 * 
	 * @return boolean
	 */
	public boolean hasError();
	/**
	 * Obtain the time when the execute method is called
	 * 
	 * @return long
	 */
	public long getStartTime();
	/**
	 * Obtain the time when the execute method completes
	 * 
	 * @return long
	 */
	public long getEndTime();
	/**
	 * Obtain the number of milliseconds it took to execute the command
	 * 
	 * @return long
	 */
	public long getProcessingTime();
	/**
	 * Determine if the command is valid
	 * 
	 * @return boolean
	 */
	public boolean isValid();
	/**
	 * Add the request to the command so the command can read parameters
	 * required by the command directly from the request
	 * 
	 * @param request
	 */
	public void setRequest(IRequest request);
	/**
	 * Obtain the request the command used for any input parameters
	 * 
	 * @return IRequest
	 */
	public IRequest getRequest();
	/**
	 * Have the command actually do what its name suggests
	 */
	public IResponse execute();
	
}
