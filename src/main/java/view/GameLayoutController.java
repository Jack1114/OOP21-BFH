package view;

import java.io.PrintStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import controller.globalGenerator.GlobalGenerator;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.DialogPane;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.*;
import javafx.stage.StageStyle;
import model.player.Pair;
import model.player.Player;
import model.abilities.Ability;
import model.obstacles.Obstacle;

/**
 * Core View of the Application, connects the graphic components of JavaFX to the model.
 */
public class GameLayoutController extends SharedMethodsImpl{
	
	Map<Label,Pair<Integer,Integer>> mapjbtopos = new HashMap<>();
	Map<Pair<Integer,Integer>,Label> mappostojb = new HashMap<>();
	Player player;
	GlobalGenerator gg;
	List<Pair<Integer, Pair<Integer, Integer>>> En_With_ID;
	List<Obstacle> obstacles;
	
	private final static int GOLD=25;
	
	//FXML Components.
	  @FXML
	  AnchorPane gameLayout;
	  @FXML
	  Pane charImage;
	  @FXML
	  Label charImageHolder, skillAHolder, skillBHolder;
	  @FXML
	  TextArea combatHistory;
	  @FXML
	  Pane skillA;
	  @FXML
	  Pane skillB;
	  @FXML
	  TextArea statsArea;
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
	  DialogPane dialogPane;
		
	  @FXML
	  private void initialize() {
	  
		  gameStarter();
		  
		  combatHistory.setEditable(false);
		  combatHistory.setWrapText(true);
		  combatHistory.setFocusTraversable(false);
		  statsArea.setMouseTransparent(true);
		  statsArea.setFocusTraversable(false);
  		
		   
		  /**
		   * Escape event to quit the game.
		   */
		  gameLayout.setOnKeyReleased(event -> {
		      if(event.getCode() == KeyCode.ESCAPE) {
				ButtonType surrend = new ButtonType("Yes I want to surrend.");
				ButtonType fight = new ButtonType("No, I will keep fighting!");  

			    Alert alert = new Alert(AlertType.CONFIRMATION,
				            "Do you want to give up? ",
				            surrend,
				            fight);

     	    	DialogPane dialogPane = alert.getDialogPane();

		    	dialogPane.getStylesheets().add(
		    	getClass().getResource("/assets/gameLayout.css").toExternalForm());
		    	dialogPane.getStyleClass().add("myDialog");
		    	dialogPane.setGraphic(null);		   
		    	alert.initStyle(StageStyle.UNDECORATED);  
		    	alert.setHeaderText(null);

		    	Optional<ButtonType> result = alert.showAndWait();
				if (result.isPresent()) {
				    if (result.get() == surrend) {
				        System.exit(0);
				    }
				    if (result.get() == fight) {
				        alert.close();
				    }
				}
				
		     }
		 }); 	
	}
	  // All the FXML Buttons handlers.
	    @FXML
		public void skillA(ActionEvent event) {
			if (gg.player.getGold().getGold_points() >= GOLD) {
				gg.abilityManager.getAbilityOfType(Ability.Type.ELIXIR_OF_LIFE).apply();
				update();
				System.out.println("Using " + gg.abilityManager.getAbilityOfType(Ability.Type.ELIXIR_OF_LIFE).getName());
	    		gg.player.getGold().setGold_points(gg.player.getGold().getGold_points()-GOLD);  			
			} else {
				System.out.println("You don't have enough Gold!");
			}
			update();
	    }
	  
	    @FXML
		public void skillB(ActionEvent event) {
			if (gg.abilityManager.isAvailable(Ability.Type.DOUBLE_ATTACK)) {
				gg.abilityManager.getAbilityOfType(Ability.Type.DOUBLE_ATTACK).apply();
				update();
				System.out.println("Using " + gg.abilityManager.getAbilityOfType(Ability.Type.DOUBLE_ATTACK).getName());
				gg.abilityManager.remove(Ability.Type.DOUBLE_ATTACK);
	    		gg.player.getPlayer_action().removeAction();
				System.out.println("Now you have " + gg.abilityManager.getSize(Ability.Type.DOUBLE_ATTACK) + " left"); 
			}else {
				System.out.println("You don't have any Double Attack left!");
			}
			update();
		}
	  
