package controller.entities;

/**
 * @author Olivia
 *
 */
public interface PlayerController {
	
	Pair<Integer,Integer> getPlayerPosition();
	public int getAttackPoints();
	
	void setPlayerPosition(Pair<Integer,Integer> newPos);
	
	public Life getLife();
	public Experience getExperience() ;
	
	
	

	

	

	
	


	

}
