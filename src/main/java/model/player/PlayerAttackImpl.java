/**
 * 
 */
package model.player;

import model.enemies.Global_Generator;

/**
 * @author Olivia
 *
 */


public class PlayerAttackImpl implements PlayerAttack {

	private static final int DEFAULT_ATTACK_POINTS = 5; 
	private int attackPoints = DEFAULT_ATTACK_POINTS;

	PlayerImpl player;

	public PlayerAttackImpl( PlayerImpl new_player) {
		this.player=new_player;
	}

	public int getAttackPoints() {
		return attackPoints;
	}

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
	
	public void getHit(int enemyResponseHit) { 
		player.getLife().setLifePoints(player.getLife().getLifePoints() - enemyResponseHit); //considero che ennemy_attack e hero_attack valgono la stessa cosa= attackPoints
		if(player.getLife().getLifePoints()<=0) {   //se il player non ha più vita,il gplayer muore e il turno è finito
			System.out.println("L'eroe è morto!");
			System.exit(0);
		}
	}
		

}
