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
import gov.noaa.nws.ncep.viz.ui.editedregions.util.EditRegionsValidationUtil;

public class EnterRegionReportDialog extends Dialog {

    // Input fields

    private Combo cmbStation;

    private Label lblStation;

    private Combo cmbObservatory;

    private Label lblObservatory;

    private Text txtType;

    private Label lblType;

    private Combo cmbQuality;

    private Label lblQuality;

    private Text txtRegion;

    private Label lblRegion;

    private Text txtLatitude;

    private Label lblLatitude;

    private Text txtReportLongitude;

    private Label lblReportLongitude;

    private Text txtLongitude;

    private Label lblLongitude;

    private Text txtReportLocation;

    private Label lblReportLocation;

    private Text txtLocation;

    private Label lblLocation;

    private Text txtCarlon;

    private Label lblCarlon;

    private Text txtExtent;

    private Label lblExtent;

    private Text txtArea;

    private Label lblArea;

    private Text txtNumspots;

    private Label lblNumspots;

    private Combo cmbZurich;

    private Label lblZurich;

    private Combo cmbPenumbra;

    private Label lblPenumbra;

    private Combo cmbCompact;

    private Label lblCompact;

    private Text txtSpotClass;

    private Label lblSpotClass;

    private Combo cmbMagcode;

    private Label lblMagcode;

    private Combo cmbMagclass;

    private Label lblMagclass;

    private Combo cmbObsid;

    private Label lblObsid;

    private Combo cmbReportStatus;

    private Label lblReportStatus;

    private Combo cmbValidSpotClass;

    private Label lblValidSpotClass;

    private final IUFStatusHandler statusHandler = UFStatus
            .getHandler(EnterRegionReportDialog.class);

    private Integer reportId = null;

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
     * Select the item on the Combo control that matches the given item. If no
     * items match, this function does nothing.
     * 
     * @param combo
     * @param item
     */
    private static final void selectItem(Combo combo, Object value) {
        String valueStr = convertToString(value);
        for (int index = 0; index < combo.getItemCount(); index++) {
            String item = combo.getItem(index);
            if (valueStr.equals(item)) {
                combo.select(index);
            }
        }
    }

    /**
     * 
     * @param value
     * @return value.toString() is the value is not null, empty string
     *         otherwise.
     */
    private static final String convertToString(Object obj) {
        return (obj != null) ? obj.toString() : "";
    }

    /**
     * Populate the dialog with data from an existing RegionReport object. Used
     * when updating reports.
     * 
     * @param report
     */
    public void populateData(RegionReport report) {
        selectItem(cmbStation, report.getStation());
        selectItem(cmbObservatory, report.getObservatory());
        txtType.setText(report.getType());
        selectItem(cmbQuality, report.getQuality());
        txtRegion.setText(convertToString(report.getRegion()));
        txtLatitude.setText(convertToString(report.getLatitude()));
        txtReportLongitude
                .setText(convertToString(report.getReportLongitude()));
        txtLongitude.setText(convertToString(report.getLongitude()));
        txtReportLocation.setText(convertToString(report.getReportLocation()));
        txtLocation.setText(convertToString(report.getLocation()));
        txtCarlon.setText(convertToString(report.getCarlon()));
        txtExtent.setText(convertToString(report.getExtent()));
        txtArea.setText(convertToString(report.getArea()));
        txtNumspots.setText(convertToString(report.getNumspots()));
        selectItem(cmbZurich, report.getZurich());
        selectItem(cmbPenumbra, report.getPenumbra());
        selectItem(cmbCompact, report.getCompact());
        txtSpotClass.setText(convertToString(report.getSpotclass()));
        selectItem(cmbObsid, report.getObsid());
        selectItem(cmbReportStatus, report.getReportStatus());
        selectItem(cmbValidSpotClass, report.isValidSpotClass() ? "1" : "0");
    }

    public Integer getReportId() {
        return this.reportId;
    }

    public void setReportId(Integer reportId) {
        this.reportId = reportId;
    }

