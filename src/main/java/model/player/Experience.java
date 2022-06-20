package model.player;

/**
 * Manages the Experience a Player gains when killing an Enemy.
 */
public class Experience {
	
	private static final int MAX_EXP_POINTS = 50;
	private static int DEFAULT_EXP_POINTS = 0;
	private int level = 1;
	private int expPoints; 

	public Experience() {
		this.expPoints=DEFAULT_EXP_POINTS;
	}

	public void setExpPoints(int newExpPoints) {
		this.expPoints += newExpPoints;
	}

	public int getExpPoints() {
		return this.expPoints;
	}

	/**
	 * @return Player's level.
	 */
	public int getLevel() {
		return level;	
	}
	
	/**
	 *  Add a level to the Player.
	 */
	public boolean addLevel() {
		if(checkLevel()) {
			return true;		
		}
		else {
			return false;
		}
	}
	
	public void increaseLevel() {
		this.level = this.getLevel() +1;
		
	}
	
	/**
	 * Check if the player has enough experience points to add a level.
	 * 
	 * @return boolean 
	 */	
	private boolean checkLevel() {
		if(expPoints >= MAX_EXP_POINTS * level) {		
			return true;
		}
		else {
			return false;
		}
	} 

	public void gainExp(int exp) { 
		expPoints += exp;
		addLevel();		
	}
}
