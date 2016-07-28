/**
 * This code has unlimited rights, and is provided "as is" by the National Centers 
 * for Environmental Prediction, without warranty of any kind, either expressed or implied, 
 * including but not limited to the implied warranties of merchantability and/or fitness 
 * for a particular purpose.
 * 
 * 
 * This code has been developed by the NCEP-SIB for use in the AWIPS2 system.
 * 
 */
package gov.noaa.nws.ncep.edex.plugin.editedevents.util;

import gov.noaa.nws.ncep.common.dataplugin.editedevents.Event;
import gov.noaa.nws.ncep.common.dataplugin.editedevents.GOESXrayEvent;
import gov.noaa.nws.ncep.common.dataplugin.editedevents.util.EditedEventsConstants;
import gov.noaa.nws.ncep.common.dataplugin.editedevents.xml.GoesXrayEvent;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import com.raytheon.uf.common.dataplugin.PluginDataObject;
import com.raytheon.uf.common.time.DataTime;

/**
 * Utility class for the EventsDecoder
 * 
 * <pre>
 * 
 * SOFTWARE HISTORY
 * 
 * Date         Ticket#    Engineer    Description
 * ------------ ---------- ----------- --------------------------
 * Nov 9, 2015  R9583      sgurung     Initial creation
 * 
 * </pre>
 * 
 * @author sgurung
 * @version 1.0
 */

public class EventsDecoderUtil {

    public static PluginDataObject[] convertGoesXrayEventsToPDOs(
            List<GoesXrayEvent> xrayEvents) {

        List<PluginDataObject> pdos = new ArrayList<PluginDataObject>();
        Event record = null;

        for (int i = 0; i < xrayEvents.size(); i++) {

            record = convertGoesXrayEventToEventRecord(xrayEvents.get(i));
            pdos.add(record);

        }
        return pdos.toArray(new PluginDataObject[pdos.size()]);

    }

    public static Event convertGoesXrayEventToEventRecord(
            GoesXrayEvent xrayEvent) {

        Event record = new Event();
        Calendar cal = Calendar.getInstance();

        record.setDataTime(new DataTime(xrayEvent.getCurrentTime()));
        record.setObservatory(xrayEvent.getSource());
        record.setType(EditedEventsConstants.EventType.XRA.getType());
        record.setQuality("5");
        record.setCodedType(1);
        record.setFrequency(EditedEventsConstants.GOES_XRA_EVENTS_FREQUENCY);
        record.setChangeFlag(0);
        record.setAge(null);
        record.setRegion(null);
        record.setBin(null);
        record.setStatusCd(null);
        record.setStatusText(null);
        record.setPutdmsd(null);
        record.setPutdmss(null);
        record.setLocation(null);
        record.setObsid(null);

        // Set begin date and time
        if (xrayEvent.getBeginTime() != null) {
        	cal.clear();
            cal.setTime(xrayEvent.getBeginTime());
            record.setBeginDate(cal);

            record.setBeginTime(EECommandUtil.getHourAndMinuteCombination(cal));
        } else {
            record.setBeginDate(null);
            record.setBeginTime(null);
        }

        record.setBegInq(null);

        // Set end date and time
        if (xrayEvent.getEndTime() != null) {
            cal = Calendar.getInstance();
            cal.clear();
            cal.setTime(xrayEvent.getEndTime());
            record.setEndDate(cal);

            record.setEndTime(EECommandUtil.getHourAndMinuteCombination(cal));
        } else {
            record.setEndDate(null);
            record.setEndTime(null);
        }

        record.setEndq(null);

        // Set begin date and time
        if (xrayEvent.getMaxTime() != null) {
            cal = Calendar.getInstance();
            cal.clear();
            cal.setTime(xrayEvent.getMaxTime());
            record.setMaxDate(cal);

            record.setMaxTime(EECommandUtil.getHourAndMinuteCombination(cal));
        } else {
            record.setMaxDate(null);
            record.setMaxTime(null);
        }

        record.setMaxq(null);

        // Set Particulars
        String maxClass = (xrayEvent.getMaxClass() != null) ? xrayEvent
                .getMaxClass().toString() : null;
        String curIntXrLong = (xrayEvent.getCurrentIntXrLong() != null) ? xrayEvent
                .getCurrentIntXrLong().toString() : null;
        String maxtemp = (xrayEvent.getMaxTemp() != null) ? xrayEvent
                .getMaxTemp().toString() : null;
        String maxEmissionMeas = (xrayEvent.getMaxEmissionMeas() != null) ? xrayEvent
                .getMaxEmissionMeas().toString() : null;
        String maxXrLong = (xrayEvent.getMaxXrLong() != null) ? xrayEvent
                .getMaxXrLong().toString() : null;

        record.setParticulars1(maxClass);
        record.setParticulars2(curIntXrLong);
        record.setParticulars3(maxtemp);
        record.setParticulars4(maxEmissionMeas);
        record.setParticulars5(null);
        record.setParticulars6(null);
        record.setParticulars7(null);
        record.setParticulars8(null);
        record.setParticulars9(null);
        record.setParticulars10(maxXrLong);

        return record;
    }

