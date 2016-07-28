package gov.noaa.nws.ncep.edex.plugin.editedevents.commands;

import gov.noaa.nws.ncep.common.dataplugin.editedevents.Event;
import gov.noaa.nws.ncep.common.dataplugin.editedevents.exception.EditedEventsException;
import gov.noaa.nws.ncep.common.dataplugin.editedevents.request.DowngradeEventRequest;
import gov.noaa.nws.ncep.common.dataplugin.editedevents.request.intf.IRequest;
import gov.noaa.nws.ncep.common.dataplugin.editedevents.response.DowngradeEventResponse;
import gov.noaa.nws.ncep.common.dataplugin.editedevents.response.intf.IResponse;
import gov.noaa.nws.ncep.common.dataplugin.editedevents.results.DowngradeEventResults;
import gov.noaa.nws.ncep.common.dataplugin.editedevents.results.intf.IResults;
import gov.noaa.nws.ncep.edex.plugin.editedevents.dao.EventsDao;
import gov.noaa.nws.ncep.common.dataplugin.editedevents.util.EditedEventsConstants;

import java.util.Calendar;

import com.raytheon.uf.common.status.IUFStatusHandler;
import com.raytheon.uf.common.status.UFStatus;

/**
 * 
 * The command class that is executed to downgrade an event.
 * 
 * User downgrades the status of an event. 
 * The following rules apply:
 * 
 * 	1.  A "deleted" event (statuscode = 1) may not be downgraded.
 * 	2.  A "loser" event (statuscode= 2) may not be be downgraded.
 * 	3.  A "contender" event (statuscode = 3), "best of group" event 
 * 		(statusCode = 4), and "sole best" event (statuscode = 5)
 *  	may be downgraded to loser status (statuscode = 2).
 * 
 * 
 * <pre>
 * 
 * SOFTWARE HISTORY
 * 
 * Date         Ticket#    Engineer    Description
 * ------------ ---------- ----------- --------------------------
 * Jan 9, 2016  R9583      sgurung     Initial creation
 * 
 * </pre>
 * 
 * @author sgurung
 * @version 1.0
 */
public class DowngradeEventCommand extends BaseCommand {
	 
	/**
     *  The request from the client that resulted in
     *  creating an instance of the command
     */
    private DowngradeEventRequest request = null;
    
    /**
     * Dao class for Event
     */
    private EventsDao eventDao = null;
    
    /**
     * Begin date time 
     */
    private Calendar beginDTTM = null;
      
    /**
	 * Logger
	 */
	 private static final IUFStatusHandler statusHandler = UFStatus.getHandler(SelectBestEventCommand.class);
	
	
	/**
	 * 
	 */
	public DowngradeEventCommand() {
		// TODO Auto-generated constructor stub
	}

	/* (non-Javadoc)
	 * @see gov.noaa.nws.ncep.edex.plugin.editedevents.commands.intf.ICommand#getError()
	 */
	@Override
	public EditedEventsException getError() {
		return this.error;
	}

	/* (non-Javadoc)
	 * @see gov.noaa.nws.ncep.edex.plugin.editedevents.commands.intf.ICommand#hasError()
	 */
	@Override
	public boolean hasError() {
        if (this.error == null) {
            return false;
        } else {
            return true;
        }
	}

	/* (non-Javadoc)
	 * @see gov.noaa.nws.ncep.edex.plugin.editedevents.commands.intf.ICommand#getStartTime()
	 */
	@Override
	public long getStartTime() {
		return this.startTime;
	}

	/* (non-Javadoc)
	 * @see gov.noaa.nws.ncep.edex.plugin.editedevents.commands.intf.ICommand#getEndTime()
	 */
	@Override
	public long getEndTime() {
		return this.endTime;
	}

	/* (non-Javadoc)
	 * @see gov.noaa.nws.ncep.edex.plugin.editedevents.commands.intf.ICommand#getProcessingTime()
	 */
	@Override
	public long getProcessingTime() {
        long time = 0L;

        time = this.getEndTime() - this.getStartTime();

        return time;
	}

