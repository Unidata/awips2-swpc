/**
 * This code has unlimited rights, and is provided "as is" by the National Centers 
 * for Environmental Prediction, without warranty of any kind, either expressed or implied, 
 * including but not limited to the implied warranties of merchantability and/or fitness 
 * for a particular purpose.
 * 
 * This code has been developed by the NCEP-SIB for use in the AWIPS2 system.
 * 
 **/
package gov.noaa.nws.ncep.viz.ui.editedevents.util;

import gov.noaa.nws.ncep.common.dataplugin.editedevents.Event;
import gov.noaa.nws.ncep.common.dataplugin.editedevents.exception.EditedEventsException;
import gov.noaa.nws.ncep.common.dataplugin.editedevents.gateway.Gateway;
import gov.noaa.nws.ncep.common.dataplugin.editedevents.request.AddEventRequest;
import gov.noaa.nws.ncep.common.dataplugin.editedevents.request.CreateCompositeEventRequest;
import gov.noaa.nws.ncep.common.dataplugin.editedevents.request.GetEventTypesRequest;
import gov.noaa.nws.ncep.common.dataplugin.editedevents.request.GetStationsRequest;
import gov.noaa.nws.ncep.common.dataplugin.editedevents.response.AddEventResponse;
import gov.noaa.nws.ncep.common.dataplugin.editedevents.response.CreateCompositeEventResponse;
import gov.noaa.nws.ncep.common.dataplugin.editedevents.response.GetEventTypesResponse;
import gov.noaa.nws.ncep.common.dataplugin.editedevents.response.GetStationsResponse;
import gov.noaa.nws.ncep.common.dataplugin.editedevents.results.AddEventResults;
import gov.noaa.nws.ncep.common.dataplugin.editedevents.results.CreateCompositeEventResults;
import gov.noaa.nws.ncep.common.dataplugin.editedevents.results.GetEventTypesResults;
import gov.noaa.nws.ncep.common.dataplugin.editedevents.results.GetStationsResults;
import gov.noaa.nws.ncep.common.dataplugin.editedevents.util.EditedEventsConstants.EventType;
import gov.noaa.nws.ncep.common.dataplugin.ghcd.GenericHighCadenceDataRecord;
import gov.noaa.nws.ncep.common.dataplugin.ghcd.product.GenericHighCadenceDataField;
import gov.noaa.nws.ncep.common.dataplugin.ghcd.product.GenericHighCadenceDataItem;
import gov.noaa.nws.ncep.common.dataplugin.ghcd.query.GenericHighCadenceDataReqMsg;
import gov.noaa.nws.ncep.common.dataplugin.ghcd.query.GenericHighCadenceDataReqMsg.GenericHighCadenceDataReqType;
import gov.noaa.nws.ncep.common.swpcrefdb.Satellite;
import gov.noaa.nws.ncep.common.swpcrefdb.request.SatelliteTrackingStatusRequest;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Vector;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;

import com.raytheon.uf.common.dataquery.requests.DbQueryRequest;
import com.raytheon.uf.common.dataquery.requests.RequestConstraint;
import com.raytheon.uf.common.dataquery.responses.DbQueryResponse;
import com.raytheon.uf.viz.core.exception.VizException;
import com.raytheon.uf.viz.core.requests.ThriftClient;

/**
 * Utility class to support the EnterEventDialog class
 * 
 * <pre>
 *
 * SOFTWARE HISTORY
 *
 * Date         Ticket#    Engineer    Description
 * ------------ ---------- ----------- --------------------------
 * Feb 1, 2016            sgurung     Initial creation
 *
 * </pre>
 *
 * @author sgurung
 * @version 1.0	
 */

public class EnterEventUtil {
	
	public static final String[] begTimeQOptions_1 = { "", "A", "B" };
	
	public static final String[] begTimeQOptions_2 = { "", "U" };
	    
	public static final String[] maxTimeQOptions_1 = { "U" };
    
	public static final String[] maxTimeQOptions_2 = { "" };
	    
	public static final String[] endTimeQOptions_1 = { "", "A", "B" };
	
	public static final String[] endTimeQOptions_2 = { "", "U" };
	    
	public static final String[] qualityOptions_1 = { "1", "2", "3", "4", "5" };
    
	public static final String[] qualityOptions_2 = { "G", "U" };
	
	public static final String DEFAULT_XRA_FREQUENCY = "1-8A";
	
	public static final String COMPOSITE_EVENT = "COM";
	
	public static final String LOCATION_REGEX = "^[N|S]{1}[0-9]{1}[0-8]{1}[E|W]{1}[0-9]{1}[0-8]{1}";
	
	public static final String XRA_PARTICULARS_1_REGEX = "^[A|B|C|M|X][0-9]{1,2}+\\.[0-9]{1}";
	
	public static final String FLA_PARTICULARS_1_REGEX = "^[S|1|2|3|4]{1}[F|N|B]{1}";
	
	public static final String RSP_PARTICULARS_1_REGEX = "^[I|V|C]{1}[I|V|T]?[I|M]?/[1|2|3]{1}[+]?";	
	
    private final static String PLUGIN_NAME = "pluginName";

