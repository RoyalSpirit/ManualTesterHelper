package demo.ui.components;


import demo.domain.OverdraftPrefixes;
import demo.generators.OverdraftAccountsGenerator;
import demo.ui.controls.LimitedTextField;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

import static demo.ui.UiUtils.copy;
import static demo.util.UiErrorsProcessing.clearError;
import static demo.util.UiErrorsProcessing.markError;

public class OverdraftAccountsController {

    @FXML
    private LimitedTextField corrField;
    @FXML
    private ComboBox<OverdraftPrefixes> presetBox;
    @FXML
    private LimitedTextField customPrefixField;
    @FXML
    private TextField resultField;

    @FXML
    private void initialize() {
        if (presetBox != null) {
            presetBox.getItems().setAll(OverdraftPrefixes.values());
            presetBox.getSelectionModel().select(OverdraftPrefixes.SBERBANK);

            presetBox.valueProperty().addListener((obs, oldV, newV) -> applyPreset(newV));
            applyPreset(presetBox.getValue());
        }
    }

    private void applyPreset(OverdraftPrefixes preset) {
        boolean custom = preset == OverdraftPrefixes.CUSTOM || preset.getPrefix() == null;

        customPrefixField.setDisable(!custom);
        if (!custom) {
            customPrefixField.setText(preset.getPrefix());
        } else {
            customPrefixField.clear();
        }
    }

    @FXML
    private void onGenerate() {
        String corr = safe(corrField.getText());

        if (!corr.matches("\\d{20}")) {
            resultField.clear();
            markError(corrField, "Ошибка, корреспондентский счёт должен состоять из 20 цифр");
            return;
        }

        OverdraftPrefixes preset = presetBox.getValue();
        if (preset == null) {
            resultField.setText("Ошибка: выберите банк");
            return;
        }

        String customPrefix = customPrefixField.isDisabled()
                ? null
                : safe(customPrefixField.getText());

        try {
            String overdraft = OverdraftAccountsGenerator.generateOverdraftAccount(preset, corr, customPrefix);
            clearError(corrField);
            clearError(customPrefixField);
            resultField.setText(overdraft);
        } catch (IllegalArgumentException ex) {
            resultField.clear();
            markError(customPrefixField, "Ошибка, необходимо ввести префикс");
        }
    }

    @FXML
    private void copyOverdraftAccount() {
        copy(resultField);
    }

    private static String safe(String s) {
        return s == null ? "" : s.trim();
    }
}