    public static PluginDataObject[] convertGoesXrayEventsToGoesXrayPDOs(
            List<GoesXrayEvent> xrayEvents) {

        List<PluginDataObject> pdos = new ArrayList<PluginDataObject>();
        GOESXrayEvent record = null;
        
        for (int i = 0; i < xrayEvents.size(); i++) {

            record = convertGoesXrayEventToRecord(xrayEvents.get(i));
            pdos.add(record);

        }
        return pdos.toArray(new PluginDataObject[pdos.size()]);

    }

    public static GOESXrayEvent convertGoesXrayEventToRecord(
            GoesXrayEvent xrayEvent) {

        GOESXrayEvent record = new GOESXrayEvent();

        record.setDataTime(new DataTime(xrayEvent.getCurrentTime()));

        Calendar cal = Calendar.getInstance();
        cal.clear();
        cal.setTime(xrayEvent.getInsertTime());
        record.setInsertTime(Calendar.getInstance());
        record.setInsertDTTM(cal);

        String satellite[] = xrayEvent.getSource().toUpperCase().split("-");
        String satelliteDesignation = satellite[0].charAt(0) + satellite[1];
        record.setSatellite(satelliteDesignation);

        // Set begin date and time
        if (xrayEvent.getBeginTime() != null) {
            cal = Calendar.getInstance();
            cal.clear();
            cal.setTime(xrayEvent.getBeginTime());
            record.setBeginDTTM(cal);

        }

        record.setBeginXRShort(xrayEvent.getBeginXrShort());
        record.setBeginXRLong(xrayEvent.getBeginXrLong());
        record.setBeginXRatio(xrayEvent.getBeginXRatio());

        cal = Calendar.getInstance();
        cal.clear();
        cal.setTime(xrayEvent.getCurrentTime());
        record.setCurrentDTTM(cal);
        record.setCurrentXRShort(xrayEvent.getCurrentXrShort());
        record.setCurrentXRLong(xrayEvent.getCurrentXrLong());
        record.setCurrentXRatio(xrayEvent.getCurrentXRatio());

        record.setCurrentIntXRShort(xrayEvent.getCurrentIntXrShort());
        record.setCurrentIntXRLong(xrayEvent.getCurrentIntXrLong());

        // Set max date and time
        if (xrayEvent.getMaxTime() != null) {
            cal = Calendar.getInstance();
            cal.clear();
            cal.setTime(xrayEvent.getMaxTime());
            record.setMaxDTTM(cal);
        }   
        record.setMaxClass((xrayEvent.getMaxClass() != null)?xrayEvent.getMaxClass():null);

        // Set end date and time
        if (xrayEvent.getEndTime() != null) {
            cal = Calendar.getInstance();
            cal.clear();
            cal.setTime(xrayEvent.getEndTime());
            record.setEndDTTM(cal);
        }

        record.setMaxTemp(xrayEvent.getMaxTemp());
        record.setMaxEmissionMeas(xrayEvent.getMaxEmissionMeas());
        record.setMaxXRLong(xrayEvent.getMaxXrLong());

        record.setDescription("GOES Xray event");

        return record;
    }
    
}
