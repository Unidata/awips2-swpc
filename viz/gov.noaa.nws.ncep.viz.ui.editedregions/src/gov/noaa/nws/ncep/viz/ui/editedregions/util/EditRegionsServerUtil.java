package gov.noaa.nws.ncep.viz.ui.editedregions.util;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.NavigableSet;
import java.util.TreeSet;

import com.raytheon.uf.common.status.IUFStatusHandler;
import com.raytheon.uf.common.status.UFStatus;

import gov.noaa.nws.ncep.common.dataplugin.editedregions.RegionHistoryReport;
import gov.noaa.nws.ncep.common.dataplugin.editedregions.RegionReport;
import gov.noaa.nws.ncep.common.dataplugin.editedregions.exception.EditedRegionsException;
import gov.noaa.nws.ncep.common.dataplugin.editedregions.gateway.Gateway;
import gov.noaa.nws.ncep.common.dataplugin.editedregions.request.CreateRegionReportRequest;
import gov.noaa.nws.ncep.common.dataplugin.editedregions.request.CreateRegionRequest;
import gov.noaa.nws.ncep.common.dataplugin.editedregions.request.GetConsensusRequest;
import gov.noaa.nws.ncep.common.dataplugin.editedregions.request.GetLatestRegionRequest;
import gov.noaa.nws.ncep.common.dataplugin.editedregions.request.GetReferenceDataRequest;
import gov.noaa.nws.ncep.common.dataplugin.editedregions.request.GetRegionReportsRequest;
import gov.noaa.nws.ncep.common.dataplugin.editedregions.request.GetRegionsRequest;
import gov.noaa.nws.ncep.common.dataplugin.editedregions.request.ProcessIngestedReportsRequest;
import gov.noaa.nws.ncep.common.dataplugin.editedregions.request.UpdateRegionReportRequest;
import gov.noaa.nws.ncep.common.dataplugin.editedregions.request.ViewRegionReportHistoryRequest;
import gov.noaa.nws.ncep.common.dataplugin.editedregions.response.CreateRegionReportResponse;
import gov.noaa.nws.ncep.common.dataplugin.editedregions.response.CreateRegionResponse;
import gov.noaa.nws.ncep.common.dataplugin.editedregions.response.GetConsensusResponse;
import gov.noaa.nws.ncep.common.dataplugin.editedregions.response.GetLatestRegionResponse;
import gov.noaa.nws.ncep.common.dataplugin.editedregions.response.GetReferenceDataResponse;
import gov.noaa.nws.ncep.common.dataplugin.editedregions.response.GetRegionReportsResponse;
import gov.noaa.nws.ncep.common.dataplugin.editedregions.response.GetRegionsResponse;
import gov.noaa.nws.ncep.common.dataplugin.editedregions.response.ProcessIngestedReportsResponse;
import gov.noaa.nws.ncep.common.dataplugin.editedregions.response.UpdateRegionReportResponse;
import gov.noaa.nws.ncep.common.dataplugin.editedregions.response.ViewRegionReportHistoryResponse;
import gov.noaa.nws.ncep.common.dataplugin.editedregions.results.CreateRegionReportResults;
import gov.noaa.nws.ncep.common.dataplugin.editedregions.results.CreateRegionResults;
import gov.noaa.nws.ncep.common.dataplugin.editedregions.results.GetLatestRegionResults;
import gov.noaa.nws.ncep.common.dataplugin.editedregions.results.GetReferenceDataResults;
import gov.noaa.nws.ncep.common.dataplugin.editedregions.results.GetRegionReportsResults;
import gov.noaa.nws.ncep.common.dataplugin.editedregions.results.GetRegionsResults;
import gov.noaa.nws.ncep.common.dataplugin.editedregions.results.ProcessIngestedReportsResults;
import gov.noaa.nws.ncep.common.dataplugin.editedregions.results.UpdateRegionReportResults;
import gov.noaa.nws.ncep.common.dataplugin.editedregions.results.ViewRegionReportHistoryResults;
import gov.noaa.nws.ncep.common.dataplugin.editedregions.util.RegionHistoryReportUtility;

/**
 * TODO Add Description
 * 
 * <pre>
 *
 * SOFTWARE HISTORY
 *
 * Date         Ticket#    Engineer    Description
 * ------------ ---------- ----------- --------------------------
 * Jul 6, 2017            jtravis     Initial creation
 *RegionHistoryReport
 * </pre>
 *
 * @author jtravis
 */
public final class EditRegionsServerUtil {
    private EditRegionsServerUtil() {
        throw new Error(String.format("Cannot create instance of %s!",
                EditRegionsServerUtil.class));
    }

    private static final IUFStatusHandler statusHandler = UFStatus
            .getHandler(EditRegionsServerUtil.class);

    private static Map<String, Integer> observationQualityRefData = Collections
            .emptyMap();

