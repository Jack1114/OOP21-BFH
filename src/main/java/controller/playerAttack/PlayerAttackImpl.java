/**
 * 
 */
package controller.playerAttack;
import controller.globalGenerator.GlobalGenerator;
import model.enemies.Enemy;
import model.player.Player;

/**
 * The implementation of the {@link PlayerAttack}.
 *
 */
public class PlayerAttackImpl implements PlayerAttack {

	private int DEFAULT_ATTACK_POINTS =3; 
	private int attackPoints = DEFAULT_ATTACK_POINTS;

	private GlobalGenerator gg = GlobalGenerator.getInstance();
	Player player;

	public PlayerAttackImpl( Player player) {
		this.player = player;
	}
	
	 /**
     * {@inheritDoc}
     */
	public int getAttackPoints() {
		return attackPoints;
	}
	
	private void resetAttackPoints() {
		this.attackPoints = DEFAULT_ATTACK_POINTS;
	}
	
	 /**
     * {@inheritDoc}
     */
	public void attack(Enemy enemy) {
		System.out.println("Hero is attacking!");
		enemy.GetHit(getAttackPoints());
		resetAttackPoints();	
	}

	 /**
     * {@inheritDoc}
     */
	public void setAttackPoints(int newAttackPoints) {
		this.attackPoints = newAttackPoints;
	}
	
	 /**
     * {@inheritDoc}
     */
	 public void increaseAtt() {
		 setAttackPoints(this.DEFAULT_ATTACK_POINTS+=3);
		 
	 }

	 /**
	 * {@inheritDoc}
	 */
	public void getHit(int enemyID, int enemyResponseHit) {
		gg.player.getLife().setLifePoints(gg.player.getLife().getLifePoints() - enemyResponseHit);
	}
}
