/**
 * 
 */
package controller.player;

import controller.globalGenerator.Global_Generator;
import model.enemies.Enemy;
import model.player.Pair;
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

	public void attack(Enemy enemy) {
		System.out.println("Hero is attacking");
		enemy.GetHit(getAttackPoints());
		//mi serve nel caso un'abilita' abbia cambiato il valore di attackPoints
		resetAttackPoints();

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
	
	public void getHit(int enemyID,int enemyResponseHit) {
		gg.player.getLife().setLifePoints(gg.player.getLife().getLifePoints()-enemyResponseHit);
		counter(enemyID, player.getAttackPoints());
		if(player.getLife().getLifePoints()<=0) {   //se il player non ha più vita,il gplayer muore e il turno è finito
			System.out.println("L'eroe è morto!");
			System.exit(0);
		}
	}
		
	public void counter(int enemyID, int Hero_ATK) {
		gg.enemies.get(enemyID).GetHit(Hero_ATK);
		
	}

}
