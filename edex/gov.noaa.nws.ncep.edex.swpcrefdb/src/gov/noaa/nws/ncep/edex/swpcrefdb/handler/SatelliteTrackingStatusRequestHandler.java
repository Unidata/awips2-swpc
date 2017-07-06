package gov.noaa.nws.ncep.edex.swpcrefdb.handler;

import com.raytheon.edex.exception.DecoderException;
import com.raytheon.uf.common.serialization.comm.IRequestHandler;

import gov.noaa.nws.ncep.common.swpcrefdb.request.SatelliteTrackingStatusRequest;
import gov.noaa.nws.ncep.common.swpcrefdb.request.SatelliteTrackingStatusRequest.SatelliteTrackingStatusRequestType;
import gov.noaa.nws.ncep.edex.swpcrefdb.dao.SatelliteTrackingStatusDao;

/**
 * Satellite tracking status request handler.
 * 
 * <pre>
 *
 * SOFTWARE HISTORY
 *
 * Date         Ticket#    Engineer    Description
 * ------------ ---------- ----------- --------------------------
 * Apr 12, 2016  R9583     sgurung     Initial creation
 *
 * </pre>
 *
 * @author sgurung
 * @version 1.0
 */
public class SatelliteTrackingStatusRequestHandler
        implements IRequestHandler<SatelliteTrackingStatusRequest> {

    private SatelliteTrackingStatusDao trackingStatusDao;

    public SatelliteTrackingStatusRequestHandler() throws DecoderException {
        super();
        trackingStatusDao = new SatelliteTrackingStatusDao();
    }

    @Override
    public Object handleRequest(SatelliteTrackingStatusRequest request)
            throws Exception {

        if (trackingStatusDao == null)
            return null;

        SatelliteTrackingStatusRequestType requestType = request
                .getRequestType();

        switch (requestType) {
        case GET_GOES_SATELLITE:
            return trackingStatusDao.getGOESSatellite(request.getInstrument(),
                    request.getTrackingStatus(), request.getBeginDTTM(),
                    request.getEndDTTM());
        case GET_GOES_SATELLITE_FOR_INTEGRATED_FLUX:
            return trackingStatusDao.getGOESSatelliteForIntFlux(
                    request.getInstrument(), request.getTrackingStatus(),
                    request.getBeginDTTM(), request.getEndDTTM());
        case GET_GOES_SATELLITES:
            return trackingStatusDao.getGOESSatellite(request.getInstrument(),
                    request.getBeginDTTM(), request.getEndDTTM());
        default:
            break;
        }

        return null;
    }

}
