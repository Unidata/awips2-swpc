package gov.noaa.nws.ncep.edex.plugin.editedregions.commands;

import com.raytheon.uf.common.dataplugin.PluginException;
import com.raytheon.uf.common.status.IUFStatusHandler;
import com.raytheon.uf.common.status.UFStatus;

import gov.noaa.nws.ncep.common.dataplugin.editedregions.Region;
import gov.noaa.nws.ncep.common.dataplugin.editedregions.exception.EditedRegionsException;
import gov.noaa.nws.ncep.common.dataplugin.editedregions.request.CreateRegionRequest;
import gov.noaa.nws.ncep.common.dataplugin.editedregions.request.intf.IRequest;
import gov.noaa.nws.ncep.common.dataplugin.editedregions.response.CreateRegionReportResponse;
import gov.noaa.nws.ncep.common.dataplugin.editedregions.response.intf.IResponse;
import gov.noaa.nws.ncep.common.dataplugin.editedregions.results.CreateRegionReportResults;
import gov.noaa.nws.ncep.edex.plugin.editedregions.dao.RegionReportsDao;

/**
 * The command class that is executed to create a new Region
 * 
 * 
 * @author jtravis
 * @version 1.0
 */
public class CreateRegionCommand extends BaseCommand {

    /**
     * The request from the client that resulted in creating an instance of the
     * command
     */
    private CreateRegionRequest request = null;
    
    /**
     * Dao for Region
     * 
     * TODO - change to use the RegionDao
     */
    private RegionReportsDao regionReportsDao = null;
    
    /**
	 * Logger
	 */
    private static final IUFStatusHandler statusHandler = UFStatus.getHandler(CreateRegionCommand.class);

    /**
     * Default Constructor
     */
    public CreateRegionCommand() {
    }

    /*
     * (non-Javadoc)
     * 
     * @see gov.noaa.nws.ncep.edex.plugin.editedregions.commands.intf.ICommand#
     * getError()
     */
    @Override
    public EditedRegionsException getError() {
        return this.error;
    }

    /*
     * (non-Javadoc)
     * 
     * @see gov.noaa.nws.ncep.edex.plugin.editedregions.commands.intf.ICommand#
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
     * @see gov.noaa.nws.ncep.edex.plugin.editedregions.commands.intf.ICommand#
     * getStartTime()
     */
    @Override
    public long getStartTime() {
        return this.startTime;
    }

    /*
     * (non-Javadoc)
     * 
     * @see gov.noaa.nws.ncep.edex.plugin.editedregions.commands.intf.ICommand#
     * getEndTime()
     */
    @Override
    public long getEndTime() {
        return this.endTime;
    }

    /*
     * (non-Javadoc)
     * 
     * @see gov.noaa.nws.ncep.edex.plugin.editedregions.commands.intf.ICommand#
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
     * @see gov.noaa.nws.ncep.edex.plugin.editedregions.commands.intf.ICommand#
     * isValid()
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
     * @see gov.noaa.nws.ncep.edex.plugin.editedregions.commands.intf.ICommand#
     * setRequest(gov.noaa.nws.ncep.common.dataplugin.editedregions.request.intf
     * .IRequest)
     */
    @Override
    public void setRequest(IRequest request) {
        this.request = (CreateRegionRequest) request;

    }

    /*
     * (non-Javadoc)
     * 
     * @see gov.noaa.nws.ncep.edex.plugin.editedregions.commands.intf.ICommand#
     * getRequest()
     */
    @Override
    public IRequest getRequest() {
        return (IRequest) this.request;
    }

    /*
     * (non-Javadoc)
     * 
     * @see gov.noaa.nws.ncep.edex.plugin.editedregions.commands.intf.ICommand#
     * execute()
     */
    @Override
    public IResponse execute() {
        this.setStartTime();
        
        Region region = null;
        int reportId = 0;
        
        try {
        	
	        this.regionReportsDao = new RegionReportsDao();
	        
	        region = this.request.getRegion();
	
	        // TODO - add the logic to persist the region and obtain the
	        // unique id

//			reportId = this.regionReportsDao.persist(region);

        
        } catch (PluginException e) {
        	
        	EditedRegionsException ex = new EditedRegionsException(e);
        	
        }
//        } catch (DataAccessLayerException e) {
//        	EditedRegionsException ex = new EditedRegionsException(e);
//        }

        this.setEndTime();

        return this.createResponse(region);
    }

    /**
     * @param results
     * @return IResponse
     */
    private IResponse createResponse(Region region) {
        CreateRegionReportResponse response = new CreateRegionReportResponse();

        if (this.hasError()) {
            response.setError(this.getError());
        } else {
            CreateRegionReportResults results = new CreateRegionReportResults();
            results.setReportID(region.getId());
            response.setResults(results);
        }

        response.setRequest(this.getRequest());
        response.setProcessingTime(this.getProcessingTime());
        return response;
    }

}
