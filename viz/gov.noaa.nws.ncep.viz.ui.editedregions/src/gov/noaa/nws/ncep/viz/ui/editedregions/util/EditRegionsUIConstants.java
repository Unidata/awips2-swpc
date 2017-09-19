/**
 * This software was developed and / or modified by Raytheon Company,
 * pursuant to Contract DG133W-05-CQ-1067 with the US Government.
 * 
 * U.S. EXPORT CONTROLLED TECHNICAL DATA
 * This software product contains export-restricted data whose
 * export/transfer/disclosure is restricted by U.S. law. Dissemination
 * to non-U.S. persons whether in the United States or abroad requires
 * an export license or other authorization.
 * 
 * Contractor Name:        Raytheon Company
 * Contractor Address:     6825 Pine Street, Suite 340
 *                         Mail Stop B8
 *                         Omaha, NE 68106
 *                         402.291.0100
 * 
 * See the AWIPS II Master Rights File ("Master Rights File.pdf") for
 * further licensing information.
 **/
package gov.noaa.nws.ncep.viz.ui.editedregions.util;

import java.text.SimpleDateFormat;

import gov.noaa.nws.ncep.common.dataplugin.editedregions.util.EditedRegionsConstants;

/**
 * TODO Add Description
 * 
 * <pre>
 * 
 * SOFTWARE HISTORY
 * 
 * Date         Ticket#    Engineer    Description
 * ------------ ---------- ----------- --------------------------
 * Jan 28, 2015            sgurung     Initial creation
 * 
 * </pre>
 * 
 * @author sgurung
 * @version 1.0
 */
public interface EditRegionsUIConstants {

    // Edit Regions Constants
    public static final String[] COLUMNS = new String[] { "Observation Time",
            "Observatory", "Quality", "Region", "Report Location",
            "00Z Location", "Carlon", "Extent", "Area", "Numspots", "Spotclass",
            "Magclass" };

    public static final String[] TOOLTIPS = COLUMNS.clone();

    public static final int COLUMN_COUNT = 12;

    public static final int COLUMN_INDEX_REPORT_TIME = 0;

    public static final int COLUMN_INDEX_OBSERVATORY = 1;

    public static final int COLUMN_INDEX_QUALITY = 2;

    public static final int COLUMN_INDEX_REGION = 3;

    public static final int COLUMN_INDEX_REPORT_LOCATION = 4;

    public static final int COLUMN_INDEX_LOCATION = 5;

    public static final int COLUMN_INDEX_CARLON = 6;

    public static final int COLUMN_INDEX_EXTENT = 7;

    public static final int COLUMN_INDEX_AREA = 8;

    public static final int COLUMN_INDEX_NUMSPOTS = 9;

    public static final int COLUMN_INDEX_SPOTCLASS = 10;

    public static final int COLUMN_INDEX_MAGCLASS = 11;

    // Report history constants

    public static final String[] HISTORY_COLUMNS = new String[] {
            "Region Report ID", "Type of Change", "Modified Field",
            "Value Before", "Current Value", "Time of Change", };

    public static final int COLUMN_INDEX_HIST_REGION_REPORT_ID = 0;

    public static final int COLUMN_INDEX_HIST_CHANGE_TYPE = 1;

    public static final int COLUMN_INDEX_HIST_MODIFIED_FIELD = 2;

    public static final int COLUMN_INDEX_HIST_VALUE_OLD = 3;

    public static final int COLUMN_INDEX_HIST_VALUE_NEW = 4;

    public static final int COLUMN_INDEX_HIST_TIME_OF_CHANGE = 5;

    // Format constants
    /**
     * A formatter with date format yyyy-MM-ddTHH:mm:ssZ
     */
    public static final ThreadLocal<SimpleDateFormat> DATE_TIME_FORMAT = new ThreadLocal<SimpleDateFormat>() {
        @Override
        public SimpleDateFormat initialValue() {
            SimpleDateFormat format = new SimpleDateFormat(
                    "yyyy-MM-dd'T'HH:mm:ss'Z'");
            format.setTimeZone(EditedRegionsConstants.TIME_ZONE_UTC);
            return format;
        }
    };

    /**
     * A formatter with date format dd MMM yyyy
     */
    public static final ThreadLocal<SimpleDateFormat> DATE_FORMAT = new ThreadLocal<SimpleDateFormat>() {
        @Override
        public SimpleDateFormat initialValue() {
            SimpleDateFormat format = new SimpleDateFormat("dd MMM yyyy");
            format.setTimeZone(EditedRegionsConstants.TIME_ZONE_UTC);
            return format;
        }
    };

}
