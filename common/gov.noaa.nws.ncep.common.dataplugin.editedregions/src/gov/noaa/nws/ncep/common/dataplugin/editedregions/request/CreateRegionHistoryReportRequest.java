package gov.noaa.nws.ncep.common.dataplugin.editedregions.request;

import com.raytheon.uf.common.serialization.annotations.DynamicSerialize;
import com.raytheon.uf.common.serialization.annotations.DynamicSerializeElement;

import gov.noaa.nws.ncep.common.dataplugin.editedregions.RegionReport;
import gov.noaa.nws.ncep.common.dataplugin.editedregions.request.intf.IRequest;

/**
 * Request class to allow forecasters to create a region report
 * 
 * @author jtravis
 * @version 1.0
 */
@DynamicSerialize
public class CreateRegionHistoryReportRequest
        extends Request<CreateRegionHistoryReportRequest> implements IRequest {

    /**
     * The Requests ID
     */
    @DynamicSerializeElement
    private final long id;

    /**
     * The previous region report.
     */

    @DynamicSerializeElement
    private RegionReport oldReport = null;

    /**
     * The curret region report.
     */
    private RegionReport newReport = null;

    private Integer regionReportId = null;

    /**
     * 
     */
    public CreateRegionHistoryReportRequest() {
        this.id = System.currentTimeMillis();
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * gov.noaa.nws.ncep.common.dataplugin.editedevents.request.IRequest#getID()
     */
    @Override
    public long getId() {
        return this.id;
    }

    /*
     * (non-Javadoc) (this.request.getNewReport()
     * 
     * @see
     * gov.noaa.nws.ncep.common.dataplugin.editedevents.request.IRequest#isValid
     * ()
     */
    @Override
    public boolean isValid() {
        return this.oldReport != null && this.newReport != null;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * gov.noaa.nws.ncep.common.dataplugin.editedregions.request.intf.IRequest#
     * setId(long)
     */
    @Override
    public void setId(long ID) {
        // The id is set when the request is initialized
        // but the system still requires the method for
        // serialization / deserialization through the gateway.
        //
        // thus, this method does not actually change the id
        //
        // TODO - verify this
        ;
        ;
    }

    public RegionReport getOldReport() {
        return oldReport;
    }

    public void setOldReport(RegionReport oldReport) {
        this.oldReport = oldReport;
    }

    public RegionReport getNewReport() {
        return newReport;
    }

    public void setNewReport(RegionReport newReport) {
        this.newReport = newReport;
    }

    public Integer getRegionReportId() {
        return regionReportId;
    }

    public void setRegionReportId(Integer regionReportId) {
        this.regionReportId = regionReportId;
    }

}
