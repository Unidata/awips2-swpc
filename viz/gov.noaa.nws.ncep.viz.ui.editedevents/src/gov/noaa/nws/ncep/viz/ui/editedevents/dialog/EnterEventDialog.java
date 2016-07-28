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

import gov.noaa.nws.ncep.common.dataplugin.editedevents.util.EditedEventsConstants;
import gov.noaa.nws.ncep.viz.ui.editedevents.Activator;
import gov.noaa.nws.ncep.viz.ui.editedevents.util.EditEventsUtil;
import gov.noaa.nws.ncep.viz.ui.editedevents.util.EnterEventUtil;
import gov.noaa.nws.ncep.viz.ui.editedevents.util.XrayFlux;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.TimeZone;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.FocusEvent;
import org.eclipse.swt.events.FocusListener;
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
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

import com.raytheon.uf.common.status.IUFStatusHandler;
import com.raytheon.uf.common.status.UFStatus;
import com.raytheon.uf.common.time.DataTime;
import com.raytheon.uf.viz.core.exception.VizException;

/**
 * This dialog provides an interface allowing a user to create an event
 * report.  There are two methods of invoking the interface:  from the "Enter Event"
 * menu on the menu bar and with a create composite action in edited
 * events list.  The create composite action bases the new event report on the current
 * report in edited events, and adds the report to the events table and to the
 * Events list interface. When saved, the report is added to the events table,
 * and will be handled as a new report by edited events either during its next run or
 * when the user clicks on the "Refresh" button.
 * 
 * <pre>
 *
 * SOFTWARE HISTORY
 *
 * Date         Ticket#    Engineer    Description
 * ------------ ---------- ----------- --------------------------
 * Jan 26, 2016 R9583       sgurung     Initial creation
 *
 * </pre>
 *
 * @author sgurung
 * @version 1.0	
 */

public class EnterEventDialog extends Dialog {
	
	private final IUFStatusHandler statusHandler = UFStatus
            .getHandler(EditEventsDialog.class);
	
	protected SimpleDateFormat dateFormat = new SimpleDateFormat(
	            "dd MMM yyyy HH:mm:ss");
	
	private TimeZone defaultTZ = TimeZone.getTimeZone("UTC");
	
    // last location of the dialog
    private Point lastLocation = null;

    // last size
    private Point lastSize = null;
    
    private String selectedEventType = null;
    
    private Text begDateText = null;
    
    private Text maxDateText = null;
    
    private Text endDateText = null;
    
    private Combo begTimeQCombo = null;
    
    private Combo maxTimeQCombo = null;
    
    private Combo endTimeQCombo = null;
    
    private Combo obsCombo = null;
    
    private Combo qualityCombo = null;
    
    private Combo codedTypeCombo = null;
    
    private Text obsText = null;
    
    private Text locationText = null;
    
    private Text frequencyText = null;
    
    private Text part1Text = null;
    
    private Text part2Text = null;
    
    private Text part3Text = null;
    
    private Text part4Text = null;
    
    private Text part5Text = null;
    
    private Text regionText = null;
    
    private Button integrateFluxButton = null;
    
    private Label frequencyLabel = null;
    
    private Label part1Label = null;
    
    private Label part2Label = null;
    
    private Label part3Label = null;
    
    private Label part4Label = null;
    
    private Label part5Label = null; 
    
    private boolean isCompositeEvent = false;
        
    /**
     * The label for Save button
     */
    public String SAVE_LABEL = "Save";
    
    /**
     * The label for Exit button
     */
    public String EXIT_LABEL = "Exit";
    
    /**
     * The new event representing the user entries
     */
    private gov.noaa.nws.ncep.common.dataplugin.editedevents.Event newEvent = null; 
    
    /**
     * Boolean value to indicate whether new changes exist since the last time the user hit 'Save'
     * (or since the form was initially displayed if there aren't any 'Save' actions)
     */
    private boolean newChangesExist = true;
        
    /**
     * The ID of the newly created event
     */
    private Integer newEventId = null;      
	
	/**
	 * 
	 */
	public EnterEventDialog(Shell parent) {
		 super(parent);
	     setShellStyle(SWT.TITLE | SWT.CLOSE | SWT.MAX | SWT.MIN | SWT.RESIZE
	                | SWT.MODELESS);
	     dateFormat.setTimeZone(defaultTZ);

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

        // Initialize all of the controls, and layouts
        initializeComponents(top);

        top.setSize(750, 700);

        return top;

    }
    
    
    /* (non-Javadoc)
     * @see org.eclipse.jface.dialogs.Dialog#createButtonsForButtonBar(org.eclipse.swt.widgets.Composite)
     * The labels for the buttons are changed to "Save" and "Exit"
     */
    @Override
    protected void createButtonsForButtonBar(Composite parent) {
		// create Save and Exit buttons 
		createButton(parent, IDialogConstants.OK_ID, this.SAVE_LABEL,
				true);
		createButton(parent, IDialogConstants.CANCEL_ID,
				EXIT_LABEL, false);
	}
    
