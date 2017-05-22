package gov.noaa.nws.ncep.common.swpcrefdb;

import gov.noaa.nws.ncep.common.swpcrefdb.intf.ISWPCBaseTable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.raytheon.uf.common.dataplugin.persist.PersistableDataObject;
import com.raytheon.uf.common.serialization.annotations.DynamicSerialize;
import com.raytheon.uf.common.serialization.annotations.DynamicSerializeElement;

/**
 * Class representing the SWPC_REPORT_STATUS table.
 * 
 * <pre>
 * 
 * SOFTWARE HISTORY
 * 
 * Date         Ticket#    Engineer    Description
 * ------------ ---------- ----------- --------------------------
 * Jan 22, 2016 R9583      jtravis     Initial creation
 * 
 * </pre>
 * 
 * @author jtravis
 * @version 1.0
 */
@SuppressWarnings("rawtypes")
@DynamicSerialize
@Entity
@Table(name = "SWPC_REPORT_STATUS")
public class ReportStatus extends PersistableDataObject implements ISWPCBaseTable {

    private static final long serialVersionUID = -2052838874371396178L;   

    public static final String ID = "id"; 
    
    public static final String TYPE = "type";

    public static final String DESCRIPTION = "description";
    
    @Id
    @DynamicSerializeElement
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "swpc_report_status_seq_gen")
    @SequenceGenerator(name = "swpc_report_status_seq_gen", sequenceName = "SWPC_REPORT_STATUS_SEQ")
    private long id = 0;


    @Column(name = "TYPE", unique = true, nullable = false)
    @DynamicSerializeElement
    private Integer type = null;

    @Embedded
    @ManyToOne(cascade = { CascadeType.REFRESH })
    @PrimaryKeyJoinColumn
    @DynamicSerializeElement
    private StationType description;

    /**
	 * 
	 */
    public ReportStatus() {
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
	public Integer getType() {
		return type;
	}

	/**
	 * @param type the type to set
	 */
	public void setType(Integer type) {
		this.type = type;
	}

	/**
	 * @return the description
	 */
	public StationType getDescription() {
		return description;
	}

	/**
	 * @param description the description to set
	 */
	public void setDescription(StationType description) {
		this.description = description;
	}

}
