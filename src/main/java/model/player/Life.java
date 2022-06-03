package model.player;

public class Life {
	private static final int MAX_LIFE_POINTS=70; //punti che il personaggio ha dalla partenza del gioco
	private int lifePoints;
	
	public Life() {
		this.lifePoints=MAX_LIFE_POINTS;
	}
	 /**
	  *
	  * @return player's health points
	  */
	public int getLifePoints() {
		return lifePoints;
	}
	/**
	 * update the player's healt points
	 * @param newlifePoints
	 */
	public int getMaxLifePoints() {
		return MAX_LIFE_POINTS;
	}

	public void setLifePoints(int newlifePoints) {
	 lifePoints=newlifePoints;
	}
	
	public void resetLife() {
		lifePoints=MAX_LIFE_POINTS;
	}
	/**
	 * check if the player is alive
	 * @return true if the player is alife or false if not
	 */
	public boolean isAlive() {
		return this.lifePoints>0;
	}
}
