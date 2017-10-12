package gov.noaa.nws.ncep.edex.plugin.editedregions.dao;

import java.sql.SQLException;
import java.util.Calendar;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;

import com.raytheon.uf.common.dataplugin.PluginException;
import com.raytheon.uf.edex.database.dao.CoreDao;
import com.raytheon.uf.edex.database.dao.DaoConfig;

import gov.noaa.nws.ncep.common.dataplugin.editedregions.RegionConsensus;
import gov.noaa.nws.ncep.common.dataplugin.editedregions.util.EditedRegionsConstants;

/**
 * Data Access Object (DAO) class to interact with swpc_region_history_report
 * database table.
 * 
 * @author alockleigh
 * @version 1.0
 */
public class RegionConsensusDao extends CoreDao {

    /**
     * Creates a new EventBinDao
     */
    public RegionConsensusDao() {
        super(DaoConfig.forClass(RegionConsensusDao.class));
    }

    // /**
    // * Retrieves the latest region that was created
    // *
    // * @return Region
    // */
    // @SuppressWarnings({ "unchecked", "rawtypes" })
    // public List<Region> getRegions() {
    // return (List<Region>) txTemplate.execute(new TransactionCallback() {
    // @Override
    // public Object doInTransaction(TransactionStatus status) {
    //
    // Session sess = getCurrentSession();
    // Criteria crit = sess.createCriteria(Region.class);
    //
    // return crit.list();
    // }
    // });
    // }
    //
    // /**
    // * Retrieves the latest region that was created
    // *
    // * @return Region
    // */
    // @SuppressWarnings({ "unchecked", "rawtypes" })
    // public Region getLatestRegion() {
    // return (Region) txTemplate.execute(new TransactionCallback() {
    // @Override
    // public Object doInTransaction(TransactionStatus status) {
    // DetachedCriteria maxId = DetachedCriteria.forClass(Region.class)
    // .setProjection(Projections.max("id"));
    //
    // Session sess = getCurrentSession();
    // Criteria crit = sess.createCriteria(Region.class);
    // crit.add(Property.forName("id").eq(maxId));
    //
    // return crit.uniqueResult();
    // }
    // });
    // }
    //
    // /**
    // * Retrieves the list of region history reports for a given region report;
    // *
    // * @param report
    // * @return the list of RegionHistoryReports
    // */
    // @SuppressWarnings({ "unchecked" })
    // public List<RegionHistoryReport> getHistoryReports(final Integer
    // reportId) {
    // return txTemplate
    // .execute(new TransactionCallback<List<RegionHistoryReport>>() {
    // @Override
    // public List<RegionHistoryReport> doInTransaction(
    // TransactionStatus status) {
    // Session session = getSession();
    // Criteria crit = session
    // .createCriteria(RegionHistoryReport.class);
    // Criterion where = Restrictions.eq(
    // RegionHistoryReport.REGION_REPORT_ID, reportId);
    // crit.add(where);
    // return crit.list();
    // }
    //
    // });
    // }
    //
    // /**
    // *
    // * @param report
    // * @return
    // */
    // public int persist(final RegionHistoryReport report)
    // throws DataAccessLayerException {
    // try {
    // return txTemplate.execute(new TransactionCallback<Integer>() {
    // @Override
    // public Integer doInTransaction(TransactionStatus status) {
    // return (Integer) getCurrentSession().save(report);
    // }
    // });
    // } catch (TransactionException e) {
    // throw new DataAccessLayerException("Transaction failed", e);
    // }
    // }
    //
    // @SuppressWarnings("unchecked")
    // public List<Integer> getReportsWithoutHistory()
    // throws SQLException, PluginException {
    // // Get all region reports
    //
    // RegionReportsDao dao = new RegionReportsDao();
    // List<RegionReport> reports = dao.getAllRegionReports();
    //
    // List<RegionHistoryReport> historyReports = txTemplate
    // .execute(new TransactionCallback<List<RegionHistoryReport>>() {
    // @Override
    // public List<RegionHistoryReport> doInTransaction(
    // TransactionStatus status) {
    // Session session = getSession();
    // Criteria crit = session
    // .createCriteria(RegionHistoryReport.class);
    // return crit.list();
    // }
    // });
    //
    // List<Integer> reportsWithoutHistory = new ArrayList<>();
    //
    // for (RegionReport report : reports) {
    // reportsWithoutHistory.add(report.getId());
    // }
    //
    // for (RegionHistoryReport historyReport : historyReports) {
    // Integer id = historyReport.getRegionReportId();
    // reportsWithoutHistory.remove(id);
    // }
    //
    // return reportsWithoutHistory;
    // }

    public RegionConsensus getYesterdaysReport(final Integer regionId)
            throws SQLException, PluginException {

        long timeInMillis = System.currentTimeMillis();
        long millisInDay = 86400000L;

        timeInMillis = (timeInMillis - millisInDay);

        Calendar yesterday = Calendar
                .getInstance(EditedRegionsConstants.TIME_ZONE_UTC);
        yesterday.clear();
        yesterday.setTimeInMillis(timeInMillis);

        int year = yesterday.get(Calendar.YEAR);
        int month = yesterday.get(Calendar.MONTH);
        int date = yesterday.get(Calendar.DAY_OF_MONTH);

        yesterday.clear();
        yesterday.set(year, month, date);

        final Long start = yesterday.getTime().getTime();
        final Long end = start + millisInDay;
        return txTemplate.execute(new TransactionCallback<RegionConsensus>() {
            @Override
            public RegionConsensus doInTransaction(TransactionStatus status) {
                Session session = getSession();
                Criteria crit = session.createCriteria(RegionConsensus.class);
                Criterion where1 = Restrictions
                        .between(RegionConsensus.OBSERVATION_TIME, start, end);
                crit.add(where1);
                Criterion where2 = Restrictions.eq(RegionConsensus.REGION,
                        regionId);
                crit.add(where2);
                return (RegionConsensus) crit.uniqueResult();
            }
        });
    }

    public RegionConsensus getTodaysFinal(final Integer regionId)
            throws SQLException, PluginException {

        long timeInMillis = System.currentTimeMillis();
        long millisInDay = 86400000L;

        Calendar today = Calendar
                .getInstance(EditedRegionsConstants.TIME_ZONE_UTC);
        today.clear();
        today.setTimeInMillis(timeInMillis);

        int year = today.get(Calendar.YEAR);
        int month = today.get(Calendar.MONTH);
        int date = today.get(Calendar.DAY_OF_MONTH);

        today.clear();
        today.set(year, month, date);

        final Long start = today.getTime().getTime();
        final Long end = start + millisInDay;
        return txTemplate.execute(new TransactionCallback<RegionConsensus>() {
            @Override
            public RegionConsensus doInTransaction(TransactionStatus status) {
                Session session = getSession();
                Criteria crit = session.createCriteria(RegionConsensus.class);
                Criterion where1 = Restrictions
                        .between(RegionConsensus.OBSERVATION_TIME, start, end);
                crit.add(where1);
                Criterion where2 = Restrictions.eq(RegionConsensus.REGION,
                        regionId);
                crit.add(where2);
                return (RegionConsensus) crit.uniqueResult();
            }
        });

    }

}