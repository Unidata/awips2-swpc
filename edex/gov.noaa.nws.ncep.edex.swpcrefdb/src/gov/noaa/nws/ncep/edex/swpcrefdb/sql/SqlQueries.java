package gov.noaa.nws.ncep.edex.swpcrefdb.sql;

/**
 * Class defining the sql queries.
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
	 * Query to obtain tracking status for the GOES satellites
	 */
	public static final String GET_COMPLETE_GOES_TRACKING_STATUS = "SELECT s.designation satellite " +
            ", s.name satellite_name " +
            ", i.description instrument " +
            ", sts.description tracking_status " +
            ", ts.begin_dttm " +
            ", ts.end_dttm " +
            ", to_timestamp(to_char(ts.begin_dttm, " + "'" + "YYYY-MM-DD" + "'" + ")," + "'" + "YYYY-MM-DD HH24:MI:SS.ss" + "'" + ") begin_day " +
            ", to_timestamp(to_char(ts.end_dttm, " + "'" + "YYYY-MM-DD" + "'" + "), " + "'" + "YYYY-MM-DD HH24:MI:SS.ss" + "'" + ") end_day " +
            "FROM swpc_station s " +
            "INNER JOIN swpc_station_type st ON s.type_id = st.id " +
            "INNER JOIN swpc_station_instrument si ON s.id = si.station_id " +
            "INNER JOIN swpc_instrument i ON si.instrument_id = i.id " +
            "INNER JOIN swpc_tracking_status ts ON si.id = ts.stationinstrument_id " +
            "INNER JOIN swpc_satellite_tracking_status sts " +
            "ON ts.satellitetrackingstatus_id = sts.id " +
            "WHERE st.description = " + "'" + "GOES" + "'";

	/**
	 * first parameter is X-rays
	 * second parameter is Primary
	 * 3-8 parameters are some date values
	 * 9 parameter is X-rays
	 * 10 parameter is Secondary
	 * 11-16 parameters are some date values
	 * 17 parameter is X-rays
	 * 18 parameter is Tertiary
	 * 19-24 parameters are some date values
	 */
	public static final String GET_GOES_PRIMARY_SECONDARY_TERTIARY_SATELLITES = "SELECT satellite, " +
															"tracking_status," +
															"begin_dttm," +
															"end_dttm from (" + SqlQueries.GET_COMPLETE_GOES_TRACKING_STATUS + ") completeGoesTrackingStatus " +
			"WHERE (completeGoesTrackingStatus.instrument = ? " +
			"AND completeGoesTrackingStatus.tracking_status = ? " +
			"AND (completeGoesTrackingStatus.begin_day <= ? " +
			"OR completeGoesTrackingStatus.begin_day BETWEEN ? AND ?) " +
			"AND ((completeGoesTrackingStatus.end_day - cast(" + "'" + "1 day" + "'" + " as interval)) BETWEEN ? AND ? " +
					"OR (completeGoesTrackingStatus.end_day - cast(" + "'" + "1 day" + "'" + " as interval)) >= ? " +
							"OR completeGoesTrackingStatus.end_day IS NULL) " +
							"OR " +
							"(completeGoesTrackingStatus.instrument = ? " +
			"AND completeGoesTrackingStatus.tracking_status = ? " +
			"AND (completeGoesTrackingStatus.begin_day <= ? " +
			"OR completeGoesTrackingStatus.begin_day BETWEEN ? AND ?) " +
			"AND ((completeGoesTrackingStatus.end_day - cast(" + "'" + "1 day" + "'" + " as interval)) BETWEEN ? AND ? " +
					"OR (completeGoesTrackingStatus.end_day - cast(" + "'" + "1 day" + "'" + " as interval)) >= ? " +
							"OR completeGoesTrackingStatus.end_day IS NULL) " +
							"OR " +
							"(completeGoesTrackingStatus.instrument = ? " +
			"AND completeGoesTrackingStatus.tracking_status = ? " +
			"AND (completeGoesTrackingStatus.begin_day <= ? " +
			"OR completeGoesTrackingStatus.begin_day BETWEEN ? AND ?) " +
			"AND ((completeGoesTrackingStatus.end_day - cast(" + "'" + "1 day" + "'" + " as interval)) BETWEEN ? AND ? " +
					"OR (completeGoesTrackingStatus.end_day - cast(" + "'" + "1 day" + "'" + " as interval)) >= ? " +
							"OR completeGoesTrackingStatus.end_day IS NULL)))) order by begin_dttm asc";

	/**
	 * first parameter is instrument e.g. "X-rays"
	 * second parameter is tracking status e.g. "Primary", "Secondary", or "Tertiary"
	 * 3-8 parameters are some date values
	 */
	public static final String GET_GOES_SATELLITE = "SELECT satellite from (" + SqlQueries.GET_COMPLETE_GOES_TRACKING_STATUS + ") completeGoesTrackingStatus " +
			"WHERE (completeGoesTrackingStatus.instrument = ? " +
			"AND completeGoesTrackingStatus.tracking_status = ? " +
			"AND (completeGoesTrackingStatus.begin_day <= ? " +
			"OR completeGoesTrackingStatus.begin_day BETWEEN ? AND ?) " +
			"AND ((completeGoesTrackingStatus.end_day - cast(" + "'" + "1 day" + "'" + " as interval)) BETWEEN ? AND ? " +
					"OR (completeGoesTrackingStatus.end_day - cast(" + "'" + "1 day" + "'" + " as interval)) >= ? " +
							"OR completeGoesTrackingStatus.end_day IS NULL) ";
	
	/**
	 * first parameter is instrument e.g. "X-rays"
	 * second parameter is tracking status e.g. "Primary", "Secondary", or "Tertiary"
	 * 3-8 parameters are some date values
	 */
	public static final String GET_GOES_SATELLITE_FOR_INTEGRATED_FLUX = "SELECT satellite from (" + SqlQueries.GET_COMPLETE_GOES_TRACKING_STATUS + ") completeGoesTrackingStatus " +
			"WHERE completeGoesTrackingStatus.instrument = ? " +
			"AND completeGoesTrackingStatus.tracking_status = ? " +
			"AND (completeGoesTrackingStatus.begin_day <= ? " +
			"OR completeGoesTrackingStatus.begin_day BETWEEN ? AND ?) " +
			"AND (completeGoesTrackingStatus.end_day BETWEEN ? AND ? " +
					"OR completeGoesTrackingStatus.end_day >= ? " +
							"OR completeGoesTrackingStatus.end_day IS NULL) ";
	
	
	/**
	 * Query to update the swpc_goes_xray_event to set the value of the column processed=true
	 * first parameter is begin date time
	 * second parameter is satellite designation
	 */
	public static final String SET_GOES_EVENTS_TO_PROCESSED = "UPDATE swpc_goes_xray_event SET processed = true WHERE begin_dttm = ? AND satellite = ?";
}
