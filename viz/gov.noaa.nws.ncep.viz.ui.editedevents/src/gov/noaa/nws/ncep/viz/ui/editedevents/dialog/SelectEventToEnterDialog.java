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

import gov.noaa.nws.ncep.viz.ui.editedevents.util.EnterEventUtil;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.window.Window;
import org.eclipse.swt.widgets.Shell;

import com.raytheon.uf.common.status.IUFStatusHandler;
import com.raytheon.uf.common.status.UFStatus;

/**
 * This dialog provides an interface allowing a user to select
 * an event type to enter. Once the user makes a selection and clicks
 * on the OK button, EnterEventDialog will be displayed.
 * 
 * <pre>
 *
 * SOFTWARE HISTORY
 *
 * Date         Ticket#    Engineer    Description
 * ------------ ---------- ----------- --------------------------
 * Jan 26, 2016 R9583      sgurung     Initial creation
 *
 * </pre>
 *
 * @author sgurung
 * @version 1.0	
 */
public class SelectEventToEnterDialog extends Dialog {

	private final IUFStatusHandler statusHandler = UFStatus
            .getHandler(EditEventsDialog.class);
	
	private String ENTER_EVENT_LABEL = "Enter Event";
	
	private String CLOSE_LABEL = "Close";
    
    private Combo reportTypeCombo = null;
    
    private Integer newEventId = null;
	
	public SelectEventToEnterDialog(Shell parent) {
		 super(parent);
	     setShellStyle(SWT.TITLE | SWT.CLOSE | SWT.MAX | SWT.MIN | SWT.RESIZE
	                | SWT.MODELESS);
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

        top.setSize(350, 200);

        return top;

    }
    
    /**
     * Creates buttons, menus, and other controls in the dialog area
     * 
     */
    private void initializeComponents(Composite parent) {

        this.getShell().setText("Select Event Type");    
      
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
        GridLayout groupLayout = new GridLayout(2, false);
        topGroup.setLayout(groupLayout);
        topGroup.setLayoutData(new GridData(SWT.FILL, SWT.TOP, true, true));
        
        this.createControls(topGroup);     

    }
    
    /**
     * Creates controls for other fields
     * 
     * @param group
     */
    private void createControls(Group group) {
    	
    	Composite composite = new Composite(group, SWT.None);
        composite.setLayout(new GridLayout(2, false));   
    	
    	// Type of report
    	new Label(composite, SWT.LEFT).setText("Select Event Type:");
    	reportTypeCombo = new Combo(composite, SWT.DROP_DOWN | SWT.READ_ONLY);
    	reportTypeCombo.setItems(EnterEventUtil.getEventTypes());
    	reportTypeCombo.select(0);  
    	
    	new Label(composite, SWT.LEFT).setText(" ");
    }
    
    
    /* (non-Javadoc)
     * @see org.eclipse.jface.dialogs.Dialog#createButtonsForButtonBar(org.eclipse.swt.widgets.Composite)
     * The labels for the buttons are changed to "Save" and "Exit"
     */
    @Override
    protected void createButtonsForButtonBar(Composite parent) {
		// create "Enter Event" and "Close" buttons 
		createButton(parent, IDialogConstants.OK_ID, this.ENTER_EVENT_LABEL,
				true);
		createButton(parent, IDialogConstants.CANCEL_ID,
				this.CLOSE_LABEL,  false);
	}
    
    /**
	 * Open the EnterEventDialog
	 *
	 */
    @Override
	protected void okPressed() {
		
    	EnterEventDialog enterEventDlg = new EnterEventDialog(getShell());
    	enterEventDlg.setSelectedEventType(reportTypeCombo.getText());
    	if (Window.OK == enterEventDlg.open()) {
    		newEventId = enterEventDlg.getNewEventId();
    	}
    	
    	this.close();

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

}
