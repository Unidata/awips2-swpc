/**
 * This code has unlimited rights, and is provided "as is" by the National Centers 
 * for Environmental Prediction, without warranty of any kind, either expressed or implied, 
 * including but not limited to the implied warranties of merchantability and/or fitness 
 * for a particular purpose.
 * 
 * This code has been developed by the NCEP-SIB for use in the AWIPS2 system.
 * 
 **/
package gov.noaa.nws.ncep.viz.ui.editedevents.dialog;

import gov.noaa.nws.ncep.common.dataplugin.editedevents.exception.EditedEventsException;
import gov.noaa.nws.ncep.common.dataplugin.editedevents.util.EditedEventsConstants;
import gov.noaa.nws.ncep.viz.ui.editedevents.Activator;
import gov.noaa.nws.ncep.viz.ui.editedevents.util.EditEventsUIConstants;
import gov.noaa.nws.ncep.viz.ui.editedevents.util.EditEventsUtil;
import gov.noaa.nws.ncep.viz.ui.editedevents.util.EnterEventUtil;
import gov.noaa.nws.ncep.viz.ui.editedevents.util.Events;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;
import java.util.TimeZone;

import javax.swing.JFileChooser;
import javax.swing.JFrame;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.action.MenuManager;
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
import org.eclipse.jface.window.Window;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.FontData;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Text;

import com.raytheon.uf.common.status.IUFStatusHandler;
import com.raytheon.uf.common.status.UFStatus;
import com.raytheon.uf.viz.core.exception.VizException;

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

public class EditEventsDialog extends Dialog { // implements IEventsObserver {

    // singleton instance
    private static EditEventsDialog INSTANCE;

    private final IUFStatusHandler statusHandler = UFStatus
            .getHandler(EditEventsDialog.class);

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
    private TableViewer eventTableViewer = null;

    private Table eventsListTable = null;

    private Listener scrollBarListener = null;

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

    /**
     * Main menu bar.
     */
    private Menu menuBar = null;

    private String[] viewOptions = {
            EditEventsUIConstants.VIEW_OPTION_ALL_REPORTS,
            EditEventsUIConstants.VIEW_OPTION_BEST_ONLY,
            EditEventsUIConstants.VIEW_OPTION_CONTENDERS_ONLY,
            EditEventsUIConstants.VIEW_OPTION_NEW_REPORTS };

    private String[] sortByOptions = {
            EditEventsUIConstants.SORTBY_OPTION_BIN_DATE,
            EditEventsUIConstants.SORTBY_OPTION_TIME,
            EditEventsUIConstants.SORTBY_OPTION_TYPE,
            EditEventsUIConstants.SORTBY_OPTION_REGION,
            EditEventsUIConstants.SORTBY_OPTION_OBSERVATORY };

    private String[] columnTitles = { EditEventsUIConstants.COLUMN_HEADER_BIN,
            EditEventsUIConstants.COLUMN_HEADER_BEGINQ,
            EditEventsUIConstants.COLUMN_HEADER_BEGIN_DATE,
            EditEventsUIConstants.COLUMN_HEADER_BEGIN_TIME,
            EditEventsUIConstants.COLUMN_HEADER_MAXQ,
            EditEventsUIConstants.COLUMN_HEADER_MAX_TIME,
            EditEventsUIConstants.COLUMN_HEADER_ENDQ,
            EditEventsUIConstants.COLUMN_HEADER_END_TIME,
            EditEventsUIConstants.COLUMN_HEADER_OBSERVATORY,
            EditEventsUIConstants.COLUMN_HEADER_QUALITY,
            EditEventsUIConstants.COLUMN_HEADER_TYPE,
            EditEventsUIConstants.COLUMN_HEADER_LOCATION,
            EditEventsUIConstants.COLUMN_HEADER_FREQUENCY,
            EditEventsUIConstants.COLUMN_HEADER_PARTICULARS_1,
            EditEventsUIConstants.COLUMN_HEADER_PARTICULARS_2,
            EditEventsUIConstants.COLUMN_HEADER_PARTICULARS_3,
            EditEventsUIConstants.COLUMN_HEADER_PARTICULARS_4,
            EditEventsUIConstants.COLUMN_HEADER_PARTICULARS_5,
            EditEventsUIConstants.COLUMN_HEADER_PARTICULARS_6,
            EditEventsUIConstants.COLUMN_HEADER_PARTICULARS_7,
            EditEventsUIConstants.COLUMN_HEADER_PARTICULARS_8,
            EditEventsUIConstants.COLUMN_HEADER_REGION,
            EditEventsUIConstants.COLUMN_HEADER_AGE,
            EditEventsUIConstants.COLUMN_HEADER_STATUS };

