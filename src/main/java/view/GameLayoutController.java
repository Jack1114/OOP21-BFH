package view;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.swing.JButton;
import javax.swing.JPanel;

import controller.globalGenerator.Global_Generator;
import controller.obstacles.Obstacle;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
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
import model.player.Pair;
import model.player.Player;
import model.enemies.GUI;

public class GameLayoutController extends SharedMethodsImpl{
	
	Map<Label,Pair<Integer,Integer>> mapjbtopos = new HashMap<>();
	Map<Pair<Integer,Integer>,Label> mappostojb = new HashMap<>();
	Player player;
	Global_Generator gg = Global_Generator.getInstance();
	List<Pair<Integer, Pair<Integer, Integer>>> En_With_ID;
	List<Obstacle> obstacles;
	
   
	
	
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
		  
		  	this.player = gg.player;
	    	player.toString();	
	    	int HeroX = player.getPlayerPosition().getX();
	    	int HeroY = player.getPlayerPosition().getY();
	    	
	    	statsArea.setText("Experience = " + player.getExperience().getExpPoints()+ "\n"+
	    						"HP = " + player.getLife().getLifePoints() +" / "+ player.getLife().getMaxLifePoints() + "\n" +
	    						" LV =" + player.getExperience().getLevel() + "\n" +
	    						"Action= " + player.getPlayer_action().getMaxActions());
	    	
	    	moneyHolder.setText("Gold = " + player.getGold().getGold_points()); 	
	    
	    
		  combatHistory.setMouseTransparent(true);
		  combatHistory.setFocusTraversable(false);
	    	statsArea.setMouseTransparent(true);
		  statsArea.setFocusTraversable(false);
		  
		  
	    	GridPane panel = new GridPane();
	    	
			mapHolder.getChildren().add(panel);
			

	        
	        for (int row = 0; row < 12; row++) {
	            for (int col = 0; col < 10; col++) {
	            	var pos = new Pair<>(col,row);
	                Label jb = new Label();
	                jb.setMinSize(50, 50);
	                jb.setStyle("-fx-border-color: black;");
	                GridPane.setRowIndex(jb, row);
	                GridPane.setColumnIndex(jb, col);
	                mapjbtopos.put(jb, pos);
	                mappostojb.put(pos,jb);
	                panel.getChildren().addAll(jb);
	                panel.setStyle("-fx-border-insets: 5px; -fx-padding: 10 3 5 3;");
	                System.out.println("Ho stampato");
	            }
	        }
	        
	        update();
	       


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

	  public void update() {
			System.out.println("entro dentro update");

			updateHeroStats();
					
			int HeroX = player.getPlayerPosition().getX();
			int HeroY = player.getPlayerPosition().getY();
			int ID=0;
			En_With_ID = gg.getInstance().enemyposwithID;
			obstacles = gg.getInstance().obstacles;	
			mappostojb.forEach((pos,jb)->{
		
					//jb.setText("X"+pos.getX()+"Y"+pos.getY());
					// calcolo rapido per avere l' ID solo per visualizzarlo a schermo 
					En_With_ID.forEach(pair->{
						//System.out.println("ho trovato un nemico da aggiungere");
						if(pair.getY().getX()==pos.getX() && pair.getY().getY()==pos.getY()){
							//System.out.println("setto il contenuto del pulsante");
							jb.setText(""+pair.getX());
						}
					});
					
					
					obstacles.forEach(obst->{
						if(obst.getObstaclePos().getX()==pos.getX() && obst.getObstaclePos().getY()==pos.getY()){
							if(obst.getObstacleType().equals(Obstacle.Type.POOL)) {
								jb.setText("P");

							}
							if(obst.getObstacleType().equals(Obstacle.Type.ROCK)) {
								jb.setText("R");

							}
						}
					});
					
					if(HeroX==pos.getX() && HeroY==pos.getY()){
						jb.setText("H");
					}
					
					
					
					
			});
		}

	  private void updateHeroStats() {

		  System.out.println("Ho uppato le stats");
	    	statsArea.setText("Experience = " + player.getExperience().getExpPoints()+ "\n"+
					"HP = " + player.getLife().getLifePoints() +" / "+ player.getLife().getMaxLifePoints() + "\n" +
					" LV =" + player.getExperience().getLevel() + "\n" +
					"Action= " + player.getPlayer_action().getMaxActions());
	    	
	    	moneyHolder.setText("Gold = " + player.getGold().getGold_points());


		}

	 

}
