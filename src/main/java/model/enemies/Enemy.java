package model.enemies;
import java.util.*;

import controller.globalGenerator.GlobalGenerator;
import model.player.Pair;
import model.player.Player;
import model.player.PlayerImpl;

public class Enemy {

	// supponendo che lo schermo sia una griglia di 15 X 15
	//int GRID_SIZE = 15;
	
	private final static int GRID_SIZEX = 10;
	private final static int GRID_SIZEY = 12;
	private  Player player;
	private Pair<Integer,Integer> pos;
	private GlobalGenerator gg = GlobalGenerator.getInstance();
	
	private int ID;
	
	private int x;
	private int y;
	
	private int HP ;
	private int def;
	private int atk;
	private int exp;
	private int HeroEXP =gg.player.getExperience().getExpPoints();
	private int Gold;
	
	Random rand = new Random();
	
	public Enemy(int id) {
		
		// IMPORTANTE !!
		this.ID = id;
		
		// elemento di random per dare diversit� ai nemici
		int value = rand.nextInt(3)+1; 
		
		// i valori sono arbitrari
		
		HP =( 5+value + ( HeroEXP*500/(15*5) ));
		def=( 1+value/2 + ( HeroEXP/(20*8) ));
		atk=( 1+value/3 + ( HeroEXP/(10*10) ));
		exp=( 10+value - ( HeroEXP/(15*6) ));
		
		Gold=rand.nextInt(15)+10;
		generate_pos();
	}
	
	private void generate_pos() {
		// TODO Auto-generated method stub
		boolean ok=false;
		while(!ok) {
			x= rand.nextInt(GRID_SIZEX);
			y= rand.nextInt(GRID_SIZEY);
			pos=new Pair<>(x,y);
			if(gg.checkEnemyPos(pos) && gg.checkObstaclesPos(pos) && gg.checkPlayerPos(pos)) {
				gg.enemyposwithID.add(new Pair<Integer, Pair<Integer, Integer>>(this.ID,pos));
				ok=true;
			}
		}
	}


	@Override
	public String toString() {
		// TODO Auto-generated method stub
		String s = "HP = "+GetHP()+" atk = "+this.atk+" def = "+this.def+" exp = "+this.exp ;
		return s;
	}

	public int getID() {
		return this.ID;
	}
	public int GetHP() {
		return HP;
	}
	
	public int GetATK() {
		return atk;
	}
	
	public int GetDEF() {
		return def;
	}

	public void SetHP(int damage) {
		setHPennemi(GetHP()-damage);
		System.out.println("Enemy "+this.ID+" - Life = " + GetHP());
		if(GetHP()<=0) {
			gg.player.getExperience().gainExp(this.getEXP());
			gg.player.getGold().gainGold_points(this.Gold);
			Death();
		}
	}
	
	public void setHPennemi(int i) {
		HP=i;
		
	}




	private int getEXP() {
		// TODO Auto-generated method stub
		return this.exp;
	}



	public void GetHit(int hero_ATK) {
		System.out.println("Enemy "+this.ID + " hit by hero - Damage of " +(hero_ATK - GetDEF()));
		SetHP(hero_ATK - GetDEF());	
	}



	private void Death() {
		System.out.println("Enemy "+this.ID+" has died");
		setEXP(0);
		setGold(0);
		if(!gg.skipenemy.contains(this.ID)) {
			gg.skipenemy.add(this.ID);
			gg.enemyposwithID.set(this.ID, new Pair<>(this.ID,new Pair<Integer,Integer>(99,99)));
		}
	}

	private void setGold(int i) {
		// TODO Auto-generated method stub
		this.Gold=i;
	}

	private void setEXP(int i) {
		// TODO Auto-generated method stub
		this.exp=i;
	}

	
}
