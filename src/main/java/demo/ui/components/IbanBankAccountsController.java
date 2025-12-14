package demo.ui.components;

import demo.domain.IbanCountry;
import demo.ui.BaseController;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

import static demo.ui.UiUtils.copy;

public class IbanBankAccountsController extends BaseController implements Initializable {

    @FXML
    private ComboBox<IbanCountry> ibanCountryComboBox;
    @FXML
    private TextField resultIbanField;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ibanCountryComboBox.getItems().setAll(IbanCountry.values());
        if (!ibanCountryComboBox.getItems().isEmpty()) {
            ibanCountryComboBox.getSelectionModel().select(0);
        }
    }

    @FXML
    private void  onGenerateIbanAccount() {
        IbanCountry ibanCountry = ibanCountryComboBox.getValue();
        resultIbanField.setText(generatorService.generateIban(ibanCountry));
    }

    @FXML
    private void copyIbanAccount() {
        copy(resultIbanField);
    }

}
