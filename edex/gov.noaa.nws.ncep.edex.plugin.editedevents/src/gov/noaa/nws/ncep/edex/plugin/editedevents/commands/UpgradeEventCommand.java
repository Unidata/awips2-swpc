package gov.noaa.nws.ncep.edex.plugin.editedevents.commands;

import gov.noaa.nws.ncep.common.dataplugin.editedevents.Event;
import gov.noaa.nws.ncep.common.dataplugin.editedevents.exception.EditedEventsException;
import gov.noaa.nws.ncep.common.dataplugin.editedevents.request.UpgradeEventRequest;
import gov.noaa.nws.ncep.common.dataplugin.editedevents.request.intf.IRequest;
import gov.noaa.nws.ncep.common.dataplugin.editedevents.response.UpgradeEventResponse;
import gov.noaa.nws.ncep.common.dataplugin.editedevents.response.intf.IResponse;
import gov.noaa.nws.ncep.common.dataplugin.editedevents.results.UpgradeEventResults;
import gov.noaa.nws.ncep.common.dataplugin.editedevents.results.intf.IResults;
import gov.noaa.nws.ncep.edex.plugin.editedevents.dao.EventsDao;
import gov.noaa.nws.ncep.common.dataplugin.editedevents.util.EditedEventsConstants;

import java.util.Calendar;
import java.util.List;

import com.raytheon.uf.common.status.IUFStatusHandler;
import com.raytheon.uf.common.status.UFStatus;

/**
 * 
 * The command class that is executed to upgrade an event.
 * 
 * User upgrades the status of an event (to best). 
 * The following rules apply:
 * 
 * 	1.  A "deleted" event (statuscode = 1) may not be upgraded, 
 * 		unless it is a composite event
 * 	2.  A "loser" event (statuscode= 2) will be upgraded to best; 
 * 		contenders and/or best events of same type in that bin
 * 		will be downgraded to losers (set statuscode = 2). If there are other events of 
 * 		the same type as the upgraded event will be best of group (set statuscode = 4),
 * 		else sole best (set statuscode = 5)
 * 	3.  A "contender" event (statuscode = 3) will be upgraded to best of group.
 * 		Other contenders and/or best events of same type in that bin will be
 * 		downgraded to losers (set statuscode = 2).
 * 	4.  A best of group will be upgraded to sole best if no other event of that type
 * 		in the bin are found.
 * 
 * <pre>
 * 
 * SOFTWARE HISTORY
 * 
 * Date         Ticket#    Engineer    Description
 * ------------ ---------- ----------- --------------------------
 * Mar 19, 2016  R9583     sgurung     Initial creation
 * 
 * </pre>
 * 
 * @author sgurung
 * @version 1.0
 */
public class UpgradeEventCommand extends BaseCommand {
	 
