package gov.noaa.nws.ncep.viz.ui.editedregions.dialog;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;

import com.raytheon.uf.common.status.IUFStatusHandler;
import com.raytheon.uf.common.status.UFStatus;

import gov.noaa.nws.ncep.common.dataplugin.editedregions.RegionHistoryReport;
import gov.noaa.nws.ncep.common.dataplugin.editedregions.exception.EditedRegionsException;
import gov.noaa.nws.ncep.viz.ui.editedregions.util.EditRegionsServerUtil;

/**
 * 
 * This dialog is used to view the history of a region report that was selected
 * by the user.
 * 
 * <pre>
 *
 * SOFTWARE HISTORY
 *
 * Date         Ticket#    Engineer    Description
 * ------------ ---------- ----------- --------------------------
 * Jul 26, 2017            alockleigh     Initial creation
 *
 * </pre>
 *
 * @author alockleigh
 */

public class ViewRegionReportHistoryDialog extends Dialog {

    private static final String[] COLUMNS = new String[] { "Region Report ID",
            "Modified Field", "Value Before", "Current Value", "Time of Change",
            "Type of Change" };

    private static final int[] BOUNDS = new int[] { 50, 100, 100, 100, 100,
            50 };

    private static final Point DIALOG_SIZE = new Point(700, 500);

    private final IUFStatusHandler statusHandler = UFStatus
            .getHandler(EnterNewRegionDialog.class);

    /**
     * The id of the region report
     */
    @SuppressWarnings("unused")
    private final Integer reportId;

    /**
     * The list of region history report records.
     */
    private final List<RegionHistoryReport> reports;

    private final ViewRegionReportHistoryLabelProvider labelProvider = new ViewRegionReportHistoryLabelProvider();

    public ViewRegionReportHistoryDialog(Shell shell, Integer reportId) {
        super(shell);

        this.reportId = Objects.requireNonNull(reportId, "Report ID");
        setShellStyle(SWT.TITLE | SWT.CLOSE | SWT.MAX | SWT.MIN | SWT.RESIZE
                | SWT.MODELESS);
        List<RegionHistoryReport> tmpReports = Collections.emptyList();

        try {
            tmpReports = EditRegionsServerUtil.getReportHistory(reportId);
        } catch (EditedRegionsException ex) {
            statusHandler.error("Error retrieving report history", ex);
        }

        this.reports = tmpReports;
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
    protected Control createDialogArea(Composite parent) {

        Composite top = (Composite) super.createDialogArea(parent);

        // Create the main layout for the shell.

        GridLayout mainLayout = new GridLayout(1, true);
        mainLayout.marginHeight = 1;
        mainLayout.marginWidth = 1;
        top.setLayout(mainLayout);

        this.getShell().setSize(DIALOG_SIZE);

        // Initialize all of the controls, and layouts
        initializeComponents(top);

        return top;

    }

    @Override
    protected void createButtonsForButtonBar(Composite parent) {
        createButton(parent, IDialogConstants.CLOSE_ID,
                IDialogConstants.CLOSE_LABEL, true);

        Button closeBtn = getButton(IDialogConstants.CLOSE_ID);
        closeBtn.addSelectionListener(new SelectionAdapter() {
            public void widgetSelected(SelectionEvent ev) {
                ViewRegionReportHistoryDialog.this.close();
            }
        });
    }

    /**
     * Creates buttons, menus, and other controls in the dialog area
     * 
     */
    private void initializeComponents(Composite parent) {

        this.getShell().setText("View Report History");

        Composite composite = new Composite(parent, SWT.None);
        composite.setLayout(new GridLayout(1, false));

        GridData gd = new GridData();
        gd.grabExcessHorizontalSpace = true;
        gd.grabExcessVerticalSpace = true;
        gd.horizontalAlignment = GridData.FILL;
        gd.verticalAlignment = GridData.FILL;

        composite.setLayoutData(gd);

        Group topGroup = new Group(composite, SWT.SHADOW_NONE);
        GridLayout groupLayout = new GridLayout(1, false);
        topGroup.setLayout(groupLayout);

        gd = new GridData();
        gd.grabExcessHorizontalSpace = true;
        gd.grabExcessVerticalSpace = true;
        gd.horizontalAlignment = GridData.FILL;
        gd.verticalAlignment = GridData.FILL;
        topGroup.setLayoutData(gd);

        this.createTableViewer(topGroup);
        this.getShell().setMinimumSize(DIALOG_SIZE);

        Display.getCurrent().asyncExec(new Runnable() {
            @Override
            public void run() {
                ViewRegionReportHistoryDialog.this.getShell().layout(true,
                        true);
            }
        });
    }

    /**
     * Creates the TableViewer object containing the history data
     * 
     * @param parent
     */
    private void createTableViewer(Composite parent) {

        Composite composite = new Composite(parent, SWT.None);
        composite.setLayout(new GridLayout(1, false));
        composite.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));

        SashForm sashForm = new SashForm(composite, SWT.HORIZONTAL);
        GridData sashGd = new GridData(SWT.FILL, SWT.FILL, true, true);
        sashForm.setLayoutData(sashGd);
        sashForm.setSashWidth(3);

        TableViewer tableViewer = new TableViewer(sashForm,
                SWT.V_SCROLL | SWT.H_SCROLL | SWT.FULL_SELECTION);
        tableViewer.setContentProvider(new ArrayContentProvider());

        createColumns(tableViewer);

        Table table = tableViewer.getTable();
        table.setLinesVisible(true);
        table.setHeaderVisible(true);

        tableViewer.setLabelProvider(labelProvider);
        tableViewer.setInput(reports);
        resizeTable(tableViewer);

        tableViewer.refresh();
    }

    /**
     * Create the columns for the events list table
     */
    private void createColumns(TableViewer tableViewer) {

        for (int i = 0; i < COLUMNS.length; i++) {
            createTableViewerColumn(tableViewer, COLUMNS[i], BOUNDS[i], i);

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
     * Resizes the events list table
     */
    private void resizeTable(TableViewer tableViewer) {
        for (TableColumn tc : tableViewer.getTable().getColumns())
            tc.pack();
    }

}
