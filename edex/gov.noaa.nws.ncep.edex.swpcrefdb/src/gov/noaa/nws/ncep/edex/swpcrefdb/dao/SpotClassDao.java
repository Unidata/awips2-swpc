package gov.noaa.nws.ncep.edex.swpcrefdb.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;

import com.raytheon.uf.edex.database.dao.CoreDao;
import com.raytheon.uf.edex.database.dao.DaoConfig;

import gov.noaa.nws.ncep.common.swpcrefdb.SpotClass;

/**
 * Provides access to the SWPC_SPOT_CLASS database table.
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
public class SpotClassDao extends CoreDao {

    /**
     * Creates a new SpotClassDao
     */
    public SpotClassDao() {
        super(DaoConfig.forClass(SpotClassDao.class));

    }

    /**
     * Retrieves List of all SpotClasses
     * 
     * @return List of SpotClasses
     */
    @SuppressWarnings({ "unchecked", "rawtypes" })
    public List<SpotClass> getObservationTypes() {
        return (List<SpotClass>) txTemplate.execute(new TransactionCallback() {
            @Override
            public Object doInTransaction(TransactionStatus status) {
                Session sess = getCurrentSession();
                Criteria crit = sess.createCriteria(SpotClass.class);

                List<SpotClass> stations = new ArrayList<>();
                stations.addAll(crit.list());

                return stations;
            }
        });
    }

}
