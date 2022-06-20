package model.player;

import controller.globalGenerator.GlobalGenerator;
import controller.playerAttack.PlayerAttack;
import controller.playerAttack.PlayerAttackImpl;
import model.actions.Action;
import model.actions.ActionImpl;

/**
 * The implementation of the {@link Player}.
 *
 */
public class PlayerImpl implements Player {

	private final Life life;
	private final Experience experience;
	private Pair<Integer, Integer> playerPosition;
	private final  Action player_action;
	private final Gold gold;
	private final PlayerAttackImpl attack;
	private static final int ADD_HP = 5;
	
	GlobalGenerator gg = GlobalGenerator.getInstance();
	
	/**
	 * Constructor for the Player.
	 * @param pair starting position.
	 */
	public PlayerImpl(Pair<Integer, Integer> pair) {
		this.life=new Life();
		this.playerPosition=pair;
		this.experience=new Experience();
		this.player_action=new ActionImpl();
		this.gold=new Gold();
		this.attack=new PlayerAttackImpl(gg.player);
	}
	
	/**
     * {@inheritDoc}
     */
	public void setPlayerPosition(Pair<Integer,Integer> newPos) {
		playerPosition=newPos;
	}
	
	/**
     * {@inheritDoc}
     */	
	public Pair<Integer,Integer> getPlayerPosition() {
		return playerPosition;
	}
		
	public PlayerAttackImpl getPlayerAtt() {
		return this.attack;
	}
	
	public Gold getGold() {
		return this.gold;
	}
	
	public Life getLife() {
		return  this.life;
	}
	
	public Experience getExperience() {
		return  this.experience;
	}


	public void recoverPlayer() {
		System.out.println("You feel stronger than before and by magic all your wounds are healed!");
		life.setMaxLifePoints(life.getMaxLifePoints()+ADD_HP);
		life.resetLife();
	}

	public Action getPlayer_action() {
		return player_action;
	}
}
