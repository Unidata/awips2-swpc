package gov.noaa.nws.ncep.common.dataplugin.editedregions.results;

import java.util.Hashtable;

import com.raytheon.uf.common.serialization.annotations.DynamicSerialize;
import com.raytheon.uf.common.serialization.annotations.DynamicSerializeElement;

import gov.noaa.nws.ncep.common.dataplugin.editedregions.results.intf.IResults;

/**
 * Holds the results from executing the GetReferenceData command
 * 
 * <pre>
 *
 * SOFTWARE HISTORY
 *
 * Date         Ticket#    Engineer    Description
 * ------------ ---------- ----------- --------------------------
 * Jul 6, 2017            jtravis     Initial creation
 *
 * </pre>
 *
 * @author jtravis
 */
@DynamicSerialize
public class GetReferenceDataResults implements IResults {

    /**
     * Holds the Observation Quality reference data
     */
    @DynamicSerializeElement
    private Hashtable<String, Integer> observationQualityResults = new Hashtable<String, Integer>();

    /**
     * Holds the Observation Types reference data
     */
    @DynamicSerializeElement
    private Hashtable<String, Integer> observationTypeResults = new Hashtable<String, Integer>();

    /**
     * Holds the Penumbral Class reference data
     */
    @DynamicSerializeElement
    private Hashtable<String, Integer> penumbralClassResults = new Hashtable<String, Integer>();

    /**
     * Holds the Report Status reference data
     */
    @DynamicSerializeElement
    private Hashtable<String, Integer> reportStatusResults = new Hashtable<String, Integer>();

    /**
     * Constructor
     */
    public GetReferenceDataResults() {

    }

    /**
     * @return the observationQualityResults
     */
    public Hashtable<String, Integer> getObservationQualityResults() {
        return observationQualityResults;
    }

    /**
     * @param observationQualityResults
     *            the observationQualityResults to set
     */
    public void setObservationQualityResults(
            Hashtable<String, Integer> observationQualityResults) {
        this.observationQualityResults = observationQualityResults;
    }

    /**
     * @return the observationTypeResults
     */
    public Hashtable<String, Integer> getObservationTypeResults() {
        return observationTypeResults;
    }

    /**
     * @param observationTypeResults
     *            the observationTypeResults to set
     */
    public void setObservationTypeResults(
            Hashtable<String, Integer> observationTypeResults) {
        this.observationTypeResults = observationTypeResults;
    }

    /**
     * @return the penumbralClassResults
     */
    public Hashtable<String, Integer> getPenumbralClassResults() {
        return penumbralClassResults;
    }

    /**
     * @param penumbralClassResults
     *            the penumbralClassResults to set
     */
    public void setPenumbralClassResults(
            Hashtable<String, Integer> penumbralClassResults) {
        this.penumbralClassResults = penumbralClassResults;
    }

    /**
     * @return the reportStatusResults
     */
    public Hashtable<String, Integer> getReportStatusResults() {
        return reportStatusResults;
    }

    /**
     * @param reportStatusResults
     *            the reportStatusResults to set
     */
    public void setReportStatusResults(
            Hashtable<String, Integer> reportStatusResults) {
        this.reportStatusResults = reportStatusResults;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * gov.noaa.nws.ncep.common.dataplugin.editedregions.results.intf.IResults#
     * numResults()
     */
    @Override
    public int numResults() {

        int resultCount = this.numObservationQualityResults()
                + this.numObservationTypeResults()
                + this.numPenumbralClassResults()
                + this.numReportStatusResults();

        return resultCount;
    }

    /**
     * @return int
     */
    public int numReportStatusResults() {
        return this.reportStatusResults.size();
    }

    /**
     * @return int
     */
    public int numPenumbralClassResults() {
        return this.penumbralClassResults.size();
    }

    /**
     * @return int
     */
    public int numObservationQualityResults() {
        return this.observationQualityResults.size();
    }

    /**
     * @return int
     */
    public int numObservationTypeResults() {
        return this.observationTypeResults.size();
    }

}
