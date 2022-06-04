package model.player;

public class Gold {

	private int gold=0;// oro che guardagna l'ero dopo ogni turno di gioco
	private static int MAX_GOLD=0;
	public Gold() {
		this.gold=MAX_GOLD;
	}
	
	/**
	 * @return gold
	 */
	public int getGold_points() {
		return gold;
	}
	public int getMaxGold_points() {
		return MAX_GOLD;
	}
	
	/**
	 *when the palyer kill an enemie, he win gold
	 */
	public void gainGold_points(int newGold) {
		gold+=newGold;
		
	}

}
