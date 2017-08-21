package gov.noaa.nws.ncep.common.dataplugin.editedregions.request;

import com.raytheon.uf.common.serialization.annotations.DynamicSerialize;
import com.raytheon.uf.common.serialization.annotations.DynamicSerializeElement;

import gov.noaa.nws.ncep.common.dataplugin.editedregions.request.intf.IRequest;

/**
 * 
 * Request for GetReportsWithoutHistoryCommand
 * 
 * <pre>
 *
 * SOFTWARE HISTORY
 *
 * Date         Ticket#    Engineer    Description
 * ------------ ---------- ----------- --------------------------
 * Aug 21, 2017            alockleigh     Initial creation
 *
 * </pre>
 *
 * @author alockleigh
 */
@DynamicSerialize
public class GetReportsWithoutHistoryRequest
        extends Request<GetReportsWithoutHistoryRequest> implements IRequest {

    @DynamicSerializeElement
    private final long id;

    public GetReportsWithoutHistoryRequest() {
        this.id = System.currentTimeMillis();
    }

    /**
     * @return the id
     */
    public long getId() {
        return id;
    }

    @Override
    public void setId(long Id) {
        // id is final. Cannot be set.

    }

    @Override
    public boolean isValid() {
        // Always valid
        return true;
    }

}
