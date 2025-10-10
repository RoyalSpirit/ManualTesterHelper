package demo.ui.components;

import demo.generators.StringAndNumbersGenerator;
import demo.domain.Language;
import demo.ui.controls.LimitedTextField;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.function.IntFunction;

import static demo.util.UiErrorsProcessing.clearError;
import static demo.util.UiErrorsProcessing.markError;
import static demo.util.Validators.isDigits;

public class StringAndNumbersController implements Initializable {

    @FXML
    private LimitedTextField numbersLengthInputField;
    @FXML
    private TextField resultNumbersField;


    @FXML
    private LimitedTextField lettersLengthInputField;
    @FXML
    private ComboBox<Language> languageSelectionBox;
    @FXML
    private TextField resultLettersField;


    @FXML
    private LimitedTextField symbolsLengthInputField;
    @FXML
    private TextField resultSymbolsField;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        languageSelectionBox.getItems().setAll(Language.values());
        if (!languageSelectionBox.getItems().isEmpty()) {
            languageSelectionBox.getSelectionModel().select(0);
        }
    }

    private void generateWithValidation(TextField lengthField, TextField resultField, IntFunction<String> generator) {
        clearError(lengthField);

        int n;
        try {
            String length = lengthField.getText();
            if (length.trim().isEmpty() || length == null) throw new NumberFormatException();
            n = Integer.parseInt(length.trim());
            if (n <= 0) throw new NumberFormatException();
        } catch (NumberFormatException ex) {
            markError(lengthField, "");
            resultField.clear();
            return;
        }

        String result = generator.apply(n);
        clearError(lengthField);
        resultField.setText(result);
    }

    @FXML
    private void onGenerateText() {
        generateWithValidation(lettersLengthInputField, resultLettersField, n -> {
            Language getLanguageFromComboBox = languageSelectionBox.getValue();
            String language = (getLanguageFromComboBox == Language.RUSSIAN) ? "Russian" : "English";
            return StringAndNumbersGenerator.randomStringGenerator(n, language);
        });
    }

    @FXML
    private void onGenerateNumbers() {
        generateWithValidation(numbersLengthInputField, resultNumbersField,
                StringAndNumbersGenerator::randomNumberGenerator);
    }

    @FXML
    private void onGenerateSymbols() {
        generateWithValidation(symbolsLengthInputField, resultSymbolsField,
                StringAndNumbersGenerator::randomSymbolsGenerator);
    }

}
