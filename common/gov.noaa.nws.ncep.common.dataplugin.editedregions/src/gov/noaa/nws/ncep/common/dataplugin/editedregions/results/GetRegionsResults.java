package gov.noaa.nws.ncep.common.dataplugin.editedregions.results;

import java.util.Vector;

import com.raytheon.uf.common.serialization.annotations.DynamicSerialize;
import com.raytheon.uf.common.serialization.annotations.DynamicSerializeElement;

import gov.noaa.nws.ncep.common.dataplugin.editedregions.results.intf.IResults;

/**
 * Class holding the results from executing the GetRegions command
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
public class GetRegionsResults implements IResults {

    /**
     * Holds the regions
     */
    @DynamicSerializeElement
    private Vector<Integer> regions = new Vector<Integer>();

    /**
     * Constructor
     */
    public GetRegionsResults() {

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
        return this.regions.size();
    }

    /**
     * @return the regions
     */
    public Vector<Integer> getRegions() {
        return this.regions;
    }

    /**
     * @param regions
     *            the regions to set
     */
    public void setRegions(Vector<Integer> regions) {
        this.regions = regions;
    }

    /**
     * Add a single region to the collection of regions
     * 
     * This method will not allow duplicate regions to be added
     * 
     * @param region
     */
    public void setRegion(int region) {

        if (!this.regions.contains(region)) { // region not yet added
            this.regions.addElement(region);
        }

    }

}
