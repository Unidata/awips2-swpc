package gov.noaa.nws.ncep.edex.plugin.editedevents.commands;

import java.util.Calendar;
import java.util.List;

import com.raytheon.uf.common.dataplugin.PluginException;
import com.raytheon.uf.edex.database.DataAccessLayerException;

import gov.noaa.nws.ncep.common.dataplugin.editedevents.Event;
import gov.noaa.nws.ncep.common.dataplugin.editedevents.EventBin;
import gov.noaa.nws.ncep.common.dataplugin.editedevents.exception.EditedEventsException;
import gov.noaa.nws.ncep.common.dataplugin.editedevents.request.SelectBestEventRequest;
import gov.noaa.nws.ncep.common.dataplugin.editedevents.request.intf.IRequest;
import gov.noaa.nws.ncep.common.dataplugin.editedevents.response.ReVisitOldBinResponse;
import gov.noaa.nws.ncep.common.dataplugin.editedevents.response.SelectBestEventResponse;
import gov.noaa.nws.ncep.common.dataplugin.editedevents.response.intf.IResponse;
import gov.noaa.nws.ncep.common.dataplugin.editedevents.util.EditedEventsConstants;
import gov.noaa.nws.ncep.edex.plugin.editedevents.dao.EventBinDao;
import gov.noaa.nws.ncep.edex.plugin.editedevents.dao.EventsDao;