    /**
     * Verify that the data provided is valid.
     * 
     * @return
     */
    private boolean validateRegionReportData() {
        boolean pass = true;

        pass &= EditRegionsValidationUtil.validateInteger(cmbQuality.getText(),
                lblQuality);
        pass &= EditRegionsValidationUtil.validateInteger(txtRegion.getText(),
                lblRegion);
        pass &= EditRegionsValidationUtil.validateInteger(txtLatitude.getText(),
                lblLatitude);
        pass &= EditRegionsValidationUtil.validateInteger(
                txtReportLongitude.getText(), lblReportLongitude);
        pass &= EditRegionsValidationUtil
                .validateInteger(txtLongitude.getText(), lblLongitude);
        pass &= EditRegionsValidationUtil.validateInteger(txtCarlon.getText(),
                lblCarlon);
        pass &= EditRegionsValidationUtil.validateInteger(txtExtent.getText(),
                lblExtent);
        pass &= EditRegionsValidationUtil.validateInteger(txtArea.getText(),
                lblArea);
        pass &= EditRegionsValidationUtil.validateInteger(txtNumspots.getText(),
                lblNumspots);
        pass &= EditRegionsValidationUtil.validateInteger(cmbZurich.getText(),
                lblZurich);
        pass &= EditRegionsValidationUtil.validateInteger(cmbPenumbra.getText(),
                lblPenumbra);
        pass &= EditRegionsValidationUtil.validateInteger(cmbMagcode.getText(),
                lblMagcode);
        pass &= EditRegionsValidationUtil.validateInteger(txtExtent.getText(),
                lblExtent);
        pass &= EditRegionsValidationUtil
                .validateInteger(cmbReportStatus.getText(), lblReportStatus);
        pass &= EditRegionsValidationUtil.validateInteger(
                cmbValidSpotClass.getText(), lblValidSpotClass);

        this.getShell().layout(true, true);

        return pass;

    }

    /**
     * 
     * @param combo
     *            the combo box
     * @return The currently selected item in the Combo control, or null if none
     *         are selected.
     */
    private static String getSelection(Combo combo) {
        int index = combo.getSelectionIndex();
        if (index >= 0) {
            return combo.getItem(index);
        }
        return null;
    }

    /**
     * 
     * @param value
     * @return null if the string is empty, return an Integer object.
     */
    private static Integer convertInt(String value) {
        if (value.equals("")) {
            return null;
        }
        return Integer.valueOf(Integer.parseInt(value));
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
        report.setQuality(convertInt(getSelection(cmbQuality)));
        report.setRegion(convertInt(txtRegion.getText()));
        report.setLatitude(convertInt(txtLatitude.getText()));
        report.setReportLongitude(convertInt(txtReportLongitude.getText()));
        report.setLongitude(convertInt(txtLongitude.getText()));
        report.setReportLocation(txtReportLocation.getText());
        report.setLocation(txtLocation.getText());
        report.setCarlon(convertInt(txtCarlon.getText()));
        report.setExtent(convertInt(txtExtent.getText()));
        report.setArea(convertInt(txtArea.getText()));
        report.setNumspots(convertInt(txtNumspots.getText()));
        report.setZurich(convertInt(getSelection(cmbZurich)));
        report.setPenumbra(convertInt(getSelection(cmbPenumbra)));
        report.setCompact(getSelection(cmbCompact));
        report.setSpotclass(txtSpotClass.getText());
        report.setMagcode(convertInt(getSelection(cmbMagcode)));
        report.setMagclass(getSelection(cmbMagclass));
        report.setObsid(convertInt(getSelection(cmbObsid)));
        report.setReportStatus(convertInt(getSelection(cmbReportStatus)));
        report.setValidSpotClass(!"0".equals(getSelection(cmbValidSpotClass)));

        return report;
    }

