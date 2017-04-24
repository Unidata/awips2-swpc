package gov.noaa.nws.ncep.edex.plugin.editedevents.dao;

import gov.noaa.nws.ncep.common.dataplugin.editedevents.EventType;

import java.util.Vector;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;

import com.raytheon.uf.edex.database.dao.CoreDao;
import com.raytheon.uf.edex.database.dao.DaoConfig;

/**
 * Data Access Object (DAO) class to interact with swpc_event_type database
 * table.
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
public class EventTypeDao extends CoreDao {

	/**
	 * Creates a new EventTypeDao
	 */
	public EventTypeDao() {
		super(DaoConfig.forClass(EventTypeDao.class));
	}

	/**
	 * Retrieves vector of event types
	 * 
	 * @return Vector<EventType>
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Vector<EventType> getEventTypes() {
		return (Vector<EventType>) txTemplate.execute(new TransactionCallback() {
			@Override
			public Object doInTransaction(TransactionStatus status) {
				Session sess = getCurrentSession();
				Criteria crit = sess.createCriteria(EventType.class);
				crit.addOrder(Order.asc(EventType.TYPE));
				
				Vector<EventType> types = new Vector<EventType>();
				types.addAll(crit.list());
	            
	            return types;
			}
		});
	}

}