    private final static String GENERIC_HIGH_CADENCE_DATA_PLUGIN_NAME = "ghcd";
    
    private final static String GHCD_INT_FLUX_INSTRUMENT = "xrs";
    
    private final static String GHCD_INT_FLUX_DATATYPE = "xray";
    
    private final static String GHCD_INT_FLUX_DATA_RESOLUTION_UNIT = "minutes";
    
    private final static Integer GHCD_INT_FLUX_DATA_RESOLUTION_VALUE = 1;
    
    private final static String GHCD_INT_FLUX_X_LONG = "x_long";
	
	public static enum XRA_VALID_X_RAY_CLASSES {
		A {
			public String toString() {
		        return "A";
			}
		},
		B {
			public String toString() {
		        return "B";
			}
		},
		C{
			public String toString() {
		        return "C";
			}
		},
		M {
			public String toString() {
		        return "M";
			}
		},
		X {
			public String toString() {
		        return "X";
			}
		};
	}
	
	public static enum FLA_VALID_IMPORTANCE {
		S {
			public String toString() {
		        return "S";
			}
		},
		ONE {
			public String toString() {
		        return "1";
			}
		},
		TWO {
			public String toString() {
		        return "2";
			}
		},
		THREE {
			public String toString() {
		        return "3";
			}
		},
		FOUR {
			public String toString() {
		        return "4";
			}
		};
	}
	
	public static enum FLA_VALID_BRIGHTNESS {
		F {
			public String toString() {
		        return "F";
			}
		},
		N {
			public String toString() {
		        return "N";
			}
		},
		B {
			public String toString() {
		        return "B";
			}
		};
	}
	
	public static enum EVENT_TYPE {
		XRA {
		    public String toString() {
		        return "XRA";
		    }
		},
		FLA {
		    public String toString() {
		        return "FLA";
		    }
		},
		BSL {
		    public String toString() {
		        return "BSL";
		    }
		},
		DSF {
		    public String toString() {
		        return "DSF";
		    }
		},
		EPL {
		    public String toString() {
		        return "EPL";
		    }
		},
		LPS {
		    public String toString() {
		        return "LPS";
		    }
		},
		SPY {
		    public String toString() {
		        return "SPY";
		    }
		},
		DSD {
		    public String toString() {
		        return "DSD";
		    }
		},
		RNS {
		    public String toString() {
		        return "RNS";
		    }
		},
		RBR {
		    public String toString() {
		        return "RBR";
		    }
		},
		RSP {
		    public String toString() {
		        return "RSP";
		    }
		},
		PRO {
		    public String toString() {
		        return "PRO";
		    }
		},
		CME {
		    public String toString() {
		        return "CME";
		    }
		}
	}
	
	/**
	 * 
	 */
	public EnterEventUtil() {
		// TODO Auto-generated constructor stub
	}
	
	/**
     * Send a request to persist the new event/composite event
     * 
     * @return eventID the event id of the event that has been persisted/updated
     */
    public static Integer saveNewEvent(Event event) { 
      
    	Integer eventID = null;
    	
    	if (event.isComposite()) {
    		eventID = saveCompositeEvent(event);
    	} else {
    		eventID = saveEvent(event);
    	}
    	
        return eventID;
    	
    }
    
    
    /**
     * Send a request to persist the new event
     * 
     * @return eventID the event id of the event that has been persisted/updated
     */
    public static Integer saveEvent(Event event) { 
      
    	AddEventRequest request = new AddEventRequest();
    	request.setEvent(event);
    	request.setEventID(event.getId());
    	    	
    	AddEventResponse response = null;
    	gov.noaa.nws.ncep.common.dataplugin.editedevents.Event persistedEvent = null;
    	Integer eventID = null;
    	
    	 try {
         	
         	if (request.isValid()) {
         		response = Gateway.getInstance().submit(request);
 				
 				if (!response.hasErrors() && response.getResults() != null) {
 					persistedEvent = ((AddEventResults) response.getResults()).getEvent();
 					request = (AddEventRequest) response.getRequest();
 					eventID = request.getEventID();
 				}
         	}
 			
 		} catch (EditedEventsException e1) {
             // TODO Auto-generated catch block
             e1.printStackTrace();
 		}
    	 
        return eventID;
    	
    }
    
    /**
     * Send a request to persist the new composite event
     * 
     * @return eventID the event id of the event that has been persisted/updated
     */
    public static Integer saveCompositeEvent(Event event) { 
      
    	CreateCompositeEventRequest request = new CreateCompositeEventRequest();
    	request.setEvent(event);
    	request.setEventID(event.getId());
    	    	
    	CreateCompositeEventResponse response = null;
    	gov.noaa.nws.ncep.common.dataplugin.editedevents.Event persistedEvent = null;
    	Integer eventID = null;
    	
    	 try {
         	
         	if (request.isValid()) {
         		response = Gateway.getInstance().submit(request);
 				
 				if (!response.hasErrors() && response.getResults() != null) {
 					persistedEvent = ((CreateCompositeEventResults) response.getResults()).getEvent();
 					eventID = ((CreateCompositeEventResults) response.getResults()).getEventID();
 				}
         	}
 			
 		} catch (EditedEventsException e1) {
             // TODO Auto-generated catch block
             e1.printStackTrace();
 		}
    	 
        return eventID;
    	
    }
    