	    @FXML
		public void moveUP(ActionEvent event) {
    		update();
	    	gg.playerMovement.up();
    		gg.player.getPlayer_action().removeAction();
    		update();
    		gg.playerTurn();
	    }
	  
	    @FXML
		public void moveLEFT(ActionEvent event) {
    		update();
	    	gg.playerMovement.left();
    		gg.player.getPlayer_action().removeAction();
    		update();
    		gg.playerTurn();
	    }
	    
	    @FXML
		public void moveRIGHT(ActionEvent event) {
    		update();
	    	gg.playerMovement.right();
    		gg.player.getPlayer_action().removeAction();
    		update();
    		gg.playerTurn();
	    }
	    @FXML
		public void moveDOWN(ActionEvent event) {
    		update();
	    	gg.playerMovement.down();
    		gg.player.getPlayer_action().removeAction();
    		update();
    		gg.playerTurn();
	    }
	    
	    @FXML
		public void moveSTOP(ActionEvent event) {
	    	gg.playerMovement.stop();
    		gg.player.getPlayer_action().removeAction();
    		update();
    		gg.playerTurn();
	    }

	  /**
	   * Calls all the Entities generated {@link #gg}, the Global Generator
	   * and update the interface accordingly. 
	   * 	   
	   */
	  private void gameStarter() {
		  
		GlobalGenerator.setInstance(this);
		  
		gg = GlobalGenerator.getInstance();	  
		gg.generation();
	    
	    //Method to print all the System.out.println() in the correct @FXML component.
		//Called here so it can start printing from the very first update of the View.
        System.setOut(new PrintStream(System.out) {
            @Override
            public void write(byte[] buf, int off, int len) {
                super.write(buf, off, len);

                String msg = new String(buf, off, len);

                combatHistory.setText(combatHistory.getText() + msg);
		        combatHistory.selectPositionCaret(combatHistory.getLength()); 
		        combatHistory.deselect(); 
		    }
        });    
        
		this.player = gg.player;
		player.toString();	
	    int HeroX = player.getPlayerPosition().getX();
	    int HeroY = player.getPlayerPosition().getY();
	    	
	    statsArea.setText("Experience = " + gg.player.getExperience().getExpPoints()+ "\n"+
	    				  "HP = " + gg.player.getLife().getLifePoints() +" / "+ player.getLife().getMaxLifePoints() + "\n" +
	    				  " LV =" + gg.player.getExperience().getLevel() + "\n" +
	    				  "Action= " + gg.player.getPlayer_action().getMaxActions());
	    moneyHolder.setText("Gold = " + gg.player.getGold().getGold_points());
		  
	    //The graphical part of the Arena is drawn here.
		GridPane panel = new GridPane();
		mapHolder.getChildren().add(panel);
		
		for (int row = 0; row < 12; row++) {
			for (int col = 0; col < 10; col++) {
				var pos = new Pair<>(col,row);
	            Label square = new Label();
	            square.setMinSize(50, 50);
	            square.setStyle("-fx-background-image: url(/images/ground.png);");
	            GridPane.setRowIndex(square, row);
	            GridPane.setColumnIndex(square, col);
	            mapjbtopos.put(square, pos);
	            mappostojb.put(pos,square);
	            panel.getChildren().addAll(square);
	            panel.setStyle("-fx-border-insets: 5px; -fx-padding: 10 3 5 3;");
	            }
	    }
	        
	    update();
	    gg.play();
	     
	}

