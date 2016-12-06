package gov.noaa.nws.ncep.viz.ui.editedregions.dialog;

import gov.noaa.nws.ncep.common.dataplugin.editedevents.Event;
import gov.noaa.nws.ncep.common.dataplugin.editedevents.EventBin;
import gov.noaa.nws.ncep.viz.ui.editedregions.util.EditEventsUIConstants;
import gov.noaa.nws.ncep.viz.ui.editedregions.util.EditEventsUtil;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
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

/**
 * Get the cell labels/values to be displayed in the edit events list 
 * table of the EditEventsDialog. Also provides editing support for some 
 * of the cells.
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
	 * List of bin numbers that are displayed in the 
	 * cell editor for the "Bin" column.
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
    
	/* (non-Javadoc)
	 * @see org.eclipse.jface.viewers.IBaseLabelProvider#dispose()
	 */
	@Override
	public void dispose() {
		
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jface.viewers.IBaseLabelProvider#isLabelProperty(java.lang.Object, java.lang.String)
	 */
	@Override
	public boolean isLabelProperty(Object element, String property) {
		return true;
	}

	@Override
	public void removeListener(ILabelProviderListener listener) {
		// TODO Auto-generated method stub
		
	}	

	/* (non-Javadoc)
	 * @see org.eclipse.jface.viewers.IBaseLabelProvider#addListener(org.eclipse.jface.viewers.ILabelProviderListener)
	 */
	@Override
	public void addListener(ILabelProviderListener listener) {
		
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jface.viewers.ITableLabelProvider#getColumnImage(java.lang.Object, int)
	 */
	@Override
	public Image getColumnImage(Object element, int columnIndex) {
		
		return null;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jface.viewers.ITableLabelProvider#getColumnText(java.lang.Object, int)
	 */
	@Override
	public String getColumnText(Object element, int columnIndex) {
		
		String returnValue = "";
        Event er = (Event) element;
            
        switch (columnIndex) {
          case EditEventsUIConstants.COLUMN_INDEX_BIN:
             returnValue = (er.getBin() != null) ? er.getBin().getBinNumber().toString() : "" ;
             break;
          case EditEventsUIConstants.COLUMN_INDEX_BEGINQ:
              String beginQ = er.getBegInq();
              beginQ = (beginQ != null && !beginQ.trim()
                      .equalsIgnoreCase("null")) ? er.getBegInq()
                      : "";
              returnValue = beginQ;
              break;
          case EditEventsUIConstants.COLUMN_INDEX_BEGIN_DATE:
              Date date = null;
              if (er.getBeginDate() != null) {
                  date = er.getBeginDate().getTime();
              }
              returnValue = ((date != null) ? dateFormatWithoutTime.format(date)
                      : null);
              break;
          case EditEventsUIConstants.COLUMN_INDEX_BEGIN_TIME:
        	  String beginTime = (er.getBeginTime() != null && er.getId() != 0)? er.getBeginTime().toString():"";
        	  beginTime = (beginTime.length() == 1)? ("000" + beginTime) : beginTime;
        	  beginTime = (beginTime.length() == 2)? ("00" + beginTime) : beginTime;
        	  beginTime = (beginTime.length() == 3)? ("0" + beginTime) : beginTime;
              returnValue = beginTime;
              break;
          case EditEventsUIConstants.COLUMN_INDEX_MAXQ:
              String maxQ = er.getMaxq();
              maxQ = (maxQ != null && !maxQ.trim().equalsIgnoreCase(
                      "null")) ? er.getMaxq() : "";
              returnValue = maxQ;
              break;
          case EditEventsUIConstants.COLUMN_INDEX_MAX_TIME:
        	  String maxTime = (er.getMaxTime() != null && er.getId() != 0)? er.getMaxTime().toString():"";
        	  maxTime = (maxTime.length() == 1)? ("000" + maxTime) : maxTime;
        	  maxTime = (maxTime.length() == 2)? ("00" + maxTime) : maxTime;
        	  maxTime = (maxTime.length() == 3)? ("0" + maxTime) : maxTime;
              returnValue = maxTime;
              break;
          case EditEventsUIConstants.COLUMN_INDEX_ENDQ:
              String endQ = er.getEndq();
              endQ = (endQ != null && !endQ.trim().equalsIgnoreCase(
                      "null")) ? er.getEndq() : "";
              returnValue = endQ;       
              break;     
          case EditEventsUIConstants.COLUMN_INDEX_END_TIME:
          	String endTime = (er.getEndTime() != null && er.getId() != 0)? er.getEndTime().toString():"";
          	endTime = (endTime.length() == 1)? ("000" + endTime) : endTime;
        	endTime = (endTime.length() == 2)? ("00" + endTime) : endTime;
          	endTime = (endTime.length() == 3)? ("0" + endTime) : endTime;
            returnValue = endTime;
            break;
          case EditEventsUIConstants.COLUMN_INDEX_OBSERVATORY:
          	returnValue = er.getObservatory();
            break;
          case EditEventsUIConstants.COLUMN_INDEX_QUALITY:
          	returnValue = er.getQuality();
            break;
          case EditEventsUIConstants.COLUMN_INDEX_TYPE:
          	returnValue = er.getType();
            break;
          case EditEventsUIConstants.COLUMN_INDEX_LOCATION:
              String location = er.getLocation();
              location = (location != null && !location.trim()
                      .equalsIgnoreCase("null")) ? er.getLocation()
                      : "";
              returnValue = location;
              break;
          case EditEventsUIConstants.COLUMN_INDEX_FREQUENCY:
              String frequency = er.getFrequency();
              frequency = (frequency != null && !frequency.trim()
                      .equalsIgnoreCase("null")) ? er.getFrequency()
                      : "";
              returnValue = frequency;
              break;
          case EditEventsUIConstants.COLUMN_INDEX_PARTICULARS_1:
              String part1 = er.getParticulars1();
              part1 = (part1 != null && !part1.trim()
                      .equalsIgnoreCase("null")) ? part1 : "";
              returnValue = part1;
              break;
          case EditEventsUIConstants.COLUMN_INDEX_PARTICULARS_2:
              String part2 = er.getParticulars2();
              part2 = (part2 != null && !part2.trim()
                      .equalsIgnoreCase("null")) ? part2 : "";
              returnValue = part2;
              break;
          case EditEventsUIConstants.COLUMN_INDEX_PARTICULARS_3:
              String part3 = er.getParticulars3();
              part3 = (part3 != null && !part3.trim()
                      .equalsIgnoreCase("null")) ? part3 : "";
              returnValue = part3;
              break;
          case EditEventsUIConstants.COLUMN_INDEX_PARTICULARS_4:
              String part4 = er.getParticulars4();
              part4 = (part4 != null && !part4.trim()
                      .equalsIgnoreCase("null")) ? part4 : "";
              returnValue = part4;
              break;
          case EditEventsUIConstants.COLUMN_INDEX_PARTICULARS_5:
              String part5 = er.getParticulars5();
              part5 = (part5 != null && !part5.trim()
                      .equalsIgnoreCase("null")) ? part5 : "";
              returnValue = part5;
              break;
          case EditEventsUIConstants.COLUMN_INDEX_PARTICULARS_6:
              String part6 = er.getParticulars6();
              part6 = (part6 != null && !part6.trim()
                      .equalsIgnoreCase("null")) ? part6 : "";
              returnValue = part6;
              break;
          case EditEventsUIConstants.COLUMN_INDEX_PARTICULARS_7:
              String part7 = er.getParticulars7();
              part7 = (part7 != null && !part7.trim()
                      .equalsIgnoreCase("null")) ? part7 : "";
              returnValue = part7;
              break;
          case EditEventsUIConstants.COLUMN_INDEX_PARTICULARS_8:
              String part8 = er.getParticulars8();
              part8 = (part8 != null && !part8.trim()
                      .equalsIgnoreCase("null")) ? part8 : "";
              returnValue = part8;
              break;
          case EditEventsUIConstants.COLUMN_INDEX_REGION:
              Integer region = er.getRegion();
              returnValue = ((region != null && region != 9999) ? region
                      .toString() : "");
              break;
          case EditEventsUIConstants.COLUMN_INDEX_AGE:
              String age = er.getAge();
              returnValue = age;
              break;
          case EditEventsUIConstants.COLUMN_INDEX_STATUS_TEXT:
              returnValue = er.getStatusText();
              break;
          default:
          	returnValue = "";
            break;
          }
         
         return returnValue;
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
                        	 if(element != null)
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
        	 Event event = (Event) element;
        	 if (event.getId() != 0) {
        		 return true;
        	 } else {
        		 return false;
        	 }
         }

         @Override
         protected CellEditor getCellEditor(Object element) {
             
        	 Event event = (Event) element;
             ComboBoxViewerCellEditor ve;
             switch (columnIndex) {
             case 0:  
                 ve = (ComboBoxViewerCellEditor) editor;    
                 ve.getViewer().setContentProvider( new ComboContentProvider());
                 ve.getViewer().setInput(clean(bins));                     
                 ve.getViewer().getCCombo().setText(String.valueOf(event.getBin().getBinNumber()));                 
                 this.setValue(element, event.getBin().getBinNumber());               
            	 
                 return editor;
             }
             return null;
         }

         @Override
         protected Object getValue(Object element) {
        	 Event event = (Event) element;
             switch (columnIndex) {            
             case 0:
                 if (event.getBin() != null)
                     return new Integer(event.getBin().getBinNumber());
                 else
                     return new Integer(0);
             }
             return null;
         }

         @Override
         protected void setValue(Object element, Object value) {
        	
        	 Event event = (Event) element;
        	 
             switch (columnIndex) {
             case 0:
            	 Integer binNumber = null;
                 if (value != null) { 
                	 if (value instanceof EventBin) {
                		 
                		 binNumber = ((EventBin) value).getBinNumber();
                	 } else if (value instanceof String) {
                		 
                		 String val = (String) value;
                		 if (!val.isEmpty()) {
                			 binNumber = Integer.parseInt(val);
                		 }
                	 } else {
                		 
                		 binNumber = (Integer) value;
                	 }
                 }

                 // Assign the new bin number to the event (re-bin)
                 if (event.getId() != 0 & binNumber != null && event.getBin().getBinNumber().intValue() != binNumber) {
                	 EditEventsUtil.rebinEvent(beginDTTM, event, binNumber);
                 }
                 break;
             }
         }

     }
     
     private class ComboContentProvider implements IStructuredContentProvider
     {
	     @Override
	     public Object[] getElements(Object inputElement)
	     {
		     Object[] result = null;
		     
		     if( inputElement instanceof String[] )
		     {
		    	 result = (String[])inputElement; 
		     }
		     return result;
	     }
	
	     @Override
	     public void dispose()
	     {
	     }
	
		 @Override
		 public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
			
		 }
     }

	/**
	 * @return the bins
	 */
	public String[] getBins() {
		return bins;
	}

	/**
	 * @param bins the bins to set
	 */
	public void setBins(String[] bins) {
		this.bins = bins;
	}
	
	/**
	 * Clean the array of strings to remove empty strings
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
	 * @param beginDTTM the beginDTTM to set
	 */
	public void setBeginDTTM(long beginDTTM) {
		this.beginDTTM = beginDTTM;
	}
   
}
