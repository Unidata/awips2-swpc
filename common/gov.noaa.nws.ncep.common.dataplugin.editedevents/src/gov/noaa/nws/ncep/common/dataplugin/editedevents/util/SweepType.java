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
package gov.noaa.nws.ncep.common.dataplugin.editedevents.util;


/**
 * Sweep Type
 * 
 * <pre>
 *
 * SOFTWARE HISTORY
 *
 * Date         Ticket#    Engineer    Description
 * ------------ ---------- ----------- --------------------------
 * Jan 13, 2016 R9583      sgurung     Initial creation
 *
 * </pre>
 *
 * @author sgurung
 * @version 1.0	
 */

public class SweepType {

	private String type = null;
	private Integer importance = null;
	private boolean plusEnable = false;
	
	
	public SweepType(String type, Integer importance, boolean plusEnabled) {
		this.type = type;
		this.importance = importance;
		this.plusEnable = plusEnabled;
	}
	
	public String getType() {
		return type;
	}

	public Integer getImportance() {
		return importance;
	}

	public boolean isPlusEnable() {
		return plusEnable;
	}

}
