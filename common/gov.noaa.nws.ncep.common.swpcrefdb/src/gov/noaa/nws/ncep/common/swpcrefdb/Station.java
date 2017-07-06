package gov.noaa.nws.ncep.common.swpcrefdb;

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

import gov.noaa.nws.ncep.common.swpcrefdb.intf.ISWPCBaseTable;

/**
 * Class representing the SWPC_STATION table.
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
@Table(name = "SWPC_STATION")
public class Station extends PersistableDataObject implements ISWPCBaseTable {

    private static final long serialVersionUID = -2052838874371396178L;

    public static final String ID = "id";

    public static final String DESIGNATION = "designation";

    public static final String NAME = "name";

    public static final String TYPE = "type";

    @Id
    @DynamicSerializeElement
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "swpc_station_seq_gen")
    @SequenceGenerator(name = "swpc_station_seq_gen", sequenceName = "SWPC_STATION_SEQ")
    private long id = 0;

    @Column(name = "DESIGNATION", unique = false, nullable = false)
    @DynamicSerializeElement
    private String designation = null;

    @Column(name = "NAME", unique = false, nullable = false)
    @DynamicSerializeElement
    private String name = null;

    @Embedded
    @ManyToOne(cascade = { CascadeType.REFRESH })
    @PrimaryKeyJoinColumn
    @DynamicSerializeElement
    private StationType type;

    /**
     * 
     */
    public Station() {
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
     * @return the designation
     */
    public String getDesignation() {
        return designation;
    }

    /**
     * @param designation
     *            the designation to set
     */
    public void setDesignation(String designation) {
        this.designation = designation;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name
     *            the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the type
     */
    public StationType getType() {
        return type;
    }

    /**
     * @param type
     *            the type to set
     */
    public void setType(StationType type) {
        this.type = type;
    }

}
