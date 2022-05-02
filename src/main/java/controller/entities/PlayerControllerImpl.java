/**
 * 
 */
package controller.entities;

/**
 * @author Olivia
 *
 */
public class PlayerControllerImpl implements PlayerController {

	
	private final int attackPoints = 5; //punti da togliere al giocatore dopo ogni hit o che può guadagnare se crea il dano al nemico
	private  int MAXLifePoints= 70; //punti che il personaggio ha dalla partenza del gioco
	private final int MAXExpPoints=50;
	private int expPoints=MAXExpPoints; //punti che l’eroe guadagna quando uccide un nemico
	private int level=1;
	private int lifePoints=MAXLifePoints; //si decrementa ogni volta cheil giocatore subisce un hit dal nemico
	private int gold=0; // oro che guardagna l'ero dopo ogni turno di gioco
	
	private  int x; 
	private  int y; 
	
	public PlayerControllerImpl() {
		// TODO Auto-generated constructor stub
	}

	public int getX(){
		return x;
	}
	public int getY(){
		return y;
	}
	public void setX(int newx) {
		x=newx;
	}
	public void setY(int newy) {
		y=newy;
	}
	
	public int getLifePoints() {
		return lifePoints;
	}
	public int SetLifePoints(int newlifePoints) {
		return lifePoints=newlifePoints;
	}
	public int getMAXLifePoints() {
		return MAXLifePoints;
	}
	public void setMAXLifePoints() {
		
		MAXLifePoints=MAXLifePoints+getAttackPoints();
	}
	
	public int getLevel() {
		return level;
	}
	public  void checkAddLevel() {
		if(expPoints>=100*level)
		{
			expPoints-=100*level;
			
			level++; //level up
		}
	
	}
	/*ho impostato attack point come una variabile finale
	 * public int getAttackPoints() {
		return attackPoints;
	}
	public void setAttackPoints(int attackPoints) {
		//DA IMPL
		this.attackPoints = attackPoints;
	}*/

	public int getExpPoints() {
		return expPoints;
	}
	/*
	 * ho modificato maxExpPoint come un final
	 * public int getMAXExpPoints(int newExpPoints) {
		return MAXExpPoints;
	}*/
	public int GetGold() {
		return gold;
	}
	public void GainGold(int newGold) {
		gold+=newGold;
		
	}
	public void GainExp(int exp) { // quando muore il nemico
		expPoints+=exp;
		
		
	}

	public int getAttackPoints() {
		return attackPoints;
	}

	
	// spaw e counter not done


}
