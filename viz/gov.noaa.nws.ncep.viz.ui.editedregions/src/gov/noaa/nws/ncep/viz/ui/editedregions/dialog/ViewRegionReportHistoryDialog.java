package gov.noaa.nws.ncep.viz.ui.editedregions.dialog;

import java.util.Objects;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;

import com.raytheon.uf.common.status.IUFStatusHandler;
import com.raytheon.uf.common.status.UFStatus;

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
    @SuppressWarnings("unused")
    private final IUFStatusHandler statusHandler = UFStatus
            .getHandler(EnterNewRegionDialog.class);

    @SuppressWarnings("unused")
    private final Integer reportId;

    public ViewRegionReportHistoryDialog(Shell shell, Integer reportId) {
        super(shell);

        this.reportId = Objects.requireNonNull(reportId, "Report ID");
        setShellStyle(SWT.TITLE | SWT.CLOSE | SWT.MAX | SWT.MIN | SWT.RESIZE
                | SWT.MODELESS);
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

        this.getShell().setSize(500, 500);

        // Initialize all of the controls, and layouts
        initializeComponents(top);

        return top;

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
        this.getShell().setMinimumSize(500, 500);

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

        resizeTable(tableViewer);
        tableViewer.refresh();
    }

    /**
     * Create the columns for the events list table
     */
    private void createColumns(TableViewer tableViewer) {

        String[] titles = new String[] { "Foo", "Bar", "Baz" };
        int[] bounds = new int[] { 50, 50, 50 };

        for (int i = 0; i < titles.length; i++) {
            createTableViewerColumn(tableViewer, titles[i], bounds[i], i);

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
