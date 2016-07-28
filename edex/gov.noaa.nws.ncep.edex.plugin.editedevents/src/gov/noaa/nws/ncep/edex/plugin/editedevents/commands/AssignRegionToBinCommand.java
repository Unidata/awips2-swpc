package gov.noaa.nws.ncep.edex.plugin.editedevents.commands;

import java.util.Calendar;
import java.util.List;

import com.raytheon.uf.common.dataplugin.PluginException;
import com.raytheon.uf.common.status.IUFStatusHandler;
import com.raytheon.uf.common.status.UFStatus;
import com.raytheon.uf.common.status.UFStatus.Priority;
import com.raytheon.uf.edex.database.DataAccessLayerException;

import gov.noaa.nws.ncep.common.dataplugin.editedevents.Event;
import gov.noaa.nws.ncep.common.dataplugin.editedevents.EventBin;
import gov.noaa.nws.ncep.common.dataplugin.editedevents.exception.EditedEventsException;
import gov.noaa.nws.ncep.common.dataplugin.editedevents.request.intf.IRequest;
import gov.noaa.nws.ncep.common.dataplugin.editedevents.response.AssignRegionToBinResponse;
import gov.noaa.nws.ncep.common.dataplugin.editedevents.response.intf.IResponse;
import gov.noaa.nws.ncep.common.dataplugin.editedevents.util.EditedEventsConstants;
import gov.noaa.nws.ncep.edex.plugin.editedevents.dao.EventBinDao;
import gov.noaa.nws.ncep.edex.plugin.editedevents.dao.EventsDao;

