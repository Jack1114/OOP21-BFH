package controller.entities;

import model.actions.ActionImpl;

/**
 * @author Olivia
 *
 */
public interface PlayerController {
	
	public Pair<Integer,Integer> getPlayerPosition();
	public int getAttackPoints();
	
	void setPlayerPosition(Pair<Integer,Integer> newPos);
	
	public Life getLife();
	public Experience getExperience() ;
	public ActionImpl getPlayer_action();
	
	

	

	

	
	


	

}
