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

    public static final String[] COLUMNS = new String[] { "Date", "Obstime",
            "Obs", "Q", "00Z Loc", "Rpt Loc", "Lo", "LL", "Area", "Num Spots",
            "Spot Class", "Mag Class", "Mag Str", "Region" };

    public static final int COLUMN_INDEX_BIN = 0;

    public static final int COLUMN_INDEX_BEGINQ = 1;

    public static final int COLUMN_INDEX_BEGIN_DATE = 2;

    public static final int COLUMN_INDEX_BEGIN_TIME = 3;

    public static final int COLUMN_INDEX_MAXQ = 4;

    public static final int COLUMN_INDEX_MAX_TIME = 5;

    public static final int COLUMN_INDEX_ENDQ = 6;

    public static final int COLUMN_INDEX_END_TIME = 7;

    public static final int COLUMN_INDEX_OBSERVATORY = 8;

    public static final int COLUMN_INDEX_QUALITY = 9;

    public static final int COLUMN_INDEX_TYPE = 10;

    public static final int COLUMN_INDEX_LOCATION = 11;

    public static final int COLUMN_INDEX_FREQUENCY = 12;

    public static final int COLUMN_INDEX_PARTICULARS_1 = 13;

    public static final int COLUMN_INDEX_PARTICULARS_2 = 14;

    public static final int COLUMN_INDEX_PARTICULARS_3 = 15;

    public static final int COLUMN_INDEX_PARTICULARS_4 = 16;

    public static final int COLUMN_INDEX_PARTICULARS_5 = 17;

    public static final int COLUMN_INDEX_PARTICULARS_6 = 18;

    public static final int COLUMN_INDEX_PARTICULARS_7 = 19;

    public static final int COLUMN_INDEX_PARTICULARS_8 = 20;

    public static final int COLUMN_INDEX_REGION = 21;

    public static final int COLUMN_INDEX_AGE = 22;

    public static final int COLUMN_INDEX_STATUS_TEXT = 23;

    public static final String COLUMN_HEADER_BIN = "Bin";

    public static final String COLUMN_HEADER_BEGINQ = " ";

    public static final String COLUMN_HEADER_BEGIN_DATE = " BegDate ";

    public static final String COLUMN_HEADER_BEGIN_TIME = " Beg ";

    public static final String COLUMN_HEADER_MAXQ = "  ";

    public static final String COLUMN_HEADER_MAX_TIME = " Max ";

    public static final String COLUMN_HEADER_ENDQ = "  ";

    public static final String COLUMN_HEADER_END_TIME = " End ";

    public static final String COLUMN_HEADER_OBSERVATORY = " Obs ";

    public static final String COLUMN_HEADER_QUALITY = " Q ";

    public static final String COLUMN_HEADER_TYPE = " Type ";

    public static final String COLUMN_HEADER_LOCATION = " Loc ";

    public static final String COLUMN_HEADER_FREQUENCY = " Freq ";

    public static final String COLUMN_HEADER_PARTICULARS_1 = " Part 1 ";

    public static final String COLUMN_HEADER_PARTICULARS_2 = " Part 2 ";

    public static final String COLUMN_HEADER_PARTICULARS_3 = " Part 3 ";

    public static final String COLUMN_HEADER_PARTICULARS_4 = " Part 4 ";

    public static final String COLUMN_HEADER_PARTICULARS_5 = " Part 5 ";

    public static final String COLUMN_HEADER_PARTICULARS_6 = " Part 6 ";

    public static final String COLUMN_HEADER_PARTICULARS_7 = " Part 7 ";

    public static final String COLUMN_HEADER_PARTICULARS_8 = " Part 8 ";

    public static final String COLUMN_HEADER_REGION = " Reg ";

    public static final String COLUMN_HEADER_AGE = " Age ";

    public static final String COLUMN_HEADER_STATUS = " Status ";

    public static final String VIEW_OPTION_ALL_REPORTS = "All Reports";

    public static final String VIEW_OPTION_BEST_ONLY = "Best Only";

    public static final String VIEW_OPTION_CONTENDERS_ONLY = "Contenders Only";

    public static final String VIEW_OPTION_NEW_REPORTS = "New Reports";

    public static final String SORTBY_OPTION_BIN_DATE = "Bin Date";

    public static final String SORTBY_OPTION_TIME = "Time";

    public static final String SORTBY_OPTION_TYPE = "Type";

    public static final String SORTBY_OPTION_REGION = "Region";

    public static final String SORTBY_OPTION_OBSERVATORY = "Observatory";

    public static final String TOOL_TIP_BIN = "Event Bin Number which is used to group \ndifferent events that are determined \nto be from the same physical event";

    public static final String TOOL_TIP_BEGIN_DATE = "Start date of the event";

    public static final String TOOL_TIP_BEGIN_TIME = "Start time of the event";

    public static final String TOOL_TIP_BEGINQ = "Qualifier for Begin Date/Time";

    public static final String TOOL_TIP_MAXQ = "Qualifier for Max Date/Time";

    public static final String TOOL_TIP_MAX_TIME = "Event maximum time";

    public static final String TOOL_TIP_ENDQ = "Qualifier for End Date/Time";

    public static final String TOOL_TIP_END_TIME = "Event end time";

    public static final String TOOL_TIP_OBSERVATORY = "Three letter abbreviation of the observatory \nthat provide the observation";

    public static final String TOOL_TIP_QUALITY = "Quality indicator";

    public static final String TOOL_TIP_TYPE = "Abbreviation indicating the type of event";

    public static final String TOOL_TIP_LOCATION = "Heliographic location for the event";

    public static final String TOOL_TIP_FREQUENCY = "Frequency (or frequency range) of the \nobserving instrument used for the event";

    public static final String TOOL_TIP_PARTICULARS_1_10 = "Particulars 1 through Particulars 10 contain \n parameters specific to each event";

    public static final String TOOL_TIP_REGION = "The NOAA region number that is associated \n with the event";

    public static final String TOOL_TIP_AGE = "Indicative of processing status of the event. May have the following values: \n'NEW' = Newly added event \n'OLD' = Event was previously saved \n'COR' = A correction has been issued \n'DEL' = A deletion has been issued";

    public static final String TOOL_TIP_STATUS = "A text value indicating event status: \n'DEL' = Deletion\n'-' = Loser\n'=' = Contender\n'+' = Best of Group/Winner\n' ' = Sole Best";

}