	/* (non-Javadoc)
	 * @see gov.noaa.nws.ncep.edex.plugin.editedevents.commands.intf.ICommand#isValid()
	 * TODO - do we really need to validate the request here...
	 * TODO - the request should be validated before adding it to the command
	 */
	@Override
	public boolean isValid() {
		if (this.request != null && 
				this.request.isValid()) {

			// a request is associated with the command
			// and the request is valid
			return true;

		} else {
			return false;
		}
	}

	/* (non-Javadoc)
	 * @see gov.noaa.nws.ncep.edex.plugin.editedevents.commands.intf.ICommand#setRequest(gov.noaa.nws.ncep.common.dataplugin.editedevents.request.intf.IRequest)
	 */
	@Override
	public void setRequest(IRequest request) {
		this.request = (DowngradeEventRequest) request;
		
	}

	/* (non-Javadoc)
	 * @see gov.noaa.nws.ncep.edex.plugin.editedevents.commands.intf.ICommand#getRequest()
	 */
	@Override
	public IRequest getRequest() {
		return this.request;
	}

	/* (non-Javadoc)
	 * @see gov.noaa.nws.ncep.edex.plugin.editedevents.commands.intf.ICommand#execute()
	 */
	@Override
	public IResponse execute() {
		this.setStartTime();
		
		DowngradeEventResults results = null;
		String message = null;
		Integer eventID = this.request.getEventID();
		Event event = this.request.getEvent();
		event.setId(eventID);
		Integer currentEventStatusCode = event.getStatusCd();
				
		beginDTTM = Calendar.getInstance();
		beginDTTM.clear();
		beginDTTM.setTimeInMillis(this.request.getBeginDTTM());
		
		try {
			eventDao = new EventsDao();
							
			switch (currentEventStatusCode) {
			
				case 1: 
					// Deleted event -- cannot downgrade  					
					message = "You cannot downgrade a deleted event.";
					break;
				case 2: 
					// Loser event -- cannot downgrade 
					message = "You cannot downgrade a loser event.";
					break;
				case 3: 
					// Contender event -- downgrade to loser
				case 4: 
					// Best of group (winner) event -- downgrade to loser
				case 5: 
					// Sole best event -- downgrade to loser
					
					// If it is a composite event -- delete it (with user approval)
					// TODO: Notify user and get approval
					
					if (event.isComposite()) {
						event.setStatusCd(EditedEventsConstants.EVENT_STATUS.DELETED.ordinal() + 1);
						event.setStatusText(EditedEventsConstants.EVENT_STATUS.DELETED.toString());
						event.setAge(EditedEventsConstants.EVENT_AGE.DEL.toString());
					} else {
						event.setStatusCd(EditedEventsConstants.EVENT_STATUS.LOSER.ordinal() + 1);
						event.setStatusText(EditedEventsConstants.EVENT_STATUS.LOSER.toString());
					}
					break;
				default:
					break;
			}		
			
			event.setChangeFlag(1);
			eventDao.saveOrUpdate(event);
			
		} catch (Exception e) {
			String errorMsg = "ERROR - Exception Occured When Executing DowngradeEventCommand";
			EditedEventsException exception = new EditedEventsException(errorMsg);
			exception.setStackTrace(e.getStackTrace());
			this.error = exception;
		} 
		
		this.setEndTime();
        
        return this.createResponse(results, message);
	}
	
	/**
	 * @param results
	 * @return IResponse
	 */
	private IResponse createResponse(IResults results, String message) { 
		DowngradeEventResponse response = new DowngradeEventResponse();

        if (this.hasError()) {
        	response.setError(this.getError());
        } else {
        	response.setResults(results); 
        	response.setMessage(message);
        }

        // populate the response and the results
        response.setRequest(this.getRequest()); 
        response.setProcessingTime(this.getProcessingTime());
        
        return response;
		
	}	
	
}
