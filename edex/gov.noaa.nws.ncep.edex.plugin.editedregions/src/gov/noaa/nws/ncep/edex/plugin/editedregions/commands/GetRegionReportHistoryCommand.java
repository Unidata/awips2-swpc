package gov.noaa.nws.ncep.edex.plugin.editedregions.commands;

import java.util.Hashtable;
import java.util.Vector;

import com.raytheon.uf.common.status.IUFStatusHandler;
import com.raytheon.uf.common.status.UFStatus;

import gov.noaa.nws.ncep.common.dataplugin.editedregions.exception.EditedRegionsException;
import gov.noaa.nws.ncep.common.dataplugin.editedregions.request.GetReferenceDataRequest;
import gov.noaa.nws.ncep.common.dataplugin.editedregions.request.intf.IRequest;
import gov.noaa.nws.ncep.common.dataplugin.editedregions.response.GetReferenceDataResponse;
import gov.noaa.nws.ncep.common.dataplugin.editedregions.response.intf.IResponse;
import gov.noaa.nws.ncep.common.dataplugin.editedregions.results.GetReferenceDataResults;
import gov.noaa.nws.ncep.common.swpcrefdb.ObservationQuality;
import gov.noaa.nws.ncep.common.swpcrefdb.ObservationType;
import gov.noaa.nws.ncep.common.swpcrefdb.PenumbralClass;
import gov.noaa.nws.ncep.common.swpcrefdb.ReportStatus;
import gov.noaa.nws.ncep.edex.swpcrefdb.dao.ObservationQualityDao;
import gov.noaa.nws.ncep.edex.swpcrefdb.dao.ObservationTypeDao;
import gov.noaa.nws.ncep.edex.swpcrefdb.dao.PenumbralClassDao;
import gov.noaa.nws.ncep.edex.swpcrefdb.dao.ReportStatusDao;

/**
 * The command class that is executed to obtain all history for a given Region
 * Report
 * 
 * TODO complete this
 * 
 * 
 * @author jtravis
 * @version 1.0
 */
public class GetRegionReportHistoryCommand extends BaseCommand {

    /**
     * The request from the client that resulted in creating an instance of the
     * command
     */
    private GetReferenceDataRequest request = null;

    /**
     * Dao for ObservationQuality
     */
    private ObservationQualityDao observationQualityDao = null;

    /**
     * Dao for ObservationType
     */
    private ObservationTypeDao observationTypeDao = null;

    /**
     * Dao for PenumbralClass
     */
    private PenumbralClassDao penumbralClassDao = null;

    /**
     * Dao for ReportStatus
     */
    private ReportStatusDao reportStatusDao = null;

    /**
     * Logger
     */
    private static final IUFStatusHandler statusHandler = UFStatus
            .getHandler(GetRegionReportHistoryCommand.class);

    /**
     * Default Constructor
     */
    public GetRegionReportHistoryCommand() {
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
        this.request = (GetReferenceDataRequest) request;

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

        GetReferenceDataResponse response = new GetReferenceDataResponse();
        GetReferenceDataResults results = new GetReferenceDataResults();

        Hashtable<String, Integer> observationQualityResults = new Hashtable<String, Integer>();
        Hashtable<String, Integer> observationTypeResults = new Hashtable<String, Integer>();
        Hashtable<String, Integer> penumbralClassResults = new Hashtable<String, Integer>();
        Hashtable<String, Integer> reportStatusResults = new Hashtable<String, Integer>();

        observationQualityDao = new ObservationQualityDao();
        observationTypeDao = new ObservationTypeDao();
        penumbralClassDao = new PenumbralClassDao();
        reportStatusDao = new ReportStatusDao();

        // obtain all of the ref-data values
        Vector<ObservationQuality> observationQualities = observationQualityDao
                .getAllObservationQualities();
        Vector<ObservationType> observationTypes = observationTypeDao
                .getAllObservationTypes();
        Vector<PenumbralClass> penumbralClasses = penumbralClassDao
                .getAllPenumbralClasses();
        Vector<ReportStatus> reportStatuses = reportStatusDao
                .getAllReportStatuses();

        // itterate over the ObservationQualities and add to the
        // hashtable of ObservationQualities
        for (int i = 0; i <= observationQualities.size() - 1; i++) {
            ObservationQuality oq = observationQualities.get(i);
            int id = Integer.valueOf(String.valueOf(oq.getId())).intValue();
            String description = oq.getDescription();
            observationQualityResults.put(description, id);
        }

        // itterate over the ObservationTypes and add to the
        // hashtable of ObservationTypes
        for (int i = 0; i <= observationTypes.size() - 1; i++) {
            ObservationType ot = observationTypes.get(i);
            int id = Integer.valueOf(String.valueOf(ot.getId())).intValue();
            String description = ot.getDescription();
            observationTypeResults.put(description, id);
        }

        // itterate over the PenumbralClasses and add to the
        // hashtable of PenumbralClasses
        for (int i = 0; i <= penumbralClasses.size() - 1; i++) {
            PenumbralClass pc = penumbralClasses.get(i);
            int id = Integer.valueOf(String.valueOf(pc.getId())).intValue();
            String description = pc.getDescription();
            penumbralClassResults.put(description, id);
        }

        // itterate over the ReportStatuses and add to the
        // hashtable of ReportStatuses
        for (int i = 0; i <= reportStatuses.size() - 1; i++) {
            ReportStatus rs = reportStatuses.get(i);
            int id = Integer.valueOf(String.valueOf(rs.getId())).intValue();
            String description = rs.getDescription();
            reportStatusResults.put(description, id);
        }

        // add the hashtables holding the refdata to the
        // results object
        results.setObservationQualityResults(observationQualityResults);
        results.setObservationTypeResults(observationTypeResults);
        results.setPenumbralClassResults(penumbralClassResults);
        results.setReportStatusResults(reportStatusResults);

        // add the results instance to the response;
        response.setResults(results);

        this.setEndTime();

        statusHandler
                .info("Finishing Executing " + this.getClass().getSimpleName());

        return response;
    }

}
