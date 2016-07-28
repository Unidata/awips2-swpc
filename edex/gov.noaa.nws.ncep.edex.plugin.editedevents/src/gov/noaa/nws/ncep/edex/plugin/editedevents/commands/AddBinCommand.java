package gov.noaa.nws.ncep.edex.plugin.editedevents.commands;

import gov.noaa.nws.ncep.common.dataplugin.editedevents.Event;
import gov.noaa.nws.ncep.common.dataplugin.editedevents.EventBin;
import gov.noaa.nws.ncep.common.dataplugin.editedevents.exception.EditedEventsException;
import gov.noaa.nws.ncep.common.dataplugin.editedevents.request.AddBinRequest;
import gov.noaa.nws.ncep.common.dataplugin.editedevents.request.intf.IRequest;
import gov.noaa.nws.ncep.common.dataplugin.editedevents.response.AddBinResponse;
import gov.noaa.nws.ncep.common.dataplugin.editedevents.response.intf.IResponse;
import gov.noaa.nws.ncep.common.dataplugin.editedevents.results.AddBinResults;
import gov.noaa.nws.ncep.common.dataplugin.editedevents.util.EditedEventsConstants.EventType;
import gov.noaa.nws.ncep.common.swpcrefdb.HouseKeeping;
import gov.noaa.nws.ncep.edex.plugin.editedevents.dao.EventBinDao;
import gov.noaa.nws.ncep.edex.plugin.editedevents.util.EECommandUtil;
import gov.noaa.nws.ncep.edex.swpcrefdb.dao.HouseKeepingDao;

import java.util.Calendar;
import java.util.List;

import com.raytheon.uf.common.dataplugin.PluginException;
import com.raytheon.uf.common.status.IUFStatusHandler;
import com.raytheon.uf.common.status.UFStatus;
import com.raytheon.uf.common.status.UFStatus.Priority;

