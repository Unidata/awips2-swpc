package gov.noaa.nws.ncep.edex.plugin.editedregions.dao;

import gov.noaa.nws.ncep.common.dataplugin.editedregions.GOESXrayEvent;
import gov.noaa.nws.ncep.common.dataplugin.editedregions.util.EditedRegionsConstants;
import gov.noaa.nws.ncep.edex.plugin.editedregions.sql.SqlQueries;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate4.SessionFactoryUtils;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;

import com.raytheon.edex.db.dao.DefaultPluginDao;
import com.raytheon.uf.common.dataplugin.PluginException;

/**
 * Data Access Object (DAO) class to interact with swpc_goes_xray_event database
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
public class GoesXrayEventDao extends DefaultPluginDao {

    /**
     * Constructs a GoesXrayEventDao
     * 
     * @throws PluginException
     *             If errors occur during construction
     */
    public GoesXrayEventDao() throws PluginException {
        super(EditedRegionsConstants.PLUGIN_NAME);
    }

    /**
     * Retrieves list of events
     * 
     * @return list of events
     */
    @SuppressWarnings({ "unchecked", "rawtypes" })
    public List<GOESXrayEvent> getEventsList(final Calendar start,
            final Calendar end) {
        return (List<GOESXrayEvent>) txTemplate
                .execute(new TransactionCallback() {
                    @Override
                    public Object doInTransaction(TransactionStatus status) {
                        Session sess = getCurrentSession();
                        Criteria crit = sess
                                .createCriteria(GOESXrayEvent.class);
                        Criterion where1 = Restrictions.ge(GOESXrayEvent.BEGIN_DTTM, start);
                        crit.add(where1);
                        Criterion where2 = Restrictions.lt(GOESXrayEvent.BEGIN_DTTM, end);
                        crit.add(where2);
                        crit.addOrder(Order.asc(GOESXrayEvent.BEGIN_DTTM));

                        return crit.list();
                    }
                });
    }

    /**
     * Obtain the raw event record id's
     * 
     * @param beginDTTM
     * @param endDTTM
     * 
     * @return Array
     * 
     * @throws SQLException
     */
    public Vector<Integer> getRawEventIds(final long beginDTTM, final long endDTTM) throws SQLException {
    	
    	Calendar begin = Calendar.getInstance();
    	Calendar end = Calendar.getInstance();
    	begin.clear();
    	end.clear();
    	begin.setTimeInMillis(beginDTTM);
    	end.setTimeInMillis(endDTTM);
    	
    	PreparedStatement ps = null;
    	ResultSet rs = null;
    	Vector<Integer> results = new Vector<Integer>();
    	
    	Connection conn = SessionFactoryUtils.getDataSource(getSessionFactory()).getConnection();

    	try {
			ps = conn.prepareStatement(SqlQueries.GET_GOES_XRAY_EVENT_IDS);
			
			ps.setTimestamp(1, new Timestamp(beginDTTM));
			ps.setTimestamp(2, new Timestamp(endDTTM));
			
	        logger.info(" Inside GoesXrayEventDao.getRawEventIds(), executing sql query = "
	                + SqlQueries.GET_GOES_XRAY_EVENT_IDS + " with Parameters 1-2 as: " + 
	        		beginDTTM + "," + 
	                endDTTM);
	        
	        rs = ps.executeQuery();
	
	        while (rs.next()) {
	        	results.add(rs.getInt(GOESXrayEvent.ID));
	        }
	        
   	    } catch (SQLException e) {
   	        logger.error("Error retrieving GOES Xray raw event ids", e);
   	    } finally {
   	    	closeResultSet(rs);
   	    	closeStatement(ps);
   	        closeConnection(conn);	
   	    }
        
        return results;
    	
    }   
    
    /**
     * Get the list of GOES Xray Events. The query used in this method is
     * equivalent to the legacy database view goesnop_events.
     * 
     * @param beginDTTM
     *            Begin Date Time
     * @param endDTTM
     *            End Date Time
     * @return List<Map<String, Object>>
     * @throws SQLException
     */
    public List<Map<String, Object>> getRawEvents(final long beginDTTM, final long endDTTM) throws SQLException {

    	Calendar begin = Calendar.getInstance();
    	Calendar end = Calendar.getInstance();
    	begin.clear();
    	end.clear();
    	begin.setTimeInMillis(beginDTTM);
    	end.setTimeInMillis(endDTTM);
    	
    	PreparedStatement ps = null;
    	ResultSet rs = null;
        List<Map<String, Object>> results = new ArrayList<Map<String, Object>>();

        String[] fieldNames = { GOESXrayEvent.BEGIN_DTTM,
                GOESXrayEvent.MAX_DTTM, GOESXrayEvent.END_DTTM,
                GOESXrayEvent.SATELLITE, GOESXrayEvent.MAX_CLASS,
                GOESXrayEvent.CURRENT_XR_LONG, GOESXrayEvent.MAX_TEMP,
                GOESXrayEvent.MAX_EMISSION_MEAS, GOESXrayEvent.MAX_XR_LONG };

    	Connection conn = SessionFactoryUtils.getDataSource(getSessionFactory()).getConnection();        

    	try {
			ps = conn.prepareStatement(SqlQueries.GET_GOES_XRAY_EVENTS);
		
			ps.setTimestamp(1, new Timestamp(beginDTTM));
			ps.setTimestamp(2, new Timestamp(endDTTM));
			
	
	        logger.info(" Inside GoesXrayEventDao.getRawEvents(), executing sql query = "
	               + ps.toString() + " - " + " - " + SqlQueries.GET_GOES_XRAY_EVENTS + " with Parameters 1-2 as: " + 
	        		beginDTTM + "," + 
	                endDTTM);
	        
	        rs = ps.executeQuery();
	        
	        Map<String, Object> resultMap = null;
	        
	        while (rs.next()) {
	        	
	        	resultMap = new HashMap<String, Object>();
	        	
	        	Timestamp beginDttm = rs.getTimestamp(fieldNames[0]);
	        	Timestamp maxDttm = rs.getTimestamp(fieldNames[1]);
	        	Timestamp endDttm = rs.getTimestamp(fieldNames[2]);
	        	String satellite = rs.getString(fieldNames[3]);
	        	String maxClass = rs.getString(fieldNames[4]);
	        	Double currentXRLong = rs.getDouble(fieldNames[5]);
	        	Double maxTemp = rs.getDouble(fieldNames[6]);
	        	Double maxXRLong = rs.getDouble(fieldNames[7]);
	        	
	        	resultMap.put(fieldNames[0], beginDttm);
	        	resultMap.put(fieldNames[1], maxDttm);
	        	resultMap.put(fieldNames[2], endDttm);
	        	resultMap.put(fieldNames[3], satellite);
	        	resultMap.put(fieldNames[4], maxClass);
	        	resultMap.put(fieldNames[5], currentXRLong);
	        	resultMap.put(fieldNames[6], maxTemp);
	        	resultMap.put(fieldNames[7], maxXRLong);
	        	
	        	results.add(resultMap);
	        	
	        }
	        
	    } catch (SQLException e) {
	        logger.error("Error retrieving GOES Xray raw events", e);
	    } finally {
	    	closeResultSet(rs);
	    	closeStatement(ps);
	        closeConnection(conn);	
	    }

        return results;
    }
    
    /**
     * Method to set the Processed flag to TRUE for all raw
     * event records that were used during the process of creating
     * the processed/edited event record(s)
     * 
     * @param recordIds
     * 
     * @return numRecordsUpdate
     * 
     * @throws SQLException
     */
    public int setEventsToProcessed(String recordIdsCSV) throws SQLException {
    	 
    	PreparedStatement ps = null;
    	int numRecordsUpdated = 0;
    	
    	Connection conn = SessionFactoryUtils.getDataSource(getSessionFactory())
		        .getConnection();
        
        // because we are not able to pass an array to the SQL IN function we
        // do a workaround where we replace the word IDLIST with the generated CSV
        // list of id's
        String sqlQuerry = SqlQueries.SET_GOES_EVENTS_TO_PROCESSED.replace("IDLIST", recordIdsCSV);
        
        try {
			ps = conn.prepareStatement(sqlQuerry);
		
			numRecordsUpdated = ps.executeUpdate();
			
			logger.info(" EDITEDEVENTS.GoesXrayEventDao.setEventsToProcessed, numRecordsUpdated =  " + numRecordsUpdated);
        } catch (SQLException e) {
	        logger.error("Error updating the processed flag to true", e);
	    } finally {
	    	closeStatement(ps);
	        closeConnection(conn);	
	    }

		return numRecordsUpdated;
		
    }
    
    /**
     * Close resultset
     * 
     * @param rs
     */
    private void closeResultSet(ResultSet rs) {
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException e) {
                logger.error("Error closing ResultSet", e);
            }
        }
    }

    /**
     * Close Statement
     * 
     * @param s
     */
    private void closeStatement(Statement s) {
        if (s != null) {
            try {
                s.close();
            } catch (SQLException e) {
                logger.error("Error closing Statement", e);
            }
        }
    }
    
    /**
     * Close connection
     * 
     * @param connection
     */
    private void closeConnection(Connection connection) {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                logger.error("Error closing Connection", e);
            }
        }
    }
}