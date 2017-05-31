package gov.noaa.nws.ncep.common.dataplugin.editedregions.results;

import java.util.Hashtable;

import com.raytheon.uf.common.serialization.annotations.DynamicSerialize;
import com.raytheon.uf.common.serialization.annotations.DynamicSerializeElement;

import gov.noaa.nws.ncep.common.dataplugin.editedregions.results.intf.IResults;

@DynamicSerialize
public class GetReferenceDataResults implements IResults {
	
	@DynamicSerializeElement
	private Hashtable<String, Integer> observationQualityResults = new Hashtable<String, Integer>();
	
	@DynamicSerializeElement
	private Hashtable<String, Integer> observationTypeResults = new Hashtable<String, Integer>();
	
	@DynamicSerializeElement
	private Hashtable<String, Integer> penumbralClassResults = new Hashtable<String, Integer>();
	
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
	 * @param observationQualityResults the observationQualityResults to set
	 */
	public void setObservationQualityResults(Hashtable<String, Integer> observationQualityResults) {
		this.observationQualityResults = observationQualityResults;
	}



	/**
	 * @return the observationTypeResults
	 */
	public Hashtable<String, Integer> getObservationTypeResults() {
		return observationTypeResults;
	}



	/**
	 * @param observationTypeResults the observationTypeResults to set
	 */
	public void setObservationTypeResults(Hashtable<String, Integer> observationTypeResults) {
		this.observationTypeResults = observationTypeResults;
	}



	/**
	 * @return the penumbralClassResults
	 */
	public Hashtable<String, Integer> getPenumbralClassResults() {
		return penumbralClassResults;
	}



	/**
	 * @param penumbralClassResults the penumbralClassResults to set
	 */
	public void setPenumbralClassResults(Hashtable<String, Integer> penumbralClassResults) {
		this.penumbralClassResults = penumbralClassResults;
	}



	/**
	 * @return the reportStatusResults
	 */
	public Hashtable<String, Integer> getReportStatusResults() {
		return reportStatusResults;
	}



	/**
	 * @param reportStatusResults the reportStatusResults to set
	 */
	public void setReportStatusResults(Hashtable<String, Integer> reportStatusResults) {
		this.reportStatusResults = reportStatusResults;
	}
	
    /* (non-Javadoc)
     * @see gov.noaa.nws.ncep.common.dataplugin.editedregions.results.intf.IResults#numResults()
     */
    @Override
    public int numResults() {

    	int resultCount = this.numObservationQualityResults() + 
    						this.numObservationTypeResults() + 
    						this.numPenumbralClassResults() + 
    						this.numReportStatusResults();
    	
        return resultCount;
    }
    
    public int numReportStatusResults() {
    	return this.reportStatusResults.size();
    }
    
    public int numPenumbralClassResults() {
    	return this.penumbralClassResults.size();
    }
    
    public int numObservationQualityResults() {
    	return this.observationQualityResults.size();
    }
    
    public int numObservationTypeResults() {
    	return this.observationTypeResults.size();
    }

}