    /**
	 * Save the event after validating the entries. Executed when the 'Save' button is clicked.
	 *
	 */
    @Override
	protected void okPressed() {
    	
    	if (this.validateEntries() == true) {
    		
    		newEventId = EnterEventUtil.saveNewEvent(this.newEvent);
    		
    		if (newEventId == null) {
    			EnterEventUtil.displayErrorMessageDialog(this.getShell(), "Error occurred while saving the event. Please try again.");    			
    		} else if (newEvent.getId() == 0) { 
    			// If the event ID was not set before, set it here
        		newEvent.setId(newEventId);   
        		this.newChangesExist = false;
        		
        		EnterEventUtil.displayMessageDialog(this.getShell(), "The event has been saved."); 
    		}
    	}
	}
    
    /**
     * Exits the form.  First check with the user to see if they want to add it.  If so,
     * add the report and close the form. Executed when the 'Exit' button is clicked.
     */
    @Override
    protected void cancelPressed() {    	
    	
    	if (this.newChangesExist) {
    		// New changes exist
    	
	    	MessageDialog confirmDlg = new MessageDialog(this.getShell(), "Confirm", null, 
	    			"Do you want to save the changes you made?",
	    			MessageDialog.QUESTION_WITH_CANCEL, 
	    			new String[]{ IDialogConstants.YES_LABEL, IDialogConstants.NO_LABEL, IDialogConstants.CANCEL_LABEL}, 0);
	    
	    	
	    	switch(confirmDlg.open()) {
		    	case 0: 
		    		//yes
		    		okPressed();
		    	case 1:
		    		//no
		    		if (this.newEvent != null) {
		    			
		    			// process the new event created
		    			EditEventsUtil.processNewEvents(this.newEvent.getBeginTime());
		    		}
		    		close();
		    		break;
		    	case 2:
		    		//cancel - go back to the Enter Event form
		    		//close();
		    		break;
		    	default:
		    		close();
		    		break;
	    	}
	
    	} else {
    		// No new changes exist    		
    		if (this.newEvent != null) {
    			
    			// process the new event created
    			EditEventsUtil.processNewEvents(this.newEvent.getBeginTime());
    		}
    		close();
    	}
		
	}

    
    /**
     * Creates buttons, menus, and other controls in the dialog area
     * 
     */
    private void initializeComponents(Composite parent) {

        getShell().setText("Enter " + this.selectedEventType + " Event");       

        Composite composite = new Composite(parent, SWT.None);
        composite.setLayout(new GridLayout(1, false));

        GridData gd = new GridData();
        gd = new GridData();
        gd.grabExcessHorizontalSpace = true;
        gd.grabExcessVerticalSpace = true;
        gd.horizontalAlignment = GridData.FILL;
        gd.verticalAlignment = GridData.FILL;

        composite.setLayoutData(gd);

        Group topGroup = new Group(composite, SWT.SHADOW_NONE);
        GridLayout groupLayout = new GridLayout(6, false);
        topGroup.setLayout(groupLayout);
        topGroup.setLayoutData(new GridData(SWT.FILL, SWT.TOP, true, true));
        
        this.createDateTimeControls(topGroup);
        
        Group bottomGroup = new Group(composite, SWT.None);        
        groupLayout = new GridLayout(1, false);
        bottomGroup.setLayout(groupLayout);
        bottomGroup.setLayoutData(new GridData(SWT.FILL, SWT.TOP, true, true));

        this.createFieldControls(bottomGroup);
        
        //this.createDescControls(bottomGroup);
                
        this.initilizeControlsWithDefaultValues();
        
        if (this.newEvent != null) {
        	this.initializeValuesForControls();
        }

    }
        
