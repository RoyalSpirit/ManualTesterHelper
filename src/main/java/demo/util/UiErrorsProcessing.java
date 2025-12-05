package demo.util;

import javafx.css.PseudoClass;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;

public final class UiErrorsProcessing {

    private static final PseudoClass ERROR = PseudoClass.getPseudoClass("error");

    public static void markError(TextField tf, String msg) {
        tf.pseudoClassStateChanged(ERROR, true);
        tf.setTooltip((msg == null || msg.isBlank()) ? null : new Tooltip(msg));
    }

    public static void clearError(TextField tf) {
        tf.pseudoClassStateChanged(ERROR, false);
        tf.setTooltip(null);
    }

}
