package gov.noaa.nws.ncep.edex.plugin.editedevents.commands;

import gov.noaa.nws.ncep.common.dataplugin.editedevents.Event;
import gov.noaa.nws.ncep.common.dataplugin.editedevents.EventBin;
import gov.noaa.nws.ncep.common.dataplugin.editedevents.exception.EditedEventsException;
import gov.noaa.nws.ncep.common.dataplugin.editedevents.request.UpdateBinRequest;
import gov.noaa.nws.ncep.common.dataplugin.editedevents.request.intf.IRequest;
import gov.noaa.nws.ncep.common.dataplugin.editedevents.response.UpdateBinResponse;
import gov.noaa.nws.ncep.common.dataplugin.editedevents.response.intf.IResponse;
import gov.noaa.nws.ncep.common.dataplugin.editedevents.util.EditedEventsConstants;
import gov.noaa.nws.ncep.common.dataplugin.editedevents.util.EditedEventsConstants.EventType;
import gov.noaa.nws.ncep.edex.plugin.editedevents.dao.EventBinDao;
import gov.noaa.nws.ncep.edex.plugin.editedevents.dao.EventsDao;
import gov.noaa.nws.ncep.edex.swpcrefdb.dao.HouseKeepingDao;

import java.util.Calendar;

import com.raytheon.uf.common.serialization.annotations.DynamicSerialize;
import com.raytheon.uf.common.serialization.annotations.DynamicSerializeElement;
import com.raytheon.uf.common.status.IUFStatusHandler;
import com.raytheon.uf.common.status.UFStatus;
import com.raytheon.uf.common.status.UFStatus.Priority;

/**
 * Command class that separates out the update bin functionality that was part
 * of the UpdateBinInfo function in the legacy Edited Events application.
 * 
 * 
 * <pre>
 *
 * SOFTWARE HISTORY
 *
 * Date         Ticket#    Engineer    Description
 * ------------ ---------- ----------- --------------------------
 * Jan 14, 2016  R9583     jtravis     Initial creation
 *
 * </pre>
 *
 * @author jtravis
 * @version 1.0	
 */
@DynamicSerialize
public class UpdateBinCommand extends BaseCommand {

    /**
     * The request id
     */
    @DynamicSerializeElement
    private final long id;
    
    /**
     * The start of the date time range to process events
     */
    @DynamicSerializeElement
    private long beginDTTM = 0L;

    /**
     * The end of the date time range to process events
     */
    @DynamicSerializeElement
    private long endDTTM = 0L;
    
    @DynamicSerializeElement
    private int eventId = 0;
    
    @DynamicSerializeElement
    private UpdateBinRequest request = null;
    
    /**
	 * Logger
	 */
    private static final IUFStatusHandler statusHandler = UFStatus.getHandler(UpdateBinCommand.class);
	
    /**
     * Dao for HouseKeeping records
     */
    private HouseKeepingDao houseKeepingDao = null;

    /**
     * Dao for EventBin records
     */
    private EventBinDao eventBinDao = null;

    /**
     * Dao for Event records
     */
    private EventsDao eventDao = null;

    /**
     * The event that is going to be associated to the new bin
     */
    private Event event = null;

    /**
     * The event bin that is going to be updated
     */
    private EventBin bin = null;

    /**
     * Creates a new UpdateBinCommand
     * 
     */
    public UpdateBinCommand() {
    	this.id = System.currentTimeMillis();
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
        if (this.event == null || this.bin == null) {
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
    	
    	statusHandler.handle(Priority.INFO, " EDITEDEVENTS.UpdateBinCommand...." );
    	
    	this.setStartTime();

        try {

            houseKeepingDao = new HouseKeepingDao();
            eventBinDao = new EventBinDao();
            eventDao = new EventsDao();            
           
            // If the event type is xray or has a region number, update the bin

            if (event.getType().equals(EventType.XRA.getType())) {
                bin.setBeginDTTM(AddBinCommand.getUniqueBinBeginDate(
                        event.getBeginDate(), eventBinDao));

                int dateDiff = 0;
                
                if (event.getBeginDate() != null && event.getEndDate() != null) {
	                dateDiff = event.getBeginDate().get(Calendar.DAY_OF_YEAR)
	                        - event.getEndDate().get(Calendar.DAY_OF_YEAR);
                }
                
            	statusHandler.handle(Priority.INFO, " EDITEDEVENTS.UpdateBinCommand.execute(), dateDiff = " + dateDiff);

            	//TODO: check the logic for assigning the bin end date
                if (dateDiff > 60) {
                    Calendar cal = event.getBeginDate();
                    cal.add(Calendar.HOUR_OF_DAY, 1);
                    bin.setEndDTTM(cal);
                } else {
                    bin.setEndDTTM(event.getEndDate());
                }

                // TODO: Change datatype of Event.type to EventType from String
                bin.setType(EditedEventsConstants.EventType.XRA.getType());

                eventBinDao.saveOrUpdate(bin);
                
                statusHandler.handle(Priority.INFO, " EDITEDEVENTS.UpdateBinCommand.execute(), bin updated");
            }

            // Add bin information to event
            event.setBin(bin);
            event.setChangeFlag(1);
            event.setId(this.eventId);
            
            // update the event
            eventDao.saveOrUpdate(event);
            
            statusHandler.handle(Priority.INFO, " EDITEDEVENTS.UpdateBinCommand.execute(), event updated to update bin number ");

        } catch (Exception e) {
            error = new EditedEventsException(
                    "ERROR - Exception occured while executing the AddBinCommand.",
                    e);
            e.printStackTrace();
        }        
        
        this.setEndTime();
        
		return this.createResponse();
    }
   
    private IResponse createResponse() {
    	
    	// create the response
    	UpdateBinResponse response = new UpdateBinResponse();

        if (this.hasError()) {
        	response.setError(this.getError());
        } 

        // populate the response
        response.setRequest(this.getRequest()); 
        response.setProcessingTime(this.getProcessingTime());
        
        return response;
		
	}

    /**
     * @return Event
     */
    public Event getEvent() {
        return event;
    }

    /**
     * @param event
     */
    public void setEvent(Event event) {
        this.event = event;
    }

    /**
     * @return EventBin
     */
    public EventBin getBin() {
        return bin;
    }

    /**
     * @param bin
     */
    public void setBin(EventBin bin) {
        this.bin = bin;
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
		this.request = (UpdateBinRequest) request;
	}

	@Override
	public UpdateBinRequest getRequest() {
		return this.request;
	}

	/**
	 * @return the eventId
	 */
	public int getEventId() {
		return eventId;
	}

	/**
	 * @param eventId the eventId to set
	 */
	public void setEventId(int eventId) {
		this.eventId = eventId;
	}

}