    /**
     * Returns the list of all the GOES stations in the db
     * 
     * @return String[]
     */
    public static String[] getGOESObservatories() {
    	
    	String[] stations = {""};
    	Vector<String> goesStations = null;
        
    	GetStationsRequest getStationsRequest = new GetStationsRequest();
    	getStationsRequest.setStationType("GOES");
        
    	GetStationsResponse getStationsResponse = null;
        
        try {
        	
        	if (getStationsRequest.isValid()) {
				getStationsResponse = Gateway.getInstance().submit(getStationsRequest);
				
				if (!getStationsResponse.hasErrors() && getStationsResponse.getResults() != null) {
					goesStations = ((GetStationsResults) getStationsResponse.getResults()).getStationDesignations();
				}
        	}
			
		} catch (EditedEventsException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
		}
        
        if (goesStations != null) {
        	stations = Arrays.copyOf(goesStations.toArray(), goesStations.toArray().length, String[].class); 
        }
        
        return stations;
    }
    
    /**
     * Returns the list of all the event types in the db
     * 
     * @return String[]
     */
    public static String[] getEventTypes() {
    	
    	String[] reportTypes = {""};
    	
    	Vector<String> eventTypes = null;
        
    	GetEventTypesRequest request = new GetEventTypesRequest();
        
    	GetEventTypesResponse response = null;
        
        try {
        	
        	if (request.isValid()) {
				response = Gateway.getInstance().submit(request);
				
				if (!response.hasErrors() && response.getResults() != null) {
					eventTypes = ((GetEventTypesResults) response.getResults()).getEventTypes();
				}
        	}
			
		} catch (EditedEventsException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
		}
        
        if (eventTypes != null) {
        	reportTypes = Arrays.copyOf(eventTypes.toArray(), eventTypes.toArray().length, String[].class);       	
        }   
        
        return reportTypes;
    }
    
    /**
     * Returns the list of all the event coded types in the db
     * 
     * @return String[]
     */
    public static String[] getCodedTypes(String type) {
    	
    	String[] codedTypes = {""};
    	
    	Vector<String> codes = null;
        
    	GetEventTypesRequest request = new GetEventTypesRequest();
        
    	GetEventTypesResponse response = null;
        
        try {
        	
        	if (request.isValid()) {
				response = Gateway.getInstance().submit(request);
				
				if (!response.hasErrors() && response.getResults() != null) {
					codes = ((GetEventTypesResults) response.getResults()).getCodedTypes(type);
				}
        	}
			
		} catch (EditedEventsException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
		}
        
        if (codes != null) {
        	codedTypes = Arrays.copyOf(codes.toArray(), codes.toArray().length, String[].class);       	
        }   
        
        return codedTypes;
    }
    
    /**
     * Returns the frequency value associated with a given 
     * event type and code combination.
     * 
     * @paramm type
     * @param codedType
     * @return String
     */
    public static String getFrequency(String type, int codedType) {
    	
    	String frequency = null;
    	        
    	GetEventTypesRequest request = new GetEventTypesRequest();
        
    	GetEventTypesResponse response = null;
        
        try {
        	
        	if (request.isValid()) {
				response = Gateway.getInstance().submit(request);
				
				if (!response.hasErrors() && response.getResults() != null) {
					frequency = ((GetEventTypesResults) response.getResults()).getFrequency(type, codedType);
				}
        	}
			
		} catch (EditedEventsException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
		}
        
        return ((frequency!=null) ? frequency : "");
    }
    
    /**
     * Returns beginTimeQ options for a given report type
     * 
     * @param reportType
     * @return String []
     */
    public static String[] getBeginTimeQualifierOptions(String reportType) {
    	
    	String[] options = { "" };
    	
    	switch(reportType) {
    	case "XRA":    		
    	case "FLA":
		case "BSL":
		case "DSF":
		case "EPL":
		case "LPS":
		case "SPY":
		case "DSD":
    		options = begTimeQOptions_1;
    		break;
    	case "RBR":    		
    	case "RNS":
    	case "RSP":
    		options = begTimeQOptions_2;
    		break;
        default:
        	break;
    	}
    	
    	return options;
    	
    }
    
    /**
     * Returns endTimeQ options for a given report type
     * 
     * @param reportType
     * @return String []
     */
    public static String[] getEndTimeQualifierOptions(String reportType) {
    	
    	String[] options = { "" };
    	
    	switch(reportType) {
    	case "XRA":    		
    	case "FLA":
		case "BSL":
		case "DSF":
		case "EPL":
		case "LPS":
		case "SPY":
		case "DSD":
    		options = endTimeQOptions_1;
    		break;
    	case "RBR":    		
    	case "RNS":
    	case "RSP":
    		options = endTimeQOptions_2;
    		break;
        default:
        	break;
    	}
    	
    	return options;
    	
    }
    
