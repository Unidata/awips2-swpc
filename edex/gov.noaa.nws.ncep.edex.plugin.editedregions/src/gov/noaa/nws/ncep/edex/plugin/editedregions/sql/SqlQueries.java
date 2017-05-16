package gov.noaa.nws.ncep.edex.plugin.editedregions.sql;

import gov.noaa.nws.ncep.common.dataplugin.editedregions.util.EditedRegionsConstants;

/**
 * Class that defines the SQL queries
 * 
 * <pre>
 *
 * SOFTWARE HISTORY
 *
 * Date         Ticket#    Engineer    Description
 * ------------ ---------- ----------- --------------------------
 * Jan 12, 2016            sgurung     Initial creation
 *
 * </pre>
 *
 * @author sgurung
 * @version 1.0	
 */

public abstract class SqlQueries {

	/**
	 * Query to update the swpc_goes_xray_event to set the value of the column processed=true
	 */
	public static final String SET_GOES_EVENTS_TO_PROCESSED = 
			"UPDATE swpc_goes_xray_event SET processed = true WHERE id IN (IDLIST)";
	
	/**
	 * Query to obtain and aggregate the raw goes event records to produce
	 * processed events.  
	 */	
	public static String GET_GOES_XRAY_EVENTS = "SELECT gxe.begin_dttm beginDTTM, " +
			"MAX(gxe.max_dttm) maxDTTM, " +
			"MAX(gxe.end_dttm) endDTTM, " +
			"gxe.satellite, " +
			"MAX(gxe.max_class) maxClass," +
			"SUM(gxe.current_xr_long) * 60 currentXRLong, " +
			"MAX(gxe.max_temp) maxTemp," +
			"MAX(gxe.max_emission_meas) maxEmissionMeas, " +
			"MAX(gxe.max_xr_long) maxXRLong " +
			"FROM awips.swpc_goes_xray_event gxe " +
			"INNER JOIN (" + gov.noaa.nws.ncep.edex.swpcrefdb.sql.SqlQueries.GET_COMPLETE_GOES_TRACKING_STATUS + ") " +
				"cgts ON gxe.satellite = cgts.satellite " +
				"AND gxe.begin_dttm >= cgts.begin_day " +
				"AND gxe.begin_dttm <= COALESCE(gxe.end_dttm, CURRENT_TIMESTAMP AT TIME ZONE 'UTC') " +
				"AND gxe.satellite = cgts.satellite " +
					"WHERE cgts.instrument = 'X-rays' " +
					"AND gxe.begin_dttm BETWEEN ? AND ? " +
					"AND gxe.end_dttm IS NOT NULL " +
					"GROUP BY gxe.begin_dttm, gxe.satellite ORDER BY gxe.begin_dttm";
																
	/**
	 * Query to obtain all the Raw Event ID's for the Events that
	 * need to be processed.  The id's are supplied as part of the
	 * GET_GOES_XRAY_EVENTS query
	 */
	public static final String GET_GOES_XRAY_EVENT_IDS = "SELECT gxe.id FROM awips.swpc_goes_xray_event gxe INNER JOIN (" + gov.noaa.nws.ncep.edex.swpcrefdb.sql.SqlQueries.GET_COMPLETE_GOES_TRACKING_STATUS + ") " +
															"cgts ON gxe.satellite = cgts.satellite " +
															"AND gxe.begin_dttm >= cgts.begin_day " +
															"AND gxe.begin_dttm <= COALESCE(gxe.end_dttm, CURRENT_TIMESTAMP AT TIME ZONE 'UTC') " +
															"AND gxe.satellite = cgts.satellite " +
																"WHERE cgts.instrument = 'X-rays' " +
																"AND gxe.begin_dttm BETWEEN ? AND ? " +
																"AND gxe.processed = false " +
																"GROUP BY gxe.id ORDER BY gxe.id";
	
																
//	/**
//	 * Query to remove the deleted events from the swpc_event table
//	 */
//	public static final String REMOVE_DELETED_EVENTS = 
//			"DELETE FROM swpc_event WHERE begindate >= ? AND statuscd = '" + (EditedRegionsConstants.EVENT_STATUS.DELETED.ordinal() + 1) + "' AND statustext = '" + EditedRegionsConstants.EVENT_STATUS.DELETED.toString() + "'";
//	
//	/**
//	 * Query to update events' age to 'OLD' and changeflag to 0 for events with age='NEW' or 'COR'  
//	 */
//	public static final String SET_EVENTS_AGE_TO_OLD = 
//			"UPDATE swpc_event SET age = '"  + EditedRegionsConstants.EVENT_AGE.OLD.toString() + "', changeflag=0 WHERE begindate >= ? AND age = '" + EditedRegionsConstants.EVENT_AGE.NEW.toString() + "' OR age = '" + EditedRegionsConstants.EVENT_AGE.COR.toString() + "'";
//	
	/**
	 * Query to update events' changeflag to 0   
	 */
	public static final String SET_EVENTS_CHANGEFLAG_TO_ZERO = 
			"UPDATE swpc_event SET changeflag=0 WHERE begindate >= ?";
	
}
