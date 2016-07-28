/**
 * This code has unlimited rights, and is provided "as is" by the National Centers 
 * for Environmental Prediction, without warranty of any kind, either expressed or implied, 
 * including but not limited to the implied warranties of merchantability and/or fitness 
 * for a particular purpose.
 * 
 * This code has been developed by the NCEP-SIB for use in the AWIPS2 system.
 * 
 **/
package gov.noaa.nws.ncep.edex.plugin.editedevents.commands;

import gov.noaa.nws.ncep.common.dataplugin.editedevents.exception.EditedEventsException;
import gov.noaa.nws.ncep.common.dataplugin.editedevents.request.GetBinsRequest;
import gov.noaa.nws.ncep.common.dataplugin.editedevents.request.intf.IRequest;
import gov.noaa.nws.ncep.common.dataplugin.editedevents.response.GetBinsResponse;
import gov.noaa.nws.ncep.common.dataplugin.editedevents.response.intf.IResponse;
import gov.noaa.nws.ncep.common.dataplugin.editedevents.results.GetBinsResults;
import gov.noaa.nws.ncep.common.dataplugin.editedevents.results.intf.IResults;
import gov.noaa.nws.ncep.edex.plugin.editedevents.dao.EventBinDao;

import java.util.Calendar;
import java.util.List;

/**
 * The command class that is executed to obtain a
 * collection of event bins
 * 
 * <pre>
 *
 * SOFTWARE HISTORY
 *
 * Date         Ticket#    Engineer    Description
 * ------------ ---------- ----------- --------------------------
 * Jan 14, 2016 R9583      sgurung     Initial creation
 *
 * </pre>
 *
 * @author sgurung
 * @version 1.0	
 */
public class GetBinsCommand extends BaseCommand {

	/**
	 * The request supplied from the client
	 */
	private GetBinsRequest request = null;
	
	/**
	 *
	 */
	private EventBinDao eventBinDao = null;
	
	
	/**
	 * 
	 */
	public GetBinsCommand() {
	}

	/* (non-Javadoc)
	 * @see gov.noaa.nws.ncep.edex.plugin.editedevents.commands.intf.ICommand#getError()
	 */
	@Override
	public EditedEventsException getError() {
		
		return error;
	}

	/* (non-Javadoc)
	 * @see gov.noaa.nws.ncep.edex.plugin.editedevents.commands.intf.ICommand#hasError()
	 */
	@Override
	public boolean hasError() {

		boolean hasError = false;
		
		if (error != null) {
			hasError = true;
		}
		
		return hasError;
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
		
		boolean isValid = false;
		
		if (request != null) {
			isValid = true;
		}
		
		return isValid;
	}

	/* (non-Javadoc)
	 * @see gov.noaa.nws.ncep.edex.plugin.editedevents.commands.intf.ICommand#setRequest(gov.noaa.nws.ncep.common.dataplugin.editedevents.request.intf.IRequest)
	 */
	@Override
	public void setRequest(IRequest request) {
		this.request = (GetBinsRequest) request;

	}

	/* (non-Javadoc)
	 * @see gov.noaa.nws.ncep.edex.plugin.editedevents.commands.intf.ICommand#getRequest()
	 */
	@Override
	public GetBinsRequest getRequest() {
		return request;
	}

	/* (non-Javadoc)
	 * @see gov.noaa.nws.ncep.edex.plugin.editedevents.commands.intf.ICommand#execute()
	 */
	@Override
	public IResponse execute() {
		
		this.setStartTime();
		
		Calendar start = Calendar.getInstance();
		Calendar end = Calendar.getInstance();
		start.clear();
		end.clear();
		start.setTimeInMillis(this.request.getBeginDTTM());
		end.setTimeInMillis(this.request.getEndDTTM());
		
		this.eventBinDao = new EventBinDao();
		
		List<Integer> eventBins = this.eventBinDao.getBins(start, end);
		
		GetBinsResults results = new GetBinsResults();
		results.setEventBins(eventBins);
		
		this.setEndTime();
		
		return this.createResponse(results);

	}
	
	/**
	 * @param results
	 * @return IResponse
	 */
	private IResponse createResponse(IResults results) {
		
		GetBinsResponse response = new GetBinsResponse();

        if (this.hasError()) {
        	response.setError(this.getError());
        } else {
        	response.setResults(results); 
        }

        // populate the response and the results
        response.setRequest(this.getRequest()); 
        response.setProcessingTime(this.getProcessingTime());
        
        return response;
		
	}

}
