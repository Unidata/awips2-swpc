/**
 * for Environmental Prediction, without warranty of any kind, either expressed or implied, 
 * including but not limited to the implied warranties of merchantability and/or fitness 
 * for a particular purpose.
 * 
 * This code has been developed by the NCEP-SIB for use in the AWIPS2 system.
 * 
 **/
package gov.noaa.nws.ncep.viz.ui.editedregions.dialog;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.ColumnViewerEditorActivationEvent;
import org.eclipse.jface.viewers.ColumnViewerEditorActivationListener;
import org.eclipse.jface.viewers.ColumnViewerEditorDeactivationEvent;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.jface.viewers.ViewerCell;
import org.eclipse.jface.window.Window;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Text;

import com.raytheon.uf.common.status.IUFStatusHandler;
import com.raytheon.uf.common.status.UFStatus;
import com.raytheon.uf.common.status.UFStatus.Priority;

import gov.noaa.nws.ncep.common.dataplugin.editedregions.RegionReport;
import gov.noaa.nws.ncep.common.dataplugin.editedregions.exception.EditedRegionsException;
import gov.noaa.nws.ncep.common.dataplugin.editedregions.gateway.Gateway;
import gov.noaa.nws.ncep.common.dataplugin.editedregions.request.ExitRequest;
import gov.noaa.nws.ncep.common.dataplugin.editedregions.response.ExitResponse;
import gov.noaa.nws.ncep.common.dataplugin.editedregions.response.GetConsensusResponse;
import gov.noaa.nws.ncep.common.dataplugin.editedregions.results.GetConsensusTodaysResults;
import gov.noaa.nws.ncep.common.dataplugin.editedregions.results.GetConsensusYesterdaysResults;
import gov.noaa.nws.ncep.common.dataplugin.editedregions.results.GetRegionReportsResults;
import gov.noaa.nws.ncep.common.dataplugin.editedregions.util.EditedRegionsConstants;
import gov.noaa.nws.ncep.viz.ui.editedregions.util.EditRegionsServerUtil;
import gov.noaa.nws.ncep.viz.ui.editedregions.util.EditRegionsUIConstants;

/**
 * Dialog for the main user interface for the EditedEvents application
 * 
 * <pre>
 * SOFTWARE HISTORY
 * Date         Ticket#     Engineer    Description
 * ------------ ----------  ----------- --------------------------
 * 01/15        R9583       sgurung   Initial Creation.
 * 
 * </pre>
 * 
 * @author sgurung
 * 
 */
public class EditRegionsDialog extends Dialog { // implements IEventsObserver {

    // singleton instance
    private static EditRegionsDialog INSTANCE;

    private final IUFStatusHandler statusHandler = UFStatus
            .getHandler(EditRegionsDialog.class);

    // last location of the dialog
    private Point lastLocation = null;

    // last size
    private Point lastSize = null;

    // Table viewer for submitted jobs
    private TableViewer assignedRegionTableViewer = null;

    private TableViewer unassignedRegionTableViewer = null;

    // The region combo selector
    private Combo regionCombo;

    /**
     * The ID for the event which is to be selected
     */
    private Integer selectEventId = null;

    private Calendar date;

    // TODO: Replace with columns for Edited Regions
    private String[] columnTitles = EditRegionsUIConstants.COLUMNS;

    private int[] columnBounds = { 50, 50, 75, 50, 50, 50, 50, 50, 50, 50, 45,
            75, 65, 85, 85, 85, 85, 85, 85, 85, 50, 50, 50, 50 };

    // Consensus text fields.

    private Text textTodaysObservationTime;

    private Text textTodaysObservatory;

    private Text textTodaysRegion;

    private Text textTodaysReportLocation;

    private Text textTodays00ZLocation;

    private Text textTodaysCarlon;

    private Text textTodaysExtent;

    private Text textTodaysArea;

    private Text textTodaysNumspots;

    private Text textTodaysSpotclass;

    private Text textTodaysMagclass;

    private Text textYesterdaysObservationTime;

