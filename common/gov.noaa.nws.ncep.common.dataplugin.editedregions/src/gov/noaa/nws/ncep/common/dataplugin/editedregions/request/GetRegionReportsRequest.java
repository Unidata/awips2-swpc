package gov.noaa.nws.ncep.common.dataplugin.editedregions.request;

import com.raytheon.uf.common.serialization.annotations.DynamicSerialize;
import com.raytheon.uf.common.serialization.annotations.DynamicSerializeElement;

import gov.noaa.nws.ncep.common.dataplugin.editedregions.request.intf.IRequest;

/**
 * Class for allowing the client to request the back-end to obtain the Assigned
 * Region Reports
 * 
 * @author jtravis
 * @version 1.0
 */
@DynamicSerialize
public class GetRegionReportsRequest extends Request<GetRegionReportsRequest>
        implements IRequest {

    /**
     * The Requests ID
     */
    @DynamicSerializeElement
    private final long id;

    /**
     * Indicates reports that are not assigned to a region should be obtained
     */
    @DynamicSerializeElement
    private boolean obtainUnassignedReports = true;

    /**
     * Indicates reports that are assigned to a region should be obtained
     */
    @DynamicSerializeElement
    private boolean obtainAssignedReports = true;

    /**
     * Constructor
     */
    public GetRegionReportsRequest() {
        this.id = System.currentTimeMillis();
        this.obtainAssignedReports = false;
        this.obtainUnassignedReports = false;
    }

    /**
     * Constructor supplying argument to determine what kinds of region reports
     * to obtain
     * 
     * @param getAssignedReports
     * @param getUnassignedReports
     */
    public GetRegionReportsRequest(boolean getAssignedReports,
            boolean getUnassignedReports) {
        this.id = System.currentTimeMillis();
        this.obtainAssignedReports = getAssignedReports;
        this.obtainUnassignedReports = getUnassignedReports;
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

        // the request is valid only if at least one report type is being
        // requested.
        if (this.obtainAssignedReports || this.obtainUnassignedReports) {
            valid = true;
        }

        return valid;
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

    /**
     * @return the obtainUnassignedReports
     */
    public boolean isObtainUnassignedReports() {
        return obtainUnassignedReports;
    }

    /**
     * @param obtainUnassignedReports
     *            the obtainUnassignedReports to set
     */
    public void setObtainUnassignedReports(boolean obtainUnassignedReports) {
        this.obtainUnassignedReports = obtainUnassignedReports;
    }

    /**
     * @return the obtainAssignedReports
     */
    public boolean isObtainAssignedReports() {
        return obtainAssignedReports;
    }

    /**
     * @param obtainAssignedReports
     *            the obtainAssignedReports to set
     */
    public void setObtainAssignedReports(boolean obtainAssignedReports) {
        this.obtainAssignedReports = obtainAssignedReports;
    }

}