    /**
     * Creates date and time controls 
     * 
     * @param group
     */
    private void createDateTimeControls(Group group) {
    
        GridData gd = new GridData();
        gd = new GridData();
        gd.grabExcessHorizontalSpace = true;
        gd.grabExcessVerticalSpace = true;
        gd.horizontalAlignment = GridData.FILL;
        gd.verticalAlignment = GridData.FILL;

        group.setLayoutData(gd);
    	
    	// Begin date and time
        displayAsterik(group); //indicate begin date is required
    	new Label(group, SWT.LEFT).setText("Begin Date:");
        begDateText = new Text(group, SWT.BORDER);
    	begDateText.setLayoutData(new GridData(175, 18));
        begDateText.setText(dateFormat.format(Calendar.getInstance().getTime()));
        begDateText.addFocusListener(this.createFocusListener());
          
        String iconString = "icons/date_picker.gif";
  		ImageDescriptor id = Activator.imageDescriptorFromPlugin(
  				Activator.PLUGIN_ID, iconString);
  		Image icon = null;
  		if ( id != null ) {
  			icon = id.createImage();
  		}		
  		
  		final Button beginDatePickerBtn = new Button(group, SWT.PUSH); 
  		if (icon != null) {
  			beginDatePickerBtn.setImage(icon);
  		}
  		beginDatePickerBtn.addSelectionListener(new SelectionAdapter() {
  	            @Override
  	            public void widgetSelected(SelectionEvent e) {  	            	
  	            	newChangesExist = true;
  	            	
  	            	CalendarDialog calSelDlg = new CalendarDialog(
  	                		getShell());
  	                
  	            	Calendar selectedDT = null;
  	            	
  	            	Calendar beginCal = EnterEventUtil.textIsADate(begDateText.getText(), dateFormat);
  	            	if (beginCal != null) {
  	            		selectedDT = calSelDlg.open(beginCal, defaultTZ);
  	            	} else {
  	            		selectedDT = calSelDlg.open();
  	            	}
  	            	
  	                if (selectedDT != null) {
  	                    begDateText.setText(dateFormat.format(selectedDT.getTime()));
  	                } else {
  	                    return;
  	                }
  	            }

  	        });	
  		  		
  		//Begin time qualifier
  		new Label(group, SWT.LEFT).setText("   Begin Time Qualifier:");
        begTimeQCombo = new Combo(group, SWT.DROP_DOWN | SWT.READ_ONLY);
        begTimeQCombo.setItems(EnterEventUtil.getBeginTimeQualifierOptions(selectedEventType));
        begTimeQCombo.select(0);
        
        begTimeQCombo.addSelectionListener(new SelectionAdapter() {
	            @Override
	            public void widgetSelected(SelectionEvent e) {
	            	newChangesExist = true;
	            }

	        });	
                
        // Max date and time
        displayAsterik(group); //indicate max date is required
    	new Label(group, SWT.LEFT).setText("Max Date:");
        maxDateText = new Text(group, SWT.BORDER);
    	maxDateText.setLayoutData(new GridData(175, 18));
        //maxDateText.setEnabled(false);
        maxDateText.setText(dateFormat.format(Calendar.getInstance().getTime()));
        maxDateText.addFocusListener(this.createFocusListener());
  		
  		final Button maxDatePickerBtn = new Button(group, SWT.PUSH); 
  		if (icon != null) {
  			maxDatePickerBtn.setImage(icon);
  		}
  		maxDatePickerBtn.addSelectionListener(new SelectionAdapter() {
  	            @Override
  	            public void widgetSelected(SelectionEvent e) {
  	            	newChangesExist = true;
  	            	
  	            	CalendarDialog calSelDlg = new CalendarDialog(
  	                		getShell());
  	                
  	            	Calendar selectedDT = null;
  	            	
  	            	Calendar maxCal = EnterEventUtil.textIsADate(maxDateText.getText(), dateFormat);
  	            	if (maxCal != null) {
  	            		selectedDT = calSelDlg.open(maxCal, defaultTZ);
  	            	} else {
  	            		selectedDT = calSelDlg.open();
  	            	}
  	            	
  	                if (selectedDT != null) {
  	                    maxDateText.setText(dateFormat.format(selectedDT.getTime()));
  	                } else {
  	                    return;
  	                }
  	            }

  	        });	
  		  		
  		//Max time qualifier
  		new Label(group, SWT.LEFT).setText("  Max Time Qualifier:");
        maxTimeQCombo = new Combo(group, SWT.DROP_DOWN | SWT.READ_ONLY);
        maxTimeQCombo.setItems(EnterEventUtil.getMaxTimeQualifierOptions(selectedEventType));
        maxTimeQCombo.select(0);
        
        maxTimeQCombo.addSelectionListener(new SelectionAdapter() {
	            @Override
	            public void widgetSelected(SelectionEvent e) {
	            	newChangesExist = true;
	            }

	        });	
        
        // End date and time
        displayAsterik(group); //indicate end date is required
    	new Label(group, SWT.LEFT).setText("End Date:");
        endDateText = new Text(group, SWT.BORDER);
    	endDateText.setLayoutData(new GridData(175, 18));
        //endDateText.setEnabled(false);
        endDateText.setText(dateFormat.format(Calendar.getInstance().getTime()));
        endDateText.addFocusListener(this.createFocusListener());
       
  		final Button endDatePickerBtn = new Button(group, SWT.PUSH); 
  		if (icon != null) {
  			endDatePickerBtn.setImage(icon);
  		}
  		endDatePickerBtn.addSelectionListener(new SelectionAdapter() {
  	            @Override
  	            public void widgetSelected(SelectionEvent e) {
  	            	newChangesExist = true;
  	            	
  	            	CalendarDialog calSelDlg = new CalendarDialog(
  	                		getShell());
  	                
  	            	Calendar selectedDT = null;
  	            	
  	            	Calendar endCal = EnterEventUtil.textIsADate(endDateText.getText(), dateFormat);
  	            	if (endCal != null) {
  	            		selectedDT = calSelDlg.open(endCal, defaultTZ);
  	            	} else {
  	            		selectedDT = calSelDlg.open();
  	            	}
  	            	
  	                if (selectedDT != null) {
  	                	endDateText.setText(dateFormat.format(selectedDT.getTime()));
  	                } else {
  	                    return;
  	                }
  	            }

  	        });	
  		  		
  		//End time qualifier
  		new Label(group, SWT.LEFT).setText("   End Time Qualifier:");
        endTimeQCombo = new Combo(group, SWT.DROP_DOWN | SWT.READ_ONLY);  
        endTimeQCombo.setItems(EnterEventUtil.getEndTimeQualifierOptions(selectedEventType));
        endTimeQCombo.select(0);   
        
        endTimeQCombo.addSelectionListener(new SelectionAdapter() {
	            @Override
	            public void widgetSelected(SelectionEvent e) {
	            	newChangesExist = true;
	            }

	        });	
        
    }    

