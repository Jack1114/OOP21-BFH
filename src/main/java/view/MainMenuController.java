package view;

import java.io.IOException;

import controller.globalGenerator.Global_Generator;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.ImageCursor;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.stage.Stage;


public class MainMenuController extends SharedMethodsImpl{
	
	
	

    @FXML
    private AnchorPane mainMenuPane;

    @FXML
    private Pane titlePane;
    @FXML
    private Button playBtn;
    @FXML
    private Button optionsBtn;
    @FXML
    private Button exitBtn;


    @FXML
    private void initialize() {

        final Image backgroundImage = new Image(getClass().getResourceAsStream("/images/background.png"));
        final BackgroundImage background = new BackgroundImage(backgroundImage, BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, null);

        
        mainMenuPane.setBackground(new Background(background));

        
        playBtn.setText("Play");
        optionsBtn.setText("How to play");
        exitBtn.setText("Exit");
    
    
        exitBtn.setOnAction(new EventHandler<ActionEvent>() {
        	@Override
        	public void handle(ActionEvent event) {
        		System.exit(0);
        	}
        });
    	
    }
	@FXML
	public void move(ActionEvent event) {       

    	final Parent root;   
    	Global_Generator gg = Global_Generator.getInstance();

    	try {
			root = FXMLLoader.load(getClass().getClassLoader().getResource("layout/gameLayout.fxml"));
	
			Scene gameLayout = new Scene(root, 900, 650);
			Stage secondaryStage = (Stage) mainMenuPane.getScene().getWindow();
			
			setCursor(gameLayout);
			secondaryStage.setScene(gameLayout);
			secondaryStage.show();
			dragScene(root, secondaryStage);

	    	

	
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			 System.out.println("Errore");
		}
    	gg.play();

	}
	
}

