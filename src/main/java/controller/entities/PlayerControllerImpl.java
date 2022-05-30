/**
 * 
 */
package controller.entities;

import model.actions.ActionImpl;
import model.obstacles.Obstacle.Type;

import java.util.ArrayList;
import java.util.List;

import model.abilities.*;

/**
 * @author Olivia
 *
 */
public class PlayerControllerImpl implements PlayerController {

	//punti da togliere al giocatore dopo ogni hit o che pu� guadagnare se crea il dano al nemico
	private Life life;
	private Experience experience;
	Pair<Integer,Integer> playerPosition;
	public ActionImpl player_action;
	private Gold gold;
	private final List<Ability> abilities;
	private static final int ATTACK_POINTS = 5; 
	
	public PlayerControllerImpl() {
		this.life=new Life();
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

}
