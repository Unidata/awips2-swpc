package gov.noaa.nws.ncep.common.swpcrefdb;

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
 * Class representing the Satellite_Tracking_Status table
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
@Entity
@Table(name = "SWPC_SATELLITE_TRACKING_STATUS")
@DynamicSerialize
@SuppressWarnings("rawtypes")
public class SatelliteTrackingStatus extends PersistableDataObject implements
        ISWPCBaseTable {

    private static final long serialVersionUID = 7492449766451782615L;

    @Id
    @DynamicSerializeElement
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "swpc_satellite_tracking_status_seq_gen")
    @SequenceGenerator(name = "swpc_satellite_tracking_status_seq_gen", sequenceName = "SWPC_SATELLITE_TRACKING_STATUS_SEQ")
    private long id = 0;

    @Column(name = "DESCRIPTION", unique = true, nullable = false)
    @DynamicSerializeElement
    private String description = null;

    /**
	 * 
	 */
    public SatelliteTrackingStatus() {
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
