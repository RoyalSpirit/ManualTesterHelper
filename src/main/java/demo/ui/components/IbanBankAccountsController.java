package demo.ui.components;

import demo.domain.IbanCountry;
import demo.generators.IbanGenerator;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class IbanBankAccountsController implements Initializable {

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
        resultIbanField.setText(IbanGenerator.generate(ibanCountry));
    }

}
