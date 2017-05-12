package gov.noaa.nws.ncep.viz.ui.editedregions.dialog;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Shell;

import com.raytheon.uf.common.status.IUFStatusHandler;
import com.raytheon.uf.common.status.UFStatus;

public class EnterRegionReportDialog extends Dialog {

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

    private void createFieldControls(Group bottomGroup) {
        // TODO: add implementation
    }

    private void initializeControlsWithDefaultValues() {
        // TODO: add implementation
    }
}
