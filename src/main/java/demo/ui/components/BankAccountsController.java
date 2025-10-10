package demo.ui.components;

import demo.generators.AccountGenerator;
import demo.domain.Currency;
import demo.ui.controls.LimitedTextField;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

import static demo.util.UiErrorsProcessing.clearError;
import static demo.util.UiErrorsProcessing.markError;
import static demo.util.Validators.isDigits;

public class BankAccountsController implements Initializable {
    @FXML
    private LimitedTextField bicField;
    @FXML
    private ComboBox<Currency> currencyBox;
    @FXML
    private CheckBox generateNostroAccount;
    @FXML
    private TextField resultField;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        currencyBox.getItems().setAll(Currency.values());
        if (!currencyBox.getItems().isEmpty()) {
            currencyBox.getSelectionModel().select(0);
        }
    }

    @FXML
    private void onGenerateCorr() throws InterruptedException {
        clearError(bicField);

        String bic = text(bicField);
        if (!isDigits(bic, 9)) {
            markError(bicField, "БИК должен состоять из 9 цифр");
            return;
        }

        Currency cur = currencyBox.getValue();
        if (cur == null) {
            resultField.setText("Выберите валюту");
            return;
        }

        boolean isNostro = generateNostroAccount.isSelected();

        try {
            String acc = AccountGenerator.generateAccount(bic, cur.code(), isNostro);
            resultField.setText(acc);
        } catch (Exception ex) {
            resultField.setText("Ошибка: " + ex.getMessage());
        }
    }

    private static String text(TextField tf) {
        String s = tf.getText();
        return s == null ? "" : s.trim();
    }

}
