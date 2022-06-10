package controller.obstacles;
import java.util.Random;

import model.player.Pair;

public class ObstacleImpl implements Obstacle {

	private final Pair<Integer, Integer> obstaclePos;

	private final Type obstacleType;
	
	public ObstacleImpl(final Pair<Integer, Integer> pos, Type type){
		this.obstaclePos = pos;
		this.obstacleType = type;
	}

	public Pair<Integer, Integer> getObstaclePos(){
		return this.obstaclePos;
	}
	

	@Override
	public Obstacle.Type getObstacleType() {
		// TODO Auto-generated method stub
		return this.obstacleType;
	}

}
