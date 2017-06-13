package gov.noaa.nws.ncep.common.dataplugin.editedregions;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import com.raytheon.uf.common.dataplugin.persist.PersistablePluginDataObject;
import com.raytheon.uf.common.serialization.annotations.DynamicSerialize;
import com.raytheon.uf.common.serialization.annotations.DynamicSerializeElement;

@Entity
//@SequenceGenerator(initialValue = 1, name = PluginDataObject.ID_GEN, sequenceName = "SWPC_REGIONS_SEQ")
@Table(name = "SWPC_REGIONS")
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@DynamicSerialize
public class Region extends PersistablePluginDataObject {

	private static final long serialVersionUID = 6589298851235632930L;

	@Override
    public String getPluginName() {
        // TODO: Use constant.
        return "editedregions";
    }
	
	@Id
	@Column
	@DynamicSerializeElement
	private Integer regionID;
	
    @Column
    @DynamicSerializeElement
    private Date createTime;

	/**
	 * @return the regionID
	 */
	public Integer getRegionID() {
		return this.regionID;
	}

	/**
	 * @param regionID the regionID to set
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
	 * @param createTime the createTime to set
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	/**
	 * @return the serialversionuid
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
