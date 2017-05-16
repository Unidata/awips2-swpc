package gov.noaa.nws.ncep.viz.ui.editedregions.dialog;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.ColumnViewer;
import org.eclipse.jface.viewers.ComboBoxViewerCellEditor;
import org.eclipse.jface.viewers.EditingSupport;
import org.eclipse.jface.viewers.ILabelProviderListener;
import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Composite;

import gov.noaa.nws.ncep.common.dataplugin.editedregions.RegionReport;
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

    /**
     * List of bin numbers that are displayed in the cell editor for the "Bin"
     * column.
     */
    private String[] bins = null;

    private long beginDTTM = 0L;

    /**
     * Date format
     */
    protected SimpleDateFormat dateFormatWithoutTime = new SimpleDateFormat(
            "dd MMM yyyy");

    /**
     * Constructor
     *
     */
    public EditRegionsLabelProvider(String[] bins, long beginDTTM) {
        this.bins = bins;
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

        case EditRegionsUIConstants.COLUMN_INDEX_STATION:
            return String.valueOf(report.getStation());

        case EditRegionsUIConstants.COLUMN_INDEX_OBSERVATORY:
            return String.valueOf(report.getObservatory());

        case EditRegionsUIConstants.COLUMN_INDEX_TYPE:
            return report.getType();

        case EditRegionsUIConstants.COLUMN_INDEX_QUALITY:
            return String.valueOf(report.getQuality());

        case EditRegionsUIConstants.COLUMN_INDEX_REGION:
            return String.valueOf(report.getRegion());

        case EditRegionsUIConstants.COLUMN_INDEX_LATITUDE:
            return String.valueOf(report.getLatitude());

        case EditRegionsUIConstants.COLUMN_INDEX_REPORT_LONGITUDE:
            return String.valueOf(report.getReportLongitude());

        case EditRegionsUIConstants.COLUMN_INDEX_LONGITUDE:
            return String.valueOf(report.getLongitude());

        case EditRegionsUIConstants.COLUMN_INDEX_REPORT_LOCATION:
            return report.getReportLocation();

        case EditRegionsUIConstants.COLUMN_INDEX_LOCATION:
            return report.getLocation();

        case EditRegionsUIConstants.COLUMN_INDEX_CARLON:
            return String.valueOf(report.getCarlon());

        case EditRegionsUIConstants.COLUMN_INDEX_EXTENT:
            return String.valueOf(report.getExtent());

        case EditRegionsUIConstants.COLUMN_INDEX_AREA:
            return String.valueOf(report.getArea());

        case EditRegionsUIConstants.COLUMN_INDEX_NUMSPOTS:
            return String.valueOf(report.getNumspots());

        case EditRegionsUIConstants.COLUMN_INDEX_ZURICH:
            return String.valueOf(report.getZurich());

        case EditRegionsUIConstants.COLUMN_INDEX_PENUMBRA:
            return String.valueOf(report.getPenumbra());

        case EditRegionsUIConstants.COLUMN_INDEX_COMPACT:
            return report.getCompact();

        case EditRegionsUIConstants.COLUMN_INDEX_SPOTCLASS:
            return report.getSpotclass();

        case EditRegionsUIConstants.COLUMN_INDEX_MAGCODE:
            return String.valueOf(report.getMagcode());

        case EditRegionsUIConstants.COLUMN_INDEX_MAGCLASS:
            return report.getMagclass();

        case EditRegionsUIConstants.COLUMN_INDEX_OBSID:
            return String.valueOf(report.getObsid());

        case EditRegionsUIConstants.COLUMN_INDEX_REPORT_STATUS:
            return String.valueOf(report.getReportStatus());

        case EditRegionsUIConstants.COLUMN_INDEX_VALID_SPOT_CLASS:
            return String.valueOf(report.isValidSpotClass());

        default:
            return "";
        }
    }

    public int getColumnCount() {
        // TODO: Set a constant.
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

        @Override
        protected void setValue(Object element, Object value) {

            // TODO: add code, or nuke this subclass.
        }

    }

    private class ComboContentProvider implements IStructuredContentProvider {
        @Override
        public Object[] getElements(Object inputElement) {
            Object[] result = null;

            if (inputElement instanceof String[]) {
                result = (String[]) inputElement;
            }
            return result;
        }

        @Override
        public void dispose() {
        }

        @Override
        public void inputChanged(Viewer viewer, Object oldInput,
                Object newInput) {

        }
    }

    /**
     * @return the bins
     */
    public String[] getBins() {
        return bins;
    }

    /**
     * @param bins
     *            the bins to set
     */
    public void setBins(String[] bins) {
        this.bins = bins;
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