	/**
     *  The request from the client that resulted in
     *  creating an instance of the command
     */
    private UpgradeEventRequest request = null;
    
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
	public UpgradeEventCommand() {
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
		this.request = (UpgradeEventRequest) request;
		
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
		
		UpgradeEventResults results = null;
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
			
			List<Event> events = eventDao.getEventsForUpgradeCommand(beginDTTM, event.getBin(), event.getCodedType(), eventID);
						
			switch (currentEventStatusCode) {
			
				case 1: // Deleted  					
					handleDeletedEvent(event, events);
					break;
				case 2: // Loser 
					handleLoserEvent(event, events);						
					break;
				case 3: // Contender
					handleContenderEvent(event, events);					
					break;
				case 4: // Winner
					message = handleWinnerEvent(event, events);
					break;
				case 5: // Sole best
					// Can't upgrade sole best
					// TODO: Notify user
					// You may not upgrade a sole best event!	
					message = "You may not upgrade a sole best event!";
					break;
				default:
					break;
			}			
			
		} catch (Exception e) {
			String errorMsg = "ERROR - Exception Occured When Executing UpgradeEventCommand";
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
		UpgradeEventResponse response = new UpgradeEventResponse();

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
	
	/**
	 * Handle the case when the current event is a "deleted" event ((statuscode = 1)).
	 * 
	 * A "deleted" event may not be upgraded, unless it is a composite event
	 * 
	 * @param event
	 * @param events
	 */
	private void handleDeletedEvent(Event event, List<Event> events) {
		
		int eventsSize = (events != null) ? events.size() : 0;
		
		// Deleted event cannot be upgraded unless it is a composite event
		if (event.isComposite()) {
			
			if (eventsSize == 0) {
				// If no events found of same type in the bin, make 
				// the composite event the solebest
				event.setStatusCd(EditedEventsConstants.EVENT_STATUS.SOLEBEST.ordinal() + 1);
				event.setStatusText(EditedEventsConstants.EVENT_STATUS.SOLEBEST.toString());
				event.setAge(EditedEventsConstants.EVENT_AGE.NEW.toString());
				event.setChangeFlag(1);
				
			} else {
				// If other events found of same type in the bin, make 
				// other events losers and the composite event the winner 
				
				for (int i=0; i<eventsSize; i++) {
					
					Event e = events.get(i);
					e.setStatusCd(EditedEventsConstants.EVENT_STATUS.LOSER.ordinal() + 1);
					e.setStatusText(EditedEventsConstants.EVENT_STATUS.LOSER.toString());
					e.setChangeFlag(1);
					eventDao.saveOrUpdate(e);
				}
				
				event.setStatusCd(EditedEventsConstants.EVENT_STATUS.WINNER.ordinal() + 1);
				event.setStatusText(EditedEventsConstants.EVENT_STATUS.WINNER.toString());
				event.setAge(EditedEventsConstants.EVENT_AGE.NEW.toString());
				event.setChangeFlag(1);
				eventDao.saveOrUpdate(event);
			}
			
			// If optical event -- add region number of bin and events in bin						
			
			// The AssignRegionToBinCommand performs the logic
			// that is found in the legacy applications
			// OptRpt() function.
			AssignRegionToBinCommand cmd = new AssignRegionToBinCommand();
			cmd.setBeginDTTM(beginDTTM);
			cmd.setEvent(event);
			cmd.execute();
		}
	}
	
	/**
	 * Handle the case when the current event is a "loser" (statuscode = 2).
	 * 
	 * A "loser" event will be upgraded to best; contenders and/or best events 
	 * of same type in that bin will be downgraded to losers (set statuscode = 2). 
	 * If there are other events of the same type as the upgraded event will be
	 * best of group (set statuscode = 4), else sole best (set statuscode = 5).
	 * 
	 * @param event
	 * @param events
	 */
	private void handleLoserEvent(Event event, List<Event> events) {
		
		int eventsSize = (events != null) ? events.size() : 0;
		
		// Loser -- upgrade to best; check for other contenders
		// or best event and downgrade them
		
		int numNonCompositeEvents = 0;
		
		if (eventsSize > 0) {
			
			for (int i=0; i<eventsSize; i++) {
				
				Event e = events.get(i);
				
				// If a composite event is among contenders/best,
				// notify user it will be deleted (flag to be deleted by setting age=DEL) 
				// and provide opportunity to back out
				// TODO Notify user and get user input to confirm deletion -- find a way to do this 
				if (e.isComposite()) {							
					e.setStatusCd(EditedEventsConstants.EVENT_STATUS.DELETED.ordinal() + 1);
					e.setStatusText(EditedEventsConstants.EVENT_STATUS.DELETED.toString());
					e.setChangeFlag(1);
					e.setAge(EditedEventsConstants.EVENT_AGE.DEL.toString());
					eventDao.saveOrUpdate(e);
				} else {
					numNonCompositeEvents = numNonCompositeEvents + 1;
					
					// Make other contenders/bet events losers
					e.setStatusCd(EditedEventsConstants.EVENT_STATUS.LOSER.ordinal() + 1);
					e.setStatusText(EditedEventsConstants.EVENT_STATUS.LOSER.toString());
					e.setChangeFlag(1);
					eventDao.saveOrUpdate(e);
				}
			}
			
			if (numNonCompositeEvents == 0) {
				event.setStatusCd(EditedEventsConstants.EVENT_STATUS.SOLEBEST.ordinal() + 1);
				event.setStatusText(EditedEventsConstants.EVENT_STATUS.SOLEBEST.toString());							
			} else {
				event.setStatusCd(EditedEventsConstants.EVENT_STATUS.WINNER.ordinal() + 1);
				event.setStatusText(EditedEventsConstants.EVENT_STATUS.WINNER.toString());
			}

			eventDao.saveOrUpdate(event);
			
			// If optical event -- add region number of bin and events in bin						
			
			// The AssignRegionToBinCommand performs the logic
			// that is found in the legacy applications
			// OptRpt() function.
			AssignRegionToBinCommand cmd = new AssignRegionToBinCommand();
			cmd.setBeginDTTM(beginDTTM);
			cmd.setEvent(event);
			cmd.execute();
		}
	}
	
	/**
	 * 
	 * Handle the case when the current event is a "contender" (statuscode = 3).
	 * 
	 * A "contender" event (statuscode = 3) will be upgraded to best of group.
	 * Other contenders and/or best events of same type in that bin will be
	 * downgraded to losers (set statuscode = 2).
	 * 
	 * @param event
	 * @param events
	 */
	private void handleContenderEvent(Event event, List<Event> events) {
		
		int eventsSize = (events != null) ? events.size() : 0;
		
		// Contender -- upgrade to best of group -- need change flag
		event.setStatusCd(EditedEventsConstants.EVENT_STATUS.WINNER.ordinal() + 1);
		event.setStatusText(EditedEventsConstants.EVENT_STATUS.WINNER.toString());
		event.setChangeFlag(1);
		eventDao.saveOrUpdate(event);
		
		// Find other events of that type, bin that are contenders
		// or best and downgrade them to loser status (2)
			
		if (eventsSize > 0) {
			
			for (int i=0; i<eventsSize; i++) {
				
				Event e = events.get(i);
				e.setStatusCd(EditedEventsConstants.EVENT_STATUS.LOSER.ordinal() + 1);
				e.setStatusText(EditedEventsConstants.EVENT_STATUS.LOSER.toString());
				e.setChangeFlag(1);
				eventDao.saveOrUpdate(e);
			}
		}		
		
		// If optical event -- add region number of bin and events in bin						
		
		// The AssignRegionToBinCommand performs the logic
		// that is found in the legacy applications
		// OptRpt() function.
		AssignRegionToBinCommand cmd = new AssignRegionToBinCommand();
		cmd.setBeginDTTM(beginDTTM);
		cmd.setEvent(event);
		cmd.execute();
	}
	
	/**
	 * Handle the case when the current event is a winner (statuscode = 4).
	 * 
	 * A best of group will be upgraded to sole best if no other event of that 
	 * type	in the bin are found.
	 * 
	 * @param event
	 * @param events
	 */
	private String handleWinnerEvent(Event event, List<Event> events) {
		
		String message = null;
		int eventsSize = (events != null) ? events.size() : 0;
		
		// Best of group -- if there are no other events of that type
		// in that bin then upgrade to sole best
		
		if (eventsSize > 0) {
			
			// TODO: Notify user
			// This event cannot be upgraded to 'sole best'
			// because there are other events of the same type
			// in this bin	
			message = "This event cannot be upgraded to 'sole best' because there are other events of the same type in the bin";
		} else {
			// No other events -- go ahead and upgrade
			
			event.setStatusCd(EditedEventsConstants.EVENT_STATUS.SOLEBEST.ordinal() + 1);
			event.setStatusText(EditedEventsConstants.EVENT_STATUS.SOLEBEST.toString());
			event.setChangeFlag(1);
			eventDao.saveOrUpdate(event);
		}
		
		return message;
		
	}

}
