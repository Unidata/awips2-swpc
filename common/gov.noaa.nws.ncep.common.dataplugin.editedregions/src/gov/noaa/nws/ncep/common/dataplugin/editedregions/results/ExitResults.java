package gov.noaa.nws.ncep.common.dataplugin.editedregions.results;

import com.raytheon.uf.common.serialization.annotations.DynamicSerialize;

import gov.noaa.nws.ncep.common.dataplugin.editedregions.results.intf.IResults;

/**
 * Class used to hold any results from executing the ExitCommand
 * 
 * TODO in subsequent releases this class may not be necessary...review in ER
 * 2.0
 * 
 * <pre>
 * 
 * SOFTWARE HISTORY
 * 
 * Date         Ticket#    Engineer    Description
 * ------------ ---------- ----------- --------------------------
 * Apr 10, 2016 R9583      sgurung     Initial creation
 * 
 * </pre>
 * 
 * @author sgurung
 * @version 1.0
 */
@DynamicSerialize
public class ExitResults implements IResults {

    /**
     * Number of records deleted
     */
    private int numRecordsDeleted = 0;

    /**
     * Number of records updated
     */
    private int numRecordsUpdated = 0;

    public ExitResults() {

    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * gov.noaa.nws.ncep.common.dataplugin.editedevents.results.intf.IResults#
     * numResults()
     */
    @Override
    public int numResults() {
        return 0;
    }

    /**
     * @return the numRecordsDeleted
     */
    public int getNumRecordsDeleted() {
        return numRecordsDeleted;
    }

    /**
     * @param numRecordsDeleted
     *            the numRecordsDeleted to set
     */
    public void setNumRecordsDeleted(int numRecordsDeleted) {
        this.numRecordsDeleted = numRecordsDeleted;
    }

    /**
     * @return the numRecordsUpdated
     */
    public int getNumRecordsUpdated() {
        return numRecordsUpdated;
    }

    /**
     * @param numRecordsUpdated
     *            the numRecordsUpdated to set
     */
    public void setNumRecordsUpdated(int numRecordsUpdated) {
        this.numRecordsUpdated = numRecordsUpdated;
    }

}
