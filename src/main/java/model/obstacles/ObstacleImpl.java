package model.obstacles;

import javafx.util.Pair;

import java.util.Random;

public class ObstacleImpl implements Obstacle{

    private Pair<Integer, Integer> obstPosition;
    private int x;
    private int y;
    private ObstacleType randObstType;
    private Random randomPos = new Random();
    private ObstacleType obstType;

    public ObstacleImpl(){

    }

    @Override
    public Pair<Integer, Integer> getPosition() {
        return this.obstPosition;
    }

    //TODO: controllare le posizioni!
    public void setPosition() {
        this.x = randomPos.nextInt(getSizeArena);
        this.y = randomPos.nextInt(getSizeArena);
        obstPosition = new Pair<>(x, y);
    }

    public void setType() {
        obstType = ObstacleType.getRandomType();
    }


    @Override
    public ObstacleType getObstacleType() {
        return this.obstType;
    }

}
