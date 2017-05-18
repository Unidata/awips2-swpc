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

    public static final String[] COLUMNS = new String[] { "Station",
            "Observatory", "Type", "Quality", "Region", "Latitude",
            "Report Longitude", "Longitude", "Report Location", "Location",
            "Carlon", "Extent", "Area", "Numspots", "Zurich", "Penumbra",
            "Compact", "Spotclass", "Magcode", "Magclass", "Obsid",
            "Report Status", "Valid Spot Class" };

    public static final String[] TOOLTIPS = COLUMNS.clone();

    public static final int COLUMN_COUNT = 23;

    public static final int COLUMN_INDEX_STATION = 0;

    public static final int COLUMN_INDEX_OBSERVATORY = 1;

    public static final int COLUMN_INDEX_TYPE = 2;

    public static final int COLUMN_INDEX_QUALITY = 3;

    public static final int COLUMN_INDEX_REGION = 4;

    public static final int COLUMN_INDEX_LATITUDE = 5;

    public static final int COLUMN_INDEX_REPORT_LONGITUDE = 6;

    public static final int COLUMN_INDEX_LONGITUDE = 7;

    public static final int COLUMN_INDEX_REPORT_LOCATION = 8;

    public static final int COLUMN_INDEX_LOCATION = 9;

    public static final int COLUMN_INDEX_CARLON = 10;

    public static final int COLUMN_INDEX_EXTENT = 11;

    public static final int COLUMN_INDEX_AREA = 12;

    public static final int COLUMN_INDEX_NUMSPOTS = 13;

    public static final int COLUMN_INDEX_ZURICH = 14;

    public static final int COLUMN_INDEX_PENUMBRA = 15;

    public static final int COLUMN_INDEX_COMPACT = 16;

    public static final int COLUMN_INDEX_SPOTCLASS = 17;

    public static final int COLUMN_INDEX_MAGCODE = 18;

    public static final int COLUMN_INDEX_MAGCLASS = 19;

    public static final int COLUMN_INDEX_OBSID = 20;

    public static final int COLUMN_INDEX_REPORT_STATUS = 21;

    public static final int COLUMN_INDEX_VALID_SPOT_CLASS = 22;
}
