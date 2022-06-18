package model.player;
import controller.actions.Action;
public interface Player {
	/**
	 * @return player current position
	 */
	public Pair<Integer,Integer> getPlayerPosition();

	/**
	 * when the player move, this function update the new position
	 */
	void setPlayerPosition(Pair<Integer,Integer> newPos);
	/**
	 * @return player's life points
	 */
	public Life getLife();
	/**
	 * @return player's gold points
	 */
	public Gold getGold();
	/**
	 * @return player's experience points
	 */
	public Experience getExperience();
	/**
	 * @return player's action points
	 */
	public Action getPlayer_action();
	
	
	public void recoverPlayer();

}
