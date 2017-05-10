package gov.noaa.nws.ncep.common.dataplugin.editedregions.request.test;

import gov.noaa.nws.ncep.common.dataplugin.editedregions.RegionReport;
import gov.noaa.nws.ncep.common.dataplugin.editedregions.exception.EditedRegionsException;
import gov.noaa.nws.ncep.common.dataplugin.editedregions.gateway.Gateway;
import gov.noaa.nws.ncep.common.dataplugin.editedregions.request.CreateRegionReportRequest;
import gov.noaa.nws.ncep.common.dataplugin.editedregions.response.CreateRegionReportResponse;

/**
 * @author jtravis
 *
 */
public class TestCreateRegionReportRequest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		CreateRegionReportRequest request = new CreateRegionReportRequest();
		RegionReport report = new RegionReport();
		
		Gateway gateway = Gateway.getInstance();
		
		//-------------Region Report Parameters TEST 001-----------
	    String reportLocation = "N03E82";
	    String location = "N03E72";
	    String observatory = "JST";
	    String type = "spt";
	    String compact = "1";
	    String spotclass = "Hsx";
	    String magclass = "A";
		int station = 16320;
	    int quality = 2;
	    int region = 2625;
	    int latitude = 3;
	    int reportLongitude = 82;
	    int longitude = 72;
	    int carlon = 253;
	    int extent = 1;
	    int area = 30;
	    int numspots = 1;
	    int zurich = 7;
	    int penumbra = 2;
	    int magcode = 1;
	    int obsid = 4;
	    int reportStatus = 3;
	    boolean validSpotClass = true;
		
		
		report.setStation(station);
	    report.setObservatory(observatory);
	    report.setType(type);
	    report.setQuality(quality);
	    report.setRegion(region);
	    report.setLatitude(latitude);
	    report.setReportLongitude(reportLongitude);
	    report.setLongitude(longitude);
	    report.setReportLocation(reportLocation); 
	    report.setLocation(location);
	    report.setCarlon(carlon);
	    report.setExtent(extent);
	    report.setArea(area);
	    report.setNumspots(numspots);
	    report.setZurich(zurich);
	    report.setPenumbra(penumbra);
	    report.setCompact(compact);
	    report.setSpotclass(spotclass);
	    report.setMagcode(magcode);
	    report.setMagclass(magclass);
	    report.setObsid(obsid);
	    report.setReportStatus(reportStatus);
	    report.setValidSpotClass(validSpotClass);
	    
	    request.setRegionReport(report);
		
		try {
			
			CreateRegionReportResponse response = gateway.submit(request);
			
			
			
		} catch (EditedRegionsException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
