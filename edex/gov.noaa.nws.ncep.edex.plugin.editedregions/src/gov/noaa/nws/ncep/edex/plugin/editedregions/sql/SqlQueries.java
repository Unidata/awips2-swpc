package gov.noaa.nws.ncep.edex.plugin.editedregions.sql;

/**
 * Class that defines the SQL queries.
 * 
 * <pre>
 *
 * SOFTWARE HISTORY
 *
 * Date         Ticket#    Engineer    Description
 * ------------ ---------- ----------- --------------------------
 * Jan 12, 2016            alockleigh     Initial creation
 *
 * </pre>
 *
 * @author alockleigh
 * @version 1.0
 */

public abstract class SqlQueries {

    /**
     * Query retrieve region report records that have not had history recorded
     * for them yet. Used for records that have been ingested.
     */
    public static final String GET_REGION_REPORTS_WITHOUT_HISTORY = "SELECT id FROM awips.swpc_region_reports rr WHERE NOT EXISTS "
            + "(SELECT regionreportid FROM awips.swpc_region_history_reports rhr "
            + "WHERE rr.id = rhr.regionreportid);";

}