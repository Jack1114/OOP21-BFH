/**
 * 
 */
package model.player;

import controller.actions.ActionImpl;

public class PlayerImpl implements Player {

	//punti da togliere al giocatore dopo ogni hit o che pu� guadagnare se crea il dano al nemico
	private final Life life;
	private final Experience experience;
	private Pair<Integer,Integer> playerPosition;
	private final  ActionImpl player_action;
	private final Gold gold;
	private static final int ATTACK_POINTS = 5; 
	
	public PlayerImpl(Pair<Integer, Integer> pair) {
		this.life=new Life();
		this.playerPosition=pair;
		this.experience=new Experience();
		this.player_action=new ActionImpl();
		this.gold=new Gold();
		
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
	
	public int getAttackPoints() {
		return ATTACK_POINTS;
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
		life.resetLife();
	}

	public ActionImpl getPlayer_action() {
		return player_action;
	}
	

}
