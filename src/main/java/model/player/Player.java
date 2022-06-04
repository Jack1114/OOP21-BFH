package model.player;

import controller.actions.ActionImpl;

/**
 * @author Olivia
 *
 */
public interface Player {
	
	public Pair<Integer,Integer> getPlayerPosition();
	public int getAttackPoints();
	
	void setPlayerPosition(Pair<Integer,Integer> newPos);
	
	public Life getLife();
	public Gold getGold();
	public Experience getExperience() ;
	public ActionImpl getPlayer_action();
	
	

	

	

	
	


	

}
