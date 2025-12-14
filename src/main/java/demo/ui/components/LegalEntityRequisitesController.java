package demo.ui.components;

import demo.ui.BaseController;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

import static demo.ui.UiUtils.copy;

public class LegalEntityRequisitesController extends BaseController {

    @FXML
    private TextField resultInnField;
    @FXML
    private TextField resultOgrnField;
    @FXML
    private TextField resultKppField;
    @FXML
    private TextField resultOkpoField;

    @FXML
    private void onGenerateInn() {
        resultInnField.setText(generatorService.generateInn());
    }

    @FXML
    private void copyInn() {
        copy(resultInnField);
    }

    @FXML
    private void onGenerateKpp() {
        resultKppField.setText(generatorService.generateKpp());
    }

    @FXML
    private void copyKpp() {
        copy(resultKppField);
    }

    @FXML
    private void onGenerateOgrn() {
        resultOgrnField.setText(generatorService.generateOgrn());
    }

    @FXML
    private void copyOgrn() {
        copy(resultOgrnField);
    }

    @FXML
    private void onGenerateOkpo() {
        resultOkpoField.setText(generatorService.generateOkpo());
    }

    @FXML
    private void copyOkpo() {
        copy(resultOkpoField);
    }

    @FXML
    private void onGenerateAllRequisites() {
        onGenerateInn();
        onGenerateKpp();
        onGenerateOgrn();
        onGenerateOkpo();
    }

}
