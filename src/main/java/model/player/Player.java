package model.player;
import controller.playerAttack.PlayerAttackImpl;
import model.actions.Action;

/**
 * Interface for the Player.
 */
public interface Player {
	
	/**
	 * @return Player current position.
	 */
	public Pair<Integer,Integer> getPlayerPosition();

	/**
	 * Update Player's Pos after moving.
	 * @param newPos
	 */
	void setPlayerPosition(Pair<Integer,Integer> newPos);
	
	public Life getLife();

	public Gold getGold();
	
	public Experience getExperience();

	public Action getPlayer_action();
	
	public void recoverPlayer();
	
	PlayerAttackImpl getPlayerAtt();
}
