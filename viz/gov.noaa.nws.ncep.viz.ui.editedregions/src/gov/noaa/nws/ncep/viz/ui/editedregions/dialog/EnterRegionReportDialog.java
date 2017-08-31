package gov.noaa.nws.ncep.viz.ui.editedregions.dialog;

import java.util.Calendar;
import java.util.Collection;
import java.util.Date;

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

/**
 * TODO the class level Label parameters are unused but will be used in
 * subsequent releases...added suppress warnings for now
 * 
 * @author Ada
 *
 */
public class EnterRegionReportDialog extends Dialog {

    private static final String NO_REGION = "None";

    // Input fields

    private Combo cmbObservatory;

    @SuppressWarnings("unused")
    private Label lblObservatory;

    private Text txtType;

    @SuppressWarnings("unused")
    private Label lblType;

    private Combo cmbQuality;

    @SuppressWarnings("unused")
    private Label lblQuality;

    private Combo cmbRegion;

    @SuppressWarnings("unused")
    private Label lblRegion;

    private Text txtReportLocation;

    @SuppressWarnings("unused")
    private Label lblReportLocation;

    private Text txtLocation;

    @SuppressWarnings("unused")
    private Label lblLocation;

    private Text txtCarlon;

    private Label lblCarlon;

    private Text txtExtent;

    private Label lblExtent;

    private Text txtArea;

    private Label lblArea;

    private Text txtNumspots;

    private Label lblNumspots;

    private Text txtSpotClass;

    @SuppressWarnings("unused")
    private Label lblSpotClass;

    private Combo cmbMagclass;

    @SuppressWarnings("unused")
    private Label lblMagclass;

    private final IUFStatusHandler statusHandler = UFStatus
            .getHandler(EnterRegionReportDialog.class);

    private Integer reportId = null;

