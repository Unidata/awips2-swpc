package gov.noaa.nws.ncep.common.dataplugin.editedregions.request;

import com.raytheon.uf.common.serialization.annotations.DynamicSerialize;
import com.raytheon.uf.common.serialization.annotations.DynamicSerializeElement;

import gov.noaa.nws.ncep.common.dataplugin.editedregions.request.intf.IRequest;

/**
 * Class for allowing the client to request the back-end to obtain the regions
 * that can be assigned to a region report
 * 
 * @author jtravis
 * @version 1.0
 */
@DynamicSerialize
public class GetRegionsRequest extends Request<GetRegionsRequest>
        implements IRequest {

    /**
     * The Requests ID
     */
    @DynamicSerializeElement
    private final long id;

    /**
     * Begin time value in milliseconds used for filtering requests
     */
    @DynamicSerializeElement
    private long beginTime = 0L;

    /**
     * End time value in milliseconds used for filtering requests
     */
    @DynamicSerializeElement
    private long endTime = 0L;

    /**
     * Indicator showing if filtering is applied for the request
     */
    @DynamicSerializeElement
    private boolean filterApplied = false;

    /**
     * Constructor
     */
    public GetRegionsRequest() {
        this.id = System.currentTimeMillis();
    }

    /**
     * Constructor supplying time parameters to use as filter for obtaining
     * regions based on a time range
     * 
     * @param getAssignedReports
     * @param getUnassignedReports
     */
    public GetRegionsRequest(long beginTime, long endTime) {
        this.id = System.currentTimeMillis();
        this.beginTime = beginTime;
        this.endTime = endTime;
        this.filterApplied = true;
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

        if (this.filterApplied) {
            if (this.beginTime != 0L || this.endTime != 0L) {
                valid = true;
            }
        } else {
            if (this.beginTime == 0L || this.endTime == 0L) {
                valid = true;
            }
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
     * @return the beginTime
     */
    public long getBeginTime() {
        return beginTime;
    }

    /**
     * Note setting the begin time parameter is only to be performed via the
     * constructor. This method is required for serialization purposes only.
     * 
     * @param beginTime
     *            the beginTime to set
     */
    public void setBeginTime(long beginTime) {
        ;
        ;
    }

    /**
     * @return the endTime
     */
    public long getEndTime() {
        return endTime;
    }

    /**
     * Note setting the end time parameter is only to be performed via the
     * constructor. This method is required for serialization purposes only.
     * 
     * @param endTime
     *            the endTime to set
     */
    public void setEndTime(long endTime) {
        ;
        ;
    }

    /**
     * @return the filterApplied
     */
    public boolean isFilterApplied() {
        return this.filterApplied;
    }

    /**
     * Note that this method is required for serialization purposes but setting
     * the filterApplied parameter on the request should only be performed by
     * the constructor. Thus, this method does not alter the filterApplied
     * parameter.
     * 
     * @param filterApplied
     *            the filterApplied to set
     */
    public void setFilterApplied(boolean filterApplied) {
        ;
        ;
    }

}
