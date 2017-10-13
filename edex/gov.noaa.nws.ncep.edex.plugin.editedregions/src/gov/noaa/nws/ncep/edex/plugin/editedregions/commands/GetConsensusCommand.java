package gov.noaa.nws.ncep.edex.plugin.editedregions.commands;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import com.raytheon.uf.common.status.IUFStatusHandler;
import com.raytheon.uf.common.status.UFStatus;

import gov.noaa.nws.ncep.common.dataplugin.editedregions.RegionConsensus;
import gov.noaa.nws.ncep.common.dataplugin.editedregions.RegionReport;
import gov.noaa.nws.ncep.common.dataplugin.editedregions.exception.EditedRegionsException;
import gov.noaa.nws.ncep.common.dataplugin.editedregions.request.GetConsensusRequest;
import gov.noaa.nws.ncep.common.dataplugin.editedregions.request.intf.IRequest;
import gov.noaa.nws.ncep.common.dataplugin.editedregions.response.GetConsensusResponse;
import gov.noaa.nws.ncep.common.dataplugin.editedregions.response.intf.IResponse;
import gov.noaa.nws.ncep.common.dataplugin.editedregions.results.GetConsensusFinalResults;
import gov.noaa.nws.ncep.common.dataplugin.editedregions.results.GetConsensusTodaysResults;
import gov.noaa.nws.ncep.common.dataplugin.editedregions.results.GetConsensusYesterdaysResults;
import gov.noaa.nws.ncep.common.dataplugin.editedregions.util.EditedRegionsConstants;
import gov.noaa.nws.ncep.edex.plugin.editedregions.dao.RegionConsensusDao;
import gov.noaa.nws.ncep.edex.plugin.editedregions.dao.RegionReportsDao;
import gov.noaa.nws.ncep.edex.plugin.editedregions.util.RefCodes;

/**
 * The command class that is executed to obtain the latest region that was
 * created.
 * 
 * 
 * @author jtravis
 * @version 1.0
 */
public class GetConsensusCommand extends BaseCommand {

    private static final long DAY_IN_MILLIS = TimeUnit.DAYS.toMillis(1);

    /**
     * The request from the client that resulted in creating an instance of the
     * command
     */
    private GetConsensusRequest request = null;

    /**
     * Dao for Region Reports
     */
    private RegionReportsDao regionReportsDao = null;

    /**
     * Dao for access to the SWPC_REGION_CONSENSUS table
     */
    private RegionConsensusDao regionConsensusDao = null;

    /**
     * Logger
     */
    private static final IUFStatusHandler statusHandler = UFStatus
            .getHandler(GetConsensusCommand.class);

    /**
     * Default Constructor
     */
    public GetConsensusCommand() {
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
        this.request = (GetConsensusRequest) request;

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

        statusHandler
                .info("Starting Executing " + this.getClass().getSimpleName());

        this.setStartTime();

        GetConsensusResponse response = new GetConsensusResponse();

        try {

            regionReportsDao = new RegionReportsDao();
            regionConsensusDao = new RegionConsensusDao();

            Calendar date = Calendar
                    .getInstance(EditedRegionsConstants.TIME_ZONE_UTC);

            date.clear();
            date.setTimeInMillis(request.getDttm());

            Calendar cal = Calendar
                    .getInstance(EditedRegionsConstants.TIME_ZONE_UTC);
            cal.clear();
            cal.set(date.get(Calendar.YEAR), date.get(Calendar.MONTH),
                    date.get(Calendar.DAY_OF_MONTH));

            Date start = cal.getTime();
            Date end = new Date(start.getTime() + DAY_IN_MILLIS);
            Calendar todaysCalendar = (Calendar) cal.clone();

            // obtain all region reports for the given region
            // and given date
            List<RegionReport> rsCurrentDay = regionReportsDao
                    .getRegionReports(start, end, request.getRegion());

            // obtain all region reports for the given region for the previous
            // day
            // TODO this goes away when logic to pull the previous days final
            // report is completed
            cal.add(Calendar.DAY_OF_MONTH, -1);
            start = cal.getTime();
            end = new Date(start.getTime() + DAY_IN_MILLIS);

            List<RegionReport> rsPreviousDay = regionReportsDao
                    .getRegionReports(start, end, request.getRegion());

            // compute the Todays Consensus values
            if (rsCurrentDay.isEmpty() || rsPreviousDay.isEmpty()) {
                response.setErrorMessage(
                        "No Records Found for Current and / or Previous Day.  Some consensus values may not have been computed.");
            }

            response.setTodaysConsensusResults(
                    this.computeTodaysConsensus(todaysCalendar, rsCurrentDay));

            // Obtain Yesterdays Report and add it to the
            // GetConsensusResponse
            RegionConsensus yesterdaysReport = regionConsensusDao
                    .getYesterdaysReport(request.getRegion());

            response.setYesterdaysConsensusResults(
                    this.createYesterdaysResults(yesterdaysReport));

            // Obtain Todays Final and add it to the GetConsensusResponse.
            RegionConsensus todaysFinal = regionConsensusDao
                    .getTodaysFinal(request.getRegion());
            response.setFinalConsensusResults(
                    this.createTodaysFinal(todaysFinal));

        } catch (Exception e) {
            this.setError(new EditedRegionsException(e));
        }

        this.setEndTime();

        statusHandler
                .info("Finishing Executing " + this.getClass().getSimpleName());

        // add the results instance to the response;
        response.setRequest(getRequest());
        // response.setResults(results);
        // response.setError(this.getError());
        response.setProcessingTime(this.getProcessingTime());

        return response;
    }

