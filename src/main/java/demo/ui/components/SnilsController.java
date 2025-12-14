package demo.ui.components;

import demo.ui.BaseController;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

import static demo.ui.UiUtils.copy;

public class SnilsController extends BaseController {

    @FXML
    private TextField resultSnilsField;

    @FXML
    private void onGenerateSnils() {
        resultSnilsField.setText(generatorService.generateTestSnils());
    }

    @FXML
    private void copySnils() {
        copy(resultSnilsField);
    }

}
