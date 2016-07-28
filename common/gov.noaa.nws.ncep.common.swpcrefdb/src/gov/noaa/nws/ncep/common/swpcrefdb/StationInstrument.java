package gov.noaa.nws.ncep.common.swpcrefdb;

import gov.noaa.nws.ncep.common.swpcrefdb.intf.ISWPCBaseTable;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.raytheon.uf.common.dataplugin.persist.PersistableDataObject;
import com.raytheon.uf.common.serialization.annotations.DynamicSerialize;
import com.raytheon.uf.common.serialization.annotations.DynamicSerializeElement;

/**
 * Class representing the Station_Instrument join table.
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
@Table(name = "SWPC_STATION_INSTRUMENT")
@DynamicSerialize
@SuppressWarnings("rawtypes")
public class StationInstrument extends PersistableDataObject implements
        ISWPCBaseTable {

    private static final long serialVersionUID = -4024472156331641898L;

    @Id
    @DynamicSerializeElement
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "swpc_station_instrument_seq_gen")
    @SequenceGenerator(name = "swpc_station_instrument_seq_gen", sequenceName = "SWPC_STATION_INSTRUMENT_SEQ")
    private long id = 0;

    @ManyToOne(optional = false)
    @PrimaryKeyJoinColumn
    @DynamicSerializeElement
    private Station station = null;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(referencedColumnName = "id", name = "instrument_id")
    @DynamicSerializeElement
    private Instrument instrument = null;

    /**
	 * 
	 */
    public StationInstrument() {
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
     * @return the station
     */
    public Station getStation() {
        return station;
    }

    /**
     * @param station
     *            the station to set
     */
    public void setStation(Station station) {
        this.station = station;
    }

    /**
     * @return the instrument
     */
    public Instrument getInstrument() {
        return instrument;
    }

    /**
     * @param instrument
     *            the instrument to set
     */
    public void setInstrument(Instrument instrument) {
        this.instrument = instrument;
    }

}
