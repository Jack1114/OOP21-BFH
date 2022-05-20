package controller.entities;

public class Experience {
	private static final int MAX_EXP_POINTS=50;
	private int level=1;
	private int expPoints; //punti che l’eroe guadagna quando uccide un nemico
	
	public Experience() {
		this.expPoints=MAX_EXP_POINTS;
	}

	public void setExpPoints(int newExpPoints) {
		this.expPoints += newExpPoints;
	}

	public int getExpPoints() {
		return expPoints;
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

	public void gainExp(int exp) { // quando muore il nemico
		expPoints+=exp;
		
		
	}

}
