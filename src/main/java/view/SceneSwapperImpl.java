package view;


import java.util.HashMap;
import java.util.Map;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class SceneSwapperImpl implements SceneSwapper {

    private Stage main;
    private Map<String, Scene> scenes;

    /**
     * Constructor, no parameters because if stage not given creates new one with empty properties.
     */
    public SceneSwapperImpl() {
        this(new Stage());
    }
    /**
     * Constructor, uses as main the given stage.
     * @param stage
     */
    public SceneSwapperImpl(final Stage stage) {
        this.main = stage;

    }

    @Override
    public final void buildScene(final String name, final int w, final int h, final Parent root) {
        if (this.scenes == null) {
            this.scenes = new HashMap<>();
        }
        this.scenes.put(name, new Scene(root, w, h));
        if (this.main.getScene() == null) {
            this.swapTo(name);
        }
    }

    @Override
    public final void addScene(final String name, final Scene scene) {
        this.main.setScene(scene);
    }

    @Override
    public final void removeScene(final String name) {

    }

    //Return temporaneo
    @Override
    public final boolean swapTo(final String name) {
        Scene swap;
		return false;
 
    }

    @Override
    public final Scene getCurrent() {
        return this.main.getScene();
    }

    @Override
    public final Stage getMain() {
        return this.main;
    }



}
