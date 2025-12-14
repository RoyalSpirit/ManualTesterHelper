package demo.ui.components;

import demo.domain.Language;
import demo.ui.BaseController;
import demo.ui.controls.LimitedTextField;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.function.IntFunction;

import static demo.ui.UiUtils.copy;
import static demo.ui.util.UiErrorsProcessing.clearError;
import static demo.ui.util.UiErrorsProcessing.markError;

public class StringAndNumbersController extends BaseController implements Initializable {

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
        if (languageSelectionBox != null) {
            languageSelectionBox.getItems().setAll(Language.values());
            if (!languageSelectionBox.getItems().isEmpty()) {
                languageSelectionBox.getSelectionModel().select(0);
            }
        }
    }

    private void generateWithValidation(TextField lengthField, TextField resultField, IntFunction<String> generator) {
        clearError(lengthField);

        int n;
        try {
            String length = lengthField.getText();
            if (length == null || length.trim().isEmpty()) throw new NumberFormatException();
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
            Language lang = languageSelectionBox.getValue();
            return generatorService.generateRandomText(n, lang);
        });
    }

    @FXML
    private void copyGeneratedText() {
        copy(resultLettersField);
    }

    @FXML
    private void onGenerateNumbers() {
        generateWithValidation(numbersLengthInputField, resultNumbersField,
                generatorService::generateRandomNumbers);
    }

    @FXML
    private void copyGeneratedNumbers() {
        copy(resultNumbersField);
    }

    @FXML
    private void onGenerateSymbols() {
        generateWithValidation(symbolsLengthInputField, resultSymbolsField,
                generatorService::generateRandomSymbols);
    }

    @FXML
    private void copyGeneratedSymbols() {
        copy(resultSymbolsField);
    }

}
