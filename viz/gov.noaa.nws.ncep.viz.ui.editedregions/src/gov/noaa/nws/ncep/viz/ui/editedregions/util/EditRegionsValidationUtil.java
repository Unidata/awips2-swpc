package gov.noaa.nws.ncep.viz.ui.editedregions.util;

import java.util.Objects;
import java.util.regex.Pattern;

import org.eclipse.swt.widgets.Label;

public final class EditRegionsValidationUtil {

    private static final Pattern PATTERN_INTEGER = Pattern.compile("^\\d*$");

    private EditRegionsValidationUtil() {

    }

    /**
     * Validates that the given value is either a blank string, or a valid
     * integer. Updates errorLabel with message, if appropriate.
     * 
     * @param value
     *            The value being validated.
     * @param errorLabel
     *            The label where the error message is written, if applicable.
     * @return true if validation passes, false otherwise
     */
    public static boolean validateInteger(String value, Label errorLabel) {
        Objects.requireNonNull(errorLabel, "errorLabel");
        Objects.requireNonNull(value, "value");

        if (PATTERN_INTEGER.matcher(value).matches()) {
            errorLabel.setText("");
            return true;
        } else {
            errorLabel.setText("Valid integer required.");
            return false;
        }

    }

}
