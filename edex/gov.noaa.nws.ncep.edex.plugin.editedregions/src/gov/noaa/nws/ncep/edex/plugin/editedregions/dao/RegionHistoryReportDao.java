package gov.noaa.nws.ncep.edex.plugin.editedregions.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Property;
import org.hibernate.criterion.Restrictions;
import org.springframework.transaction.TransactionException;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;

import com.raytheon.uf.edex.database.DataAccessLayerException;
import com.raytheon.uf.edex.database.dao.CoreDao;
import com.raytheon.uf.edex.database.dao.DaoConfig;

import gov.noaa.nws.ncep.common.dataplugin.editedregions.Region;
import gov.noaa.nws.ncep.common.dataplugin.editedregions.RegionHistoryReport;

/**
 * Data Access Object (DAO) class to interact with swpc_region_history_report
 * database table.
 * 
 * @author alockleigh
 * @version 1.0
 */
public class RegionHistoryReportDao extends CoreDao {

    /**
     * Creates a new EventBinDao
     */
    public RegionHistoryReportDao() {
        super(DaoConfig.forClass(RegionHistoryReportDao.class));
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

    /**
     * Retrieves the list of region history reports for a given region report;
     * 
     * @param report
     * @return the list of RegionHistoryReports
     */
    @SuppressWarnings({ "unchecked" })
    public List<RegionHistoryReport> getHistoryReports(final Integer reportId) {
        return txTemplate
                .execute(new TransactionCallback<List<RegionHistoryReport>>() {
                    @Override
                    public List<RegionHistoryReport> doInTransaction(
                            TransactionStatus status) {
                        Session session = getSession();
                        Criteria crit = session
                                .createCriteria(RegionHistoryReport.class);
                        Criterion where = Restrictions.eq(
                                RegionHistoryReport.REGION_REPORT_ID, reportId);
                        crit.add(where);
                        return crit.list();
                    }

                });
    }

    /**
     * 
     * @param report
     * @return
     */
    public int persist(final RegionHistoryReport report)
            throws DataAccessLayerException {
        try {
            return txTemplate.execute(new TransactionCallback<Integer>() {
                @Override
                public Integer doInTransaction(TransactionStatus status) {
                    return (Integer) getCurrentSession().save(report);
                }
            });
        } catch (TransactionException e) {
            throw new DataAccessLayerException("Transaction failed", e);
        }
    }

}