package view;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.PrintStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.swing.JButton;
import javax.swing.JPanel;

import controller.globalGenerator.GlobalGenerator;
import controller.obstacles.Obstacle;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
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
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.DialogPane;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import model.player.Pair;
import model.player.Player;
import model.abilities.Ability;

public class GameLayoutController extends SharedMethodsImpl{
	
	Map<Label,Pair<Integer,Integer>> mapjbtopos = new HashMap<>();
	Map<Pair<Integer,Integer>,Label> mappostojb = new HashMap<>();
	Player player;
	GlobalGenerator gg;
	List<Pair<Integer, Pair<Integer, Integer>>> En_With_ID;
	List<Obstacle> obstacles;
	private final static int GOLD=25;
	
	
	
	
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
		  
		 

		  
		  myfunction();

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
	  

	    @FXML
		public void skillA(ActionEvent event) {
	
			if (gg.player.getGold().getGold_points()>=GOLD) {
				gg.abilityManager.getAbilityOfType(Ability.Type.ELIXIR_OF_LIFE).apply();
				update();
				System.out.println("Using " + gg.abilityManager.getAbilityOfType(Ability.Type.ELIXIR_OF_LIFE).getName());
	    		gg.player.getGold().setGold_points(gg.player.getGold().getGold_points()-GOLD);  			
			}else {
				System.out.println("You don't have enough Gold");
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
				System.out.println("You don't have any Double Attack left");

			}
			update();}
	  
	    
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

	  private void myfunction() {
		  
		  GlobalGenerator.setInstance(this);
		  
		  gg = GlobalGenerator.getInstance();
		  
		  gg.generation();
		  
	        System.setOut(new PrintStream(System.out) {
	            @Override
	            public void write(byte[] buf, int off, int len) {
	                super.write(buf, off, len);

	                String msg = new String(buf, off, len);

	                combatHistory.setText(combatHistory.getText() + msg);

			            	combatHistory.selectPositionCaret(combatHistory.getLength()); 
			            	combatHistory.deselect(); 
			        		combatHistory.setWrapText(true);
			        		combatHistory.setFocusTraversable(false);
			        		combatHistory.setEditable(false);
					    	
		
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
		  
		  
		
		  GridPane panel = new GridPane();
	    	
			mapHolder.getChildren().add(panel);
			

	        
	        for (int row = 0; row < 12; row++) {
	            for (int col = 0; col < 10; col++) {
	            	var pos = new Pair<>(col,row);
	                Label jb = new Label();
	                jb.setMinSize(50, 50);
	                jb.setStyle("-fx-background-image: url(/images/ground.png);");
	                GridPane.setRowIndex(jb, row);
	                GridPane.setColumnIndex(jb, col);
	                mapjbtopos.put(jb, pos);
	                mappostojb.put(pos,jb);
	                panel.getChildren().addAll(jb);
	                panel.setStyle("-fx-border-insets: 5px; -fx-padding: 10 3 5 3;");
	                //System.out.println("Ho stampato");
	            }
	        }
	        
	        update();
	        gg.play();
	        
	      
	}

	public void update() {

			updateHeroStats();
					
			int HeroX = gg.player.getPlayerPosition().getX();
			int HeroY = gg.player.getPlayerPosition().getY();

			int ID=0;
			En_With_ID = gg.getInstance().enemyposwithID;
			obstacles = gg.getInstance().obstacles;	
			List<Integer> skipenemy = gg.skipenemy;
			
			mappostojb.forEach((pos,jb)->{
		
				jb.setStyle("-fx-background-image: url(/images/ground.png);-fx-background-size: 100% 100%;");
				jb.setGraphic(null);
				//Image ground = new Image(getClass().getResourceAsStream("/images/ground.png"));
				//jb.setGraphic(new ImageView((ground)));

					// calcolo rapido per avere l' ID solo per visualizzarlo a schermo 
					En_With_ID.forEach(pair->{
						//System.out.println("ho trovato un nemico da aggiungere");
						if(pair.getY().getX()==pos.getX() && pair.getY().getY()==pos.getY()){
							//System.out.println("setto il contenuto del pulsante");
							
							//TODO: controllare che il nemico sia morto e cancellarlo
							if(skipenemy.contains(pair.getX())) {
								jb.setStyle("-fx-background-image: url(/images/ground.png);-fx-background-size: 100% 100%;");
								jb.setGraphic(null);
                            }else if (pair.getX() == 0) {
								Image enemy0 = new Image(getClass().getResourceAsStream("/images/enemy0.png"));
								   jb.setGraphic(new ImageView((enemy0)));
								   jb.setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
								   jb.setAlignment(Pos.CENTER);
								
							}else if (pair.getX() == 1) {
								Image enemy1 = new Image(getClass().getResourceAsStream("/images/enemy1.png"));
								   jb.setGraphic(new ImageView((enemy1)));
								   jb.setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
								   jb.setAlignment(Pos.CENTER);
								
							} else if (pair.getX() == 2) {
								Image enemy2 = new Image(getClass().getResourceAsStream("/images/enemy2.png"));
								   jb.setGraphic(new ImageView((enemy2)));
								   jb.setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
								   jb.setAlignment(Pos.CENTER);
								
							}
						}
					});
					
					
					obstacles.forEach(obst->{
						if(obst.getObstaclePos().getX()==pos.getX() && obst.getObstaclePos().getY()==pos.getY()){
							if(obst.getObstacleType().equals(Obstacle.Type.POOL)) {
								jb.setStyle("-fx-background-image: url(/images/mud.png); -fx-background-size: 100% 100%;");
								//Image pool = new Image(getClass().getResourceAsStream("/images/mud.png"));
								//jb.setGraphic(new ImageView((pool)));
								//jb.setStyle("-fx-background-image: url(/images/mud.png); -fx-background-size: 100% 100%; -fx-alignment: CENTER; ");


							}
							if(obst.getObstacleType().equals(Obstacle.Type.ROCK)) {
								jb.setStyle("-fx-background-image: url(/images/stone.png); -fx-background-size: 100% 100%;");

							}
						}
					});
					
					
					if(HeroX==pos.getX() && HeroY==pos.getY()){
						
						Image hero = new Image(getClass().getResourceAsStream("/images/hero.png"));
					   jb.setGraphic(new ImageView((hero)));
					   jb.setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
					   jb.setAlignment(Pos.CENTER);
						//jb.setStyle("-fx-background-image: url(/images/ground.png); -fx-alignment: CENTER; -fx-graphic: url(/images/hero.png); -fx-content-display: graphic-only;");
					}
					
					
					
					
			});
			if(gg.player.getLife().getLifePoints()<=0) {   
				System.exit(0);
			}
		}

	  private void updateHeroStats() {

		  //		System.out.println("Ho uppato le stats");
		  
		  //		System.out.println("--- blocco 7777 ---");
		  
		  //		System.out.println(gg.player);

		  
	    	statsArea.setText("Experience = " + gg.player.getExperience().getExpPoints()+ "\n"+
					"Life Points = " + gg.player.getLife().getLifePoints() +" / "+ gg.player.getLife().getMaxLifePoints() + "\n" +
					"Level =" + gg.player.getExperience().getLevel() + "\n" +
					"Action= " + gg.player.getPlayer_action().getAvailableActions());
	    	
	    	//		System.out.println("--- blocco 7788 ---");
	    	
	    	moneyHolder.setText("Gold = " + gg.player.getGold().getGold_points());
	    	
	    	//		System.out.println("--- blocco 8888 ---");
	    	
	    	


		}

	 

}
