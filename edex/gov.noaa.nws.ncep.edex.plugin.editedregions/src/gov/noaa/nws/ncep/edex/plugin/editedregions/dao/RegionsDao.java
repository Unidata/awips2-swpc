package gov.noaa.nws.ncep.edex.plugin.editedregions.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Property;
import org.springframework.transaction.TransactionException;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;

import com.raytheon.uf.edex.database.DataAccessLayerException;
import com.raytheon.uf.edex.database.dao.CoreDao;
import com.raytheon.uf.edex.database.dao.DaoConfig;

import gov.noaa.nws.ncep.common.dataplugin.editedregions.Region;

/**
 * Data Access Object (DAO) class to interact with swpc_region database table.
 * 
 * @author jtravis
 * @version 1.0
 */
public class RegionsDao extends CoreDao {

    /**
     * Creates a new EventBinDao
     */
    public RegionsDao() {
        super(DaoConfig.forClass(RegionsDao.class));
    }

    /**
     * Persist the Region and return the ID
     *
     * @param region
     * @return id the generated unique identifier for this region (NOT the
     *         region number)
     * 
     * @throws DataAccessLayerException
     */
    public Long persist(final Region region) throws DataAccessLayerException {
        try {
            // Get a session and create a new criteria instance
            return (Long) txTemplate.execute(new TransactionCallback<Long>() {
                @Override
                public Long doInTransaction(TransactionStatus status) {
                    return (Long) getCurrentSession().save(region);
                }
            });

        } catch (TransactionException e) {
            throw new DataAccessLayerException("Transaction failed", e);
        }

    }

    /**
     * Retrieves the latest region that was created
     *
     * @return Region
     */
    @SuppressWarnings({ "unchecked", "rawtypes" })
    public List<Region> getRegions() {
        return (List<Region>) txTemplate.execute(new TransactionCallback() {
            @Override
            public Object doInTransaction(TransactionStatus status) {

                Session sess = getCurrentSession();
                Criteria crit = sess.createCriteria(Region.class);

                return crit.list();
            }
        });
    }

    /**
     * Retrieves the latest region that was created
     *
     * @return Region
     */
    @SuppressWarnings({ "unchecked", "rawtypes" })
    public Region getLatestRegion() {
        return (Region) txTemplate.execute(new TransactionCallback() {
            @Override
            public Object doInTransaction(TransactionStatus status) {
                DetachedCriteria maxId = DetachedCriteria.forClass(Region.class)
                        .setProjection(Projections.max("id"));

                Session sess = getCurrentSession();
                Criteria crit = sess.createCriteria(Region.class);
                crit.add(Property.forName("id").eq(maxId));

                return crit.uniqueResult();
            }
        });
    }
}