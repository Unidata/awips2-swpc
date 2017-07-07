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
}
