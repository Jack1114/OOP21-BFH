package view;


import controller.StateSetter;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;


public class OptionsView {
    private static final int HEIGHT = 650;
    private static final int WIDTH = 1080;

    private static final int PLAY_LAYOUTX = 310;
    private static final int PLAY_LAYOUTY = 450;
    
    private static final int OPTIONS_LAYOUTX = 325;
    private static final int OPTIONS_LAYOUTY = 510;
    
    private static final int EXIT_LAYOUTX = 300;
    private static final int EXIT_LAYOUTY = 570;

    private final AnchorPane pane;
    private final Scene scene;
    
    /**
     * Construct a {@link MenuView}.
     */
    public OptionsView() {
        this.pane = new AnchorPane();
        this.scene = new Scene(pane, WIDTH, HEIGHT);
        createBackGround();
        createButton();
    }
    
    /**
     * 
     * @return the scene.
     */
    	public final Scene getOptionsScene() {
            return this.scene;
        }
    	
    private void createButton() {
        createPlayButton();
        createOptionsButton();
        createExitButton();
		
	}


	private void createBackGround() {
        final Image backgroundImage = new Image(getClass().getResourceAsStream("/images/background.png"), WIDTH, HEIGHT,
                false, true);

        final BackgroundImage background = new BackgroundImage(backgroundImage, BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, null);

        pane.setBackground(new Background(background));

		
	}

	
    private void createPlayButton() {
        final ButtonStyle playButton = new ButtonStyle("Prova Prova");
        playButton.setLayoutX(PLAY_LAYOUTX);
        playButton.setLayoutY(PLAY_LAYOUTY);
        pane.getChildren().add(playButton);

        playButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(final ActionEvent event) {
                StateSetter.state = StateSetter.StateSetterEnum.GAME;
                StateSetter.change = true;
                StateSetter.init = true;
            }
        });
    }
    
	private void createOptionsButton() {
        final ButtonStyle playButton = new ButtonStyle("OPTIONS");
        playButton.setLayoutX(OPTIONS_LAYOUTX);
        playButton.setLayoutY(OPTIONS_LAYOUTY);
        pane.getChildren().add(playButton);

        /* TO DO
      playButton.setOnAction(new EventHandler<ActionEvent>() {
       
		
	}
    */
	}
	private void createExitButton() {
        final ButtonStyle playButton = new ButtonStyle("EXIT");
        playButton.setLayoutX(EXIT_LAYOUTX);
        playButton.setLayoutY(EXIT_LAYOUTY);
        pane.getChildren().add(playButton);

        playButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(final ActionEvent event) {
            		System.exit(0);
            }
        });
		
	}




}
