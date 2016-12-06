package gov.noaa.nws.ncep.viz.ui.editedregions.util;

import gov.noaa.nws.ncep.common.dataplugin.editedevents.Event;
import gov.noaa.nws.ncep.common.dataplugin.editedevents.EventBin;
import gov.noaa.nws.ncep.common.dataplugin.editedevents.exception.EditedEventsException;
import gov.noaa.nws.ncep.common.dataplugin.editedevents.gateway.Gateway;
import gov.noaa.nws.ncep.common.dataplugin.editedevents.request.GetEventsRequest;
import gov.noaa.nws.ncep.common.dataplugin.editedevents.request.criteria.GetEventsCriteria;
import gov.noaa.nws.ncep.common.dataplugin.editedevents.response.GetEventsResponse;
import gov.noaa.nws.ncep.common.dataplugin.editedevents.results.GetEventsResults;
import gov.noaa.nws.ncep.common.dataplugin.editedevents.util.EditedEventsConstants;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.ListIterator;
import java.util.Set;

import com.raytheon.uf.common.time.DataTime;

/**
 * Class that is used to retrieve the list of events
 * 
 * <pre>
 *
 * SOFTWARE HISTORY
 *
 * Date         Ticket#    Engineer    Description
 * ------------ ---------- ----------- --------------------------
 * Mar 1, 2016   R9583     sgurung     Initial creation
 *
 * </pre>
 *
 * @author sgurung
 * @version 1.0	
 */
public class Events {

	private String sortBy = null;
	
	private Integer bin = null;
	
	private String age = null;
	
	private String view = null;
	
	private long beginDTTM = 0L;
	
	private long endDTTM = 0L;
	
	private LinkedHashMap<Integer, Event> events = new LinkedHashMap<Integer, Event>();
	
	public Events() {
		
	}

	/**
	 * @return the sortBy
	 */
	public String getSortBy() {
		return sortBy;
	}

	/**
	 * @param sortBy the sortBy to set
	 */
	public void setSortBy(String sortBy) {
		this.sortBy = sortBy;
	}

	/**
	 * @return the bin
	 */
	public Integer getBin() {
		return bin;
	}

	/**
	 * @param bin the bin to set
	 */
	public void setBin(Integer bin) {
		this.bin = bin;
	}

	/**
	 * @return the age
	 */
	public String getAge() {
		return age;
	}

	/**
	 * @param age the age to set
	 */
	public void setAge(String age) {
		this.age = age;
	}

	/**
	 * @return the view
	 */
	public String getView() {
		return view;
	}

	/**
	 * @param view the view to set
	 */
	public void setView(String view) {
		this.view = view;
	}

	/**
	 * @return the beginDTTM
	 */
	public long getBeginDTTM() {
		return beginDTTM;
	}

	/**
	 * @param beginDTTM the beginDTTM to set
	 */
	public void setBeginDTTM(long beginDTTM) {
		this.beginDTTM = beginDTTM;
	}

	/**
	 * @return the endDTTM
	 */
	public long getEndDTTM() {
		return endDTTM;
	}

	/**
	 * @param endDTTM the endDTTM to set
	 */
	public void setEndDTTM(long endDTTM) {
		this.endDTTM = endDTTM;
	}
	