    private static Map<String, Integer> observationTypeRefData = Collections
            .emptyMap();

    private static Map<String, Integer> penumbralClassRefData = Collections
            .emptyMap();

    private static Map<String, Integer> reportStatusRefData = Collections
            .emptyMap();

    static {
        updateHistoryForIngestedReports();
        loadReferenceData();
    }

    /**
     * 
     */
    private static void updateHistoryForIngestedReports() {
        try {
            ProcessIngestedReportsRequest request = new ProcessIngestedReportsRequest();
            if (request.isValid()) {

                ProcessIngestedReportsResponse response = Gateway.getInstance()
                        .submit(request);
                if (response.hasErrors()) {
                    throw response.getError();
                } else if (response.getResults() != null) {
                    ProcessIngestedReportsResults results = (ProcessIngestedReportsResults) response
                            .getResults();
                    if (!results.isSuccessful()) {
                        statusHandler.error("History could not be updated.");
                    }
                }
            }
        } catch (EditedRegionsException e) {
            statusHandler.error("Error updating report history.", e);
        }
    }

    /**
     * Obtain the region reports
     * 
     * @param bAssignedReports
     * @param bUnassignedReports
     * @return
     * @throws EditedRegionsException
     */
    public static GetRegionReportsResults getRegionReports(
            boolean bAssignedReports, boolean bUnassignedReports)
                    throws EditedRegionsException {

        GetRegionReportsRequest request = new GetRegionReportsRequest();

        request.setObtainAssignedReports(bAssignedReports);
        request.setObtainUnassignedReports(bUnassignedReports);

        if (request.isValid()) {
            GetRegionReportsResponse response = Gateway.getInstance()
                    .submit(request);
            if (response.getResults() != null && !response.hasErrors()) {
                GetRegionReportsResults result = (GetRegionReportsResults) response
                        .getResults();
                return result;
            }
        }

        return null;
    }

    /**
     * 
     * 
     * @param report
     * @return
     * @throws EditedRegionsException
     */
    public static Integer saveNewRegionReport(RegionReport report)
            throws EditedRegionsException {
        CreateRegionReportRequest request = new CreateRegionReportRequest();
        request.setRegionReport(report);

        if (request.isValid()) {
            CreateRegionReportResponse response = Gateway.getInstance()
                    .submit(request);
            if (response.getResults() != null && !response.hasErrors()) {
                CreateRegionReportResults result = (CreateRegionReportResults) response
                        .getResults();
                return Integer.valueOf(result.getReportID());
            }
        }
        return null;
    }

    /**
     * @param report
     * @return
     * @throws EditedRegionsException
     */
    public static RegionReport updateRegionReport(RegionReport report)
            throws EditedRegionsException {
        UpdateRegionReportRequest request = new UpdateRegionReportRequest();
        request.setRegionReport(report);
        request.setRegionReportID(report.getId());

        if (request.isValid()) {
            UpdateRegionReportResponse response = Gateway.getInstance()
                    .submit(request);
            if (response.hasErrors()) {
                throw response.getError();
            } else if (response.getResults() != null) {
                UpdateRegionReportResults results = (UpdateRegionReportResults) response
                        .getResults();
                return results.getReport();
            }
        }
        return null;
    }

    /**
     * 
     * @return The latest region id to be created.
     * @throws EditedRegionsException
     */
    public static Integer getLatestRegion() throws EditedRegionsException {
        GetLatestRegionRequest request = new GetLatestRegionRequest();
        if (request.isValid()) {
            GetLatestRegionResponse response = Gateway.getInstance()
                    .submit(request);
            if (response.hasErrors()) {
                throw response.getError();
            } else if (response.getResults() != null) {
                GetLatestRegionResults results = (GetLatestRegionResults) response
                        .getResults();
                return results.getLatestRegion();
            }
        }
        return null;
    }

    /**
     * 
     * @return A list of all valid region ids.
     * @throws EditedRegionsException
     */
    public static List<Integer> getAllRegions() throws EditedRegionsException {
        GetRegionsRequest request = new GetRegionsRequest();
        if (request.isValid()) {
            GetRegionsResponse response = Gateway.getInstance().submit(request);
            if (response.hasErrors()) {
                throw response.getError();
            } else if (response.getResults() != null) {
                GetRegionsResults results = (GetRegionsResults) response
                        .getResults();
                return results.getRegions();
            }
        }
        return Collections.emptyList();
    }

    /**
     * Persists the region object to the backend.
     * 
     * @param region
     *            The region object
     * @return The region object identifier.
     * @throws EditedRegionsException
     */
    public static Long createRegion(Integer regionID)
            throws EditedRegionsException {
        CreateRegionRequest request = new CreateRegionRequest();
        request.setRegionID(regionID);
        if (request.isValid()) {
            CreateRegionResponse response = Gateway.getInstance()
                    .submit(request);
            if (response.hasErrors()) {
                throw response.getError();
            } else if (response.getResults() != null) {
                CreateRegionResults results = (CreateRegionResults) response
                        .getResults();
                return results.getCreatedRegion();
            }
        }
        return null;
    }

