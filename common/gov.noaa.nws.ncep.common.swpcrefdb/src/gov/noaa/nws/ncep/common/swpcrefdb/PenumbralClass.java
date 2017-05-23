package gov.noaa.nws.ncep.common.swpcrefdb;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.raytheon.uf.common.dataplugin.persist.PersistableDataObject;
import com.raytheon.uf.common.serialization.annotations.DynamicSerialize;
import com.raytheon.uf.common.serialization.annotations.DynamicSerializeElement;

import gov.noaa.nws.ncep.common.swpcrefdb.intf.ISWPCBaseTable;

/**
 * Class representing the SWPC_PENUMBRA table.
 * 
 * <pre>
 * 
 * SOFTWARE HISTORY
 * 
 * Date         Ticket#    Engineer    Description
 * ------------ ---------- ----------- --------------------------
 * May 22, 2017 R9583      alockleigh     Initial creation
 * 
 * </pre>
 * 
 * @author alockleigh
 * @version 1.0
 */
@SuppressWarnings("rawtypes")
@DynamicSerialize
@Entity
@Table(name = "SWPC_PENUMBRAL_CLASS")
public class PenumbralClass extends PersistableDataObject
        implements ISWPCBaseTable {

    private static final long serialVersionUID = -2052838874371396188L;

    public static final String ID = "id";

    public static final String CODE = "code";

    public static final String DESCRIPTION = "description";

    @Id
    @DynamicSerializeElement
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "swpc_penumbral_class_seq_gen")
    @SequenceGenerator(name = "swpc_penumbral_class_seq_gen", sequenceName = "SWPC_PENUMBRAL_CLASS_SEQ")
    private long id = 0;

    @Column(name = "CODE", unique = true, nullable = false)
    @DynamicSerializeElement
    private Integer code = null;

    @Embedded
    // @ManyToOne(cascade = { CascadeType.REFRESH })
    @PrimaryKeyJoinColumn
    @DynamicSerializeElement
    private String description;

    /**
     * 
     */
    public PenumbralClass() {
    }

    @Override
    public long getId() {
        return this.id;
    }

    @Override
    public void setId(long id) {
        this.id = id;

    }

    /**
     * @return the type
     */
    public Integer getType() {
        return code;
    }

    /**
     * @param type
     *            the type to set
     */
    public void setType(Integer type) {
        this.code = type;
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
