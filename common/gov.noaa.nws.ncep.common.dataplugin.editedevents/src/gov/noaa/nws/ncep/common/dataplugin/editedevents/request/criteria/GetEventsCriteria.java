package gov.noaa.nws.ncep.common.dataplugin.editedevents.request.criteria;

/**
 * The criteria used for requesting events
 * 
 * <pre>
 * 
 * SOFTWARE HISTORY
 * 
 * Date         Ticket#    Engineer    Description
 * ------------ ---------- ----------- --------------------------
 * Mar 01, 2016 R9583     jtravis     Initial creation
 * 
 * </pre>
 * 
 * @author jtravis
 * @version 1.0
 */
public enum GetEventsCriteria {

	EVENTS_WITH_THIS_BIN_NUMBER_AND_DATE_RANGE,
	EVENTS_WITH_THIS_AGE_AND_DATE_RANGE,
	EVENTS_WITH_THIS_STATUS_TEXT_AND_DATE_RANGE,
	EVENTS_WITH_THIS_BIN_NUMBER_AND_AGE_AND_DATE_RANGE,
	EVENTS_WITH_THIS_BIN_NUMBER_AND_STATUS_TEXT_AND_DATE_RANGE,
	EVENTS_WITH_THIS_BIN_NUMBER,
	EVENTS_WITHOUT_THIS_BIN_NUMBER,
	EVENTS_BY_DATE_RANGE,
	EVENTS_AFTER_START_DATE,
	EVENTS_BEFORE_START_DATE,
	ALL
	
}
