package gov.noaa.nws.ncep.common.dataplugin.editedregions.request;

import com.raytheon.uf.common.serialization.annotations.DynamicSerialize;
import com.raytheon.uf.common.serialization.annotations.DynamicSerializeElement;

import gov.noaa.nws.ncep.common.dataplugin.editedregions.Region;
import gov.noaa.nws.ncep.common.dataplugin.editedregions.request.intf.IRequest;

/**
 * Request class to initiate the creation of a new region
 * 
 * @author jtravis
 * @version 1.0
 */
@DynamicSerialize
public class CreateRegionRequest extends Request<CreateRegionRequest>
        implements IRequest {

    /**
     * The Requests ID
     */
    @DynamicSerializeElement
    private final long id;

    /**
     * The region to create
     */
    @DynamicSerializeElement
    private Region region = null;

    /**
     * The region ID of the region report that is persisted/updated TODO do we
     * need this?
     */
    @DynamicSerializeElement
    private Integer regionID = null;

    /**
     * 
     */
    public CreateRegionRequest() {
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

        if (this.region != null) {
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
     * @return the regionID
     */
    public Integer getRegionID() {
        return this.regionID;
    }

    /**
     * @param regionID
     *            the regionID to set
     */
    public void setRegionID(Integer regionID) {
        this.regionID = regionID;
    }

}
