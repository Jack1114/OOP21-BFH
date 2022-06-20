package model.obstacles;

import model.player.Pair;

public interface Obstacle {


	enum Type{
		ROCK,
		POOL;
	}
	
    /**
     * @return Obstacle's position
     */
    public Pair<Integer, Integer> getObstaclePos();
    
    /**
     * @return Obstacle's type
     */
    public Type getObstacleType();
}