    /**
     * Returns maxTimeQ options for a given report type
     * 
     * @par
     * am reportType
     * @return String []
     */public static String[] getMaxTimeQualifierOptions(String reportType) {
    	
    	String[] options = { "" };
    	
    	switch(reportType) {
    	case "XRA":    		
    	case "FLA":
    		options = maxTimeQOptions_1;
    		break;
    	case "RBR":    		
    	case "RNS":
    		options = maxTimeQOptions_2;
    		break;
        default:
        	break;
    	}
    	
    	return options;
    	
    }
    
     /**
      * Returns quality value options for a given report type
      * 
      * @param reportType
      * @return String []
      */
     public static String[] getQualityOptions(String reportType) {
    	
    	String[] options = { "" };
    	
    	switch(reportType) {
    	case "XRA":    		
    	case "FLA":
    	case "CME":
		case "BSL":
		case "DSF":
		case "EPL":
		case "LPS":
		case "SPY":
		case "DSD":
    		options = qualityOptions_1;
    		break;
    	case "RBR":    		
    	case "RNS":
    	case "RSP":
    		options = qualityOptions_2;
    		break;
        default:
        	break;
    	}
    	
    	return options;
    	
    }
    
    /**
     * Verify if the supplied string matches the supplied regular expression
     * 
     * @param regex
     * @param stringToMatch
     * @return boolean
     */
    public static boolean matchRegex(String regex, String stringToMatch) {
    	
    	boolean isMatch = false;
    	
    	Pattern pattern = Pattern.compile(regex);
    	
    	Matcher matcher = pattern.matcher(stringToMatch);
    	isMatch = matcher.matches();
    	
    	return  isMatch; 
    }
    
    /**
	 * Format a String based on a pattern
	 * 
	 * @param pattern
	 * @param value
	 * 
	 * @return String
	 */
	public static String format(String pattern, String value) {

		DecimalFormat myFormatter = new DecimalFormat(pattern);
		String output = myFormatter.format(Double.valueOf(value));

		return output;

	}
	
	/**
     * Check if the specified text is a valid number.
     * 
     * @param text
     *            Text to test.
     * @return Integer
     */
	public static Integer textIsAnInteger(String text) {
        try {
            Integer num = Integer.parseInt(text);
            return num;
        } catch (NumberFormatException nfe) {
            return null;
        }
    }
    
    /**
     * Check if the specified text is a valid double value.
     * 
     * @param text
     *            Text to test.
     * @return double
     */
    public static Double textIsADouble(String text) {
        try {
            Double dbl = Double.parseDouble(text);
            return dbl;
        } catch (NumberFormatException nfe) {
            return null;
        }
    }
    
    /**
     * Check if the specified date text represents a valid date
     * 
     * @param date
     * @return Calendar
     */
    public static Calendar textIsADate(String date, SimpleDateFormat dateFormat) {
    	
    	Calendar cal = Calendar.getInstance();
		
		 try {
             Date dt = dateFormat.parse(date);
             cal.clear();
             cal.setTimeInMillis(dt.getTime());
         } catch (ParseException e) {
             return null;
         }
		return cal;
    }
	
	/**
     * Particulars10 is the numeric equivalent of Particulars1
     * 
     * @param particulars1
     * @return String
     */
    public static String deriveParticulars10(String particulars1, String selectedEventType) {
    	
    	String particulars10 = null;
		
		switch (EnterEventUtil.EVENT_TYPE.valueOf(selectedEventType)) {
			case XRA:				
				// Particulars10 equal to the numeric equivalent of the max flux   
				
				String xrayClass = String.valueOf(particulars1.charAt(0));
				String midValue = particulars1.substring(1, 2);
				Double part10Dbl = null; 
				int divideBy = 1;
			    					
				switch (EnterEventUtil.XRA_VALID_X_RAY_CLASSES.valueOf(xrayClass)) {
					case A:
						divideBy = 10000;	
						break;
					case B:
						divideBy = 100000;		
						break;
					case C:
						divideBy = 1000000;		
						break;
					case M:
						divideBy = 10000000;	
						break;
					case X:
						divideBy = 100000000;
						break;
					default:
						break;
				}

				part10Dbl = Double.parseDouble(midValue) / divideBy;
				particulars10 = EnterEventUtil.format("0.0E00", String.valueOf(part10Dbl));
				break;
			case FLA:
				// set Particulars10 equal to its numeric equivalent
				
				String importance = String.valueOf(particulars1.charAt(0)).toUpperCase();
				
				switch (importance) {
					case "1":
						importance = "ONE";	
						break;
					case "2":
						importance = "TWO";	
						break;
					case "3":
						importance = "THREE";	
						break;
					case "4":
						importance = "FOUR";	
						break;
					default:					
						break;
				}
				
				String brightness = String.valueOf(particulars1.charAt(1)).toUpperCase();
				
				Integer part10Int = null;
				
				// importance
				switch (EnterEventUtil.FLA_VALID_IMPORTANCE.valueOf(importance)) {
					case S:
						part10Int = 10;	
						break;
					case ONE:
						part10Int = 20;	
						break;
					case TWO:
						part10Int = 30;	
						break;
					case THREE:
						part10Int = 40;	
						break;
					case FOUR:
						part10Int = 50;	
						break;
					default:					
						break;
				}
				
				// brightness 
				switch (EnterEventUtil.FLA_VALID_BRIGHTNESS.valueOf(brightness)) {
					case F:
						part10Int += 1;	
						break;
					case N:
						part10Int += 2;	
						break;
					case B:
						part10Int += 3;	
						break;					
					default:					
						break;
				}		
				particulars10 = Integer.toString(part10Int);
				
			default:
				break;
	    }
		
    	return particulars10;
    }
      