/**
 * Command class used to re-check an old bin number that an event
 * had been assigned too.  The event was re-assigned to a new bin
 * so we need to see if any adjustments need to be made to the old
 * bin that an event was assigned to.
 * 
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
public class ReVisitOldBinCommand extends BaseCommand {
	
	private Integer statusOfRebinnedReport = null;
	
	private Integer typeOfRebinnedReport = null;
	
	/**
	 * User supplied date - this is the value taken from
	 * the Edited Events data/time range fields on the UI
	 */
	private Calendar beginDTTM = null;
	
	private EventsDao eventsDAO = null;
	
	private EventBinDao eventBinDAO = null;
	
	/**
	 * The old bin number
	 */
	private Integer oldBinNumber = null;
	
	/**
	 * 
	 */
	public ReVisitOldBinCommand() {
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
		if (this.oldBinNumber == null || 
				this.statusOfRebinnedReport == null || 
				this.typeOfRebinnedReport == null) {
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
		// no request is supplied for this command because
		// the command is never a direct request from the client
		;;
	}

	/* (non-Javadoc)
	 * @see gov.noaa.nws.ncep.edex.plugin.editedevents.commands.intf.ICommand#getRequest()
	 */
	@Override
	public IRequest getRequest() {
		// no request is returned for this command because
		// the command is never a direct request from the client
		return null;
	}
	
	/**
	 * @return the oldBinNumber
	 */
	public Integer getOldBinNumber() {
		return this.oldBinNumber;
	}

	/**
	 * @param oldBinNumber the oldBinNumber to set
	 */
	public void setOldBinNumber(Integer oldBinNumber) {
		this.oldBinNumber = oldBinNumber;
	}

	/* (non-Javadoc)
	 * @see gov.noaa.nws.ncep.edex.plugin.editedevents.commands.intf.ICommand#execute()
	 */
	@Override
	public IResponse execute() {
		
		ReVisitOldBinResponse response = new ReVisitOldBinResponse();
		List<Event> contenders = null;
		
		// its all about determining what happens to reports
		// in the old bin when one report has been re-binned.
		
		this.setStartTime();
		
		try {
			
			this.eventsDAO = new EventsDao();
			this.eventBinDAO = new EventBinDao();			
			
			// figure out what to do in the old bin if the
			// report with the bin change was a "best of group" or
			// a "contender"; no need for action if it was "sole best"
			// or "loser"
			switch (this.statusOfRebinnedReport) {
			case 3:
				// Re-binned report was a contender -- find other contenders
				// of that type in that bin; if multiple contenders are left,
				// do nothing; otherwise, upgrade the other contender.  Count
				// all reports of that type in that bin so a determination can
				// be made if the remaining contender should be "best of group"
				// or "sole best"
				// TODO - complete
				contenders = this.eventsDAO.findContenders(this.typeOfRebinnedReport,
												this.oldBinNumber,
												this.statusOfRebinnedReport,
												this.beginDTTM);
				
				// if there are no contenders or if there are more
				// than 1 contender do nothing;
				if (contenders.isEmpty() || contenders.size() > 1) {
					break;
				} else { // exactly 1 contender exists
					
					// upgrade the contender event
					Event contender = contenders.get(0);
					contender.setStatusCd(5);
					contender.setStatusText("");
					contender.setChangeFlag(1);
					
					// count all reports of that type in that bin so a
					// determination can be made if the remaining contender
					// should be "best of group" or "sole best"
					List<Event> events = this.eventsDAO.checkBestOfGroupStatus(this.typeOfRebinnedReport,
															this.oldBinNumber,
															2,
															this.beginDTTM);
					
					if (!events.isEmpty() && events.size() > 1) {
						Event event = events.get(0); // get the first event
						event.setStatusCd(4);
						event.setStatusText("+");
						
						this.eventsDAO.persist(event);
						
					}
					
				}
				
				// if optical, add region # (if it exists) to
				// bin and events in bin
				int regionTmp = -1; // default to -1 indicating that the region is null on event
				Event contender = contenders.get(0);
				if (contender.getRegion() != null) {
					regionTmp = contender.getRegion().intValue();
				}
				
				// The AssignRegionToBinCommand performs the logic
				// that is found in the legacy applications
				// OptRpt() function.
				AssignRegionToBinCommand cmd = new AssignRegionToBinCommand();
				cmd.setBeginDTTM(this.beginDTTM);
				cmd.setEvent(contender);
				cmd.execute();
				
				break;
				
			case 4:
				
				// Re-binned report was a best of group;
				// search for other reports and pick a best
				// TODO complete this section
				
				// search for other reports
				List<Event> otherReports = this.eventsDAO.searchReports(this.typeOfRebinnedReport,
											this.oldBinNumber, 2,
											this.beginDTTM);
				
				// if there are other reports
				// pick the best one
				if (!otherReports.isEmpty()) {
					
					EventBin oldBin = eventBinDAO.queryByBinNumber(this.oldBinNumber);
					
					SelectBestEventCommand sbeCmd = new SelectBestEventCommand();
					
					SelectBestEventRequest sbeRequest = new SelectBestEventRequest();					
					sbeRequest.setBeginDTTM(this.beginDTTM.getTimeInMillis());
					sbeRequest.setEventBin(oldBin);
					sbeRequest.setEventCodedType(this.typeOfRebinnedReport);
					sbeRequest.setOrigin(EditedEventsConstants.Origin.REVISIT_OLD_BIN);
										
					sbeCmd.setRequest(sbeRequest);
					
					SelectBestEventResponse sbeResponse = (SelectBestEventResponse) sbeCmd.execute();
					
					// TODO - add logic here to check the response and do something
					
				}
				
				break;
				
			default:
				// don't do anything if the event was a:
				// 1) Sole Best (statuscode = 5)
				// 2) Loser (statuscode = 2)
				// 3) Deleted (statuscode = 1)
				break;
				
			}
			
			
		} catch (PluginException e) {
			this.error = new EditedEventsException("ERROR - Exception While Executing ReVisitOldBinCommand - " + e.getMessage(), e);
			this.error.setStackTrace(e.getStackTrace());
		} catch (DataAccessLayerException e) {
			this.error = new EditedEventsException("ERROR - Exception Wile Executing ReVisitOldBinCommand - " + e.getMessage(), e);
			this.error.setStackTrace(e.getStackTrace());
		}

		this.setEndTime();
		
		response.setError(this.error);
		response.setProcessingTime(this.getProcessingTime());
		
		return response;
	}
	
	/**
	 * @return the statusOfRebinnedReport
	 */
	public Integer getStatusOfRebinnedReport() {
		return statusOfRebinnedReport;
	}

	/**
	 * @param statusOfRebinnedReport the statusOfRebinnedReport to set
	 */
	public void setStatusOfRebinnedReport(Integer statusOfRebinnedReport) {
		this.statusOfRebinnedReport = statusOfRebinnedReport;
	}

	/**
	 * @return the typeOfRebinnedReport
	 */
	public Integer getTypeOfRebinnedReport() {
		return typeOfRebinnedReport;
	}

	/**
	 * @param typeOfRebinnedReport the typeOfRebinnedReport to set
	 */
	public void setTypeOfRebinnedReport(Integer typeOfRebinnedReport) {
		this.typeOfRebinnedReport = typeOfRebinnedReport;
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

}
