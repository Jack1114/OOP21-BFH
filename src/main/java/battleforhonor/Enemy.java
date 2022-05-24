package battleforhonor;
import java.util.*;

import controller.entities.Pair;
import controller.entities.PlayerControllerImpl;

public class Enemy {

	// supponendo che lo schermo sia una griglia di 15 X 15
	int GRID_SIZE = 15;
	PlayerControllerImpl player;
	Pair<Integer,Integer> pos;
	
	int ID;
	
	int x;
	int y;
	
	int HP;
	int def;
	int atk;
	int exp;
	int HeroEXP =player.getExperience().getMaxExpPoints();
	int Gold;
	
	Random rand = new Random();
	
	public Enemy(int id) {
		
		// IMPORTANTE !!
		this.ID = id;
		
		// elemento di random per dare diversit� ai nemici
		int value = rand.nextInt(10)+1; 
		
		// i valori sono arbitrari
		HP =( 5+value + ( HeroEXP/(15*5) ));
		def=( 1+value/2 + ( HeroEXP/(20*8) ));
		atk=( 1+value/3 + ( HeroEXP/(10*10) ));
		exp=( 20+value*2 + ( HeroEXP/(15*6) ));
		Gold=rand.nextInt(15)+10;
		generate_pos();
	}
	
	
	
	private void generate_pos() {
		// TODO Auto-generated method stub
		boolean ok=false;
		while(!ok) {
			x= rand.nextInt(GRID_SIZE);
			y= rand.nextInt(GRID_SIZE);
			pos=new Pair<>(x,y);
			if( (!Global_Generator.enemyposwithID.contains(pos) ) && (!Global_Generator.obstacles_pos.contains(pos))  ) {
				Global_Generator.enemyposwithID.add(new Pair(this.ID,pos));
				ok=true;
			}
		}
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		String s = "HP = "+this.HP+" atk = "+this.atk+" def = "+this.def+" exp = "+this.exp ;
		return s;
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

	private void SetHP(int damage) {
		this.HP = HP-damage;
		System.out.println("Enemy ID : "+this.ID+" HP Remaining = "+GetHP());
		if(GetHP()<=0) {
			Hero.GainExp(this.getEXP());
			Hero.GainGold(this.Gold);
			Death();
		}
	}
	
	private int getEXP() {
		// TODO Auto-generated method stub
		return this.exp;
	}



	public void GetHit(int hero_ATK) {
		// TODO Auto-generated method stub
		System.out.println("getting hit by counter BY "+ (hero_ATK-GetDEF())+" Damage");
		SetHP(hero_ATK-GetDEF());
		
	}



	private void Death() {
		// TODO Auto-generated method stub
		System.out.println("ENEMY "+this.ID+" has died");
		setEXP(0);
		setGold(0);
		if(!Global_Generator.skipenemy.contains(this.ID)) {
			Global_Generator.skipenemy.add(this.ID);
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
