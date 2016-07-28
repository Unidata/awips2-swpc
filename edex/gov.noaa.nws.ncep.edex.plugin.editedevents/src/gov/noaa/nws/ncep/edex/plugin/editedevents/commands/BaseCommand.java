package gov.noaa.nws.ncep.edex.plugin.editedevents.commands;

import gov.noaa.nws.ncep.common.dataplugin.editedevents.exception.EditedEventsException;
import gov.noaa.nws.ncep.edex.plugin.editedevents.commands.intf.ICommand;

/**
 * The Base Command class from which all Edited Event commands
 * must extend
 *  
 * <pre>
 * 
 * SOFTWARE HISTORY
 * 
 * Date         Ticket#    Engineer    Description
 * ------------ ---------- ----------- --------------------------
 * Jan 9, 2016  R9583      jtravis     Initial creation
 * 
 * </pre>
 * 
 * @author jtravis
 * @version 1.0
 */
public abstract class BaseCommand implements ICommand {
	
	/**
	 * store the time the execute method on the command was called
	 */
	protected Long startTime = null;
	
	/**
	 * store the time the execute method on the command completed
	 */
	protected Long endTime = null;
	
	/**
	 * error
	 */
	protected EditedEventsException error = null;
	
	/**
	 * Set the time the execute method on the command completed
	 */
	protected void setEndTime() {
		if (endTime == null) {
			this.endTime = System.currentTimeMillis();
		}
	}
	
	/**
	 * Set the time the execute method on the command was called
	 */
	protected void setStartTime() {
		if (startTime == null) {
			this.startTime = System.currentTimeMillis();
		}
	}
	
	/**
	 * Add an exception to the command
	 * 
	 * @param e
	 */
	protected void setError(EditedEventsException e) {
		this.error = e;
	}	

}
