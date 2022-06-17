/**
 * 
 */
package controller.player;

import controller.globalGenerator.Global_Generator;
import model.enemies.Enemy;
import model.player.PlayerImpl;

public class PlayerAttackImpl implements PlayerAttack {

	private static final int DEFAULT_ATTACK_POINTS =20; 
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

	}

	public void setAttackPoints(int newAttackPoints) {
		// TODO Auto-generated method stub
		this.attackPoints = newAttackPoints;
	}

	/**
	 * 
	 * after an attack from the enemie, the playerlost some health points
	 */
	
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
