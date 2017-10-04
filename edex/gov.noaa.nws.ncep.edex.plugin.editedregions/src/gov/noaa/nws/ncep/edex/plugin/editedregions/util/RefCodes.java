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
package gov.noaa.nws.ncep.edex.plugin.editedregions.util;

import java.util.Hashtable;

/**
 * Codes used for computing the spot class.
 * 
 * TODO this will need to be put in ref data tables in a subsequent release
 * 
 * <pre>
 *
 * SOFTWARE HISTORY
 *
 * Date         Ticket#    Engineer    Description
 * ------------ ---------- ----------- --------------------------
 * Sep 28, 2017            jtravis     Initial creation
 *
 * </pre>
 *
 * @author jtravis
 */
public class RefCodes {

    private static final Hashtable<Integer, String> zurichCodes = new Hashtable<Integer, String>();

    private static final Hashtable<Integer, String> penumbraCodes = new Hashtable<Integer, String>();

    private static final Hashtable<Integer, String> compactCodes = new Hashtable<Integer, String>();

    private static final Hashtable<Integer, String> magneticCodes = new Hashtable<Integer, String>();

    /**
     * 
     */
    public RefCodes() {
        // TODO Auto-generated constructor stub
    }

    // populate the codes
    static {

        // populate zurich codes
        zurichCodes.put(1, "A");
        zurichCodes.put(2, "B");
        zurichCodes.put(3, "C");
        zurichCodes.put(4, "D");
        zurichCodes.put(5, "E");
        zurichCodes.put(6, "F");
        zurichCodes.put(7, "H");

        // populate penumbra codes
        penumbraCodes.put(0, "x");
        penumbraCodes.put(1, "r");
        penumbraCodes.put(2, "s");
        penumbraCodes.put(3, "a");
        penumbraCodes.put(4, "h");
        penumbraCodes.put(5, "k");

        // populate compact codes
        compactCodes.put(0, "x");
        compactCodes.put(7, "o");
        compactCodes.put(8, "i");
        compactCodes.put(9, "c");

        // populate magnetic codes
        magneticCodes.put(1, "A");
        magneticCodes.put(2, "B");
        magneticCodes.put(3, "BG");
        magneticCodes.put(4, "G");
        magneticCodes.put(5, "BD");
        magneticCodes.put(6, "BDG");
        magneticCodes.put(7, "GD");

    }

    /**
     * Obtain the Alphabetic Zurich code given the numeric Zurich code
     * 
     * If invalid numeric code supplied the return value will be a string
     * specifying that no zurich value was found for that numeric code
     * 
     * @param value
     * 
     * @return code
     */
    public static String getZurichCode(int value) {

        String code = null;

        if (!zurichCodes.containsKey(value)) {

            code = "NO ZURICH CODE FOR VALUE: " + value;

        } else {

            code = zurichCodes.get(value);

        }

        return code;

    }

    /**
     * Obtain the Alphabetic Penumbra code given the numeric Penumbra code
     * 
     * If invalid numeric code supplied the return value will be a string
     * specifying that no Penumbra value was found for that numeric code
     * 
     * 
     * @param value
     * 
     * @return code
     */
    public static String getPenumbraCode(int value) {

        String code = null;

        if (!penumbraCodes.containsKey(value)) {

            code = "NO PENUMBRA CODE FOR VALUE: " + value;

        } else {

            code = penumbraCodes.get(value);

        }

        return code;

    }

    /**
     * Obtain the Alphabetic Compact code given the numeric Compact code
     * 
     * If invalid numeric code supplied the return value will be a string
     * specifying that no Compact value was found for that numeric code
     * 
     * @param value
     * 
     * @return code
     */
    public static String getCompactCode(int value) {

        String code = null;

        if (!compactCodes.containsKey(value)) {

            code = "NO COMPACT CODE FOR VALUE: " + value;

        } else {

            code = compactCodes.get(value);

        }

        return code;

    }

    /**
     * Obtain the Alphabetic Magnetic class given the numeric Magnetic Code
     * 
     * If invalid numeric code supplied the return value will be a string
     * specifying that no Compact value was found for that numeric code
     * 
     * @param value
     * 
     * @return code
     */
    public static String getMagneticCode(int value) {
        String code = null;

        if (!magneticCodes.containsKey(value)) {

            code = "NO MAGNETIC CODE FOR VALUE: " + value;

        } else {

            code = magneticCodes.get(value);

        }

        return code;
    }

}
