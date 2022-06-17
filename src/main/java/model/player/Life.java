package model.player;

public class Life {
	private int MAX_LIFE_POINTS=50; //punti che il personaggio ha dalla partenza del gioco
	private int lifePoints;
	
	public Life() {
		this.lifePoints=MAX_LIFE_POINTS;
	}
	 /**
	  *
	  * @return lifePoints
	  */
	public int getLifePoints() {
		return lifePoints;
	}
	/**
	 * @return MAX_LIFE_POINTS
	 */
	public int getMaxLifePoints() {
		return MAX_LIFE_POINTS;
	}
	public void setMaxLifePoints(int lifeP) {
		MAX_LIFE_POINTS=lifeP;
	}
	
	/**
	 * update the player's healt points
	 * @param newlifePoints
	 */
	public void setLifePoints(int newlifePoints) {
		if(checkLifePoints(newlifePoints)) {
			 lifePoints = newlifePoints;
		}else {
			 lifePoints = MAX_LIFE_POINTS;
		}
	}
	
	/**
	 * @param lifePoints
	 * @return true if the player's lifePoints è massimi or false if not
	 */

	private boolean checkLifePoints(final int lifePoints) {
		return lifePoints < MAX_LIFE_POINTS;
	}
	
	/**
	 * update lifePoints to MAX_LIFE_POINTS
	 */
	
	public void resetLife() {
		lifePoints=getMaxLifePoints();
	}
	/**
	 * check if the player is alive
	 * @return true if the player is alife or false if not
	 */
	public boolean isAlive() {
		return this.lifePoints>0;
	}
}
