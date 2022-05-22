package model.obstacles;

import controller.entities.Pair;

public interface Obstacle {


    /**
     *
     * @return obstacle's position
     */
    public Pair<Integer, Integer> getObstaclePos();

}
