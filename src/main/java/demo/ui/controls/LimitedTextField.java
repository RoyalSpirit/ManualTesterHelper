package demo.ui.controls;

import javafx.beans.property.*;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;

public class LimitedTextField extends TextField {
    public enum Mode {ANY, DIGITS, LETTERS}

    private final IntegerProperty maxLength = new SimpleIntegerProperty(this, "maxLength", Integer.MAX_VALUE);
    private final ObjectProperty<Mode> mode = new SimpleObjectProperty<>(this, "mode", Mode.ANY);

    public LimitedTextField() {
        maxLength.addListener((obs, o, n) -> applyFormatter());
        mode.addListener((obs, o, n) -> applyFormatter());
        applyFormatter();
    }

    private void applyFormatter() {
        setTextFormatter(new TextFormatter<>(c -> {
            String nt = c.getControlNewText();
            if (nt.length() > getMaxLength()) return null;
            return switch (getMode()) {
                case ANY -> c;
                case DIGITS -> nt.matches("\\d*") ? c : null;
                case LETTERS -> nt.matches("[A-Za-zА-Яа-яЁё]*") ? c : null;
            };
        }));
    }

    // свойства для FXML
    public int getMaxLength() {
        return maxLength.get();
    }

    public void setMaxLength(int v) {
        maxLength.set(v);
    }

    public IntegerProperty maxLengthProperty() {
        return maxLength;
    }

    public Mode getMode() {
        return mode.get();
    }

    public void setMode(Mode m) {
        mode.set(m);
    }

    public ObjectProperty<Mode> modeProperty() {
        return mode;
    }
}
