/**
 * This code has unlimited rights, and is provided "as is" by the National Centers 
 * for Environmental Prediction, without warranty of any kind, either expressed or implied, 
 * including but not limited to the implied warranties of merchantability and/or fitness 
 * for a particular purpose.
 * 
 * 
 * This code has been developed by the NCEP-SIB for use in the AWIPS2 system.
 * 
 */
package gov.noaa.nws.ncep.edex.plugin.editedregions.util;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.List;

import com.raytheon.uf.common.dataplugin.PluginDataObject;
import com.raytheon.uf.common.time.DataTime;

import gov.noaa.nws.ncep.common.dataplugin.editedregions.RegionReport;
import gov.noaa.nws.ncep.common.dataplugin.editedregions.exception.EditedRegionsException;
import gov.noaa.nws.ncep.common.dataplugin.editedregions.request.CreateRegionHistoryReportRequest;
import gov.noaa.nws.ncep.common.dataplugin.editedregions.response.CreateRegionHistoryReportResponse;
import gov.noaa.nws.ncep.common.dataplugin.editedregions.results.CreateRegionHistoryReportResults;
import gov.noaa.nws.ncep.common.dataplugin.editedregions.util.EditedRegionsConstants.REGION_REPORT_CHANGE_TYPE;
import gov.noaa.nws.ncep.edex.plugin.editedregions.commands.CreateRegionHistoryReportCommand;

/**
 * Utility class for the EventsDecoder.
 * 
 * <pre>
 * 
 * SOFTWARE HISTORY
 * 
 * Date         Ticket#    Engineer    Description
 * ------------ ---------- ----------- --------------------------
 * Nov 9, 2015  R9583      sgurung     Initial creation
 * 
 * </pre>
 * 
 * @author sgurung
 * @version 1.0
 */

public class RegionsDecoderUtil {

    public static PluginDataObject[] convertRegionReportsPDOs(
            List<RegionReport> reports) {

        List<PluginDataObject> pdos = new ArrayList<>();
        RegionReport report = null;

        for (int i = 0; i < reports.size(); i++) {

            report = updateRegionReport(reports.get(i));
            pdos.add(report);

        }
        return pdos.toArray(new PluginDataObject[pdos.size()]);

    }

    // @TODO -fix the naming of this method
    // @TODO -why on earth do we have to have a method to populate the DataTime
    // parameter??
    public static RegionReport updateRegionReport(RegionReport report) {

        // Event record = new Event();
        // Calendar cal = Calendar.getInstance();

        report.setDataTime(new DataTime(Calendar.getInstance().getTime()));

        return report;
    }

    public static void addRegionReportHistory(Collection<RegionReport> reports)
            throws EditedRegionsException {
        for (RegionReport report : reports) {
            CreateRegionHistoryReportRequest request = new CreateRegionHistoryReportRequest();
            request.setChangeType(REGION_REPORT_CHANGE_TYPE.CREATE);
            request.setRegionReportId(report.getId());
            request.setNewReport(report);

            CreateRegionHistoryReportCommand command = new CreateRegionHistoryReportCommand();

            CreateRegionHistoryReportResponse response = (CreateRegionHistoryReportResponse) command
                    .execute();

            CreateRegionHistoryReportResults results = (CreateRegionHistoryReportResults) response
                    .getResults();
            if (!results.isSuccessful()) {
                throw new EditedRegionsException(String.format(
                        "History could not be saved for report %d",
                        report.getId()));
            }
        }
    }
}
