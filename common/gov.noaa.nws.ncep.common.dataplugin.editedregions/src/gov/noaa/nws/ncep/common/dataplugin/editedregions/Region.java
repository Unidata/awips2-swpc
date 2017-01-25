package gov.noaa.nws.ncep.common.dataplugin.editedregions;

import com.raytheon.uf.common.dataplugin.persist.PersistablePluginDataObject;
import com.raytheon.uf.common.serialization.annotations.DynamicSerialize;

@DynamicSerialize
public class Region extends PersistablePluginDataObject {

	private static final long serialVersionUID = 6589298851235632930L;

	@Override
    public String getPluginName() {
        // TODO: Use constant.
        return "editedregions";
    }

}
