package gov.noaa.nws.ncep.common.swpcrefdb.intf;
/**
 * Common interface for SWPC Reference tables
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
public interface ISWPCBaseTable{

    public long getId();
    public void setId(long id);
	
}
