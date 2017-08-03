package gov.noaa.nws.ncep.common.dataplugin.editedregions.request;

import com.raytheon.uf.common.serialization.annotations.DynamicSerialize;
import com.raytheon.uf.common.serialization.annotations.DynamicSerializeElement;

import gov.noaa.nws.ncep.common.dataplugin.editedregions.RegionHistoryReport;
import gov.noaa.nws.ncep.common.dataplugin.editedregions.request.intf.IRequest;

/**
 * Request class to allow forecasters to create a region report
 * 
 * @author jtravis
 * @version 1.0
 */
@DynamicSerialize
public class UpdateRegionHistoryReportRequest
        extends Request<UpdateRegionHistoryReportRequest> implements IRequest {

    /**
     * The Requests ID
     */
    @DynamicSerializeElement
    private final long id;

    /**
     * The region report to persist
     */
    @DynamicSerializeElement
    private RegionHistoryReport historyReport = null;

    /**
     * The region report ID of the region report that is persisted/updated TODO
     * do we need this?
     */
    @DynamicSerializeElement
    private Integer historyReportID = null;

    /**
     * 
     */
    public UpdateRegionHistoryReportRequest() {
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
     * (non-Javadoc)
     * 
     * @see
     * gov.noaa.nws.ncep.common.dataplugin.editedevents.request.IRequest#isValid
     * ()
     */
    @Override
    public boolean isValid() {
        boolean valid = false;

        if (this.historyReport != null) {
            valid = true;
        }

        return valid;
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

    public RegionHistoryReport getHistoryReport() {
        return historyReport;
    }

    public void setHistoryReport(RegionHistoryReport historyReport) {
        this.historyReport = historyReport;
    }

    public Integer getHistoryReportID() {
        return historyReportID;
    }

    public void setHistoryReportID(Integer historyReportID) {
        this.historyReportID = historyReportID;
    }

}
