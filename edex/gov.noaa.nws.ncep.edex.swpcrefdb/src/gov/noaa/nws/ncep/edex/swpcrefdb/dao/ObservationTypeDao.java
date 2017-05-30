package gov.noaa.nws.ncep.edex.swpcrefdb.dao;

import java.util.Vector;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;

import com.raytheon.uf.edex.database.dao.CoreDao;
import com.raytheon.uf.edex.database.dao.DaoConfig;

import gov.noaa.nws.ncep.common.swpcrefdb.ObservationType;

/**
 * Provides access to the SWPC_STATION database table
 * 
 * <pre>
 *
 * SOFTWARE HISTORY
 *
 * Date         Ticket#    Engineer    Description
 * ------------ ---------- ----------- --------------------------
 * Jan 12, 2016  NA     jtravis     Initial creation
 *
 * </pre>
 *
 * @author jtravis
 * @version 1.0	
 */
public class ObservationTypeDao extends CoreDao {

    /**
     * Creates a new StationDao
     */
    public ObservationTypeDao() {
        super(DaoConfig.forClass(ObservationTypeDao.class));
      
    }

    /**
     * Retrieves Vector of all ObservationTypes
     * 
     * @param type the ObservationType
     * @return Vector of ObservationTypes
     */
    @SuppressWarnings({ "unchecked", "rawtypes" })
    public Vector<ObservationType> getObservationTypes(final ObservationType type) {
        return (Vector<ObservationType>) txTemplate.execute(new TransactionCallback() {
            @Override
            public Object doInTransaction(TransactionStatus status) {
                Session sess = getCurrentSession();                
                Criteria crit = sess.createCriteria(ObservationType.class);
                
                Vector<ObservationType> stations = new Vector<ObservationType>();
                stations.addAll(crit.list());
                
                return stations;
            }
        });
    }

}
