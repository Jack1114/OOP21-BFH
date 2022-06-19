package controller.obstacles;

import java.util.List;
import java.util.Random;

import controller.globalGenerator.GlobalGenerator;
import model.obstacles.Obstacle;
import model.obstacles.ObstacleImpl;
import model.player.Pair;

public class ObstacleGenerator {

	private static final int NUM_POOL_OBST = 10;
	private static final int NUM_ROCK_OBST = 10;
    private final List<Obstacle> obstacles;
    private GlobalGenerator gg = GlobalGenerator.getInstance(); 

    public ObstacleGenerator(List<Obstacle> obstacles){
    	this.obstacles = obstacles;
    }

	/**
	 * Generate n Obstacles in random position
	 */
	public void generateObstacles(){
		for (int i = 0; i < NUM_ROCK_OBST; i++){
			addObstacle(createObstacle(gg.randPositionObstacles(gg.getGridSizeX(),gg.getGridSizeY()), ObstacleImpl.Type.ROCK));
		}
		for (int i = 0; i < NUM_POOL_OBST; i++){
			addObstacle(createObstacle(gg.randPositionObstacles(gg.getGridSizeX(),gg.getGridSizeY()), ObstacleImpl.Type.POOL));
		}
	}
	
	private void addObstacle(Obstacle obstacle){
		obstacles.add(obstacle);
	}

	private Obstacle createObstacle(final Pair<Integer, Integer> pos, final ObstacleImpl.Type type){
		return new ObstacleImpl(pos, type);
	}
	
}
