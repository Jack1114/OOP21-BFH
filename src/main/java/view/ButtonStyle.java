package view;

import javafx.scene.control.Button;
import javafx.scene.text.Font;

/**
 * 
 * Defines standard button.
 *
 */
public class ButtonStyle extends Button {


    private static final int BUTTON_HEIGHT = 49;
    private static final int BUTTON_WIDTH = 190;


    /**
     * 
     * @param text the name of the button.
     */
    public ButtonStyle(final String text) {
        setText(text);
        setPrefHeight(BUTTON_HEIGHT);
        setPrefWidth(BUTTON_WIDTH);
        getStylesheets().add(getClass().getResource("/assets/general_graphic.css").toExternalForm());
    }


/*    private void setButtonPressedStyle() {

        setPrefHeight(BUTTON_HEIGHT_PRESSED);
        setLayoutY(getLayoutY() + 4);
    }

    private void setButtonReleasedStyle() {
        setPrefHeight(BUTTON_HEIGHT);
        setLayoutY(getLayoutY() - 4);
    }
*/

}
