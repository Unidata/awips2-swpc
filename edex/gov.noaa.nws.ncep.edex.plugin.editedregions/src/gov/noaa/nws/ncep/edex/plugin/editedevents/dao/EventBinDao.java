package gov.noaa.nws.ncep.edex.plugin.editedevents.dao;

import gov.noaa.nws.ncep.common.dataplugin.editedevents.EventBin;
import gov.noaa.nws.ncep.common.dataplugin.editedevents.util.EditedEventsConstants;

import java.util.Calendar;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;

import com.raytheon.uf.edex.database.dao.CoreDao;
import com.raytheon.uf.edex.database.dao.DaoConfig;

/**
 * Data Access Object (DAO) class to interact with swpc_event_bin database
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
public class EventBinDao extends CoreDao {

	/**
	 * Creates a new EventBinDao
	 */
	public EventBinDao() {
		super(DaoConfig.forClass(EventBinDao.class));
	}

	/**
	 * Retrieves list of event bins within given date range except Radio Noise
	 * Storms(Type=RNS) and Disappearing Filaments (Type=DSF).
	 * 
	 * @param parm1
	 * @param parm2
	 * @return List<EventBin>
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<EventBin> getBinsMinusRNSandDSF(final Calendar parm1,
			final Calendar parm2) {
		return (List<EventBin>) txTemplate.execute(new TransactionCallback() {
			@Override
			public Object doInTransaction(TransactionStatus status) {
				
				List<EventBin> bins = null;
				 
				try {
				Session sess = getCurrentSession();
				Criteria crit = sess.createCriteria(EventBin.class);
				Criterion where1 = Restrictions.ge(EventBin.END_DATE_TIME,
						parm2);
				crit.add(where1);
				Criterion where2 = Restrictions.le(EventBin.BEGIN_DATE_TIME, parm1);
				crit.add(where2);
				Criterion where3 = Restrictions.ne(EventBin.TYPE,
						EditedEventsConstants.EventType.RNS.getType());
				crit.add(where3);
				Criterion where4 = Restrictions.ne(EventBin.TYPE,
						EditedEventsConstants.EventType.DSF.getType());
				crit.add(where4);
				crit.addOrder(Order.asc(EventBin.BEGIN_DATE_TIME));
				
				bins = crit.list();
				
				} catch (Exception e) {
					e.printStackTrace();
				}
				

				return bins;
			}
		});
	}

	/**
	 * Retrieves list of event bins of type: Radio Noise Storms(Type=RNS)
	 * 
	 * @param start
	 * @return List<EventBin>
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<EventBin> getRNSBins(final Calendar start) {
		return (List<EventBin>) txTemplate.execute(new TransactionCallback() {
			@Override
			public Object doInTransaction(TransactionStatus status) {
				Session sess = getCurrentSession();
				Criteria crit = sess.createCriteria(EventBin.class);
				Criterion where1 = Restrictions.eq(EventBin.BEGIN_DATE_TIME,
						start);
				crit.add(where1);
				Criterion where2 = Restrictions.eq(EventBin.TYPE,
						EditedEventsConstants.EventType.RNS.getType());
				crit.add(where2);
				
				return crit.list();
			}
		});
	}

	/**
	 * Retrieves list of event bins of type: Disappearing Filaments (Type=DSF)
	 * 
	 * @param eventBegin
	 * @param eventEnd
	 * @param searchCriteria
	 * @return List<EventBin>
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<EventBin> getDSFBins(final Calendar eventBegin,
			final Calendar eventEnd, final int searchCriteria) {
		return (List<EventBin>) txTemplate.execute(new TransactionCallback() {
			@Override
			public Object doInTransaction(TransactionStatus status) {
				Session sess = getCurrentSession();
				Criteria crit = sess.createCriteria(EventBin.class);

				Criterion where1 = Restrictions.eq(EventBin.TYPE,
						EditedEventsConstants.EventType.DSF.getType());
				crit.add(where1);

				switch (searchCriteria) {
				case 1:
					Criterion where2 = Restrictions.le(
							EventBin.BEGIN_DATE_TIME, eventBegin);
					crit.add(where2);
					Criterion where3 = Restrictions.ge(EventBin.END_DATE_TIME,
							eventBegin);
					crit.add(where3);
					break;
				case 2:
					Criterion where4 = Restrictions.le(
							EventBin.BEGIN_DATE_TIME, eventEnd);
					crit.add(where4);
					Criterion where5 = Restrictions.ge(EventBin.END_DATE_TIME,
							eventEnd);
					crit.add(where5);
					break;
				case 3:
					Criterion where6 = Restrictions.ge(
							EventBin.BEGIN_DATE_TIME, eventBegin);
					crit.add(where6);
					Criterion where7 = Restrictions.le(EventBin.END_DATE_TIME,
							eventEnd);
					crit.add(where7);
					break;

				default:
					Criterion where8 = Restrictions.ge(
							EventBin.BEGIN_DATE_TIME, eventBegin);

					crit.add(where8);
					break;

				}

				// crit.addOrder(Order.asc(EventBin.BEGIN_DATE_TIME));

				return crit.list();
			}
		});
	}

	/**
	 * Retrieves list of event bins to free (delete).
	 * 
	 * @param binNumber1
	 * @param binNumber2
	 * @param useCondition1
	 * @return List<EventBin>
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<EventBin> getBinsToFree(final int binNumber1,
			final int binNumber2, final boolean useCondition1) {
		return (List<EventBin>) txTemplate.execute(new TransactionCallback() {
			@Override
			public Object doInTransaction(TransactionStatus status) {
				Session sess = getCurrentSession();
				Criteria crit = sess.createCriteria(EventBin.class);

				if (useCondition1) {
					Criterion where1 = Restrictions.gt(EventBin.BIN_NUMBER,
							binNumber1);
					crit.add(where1);
					Criterion where2 = Restrictions.le(EventBin.BIN_NUMBER,
							binNumber2);
					crit.add(where2);
				} else {
					Criterion where1 = Restrictions.le(EventBin.BIN_NUMBER,
							binNumber2);
					Criterion where2 = Restrictions.ge(EventBin.BIN_NUMBER,
							binNumber1);
					crit.add(Restrictions.disjunction().add(where1).add(where2)); // where1
																					// OR
																					// where2
				}
				return crit.list();
			}
		});
	}

	/**
	 * Retrieves list of event bins with given bin begin datetime.
	 * 
	 * @return List<EventBin>
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<EventBin> getBins(final Calendar binBegin) {
		return (List<EventBin>) txTemplate.execute(new TransactionCallback() {
			@Override
			public Object doInTransaction(TransactionStatus status) {
				Session sess = getCurrentSession();
				Criteria crit = sess.createCriteria(EventBin.class);
				Criterion where1 = Restrictions.eq(EventBin.BEGIN_DATE_TIME,
						binBegin);
				crit.add(where1);
				return crit.list();
			}
		});
	}

    @SuppressWarnings({ "unchecked", "rawtypes" })
    public EventBin queryByBinNumber(final Integer binNumber) {
        return (EventBin) txTemplate.execute(new TransactionCallback() {
            @Override
            public Object doInTransaction(TransactionStatus status) {
                Session sess = getCurrentSession();
                Criteria crit = sess.createCriteria(EventBin.class);
                Criterion where1 = Restrictions.eq(EventBin.BIN_NUMBER,
                        binNumber);
                crit.add(where1);
                return (EventBin) crit.uniqueResult();
            }
        });
    }
	
	/**
	 * Retrieves list of event bins
	 * 
	 * @return List<EventBin>
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<EventBin> getBins() {
		return (List<EventBin>) txTemplate.execute(new TransactionCallback() {
			@Override
			public Object doInTransaction(TransactionStatus status) {
				Session sess = getCurrentSession();
				Criteria crit = sess.createCriteria(EventBin.class);
				crit.addOrder(Order.asc(EventBin.BIN_NUMBER));
				return crit.list();
			}
		});
	}
	
	/**
	 * Retrieves list of distinct event bins within a given date range
	 * 
	 * @return List<Integer>
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<Integer> getBins(final Calendar start,
			final Calendar end) {
		return (List<Integer>) txTemplate.execute(new TransactionCallback() {
			@Override
			public Object doInTransaction(TransactionStatus status) {
				Session sess = getCurrentSession();
				Criteria crit = sess.createCriteria(EventBin.class);				
				
				Criterion where1 = Restrictions.ge(EventBin.BEGIN_DATE_TIME, start);			
				crit.add(where1);
							
				Criterion where2 = Restrictions.le(EventBin.BEGIN_DATE_TIME, end);	
				crit.add(where2);
				
				crit.setProjection(Projections.distinct(Projections.property(EventBin.BIN_NUMBER)));
								
				crit.addOrder(Order.asc(EventBin.BIN_NUMBER));
				return crit.list();
			}
		});
	}
	
}