    /**
     * Convenience method to populate the values on the
     * GetConsensusYesterdaysResults class with the values from the
     * RegionConsensus instance that represents yesterdays report
     * 
     * @param yesterday
     * 
     * @return GetConsensusYesterdaysResults
     */
    private GetConsensusYesterdaysResults createYesterdaysResults(
            RegionConsensus yesterday) {

        GetConsensusYesterdaysResults yesterdaysResults = new GetConsensusYesterdaysResults();

        if (yesterday == null) {
            return yesterdaysResults;
        } else { // populate yesterdays results

            yesterdaysResults.setArea(yesterday.getArea());
            yesterdaysResults
                    .setObservationTime(yesterday.getObservationTime());
            yesterdaysResults.setObservatory(yesterday.getObservatory());
            yesterdaysResults.setRegion(yesterday.getRegion());
            yesterdaysResults.setLatitude(yesterday.getLatitude());
            yesterdaysResults.setLongitude(yesterday.getLongitude());
            yesterdaysResults.setCarlon(yesterday.getCarlon());
            yesterdaysResults.setExtent(yesterday.getExtent());
            yesterdaysResults.setNumspots(yesterday.getNumspots());
            yesterdaysResults.setMagclass(yesterday.getMagclass());
            yesterdaysResults.setReportLocation(yesterday.getReportLocation());
            yesterdaysResults
                    .setReport00ZLocation(yesterday.getReport00ZLocation());
            yesterdaysResults.setSpotClass(yesterday.getSpotClass());

        }

        return yesterdaysResults;

    }

    /**
     * Convenience method to populate the values on the
     * GetConsensusYesterdaysResults class with the values from the
     * RegionConsensus instance that represents yesterdays report
     * 
     * @param todaysFinal
     * 
     * @return GetConsensusYesterdaysResults
     */
    private GetConsensusFinalResults createTodaysFinal(
            RegionConsensus todaysFinal) {

        GetConsensusFinalResults finalResults = new GetConsensusFinalResults();

        if (todaysFinal == null) {
            finalResults.setPopulated(false);
            return finalResults;
        } else { // populate yesterdays results

            finalResults.setArea(todaysFinal.getArea());
            finalResults.setObservationTime(todaysFinal.getObservationTime());
            finalResults.setObservatory(todaysFinal.getObservatory());
            finalResults.setRegion(todaysFinal.getRegion());
            finalResults.setLatitude(todaysFinal.getLatitude());
            finalResults.setLongitude(todaysFinal.getLongitude());
            finalResults.setCarlon(todaysFinal.getCarlon());
            finalResults.setExtent(todaysFinal.getExtent());
            finalResults.setNumspots(todaysFinal.getNumspots());
            finalResults.setMagclass(todaysFinal.getMagclass());
            finalResults.setReportLocation(todaysFinal.getReportLocation());
            finalResults
                    .setReport00ZLocation(todaysFinal.getReport00ZLocation());
            finalResults.setSpotClass(todaysFinal.getSpotClass());

            finalResults.setPopulated(true);

        }

        return finalResults;

    }

    // TODO complete this stub
    private GetConsensusTodaysResults computeTodaysConsensus(Calendar date,
            List<RegionReport> reports) {
        GetConsensusTodaysResults results = new GetConsensusTodaysResults();

        int count = reports.size();
        if (count == 0) {
            return results;
        }

        int regionId = 0;

        int latitudeSum = 0;
        int longitudeSum = 0;
        int carlonSum = 0;
        int extentSum = 0;
        int areaSum = 0;

        int zurich = 0;
        int penumbra = 0;
        int compact = 0;
        int magcode = 0;
        int numSpots = 0;

        for (RegionReport report : reports) {
            latitudeSum += report.getLatitude();
            longitudeSum += report.getLongitude();
            carlonSum += report.getCarlon();
            extentSum += report.getExtent();
            areaSum += report.getArea();

            // compute the max values
            zurich = Math.max(zurich, report.getZurich());
            penumbra = Math.max(penumbra, report.getPenumbra());
            compact = Math.max(compact, report.getCompact());
            magcode = Math.max(magcode, report.getMagcode());
            numSpots = Math.max(numSpots, report.getNumspot());

            regionId = report.getRegion();
        }

        // compute and set the mean values to the results object
        results.setReportLocation(
                getLocation(latitudeSum / count, longitudeSum / count));
        results.setReport00ZLocation(
                getLocation(latitudeSum / count, carlonSum / count));
        results.setCarlon(carlonSum / count);
        results.setExtent(extentSum / count);
        results.setArea(areaSum / count);

        // set the max values to the results object
        results.setMagclass(RefCodes.getMagneticCode(magcode));
        results.setNumspots(numSpots);

        // Build out the spot class

        String spotclass = RefCodes.getZurichCode(zurich)
                + RefCodes.getPenumbraCode(penumbra)
                + RefCodes.getCompactCode(compact);
        results.setSpotClass(spotclass);

        // Compute other values
        results.setRegion(regionId);
        results.setObservatory("SWO");
        results.setObservationTime(date.getTimeInMillis());

        return results;

    }

    private static String getLocation(int latitude, int longitude) {
        StringBuilder sb = new StringBuilder();

        sb.append((latitude < 0) ? "N" : "S");
        sb.append(String.format("%02d", Math.abs(latitude)));

        sb.append((longitude < 0) ? "W" : "E");
        sb.append(String.format("%02d", Math.abs(longitude)));

        return sb.toString();
    }

}
