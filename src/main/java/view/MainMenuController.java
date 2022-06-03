package view;

import java.io.IOException;

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


public class MainMenuController {

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

       	
 
	try {
		 root = FXMLLoader.load(getClass().getClassLoader().getResource("layout/gameLayout.fxml"));
	
			Scene gameLayout = new Scene(root, 900, 650);
			Stage secondaryStage = (Stage) mainMenuPane.getScene().getWindow();
			
			 Image image = new Image("images/cursorHand_grey.png");  //pass in the image path
			 gameLayout.setCursor(new ImageCursor(image));
			 gameLayout.getStylesheets().add(getClass().getResource("/assets/gameLayout.css").toExternalForm());
			secondaryStage.setScene(gameLayout);
			secondaryStage.show();
		       	
		    root.setOnMousePressed(pressEvent -> {
		        root.setOnMouseDragged(dragEvent -> {
		        	secondaryStage.setX(dragEvent.getScreenX() - pressEvent.getSceneX());
		        	secondaryStage.setY(dragEvent.getScreenY() - pressEvent.getSceneY());
		        });
		    });
	
	
	
	
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	



		
		}
}

