/**
 * 
 */
package model.player;

import java.util.ArrayList;
import java.util.List;

import controller.actions.ActionImpl;
import controller.obstacles.Obstacle.Type;
import model.abilities.*;

/**
 * @author Olivia
 *
 */
public class PlayerImpl implements Player {

	//punti da togliere al giocatore dopo ogni hit o che può guadagnare se crea il dano al nemico
	private final Life life;
	private final Experience experience;
	private Pair<Integer,Integer> playerPosition;
	private final  ActionImpl player_action;
	private final Gold gold;
	private final List<Ability> abilities;
	private static final int ATTACK_POINTS = 5; 
	
	public PlayerImpl(Pair<Integer, Integer> pair) {
		this.life=new Life();
		this.playerPosition=pair;
		this.experience=new Experience();
		this.player_action=new ActionImpl();
		this.gold=new Gold();
		this.abilities = new ArrayList<>();
		
	}
	
	public void addAbility(final Ability newAbility) {
		this.abilities.add(newAbility);
	}
	
	public List<Ability> getAbilities(){
		return this.abilities;
	}
	
	public Ability getAbility(final int index) {
		//TODO: fare un check che la lista non sia vuota
		return this.abilities.get(index);
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
	/**
	 * when the player move, this function update the new position
	 */
	public void setPlayerPosition(Pair<Integer,Integer> newPos) {
		playerPosition=newPos;
	}
	
	/**
	 * @return player current position
	 */
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
