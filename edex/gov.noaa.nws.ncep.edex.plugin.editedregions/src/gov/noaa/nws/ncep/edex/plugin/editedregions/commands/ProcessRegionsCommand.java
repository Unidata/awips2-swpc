package gov.noaa.nws.ncep.edex.plugin.editedregions.commands;

import java.text.DecimalFormat;

import com.raytheon.uf.common.status.IUFStatusHandler;
import com.raytheon.uf.common.status.UFStatus;

import gov.noaa.nws.ncep.common.dataplugin.editedevents.request.ProcessEventsRequest;
import gov.noaa.nws.ncep.common.dataplugin.editedregions.exception.EditedRegionsException;
import gov.noaa.nws.ncep.common.dataplugin.editedregions.request.intf.IRequest;
import gov.noaa.nws.ncep.common.dataplugin.editedregions.response.intf.IResponse;
import gov.noaa.nws.ncep.edex.plugin.editedregions.dao.RegionReportsDao;
import gov.noaa.nws.ncep.edex.plugin.editedregions.dao.GoesXrayEventDao;

/**
 * Command class use to perform the processing of new events.
 * 
 * <pre>
 *
 * SOFTWARE HISTORY
 *
 * Date         Ticket#    Engineer    Description
 * ------------ ---------- ----------- --------------------------
 * Jan 14, 2016  R9583     jtravis     Initial creation
 *
 * </pre>
 *
 * @author jtravis
 * @version 1.0
 */
public class ProcessRegionsCommand extends BaseCommand {

    /**
     * The request from the client that resulted in creating an instance of the
     * command
     */
    private ProcessEventsRequest request = null;

    /**
     * Dao for Event records
     */
    private RegionReportsDao eventDao = null;

    /**
     * Dao for GoesXrayEvent records
     */
    private GoesXrayEventDao goesXrayEventDao = null;

    /**
     * Logger
     */
    private static final IUFStatusHandler statusHandler = UFStatus
            .getHandler(ProcessRegionsCommand.class);

    public ProcessRegionsCommand() {

    }

