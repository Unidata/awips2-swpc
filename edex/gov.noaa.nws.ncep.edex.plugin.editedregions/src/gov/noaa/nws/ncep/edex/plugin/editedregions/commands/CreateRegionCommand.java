package gov.noaa.nws.ncep.edex.plugin.editedregions.commands;

import com.raytheon.uf.common.dataplugin.PluginException;
import com.raytheon.uf.common.status.IUFStatusHandler;
import com.raytheon.uf.common.status.UFStatus;
import com.raytheon.uf.edex.database.DataAccessLayerException;

import gov.noaa.nws.ncep.common.dataplugin.editedregions.Region;
import gov.noaa.nws.ncep.common.dataplugin.editedregions.exception.EditedRegionsException;
import gov.noaa.nws.ncep.common.dataplugin.editedregions.request.CreateRegionRequest;
import gov.noaa.nws.ncep.common.dataplugin.editedregions.request.intf.IRequest;
import gov.noaa.nws.ncep.common.dataplugin.editedregions.response.CreateRegionReportResponse;
import gov.noaa.nws.ncep.common.dataplugin.editedregions.response.CreateRegionResponse;
import gov.noaa.nws.ncep.common.dataplugin.editedregions.response.intf.IResponse;
import gov.noaa.nws.ncep.common.dataplugin.editedregions.results.CreateRegionReportResults;
import gov.noaa.nws.ncep.common.dataplugin.editedregions.results.CreateRegionResults;
import gov.noaa.nws.ncep.edex.plugin.editedregions.dao.RegionReportsDao;
import gov.noaa.nws.ncep.edex.plugin.editedregions.dao.RegionsDao;

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
    private RegionsDao regionsDao = null;
    
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
        	
	        this.regionsDao = new RegionsDao();
	        
	        region = this.request.getRegion();
	        
	        reportId = this.regionsDao.persist(region);

        
        } catch (PluginException e) {
        	EditedRegionsException er = new EditedRegionsException(e);
        } catch (DataAccessLayerException e) {
        	EditedRegionsException er = new EditedRegionsException(e);
        }

        this.setEndTime();

        return this.createResponse(reportId);
    }

    /**
     * @param results
     * @return IResponse
     */
    private IResponse createResponse(int reportId) {
        CreateRegionResponse response = new CreateRegionResponse();

        if (this.hasError()) {
            response.setError(this.getError());
        } else {
            CreateRegionResults results = new CreateRegionResults();
            results.setCreatedRegion(reportId);
            response.setResults(results);
        }

        response.setRequest(this.getRequest());
        response.setProcessingTime(this.getProcessingTime());
        return response;
    }

}