    /**
     * Creates controls for other fields
     * 
     * @param group
     */
    private void createFieldControls(Group group) {
    	
    	Composite composite = new Composite(group, SWT.None);
        composite.setLayout(new GridLayout(3, false));
        composite.setLayoutData(new GridData(GridData.FILL, GridData.FILL, true, true));
    	
    	// Observatory
    	new Label(composite, SWT.LEFT).setText("Observatory:");
    	
    	if (this.selectedEventType.equals(EnterEventUtil.EVENT_TYPE.XRA.toString())
    			&& !this.isCompositeEvent) {
	    	obsCombo = new Combo(composite, SWT.DROP_DOWN | SWT.READ_ONLY);
	    	obsCombo.setItems(EnterEventUtil.getGOESObservatories());
	    	obsCombo.select(0); 
	    	
	    	obsCombo.addSelectionListener(new SelectionAdapter() {
  	            @Override
  	            public void widgetSelected(SelectionEvent e) {
  	            	newChangesExist = true;
  	            }

  	        });	
    	} else {
    		obsText = new Text(composite, SWT.BORDER);
        	obsText.setLayoutData(new GridData(100, 18));
    		obsText.addFocusListener(this.createFocusListener());
    	}
    	
    	new Label(composite, SWT.LEFT).setText(" ");
    	
    	// Quality of observation
    	new Label(composite, SWT.LEFT).setText("Quality of Observation:");
    	qualityCombo = new Combo(composite, SWT.DROP_DOWN | SWT.READ_ONLY);
    	qualityCombo.setItems(EnterEventUtil.getQualityOptions(selectedEventType));
    	qualityCombo.select(0);  
    	
    	qualityCombo.addSelectionListener(new SelectionAdapter() {
	            @Override
	            public void widgetSelected(SelectionEvent e) {
	            	newChangesExist = true;
	            }

	        });	
    	
    	new Label(composite, SWT.LEFT).setText(" ");
    	new Label(composite, SWT.LEFT).setText("Type of Report:");
    	new Label(composite, SWT.RIGHT).setText(this.selectedEventType);
    	new Label(composite, SWT.LEFT).setText(" ");
    	
    	// Coded Type
    	new Label(composite, SWT.LEFT).setText("Coded Type:");
    	codedTypeCombo = new Combo(composite, SWT.DROP_DOWN | SWT.READ_ONLY);
    	codedTypeCombo.setItems(EnterEventUtil.getCodedTypes(this.selectedEventType));
    	codedTypeCombo.select(0);
    	
    	codedTypeCombo.addSelectionListener(new SelectionAdapter() {
	            @Override
	            public void widgetSelected(SelectionEvent e) {
	            	newChangesExist = true;
	            	setControlValuesBasedOnCodedType();
	            }

	        });	
    	
    	new Label(composite, SWT.LEFT).setText(" ");
    	
    	// Location
    	new Label(composite, SWT.LEFT).setText("Location:");
    	locationText = new Text(composite, SWT.BORDER);
    	locationText.setLayoutData(new GridData(100, 18));
        locationText.addFocusListener(this.createFocusListener());
    	
    	new Label(composite, SWT.LEFT).setText(" ");
    	    
    	// Frequency
    	new Label(composite, SWT.LEFT).setText("Frequency:");
    	frequencyText = new Text(composite, SWT.BORDER);
    	frequencyText.setLayoutData(new GridData(100, 18));
        frequencyText.addFocusListener(this.createFocusListener());
    	
    	frequencyLabel = new Label(composite, SWT.LEFT);
    	
    	// Particulars1
    	new Label(composite, SWT.LEFT).setText("Particulars1:");
    	part1Text = new Text(composite, SWT.BORDER);
    	part1Text.setLayoutData(new GridData(100, 18));
        part1Text.addFocusListener(this.createFocusListener());
    	
    	part1Label = new Label(composite, SWT.LEFT);
    	//part1Label.setText("");
    	
    	// Particulars2
    	new Label(composite, SWT.LEFT).setText("Particulars2:");
    	part2Text = new Text(composite, SWT.BORDER);   
    	part2Text.setLayoutData(new GridData(100, 18));
        part2Text.addFocusListener(this.createFocusListener());
    	
    	Composite part2Comp = new Composite(composite, SWT.NONE);
    	part2Comp.setLayout(new GridLayout(2, false));
    	
    	part2Label = new Label(part2Comp, SWT.LEFT);
    	
    	this.integrateFluxButton = new Button (part2Comp, SWT.PUSH);
    	this.integrateFluxButton.setText("Integrate Flux");
    	this.integrateFluxButton.setEnabled(false);
    	integrateFluxButton.addSelectionListener(new SelectionAdapter() {
             @Override
             public void widgetSelected(SelectionEvent e) {

            	Calendar beginCal = EnterEventUtil.textIsADate(begDateText.getText(), dateFormat);
             	Calendar endCal = EnterEventUtil.textIsADate(endDateText.getText(), dateFormat);
             	String observatory = null;
             	if (!isCompositeEvent && selectedEventType.equals(EnterEventUtil.EVENT_TYPE.XRA.toString())) {
             		observatory = obsCombo.getItem(obsCombo.getSelectionIndex());
            	} 
             	
             	try {
             		XrayFlux fluxData = EnterEventUtil.getIntegratedFlux(observatory, beginCal, endCal);
             		if (fluxData != null) {
             			part1Text.setText(fluxData.getEventClass());
             			part2Text.setText(fluxData.getIntegratedFlux(beginCal, endCal).toString());
             		}
             	}
             	catch (VizException ve) {
             		EnterEventUtil.displayErrorMessageDialog(getShell(), "Error while calculating integrated flux."); 
             	} 	 
             	 
             }

        });
    	
    	// Particulars3
    	new Label(composite, SWT.LEFT).setText("Particulars3:");
    	part3Text = new Text(composite, SWT.BORDER);
    	part3Text.setLayoutData(new GridData(100, 18));
        part3Text.addFocusListener(this.createFocusListener());
    	
    	part3Label = new Label(composite, SWT.LEFT);
    	
    	// Particulars4
    	new Label(composite, SWT.LEFT).setText("Particulars4:");
    	part4Text = new Text(composite, SWT.BORDER);
    	part4Text.setLayoutData(new GridData(100, 18));
        part4Text.addFocusListener(this.createFocusListener());
    	
    	part4Label = new Label(composite, SWT.LEFT);
    	
    	// Particulars5
    	new Label(composite, SWT.LEFT).setText("Particulars5:");
    	part5Text = new Text(composite, SWT.BORDER);
    	part5Text.setLayoutData(new GridData(100, 18));
        part5Text.addFocusListener(this.createFocusListener());
    	
    	part5Label = new Label(composite, SWT.LEFT);
    	
    	// Empty columns
    	new Label(composite, SWT.LEFT).setText(" ");
    	new Label(composite, SWT.LEFT).setText(" ");
    	new Label(composite, SWT.LEFT).setText(" ");
    	
    	// Region
    	new Label(composite, SWT.LEFT).setText("Region:");
    	regionText = new Text(composite, SWT.BORDER);
    	regionText.setLayoutData(new GridData(100, 18));
        regionText.addFocusListener(this.createFocusListener());
    	
    	new Label(composite, SWT.LEFT).setText(" ");
    	
    	// Required fields message display
    	Label requiredMsg = new Label(composite, SWT.LEFT);
    	requiredMsg.setText("* Required Fields");
    	requiredMsg.setForeground(getShell().getDisplay().getSystemColor(SWT.COLOR_RED));
        
    }  
    
