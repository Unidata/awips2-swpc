package gov.noaa.nws.ncep.edex.plugin.editedevents.commands;

import gov.noaa.nws.ncep.common.dataplugin.editedevents.Event;
import gov.noaa.nws.ncep.common.dataplugin.editedevents.exception.EditedEventsException;
import gov.noaa.nws.ncep.common.dataplugin.editedevents.request.GetEventsRequest;
import gov.noaa.nws.ncep.common.dataplugin.editedevents.request.intf.IRequest;
import gov.noaa.nws.ncep.common.dataplugin.editedevents.response.GetEventsResponse;
import gov.noaa.nws.ncep.common.dataplugin.editedevents.response.intf.IResponse;
import gov.noaa.nws.ncep.common.dataplugin.editedevents.results.GetEventsResults;
import gov.noaa.nws.ncep.edex.plugin.editedevents.dao.EventsDao;

import java.util.Calendar;
import java.util.LinkedHashMap;
import java.util.List;

import com.raytheon.uf.common.dataplugin.PluginException;

/**
 * The command class that is executed to obtain a
 * collection of events
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
public class GetEventsCommand extends BaseCommand {

	/**
	 * The request supplied from the client
	 */
	private GetEventsRequest request = null;
		
	/**
	 * 
	 */
	public GetEventsCommand() {
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
		if (this.request == null) {
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
		this.request = (GetEventsRequest) request;
		
	}

	/* (non-Javadoc)
	 * @see gov.noaa.nws.ncep.edex.plugin.editedevents.commands.intf.ICommand#getRequest()
	 */
	@Override
	public GetEventsRequest getRequest() {
		return this.request;
	}

	/* (non-Javadoc)
	 * @see gov.noaa.nws.ncep.edex.plugin.editedevents.commands.intf.ICommand#execute()
	 */
	@Override
	public IResponse execute() {
		
		this.setStartTime();
		
		List<Event> events = null; // holds the events returned by call to hibernate
		Calendar start = null;
		Calendar end = null;
		
		try {
			
			EventsDao dao = new EventsDao();
			
			// What is the criteria for obtaining events?
			switch (request.getCriteria()) {
			
			case EVENTS_WITH_THIS_BIN_NUMBER_AND_DATE_RANGE:
				
				start = Calendar.getInstance();
				end = Calendar.getInstance();
				start.clear();
				end.clear();
				start.setTimeInMillis(this.request.getBeginDTTM());
				end.setTimeInMillis(this.request.getEndDTTM());
				
				events = dao.getEvents(start,
								end,
								this.request.getBinNumber().intValue(),
								this.request.isInclusiveOfStartDate(),
								this.request.isInclusiveOfEndDate(),
								this.request.isSortEnabled(),
								this.request.getSortBy());
				
				break;
				case EVENTS_WITH_THIS_AGE_AND_DATE_RANGE:
				
				start = Calendar.getInstance();
				end = Calendar.getInstance();
				start.clear();
				end.clear();
				start.setTimeInMillis(this.request.getBeginDTTM());
				end.setTimeInMillis(this.request.getEndDTTM());
				
				events = dao.getEvents(start,
								end,
								this.request.getAge(),
								this.request.isInclusiveOfStartDate(),
								this.request.isInclusiveOfEndDate(),
								this.request.isSortEnabled(),
								this.request.getSortBy());
				
				break;
				case EVENTS_WITH_THIS_STATUS_TEXT_AND_DATE_RANGE:
					
					start = Calendar.getInstance();
					end = Calendar.getInstance();
					start.clear();
					end.clear();
					start.setTimeInMillis(this.request.getBeginDTTM());
					end.setTimeInMillis(this.request.getEndDTTM());
					
					if (this.request.getStatusText().contains("OR")) {
						String[] statuses = new String[2];
						statuses = this.request.getStatusText().split("OR");
						
						events = dao.getEvents(start,
								end,
								this.request.isInclusiveOfStartDate(),
								this.request.isInclusiveOfEndDate(),
								this.request.isSortEnabled(),
								this.request.getSortBy(),
								statuses[0].trim(), statuses[1].trim());
						
					} else {
					
						events = dao.getEvents(start,
										end,
										this.request.isInclusiveOfStartDate(),
										this.request.isInclusiveOfEndDate(),
										this.request.isSortEnabled(),
										this.request.getSortBy(),
										this.request.getStatusText());
					}
					
					break;
				case EVENTS_WITH_THIS_BIN_NUMBER_AND_AGE_AND_DATE_RANGE:
					
					start = Calendar.getInstance();
					end = Calendar.getInstance();
					start.clear();
					end.clear();
					start.setTimeInMillis(this.request.getBeginDTTM());
					end.setTimeInMillis(this.request.getEndDTTM());
					
					events = dao.getEvents(start,
									end,
									this.request.getBinNumber().intValue(),
									this.request.getAge(),
									this.request.isInclusiveOfStartDate(),
									this.request.isInclusiveOfEndDate(),
									this.request.isSortEnabled(),
									this.request.getSortBy());
					
					break;
					case EVENTS_WITH_THIS_BIN_NUMBER_AND_STATUS_TEXT_AND_DATE_RANGE:
					
					start = Calendar.getInstance();
					end = Calendar.getInstance();
					start.clear();
					end.clear();
					start.setTimeInMillis(this.request.getBeginDTTM());
					end.setTimeInMillis(this.request.getEndDTTM());
					
					events = dao.getEvents(start,
									end,
									this.request.getBinNumber().intValue(),
									this.request.isInclusiveOfStartDate(),
									this.request.isInclusiveOfEndDate(),
									this.request.isSortEnabled(),
									this.request.getSortBy(),
									this.request.getStatusText());
					
					break;
			case EVENTS_WITH_THIS_BIN_NUMBER:
				
				events = dao.getEventsByBin(this.request.getBinNumber().intValue(),
											false,
											this.request.isSortEnabled());
				
				break;
			case EVENTS_WITHOUT_THIS_BIN_NUMBER:
				
				events = dao.getEventsByBin(this.request.getBinNumber().intValue(),
						true,
						this.request.isSortEnabled());
				
				break;
			case EVENTS_BY_DATE_RANGE:
				
				start = Calendar.getInstance();
				end = Calendar.getInstance();
				start.clear();
				end.clear();
				start.setTimeInMillis(this.request.getBeginDTTM());
				end.setTimeInMillis(this.request.getEndDTTM());
				
				events = dao.getEvents(start,
								end,
								this.request.isInclusiveOfStartDate(),
								this.request.isInclusiveOfEndDate(),
								this.request.isSortEnabled(),
								this.request.getSortBy());
				
				
				break;
			case EVENTS_AFTER_START_DATE:
				
				start = Calendar.getInstance();
				start.clear();
				start.setTimeInMillis(this.request.getBeginDTTM());
				
				events = dao.getEventsAfterDate(start,
								this.request.isInclusiveOfStartDate(),
								this.request.isSortEnabled());
				
				break;
			case EVENTS_BEFORE_START_DATE:
				
				start = Calendar.getInstance();
				start.clear();
				start.setTimeInMillis(this.request.getBeginDTTM());
				
				events = dao.getEventsBeforeDate(start,
						this.request.isInclusiveOfStartDate(),
						this.request.isSortEnabled());
				
				break;
			case ALL:
				
				events = dao.getAllEvents(this.request.isSortEnabled());
				
				break;
			
			}
						
		} catch (PluginException e) {
			String errorMsg = "ERROR - PluginException Occured When Executing GetEventsCommand";
			EditedEventsException exception = new EditedEventsException(errorMsg);
			exception.setStackTrace(e.getStackTrace());
			this.error = exception;
		}
		
		
		this.setEndTime();
		
		return this.createResponse(events);
		
	}
	
	/**
	 * @param results
	 * @return IResponse
	 */
	private IResponse createResponse(List<Event> events) { 
		
		GetEventsResponse response = new GetEventsResponse();
		GetEventsResults results = new GetEventsResults();		
		
		LinkedHashMap<Integer, Event> eventsHT = new LinkedHashMap<Integer, Event>();
		 
    	int eventsSize = ((events!=null)?events.size():0);	 
	
    	for (int i=0; i<eventsSize; i++) {
	         Event event = events.get(i);
	         Integer id = event.getId();
	         
	         eventsHT.put(id, event);
    	}
    	
		results.setEvents(eventsHT);

		// populate the response and the results
        if (this.hasError()) {
        	response.setError(this.getError());
        } else {
        	response.setResults(results); 
        }        
        
        response.setRequest(this.getRequest()); 
        response.setProcessingTime(this.getProcessingTime());
        
        return response;
		
	}

}
