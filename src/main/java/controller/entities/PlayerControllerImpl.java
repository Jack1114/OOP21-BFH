/**
 * 
 */
package controller.entities;

import model.actions.ActionImpl;
import model.obstacles.Obstacle.Type;

/**
 * @author Olivia
 *
 */
public class PlayerControllerImpl implements PlayerController {

	//punti da togliere al giocatore dopo ogni hit o che può guadagnare se crea il dano al nemico
	public Life life;
	Experience experience;
	Pair<Integer,Integer> playerPosition;
	public ActionImpl player_action;
	Gold gold;
	Type type;
	
	public PlayerControllerImpl() {
		this.life=new Life();
		this.experience=new Experience();
		this.player_action=new ActionImpl();
		this.gold=new Gold();
		
	}
	
	/**
	 * when the player move, this function update the new position
	 */
	public void setPlayerPosition(Pair<Integer,Integer> newPos) {
		playerPosition=newPos;
	}
	
	/**
	 * @return playerPosition 
	 */
	public Pair<Integer,Integer> getPlayerPosition() { // invece di usare x e y separatamente
		return playerPosition;
	}
	
	// resetta i punti vita del palyer
	public void recoverPlayer() {
		System.out.println("Ti senti piu forte di prima e le tue ferite sono state curate !!");
		life.resetLife();
	}

}
