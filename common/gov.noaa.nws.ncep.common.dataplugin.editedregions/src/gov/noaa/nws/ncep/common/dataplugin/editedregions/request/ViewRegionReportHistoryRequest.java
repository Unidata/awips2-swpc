package gov.noaa.nws.ncep.common.dataplugin.editedregions.request;

import com.raytheon.uf.common.serialization.annotations.DynamicSerialize;
import com.raytheon.uf.common.serialization.annotations.DynamicSerializeElement;

import gov.noaa.nws.ncep.common.dataplugin.editedregions.request.intf.IRequest;

/**
 * Class for allowing the client to request the back-end to obtain the history
 * of all changes made to a given Region Report
 * 
 * TODO complete this
 * 
 * @author jtravis
 * @version 1.0
 */
@DynamicSerialize
public class ViewRegionReportHistoryRequest
        extends Request<ViewRegionReportHistoryRequest> implements IRequest {

    /**
     * The Requests ID
     */
    @DynamicSerializeElement
    private final long id;

    private Integer reportId;

    /**
     * Constructor
     */
    public ViewRegionReportHistoryRequest() {
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
     * TODO currently no validation performed as the request requires no
     * parameters which could be used for validation revisit in ER v2.0
     * 
     * @see
     * gov.noaa.nws.ncep.common.dataplugin.editedevents.request.IRequest#isValid
     * ()
     */
    @Override
    public boolean isValid() {
        return this.reportId != null;
    }

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

    public Integer getReportId() {
        return reportId;
    }

    public void setReportId(Integer reportId) {
        this.reportId = reportId;
    }

}
