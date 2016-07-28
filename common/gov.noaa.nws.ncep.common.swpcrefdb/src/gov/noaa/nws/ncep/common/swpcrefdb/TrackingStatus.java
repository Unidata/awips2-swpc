package gov.noaa.nws.ncep.common.swpcrefdb;

import gov.noaa.nws.ncep.common.swpcrefdb.intf.ISWPCBaseTable;

import java.util.Calendar;

import javax.persistence.Column;
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
 * Class representing the Tracking_Status table.
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
@Table(name = "SWPC_TRACKING_STATUS")
@DynamicSerialize
@SuppressWarnings("rawtypes")
public class TrackingStatus extends PersistableDataObject implements ISWPCBaseTable {

    private static final long serialVersionUID = -8800972469385178864L;

    @Id
    @DynamicSerializeElement
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "swpc_tracking_status_seq_gen")
    @SequenceGenerator(name = "swpc_tracking_status_seq_gen", sequenceName = "SWPC_TRACKING_STATUS_SEQ")
    @Column(name = "ID", unique = true, nullable = false)
    private long id = 0;

    @ManyToOne(optional = false)
    @PrimaryKeyJoinColumn
    @DynamicSerializeElement
    private StationInstrument stationInstrument = null;

    @ManyToOne(optional = false)
    @PrimaryKeyJoinColumn
    @DynamicSerializeElement
    private SatelliteTrackingStatus satelliteTrackingStatus = null;

    @Column(name = "BEGIN_DTTM", unique = false, nullable = false)
    @DynamicSerializeElement
    private Calendar beginDTTM = null;

    @Column(name = "END_DTTM", unique = false, nullable = true)
    @DynamicSerializeElement
    private Calendar endDTTM = null;

    @Column(name = "DESCRIPTION", unique = false, nullable = true)
    @DynamicSerializeElement
    private String description = null;

    /**
	 * 
	 */
    public TrackingStatus() {
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
     * @return the stationInstrument
     */
    public StationInstrument getStationInstrument() {
        return stationInstrument;
    }

    /**
     * @param stationInstrument
     *            the stationInstrument to set
     */
    public void setStationInstrument(StationInstrument stationInstrument) {
        this.stationInstrument = stationInstrument;
    }

    /**
     * @return the satelliteTrackingStatus
     */
    public SatelliteTrackingStatus getSatelliteTrackingStatus() {
        return satelliteTrackingStatus;
    }

    /**
     * @param satelliteTrackingStatus
     *            the satelliteTrackingStatus to set
     */
    public void setSatelliteTrackingStatus(
            SatelliteTrackingStatus satelliteTrackingStatus) {
        this.satelliteTrackingStatus = satelliteTrackingStatus;
    }

    /**
     * @return the beginDTTM
     */
    public Calendar getBeginDTTM() {
        return beginDTTM;
    }

    /**
     * @param beginDTTM
     *            the beginDTTM to set
     */
    public void setBeginDTTM(Calendar beginDTTM) {
        this.beginDTTM = beginDTTM;
    }

    /**
     * @return the endDTTM
     */
    public Calendar getEndDTTM() {
        return endDTTM;
    }

    /**
     * @param endDTTM
     *            the endDTTM to set
     */
    public void setEndDTTM(Calendar endDTTM) {
        this.endDTTM = endDTTM;
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
