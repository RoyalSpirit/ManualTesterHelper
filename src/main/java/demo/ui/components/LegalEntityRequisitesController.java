package demo.ui.components;

import demo.generators.InnGenerator;
import demo.generators.KppGenerator;
import demo.generators.OgrnGenerator;
import demo.generators.OkpoGenerator;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

import static demo.ui.UiUtils.copy;

public class LegalEntityRequisitesController {

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
        String resultInn = InnGenerator.generateInn();
        resultInnField.setText(resultInn);
    }

    @FXML
    private void copyInn() {
        copy(resultInnField);
    }

    @FXML
    private void onGenerateKpp() {
        String resultKpp = KppGenerator.generateKpp();
        resultKppField.setText(resultKpp);
    }

    @FXML
    private void copyKpp() {
        copy(resultKppField);
    }

    @FXML
    private void onGenerateOgrn() {
        String resultOgrn = OgrnGenerator.generateOgrn();
        resultOgrnField.setText(resultOgrn);
    }

    @FXML
    private void copyOgrn() {
        copy(resultOgrnField);
    }

    @FXML
    private void onGenerateOkpo() {
        String resulOkpo = OkpoGenerator.generateOkpo();
        resultOkpoField.setText(resulOkpo);
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