    private void displayAsterik(Composite composite) {
    	Label requiredMsg = new Label(composite, SWT.LEFT);
    	requiredMsg.setText("*");
    	requiredMsg.setForeground(getShell().getDisplay().getSystemColor(SWT.COLOR_RED));
    }
    
    /**
     * Create a FocusListener
     *
     * @return FocusListener
     */
    private FocusListener createFocusListener() {
    	
    	// add a focus listener
        return new FocusListener() {           

			@Override
			public void focusGained(FocusEvent e) {
				 newChangesExist = true;				
			}

			@Override
			public void focusLost(FocusEvent e) {
				
			}
        };
    	
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
        if (getShell() != null && !getShell().isDisposed()) {
            Rectangle bounds = getShell().getBounds();
            lastLocation = new Point(bounds.x, bounds.y);
            lastSize = getShell().getSize();
        }
        return super.close();
    }
    
	/**
	 * @return the selectedEventType
	 */
	public String getSelectedEventType() {
		return selectedEventType;
	}

	/**
	 * @param selectedEventType the selectedEventType to set
	 */
	public void setSelectedEventType(String selectedEventType) {
		this.selectedEventType = selectedEventType;
	}    
	
	/**
	 * Initialize form controls with default values based on the selected
	 * event type
	 */
	public void initilizeControlsWithDefaultValues() {		
		
		switch (EnterEventUtil.EVENT_TYPE.valueOf(this.selectedEventType)) {
			case XRA:
				this.qualityCombo.select(4);
				this.integrateFluxButton.setEnabled(true);
				this.frequencyLabel.setText("(Angstrom)");
				this.part1Label.setText("(Class e.g. M2.1)");
				this.part2Label.setText("(Integrated flux)");
				this.locationText.setEnabled(false);
				this.part3Text.setEnabled(false);
				this.part4Text.setEnabled(false);
				this.part5Text.setEnabled(false);
				break;
			case FLA:
				this.qualityCombo.select(2);
				this.part1Label.setText("(Importance/Brightness)");
				this.part2Label.setText("(Remark 1)");
				this.part3Label.setText("(Remark 2)");
				this.part4Label.setText("(Area)");
				this.part5Label.setText("(Intensity)");
				break;
			case BSL:
			case DSF:
			case EPL:
			case LPS:
			case SPY:
			case DSD:
				this.qualityCombo.select(2);
				this.part1Label.setText("(Extent)");
				this.part2Label.setText("(Blue Shift in Angstorm)");
				this.part3Label.setText("(Red Shift in Angstorm)");
				break;
			case RNS:
				this.qualityCombo.select(0);
				this.frequencyLabel.setText("MHz");
				this.part1Label.setText("(Peak Flux)");
				break;
			case RBR:
				this.qualityCombo.select(0);
				this.frequencyLabel.setText("MHz");
				this.part1Label.setText("(Peak Flux)");
				this.part2Label.setText("(Castelli U)");
				this.part3Label.setText("(Integrated Flux Start to End)");
				this.part4Label.setText("(Mean Flux)");
				break;
			case RSP:
				this.qualityCombo.select(0);
				//this.frequencyText.setText("30-80");
				this.frequencyLabel.setText("MHz");
				this.part1Label.setText("(Type/Importance)");
				break;
			case PRO:
				this.qualityCombo.select(4);
				this.part1Label.setText("(Peak Flux)");
				this.part2Label.setText("(Integrated Flux)");
				break;
			case CME:
				this.obsText.setText("SOH");
				this.frequencyLabel.setText("(Wavelength)");
				this.part1Label.setText("(1st Pos-2nd Pos/Dir e.g., 000-360/FS)");
				this.part2Label.setText("(Velocity)");
				this.part3Label.setText("(Event Type e.g., FLA)");
				this.part4Label.setText("(Event Start/End Time hhmm/hhmm)");
				this.part5Label.setText("(Wave && Dimming e.g., WAV/DIM, WAV, DIMx)");
				break;
		    default:
		    	break;
		}
				

		if (this.isCompositeEvent) {
			obsText.setText(EnterEventUtil.COMPOSITE_EVENT);
			obsText.setEnabled(false);
		}
		
		setControlValuesBasedOnCodedType();
				
	}
	
