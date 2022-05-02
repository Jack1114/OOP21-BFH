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

	/**
	 * 
	 */
	public PlayerAttackControlerImpl() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void Attack(Pair<Integer, Integer> new_heropos2) {
		/*
		Global_Generator.enemyposwithID.forEach(item->{
			  if(item.getX==player_pos2.getX && item.getY==player_pos2.getY)
		  	{
		  	PlayerControllerImpl playerControllerImpl =new PlayerControllerImpl();
		  	Globa_Generator.ennemies.get(item.getX()).GetHit(playerControllerImpl.getAttackPoints())
		  	}
		 })*/
		
	}

	public void GetHit(int enemy_atk, int enemyID) { 
		PlayerControllerImpl playerControllerImpl=new PlayerControllerImpl();
		playerControllerImpl.SetLifePoints(playerControllerImpl.getLifePoints()-playerControllerImpl.getAttackPoints()); //considero che ennemy_attack e hero_attack valgono la stessa cosa= attackPoints
		if(playerControllerImpl.getLifePoints()<=0) {
			System.out.println("L' eroe è morto !! ");
			System.exit(0);
			// il gioco si chiude e il turno è finito
		}
		
	}



}
