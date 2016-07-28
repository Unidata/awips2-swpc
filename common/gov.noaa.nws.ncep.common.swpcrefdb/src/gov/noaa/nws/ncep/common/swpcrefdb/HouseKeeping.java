package gov.noaa.nws.ncep.common.swpcrefdb;

import gov.noaa.nws.ncep.common.swpcrefdb.intf.ISWPCBaseTable;

import java.util.Calendar;

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
 * Class representing the HouseKeeping table
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
@Entity
@Table(name = "SWPC_HOUSEKEEPING")
@DynamicSerialize
public class HouseKeeping extends PersistableDataObject implements
        ISWPCBaseTable {

    private static final long serialVersionUID = 6219793648190812889L;

    public static final String APPLICATION_NAME = "applicationName";

    public static final String CURRENT_DATE_TIME = "currentDTTM";

    public static final String DESCRIPTION = "description";

    public static final String DMS_DATE_TIME = "dmsDTTM";

    public static final String ITEM_1 = "item1";

    public static final String ITEM_2 = "item2";

    public static final String ITEM_3 = "item3";

    public static final String ITEM_4 = "item4";

    public static final String ITEM_5 = "item5";

    public static final String ID = "id";

    @Id
    @DynamicSerializeElement
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "swpc_housekeeping_seq_gen")
    @SequenceGenerator(name = "swpc_housekeeping_seq_gen", sequenceName = "SWPC_HOUSEKEEPING_SEQ")
    @Column(name = "ID", unique = true, nullable = false)
    private long id = 0;

    @Column(name = "CURRENT_DTTM", unique = false, nullable = true)
    @DynamicSerializeElement
    private Calendar currentDTTM = null;

    @Column(name = "DMS_DTTM", unique = false, nullable = true)
    @DynamicSerializeElement
    private Calendar dmsDTTM = null;

    @Column(name = "ITEM_1", unique = false, nullable = true)
    @DynamicSerializeElement
    private int item1;

    @Column(name = "ITEM_2", unique = false, nullable = true)
    @DynamicSerializeElement
    private int item2;

    @Column(name = "ITEM_3", unique = false, nullable = true)
    @DynamicSerializeElement
    private int item3;

    @Column(name = "ITEM_4", unique = false, nullable = true)
    @DynamicSerializeElement
    private int item4;

    @Column(name = "ITEM_5", unique = false, nullable = true)
    @DynamicSerializeElement
    private int item5;

    @Column(name = "APPLICATION_NAME", unique = true, nullable = false)
    @DynamicSerializeElement
    private String applicationName = null;

    @Column(name = "DESCRIPTION", unique = false, nullable = false)
    @DynamicSerializeElement
    private String description = null;

    /**
	 * 
	 */
    public HouseKeeping() {
        // TODO Auto-generated constructor stub
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
     * @return the currentDTTM
     */
    public Calendar getCurrentDTTM() {
        return currentDTTM;
    }

    /**
     * @param currentDTTM
     *            the currentDTTM to set
     */
    public void setCurrentDTTM(Calendar currentDTTM) {
        this.currentDTTM = currentDTTM;
    }

    /**
     * @return the dmsDTTM
     */
    public Calendar getDmsDTTM() {
        return dmsDTTM;
    }

    /**
     * @param dmsDTTM
     *            the dmsDTTM to set
     */
    public void setDmsDTTM(Calendar dmsDTTM) {
        this.dmsDTTM = dmsDTTM;
    }

    /**
     * @return the item1
     */
    public int getItem1() {
        return item1;
    }

    /**
     * @param item1
     *            the item1 to set
     */
    public void setItem1(int item1) {
        this.item1 = item1;
    }

    /**
     * @return the item2
     */
    public int getItem2() {
        return item2;
    }

    /**
     * @param item2
     *            the item2 to set
     */
    public void setItem2(int item2) {
        this.item2 = item2;
    }

    /**
     * @return the item3
     */
    public int getItem3() {
        return item3;
    }

    /**
     * @param item3
     *            the item3 to set
     */
    public void setItem3(int item3) {
        this.item3 = item3;
    }

    /**
     * @return the item4
     */
    public int getItem4() {
        return item4;
    }

    /**
     * @param item4
     *            the item4 to set
     */
    public void setItem4(int item4) {
        this.item4 = item4;
    }

    /**
     * @return the item5
     */
    public int getItem5() {
        return item5;
    }

    /**
     * @param item5
     *            the item5 to set
     */
    public void setItem5(int item5) {
        this.item5 = item5;
    }

    /**
     * @return the applicationName
     */
    public String getApplicationName() {
        return applicationName;
    }

    /**
     * @param applicationName
     *            the applicationName to set
     */
    public void setApplicationName(String applicationName) {
        this.applicationName = applicationName;
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
