package gov.noaa.nws.ncep.edex.swpcrefdb.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;

import com.raytheon.uf.edex.database.dao.CoreDao;
import com.raytheon.uf.edex.database.dao.DaoConfig;

import gov.noaa.nws.ncep.common.swpcrefdb.SunSpotQuadrantLocation;

/**
 * Provides access to the SWPC_SUNSPOT_QUADRANT_LOCATION database table.
 * 
 * <pre>
 *
 * SOFTWARE HISTORY
 *
 * Date         Ticket#    Engineer    Description
 * ------------ ---------- ----------- --------------------------
 * May 30, 2017  N/A     alockleigh     Initial creation
 *
 * </pre>
 *
 * @author alockleigh
 * @version 1.0
 */
public class SunSpotQuadrantLocationDao extends CoreDao {

    /**
     * Creates a new SunSpotQuadrantLocationDao
     */
    public SunSpotQuadrantLocationDao() {
        super(DaoConfig.forClass(SunSpotQuadrantLocationDao.class));

    }

    /**
     * Retrieves List of all SunSpotQuadrantLocations
     * 
     * @return List of SunSpotQuadrantLocations
     */
    @SuppressWarnings({ "unchecked", "rawtypes" })
    public List<SunSpotQuadrantLocation> getObservationTypes() {
        return (List<SunSpotQuadrantLocation>) txTemplate
                .execute(new TransactionCallback() {
                    @Override
                    public Object doInTransaction(TransactionStatus status) {
                        Session sess = getCurrentSession();
                        Criteria crit = sess
                                .createCriteria(SunSpotQuadrantLocation.class);

                        List<SunSpotQuadrantLocation> stations = new ArrayList<>();
                        stations.addAll(crit.list());

                        return stations;
                    }
                });
    }

}
