package demo.ui.components;

import demo.ui.BaseController;
import demo.ui.controls.LimitedTextField;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;

import java.util.UUID;

import static demo.ui.UiUtils.copy;
import static demo.ui.util.UiErrorsProcessing.clearError;
import static demo.ui.util.UiErrorsProcessing.markError;
import static demo.util.Validators.isLetters;

public class BicSwiftUuidController extends BaseController {

    @FXML
    private TextField resultBicField;
    @FXML
    private LimitedTextField countryCodeInputField;
    @FXML
    private TextField resultSwiftField;
    @FXML
    private TextField resultUuidField;
    @FXML
    private CheckBox generateWithoutDashes;

    @FXML
    private void onGenerateBic() {
        String resultBic = generatorService.generateBic();
        resultBicField.setText(resultBic);
    }

    @FXML
    private void copyBic() {
        copy(resultBicField);
    }

    @FXML
    private void onGenerateSwift() {
        clearError(countryCodeInputField);
        String countryCode = countryCodeInputField.getText();
        if (!isLetters(countryCode, 2)) {
            markError(countryCodeInputField, "");
            resultSwiftField.clear();
            return;
        }
        String resultSwift = generatorService.generateSwift(countryCode);
        resultSwiftField.setText(resultSwift);
    }

    @FXML
    private void copySwift() {
        copy(resultSwiftField);
    }

    @FXML
    private void onGenerateUuid() {
        String uuid = UUID.randomUUID().toString();
        String resultUuid = generateWithoutDashes.isSelected() ? uuid.replaceAll("-", "") : uuid;
        resultUuidField.setText(resultUuid);
    }

    @FXML
    private void copyUuid() {
        copy(resultUuidField);
    }

}
