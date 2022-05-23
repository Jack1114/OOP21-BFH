package model.obstacles;

import controller.entities.Pair;

public interface Obstacle {


	enum Type{
		ROCK,
		MUD;
	}
    /**
     *
     * @return obstacle's position
     */
    public Pair<Integer, Integer> getObstaclePos();
    
    /**
     * 
     * @return obstacle's type
     */
    public Type getObstacleTyep();
}
