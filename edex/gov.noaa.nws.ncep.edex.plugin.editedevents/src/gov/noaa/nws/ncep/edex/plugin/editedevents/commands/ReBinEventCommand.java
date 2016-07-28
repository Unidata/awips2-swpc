package gov.noaa.nws.ncep.edex.plugin.editedevents.commands;

import java.util.Calendar;

import gov.noaa.nws.ncep.common.dataplugin.editedevents.EventBin;
import gov.noaa.nws.ncep.common.dataplugin.editedevents.exception.EditedEventsException;
import gov.noaa.nws.ncep.common.dataplugin.editedevents.request.ReBinEventRequest;
import gov.noaa.nws.ncep.common.dataplugin.editedevents.request.SelectBestEventRequest;
import gov.noaa.nws.ncep.common.dataplugin.editedevents.request.intf.IRequest;
import gov.noaa.nws.ncep.common.dataplugin.editedevents.response.ReBinEventResponse;
import gov.noaa.nws.ncep.common.dataplugin.editedevents.response.ReVisitOldBinResponse;
import gov.noaa.nws.ncep.common.dataplugin.editedevents.response.SelectBestEventResponse;
import gov.noaa.nws.ncep.common.dataplugin.editedevents.response.UpdateBinResponse;
import gov.noaa.nws.ncep.common.dataplugin.editedevents.response.intf.IResponse;
import gov.noaa.nws.ncep.common.dataplugin.editedevents.util.EditedEventsConstants;
import gov.noaa.nws.ncep.edex.plugin.editedevents.dao.EventBinDao;

/**
 * Command class use to perform the rebinning of events.
 * 
 * <pre>
 *
 * SOFTWARE HISTORY
 *
 * Date         Ticket#    Engineer    Description
 * ------------ ---------- ----------- --------------------------
 * Mar 14, 2016  R9583     jtravis     Initial creation
 *
 * </pre>
 *
 * @author jtravis
 * @version 1.0	
 */
public class ReBinEventCommand extends BaseCommand {

	/**
	 * The request supplied from the client
	 */
	private ReBinEventRequest request = null;
	
	/**
	 * The error
	 */
	private EditedEventsException error = null;
	
	/**
	 * 
	 */
	public ReBinEventCommand() {
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
	 */
	@Override
	public boolean isValid() {
		if (this.request == null || !this.request.isValid()) {
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
		this.request = (ReBinEventRequest) request;
	}

	/* (non-Javadoc)
	 * @see gov.noaa.nws.ncep.edex.plugin.editedevents.commands.intf.ICommand#getRequest()
	 */
	@Override
	public ReBinEventRequest getRequest() {
		return this.request;
	}

	/* (non-Javadoc)
	 * @see gov.noaa.nws.ncep.edex.plugin.editedevents.commands.intf.ICommand#execute()
	 */
	@Override
	public IResponse execute() {
		
		EventBinDao eventBinDao = null;
		EventBin newBin = null;
		UpdateBinCommand ubCmd = null;
		ReVisitOldBinCommand rvobCmd = null;
		ReBinEventResponse rbeResponse = new ReBinEventResponse();
		ReVisitOldBinResponse rvobResponse = null;
		UpdateBinResponse ubResponse = null;
		
		// first confirm if the command is valid
		if (!this.isValid()) {

			EditedEventsException e = new EditedEventsException("ERROR - Command is Invalid");
			rbeResponse.setError(e);
			rbeResponse.setMessage("The command can be invalid if either the " +
									"command or the request associated with the " +
									"command is invalid.");
			
		} else {
			
			this.setStartTime();
			
			eventBinDao = new EventBinDao();
			
			// obtain the bin the event will be assigned too
			newBin = eventBinDao.queryByBinNumber(this.request.getNewBin());
			
			// create the command to update the bin
			// associated with an event
			ubCmd = new UpdateBinCommand();
			ubCmd.setEventId(this.request.getEventId()); // the events unique id
			ubCmd.setEvent(this.request.getEvent()); // the event to update bin assignment
			ubCmd.setBin(newBin); // the new bin for the event
			
			ubResponse = (UpdateBinResponse) ubCmd.execute(); // execute the UpdateBinCommand
			
			// check that no errors occurred when
			// executing the UpdateBinCommand
			if (ubResponse.hasErrors()) {
				rbeResponse.setError(ubResponse.getError());
				rbeResponse.setMessage("ERROR - Error occured executing the UpdateBinCommand - " + ubResponse.getError().getMessage());
			} else {
				
				// Select best event in new bin
				SelectBestEventCommand sbeCmd = new SelectBestEventCommand();
				
				SelectBestEventRequest sbeRequest = new SelectBestEventRequest();				
				sbeRequest.setBeginDTTM(this.request.getStartDTTM());
				sbeRequest.setEventID(this.request.getEventId());
				sbeRequest.setOrigin(EditedEventsConstants.Origin.REBIN_EVENT);
				
				sbeCmd.setRequest(sbeRequest);
				
				SelectBestEventResponse sbeResponse = (SelectBestEventResponse) sbeCmd.execute();				
				
				
				// The event has now been associated with the new bin
				// and we need to make adjustments to the bin that the
				// event used to be associated with
				rvobCmd = new ReVisitOldBinCommand();
				Calendar cal = Calendar.getInstance();
				cal.clear();
				cal.setTimeInMillis(this.request.getStartDTTM());
				
				rvobCmd.setBeginDTTM(cal);
				rvobCmd.setOldBinNumber(this.request.getCurrentBin());
				rvobCmd.setStatusOfRebinnedReport(this.request.getEvent().getStatusCd());
				rvobCmd.setTypeOfRebinnedReport(this.request.getEvent().getCodedType());
				
				rvobResponse = (ReVisitOldBinResponse) rvobCmd.execute(); // execute the ReVisitOldBinCommand
				
				// check that no errors occurred when
				// executing the ReVisitOldBinCommand
				if (rvobResponse.hasErrors()) {
					rbeResponse.setError(rvobResponse.getError());
					rbeResponse.setMessage("ERROR - Error occured executing the ReVisitOldBinCommand - " + rvobResponse.getError().getMessage());
				}
			}
			
			
			this.setEndTime();
			
		}   
        
        rbeResponse.setRequest(this.getRequest()); 
        rbeResponse.setProcessingTime(this.getProcessingTime());
		
		return rbeResponse;
	}
}
