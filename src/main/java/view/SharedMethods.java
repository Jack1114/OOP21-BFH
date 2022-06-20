package view;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Shared Methods between Scenes.
 */
public interface SharedMethods {
	
	/**
	 * Add a custom cursor.
	 * @param scene 
	 */
	public void setCursor(Scene scene);
	
	/**
	 * Add drag method to an undecorated Scene.
	 * @param root
	 * @param stage
	 */
	public void dragScene(Parent root, Stage stage);
}
