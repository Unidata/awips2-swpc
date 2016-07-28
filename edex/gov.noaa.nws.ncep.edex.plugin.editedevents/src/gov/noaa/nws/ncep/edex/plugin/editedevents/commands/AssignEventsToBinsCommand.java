package gov.noaa.nws.ncep.edex.plugin.editedevents.commands;

import gov.noaa.nws.ncep.common.dataplugin.editedevents.Event;
import gov.noaa.nws.ncep.common.dataplugin.editedevents.EventBin;
import gov.noaa.nws.ncep.common.dataplugin.editedevents.exception.EditedEventsException;
import gov.noaa.nws.ncep.common.dataplugin.editedevents.request.AddBinRequest;
import gov.noaa.nws.ncep.common.dataplugin.editedevents.request.AssignEventsToBinsRequest;
import gov.noaa.nws.ncep.common.dataplugin.editedevents.request.intf.IRequest;
import gov.noaa.nws.ncep.common.dataplugin.editedevents.response.AddBinResponse;
import gov.noaa.nws.ncep.common.dataplugin.editedevents.response.intf.IResponse;
import gov.noaa.nws.ncep.common.dataplugin.editedevents.results.AddBinResults;
import gov.noaa.nws.ncep.edex.plugin.editedevents.dao.EventBinDao;
import gov.noaa.nws.ncep.edex.plugin.editedevents.dao.EventsDao;
import gov.noaa.nws.ncep.edex.plugin.editedevents.util.EECommandUtil;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import com.raytheon.uf.common.dataplugin.PluginException;
import com.raytheon.uf.common.status.IUFStatusHandler;
import com.raytheon.uf.common.status.UFStatus;
import com.raytheon.uf.common.status.UFStatus.Priority;

