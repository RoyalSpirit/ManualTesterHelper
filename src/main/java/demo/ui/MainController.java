package demo.ui;

import javafx.animation.FadeTransition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.StackPane;
import javafx.util.Duration;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import static demo.ui.UiUtils.showToast;

public class MainController {

    @FXML
    private StackPane contentRoot;

    @FXML
    private ToggleButton btnAccounts;
    @FXML
    private ToggleButton btnLegal;
    @FXML
    private ToggleButton btnCodes;
    @FXML
    private ToggleButton btnStrings;
    @FXML
    private ToggleButton btnSnils;

    private ToggleGroup menuGroup;

    @FXML
    public void initialize() {
        menuGroup = new ToggleGroup();

        btnAccounts.setToggleGroup(menuGroup);
        btnLegal.setToggleGroup(menuGroup);
        btnCodes.setToggleGroup(menuGroup);
        btnStrings.setToggleGroup(menuGroup);
        btnSnils.setToggleGroup(menuGroup);

        btnAccounts.setSelected(true);
        showBankAccounts();

        menuGroup.selectedToggleProperty().addListener((obs, oldToggle, newToggle) -> {
            if (newToggle == null && oldToggle != null) {
                menuGroup.selectToggle(oldToggle);
            }
        });
    }

    @FXML
    private void showBankAccounts() {
        loadWithFade("/demo/ui/components/BankAccountsPane.fxml");
    }

    @FXML
    private void showLegalEntity() {
        loadWithFade("/demo/ui/components/LegalEntityRequisitesPane.fxml");
    }

    @FXML
    private void showCodes() {
        loadWithFade("/demo/ui/components/BicSwiftUuidPane.fxml");
    }

    @FXML
    private void showStringsNumbers() {
        loadWithFade("/demo/ui/components/StringAndNumbersPane.fxml");
    }

    @FXML
    private void showSnils() {
        loadWithFade("/demo/ui/components/SnilsPane.fxml");
    }

    @FXML
    private void onToggleThemeClicked() {
        List<String> messages = List.of(
                "Тёмная тема появится сразу после релиза версии 9472.01 🌚",
                "Тёмная тема сейчас тоже спит. Не будем её будить 😴",
                "Представь, что всё уже стало тёмным. 😏",
                "Разработчик включил тёмную тему… но только у себя в комнате. 😬",
                "Тёмная тема загружается… загружается… загружается… Ладно, нет. 😈",
                "Тёмная тема не загрузилась, потому что ты слишком светлая личность. 😏"
        );

        String msg = messages.get(
                ThreadLocalRandom.current().nextInt(messages.size())
        );

        showToast(contentRoot, msg);
    }

    private void loadWithFade(String path) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(path));
            Node newNode = loader.load();

            Node oldNode = contentRoot.getChildren().isEmpty()
                    ? null
                    : contentRoot.getChildren().get(0);

            if (oldNode == null) {
                contentRoot.getChildren().setAll(newNode);
                newNode.setOpacity(0);

                FadeTransition fadeIn = new FadeTransition(Duration.millis(200), newNode);
                fadeIn.setFromValue(0.0);
                fadeIn.setToValue(1.0);
                fadeIn.play();

                return;
            }

            FadeTransition fadeOut = new FadeTransition(Duration.millis(150), oldNode);
            fadeOut.setFromValue(1.0);
            fadeOut.setToValue(0.0);

            fadeOut.setOnFinished(e -> {
                contentRoot.getChildren().setAll(newNode);
                newNode.setOpacity(0);

                FadeTransition fadeIn = new FadeTransition(Duration.millis(200), newNode);
                fadeIn.setFromValue(0.0);
                fadeIn.setToValue(1.0);
                fadeIn.play();
            });

            fadeOut.play();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}