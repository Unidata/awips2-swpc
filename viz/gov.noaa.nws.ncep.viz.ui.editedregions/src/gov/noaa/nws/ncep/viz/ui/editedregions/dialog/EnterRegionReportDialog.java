package gov.noaa.nws.ncep.viz.ui.editedregions.dialog;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

import com.raytheon.uf.common.status.IUFStatusHandler;
import com.raytheon.uf.common.status.UFStatus;

public class EnterRegionReportDialog extends Dialog {

    // Input fields
    private Combo cmbObservatory;

    private Text txtType;

    private Combo cmbQuality;

    private Text txtRegion;

    private Text txtLatitude;

    private Text txtReportLongitude;

    private Text txtLongitude;

    private Text txtReportLocation;

    private Text txtLocation;

    private Text txtCarlon;

    private Text txtExtent;

    private Text txtArea;

    private Text txtNumspots;

    private Combo cmbZurich;

    private Combo cmbPenumbra;

    private Combo cmbCompact;

    private Text txtSpotClass;

    private Combo cmbMagcode;

    private Combo cmbMagclass;

    private Combo cmbObsid;

    private Combo cmbReportStatus;

    private Combo cmbValidSpotClass;

    @SuppressWarnings("unused")
    private final IUFStatusHandler statusHandler = UFStatus
            .getHandler(EnterRegionReportDialog.class);

    protected EnterRegionReportDialog(Shell parent) {
        super(parent);
    }

    protected Control createDialogArea(Composite parent) {
        Composite top = (Composite) super.createDialogArea(parent);
        this.initializeComponents(parent);
        return top;
    }

    private void initializeComponents(Composite parent) {
        getShell().setText("Enter new region report.");

        Composite composite = new Composite(parent, SWT.None);
        composite.setLayout(new GridLayout(1, false));

        GridData gd = new GridData();
        gd = new GridData();
        gd.grabExcessHorizontalSpace = true;
        gd.grabExcessVerticalSpace = true;
        gd.horizontalAlignment = GridData.FILL;
        gd.verticalAlignment = GridData.FILL;

        composite.setLayoutData(gd);
        GridLayout groupLayout = new GridLayout(6, false);

        Group bottomGroup = new Group(composite, SWT.None);
        groupLayout = new GridLayout(1, false);
        bottomGroup.setLayout(groupLayout);
        bottomGroup.setLayoutData(new GridData(SWT.FILL, SWT.TOP, true, true));

        this.createFieldControls(bottomGroup);

        this.initializeControlsWithDefaultValues();

    }

    /**
     * Adds a new row with a text entry widget and the provided label.
     * 
     * @param parent
     * @param label
     * @return the Text control
     * 
     */
    private Text addTextControl(Composite parent, String label) {
        new Label(parent, SWT.LEFT).setText(label);
        Text text = new Text(parent, SWT.BORDER);
        new Label(parent, SWT.LEFT).setText(" ");
        return text;
    }

    /**
     * Adds a new row with a combo selection widget and the provided label.
     * 
     * @param parent
     * @param label
     * @return the Combo control
     */
    private Combo addComboControl(Composite parent, String label) {
        new Label(parent, SWT.LEFT).setText(label);
        Combo combo = new Combo(parent, SWT.DROP_DOWN ^ SWT.READ_ONLY);
        new Label(parent, SWT.LEFT).setText(" ");
        return combo;
    }

    /**
     * Add the controls for the region report fields onto the GUI.
     * 
     * @param group
     *            The parent group
     */
    private void createFieldControls(Group group) {

        Composite composite = new Composite(group, SWT.None);
        composite.setLayout(new GridLayout(3, false));
        composite.setLayoutData(
                new GridData(GridData.FILL, GridData.FILL, true, true));

        new Label(composite, SWT.LEFT).setText(" ");

        // Observatory field
        cmbObservatory = addComboControl(composite, "Observatory");

        // Type field
        txtType = addTextControl(composite, "Type");

        // Quality field
        cmbQuality = addComboControl(composite, "Quality");

        // Region field
        txtRegion = addTextControl(composite, "Region");

        // Latitude field
        txtLatitude = addTextControl(composite, "Latitude");

        // Report Longitude field
        txtReportLongitude = addTextControl(composite, "Report Longitude");

        // Longitude field
        txtLongitude = addTextControl(composite, "Longitude");

        // Report Location field
        txtReportLocation = addTextControl(composite, "Report Location");

        // Location field
        txtLocation = addTextControl(composite, "Location");

        // Carlon field
        txtCarlon = addTextControl(composite, "Carlon");

        // Extent field
        txtExtent = addTextControl(composite, "Extent");

        // Area field
        txtArea = addTextControl(composite, "Area");

        // Num spots field
        txtNumspots = addTextControl(composite, "Num spots");

        // Zurich field
        cmbZurich = addComboControl(composite, "Zurich");

        // Penumbra field
        cmbPenumbra = addComboControl(composite, "Penumbra");

        // Compact field
        cmbCompact = addComboControl(composite, "Compact");

        // Spotclass field
        txtSpotClass = addTextControl(composite, "Spot class");

        // Magcode field
        cmbMagcode = addComboControl(composite, "Magcode");

        // Magclass field
        cmbMagclass = addComboControl(composite, "Magclass");

        // Obsid field
        cmbObsid = addComboControl(composite, "Obsid");

        // Report Status field
        cmbReportStatus = addComboControl(composite, "Report Status");

        // ValidSpotClass field
        cmbValidSpotClass = addComboControl(composite, "Valid Spot Class");

    }

    private void initializeControlsWithDefaultValues() {
        // TODO: add implementation
    }
}
