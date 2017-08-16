package gov.noaa.nws.ncep.edex.plugin.editedregions.commands;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import com.raytheon.uf.common.time.DataTime;
import com.raytheon.uf.edex.database.DataAccessLayerException;

import gov.noaa.nws.ncep.common.dataplugin.editedregions.RegionHistoryReport;
import gov.noaa.nws.ncep.common.dataplugin.editedregions.RegionReport;
import gov.noaa.nws.ncep.common.dataplugin.editedregions.exception.EditedRegionsException;
import gov.noaa.nws.ncep.common.dataplugin.editedregions.request.CreateRegionHistoryReportRequest;
import gov.noaa.nws.ncep.common.dataplugin.editedregions.request.intf.IRequest;
import gov.noaa.nws.ncep.common.dataplugin.editedregions.response.CreateRegionHistoryReportResponse;
import gov.noaa.nws.ncep.common.dataplugin.editedregions.response.intf.IResponse;
import gov.noaa.nws.ncep.common.dataplugin.editedregions.results.CreateRegionHistoryReportResults;
import gov.noaa.nws.ncep.common.dataplugin.editedregions.util.EditedRegionsConstants;
import gov.noaa.nws.ncep.common.dataplugin.editedregions.util.EditedRegionsConstants.REGION_REPORT_CHANGE_TYPE;
import gov.noaa.nws.ncep.edex.plugin.editedregions.dao.RegionHistoryReportDao;

/**
 * The sub-command class that is executed to add a region history report item.
 * 
 * 
 * @author alockleigh
 * @version 1.0
 */
public class CreateRegionHistoryReportCommand extends BaseCommand {
    /**
     * Checks for equality between two objects.
     * 
     * @param a
     * @param b
     * @return
     */
    private static boolean equals(Object a, Object b) {
        return (a == b) || (a != null && a.equals(b));
    }

    /**
     * The request from the client that resulted in creating an instance of the
     * command
     */
    private CreateRegionHistoryReportRequest request = null;

    /**
     * Dao for accessing the swpc_region_history_reports tables.
     */
    private final RegionHistoryReportDao regionHistoryReportsDao = new RegionHistoryReportDao();

    /**
     * Default Constructor
     */
    public CreateRegionHistoryReportCommand() {
    }

    /*
     * (non-Javadoc)
     * 
     * @see gov.noaa.nws.ncep.edex.plugin.editedregions.commands.intf.ICommand#
     * getError()
     */
    @Override
    public EditedRegionsException getError() {
        return this.error;
    }

    /*
     * (non-Javadoc)
     * 
     * @see gov.noaa.nws.ncep.edex.plugin.editedregions.commands.intf.ICommand#
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
     * @see gov.noaa.nws.ncep.edex.plugin.editedregions.commands.intf.ICommand#
     * getStartTime()
     */
    @Override
    public long getStartTime() {
        return this.startTime;
    }

    /*
     * (non-Javadoc)
     * 
     * @see gov.noaa.nws.ncep.edex.plugin.editedregions.commands.intf.ICommand#
     * getEndTime()
     */
    @Override
    public long getEndTime() {
        return this.endTime;
    }

    /*
     * (non-Javadoc)
     * 
     * @see gov.noaa.nws.ncep.edex.plugin.editedregions.commands.intf.ICommand#
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
     * @see gov.noaa.nws.ncep.edex.plugin.editedregions.commands.intf.ICommand#
     * isValid()
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
     * @see gov.noaa.nws.ncep.edex.plugin.editedregions.commands.intf.ICommand#
     * setRequest(gov.noaa.nws.ncep.common.dataplugin.editedregions.request.intf
     * .IRequest)
     */
    @Override
    public void setRequest(IRequest request) {
        this.request = (CreateRegionHistoryReportRequest) request;

    }

    /*
     * (non-Javadoc)
     * 
     * @see gov.noaa.nws.ncep.edex.plugin.editedregions.commands.intf.ICommand#
     * getRequest()
     */
    @Override
    public IRequest getRequest() {
        return (IRequest) this.request;
    }

