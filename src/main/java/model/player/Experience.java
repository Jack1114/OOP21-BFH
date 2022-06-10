package model.player;

public class Experience {
	private static final int MAX_EXP_POINTS = 70;
	private static int DEFAULT_EXP_POINTS=0;
	private int level=1;
	private int expPoints; //punti che l’eroe guadagna quando uccide un nemico

	public Experience() {
		this.expPoints=DEFAULT_EXP_POINTS;
	}
	/**
	 * 
	 * @param newExpPoints
	 * experience points
	 */
	public void setExpPoints(int newExpPoints) {
		this.expPoints += newExpPoints;
	}
	/**
	 * @return expPoits
	 */
	
	public int getExpPoints() {
		return this.expPoints;
	}

	/**
	 * @return player's level
	 */
	public int getLevel() {
		return level;	
	}
	/**
	 *  add player's level
	 */
	public boolean addLevel() {
		if(checkLevel()) {
			level++; //level up
			return true;
		}
		return false;
	}
	/**
	 * check if the player has enough experience points to add level
	 * @return boolean
	 */
	
	private boolean checkLevel() {
		return this.expPoints >= MAX_EXP_POINTS * level;
	}
	

	/**
	 * @param exp
	 * add the player's experience points when the enemy dies
	 */
	public void gainExp(int exp) { 
		expPoints+=exp;
		addLevel();		
	}

}