    private Text textYesterdaysObservatory;

    private Text textYesterdaysRegion;

    private Text textYesterdaysReportLocation;

    private Text textYesterdays00ZLocation;

    private Text textYesterdaysCarlon;

    private Text textYesterdaysExtent;

    private Text textYesterdaysArea;

    private Text textYesterdaysNumspots;

    private Text textYesterdaysSpotclass;

    private Text textYesterdaysMagclass;

    private Text textFinalObservationTime;

    private Text textFinalObservatory;

    private Text textFinalRegion;

    private Text textFinalReportLocation;

    private Text textFinal00ZLocation;

    private Text textFinalCarlon;

    private Text textFinalExtent;

    private Text textFinalArea;

    private Text textFinalNumspots;

    private Text textFinalSpotclass;

    private Text textFinalMagclass;

    /**
     * Label provider for the cells in the edit events table
     */
    // TODO: We should consider updating this class to return a column count,
    // possibly add
    // a new interface.
    private EditRegionsLabelProvider labelProvider = null;

    private static final Point DIALOG_SIZE = new Point(1200, 900);

    /**
     * Creates a EditEventsDialog instance
     * 
     * @param parent
     */
    public EditRegionsDialog(Shell parent) {
        super(parent);

        setShellStyle(SWT.TITLE | SWT.CLOSE | SWT.MAX | SWT.MIN | SWT.RESIZE
                | SWT.MODELESS);

    }

    /**
     * Creates the dialog if the dialog does not exist and returns the instance.
     * If the dialog exists, return the instance.
     * 
     * @param parShell
     * @return
     */
    public static EditRegionsDialog getInstance(Shell parShell) {

        if (INSTANCE == null) {
            INSTANCE = new EditRegionsDialog(parShell);
        }
        return INSTANCE;

    }