	/**
	 * Initialize form controls with values based on newEvent
	 */
	public void initializeValuesForControls() {	
		
		if (newEvent.getBeginDate() != null) {
			this.begDateText.setText(dateFormat.format(newEvent.getBeginDate().getTime()));
		}
		
		if (newEvent.getMaxDate() != null) {
			this.maxDateText.setText(dateFormat.format(newEvent.getMaxDate().getTime()));
		}
		
		if (newEvent.getEndDate() != null) {
			this.endDateText.setText(dateFormat.format(newEvent.getEndDate().getTime()));
		}
		
		this.begTimeQCombo.setText((newEvent.getBegInq() != null) ? newEvent.getBegInq() : "");
		this.maxTimeQCombo.setText((newEvent.getMaxq() != null) ? newEvent.getMaxq() : "");
		this.endTimeQCombo.setText((newEvent.getEndq() != null) ? newEvent.getEndq() : "");
		this.codedTypeCombo.setText((newEvent.getCodedType() != null) ? String.valueOf(newEvent.getCodedType()) : "");
		this.qualityCombo.setText((newEvent.getQuality() != null) ? newEvent.getQuality() : "");
		this.obsText.setText((newEvent.getObservatory() != null) ? newEvent.getObservatory() : "");
		this.frequencyText.setText((newEvent.getFrequency() != null) ? newEvent.getFrequency() : "");
		this.locationText.setText((newEvent.getLocation() != null) ? newEvent.getLocation() : "");
		this.regionText.setText((newEvent.getRegion() != null) ? String.valueOf(newEvent.getRegion()) : "");
		this.part1Text.setText((newEvent.getParticulars1() != null) ? newEvent.getParticulars1() : "");
		this.part2Text.setText((newEvent.getParticulars2() != null) ? newEvent.getParticulars2() : "");
		this.part3Text.setText((newEvent.getParticulars3() != null) ? newEvent.getParticulars3() : "");
		this.part4Text.setText((newEvent.getParticulars4() != null) ? newEvent.getParticulars4() : "");
		this.part5Text.setText((newEvent.getParticulars5() != null) ? newEvent.getParticulars5() : "");
		
		if (isCompositeEvent) {
			this.integrateFluxButton.setEnabled(false);
		}
	}
	