    private int[] columnBounds = { 50, 50, 75, 50, 50, 50, 50, 50, 50, 50, 45,
            75, 65, 85, 85, 85, 85, 85, 85, 85, 50, 50, 50, 50 };

    /**
     * List of bins
     */
    private List<Integer> eventBins = null;

    /**
     * Label provider for the cells in the edit events table
     */
    private EditEventsLabelProvider labelProvider = null;

    /**
     * Creates a EditEventsDialog instance
     * 
     * @param parent
     */
    public EditEventsDialog(Shell parent) {
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
    public static EditEventsDialog getInstance(Shell parShell) {

        if (INSTANCE == null) {
            INSTANCE = new EditEventsDialog(parShell);
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

        getShell().setText("Edited Events");

        this.createMenus();

        // show scroll bars when needed
        scrollBarListener = new Listener() {
            @Override
            public void handleEvent(Event event) {
                Text txt = (Text) event.widget;
                Rectangle r1 = txt.getClientArea();
                Rectangle r2 = txt.computeTrim(r1.x, r1.y, r1.width, r1.height);
                Point p = txt.computeSize(SWT.DEFAULT, SWT.DEFAULT, true);
                txt.getHorizontalBar().setVisible(r2.width <= p.x);
                txt.getVerticalBar().setVisible(r2.height <= p.y);
                if (event.type == SWT.Modify) {
                    txt.getParent().layout(true);
                    txt.showSelection();
                }
            }
        };

        // Sash form to hold the filter criteria area and events list table
        SashForm sashForm = new SashForm(parent, SWT.VERTICAL);
        GridData sashGd = new GridData(SWT.FILL, SWT.FILL, true, true);
        sashForm.setLayoutData(sashGd);
        sashForm.setSashWidth(3);

        createFilterControls(sashForm);

        createEventsListControls(sashForm);

        sashForm.setWeights(new int[] { 2, 8 });

    }

    /**
     * Do not create Ok/Cancel buttons for this dialog.
     */
    @Override
    public Control createButtonBar(Composite parent) {
        return null;
    }

    /**
     * Creates controls in the Events List section (bottom section) in the tab
     * 
     * @param parent
     */
    private void createEventsListControls(Composite parent) {

        SashForm sashForm = new SashForm(parent, SWT.HORIZONTAL);
        GridData sashGd = new GridData(SWT.FILL, SWT.FILL, true, true);
        sashForm.setLayoutData(sashGd);
        sashForm.setSashWidth(3);

        // Create the events list table viewer
        eventTableViewer = new TableViewer(sashForm, SWT.V_SCROLL
                | SWT.H_SCROLL | SWT.FULL_SELECTION);
        // eventTableViewer.setSorter(new EventViewerSorter());

        eventTableViewer.setContentProvider(new ArrayContentProvider());
        eventTableViewer.setInput(this.getEvents());

        labelProvider = new EditEventsLabelProvider(binCombo.getItems(),
                fromBeginCal.getTimeInMillis());

        // Create the columns
        createColumns(labelProvider);

        eventTableViewer.setLabelProvider(labelProvider);

        eventTableViewer
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

        eventsListTable = eventTableViewer.getTable();

        // Show the lines and column headers
        eventsListTable.setLinesVisible(true);
        eventsListTable.setHeaderVisible(true);

        if (eventTableViewer.getTable().getItemCount() > 0
                && selectEventId == null) {
            eventTableViewer.getTable().setFocus();
            eventTableViewer.setSelection(new StructuredSelection(
                    eventTableViewer.getElementAt(0)), true);
            eventTableViewer.getTable().showSelection();
            eventTableViewer.getTable().notifyListeners(SWT.Selection, null);
        }

        eventTableViewer.refresh();
        resizeTable();

        createContextMenu();

        eventTableViewer.getColumnViewerEditor().addEditorActivationListener(
                createEditorActivationListener());

    }

    private ColumnViewerEditorActivationListener createEditorActivationListener() {
        final Table t = eventTableViewer.getTable();
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

                refreshEventsListTable();
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
        filterComp.setLayoutData(new GridData(GridData.CENTER, SWT.TOP, true,
                true));

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
                refreshEventsListTable();
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
        ImageDescriptor id = Activator.imageDescriptorFromPlugin(
                Activator.PLUGIN_ID, iconString);
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

                String selectedDate = viewDateFormat.format(fromBeginCal
                        .getTime());
                fromDateText.setText(selectedDate);

                refreshEventsListTable();
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

                String selectedDate = viewDateFormat.format(toBeginCal
                        .getTime());
                toDateText.setText(selectedDate);

                refreshEventsListTable();
            }

        });