    /**
     * Display a information message dialog.
     * 
     * @param message
     *            Message to display in the dialog.
     */
    public static void displayMessageDialog(Shell shell, String message) {
        MessageBox mb = new MessageBox(shell, SWT.ICON_INFORMATION | SWT.OK);
        mb.setText("Information");
        mb.setMessage(message);
        mb.open();
    }
    
    /**
     * Display a error message dialog.
     * 
     * @param message
     *            Message to display in the dialog.
     */
    public static void displayErrorMessageDialog(Shell shell, String message) {
        MessageBox mb = new MessageBox(shell, SWT.ICON_ERROR | SWT.OK);
        mb.setText("Error");
        mb.setMessage(message);
        mb.open();
    }

    public static boolean validateNotNull(Shell shell, Calendar date, String fieldName) {
    	if(date == null) {
    		displayErrorMessageDialog(shell, fieldName + " is a required field.");
    		return false;    		
    	}  else {
    		return true;
    	}
    }
    
    public static boolean validateDateTime(Shell shell, Calendar beginDate, Calendar maxDate, Calendar endDate) {
    	
    	boolean isValid = true;
    	String errorMessage = "";
    	
    	if (!validateNotNull(shell, beginDate, "Begin Date")) {
     		isValid = false;
     	} 
    	 
    	if (!validateNotNull(shell, endDate, "End Date")) {
    		isValid = false;
    	} 
    	
    	// ensure begin date/time is not greater than current date/time
    	if (beginDate.after(Calendar.getInstance())) {
    		errorMessage += "Will not accept Begin Date/time value > current date/time.\n";
    		isValid = false;    
    	}
    	
    	// ensure end date/time is not greater than current date/time
    	if (endDate.after(Calendar.getInstance())) {
    		errorMessage += "Will not accept End Date/time value > current date/time.\n";
    		isValid = false;      
    	}
    	
    	// Check date/times to ensure proper ordering (i.e., begindate <= maxdate <=enddate)		
		if (maxDate == null) {
			if (beginDate.after(endDate)) {		
				errorMessage += "Begin Date cannot be later than End date. Please correct it prior to saving the event.\n";
				isValid = false;    
			}	
		} else {
			if (beginDate.after(maxDate)) {		
				errorMessage += "Begin Date cannot be later than Max date. Please correct it prior to saving the event.\n";
				isValid = false;    
			}
			if (maxDate.after(endDate)) {
				errorMessage += "Max Date cannot be later than End date. Please correct it prior to saving the event.\n";
				isValid = false;    
			}
		}	
    	
		if (!isValid) {
			displayErrorMessageDialog(shell, errorMessage);
		}
		
    	return isValid;
    }
    
    /**
     * Validate the frequency value. 
     * 
     * @param frequency
     * @return boolean
     */
    public static boolean validateFrequency(Shell shell, String frequency, String codedType) {
    	
    	boolean isValid = true;
    	int codedTypeInt = Integer.parseInt(codedType);
    	
    	if (frequency.isEmpty() && codedTypeInt >= 30 && codedTypeInt <=54) {
    		
			displayErrorMessageDialog(shell, "Radio reports require a frequency. Please enter a frequency value.");
			isValid = false;
    		
    	}
    	
    	return isValid;    	
    }
    
    
    /**
     * Validate the location value. Expected format = LnnLnn with the
     * first L=[N|S], the first nn=00-90, the second L = [E|W]
     * and the second nn=00-90.
     * 
     * @param location
     * @return boolean
     */
    public static boolean validateLocation(Shell shell, String location) {
    	
    	boolean isValid = true;
    	
    	if (!location.isEmpty()) {
    		
    		isValid = EnterEventUtil.matchRegex(EnterEventUtil.LOCATION_REGEX, location.toUpperCase());
    		if (!isValid) {
    			displayErrorMessageDialog(shell, "Location value entered is not valid. Expected format = LnnLnn with the " +
    											" first L=[N|S], the first nn=00-90, the second L = [E|W] and the second nn=00-90. Please re-enter.");
    			isValid = false;
    		}
    		
    	}
    	
    	return isValid;    	
    }
    
    /**
     * Validate region
     * 
     * @param region
     * @return
     */
    public static boolean validateRegion(Shell shell, Integer region) {
    	
    	boolean isValid = true;
    	
    	if (region == null) {    		
    		displayErrorMessageDialog(shell, "Region is invalid. Please re-enter.");
    		isValid = false;  
    		
    	} else if (region < 0) {

    		displayErrorMessageDialog(shell, "Region is not a positive number. Please re-enter.");
    		isValid = false;  
    	}
    	
    	return isValid;
    }
    
