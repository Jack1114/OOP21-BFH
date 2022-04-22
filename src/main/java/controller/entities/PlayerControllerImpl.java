/**
 * 
 */
package controller.entities;

/**
 * @author Olivia
 *
 */
public class PlayerControllerImpl implements PlayerController {

	
	private static int puntiAzione = 5; //numero di azioni che un personaggio può fare in un turno
	private static int puntiVita = 70; //punti che il personaggio ha dalla partenza del gioco
	private static int puntiEsp=0; //punti che l’eroe guadagna quando uccide un nemico
	private static int livello=1;
	
	private static int x; 
	private static int y; 
	
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
	
	public int getPuntiAzione() {
		return puntiAzione;
	}
	public int getpuntiVita() {
		return puntiVita;
	}
	public int getLivello() {
		return livello;
	}
	public int getPuntiEsp() {
		return puntiEsp;
	}
	
	public void geszionePuntiEsp() {
		if(nemici.punti==0) //nemico morte
		{
			if(livello==1)
				puntiEsp+=20;
			else
				puntiEsp+=10;
					
		}
			
	}
	
	public void geszionePuntiVita() {
		if(PlayerImpl2.playerAttak==true) //nemico morte
		{
			puntiVita=-5;
		}
			
	}
	
	public boolean playerAttak() {
		if(nemico.getX()==PlayerImpl2.getX() && nemico.getY()==PlayerImpl2.getY()) {
			return true;
		}
			
	}
	
	public  void aumentaLivello() {
		if(puntiEsp>=100) //nemico morte
		{
			livello++;
		}
			
	}

}