	/**
	 * Set the appropriate control values based on the selected codedType value
	 */
	public void setControlValuesBasedOnCodedType() {		
		
		switch (EnterEventUtil.EVENT_TYPE.valueOf(this.selectedEventType)) {
			case XRA:
			case FLA:
			case BSL:
			case DSF:
			case EPL:
			case LPS:
			case SPY:
			case DSD:
			case RNS:
			case RBR:
			case RSP://set particulars 
				int codedType = Integer.parseInt(codedTypeCombo.getItem(codedTypeCombo.getSelectionIndex()));
				
				switch(codedType) {
				case 50:
					part1Text.setText("II/");
					break;
				case 51:
					part1Text.setText("III/");
					break;
				case 52:
					part1Text.setText("IV/");
					break;
				case 53:
					part1Text.setText("V/");
					break;
				case 54:
					part1Text.setText("CTM/");
					break;
				default:
					break;
				}
			case PRO:
			case CME:            	
            	String frequency = EnterEventUtil.getFrequency(selectedEventType, Integer.parseInt(codedTypeCombo.getItem(codedTypeCombo.getSelectionIndex())));
            	frequencyText.setText(frequency);
            	break;
		    default:
		    	break;
		}				
	}

	/**
	 * @return isCompositeEvent
	 */
	public boolean isCompositeEvent() {
		return isCompositeEvent;
	}

	/**
	 * @param isCompositeEvent
	 */
	public void setCompositeEvent(boolean isComposite) {
		this.isCompositeEvent = isComposite;
	}
	
	/**
	 * @return the newEventId
	 */
	public Integer getNewEventId() {
		return newEventId;
	}

	/**
	 * @param newEventId the newEventId to set
	 */
	public void setNewEventId(Integer newEventId) {
		this.newEventId = newEventId;
	}

	/**
	 * @return the newEvent
	 */
	public gov.noaa.nws.ncep.common.dataplugin.editedevents.Event getNewEvent() {
		return newEvent;
	}

	/**
	 * @param event the newEvent to set
	 */
	public void setNewEvent(
			gov.noaa.nws.ncep.common.dataplugin.editedevents.Event event) {

		this.newEventId = null;
			
		if (event != null) {
	    	
			this.newEvent = new gov.noaa.nws.ncep.common.dataplugin.editedevents.Event();
			this.newEvent.setDataTime(event.getDataTime());
			this.newEvent.setBeginDate(event.getBeginDate());
			this.newEvent.setBeginTime(event.getBeginTime());   	    	
	    	this.newEvent.setMaxDate(event.getMaxDate());
	    	this.newEvent.setMaxTime(event.getMaxTime());  	    		
	    	this.newEvent.setEndDate(event.getEndDate());
	    	this.newEvent.setEndTime(event.getEndTime());
	    	
	    	this.newEvent.setBegInq(event.getBegInq());
	    	this.newEvent.setMaxq(event.getMaxq());  
	    	this.newEvent.setEndq(event.getEndq());

	    	if (this.isCompositeEvent) {
	    		this.newEvent.setObservatory(EnterEventUtil.COMPOSITE_EVENT);
	    	} else {
	    		this.newEvent.setObservatory(event.getObservatory());
	    	}
	    	
	    	this.newEvent.setQuality(event.getQuality());
	    	this.newEvent.setType(event.getType());				
	    	this.newEvent.setCodedType(event.getCodedType());
	    	this.newEvent.setLocation(event.getLocation());					
	    	this.newEvent.setFrequency(event.getFrequency());
			
	    	this.newEvent.setParticulars1(event.getParticulars1());
	    	this.newEvent.setParticulars2(event.getParticulars2());
	    	this.newEvent.setParticulars3(event.getParticulars3());
	    	this.newEvent.setParticulars4(event.getParticulars4());
	    	this.newEvent.setParticulars5(event.getParticulars5());
	    	this.newEvent.setParticulars6(event.getParticulars6());
	    	this.newEvent.setParticulars7(event.getParticulars7());
	    	this.newEvent.setParticulars8(event.getParticulars8());
	    	this.newEvent.setParticulars9(event.getParticulars9());			
	    	this.newEvent.setParticulars10(event.getParticulars10());
	
	    	this.newEvent.setRegion(event.getRegion());    	
	    	this.newEvent.setBin(event.getBin());
	    	this.newEvent.setPutdmsd(event.getPutdmsd());
	    	this.newEvent.setPutdmss(event.getPutdmss());
	    	
	    	this.newEvent.setAge(event.getAge());
	    	this.newEvent.setChangeFlag(event.getChangeFlag());	
	    	this.newEvent.setObsid(event.getObsid());
	    	this.newEvent.setStatusCd(event.getStatusCd());
	    	this.newEvent.setStatusText(event.getStatusText());
		}
		
	}
	
