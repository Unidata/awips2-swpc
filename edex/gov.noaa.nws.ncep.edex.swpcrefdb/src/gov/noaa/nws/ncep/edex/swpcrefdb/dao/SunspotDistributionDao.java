package gov.noaa.nws.ncep.edex.swpcrefdb.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;

import com.raytheon.uf.edex.database.dao.CoreDao;
import com.raytheon.uf.edex.database.dao.DaoConfig;

import gov.noaa.nws.ncep.common.swpcrefdb.SunspotDistribution;

/**
 * Provides access to the SWPC_SUNSPOT_DISTRIBUTION database table.
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
public class SunspotDistributionDao extends CoreDao {

    /**
     * Creates a new SunspotDistributionDao
     */
    public SunspotDistributionDao() {
        super(DaoConfig.forClass(SunspotDistributionDao.class));

    }

    /**
     * Retrieves List of all SunspotDistributions
     * 
     * @return List of SunspotDistributions
     */
    @SuppressWarnings({ "unchecked", "rawtypes" })
    public List<SunspotDistribution> getObservationTypes() {
        return (List<SunspotDistribution>) txTemplate
                .execute(new TransactionCallback() {
                    @Override
                    public Object doInTransaction(TransactionStatus status) {
                        Session sess = getCurrentSession();
                        Criteria crit = sess
                                .createCriteria(SunspotDistribution.class);

                        List<SunspotDistribution> stations = new ArrayList<>();
                        stations.addAll(crit.list());

                        return stations;
                    }
                });
    }

}
