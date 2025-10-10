package demo.ui;

import demo.service.GeneratorService;
import javafx.fxml.FXML;
import javafx.scene.control.TabPane;

public class MainController {

    @FXML
    private TabPane tabs;
    private GeneratorService service;

    public void setService(GeneratorService s) {
        this.service = s;
    }

}
