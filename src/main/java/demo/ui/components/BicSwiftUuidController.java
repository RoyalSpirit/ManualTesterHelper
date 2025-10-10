package demo.ui.components;

import demo.generators.BicGenerator;
import demo.generators.SwiftGenerator;
import demo.ui.controls.LimitedTextField;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;

import java.util.UUID;

import static demo.util.UiErrorsProcessing.clearError;
import static demo.util.UiErrorsProcessing.markError;
import static demo.util.Validators.isLetters;

public class BicSwiftUuidController {

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
        String resultBic = BicGenerator.generateBic();
        resultBicField.setText(resultBic);
    }

    @FXML
    private void onGenerateSwift() throws InterruptedException {
        clearError(countryCodeInputField);
        String countryCode = countryCodeInputField.getText();
        if (!isLetters(countryCode, 2)) {
            markError(countryCodeInputField, "");
            resultSwiftField.clear();
            return;
        }
        String resultSwift = SwiftGenerator.generateEightSwift(countryCode);
        resultSwiftField.setText(resultSwift);
    }

    @FXML
    private void onGenerateUuid() {
        String uuid = UUID.randomUUID().toString();
        String resultUuid = generateWithoutDashes.isSelected() ? uuid.replaceAll("-", "") : uuid;
        resultUuidField.setText(resultUuid);
    }

}
