package gov.noaa.nws.ncep.common.swpcrefdb;

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

import gov.noaa.nws.ncep.common.swpcrefdb.intf.ISWPCBaseTable;

/**
 * Class representing the SWPC_SUNSPOT_QUADRANT_LOCATION table.
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
@Table(name = "SWPC_SUNSPOT_QUADRANT_LOCATION")
public class SunSpotQuadrantLocation extends PersistableDataObject
        implements ISWPCBaseTable {

    private static final long serialVersionUID = -2052838874371396178L;

    public static final String ID = "id";

    // public static final String CODE = "code";

    public static final String DESCRIPTION = "description";

    @Id
    @DynamicSerializeElement
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "swpc_sunspot_quadrant_location_seq_gen")
    @SequenceGenerator(name = "swpc_sunspot_quadrant_location_seq_gen", sequenceName = "SWPC_SUNSPOT_QUADRANT_LOCATION_SEQ")
    @Column(name = "ID", unique = true, nullable = false)
    private long id = 0;

    // @Column(name = "CODE", unique = true, nullable = false)
    // @DynamicSerializeElement
    // private Integer code = null;

    // @Embedded
    // @ManyToOne(cascade = { CascadeType.REFRESH })
    @Column(name = "DESCRIPTION", unique = true, nullable = false)
    @DynamicSerializeElement
    private String description;

    /**
     * 
     */
    public SunSpotQuadrantLocation() {
    }

    /*
     * (non-Javadoc)
     * 
     * @see gov.noaa.nws.ncep.common.swpcrefdb.intf.ISWPCBaseTable#getId()
     */
    @Override
    public long getId() {
        return this.id;
    }

    /*
     * (non-Javadoc)
     * 
     * @see gov.noaa.nws.ncep.common.swpcrefdb.intf.ISWPCBaseTable#setId(long)
     */
    @Override
    public void setId(long id) {
        this.id = id;

    }

    /**
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description
     *            the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }

}
