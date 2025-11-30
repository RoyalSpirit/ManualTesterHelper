package demo;

import demo.service.GeneratorService;
import demo.service.impl.DefaultGeneratorService;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class App extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader fxml = new FXMLLoader(getClass().getResource("/demo/ui/MainView.fxml"));
        GeneratorService service = new DefaultGeneratorService();
        fxml.setControllerFactory(type -> {
            try {
                var c = type.getDeclaredConstructor().newInstance();
                return c;
            } catch (Exception e) { throw new RuntimeException(e);
            }
        });
        Scene scene = new Scene(fxml.load(), 1500, 800);
        scene.getStylesheets().add(
                App.class.getResource("/demo/ui/app.css").toExternalForm()
        );

        stage.setTitle("ManualTesterHelper");
        stage.setScene(scene);
        stage.setMinWidth(1200);
        stage.setMinHeight(700);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

}
