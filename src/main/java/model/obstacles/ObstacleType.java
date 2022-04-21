package model.obstacles;

public enum ObstacleType {

    MUD(""),

    FENCE(""),

    ROCK("");

    private final String descr;

    ObstacleType(final String descr){
        this.descr = descr;
    }

    public final String getDescr(){
        return this.descr;
    }

}
