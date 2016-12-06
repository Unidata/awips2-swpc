package gov.noaa.nws.ncep.viz.ui.editedregions.util;

import gov.noaa.nws.ncep.common.dataplugin.editedevents.Event;
import gov.noaa.nws.ncep.common.dataplugin.editedevents.exception.EditedEventsException;
import gov.noaa.nws.ncep.common.dataplugin.editedevents.gateway.Gateway;
import gov.noaa.nws.ncep.common.dataplugin.editedevents.request.AddNewBinForEventRequest;
import gov.noaa.nws.ncep.common.dataplugin.editedevents.request.DowngradeEventRequest;
import gov.noaa.nws.ncep.common.dataplugin.editedevents.request.ExitRequest;
import gov.noaa.nws.ncep.common.dataplugin.editedevents.request.GetBinsRequest;
import gov.noaa.nws.ncep.common.dataplugin.editedevents.request.ProcessEventsRequest;
import gov.noaa.nws.ncep.common.dataplugin.editedevents.request.ReBinEventRequest;
import gov.noaa.nws.ncep.common.dataplugin.editedevents.request.UpgradeEventRequest;
import gov.noaa.nws.ncep.common.dataplugin.editedevents.response.AddNewBinForEventResponse;
import gov.noaa.nws.ncep.common.dataplugin.editedevents.response.DowngradeEventResponse;
import gov.noaa.nws.ncep.common.dataplugin.editedevents.response.ExitResponse;
import gov.noaa.nws.ncep.common.dataplugin.editedevents.response.GetBinsResponse;
import gov.noaa.nws.ncep.common.dataplugin.editedevents.response.ProcessEventsResponse;
import gov.noaa.nws.ncep.common.dataplugin.editedevents.response.ReBinEventResponse;
import gov.noaa.nws.ncep.common.dataplugin.editedevents.response.UpgradeEventResponse;
import gov.noaa.nws.ncep.common.dataplugin.editedevents.results.GetBinsResults;
import gov.noaa.nws.ncep.common.dataplugin.editedevents.results.ProcessEventsResults;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Vector;

import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;

import com.raytheon.uf.viz.core.exception.VizException;

/**
 * TODO Add Description
 * 
 * <pre>
 * 
 * SOFTWARE HISTORY
 * 
 * Date         Ticket#    Engineer    Description
 * ------------ ---------- ----------- --------------------------
 * Jan 8, 2015            sgurung     Initial creation
 * 
 * </pre>
 * 
 * @author sgurung
 * @version 1.0
 */

public class EditEventsUtil {

    /**
     * Send a request to Gateway to start processing the newly added event
     * 
     * @param beginDTTM
     */
    public static void processNewEvents(long beginDTTM) {
        processNewEvents(beginDTTM, beginDTTM, false);
    }

    /**
     * Send a request to Gateway to start processing of new events
     * 
     * @param beginDTTM
     * @param endDTTM
     */
    public static void processNewEvents(long beginDTTM, long endDTTM) {
        processNewEvents(beginDTTM, endDTTM, true);
    }

