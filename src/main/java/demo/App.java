package demo;

import demo.service.GeneratorService;
import demo.service.impl.DefaultGeneratorService;
import demo.ui.BaseController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.Objects;

public class App extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        GeneratorService service = new DefaultGeneratorService();

        FXMLLoader fxml = new FXMLLoader(getClass().getResource("/demo/ui/MainView.fxml"));
        fxml.setControllerFactory(type -> {
            try {
                Object controller = type.getDeclaredConstructor().newInstance();

                if (controller instanceof BaseController base) {
                    base.setGeneratorService(service);
                }

                return controller;
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
        Scene scene = new Scene(fxml.load(), 1500, 800);
        scene.getStylesheets().add(
                Objects.requireNonNull(App.class.getResource("/demo/ui/app.css")).toExternalForm()
        );

        stage.setTitle("ManualTesterHelper");
        stage.setScene(scene);
        stage.setMinWidth(1500);
        stage.setMinHeight(800);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