    @Override
    protected void okPressed() {
        try {
            if (validateRegionReportData()) {
                RegionReport report = buildRegionReport();
                if (reportId != null) {
                    EditRegionsServerUtil.updateRegionReport(report);
                } else {
                    EditRegionsServerUtil.saveNewRegionReport(report);
                }

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
                        "Your data could not be validated. Please address errors and try again.");
                mb.open();
                return;
            }
        } catch (EditedRegionsException ex) {
            statusHandler.handle(Priority.ERROR,
                    "Unable to save new region report.", ex);
        }
        super.okPressed();
    }

    /**
     * Perform an initialization of all components on this dialog.
     * 
     * @param parent
     *            The parent composite
     */
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
        return combo;
    }

    /**
     * Adds a new Label control to the parent composite.
     * 
     * @param parent
     * @return the Label control
     */
    private Label addLabel(Composite parent) {
        Label label = new Label(parent, SWT.LEFT);
        label.setText("");
        return label;
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

        // Station field

        cmbStation = addComboControl(composite, "Station",
                new String[] { "Alpha", "Beta", "Delta" });

        lblStation = addLabel(composite);

        // Observatory field
        cmbObservatory = addComboControl(composite, "Observatory",
                new String[] { "APLM", "KHMN", "LISS" });

        lblObservatory = addLabel(composite);

        // Type field
        txtType = addTextControl(composite, "Type");

        lblType = addLabel(composite);

        // Quality field
        cmbQuality = addComboControl(composite, "Quality",
                new String[] { "4", "5" });

        lblQuality = addLabel(composite);

        // Region field
        txtRegion = addTextControl(composite, "Region");

        lblRegion = addLabel(composite);

        // Latitude field
        txtLatitude = addTextControl(composite, "Latitude");

        lblLatitude = addLabel(composite);

        // Report Longitude field
        txtReportLongitude = addTextControl(composite, "Report Longitude");

        lblReportLongitude = addLabel(composite);

        // Longitude field
        txtLongitude = addTextControl(composite, "Longitude");

        lblLongitude = addLabel(composite);

        // Report Location field
        txtReportLocation = addTextControl(composite, "Report Location");

        lblReportLocation = addLabel(composite);

        // Location field
        txtLocation = addTextControl(composite, "Location");

        lblLocation = addLabel(composite);

        // Carlon field
        txtCarlon = addTextControl(composite, "Carlon");

        lblCarlon = addLabel(composite);

        // Extent field
        txtExtent = addTextControl(composite, "Extent");

        lblExtent = addLabel(composite);

        // Area field
        txtArea = addTextControl(composite, "Area");

        lblArea = addLabel(composite);

        // Num spots field
        txtNumspots = addTextControl(composite, "Num spots");

        lblNumspots = addLabel(composite);

        // Zurich field
        cmbZurich = addComboControl(composite, "Zurich",
                new String[] { "1", "2", "3", "4", "5", "6", "7" });

        lblZurich = addLabel(composite);

        // Penumbra field
        cmbPenumbra = addComboControl(composite, "Penumbra",
                new String[] { "0", "1", "2", "3", "4", "5" });

        lblPenumbra = addLabel(composite);

        // Compact field
        cmbCompact = addComboControl(composite, "Compact",
                new String[] { "/", "7", "8", "9" });

        lblCompact = addLabel(composite);

        // Spotclass field
        txtSpotClass = addTextControl(composite, "Spot class");

        lblSpotClass = addLabel(composite);

        // Magcode field
        cmbMagcode = addComboControl(composite, "Magcode",
                new String[] { "1", "2", "3", "4", "5", "6", "7" });

        lblMagcode = addLabel(composite);

        // Magclass field
        cmbMagclass = addComboControl(composite, "Magclass",
                new String[] { "A", "B", "BG", "G", "BD", "BDG", "GD" });

        lblMagclass = addLabel(composite);

        // Obsid field
        cmbObsid = addComboControl(composite, "Obsid",
                new String[] { "1", "2", "3", "4" });

        lblObsid = addLabel(composite);

        // Report Status field
        cmbReportStatus = addComboControl(composite, "Report Status",
                new String[] { "1", "2", "3", "4" });

        lblReportStatus = addLabel(composite);

        // ValidSpotClass field
        cmbValidSpotClass = addComboControl(composite, "Valid Spot Class",
                new String[] { "0", "1" });

        lblValidSpotClass = addLabel(composite);

    }

    private void initializeControlsWithDefaultValues() {
        // TODO: add implementation
    }
}