/**
 * A sub-command that is called by the ProcessEvents command
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
public class AssignEventsToBinsCommand extends BaseCommand {

    /**
     * The request from the client that resulted in creating an instance of the
     * command
     */
    private AssignEventsToBinsRequest request = null;
	
    /**
     * Holds a collection of events that need to be assigned to bins
     */
    private List<Event> events = new ArrayList<Event>();

    /**
     * Dao for EventBin records
     */
    private EventBinDao eventBinDao = null;

    /**
     * Dao for Event records
     */
    private EventsDao eventDao = null;

    /**
     * Begin Date/time
     */
    private Calendar beginDTTM = null;
    
    /**
	 * Logger
	 */
    private static final IUFStatusHandler statusHandler = UFStatus.getHandler(AssignEventsToBinsCommand.class);

    /**
	 * 
	 */
    public AssignEventsToBinsCommand() {

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
        if (this.getBeginDTTM() == null) {
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
     * execute()
     */
    @Override
    public IResponse execute() {
    	
    	statusHandler.handle(Priority.INFO, " EDITEDEVENTS.AssignEventsToBinsCommand.execute()....");
		
    	this.setStartTime();

        try {

            eventBinDao = new EventBinDao();
            eventDao = new EventsDao();

            // Bin all new events except RNS and DSF event types
            this.binNewEvents();

            // Bin new RNS events
            this.binNewRNSEvents();

            // Bin new DSF events
            this.binNewDSFEvents();

        } catch (Exception e) {
            error = new EditedEventsException(
                    "ERROR - Exception occured while executing the AssignEventsToBinsCommand.",
                    e);
        }
        
        this.setEndTime();
        
        return null;
    }

    /**
     * Return the events
     * 
     * @return List<Event>
     */
    public List<Event> getEvents() {
        return events;
    }

    /**
     * Set the list of events
     * 
     * @param events
     */
    public void setEvents(List<Event> events) {
        this.events = events;
    }

    /**
     * Return the begin date/time
     * 
     * @return Calendar
     */
    public Calendar getBeginDTTM() {
        return beginDTTM;
    }

    /**
     * Set begin date/time
     * 
     * @param beginDTTM
     */
    public void setBeginDTTM(Calendar beginDTTM) {
        this.beginDTTM = beginDTTM;
    }

    /**
     * Bin all new events except RNS and DSF
     * 
     * @throws PluginException
     */
    private void binNewEvents() throws PluginException {

        statusHandler.handle(Priority.INFO, " EDITEDEVENTS.AssignEventsToBinsCommand.binNewEvents()");
        
        List<EventBin> bins = null;
        boolean foundBin = false;
        EventBin bin = null;

        try {
	        events = eventDao.getNewEventsMinusRNSandDSF(this.beginDTTM);
	        
	        statusHandler.handle(Priority.INFO, " EDITEDEVENTS.AssignEventsToBinsCommand.binNewEvents()...." + events);
        
        } catch (Exception e) {
        	statusHandler.handle(Priority.INFO, " Error occurred at EDITEDEVENTS.AssignEventsToBinsCommand.binNewEvents()");
        	e.printStackTrace();
        }

        for (int i = 0; i < events.size(); i++) {        	
        	
            Event event = events.get(i);

            // Apply the first search criteria: if the begindate of the event
            // falls between the begin_dttm and end_dttm of the bin
            bins = eventBinDao.getBinsMinusRNSandDSF(event.getBeginDate(),
                    event.getBeginDate());

            int binsSize = (bins != null) ? bins.size() : 0;
            
            statusHandler.handle(Priority.INFO, " EDITEDEVENTS.AssignEventsToBinsCommand.callAddOrUpdateBinCommand(), 1 binsSize = " + binsSize);

            if (binsSize > 0) { // Found an existing bin
                foundBin = true;
                bin = bins.get(0);
            } else {

                // Apply the second search criteria: if the maxdate of the event
                // falls between the begin_dttm and end_dttm of the bin
                bins = eventBinDao.getBinsMinusRNSandDSF(event.getMaxDate(),
                        event.getMaxDate());

                binsSize = (bins != null) ? bins.size() : 0;
                
                statusHandler.handle(Priority.INFO, " EDITEDEVENTS.AssignEventsToBinsCommand.callAddOrUpdateBinCommand(), 2 binsSize = " + binsSize);


                if (binsSize > 0) { // Found an existing bin
                    foundBin = true;
                    bin = bins.get(0);
                }
            }
            
            statusHandler.handle(Priority.INFO, " EDITEDEVENTS.AssignEventsToBinsCommand.callAddOrUpdateBinCommand(), event begin date " + event.getBeginDate());

            statusHandler.handle(Priority.INFO, " EDITEDEVENTS.AssignEventsToBinsCommand.callAddOrUpdateBinCommand(), foundBin = " + foundBin);

            // Either add a bin or edit the bin and add the bin number to the
            // event
            this.callAddOrUpdateBinCommand(foundBin, bin, event);
            
            // Reset foundBin to false
            foundBin = false;

        }

    }

    /**
     * Bin new Radio Noise Storm (RNS) events. All these events go in one bin
     * per day.
     * @throws ParseException 
     */
    private void binNewRNSEvents() throws ParseException {

        List<EventBin> bins = null;
        boolean foundBin = false;
        EventBin bin = null;

        events = eventDao.getNewRNSEvents(this.beginDTTM);

        for (int i = 0; i < events.size(); i++) {

            Event event = events.get(i);

            bins = eventBinDao.getRNSBins(EECommandUtil.clearCalendarHHMMSS(event.getBeginDate()));
            int binsSize = (bins != null) ? bins.size() : 0;

            if (binsSize > 0) { // Bins exist
                foundBin = true;
                bin = bins.get(0);
            } else {
                foundBin = false;
            }

            // Either add a bin or edit the bin and add the bin number to the
            // event
            this.callAddOrUpdateBinCommand(foundBin, bin, event);
            
            // Reset foundBin to false
            foundBin = false;

        }
    }

    /**
     * Bin new Disappearing Filament (DSF) events. These events have their own
     * bins and require matching not only times but locations.
     */
    private void binNewDSFEvents() {

        List<EventBin> bins = null;
        boolean foundBin = false;
        EventBin bin = null;

        events = eventDao.getNewDSFEvents(this.beginDTTM);

        for (int i = 0; i < events.size(); i++) {

            Event event = events.get(i);
            int lat = 0;
            int lon = 0;

            // Determine the numeric lat and lon of the DSF event
            if (event.getLocation() != null && !event.getLocation().isEmpty()) {

                if (event.getLocation().startsWith("N")) { // Latitude
                    lat = AddBinCommand.getLatitude(event.getLocation());
                } else {
                    lat = -AddBinCommand.getLatitude(event.getLocation());
                }

                if (event.getLocation().substring(3, 4).equals("E")) { // Longitude
                    lon = AddBinCommand.getLongitude(event.getLocation());
                } else {
                    lon = -AddBinCommand.getLongitude(event.getLocation());
                }

                // Find DSF bin for today

            }

            // Search criteria: 1. Begin date/time of the report falls between
            // the Begin and End date/times of the bin
            // 2. End date/time of the report falls between
            // the Begin and End date/times of the bin
            // 3. Begin date/time of the bin falls between
            // the Begindate and the Enddate of the report.

            for (int srchCriteria = 1; srchCriteria <= 3; srchCriteria++) {
                bins = eventBinDao.getDSFBins(event.getBeginDate(),
                        event.getEndDate(), srchCriteria);
                int binsSize = (bins != null) ? bins.size() : 0;

                for (int k = 0; k < binsSize; k++) {

                    // Found a bin -- test the location to check if it matches
                    bin = bins.get(k);
                    
                    if (bin.getLat() != 0 && bin.getLon() != 0) {

	                    if ((bin.getLat() - 10) <= lat
	                            && lat <= (bin.getLat() + 10)) {
	                        if ((bin.getLon() - 10) <= lon
	                                && lon <= (bin.getLon() + 10)) {
	                            foundBin = true;
	                            break;
	                        }
	                    }
                    }
                }

                if (foundBin) {
                    break;
                }
            }

            // Either add a bin or edit the bin and add the bin number to the
            // event
            this.callAddOrUpdateBinCommand(foundBin, bin, event);
            
            // Reset foundBin to false
            foundBin = false;
        }
    }

    private void callAddOrUpdateBinCommand(boolean foundBin, EventBin bin,
            Event event) {
    	
    	statusHandler.handle(Priority.INFO, " EDITEDEVENTS.AssignEventsToBinsCommand.callAddOrUpdateBinCommand()...." );

        if (foundBin) {
            // Edit existing bin
        	// TODO - the command needs to be supplied with a request
            UpdateBinCommand ubCmd = new UpdateBinCommand();
            ubCmd.setBin(bin);
            ubCmd.setEventId(event.getId());
            ubCmd.setEvent(event);            
            ubCmd.execute();

        } else {
            // Add new bin
        	AddBinRequest request = new AddBinRequest();
        	request.setEvent(event);
            AddBinCommand abCmd = new AddBinCommand();
            abCmd.setRequest(request);
             
            AddBinResponse abResponse = (AddBinResponse) abCmd.execute(); // execute the AddBinCommand
			
			// check that no errors occurred when
			// executing the AddBinCommand
			if (!abResponse.hasErrors()) {	
				EventBin newBin = ((AddBinResults) abResponse.getResults()).getBin();
				
				// Add bin number to event report
	            event.setBin(newBin);
	            event.setChangeFlag(1);

	            eventDao.saveOrUpdate(event);
			}
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
	 * @see gov.noaa.nws.ncep.edex.plugin.editedevents.commands.intf.ICommand#setRequest(gov.noaa.nws.ncep.common.dataplugin.editedevents.request.intf.IRequest)
	 */
	@Override
	public void setRequest(IRequest request) {
		this.request = (AssignEventsToBinsRequest) request;
	}

	/* (non-Javadoc)
	 * @see gov.noaa.nws.ncep.edex.plugin.editedevents.commands.intf.ICommand#getRequest()
	 */
	@Override
	public AssignEventsToBinsRequest getRequest() {
		return this.request;
	}

}
