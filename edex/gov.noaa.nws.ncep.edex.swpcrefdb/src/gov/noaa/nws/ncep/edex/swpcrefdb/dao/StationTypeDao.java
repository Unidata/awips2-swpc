package gov.noaa.nws.ncep.edex.swpcrefdb.dao;

import gov.noaa.nws.ncep.common.swpcrefdb.StationType;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;

import com.raytheon.uf.edex.database.dao.CoreDao;
import com.raytheon.uf.edex.database.dao.DaoConfig;

/**
 * Provides access to the SWPC_STATION_TYPE database table
 * 
 * <pre>
 *
 * SOFTWARE HISTORY
 *
 * Date         Ticket#    Engineer    Description
 * ------------ ---------- ----------- --------------------------
 * Jan 12, 2016  R9583     sgurung     Initial creation
 *
 * </pre>
 *
 * @author sgurung
 * @version 1.0	
 */
public class StationTypeDao extends CoreDao {

    /**
     * Creates a new StationTypeDao
     */
    public StationTypeDao() {
        super(DaoConfig.forClass(StationTypeDao.class));
      
    }

    /**
     * Retrieve a station type given a description
     * 
     * @param description
     * @return StationType
     */
    @SuppressWarnings({ "unchecked", "rawtypes" })
    public StationType getStationType(final String description) {
        return (StationType) txTemplate.execute(new TransactionCallback() {
            @Override
            public Object doInTransaction(TransactionStatus status) {
                Session sess = getCurrentSession();                
                Criteria crit = sess.createCriteria(StationType.class);
                Criterion where1 = Restrictions.eq(
                        StationType.DESCRIPTION, description);
                crit.add(where1);
                return crit.list().get(0);
            }
        });
    }

}