        final Button resetDateButton = new Button(composite, SWT.PUSH);
        resetDateButton.setText("Reset Times");
        resetDateButton.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {

                resetFilterDates();
                fromDateText.setText(viewDateFormat.format(fromBeginCal
                        .getTime()));
                toDateText.setText(viewDateFormat.format(toBeginCal.getTime()));
                refreshEventsListTable();
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
        viewCombo.setItems(viewOptions);
        viewCombo.select(0);

        viewCombo.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                refreshEventsListTable();
            }
        });

        String iconString = "icons/reset.png";
        ImageDescriptor id = Activator.imageDescriptorFromPlugin(
                Activator.PLUGIN_ID, iconString);
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
                viewCombo.setText(viewOptions[0]);
                refreshEventsListTable();
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
        sortByCombo.setItems(sortByOptions);
        sortByCombo.select(0);

        sortByCombo.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                refreshEventsListTable();
            }
        });

        String iconString = "icons/reset.png";
        ImageDescriptor id = Activator.imageDescriptorFromPlugin(
                Activator.PLUGIN_ID, iconString);
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
                sortByCombo.setText(sortByOptions[0]);
                refreshEventsListTable();
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
                refreshEventsListTable();
            }
        });

        String iconString = "icons/reset.png";
        ImageDescriptor id = Activator.imageDescriptorFromPlugin(
                Activator.PLUGIN_ID, iconString);
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
                refreshEventsListTable();
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
                viewCombo.setText(viewOptions[0]);
                refreshEventsListTable();
            }

        });

        new Label(composite, SWT.LEFT).setText("");
        new Label(composite, SWT.LEFT).setText("");

        final Button resetSortButton = new Button(composite, SWT.PUSH);
        resetSortButton.setText("Reset Sort");
        resetSortButton.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                sortByCombo.setText(sortByOptions[0]);
                refreshEventsListTable();
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
                refreshEventsListTable();
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
    public boolean close() {

        if (confirmClose()) {

            if (getShell() != null && !getShell().isDisposed()) {
                Rectangle bounds = getShell().getBounds();
                lastLocation = new Point(bounds.x, bounds.y);
                lastSize = getShell().getSize();
            }
            return super.close();
        } else {
            refreshEventsListTable();
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

        MessageBox saveExitConfMB = new MessageBox(this.getShell(), SWT.YES
                | SWT.NO);
        saveExitConfMB.setText("Save/Exit EE Application?");
        saveExitConfMB.setMessage("Do you want to save your changes?");

        if (saveExitConfMB.open() == SWT.YES) {

            // Check to see if contender events exist
            int contenderEventID = findContenderEvent();

            if (contenderEventID == 0) { // Contender events do not exist
                EditEventsUtil.exitEditedEvents(fromBeginCal.getTimeInMillis());
                close = true;
            } else {
                // Contender events exists
                MessageBox contenderConfMB = new MessageBox(this.getShell(),
                        SWT.YES | SWT.NO);
                contenderConfMB.setText("Contenders Exist");
                contenderConfMB
                        .setMessage("There are still unresolved 'Contender' events that need forecaster decision. Are you sure you don't want to take care of this now?");

                if (contenderConfMB.open() == SWT.YES) {
                    selectEventId = contenderEventID;
                    close = false;
                } else {
                    EditEventsUtil.exitEditedEvents(fromBeginCal
                            .getTimeInMillis());
                    close = true;
                }
            }
        } else {
            close = true;
        }

        return close;
    }

    /**
     * Check to see if there are one or more events with status='Contender'
     * (symbol='='). If contender events exist, return the event ID of the first
     * contender event in the events list.
     * 
     * @return eventID
     */
    private int findContenderEvent() {
        Table tbl = eventTableViewer.getTable();
        TableItem items[] = tbl.getItems();

        for (int ii = 0; ii < items.length; ii++) {
            gov.noaa.nws.ncep.common.dataplugin.editedevents.Event event = (gov.noaa.nws.ncep.common.dataplugin.editedevents.Event) items[ii]
                    .getData();

            // return the event ID if the event is a contender
            if (EditedEventsConstants.EVENT_STATUS.CONTENDER.toString()
                    .equalsIgnoreCase(event.getStatusText())) {
                return event.getId();
            }
        }

        return 0;
    }

    /**
     * Create the columns for the events list table
     */
    private void createColumns(EditEventsLabelProvider labelProvider) {

        for (int i = 0; i < columnTitles.length; i++) {
            TableViewerColumn col = createTableViewerColumn(columnTitles[i],
                    columnBounds[i], i);

            // Add tool tip text for the column headers
            switch (i) {
            case EditEventsUIConstants.COLUMN_INDEX_BIN:
                // Add cell editing support for the column that represents the
                // bin number
                col.setEditingSupport(labelProvider.getEditorSupport(
                        eventTableViewer, i));
                col.getColumn().setToolTipText(
                        EditEventsUIConstants.TOOL_TIP_BIN);
                break;
            case EditEventsUIConstants.COLUMN_INDEX_BEGINQ:
                col.getColumn().setToolTipText(
                        EditEventsUIConstants.TOOL_TIP_BEGINQ);
                break;
            case EditEventsUIConstants.COLUMN_INDEX_BEGIN_DATE:
                col.getColumn().setToolTipText(
                        EditEventsUIConstants.TOOL_TIP_BEGIN_DATE);
                break;
            case EditEventsUIConstants.COLUMN_INDEX_BEGIN_TIME:
                col.getColumn().setToolTipText(
                        EditEventsUIConstants.TOOL_TIP_BEGIN_TIME);
                break;
            case EditEventsUIConstants.COLUMN_INDEX_MAXQ:
                col.getColumn().setToolTipText(
                        EditEventsUIConstants.TOOL_TIP_MAXQ);
                break;
            case EditEventsUIConstants.COLUMN_INDEX_MAX_TIME:
                col.getColumn().setToolTipText(
                        EditEventsUIConstants.TOOL_TIP_MAX_TIME);
                break;
            case EditEventsUIConstants.COLUMN_INDEX_ENDQ:
                col.getColumn().setToolTipText(
                        EditEventsUIConstants.TOOL_TIP_ENDQ);
                break;
            case EditEventsUIConstants.COLUMN_INDEX_END_TIME:
                col.getColumn().setToolTipText(
                        EditEventsUIConstants.TOOL_TIP_END_TIME);
                break;
            case EditEventsUIConstants.COLUMN_INDEX_OBSERVATORY:
                col.getColumn().setToolTipText(
                        EditEventsUIConstants.TOOL_TIP_OBSERVATORY);
                break;
            case EditEventsUIConstants.COLUMN_INDEX_QUALITY:
                col.getColumn().setToolTipText(
                        EditEventsUIConstants.TOOL_TIP_QUALITY);
                break;
            case EditEventsUIConstants.COLUMN_INDEX_TYPE:
                col.getColumn().setToolTipText(
                        EditEventsUIConstants.TOOL_TIP_TYPE);
                break;
            case EditEventsUIConstants.COLUMN_INDEX_LOCATION:
                col.getColumn().setToolTipText(
                        EditEventsUIConstants.TOOL_TIP_LOCATION);
                break;
            case EditEventsUIConstants.COLUMN_INDEX_FREQUENCY:
                col.getColumn().setToolTipText(
                        EditEventsUIConstants.TOOL_TIP_FREQUENCY);
                break;
            case EditEventsUIConstants.COLUMN_INDEX_PARTICULARS_1:
                col.getColumn().setToolTipText(
                        EditEventsUIConstants.TOOL_TIP_PARTICULARS_1_10);
                break;
            case EditEventsUIConstants.COLUMN_INDEX_PARTICULARS_2:
                col.getColumn().setToolTipText(
                        EditEventsUIConstants.TOOL_TIP_PARTICULARS_1_10);
                break;
            case EditEventsUIConstants.COLUMN_INDEX_PARTICULARS_3:
                col.getColumn().setToolTipText(
                        EditEventsUIConstants.TOOL_TIP_PARTICULARS_1_10);
                break;
            case EditEventsUIConstants.COLUMN_INDEX_PARTICULARS_4:
                col.getColumn().setToolTipText(
                        EditEventsUIConstants.TOOL_TIP_PARTICULARS_1_10);
                break;
            case EditEventsUIConstants.COLUMN_INDEX_PARTICULARS_5:
                col.getColumn().setToolTipText(
                        EditEventsUIConstants.TOOL_TIP_PARTICULARS_1_10);
                break;
            case EditEventsUIConstants.COLUMN_INDEX_PARTICULARS_6:
                col.getColumn().setToolTipText(
                        EditEventsUIConstants.TOOL_TIP_PARTICULARS_1_10);
                break;
            case EditEventsUIConstants.COLUMN_INDEX_PARTICULARS_7:
                col.getColumn().setToolTipText(
                        EditEventsUIConstants.TOOL_TIP_PARTICULARS_1_10);
                break;
            case EditEventsUIConstants.COLUMN_INDEX_PARTICULARS_8:
                col.getColumn().setToolTipText(
                        EditEventsUIConstants.TOOL_TIP_PARTICULARS_1_10);
                break;
            case EditEventsUIConstants.COLUMN_INDEX_REGION:
                col.getColumn().setToolTipText(
                        EditEventsUIConstants.TOOL_TIP_REGION);
                break;
            case EditEventsUIConstants.COLUMN_INDEX_AGE:
                col.getColumn().setToolTipText(
                        EditEventsUIConstants.TOOL_TIP_AGE);
                break;
            case EditEventsUIConstants.COLUMN_INDEX_STATUS_TEXT:
                col.getColumn().setToolTipText(
                        EditEventsUIConstants.TOOL_TIP_STATUS);
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
    private TableViewerColumn createTableViewerColumn(String title, int bound,
            final int colNumber) {
        final TableViewerColumn viewerColumn = new TableViewerColumn(
                eventTableViewer, SWT.NONE);

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
     * Find the table item of the specified event report.
     * 
     * @param event
     * @return
     */
    // private TableItem findTableItem(Event event) {
    // Table tbl = eventTableViewer.getTable();
    // TableItem items[] = tbl.getItems();
    //
    // for (int ii = 0; ii < items.length; ii++) {
    // if (items[ii].getData().equals(event)) {
    // return items[ii];
    // }
    // }
    // return null;
    // }

    // @Override
    // public void updateEvent(
    // gov.noaa.nws.ncep.common.dataplugin.editedevents.Event event) {
    // // make call to update event in db
    // eventTableViewer.update(event, null);
    //
    // // setItemBackgroundColor(event);
    //
    // // update output
    // // if (eventTableViewer.getTable().getSelection().length > 0
    // // && eventTableViewer.getTable().getSelection()[0].getData()
    // // .equals(event)) {
    // // String output = event.getOutput();
    // // if (!event.getError().isEmpty()) {
    // // output += "\nError message:\n" + event.getError();
    // // }
    // // }
    // }

    // @Override
    // public void addEvent(
    // gov.noaa.nws.ncep.common.dataplugin.editedevents.Event event) {
    // // make call to add new row to db
    // eventTableViewer.add(event);
    // }
    /**
     * @param originalData
     * @param additionalStyle
     * @return
     */
    private static FontData[] getModifiedFontData(FontData[] originalData,
            int additionalStyle) {
        FontData[] styleData = new FontData[originalData.length];
        for (int i = 0; i < styleData.length; i++) {
            FontData base = originalData[i];
            styleData[i] = new FontData(base.getName(), base.getHeight(),
                    base.getStyle() | additionalStyle);
        }
        return styleData;
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
        ge.setBin((!binCombo.getText().isEmpty()) ? Integer.parseInt(binCombo
                .getText()) : null);
        ge.setSortBy((!sortByCombo.getText().isEmpty()) ? sortByCombo.getText()
                : null);
        ge.setView((!viewCombo.getText().isEmpty()) ? viewCombo.getText()
                : null);

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
     * Create the menu bar and menus.
     */
    private void createMenus() {
        menuBar = new Menu(this.getShell(), SWT.BAR);

        this.createFileMenu(menuBar);
        this.createOptionsMenu(menuBar);
        this.createViewMenu(menuBar);
        this.createHelpMenu(menuBar);

        this.getShell().setMenuBar(menuBar);
    }

    /**
     * Create the File menu.
     * 
     * @param menuBar
     *            Menu bar.
     */
    private void createFileMenu(Menu menuBar) {
        // -------------------------------------
        // Create the File menu
        // -------------------------------------
        Menu fileMenu = new Menu(menuBar);

        // Create the File menu item in the menubar
        MenuItem fileMenuItem = new MenuItem(menuBar, SWT.CASCADE);
        fileMenuItem.setText("File");
        fileMenuItem.setMenu(fileMenu);

        // -------------------------------------------------
        // Create all the items in the File dropdown menu
        // -------------------------------------------------

        // Export menu item
        MenuItem exportMI = new MenuItem(fileMenu, SWT.NONE);
        exportMI.setText("Export Events...");
        exportMI.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent event) {
                exportEvents();
            }
        });

        // new MenuItem(fileMenu, SWT.SEPARATOR);

        // Exit menu item
        MenuItem exitMI = new MenuItem(fileMenu, SWT.NONE);
        exitMI.setText("Exit");
        exitMI.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent event) {
                close();
            }
        });
    }

    /**
     * Create the Options menu.
     * 
     * @param menuBar
     *            Menu bar.
     */
    private void createOptionsMenu(Menu menuBar) {
        // -------------------------------------
        // Create the Options menu
        // -------------------------------------
        Menu toolsMenu = new Menu(menuBar);

        // Create the Options menu item in the menubar
        MenuItem toolsMenuItem = new MenuItem(menuBar, SWT.CASCADE);
        toolsMenuItem.setText("Options");
        toolsMenuItem.setMenu(toolsMenu);

        // -------------------------------------------------
        // Create all the items in the Options dropdown menu
        // -------------------------------------------------

        // Enter Event menu item
        MenuItem enterEventMI = new MenuItem(toolsMenu, SWT.NONE);
        enterEventMI.setText("Enter Event...");
        enterEventMI.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent event) {

                SelectEventToEnterDialog selectEventDlg = new SelectEventToEnterDialog(
                        getShell());

                if (Window.OK == selectEventDlg.open()) {
                    selectEventId = selectEventDlg.getNewEventId();
                }

                refreshEventsListTable();

            }
        });
    }

    /**
     * Create the View menu.
     * 
     * @param menuBar
     *            Menu bar.
     */
    private void createViewMenu(Menu menuBar) {
        // -------------------------------------
        // Create the View menu
        // -------------------------------------
        Menu toolsMenu = new Menu(menuBar);

        // Create the View menu item in the menubar
        MenuItem toolsMenuItem = new MenuItem(menuBar, SWT.CASCADE);
        toolsMenuItem.setText("View");
        toolsMenuItem.setMenu(toolsMenu);

        // -------------------------------------------------
        // Create all the items in the View dropdown menu
        // -------------------------------------------------

        // Default menu item
        MenuItem defaultMI = new MenuItem(toolsMenu, SWT.NONE);
        defaultMI.setText("Default");
        defaultMI.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent event) {

            }
        });

        // Extended menu item
        MenuItem extendedMI = new MenuItem(toolsMenu, SWT.NONE);
        extendedMI.setText("Extended");
        extendedMI.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent event) {

            }
        });

        // Customized menu item
        MenuItem customizedMI = new MenuItem(toolsMenu, SWT.NONE);
        customizedMI.setText("Customized");
        customizedMI.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent event) {

            }
        });
    }

    /**
     * Create the Help menu.
     * 
     * @param menuBar
     *            Menu bar.
     */
    private void createHelpMenu(Menu menuBar) {
        // -------------------------------------
        // Create the Help menu
        // -------------------------------------
        Menu helpMenu = new Menu(menuBar);

        // Create the Help menu item in the menubar
        MenuItem helpMenuItem = new MenuItem(menuBar, SWT.CASCADE);
        helpMenuItem.setText("Help");
        helpMenuItem.setMenu(helpMenu);

        // -------------------------------------------------
        // Create all the items in the Help dropdown menu
        // -------------------------------------------------

        // Default menu item
        MenuItem aboutMI = new MenuItem(helpMenu, SWT.NONE);
        aboutMI.setText("About");
        aboutMI.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent event) {
                EditEventsUtil.displayMessageDialog(getShell(),
                        "Edited Events Version "
                                + EditedEventsConstants.VERSION_NUMBER,
                        "About Edited Events");
            }
        });
    }

    /**
     * Refresh the events list in the table display. Also, refresh the binCombo
     * to get the latest bins.
     */
    private void refreshEventsListTable() {

        List<gov.noaa.nws.ncep.common.dataplugin.editedevents.Event> events = getEvents();

        eventTableViewer.setInput(events);
        setTableRowSelection(events);
        eventTableViewer.refresh();
        resizeTable();
        refreshBinCombo();

    }

    /**
     * @param rowIndex
     */
    private void setTableRowSelection(
            List<gov.noaa.nws.ncep.common.dataplugin.editedevents.Event> events) {

        if (eventTableViewer.getTable().getItemCount() > 0) {

            eventTableViewer.getTable().setFocus();

            if (selectEventId != null) {
                int i = 0;
                for (i = 0; i < events.size(); i++) {
                    if (selectEventId == events.get(i).getId()) {
                        break;
                    }
                }
                if (eventTableViewer.getElementAt(i) != null) {
                    eventTableViewer.setSelection(new StructuredSelection(
                            eventTableViewer.getElementAt(i)), true);
                } else {
                    eventTableViewer.setSelection(new StructuredSelection(
                            eventTableViewer.getElementAt(0)), true);
                }
            }

            eventTableViewer.getTable().showSelection();
            eventTableViewer.getTable().notifyListeners(SWT.Selection, null);
        }
    }

    /**
     * Create the context menu that is displayed when right clicking on a
     * selected event
     */
    private void createContextMenu() {

        // add a popup menu for the selected row
        MenuManager popManager = new MenuManager();
        IAction createBinAction = new CreateNewBinAction();
        popManager.add(createBinAction);

        IAction createCompositeAction = new CreateCompositeAction();
        popManager.add(createCompositeAction);

        IAction upgradeEventAction = new UpgradeEventAction();
        popManager.add(upgradeEventAction);

        IAction downgradeEventAction = new DowngradeEventAction();
        popManager.add(downgradeEventAction);

        Menu menu = popManager.createContextMenu(eventTableViewer.getTable());
        eventTableViewer.getTable().setMenu(menu);

    }

    /**
     * Action class that is responsible for creating a new bin for a selected
     * event
     * 
     * @author sgurung
     * 
     */
    private class CreateNewBinAction extends Action {

        public CreateNewBinAction() {
            super("Create New Bin for this Event");
        }

        public void run() {

            int index = eventTableViewer.getTable().getSelectionIndex();
            if (index == -1)
                return; // no row selected

            TableItem item = eventTableViewer.getTable().getItem(index);

            // get data for the row that was clicked.
            gov.noaa.nws.ncep.common.dataplugin.editedevents.Event selectedEvent = (gov.noaa.nws.ncep.common.dataplugin.editedevents.Event) item
                    .getData();
            selectEventId = selectedEvent.getId();

            if (selectedEvent.getId() != 0) {
                EditEventsUtil.addNewBinForEvent(
                        fromBeginCal.getTimeInMillis(), selectedEvent);
                refreshEventsListTable();
            }
        }
    }

    /**
     * Action class that is responsible for creating a composite event for a
     * selected event
     * 
     * @author sgurung
     * 
     */
    private class CreateCompositeAction extends Action {

        public CreateCompositeAction() {
            super("Create Composite");
        }

        public void run() {

            int index = eventTableViewer.getTable().getSelectionIndex();
            if (index == -1)
                return; // no row selected

            TableItem item = eventTableViewer.getTable().getItem(index);

            // get data for the row that was clicked.
            gov.noaa.nws.ncep.common.dataplugin.editedevents.Event selectedEvent = (gov.noaa.nws.ncep.common.dataplugin.editedevents.Event) item
                    .getData();

            if (selectedEvent.getId() != 0) {

                // A composite report may only be composed based on a best
                // report.
                if (selectedEvent.getStatusCd() >= 4) {

                    EnterEventDialog enterEventDlg = new EnterEventDialog(
                            getShell());
                    enterEventDlg.setCompositeEvent(true);
                    enterEventDlg.setSelectedEventType(selectedEvent.getType());
                    enterEventDlg.setNewEvent(selectedEvent);
                    if (Window.OK == enterEventDlg.open()) {
                        selectEventId = enterEventDlg.getNewEventId();
                    }
                } else {

                    EnterEventUtil
                            .displayMessageDialog(getShell(),
                                    "You may only create a composite of a 'best' report");
                }

                refreshEventsListTable();
            }
        }
    }

    /**
     * Action class that is responsible for upgrading a selected event
     * 
     * @author sgurung
     * 
     */
    private class UpgradeEventAction extends Action {

        public UpgradeEventAction() {
            super("Upgrade Event");
        }

        public void run() {

            int index = eventTableViewer.getTable().getSelectionIndex();
            if (index == -1)
                return; // no row selected

            TableItem item = eventTableViewer.getTable().getItem(index);

            // get data for the row that was clicked.
            gov.noaa.nws.ncep.common.dataplugin.editedevents.Event selectedEvent = (gov.noaa.nws.ncep.common.dataplugin.editedevents.Event) item
                    .getData();

            if (selectedEvent.getId() != 0) {
                String message = EditEventsUtil.upgradeEvent(
                        fromBeginCal.getTimeInMillis(), selectedEvent);

                if (message != null) {
                    EditEventsUtil.displayMessageDialog(getShell(), message);
                }

                refreshEventsListTable();
            }
        }
    }

    /**
     * Action class that is responsible for downgrading a selected event
     * 
     * @author sgurung
     * 
     */
    private class DowngradeEventAction extends Action {

        public DowngradeEventAction() {
            super("Downgrade Event");
        }

        public void run() {

            int index = eventTableViewer.getTable().getSelectionIndex();
            if (index == -1)
                return; // no row selected

            TableItem item = eventTableViewer.getTable().getItem(index);

            // get data for the row that was clicked.
            gov.noaa.nws.ncep.common.dataplugin.editedevents.Event selectedEvent = (gov.noaa.nws.ncep.common.dataplugin.editedevents.Event) item
                    .getData();

            if (selectedEvent.getId() != 0) {

                String message = EditEventsUtil.downgradeEvent(
                        fromBeginCal.getTimeInMillis(), selectedEvent);

                if (message != null) {
                    EditEventsUtil.displayMessageDialog(getShell(), message);
                }

                refreshEventsListTable();
            }
        }
    }

    /**
     * Resizes the events list table
     */
    private void resizeTable() {
        for (TableColumn tc : eventTableViewer.getTable().getColumns())
            tc.pack();
    }

    /**
     * Export the edited events list to a file
     * 
     */
    private void exportEvents() {

        JFrame parentFrame = new JFrame();

        JFileChooser chooser = new JFileChooser();
        chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        chooser.setDialogTitle("Export Edited Events List");

        int returnValue = chooser.showSaveDialog(parentFrame);

        if (returnValue == JFileChooser.APPROVE_OPTION) {

            try {
                EditEventsUtil.exportTable(eventTableViewer,
                        chooser.getSelectedFile());
            } catch (VizException e1) {
                EditEventsUtil.displayMessageDialog(getShell(),
                        "Error occurred while exporting the events to a file");
            }
        }

    }

}
