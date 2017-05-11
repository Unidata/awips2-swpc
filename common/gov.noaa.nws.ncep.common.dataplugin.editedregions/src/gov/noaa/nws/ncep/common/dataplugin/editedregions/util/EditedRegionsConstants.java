/**
 * This code has unlimited rights, and is provided "as is" by the National Centers 
 * for Environmental Prediction, without warranty of any kind, either expressed or implied, 
 * including but not limited to the implied warranties of merchantability and/or fitness 
 * for a particular purpose.
 * 
 * 
 * This code has been developed by the NCEP-SIB for use in the AWIPS2 system.
 * 
 */
package gov.noaa.nws.ncep.common.dataplugin.editedregions.util;

import java.util.TimeZone;

/**
 * Edited Regions Constants
 * 
 * @author jtravis
 * @version 1.0
 */

public class EditedRegionsConstants {

    /**
     * UTC Time Zone
     */
    public static final TimeZone TIME_ZONE_UTC = TimeZone.getTimeZone("UTC");

    /**
     * Plugin name
     */
    public static final String PLUGIN_NAME = "editedregions";

    /**
     * Default value for missing region
     */
    public static final Integer MISSING_REGION_VAL = null;

    /**
     * Default frequenct for goes xray events
     */
    public static final String GOES_XRA_EVENTS_FREQUENCY = "1-8A";

    public static final String VERSION_NUMBER = "1.0";

    public static final String EVENT_QUALITY_5 = "5";

    // Defines edited event types

    /**
     * @author jtravis
     *
     *         TODO - probably can delete...this was copied from the
     *         EditedEventsConstance class
     *
     */
    public static enum EventType {
        XRA("XRA"), // 0
        CME("CME"), // 1
        FLA("FLA"), // 2
        RNS("RNS"), // 3
        DSF("DSF"), // 4
        RSP("RSP"), // 5
        RBR("RBR"), // 6
        DALAS("DALAS"); // 7

        private String type;

        // Constructor
        EventType(String type) {
            this.type = type;
        }

        public String getType() {
            return type;
        }
    };

    /**
     * Event age
     * 
     * TODO - probably can delete...this was copied from the
     * EditedEventsConstance class
     * 
     */
    public static enum EVENT_AGE {
        NEW {
            public String toString() {
                return "NEW";
            }
        },
        COR {
            public String toString() {
                return "COR";
            }
        },
        OLD {
            public String toString() {
                return "OLD";
            }
        },
        DEL {
            public String toString() {
                return "DEL";
            }
        };
    }

    /**
     * Event status text values
     * 
     * TODO - probably can delete...this was copied from the
     * EditedEventsConstance class
     * 
     */
    public static enum EVENT_STATUS {
        DELETED { // StatusCode = 1
            public String toString() {
                return "DEL";
            }
        },
        LOSER { // StatusCode = 2
            public String toString() {
                return "-";
            }
        },
        CONTENDER { // StatusCode = 3
            public String toString() {
                return "=";
            }
        },
        WINNER { // StatusCode = 4
            public String toString() {
                return "+";
            }
        },
        SOLEBEST { // StatusCode = 5
            public String toString() {
                return "";
            }
        };
    }

    /**
     * The origin of where the SelectBestEventCommand is being called from
     * 
     * TODO - probably can delete...this was copied from the
     * EditedEventsConstance class
     */
    public static enum Origin {
        PROCESS_EVENTS, REBIN_EVENT, REVISIT_OLD_BIN
    }

}
