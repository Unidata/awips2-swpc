package gov.noaa.nws.ncep.common.dataplugin.editedevents;

import gov.noaa.nws.ncep.common.swpcrefdb.intf.ISWPCBaseTable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.raytheon.uf.common.dataplugin.persist.PersistableDataObject;
import com.raytheon.uf.common.serialization.annotations.DynamicSerialize;
import com.raytheon.uf.common.serialization.annotations.DynamicSerializeElement;

/**
 * Class representing the type of an event
 * 
 * <pre>
 * 
 * SOFTWARE HISTORY
 * 
 * Date         Ticket#    Engineer    Description
 * ------------ ---------- ----------- --------------------------
 * Aug 5, 2015  R9583       jtravis     Initial creation
 * 
 * </pre>
 * 
 * @author jtravis
 * @version 1.0
 */
@SuppressWarnings("rawtypes")
@Entity
@Table(name = "SWPC_EVENT_TYPE")
@DynamicSerialize
public class EventType extends PersistableDataObject implements ISWPCBaseTable {
	
	public static final String ID = "id";
	
	public static final String TYPE = "type";
	
	public static final String CODE = "code";
	
	public static final String INDICATOR = "indicator";
	
	public static final String DESCRIPTION = "description";
	
	public static final String FREQUENCY = "frequency";

    @Id
    @DynamicSerializeElement
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "swpc_event_type_seq_gen")
    @SequenceGenerator(name = "swpc_event_type_seq_gen", sequenceName = "SWPC_EVENT_TYPE_SEQ")
    @Column(name = "ID", unique = true, nullable = false)
    private long id = 0;

    @Column(name = "TYPE", unique = false, nullable = false)
    @DynamicSerializeElement
    private String type = null;
    
    @Column(name = "CODE", unique = true, nullable = false)
    @DynamicSerializeElement
    private int code;
    
    @Column(name = "INDICATOR", unique = false, nullable = true)
    @DynamicSerializeElement
    private String indicator = null;
    
    @Column(name = "DESCRIPTION", unique = false, nullable = false)
    @DynamicSerializeElement
    private String description = null;
    
    @Column(name = "FREQUENCY", unique = false, nullable = true)
    @DynamicSerializeElement
    private String frequency = null;
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 807542108567603488L;

	/**
	 * 
	 */
	public EventType() {
		
	}

	/* (non-Javadoc)
	 * @see gov.noaa.nws.ncep.common.swpcrefdb.intf.ISWPCBaseTable#getId()
	 */
	@Override
	public long getId() {
		return this.id;
	}

	/* (non-Javadoc)
	 * @see gov.noaa.nws.ncep.common.swpcrefdb.intf.ISWPCBaseTable#setId(long)
	 */
	@Override
	public void setId(long id) {
		this.id = id;
	}

	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}

	/**
	 * @param type the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}

	/**
	 * @return the code
	 */
	public int getCode() {
		return code;
	}

	/**
	 * @param code the code to set
	 */
	public void setCode(int code) {
		this.code = code;
	}

	/**
	 * @return the indicator
	 */
	public String getIndicator() {
		return indicator;
	}

	/**
	 * @param indicator the indicator to set
	 */
	public void setIndicator(String indicator) {
		this.indicator = indicator;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @return the frequency
	 */
	public String getFrequency() {
		return frequency;
	}

	/**
	 * @param frequency the frequency to set
	 */
	public void setFrequency(String frequency) {
		this.frequency = frequency;
	}

}
