/**
 * 
 */
package controller.entities;

import battleforhonor.Global_Generator;

/**
 * @author Olivia
 *
 */

public class PlayerAttackControlerImpl implements PlayerAttackController {
	private static final int DEFAULT_ATTACK_POINTS = 5; 
	private int attackPoints = DEFAULT_ATTACK_POINTS;
	PlayerControllerImpl player;

	public PlayerAttackControlerImpl( PlayerControllerImpl new_player) {
		this.player=new_player;
	}
	
	/** @return player's attack_points
	 * 
	 */

	public int getAttackPoints() {
		return attackPoints;
	}

	/**
	 * attack beetwen the player and the enemie
	 */
	public void attack() {
		//int responseHit;
		Global_Generator.enemyposwithID.forEach(item->{
		if(item.getY().getX()==player.getPlayerPosition().getX() && item.getY().getY()==player.getPlayerPosition().getY());
		  	{
		  	Global_Generator.enemies.get(item.getX()).GetHit(getAttackPoints());
		  	//mi serve nel caso un'abilita' abbia cambiato il valore di attackPoints
		  	resetAttackPoints();

		  	//responseHit = (int)(Global_Generator.enemies.get(item.getX()).GetATK()) / 4;
		  	//getHit(responseHit);
		  	}
		});
	}

	public void setAttackPoints(int newAttackPoints) {
		// TODO Auto-generated method stub
		this.attackPoints = newAttackPoints;
	}
	
	public void resetAttackPoints() {
		this.attackPoints = DEFAULT_ATTACK_POINTS;
	}

	
	/**
	 * 
	 * after an attack from the enemie, the playerlost some health points
	 */
	/*
	public void getHit(int enemyResponseHit) { 
		player.life.setLifePoints(player.life.getLifePoints() - enemyResponseHit); //considero che ennemy_attack e hero_attack valgono la stessa cosa= attackPoints
		if(player.life.getLifePoints()<=0) {   //se il player non ha più vita,il gplayer muore e il turno è finito
			System.out.println("L'eroe è morto!");
			System.exit(0);
		}
		*/

}
