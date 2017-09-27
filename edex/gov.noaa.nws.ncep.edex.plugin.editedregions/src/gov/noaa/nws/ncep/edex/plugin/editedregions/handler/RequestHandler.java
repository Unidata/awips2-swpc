package gov.noaa.nws.ncep.edex.plugin.editedregions.handler;

import com.raytheon.uf.common.dataplugin.PluginException;
import com.raytheon.uf.common.serialization.comm.IRequestHandler;
import com.raytheon.uf.common.serialization.comm.IServerRequest;

import gov.noaa.nws.ncep.common.dataplugin.editedregions.exception.EditedRegionsException;
import gov.noaa.nws.ncep.common.dataplugin.editedregions.request.CreateRegionReportRequest;
import gov.noaa.nws.ncep.common.dataplugin.editedregions.request.CreateRegionRequest;
import gov.noaa.nws.ncep.common.dataplugin.editedregions.request.ExitRequest;
import gov.noaa.nws.ncep.common.dataplugin.editedregions.request.GetConsensusRequest;
import gov.noaa.nws.ncep.common.dataplugin.editedregions.request.GetLatestRegionRequest;
import gov.noaa.nws.ncep.common.dataplugin.editedregions.request.GetReferenceDataRequest;
import gov.noaa.nws.ncep.common.dataplugin.editedregions.request.GetRegionReportsRequest;
import gov.noaa.nws.ncep.common.dataplugin.editedregions.request.GetRegionsRequest;
import gov.noaa.nws.ncep.common.dataplugin.editedregions.request.ProcessIngestedReportsRequest;
import gov.noaa.nws.ncep.common.dataplugin.editedregions.request.UnknownRequest;
import gov.noaa.nws.ncep.common.dataplugin.editedregions.request.UpdateRegionReportRequest;
import gov.noaa.nws.ncep.common.dataplugin.editedregions.request.ViewRegionReportHistoryRequest;
import gov.noaa.nws.ncep.common.dataplugin.editedregions.request.intf.IRequest;
import gov.noaa.nws.ncep.common.dataplugin.editedregions.response.CreateRegionReportResponse;
import gov.noaa.nws.ncep.common.dataplugin.editedregions.response.CreateRegionResponse;
import gov.noaa.nws.ncep.common.dataplugin.editedregions.response.ExitResponse;
import gov.noaa.nws.ncep.common.dataplugin.editedregions.response.GetConsensusResponse;
import gov.noaa.nws.ncep.common.dataplugin.editedregions.response.GetLatestRegionResponse;
import gov.noaa.nws.ncep.common.dataplugin.editedregions.response.GetReferenceDataResponse;
import gov.noaa.nws.ncep.common.dataplugin.editedregions.response.GetRegionReportsResponse;
import gov.noaa.nws.ncep.common.dataplugin.editedregions.response.GetRegionsResponse;
import gov.noaa.nws.ncep.common.dataplugin.editedregions.response.ProcessIngestedReportsResponse;
import gov.noaa.nws.ncep.common.dataplugin.editedregions.response.UnknownRequestResponse;
import gov.noaa.nws.ncep.common.dataplugin.editedregions.response.UpdateRegionReportResponse;
import gov.noaa.nws.ncep.common.dataplugin.editedregions.response.ViewRegionReportHistoryResponse;
import gov.noaa.nws.ncep.edex.plugin.editedregions.commands.CreateRegionCommand;
import gov.noaa.nws.ncep.edex.plugin.editedregions.commands.CreateRegionReportCommand;
import gov.noaa.nws.ncep.edex.plugin.editedregions.commands.ExitCommand;
import gov.noaa.nws.ncep.edex.plugin.editedregions.commands.GetConsensusCommand;
import gov.noaa.nws.ncep.edex.plugin.editedregions.commands.GetLatestRegionCommand;
import gov.noaa.nws.ncep.edex.plugin.editedregions.commands.GetReferenceDataCommand;
import gov.noaa.nws.ncep.edex.plugin.editedregions.commands.GetRegionReportsCommand;
import gov.noaa.nws.ncep.edex.plugin.editedregions.commands.GetRegionsCommand;
import gov.noaa.nws.ncep.edex.plugin.editedregions.commands.ProcessIngestedReportsCommand;
import gov.noaa.nws.ncep.edex.plugin.editedregions.commands.UpdateRegionReportCommand;
import gov.noaa.nws.ncep.edex.plugin.editedregions.commands.ViewRegionReportHistoryCommand;

