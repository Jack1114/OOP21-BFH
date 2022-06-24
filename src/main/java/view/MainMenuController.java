package view;

import java.io.IOException;
import java.util.Optional;

import controller.globalGenerator.GlobalGenerator;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * Intro View of the Application, for aesthetic purposes.
 */
public class MainMenuController extends SharedMethodsImpl{
	
	
	//GlobalGenerator gg = GlobalGenerator.getInstance();

    @FXML
    private AnchorPane mainMenuPane;
    @FXML
    private Pane titlePane;
    @FXML
    private Button playBtn;
    @FXML
    private Button howtoplayBtn;
    @FXML
    private Button exitBtn;


    @FXML
    private void initialize() {
    	

        final Image backgroundImage = new Image(getClass().getResourceAsStream("/images/background.png"));
        final BackgroundImage background = new BackgroundImage(backgroundImage, BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, null);

        mainMenuPane.setBackground(new Background(background));

        
        playBtn.setText("Play");
        howtoplayBtn.setText("How to play");
        exitBtn.setText("Exit");
        
        //How To Play Dialog handler
        howtoplayBtn.setOnAction(new EventHandler<ActionEvent>(){
        	public void handle(ActionEvent event) {
        		Alert alert = new Alert(AlertType.CONFIRMATION);
        		alert.setGraphic(null);
        		alert.setHeaderText(null);
        		alert.initStyle(StageStyle.UNDECORATED); 

        		DialogPane dialogPane = alert.getDialogPane();
        		dialogPane.getStylesheets().add(
        				getClass().getResource("/assets/menuLayout.css").toExternalForm());
    	  
        		dialogPane.getStyleClass().add("dialogPane");
          
        		alert.getDialogPane().setPrefSize(1250, 650);
        		ButtonType accept = new ButtonType("Okay, I understood!");

        		alert.getButtonTypes().setAll(accept);

        		Optional<ButtonType> result = alert.showAndWait();
        		if (result.get() == accept){
        			alert.close();
        		} 
        	}
        });
        
        //Exit button handler
        exitBtn.setOnAction(new EventHandler<ActionEvent>() {
        	@Override
        	public void handle(ActionEvent event) {
        		System.exit(0);
        	}
        });
    }

    /**
     * Moves to the next Scene
     * @param event
     */
	@FXML
	public void move(ActionEvent event) {       

    	final Parent root;   

    	try {
			root = FXMLLoader.load(getClass().getClassLoader().getResource("layout/gameLayout.fxml"));
	
			Scene gameLayout = new Scene(root, 900, 650);
			Stage secondaryStage = (Stage) mainMenuPane.getScene().getWindow();
			
			setCursor(gameLayout);
			secondaryStage.setScene(gameLayout);
			secondaryStage.show();
			dragScene(root, secondaryStage);

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}

