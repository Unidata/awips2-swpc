package gov.noaa.nws.ncep.edex.swpcrefdb.dao;

import java.util.Vector;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;

import com.raytheon.uf.edex.database.dao.CoreDao;
import com.raytheon.uf.edex.database.dao.DaoConfig;

import gov.noaa.nws.ncep.common.swpcrefdb.PenumbralClass;

/**
 * Provides access to the SWPC_PENUMBRAL_CLASS database table
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
public class PenumbralClassDao extends CoreDao {

    /**
     * Creates a new PenumbralClassDao
     */
    public PenumbralClassDao() {
        super(DaoConfig.forClass(PenumbralClassDao.class));
      
    }

    /**
     * Retrieves Vector of all PenumbralClass instances
     * 
     * @param type the PenumbralClass
     * @return Vector of PenumbralClass instances
     */
    @SuppressWarnings({ "unchecked", "rawtypes" })
    public Vector<PenumbralClass> getAllPenumbralClasses() {
        return (Vector<PenumbralClass>) txTemplate.execute(new TransactionCallback() {
            @Override
            public Object doInTransaction(TransactionStatus status) {
                Session sess = getCurrentSession();                
                Criteria crit = sess.createCriteria(PenumbralClass.class);
                
                Vector<PenumbralClass> results = new Vector<PenumbralClass>();
                results.addAll(crit.list());
                
                return results;
            }
        });
    }

}
