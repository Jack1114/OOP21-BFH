package model.obstacles;

import java.util.Random;

public enum ObstacleType {

    MUD,

    FENCE,

    ROCK;

    /*
    private final String descr;

    ObstacleType(final String descr){

        this.descr = descr;
    }

    public final String getDescr(){

        return this.descr;
    }

    public static ObstacleType getRandomType() {
        Random random = new Random();
        return values()[random.nextInt(values().length)];
    }
    */
}
