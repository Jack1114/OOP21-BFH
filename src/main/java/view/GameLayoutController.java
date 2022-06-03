package view;

import java.io.IOException;
import java.util.Optional;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DialogPane;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class GameLayoutController {
	  @FXML
	  AnchorPane gameLayout;
	  @FXML
	  Pane charImage;
	  @FXML
	  TextArea combatHistory;
	  @FXML
	  Pane skillA;
	  @FXML
	  Pane skillB;
	  @FXML
	  TextArea statsArea;
	  @FXML
	  GridPane inventory;
	  @FXML
	  Label moneyHolder;
	  @FXML
	  Pane mapHolder;
	  @FXML
	  Pane btnHolder;
	  @FXML
	  Button up, left, right, down, stop;
	  @FXML
	  Button skillABtn, skillBBtn;
	  
	  @FXML
	    private void initialize() {
		  
		  /**
		   * Escape event to quit the game.
		   */
		  gameLayout.setOnKeyReleased(event -> {
		      if(event.getCode() == KeyCode.ESCAPE) {
		    	  Alert alert = new Alert(AlertType.CONFIRMATION);
		    	  
		    	  DialogPane dialogPane = alert.getDialogPane();
		    	  dialogPane.getStylesheets().add(
		    	  getClass().getResource("/assets/gameLayout.css").toExternalForm());
		    	  dialogPane.getStyleClass().add("myDialog");
		    	  
		    		alert.setHeaderText(null);
				    alert.setContentText("Vuoi arrenderti?");
				    Optional<ButtonType> result = alert.showAndWait();
				    if (result.isPresent()) {
				        if (result.get() == ButtonType.OK) {
				        	System.exit(0);
				        }
				        if (result.get() == ButtonType.CANCEL) {
				            alert.close();
				        }
				    }
				
		      }
		    });
		  
		
		  
	
	        
	    
	    	
	    }

	  


	  

}
