package view;

import java.io.IOException;
import java.util.Optional;

import controller.globalGenerator.GlobalGenerator;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.ImageCursor;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.stage.StageStyle;


public class MainMenuController extends SharedMethodsImpl{
	
	
	GlobalGenerator gg = GlobalGenerator.getInstance();

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
    
        
        optionsBtn.setOnAction(new EventHandler<ActionEvent>(){
        	
      public void handle(ActionEvent event) {
    	  
    	  
    	  Alert alert = new Alert(AlertType.CONFIRMATION);
    	  alert.setGraphic(null);
    	  alert.setHeaderText(null);
    	  alert.initStyle(StageStyle.UNDECORATED); 

       /*   final Image tutorial = new Image(getClass().getResourceAsStream("/images/howtoplay.png"));
          final BackgroundImage tutorialb = new BackgroundImage(tutorial, BackgroundRepeat.NO_REPEAT,
                  BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, null);
        */
          DialogPane dialogPane = alert.getDialogPane();
    	  dialogPane.getStylesheets().add(
    	  getClass().getResource("/assets/menuLayout.css").toExternalForm());
    	  
    	  dialogPane.getStyleClass().add("dialogPane");
          
        //  dialogPane.setBackground(new Background(tutorialb));
         
          alert.getDialogPane().setPrefSize(1250, 650);
    	  ButtonType buttonTypeOne = new ButtonType("Okay, I understood!");

    	  alert.getButtonTypes().setAll(buttonTypeOne);

    	  Optional<ButtonType> result = alert.showAndWait();
    	  if (result.get() == buttonTypeOne){
    	      alert.close();
    	  } 
    	  
    	  
      	}
        });
        
        
        
        
        
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
			
			setCursor(gameLayout);
			secondaryStage.setScene(gameLayout);
			secondaryStage.show();
			dragScene(root, secondaryStage);

	    	

	
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			 System.out.println("Errore");
		}
	}
	
}

