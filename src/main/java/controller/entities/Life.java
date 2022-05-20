package controller.entities;

public class Life {
	private static final int MAX_LIFE_POINTS=70; //punti che il personaggio ha dalla partenza del gioco
	private int lifePoints;
	
	public Life() {
		this.lifePoints=MAX_LIFE_POINTS;
	}
	
	public int getLifePoints() {
		return lifePoints;
	}

	public void setLifePoints(int newlifePoints) {
	 lifePoints=newlifePoints;
	}
	
	public boolean isAlive() {
		return this.lifePoints>0;
	}
}