/**
 * Class that provides the capability to initialize commands based on requests
 * provided by client components and execute the commands.
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

        case "GetConsensusRequest":
            GetConsensusRequest getConsensusRequest = (GetConsensusRequest) Class
                    .forName(requestClassName).cast(request);

            GetConsensusResponse getConsensusResponse = null;

            if (!getConsensusRequest.isValid()) {
                getConsensusResponse = new GetConsensusResponse();
                EditedRegionsException e = new EditedRegionsException(
                        "ERROR - " + "Request Is Invalid");

                getConsensusResponse.setError(e);

            } else {

                GetConsensusCommand cmd = new GetConsensusCommand();

                cmd.setRequest(getConsensusRequest);

                // create the response
                getConsensusResponse = (GetConsensusResponse) cmd.execute();

            }

            return getConsensusResponse;

        case "ViewRegionReportHistoryRequest":
            ViewRegionReportHistoryRequest viewRegionReportHistoryRequest = (ViewRegionReportHistoryRequest) Class
                    .forName(requestClassName).cast(request);

            ViewRegionReportHistoryResponse viewRegionReportHistoryResponse = null;

            if (!viewRegionReportHistoryRequest.isValid()) {
                viewRegionReportHistoryResponse = new ViewRegionReportHistoryResponse();
                EditedRegionsException e = new EditedRegionsException(
                        "ERROR - " + "Request Is Invalid");

                viewRegionReportHistoryResponse.setError(e);

            } else {

                ViewRegionReportHistoryCommand cmd = new ViewRegionReportHistoryCommand();

                cmd.setRequest(viewRegionReportHistoryRequest);

                // create the response
                viewRegionReportHistoryResponse = (ViewRegionReportHistoryResponse) cmd
                        .execute();

            }

            return viewRegionReportHistoryResponse;

        case "GetLatestRegionRequest":
            GetLatestRegionRequest getLatestRegionRequest = (GetLatestRegionRequest) Class
                    .forName(requestClassName).cast(request);

            GetLatestRegionResponse getLatestRegionResponse = null;

            if (!getLatestRegionRequest.isValid()) {
                getLatestRegionResponse = new GetLatestRegionResponse();
                EditedRegionsException e = new EditedRegionsException(
                        "ERROR - " + "Request Is Invalid");

                getLatestRegionResponse.setError(e);

            } else {

                GetLatestRegionCommand cmd = new GetLatestRegionCommand();

                cmd.setRequest(getLatestRegionRequest);

                // create the response
                getLatestRegionResponse = (GetLatestRegionResponse) cmd
                        .execute();

            }

            return getLatestRegionResponse;

        case "GetRegionsRequest":
            GetRegionsRequest getRegionsRequest = (GetRegionsRequest) Class
                    .forName(requestClassName).cast(request);

            GetRegionsResponse getRegionsResponse = null;

            if (!getRegionsRequest.isValid()) {
                getRegionsResponse = new GetRegionsResponse();
                EditedRegionsException e = new EditedRegionsException(
                        "ERROR - " + "Request Is Invalid");

                getRegionsResponse.setError(e);

            } else {

                GetRegionsCommand cmd = new GetRegionsCommand();

                cmd.setRequest(getRegionsRequest);

                // create the response
                getRegionsResponse = (GetRegionsResponse) cmd.execute();

            }

            return getRegionsResponse;

        case "CreateRegionRequest":
            CreateRegionRequest createRegionRequest = (CreateRegionRequest) Class
                    .forName(requestClassName).cast(request);

            CreateRegionResponse createRegionResponse = null;

            if (!createRegionRequest.isValid()) {
                createRegionResponse = new CreateRegionResponse();
                EditedRegionsException e = new EditedRegionsException(
                        "ERROR - " + "Request Is Invalid");

                createRegionResponse.setError(e);

            } else {

                CreateRegionCommand cmd = new CreateRegionCommand();

                cmd.setRequest(createRegionRequest);

                // create the response
                createRegionResponse = (CreateRegionResponse) cmd.execute();

            }

            return createRegionResponse;

        case "GetReferenceDataRequest":
            GetReferenceDataRequest getReferenceDataRequest = (GetReferenceDataRequest) Class
                    .forName(requestClassName).cast(request);

            GetReferenceDataResponse getReferenceDataResponse = null;

            if (!getReferenceDataRequest.isValid()) {
                getReferenceDataResponse = new GetReferenceDataResponse();
                EditedRegionsException e = new EditedRegionsException(
                        "ERROR - " + "Request Is Invalid");

                getReferenceDataResponse.setError(e);

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

        case "ProcessIngestedReportsRequest":
            ProcessIngestedReportsRequest historyRequest = ProcessIngestedReportsRequest.class
                    .cast(request);

            ProcessIngestedReportsResponse historyResponse = null;
            if (!historyRequest.isValid()) {
                historyResponse = new ProcessIngestedReportsResponse();
                historyResponse.setError(
                        new EditedRegionsException("ERROR - Invalid request"));
            } else {
                ProcessIngestedReportsCommand historyCommand = new ProcessIngestedReportsCommand();
                historyCommand.setRequest(historyRequest);
                historyResponse = (ProcessIngestedReportsResponse) historyCommand
                        .execute();
            }

            return historyResponse;

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

            long startDTTM = System.currentTimeMillis();

            IRequest unknownRequest = (IRequest) Class.forName(requestClassName)
                    .cast(request);

            UnknownRequestResponse unknownRequestResponse = new UnknownRequestResponse();

            unknownRequestResponse
                    .setProcessingTime(System.currentTimeMillis() - startDTTM);
            unknownRequestResponse.setError(null);
            unknownRequestResponse.setResults(null);
            unknownRequestResponse.setRequest(unknownRequest);
            unknownRequestResponse
                    .setResponseTime(System.currentTimeMillis() - startDTTM);

            return unknownRequestResponse;
        }
    }
}
