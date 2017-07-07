package gov.noaa.nws.ncep.edex.plugin.editedregions.commands;

import gov.noaa.nws.ncep.common.dataplugin.editedregions.exception.EditedRegionsException;
import gov.noaa.nws.ncep.edex.plugin.editedregions.commands.intf.ICommand;

/**
 * The Base Command class from which all Edited Regions commands must extend.
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
    protected EditedRegionsException error = null;

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
    protected void setError(EditedRegionsException e) {
        this.error = e;
    }

}
