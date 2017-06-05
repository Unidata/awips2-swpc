package gov.noaa.nws.ncep.edex.plugin.editedregions.handler;

import com.raytheon.uf.common.dataplugin.PluginException;
import com.raytheon.uf.common.serialization.comm.IRequestHandler;
import com.raytheon.uf.common.serialization.comm.IServerRequest;

import gov.noaa.nws.ncep.common.dataplugin.editedregions.exception.EditedRegionsException;
import gov.noaa.nws.ncep.common.dataplugin.editedregions.request.CreateRegionReportRequest;
import gov.noaa.nws.ncep.common.dataplugin.editedregions.request.ExitRequest;
import gov.noaa.nws.ncep.common.dataplugin.editedregions.request.GetReferenceDataRequest;
import gov.noaa.nws.ncep.common.dataplugin.editedregions.request.GetRegionReportsRequest;
import gov.noaa.nws.ncep.common.dataplugin.editedregions.request.UnknownRequest;
import gov.noaa.nws.ncep.common.dataplugin.editedregions.request.UpdateRegionReportRequest;
import gov.noaa.nws.ncep.common.dataplugin.editedregions.response.CreateRegionReportResponse;
import gov.noaa.nws.ncep.common.dataplugin.editedregions.response.ExitResponse;
import gov.noaa.nws.ncep.common.dataplugin.editedregions.response.GetReferenceDataResponse;
import gov.noaa.nws.ncep.common.dataplugin.editedregions.response.GetRegionReportsResponse;
import gov.noaa.nws.ncep.common.dataplugin.editedregions.response.UpdateRegionReportResponse;
import gov.noaa.nws.ncep.edex.plugin.editedregions.commands.CreateRegionReportCommand;
import gov.noaa.nws.ncep.edex.plugin.editedregions.commands.ExitCommand;
import gov.noaa.nws.ncep.edex.plugin.editedregions.commands.GetReferenceDataCommand;
import gov.noaa.nws.ncep.edex.plugin.editedregions.commands.GetRegionReportsCommand;
import gov.noaa.nws.ncep.edex.plugin.editedregions.commands.UpdateRegionReportCommand;

/**
 * Class that provides the capability to initialize commands based on requests
 * provided by client components and execute the commands
 * 
 * 
 * @author jtravis
 * @version 1.0
 */