    /**
     * Validate value of Particulars1. Also define Particulars10 
     * (Particulars10 is the numeric equivalent of Particulars1)
     * 
     * @param particulars1
     * @return boolean
     */
    public static boolean validateParticulars1(Shell shell, String particulars1, String selectedEventType) {
    	 
    	boolean isValid = true;    
    	String errorMsg = "";
    	
    	if (particulars1.isEmpty()) {
    		// Particulars 1 is required
    		errorMsg += "Particulars 1 is a required field. Please enter a value for Particulars1.";
    		isValid = false;
    	} else {
    		// Determine if Particulars1 is appropriate for the event type
    		
    		switch (EnterEventUtil.EVENT_TYPE.valueOf(selectedEventType)) {
    			case XRA:
    				// Ensure Particulars 1 value has an acceptable format 
    				// (valid X-ray class and only one digit after decimal format). 
    				// e.g. C0.1    				
    				isValid = EnterEventUtil.matchRegex(EnterEventUtil.XRA_PARTICULARS_1_REGEX, particulars1);
    				
    				if (!isValid) {
    					errorMsg += "Particulars 1 is invalid. Please re-enter";
    					
    				} 
    				break;
    			case FLA:
    				// Verify that flare importance/brightness is valid 
    				isValid = EnterEventUtil.matchRegex(EnterEventUtil.FLA_PARTICULARS_1_REGEX, particulars1);
    				
    				if (!isValid) {
    					errorMsg += "Particulars 1 is invalid. First character must be S, 1, 2, 3 or 4 and second character must be F, N or B. Please re-enter";
    					
    				}     				
    				break;
    			case RSP:
    				// Verify that type/importance is valid 
    				isValid = EnterEventUtil.matchRegex(EnterEventUtil.RSP_PARTICULARS_1_REGEX, particulars1);
    				
    				if (!isValid) {
    					errorMsg += "Particulars 1 is invalid. Type is II, III, IV, V or CTM; Importance is 1, 2, 3, 1+, 2+, 3+. Please re-enter";
    					
    				}     				
    				break;
    			default:
    				break;
    	    }
    	}    	
    	
    	if (!isValid) {
    		displayErrorMessageDialog(shell, errorMsg);
    	}
    	
    	return isValid;
    }
    
    /**
     * Validate value of Particulars2. 
     * 
     * @param particulars2
     * @return HashMap<String,String>
     */
    public static HashMap<String,String> validateParticulars2(Shell shell, String particulars2, String selectedEventType) {
    	 
    	Boolean isValid = true; 
    	String errorMsg = "";
    	HashMap<String,String> results = new HashMap<>();

    	Double part2Dbl = null;
    	String part2Value = null;
    	
    	if (particulars2.isEmpty()) {   		

        	MessageDialog confirmDlg = new MessageDialog(shell, "Confirm", null, 
        			"Are you sure you want to leave Particulars 2 blank?",
        			MessageDialog.QUESTION, 
        			new String[]{"Yes", "No"}, 0);
        	confirmDlg.open();

        	if( confirmDlg.getReturnCode() == MessageDialog.CANCEL ) {
        		errorMsg = "Please enter a value for Particulars 2.";
        		isValid = false;
        	} 
        	
    	} else {
    		
    		if (particulars2.endsWith("A")) {
    			particulars2 = particulars2.substring(0, particulars2.length() - 1);
    		}
    		part2Dbl = EnterEventUtil.textIsADouble(particulars2);
    		
    		if (part2Dbl == null) {
    			part2Value = particulars2;
    		}
    		else {
	    		if (part2Dbl == 0) {
	    		
		    		errorMsg = "Zero not allowed for Particulars 2. Please re-enter.";
		    		isValid = false;
	        	} else {
	        		part2Value = EnterEventUtil.format("0.00E00", String.valueOf(part2Dbl));
	        	}
    		}
    	} 
    	
    	if (isValid && part2Value != null) {
    		
	    	// For consistency, ensure that
	        // a Blue Shift in Angstrom has an "A" (for Angstrom) at the end 
	        // of it. This only applies to disk/limb events of type DSF, SPY, BSL,
	        // LPS and EPL.
	    	switch (EnterEventUtil.EVENT_TYPE.valueOf(selectedEventType)) {
				case DSF:
				case SPY:
				case BSL:
				case LPS:
				case EPL:
					if (!particulars2.endsWith("A")) {
						part2Value = part2Value.concat("A");
					}
					break;
				default:
					break;
	    	}	    	
    	} 
    	
    	if (!errorMsg.isEmpty()) {
    		displayErrorMessageDialog(shell, errorMsg);
    	}
    	
    	results.put("isValid", isValid.toString());
    	results.put("Particulars2", ((part2Value!=null)?part2Value:""));
    	
    	return results;
    }
    
