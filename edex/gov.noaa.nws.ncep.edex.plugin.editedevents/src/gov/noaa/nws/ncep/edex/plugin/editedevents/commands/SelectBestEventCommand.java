package gov.noaa.nws.ncep.edex.plugin.editedevents.commands;

import gov.noaa.nws.ncep.common.dataplugin.editedevents.Event;
import gov.noaa.nws.ncep.common.dataplugin.editedevents.exception.EditedEventsException;
import gov.noaa.nws.ncep.common.dataplugin.editedevents.request.SelectBestEventRequest;
import gov.noaa.nws.ncep.common.dataplugin.editedevents.request.intf.IRequest;
import gov.noaa.nws.ncep.common.dataplugin.editedevents.response.SelectBestEventResponse;
import gov.noaa.nws.ncep.common.dataplugin.editedevents.response.intf.IResponse;
import gov.noaa.nws.ncep.common.dataplugin.editedevents.results.SelectBestEventResults;
import gov.noaa.nws.ncep.common.dataplugin.editedevents.util.EditedEventsConstants;
import gov.noaa.nws.ncep.common.dataplugin.editedevents.util.EventCodes;
import gov.noaa.nws.ncep.common.dataplugin.editedevents.util.SweepType;
import gov.noaa.nws.ncep.common.swpcrefdb.Satellite;
import gov.noaa.nws.ncep.edex.plugin.editedevents.Observatory;
import gov.noaa.nws.ncep.edex.plugin.editedevents.dao.EventsDao;
import gov.noaa.nws.ncep.edex.swpcrefdb.dao.SatelliteTrackingStatusDao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Vector;

import com.raytheon.uf.common.status.IUFStatusHandler;
import com.raytheon.uf.common.status.UFStatus;
import com.raytheon.uf.common.status.UFStatus.Priority;
import com.raytheon.uf.edex.database.DataAccessLayerException;

/**
 * A sub-command that is called by the ProcessEvents command
 * 
 * The purpose of this sub-command is to determine which event among duplicate
 * events is the "best" event.
 * 
 * <pre>
 *
 * SOFTWARE HISTORY
 *
 * Date         Ticket#    Engineer    Description
 * ------------ ---------- ----------- --------------------------
 * Feb 24, 2016  R9583     jtravis     Initial creation
 *
 * </pre>
 *
 * @author jtravis
 * @version 1.0	
 */
public class SelectBestEventCommand extends BaseCommand {

	private SelectBestEventRequest request = null;

	/**
	 * Dao for Event records
	 */
	private EventsDao eventDao = null;
	
	/**
	 * Dao for SatelliteTrackingStatus
	 */
	private SatelliteTrackingStatusDao satelliteTrackingStatusDao = null;

	/**
	 * Begin Date/time
	 */
	private Calendar beginDTTM = null;

	public static enum BestAction {
		SOLEBEST, NEWWIN, NEWLOSE, DRAW
	}
	
	/**
	 * HasMap of observatories which specify the
	 * 3-Letter identifiers and local noon values for each observatory
	 */
	private HashMap<String, Observatory> observatories = null; 
	
	/**
	 * Logger
	 */
	 private static final IUFStatusHandler statusHandler = UFStatus.getHandler(SelectBestEventCommand.class);
	