	 /**
	  * Updates the View by drawing the correct component.
	  */
	public void update() {

			updateHeroStats();
					
			int HeroX = gg.player.getPlayerPosition().getX();
			int HeroY = gg.player.getPlayerPosition().getY();

			int ID=0;
			En_With_ID = gg.getInstance().enemyposwithID;
			obstacles = gg.getInstance().obstacles;	
			List<Integer> skipenemy = gg.skipenemy;
			
			//Checks all the squares in the GridPane.
			mappostojb.forEach((pos,square)->{
		
				square.setStyle("-fx-background-image: url(/images/ground.png);-fx-background-size: 100% 100%;");
				square.setGraphic(null);
					//Draw the Enemies.
					En_With_ID.forEach(pair->{
						if(pair.getY().getX() == pos.getX() && pair.getY().getY() == pos.getY()){
							if(skipenemy.contains(pair.getX())) {
								square.setStyle("-fx-background-image: url(/images/ground.png);-fx-background-size: 100% 100%;");
								square.setGraphic(null);
                            }else if (pair.getX() == 0) {
								Image enemy0 = new Image(getClass().getResourceAsStream("/images/enemy0.png"));
								   square.setGraphic(new ImageView((enemy0)));
								   square.setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
								   square.setAlignment(Pos.CENTER);	
							}else if (pair.getX() == 1) {
								Image enemy1 = new Image(getClass().getResourceAsStream("/images/enemy1.png"));
								   square.setGraphic(new ImageView((enemy1)));
								   square.setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
								   square.setAlignment(Pos.CENTER);			
							} else if (pair.getX() == 2) {
								Image enemy2 = new Image(getClass().getResourceAsStream("/images/enemy2.png"));
								   square.setGraphic(new ImageView((enemy2)));
								   square.setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
								   square.setAlignment(Pos.CENTER);		
							}
						}
					});
					
					//Draw the Obstacles.
					obstacles.forEach(obst->{
						if(obst.getObstaclePos().getX()==pos.getX() && obst.getObstaclePos().getY()==pos.getY()){
							if(obst.getObstacleType().equals(Obstacle.Type.POOL)) {
								square.setStyle("-fx-background-image: url(/images/mud.png); -fx-background-size: 100% 100%;");
							}
							if(obst.getObstacleType().equals(Obstacle.Type.ROCK)) {
								square.setStyle("-fx-background-image: url(/images/stone.png); -fx-background-size: 100% 100%;");
							}
						}
					});
					
					//Draw the Player.
					if(HeroX==pos.getX() && HeroY==pos.getY()){
						Image hero = new Image(getClass().getResourceAsStream("/images/hero.png"));
					    square.setGraphic(new ImageView((hero)));
					    square.setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
					    square.setAlignment(Pos.CENTER);
					}
		
			});
			
			//Death Dialog
			if(gg.player.getLife().getLifePoints()<=0) {   
			   ButtonType defeat = new ButtonType("I will try harder next time!");
			   Alert alert = new Alert(AlertType.CONFIRMATION,
			            "Oh no, you died!",
			            defeat);

	    	  DialogPane dialogPane = alert.getDialogPane();
	    	  dialogPane.getStylesheets().add(
	    	  getClass().getResource("/assets/gameLayout.css").toExternalForm());
	    	  dialogPane.getStyleClass().add("myDialog");
	    	  dialogPane.setGraphic(null);
	   
	    	  alert.initStyle(StageStyle.UNDECORATED);  
	    	  alert.setHeaderText(null);
			    
	    	  Optional<ButtonType> result = alert.showAndWait();
			  if (result.get() == defeat) {
			      System.exit(0); 
			     }
			  }
	}

	/**
	 * Update the Stats fields in the View whenever they are changed.
	 */
	private void updateHeroStats() {

	    	statsArea.setText("Experience = " + gg.player.getExperience().getExpPoints()+ "\n"+
					"Life Points = " + gg.player.getLife().getLifePoints() +" / "+ gg.player.getLife().getMaxLifePoints() + "\n" +
					"Level =" + gg.player.getExperience().getLevel() + "\n" +
					"Action= " + gg.player.getPlayer_action().getAvailableActions());
	    	
	    	moneyHolder.setText("Gold = " + gg.player.getGold().getGold_points());	    	
	}
}