	/**
     * Returns the list of events in the db that matches the criteria in the filter controls
     * 
     * @return List<gov.noaa.nws.ncep.common.dataplugin.editedevents.Event>
	 * @throws EditedEventsException 
     */
    public List<Event> getEvents() throws EditedEventsException {
    	
    	String sortByColumn = Event.BEGIN_DATE;
    	String statusText = null;
    	boolean addSeparator = false;
    	
    	GetEventsRequest getEventsRequest = new GetEventsRequest();
    	getEventsRequest.setCriteria(GetEventsCriteria.EVENTS_BY_DATE_RANGE);
    	getEventsRequest.setBeginDTTM(beginDTTM);
    	getEventsRequest.setEndDTTM(endDTTM);
    	getEventsRequest.setInclusiveOfEndDate(true);
    	getEventsRequest.setInclusiveOfStartDate(true);
    	getEventsRequest.setSortBy(Event.BEGIN_DATE);    		
    	
    	if (view != null) {
	    	switch(view) {
		    	case EditEventsUIConstants.VIEW_OPTION_ALL_REPORTS:
		    		break;
		    	case EditEventsUIConstants.VIEW_OPTION_BEST_ONLY:
		    		statusText= EditedEventsConstants.EVENT_STATUS.WINNER.toString() + "OR " + EditedEventsConstants.EVENT_STATUS.SOLEBEST.toString();
		    		break;
		    	case EditEventsUIConstants.VIEW_OPTION_CONTENDERS_ONLY:
		    		statusText = EditedEventsConstants.EVENT_STATUS.CONTENDER.toString();
		    		break;
		    	case EditEventsUIConstants.VIEW_OPTION_NEW_REPORTS:
		    		 age = EditedEventsConstants.EVENT_AGE.NEW.toString();
		    		break;
		    	default:
		    		break;
	    	}
    	}
    	
    	if (sortBy != null) {
	    	switch(sortBy) {
		    	case EditEventsUIConstants.SORTBY_OPTION_BIN_DATE:		    		
		    		sortByColumn = EventBin.BEGIN_DATE_TIME;
		    		addSeparator = true;
	            	break;
		    	case EditEventsUIConstants.SORTBY_OPTION_TIME:
		    		sortByColumn = Event.BEGIN_DATE;
		    		addSeparator = false;
			    	break;
		    	case EditEventsUIConstants.SORTBY_OPTION_TYPE:
		    		sortByColumn = Event.CODED_TYPE;
		    		addSeparator = true;
			    	break;
		    	case EditEventsUIConstants.SORTBY_OPTION_REGION:
		    		sortByColumn = Event.REGION;
		    		addSeparator = true;
			    	break;
		    	case EditEventsUIConstants.SORTBY_OPTION_OBSERVATORY:
		    		sortByColumn = Event.OBSERVATORY;
		    		addSeparator = false;
			    	break;
		    	default:
		    		break;
	    	}
    	}
    	    	
    	if (bin != null && age != null) {
    		
    		getEventsRequest.setBinNumber(bin);
    		getEventsRequest.setAge(age);
        	getEventsRequest.setCriteria(GetEventsCriteria.EVENTS_WITH_THIS_BIN_NUMBER_AND_AGE_AND_DATE_RANGE);
    	} else if (bin != null && statusText != null) {
    		
    		getEventsRequest.setBinNumber(bin);
    		getEventsRequest.setStatusText(statusText);
        	getEventsRequest.setCriteria(GetEventsCriteria.EVENTS_WITH_THIS_BIN_NUMBER_AND_STATUS_TEXT_AND_DATE_RANGE);
    	} else if (bin != null && age == null && statusText == null) {
    		
    		getEventsRequest.setBinNumber(bin);
    		getEventsRequest.setCriteria(GetEventsCriteria.EVENTS_WITH_THIS_BIN_NUMBER_AND_DATE_RANGE);
    	} else if (age != null && bin == null && statusText == null){

    		getEventsRequest.setAge(age);
        	getEventsRequest.setCriteria(GetEventsCriteria.EVENTS_WITH_THIS_AGE_AND_DATE_RANGE);
    	} else if (statusText != null && age == null && bin == null){

    		getEventsRequest.setStatusText(statusText);
        	getEventsRequest.setCriteria(GetEventsCriteria.EVENTS_WITH_THIS_STATUS_TEXT_AND_DATE_RANGE);
    	}
    	
    	getEventsRequest.setSortBy(sortByColumn);
		getEventsRequest.setSortEnabled(true); 
    	
    	GetEventsResponse getEventsResponse = null;
         	
     	if (getEventsRequest.isValid()) {
     		getEventsResponse = Gateway.getInstance().submit(getEventsRequest);
			
			if (!getEventsResponse.hasErrors() && getEventsResponse.getResults() != null) {
				events = ((GetEventsResults) getEventsResponse.getResults()).getEvents();
			}
     	}
    	
     	List<Event> eventsList = new ArrayList<Event>();
     	Set<Integer> keys = events.keySet();     	
     	
    	for (Integer id: keys) {
    		Event event = events.get(id);
    		event.setId(id);
    		
    		eventsList.add(event);
    	}
    	
    	if(addSeparator) {
     	
	    	// Create an empty event to be used as a separator to group events
	     	Event separatorEvent = new Event();
	     	separatorEvent.setId(0);
	     	separatorEvent.setDataTime(new DataTime(Calendar.getInstance()));
	    	 
	     	boolean groupByBinBegDate = (EventBin.BEGIN_DATE_TIME.compareToIgnoreCase(sortByColumn) == 0) ? true : false;
	     	boolean groupByCodedType = (Event.CODED_TYPE.compareToIgnoreCase(sortByColumn) == 0) ? true : false;
	     	boolean groupByRegion = (Event.REGION.compareToIgnoreCase(sortByColumn) == 0) ? true : false;
	     
	     	ListIterator<Event> iter = eventsList.listIterator();
	     	Event prev = null;
	     	
	     	// add separator(s) (empty event with id=0) to group the events
	        while(iter.hasNext()) {
	    		Event curr = (Event) iter.next();    		    		    		
	    		
				if (prev != null) {
	    			if (groupByBinBegDate && prev.getBin().getBinNumber().intValue() != curr.getBin().getBinNumber().intValue()) {
	    				iter.previous();
	    				iter.add(separatorEvent);
	    			}
	    			if (groupByCodedType && prev.getCodedType().intValue() != curr.getCodedType().intValue()) {
	    				iter.previous();
	    				iter.add(separatorEvent);
	    			}
	    			int prevEventRegion = (prev.getRegion() != null) ? prev.getRegion().intValue() : 0;
	    			int currEventRegion = (curr.getRegion() != null) ? curr.getRegion().intValue() : 0;
	    			if (groupByRegion && prevEventRegion != currEventRegion) {
	    				iter.previous();
	    				iter.add(separatorEvent);
	    			}
				}
	    		
	    		prev = curr;
	    	}
        
    	}
    	 
        return eventsList;    	
    }


}
