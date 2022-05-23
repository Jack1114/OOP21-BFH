/**
 * 
 */
package controller.entities;

import battleforhonor.Global_Generator;
import javafx.util.Pair;

/**
 * @author Olivia
 *
 */

public class PlayerAttackControlerImpl implements PlayerAttackController {
	private final int attackPoints = 5; 
	PlayerControllerImpl player;
	/**
	 * 
	 */
	public PlayerAttackControlerImpl( PlayerControllerImpl new_player) {
		this.player=new_player;
	}

	public int getAttackPoints() {
		return attackPoints;
	}

	public void attack(controller.entities.Pair<Integer, Integer> new_heropos2) {
		Global_Generator.enemyposwithID.forEach(item->{
		if(item.getY().getX()==player.getPlayerPosition().getX() && item.getY().getY()==player.getPlayerPosition().getY());
		  	{
		  	Global_Generator.enemies.get(item.getX()).GetHit(getAttackPoints());
		  	}
		});
	}

	public void getHit(int enemy_atk, int enemyID) { 
		player.life.setLifePoints(player.life.getLifePoints()-getAttackPoints()); //considero che ennemy_attack e hero_attack valgono la stessa cosa= attackPoints
		if(player.life.getLifePoints()<=0) {
			System.out.println("L' eroe è morto !! ");
			System.exit(0);
			// il gioco si chiude e il turno è finito
		}
		
	}







}
