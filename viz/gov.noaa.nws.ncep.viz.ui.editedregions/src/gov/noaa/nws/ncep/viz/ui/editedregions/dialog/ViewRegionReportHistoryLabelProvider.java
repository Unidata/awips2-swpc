package gov.noaa.nws.ncep.viz.ui.editedregions.dialog;

import java.time.Instant;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.ColumnViewer;
import org.eclipse.jface.viewers.ComboBoxViewerCellEditor;
import org.eclipse.jface.viewers.EditingSupport;
import org.eclipse.jface.viewers.ILabelProviderListener;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Composite;

import gov.noaa.nws.ncep.common.dataplugin.editedregions.RegionHistoryReport;
import gov.noaa.nws.ncep.viz.ui.editedregions.util.EditRegionsUIConstants;

/**
 * Get the cell labels/values to be displayed in the edit events list table of
 * the ViewRegionReportHistoryDialog.
 * 
 * <pre>
 * 
 * SOFTWARE HISTORY
 * 
 * Date         Ticket#    Engineer    Description
 * ------------ ---------- ----------- --------------------------
 * Feb 16, 2015   R9583    sgurung     Initial creation
 * 
 * </pre>
 * 
 * @author sgurung
 * @version 1.0
 */
public class ViewRegionReportHistoryLabelProvider
        implements ITableLabelProvider {

    /**
     * Constructor
     *
     */
    public ViewRegionReportHistoryLabelProvider() {
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.jface.viewers.IBaseLabelProvider#dispose()
     */
    @Override
    public void dispose() {

    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.eclipse.jface.viewers.IBaseLabelProvider#isLabelProperty(java.lang.
     * Object, java.lang.String)
     */
    @Override
    public boolean isLabelProperty(Object element, String property) {
        return true;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.eclipse.jface.viewers.IBaseLabelProvider#removeListener(org.eclipse.
     * jface.viewers.ILabelProviderListener)
     */
    @Override
    public void removeListener(ILabelProviderListener listener) {
        // TODO Auto-generated method stub

    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.eclipse.jface.viewers.IBaseLabelProvider#addListener(org.eclipse.
     * jface.viewers.ILabelProviderListener)
     */
    @Override
    public void addListener(ILabelProviderListener listener) {

    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.eclipse.jface.viewers.ITableLabelProvider#getColumnImage(java.lang.
     * Object, int)
     */
    @Override
    public Image getColumnImage(Object element, int columnIndex) {

        return null;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.eclipse.jface.viewers.ITableLabelProvider#getColumnText(java.lang.
     * Object, int)
     */
    @Override
    public String getColumnText(Object element, int columnIndex) {

        RegionHistoryReport historyReport = (RegionHistoryReport) element;

        switch (columnIndex) {

        case EditRegionsUIConstants.COLUMN_INDEX_HIST_REGION_REPORT_ID:
            return convertToDisplay(historyReport.getRegionReportId());

        case EditRegionsUIConstants.COLUMN_INDEX_HIST_MODIFIED_FIELD:
            return convertToDisplay(historyReport.getModifiedField());

        case EditRegionsUIConstants.COLUMN_INDEX_HIST_VALUE_NEW:
            return convertToDisplay(historyReport.getFieldValueCurrent());

        case EditRegionsUIConstants.COLUMN_INDEX_HIST_VALUE_OLD:
            return convertToDisplay(historyReport.getFieldValueBefore());

        case EditRegionsUIConstants.COLUMN_INDEX_HIST_TIME_OF_CHANGE:
            long timestamp = historyReport.getDataTime().getRefTime().getTime();
            ZonedDateTime dateTime = Instant.ofEpochMilli(timestamp)
                    .atZone(ZoneOffset.UTC);
            return DateTimeFormatter.ISO_ZONED_DATE_TIME.format(dateTime);

        case EditRegionsUIConstants.COLUMN_INDEX_HIST_CHANGE_TYPE:
            return convertToDisplay(historyReport.getTypeOfChange());

        default:
            return "";
        }
    }

    /**
     * Convert the element into text for display on the Edit Regions GUI. If the
     * element is not null, the toString() element is called on the element.
     * Otherwise, an empty string is returned.
     * 
     * @param element
     * @return
     */
    private static String convertToDisplay(Object element) {
        return element != null ? element.toString() : "";
    }

    /**
     * 
     * @return The number of region report columns.
     */
    public int getColumnCount() {
        return EditRegionsUIConstants.COLUMN_COUNT;
    }

    /**
     * 
     * 
     * @param viewer
     * @param columnIndex
     * @return
     */
    public EditingSupport getEditorSupport(ColumnViewer viewer,
            int columnIndex) {
        return new ElementEditSupport(viewer, columnIndex);
    }

    protected class ElementEditSupport extends EditingSupport {

        int columnIndex;

        CellEditor editor;

        public ElementEditSupport(ColumnViewer viewer, int columnIndex) {
            super(viewer);
            this.columnIndex = columnIndex;

            switch (columnIndex) {
            case 0:
                ComboBoxViewerCellEditor ve = new ComboBoxViewerCellEditor(
                        (Composite) viewer.getControl(), SWT.READ_ONLY);
                editor = ve;
                ve.getViewer().setLabelProvider(new LabelProvider() {
                    @Override
                    public String getText(Object element) {
                        if (element != null)
                            return (String) element;
                        else
                            return "";
                    }

                });

                break;
            }
        }

        /*
         * (non-Javadoc)
         * 
         * @see
         * org.eclipse.jface.viewers.EditingSupport#canEdit(java.lang.Object)
         */
        @Override
        protected boolean canEdit(Object element) {
            // Event event = (Event) element;
            // if (event.getId() != 0) {
            // return true;
            // } else {
            // return false;
            // }
            return true;
        }

        /*
         * (non-Javadoc)
         * 
         * @see
         * org.eclipse.jface.viewers.EditingSupport#getCellEditor(java.lang.
         * Object)
         */
        @Override
        protected CellEditor getCellEditor(Object element) {

            // Event event = (Event) element;
            // ComboBoxViewerCellEditor ve;
            // switch (columnIndex) {
            // case 0:
            // ve = (ComboBoxViewerCellEditor) editor;
            // ve.getViewer().setContentProvider(new ComboContentProvider());
            // ve.getViewer().setInput(clean(bins));
            // ve.getViewer().getCCombo()
            // .setText(String.valueOf(event.getBin().getBinNumber()));
            // this.setValue(element, event.getBin().getBinNumber());
            //
            // return editor;
            // }
            return null;
        }

        /*
         * (non-Javadoc)
         * 
         * @see
         * org.eclipse.jface.viewers.EditingSupport#getValue(java.lang.Object)
         */
        @Override
        protected Object getValue(Object element) {
            // Event event = (Event) element;
            // switch (columnIndex) {
            // case 0:
            // if (event.getBin() != null)
            // return new Integer(event.getBin().getBinNumber());
            // else
            // return new Integer(0);
            // }
            return null;
        }

        /*
         * (non-Javadoc)
         * 
         * @see
         * org.eclipse.jface.viewers.EditingSupport#setValue(java.lang.Object,
         * java.lang.Object)
         */
        @Override
        protected void setValue(Object element, Object value) {

            // TODO: add code, or nuke this subclass.
        }
    }

    /**
     * Clean the array of strings to remove empty strings
     * 
     * @param v
     * @return String[]
     */
    public static String[] clean(final String[] v) {
        List<String> list = new ArrayList<String>(Arrays.asList(v));
        list.removeAll(Collections.singleton(""));
        return list.toArray(new String[list.size()]);
    }

}
