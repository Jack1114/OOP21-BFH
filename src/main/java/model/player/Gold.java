package model.player;

public class Gold {

	private int gold=0; // oro che guardagna l'ero dopo ogni turno di gioco
	public Gold() {
		
	}
	
	/**
	 * @return gold
	 */
	public int getGold_points() {
		return gold;
	}
	
	/**
	 *when the palyer kill an enemie, he win gold
	 */
	public void gainGold_points(int newGold) {
		gold+=newGold;
		
	}

}
