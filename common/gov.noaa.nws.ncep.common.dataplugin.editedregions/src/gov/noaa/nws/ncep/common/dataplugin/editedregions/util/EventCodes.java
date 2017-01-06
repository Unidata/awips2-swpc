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
package gov.noaa.nws.ncep.common.dataplugin.editedregions.util;

import java.util.HashMap;

/**
 * Event Codes
 * 
 * <pre>
 *
 * SOFTWARE HISTORY
 *
 * Date         Ticket#    Engineer    Description
 * ------------ ---------- ----------- --------------------------
 * Jan 2016     R9583       sgurung     Initial creation
 *
 * </pre>
 *
 * @author sgurung
 * @version 1.0	
 */

public class EventCodes {

	public static HashMap<String, SweepType> sweepTypes = initSweepTypeCombinations();
	
	private static HashMap<String, SweepType> initSweepTypeCombinations() {
		
		HashMap<String, SweepType> sweepTypes = new HashMap<String, SweepType>();
		
		//base combinations
		sweepTypes.put("II", new SweepType("II", null, false));
		sweepTypes.put("III", new SweepType("III", null, false));
		sweepTypes.put("IV", new SweepType("IV", null, false));
		sweepTypes.put("V", new SweepType("V", null, false));
		sweepTypes.put("VI", new SweepType("VI", null, false));
		sweepTypes.put("VII", new SweepType("VII", null, false));
		sweepTypes.put("CTM", new SweepType("CTM", null, false));
	
		// base + digit = 1
		sweepTypes.put("II/1", new SweepType("II", 1, false));
		sweepTypes.put("III/1", new SweepType("III", 1, false));
		sweepTypes.put("IV/1", new SweepType("IV", 1, false));
		sweepTypes.put("V/1", new SweepType("V", 1, false));
		sweepTypes.put("VI/1", new SweepType("VI", 1, false));
		sweepTypes.put("VII/1", new SweepType("VII", 1, false));
		sweepTypes.put("CTM/1", new SweepType("CTM", 1, false));
		
		// base + digit = 2
		sweepTypes.put("II/2", new SweepType("II", 2, false));
		sweepTypes.put("III/2", new SweepType("III", 2, false));
		sweepTypes.put("IV/2", new SweepType("IV", 2, false));
		sweepTypes.put("V/2", new SweepType("V", 2, false));
		sweepTypes.put("VI/2", new SweepType("VI", 2, false));
		sweepTypes.put("VII/2", new SweepType("VII", 2, false));
		sweepTypes.put("CTM/2", new SweepType("CTM", 2, false));
		
		// base + digit = 3
		sweepTypes.put("II/3", new SweepType("II", 3, false));
		sweepTypes.put("III/3", new SweepType("III", 3, false));
		sweepTypes.put("IV/3", new SweepType("IV", 3, false));
		sweepTypes.put("V/3", new SweepType("V", 3, false));
		sweepTypes.put("VI/3", new SweepType("VI", 3, false));
		sweepTypes.put("VII/3", new SweepType("VII", 3, false));
		sweepTypes.put("CTM/3", new SweepType("CTM", 3, false));
		
		
		// base + digit = 1 include +
		sweepTypes.put("II/1+", new SweepType("II", 1, true));
		sweepTypes.put("III/1+", new SweepType("III", 1, true));
		sweepTypes.put("IV/1+", new SweepType("IV", 1, true));
		sweepTypes.put("V/1+", new SweepType("V", 1, true));
		sweepTypes.put("VI/1+", new SweepType("VI", 1, true));
		sweepTypes.put("VII/1+", new SweepType("VII", 1, true));
		sweepTypes.put("CTM/1+", new SweepType("CTM", 1, true));
		
		// base + digit = 2 include +
		sweepTypes.put("II/2+", new SweepType("II", 2, true));
		sweepTypes.put("III/2+", new SweepType("III", 2, true));
		sweepTypes.put("IV/2+", new SweepType("IV", 2, true));
		sweepTypes.put("V/2+", new SweepType("V", 2, true));
		sweepTypes.put("VI/2+", new SweepType("VI", 2, true));
		sweepTypes.put("VII/2+", new SweepType("VII", 2, true));
		sweepTypes.put("CTM/2+", new SweepType("CTM", 2, true));
		
		// base + digit = 3 include +
		sweepTypes.put("II/3+", new SweepType("II", 3, true));
		sweepTypes.put("III/3+", new SweepType("III", 3, true));
		sweepTypes.put("IV/3+", new SweepType("IV", 3, true));
		sweepTypes.put("V/3+", new SweepType("V", 3, true));
		sweepTypes.put("VI/3+", new SweepType("VI", 3, true));
		sweepTypes.put("VII/3+", new SweepType("VII", 3, true));
		sweepTypes.put("CTM/3+", new SweepType("CTM", 3, true));
		
		return sweepTypes;
	
	}

}
