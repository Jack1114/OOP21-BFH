package model.obstacles;

public interface Obstacle {

    /**
     *
     * @return obstacle's position in the world.
     */
    Pair<X, Y> getPosition();

    /**
     * Used to set obstacle's position.
     *
     * @param position new obstacle's position
     */
    void setPosition(Pair<X, Y> position);

    /**
     *
     * @return obstacle's type
     */
    ObstacleType getObstacleType();


}
