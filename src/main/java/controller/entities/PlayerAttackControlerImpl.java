/**
 * 
 */
package controller.entities;

import javafx.util.Pair;

/**
 * @author Olivia
 *
 */

public class PlayerAttackControlerImpl implements PlayerAttackController {
	private final int attackPoints = 5; 
	/**
	 * 
	 */
	public PlayerAttackControlerImpl() {
		
	}

	public int getAttackPoints() {
		return attackPoints;
	}

	public void Attack(Pair<Integer, Integer> new_heropos2) {
		/*
		Global_Generator.enemyposwithID.forEach(item->{
			  if(item.getX==player_pos2.getX && item.getY==player_pos2.getY)
		  	{
		  	PlayerControllerImpl playerControllerImpl =new PlayerControllerImpl();
		  	Globa_Generator.ennemies.get(item.getX()).GetHit(getAttackPoints())
		  	}
		 })*/
		
	}

	public void GetHit(int enemy_atk, int enemyID) { 
		PlayerControllerImpl playerControllerImpl=new PlayerControllerImpl();
		playerControllerImpl.life.setLifePoints(playerControllerImpl.life.getLifePoints()-getAttackPoints()); //considero che ennemy_attack e hero_attack valgono la stessa cosa= attackPoints
		if(playerControllerImpl.life.getLifePoints()<=0) {
			System.out.println("L' eroe è morto !! ");
			System.exit(0);
			// il gioco si chiude e il turno è finito
		}
		
	}



}
