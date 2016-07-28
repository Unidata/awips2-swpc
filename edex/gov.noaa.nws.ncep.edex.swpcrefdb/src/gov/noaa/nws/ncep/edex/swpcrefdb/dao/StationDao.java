package gov.noaa.nws.ncep.edex.swpcrefdb.dao;

import gov.noaa.nws.ncep.common.swpcrefdb.Station;
import gov.noaa.nws.ncep.common.swpcrefdb.StationType;

import java.util.Vector;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;

import com.raytheon.uf.edex.database.dao.CoreDao;
import com.raytheon.uf.edex.database.dao.DaoConfig;

/**
 * Provides access to the SWPC_STATION database table
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
public class StationDao extends CoreDao {

    /**
     * Creates a new StationDao
     */
    public StationDao() {
        super(DaoConfig.forClass(StationDao.class));
      
    }

    /**
     * Retrieves list of stations with a given type
     * 
     * @param type the station type
     * @return list of stations
     */
    @SuppressWarnings({ "unchecked", "rawtypes" })
    public Vector<Station> getStations(final StationType type) {
        return (Vector<Station>) txTemplate.execute(new TransactionCallback() {
            @Override
            public Object doInTransaction(TransactionStatus status) {
                Session sess = getCurrentSession();                
                Criteria crit = sess.createCriteria(Station.class);
                Criterion where1 = Restrictions.eq(
                        Station.TYPE, type);
                crit.add(where1);                
                crit.addOrder(Order.asc(Station.DESIGNATION));
                
                Vector<Station> stations = new Vector<Station>();
                stations.addAll(crit.list());
                return stations;
            }
        });
    }

}
