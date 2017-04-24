package gov.noaa.nws.ncep.edex.plugin.editedregions.dao;

import com.raytheon.uf.common.dataplugin.PluginException;
import com.raytheon.uf.common.dataplugin.persist.IPersistable;
import com.raytheon.uf.common.datastorage.IDataStore;
import com.raytheon.uf.edex.database.plugin.PluginDao;

/**
 * Data Access Object (DAO) class to interact with swpc_event database table.
 * 
 * *
 * 
 * <pre>
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
public class EventsDao extends PluginDao {
    //
    // /**
    // * Creates a new EventsDao
    // *
    // * @param pluginName
    // * @throws PluginException
    // */
    public EventsDao() throws PluginException {
        super("editedregions");
    }

    //
    // /**
    // * Creates a new EventsDao
    // *
    // * @throws PluginException
    // */
    // public EventsDao() throws PluginException {
    // super(EditedEventsConstants.PLUGIN_NAME);
    // }
    //
    // /**
    // * Persist the event and return the event id
    // *
    // * @param event
    // * @return id
    // * @throws DataAccessLayerException
    // */
    // public Integer persist(final Event event) throws DataAccessLayerException
    // {
    // int id = 0;
    // try {
    // // Get a session and create a new criteria instance
    // id = txTemplate
    // .execute(new TransactionCallback<Integer>() {
    // @Override
    // public Integer doInTransaction(TransactionStatus status) {
    //
    // return (Integer) getCurrentSession().save(event);
    // }
    // });
    // } catch (TransactionException e) {
    // throw new DataAccessLayerException("Transaction failed", e);
    // }
    // return id;
    //
    //
    // }
    //
    // /**
    // * Retrieves list of events within given date range
    // *
    // * TODO - method might go away
    // *
    // * @return list of events
    // */
    // @SuppressWarnings({ "unchecked", "rawtypes" })
    // public List<Event> getEvents(final Calendar start, final Calendar end) {
    // return (List<Event>) txTemplate.execute(new TransactionCallback() {
    // @Override
    // public Object doInTransaction(TransactionStatus status) {
    // Session sess = getCurrentSession();
    // Criteria crit = sess.createCriteria(Event.class);
    // Criterion where1 = Restrictions.ge(Event.BEGIN_DATE, start);
    // crit.add(where1);
    // Criterion where2 = Restrictions.lt(Event.BEGIN_DATE, end);
    // crit.add(where2);
    // crit.addOrder(Order.asc(Event.BEGIN_DATE));
    //
    // return crit.list();
    // }
    // });
    // }
    //
    // /**
    // * Retrieves list of events based on the bin number
    // *
    // * @return list of events
    // */
    // @SuppressWarnings({ "unchecked", "rawtypes" })
    // public List<Event> getEventsByBin(final Integer binNumber,
    // final boolean notBinNumber,
    // final boolean sortAscending) {
    // return (List<Event>) txTemplate.execute(new TransactionCallback() {
    // @Override
    // public Object doInTransaction(TransactionStatus status) {
    // Session sess = getCurrentSession();
    // Criteria crit = sess.createCriteria(Event.class);
    // Criterion where1 = null;
    //
    // if (notBinNumber) {
    // where1 = Restrictions.ne(Event.BIN, getEventBinByBinNumber(binNumber));
    // } else {
    // where1 = Restrictions.eq(Event.BIN, getEventBinByBinNumber(binNumber));
    // }
    //
    // crit.add(where1);
    //
    // if (sortAscending) {
    // crit.addOrder(Order.asc(Event.BEGIN_DATE));
    // }
    //
    // return crit.list();
    // }
    // });
    // }
    //
    // /**
    // * Retrieves list of events that occur before the start
    // * date
    // *
    // * @return list of events
    // */
    // @SuppressWarnings({ "unchecked", "rawtypes" })
    // public List<Event> getEventsBeforeDate(final Calendar start,
    // final boolean includesStartDate,
    // final boolean sortAscending) {
    // return (List<Event>) txTemplate.execute(new TransactionCallback() {
    // @Override
    // public Object doInTransaction(TransactionStatus status) {
    // Session sess = getCurrentSession();
    // Criteria crit = sess.createCriteria(Event.class);
    // Criterion where1 = null;
    //
    // if (includesStartDate) {
    // where1 = Restrictions.le(Event.BEGIN_DATE, start);
    // } else {
    // where1 = Restrictions.lt(Event.BEGIN_DATE, start);
    // }
    // crit.add(where1);
    //
    // if (sortAscending) {
    // crit.addOrder(Order.asc(Event.BEGIN_DATE));
    // }
    //
    // return crit.list();
    // }
    // });
    // }
    //
    // /**
    // * Retrieves list of all events
    // *
    // * @return list of events
    // */
    // @SuppressWarnings({ "unchecked", "rawtypes" })
    // public List<Event> getAllEvents(final boolean sortAscending) {
    // return (List<Event>) txTemplate.execute(new TransactionCallback() {
    // @Override
    // public Object doInTransaction(TransactionStatus status) {
    // Session sess = getCurrentSession();
    // Criteria crit = sess.createCriteria(Event.class);
    //
    // if (sortAscending) {
    // crit.addOrder(Order.asc(Event.BEGIN_DATE));
    // }
    //
    // return crit.list();
    // }
    // });
    // }
    //
    // /**
    // * Retrieves list of events that occur on or after the start
    // * date
    // *
    // * @return list of events
    // */
    // @SuppressWarnings({ "unchecked", "rawtypes" })
    // public List<Event> getEventsAfterDate(final Calendar start,
    // final boolean includesStartDate,
    // final boolean sortAscending) {
    // return (List<Event>) txTemplate.execute(new TransactionCallback() {
    // @Override
    // public Object doInTransaction(TransactionStatus status) {
    // Session sess = getCurrentSession();
    // Criteria crit = sess.createCriteria(Event.class);
    // Criterion where1 = null;
    //
    // if (includesStartDate) {
    // where1 = Restrictions.ge(Event.BEGIN_DATE, start);
    // } else {
    // where1 = Restrictions.gt(Event.BEGIN_DATE, start);
    // }
    // crit.add(where1);
    //
    // if (sortAscending) {
    // crit.addOrder(Order.asc(Event.BEGIN_DATE));
    // }
    //
    // return crit.list();
    // }
    // });
    // }
    //
    // /**
    // * Retrieves list of events within given date range, bin number and age
    // *
    // * @return list of events
    // */
    // @SuppressWarnings({ "unchecked", "rawtypes" })
    // public List<Event> getEvents(final Calendar start,
    // final Calendar end,
    // final int bin,
    // final String age,
    // final boolean includesStartDate,
    // final boolean includesEndDate,
    // final boolean sortAscending,
    // final String sortByColumn) {
    // return (List<Event>) txTemplate.execute(new TransactionCallback() {
    // @Override
    // public Object doInTransaction(TransactionStatus status) {
    // Session sess = getCurrentSession();
    // Criteria crit = sess.createCriteria(Event.class,
    // "event").createAlias("event.bin", "bin").setFetchMode("bin",
    // FetchMode.JOIN);
    // Criterion where1 = null;
    // Criterion where2 = null;
    // Criterion where3 = null;
    // Criterion where4 = null;
    //
    // if (includesStartDate) {
    // where1 = Restrictions.ge("event." + Event.BEGIN_DATE, start);
    // } else {
    // where1 = Restrictions.gt("event." + Event.BEGIN_DATE, start);
    // }
    // crit.add(where1);
    //
    // if (includesEndDate) {
    // where2 = Restrictions.le("event." + Event.BEGIN_DATE, end);
    // } else {
    // where2 = Restrictions.lt("event." + Event.BEGIN_DATE, end);
    // }
    // crit.add(where2);
    //
    // where3 = Restrictions.eq("bin." + EventBin.BIN_NUMBER, bin);
    // crit.add(where3);
    //
    // where4 = Restrictions.eq("event." + Event.AGE, age);
    // crit.add(where4);
    //
    // String sortTable =
    // (EventBin.BEGIN_DATE_TIME.compareToIgnoreCase(sortByColumn)==0) ? "bin" :
    // "event";
    //
    // if (sortAscending) {
    // crit.addOrder(Order.asc(sortTable + "." + sortByColumn));
    // } else {
    // crit.addOrder(Order.desc(sortTable + "." + sortByColumn));
    // }
    //
    // return crit.list();
    // }
    // });
    // }
    //
    // /**
    // * Retrieves list of events within given date range, bin number and status
    // text
    // *
    // * @return list of events
    // */
    // @SuppressWarnings({ "unchecked", "rawtypes" })
    // public List<Event> getEvents(final Calendar start,
    // final Calendar end,
    // final int bin,
    // final boolean includesStartDate,
    // final boolean includesEndDate,
    // final boolean sortAscending,
    // final String sortByColumn,
    // final String statusText) {
    // return (List<Event>) txTemplate.execute(new TransactionCallback() {
    // @Override
    // public Object doInTransaction(TransactionStatus status) {
    // Session sess = getCurrentSession();
    // Criteria crit = sess.createCriteria(Event.class,
    // "event").createAlias("event.bin", "bin").setFetchMode("bin",
    // FetchMode.JOIN);
    // Criterion where1 = null;
    // Criterion where2 = null;
    // Criterion where3 = null;
    // Criterion where4 = null;
    //
    // if (includesStartDate) {
    // where1 = Restrictions.ge("event." + Event.BEGIN_DATE, start);
    // } else {
    // where1 = Restrictions.gt("event." + Event.BEGIN_DATE, start);
    // }
    // crit.add(where1);
    //
    //
    // if (includesEndDate) {
    // where2 = Restrictions.le("event." + Event.BEGIN_DATE, end);
    // } else {
    // where2 = Restrictions.lt("event." + Event.BEGIN_DATE, end);
    // }
    // crit.add(where2);
    //
    // where3 = Restrictions.eq("bin." + EventBin.BIN_NUMBER, bin);
    // crit.add(where3);
    //
    // where4 = Restrictions.eq("event." + Event.STATUS_TEXT, statusText);
    // crit.add(where4);
    //
    // String sortTable =
    // (EventBin.BEGIN_DATE_TIME.compareToIgnoreCase(sortByColumn)==0) ? "bin" :
    // "event";
    //
    // if (sortAscending) {
    // crit.addOrder(Order.asc(sortTable + "." + sortByColumn));
    // } else {
    // crit.addOrder(Order.desc(sortTable + "." + sortByColumn));
    // }
    //
    // return crit.list();
    // }
    // });
    // }
    //
    // /**
    // * Retrieves list of events within given date range and age
    // *
    // * @return list of events
    // */
    // @SuppressWarnings({ "unchecked", "rawtypes" })
    // public List<Event> getEvents(final Calendar start,
    // final Calendar end,
    // final String age,
    // final boolean includesStartDate,
    // final boolean includesEndDate,
    // final boolean sortAscending,
    // final String sortByColumn) {
    // return (List<Event>) txTemplate.execute(new TransactionCallback() {
    // @Override
    // public Object doInTransaction(TransactionStatus status) {
    // Session sess = getCurrentSession();
    // Criteria crit = sess.createCriteria(Event.class,
    // "event").createAlias("event.bin", "bin").setFetchMode("bin",
    // FetchMode.JOIN);
    // Criterion where1 = null;
    // Criterion where2 = null;
    // Criterion where3 = null;
    //
    // if (includesStartDate) {
    // where1 = Restrictions.ge("event." + Event.BEGIN_DATE, start);
    // } else {
    // where1 = Restrictions.gt("event." + Event.BEGIN_DATE, start);
    // }
    // crit.add(where1);
    //
    //
    // if (includesEndDate) {
    // where2 = Restrictions.le("event." + Event.BEGIN_DATE, end);
    // } else {
    // where2 = Restrictions.lt("event." + Event.BEGIN_DATE, end);
    // }
    // crit.add(where2);
    //
    // where3 = Restrictions.eq("event." + Event.AGE, age);
    // crit.add(where3);
    //
    // String sortTable =
    // (EventBin.BEGIN_DATE_TIME.compareToIgnoreCase(sortByColumn)==0) ? "bin" :
    // "event";
    //
    // if (sortAscending) {
    // crit.addOrder(Order.asc(sortTable + "." + sortByColumn));
    // } else {
    // crit.addOrder(Order.desc(sortTable + "." + sortByColumn));
    // }
    //
    // return crit.list();
    // }
    // });
    // }
    //
    // /**
    // * Retrieves list of events within given date range and status text
    // *
    // * @return list of events
    // */
    // @SuppressWarnings({ "unchecked", "rawtypes" })
    // public List<Event> getEvents(final Calendar start,
    // final Calendar end,
    // final boolean includesStartDate,
    // final boolean includesEndDate,
    // final boolean sortAscending,
    // final String sortByColumn,
    // final String statusText) {
    // return (List<Event>) txTemplate.execute(new TransactionCallback() {
    // @Override
    // public Object doInTransaction(TransactionStatus status) {
    // Session sess = getCurrentSession();
    // Criteria crit = sess.createCriteria(Event.class,
    // "event").createAlias("event.bin", "bin").setFetchMode("bin",
    // FetchMode.JOIN);
    // Criterion where1 = null;
    // Criterion where2 = null;
    // Criterion where3 = null;
    //
    // if (includesStartDate) {
    // where1 = Restrictions.ge("event." + Event.BEGIN_DATE, start);
    // } else {
    // where1 = Restrictions.gt("event." + Event.BEGIN_DATE, start);
    // }
    // crit.add(where1);
    //
    //
    // if (includesEndDate) {
    // where2 = Restrictions.le("event." + Event.BEGIN_DATE, end);
    // } else {
    // where2 = Restrictions.lt("event." + Event.BEGIN_DATE, end);
    // }
    // crit.add(where2);
    //
    // where3 = Restrictions.eq("event." + Event.STATUS_TEXT, statusText);
    // crit.add(where3);
    //
    // String sortTable =
    // (EventBin.BEGIN_DATE_TIME.compareToIgnoreCase(sortByColumn)==0) ? "bin" :
    // "event";
    //
    // if (sortAscending) {
    // crit.addOrder(Order.asc(sortTable + "." + sortByColumn));
    // } else {
    // crit.addOrder(Order.desc(sortTable + "." + sortByColumn));
    // }
    //
    // return crit.list();
    // }
    // });
    // }
    //
    // /**
    // * Retrieves list of events within given date range and status text1 OR
    // status text2
    // *
    // * @return list of events
    // */
    // @SuppressWarnings({ "unchecked", "rawtypes" })
    // public List<Event> getEvents(final Calendar start,
    // final Calendar end,
    // final boolean includesStartDate,
    // final boolean includesEndDate,
    // final boolean sortAscending,
    // final String sortByColumn,
    // final String statusText1,
    // final String statusText2) {
    // return (List<Event>) txTemplate.execute(new TransactionCallback() {
    // @Override
    // public Object doInTransaction(TransactionStatus status) {
    // Session sess = getCurrentSession();
    // Criteria crit = sess.createCriteria(Event.class,
    // "event").createAlias("event.bin", "bin").setFetchMode("bin",
    // FetchMode.JOIN);
    // Criterion where1 = null;
    // Criterion where2 = null;
    // Criterion where3 = null;
    // Criterion where4 = null;
    //
    // if (includesStartDate) {
    // where1 = Restrictions.ge("event." + Event.BEGIN_DATE, start);
    // } else {
    // where1 = Restrictions.gt("event." + Event.BEGIN_DATE, start);
    // }
    // crit.add(where1);
    //
    //
    // if (includesEndDate) {
    // where2 = Restrictions.le("event." + Event.BEGIN_DATE, end);
    // } else {
    // where2 = Restrictions.lt("event." + Event.BEGIN_DATE, end);
    // }
    // crit.add(where2);
    //
    // where3 = Restrictions.eq("event." + Event.STATUS_TEXT, statusText1);
    // where4 = Restrictions.eq("event." + Event.STATUS_TEXT, statusText2);
    // crit.add(Restrictions.disjunction().add(where3).add(where4)); // where3
    // OR where4
    //
    // String sortTable =
    // (EventBin.BEGIN_DATE_TIME.compareToIgnoreCase(sortByColumn)==0) ? "bin" :
    // "event";
    //
    // if (sortAscending) {
    // crit.addOrder(Order.asc(sortTable + "." + sortByColumn));
    // } else {
    // crit.addOrder(Order.desc(sortTable + "." + sortByColumn));
    // }
    //
    // return crit.list();
    // }
    // });
    // }
    //
    //
    // /**
    // * Retrieves list of events within given date range and bin number
    // *
    // * @return list of events
    // */
    // @SuppressWarnings({ "unchecked", "rawtypes" })
    // public List<Event> getEvents(final Calendar start,
    // final Calendar end,
    // final int bin,
    // final boolean includesStartDate,
    // final boolean includesEndDate,
    // final boolean sortAscending,
    // final String sortByColumn) {
    // return (List<Event>) txTemplate.execute(new TransactionCallback() {
    // @Override
    // public Object doInTransaction(TransactionStatus status) {
    // Session sess = getCurrentSession();
    // Criteria crit = sess.createCriteria(Event.class,
    // "event").createAlias("event.bin", "bin").setFetchMode("bin",
    // FetchMode.JOIN);
    // List<Event> events = null;
    // Criterion where1 = null;
    // Criterion where2 = null;
    // Criterion where3 = null;
    //
    // if (includesStartDate) {
    // where1 = Restrictions.ge("event." + Event.BEGIN_DATE, start);
    // } else {
    // where1 = Restrictions.gt("event." + Event.BEGIN_DATE, start);
    // }
    // crit.add(where1);
    //
    //
    // if (includesEndDate) {
    // where2 = Restrictions.le("event." + Event.BEGIN_DATE, end);
    // } else {
    // where2 = Restrictions.lt("event." + Event.BEGIN_DATE, end);
    // }
    // crit.add(where2);
    //
    // where3 = Restrictions.eq("bin." + EventBin.BIN_NUMBER, bin);
    // crit.add(where3);
    //
    // String sortTable =
    // (EventBin.BEGIN_DATE_TIME.compareToIgnoreCase(sortByColumn)==0) ? "bin" :
    // "event";
    //
    // if (sortAscending) {
    // crit.addOrder(Order.asc(sortTable + "." + sortByColumn));
    // } else {
    // crit.addOrder(Order.desc(sortTable + "." + sortByColumn));
    // }
    //
    // try {
    // events = crit.list();
    //
    // } catch (HibernateException e) {
    // e.printStackTrace();
    // }
    //
    // return events;
    // }
    // });
    // }
    //
    // /**
    // * Retrieves list of events within given date range
    // *
    // * @return list of events
    // */
    // @SuppressWarnings({ "unchecked", "rawtypes" })
    // public List<Event> getEvents(final Calendar start,
    // final Calendar end,
    // final boolean includesStartDate,
    // final boolean includesEndDate,
    // final boolean sortAscending,
    // final String sortByColumn) {
    // return (List<Event>) txTemplate.execute(new TransactionCallback() {
    // @Override
    // public Object doInTransaction(TransactionStatus status) {
    // Session sess = getCurrentSession();
    // Criteria crit = sess.createCriteria(Event.class,
    // "event").createAlias("event.bin", "bin").setFetchMode("bin",
    // FetchMode.JOIN);
    //
    // List<Event> events = null;
    // Criterion where1 = null;
    // Criterion where2 = null;
    //
    // if (includesStartDate) {
    // where1 = Restrictions.ge("event." + Event.BEGIN_DATE, start);
    // } else {
    // where1 = Restrictions.gt("event." + Event.BEGIN_DATE, start);
    // }
    // crit.add(where1);
    //
    //
    // if (includesEndDate) {
    // where2 = Restrictions.le("event." + Event.BEGIN_DATE, end);
    // } else {
    // where2 = Restrictions.lt("event." + Event.BEGIN_DATE, end);
    // }
    // crit.add(where2);
    //
    // String sortTable =
    // (EventBin.BEGIN_DATE_TIME.compareToIgnoreCase(sortByColumn)==0) ? "bin" :
    // "event";
    //
    // if (sortAscending) {
    // crit.addOrder(Order.asc(sortTable + "." + sortByColumn));
    // } else {
    // crit.addOrder(Order.desc(sortTable + "." + sortByColumn));
    // }
    //
    // try {
    // events = crit.list();
    //
    // } catch (HibernateException e) {
    //
    // e.printStackTrace();
    // }
    //
    // return events;
    // }
    // });
    // }
    //
    // /**
    // * Retrieves data from events table. <br>
    // *
    // * @param bin
    // * bin
    // * @param statusText
    // * statusText
    // * @param age
    // * age
    // * @param sortBy
    // * sortBy
    // * @param start
    // * start date
    // * @param end
    // * end date
    // * @return List of data map containing requested data
    // * @throws Exception
    // */
    // public List<Map<String, Object>> getEvents(final Integer bin,
    // final String status, final String age, final String sort,
    // final Date start, final Date end) throws Exception {
    //
    // List<Map<String, Object>> resultMaps = new ArrayList<Map<String,
    // Object>>();
    // StringBuffer sql = new StringBuffer();
    // String[] fieldNames = { "bin", "beginq", "begindate", "maxq",
    // "maxtime", "endq", "endtime", "observatory", "quality", "type",
    // "location", "frequency", "particulars1", "particulars2",
    // "particulars3", "particulars4", "particulars5", "particulars6",
    // "particulars7", "particulars8", "particulars9",
    // "particulars10", "region", "age", "statustext", "codedtype" };
    //
    // if ("bin date".equals(sort)) {
    // List<Integer> bins = new ArrayList<Integer>();
    //
    // if (bin != null) {
    // bins.add(bin);
    // } else {
    //
    // // get distinct bins from events table sorted by beginddate asc
    // sql.append(" SELECT DISTINCT " + Event.BIN + ", "
    // + Event.BEGIN_DATE);
    // sql.append(" FROM events WHERE bin IS NOT NULL ");
    //
    // if (status != null) {
    // sql.append(" AND statustext = '" + status + "' ");
    // }
    // if (age != null) {
    // sql.append(" AND age = '" + age + "' ");
    // }
    // if (start != null && end != null) {
    // sql.append(" AND begindate >= '").append(start)
    // .append("' AND begindate <= '").append(end)
    // .append("'");
    // }
    //
    // sql.append(" ORDER BY begindate ASC ");
    //
    // Object[] results = executeSQLQuery(sql.toString());
    //
    // if (results.length == 0) {
    // return new ArrayList<Map<String, Object>>();
    // }
    //
    // for (Object obj : results) {
    // if (obj instanceof Object[] == false) {
    // obj = new Object[] { obj };
    // }
    // Object[] objs = (Object[]) obj;
    // if (!bins.contains(objs[0])) {
    // bins.add((Integer) objs[0]);
    // }
    // }
    //
    // }
    //
    // for (int k = 0; k < bins.size(); k++) {
    //
    // sql = new StringBuffer();
    // sql.append(" SELECT bin, beginq, begindate, maxq, maxtime, endq, endtime,
    // observatory, quality, type, location, frequency, ");
    // sql.append(" particulars1, particulars2, particulars3, particulars4,
    // particulars5, particulars6, particulars7, ");
    // sql.append(" particulars8, particulars9, particulars10, region, age,
    // statustext, codedtype ");
    // sql.append(" FROM events ");
    //
    // sql.append(" WHERE bin = ").append(bins.get(k));
    //
    // if (status != null) {
    // sql.append(" AND statustext = '" + status + "' ");
    // }
    // if (age != null) {
    // sql.append(" AND age = '" + age + "' ");
    // }
    // if (start != null && end != null) {
    // sql.append(" AND begindate >= '").append(start)
    // .append("' AND begindate <= '").append(end)
    // .append("'");
    // }
    //
    // sql.append(" GROUP BY bin, beginq, begindate, maxq, maxtime, endq,
    // endtime, observatory, quality, type, location, frequency, ");
    // sql.append(" particulars1, particulars2, particulars3, particulars4,
    // particulars5, particulars6, particulars7, ");
    // sql.append(" particulars8, particulars9, particulars10, region, age,
    // statustext, codedtype ");
    //
    // sql.append(" ORDER BY (CASE when type='XRA' then 1 ");
    // sql.append(" when type='FLA' then 2 ");
    // sql.append(" when type='CME' then 3 ");
    // sql.append(" when type='UCMEO' then 4 ");
    // sql.append(" when type='BSL' then 5 ");
    // sql.append(" when type='DSF' then 6 ");
    // sql.append(" when type='UFILA' then 7 ");
    // sql.append(" when type='EPL' then 8 ");
    // sql.append(" when type='LPS' then 9 ");
    // sql.append(" when type='SPY' then 10 ");
    // sql.append(" when type='DSD' then 11 ");
    // sql.append(" when type='RBR' then 12 ");
    // sql.append(" when type='RNS' then 13 ");
    // sql.append(" when type='RSP' then 14 ");
    // sql.append(" END ");
    // sql.append(" ), codedtype, begindate ASC ");
    //
    // Object[] results = executeSQLQuery(sql.toString());
    //
    // if (results.length == 0) {
    // continue;
    // }
    //
    // for (Object obj : results) {
    // if (obj instanceof Object[] == false) {
    // obj = new Object[] { obj };
    // }
    // Object[] objs = (Object[]) obj;
    // if (objs.length != fieldNames.length) {
    // throw new Exception(
    // "Column count returned does not match expected column count");
    // }
    // Map<String, Object> resultMap = new HashMap<String, Object>(
    // objs.length * 2);
    // for (int i = 0; i < fieldNames.length; ++i) {
    // resultMap.put(fieldNames[i], objs[i]);
    // }
    // resultMaps.add(resultMap);
    // }
    //
    // }
    // }
    //
    // else {
    // sql.append(" SELECT bin, beginq, begindate, maxq, maxtime, endq, endtime,
    // observatory, quality, type, location, frequency, ");
    // sql.append(" particulars1, particulars2, particulars3, particulars4,
    // particulars5, particulars6, particulars7, ");
    // sql.append(" particulars8, particulars9, particulars10, region, age,
    // statustext, codedtype ");
    // sql.append(" FROM events ");
    // sql.append(" WHERE bin IS NOT NULL ");
    //
    // if (bin != null) {
    // sql.append(" AND bin = " + bin);
    // }
    //
    // if (status != null) {
    // sql.append(" AND statustext = '" + status + "' ");
    // }
    // if (age != null) {
    // sql.append(" AND age = '" + age + "' ");
    // }
    // if (start != null && end != null) {
    // sql.append(" AND begindate >= '").append(start)
    // .append("' AND begindate <= '").append(end).append("'");
    // }
    //
    // if (sort != null) {
    // sql.append(" ORDER BY " + sort + " ASC ");
    // }
    //
    // Object[] results = executeSQLQuery(sql.toString());
    //
    // if (results.length == 0) {
    // return resultMaps;
    // }
    //
    // for (Object obj : results) {
    // if (obj instanceof Object[] == false) {
    // obj = new Object[] { obj };
    // }
    // Object[] objs = (Object[]) obj;
    // if (objs.length != fieldNames.length) {
    // throw new Exception(
    // "Column count returned does not match expected column count");
    // }
    // Map<String, Object> resultMap = new HashMap<String, Object>(
    // objs.length * 2);
    // for (int i = 0; i < fieldNames.length; ++i) {
    // resultMap.put(fieldNames[i], objs[i]);
    // }
    // resultMaps.add(resultMap);
    // }
    // }
    //
    // return resultMaps;
    //
    // }
    //
    @Override
    protected IDataStore populateDataStore(IDataStore dataStore,
            IPersistable obj) throws Exception {
        // TODO Auto-generated method stub
        return null;
    }
    //
    // // TODO - add methods to perform queries
    //
    // /**
    // * Query to find existing event(s) or duplicate event(s)
    // *
    // * @param beginDate
    // * @param observatory
    // * @param obsId
    // * @param codedType
    // * @return List<Event>
    // */
    // @SuppressWarnings({ "unchecked", "rawtypes" })
    // public List<Event> findDuplicateEvents(final Calendar beginDate,
    // final String observatory, final int obsId, final int codedType) {
    //
    // return (List<Event>) txTemplate.execute(new TransactionCallback() {
    // @Override
    // public Object doInTransaction(TransactionStatus status) {
    // Session sess = getCurrentSession();
    // Criteria crit = sess.createCriteria(Event.class);
    // Criterion where1 = Restrictions.eq(Event.BEGIN_DATE, beginDate);
    // crit.add(where1);
    // Criterion where2 = Restrictions.eq(Event.OBSERVATORY,
    // observatory);
    // crit.add(where2);
    // Criterion where3 = Restrictions.eq(Event.OBS_ID, obsId);
    // crit.add(where3);
    // Criterion where4 = Restrictions.eq(Event.CODED_TYPE, codedType);
    // crit.add(where4);
    //
    // List<Event> events = null;
    //
    // try {
    //
    // events = crit.list();
    //
    // } catch (HibernateException e) {
    // e.printStackTrace();
    // }
    //
    // return events;
    // }
    // });
    // }
    //
    // /**
    // * Retrieves list of new events except Radio Noise Storms(Type=RNS) and
    // * Disappearing Filaments (Type=DSF).
    // *
    // * @param start
    // * @param end
    // * @return List<Event> The list of events
    // */
    // @SuppressWarnings({ "unchecked", "rawtypes" })
    // public List<Event> getNewEventsMinusRNSandDSF(final Calendar start) {
    // return (List<Event>) txTemplate.execute(new TransactionCallback() {
    // @Override
    // public Object doInTransaction(TransactionStatus status) {
    // List<Event> events = null;
    //
    // Session sess = getCurrentSession();
    // Criteria crit = sess.createCriteria(Event.class);
    //
    // Criterion where1 = Restrictions.ge(Event.BEGIN_DATE, start);
    // crit.add(where1);
    //
    // Criterion where2 = Restrictions.ne(Event.TYPE,
    // EditedEventsConstants.EventType.RNS.getType());
    // crit.add(where2);
    //
    // Criterion where3 = Restrictions.ne(Event.TYPE,
    // EditedEventsConstants.EventType.DSF.getType());
    // crit.add(where3);
    //
    // Criterion where4 = Restrictions.isNull(Event.BIN);
    // crit.add(where4);
    // //Criterion where5 = Restrictions.eq(Event.BIN, 0);
    //
    // //crit.add(Restrictions.disjunction().add(where4).add(where5)); // where4
    // // OR
    // // where5
    // crit.addOrder(Order.asc(Event.BEGIN_DATE));
    // crit.addOrder(Order.asc(Event.CODED_TYPE));
    //
    // events = crit.list();
    //
    // return events;
    // }
    // });
    // }
    //
    // /**
    // * Retrieves list of new events of type: Radio Noise Storms(Type=RNS)
    // *
    // * @param start
    // * @return List<Event> The list of events
    // */
    // @SuppressWarnings({ "unchecked", "rawtypes" })
    // public List<Event> getNewRNSEvents(final Calendar start) {
    // return (List<Event>) txTemplate.execute(new TransactionCallback() {
    // @Override
    // public Object doInTransaction(TransactionStatus status) {
    // Session sess = getCurrentSession();
    // Criteria crit = sess.createCriteria(Event.class);
    // Criterion where1 = Restrictions.ge(Event.BEGIN_DATE, start);
    // crit.add(where1);
    // Criterion where2 = Restrictions.eq(Event.TYPE,
    // EditedEventsConstants.EventType.RNS.getType());
    // crit.add(where2);
    //
    // Criterion where3 = Restrictions.isNull(Event.BIN);
    // crit.add(where3);
    // //Criterion where4 = Restrictions.eq(Event.BIN, 0);
    //
    // //crit.add(Restrictions.disjunction().add(where3).add(where4)); // where3
    // // OR
    // // where4
    //
    // crit.addOrder(Order.asc(Event.BEGIN_DATE));
    //
    // return crit.list();
    // }
    // });
    // }
    //
    // /**
    // * Retrieves list of new events of type: Disappearing Filaments
    // (Type=DSF).
    // *
    // * @param start
    // * @return List<Event> The list of events
    // */
    // @SuppressWarnings({ "unchecked", "rawtypes" })
    // public List<Event> getNewDSFEvents(final Calendar start) {
    // return (List<Event>) txTemplate.execute(new TransactionCallback() {
    // @Override
    // public Object doInTransaction(TransactionStatus status) {
    // Session sess = getCurrentSession();
    // Criteria crit = sess.createCriteria(Event.class);
    // Criterion where1 = Restrictions.ge(Event.BEGIN_DATE, start);
    // crit.add(where1);
    // Criterion where2 = Restrictions.eq(Event.TYPE,
    // EditedEventsConstants.EventType.DSF.getType());
    // crit.add(where2);
    // Criterion where3 = Restrictions.eq(Event.CODED_TYPE, 21);
    // crit.add(where3);
    //
    // Criterion where4 = Restrictions.isNull(Event.BIN);
    // crit.add(where4);
    // //Criterion where5 = Restrictions.eq(Event.BIN, 0);
    //
    // //crit.add(Restrictions.disjunction().add(where4).add(where5)); // where4
    // // OR
    // // where5
    //
    // crit.addOrder(Order.asc(Event.BEGIN_DATE));
    //
    // return crit.list();
    // }
    // });
    // }
    //
    // /**
    // * Query to find event(s) of this type in this bin
    // *
    // * @param beginDate
    // * @return List<Event>
    // */
    // @SuppressWarnings({ "unchecked", "rawtypes" })
    // public List<Event> findContenders(final int reportType,
    // final int binNumber,
    // final int statusCode,
    // final Calendar beginDTTM) {
    //
    // return (List<Event>) txTemplate.execute(new TransactionCallback() {
    // @Override
    // public Object doInTransaction(TransactionStatus status) {
    //
    // logger.info(" EDITEDEVENTS.EventsDao.findContenders... ");
    //
    // List<Event> events = null;
    // try {
    //
    // Session sess = getCurrentSession();
    // Criteria crit = sess.createCriteria(Event.class);
    // Criterion where1 = Restrictions.and(
    // Restrictions.ge(Event.BEGIN_DATE, beginDTTM),
    // Restrictions.eq(Event.BIN, getEventBinByBinNumber(binNumber)));
    // crit.add(where1);
    // Criterion where2 = Restrictions.eq(Event.CODED_TYPE, reportType);
    // crit.add(where2);
    // Criterion where3 = Restrictions.eq(Event.STATUS_CODE, statusCode);
    // crit.add(where3);
    //
    // events = crit.list();
    //
    // } catch (Exception e) {
    //
    // logger.info(" Error occurred
    // EDITEDEVENTS.EventsDao.getEventsForBestSelection... ");
    // e.printStackTrace();
    // }
    // return events;
    // }
    // });
    // }
    //
    // /**
    // * Query to search for reports
    // *
    // * @param beginDate
    // * @return List<Event>
    // */
    // @SuppressWarnings({ "unchecked", "rawtypes" })
    // public List<Event> searchReports(final int reportType,
    // final int binNumber,
    // final int statusCode,
    // final Calendar beginDTTM) {
    //
    // return (List<Event>) txTemplate.execute(new TransactionCallback() {
    // @Override
    // public Object doInTransaction(TransactionStatus status) {
    //
    // logger.info(" EDITEDEVENTS.EventsDao.findContenders... ");
    //
    // List<Event> events = null;
    // try {
    //
    // Session sess = getCurrentSession();
    // Criteria crit = sess.createCriteria(Event.class);
    // Criterion where1 = Restrictions.and(
    // Restrictions.ge(Event.BEGIN_DATE, beginDTTM),
    // Restrictions.eq(Event.BIN, getEventBinByBinNumber(binNumber)));
    // crit.add(where1);
    // Criterion where2 = Restrictions.eq(Event.CODED_TYPE, reportType);
    // crit.add(where2);
    // Criterion where3 = Restrictions.ge(Event.STATUS_CODE, statusCode);
    // crit.add(where3);
    //
    // events = crit.list();
    //
    // } catch (Exception e) {
    //
    // logger.info(" Error occurred
    // EDITEDEVENTS.EventsDao.getEventsForBestSelection... ");
    // e.printStackTrace();
    // }
    // return events;
    // }
    // });
    // }
    //
    // /**
    // * Query to check for best of group status
    // *
    // * @param beginDate
    // * @return List<Event>
    // */
    // @SuppressWarnings({ "unchecked", "rawtypes" })
    // public List<Event> checkBestOfGroupStatus(final int reportType,
    // final int binNumber,
    // final int statusCode,
    // final Calendar beginDTTM) {
    //
    // return (List<Event>) txTemplate.execute(new TransactionCallback() {
    // @Override
    // public Object doInTransaction(TransactionStatus status) {
    //
    // logger.info(" EDITEDEVENTS.EventsDao.findContenders... ");
    //
    // List<Event> events = null;
    // try {
    // Session sess = getCurrentSession();
    // Criteria crit = sess.createCriteria(Event.class);
    // Criterion where1 = Restrictions.and(
    // Restrictions.ge(Event.BEGIN_DATE, beginDTTM),
    // Restrictions.eq(Event.BIN, getEventBinByBinNumber(binNumber)));
    // crit.add(where1);
    // // TODO -complete
    // Criterion where3 = Restrictions.eq(Event.CODED_TYPE, reportType);
    // crit.add(where3);
    // Criterion where4 = Restrictions.eq(Event.STATUS_CODE, statusCode);
    // crit.add(where4);
    //
    // events = crit.list();
    //
    // } catch (Exception e) {
    //
    // logger.info(" Error occurred
    // EDITEDEVENTS.EventsDao.getEventsForBestSelection... ");
    // e.printStackTrace();
    // }
    // return events;
    // }
    // });
    // }
    //
    // /**
    // * Query to find event(s) that is going to be used to perform selection of
    // * the best event in each bin for process events functionality
    // *
    // * @param beginDate
    // * @return List<Event>
    // */
    // @SuppressWarnings({ "unchecked", "rawtypes" })
    // public List<Event> getEventsForBestSelectionForProcessEvents(final
    // Calendar beginDate) {
    //
    // return (List<Event>) txTemplate.execute(new TransactionCallback() {
    // @Override
    // public Object doInTransaction(TransactionStatus status) {
    //
    // logger.info("
    // EDITEDEVENTS.EventsDao.getEventsForBestSelectionForProcessEvents... ");
    //
    // List<Event> events = null;
    // try {
    // Session sess = getCurrentSession();
    // Criteria crit = sess.createCriteria(Event.class);
    // Criterion where1 = Restrictions.ge(Event.BEGIN_DATE, beginDate);
    // crit.add(where1);
    // Criterion where2 = Restrictions.eq(Event.STATUS_CODE, 0);
    // crit.add(where2);
    //
    // events = crit.list();
    //
    // } catch (Exception e) {
    //
    // logger.info(" Error occurred
    // EDITEDEVENTS.EventsDao.getEventsForBestSelectionForProcessEvents... ");
    // e.printStackTrace();
    // }
    // return events;
    // }
    // });
    // }
    //
    // /**
    // * Query to find event(s) that is going to be used to perform selection of
    // * the best event in a bin for rebin event functionality
    // *
    // * @param eventID
    // * @return List<Event>
    // */
    // @SuppressWarnings({ "unchecked", "rawtypes" })
    // public List<Event> getEventsForBestSelectionForRebinEvent(final Integer
    // eventID) {
    //
    // return (List<Event>) txTemplate.execute(new TransactionCallback() {
    // @Override
    // public Object doInTransaction(TransactionStatus status) {
    //
    // logger.info("
    // EDITEDEVENTS.EventsDao.getEventsForBestSelectionForRebinEvent... ");
    //
    // List<Event> events = null;
    // try {
    // Session sess = getCurrentSession();
    // Criteria crit = sess.createCriteria(Event.class);
    // Criterion where1 = Restrictions.eq(Event.ID, eventID);
    // crit.add(where1);
    //
    // events = crit.list();
    //
    // } catch (Exception e) {
    //
    // logger.info(" Error occurred
    // EDITEDEVENTS.EventsDao.getEventsForBestSelectionForRebinEvent... ");
    // e.printStackTrace();
    // }
    // return events;
    // }
    // });
    // }
    //
    // /**
    // * Query to find event(s) that is going to be used to perform selection of
    // * the best event in each bin
    // *
    // * @param beginDate
    // * @param bin
    // * @param codedType
    // * @return List<Event>
    // */
    // @SuppressWarnings({ "unchecked", "rawtypes" })
    // public List<Event> getEventsForBestSelectionForRevisitOldBin(final
    // Calendar beginDate, final EventBin bin, final Integer codedType) {
    //
    // return (List<Event>) txTemplate.execute(new TransactionCallback() {
    // @Override
    // public Object doInTransaction(TransactionStatus status) {
    //
    // logger.info(" EDITEDEVENTS.EventsDao.getEventsForBestSelection... ");
    //
    // List<Event> events = null;
    // try {
    // Session sess = getCurrentSession();
    // Criteria crit = sess.createCriteria(Event.class);
    // Criterion where1 = Restrictions.ge(Event.BEGIN_DATE, beginDate);
    // crit.add(where1);
    // Criterion where2 = Restrictions.eq(Event.BIN, bin);
    // crit.add(where2);
    // Criterion where3 = Restrictions.eq(Event.CODED_TYPE, codedType);
    // crit.add(where3);
    // Criterion where4 = Restrictions.gt(Event.STATUS_CODE, 2);
    // crit.add(where4);
    //
    // events = crit.list();
    //
    // } catch (Exception e) {
    //
    // logger.info(" Error occurred
    // EDITEDEVENTS.EventsDao.getEventsForBestSelection... ");
    // e.printStackTrace();
    // }
    // return events;
    // }
    // });
    // }
    //
    // /**
    // * Query to check if there is a best event of the same type in the same
    // bin
    // *
    // * @param beginDate
    // * @param bin
    // * @param eventID
    // * @return List<Event>
    // */
    // @SuppressWarnings({ "unchecked", "rawtypes" })
    // public List<Event> getBestEvents(final Calendar beginDate,
    // final EventBin bin, final Integer codedType, final int eventID) {
    //
    // return (List<Event>) txTemplate.execute(new TransactionCallback() {
    // @Override
    // public Object doInTransaction(TransactionStatus status) {
    // logger.info(" EDITEDEVENTS.EventsDao.getBestEvents... ");
    //
    // List<Event> events = null;
    // try {
    // Session sess = getCurrentSession();
    // Criteria crit = sess.createCriteria(Event.class);
    // Criterion where1 = Restrictions.ge(Event.BEGIN_DATE, beginDate);
    // crit.add(where1);
    // Criterion where2 = Restrictions.eq(Event.BIN, bin);
    // crit.add(where2);
    // Criterion where3 = Restrictions.eq(Event.CODED_TYPE, codedType);
    // crit.add(where3);
    // Criterion where4 = Restrictions.ge(Event.STATUS_CODE, 3);
    // crit.add(where4);
    // Criterion where5 = Restrictions.ne(Event.ID, eventID);
    // crit.add(where5);
    // events = crit.list();
    //
    // } catch (Exception e) {
    //
    // logger.info(" Error occurred EDITEDEVENTS.EventsDao.getBestEvents... ");
    // e.printStackTrace();
    // }
    // return events;
    // }
    // });
    // }
    //
    // /**
    // * Query to get list of events given beginDate and binNumber
    // *
    // * @param beginDate
    // * @param binNumber
    // * @return List<Event>
    // */
    // @SuppressWarnings("unchecked")
    // public List<Event> getEvents(Calendar beginDate, Integer binNumber)
    // throws DataAccessLayerException {
    //
    //
    // List<String> fields = new ArrayList<String>();
    // List<Object> values = new ArrayList<Object>();
    //
    // // TODO check to confirm if this will work
    // fields.add(Event.BEGIN_DATE);
    // values.add(beginDate);
    //
    // fields.add(Event.BIN);
    // values.add(getEventBinByBinNumber(binNumber));
    //
    // return (List<Event>) queryByCriteria(fields, values);
    // }
    //
    // /**
    // * Returns the EventBin object associated with a given binNumber
    // *
    // * @param binNumber
    // * @return EventBin
    // */
    // public EventBin getEventBinByBinNumber(Integer binNumber) {
    //
    // EventBinDao eventBinDao = new EventBinDao();
    // return eventBinDao.queryByBinNumber(binNumber);
    // }
    //
    // /**
    // * Query used by the UpgradeEventCommand to check if there is a
    // * best event of the same type in the same bin
    // *
    // * @param beginDate
    // * @param bin
    // * @param eventID
    // * @return List<Event>
    // */
    // @SuppressWarnings({ "unchecked", "rawtypes" })
    // public List<Event> getEventsForUpgradeCommand(final Calendar beginDate,
    // final EventBin bin, final Integer codedType, final int eventID) {
    //
    // return (List<Event>) txTemplate.execute(new TransactionCallback() {
    // @Override
    // public Object doInTransaction(TransactionStatus status) {
    // logger.info(" EDITEDEVENTS.EventsDao.getEventsForUpgradeCommand... ");
    //
    // List<Event> events = null;
    // try {
    // Session sess = getCurrentSession();
    // Criteria crit = sess.createCriteria(Event.class);
    // Criterion where1 = Restrictions.ge(Event.BEGIN_DATE, beginDate);
    // crit.add(where1);
    // Criterion where2 = Restrictions.eq(Event.BIN, bin);
    // crit.add(where2);
    // Criterion where3 = Restrictions.eq(Event.CODED_TYPE, codedType);
    // crit.add(where3);
    // Criterion where4 = Restrictions.ge(Event.STATUS_CODE, 2);
    // crit.add(where4);
    // Criterion where5 = Restrictions.ne(Event.ID, eventID);
    // crit.add(where5);
    // events = crit.list();
    //
    // } catch (Exception e) {
    //
    // logger.info(" Error occurred
    // EDITEDEVENTS.EventsDao.getEventsForUpgradeCommand... ");
    // e.printStackTrace();
    // }
    // return events;
    // }
    // });
    // }
    //
    // /**
    // * Method to set events' age to 'OLD' and changeflag to 0
    // *
    // * @param beginDttm
    // *
    // * @return numRecordsUpdated
    // *
    // * @throws SQLException
    // */
    // public int setEventsAgeToOld(final long beginDttm) throws SQLException {
    //
    // PreparedStatement ps = null;
    // int numRecordsUpdated = 0;
    //
    // Connection conn = SessionFactoryUtils.getDataSource(getSessionFactory())
    // .getConnection();
    //
    // try {
    // ps = conn.prepareStatement(SqlQueries.SET_EVENTS_AGE_TO_OLD);
    // ps.setTimestamp(1, new Timestamp(beginDttm));
    //
    // numRecordsUpdated = ps.executeUpdate();
    //
    // } catch (SQLException e) {
    // logger.error("Error updating events' age to 'OLD' and changeFlag to 0",
    // e);
    // } finally {
    // closeStatement(ps);
    // closeConnection(conn);
    // }
    //
    //
    // return numRecordsUpdated;
    //
    // }
    //
    // /**
    // * Method to set events' changeflag to 0
    // *
    // * @param beginDttm
    // *
    // * @return numRecordsUpdated
    // *
    // * @throws SQLException
    // */
    // public int setChangeFlagToZero(final long beginDttm) throws SQLException
    // {
    //
    // PreparedStatement ps = null;
    // int numRecordsUpdated = 0;
    //
    // Connection conn = SessionFactoryUtils.getDataSource(getSessionFactory())
    // .getConnection();
    //
    // try {
    // ps = conn.prepareStatement(SqlQueries.SET_EVENTS_CHANGEFLAG_TO_ZERO);
    // ps.setTimestamp(1, new Timestamp(beginDttm));
    //
    // numRecordsUpdated = ps.executeUpdate();
    //
    // } catch (SQLException e) {
    // logger.error("Error updating events' changeFlag to 0", e);
    // } finally {
    // closeStatement(ps);
    // closeConnection(conn);
    // }
    //
    // return numRecordsUpdated;
    //
    // }
    //
    // /**
    // * Method to delete events flagged as deleted (statuscd=1 and
    // statustext='DEL')
    // *
    // * @param beginDttm
    // *
    // * @return numRecordsDeleted
    // *
    // * @throws SQLException
    // */
    // public int removeDeletedEvents(final long beginDttm) throws SQLException
    // {
    //
    // int numRecordsDeleted = 0;
    // PreparedStatement ps = null;
    //
    // Connection conn = SessionFactoryUtils.getDataSource(getSessionFactory())
    // .getConnection();
    //
    // try{
    // ps = conn.prepareStatement(SqlQueries.REMOVE_DELETED_EVENTS);
    // ps.setTimestamp(1, new Timestamp(beginDttm));
    //
    // numRecordsDeleted = ps.executeUpdate();
    //
    // } catch (SQLException e) {
    // logger.error("Error removing events flagged as deleted", e);
    // } finally {
    // closeStatement(ps);
    // closeConnection(conn);
    // }
    //
    // return numRecordsDeleted;
    //
    // }
    //
    // /**
    // * Close resultset
    // *
    // * @param rs
    // */
    // private void closeResultSet(ResultSet rs) {
    // if (rs != null) {
    // try {
    // rs.close();
    // } catch (SQLException e) {
    // logger.error("Error closing ResultSet", e);
    // }
    // }
    // }
    //
    // /**
    // * Close Statement
    // *
    // * @param s
    // */
    // private void closeStatement(Statement s) {
    // if (s != null) {
    // try {
    // s.close();
    // } catch (SQLException e) {
    // logger.error("Error closing Statement", e);
    // }
    // }
    // }
    //
    // /**
    // * Close connection
    // *
    // * @param connection
    // */
    // private void closeConnection(Connection connection) {
    // if (connection != null) {
    // try {
    // connection.close();
    // } catch (SQLException e) {
    // logger.error("Error closing Connection", e);
    // }
    // }
    // }
    //

}