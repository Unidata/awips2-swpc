package gov.noaa.nws.ncep.edex.plugin.editedevents.commands;

import gov.noaa.nws.ncep.common.dataplugin.editedevents.Event;
import gov.noaa.nws.ncep.common.dataplugin.editedevents.exception.EditedEventsException;
import gov.noaa.nws.ncep.common.dataplugin.editedevents.request.AddEventRequest;
import gov.noaa.nws.ncep.common.dataplugin.editedevents.request.CreateCompositeEventRequest;
import gov.noaa.nws.ncep.common.dataplugin.editedevents.request.intf.IRequest;
import gov.noaa.nws.ncep.common.dataplugin.editedevents.response.AddEventResponse;
import gov.noaa.nws.ncep.common.dataplugin.editedevents.response.CreateCompositeEventResponse;
import gov.noaa.nws.ncep.common.dataplugin.editedevents.response.intf.IResponse;
import gov.noaa.nws.ncep.common.dataplugin.editedevents.results.AddEventResults;
import gov.noaa.nws.ncep.common.dataplugin.editedevents.results.CreateCompositeEventResults;
import gov.noaa.nws.ncep.common.dataplugin.editedevents.results.intf.IResults;

/**
 * The command class used to create a composite event. 
 * A composite report may only be composed based on a best report.
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
public class CreateCompositeEventCommand extends BaseCommand {

    /**
     * The request from the client that resulted in creating an instance of the
     * command
     */
    private CreateCompositeEventRequest request = null;
    
	/**
	 * The error
	 */
	private EditedEventsException error = null;
	
	/**
	 * 
	 */
	public CreateCompositeEventCommand() {
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
		if (this.request != null && 
				this.request.isValid()) {

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
		this.request = (CreateCompositeEventRequest) request;
		
	}

	/* (non-Javadoc)
	 * @see gov.noaa.nws.ncep.edex.plugin.editedevents.commands.intf.ICommand#getRequest()
	 */
	@Override
	public CreateCompositeEventRequest getRequest() {
		return this.request;
	}

	/* (non-Javadoc)
	 * @see gov.noaa.nws.ncep.edex.plugin.editedevents.commands.intf.ICommand#execute()
	 */
	@Override
	public IResponse execute() {
		
		this.setStartTime();
    	    	
		CreateCompositeEventResults results = new CreateCompositeEventResults();
    	AddEventResponse addEventResponse = null;
    	AddEventCommand addEventCmd = null;
    	Event compositeEvent = null;
    	Integer compositeEventID = null;
    	
    	AddEventRequest addEventRequest = new AddEventRequest();
    	addEventRequest.setEvent(this.request.getEvent());
    	addEventRequest.setEventID(this.request.getEventID());
    	
    	if (addEventRequest.isValid()) {
    		
    		addEventCmd = new AddEventCommand();
    		addEventCmd.setRequest(addEventRequest);
    		addEventResponse = (AddEventResponse) addEventCmd.execute();
    		
    		if (!addEventResponse.hasErrors() && addEventResponse.getResults() != null) {
    			compositeEvent = ((AddEventResults) addEventResponse.getResults()).getEvent();
    			addEventRequest = (AddEventRequest) addEventResponse.getRequest();
    			compositeEventID = addEventRequest.getEventID();
    			
    			results.setEvent(compositeEvent);
    			results.setEventID(compositeEventID);
    			
    		} else {
    			EditedEventsException exception = new EditedEventsException(addEventResponse.getMessage());
    			this.error = exception;
    		}    		
    		
    	} else {
    		String errorMsg = "ERROR - AddEventRequest inside CreateCompositeEventCommand is not valid";
			EditedEventsException exception = new EditedEventsException(errorMsg);
			this.error = exception;
    	}
    			
		this.setEndTime();
		
		return this.createResponse(results);
	}
	
	private IResponse createResponse(IResults results) {
		
		CreateCompositeEventResponse response = new CreateCompositeEventResponse();

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
