package demo.ui;

import javafx.animation.*;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
import javafx.scene.layout.StackPane;
import javafx.util.Duration;

public final class UiUtils {

    public static void copyToClipboard(String text) {
        Clipboard clipboard = Clipboard.getSystemClipboard();
        ClipboardContent content = new ClipboardContent();
        content.putString(text);
        clipboard.setContent(content);
    }

    public static void copy(TextField field) {
        copyToClipboard(field.getText());
        fadeThroughNode(field);
    }

    public static void flashNode(Node node) {
        FadeTransition ft = new FadeTransition(Duration.millis(150), node);
        ft.setFromValue(1.0);
        ft.setToValue(0.4);

        FadeTransition ft2 = new FadeTransition(Duration.millis(150), node);
        ft2.setFromValue(0.4);
        ft2.setToValue(1.0);

        ft.setOnFinished(e -> ft2.play());
        ft.play();
    }

    public static void bounceNode(Node node) {
        ScaleTransition grow = new ScaleTransition(Duration.millis(120), node);
        grow.setFromX(1.0);
        grow.setFromY(1.0);
        grow.setToX(1.08);
        grow.setToY(1.08);

        ScaleTransition shrink = new ScaleTransition(Duration.millis(120), node);
        shrink.setFromX(1.08);
        shrink.setFromY(1.08);
        shrink.setToX(1.0);
        shrink.setToY(1.0);

        SequentialTransition seq = new SequentialTransition(grow, shrink);
        seq.play();
    }

    public static void fadeThroughNode(Node node) {
        Timeline fadeOut = new Timeline(
                new KeyFrame(Duration.millis(90),
                        new KeyValue(node.opacityProperty(), 0.0, Interpolator.EASE_BOTH),
                        new KeyValue(node.scaleXProperty(), 0.96, Interpolator.EASE_BOTH),
                        new KeyValue(node.scaleYProperty(), 0.96, Interpolator.EASE_BOTH))
        );

        PauseTransition pause = new PauseTransition(Duration.millis(30));

        Timeline fadeIn = new Timeline(
                new KeyFrame(Duration.millis(210),
                        new KeyValue(node.opacityProperty(), 1.0, Interpolator.EASE_BOTH),
                        new KeyValue(node.scaleXProperty(), 1.0, Interpolator.EASE_BOTH),
                        new KeyValue(node.scaleYProperty(), 1.0, Interpolator.EASE_BOTH))
        );

        SequentialTransition seq = new SequentialTransition(fadeOut, pause, fadeIn);
        seq.play();
    }

    public static void showToast(StackPane root, String message) {
        Label toast = new Label(message);
        toast.getStyleClass().add("toast");
        toast.setMouseTransparent(true);

        root.getChildren().add(toast);
        StackPane.setAlignment(toast, Pos.BOTTOM_CENTER);
        StackPane.setMargin(toast, new Insets(0, 0, 20, 0));

        toast.setOpacity(0.0);

        FadeTransition fadeIn = new FadeTransition(Duration.millis(200), toast);
        fadeIn.setFromValue(0.0);
        fadeIn.setToValue(1.0);

        PauseTransition stay = new PauseTransition(Duration.millis(2100));

        FadeTransition fadeOut = new FadeTransition(Duration.millis(250), toast);
        fadeOut.setFromValue(1.0);
        fadeOut.setToValue(0.0);

        fadeOut.setOnFinished(e -> root.getChildren().remove(toast));

        new SequentialTransition(fadeIn, stay, fadeOut).play();
    }

}