public class RequestHandler implements IRequestHandler<IServerRequest> {

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.raytheon.uf.common.serialization.comm.IRequestHandler#handleRequest(
     * com.raytheon.uf.common.serialization.comm.IServerRequest)
     */
    public Object handleRequest(IServerRequest request)
            throws ClassNotFoundException, PluginException {

        // in the event someone pass a null request
        // create an UnknowRequest instance that
        // will then be returned on an UnknownRequestResponse
        // instance in the default operation in the switch statement below
        if (request == null) {
            request = new UnknownRequest();
        }

        String requestClassName = request.getClass().getName();
        String requestClassSimpleName = request.getClass().getSimpleName();

        switch (requestClassSimpleName) {

        case "GetReferenceDataRequest":
            GetReferenceDataRequest getReferenceDataRequest = (GetReferenceDataRequest) Class
                    .forName(requestClassName).cast(request);

            GetReferenceDataResponse getReferenceDataResponse = null;

            if (!getReferenceDataRequest.isValid()) {
                getReferenceDataResponse = new GetReferenceDataResponse();
                EditedRegionsException e = new EditedRegionsException(
                        "ERROR - " + "Request Is Invalid");

            } else {

                GetReferenceDataCommand cmd = new GetReferenceDataCommand();

                cmd.setRequest(getReferenceDataRequest);

                // create the response
                getReferenceDataResponse = (GetReferenceDataResponse) cmd
                        .execute();
            }

            return getReferenceDataResponse;

        case "GetRegionReportsRequest":
            GetRegionReportsRequest getReportsRequest = (GetRegionReportsRequest) Class
                    .forName(requestClassName).cast(request);

            GetRegionReportsResponse getReportsResponse = null;

            if (!getReportsRequest.isValid()) {

                getReportsResponse = new GetRegionReportsResponse();
                EditedRegionsException e = new EditedRegionsException(
                        "ERROR - " + "Request Is Invalid");
                getReportsResponse.setError(e);

            } else {

                GetRegionReportsCommand cmd = new GetRegionReportsCommand();

                cmd.setRequest(getReportsRequest);

                // create the response
                getReportsResponse = (GetRegionReportsResponse) cmd.execute();
            }

            return getReportsResponse;
        case "CreateRegionReportRequest":

            CreateRegionReportRequest createRegionReportRequest = (CreateRegionReportRequest) Class
                    .forName(requestClassName).cast(request);

            CreateRegionReportResponse createRegionReportResponse = null;

            if (!createRegionReportRequest.isValid()) {
                createRegionReportResponse = new CreateRegionReportResponse();
                EditedRegionsException e = new EditedRegionsException(
                        "ERROR - Request Is Invalid");
                createRegionReportResponse.setError(e);
            } else {
                // build the command which includes adding
                // the request to the command in the event
                // the request had parameters necessary to satisfy
                // the command
                CreateRegionReportCommand createRegionReportCmd = new CreateRegionReportCommand();
                createRegionReportCmd.setRequest(createRegionReportRequest);

                // create the response
                createRegionReportResponse = (CreateRegionReportResponse) createRegionReportCmd
                        .execute();
            }

            return createRegionReportResponse;

        case "UpdateRegionReportRequest":
            UpdateRegionReportRequest updateRegionReportRequest = (UpdateRegionReportRequest) Class
                    .forName(requestClassName).cast(request);

            UpdateRegionReportResponse updateRegionReportResponse = null;
            if (!updateRegionReportRequest.isValid()) {
                updateRegionReportResponse = new UpdateRegionReportResponse();
                updateRegionReportResponse.setError(
                        new EditedRegionsException("ERROR - Invalid Request"));
            } else {
                UpdateRegionReportCommand updateRegionReportCmd = new UpdateRegionReportCommand();
                updateRegionReportCmd.setRequest(updateRegionReportRequest);

                updateRegionReportResponse = (UpdateRegionReportResponse) updateRegionReportCmd
                        .execute();
            }
            return updateRegionReportResponse;

        case "CreateRegionRequest":
            // UpdateEventRequest updateEventRequest = (UpdateEventRequest)
            // Class.forName(requestClassName).cast(request);
            //
            // UpdateEventResponse updateEventResponse = null;
            //
            // if (!updateEventRequest.isValid()) {
            // updateEventResponse = new UpdateEventResponse();
            // EditedEventsException e = new EditedEventsException("ERROR -
            // Request Is Invalid");
            // updateEventResponse.setError(e);
            // } else {
            // // build the command which includes adding
            // // the request to the command in the event
            // // the request had parameters necessary to satisfy
            // // the command
            // UpdateEventCommand updateEventCommand = new UpdateEventCommand();
            // updateEventCommand.setRequest(updateEventRequest);
            //
            // // create the response
            // updateEventResponse = (UpdateEventResponse)
            // updateEventCommand.execute();
            // }
            //
            // return updateEventResponse;

        case "SaveRequest":
            // SaveRequest saveRequest = (SaveRequest) Class
            // .forName(requestClassName).cast(request);
            //
            // SaveResponse saveResponse = null;
            //
            // if (!saveRequest.isValid()) {
            // saveResponse = new SaveResponse();
            // EditedEventsException e = new EditedEventsException("ERROR -
            // Request Is Invalid");
            // saveResponse.setError(e);
            // } else {
            // // build the command which includes adding
            // // the request to the command in the event
            // // the request had parameters necessary to satisfy
            // // the command
            // SaveCommand saveCommand = new SaveCommand();
            // saveCommand.setRequest(saveRequest);
            //
            // // create the response
            // saveResponse = (SaveResponse) saveCommand.execute();
            // }
            //
            // return saveResponse;

        case "ProcessEventsRequest":

            // ProcessEventsRequest processEventsRequest =
            // (ProcessEventsRequest) Class
            // .forName(requestClassName).cast(request);
            //
            // ProcessEventsResponse processEventsResponse = new
            // ProcessEventsResponse();
            //
            // // Validate the request
            // if (!processEventsRequest.isValid()) {
            // EditedEventsException error = new EditedEventsException("ERROR -
            // ProcessEventsRequest IS NOT VALID");
            // processEventsResponse.setError(error);
            //
            // } else {
            // // build the command which includes adding
            // // the request to the command in the event
            // // the request had parameters necessary to satisfy
            // // the command
            // ProcessEventsCommand processEventsCommand = new
            // ProcessEventsCommand();
            // processEventsCommand.setRequest(processEventsRequest);
            //
            // // Execute the command and get the response
            // processEventsResponse = (ProcessEventsResponse)
            // processEventsCommand.execute();
            //
            // }
            //
            // return processEventsResponse;

        case "AddEventRequest":

            // AddEventRequest addEventRequest = (AddEventRequest)
            // Class.forName(
            // requestClassName).cast(request);
            //
            // // create the response
            // AddEventResponse addEventResponse = null;
            //
            // if (!addEventRequest.isValid()) {
            //
            // EditedEventsException exception = new
            // EditedEventsException("ERROR - AddEventRequest Is Not Valid");
            // addEventResponse = new AddEventResponse();
            // addEventResponse.setError(exception);
            //
            // } else {
            // // build the command which includes adding
            // // the request to the command in the event
            // // the request had parameters necessary to satisfy
            // // the command
            // GetAssignedRegionReportsCommand getAssignedRegionReportsCommand =
            // new GetAssignedRegionReportsCommand();
            // getAssignedRegionReportsCommand.setRequest(addEventRequest);
            //
            //// addEventResponse = (AddEventResponse)
            // this.processCommand(addEventCommand);
            // addEventResponse = (AddEventResponse)
            // getAssignedRegionReportsCommand.execute();
            //
            // }
            //
            // return addEventResponse;

        case "CreateCompositeEventRequest":

            // CreateCompositeEventRequest ccEventRequest =
            // (CreateCompositeEventRequest) Class.forName(
            // requestClassName).cast(request);
            //
            // // create the response
            // CreateCompositeEventResponse ccEventResponse = null;
            //
            // if (!ccEventRequest.isValid()) {
            //
            // EditedEventsException exception = new
            // EditedEventsException("ERROR - CreateCompositeEventRequest Is Not
            // Valid");
            // ccEventResponse = new CreateCompositeEventResponse();
            // ccEventResponse.setError(exception);
            //
            // } else {
            //
            // // build the command which includes adding
            // // the request to the command in the event
            // // the request had parameters necessary to satisfy
            // // the command
            // CreateCompositeEventCommand ccEventCommand = new
            // CreateCompositeEventCommand();
            // ccEventCommand.setRequest(ccEventRequest);
            //
            // ccEventResponse = (CreateCompositeEventResponse)
            // ccEventCommand.execute();
            //
            // }
            //
            // return ccEventResponse;

        case "GetEventsRequest":

            // GetEventsRequest getEventsRequest = (GetEventsRequest) Class
            // .forName(requestClassName).cast(request);
            //
            // GetEventsResponse getEventsResponse = null;
            //
            // if (!getEventsRequest.isValid()) {
            //
            // EditedEventsException exception = new
            // EditedEventsException("ERROR - GetEventsRequest Is Not Valid");
            // getEventsResponse = new GetEventsResponse();
            // getEventsResponse.setError(exception);
            //
            // } else {
            //
            // // build the command which includes adding
            // // the request to the command in the event
            // // the request had parameters necessary to satisfy
            // // the command
            // GetEventsCommand getEventsCommand = new GetEventsCommand();
            // getEventsCommand.setRequest(getEventsRequest);
            //
            // // create the response
            // getEventsResponse = (GetEventsResponse)
            // getEventsCommand.execute();
            //
            // }
            //
            // return getEventsResponse;

        case "ReBinEventRequest":

            // ReBinEventRequest reBinEventRequest = (ReBinEventRequest) Class
            // .forName(requestClassName).cast(request);
            //
            // ReBinEventResponse reBinEventResponse = null;
            //
            // if (!reBinEventRequest.isValid()) {
            // reBinEventResponse = new ReBinEventResponse();
            // EditedEventsException e = new EditedEventsException("ERROR -
            // Request Is Invalid");
            // reBinEventResponse.setError(e);
            // } else {
            //
            // // build the command which includes adding
            // // the request to the command in the event
            // // the request had parameters necessary to satisfy
            // // the command
            // ReBinEventCommand reBinEventCommand = new ReBinEventCommand();
            // reBinEventCommand.setRequest(reBinEventRequest);
            //
            // // execute the command and obtain the response
            // reBinEventResponse = (ReBinEventResponse)
            // reBinEventCommand.execute();
            //
            // }
            //
            // return reBinEventResponse;

        case "AddNewBinForEventRequest":

            // AddNewBinForEventRequest addNewBinForEventRequest =
            // (AddNewBinForEventRequest) Class
            // .forName(requestClassName).cast(request);
            //
            // AddNewBinForEventResponse addNewBinForEventResponse = null;
            //
            // if (!addNewBinForEventRequest.isValid()) {
            // addNewBinForEventResponse = new AddNewBinForEventResponse();
            // EditedEventsException e = new EditedEventsException("ERROR -
            // Request Is Invalid");
            // addNewBinForEventResponse.setError(e);
            // } else {
            //
            // // build the command which includes adding
            // // the request to the command in the event
            // // the request had parameters necessary to satisfy
            // // the command
            // AddNewBinForEventCommand addNewBinForEventCommand = new
            // AddNewBinForEventCommand();
            // addNewBinForEventCommand.setRequest(addNewBinForEventRequest);
            //
            // // execute the command and obtain the response
            // addNewBinForEventResponse = (AddNewBinForEventResponse)
            // addNewBinForEventCommand.execute();
            //
            // }
            //
            // return addNewBinForEventResponse;

        case "CreateCompositEventRequest":

            // CreateCompositeEventRequest createCompositEventRequest =
            // (CreateCompositeEventRequest) Class
            // .forName(requestClassName).cast(request);
            //
            // CreateCompositeEventResponse cceResponse = null;
            //
            // if (!createCompositEventRequest.isValid()) {
            // cceResponse = new CreateCompositeEventResponse();
            // EditedEventsException e = new EditedEventsException("ERROR -
            // Request Is Invalid");
            // cceResponse.setError(e);
            // } else {
            // // build the command which includes adding
            // // the request to the command in the event
            // // the request had parameters necessary to satisfy
            // // the command
            // CreateCompositeEventCommand createCompositEventCommand = new
            // CreateCompositeEventCommand();
            // createCompositEventCommand.setRequest(createCompositEventRequest);
            //
            // // create the response
            // cceResponse = (CreateCompositeEventResponse)
            // createCompositEventCommand.execute();
            // }
            //
            // return cceResponse;

        case "UpgradeEventRequest":
            // UpgradeEventRequest upgradeEventRequest = (UpgradeEventRequest)
            // Class.forName(requestClassName).cast(request);
            //
            // UpgradeEventResponse upgradeEventResponse = null;
            //
            // if (!upgradeEventRequest.isValid()) {
            // upgradeEventResponse = new UpgradeEventResponse();
            // EditedEventsException e = new EditedEventsException("ERROR -
            // Request Is Invalid");
            // upgradeEventResponse.setError(e);
            // } else {
            // // build the command which includes adding
            // // the request to the command in the event
            // // the request had parameters necessary to satisfy
            // // the command
            // UpgradeEventCommand upgradeEventCommand = new
            // UpgradeEventCommand();
            // upgradeEventCommand.setRequest(upgradeEventRequest);
            //
            // // create the response
            // upgradeEventResponse = (UpgradeEventResponse)
            // upgradeEventCommand.execute();
            // }
            //
            // return upgradeEventResponse;

        case "DowngradeEventRequest":
            // DowngradeEventRequest downgradeEventRequest =
            // (DowngradeEventRequest)
            // Class.forName(requestClassName).cast(request);
            //
            // DowngradeEventResponse downgradeEventResponse = null;
            //
            // if (!downgradeEventRequest.isValid()) {
            // downgradeEventResponse = new DowngradeEventResponse();
            // EditedEventsException e = new EditedEventsException("ERROR -
            // Request Is Invalid");
            // downgradeEventResponse.setError(e);
            // } else {
            // // build the command which includes adding
            // // the request to the command in the event
            // // the request had parameters necessary to satisfy
            // // the command
            // DowngradeEventCommand downgradeEventCommand = new
            // DowngradeEventCommand();
            // downgradeEventCommand.setRequest(downgradeEventRequest);
            //
            // // create the response
            // downgradeEventResponse = (DowngradeEventResponse)
            // downgradeEventCommand.execute();
            // }
            //
            // return downgradeEventResponse;

        case "GetBinsRequest":

            // GetBinsRequest getBinsRequest = (GetBinsRequest) Class.forName(
            // requestClassName).cast(request);
            //
            //
            // GetBinsResponse getBinsResponse = new GetBinsResponse();
            //
            // // Validate the request
            // if (!getBinsRequest.isValid()) {
            //
            // EditedEventsException error = new EditedEventsException("ERROR -
            // GetBinsRequest IS NOT VALID");
            // getBinsResponse.setError(error);
            //
            // } else {
            //
            // // build the command which includes adding
            // // the request to the command in the event
            // // the request had parameters necessary to satisfy
            // // the command
            // GetBinsCommand getBinsCommand = new GetBinsCommand();
            // getBinsCommand.setRequest(getBinsRequest);
            //
            // // Execute the command and get the response
            // getBinsResponse = (GetBinsResponse) getBinsCommand.execute();
            // }
            //
            //
            // return getBinsResponse;

        case "GetStationsRequest":

            // GetStationsRequest getStationsRequest = (GetStationsRequest)
            // Class.forName(
            // requestClassName).cast(request);
            //
            //
            // GetStationsResponse getStationsResponse = new
            // GetStationsResponse();
            //
            // // Validate the request
            // if (!getStationsRequest.isValid()) {
            //
            // EditedEventsException error = new EditedEventsException("ERROR -
            // GetStationsRequest IS NOT VALID");
            // getStationsResponse.setError(error);
            //
            // } else {
            //
            // // build the command
            // GetStationsCommand getStationsCommand = new GetStationsCommand();
            // getStationsCommand.setRequest(getStationsRequest);
            //
            // // Execute the command and get the response
            // getStationsResponse = (GetStationsResponse)
            // getStationsCommand.execute();
            //
            // }
            //
            // return getStationsResponse;

        case "ExitRequest":
            ExitRequest exitRequest = (ExitRequest) Class
                    .forName(requestClassName).cast(request);

            ExitResponse exitResponse = null;

            if (!exitRequest.isValid()) {
                exitResponse = new ExitResponse();
                EditedRegionsException e = new EditedRegionsException(
                        "ERROR -" + "Request Is Invalid");
                exitResponse.setError(e);
            } else {
                // build the command which includes adding
                // the request to the command in the event
                // the request had parameters necessary to satisfy
                // the command
                ExitCommand exitCommand = new ExitCommand();
                exitCommand.setRequest(exitRequest);

                // create the response
                exitResponse = (ExitResponse) exitCommand.execute();
            }

            return exitResponse;

        default: // notify caller that the request is not a known request type

            // long startDTTM = System.currentTimeMillis();
            //
            // IRequest unknownRequest = (IRequest)
            // Class.forName(requestClassName).cast(request);
            //
            // UnknownRequestResponse unknownRequestResponse = new
            // UnknownRequestResponse();
            //
            // unknownRequestResponse.setProcessingTime(System.currentTimeMillis()
            // - startDTTM);
            // unknownRequestResponse.setError(null);
            // unknownRequestResponse.setResults(null);
            // unknownRequestResponse.setRequest(unknownRequest);
            // unknownRequestResponse.setResponseTime(System.currentTimeMillis()
            // - startDTTM);
            //
            // return unknownRequestResponse;
            return null;
        }
    }
}
