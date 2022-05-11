package model.obstacles;

public class ObstacleGenerator {

    private final ObstacleImpl obstacle = new ObstacleImpl();
    private final boolean success;

    public ObstacleGenerator(){
        this.success = false;
        while(!success) {
            generateObstacle();
        }

    }

    public boolean generateObstacle(){
        this.obstacle.setPosition();
        this.obstacle.setType();
        Global_Generator.obstacles_pos.add(obst_pos);
    }
}
