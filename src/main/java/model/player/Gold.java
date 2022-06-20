package model.player;

/**
 * When killing an Enemy, the Player gets Gold that can be spent.
 */
public class Gold {

	//Starting gold value.
	private int gold = 0;

	public Gold() {

	}
	

	public int getGold_points() {
		return gold;
	}

	/**
	 * Update player's gold
	 */
	public void setGold_points(int g) {
		this.gold = g;
	}

	
	/**
	 *Gold gained by slaining Enemies
	 */
	public void gainGold_points(int newGold) {
		this.gold += newGold;
		
	}

}