    /**
     * @param reportId
     * @return
     * @throws EditedRegionsException
     */
    public static List<RegionHistoryReport> getReportHistory(Integer reportId)
            throws EditedRegionsException {
        ViewRegionReportHistoryRequest request = new ViewRegionReportHistoryRequest();
        request.setReportId(reportId);
        if (request.isValid()) {
            ViewRegionReportHistoryResponse response = Gateway.getInstance()
                    .submit(request);
            if (response.hasErrors()) {
                throw response.getError();
            } else if (response.getResults() != null) {
                ViewRegionReportHistoryResults results = (ViewRegionReportHistoryResults) response
                        .getResults();
                List<RegionHistoryReport> reports = new ArrayList<>();
                for (Map<String, String> reportMap : results
                        .getHistoryReportList()) {
                    RegionHistoryReport historyReport = RegionHistoryReportUtility
                            .convertToObject(reportMap);
                    reports.add(historyReport);
                }

                Collections.sort(reports,
                        new Comparator<RegionHistoryReport>() {
                            @Override
                            public int compare(RegionHistoryReport lhs,
                                    RegionHistoryReport rhs) {
                                return Long.compare(lhs.getTimeOfChange(),
                                        rhs.getTimeOfChange());
                            }
                        });
                return reports;
            }
        }
        return Collections.emptyList();

    }

    /**
     * @param date
     * @param region
     * @return
     * @throws EditedRegionsException
     */
    public static GetConsensusResponse getConsensus(Calendar date,
            Integer region) throws EditedRegionsException {
        GetConsensusRequest request = new GetConsensusRequest();
        request.setDttm(date);
        request.setRegion(region);

        if (request.isValid()) {
            GetConsensusResponse response = Gateway.getInstance()
                    .submit(request);
            if (response.hasErrors()) {
                throw response.getError();
            } else if (response.hasResults()) {
                return response;
            }
        }
        return null;
    }

    /**
     * @return
     * @throws EditedRegionsException
     */
    private static GetReferenceDataResults getReferenceData()
            throws EditedRegionsException {
        GetReferenceDataRequest request = new GetReferenceDataRequest();
        if (request.isValid()) {
            GetReferenceDataResponse response = Gateway.getInstance()
                    .submit(request);
            if (response.hasErrors()) {
                throw response.getError();
            } else if (response.getResults() != null) {
                GetReferenceDataResults results = (GetReferenceDataResults) response
                        .getResults();
                return results;
            }
        }
        return null;
    }

    /**
     * 
     */
    private static void loadReferenceData() {
        try {
            GetReferenceDataResults results = getReferenceData();
            observationQualityRefData = results.getObservationQualityResults();
            observationTypeRefData = results.getObservationTypeResults();
            penumbralClassRefData = results.getPenumbralClassResults();
            reportStatusRefData = results.getReportStatusResults();
        } catch (EditedRegionsException ex) {
            statusHandler.error("Error loading reference data", ex);
        }
    }

    /**
     * @param map
     * @param value
     * @return
     */
    private static <K, V> K getKeyForValue(Map<? extends K, ? extends V> map,
            V value) {
        for (Map.Entry<? extends K, ? extends V> entry : map.entrySet()) {
            if (value.equals(entry.getValue())) {
                return entry.getKey();
            }
        }
        return null;
    }

    public static NavigableSet<String> getObservationQualityRefData() {
        return new TreeSet<>(observationQualityRefData.keySet());
    }

    public static String getObservationQuality(Integer id) {
        return getKeyForValue(observationQualityRefData, id);
    }

    public static Integer getObservationQualityId(String text) {
        return observationQualityRefData.get(text);
    }

    public static NavigableSet<String> getObservationTypeRefData() {
        return new TreeSet<>(observationTypeRefData.keySet());
    }

    public static String getObservationType(Integer id) {
        return getKeyForValue(observationTypeRefData, id);
    }

    public static Integer getObservationTypeId(String text) {
        return observationTypeRefData.get(text);
    }

    public static NavigableSet<String> getPenumbralClassRefData() {
        return new TreeSet<>(penumbralClassRefData.keySet());
    }

    public static String getPenumbralClass(int id) {
        return getKeyForValue(penumbralClassRefData, id);
    }

    public static Integer getPenumbralClassId(String text) {
        return penumbralClassRefData.get(text);
    }

    public static NavigableSet<String> getReportStatusRefData() {
        return new TreeSet<>(reportStatusRefData.keySet());
    }

    public static String getReportStatus(Integer id) {
        return getKeyForValue(reportStatusRefData, id);
    }

    public static Integer getReportStatusId(String text) {
        return reportStatusRefData.get(text);
    }
}