    /**
     * Validate value of Particulars3. 
     * 
     * @param particulars3
     * @return HashMap<String,String>
     */
    public static HashMap<String,String> validateParticulars3(Shell shell, String particulars3, String selectedEventType) {
    	 
    	Boolean isValid = true; 
    	String errorMsg = "";
    	HashMap<String,String> results = new HashMap<String, String>();

    	Double part3Dbl = null;
    	String part3Value = "";
    	
    	if (!particulars3.isEmpty() && selectedEventType.equals(EventType.RBR.getType())) {   
        	    		
    		if (particulars3.endsWith("A")) {
    			particulars3 = particulars3.substring(0, particulars3.length() - 1);
    		}
    		part3Dbl = EnterEventUtil.textIsADouble(particulars3);
    		
    		if (part3Dbl != null && part3Dbl == 0) {
    		
	    		errorMsg = "Particular 3 value is invalid. Please re-enter.";
	    		isValid = false;
        	} else if (part3Dbl != null) {
        		part3Value = EnterEventUtil.format("0.0E00", String.valueOf(part3Dbl));
        	}
    		
    	} 
    	
    	if (isValid && particulars3 != null) {
    		
	    	// For consistency, ensure that
	        // a Red Shift in Angstrom has an "A" (for Angstrom) at the end 
	        // of it. This only applies to disk/limb events of type DSF, SPY, BSL,
	        // LPS and EPL.
	    	switch (EnterEventUtil.EVENT_TYPE.valueOf(selectedEventType)) {
				case DSF:
				case SPY:
				case BSL:
				case LPS:
				case EPL:
					if (!particulars3.endsWith("A")) {
						part3Value = part3Value.concat("A");
					}
					break;
				default:
					break;
	    	}	    	
    	}
    	
    	if (!errorMsg.isEmpty()) {
    		displayErrorMessageDialog(shell, errorMsg);
    	}
    	
    	results.put("isValid", isValid.toString());
    	results.put("Particulars3", ((part3Value!=null)?part3Value:""));
    	
    	return results;
    }

    
    /**
     * Returns the Xray Flux Data (peak flux and integrated flux) from 1 minute X-ray Data
     * 
     * @param observatory
     * @param startTime
     * @param endTime
     * @return XrayFlux
     * @throws VizException
     */
    public static XrayFlux getIntegratedFlux(String observatory, Calendar startTime, Calendar endTime) throws VizException {
    	
    	XrayFlux fluxData = null;
    	String satellite = observatory;
    	if (satellite == null) {    		
    		return null;  		
    	}  else {   		

    		// Get the list of primary, secondary and tertiary GOES satellites
    		ArrayList<Satellite> satellites = getGOESSatellites(startTime, endTime);
    		Satellite satPrimary = null;
    		Satellite satSecondary = null;
    		Satellite satTertiary = null;
    		
    		for (int i = 0; i <= satellites.size() -1; i++) {
    			if (satellites.get(i).isPrimary()) {
    				satPrimary = satellites.get(i);
    			} else if (satellites.get(i).isSecondary()) {
    				satSecondary = satellites.get(i);
    			} else {
    				satTertiary = satellites.get(i);
    			}
    		}
    		
    		String primarySatelliteCode = (satPrimary != null) ? satPrimary.getCode() : "";
    		String secondarySatelliteCode = (satSecondary != null) ? satSecondary.getCode() : "";
    		String tertiarySatelliteCode = (satTertiary != null) ? satTertiary.getCode() : "";
    		
    		// Check if the observatory selected corresponds to either Primary, Secondary or Tertiary satellites
    		if (satellite.compareToIgnoreCase(primarySatelliteCode) != 0
    				&& satellite.compareToIgnoreCase(secondarySatelliteCode) != 0 
    					&& satellite.compareToIgnoreCase(tertiarySatelliteCode) != 0) {
    			throw new VizException("The Observatory selected doesn't correspond with any of the Satellites configured for X-rays.");
    		}    		
    	}

		if (satellite.length() == 3) {
			// convert the satellite name i.e. "G13" to "goes-13" (as used in ghcd table) 
			satellite = satellite.substring(1);
			satellite = "goes-" + satellite;
		}
        
    	// Get the list of reference times containing generic high cadence
        // data (GHCD) between a given start time and end time
        List<Date> refTimes = getGhcdDataRefTimes(startTime, endTime);
        
        if (refTimes.size() > 0) {

            // load data for the selected reference times   
        	fluxData = getXrayFluxData(refTimes, satellite);
        }        
    	
    	return fluxData;
    }
    
    /**
     * Get the list of reference times containing generic high cadence
     * data (GHCD) between a given start time and end time.
     * 
     * @param startTime
     * @param endTime
     * @return
     * @throws VizException
     */
    public static List<Date> getGhcdDataRefTimes(Calendar startCal, Calendar endCal) throws VizException {

        SimpleDateFormat queryDateFmt = new SimpleDateFormat(
                "yyyy-MM-dd HH:mm:ss");


        String startTimeStr = queryDateFmt.format(startCal.getTime());
        String endTimeStr = queryDateFmt.format(endCal.getTime());
    	
    	HashMap<String, RequestConstraint> reqConstraints = new HashMap<>();
        reqConstraints.put(PLUGIN_NAME,
                  new RequestConstraint(GENERIC_HIGH_CADENCE_DATA_PLUGIN_NAME));
        RequestConstraint reqConstr = new RequestConstraint();

        String[] constraintList = { startTimeStr, endTimeStr };
        reqConstr.setBetweenValueList(constraintList);
        reqConstr.setConstraintType(RequestConstraint.ConstraintType.BETWEEN);

        reqConstraints.put("dataTime.refTime", reqConstr);
        DbQueryRequest request = new DbQueryRequest();
        request.setConstraints(reqConstraints);
        DbQueryResponse response = (DbQueryResponse) ThriftClient
                  .sendRequest(request);

        GenericHighCadenceDataRecord[] records = response
                .getEntityObjects(GenericHighCadenceDataRecord.class);
        
        List<Date> refTimes = new ArrayList<>();
        if (records.length > 0) {
        	for(int i=0; i<records.length; i++) {
        		GenericHighCadenceDataRecord record = records[i];
        		refTimes.add(record.getDataTime().getRefTime());
        	}
        }                      
    	
    	return refTimes;
    }  
    
