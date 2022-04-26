package view;


import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public final class MainMenu extends Application {

    @Override
    public void start(final Stage primaryStage) throws Exception {

        Group root = new Group();
        Scene scene = new Scene(root);
           
        primaryStage.setScene(scene);  
        primaryStage.show();
    }

    public static void run(final String... args) {
        launch();
    }


    public static final class Main {
        private Main() {
            // the constructor will never be called directly.
        }

        public static void main(final String...args) {
            Application.launch(MainMenu.class, args);

        }
    }
}
