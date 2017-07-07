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
public class UpdateRegionReportRequest
        extends Request<UpdateRegionReportRequest> implements IRequest {

    /**
     * The Requests ID
     */
    @DynamicSerializeElement
    private final long id;

    /**
     * The region report to persist
     */
    @DynamicSerializeElement
    private RegionReport regionReport = null;

    /**
     * The region report ID of the region report that is persisted/updated TODO
     * do we need this?
     */
    @DynamicSerializeElement
    private Integer regionReportID = null;

    /**
     * 
     */
    public UpdateRegionReportRequest() {
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

        if (this.regionReport != null) {
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

    /**
     * @return the region
     */
    public RegionReport getRegionReport() {
        return this.regionReport;
    }

    /**
     * @param
     */
    public void setRegionReport(RegionReport regionReport) {
        this.regionReport = regionReport;
    }

    /**
     * @return the regionReportID
     */
    public Integer getRegionReportID() {
        return this.regionReportID;
    }

    /**
     * @param regionReportID
     *            the regionReportID to set
     */
    public void setRegionReportID(Integer regionReportID) {
        this.regionReportID = regionReportID;
    }

}
