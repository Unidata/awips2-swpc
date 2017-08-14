package gov.noaa.nws.ncep.common.dataplugin.editedregions.util;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import gov.noaa.nws.ncep.common.dataplugin.editedregions.RegionHistoryReport;
import gov.noaa.nws.ncep.common.dataplugin.editedregions.util.EditedRegionsConstants.REGION_REPORT_CHANGE_TYPE;

/**
 * 
 * Class for converting RegionHistoryReport objects to and from maps.
 * 
 * <pre>
 *
 * SOFTWARE HISTORY
 *
 * Date         Ticket#    Engineer    Description
 * ------------ ---------- ----------- --------------------------
 * Aug 14, 2017            alockleigh     Initial creation
 *
 * </pre>
 *
 * @author alockleigh
 */
public final class RegionHistoryReportUtility {

    private RegionHistoryReportUtility() {
        throw new Error("No instance for you.");
    }

    private static final String OLD_VALUE = "old";

    private static final String NEW_VALUE = "new";

    private static final String FIELD_NAME = "field";

    private static final String REPORT_ID = "id";

    private static final String TIME_OF_CHANGE = "dttm";

    private static final String TYPE_OF_CHANGE = "type";

    public static Map<String, String> convertToMap(
            RegionHistoryReport historyReport) {
        Objects.requireNonNull(historyReport);
        Map<String, String> map = new HashMap<>();
        map.put(REPORT_ID, String.valueOf(historyReport.getRegionReportId()));
        map.put(OLD_VALUE, historyReport.getFieldValueBefore());
        map.put(NEW_VALUE, historyReport.getFieldValueCurrent());
        map.put(FIELD_NAME, historyReport.getModifiedField());
        map.put(TYPE_OF_CHANGE, historyReport.getTypeOfChange().name());
        map.put(TIME_OF_CHANGE,
                String.valueOf(historyReport.getTimeOfChange()));
        return map;
    }

    public static RegionHistoryReport convertToObject(Map<String, String> map) {
        Objects.requireNonNull(map);
        RegionHistoryReport historyReport = new RegionHistoryReport();

        historyReport.setRegionReportId(Integer.parseInt(map.get(REPORT_ID)));
        historyReport.setFieldValueBefore(map.get(OLD_VALUE));
        historyReport.setFieldValueCurrent(map.get(NEW_VALUE));
        historyReport.setModifiedField(map.get(FIELD_NAME));
        historyReport.setTypeOfChange(Enum.valueOf(
                REGION_REPORT_CHANGE_TYPE.class, map.get(TYPE_OF_CHANGE)));

        long timestamp = Long.parseLong(map.get(TIME_OF_CHANGE));
        historyReport.setTimeOfChange(timestamp);

        return historyReport;

    }

}
