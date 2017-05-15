package gov.noaa.nws.ncep.viz.ui.editedregions.dialog;

import java.util.Calendar;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

import com.raytheon.uf.common.status.IUFStatusHandler;
import com.raytheon.uf.common.status.UFStatus;
import com.raytheon.uf.common.status.UFStatus.Priority;
import com.raytheon.uf.common.time.DataTime;

import gov.noaa.nws.ncep.common.dataplugin.editedregions.RegionReport;
import gov.noaa.nws.ncep.common.dataplugin.editedregions.exception.EditedRegionsException;
import gov.noaa.nws.ncep.common.dataplugin.editedregions.util.EditedRegionsConstants;
import gov.noaa.nws.ncep.viz.ui.editedregions.util.EditRegionsServerUtil;

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

    private final IUFStatusHandler statusHandler = UFStatus
            .getHandler(EnterRegionReportDialog.class);

    protected EnterRegionReportDialog(Shell parent) {
        super(parent);
    }

    @Override
    protected Control createDialogArea(Composite parent) {
        Composite top = (Composite) super.createDialogArea(parent);

        GridLayout layout = new GridLayout(1, true);
        layout.marginHeight = 1;
        layout.marginWidth = 1;
        top.setLayout(layout);

        this.initializeComponents(parent);

        this.getShell().setSize(750, 750);

        return top;
    }

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
     * Verify that the data provided is valid.
     * 
     * @return
     */
    private boolean validateData() {
        // TODO: Add implementation
        return true;
    }

    private static String getSelection(Combo combo) {
        int index = combo.getSelectionIndex();
        if (index >= 0) {
            return combo.getItem(index);
        }
        return null;
    }

    /**
     * Build out the RegionReport object from the entered data.
     * 
     * @return
     */
    private RegionReport buildRegionReport() {
        Calendar calendar = Calendar
                .getInstance(EditedRegionsConstants.TIME_ZONE_UTC);
        RegionReport report = new RegionReport();
        report.setDataTime(new DataTime(calendar));
        report.setPersistenceTime(calendar.getTime());

        report.setObservatory(getSelection(cmbObservatory));
        report.setType(txtType.getText());
        report.setQuality(Integer.parseInt(getSelection(cmbQuality)));
        report.setRegion(Integer.parseInt(txtRegion.getText()));
        report.setLatitude(Integer.parseInt(txtLatitude.getText()));
        report.setReportLongitude(
                Integer.parseInt(txtReportLongitude.getText()));
        report.setLongitude(Integer.parseInt(txtLongitude.getText()));
        report.setReportLocation(txtReportLocation.getText());
        report.setLocation(txtLocation.getText());
        report.setCarlon(Integer.parseInt(txtCarlon.getText()));
        report.setExtent(Integer.parseInt(txtExtent.getText()));
        report.setArea(Integer.parseInt(txtArea.getText()));
        report.setNumspots(Integer.parseInt(txtNumspots.getText()));
        report.setZurich(Integer.parseInt(getSelection(cmbZurich)));
        report.setPenumbra(Integer.parseInt(getSelection(cmbPenumbra)));
        report.setCompact(getSelection(cmbCompact));
        report.setSpotclass(txtSpotClass.getText());
        report.setMagcode(Integer.parseInt(getSelection(cmbMagcode)));
        report.setMagclass(getSelection(cmbMagclass));
        report.setObsid(Integer.parseInt(getSelection(cmbObsid)));
        report.setReportStatus(Integer.parseInt(getSelection(cmbReportStatus)));
        report.setValidSpotClass(!"0".equals(getSelection(cmbValidSpotClass)));

        return report;
    }

    @Override
    protected void okPressed() {
        try {
            if (validateData()) {
                RegionReport report = buildRegionReport();
                EditRegionsServerUtil.saveNewRegionReport(report);

                MessageBox mb = new MessageBox(this.getShell(),
                        SWT.ICON_INFORMATION ^ SWT.OK);
                mb.setText("Saved");
                mb.setMessage("Your region report data has been saved!");
                mb.open();
            } else {
                MessageBox mb = new MessageBox(this.getShell(),
                        SWT.ICON_ERROR ^ SWT.OK);
                mb.setText("Please correct errors.");
                mb.setMessage(
                        "Your data could not be validated. Please re-enter data and try again.");
                mb.open();
                return;
            }
        } catch (EditedRegionsException ex) {
            statusHandler.handle(Priority.ERROR,
                    "Unable to save new region report.", ex);
        }
        super.okPressed();
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
    private Combo addComboControl(Composite parent, String label,
            String[] items) {
        new Label(parent, SWT.LEFT).setText(label);
        Combo combo = new Combo(parent, SWT.DROP_DOWN ^ SWT.READ_ONLY);
        combo.setItems(items);
        combo.select(0);
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
        cmbObservatory = addComboControl(composite, "Observatory",
                new String[] { "APLM", "KHMN", "LISS" });

        // Type field
        txtType = addTextControl(composite, "Type");

        // Quality field
        cmbQuality = addComboControl(composite, "Quality",
                new String[] { "4", "5" });

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
        cmbZurich = addComboControl(composite, "Zurich",
                new String[] { "1", "2", "3", "4", "5", "6", "7" });

        // Penumbra field
        cmbPenumbra = addComboControl(composite, "Penumbra",
                new String[] { "0", "1", "2", "3", "4", "5" });

        // Compact field
        cmbCompact = addComboControl(composite, "Compact",
                new String[] { "/", "7", "8", "9" });

        // Spotclass field
        txtSpotClass = addTextControl(composite, "Spot class");

        // Magcode field
        cmbMagcode = addComboControl(composite, "Magcode",
                new String[] { "1", "2", "3", "4", "5", "6", "7" });

        // Magclass field
        cmbMagclass = addComboControl(composite, "Magclass",
                new String[] { "A", "B", "BG", "G", "BD", "BDG", "GD" });

        // Obsid field
        cmbObsid = addComboControl(composite, "Obsid",
                new String[] { "1", "2", "3", "4" });

        // Report Status field
        cmbReportStatus = addComboControl(composite, "Report Status",
                new String[] { "1", "2", "3", "4" });

        // ValidSpotClass field
        cmbValidSpotClass = addComboControl(composite, "Valid Spot Class",
                new String[] { "0", "1" });

    }

    private void initializeControlsWithDefaultValues() {
        // TODO: add implementation
    }
}
