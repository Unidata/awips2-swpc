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

    public static final String VERSION_NUMBER = "1.0";

    // TODO - may not need
    public static enum REGION_REPORT_STATUS {
    	ASSIGNED("Assigned"),
    	UNASSIGNED("Un-Assigned");
    	
    	private String type;
    	
    	REGION_REPORT_STATUS(String type) {
    		this.type = type;
    	}
    	
    	public String getType() {
    		return type;
    	}
    }
}