	 /**
     * EnterEventUtil.validate the user entries.
     * 
     * @return True if the entries are valid, false otherwise.
     */
    private boolean validateEntries() {
    	
    	if (newEvent == null) {
    		newEvent = new gov.noaa.nws.ncep.common.dataplugin.editedevents.Event();
    	}

    	Calendar beginCal = EnterEventUtil.textIsADate(begDateText.getText(), dateFormat);
    	Calendar maxCal = (maxDateText.getText().isEmpty()) ? null: EnterEventUtil.textIsADate(maxDateText.getText(), dateFormat);
    	Calendar endCal = EnterEventUtil.textIsADate(endDateText.getText(), dateFormat);
    	
    	if (!EnterEventUtil.validateDateTime(this.getShell(), beginCal, maxCal, endCal)) {
    		return false;
    	}
    	
    	newEvent.setDataTime(new DataTime(beginCal.getTime()));
    	newEvent.setBeginDate(beginCal);
    	newEvent.setBeginTime(EditEventsUtil.getHourAndMinuteCombination(beginCal));   	    	
    		
    	if(maxCal != null) {
    		newEvent.setMaxDate(maxCal);
        	newEvent.setMaxTime(EditEventsUtil.getHourAndMinuteCombination(maxCal));  	
    	}		
    	
		newEvent.setEndDate(endCal);
    	newEvent.setEndTime(EditEventsUtil.getHourAndMinuteCombination(endCal));
    	
    	newEvent.setBegInq(begTimeQCombo.getItem(begTimeQCombo.getSelectionIndex()));
    	newEvent.setMaxq(maxTimeQCombo.getItem(maxTimeQCombo.getSelectionIndex()));  
    	newEvent.setEndq(endTimeQCombo.getItem(endTimeQCombo.getSelectionIndex()));
    			
    	if (!this.isCompositeEvent && this.selectedEventType.equals(EnterEventUtil.EVENT_TYPE.XRA.toString())) {
    		newEvent.setObservatory(obsCombo.getItem(obsCombo.getSelectionIndex()));
    	} else {
    		newEvent.setObservatory(obsText.getText().toUpperCase());
    	}
		newEvent.setQuality(qualityCombo.getItem(qualityCombo.getSelectionIndex()));
		newEvent.setType(this.selectedEventType);	
		
		newEvent.setCodedType(Integer.parseInt(codedTypeCombo.getItem(codedTypeCombo.getSelectionIndex())));
		
		if (!EnterEventUtil.validateLocation(this.getShell(), locationText.getText())) {
			return false;
		}		
		newEvent.setLocation(locationText.getText().toUpperCase());
				
		if (!EnterEventUtil.validateFrequency(this.getShell(), frequencyText.getText(), codedTypeCombo.getItem(codedTypeCombo.getSelectionIndex()))) {
			return false;
		}
		newEvent.setFrequency(frequencyText.getText().toUpperCase());
		
		if (!EnterEventUtil.validateParticulars1(this.getShell(), part1Text.getText().toUpperCase(), selectedEventType)) {
			return false;
		}		
		newEvent.setParticulars1(part1Text.getText().toUpperCase());
		
		HashMap<String, String> validatePart2Results = EnterEventUtil.validateParticulars2(this.getShell(), part2Text.getText().toUpperCase(), selectedEventType);
		String part2Valid = validatePart2Results.get("isValid");
		String part2Value = validatePart2Results.get("Particulars2");	
		
		if (!Boolean.parseBoolean(part2Valid)) {
			return false;
		}		
		newEvent.setParticulars2(part2Value.toUpperCase());
		
		HashMap<String, String> validatePart3Results = EnterEventUtil.validateParticulars3(this.getShell(), part3Text.getText().toUpperCase(), selectedEventType);
		String part3Valid = validatePart3Results.get("isValid");
		String part3Value = validatePart3Results.get("Particulars3");	
		
		if (!Boolean.parseBoolean(part3Valid)) {
			return false;
		}		
		newEvent.setParticulars3(part3Value.toUpperCase());
		
		if (!part4Text.getText().isEmpty()) {
			newEvent.setParticulars4(part4Text.getText().toUpperCase());
		}
		
		if (!part4Text.getText().isEmpty()) {
			newEvent.setParticulars5(part5Text.getText().toUpperCase());
		}		
		
		newEvent.setParticulars6(null);
		newEvent.setParticulars7(null);
		newEvent.setParticulars8(null);
		newEvent.setParticulars9(null);
		
		newEvent.setParticulars10(EnterEventUtil.deriveParticulars10(part1Text.getText().toUpperCase(), this.selectedEventType));

		if (!regionText.getText().isEmpty()) {
	    	Integer region = EnterEventUtil.textIsAnInteger(regionText.getText());
	    	
	    	if (!EnterEventUtil.validateRegion(this.getShell(), region)) {
	    		return false;
	    	}	
    		newEvent.setRegion(region);    	
		} else {
			newEvent.setRegion(EditedEventsConstants.MISSING_REGION_VAL);
		}    	
    	    	
    	if (!this.newEvent.isComposite()) {
    		newEvent.setAge(EditedEventsConstants.EVENT_AGE.NEW.toString());    		
    	} 
    	
		newEvent.setChangeFlag(0);	
		newEvent.setObsid(0);
		newEvent.setStatusCd(0);
		
        return true;
    }
    
  
}
