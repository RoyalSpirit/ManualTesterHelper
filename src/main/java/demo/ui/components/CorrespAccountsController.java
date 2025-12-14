package demo.ui.components;

import demo.domain.Currency;
import demo.ui.BaseController;
import demo.ui.controls.LimitedTextField;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

import static demo.ui.UiUtils.copy;
import static demo.ui.util.UiErrorsProcessing.clearError;
import static demo.ui.util.UiErrorsProcessing.markError;
import static demo.util.Validators.isDigits;

public class CorrespAccountsController extends BaseController implements Initializable {

    @FXML
    private LimitedTextField bicField;
    @FXML
    private ComboBox<Currency> currencyBox;
    @FXML
    private ComboBox<String> bankTypeBox;
    @FXML
    private CheckBox generateNostroAccount;
    @FXML
    private TextField resultField;

    String[] bankTypes = {"Резидент", "Нерезидент"};

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        currencyBox.getItems().setAll(Currency.values());
        bankTypeBox.getItems().setAll(bankTypes);
        if (!currencyBox.getItems().isEmpty()) {
            currencyBox.getSelectionModel().select(0);
        }
        if (!bankTypeBox.getItems().isEmpty()) {
            bankTypeBox.getSelectionModel().select(0);
        }
    }

    @FXML
    private void onGenerateCorr() {
        clearError(bicField);

        String bic = text(bicField);
        if (!isDigits(bic, 9)) {
            markError(bicField, "БИК должен состоять из 9 цифр");
            return;
        }

        boolean isNostro = generateNostroAccount.isSelected();

        try {
            String acc = generatorService.generateCorrespAccount(
                    bic,
                    currencyBox.getValue().code(),
                    bankTypeBox.getValue(),
                    isNostro);
            resultField.setText(acc);
        } catch (Exception ex) {
            resultField.setText("Ошибка: " + ex.getMessage());
        }
    }

    @FXML
    private void copyCorrespAccount() {
        copy(resultField);
    }

    private static String text(TextField tf) {
        String s = tf.getText();
        return s == null ? "" : s.trim();
    }

}