	/**
	 * 
	 */
	public SelectBestEventCommand() {
		
		this.observatories = new HashMap<String, Observatory>();
		
		this.observatories.put("SVI", new Observatory("SVI",1048, true));
		this.observatories.put("BOU", new Observatory("BOU",-1, true));
		this.observatories.put("CUL", new Observatory("CUL",204, true));
		this.observatories.put("G08", new Observatory("G08",-1, true));
		this.observatories.put("G10", new Observatory("G10",-1, true));
		this.observatories.put("G12", new Observatory("G12",-1, true));
		this.observatories.put("HOL", new Observatory("HOL",1904, true));
		this.observatories.put("LEA", new Observatory("LEA",420, true));
		this.observatories.put("PAL", new Observatory("PAL",2232, true));
		this.observatories.put("RAM", new Observatory("RAM",-1, true));
		this.observatories.put("SAG", new Observatory("SAG",1640, true));
		this.observatories.put("IZM", new Observatory("IZM",-1, true));
		this.observatories.put("G09", new Observatory("G09",-1, true));
		this.observatories.put("G11", new Observatory("G11" ,-1, true));
		
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * gov.noaa.nws.ncep.edex.plugin.editedevents.commands.intf.ISubCommand#
	 * getError()
	 */
	@Override
	public EditedEventsException getError() {
		return this.error;
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * gov.noaa.nws.ncep.edex.plugin.editedevents.commands.intf.ISubCommand#
	 * hasError()
	 */
	@Override
	public boolean hasError() {
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
	 * gov.noaa.nws.ncep.edex.plugin.editedevents.commands.intf.ISubCommand#
	 * isValid()
	 */
	@Override
	public boolean isValid() {
		if (this.request != null && this.request.isValid()) {

			// a request is associated with the command
			// and the request is valid
			return true;

		} else {
			return false;
		}
	}


	/* Select the best of each type of event in each bin
	 * 
	 * (non-Javadoc)
	 * @see gov.noaa.nws.ncep.edex.plugin.editedevents.commands.intf.ICommand#execute()
	 */
	@Override
	public IResponse execute() {

		statusHandler.handle(Priority.INFO, " EDITEDEVENTS.SelectBestCommand... ");

		this.setStartTime();
		
		beginDTTM = Calendar.getInstance();
		beginDTTM.clear();
		beginDTTM.setTimeInMillis(this.request.getBeginDTTM());
		
		try {

			this.eventDao = new EventsDao();
			this.satelliteTrackingStatusDao = new SatelliteTrackingStatusDao();

			List<Event> events = null;
			
			switch (this.request.getOrigin()) {
				case PROCESS_EVENTS:
					events = eventDao.getEventsForBestSelectionForProcessEvents(beginDTTM);
					break;
				case REBIN_EVENT:
					events = eventDao.getEventsForBestSelectionForRebinEvent(this.request.getEventID());
					break;
				case REVISIT_OLD_BIN:
					events = eventDao.getEventsForBestSelectionForRevisitOldBin(beginDTTM, this.request.getEventBin(), this.request.getEventCodedType());
					break;
				default:
					break;
			}
			
			int eventsSize = (events != null) ? events.size() : 0;
			
			statusHandler.handle(Priority.INFO, " EDITEDEVENTS.SelectBestCommand... eventsSize = " + eventsSize);

			for (int i = 0; i < eventsSize; i++) {

				this.selectBest(events.get(i));
			}

		} catch (Exception e) {
			error = new EditedEventsException(
					"ERROR - Exception occured while executing the SelectBestEventCommand.",e);
			e.printStackTrace();
		}
		
		this.setEndTime();
	
		return this.createResponse();

	}
	

	/**
	 * Create response 
	 * 
	 * @return IResponse
	 */
	private IResponse createResponse() {
		
		SelectBestEventResponse response = new SelectBestEventResponse();

        if (this.hasError()) {
        	response.setError(this.getError());
        } else {
        	response.setResults(new SelectBestEventResults());
        	response.setMessage("SelectBestEventCommand executed successfully"); 
        }

        // populate the response 
        response.setRequest(this.getRequest()); 
        response.setProcessingTime(this.getProcessingTime());
        
        return response;
		
	}

	/**
	 * Find the "best" event of each type within each bin. If sole best, then
	 * mark it and return. If other events of same type in bin, then compare the
	 * two events (new and current best).
	 * 
	 * @param newEvent
	 * @throws DataAccessLayerException
	 * @throws SQLException 
	 */
	private void selectBest(Event newEvent) throws DataAccessLayerException, SQLException {
		
		statusHandler.handle(Priority.INFO, " EDITEDEVENTS.SelectBestEventCommand.selectBest()... ");

		// See if there is a best event of the same type in the same bin
		List<Event> bestEvents = eventDao.getBestEvents(beginDTTM,
				newEvent.getBin(), newEvent.getCodedType(), newEvent.getId());
		
		int eventsSize = (bestEvents != null) ? bestEvents.size() : 0;
		
		statusHandler.handle(Priority.INFO, " EDITEDEVENTS.SelectBestEventCommand.selectBest()... eventsSize = " + eventsSize);

		if (eventsSize == 0) {
			// New event is sole best
			// TODO the event status should be meaningful...if its sole best value suppled should be "SOLE-BEST"
			// TODO client can display the event status however it wants but the db should have something meaningful
			
			this.setEventStatus(BestAction.SOLEBEST, bestEvents, null, newEvent);
		} else {
			
			statusHandler.handle(Priority.INFO, " EDITEDEVENTS.SelectBestEventCommand.selectBest()... else ");
			
			// Competitors exist, select winner
			
			if (newEvent.isComposite()) {
				
				//For a composite event, set the status of other events to '-' (loser) and the 
				//composite event status to '+' (winner/best of group).
				this.setEventStatusForCompositeEvent(bestEvents, newEvent);
				
			} else {
				// Compare events and select the best based on the criteria for the
				// codedtype
				
				BestAction bestAction = null;
				
				for (int i = 0; i < eventsSize; i++) {
					
					Event bestEvent = bestEvents.get(i);
					
					switch (newEvent.getCodedType()) {
	
					// CodedType = 1,2,60, 61 => GOES events
					case 1:
					case 2:
					case 60:
					case 61:
						// Primary satellite wins
						bestAction = this.compareGoesEvents(newEvent, bestEvent); 
						break;
					// CodedType = 10, 20, 21, 22, 23, 24, 29, 200 => Optical events
					case 10:
					case 20:
					case 21:
					case 22:
					case 23:
					case 24:
					case 29:
					case 200:
						bestAction = this.compareOpticalEvents(newEvent, bestEvent);
						break;
					// CodedType = 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42 =>
					// Bursts events
					case 30:
					case 31:
					case 32:
					case 33:
					case 34:
					case 35:
					case 36:
					case 37:
					case 38:
					case 39:
					case 40:
					case 41:
					case 42:
						bestAction = this.compareBurstEvents(newEvent, bestEvent);
						break;
					// CodedType = 50, 51, 52, 53, 54, 55, 56 => Sweeps events
					case 50:
					case 51:
					case 52:
					case 53:
					case 54:
					case 55:
					case 56:
						bestAction = this.compareSweepEvents(newEvent, bestEvent);
						break;
					default:
						bestAction = BestAction.DRAW;
						break;
	
					}
	
					this.setEventStatus(bestAction, bestEvents, bestEvent, newEvent);
				}
			}
		}
		

		AssignRegionToBinCommand cmd = new AssignRegionToBinCommand(newEvent, this.beginDTTM);
		cmd.execute();
	}
	
	
	/**
	 * Tie-breaker when action == draw
	 * need to count the time qualifiers in the
	 * event
	 * 
	 * @param event
	 * 
	 * @return int
	 */
	private int countTimeQualifiers(Event event) {
		
		int timeQualifiers = 0;

		if (event.getBegInq() != null) {
			timeQualifiers++;
		}
		
		if (event.getMaxq() != null) {
			timeQualifiers++;
		}
		
		if (event.getEndq() != null) {
			timeQualifiers++;
		}
		
		return timeQualifiers;
	}

	/**
	 * For a composite event, if there are other events with the same codedType
	 * as the composite event, set their status to '-' (loser) and the 
	 * composite event status to '+' (winner/best of group).
	 * 
	 * @param action
	 * @param otherEvents
	 * @param compositeEvent
	 */	
	private void setEventStatusForCompositeEvent(List<Event> otherEvents, Event compositeEvent) {
		
		List<Event> eventsToUpdate = new ArrayList<Event>();
		
		this.updateEventStatus(compositeEvent, EditedEventsConstants.EVENT_STATUS.WINNER.ordinal() + 1, EditedEventsConstants.EVENT_STATUS.WINNER.toString(), 1);
		eventsToUpdate.add(compositeEvent);
		
		for (int i = 0; i < otherEvents.size(); i++) {
			Event event = otherEvents.get(i);

			this.updateEventStatus(event, EditedEventsConstants.EVENT_STATUS.LOSER.ordinal() + 1, EditedEventsConstants.EVENT_STATUS.LOSER.toString(), 1);
			eventsToUpdate.add(event);
		}

		// Do a batch update to update the event status
		if (eventsToUpdate.size() > 0) {
			eventDao.persistAll(eventsToUpdate);
		}
		
	}
	/**
	 * Perform the action resulting from a select best comparison of two events.
	 * Action to take: 1) solebest -- no other best events exist, make the new
	 * event best of group, 2) newwin -- make new event best of group and
	 * current best a loser, 3) newlose -- make new event a loser and ensure
	 * current best is best of group 4) draw -- make both events contenders
	 * 
	 * @param action
	 * @param bestEvents
	 * @param oldBestEvent
	 * @param newEvent
	 */	
	private void setEventStatus(BestAction action,
			List<Event> bestEvents, Event oldBestEvent, Event newEvent) {
		
		List<Event> eventsToUpdate = new ArrayList<Event>();

		// The following switch case statement is related to setting the
		// status code and status text for the event

		switch (action) {
			case SOLEBEST:
				// Make the new event the sole best
				this.updateEventStatus(newEvent, EditedEventsConstants.EVENT_STATUS.SOLEBEST.ordinal() + 1, EditedEventsConstants.EVENT_STATUS.SOLEBEST.toString(), 1);
				eventsToUpdate.add(newEvent);
				break;
			case NEWWIN:
				// Make the new event best of group
	
				this.updateEventStatus(newEvent, EditedEventsConstants.EVENT_STATUS.WINNER.ordinal() + 1, EditedEventsConstants.EVENT_STATUS.WINNER.toString(), 1);
				eventsToUpdate.add(newEvent);
	
				// Make the old event a loser
	
				if (oldBestEvent.getStatusCd() == 3) {
					// Current best is contender
					// Look for the other contenders to turn into losers
	
					for (int i = 0; i < bestEvents.size(); i++) {
						Event tmpEvent = bestEvents.get(i);
						if (tmpEvent.getId() != oldBestEvent.getId()) {
							this.updateEventStatus(tmpEvent, EditedEventsConstants.EVENT_STATUS.LOSER.ordinal() + 1, EditedEventsConstants.EVENT_STATUS.LOSER.toString(), 1);
							eventsToUpdate.add(tmpEvent);
						}
					}
	
				} else {
					// Make the old best event a loser, unless it is a consensus
					// then delete it
	
					if (newEvent.isComposite()) {
	
						if (oldBestEvent.getStatusCd() == 5) {
							// Once composite is deleted, new event will be the sole
							// best
							this.updateEventStatus(newEvent, EditedEventsConstants.EVENT_STATUS.SOLEBEST.ordinal() + 1, EditedEventsConstants.EVENT_STATUS.SOLEBEST.toString(), 0);
							eventsToUpdate.add(newEvent);
						}
	
						// Delete the composite
						oldBestEvent.setStatusCd(EditedEventsConstants.EVENT_STATUS.DELETED.ordinal() + 1);
						oldBestEvent.setStatusText(EditedEventsConstants.EVENT_STATUS.DELETED.toString());
						oldBestEvent.setChangeFlag(0);
						oldBestEvent.setAge(EditedEventsConstants.EVENT_AGE.DEL.toString());
						eventsToUpdate.add(oldBestEvent);
	
					} else {
						// Make the old best event a loser
						this.updateEventStatus(oldBestEvent, EditedEventsConstants.EVENT_STATUS.LOSER.ordinal() + 1, EditedEventsConstants.EVENT_STATUS.LOSER.toString(), 1);
						eventsToUpdate.add(oldBestEvent);
					}
	
				}
	
				break;
			case NEWLOSE:
				// Make the new event a loser
	
				this.updateEventStatus(newEvent, EditedEventsConstants.EVENT_STATUS.LOSER.ordinal() + 1, EditedEventsConstants.EVENT_STATUS.LOSER.toString(), 1);
				eventsToUpdate.add(newEvent);
	
				// Make the best event "best of group" (i.e., 4)
				if (oldBestEvent.getStatusCd() != 3) {
					this.updateEventStatus(oldBestEvent, EditedEventsConstants.EVENT_STATUS.WINNER.ordinal() + 1, EditedEventsConstants.EVENT_STATUS.WINNER.toString(), 1);
					eventsToUpdate.add(oldBestEvent);
				}
	
				break;
			case DRAW:
				// Make both events contenders
	
				this.updateEventStatus(newEvent, EditedEventsConstants.EVENT_STATUS.CONTENDER.ordinal() + 1, EditedEventsConstants.EVENT_STATUS.CONTENDER.toString(), 1);
				eventsToUpdate.add(newEvent);
	
				if (oldBestEvent.getStatusCd() != 3) {
					this.updateEventStatus(oldBestEvent, EditedEventsConstants.EVENT_STATUS.CONTENDER.ordinal() + 1, EditedEventsConstants.EVENT_STATUS.CONTENDER.toString(), 1);
					eventsToUpdate.add(oldBestEvent);
				}
	
				break;
			default:
				break;
		}
		
		// Do a batch update to update the event status
		if (eventsToUpdate.size() > 0) {
			eventDao.persistAll(eventsToUpdate);
		}

	}

	/**
	 * @param event
	 * @param statusCode
	 * @param statusText
	 * @param changeFlag
	 */
	private void updateEventStatus(Event event, int statusCode,
			String statusText, int changeFlag) {

		statusHandler.handle(Priority.INFO, " EDITEDEVENTS.SelectBestEventCommand.updateEventStatus()... ");
		
		event.setStatusCd(statusCode);
		event.setStatusText(statusText);
		event.setChangeFlag(changeFlag);
	}
	
	// TODO test only
	/**
	 * Compare two GOES events of the same type -- the primary satellite wins
	 * 
	 * First obtain the primary, secondary, and tertiary satellites
	 * 
	 * Second determine the best action
	 * 
	 * Logic obtained from legacy EditEventsSourceCode method comparegoes(Action As String)
	 * 
	 * @param currentEvent
	 * @param bestEvent
	 * 
	 * @return BestAction
	 * 
	 * @throws SQLException
	 */
	private BestAction compareGoesEvents(Event currentEvent, Event bestEvent) throws SQLException{

		Vector<Satellite> satellites = null;

		BestAction bestAction = BestAction.DRAW; // default

		// obtain the satellites
		switch (currentEvent.getCodedType()) {
		case 1: // X-ray
			 
			 satellites = this.satelliteTrackingStatusDao.getGOESSatellite("X-rays",
																				currentEvent.getBeginDate(),
																				currentEvent.getEndDate());

			break;
		case 60:
		case 61:
			 
			satellites = this.satelliteTrackingStatusDao.getGOESSatellite("Protons",
						currentEvent.getBeginDate(),
						currentEvent.getEndDate());
			 
			break;
		default:
			break;
		}

		// determine the best action
		bestAction = getBestAction(currentEvent.getObservatory().trim(),
								bestEvent.getObservatory().trim(),
								satellites);
		
		return bestAction;
	}
	
	/**
	 * Method to perform business rules for comparing
	 * optical events
	 * 
	 * Scenarios:
	 * 
	 * 	1) Superior seeing conditions wins
	 * 	2) No time qualifiers wins
	 * 
	 * Logic obtained from legacy EditEventsSourceCode method compareoptical(Action As String)
	 * 
	 * @param currentEvent
	 * @param bestEvent
	 * 
	 * @return BestAction
	 */
	private BestAction compareOpticalEvents(Event currentEvent, Event bestEvent) {
		
		BestAction bestAction = BestAction.DRAW;
		int currentTimeCount = 0;
		int bestTimeCount = 0;
		
		int bestQuality = Integer.valueOf(bestEvent.getQuality()).intValue();
		int currentQuality = Integer.valueOf(currentEvent.getQuality()).intValue();
		
		if (bestQuality > currentQuality) {
			bestAction = BestAction.NEWLOSE;
		} else if (bestQuality < currentQuality) {
			bestAction = BestAction.NEWWIN;
		} else {
			
			// tie-breaker when action == draw
			// count time qualifiers in new event
			currentTimeCount = this.countTimeQualifiers(currentEvent);
			
			// count time qualifiers in current best report
			bestTimeCount = this.countTimeQualifiers(bestEvent);
			
			// compare time qualifier counts
			if (bestTimeCount > currentTimeCount) {
				bestAction = BestAction.NEWWIN;
			} else if (bestTimeCount < currentTimeCount) {
				bestAction = BestAction.NEWLOSE;
			}
			
		}
		
		
		return bestAction;
		
	}
	
	
	/**
	 * Method to perform business rules for comparing
	 * bursts events in the same bin to determine which event is "best"
	 * 
	 * Scenarios:
	 * 
	 * 	1) Superior quality of observation wins
	 *  2) Max time closest to observatory local noon wins
	 * 	3) Fewest time qualifiers wins
	 * 
	 * Logic obtained from legacy EditEventsSourceCode method comparebursts(Action As String)
	 * 
	 * @param currentEvent
	 * @param bestEvent
	 * 
	 * @return BestAction
	 */
	private BestAction compareBurstEvents(Event currentEvent, Event bestEvent) {
		
		BestAction bestAction = null;
		int currentTimeCount = 0;   // Number of time qualifiers in new/current event
		int bestTimeCount = 0;      // Number of time qualifiers in best event
		
		// Superior quality of observation wins
		// Test first to see if the quality is the same in the two events -- if so
		// its a draw and further checking is needed
		// If they are not the same, then determine which event has better seeing
		
		String bestQuality = bestEvent.getQuality();
		String currentQuality = currentEvent.getQuality();
		
		if (bestQuality.compareToIgnoreCase(currentQuality) == 0) {
			bestAction = BestAction.DRAW;
		} else {
			
			// quality not the same in the two reports
			switch(bestQuality) {
			case "G":
				bestAction = BestAction.NEWLOSE;
				break;
			case "F":
				if (currentQuality.compareToIgnoreCase("G") == 0) {
					bestAction = BestAction.NEWWIN;
				} else {
					bestAction = BestAction.NEWLOSE;
				}
				
				bestAction = compareLocalNoonAtObservatory(currentEvent, bestEvent);				
				break;
			case "U":
				bestAction = BestAction.NEWWIN;
				break;
			default:
				bestAction = compareLocalNoonAtObservatory(currentEvent, bestEvent);
			}	
			
		}
			
		
		if (bestAction.compareTo(BestAction.DRAW) == 0) {
			
			// no time qualifiers wins
	        
			// Count time qualifiers in new/current report			
			currentTimeCount = this.countTimeQualifiers(currentEvent);
			
			// Count time qualifiers in best event
			bestTimeCount = this.countTimeQualifiers(bestEvent);
			
			// compare time qualifier counts
			if (bestTimeCount > currentTimeCount) {
				bestAction = BestAction.NEWWIN;
			} else if (bestTimeCount < currentTimeCount) {
				bestAction = BestAction.NEWLOSE;
			} else {
				bestAction = BestAction.DRAW;
			}
						
		}
		
		return bestAction;
		
	}
	
	/**
	 * Determine difference between local noon at observatory
	 * issuing new/current event and the max time of the event.
	 * 
	 * Max time closest to local noon at observatory wins.
	 * 
	 * @param currentEvent
	 * @param bestEvent
	 * 
	 * @return BestAction
	 */
	private BestAction compareLocalNoonAtObservatory(Event currentEvent, Event bestEvent) {
		
		int noonNew = 0;            // Local noon at new/current event observatory
		int noonBest = 0;           // Local noon at best event observatory
		BestAction bestAction = null;
		
		if (this.observatories.containsKey(currentEvent.getObservatory().trim().toUpperCase())) {
			Observatory o = this.observatories.get(currentEvent.getObservatory().trim().toUpperCase());
			noonNew = Math.abs(o.getLocalNoon() - currentEvent.getMaxTime());
		}
		
		if (this.observatories.containsKey(bestEvent.getObservatory().trim().toUpperCase())) {
			Observatory o = this.observatories.get(bestEvent.getObservatory().trim().toUpperCase());
			noonBest = Math.abs(o.getLocalNoon() - bestEvent.getMaxTime());
		}
					
		// Determine which event is closest to local noon
		
		if (noonBest < noonNew) {				
			bestAction = BestAction.NEWLOSE;
			
		} else if (noonBest > noonNew) {
			bestAction = BestAction.NEWWIN;
			
		} else if (noonBest == noonNew) {
			bestAction = BestAction.DRAW;
		}
		
		return bestAction;
		
	}
	
	/**
	 * Method to perform business rules for comparing
	 * sweep events in the same bin to determine which event is "best"
	 * 
	 * Scenarios:
	 * 
	 * 	1) Superior quality of observation wins
	 *  2) Largest importance wins
	 *  3) Fewest time qualifiers wins
	 * 	4) Broadest band width wins
	 *  5) Report begin time closest to observatory local noon wins 
	 * 
	 * Logic obtained from legacy EditEventsSourceCode method comparesweeps(Action As String)
	 * 
	 * @param currentEvent
	 * @param bestEvent
	 * 
	 * @return BestAction
	 */
	private BestAction compareSweepEvents(Event currentEvent, Event bestEvent) {
		
		BestAction bestAction = null;
//		int noonNew = 0;            // Local noon at new/current event observatory
//		int noonBest = 0;           // Local noon at best event observatory
//		int currentTimeCount = 0;   // Number of time qualifiers in new/current event
//		int bestTimeCount = 0;      // Number of time qualifiers in best event
		
		String bestQuality = bestEvent.getQuality();
		String currentQuality = currentEvent.getQuality();
		
		//Superior quality of observations wins
		
		if (bestQuality.compareToIgnoreCase(currentQuality) == 0) {
			bestAction = BestAction.DRAW;
		} else {
			
			switch(bestQuality) {
			case "C":
				bestAction = BestAction.NEWLOSE;
				break;
			case "U":
				bestAction = BestAction.NEWWIN;
				// Largest importance wins - Extract importance from new event Particulars
				this.getBestActionByEventImportance(currentEvent, bestEvent); // Tie-breaker 1
				break;
			default:
				bestAction = BestAction.DRAW;
				// Largest importance wins - Extract importance from new event Particulars
				this.getBestActionByEventImportance(currentEvent, bestEvent); // Tie-breaker 1
				break;
			}
		}
		
		// TODO Not complete yet. Implement logic for tie-breaker #2, #3 and #4 
				
		return bestAction;
	}
	
	/**
	 * Determine the BestAction based on the importance indicator that is specified
	 * as a sub-component of the particulars1 value from the db which is referred
	 * to, in the case of sweep events, as the (type of sweep / importance)
	 * 
	 * @see EventCodes
	 * 
	 * @param currentEvent
	 * @param bestEvent
	 * 
	 * @return BestAction
	 */
	private BestAction getBestActionByEventImportance(Event currentEvent, Event bestEvent) {
		
		BestAction bestAction = null;
		double currentImportance = 0; // means either the field is null or the sweep type cod was not found
		double bestImportance = 0; // means either the field is null or the sweep type cod was not found
		
		// Obtain the SweepType Instance corresponding to the sweep type code
		// obtained from the current event
		if (EventCodes.sweepTypes.containsKey(currentEvent.getParticulars1())) {
			
			SweepType sweepType = EventCodes.sweepTypes.get(currentEvent.getParticulars1());
			currentImportance = sweepType.getImportance().doubleValue();
			if (sweepType.isPlusEnable()) {
				currentImportance = currentImportance + 0.5;
			}
		
		}
		
		// Obtain the SweepType Instance corresponding to the sweep type code
		// obtained from the best event
		if (EventCodes.sweepTypes.containsKey(bestEvent.getParticulars1())) {
			
			SweepType sweepType = EventCodes.sweepTypes.get(bestEvent.getParticulars1());
			bestImportance = sweepType.getImportance().doubleValue();
			if (sweepType.isPlusEnable()) {
				bestImportance = bestImportance + 0.5;
			}
		
		}
		
		if (currentImportance > bestImportance) {
			bestAction = BestAction.NEWWIN;
		} else if (currentImportance == bestImportance) {
			bestAction = BestAction.DRAW;
		} else {
			bestAction = BestAction.NEWLOSE;
		}
		

		
		return bestAction;
		
	}
	
	/**
	 * Select best xray or particle event -- on the assumption that x-ray events do not overlap
	 * then there can only be one Primary event
	 * 
	 * Select the event with the primary satellite as the observatory
	 * 
	 * @param currentEventObservatory
	 * @param bestEventObservatory
	 * @param primarySat
	 * @param secondarySat
	 * @param tertiarySat
	 * 
	 * @return BestAction
	 */
	private BestAction getBestAction(String currentEventObservatory,
											String bestEventObservatory,
											Vector<Satellite> satellites) {
		
		Satellite satPrimary = null;
		Satellite satSecondary = null;
		Satellite satTertiary = null;
		
		for (int i = 0; i <= satellites.size() -1; i++) {
			if (satellites.get(i).isPrimary()) {
				satPrimary = satellites.get(i);
			} else if (satellites.get(i).isSecondary()) {
				satSecondary = satellites.get(i);
			} else {
				satTertiary = satellites.get(i);
			}
		}
		
		BestAction bestAction = BestAction.DRAW;
		String primarySatelliteCode = (satPrimary != null) ? satPrimary.getCode() : "";
		String secondarySatelliteCode = (satSecondary != null) ? satSecondary.getCode() : "";
		String tertiarySatelliteCode = (satTertiary != null) ? satTertiary.getCode() : "";
		
		if (bestEventObservatory.compareToIgnoreCase(primarySatelliteCode) == 0) { // comparison with Primary Satellite
			if (currentEventObservatory.compareToIgnoreCase(primarySatelliteCode) == 0) {
				bestAction = BestAction.DRAW;
			} else {
				bestAction = BestAction.NEWLOSE;
			}
		} else if (bestEventObservatory.compareToIgnoreCase(secondarySatelliteCode) == 0) { // comparison with Secondary Satellite
			if (currentEventObservatory.compareToIgnoreCase(primarySatelliteCode) == 0) {
				bestAction = BestAction.NEWWIN;
			} else {
				if (currentEventObservatory.compareToIgnoreCase(secondarySatelliteCode) == 0) {
					bestAction = BestAction.DRAW;
				} else {
					bestAction = BestAction.NEWLOSE;
				}
			}
		} else if (bestEventObservatory.compareToIgnoreCase(tertiarySatelliteCode) == 0) { // comparison with Tertiary Satellite
			if (currentEventObservatory.compareToIgnoreCase(tertiarySatelliteCode) == 0) {
				bestAction = BestAction.DRAW;
			} else {
				bestAction = BestAction.NEWWIN;
			}
		}
		
		return bestAction;
	}

	@Override
	public long getStartTime() {
		return this.startTime;
	}

	@Override
	public long getEndTime() {
		return this.endTime;
	}

	@Override
	public long getProcessingTime() {
        long time = 0L;

        time = this.getEndTime() - this.getStartTime();

        return time;
	}

	@Override
	public void setRequest(IRequest request) {
		this.request = (SelectBestEventRequest) request;
	}

	@Override
	public SelectBestEventRequest getRequest() {
		return this.request;
	}

}
