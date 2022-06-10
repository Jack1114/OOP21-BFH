package view;

import javafx.scene.ImageCursor;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class SharedMethodsImpl implements SharedMethods {

	@Override
	public void setCursor(Scene scene) {
		Image image = new Image("images/cursorHand_grey.png");  //pass in the image path
		scene.setCursor(new ImageCursor(image));
		scene.getStylesheets().add(getClass().getResource("/assets/gameLayout.css").toExternalForm());
	}

	@Override
	public void dragScene(Parent root, Stage stage) {
	    root.setOnMousePressed(pressEvent -> {
	        root.setOnMouseDragged(dragEvent -> {
	        	stage.setX(dragEvent.getScreenX() - pressEvent.getSceneX());
	        	stage.setY(dragEvent.getScreenY() - pressEvent.getSceneY());
	        });
	    });

	}

}
