package gov.noaa.nws.ncep.edex.plugin.editedregions.commands;

import java.util.Iterator;
import java.util.List;

import com.raytheon.uf.common.dataplugin.PluginException;
import com.raytheon.uf.common.status.IUFStatusHandler;
import com.raytheon.uf.common.status.UFStatus;

import gov.noaa.nws.ncep.common.dataplugin.editedregions.Region;
import gov.noaa.nws.ncep.common.dataplugin.editedregions.exception.EditedRegionsException;
import gov.noaa.nws.ncep.common.dataplugin.editedregions.request.GetLatestRegionRequest;
import gov.noaa.nws.ncep.common.dataplugin.editedregions.request.GetRegionsRequest;
import gov.noaa.nws.ncep.common.dataplugin.editedregions.request.intf.IRequest;
import gov.noaa.nws.ncep.common.dataplugin.editedregions.response.GetLatestRegionResponse;
import gov.noaa.nws.ncep.common.dataplugin.editedregions.response.GetRegionsResponse;
import gov.noaa.nws.ncep.common.dataplugin.editedregions.response.intf.IResponse;
import gov.noaa.nws.ncep.common.dataplugin.editedregions.results.GetLatestRegionResults;
import gov.noaa.nws.ncep.common.dataplugin.editedregions.results.GetRegionsResults;
import gov.noaa.nws.ncep.edex.plugin.editedregions.dao.RegionsDao;

/**
 * The command class that is executed to obtain the latest
 * region that was created
 * 
 * 
 * @author jtravis
 * @version 1.0
 */
public class GetRegionsCommand extends BaseCommand {

    /**
     * The request from the client that resulted in creating an instance of the
     * command
     */
    private GetRegionsRequest request = null;
    
    /**
     * Dao for ReportStatus
     */
    private RegionsDao regionsDao = null;
    
    /**
     * Logger
     */
    private static final IUFStatusHandler statusHandler = UFStatus
            .getHandler(GetRegionsCommand.class);

    /**
     * Default Constructor
     */
    public GetRegionsCommand() {
    }

    /*
     * (non-Javadoc)
     * 
     * @see gov.noaa.nws.ncep.edex.plugin.editedevents.commands.intf.ICommand#
     * getError()
     */
    @Override
    public EditedRegionsException getError() {
        return this.error;
    }

    /*
     * (non-Javadoc)
     * 
     * @see gov.noaa.nws.ncep.edex.plugin.editedevents.commands.intf.ICommand#
     * hasError()
     */
    @Override
    public boolean hasError() {
        if (this.error == null) {
            return false;
        } else {
            return true;
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see gov.noaa.nws.ncep.edex.plugin.editedevents.commands.intf.ICommand#
     * getStartTime()
     */
    @Override
    public long getStartTime() {
        return this.startTime;
    }

    /*
     * (non-Javadoc)
     * 
     * @see gov.noaa.nws.ncep.edex.plugin.editedevents.commands.intf.ICommand#
     * getEndTime()
     */
    @Override
    public long getEndTime() {
        return this.endTime;
    }

    /*
     * (non-Javadoc)
     * 
     * @see gov.noaa.nws.ncep.edex.plugin.editedevents.commands.intf.ICommand#
     * getProcessingTime()
     */
    @Override
    public long getProcessingTime() {
        long time = 0L;

        time = this.getEndTime() - this.getStartTime();

        return time;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * gov.noaa.nws.ncep.edex.plugin.editedevents.commands.intf.ICommand#isValid
     * () TODO - do we really need to validate the request here... TODO - the
     * request should be validated before adding it to the command
     */
    @Override
    public boolean isValid() {
        if (this.request != null && this.request.isValid()) {

            // a request is associated with the command
            // and the request is valid
            return true;

        } else {
            return false;
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see gov.noaa.nws.ncep.edex.plugin.editedevents.commands.intf.ICommand#
     * setRequest(gov.noaa.nws.ncep.common.dataplugin.editedevents.request.intf.
     * IRequest)
     */
    @Override
    public void setRequest(IRequest request) {
        this.request = (GetRegionsRequest) request;

    }

    /*
     * (non-Javadoc)
     * 
     * @see gov.noaa.nws.ncep.edex.plugin.editedevents.commands.intf.ICommand#
     * getRequest()
     */
    @Override
    public IRequest getRequest() {
        return (IRequest) this.request;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * gov.noaa.nws.ncep.edex.plugin.editedevents.commands.intf.ICommand#execute
     * ()
     */
    @Override
    public IResponse execute() {
    	
    	this.setStartTime();
    	
        GetRegionsResponse response = new GetRegionsResponse();
        GetRegionsResults results = new GetRegionsResults();

        try {
        	
			regionsDao = new RegionsDao();
			
			List<Region> regions = regionsDao.getRegions();
			Iterator<Region> it = regions.iterator();
			
			while (it.hasNext()) {
				results.setRegion(it.next().getRegionID().intValue());
			}
		// TODO correct this...do not catch a generic exception!!!
//        } catch (PluginException e) {
//			this.setError(new EditedRegionsException(e));
//		}
			
        } catch (Exception e) {
			this.setError(new EditedRegionsException(e));
		}
        
        this.setEndTime();
        
        // add the results instance to the response;
    	response.setResults(results);
    	response.setError(this.getError());
    	response.setProcessingTime(this.getProcessingTime());
        
        return response;
    }

}
