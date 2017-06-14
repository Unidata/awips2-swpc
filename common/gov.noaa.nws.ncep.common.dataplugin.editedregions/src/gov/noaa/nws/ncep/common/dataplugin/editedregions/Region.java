package gov.noaa.nws.ncep.common.dataplugin.editedregions;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import com.raytheon.uf.common.dataplugin.persist.PersistableDataObject;
import com.raytheon.uf.common.serialization.annotations.DynamicSerialize;
import com.raytheon.uf.common.serialization.annotations.DynamicSerializeElement;

@Entity
// @SequenceGenerator(initialValue = 1, name = PluginDataObject.ID_GEN,
// sequenceName = "SWPC_REGIONS_SEQ")
@Table(name = "SWPC_REGIONS")
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@DynamicSerialize
public class Region extends PersistableDataObject {

    private static final long serialVersionUID = 6589298851235632930L;

    @Id
    @DynamicSerializeElement
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "swpc_regions_seq_gen")
    @SequenceGenerator(name = "swpc_regions_seq_gen", sequenceName = "SWPC_REGIONS_SEQ")
    @Column(name = "ID", unique = true, nullable = false)
    private long id = 0;

    @Column
    @DynamicSerializeElement
    private Integer regionID;

    @Column
    @DynamicSerializeElement
    private Date createTime;

    public long getId() {
        return this.id;
    }

    public void setId(long id) {
        this.id = id;
    }

    /**
     * @return the regionID
     */
    public Integer getRegionID() {
        return this.regionID;
    }

    /**
     * @param regionID
     *            the regionID to set
     */
    public void setRegionID(Integer regionID) {
        this.regionID = regionID;
    }

    /**
     * @return the createTime
     */
    public Date getCreateTime() {
        return this.createTime;
    }

    /**
     * @param createTime
     *            the createTime to set
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

}
