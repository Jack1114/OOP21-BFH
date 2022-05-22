/**
 * 
 */
package controller.entities;

/**
 * @author Olivia
 *
 */
public class PlayerControllerImpl implements PlayerController {

	//punti da togliere al giocatore dopo ogni hit o che può guadagnare se crea il dano al nemico
	private int gold=0; // oro che guardagna l'ero dopo ogni turno di gioco
	Life life ;
	Experience experience;
	Pair<Integer,Integer> playerPosition;
	
	public PlayerControllerImpl() {
		this.life=new Life();
		this.experience=new Experience();
		
	}
	public void setPlayerPosition(Pair<Integer,Integer> newPos) {
		playerPosition=newPos;
	}
	public Pair<Integer,Integer> getPlayerPosition() { // invece di usare x e y separatamente
		return playerPosition;
	}
	public int getGold() {
		return gold;
	}
	public void gainGold(int newGold) {
		gold+=newGold;
		
	}






}
