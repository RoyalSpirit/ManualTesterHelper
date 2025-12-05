package demo.ui.components;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;

import static demo.generators.SnilsTestGenerator.generateTestSnils;
import static demo.ui.UiUtils.copy;

public class SnilsController {

    @FXML
    private TextField resultSnilsField;

    @FXML
    private void onGenerateSnils() {
        String resultSnils = generateTestSnils();
        resultSnilsField.setText(resultSnils);
    }

    @FXML
    private void copySnils() {
        copy(resultSnilsField);
    }

}
