package gov.noaa.nws.ncep.common.dataplugin.editedevents.request;

import gov.noaa.nws.ncep.common.dataplugin.editedevents.request.criteria.GetEventsCriteria;
import gov.noaa.nws.ncep.common.dataplugin.editedevents.request.intf.IRequest;

import com.raytheon.uf.common.serialization.annotations.DynamicSerialize;
import com.raytheon.uf.common.serialization.annotations.DynamicSerializeElement;

/**
 * Class for allowing the client to request the back-end to
 * obtain a list of events
 * 
 * <pre>
 * 
 * SOFTWARE HISTORY
 * 
 * Date         Ticket#    Engineer    Description
 * ------------ ---------- ----------- --------------------------
 * Dec 12, 2015 R9583      jtravis     Initial creation
 * 
 * </pre>
 * 
 * @author jtravis
 * @version 1.0
 */
@DynamicSerialize
public class GetEventsRequest extends Request<GetEventsRequest> implements
        IRequest {

    /**
     * The Request ID
     */
    @DynamicSerializeElement
    private final long id;
    
    /**
     * The start of the date time range to process events
     */
    @DynamicSerializeElement
    private long beginDTTM = 0L;

    /**
     * The end of the date time range to process events
     */
    @DynamicSerializeElement
    private long endDTTM = 0L;
    
    /**
     * The criteria by which events should be obtained 
     */
    @DynamicSerializeElement
    private GetEventsCriteria criteria = null;
    
    /**
     * The bin number used to filter events
     */
    @DynamicSerializeElement
    private Integer binNumber = null;
    
    /**
     * Will the filtering include events with the
     * specified start date
     * 
     * Default is false
     */
    @DynamicSerializeElement
    private boolean inclusiveOfStartDate = false;
    
    /**
     * Will the filtering includes events with the
     * specified end date
     * 
     * Default is false;
     */
    @DynamicSerializeElement
    private boolean inclusiveOfEndDate = false;
    
    /**
     * Should the results be sorted in ascending order
     * 
     * Defaults is false
     */
    @DynamicSerializeElement
    private boolean sortEnabled = false;
    
    @DynamicSerializeElement
    private String sortBy = null;

    @DynamicSerializeElement
    private String age;
    
    @DynamicSerializeElement
    private String statusText;

	/**
	 * 
	 */
	public GetEventsRequest() {
		this.id = System.currentTimeMillis();
	}

    /*
     * (non-Javadoc)
     * 
     * @see
     * gov.noaa.nws.ncep.common.dataplugin.editedevents.request.IRequest#getID()
     */
    @Override
    public long getId() {
    	return this.id;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * gov.noaa.nws.ncep.common.dataplugin.editedevents.request.IRequest#isValid
     * ()
     */
    @Override
    public boolean isValid() {
        
    	boolean isValid = false;
    	
    	if (this.criteria != null) {
    		
    		switch (this.criteria) {
    		
    		case EVENTS_WITHOUT_THIS_BIN_NUMBER:
    		case EVENTS_WITH_THIS_BIN_NUMBER:
    			
    			if (this.binNumber != null) {
    				isValid = true;
    			}
    			
    			break;
    		case EVENTS_BY_DATE_RANGE:
    			
    			if (this.beginDTTM != 0 && this.endDTTM != 0) {
    				isValid = true;
    			}

    		case EVENTS_BEFORE_START_DATE:
    		case EVENTS_AFTER_START_DATE:
    			
    			if (this.beginDTTM != 0) {
    				isValid = true;
    			}
    			
    			break;
    		case EVENTS_WITH_THIS_BIN_NUMBER_AND_DATE_RANGE:
    			
    			if ((this.binNumber != null) && (this.beginDTTM != 0 && this.endDTTM != 0)) {
    				isValid = true;
    			}
    			
    			break;
    		case EVENTS_WITH_THIS_AGE_AND_DATE_RANGE:
        			
        			if ((this.age != null) && (this.beginDTTM != 0 && this.endDTTM != 0)) {
        				isValid = true;
        			}
        			
        			break;	
    		case EVENTS_WITH_THIS_STATUS_TEXT_AND_DATE_RANGE:
    			
    			if ((this.statusText != null) && (this.beginDTTM != 0 && this.endDTTM != 0)) {
    				isValid = true;
    			}
    			
    			break;
    		case EVENTS_WITH_THIS_BIN_NUMBER_AND_AGE_AND_DATE_RANGE:
    			
    			if ((this.binNumber != null) && (this.age != null) && (this.beginDTTM != 0 && this.endDTTM != 0)) {
    				isValid = true;
    			}
    			
    			break;		
    		case EVENTS_WITH_THIS_BIN_NUMBER_AND_STATUS_TEXT_AND_DATE_RANGE:
    			
    			if ((this.binNumber != null) && (this.statusText != null) && (this.beginDTTM != 0 && this.endDTTM != 0)) {
    				isValid = true;
    			}
    			
    			break;	
    		case ALL: // obtain all events, no filtering based on criteria
    			break;
    		default: // Unknown Criteria
    				
    		}
    		
    	}
    	
        return isValid;
    }

    @Override
    public void setId(long Id) {
    	// The id is set when the request is initialized
    	// but the system still requires the method for
    	// serialization / deserialization through the gateway.
    	//
    	// thus, this method does not actually change the id
    	//
    	// TODO - verify this
    	;;
    }

    /**
     * Get the start date time range used for filtering
     * which events should be processed
     * 
     * @return long
     */
	public long getBeginDTTM() {
		return this.beginDTTM;
	}

	/**
     * Set the start date time range for filtering which
     * events should be processed
     * 
     * @param beginDTTM
     */
	public void setBeginDTTM(long beginDTTM) {
		this.beginDTTM = beginDTTM;
	}

	/**
     * Get the end date time range used for filtering
     * which events should be processed
     * 
     * @return Calendar
     */
	public long getEndDTTM() {
		return this.endDTTM;
	}

	   /**
     * Set the end date time range for filtering which
     * events should be processed
     * 
     * @param endDTTM
     */
	public void setEndDTTM(long endDTTM) {
		this.endDTTM = endDTTM;
	}

	/**
	 * @return the criteria
	 */
	public GetEventsCriteria getCriteria() {
		return criteria;
	}

	/**
	 * @param criteria the criteria to set
	 */
	public void setCriteria(GetEventsCriteria criteria) {
		this.criteria = criteria;
	}

	/**
	 * @return the binNumber
	 */
	public Integer getBinNumber() {
		return binNumber;
	}

	/**
	 * @param binNumber the binNumber to set
	 */
	public void setBinNumber(Integer binNumber) {
		this.binNumber = binNumber;
	}

	/**
	 * @return the isSortEnabled
	 */
	public boolean isSortEnabled() {
		return sortEnabled;
	}

	/**
	 * @param isSortEnabled the isSortEnabled to set
	 */
	public void setSortEnabled(boolean sortEnabled) {
		this.sortEnabled = sortEnabled;
	}

	/**
	 * @return the isInclusiveOfStartDate
	 */
	public boolean isInclusiveOfStartDate() {
		return inclusiveOfStartDate;
	}

	/**
	 * @param isInclusiveOfStartDate the isInclusiveOfStartDate to set
	 */
	public void setInclusiveOfStartDate(boolean inclusiveOfStartDate) {
		this.inclusiveOfStartDate = inclusiveOfStartDate;
	}

	/**
	 * @return the isInclusiveOfEndDate
	 */
	public boolean isInclusiveOfEndDate() {
		return inclusiveOfEndDate;
	}

	/**
	 * @param isInclusiveOfEndDate the isInclusiveOfEndDate to set
	 */
	public void setInclusiveOfEndDate(boolean inclusiveOfEndDate) {
		this.inclusiveOfEndDate = inclusiveOfEndDate;
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
	 * @return the statusText
	 */
	public String getStatusText() {
		return statusText;
	}

	/**
	 * @param statusText the statusText to set
	 */
	public void setStatusText(String statusText) {
		this.statusText = statusText;
	}


}
