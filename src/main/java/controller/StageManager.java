package controller;


import java.io.FileNotFoundException;

import javafx.application.Application;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import view.MenuView;
import view.SceneSwapperImpl;


public final class StageManager extends Application {

	private Stage stage;
	private SceneSwapperImpl swap;

    public static void main(final String... args) {
        Application.launch();
    }



    @Override
    public void start(final Stage primaryStage) throws FileNotFoundException  {
    	
    	//Loading external font before using it.
    	Font.loadFont(getClass().getResourceAsStream("/assets/munro.ttf"), 30);
    	this.stage = primaryStage;
    	swap = new SceneSwapperImpl(primaryStage); 
    	swap.addScene(StateSetter.StateSetterEnum.MENU.getName(), new MenuView().getMenuScene());
    	StateSetter.state = StateSetter.StateSetterEnum.MENU;
    	primaryStage.getIcons().add(new Image(getClass().getResourceAsStream("/images/icon.png")));
    	primaryStage.initStyle(StageStyle.UNDECORATED);
    	primaryStage.setResizable(false);    	
    	primaryStage.show();
    }
    
    private void changeScene() {
        swap.swapTo(StateSetter.state.getName());


    }
    /**
     * @return
     *          The current scene shown.
     */
    public final Scene getScene() {
        return this.swap.getCurrent();
    }

}
