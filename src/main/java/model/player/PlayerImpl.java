/**
 * 
 */
package model.player;

import controller.globalGenerator.GlobalGenerator;
import controller.playerAttack.PlayerAttack;
import controller.playerAttack.PlayerAttackImpl;
import model.actions.Action;
import model.actions.ActionImpl;

public class PlayerImpl implements Player {

	//punti da togliere al giocatore dopo ogni hit o che può guadagnare se crea il dano al nemico
	private final Life life;
	private final Experience experience;
	private Pair<Integer,Integer> playerPosition;
	private final  Action player_action;
	private final Gold gold;
	private final PlayerAttackImpl attack;
	private static final int ADD_HP = 5;
	GlobalGenerator gg = GlobalGenerator.getInstance();
	
	public PlayerImpl(Pair<Integer, Integer> pair) {
		this.life=new Life();
		this.playerPosition=pair;
		this.experience=new Experience();
		this.player_action=new ActionImpl();
		this.gold=new Gold();
		this.attack=new PlayerAttackImpl(gg.player);

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

	public void setPlayerPosition(Pair<Integer,Integer> newPos) {
		playerPosition=newPos;
	}
	
	
	public Pair<Integer,Integer> getPlayerPosition() {
		return playerPosition;
	}
	
	// resetta i punti vita del palyer
	public void recoverPlayer() {
		System.out.println("Ti senti piu forte di prima e le tue ferite sono state curate !!");
		life.setMaxLifePoints(life.getMaxLifePoints()+ADD_HP);
		life.resetLife();
		
	}

	public Action getPlayer_action() {
		return player_action;
	}
	

}
