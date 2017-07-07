package gov.noaa.nws.ncep.viz.ui.editedregions.dialog;

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

import gov.noaa.nws.ncep.common.dataplugin.editedregions.RegionReport;
import gov.noaa.nws.ncep.viz.ui.editedregions.util.EditRegionsServerUtil;
import gov.noaa.nws.ncep.viz.ui.editedregions.util.EditRegionsUIConstants;

/**
 * Get the cell labels/values to be displayed in the edit events list table of
 * the EditEventsDialog. Also provides editing support for some of the cells.
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
public class EditRegionsLabelProvider implements ITableLabelProvider {

    private long beginDTTM = 0L;

    /**
     * Constructor
     *
     */
    public EditRegionsLabelProvider(long beginDTTM) {
        this.beginDTTM = beginDTTM;
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

        RegionReport report = (RegionReport) element;

        switch (columnIndex) {

        case EditRegionsUIConstants.COLUMN_INDEX_REPORT_TIME:
            ZonedDateTime dateTime = report.getObservationTime().toInstant()
                    .atZone(ZoneOffset.UTC);
            return DateTimeFormatter.ISO_ZONED_DATE_TIME.format(dateTime);

        case EditRegionsUIConstants.COLUMN_INDEX_OBSERVATORY:
            return convertToDisplay(report.getObservatory());

        case EditRegionsUIConstants.COLUMN_INDEX_QUALITY:
            return EditRegionsServerUtil
                    .getObservationQuality(report.getQuality());

        case EditRegionsUIConstants.COLUMN_INDEX_REGION:
            return convertToDisplay(report.getRegion());

        case EditRegionsUIConstants.COLUMN_INDEX_REPORT_LOCATION:
            return convertToDisplay(report.getReportLocation());

        case EditRegionsUIConstants.COLUMN_INDEX_LOCATION:
            return convertToDisplay(report.getLocation());

        case EditRegionsUIConstants.COLUMN_INDEX_CARLON:
            return convertToDisplay(report.getCarlon());

        case EditRegionsUIConstants.COLUMN_INDEX_EXTENT:
            return convertToDisplay(report.getExtent());

        case EditRegionsUIConstants.COLUMN_INDEX_AREA:
            return convertToDisplay(report.getArea());

        case EditRegionsUIConstants.COLUMN_INDEX_NUMSPOTS:
            return convertToDisplay(report.getNumspot());

        case EditRegionsUIConstants.COLUMN_INDEX_SPOTCLASS:
            return convertToDisplay(report.getSpotclass());

        case EditRegionsUIConstants.COLUMN_INDEX_MAGCLASS:
            return convertToDisplay(report.getMagclass());

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

    /**
     * @return the beginDTTM
     */
    public long getBeginDTTM() {
        return beginDTTM;
    }

    /**
     * @param beginDTTM
     *            the beginDTTM to set
     */
    public void setBeginDTTM(long beginDTTM) {
        this.beginDTTM = beginDTTM;
    }

}