/**
 * Command class that separates out the add bin functionality that was part of
 * the UpdateBinInfo function in the legacy Edited Events application.
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
public class AddBinCommand extends BaseCommand {

    /**
     * The request from the client that resulted in creating an instance of the
     * command
     */
    private AddBinRequest request = null;
   
    /**
     * Dao for HouseKeeping records
     */
    private HouseKeepingDao houseKeepingDao = null;

    /**
     * Dao for EventBin records
     */
    private EventBinDao eventBinDao = null;
    
    /**
	 * Logger
	 */
    private static final IUFStatusHandler statusHandler = UFStatus.getHandler(AddBinCommand.class);

    /**
     * Creates a new AddBinCommand
     * 
     * @throws PluginException
     */
    public AddBinCommand() {

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

    /*
     * (non-Javadoc)
     * 
     * @see
     * gov.noaa.nws.ncep.edex.plugin.editedevents.commands.intf.ISubCommand#
     * execute()
     */
    @Override
    public IResponse execute() {
    
    	this.setStartTime();
    	
    	EventBin newBin = null;
    	Event event = this.request.getEvent();
    	    	
        try {

            houseKeepingDao = new HouseKeepingDao();
            eventBinDao = new EventBinDao();

            int nextBinNumber = 0;

            // Get the last used/free bin number from swpc_housekeeping db table
            HouseKeeping houseKeeping = houseKeepingDao
                    .getHouseKeepingForEvents("Events");

            nextBinNumber = houseKeeping.getItem1();
            
            statusHandler.handle(Priority.DEBUG, " EDITEDEVENTS.AddBinCommand.execute(), nextBinNumber = " + nextBinNumber );

            // Event bin numbers run from 10 to 9990 with an increment of 10
            // Increment bin number (item_1) by 10
            nextBinNumber = nextBinNumber + EventBin.BIN_NUMBER_INCREMENT;

            // Recycle bin numbers when 9990 is reached
            if (nextBinNumber > EventBin.BIN_NUMBER_END) {
                nextBinNumber = EventBin.BIN_NUMBER_START;
            }

            // Update item_1 (next bin number) in swpc_housekeeping db table

            houseKeeping.setItem1(nextBinNumber);
            houseKeepingDao.saveOrUpdate(houseKeeping);
            
            statusHandler.handle(Priority.INFO, " EDITEDEVENTS.AddBinCommand.execute(), houseKeeping.id = " + houseKeeping.getId() );

            // If there are no extra bins, free up the next 100 bins
            if (houseKeeping.getItem1() == houseKeeping.getItem2()) {
                freeBins(nextBinNumber, houseKeeping);
            }

            // Create new bin to add to the db table swpc_eventbin
            newBin = new EventBin();
            newBin.setBinNumber(nextBinNumber);

            // Bin times are determined by the type of report. RNS bin times
            // aren't
            // used, since all reports for a given day fall in the same bin. DSF
            // times match the times of the report. All other types of bins
            // match the event report times or unless the duration is greater
            // than 60 minutes, then the bin end time is the bin begin time
            // plus 60 minutes

            if (event.getType().equals(EventType.RNS.getType())) {

                newBin.setBeginDTTM(EECommandUtil.clearCalendarHHMMSS(event
                        .getBeginDate()));
                newBin.setEndDTTM(newBin.getBeginDTTM()); // TODO legacy code
                                                          // has
                                                          // "rsbin!Begindate+",
                                                          // need to find out
                                                          // what "+" means
            } else {
                newBin.setBeginDTTM(AddBinCommand.getUniqueBinBeginDate(
                        event.getBeginDate(), eventBinDao));

                // Set End date/time. lat and lon, type for DSF event type
                if (event.getType().equals(EventType.DSF.getType())) {
                    newBin.setEndDTTM(event.getEndDate());
                    if (event.getLocation() != null && !event.getLocation().isEmpty()) {

                        if (event.getLocation().startsWith("N")) { // Latitude
                            newBin.setLat(AddBinCommand.getLatitude(event
                                    .getLocation()));
                        } else {
                            newBin.setLat(-AddBinCommand.getLatitude(event
                                    .getLocation()));
                        }

                        if (event.getLocation().substring(3, 4).equals("E")) { // Longitude
                            newBin.setLon(AddBinCommand.getLongitude(event
                                    .getLocation()));
                        } else {
                            newBin.setLon(-AddBinCommand.getLongitude(event
                                    .getLocation()));
                        }
                    }

                } else { // Set End date/time for all other event types except
                         // RNS and DSF

                    int dateDiff = 0;
                    
                    if (event.getBeginDate() != null && event.getEndDate() != null) {
	                    dateDiff = event.getBeginDate().get(
	                            Calendar.DAY_OF_YEAR)
	                            - event.getEndDate().get(Calendar.DAY_OF_YEAR);
                    }
                                        
                    if (dateDiff > 60) {
                        Calendar cal = event.getBeginDate();
                        cal.add(Calendar.HOUR_OF_DAY, 1);
                        newBin.setEndDTTM(cal);
                    } else {
                        newBin.setEndDTTM(event.getEndDate());
                    }
                }
            }

            // If an event report has a type (always), add it to the bin
            if (event.getType() != null) {
                // TODO: Change datatype of Event.type to EventType from String
                newBin.setType(event.getType());
            }

            eventBinDao.saveOrUpdate(newBin);
   
        } catch (Exception e) {
            error = new EditedEventsException(
                    "ERROR - Exception occured while executing the AddBinCommand.",
                    e);
        }
        
        this.setEndTime();
        
		return this.createResponse(newBin);
    }

    /**
     * Free bins: delete 100 bins from the swpc_eventbin db table
     * 
     * @param lastFreeBinNumbr
     * @param houseKeeping
     */
    private void freeBins(int lastFreeBinNumbr, HouseKeeping houseKeeping) {

        // Determine the new last free bin number (to be saved as item2 in
        // swpc_housekeeping table)
        int newLastFreeBinNumber = lastFreeBinNumbr + 1000;

        if (newLastFreeBinNumber > EventBin.BIN_NUMBER_END) {
            newLastFreeBinNumber = newLastFreeBinNumber
                    - EventBin.BIN_NUMBER_END;
        }

        List<EventBin> eventBins = null;

        // Get the list of bins to free
        if (newLastFreeBinNumber > lastFreeBinNumbr) {
            eventBins = eventBinDao.getBinsToFree(lastFreeBinNumbr,
                    newLastFreeBinNumber, true);
        } else {
            eventBins = eventBinDao.getBinsToFree(lastFreeBinNumbr,
                    newLastFreeBinNumber, false);
        }

        int eventBinsSize = (eventBins != null) ? eventBins.size() : 0;

        // Delete all the bin records found
        for (int i = 0; i < eventBinsSize; i++) {
            EventBin eventBin = eventBins.get(i);

            eventBinDao.delete(eventBin);
        }

        // Update swpc_housekeeping db table with new "last free bin" value
        houseKeeping.setItem2(newLastFreeBinNumber);
        houseKeepingDao.saveOrUpdate(houseKeeping);

    }

    /**
     * Determine if the Event begin time will be a unique Bin Time; Increment
     * the bin time, 1 minute at a time, until it is unique.
     * 
     * @param eventBegin
     *            The event begin time
     * @param dao
     *            The EventBinDao
     * @return Calendar The bin begin time
     */
    public static Calendar getUniqueBinBeginDate(Calendar eventBegin, EventBinDao dao) {
    
        Calendar binBegin = Calendar.getInstance();
        binBegin.clear();
        binBegin.setTimeInMillis(eventBegin.getTimeInMillis());
        
        int binsSize = 0;

        do {

            List<EventBin> bins = dao.getBins(binBegin);

            binsSize = (bins != null) ? bins.size() : 0;

            if (binsSize == 0) {
                break;
            } else {
            	binBegin.add(Calendar.MINUTE, 1);
            }
        } while (binsSize != 0);

        return binBegin;
    }

    /**
     * Return the latitude value from the location
     * 
     * @param location
     * @return int
     */
    public static int getLatitude(String location) {
        int lat = Integer.parseInt(location.substring(1, 3));

        return lat;
    }

    /**
     * Return the longitude value from the location
     * 
     * @param location
     * @return int
     */
    public static int getLongitude(String location) {
        int lon = Integer.parseInt(location.substring(location.length() - 2,
                location.length()));

        return lon;
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
		this.request = (AddBinRequest) request;
	}

	/* (non-Javadoc)
	 * @see gov.noaa.nws.ncep.edex.plugin.editedevents.commands.intf.ICommand#getRequest()
	 */
	@Override
	public AddBinRequest getRequest() {
		return this.request;
	}
	

	/**
	 * Create response
	 * 
	 * @param newBin
	 * @return
	 */
	private IResponse createResponse(EventBin newBin) {
		
		AddBinResponse response = new AddBinResponse();
		
		// set the results
		AddBinResults results = new AddBinResults();
		results.setBin(newBin);

        if (this.hasError()) {
        	response.setError(this.getError());
        } else {
        	response.setResults(results);
        }

        // populate the response
        response.setRequest(this.getRequest()); 
        response.setProcessingTime(this.getProcessingTime());
        
        return response;
		
	}

}
