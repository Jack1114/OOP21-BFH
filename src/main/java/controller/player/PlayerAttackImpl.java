/**
 * 
 */
package controller.player;

import controller.globalGenerator.GlobalGenerator;
import model.enemies.Enemy;
import model.player.Player;
import model.player.PlayerImpl;

public class PlayerAttackImpl implements PlayerAttack {

	private static final int DEFAULT_ATTACK_POINTS =3; 
	private int attackPoints = DEFAULT_ATTACK_POINTS;

	private GlobalGenerator gg = GlobalGenerator.getInstance();
	Player player;

	public PlayerAttackImpl( Player player) {
		this.player=player;
	}

	public int getAttackPoints() {
		return attackPoints;
	}
	
	private void resetAttackPoints() {
		this.attackPoints = DEFAULT_ATTACK_POINTS;
	}

	public void attack(Enemy enemy) {
		System.out.println("Hero is attacking");
		enemy.GetHit(getAttackPoints());
		resetAttackPoints();	
	}

	public void setAttackPoints(int newAttackPoints) {
		this.attackPoints = newAttackPoints;
	}

	public void getHit(int enemyID,int enemyResponseHit) {
		gg.player.getLife().setLifePoints(gg.player.getLife().getLifePoints()-enemyResponseHit);
		//counter(enemyID, gg.player.getAttackPoints());
		if(gg.player.getLife().getLifePoints()<=0) {   //se il player non ha più vita,il gplayer muore e il turno è finito
			System.out.println("L'eroe è morto!");
			System.exit(0);
		}
	}
		 //manteniamo counter se quando il nemico attaca il player(getHit) il player fa un contro attacko(GetHit)
	private void counter(int enemyID, int Hero_ATK) {
		gg.enemies.get(enemyID).GetHit(Hero_ATK);
		
	}

}
