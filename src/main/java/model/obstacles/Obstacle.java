package model.obstacles;

import model.player.Pair;

public interface Obstacle {


	enum Type{
		ROCK,
		POOL;
	}
	
    /**
     * @return obstacle's position
     */
    public Pair<Integer, Integer> getObstaclePos();
    
    /**
     * @return obstacle's type
     */
    public Type getObstacleType();
}