    /**
     * Load the data and calculate the peak flux and average integrated flux based on the data.
     * 
     * @param refTimes
     * @param satellite
     * @return
     * @throws VizException
     */
    private static XrayFlux getXrayFluxData(List<Date> refTimes, String satellite) throws VizException {

        // get data from hdf5
        List<GenericHighCadenceDataItem> dataItems = getGhcdDataItems(
                refTimes, satellite, GHCD_INT_FLUX_INSTRUMENT,
                GHCD_INT_FLUX_DATATYPE, GHCD_INT_FLUX_DATA_RESOLUTION_UNIT,
                GHCD_INT_FLUX_DATA_RESOLUTION_VALUE);

        List<Float> fluxValues = new ArrayList<>();        
        Float sumFlux = 0f;
        Float avgFlux = 0f;
        Float peakFlux = 0f;
        
        if (dataItems.size() > 0) {
    
            for (GenericHighCadenceDataItem dataItem : dataItems) {
              
                for (GenericHighCadenceDataField field : dataItem
                        .getGhcdFields()) {
                    if (field.getName().compareTo(GHCD_INT_FLUX_X_LONG) == 0) {
                        try {                        	
                        	fluxValues.add(Float.parseFloat(field.getValue()));
                        	sumFlux += Float.parseFloat(field.getValue());
                        } catch (NumberFormatException e) {
                           continue;
                        }
                    }
                }
            }
            
            peakFlux = Collections.max(fluxValues);            
            avgFlux = (sumFlux / dataItems.size()) ;           
        }
        
        XrayFlux flux = new XrayFlux(peakFlux, avgFlux);
        return flux;
    }
        
    /**
     * 
     * Retrieves a list of GenericHighCadenceDataItem records
     * 
     * @param refTimeList
     *            The list of reference times
     * @param source
     *            The source
     * @param dataResolUnits
     *            The data resolution unit
     * @param dataResolVal
     *            The data resolution value
     * @param instrument
     *            The instrument
     * @param datatype
     *            The data type
     * @return List of GenericHighCadenceDataItem records
     * @throws VizException
     */
    public static List<GenericHighCadenceDataItem> getGhcdDataItems(
            List<Date> refTimes, String source, String instrument,
            String datatype, String dataResolUnits, Integer dataResolVal)
            throws VizException {

        GenericHighCadenceDataReqMsg req = new GenericHighCadenceDataReqMsg();
        req.setReqType(GenericHighCadenceDataReqType.GET_GHCD_DATA_ITEMS);
        req.setQueryTimeList(refTimes);
        req.setSource(source);
        req.setInstrument(instrument);
        req.setDatatype(datatype);
        req.setDataResolUnits(dataResolUnits);
        req.setDataResolVal(dataResolVal);
        List<GenericHighCadenceDataItem> rsltsList = new ArrayList<>();
        try {
            Object rslts = ThriftClient.sendRequest(req);

            if (rslts != null && rslts instanceof List<?>) {
                rsltsList = (ArrayList<GenericHighCadenceDataItem>) rslts;
            }
        } catch (Exception e) {
            throw new VizException(
                    "Error during retrieval request of GHCD data items.", e);
        }

        return rsltsList;
    }
    
    /**
     * Returns the primary, secondary and tertiary GOES satellites
     * 
     * @param beginDTTM
     * @param endDTTM
     * @return Vector<Satellite>
     * @throws VizException
     */
    public static ArrayList<Satellite> getGOESSatellites(Calendar beginDTTM, Calendar endDTTM) throws VizException {
    	  
    	ArrayList<Satellite> satellites = null;
    	
    	SatelliteTrackingStatusRequest req = new SatelliteTrackingStatusRequest();
    	req.setRequestType(SatelliteTrackingStatusRequest.SatelliteTrackingStatusRequestType.GET_GOES_SATELLITES);    	
    	req.setInstrument("X-rays");
    	req.setBeginDTTM(beginDTTM);
    	req.setEndDTTM(endDTTM);    	
       
        try {
            Object rslt = ThriftClient.sendRequest(req);

            if (rslt != null && rslt instanceof ArrayList<?>) {
                satellites = (ArrayList<Satellite>) rslt;
            }
        } catch (Exception e) {
            throw new VizException(
                    "Error during retrieval request of GOES satellite.", e);
        }
    	
    	return satellites;
    	
    }    
    
}