    /*
     * (non-Javadoc)
     * 
     * @see gov.noaa.nws.ncep.edex.plugin.editedregions.commands.intf.ICommand#
     * execute()
     * 
     * TODO - complete this
     */
    @Override
    public IResponse execute() {

        List<RegionHistoryReport> historyReports = new ArrayList<>();
        RegionHistoryReport historyReport = null;

        boolean success = false;

        this.setStartTime();

        Calendar calendar = Calendar.getInstance();
        calendar.setTimeZone(EditedRegionsConstants.TIME_ZONE_UTC);
        calendar.setTimeInMillis(this.getStartTime());

        DataTime dataTime = new DataTime(calendar);
        try {
            // Build out the RegionHistoryReport items.
            RegionReport oldReport = this.request.getOldReport();
            RegionReport newReport = this.request.getNewReport();

            if (!equals(oldReport.getObservationTime(),
                    newReport.getObservationTime())) {
                historyReport = new RegionHistoryReport();
                historyReport.setModifiedField("observationTime");
                historyReport.setFieldValueBefore(
                        String.valueOf(oldReport.getObservationTime()));
                historyReport.setFieldValueCurrent(
                        String.valueOf(newReport.getObservationTime()));
                historyReport.setTimeOfChange(this.getStartTime());
                historyReport.setTypeOfChange(REGION_REPORT_CHANGE_TYPE.UPDATE);
                historyReport.setDataTime(dataTime);
                historyReport
                        .setRegionReportId(this.request.getRegionReportId());

                historyReports.add(historyReport);
            }

            if (!equals(oldReport.getObservatory(),
                    newReport.getObservatory())) {
                historyReport = new RegionHistoryReport();
                historyReport.setModifiedField("observatory");
                historyReport.setFieldValueBefore(
                        String.valueOf(oldReport.getObservatory()));
                historyReport.setFieldValueCurrent(
                        String.valueOf(newReport.getObservatory()));
                historyReport.setTimeOfChange(this.getStartTime());
                historyReport.setTypeOfChange(REGION_REPORT_CHANGE_TYPE.UPDATE);
                historyReport.setDataTime(dataTime);
                historyReport
                        .setRegionReportId(this.request.getRegionReportId());

                historyReports.add(historyReport);
            }

            if (!equals(oldReport.getQuality(), newReport.getQuality())) {
                historyReport = new RegionHistoryReport();
                historyReport.setModifiedField("quality");
                historyReport.setFieldValueBefore(
                        String.valueOf(oldReport.getQuality()));
                historyReport.setFieldValueCurrent(
                        String.valueOf(newReport.getQuality()));
                historyReport.setTimeOfChange(this.getStartTime());
                historyReport.setTypeOfChange(REGION_REPORT_CHANGE_TYPE.UPDATE);
                historyReport.setDataTime(dataTime);
                historyReport
                        .setRegionReportId(this.request.getRegionReportId());

                historyReports.add(historyReport);
            }

            if (!equals(oldReport.getRegion(), newReport.getRegion())) {
                historyReport = new RegionHistoryReport();
                historyReport.setModifiedField("region");
                historyReport.setFieldValueBefore(
                        String.valueOf(oldReport.getRegion()));
                historyReport.setFieldValueCurrent(
                        String.valueOf(newReport.getRegion()));
                historyReport.setTimeOfChange(this.getStartTime());
                historyReport.setTypeOfChange(REGION_REPORT_CHANGE_TYPE.UPDATE);
                historyReport.setDataTime(dataTime);
                historyReport
                        .setRegionReportId(this.request.getRegionReportId());

                historyReports.add(historyReport);
            }

            if (!equals(oldReport.getReportLocation(),
                    newReport.getReportLocation())) {
                historyReport = new RegionHistoryReport();
                historyReport.setModifiedField("reportLocation");
                historyReport.setFieldValueBefore(
                        String.valueOf(oldReport.getReportLocation()));
                historyReport.setFieldValueCurrent(
                        String.valueOf(newReport.getReportLocation()));
                historyReport.setTimeOfChange(this.getStartTime());
                historyReport.setTypeOfChange(REGION_REPORT_CHANGE_TYPE.UPDATE);
                historyReport.setDataTime(dataTime);
                historyReport
                        .setRegionReportId(this.request.getRegionReportId());

                historyReports.add(historyReport);
            }

            if (!equals(oldReport.getLocation(), newReport.getLocation())) {
                historyReport = new RegionHistoryReport();
                historyReport.setModifiedField("location");
                historyReport.setFieldValueBefore(
                        String.valueOf(oldReport.getLocation()));
                historyReport.setFieldValueCurrent(
                        String.valueOf(newReport.getLocation()));
                historyReport.setTimeOfChange(this.getStartTime());
                historyReport.setTypeOfChange(REGION_REPORT_CHANGE_TYPE.UPDATE);
                historyReport.setDataTime(dataTime);
                historyReport
                        .setRegionReportId(this.request.getRegionReportId());

                historyReports.add(historyReport);
            }

            if (!equals(oldReport.getCarlon(), newReport.getCarlon())) {
                historyReport = new RegionHistoryReport();
                historyReport.setModifiedField("carlon");
                historyReport.setFieldValueBefore(
                        String.valueOf(oldReport.getCarlon()));
                historyReport.setFieldValueCurrent(
                        String.valueOf(newReport.getCarlon()));
                historyReport.setTimeOfChange(this.getStartTime());
                historyReport.setTypeOfChange(REGION_REPORT_CHANGE_TYPE.UPDATE);
                historyReport.setDataTime(dataTime);
                historyReport
                        .setRegionReportId(this.request.getRegionReportId());

                historyReports.add(historyReport);
            }

            if (!equals(oldReport.getExtent(), newReport.getExtent())) {
                historyReport = new RegionHistoryReport();
                historyReport.setModifiedField("extent");
                historyReport.setFieldValueBefore(
                        String.valueOf(oldReport.getExtent()));
                historyReport.setFieldValueCurrent(
                        String.valueOf(newReport.getExtent()));
                historyReport.setTimeOfChange(this.getStartTime());
                historyReport.setTypeOfChange(REGION_REPORT_CHANGE_TYPE.UPDATE);
                historyReport.setDataTime(dataTime);
                historyReport
                        .setRegionReportId(this.request.getRegionReportId());

                historyReports.add(historyReport);
            }

            if (!equals(oldReport.getArea(), newReport.getArea())) {
                historyReport = new RegionHistoryReport();
                historyReport.setModifiedField("area");
                historyReport.setFieldValueBefore(
                        String.valueOf(oldReport.getArea()));
                historyReport.setFieldValueCurrent(
                        String.valueOf(newReport.getArea()));
                historyReport.setTimeOfChange(this.getStartTime());
                historyReport.setTypeOfChange(REGION_REPORT_CHANGE_TYPE.UPDATE);
                historyReport.setDataTime(dataTime);
                historyReport
                        .setRegionReportId(this.request.getRegionReportId());

                historyReports.add(historyReport);
            }

            if (!equals(oldReport.getNumspot(), newReport.getNumspot())) {
                historyReport = new RegionHistoryReport();
                historyReport.setModifiedField("numspot");
                historyReport.setFieldValueBefore(
                        String.valueOf(oldReport.getNumspot()));
                historyReport.setFieldValueCurrent(
                        String.valueOf(newReport.getNumspot()));
                historyReport.setTimeOfChange(this.getStartTime());
                historyReport.setTypeOfChange(REGION_REPORT_CHANGE_TYPE.UPDATE);
                historyReport.setDataTime(dataTime);
                historyReport
                        .setRegionReportId(this.request.getRegionReportId());

                historyReports.add(historyReport);
            }

            if (!equals(oldReport.getSpotclass(), newReport.getSpotclass())) {
                historyReport = new RegionHistoryReport();
                historyReport.setModifiedField("spotclass");
                historyReport.setFieldValueBefore(
                        String.valueOf(oldReport.getSpotclass()));
                historyReport.setFieldValueCurrent(
                        String.valueOf(newReport.getSpotclass()));
                historyReport.setTimeOfChange(this.getStartTime());
                historyReport.setTypeOfChange(REGION_REPORT_CHANGE_TYPE.UPDATE);
                historyReport.setDataTime(dataTime);
                historyReport
                        .setRegionReportId(this.request.getRegionReportId());

                historyReports.add(historyReport);
            }

            if (!equals(oldReport.getMagclass(), newReport.getMagclass())) {
                historyReport = new RegionHistoryReport();
                historyReport.setModifiedField("magclass");
                historyReport.setFieldValueBefore(
                        String.valueOf(oldReport.getMagclass()));
                historyReport.setFieldValueCurrent(
                        String.valueOf(newReport.getMagclass()));
                historyReport.setTimeOfChange(this.getStartTime());
                historyReport.setTypeOfChange(REGION_REPORT_CHANGE_TYPE.UPDATE);
                historyReport.setDataTime(dataTime);
                historyReport
                        .setRegionReportId(this.request.getRegionReportId());

                historyReports.add(historyReport);
            }

            // RegionReport updatedReport = null;

            // this.regionReportsDao = new RegionReportsDao();
            //
            // RegionReport report = this.request.getRegionReport();
            // report.setId(this.request.getRegionReportID());
            //
            // this.regionReportsDao.update(report);
            // updatedReport = (RegionReport) this.regionReportsDao
            // .queryById(report.getId());

            for (RegionHistoryReport hr : historyReports) {
                regionHistoryReportsDao.persist(hr);
            }

            success = true;

            // first obtain the region history report(s) from the
            // the request which will contain the change that has
            // been performed
            // historyReport =
            // dao.getHistoryReports(request.getHistoryReportID());

            //

        } catch (DataAccessLayerException ex) {
            setError(new EditedRegionsException(ex));
        }

        this.setEndTime();

        return this.createResponse(success);
    }

    /**
     * Create the response that will be returned from executing the request
     * 
     * @param results
     * @return IResponse
     */

    private IResponse createResponse(boolean success) {
        CreateRegionHistoryReportResponse response = new CreateRegionHistoryReportResponse();
        if (this.hasError()) {
            response.setError(this.getError());
        } else {
            CreateRegionHistoryReportResults results = new CreateRegionHistoryReportResults(
                    success);
            response.setResults(results);
        }
        response.setRequest(this.getRequest());
        response.setProcessingTime(this.getProcessingTime());
        return response;
    }

}
