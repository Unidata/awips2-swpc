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
import gov.noaa.nws.ncep.common.dataplugin.editedevents.request.GetStationsRequest;
import gov.noaa.nws.ncep.common.dataplugin.editedevents.request.intf.IRequest;
import gov.noaa.nws.ncep.common.dataplugin.editedevents.response.GetStationsResponse;
import gov.noaa.nws.ncep.common.dataplugin.editedevents.response.intf.IResponse;
import gov.noaa.nws.ncep.common.dataplugin.editedevents.results.GetStationsResults;
import gov.noaa.nws.ncep.common.swpcrefdb.Station;
import gov.noaa.nws.ncep.common.swpcrefdb.StationType;
import gov.noaa.nws.ncep.edex.swpcrefdb.dao.StationDao;
import gov.noaa.nws.ncep.edex.swpcrefdb.dao.StationTypeDao;

import java.util.Vector;
import com.raytheon.uf.common.status.IUFStatusHandler;
import com.raytheon.uf.common.status.UFStatus;
import com.raytheon.uf.common.status.UFStatus.Priority;

/**
 * The command class that is executed to obtain a
 * collection of stations
 * 
 * <pre>
 *
 * SOFTWARE HISTORY
 *
 * Date         Ticket#    Engineer    Description
 * ------------ ---------- ----------- --------------------------
 * Jan 14, 2016  R9583     sgurung     Initial creation
 *
 * </pre>
 *
 * @author sgurung
 * @version 1.0	
 */
public class GetStationsCommand extends BaseCommand {

	/**
	 * The request supplied from the client
	 */
	private GetStationsRequest request = null;	
	
	/**
	 * Logger
	 */
	 private static final IUFStatusHandler statusHandler = UFStatus.getHandler(GetStationsCommand.class);
		
	/**
	 * Default constructor
	 */
	public GetStationsCommand() {
		
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
		this.request = (GetStationsRequest) request;

	}

	/* (non-Javadoc)
	 * @see gov.noaa.nws.ncep.edex.plugin.editedevents.commands.intf.ICommand#getRequest()
	 */
	@Override
	public GetStationsRequest getRequest() {
		return request;
	}

	/* (non-Javadoc)
	 * @see gov.noaa.nws.ncep.edex.plugin.editedevents.commands.intf.ICommand#execute()
	 */
	@Override
	public IResponse execute() {
		
		statusHandler.handle(Priority.INFO, " EDITEDEVENTS.GetStationsCommand.execute()... ");
		
		this.setStartTime();
		
		StationDao stationDao = new StationDao();
		StationTypeDao stationTypeDao = new StationTypeDao();
		Vector<Station> stations = null;
		StationType reqStationType = null;		
		reqStationType = stationTypeDao.getStationType(request.getStationType());
		
		statusHandler.handle(Priority.INFO, " EDITEDEVENTS.GetStationsCommand.execute() reqStationType = " + reqStationType);
		
		if (reqStationType != null) {
			stations = stationDao.getStations(reqStationType);			
			
		} else {
			String errorMsg = "ERROR - Station Type provided does not exist";
			EditedEventsException exception = new EditedEventsException(errorMsg);
			this.error = exception;
		}
		
		this.setEndTime();
		
		return this.createResponse(stations);

	}

	/**
	 * Create the response
	 * 
	 * @param stations
	 * @return IResponse
	 */
	private IResponse createResponse(Vector<Station> stations) { 
		
		GetStationsResponse response = new GetStationsResponse();
		
		GetStationsResults results = new GetStationsResults();
		results.setResults(stations);

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