    /*
     * (non-Javadoc)
     *
     * @see
     * gov.noaa.nws.ncep.edex.plugin.editedevents.commands.ICommand#execute()
     */
    @Override
    public IResponse execute() {
        return null;
        //
        // this.setStartTime();
        //
        // Vector<Event> events = new Vector<Event>();
        //
        // try {
        //
        // eventDao = new EventsDao();
        // goesXrayEventDao = new GoesXrayEventDao();
        //
        // if (this.request.isProcessOtherEvents()) {
        //
        // Event event = new Event();
        //
        // // Get the list of GOESXrayEvents from swpc_goes_xray_event table
        // List<Map<String, Object>> results =
        // goesXrayEventDao.getRawEvents(this.request.getBeginDTTM(),
        // this.request.getEndDTTM());
        //
        // int resultsSize = (results != null) ? results.size() : 0;
        //
        // statusHandler.handle(Priority.INFO, "
        // EDITEDEVENTS.ProcessEventsCommand results = " + resultsSize);
        //
        // // loop through the results and create
        // // the event records.
        // for (int i = 0; i < resultsSize; i++) {
        //
        // Map<String, Object> map = results.get(i);
        //
        // if (map != null) {
        // event = this.createEventRecord(map);
        //
        // // Apply COR and DEL business rules
        // this.applyCORandDELRules(event);
        //
        // try {
        // // persist the event to the swpc_event table
        // eventDao.saveOrUpdate(event);
        // }
        // catch (DataIntegrityViolationException e) {
        // statusHandler.handle(Priority.INFO, "
        // EDITEDEVENTS.ProcessEventsCommand: DataIntegrityViolationException
        // occured");
        // }
        // }
        // }
        // }
        //
        // Calendar beginDTTM = Calendar.getInstance();
        // beginDTTM.clear();
        // beginDTTM.setTimeInMillis(this.request.getBeginDTTM());
        //
        // // Bin all the new events
        // AssignEventsToBinsCommand aetbCmd = new AssignEventsToBinsCommand();
        // aetbCmd.setBeginDTTM(beginDTTM);
        // if (aetbCmd.isValid()) {
        // aetbCmd.execute();
        // }
        //
        // // Select best events
        // SelectBestEventResponse sbeResponse = null;
        // SelectBestEventCommand sbeCmd = new SelectBestEventCommand();
        //
        // SelectBestEventRequest sbeRequest = new SelectBestEventRequest();
        // sbeRequest.setBeginDTTM(this.request.getBeginDTTM());
        // sbeRequest.setOrigin(EditedEventsConstants.Origin.PROCESS_EVENTS);
        //
        // sbeCmd.setRequest(sbeRequest);
        //
        // if (sbeCmd.isValid()) {
        // sbeResponse = (SelectBestEventResponse) sbeCmd.execute();
        // }
        //
        // statusHandler.handle(Priority.INFO, "
        // EDITEDEVENTS.ProcessEventsCommand.execute() complete.");
        //
        //
        // } catch (Exception e) {
        // error = new EditedEventsException(
        // "ERROR - Exception occured while executing the
        // ProcessEventsCommand.",
        // e);
        // e.printStackTrace();
        // }
        //
        // this.setEndTime();
        //
        // return this.createResponse(events);
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
     * @see
     *
     * gov.noaa.nws.ncep.edex.plugin.editedevents.commands.ICommand#getStartTime
     * ()
     */
    @Override
    public long getStartTime() {
        return this.startTime;
    }

    /*
     * (non-Javadoc)
     *
     * @see
     *
     * gov.noaa.nws.ncep.edex.plugin.editedevents.commands.ICommand#getEndTime()
     */
    @Override
    public long getEndTime() {
        return this.endTime;
    }

    /*
     * (non-Javadoc)
     *
     * @see gov.noaa.nws.ncep.edex.plugin.editedevents.commands.ICommand#
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
     * gov.noaa.nws.ncep.edex.plugin.editedevents.commands.ICommand#isValid()
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
     * @see
     * gov.noaa.nws.ncep.edex.plugin.editedevents.commands.ICommand#hasError()
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
     * @see
     *
     * gov.noaa.nws.ncep.edex.plugin.editedevents.commands.ICommand#getRequest()
     */
    @Override
    public IRequest getRequest() {
        return null;
    }

    /**
     * Create an Event record object based on the values in the given map
     *
     * @param map
     *
     * @return Event
     */
    // private Event createEventRecord(Map<String, Object> map) {
    //
    // statusHandler.handle(Priority.INFO, "
    // EDITEDEVENTS.ProcessEventsCommand.createEventRecord()");
    //
    // Event event = new Event();
    //
    // try {
    //
    // event.setType(EditedEventsConstants.EventType.XRA.getType());
    // event.setQuality(EditedEventsConstants.EVENT_QUALITY_5);
    // event.setCodedType(1);
    // event.setFrequency(EditedEventsConstants.GOES_XRA_EVENTS_FREQUENCY);
    // event.setChangeFlag(0);
    // event.setObservatory((String) map.get(GOESXrayEvent.SATELLITE));
    //
    // event.setAge(null);
    // event.setRegion(EditedEventsConstants.MISSING_REGION_VAL);
    // event.setBin(null);
    // event.setStatusCd(0);
    // event.setStatusText(null);
    // event.setPutdmsd(null);
    // event.setPutdmss(null);
    // event.setLocation(null);
    // event.setObsid(0);
    //
    // // Set begin date and time
    // Calendar beginDTTM = Calendar.getInstance();
    // Timestamp tmp = (Timestamp) map.get(GOESXrayEvent.BEGIN_DTTM);
    // if (tmp != null) {
    // beginDTTM.setTimeInMillis(tmp.getTime());
    // event.setBeginDate(beginDTTM);
    //
    // event.setBeginTime(Integer.valueOf((beginDTTM
    // .get(Calendar.HOUR_OF_DAY) + "" +
    // ERCommandUtil.getTwoDigitValueAsString(beginDTTM
    // .get(Calendar.MINUTE)))));
    // } else {
    // event.setBeginDate(null);
    // event.setBeginTime(null);
    // }
    //
    // event.setBegInq(null);
    // event.setDataTime(new DataTime(beginDTTM));
    //
    // // Set end date and time
    // Calendar endDTTM = Calendar.getInstance();
    // tmp = (Timestamp) map.get(GOESXrayEvent.END_DTTM);
    // if (tmp != null) {
    // endDTTM.setTimeInMillis(tmp.getTime());
    // event.setEndDate(endDTTM);
    //
    // event.setEndTime(Integer.valueOf((endDTTM.get(Calendar.HOUR_OF_DAY)
    // + "" +
    // ERCommandUtil.getTwoDigitValueAsString(endDTTM.get(Calendar.MINUTE)))));
    // } else {
    // event.setEndDate(null);
    // event.setEndTime(null);
    // }
    //
    // event.setEndq(null);
    //
    // // Set max date and time
    // Calendar maxDTTM = Calendar.getInstance();
    // tmp = (Timestamp) map.get(GOESXrayEvent.MAX_DTTM);
    // if (tmp != null) {
    // maxDTTM.setTimeInMillis(tmp.getTime());
    // event.setMaxDate(maxDTTM);
    //
    // event.setMaxTime(Integer.valueOf((maxDTTM.get(Calendar.HOUR_OF_DAY)
    // + "" +
    // ERCommandUtil.getTwoDigitValueAsString(maxDTTM.get(Calendar.MINUTE)))));
    // } else {
    // event.setMaxDate(null);
    // event.setMaxTime(null);
    // }
    //
    // event.setMaxq(null);
    //
    // String maxClass = ((String) map.get(GOESXrayEvent.MAX_CLASS) != null) ?
    // (String) map
    // .get(GOESXrayEvent.MAX_CLASS).toString() : "";
    //
    // double curIntXrLong = (double) ((map
    // .get(GOESXrayEvent.CURRENT_XR_LONG) != null) ? map
    // .get(GOESXrayEvent.CURRENT_XR_LONG) : 0.0);
    // double maxTemp = (double) ((map.get(GOESXrayEvent.MAX_TEMP) != null) ?
    // map
    // .get(GOESXrayEvent.MAX_TEMP) : 0.0);
    // double maxEmissionMeas = (double)
    // ((map.get(GOESXrayEvent.MAX_EMISSION_MEAS) != null) ? map
    // .get(GOESXrayEvent.MAX_EMISSION_MEAS) : 0.0);
    // double maxXrLong = (double) (( map.get(GOESXrayEvent.MAX_XR_LONG) !=
    // null) ? map
    // .get(GOESXrayEvent.MAX_XR_LONG) : 0.0);
    //
    // // Set the Particular1-10 fields
    //
    // // Truncate anything after the first character after the decimal point
    // String particulars1 = maxClass;
    // if (maxClass.contains(".")) {
    // particulars1 = maxClass.substring(0, maxClass.indexOf(".") + 2);
    // }
    // event.setParticulars1(particulars1);
    //
    // // Scientific Notation (rounding)
    // event.setParticulars2(this.format("0.00E00",
    // String.valueOf(curIntXrLong)));
    //
    // // Round to Integer
    // event.setParticulars3(this.format("######0", String.valueOf(maxTemp)));
    //
    // // 4 Sigfigs Two To Right of Decimal
    // event.setParticulars4(this.format("00.00",
    // String.valueOf(maxEmissionMeas)));
    //
    // event.setParticulars5(null);
    // event.setParticulars6(null);
    // event.setParticulars7(null);
    // event.setParticulars8(null);
    // event.setParticulars9(null);
    // event.setParticulars10(getParticulars10(String.valueOf(maxXrLong)));
    //
    // event.setInsertTime(Calendar.getInstance());
    //
    // } catch (Exception e) {
    // statusHandler.handle(Priority.INFO, " EDITEDEVENTS: Error occured at
    // ProcessEventsCommand.createEventRecord.", e);
    // }
    //
    // return event;
    // }

    /**
     * Add a new event to the swpc_event table
     *
     * @param event
     * @throws PluginException
     */
    // private void applyCORandDELRules(Event event) throws PluginException,
    // TransactionException {
    //
    // statusHandler.handle(Priority.INFO, "
    // EDITEDEVENTS.ProcessEventsCommand.applyCORandDELRules()");
    //
    // // Find existing/duplicate events with same beginDate, observatory,
    // // obsId and codedType as the event that is passed to this method
    // List<Event> dupEvents = eventDao.findDuplicateEvents(
    // event.getBeginDate(), event.getObservatory(), event.getObsid(),
    // event.getCodedType());
    //
    // statusHandler.handle(Priority.INFO, "
    // EDITEDEVENTS.ProcessEventsCommand.applyCORandDELRules(), dupEvents.size =
    // " + ((dupEvents != null)?dupEvents.size():0));
    //
    // statusHandler.handle(Priority.INFO, "
    // EDITEDEVENTS.ProcessEventsCommand.applyCORandDELRules(), event status
    // code = " +event.getStatusCd());
    //
    // switch (event.getStatusCd()) {
    // case 3: // Correction: delete original event, if one found & add new
    // // event
    //
    // applyCORRule(dupEvents);
    // event.setAge(EditedEventsConstants.EVENT_AGE.COR.toString());
    // break;
    //
    // case 4: // Deletion: Mark deleted event if one found & exit the method
    //
    // applyDELRule(dupEvents);
    // return;
    //
    // default: // Normal report: If duplicate found, exit; else, add the new
    // // event
    //
    // int dupEventsSize = (dupEvents != null) ? dupEvents.size() : 0;
    //
    // if (dupEventsSize == 0) {
    // event.setAge(EditedEventsConstants.EVENT_AGE.NEW.toString());
    // } else {
    // return;
    // }
    //
    // break;
    // }
    //
    // // Set additional parameters for the new event
    // event.setChangeFlag(1);
    // // setEventParameters(newEvent, event);
    //
    // // Persist new event to db
    // //eventDao.persist(event);
    //
    // }
    //
    // /**
    // * Apply CORRECTION rules when duplicate events exist: Delete original
    // * event, if one found
    // *
    // * @param event
    // * @param List
    // * <Event>
    // */
    // private void applyCORRule(List<Event> dupEvents) {
    //
    // int dupEventsSize = (dupEvents != null) ? dupEvents.size() : 0;
    //
    // for (int i = 0; i < dupEventsSize; i++) {
    //
    // Event dupEvent = dupEvents.get(i);
    // eventDao.delete(dupEvent);
    // }
    //
    // }

    // /**
    // * Apply DELETION rules when duplicate events exist: Mark deleted event if
    // * one found
    // *
    // * @param event
    // * @param List
    // * <Event>
    // */
    // private void applyDELRule(List<Event> dupEvents) {
    //
    // int dupEventsSize = (dupEvents != null) ? dupEvents.size() : 0;
    //
    // for (int i = 0; i < dupEventsSize; i++) {
    //
    // Event dupEvent = dupEvents.get(i);
    // dupEvent.setAge(EditedEventsConstants.EVENT_AGE.DEL.toString());
    // dupEvent.setStatusCd(1);
    // dupEvent.setStatusText(
    // EditedEventsConstants.EVENT_AGE.DEL.toString());
    // dupEvent.setChangeFlag(0);
    // eventDao.saveOrUpdate(dupEvent);
    // }
    // }

    /**
     * Format a String based on a pattern
     *
     * @param pattern
     * @param value
     *
     * @return String
     */
    public String format(String pattern, String value) {

        DecimalFormat myFormatter = new DecimalFormat(pattern);
        String output = myFormatter.format(Double.valueOf(value));

        return output;

    }

    /**
     * Return the value for Particulars10 by using Scientific Notation
     * (Truncating) format
     *
     * @param value
     * @return String
     */
    public String getParticulars10(String value) {

        String result = null;

        // TODO double check this logic later
        if (value.length() > 4) {
            result = String.format("%e",
                    Double.parseDouble(value.substring(0, 3)))
                    + String.format("%e", Double.parseDouble(value
                            .substring(value.length() - 4, value.length())));
        }

        return result;

    }

    /*
     * (non-Javadoc)
     *
     * @see
     *
     * gov.noaa.nws.ncep.edex.plugin.editedevents.commands.intf.ICommand#
     * setRequest
     *
     * (gov.noaa.nws.ncep.common.dataplugin.editedevents.request.intf.IRequest)
     */
    @Override
    public void setRequest(IRequest request) {
        this.request = (ProcessEventsRequest) request;
    }

    // /**
    // * Create the response
    // *
    // * @param results
    // * @return IResponse
    // */
    // private IResponse createResponse(Vector<Event> events) {
    //
    // ProcessEventsResponse response = new ProcessEventsResponse();
    //
    // // set the results
    // ProcessEventsResults results = new ProcessEventsResults();
    // results.setEvents(events);
    //
    // // populate the response and the results
    // if (this.hasError()) {
    // response.setError(this.getError());
    // } else {
    // response.setResults(results);
    // }
    //
    // response.setRequest(this.getRequest());
    // response.setProcessingTime(this.getProcessingTime());
    //
    // return response;
    //
    // }

}
