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

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;
import java.util.TimeZone;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.resource.ImageDescriptor;
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
import org.eclipse.swt.graphics.Image;
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

import gov.noaa.nws.ncep.common.dataplugin.editedevents.exception.EditedEventsException;
import gov.noaa.nws.ncep.viz.ui.editedregions.Activator;
import gov.noaa.nws.ncep.viz.ui.editedregions.util.EditEventsUtil;
import gov.noaa.nws.ncep.viz.ui.editedregions.util.EditRegionsUIConstants;
import gov.noaa.nws.ncep.viz.ui.editedregions.util.Events;

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

    protected SimpleDateFormat dateFormat = new SimpleDateFormat(
            "dd MMM yyyy HH:mm:ss");

    protected SimpleDateFormat viewDateFormat = new SimpleDateFormat(
            "MM/dd/yyyy HH:mm:ss");

    private TimeZone defaultTZ = TimeZone.getTimeZone("UTC");

    // last location of the dialog
    private Point lastLocation = null;

    // last size
    private Point lastSize = null;

    // Table viewer for submitted jobs
    private TableViewer assignedRegionTableViewer = null;

    private TableViewer unassignedRegionTableViewer = null;

    private Calendar fromBeginCal;

    private Calendar toBeginCal;

    private Text fromDateText = null;

    private Text toDateText = null;

    private Combo viewCombo = null;

    private Combo binCombo = null;

    private Combo sortByCombo = null;

    /**
     * The ID for the event which is to be selected
     */
    private Integer selectEventId = null;

    // TODO: Replace with columns for Edited Regions
    private String[] columnTitles = EditRegionsUIConstants.COLUMNS;

    private int[] columnBounds = { 50, 50, 75, 50, 50, 50, 50, 50, 50, 50, 45,
            75, 65, 85, 85, 85, 85, 85, 85, 85, 50, 50, 50, 50 };

    /**
     * List of bins
     */
    private List<Integer> eventBins = null;

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

        dateFormat.setTimeZone(defaultTZ);
        viewDateFormat.setTimeZone(defaultTZ);

        this.resetFilterDates();

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

        // call processNewEvents
        EditEventsUtil.processNewEvents(fromBeginCal.getTimeInMillis(),
                toBeginCal.getTimeInMillis());

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

        createFilterControls(sashForm);

        assignedRegionTableViewer = createEventsListControls(sashForm,
                "Assigned Regions");

        unassignedRegionTableViewer = createEventsListControls(sashForm,
                "Unassigned Regions");

        createConsensusControls(sashForm);

        sashForm.setWeights(new int[] { 2, 1, 6, 1, 6, 6 });

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

    /**
     * Creates controls in the Events List section (bottom section) in the tab
     * 
     * @param parent
     */
    private TableViewer createEventsListControls(Composite parent,
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
        tableViewer.setInput(this.getEvents());

        labelProvider = new EditRegionsLabelProvider(binCombo.getItems(),
                fromBeginCal.getTimeInMillis());

        // Create the columns
        createColumns(tableViewer, labelProvider);

        tableViewer.setLabelProvider(labelProvider);

        tableViewer
                .addSelectionChangedListener(new ISelectionChangedListener() {
                    public void selectionChanged(
                            final SelectionChangedEvent event) {
                        IStructuredSelection selection = (IStructuredSelection) event
                                .getSelection();

                        gov.noaa.nws.ncep.common.dataplugin.editedevents.Event selectedEvent = (gov.noaa.nws.ncep.common.dataplugin.editedevents.Event) selection
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
     * Creates controls in the Filter section (top left section) in the tab
     * 
     * @param parent
     */
    private void createFilterControls(Composite parent) {

        Group filterGroup = new Group(parent, SWT.SHADOW_OUT);
        filterGroup.setLayout(new GridLayout(1, false));
        filterGroup.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));

        Composite filterComp = new Composite(filterGroup, SWT.None);

        GridLayout gridLayout = new GridLayout(10, false);

        filterComp.setLayout(gridLayout);
        filterComp.setLayoutData(
                new GridData(GridData.CENTER, SWT.TOP, true, true));

        this.createViewWindowControls(filterComp);

        this.createRefreshControl(filterComp);

        this.createViewControls(filterComp);

        this.createSortByControls(filterComp);

        this.createSelectBinControls(filterComp);
    }

    /**
     * Creates the Refresh button control (top right section)
     * 
     * @param parent
     */
    private void createRefreshControl(Composite parent) {

        new Label(parent, SWT.LEFT).setText("   ");

        final Button refreshButton = new Button(parent, SWT.PUSH);
        refreshButton.setText("  Refresh  ");
        refreshButton.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {

                EditEventsUtil.processNewEvents(fromBeginCal.getTimeInMillis(),
                        toBeginCal.getTimeInMillis());
                refreshRegionTables();
            }

        });

    }

    /**
     * Creates the controls for the dates selection
     * 
     * @param composite
     */
    public void createViewWindowControls(Composite composite) {

        new Label(composite, SWT.LEFT).setText("View Window:");
        fromDateText = new Text(composite, SWT.BORDER);
        fromDateText.setText(viewDateFormat.format(fromBeginCal.getTime()));
        fromDateText.setEnabled(false);

        String iconString = "icons/date_picker.gif";
        ImageDescriptor id = Activator
                .imageDescriptorFromPlugin(Activator.PLUGIN_ID, iconString);
        Image icon = null;
        if (id != null) {
            icon = id.createImage();
        }

        final Button fromDateDatePickerButton = new Button(composite, SWT.PUSH);
        fromDateDatePickerButton.setToolTipText("Select From Date");
        if (icon != null) {
            fromDateDatePickerButton.setImage(icon);
        }
        fromDateDatePickerButton.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {

                CalendarDialog calDialog = new CalendarDialog(getShell());

                Calendar selectedDT = calDialog.open(fromBeginCal, defaultTZ);
                if (selectedDT != null) {
                    fromBeginCal = selectedDT;
                } else {
                    return;
                }

                String selectedDate = viewDateFormat
                        .format(fromBeginCal.getTime());
                fromDateText.setText(selectedDate);

                refreshRegionTables();
            }

        });

        new Label(composite, SWT.LEFT).setText("To:");
        toDateText = new Text(composite, SWT.BORDER);
        toDateText.setText(viewDateFormat.format(toBeginCal.getTime()));
        toDateText.setEnabled(false);

        final Button toDateDatePickerButton = new Button(composite, SWT.PUSH);
        toDateDatePickerButton.setToolTipText("Select To Date");
        if (icon != null) {
            toDateDatePickerButton.setImage(icon);
        }
        toDateDatePickerButton.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {

                CalendarDialog calSelDlg = new CalendarDialog(getShell());

                Calendar selectedDT = calSelDlg.open(toBeginCal, defaultTZ);
                if (selectedDT != null) {
                    toBeginCal = selectedDT;
                } else {
                    return;
                }

                String selectedDate = viewDateFormat
                        .format(toBeginCal.getTime());
                toDateText.setText(selectedDate);

                refreshRegionTables();
            }

        });

        final Button resetDateButton = new Button(composite, SWT.PUSH);
        resetDateButton.setText("Reset Times");
        resetDateButton.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {

                resetFilterDates();
                fromDateText
                        .setText(viewDateFormat.format(fromBeginCal.getTime()));
                toDateText.setText(viewDateFormat.format(toBeginCal.getTime()));
                refreshRegionTables();
            }

        });

        new Label(composite, SWT.LEFT).setText("");

    }

    /**
     * Creates the view selection controls
     * 
     * @param composite
     */
    public void createViewControls(Composite composite) {

        // RequestType combo
        new Label(composite, SWT.LEFT).setText("View:");
        viewCombo = new Combo(composite, SWT.DROP_DOWN | SWT.READ_ONLY);
        viewCombo.setItems(new String[] { "Place holder" });
        viewCombo.select(0);

        viewCombo.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                refreshRegionTables();
            }
        });

        String iconString = "icons/reset.png";
        ImageDescriptor id = Activator
                .imageDescriptorFromPlugin(Activator.PLUGIN_ID, iconString);
        Image icon = null;
        if (id != null) {
            icon = id.createImage();
        }

        final Button resetViewButton = new Button(composite, SWT.PUSH);
        resetViewButton.setToolTipText("Reset View");
        if (icon != null) {
            resetViewButton.setImage(icon);
        }
        resetViewButton.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                viewCombo.setText(viewCombo.getItem(0));
                refreshRegionTables();
            }

        });

    }

    /**
     * Creates the sort by selection controls
     * 
     * @param composite
     */
    public void createSortByControls(Composite composite) {

        // Sort By combo
        Label sortLbl = new Label(composite, SWT.LEFT);
        sortLbl.setText("Sort by:");
        sortByCombo = new Combo(composite, SWT.DROP_DOWN | SWT.READ_ONLY);
        sortByCombo.setItems(new String[] { "Sort by placeholder" });
        sortByCombo.select(0);

        sortByCombo.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                refreshRegionTables();
            }
        });

        String iconString = "icons/reset.png";
        ImageDescriptor id = Activator
                .imageDescriptorFromPlugin(Activator.PLUGIN_ID, iconString);
        Image icon = null;
        if (id != null) {
            icon = id.createImage();
        }

        final Button resetSortByButton = new Button(composite, SWT.PUSH);
        resetSortByButton.setToolTipText("Reset Sort");
        if (icon != null) {
            resetSortByButton.setImage(icon);
        }
        resetSortByButton.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                sortByCombo.setText(sortByCombo.getItem(0));
                refreshRegionTables();
            }

        });

    }

    /**
     * Cretes the bin selection controls
     * 
     * @param composite
     */
    public void createSelectBinControls(Composite composite) {

        // Bin Number combo
        new Label(composite, SWT.LEFT).setText("View Bin Number:");
        binCombo = new Combo(composite, SWT.DROP_DOWN | SWT.READ_ONLY);
        binCombo.setSize(75, 18);
        binCombo.add("");

        this.refreshBinCombo();

        binCombo.addSelectionListener(new SelectionAdapter() {

            @Override
            public void widgetSelected(SelectionEvent e) {
                refreshRegionTables();
            }
        });

        String iconString = "icons/reset.png";
        ImageDescriptor id = Activator
                .imageDescriptorFromPlugin(Activator.PLUGIN_ID, iconString);
        Image icon = null;
        if (id != null) {
            icon = id.createImage();
        }

        final Button resetBinButton = new Button(composite, SWT.PUSH);
        resetBinButton.setToolTipText("Reset Bin to All");
        if (icon != null) {
            resetBinButton.setImage(icon);
        }
        resetBinButton.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                binCombo.setText("");
                refreshRegionTables();
            }

        });

        new Label(composite, SWT.LEFT).setText("");
    }

    /**
     * Creates the reset buttons for view, sort and bin selections
     * 
     * @param composite
     */
    public void createResetButtons(Composite composite) {
        new Label(composite, SWT.LEFT).setText("");

        final Button resetViewButton = new Button(composite, SWT.PUSH);
        resetViewButton.setText("Reset View");
        resetViewButton.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                viewCombo.setText("Reset placeholder");
                refreshRegionTables();
            }

        });

        new Label(composite, SWT.LEFT).setText("");
        new Label(composite, SWT.LEFT).setText("");

        final Button resetSortButton = new Button(composite, SWT.PUSH);
        resetSortButton.setText("Reset Sort");
        resetSortButton.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                sortByCombo.setText("Sort by placeholder");
                refreshRegionTables();
            }

        });

        new Label(composite, SWT.LEFT).setText("");
        new Label(composite, SWT.LEFT).setText("");

        final Button resetBinButton = new Button(composite, SWT.PUSH);
        resetBinButton.setText("Reset Bin to all");
        resetBinButton.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                binCombo.setText("");
                refreshRegionTables();
            }

        });

    }

    /**
     * Reset fromBeginDate and toBeginDate to default (toBeginDate = current
     * time and fromBeginDate = toBeginTime - 48 hours)
     */
    private void resetFilterDates() {
        toBeginCal = Calendar.getInstance();

        Calendar cal = Calendar.getInstance();
        cal.clear();
        cal.setTimeInMillis(toBeginCal.getTimeInMillis());
        cal.add(Calendar.HOUR, -48);
        fromBeginCal = cal;
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
        saveExitConfMB.setText("Save/Exit EE Application?");
        saveExitConfMB.setMessage("Do you want to save your changes?");

        close = true;

        // TODO: Just a placeholder for now. We'll need a different check moving
        // forward.

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
     * Returns the list of events in the db that matches the criteria in the
     * filter controls
     * 
     * @return List<gov.noaa.nws.ncep.common.dataplugin.editedevents.Event>
     */
    private List<gov.noaa.nws.ncep.common.dataplugin.editedevents.Event> getEvents() {

        Events ge = new Events();
        ge.setBeginDTTM(fromBeginCal.getTimeInMillis());
        ge.setEndDTTM(toBeginCal.getTimeInMillis());
        ge.setBin((!binCombo.getText().isEmpty())
                ? Integer.parseInt(binCombo.getText()) : null);
        ge.setSortBy((!sortByCombo.getText().isEmpty()) ? sortByCombo.getText()
                : null);
        ge.setView(
                (!viewCombo.getText().isEmpty()) ? viewCombo.getText() : null);

        List<gov.noaa.nws.ncep.common.dataplugin.editedevents.Event> events = null;

        try {
            events = ge.getEvents();

        } catch (EditedEventsException e) {
            statusHandler.error("Unable to get the list of events",
                    e.getMessage());
            e.printStackTrace();
        }

        return events;

    }

    /**
     * Initializes the bin combo to display the most up-to-date list of bins
     */
    private void refreshBinCombo() {

        String curBin = binCombo.getText();

        List<String> bins = Arrays.asList(binCombo.getItems());

        eventBins = EditEventsUtil.getBins(fromBeginCal.getTimeInMillis(),
                toBeginCal.getTimeInMillis());
        int eventBinsSize = (eventBins != null) ? eventBins.size() : 0;

        // add new bins to the binCombo
        for (int j = 0; j < eventBinsSize; j++) {

            Integer bin = eventBins.get(j);

            if (!bins.contains(String.valueOf(bin))) {
                binCombo.add(String.valueOf(bin));
            }
        }

        // Re-sort the items in the binCombo
        String[] items = binCombo.getItems();
        Arrays.sort(items);
        binCombo.setItems(items);

        // select the current bin
        for (int j = 0; j < items.length; j++) {

            if (curBin.equals(items[j])) {
                binCombo.select(j);
            }

        }

        if (labelProvider != null) {

            // TODO move this to a more appropriate place later
            labelProvider.setBeginDTTM(fromBeginCal.getTimeInMillis());

            // update the list of bins in the cell editor (combo viewer for bin
            // number)
            if (eventBins != null && eventBins.size() > 0) {
                labelProvider.setBins(binCombo.getItems());
            }
        }
    }

    /**
     * Refresh the events list in the table display. Also, refresh the binCombo
     * to get the latest bins.
     */
    private void refreshRegionTables() {

        assignedRegionTableViewer.setInput(null);
        assignedRegionTableViewer.refresh();
        resizeTable(assignedRegionTableViewer);
        refreshBinCombo();

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