/**
 * Determine if a "best" event is optical -- if it is, add non-zero region
 * to the event bin; else if a region has been assigned to the bin, add it
 * to the event.
 * 
 * This command performs the logic that is found in the legacy applications
 * OptRpt() function.
 * 
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
public class AssignRegionToBinCommand extends BaseCommand {
	
	/**
	 * The event we are concerned with
	 */
	private Event event = null;
	
	/**
	 * User supplied date/time value
	 */
	private Calendar beginDTTM = null;
	
	/**
	 * Hold any error that may occure
	 */
	private EditedEventsException error = null;
	
	/**
	 * Dao for Event records
	 */
	private EventsDao eventDao = null;
	
	/**
	 * Dao for EventBin records
	 */
	private EventBinDao eventBinDao = null;
	
	/**
	 * Logger
	 */
	 private static final IUFStatusHandler statusHandler = UFStatus.getHandler(AssignRegionToBinCommand.class);
	
	/**
	 * Default Constructor
	 */
	public AssignRegionToBinCommand() {
	}
	
	/**
	 * Constructor taking all user supplied values
	 * 
	 * @param event
	 * @param beginDTTM
	 */
	public AssignRegionToBinCommand(Event event, Calendar beginDTTM) {
		this.event = event;
		this.beginDTTM = beginDTTM;
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
	 */
	@Override
	public boolean isValid() {
		if (this.event == null || this.beginDTTM == null) {
			return false;
		} else {
			return true;
		}
	}

	/* (non-Javadoc)
	 * @see gov.noaa.nws.ncep.edex.plugin.editedevents.commands.intf.ICommand#setRequest(gov.noaa.nws.ncep.common.dataplugin.editedevents.request.intf.IRequest)
	 */
	@Override
	public void setRequest(IRequest request) {
		;;
	}

	/* (non-Javadoc)
	 * @see gov.noaa.nws.ncep.edex.plugin.editedevents.commands.intf.ICommand#getRequest()
	 */
	@Override
	public IRequest getRequest() {
		return null;
	}

	/* (non-Javadoc)
	 * @see gov.noaa.nws.ncep.edex.plugin.editedevents.commands.intf.ICommand#execute()
	 */
	@Override
	public IResponse execute() {

		this.setStartTime();
		
		eventBinDao = new EventBinDao();
		try {
			eventDao = new EventsDao();
			
			Integer region = EditedEventsConstants.MISSING_REGION_VAL;
			if (event.getRegion() != null) {
				region = event.getRegion().intValue();
			}
	
			EventBin bin = eventBinDao.queryByBinNumber(event.getBin().getBinNumber());	
	
			statusHandler.handle(Priority.INFO, " EDITEDEVENTS.AssignRegionToBinCommand.execute()... bin number =  "+ bin.getBinNumber());
	
			if (event.getCodedType() >= 10 && event.getCodedType() <= 24
					&& region != EditedEventsConstants.MISSING_REGION_VAL) {
				// Event is an optical event
				this.assignRegionToBinForOpticalEvent(region, bin);
			} else if (event.getCodedType() == 2 && region != EditedEventsConstants.MISSING_REGION_VAL) {
				// Event is not a flare; check for XFL
				this.assignRegionToBinForOpticalEvent(region, bin);
			} else {

				// Event is not optical, or region number is zero
				// Check to see if the bin has a region number assigned and if so,
				// add it to the event
				this.assignRegionToNonOpticalEvent(event, region, bin);
			}

		} catch (DataAccessLayerException e) {
			this.error = new EditedEventsException("ERROR - AssignRegionToBinCommand - " + e.getMessage(), e);
					
		} catch (PluginException e) {			
			this.error = new EditedEventsException("ERROR - AssignRegionToBinCommand - " + e.getMessage(), e);
		}

		this.setEndTime();
		
		return this.createResponse();
	}
	
	private IResponse createResponse() {
		
		AssignRegionToBinResponse response = new AssignRegionToBinResponse();

        if (this.hasError()) {
        	response.setError(this.getError());
        } 

        // populate the response
        response.setRequest(this.getRequest()); 
        response.setProcessingTime(this.getProcessingTime());
        
        return response;
		
	}
	
	/**
	 * Assign region number to a bin. The only ways a region is assigned to a
	 * bin is from a "best" optical event or a composite.
	 * 
	 * @param region
	 * @param bin
	 * @throws DataAccessLayerException
	 */
	private void assignRegionToBinForOpticalEvent(Integer region, EventBin bin)
			throws DataAccessLayerException {

		if (bin.getRegion() == null || bin.getRegion() != region) {
			bin.setRegion(region);
			eventBinDao.saveOrUpdate(bin);

			this.assignRegionToEvents(bin.getBinNumber(), region);
		}
	}
	
	/**
	 * Check to see if the bin has a region number assigned and if so, add it to
	 * the event
	 * 
	 * @param event
	 * @param region
	 * @param bin
	 */
	private void assignRegionToNonOpticalEvent(Event event, Integer region,
			EventBin bin) {

		if (bin != null && bin.getRegion() != null) {
			event.setRegion(region);
			event.setChangeFlag(1);
			eventDao.saveOrUpdate(event);
		}
	}
	
	/**
	 * Assign a region number to events in an event bin. Only non-optical events
	 * and optical events without a region number already assigned will be
	 * affected.
	 * 
	 * @param binNumber
	 * @param region
	 * @throws DataAccessLayerException
	 */
	private void assignRegionToEvents(Integer binNumber, Integer region)
			throws DataAccessLayerException {

		Calendar cal = Calendar.getInstance();
		cal.clear();
		cal.setTime(this.beginDTTM.getTime());
		cal.add(Calendar.DAY_OF_YEAR, -2);

		List<Event> events = eventDao.getEvents(cal, binNumber);
		int eventsSize = (events != null) ? events.size() : 0;

		for (int i = 0; i < eventsSize; i++) {

			Event event = events.get(i);

			switch (event.getCodedType()) {
				case 10:
				case 20:
				case 21:
				case 22:
				case 23:
				case 24:
					// Optical Events. Only if no current region is assigned
					if (event.getRegion() == null) {
						event.setRegion(region);
						event.setChangeFlag(1);
					}
					break;
				default:
					// Non-optical Events
					event.setRegion(region);
					event.setChangeFlag(1);
					break;
			}

			eventDao.saveOrUpdate(event);
		}

	}

	/**
	 * @return the beginDTTM
	 */
	public Calendar getBeginDTTM() {
		return beginDTTM;
	}

	/**
	 * @param beginDTTM the beginDTTM to set
	 */
	public void setBeginDTTM(Calendar beginDTTM) {
		this.beginDTTM = beginDTTM;
	}

	/**
	 * @return the event
	 */
	public Event getEvent() {
		return event;
	}

	/**
	 * @param event the event to set
	 */
	public void setEvent(Event event) {
		this.event = event;
	}

}
