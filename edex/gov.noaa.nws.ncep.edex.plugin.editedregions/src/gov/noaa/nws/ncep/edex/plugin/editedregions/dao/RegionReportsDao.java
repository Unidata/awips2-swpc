package gov.noaa.nws.ncep.edex.plugin.editedregions.dao;

import java.util.Calendar;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Property;
import org.hibernate.criterion.Restrictions;
import org.springframework.transaction.TransactionException;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;

import com.raytheon.uf.common.dataplugin.PluginException;
import com.raytheon.uf.common.dataplugin.persist.IPersistable;
import com.raytheon.uf.common.datastorage.IDataStore;
import com.raytheon.uf.edex.database.DataAccessLayerException;
import com.raytheon.uf.edex.database.plugin.PluginDao;

import gov.noaa.nws.ncep.common.dataplugin.editedregions.RegionReport;
import gov.noaa.nws.ncep.common.dataplugin.editedregions.util.EditedRegionsConstants;

/**
 * Data Access Object (DAO) class to interact with swpc_region_report database
 * table.
 * 
 * TODO need to create the swpc_region_report table
 * 
 * @author jtravis
 * @version 1.0
 */
public class RegionReportsDao extends PluginDao {

    /**
     * Creates a new RegionReportsDAO
     *
     * @throws PluginException
     */
    public RegionReportsDao() throws PluginException {
        super(EditedRegionsConstants.PLUGIN_NAME);
    }

    /**
     * Creates a new RegionReportsDao
     * 
     * @param pluginName
     * @throws PluginException
     */
    public RegionReportsDao(String pluginName) throws PluginException {
        super(pluginName);
    }

    /**
     * Persist the event and return the event id
     *
     * @param regionReport
     * @return id
     * @throws DataAccessLayerException
     */
    public Integer persist(final RegionReport regionReport)
            throws DataAccessLayerException {
        int id = 0;
        try {
            // Get a session and create a new criteria instance
            id = txTemplate.execute(new TransactionCallback<Integer>() {

                public Integer doInTransaction(TransactionStatus status) {

                    return (Integer) getCurrentSession().save(regionReport);
                }
            });

        } catch (TransactionException e) {
            throw new DataAccessLayerException("Transaction failed", e);
        }

        return id;
    }

    public RegionReport getRegionReport(final Integer reportId) {
        return txTemplate.execute(new TransactionCallback<RegionReport>() {
            @Override
            public RegionReport doInTransaction(TransactionStatus status) {
                Session session = getCurrentSession();
                Criteria crit = session.createCriteria(RegionReport.class);
                crit.add(Property.forName("id").eq(reportId));

                return (RegionReport) crit.uniqueResult();
            }

        });
    }

    /**
     * Retrieves list of Region Reports that have been assigned to a region
     *
     * @return list of events
     */
    @SuppressWarnings({ "unchecked", "rawtypes" })
    public List<RegionReport> getAssignedRegionReports(
            final boolean sortAscending) {
        return (List<RegionReport>) txTemplate
                .execute(new TransactionCallback() {

                    @Override
                    public Object doInTransaction(TransactionStatus status) {
                        Session sess = getCurrentSession();
                        Criteria crit = sess.createCriteria(RegionReport.class);
                        Criterion where1 = Restrictions
                                .isNotNull(RegionReport.REGION);

                        crit.add(where1);

                        if (sortAscending) {
                            crit.addOrder(Order.asc(RegionReport.STATION));
                        }

                        return crit.list();
                    }
                });
    }

    /**
     * Retrieves list of Region Reports that have not been assigned to a region
     *
     * @return list of events
     */
    @SuppressWarnings({ "unchecked", "rawtypes" })
    public List<RegionReport> getUnAssignedRegionReports(
            final boolean sortAscending) {
        return (List<RegionReport>) txTemplate
                .execute(new TransactionCallback() {

                    @Override
                    public Object doInTransaction(TransactionStatus status) {
                        Session sess = getCurrentSession();
                        Criteria crit = sess.createCriteria(RegionReport.class);
                        Criterion where1 = Restrictions
                                .isNull(RegionReport.REGION);

                        crit.add(where1);

                        if (sortAscending) {
                            crit.addOrder(Order.asc(RegionReport.STATION));
                        }

                        return crit.list();
                    }
                });
    }

    /**
     * Retrieves list of Region Reports that have not been assigned to a region
     *
     * @return list of events
     */
    @SuppressWarnings({ "unchecked" })
    public List<RegionReport> getAllRegionReports() {
        return (List<RegionReport>) txTemplate
                .execute(new TransactionCallback<List<RegionReport>>() {

                    @Override
                    public List<RegionReport> doInTransaction(
                            TransactionStatus status) {
                        Session sess = getCurrentSession();
                        Criteria crit = sess.createCriteria(RegionReport.class);
                        return crit.list();
                    }
                });
    }

    @SuppressWarnings("unchecked")
    public List<RegionReport> getRegionReports(final Calendar start,
            final Integer regionId) {
        return (List<RegionReport>) txTemplate
                .execute(new TransactionCallback<List<RegionReport>>() {
                    @Override
                    public List<RegionReport> doInTransaction(
                            TransactionStatus status) {
                        Session sess = getCurrentSession();
                        Criteria crit = sess.createCriteria(RegionReport.class);
                        Criterion where1 = Restrictions.eq(
                                RegionReport.OBSERVATION_TIME, start.getTime());
                        crit.add(where1);
                        Criterion where2 = Restrictions.eq(RegionReport.REGION,
                                regionId);
                        crit.add(where2);

                        return crit.list();
                    }
                });
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.raytheon.uf.edex.database.plugin.PluginDao#populateDataStore(com.
     * raytheon.uf.common.datastorage.IDataStore,
     * com.raytheon.uf.common.dataplugin.persist.IPersistable)
     */
    @Override
    protected IDataStore populateDataStore(IDataStore dataStore,
            IPersistable obj) throws Exception {
        // TODO Auto-generated method stub
        return null;
    }
}