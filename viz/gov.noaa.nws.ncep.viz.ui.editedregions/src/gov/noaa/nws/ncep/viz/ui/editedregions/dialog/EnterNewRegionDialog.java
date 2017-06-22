/**
 * This code has unlimited rights, and is provided "as is" by the National Centers 
 * for Environmental Prediction, without warranty of any kind, either expressed or implied, 
 * including but not limited to the implied warranties of merchantability and/or fitness 
 * for a particular purpose.
 * 
 * This code has been developed by the NCEP-SIB for use in the AWIPS2 system.
 * 
 **/
package gov.noaa.nws.ncep.viz.ui.editedregions.dialog;

import java.util.regex.Pattern;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

import com.raytheon.uf.common.status.IUFStatusHandler;
import com.raytheon.uf.common.status.UFStatus;

import gov.noaa.nws.ncep.common.dataplugin.editedregions.exception.EditedRegionsException;
import gov.noaa.nws.ncep.viz.ui.editedregions.util.EditRegionsServerUtil;

/**
 * This dialog provides an interface allowing a user to select an event type to
 * enter. Once the user makes a selection and clicks on the OK button,
 * EnterEventDialog will be displayed.
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
public class EnterNewRegionDialog extends Dialog {

    private static final Pattern PATTERN_INTEGER = Pattern.compile("^\\d+$");

    private final IUFStatusHandler statusHandler = UFStatus
            .getHandler(EnterNewRegionDialog.class);

    private Text txtRegion = null;

    private Integer newRegionId = null;

    public EnterNewRegionDialog(Shell parent) {
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

        this.getShell().setText("New Region Dialog.");

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

        new Label(composite, SWT.LEFT).setText("Enter new region id: ");
        txtRegion = new Text(composite, SWT.BORDER);

    }

    /**
     * Open the EnterNewRegionDialog
     *
     */
    @Override
    protected void okPressed() {
        try {
            String region = txtRegion.getText();
            if (PATTERN_INTEGER.matcher(region).matches()) {
                // Validation passes. Create the new Region.
                Integer regionId = Integer.parseInt(region);
                EditRegionsServerUtil.createRegion(regionId);
            } else {
                // Validation failed. Get the user to correct errors.
                MessageBox errorDlg = new MessageBox(this.getShell(),
                        SWT.OK | SWT.ICON_ERROR);
                errorDlg.setText("Please correct errors.");
                errorDlg.setMessage("A valid integer is required.");
                errorDlg.open();
                return;
            }

            super.okPressed();
        } catch (EditedRegionsException ex) {
            statusHandler.error("Error creating new region.", ex);
        }

    }

    /**
     * @return the newEventId
     */
    public Integer getNewRegionid() {
        return this.newRegionId;
    }

    /**
     * @param newEventId
     *            the newEventId to set
     */
    public void setNewRegionId(Integer newRegionId) {
        this.newRegionId = newRegionId;
    }

}