    /*
     * (non-Javadoc)
     * 
     * Creates the dialog area
     * 
     * @see
     * org.eclipse.jface.dialogs.Dialog#createDialogArea(org.eclipse.swt.widgets
     * .Composite)
     */
    @Override
    public Control createDialogArea(Composite parent) {

        Composite top = (Composite) super.createDialogArea(parent);

        date = Calendar.getInstance(EditedRegionsConstants.TIME_ZONE_UTC);

        // Create the main layout for the shell.
        GridLayout mainLayout = new GridLayout(1, true);
        mainLayout.marginHeight = 1;
        mainLayout.marginWidth = 1;
        top.setLayout(mainLayout);
        top.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));

        // Initialize all of the controls, and layouts
        initializeComponents(top);

        this.getShell().setSize(DIALOG_SIZE);

        this.getShell().setMinimumSize(DIALOG_SIZE);

        Display.getCurrent().asyncExec(new Runnable() {
            @Override
            public void run() {
                EditRegionsDialog.this.getShell().layout(true, true);
            }
        });
        return top;

    }

    /**
     * Creates buttons, menus, and other controls in the dialog area
     * 
     */
    private void initializeComponents(Composite parent) {

        getShell().setText("Edited Regions");

        // Sash form to hold the filter criteria area and events list table
        SashForm sashForm = new SashForm(parent, SWT.VERTICAL);
        GridData sashGd = new GridData(SWT.FILL, SWT.FILL, true, true);
        sashForm.setLayoutData(sashGd);
        sashForm.setSashWidth(3);

        createRegionControls(sashForm);

        assignedRegionTableViewer = createRegionsListControls(sashForm,
                "Assigned Regions");

        unassignedRegionTableViewer = createRegionsListControls(sashForm,
                "Unassigned Regions");

        createConsensusControls(sashForm);

        createCloseControl(sashForm);

        sashForm.setWeights(new int[] { 2, 1, 6, 1, 6, 6, 2 });

        parent.pack(true);

        refreshDialog();

    }

    /*
     * (non-Javadoc)
     * 
     * Do not create Ok/Cancel buttons for this dialog.
     * 
     * @see
     * org.eclipse.jface.dialogs.Dialog#createButtonBar(org.eclipse.swt.widgets.
     * Composite)
     */
    @Override
    public Control createButtonBar(Composite parent) {
        return null;
    }

    /**
     * Create controls for Yesterday's Report, Today's Consensus, and Today's
     * Final
     * 
     * @param parent
     */
    private void createConsensusControls(Composite parent) {
        Composite consensusComp = new Composite(parent, SWT.NONE);
        consensusComp.setLayout(new GridLayout(2, false));

        Group gridGroup = new Group(consensusComp, SWT.SHADOW_OUT);
        gridGroup.setLayout(new GridLayout(columnTitles.length + 1, false));
        gridGroup.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));

        Composite gridComp = new Composite(gridGroup, SWT.NONE);

        GridLayout gridLayout = new GridLayout(
                EditRegionsUIConstants.CONSENSUS_COLUMNS.length + 1, false);

        gridComp.setLayout(gridLayout);
        gridComp.setLayoutData(
                new GridData(GridData.CENTER, SWT.TOP, true, true));

        new Label(gridComp, SWT.CENTER).setText("");

        for (String column : EditRegionsUIConstants.CONSENSUS_COLUMNS) {
            new Label(gridComp, SWT.CENTER | SWT.WRAP).setText(column);
        }

        new Label(gridComp, SWT.LEFT).setText("Yesterday's report");
        textYesterdaysObservationTime = createConsensusTextControl(gridComp);
        textYesterdaysObservatory = createConsensusTextControl(gridComp);
        textYesterdaysRegion = createConsensusTextControl(gridComp);
        textYesterdaysReportLocation = createConsensusTextControl(gridComp);
        textYesterdays00ZLocation = createConsensusTextControl(gridComp);
        textYesterdaysCarlon = createConsensusTextControl(gridComp);
        textYesterdaysExtent = createConsensusTextControl(gridComp);
        textYesterdaysArea = createConsensusTextControl(gridComp);
        textYesterdaysNumspots = createConsensusTextControl(gridComp);
        textYesterdaysSpotclass = createConsensusTextControl(gridComp);
        textYesterdaysMagclass = createConsensusTextControl(gridComp);

        new Label(gridComp, SWT.LEFT).setText("Today's Consensus");
        textTodaysObservationTime = createConsensusTextControl(gridComp);
        textTodaysObservatory = createConsensusTextControl(gridComp);
        textTodaysRegion = createConsensusTextControl(gridComp);
        textTodaysReportLocation = createConsensusTextControl(gridComp);
        textTodays00ZLocation = createConsensusTextControl(gridComp);
        textTodaysCarlon = createConsensusTextControl(gridComp);
        textTodaysExtent = createConsensusTextControl(gridComp);
        textTodaysArea = createConsensusTextControl(gridComp);
        textTodaysNumspots = createConsensusTextControl(gridComp);
        textTodaysSpotclass = createConsensusTextControl(gridComp);
        textTodaysMagclass = createConsensusTextControl(gridComp);

        new Label(gridComp, SWT.LEFT).setText("Today's Final");
        textFinalObservationTime = createConsensusTextControl(gridComp);
        textFinalObservatory = createConsensusTextControl(gridComp);
        textFinalRegion = createConsensusTextControl(gridComp);
        textFinalReportLocation = createConsensusTextControl(gridComp);
        textFinal00ZLocation = createConsensusTextControl(gridComp);
        textFinalCarlon = createConsensusTextControl(gridComp);
        textFinalExtent = createConsensusTextControl(gridComp);
        textFinalArea = createConsensusTextControl(gridComp);
        textFinalNumspots = createConsensusTextControl(gridComp);
        textFinalSpotclass = createConsensusTextControl(gridComp);
        textFinalMagclass = createConsensusTextControl(gridComp);

        Composite checkboxComp = new Composite(consensusComp, SWT.NONE);
        checkboxComp.setLayout(new GridLayout(1, true));
        new Button(checkboxComp, SWT.CHECK).setText("Fix Final");
        new Button(checkboxComp, SWT.CHECK).setText("Inactivate");

    }

    private Text createConsensusTextControl(Composite parent) {
        Text text = new Text(parent, SWT.BORDER | SWT.READ_ONLY);
        text.setLayoutData(new GridData(SWT.CENTER, SWT.TOP, true, true));
        return text;
    }

    /**
     * @param parent
     */
    private void createCloseControl(Composite parent) {

        Composite closeComp = new Composite(parent, SWT.NONE);
        closeComp.setLayout(new GridLayout(1, false));

        Button closeButton = new Button(closeComp, SWT.PUSH);
        closeButton.setText("    Close    ");
        closeButton.setLayoutData(
                new GridData(SWT.RIGHT, SWT.CENTER, true, true, 1, 1));
        closeButton.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                close();
            }
        });
    }

    /**
     * Creates controls in the Events List section (bottom section) in the tab
     * 
     * @param parent
     */
    private TableViewer createRegionsListControls(Composite parent,
            String label) {

        new Label(parent, SWT.LEFT).setText(label);

        SashForm sashForm = new SashForm(parent, SWT.HORIZONTAL);
        GridData sashGd = new GridData(SWT.FILL, SWT.FILL, true, true);
        sashForm.setLayoutData(sashGd);
        sashForm.setSashWidth(3);

        // Create the events list table viewer
        TableViewer tableViewer = new TableViewer(sashForm,
                SWT.V_SCROLL | SWT.H_SCROLL | SWT.FULL_SELECTION);
        // eventTableViewer.setSorter(new EventViewerSorter());

        tableViewer.setContentProvider(new ArrayContentProvider());
        // tableViewer.setInput(this.getEvents());

        labelProvider = new EditRegionsLabelProvider(
                System.currentTimeMillis());

        // Create the columns
        createColumns(tableViewer, labelProvider);

        tableViewer.setLabelProvider(labelProvider);

        tableViewer
                .addSelectionChangedListener(new ISelectionChangedListener() {
                    public void selectionChanged(
                            final SelectionChangedEvent event) {
                        // IStructuredSelection selection =
                        // (IStructuredSelection) event
                        // .getSelection();
                        //
                        // gov.noaa.nws.ncep.common.dataplugin.editedregions.Event
                        // selectedEvent =
                        // (gov.noaa.nws.ncep.common.dataplugin.editedregions.Event)
                        // selection
                        // .getFirstElement();
                        //
                        // if (selectedEvent != null) {
                        // selectEventId = selectedEvent.getId();
                        // }

                    }
                });

        Table table = tableViewer.getTable();

        // Show the lines and column headers
        table.setLinesVisible(true);
        table.setHeaderVisible(true);

        if (tableViewer.getTable().getItemCount() > 0
                && selectEventId == null) {
            tableViewer.getTable().setFocus();
            tableViewer.setSelection(
                    new StructuredSelection(tableViewer.getElementAt(0)), true);
            tableViewer.getTable().showSelection();
            tableViewer.getTable().notifyListeners(SWT.Selection, null);
        }

        tableViewer.refresh();
        resizeTable(tableViewer);

        createContextMenu(tableViewer);

        tableViewer.getColumnViewerEditor().addEditorActivationListener(
                createEditorActivationListener(tableViewer));
        return tableViewer;

    }

    /**
     * @param tableViewer
     * @return
     */
    private ColumnViewerEditorActivationListener createEditorActivationListener(
            TableViewer tableViewer) {
        final Table t = tableViewer.getTable();
        return new ColumnViewerEditorActivationListener() {

            @Override
            public void beforeEditorDeactivated(
                    ColumnViewerEditorDeactivationEvent event) {
            }

            @Override
            public void beforeEditorActivated(
                    ColumnViewerEditorActivationEvent event) {
                ViewerCell cell = (ViewerCell) event.getSource();
                t.showColumn(t.getColumn(cell.getColumnIndex()));

            }

            @Override
            public void afterEditorDeactivated(
                    ColumnViewerEditorDeactivationEvent event) {

                refreshDialog();
            }

            @Override
            public void afterEditorActivated(
                    ColumnViewerEditorActivationEvent event) {

            }
        };
    }

    /**
     * Creates controls for the upper section of the Edit Regions dialog.
     * 
     * @param parent
     */
    private void createRegionControls(Composite parent) {

        Group regionGroup = new Group(parent, SWT.SHADOW_OUT);
        regionGroup.setLayout(new GridLayout(16, false));
        regionGroup
                .setLayoutData(new GridData(SWT.LEAD, SWT.CENTER, true, true));

        Composite regionComp = new Composite(regionGroup, SWT.None);

        GridLayout gridLayout = new GridLayout(11, false);

        regionComp.setLayout(gridLayout);
        regionComp.setLayoutData(
                new GridData(GridData.CENTER, SWT.TOP, true, true));

        Button btnLeft = new Button(regionComp, SWT.PUSH);
        Button btnRight = new Button(regionComp, SWT.PUSH);

        btnLeft.setText("<--");
        btnRight.setText("-->");

        new Label(regionComp, SWT.LEAD).setText("");

        new Label(regionComp, SWT.LEAD).setText("Region:");

        regionCombo = new Combo(regionComp, SWT.READ_ONLY | SWT.DROP_DOWN);

        Button newRegionButton = new Button(regionComp, SWT.PUSH);
        newRegionButton.setText("New Region");

        newRegionButton.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent ev) {
                createNewRegion();
            }
        });

        Button newRegionReportButton = new Button(regionComp, SWT.PUSH);
        newRegionReportButton.setText("Create Report");

        newRegionReportButton.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent ev) {
                createNewRegionReport();
            }
        });

        Button undoRegionButton = new Button(regionComp, SWT.PUSH);
        undoRegionButton.setText("Undo Region");

        new Label(regionComp, SWT.LEAD).setText("");

        Text dateText = new Text(regionComp, SWT.BORDER);
        dateText.setText(EditRegionsUIConstants.DATE_FORMAT.get()
                .format(date.getTime()));
        dateText.setEditable(false);

        Button refreshButton = new Button(regionComp, SWT.PUSH);
        refreshButton.setText("Refresh");

        refreshButton.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent ev) {
                refreshDialog();
            }
        });

    }

    /*
     * (non-Javadoc)
     * 
     * Set the location and size of the dialog
     * 
     * @see org.eclipse.jface.window.Window#open()
     */
    @Override
    public int open() {

        if (this.getShell() == null) {
            this.create();
        }

        Point size = getInitialSize();
        getShell().setSize(size);
        getShell().setLocation(getInitialLocation(size));

        return super.open();
    }

    /*
     * (non-Javadoc)
     * 
     * Get the last used size of the dialog.
     * 
     * @return
     * 
     * @see org.eclipse.jface.dialogs.Dialog#getInitialSize()
     */
    @Override
    protected Point getInitialSize() {
        return (lastSize == null) ? super.getInitialSize() : lastSize;
    }

    /*
     * (non-Javadoc)
     * 
     * Get the last used location of the dialog.
     * 
     * @param size
     * 
     * @return
     * 
     * @see org.eclipse.jface.dialogs.Dialog#getInitialLocation(org.eclipse.swt.
     * graphics.Point)
     */
    @Override
    protected Point getInitialLocation(Point size) {
        return (lastLocation == null) ? super.getInitialLocation(size)
                : lastLocation;
    }

    /*
     * (non-Javadoc)
     * 
     * Save location and size of the dialog.
     * 
     * @see org.eclipse.jface.dialogs.Dialog#close()
     */
    @Override
    public boolean close() {

        if (confirmClose()) {

            if (getShell() != null && !getShell().isDisposed()) {
                Rectangle bounds = getShell().getBounds();
                lastLocation = new Point(bounds.x, bounds.y);
                lastSize = getShell().getSize();
            }
            return super.close();
        } else {
            refreshDialog();
            return false;
        }

    }

    /**
     * Confirm before closing the dialog
     * 
     * @return boolean
     */
    private boolean confirmClose() {

        boolean close = false;

        MessageBox saveExitConfMB = new MessageBox(this.getShell(),
                SWT.OK | SWT.CANCEL);
        saveExitConfMB.setText("Confirm exit");
        saveExitConfMB.setMessage("Do you want to save and exit?");

        int buttonID = saveExitConfMB.open();

        if (buttonID == SWT.OK) {
            ExitRequest request = new ExitRequest();
            ExitResponse response = null;
            request.setBeginDTTM(System.currentTimeMillis());

            Gateway gateway = Gateway.getInstance();

            try {
                response = gateway.submit(request);
                if (response.getError() != null) {
                    throw response.getError();
                }

            } catch (EditedRegionsException e) {
                statusHandler.handle(Priority.PROBLEM, e.getLocalizedMessage(),
                        e);
            }
            close = true;
        }

        return close;
    }

    /**
     * Create the columns for the events list table
     */
    private void createColumns(TableViewer tableViewer,
            EditRegionsLabelProvider labelProvider) {

        for (int i = 0; i < columnTitles.length; i++) {
            TableViewerColumn col = createTableViewerColumn(tableViewer,
                    columnTitles[i], columnBounds[i], i);

            // Add tool tip text for the column headers
            col.setEditingSupport(
                    labelProvider.getEditorSupport(tableViewer, i));
            col.getColumn().setToolTipText(EditRegionsUIConstants.TOOLTIPS[i]);

        }

    }

    /**
     * Create a column of a table viewer
     * 
     * @param title
     *            - column title
     * @param bound
     *            - column width
     * @param colNumber
     *            - column number
     * @return TableViewerColumn
     */
    private TableViewerColumn createTableViewerColumn(TableViewer tableViewer,
            String title, int bound, final int colNumber) {
        final TableViewerColumn viewerColumn = new TableViewerColumn(
                tableViewer, SWT.NONE);

        final TableColumn column = viewerColumn.getColumn();
        column.setText(title);
        column.setWidth(bound);
        column.setResizable(true);
        column.setMoveable(true);

        // column.addSelectionListener(new SelectionAdapter() {
        //
        // @Override
        // public void widgetSelected(SelectionEvent e) {
        // ((EventViewerSorter) eventTableViewer.getSorter())
        // .setColumn(colNumber);
        // ((EventViewerSorter) eventTableViewer.getSorter())
        // .doSort(colNumber);
        //
        // int dir = ((EventViewerSorter) eventTableViewer.getSorter())
        // .getDirection();
        // eventTableViewer.getTable().setSortColumn(column);
        // eventTableViewer.getTable().setSortDirection(dir);
        //
        // // set sort direction
        // eventsListTable.setSortDirection(eventTableViewer.getTable()
        // .getSortDirection() == 0 ? SWT.UP : SWT.DOWN);
        //
        // eventTableViewer.refresh();
        // eventsListTable.setRedraw(true);
        // }
        // });

        return viewerColumn;
    }

    /**
     * Refreshes all items in the dialog, including the lists of assigned and
     * unassigned region reports, along with the list of valid regions.
     * 
     * TODO this comment needs to change
     */
    private void refreshDialog() {
        List<RegionReport> assignedReports = Collections.emptyList();
        List<RegionReport> unassignedReports = Collections.emptyList();

        List<Integer> regionIDs = Collections.emptyList();

        try {
            GetRegionReportsResults results = EditRegionsServerUtil
                    .getRegionReports(true, true);

            assignedReports = new ArrayList<>();
            for (Map.Entry<Integer, RegionReport> entry : results
                    .getAssignedRegionReports().entrySet()) {
                RegionReport report = entry.getValue();
                report.setId(entry.getKey());
                assignedReports.add(report);
            }

            unassignedReports = new ArrayList<>();
            for (Map.Entry<Integer, RegionReport> entry : results
                    .getUnAssignedReports().entrySet()) {
                RegionReport report = entry.getValue();
                report.setId(entry.getKey());
                unassignedReports.add(report);
            }

            regionIDs = EditRegionsServerUtil.getAllRegions();

        } catch (EditedRegionsException e) {
            statusHandler.handle(Priority.PROBLEM, e.getLocalizedMessage(), e);
        }
        assignedRegionTableViewer.setInput(assignedReports);
        assignedRegionTableViewer.refresh();

        unassignedRegionTableViewer.setInput(unassignedReports);
        unassignedRegionTableViewer.refresh();

        resizeTable(assignedRegionTableViewer);
        resizeTable(unassignedRegionTableViewer);

        List<String> regionStrings = new ArrayList<String>();
        for (Integer region : regionIDs) {
            regionStrings.add(String.valueOf(region));
        }

        regionCombo.setItems(regionStrings.toArray(new String[0]));
        regionCombo.select(0);

        refreshConsensus();
    }

    /**
     * Called when either the specified date is changed, or the specified region
     * is changed.
     */
    private void refreshConsensus() {
        try {
            int index = regionCombo.getSelectionIndex();
            if (index == -1) {
                return;
            }
            String item = regionCombo.getItem(index);
            Integer region = Integer.parseInt(item);
            GetConsensusResponse response = EditRegionsServerUtil
                    .getConsensus(date, region);
            GetConsensusTodaysResults todaysResults = (GetConsensusTodaysResults) response
                    .getTodaysConsensusResults();
            GetConsensusYesterdaysResults yesterdaysResults = (GetConsensusYesterdaysResults) response
                    .getYesterdaysConsensusResults();

            // Process todays Consensus
            textTodaysArea.setText(toString(todaysResults.getArea()));
            textTodaysCarlon.setText(toString(todaysResults.getCarlon()));
            textTodaysExtent.setText(toString(todaysResults.getExtent()));

            textTodaysMagclass.setText(toString(todaysResults.getMagclass()));
            textTodaysNumspots.setText(toString(todaysResults.getNumspots()));
            textTodaysObservationTime.setText(
                    getObservationTime(todaysResults.getObservationTime()));
            textTodaysObservatory
                    .setText(toString(todaysResults.getObservatory()));
            textTodaysRegion.setText(toString(todaysResults.getRegion()));

            textTodaysReportLocation
                    .setText(toString(todaysResults.getReportLocation()));
            textTodaysSpotclass.setText(toString(todaysResults.getSpotClass()));

            textTodays00ZLocation
                    .setText(toString(todaysResults.getReport00ZLocation()));

            // Process yesterdays Consensus
            textYesterdaysArea.setText(toString(yesterdaysResults.getArea()));
            textYesterdaysCarlon
                    .setText(toString(yesterdaysResults.getCarlon()));
            textYesterdaysExtent
                    .setText(toString(yesterdaysResults.getExtent()));

            textYesterdaysMagclass
                    .setText(toString(yesterdaysResults.getMagclass()));
            textYesterdaysNumspots
                    .setText(toString(yesterdaysResults.getNumspots()));
            textYesterdaysObservationTime.setText(
                    getObservationTime(yesterdaysResults.getObservationTime()));
            textYesterdaysObservatory
                    .setText(toString(yesterdaysResults.getObservatory()));
            textYesterdaysRegion
                    .setText(toString(yesterdaysResults.getRegion()));

            textYesterdaysReportLocation
                    .setText(toString(yesterdaysResults.getReportLocation()));
            textYesterdaysSpotclass
                    .setText(toString(yesterdaysResults.getSpotClass()));

            textYesterdays00ZLocation.setText(
                    toString(yesterdaysResults.getReport00ZLocation()));

        } catch (EditedRegionsException e) {
            statusHandler.error("Error refreshing consensus.", e);
        }
    }

    /**
     * create new region report
     */
    private void createNewRegionReport() {

        EnterRegionReportDialog regionReportDlg = new EnterRegionReportDialog(
                getShell());
        if (regionReportDlg.open() == Window.OK) {
            refreshDialog();
        }

    }

    /**
     * Create a new region.
     */
    private void createNewRegion() {
        try {
            Integer latestRegion = EditRegionsServerUtil.getLatestRegion();
            if (latestRegion == null) {
                EnterNewRegionDialog newRegionDialog = new EnterNewRegionDialog(
                        this.getShell());
                if (newRegionDialog.open() == Window.OK) {
                    refreshDialog();
                }
                return;
            }
            Integer newRegion = latestRegion + 1;
            MessageBox newRegionDlg = new MessageBox(this.getShell(),
                    SWT.OK | SWT.CANCEL);
            newRegionDlg.setText("New region.");
            newRegionDlg.setMessage(String
                    .format("Do you wish to create region %d?", newRegion));

            if (newRegionDlg.open() == SWT.OK) {
                EditRegionsServerUtil.createRegion(newRegion);
                refreshDialog();
            }
        } catch (EditedRegionsException ex) {
            statusHandler.error("Error creating new region", ex);
        }
    }

    /**
     * Create the context menu that is displayed when right clicking on a
     * selected region report.
     */
    private void createContextMenu(TableViewer tableViewer) {

        // add a popup menu for the selected row
        MenuManager menuManager = new MenuManager();

        IAction updateRegionReportAction = new UpdateRegionReportAction(
                tableViewer.getTable());
        menuManager.add(updateRegionReportAction);

        IAction viewRegionReportHistoryAction = new ViewRegionReportHistoryAction(
                tableViewer.getTable());
        menuManager.add(viewRegionReportHistoryAction);

        Menu menu = menuManager.createContextMenu(tableViewer.getTable());
        tableViewer.getTable().setMenu(menu);

    }

    /**
     * Action class responsible for updating the selected region report.
     *
     * @author alockleigh
     */
    private final class UpdateRegionReportAction extends Action {
        private final Table table;

        public UpdateRegionReportAction(Table table) {
            super("Update report");
            this.table = Objects.requireNonNull(table, "table");
        }

        @Override
        public void run() {
            int index = this.table.getSelectionIndex();
            if (index < 0) {
                return;
            }

            TableItem item = this.table.getItem(index);
            RegionReport report = (RegionReport) item.getData();

            EnterRegionReportDialog regionReportDlg = new EnterRegionReportDialog(
                    EditRegionsDialog.this.getShell());
            regionReportDlg.setReportId(report.getId());
            regionReportDlg.setObservationTime(report.getObservationTime());
            regionReportDlg.create();
            regionReportDlg.populateData(report);
            if (regionReportDlg.open() == Window.OK) {
                refreshDialog();
            }
        }
    }

    private final class ViewRegionReportHistoryAction extends Action {
        private final Table table;

        public ViewRegionReportHistoryAction(Table table) {
            super("View report history");
            this.table = Objects.requireNonNull(table, "table");
        }

        @Override
        public void run() {
            int index = this.table.getSelectionIndex();
            if (index < 0) {
                return;
            }

            TableItem item = this.table.getItem(index);
            RegionReport report = (RegionReport) item.getData();

            ViewRegionReportHistoryDialog dialog = new ViewRegionReportHistoryDialog(
                    EditRegionsDialog.this.getShell(), report.getId());
            dialog.open();
        }
    }

    /**
     * Resizes the events list table
     */
    private void resizeTable(TableViewer tableViewer) {
        for (TableColumn tc : tableViewer.getTable().getColumns())
            tc.pack();
    }

    private static String toString(Object value) {
        return (value != null) ? value.toString() : "";
    }

    public static final ThreadLocal<SimpleDateFormat> OBSERVATION_DATE_FORMAT = new ThreadLocal<SimpleDateFormat>() {
        @Override
        public SimpleDateFormat initialValue() {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            format.setTimeZone(EditedRegionsConstants.TIME_ZONE_UTC);
            return format;
        }
    };

    private static String getObservationTime(Long timestamp) {
        Date date = new Date(timestamp.longValue());
        String strDate = OBSERVATION_DATE_FORMAT.get().format(date);

        return strDate + " 2400Z";

    }

}
