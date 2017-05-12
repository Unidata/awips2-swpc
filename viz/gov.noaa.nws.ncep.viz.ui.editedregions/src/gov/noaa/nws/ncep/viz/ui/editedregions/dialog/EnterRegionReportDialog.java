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

    private Text txtSpotclass;

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

    private void createFieldControls(Group group) {

        Composite composite = new Composite(group, SWT.None);
        composite.setLayout(new GridLayout(3, false));
        composite.setLayoutData(
                new GridData(GridData.FILL, GridData.FILL, true, true));

        // Observatory field
        new Label(composite, SWT.LEFT).setText("Observatory: ");
        cmbObservatory = new Combo(composite, SWT.DROP_DOWN ^ SWT.READ_ONLY);

        new Label(composite, SWT.LEFT).setText(" ");

        // Type field
        new Label(composite, SWT.LEFT).setText("Type: ");
        txtType = new Text(composite, SWT.BORDER);

        new Label(composite, SWT.LEFT).setText(" ");
    }

    private void initializeControlsWithDefaultValues() {
        // TODO: add implementation
    }
}
