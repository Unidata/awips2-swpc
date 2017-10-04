package gov.noaa.nws.ncep.edex.plugin.editedregions.commands;

import java.util.Calendar;
import java.util.List;

import com.raytheon.uf.common.status.IUFStatusHandler;
import com.raytheon.uf.common.status.UFStatus;

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

            // What region are we interested in
            int region = request.getRegion();
            Calendar date = Calendar
                    .getInstance(EditedRegionsConstants.TIME_ZONE_UTC);

            date.clear();
            date.setTimeInMillis(request.getDttm());

            // obtain all region reports for the given region
            // and given date
            List<RegionReport> rsCurrentDay = regionReportsDao
                    .getRegionReports(date, region);

            // obtain all region reports for the given region for the previous
            // day
            date.set(Calendar.DAY_OF_YEAR,
                    (date.get(Calendar.DAY_OF_YEAR) - 1));
            List<RegionReport> rsPreviousDay = regionReportsDao
                    .getRegionReports(date, region);

            // compute the three consensus values
            // 1) Todays Consensus
            // 2) Yesterdays Consensus
            // 3) Final Consensus

            if (rsCurrentDay.isEmpty() || rsPreviousDay.isEmpty()) {
                response.setErrorMessage(
                        "No Records Found for Current and / or Previous Day.  Some consensus values may not have been computed.");
            }

            response.setTodaysConsensusResults(
                    this.computeTodaysConsensus(rsCurrentDay));
                    // response.setFinalResults(
                    // this.computeFinalConsensus(rsCurrentDay));

            // response.setYesterdaysConsensusResults(
            // this.computeYesterdaysConsensus(rsPreviousDay));

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

    // TODO complete this stub
    private GetConsensusTodaysResults computeTodaysConsensus(
            List<RegionReport> reports) {
        // TODO: How do we want to handle having no reports available?
        GetConsensusTodaysResults results = new GetConsensusTodaysResults();

        int count = reports.size();
        if (count == 0) {
            return results;
        }

        int latitudeSum = 0;
        int longitudeSum = 0;
        int carlonSum = 0;

        int zurich = 0;
        int penumbra = 0;
        int compact = 0;
        int magcode = 0;
        int numSpots = 0;

        for (RegionReport report : reports) {
            latitudeSum += report.getLatitude();
            longitudeSum += report.getLongitude();
            carlonSum += report.getCarlon();

            // compute the max values
            zurich = Math.max(zurich, report.getZurich());
            penumbra = Math.max(penumbra, report.getPenumbra());
            compact = Math.max(compact, report.getCompact());
            magcode = Math.max(magcode, report.getMagcode());
            numSpots = Math.max(numSpots, report.getNumspot());
        }

        // compute and set the mean values to the results object
        // results.setLatitude(latitudeSum / count);
        // results.setLongitude(longitudeSum / count);
        // results.setCarlon(carlonSum / count);

        // set the max values to the results object
        // results.setMagcode(magcode);
        // results.setNumspots(numSpots);

        // Build out the spot class

        String spotclass = RefCodes.getZurichCode(zurich)
                + RefCodes.getPenumbraCode(penumbra)
                + RefCodes.getCompactCode(compact);
        // results.setSpotClass(spotclass);

        return results;

    }

    // TODO complete this stub
    private GetConsensusYesterdaysResults computeYesterdaysConsensus(
            List<RegionReport> reports) {
        GetConsensusYesterdaysResults results = new GetConsensusYesterdaysResults();

        return results;
    }

    // TODO complete this stub
    // TODO Do we even need this method?
    private GetConsensusFinalResults computeFinalConsensus(
            List<RegionReport> reports) {
        GetConsensusFinalResults results = new GetConsensusFinalResults();

        return results;
    }

}
