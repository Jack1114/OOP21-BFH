import java.util.Random;

// Tutto molto basilare solo perch� mi serviva vedere se prendeva la x e y dell' eroe 

public class Hero {
	
	static Random r= new Random(); 
	
	static int MAX_HP = 70;
	static int HP = MAX_HP;
	static int ATK= 15;
	static int Gold=0;
	private static final int MAX_ACTIONS = 3
	
	static int x; // 5
	static int y; // 8
	
	static int EXP=0;
	static int MAX_EXP=0;
	static int LV=1;

	public final int actions = MAX_ACTIONS;

	public int getActions(){
		return this.actions;
	}

	public void removeAction(){
		if(actions > 0){
			this.actions--;
		}
	}

	public void resetActions(){
		this.actions = MAX_ACTIONS;
	}

	
	static int getX(){
		return x;
	}
	
	static int getY(){
		return y;
	}
	
	static void setX(int newx) {
		x=newx;
	}
	static void setY(int newy) {
		y=newy;
	}

	public static void GetHit(int enemy_atk, int enemyID) {
		// TODO Auto-generated method stub
		SetHP(GetHP()-enemy_atk);
		System.out.println("MAX_HP = "+MAX_HP+" Actual HP = "+GetHP());
		counter(enemyID,GetATK());
		
		if(GetHP()<=0) {
			System.out.println("L' eroe � morto !! ");
			System.exit(0);
		}
	}
	
	private static void counter(int enemyID, int Hero_ATK) {
		Global_Generator.enemies.get(enemyID).GetHit(Hero_ATK);
		
	}

	public static int GetHP() {
		return HP;
	}
	public static int GetMaxHP() {
		return MAX_HP;
	}
	public static int GetLV() {
		return LV;
	}
	public static int GetATK() {
		return ATK;
	}
	
	public static int SetHP(int newHP) {
		return HP=newHP;
	}

	public static void GainExp(int exp) {
		// TODO Auto-generated method stub
		EXP+=exp;
		MAX_EXP+=EXP;
		checkLV_UP();
	}

	private static void checkLV_UP() {
		// TODO Auto-generated method stub
		if(EXP>=100*LV) {
			System.out.println("YOU LELEVED UP !! ");
			int increase = r.nextInt(10)+4;
			SetMAXHP(increase*5);
			SetHP(MAX_HP);
			SetATK((increase+5)*3/2);
			EXP-=100*LV;
			LV++;
		}
	}

	private static void SetATK(int increase) {
		// TODO Auto-generated method stub
		System.out.println("ATK : "+ATK+ " -> "+ (ATK+increase) );
		ATK= ATK+increase;
	}

	private static void SetMAXHP(int increase) {
		// TODO Auto-generated method stub
		System.out.println("MAX HP : "+MAX_HP+ " -> "+ (MAX_HP+increase) );
		MAX_HP = MAX_HP+increase;
		System.out.println("HP : "+GetHP()+" -> "+MAX_HP);
	}

	public static void spawn(int GRID_SIZE) {
		// TODO Auto-generated method stub
		setX(r.nextInt(GRID_SIZE));
		setY(r.nextInt(GRID_SIZE));
	}

	public static int getCurrentEXP() {
		// TODO Auto-generated method stub
		return EXP;
	}
	public static int getCurrentMAX_EXP() {
		// TODO Auto-generated method stub
		return MAX_EXP;
	}
	
	public static void GainGold(int gold) {
		Gold+=gold;
	}
	public static int GetGold() {
		return Gold;
	}
}