    private Date observationTime = null;

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
        selectItem(cmbRegion, report.getRegion());
        selectItem(cmbObservatory, report.getObservatory());
        txtType.setText(report.getType());
        selectItem(cmbQuality, EditRegionsServerUtil
                .getObservationQuality(report.getQuality()));
        txtReportLocation.setText(convertToString(report.getReportLocation()));
        txtLocation.setText(convertToString(report.getLocation()));
        txtCarlon.setText(convertToString(report.getCarlon()));
        txtExtent.setText(convertToString(report.getExtent()));
        txtArea.setText(convertToString(report.getArea()));
        txtSpotClass.setText(convertToString(report.getSpotclass()));
        txtNumspots.setText(convertToString(report.getNumspot()));
    }

    /**
     * 
     * @return The report id
     */
    public Integer getReportId() {
        return this.reportId;
    }

    /**
     * 
     * @param reportId
     *            The report id to set
     */
    public void setReportId(Integer reportId) {
        this.reportId = reportId;
    }

    /**
     * 
     * @return The observation time
     */
    public Date getObservationTime() {
        return this.observationTime;
    }

    /**
     * 
     * @param observationTime
     *            The observation time to set.
     */
    public void setObservationTime(Date observationTime) {
        this.observationTime = observationTime;
    }

    /**
     * Verify that the data provided is valid.
     * 
     * @return
     */
    private boolean validateRegionReportData() {
        boolean pass = true;

        pass &= EditRegionsValidationUtil.validateInteger(txtCarlon.getText(),
                lblCarlon);
        pass &= EditRegionsValidationUtil.validateInteger(txtExtent.getText(),
                lblExtent);
        pass &= EditRegionsValidationUtil.validateInteger(txtArea.getText(),
                lblArea);
        pass &= EditRegionsValidationUtil.validateInteger(txtNumspots.getText(),
                lblNumspots);
        pass &= EditRegionsValidationUtil.validateInteger(txtExtent.getText(),
                lblExtent);

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
        if (getReportId() != null) {
            report.setId(getReportId());
        }
        report.setDataTime(new DataTime(calendar));
        report.setPersistenceTime(calendar.getTime());
        report.setObservationTime(getObservationTime());

        String region = getSelection(cmbRegion);
        if (region.equals(NO_REGION)) {
            report.setRegion(null);
        } else {
            report.setRegion(convertInt(region));
        }

        // report.setStation(convertInt(getSelection(cmbStation)));
        report.setObservatory(getSelection(cmbObservatory));
        report.setType(txtType.getText());
        report.setQuality(EditRegionsServerUtil
                .getObservationQualityId(getSelection(cmbQuality)));
        // report.setLatitude(convertInt(txtLatitude.getText()));
        // report.setReportLongitude(convertInt(txtReportLongitude.getText()));
        // report.setLongitude(convertInt(txtLongitude.getText()));
        report.setReportLocation(txtReportLocation.getText());
        report.setLocation(txtLocation.getText());
        report.setCarlon(convertInt(txtCarlon.getText()));
        report.setExtent(convertInt(txtExtent.getText()));
        report.setArea(convertInt(txtArea.getText()));
        report.setNumspot(convertInt(txtNumspots.getText()));
        // report.setZurich(convertInt(getSelection(cmbZurich)));
        // report.setPenumbra(EditRegionsServerUtil
        // .getPenumbralClassId(getSelection(cmbPenumbra)));
        // report.setCompact(getSelection(cmbCompact));
        report.setSpotclass(txtSpotClass.getText());
        // report.setMagcode(convertInt(getSelection(cmbMagcode)));
        report.setMagclass(getSelection(cmbMagclass));
        // report.setObsid(EditRegionsServerUtil
        // .getObservationTypeId(getSelection(cmbObsid)));
        // report.setReportStatus(EditRegionsServerUtil
        // .getReportStatusId(getSelection(cmbReportStatus)));
        // report.setValidSpotClass(!"0".equals(getSelection(cmbValidSpotClass)));

        return report;
    }

    @Override
    protected void okPressed() {
        try {
            if (validateRegionReportData()) {
                RegionReport report = buildRegionReport();
                if (getReportId() != null) {
                    EditRegionsServerUtil.updateRegionReport(report);
                } else {
                    EditRegionsServerUtil.saveNewRegionReport(report);
                }
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
        String title;
        if (getReportId() != null) {
            title = "Update region report.";
        } else {
            title = "Enter new region report.";
        }

        getShell().setText(title);

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
     * Adds a new row with a combo selection widget and the provided label.
     * 
     * @param parent
     * @param label
     * @return the Combo control
     */
    private Combo addComboControl(Composite parent, String label,
            Collection<String> items) {
        new Label(parent, SWT.LEFT).setText(label);
        Combo combo = new Combo(parent, SWT.DROP_DOWN ^ SWT.READ_ONLY);
        combo.setItems(items.toArray(new String[0]));
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

        // Observatory field
        // This is the UI codes as listed in Edited Regions FRD v3.0
        // ICAO codes (APLM, KHMN, LISS) are not in use.
        cmbObservatory = addComboControl(composite, "Observatory",
                new String[] { "LEA", "HOL", "SVI" });

        lblObservatory = addLabel(composite);

        // Type field
        txtType = addTextControl(composite, "Type");

        lblType = addLabel(composite);

        // Quality field
        cmbQuality = addComboControl(composite, "Quality",
                EditRegionsServerUtil.getObservationQualityRefData());

        lblQuality = addLabel(composite);

        // Region field
        String[] regions = new String[0];
        try {
            String[] tmp = EditRegionsServerUtil.getAllRegions().stream()
                    .map(String::valueOf).toArray(String[]::new);
            regions = new String[tmp.length + 1];
            regions[0] = NO_REGION;
            System.arraycopy(tmp, 0, regions, 1, tmp.length);
        } catch (EditedRegionsException e) {
            statusHandler.error("Error retreiving region ids.", e);
        }

        cmbRegion = addComboControl(composite, "Region", regions);

        lblRegion = addLabel(composite);

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

        // Spotclass field
        txtSpotClass = addTextControl(composite, "Spot class");

        lblSpotClass = addLabel(composite);

        // Magclass field
        cmbMagclass = addComboControl(composite, "Magclass",
                new String[] { "A", "B", "BG", "G", "BD", "BDG", "GD" });

        lblMagclass = addLabel(composite);

    }

    /**
     * 
     */
    private void initializeControlsWithDefaultValues() {
        // TODO: add implementation
    }
}
