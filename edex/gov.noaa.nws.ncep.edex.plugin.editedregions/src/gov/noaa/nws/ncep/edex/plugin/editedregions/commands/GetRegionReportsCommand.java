package gov.noaa.nws.ncep.edex.plugin.editedregions.commands;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.raytheon.uf.common.dataplugin.PluginException;
import com.raytheon.uf.common.status.IUFStatusHandler;
import com.raytheon.uf.common.status.UFStatus;

import gov.noaa.nws.ncep.common.dataplugin.editedregions.RegionReport;
import gov.noaa.nws.ncep.common.dataplugin.editedregions.exception.EditedRegionsException;
import gov.noaa.nws.ncep.common.dataplugin.editedregions.request.GetRegionReportsRequest;
import gov.noaa.nws.ncep.common.dataplugin.editedregions.request.intf.IRequest;
import gov.noaa.nws.ncep.common.dataplugin.editedregions.response.GetRegionReportsResponse;
import gov.noaa.nws.ncep.common.dataplugin.editedregions.response.intf.IResponse;
import gov.noaa.nws.ncep.common.dataplugin.editedregions.results.GetRegionReportsResults;
import gov.noaa.nws.ncep.edex.plugin.editedregions.dao.RegionReportsDao;

/**
 * The command class that is executed to obtain all region reports regardless of
 * if they have been assigned or unassigned to a specific region
 * 
 * 
 * @author jtravis
 * @version 1.0
 */
public class GetRegionReportsCommand extends BaseCommand {

    /**
     * The request from the client that resulted in creating an instance of the
     * command
     */
    private GetRegionReportsRequest request = null;

    /**
     * Dao for Region Report records
     */
    private RegionReportsDao regionReportsDao = null;

    /**
     * Logger
     */
    private static final IUFStatusHandler statusHandler = UFStatus
            .getHandler(GetRegionReportsCommand.class);

    /**
     * Default Constructor
     */
    public GetRegionReportsCommand() {
    }

    /*
     * (non-Javadoc)
     * 
     * @see gov.noaa.nws.ncep.edex.plugin.editedevents.commands.intf.ICommand#
     * getError()
     */
    @Override
    public EditedRegionsException getError() {
        return this.error;
    }

    /*
     * (non-Javadoc)
     * 
     * @see gov.noaa.nws.ncep.edex.plugin.editedevents.commands.intf.ICommand#
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
     * @see gov.noaa.nws.ncep.edex.plugin.editedevents.commands.intf.ICommand#
     * getStartTime()
     */
    @Override
    public long getStartTime() {
        return this.startTime;
    }

    /*
     * (non-Javadoc)
     * 
     * @see gov.noaa.nws.ncep.edex.plugin.editedevents.commands.intf.ICommand#
     * getEndTime()
     */
    @Override
    public long getEndTime() {
        return this.endTime;
    }

    /*
     * (non-Javadoc)
     * 
     * @see gov.noaa.nws.ncep.edex.plugin.editedevents.commands.intf.ICommand#
     * getProcessingTime()
     */
    @Override
    public long getProcessingTime() {
        long time = 0L;

        time = this.getEndTime() - this.getStartTime();

        return time;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * gov.noaa.nws.ncep.edex.plugin.editedevents.commands.intf.ICommand#isValid
     * () TODO - do we really need to validate the request here... TODO - the
     * request should be validated before adding it to the command
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

    /*
     * (non-Javadoc)
     * 
     * @see gov.noaa.nws.ncep.edex.plugin.editedevents.commands.intf.ICommand#
     * setRequest(gov.noaa.nws.ncep.common.dataplugin.editedevents.request.intf.
     * IRequest)
     */
    @Override
    public void setRequest(IRequest request) {
        this.request = (GetRegionReportsRequest) request;

    }

    /*
     * (non-Javadoc)
     * 
     * @see gov.noaa.nws.ncep.edex.plugin.editedevents.commands.intf.ICommand#
     * getRequest()
     */
    @Override
    public IRequest getRequest() {
        return (IRequest) this.request;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * gov.noaa.nws.ncep.edex.plugin.editedevents.commands.intf.ICommand#execute
     * ()
     */
    @Override
    public IResponse execute() {

        this.setStartTime();

        List<RegionReport> unAssignedRegionReports = new ArrayList<>();
        List<RegionReport> assignedRegionReports = new ArrayList<>();
        GetRegionReportsResponse response = new GetRegionReportsResponse();
        GetRegionReportsResults results = new GetRegionReportsResults();

        try {

            regionReportsDao = new RegionReportsDao();

            if (this.request.isObtainAssignedReports()) {

                assignedRegionReports = regionReportsDao
                        .getAssignedRegionReports(true);

                Map<Integer, RegionReport> assignedReportMap = new LinkedHashMap<>();
                for (RegionReport report : assignedRegionReports) {
                    assignedReportMap.put(report.getId(), report);
                }

                results.setAssignedRegionReports(assignedReportMap);
            }
            if (this.request.isObtainUnassignedReports()) {

                unAssignedRegionReports = regionReportsDao
                        .getUnAssignedRegionReports(true);

                Map<Integer, RegionReport> unassignedReportMap = new LinkedHashMap<>();
                for (RegionReport report : unAssignedRegionReports) {
                    unassignedReportMap.put(report.getId(), report);
                }

                results.setUnAssignedReports(unassignedReportMap);
            }

            response.setResults(results);

        } catch (PluginException e) {
            EditedRegionsException erx = new EditedRegionsException(e);
            response.setError(erx);
        }

        this.setEndTime();

        return response;
    }

}
