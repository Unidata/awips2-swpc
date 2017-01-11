/**
\ * This code has unlimited rights, and is provided "as is" by the National Centers 
 * for Environmental Prediction, without warranty of any kind, either expressed or implied, 
 * including but not limited to the implied warranties of merchantability and/or fitness 
 * for a particular purpose.
 * 
 * This code has been developed by the NCEP-SIB for use in the AWIPS2 system.
 * 
 **/
package gov.noaa.nws.ncep.viz.ui.editedregions.dialog;

import java.time.LocalDate;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.ColumnViewerEditorActivationEvent;
import org.eclipse.jface.viewers.ColumnViewerEditorActivationListener;
import org.eclipse.jface.viewers.ColumnViewerEditorDeactivationEvent;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.jface.viewers.ViewerCell;
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
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.Text;

import com.raytheon.uf.common.status.IUFStatusHandler;
import com.raytheon.uf.common.status.UFStatus;
import com.raytheon.uf.common.status.UFStatus.Priority;

import gov.noaa.nws.ncep.common.dataplugin.editedregions.exception.EditedRegionsException;
import gov.noaa.nws.ncep.common.dataplugin.editedregions.gateway.Gateway;
import gov.noaa.nws.ncep.common.dataplugin.editedregions.request.ExitRequest;
import gov.noaa.nws.ncep.common.dataplugin.editedregions.response.ExitResponse;
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

    /**
     * The ID for the event which is to be selected
     */
    private Integer selectEventId = null;

    // TODO: Replace with columns for Edited Regions
    private String[] columnTitles = EditRegionsUIConstants.COLUMNS;

    private int[] columnBounds = { 50, 50, 75, 50, 50, 50, 50, 50, 50, 50, 45,
            75, 65, 85, 85, 85, 85, 85, 85, 85, 50, 50, 50, 50 };

    /**
     * Label provider for the cells in the edit events table
     */
    // TODO: We should consider updating this class to return a column count,
    // possibly add
    // a new interface.
    private EditRegionsLabelProvider labelProvider = null;

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

    /**
     * Creates the dialog area
     */
    @Override
    public Control createDialogArea(Composite parent) {

        Composite top = (Composite) super.createDialogArea(parent);

        // Create the main layout for the shell.
        GridLayout mainLayout = new GridLayout(1, true);
        mainLayout.marginHeight = 1;
        mainLayout.marginWidth = 1;
        top.setLayout(mainLayout);
        top.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));

        // top.setSize(800, 900);
        this.getShell().setSize(800, 900);

        // Initialize all of the controls, and layouts
        initializeComponents(top);

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
        createArrowControls(sashForm);

        sashForm.setWeights(new int[] { 4, 1, 6, 1, 6, 6, 2 });

        parent.pack(true);

    }

    /**
     * Do not create Ok/Cancel buttons for this dialog.
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

        GridLayout gridLayout = new GridLayout(columnTitles.length + 1, false);

        gridComp.setLayout(gridLayout);
        gridComp.setLayoutData(
                new GridData(GridData.CENTER, SWT.TOP, true, true));

        for (String rowLabel : new String[] { "Yesterday's report",
                "Today's Consensus", "Today's Final" }) {
            new Label(gridComp, SWT.LEFT).setText(rowLabel);

            for (int i = 0; i < columnTitles.length; i++) {
                Text text = new Text(gridComp, SWT.BORDER);
                text.setLayoutData(new GridData(20, SWT.DEFAULT));
                text.setEnabled(false);
            }
        }

        Composite checkboxComp = new Composite(consensusComp, SWT.NONE);
        checkboxComp.setLayout(new GridLayout(1, true));
        new Button(checkboxComp, SWT.CHECK).setText("Fix Final");
        new Button(checkboxComp, SWT.CHECK).setText("Inactivate");

    }

    private void createArrowControls(Composite parent) {
        Composite arrowComp = new Composite(parent, SWT.NONE);
        arrowComp.setLayout(new GridLayout(2, false));

        Button btnLeft = new Button(arrowComp, SWT.PUSH);
        Button btnRight = new Button(arrowComp, SWT.PUSH);

        btnLeft.setText("<--");
        btnRight.setText("-->");

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

        labelProvider = new EditRegionsLabelProvider(new String[0],
                System.currentTimeMillis());

        // Create the columns
        createColumns(tableViewer, labelProvider);

        tableViewer.setLabelProvider(labelProvider);

        tableViewer
                .addSelectionChangedListener(new ISelectionChangedListener() {
                    public void selectionChanged(
                            final SelectionChangedEvent event) {
                        IStructuredSelection selection = (IStructuredSelection) event
                                .getSelection();

                        gov.noaa.nws.ncep.common.dataplugin.editedregions.Event selectedEvent = (gov.noaa.nws.ncep.common.dataplugin.editedregions.Event) selection
                                .getFirstElement();

                        if (selectedEvent != null) {
                            selectEventId = selectedEvent.getId();
                        }

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

                refreshRegionTables();
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

        GridLayout gridLayout = new GridLayout(9, false);

        regionComp.setLayout(gridLayout);
        regionComp.setLayoutData(
                new GridData(GridData.CENTER, SWT.TOP, true, true));

        Button closeButton = new Button(regionComp, SWT.PUSH);
        closeButton.setText("Close");
        closeButton.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                close();
            }
        });

        new Label(regionComp, SWT.LEAD).setText("");

        new Label(regionComp, SWT.LEAD).setText("Region:");

        Combo regionCombo = new Combo(regionComp,
                SWT.READ_ONLY | SWT.DROP_DOWN);
        regionCombo
                .setItems(new String[] { "Region 1", "Region 2", "Region 3" });
        regionCombo.select(0);

        Button newRegionButton = new Button(regionComp, SWT.PUSH);
        newRegionButton.setText("New Region");

        Button undoRegionButton = new Button(regionComp, SWT.PUSH);
        undoRegionButton.setText("Undo Region");

        new Label(regionComp, SWT.LEAD).setText("");

        new Label(regionComp, SWT.LEAD).setText("Region data for:");

        DateTimeFormatter formatter = DateTimeFormatter
                .ofPattern("dd MMM yyyy");
        LocalDate date = LocalDate.now(ZoneOffset.UTC);

        Text dateText = new Text(regionComp, SWT.BORDER);
        dateText.setText(formatter.format(date));

    }

    /**
     * Set the location and size of the dialog
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

    /**
     * Get the last used size of the dialog.
     * 
     * @return
     */
    @Override
    protected Point getInitialSize() {
        return (lastSize == null) ? super.getInitialSize() : lastSize;
    }

    /**
     * Get the last used location of the dialog.
     * 
     * @param size
     * @return
     */
    @Override
    protected Point getInitialLocation(Point size) {
        return (lastLocation == null) ? super.getInitialLocation(size)
                : lastLocation;
    }

    /**
     * Save location and size of the dialog.
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
            refreshRegionTables();
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
                SWT.YES | SWT.NO);
        saveExitConfMB.setText("Save and Exit?");
        saveExitConfMB.setMessage("Do you want to save your changes?");

        if (saveExitConfMB.open() == SWT.YES) {
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
            switch (i) {
            case EditRegionsUIConstants.COLUMN_INDEX_BIN:
                // Add cell editing support for the column that represents the
                // bin number
                col.setEditingSupport(
                        labelProvider.getEditorSupport(tableViewer, i));
                col.getColumn()
                        .setToolTipText(EditRegionsUIConstants.TOOL_TIP_BIN);
                break;
            case EditRegionsUIConstants.COLUMN_INDEX_BEGINQ:
                col.getColumn()
                        .setToolTipText(EditRegionsUIConstants.TOOL_TIP_BEGINQ);
                break;
            case EditRegionsUIConstants.COLUMN_INDEX_BEGIN_DATE:
                col.getColumn().setToolTipText(
                        EditRegionsUIConstants.TOOL_TIP_BEGIN_DATE);
                break;
            case EditRegionsUIConstants.COLUMN_INDEX_BEGIN_TIME:
                col.getColumn().setToolTipText(
                        EditRegionsUIConstants.TOOL_TIP_BEGIN_TIME);
                break;
            case EditRegionsUIConstants.COLUMN_INDEX_MAXQ:
                col.getColumn()
                        .setToolTipText(EditRegionsUIConstants.TOOL_TIP_MAXQ);
                break;
            case EditRegionsUIConstants.COLUMN_INDEX_MAX_TIME:
                col.getColumn().setToolTipText(
                        EditRegionsUIConstants.TOOL_TIP_MAX_TIME);
                break;
            case EditRegionsUIConstants.COLUMN_INDEX_ENDQ:
                col.getColumn()
                        .setToolTipText(EditRegionsUIConstants.TOOL_TIP_ENDQ);
                break;
            case EditRegionsUIConstants.COLUMN_INDEX_END_TIME:
                col.getColumn().setToolTipText(
                        EditRegionsUIConstants.TOOL_TIP_END_TIME);
                break;
            case EditRegionsUIConstants.COLUMN_INDEX_OBSERVATORY:
                col.getColumn().setToolTipText(
                        EditRegionsUIConstants.TOOL_TIP_OBSERVATORY);
                break;
            case EditRegionsUIConstants.COLUMN_INDEX_QUALITY:
                col.getColumn().setToolTipText(
                        EditRegionsUIConstants.TOOL_TIP_QUALITY);
                break;
            case EditRegionsUIConstants.COLUMN_INDEX_TYPE:
                col.getColumn()
                        .setToolTipText(EditRegionsUIConstants.TOOL_TIP_TYPE);
                break;
            case EditRegionsUIConstants.COLUMN_INDEX_LOCATION:
                col.getColumn().setToolTipText(
                        EditRegionsUIConstants.TOOL_TIP_LOCATION);
                break;
            case EditRegionsUIConstants.COLUMN_INDEX_FREQUENCY:
                col.getColumn().setToolTipText(
                        EditRegionsUIConstants.TOOL_TIP_FREQUENCY);
                break;
            case EditRegionsUIConstants.COLUMN_INDEX_PARTICULARS_1:
                col.getColumn().setToolTipText(
                        EditRegionsUIConstants.TOOL_TIP_PARTICULARS_1_10);
                break;
            case EditRegionsUIConstants.COLUMN_INDEX_PARTICULARS_2:
                col.getColumn().setToolTipText(
                        EditRegionsUIConstants.TOOL_TIP_PARTICULARS_1_10);
                break;
            case EditRegionsUIConstants.COLUMN_INDEX_PARTICULARS_3:
                col.getColumn().setToolTipText(
                        EditRegionsUIConstants.TOOL_TIP_PARTICULARS_1_10);
                break;
            case EditRegionsUIConstants.COLUMN_INDEX_PARTICULARS_4:
                col.getColumn().setToolTipText(
                        EditRegionsUIConstants.TOOL_TIP_PARTICULARS_1_10);
                break;
            case EditRegionsUIConstants.COLUMN_INDEX_PARTICULARS_5:
                col.getColumn().setToolTipText(
                        EditRegionsUIConstants.TOOL_TIP_PARTICULARS_1_10);
                break;
            case EditRegionsUIConstants.COLUMN_INDEX_PARTICULARS_6:
                col.getColumn().setToolTipText(
                        EditRegionsUIConstants.TOOL_TIP_PARTICULARS_1_10);
                break;
            case EditRegionsUIConstants.COLUMN_INDEX_PARTICULARS_7:
                col.getColumn().setToolTipText(
                        EditRegionsUIConstants.TOOL_TIP_PARTICULARS_1_10);
                break;
            case EditRegionsUIConstants.COLUMN_INDEX_PARTICULARS_8:
                col.getColumn().setToolTipText(
                        EditRegionsUIConstants.TOOL_TIP_PARTICULARS_1_10);
                break;
            case EditRegionsUIConstants.COLUMN_INDEX_REGION:
                col.getColumn()
                        .setToolTipText(EditRegionsUIConstants.TOOL_TIP_REGION);
                break;
            case EditRegionsUIConstants.COLUMN_INDEX_AGE:
                col.getColumn()
                        .setToolTipText(EditRegionsUIConstants.TOOL_TIP_AGE);
                break;
            case EditRegionsUIConstants.COLUMN_INDEX_STATUS_TEXT:
                col.getColumn()
                        .setToolTipText(EditRegionsUIConstants.TOOL_TIP_STATUS);
                break;
            default:
                break;
            }

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
     * Refresh the events list in the table display. Also, refresh the binCombo
     * to get the latest bins.
     */
    private void refreshRegionTables() {

        assignedRegionTableViewer.setInput(null);
        assignedRegionTableViewer.refresh();

        unassignedRegionTableViewer.setInput(null);
        unassignedRegionTableViewer.refresh();

        resizeTable(assignedRegionTableViewer);
        resizeTable(unassignedRegionTableViewer);

    }

    /**
     * Create the context menu that is displayed when right clicking on a
     * selected region
     */
    private void createContextMenu(TableViewer tableViewer) {

        // add a popup menu for the selected row

        // TODO: add code or delete function.

    }

    /**
     * Resizes the events list table
     */
    private void resizeTable(TableViewer tableViewer) {
        for (TableColumn tc : tableViewer.getTable().getColumns())
            tc.pack();
    }

}