    /**
     * Send a request to Gateway to start processing of new events
     * 
     * @param beginDTTM
     * @param endDTTM
     * @param processOtherEvents
     */
    public static void processNewEvents(long beginDTTM, long endDTTM,
            boolean processOtherEvents) {

        ProcessEventsRequest processEventsRequest = new ProcessEventsRequest(
                processOtherEvents);
        processEventsRequest.setBeginDTTM(beginDTTM);
        processEventsRequest.setEndDTTM(endDTTM);

        ProcessEventsResponse processEventsResponse = null;
        Vector<gov.noaa.nws.ncep.common.dataplugin.editedevents.Event> processedEvents = null;

        try {
            processEventsResponse = Gateway.getInstance().submit(
                    processEventsRequest);

            if (!processEventsResponse.hasErrors()
                    && processEventsResponse.getResults() != null) {
                processedEvents = ((ProcessEventsResults) processEventsResponse
                        .getResults()).getEvents();
            }

        } catch (EditedEventsException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
    }

    /**
     * Returns the list of all the bins in the db within a given date range
     * 
     * @param beginDTTM
     * @param endDTTM
     * @return List<Integer>
     */
    public static List<Integer> getBins(long beginDTTM, long endDTTM) {

        GetBinsRequest request = new GetBinsRequest();
        request.setBeginDTTM(beginDTTM);
        request.setEndDTTM(endDTTM);

        GetBinsResponse response = null;
        List<Integer> eventBins = null;

        try {

            if (request.isValid()) {
                response = Gateway.getInstance().submit(request);

                if (!response.hasErrors() && response.getResults() != null) {
                    eventBins = ((GetBinsResults) response.getResults())
                            .getEventBins();
                }
            }

        } catch (EditedEventsException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }

        return eventBins;
    }

    /**
     * Send a request to Gateway to re-bin/associate an event to a different bin
     * number
     * 
     * @param beginDTTM
     * @param event
     * @param newBin
     */
    public static void rebinEvent(long beginDTTM, Event event, Integer newBin) {

        ReBinEventRequest request = new ReBinEventRequest();
        ReBinEventResponse response = null;
        request.setCurrentBin(event.getBin().getBinNumber());
        request.setEvent(event);
        request.setEventId(event.getId());
        request.setStartDTTM(beginDTTM);
        request.setNewBin(newBin);

        Gateway gateway = Gateway.getInstance();

        try {

            response = gateway.submit(request);

        } catch (EditedEventsException e) {
            e.getMessage();
            e.printStackTrace();
        }
    }

    /**
     * Send a request to Gateway to re-bin/associate an event to a new bin
     * number
     * 
     * @param beginDTTM
     * @param event
     * @param newBin
     */
    public static void addNewBinForEvent(long beginDTTM, Event event) {

        AddNewBinForEventRequest request = new AddNewBinForEventRequest();
        AddNewBinForEventResponse response = null;
        request.setOriginalBin(event.getBin().getBinNumber());
        request.setEvent(event);
        request.setEventId(event.getId());
        request.setStartDTTM(beginDTTM);

        Gateway gateway = Gateway.getInstance();

        try {

            response = gateway.submit(request);

        } catch (EditedEventsException e) {
            e.getMessage();
            e.printStackTrace();
        }
    }

    /**
     * Send a request to Gateway to upgrade an event
     * 
     * @param beginDTTM
     * @param event
     * @return String
     */
    public static String upgradeEvent(long beginDTTM, Event event) {

        UpgradeEventRequest request = new UpgradeEventRequest();
        UpgradeEventResponse response = null;
        String message = null;
        request.setEvent(event);
        request.setEventID(event.getId());
        request.setBeginDTTM(beginDTTM);

        Gateway gateway = Gateway.getInstance();

        try {

            response = gateway.submit(request);

            if (!response.hasErrors() && response.getMessage() != null) {
                message = response.getMessage();
            }

        } catch (EditedEventsException e) {
            e.getMessage();
            e.printStackTrace();
        }

        return message;
    }

    /**
     * Send a request to Gateway to downgrade an event
     * 
     * @param beginDTTM
     * @param event
     * @return String
     */
    public static String downgradeEvent(long beginDTTM, Event event) {

        DowngradeEventRequest request = new DowngradeEventRequest();
        DowngradeEventResponse response = null;
        String message = null;
        request.setEvent(event);
        request.setEventID(event.getId());
        request.setBeginDTTM(beginDTTM);

        Gateway gateway = Gateway.getInstance();

        try {

            response = gateway.submit(request);

            if (!response.hasErrors() && response.getMessage() != null) {
                message = response.getMessage();
            }

        } catch (EditedEventsException e) {
            e.getMessage();
            e.printStackTrace();
        }

        return message;
    }

    /**
     * Add "0" in front of the number if it is a 1 digit number
     * 
     * @param number
     * @return String
     */
    public static String getTwoDigitValueAsString(int number) {

        String min = String.valueOf(number);

        if (min.length() == 1) {
            min = "0" + min;
        }

        return min;

    }

    /**
     * Return the concatenation of hours and minutes from the Calendar as an
     * integer
     * 
     * @param cal
     * @return Integer
     */
    public static Integer getHourAndMinuteCombination(Calendar cal) {

        String value = getTwoDigitValueAsString(cal.get(Calendar.HOUR_OF_DAY))
                + "" + getTwoDigitValueAsString(cal.get(Calendar.MINUTE));

        return Integer.parseInt(value);

    }

    /**
     * Display a message dialog.
     * 
     * @param shell
     * @param message
     *            Message to display in the dialog.
     */
    public static void displayMessageDialog(Shell shell, String message) {
        displayMessageDialog(shell, message, "Information");
    }

    /**
     * Display a message dialog.
     * 
     * @param shell
     * @param message
     * @param title
     */
    public static void displayMessageDialog(Shell shell, String message,
            String title) {
        MessageBox mb = new MessageBox(shell, SWT.ICON_INFORMATION | SWT.OK);
        mb.setText(title);
        mb.setMessage(message);
        mb.open();
    }

    /**
     * Checks if the date is valid/conforms to the date format
     * 
     * @param dateToValidate
     * @param sdf
     * @return boolean
     */
    public boolean isDateValid(String dateToValidate, SimpleDateFormat sdf) {

        if (dateToValidate == null) {
            return false;
        }

        sdf.setLenient(false);

        try {
            Date date = sdf.parse(dateToValidate);
            System.out.println(date);

        } catch (ParseException e) {

            e.printStackTrace();
            return false;
        }

        return true;
    }

    /**
     * Send a request to Gateway to perform certain operations when the EE
     * application is closed
     * 
     * @param beginDTTM
     * @return String
     */
    public static String exitEditedEvents(long beginDTTM) {

        ExitRequest request = new ExitRequest();
        ExitResponse response = null;
        String message = null;
        request.setBeginDTTM(beginDTTM);

        Gateway gateway = Gateway.getInstance();

        try {

            response = gateway.submit(request);

            if (!response.hasErrors() && response.getMessage() != null) {
                message = response.getMessage();
            }

        } catch (EditedEventsException e) {
            e.getMessage();
            e.printStackTrace();
        }

        return message;
    }

    /**
     * Export the table data to a file.
     * 
     * @param tableViewer
     * @param file
     * @throws VizException
     */
    public static void exportTable(TableViewer tableViewer, File file)
            throws VizException {

        if (file == null) {
            return;
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {

            final Table table = tableViewer.getTable();
            final int[] columnOrder = table.getColumnOrder();
            for (int columnOrderIndex = 0; columnOrderIndex < columnOrder.length; columnOrderIndex++) {
                int columnIndex = columnOrder[columnOrderIndex];
                TableColumn tableColumn = table.getColumn(columnIndex);

                writer.write(tableColumn.getText());
                writer.write(",");
            }
            writer.write("\n");

            final int itemCount = table.getItemCount();
            for (int itemIndex = 0; itemIndex < itemCount; itemIndex++) {
                TableItem item = table.getItem(itemIndex);

                for (int columnOrderIndex = 0; columnOrderIndex < columnOrder.length; columnOrderIndex++) {
                    int columnIndex = columnOrder[columnOrderIndex];
                    writer.write(item.getText(columnIndex));
                    writer.write(",");
                }
                writer.write("\n");
            }

        } catch (IOException ioe) {

            throw new VizException(
                    "Error exporting edited events data to file.", ioe);

        }
    }

}
