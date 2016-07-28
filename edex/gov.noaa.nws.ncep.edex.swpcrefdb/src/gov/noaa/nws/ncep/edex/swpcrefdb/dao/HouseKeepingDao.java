package gov.noaa.nws.ncep.edex.swpcrefdb.dao;

import gov.noaa.nws.ncep.common.swpcrefdb.HouseKeeping;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;

import com.raytheon.uf.edex.database.dao.CoreDao;
import com.raytheon.uf.edex.database.dao.DaoConfig;

/**
 * Data Access Object (DAO) class to interact with swpc_housekeeping database
 * table.
 * 
 * * <pre>
 * 
 * SOFTWARE HISTORY
 * 
 * Date         Ticket#    Engineer    Description
 * ------------ ---------- ----------- --------------------------
 * Mar 19, 2016  R9583     sgurung     Initial creation
 * 
 * </pre>
 * 
 * @author sgurung
 * @version 1.0
 */
public class HouseKeepingDao extends CoreDao {

    /**
     * Creates a new HouseKeepingDao
     */
    public HouseKeepingDao() {
        super(DaoConfig.forClass(HouseKeepingDao.class));
      
    }

    /**
     * Retrieves a HouseKeeping record given the application name
     * 
     * @param applicationName
     * @return HouseKeeping
     */
    @SuppressWarnings({ "unchecked", "rawtypes" })
    public HouseKeeping getHouseKeepingForEvents(final String applicationName) {
        return (HouseKeeping) txTemplate.execute(new TransactionCallback() {
            @Override
            public Object doInTransaction(TransactionStatus status) {
                Session sess = getCurrentSession();                
                Criteria crit = sess.createCriteria(HouseKeeping.class);
                Criterion where1 = Restrictions.eq(
                        HouseKeeping.APPLICATION_NAME, applicationName);
                crit.add(where1);
                return crit.list().get(0);
            }
        });
    }

}
