package demo;

import demo.service.GeneratorService;
import demo.service.impl.DefaultGeneratorService;
import demo.ui.MainController;
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
                if (c instanceof MainController mc) mc.setService(service);
                return c;
            } catch (Exception e) { throw new RuntimeException(e);
            }
        });
        Scene scene = new Scene(fxml.load(), 1200, 700);
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
