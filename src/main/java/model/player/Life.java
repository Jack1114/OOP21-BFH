package model.player;

/**
 * This Class manages the Player's Life and all the possible methods.
 */
public class Life {
	
	//Starting Life Points.
	private int MAX_LIFE_POINTS = 50; 
	private int lifePoints;
	
	public Life() {
		this.lifePoints=MAX_LIFE_POINTS;
	}

	public int getLifePoints() {
		return lifePoints;
	}
	

	public int getMaxLifePoints() {
		return MAX_LIFE_POINTS;
	}
	
	public void setMaxLifePoints(int lifeP) {
		MAX_LIFE_POINTS=lifeP;
	}
	
	public void setLifePoints(int newlifePoints) {
		if(checkLifePoints(newlifePoints)) {
			 lifePoints = newlifePoints;
		}else {
			 lifePoints = MAX_LIFE_POINTS;
		}
	}
	

	private boolean checkLifePoints(final int lifePoints) {
		return lifePoints < MAX_LIFE_POINTS;
	}
	
	public void resetLife() {
		lifePoints = getMaxLifePoints();
	}
	
	public boolean isAlive() {
		return this.lifePoints > 0;
	}
}
