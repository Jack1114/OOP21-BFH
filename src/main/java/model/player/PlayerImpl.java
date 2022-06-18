/**
 * 
 */
package model.player;

import controller.actions.Action;
import controller.actions.ActionImpl;
import controller.globalGenerator.GlobalGenerator;

public class PlayerImpl implements Player {

	//punti da togliere al giocatore dopo ogni hit o che può guadagnare se crea il dano al nemico
	private final Life life;
	private final Experience experience;
	private Pair<Integer,Integer> playerPosition;
	private final  Action player_action;
	private final Gold gold;
	private static final int ATTACK_POINTS = 5; 
	GlobalGenerator gg = GlobalGenerator.getInstance();
	
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

	public void setPlayerPosition(Pair<Integer,Integer> newPos) {
		playerPosition=newPos;
	}
	
	
	public Pair<Integer,Integer> getPlayerPosition() {
		return playerPosition;
	}
	
	// resetta i punti vita del palyer
	public void recoverPlayer() {
		System.out.println("Ti senti piu forte di prima e le tue ferite sono state curate !!");
		life.setMaxLifePoints(life.getMaxLifePoints()+gg.getAddHp());
		life.resetLife();
		
	}

	public Action getPlayer_action() {
		return player_action;
	}
	

}
