package gov.noaa.nws.ncep.edex.swpcrefdb.dao;

import java.util.Vector;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;

import com.raytheon.uf.edex.database.dao.CoreDao;
import com.raytheon.uf.edex.database.dao.DaoConfig;

import gov.noaa.nws.ncep.common.swpcrefdb.ObservationQuality;

/**
 * Provides access to the SWPC_OBSERVATION_QUALITY database table
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
public class ObservationQualityDao extends CoreDao {

    /**
     * Creates a new ObservationQualityDao
     */
    public ObservationQualityDao() {
        super(DaoConfig.forClass(ObservationQualityDao.class));
      
    }

    /**
     * Retrieves Vector of all ObservationQuality instances
     * 
     * @param type the ObservationQuality
     * @return Vector of ObservationQuality instances
     */
    @SuppressWarnings({ "unchecked", "rawtypes" })
    public Vector<ObservationQuality> getObservationTypes(final ObservationQuality type) {
        return (Vector<ObservationQuality>) txTemplate.execute(new TransactionCallback() {
            @Override
            public Object doInTransaction(TransactionStatus status) {
                Session sess = getCurrentSession();                
                Criteria crit = sess.createCriteria(ObservationQuality.class);
                
                Vector<ObservationQuality> results = new Vector<ObservationQuality>();
                results.addAll(crit.list());
                
                return results;
            }
        });
    }

}
