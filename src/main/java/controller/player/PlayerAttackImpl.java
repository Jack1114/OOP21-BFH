/**
 * 
 */
package controller.player;

import controller.globalGenerator.Global_Generator;
import model.player.PlayerImpl;

/**
 * @author Olivia
 *
 */


public class PlayerAttackImpl implements PlayerAttack {

	private static final int DEFAULT_ATTACK_POINTS = 5; 
	private int attackPoints = DEFAULT_ATTACK_POINTS;

	private Global_Generator gg = Global_Generator.getInstance();
	PlayerImpl player;

	public PlayerAttackImpl( PlayerImpl new_player) {
		this.player=new_player;
	}

	public int getAttackPoints() {
		return attackPoints;
	}

	public void attack() {
		//int responseHit;
		gg.enemyposwithID.forEach(item->{
		if(item.getY().getX()==player.getPlayerPosition().getX() && item.getY().getY()==player.getPlayerPosition().getY());
		  	{
		  	gg.enemies.get(item.getX()).GetHit(getAttackPoints());
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
		if(player.getLife().getLifePoints()<=0) {   //se il player non ha pi� vita,il gplayer muore e il turno � finito
			System.out.println("L'eroe � morto!");
			System.exit(0);
		}
	}
		

}