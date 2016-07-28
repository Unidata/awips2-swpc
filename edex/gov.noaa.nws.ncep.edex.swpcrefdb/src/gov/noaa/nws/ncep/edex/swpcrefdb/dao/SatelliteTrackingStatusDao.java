package gov.noaa.nws.ncep.edex.swpcrefdb.dao;

import gov.noaa.nws.ncep.common.swpcrefdb.Satellite;
import gov.noaa.nws.ncep.edex.swpcrefdb.sql.SqlQueries;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Calendar;
import java.util.Vector;

import org.springframework.orm.hibernate4.SessionFactoryUtils;

import com.raytheon.uf.common.status.IUFStatusHandler;
import com.raytheon.uf.common.status.UFStatus;
import com.raytheon.uf.edex.database.dao.CoreDao;
import com.raytheon.uf.edex.database.dao.DaoConfig;

/**
 * Provides access to the SatelliteTrackingStatus database table
 * 
 * <pre>
 *
 * SOFTWARE HISTORY
 *
 * Date         Ticket#    Engineer    Description
 * ------------ ---------- ----------- --------------------------
 * Jan 12, 2016 R9583     sgurung     Initial creation
 *
 * </pre>
 *
 * @author sgurung
 * @version 1.0	
 */

public class SatelliteTrackingStatusDao extends CoreDao {
	
	 protected final IUFStatusHandler logger = UFStatus.getHandler(getClass());

	 /**
     * Creates a new SatelliteTrackingStatusDao
     */
    public SatelliteTrackingStatusDao() {
        super(DaoConfig.forClass(SatelliteTrackingStatusDao.class));
      
    }
    
    /**
     * Obtain the primary, secondary and tertiary satellites for a
     * given start and end date
     * 
     * @param instrument
     * @param startDate
     * @param endDate
     * 
     * @return Vector<Satellite>
     * 
     * @throws SQLException
     */
    public Vector<Satellite> getGOESSatellite(String instrument, Calendar startDate, Calendar endDate) throws SQLException {
    	Connection conn = SessionFactoryUtils.getDataSource(getSessionFactory())
		        .getConnection();

    	PreparedStatement ps = null;
    	ResultSet rs = null;
    	String satelliteCode = null;
    	String trackingStatus = null;
    	long beginDttm = 0L;
    	long endDttm = 0L;
    	Vector<Satellite> satellites = new Vector<Satellite>();
    	
    	try {
			ps = conn.prepareStatement(SqlQueries.GET_GOES_PRIMARY_SECONDARY_TERTIARY_SATELLITES);
	
			ps.setString(1, instrument);
			ps.setString(2, "Primary");
			ps.setDate(3, new java.sql.Date(startDate.getTimeInMillis()));
			ps.setDate(4, new java.sql.Date(startDate.getTimeInMillis()));
			ps.setDate(5, new java.sql.Date(endDate.getTimeInMillis()));
			ps.setDate(6, new java.sql.Date(startDate.getTimeInMillis()));
			ps.setDate(7, new java.sql.Date(endDate.getTimeInMillis()));
			ps.setDate(8, new java.sql.Date(endDate.getTimeInMillis()));
			ps.setString(9, instrument);
			ps.setString(10, "Secondary");
			ps.setDate(11, new java.sql.Date(startDate.getTimeInMillis()));
			ps.setDate(12, new java.sql.Date(startDate.getTimeInMillis()));
			ps.setDate(13, new java.sql.Date(endDate.getTimeInMillis()));
			ps.setDate(14, new java.sql.Date(startDate.getTimeInMillis()));
			ps.setDate(15, new java.sql.Date(endDate.getTimeInMillis()));
			ps.setDate(16, new java.sql.Date(endDate.getTimeInMillis()));
			ps.setString(17, instrument);
			ps.setString(18, "Tertiary");
			ps.setDate(19, new java.sql.Date(startDate.getTimeInMillis()));
			ps.setDate(20, new java.sql.Date(startDate.getTimeInMillis()));
			ps.setDate(21, new java.sql.Date(endDate.getTimeInMillis()));
			ps.setDate(22, new java.sql.Date(startDate.getTimeInMillis()));
			ps.setDate(23, new java.sql.Date(endDate.getTimeInMillis()));
			ps.setDate(24, new java.sql.Date(endDate.getTimeInMillis()));	
			
			rs = ps.executeQuery();
			
			if (rs.isBeforeFirst()) {
				
				// obtain the parameters from the resultset
				// that make up a satellite
				while (rs.next()) {
					satelliteCode = rs.getString("satellite");
					trackingStatus = rs.getString("tracking_status");
					if (rs.getTimestamp("begin_dttm") != null) {
						beginDttm = rs.getTimestamp("begin_dttm").getTime();
					}
					if (rs.getTimestamp("end_dttm") != null) {
						endDttm = rs.getTimestamp("end_dttm").getTime();
					}
					
					// create a Satellite instance and add it to the
					// Vector of all satellites
					satellites.add(new Satellite(satelliteCode, trackingStatus, beginDttm, endDttm));
					
				}
			}
			
	    } catch (SQLException e) {
	        logger.error("Error retrieving GOES satellites", e);
	    } finally {
	    	closeResultSet(rs);
	    	closeStatement(ps);
	        closeConnection(conn);	
	    }
		
	
		return satellites;
    }

