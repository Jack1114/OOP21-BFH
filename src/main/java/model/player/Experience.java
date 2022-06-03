package model.player;

public class Experience {
	private static int MAX_EXP_POINTS=0;
	private int level=1;
	private int expPoints=0; //punti che l’eroe guadagna quando uccide un nemico

	public Experience() {
		this.expPoints=MAX_EXP_POINTS;
	}
	/**
	 * 
	 * @param newExpPoints
	 * experience points
	 */
	public void setExpPoints(int newExpPoints) {
		this.expPoints += newExpPoints;
	}
	
	public int getMaxExpPoints() {
		return MAX_EXP_POINTS;
	}

	/**
	 * @return player'S experience points
	 */
	public int getExpPoints() {
		return expPoints;
	}
	/**
	 * @return player's level
	 */
	public int getLevel() {
		return level;
		
	}
	/**
	 * check if the player has enough experience points to add level
	 */
	public  void checkAddLevel() {
		if(expPoints>=100*level)
		{
			expPoints-=100*level;
			
			level++; //level up
		}
	
	}

	/**
	 * @param exp
	 * add the player's experience points
	 */
	public void gainExp(int exp) { // quando muore il nemico
		expPoints+=exp;
		MAX_EXP_POINTS=expPoints;
		checkAddLevel();
		
	}

}
