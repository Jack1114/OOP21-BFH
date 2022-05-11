package model.obstacles;

import javafx.util.Pair;

public interface Obstacle {

    /**
     *
     * @return obstacle's position in the world.
     */
    Pair<Integer, Integer> getPosition();

    /**
     *
     * @return obstacle's type
     */
    ObstacleType getObstacleType();

    void generateObstacle();

    void setPosition();
    void setType();

}