    /**
     * Obtain the satellite for a
     * given instrument, trackingStatus, start and end date
     * 
     * @param instrument
     * @param trackingStatus
     * @param startDate
     * @param endDate
     * 
     * @return String
     * 
     * @throws SQLException
     */
    public String getGOESSatellite(String instrument, String trackingStatus, Calendar startDate, Calendar endDate) throws SQLException {
    	Connection conn = SessionFactoryUtils.getDataSource(getSessionFactory())
		        .getConnection();

    	PreparedStatement ps = null;
    	ResultSet rs = null;
    	String satellite = null;
    	
    	try {
			ps = conn.prepareStatement(SqlQueries.GET_GOES_SATELLITE);
	
			ps.setString(1, instrument);
			ps.setString(2, trackingStatus);
			ps.setDate(3, new java.sql.Date(startDate.getTimeInMillis()));
			ps.setDate(4, new java.sql.Date(startDate.getTimeInMillis()));
			ps.setDate(5, new java.sql.Date(endDate.getTimeInMillis()));
			ps.setDate(6, new java.sql.Date(startDate.getTimeInMillis()));
			ps.setDate(7, new java.sql.Date(endDate.getTimeInMillis()));
			ps.setDate(8, new java.sql.Date(endDate.getTimeInMillis()));
			ps.setString(9, instrument);
			
			rs = ps.executeQuery();
			
			if (rs.isBeforeFirst()) {
				
				while (rs.next()) {
					satellite = rs.getString("satellite");					
				}
			}
			
	    } catch (SQLException e) {
	        logger.error("Error retrieving GOES satellite", e);
	    } finally {
	    	closeResultSet(rs);
	    	closeStatement(ps);
	        closeConnection(conn);	
	    }
		
	
		return satellite;
    }
    
    /**
     * Obtain the satellite to be used for the integrated flux calculation for a
     * given instrument, trackingStatus, start and end date
     * 
     * @param instrument
     * @param trackingStatus
     * @param startDate
     * @param endDate
     * 
     * @return String
     * 
     * @throws SQLException
     */
    public String getGOESSatelliteForIntFlux(String instrument, String trackingStatus, Calendar startDate, Calendar endDate) throws SQLException {
    	Connection conn = SessionFactoryUtils.getDataSource(getSessionFactory())
		        .getConnection();

    	PreparedStatement ps = null;
    	ResultSet rs = null;
    	String satellite = null;
    	
    	try {
			ps = conn.prepareStatement(SqlQueries.GET_GOES_SATELLITE_FOR_INTEGRATED_FLUX);
	
			ps.setString(1, instrument);
			ps.setString(2, trackingStatus);
			ps.setDate(3, new java.sql.Date(startDate.getTimeInMillis()));
			ps.setDate(4, new java.sql.Date(startDate.getTimeInMillis()));
			ps.setDate(5, new java.sql.Date(endDate.getTimeInMillis()));
			ps.setDate(6, new java.sql.Date(startDate.getTimeInMillis()));
			ps.setDate(7, new java.sql.Date(endDate.getTimeInMillis()));
			ps.setDate(8, new java.sql.Date(endDate.getTimeInMillis()));
			//ps.setString(9, instrument);
			
			rs = ps.executeQuery();
			
			if (rs.isBeforeFirst()) {
				
				while (rs.next()) {
					satellite = rs.getString("satellite");					
				}
			}
			
	    } catch (SQLException e) {
	        logger.error("Error retrieving GOES satellite", e);
	    } finally {
	    	closeResultSet(rs);
	    	closeStatement(ps);
	        closeConnection(conn);	
	    }
		
	
		return satellite;
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
