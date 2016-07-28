package gov.noaa.nws.ncep.edex.plugin.editedevents.commands;

import java.util.Calendar;

import com.raytheon.uf.common.status.IUFStatusHandler;
import com.raytheon.uf.common.status.UFStatus;

import gov.noaa.nws.ncep.common.dataplugin.editedevents.Event;
import gov.noaa.nws.ncep.common.dataplugin.editedevents.EventBin;
import gov.noaa.nws.ncep.common.dataplugin.editedevents.exception.EditedEventsException;
import gov.noaa.nws.ncep.common.dataplugin.editedevents.request.AddBinRequest;
import gov.noaa.nws.ncep.common.dataplugin.editedevents.request.AddNewBinForEventRequest;
import gov.noaa.nws.ncep.common.dataplugin.editedevents.request.intf.IRequest;
import gov.noaa.nws.ncep.common.dataplugin.editedevents.response.AddBinResponse;
import gov.noaa.nws.ncep.common.dataplugin.editedevents.response.AddNewBinForEventResponse;
import gov.noaa.nws.ncep.common.dataplugin.editedevents.response.ReVisitOldBinResponse;
import gov.noaa.nws.ncep.common.dataplugin.editedevents.response.intf.IResponse;
import gov.noaa.nws.ncep.common.dataplugin.editedevents.results.AddBinResults;
import gov.noaa.nws.ncep.common.dataplugin.editedevents.results.AddNewBinForEventResults;
import gov.noaa.nws.ncep.common.dataplugin.editedevents.util.EditedEventsConstants;
import gov.noaa.nws.ncep.edex.plugin.editedevents.dao.EventsDao;

/**
 * Command class used to bin an event in a new event bin. This will entail: a) creating a new bin,
 * b) updating housekeeping info table, c) seting the re-binnd report to sole best and d) determine
 * what happens to event status in the old bin.
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
public class AddNewBinForEventCommand extends BaseCommand {
	
	/**
     * The request from the client that resulted in creating an instance of the
     * command
     */
	private AddNewBinForEventRequest request = null;
	
	/**
	 * Logger
	 */
    private static final IUFStatusHandler statusHandler = UFStatus.getHandler(AddNewBinForEventCommand.class);


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
		 if (this.request != null && this.request.isValid()) {

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
		this.request = (AddNewBinForEventRequest) request;

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
		
		EventsDao eventDao = null;
		EventBin newBin = null;
		Integer originalStatus = null;
		AddBinCommand abCmd = null;
		ReVisitOldBinCommand rvobCmd = null;
		AddNewBinForEventResponse response = new AddNewBinForEventResponse();
		ReVisitOldBinResponse rvobResponse = null;
		AddBinResponse abResponse = null;
		
		// first confirm if the command is valid
		if (!this.isValid()) {

			EditedEventsException e = new EditedEventsException("ERROR - Command is Invalid");
			response.setError(e);
			response.setMessage("The command can be invalid if either the " +
									"command or the request associated with the " +
									"command is invalid.");
			
		} else {
			
			try {
			
				this.setStartTime();
				
				eventDao = new EventsDao();
				
				// the event to which the new bin will be assigned to
				Event event = this.request.getEvent();
				event.setId(this.request.getEventId());
				originalStatus = event.getStatusCd();
				
				// create the command to add a new bin 
				// and associate it with the event			
				AddBinRequest request = new AddBinRequest();
	        	request.setEvent(event);
	            
				abCmd = new AddBinCommand();			
				abCmd.setRequest(request); 
				
				abResponse = (AddBinResponse) abCmd.execute(); // execute the AddBinCommand
				
				// check that no errors occurred when
				// executing the AddBinCommand
				if (abResponse.hasErrors()) {
					response.setError(abResponse.getError());
					response.setMessage("ERROR - Error occured executing the AddBinCommand - " + abResponse.getError().getMessage());
				} else {
					
					// Assign new bin to event		            
					newBin = ((AddBinResults) abResponse.getResults()).getBin();					
					assignNewBinToEvent(event, newBin);
		            eventDao.saveOrUpdate(event);
					
					// If the event is optical -- add region to bin and events in bin		
					Calendar cal = Calendar.getInstance();
					cal.clear();
					cal.setTimeInMillis(this.request.getStartDTTM());
					 
					// The AssignRegionToBinCommand performs the logic that is found in the
					//  legacy applications OptRpt() function.
					AssignRegionToBinCommand cmd = new AssignRegionToBinCommand();
					cmd.setBeginDTTM(cal);
					cmd.setEvent(event);
					cmd.execute();
					
					// The event has now been associated with the new bin
					// and we need to make adjustments to the old bin that the
					// event used to be associated with (i.e., best selection modifications)
					rvobCmd = new ReVisitOldBinCommand();
					cal = Calendar.getInstance();
					cal.clear();
					cal.setTimeInMillis(this.request.getStartDTTM());
					
					rvobCmd.setBeginDTTM(cal);
					rvobCmd.setOldBinNumber(this.request.getOriginalBin());
					rvobCmd.setStatusOfRebinnedReport(originalStatus);
					rvobCmd.setTypeOfRebinnedReport(this.request.getEvent().getCodedType());
					
					rvobResponse = (ReVisitOldBinResponse) rvobCmd.execute(); // execute the ReVisitOldBinCommand
					
					// check that no errors occurred when executing the ReVisitOldBinCommand
					if (rvobResponse.hasErrors()) {
						response.setError(rvobResponse.getError());
						response.setMessage("ERROR - Error occured executing the AddNewBinForEventCommand - " + response.getError().getMessage());
					}
				}
			
			} catch (Exception e) {
	            error = new EditedEventsException(
	                    "ERROR - Exception occured while executing the AddNewBinForEventCommand.",
	                    e);
		    }
			this.setEndTime();
			
		}   
        
		return this.createResponse(newBin);
	}
	
	/**
	 * 
	 */
	private void assignNewBinToEvent(Event event, EventBin newBin) {
		
		event.setBin(newBin);	
        
        // If event is not an optical event, remove region number			
		switch (event.getCodedType()) {
			case 10: // FLA
			case 20: // BSL
			case 21: // DSF
			case 22: // EPL
			case 23: // LPS
			case 24: // SPY
				event.setRegion(EditedEventsConstants.MISSING_REGION_VAL);
				event.setChangeFlag(1);
				break;
			default:
				break;
		}	
		
		// Add statusCode and statusText to event
		event.setStatusCd(5);
		event.setStatusText("");
		event.setChangeFlag(1);
	}
	
	/**
	 * Create response
	 * 
	 * @param newBin
	 * @return
	 */
	private IResponse createResponse(EventBin newBin) {
		
		AddNewBinForEventResponse response = new AddNewBinForEventResponse();
		
		// Set the results
		AddNewBinForEventResults results = new AddNewBinForEventResults();
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
