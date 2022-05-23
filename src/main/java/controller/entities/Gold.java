package controller.entities;

public class Gold {

	private int gold=0; // oro che guardagna l'ero dopo ogni turno di gioco
	public Gold() {
		
	}
	
	/**
	 * @return gold
	 */
	public int getGold() {
		return gold;
	}
	
	/**
	 *when the palyer kill an enemie, he win gold
	 */
	public void gainGold(int newGold) {
		gold+=newGold;
		
	}

}
