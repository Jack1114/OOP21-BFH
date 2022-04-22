package controller.entities;

/**
 * @author Olivia
 *
 */
public interface PlayerController {
	
	 int getX();
	
	 int getY();
	
	void setX(int newx);
	void setY(int newy);
	
	int getPuntiAzione();
	int getpuntiVita();
	int getLivello();
	
	int getPuntiEsp();
	
	void geszionePuntiEsp();
	void geszionePuntiVita();
	

	
	void aumentaLivello();
	


	

}
