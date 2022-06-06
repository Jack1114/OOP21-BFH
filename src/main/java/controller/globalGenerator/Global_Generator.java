package controller.globalGenerator;
import java.io.BufferedReader;
import java.util.Scanner;
import java.io.InputStreamReader;
import java.util.*;

import controller.enemies.Enemy_move_control;
import controller.obstacles.Obstacle;
import controller.player.PlayerAttack;
import controller.player.PlayerAttackImpl;
import controller.player.PlayerMouvement;
import controller.player.PlayerMouvementsImpl;
import model.obstacles.*;
import model.player.*;
import model.abilities.*;
import model.enemies.Enemy;
import model.enemies.GUI;

// si occupa tutto lui di generare i nemici e le loro statistiche, L' eroe � statico per ora con una sola posizione fissa 

public class Global_Generator {
	
	public static final int GRID_SIZE=15;
	public int NUM_ENEMIES = 3;
    				// ID           POS
	public List<Pair<Integer,Pair<Integer,Integer>>> enemyposwithID = new ArrayList<>();
	//ostacoli
	public List<Obstacle> obstacles = new ArrayList<>();
	//player
	public PlayerImpl player;
	public PlayerAttack playerAttack;
	public PlayerMouvement playerMouvement;
	public Enemy ennemi;
	//nemici
	public List<Enemy> enemies;
	//id dei nemici morti
	public List<Integer> skipenemy; 

	//gui
	GUI g;	

	private static Global_Generator instance = null;
	
	private Global_Generator() {}
	
	public static synchronized Global_Generator getInstance() {
		if(instance == null) {
			instance = new Global_Generator();
		}
		return instance;
	}

	
	public void generation() {

		final ObstacleGenerator obstacleGenerator = new ObstacleGenerator(obstacles);

		this.player=new PlayerImpl(rand_pos_player(GRID_SIZE));

		this.playerAttack= new PlayerAttackImpl(player);
		this.playerMouvement= new PlayerMouvementsImpl(player);
		this.enemies= new ArrayList<Enemy>();
		this.skipenemy= new ArrayList<>(); 
		g = new GUI(15);
	
		player.addAbility(new ElixirOfLife(this.player));
		player.addAbility(new DoubleAttack(this.playerAttack));
		
		obstacleGenerator.generateObstacles();
		generate_enemies();

		g.update();
		System.out.println("Genarated obstacles, enemies and player");
		
		int turn = 0;
		int totactions = 0;
		
		while(true && totactions<80) {
			System.out.println("---- turn "+turn+" ----");	
			System.out.println("skipped enemy size = "+skipenemy.size());
			
			if(skipenemy.size()==NUM_ENEMIES) {
				reset();
				player.recoverPlayer();
				generate_enemies();
			} 
			
			playerTurn();
			enemyTurn();
			
			turn++;
			totactions++;	
		}
		
	}
	

	private void reset() {
		// TODO Auto-generated method stub
		enemyposwithID = new ArrayList<>();
		enemies= new ArrayList<Enemy>();
		skipenemy=new ArrayList<Integer>();
		obstacles = new ArrayList<>(); 
	}

	private void generate_enemies() {
		// TODO Auto-generated method stub
		for(int i=0; i<NUM_ENEMIES;i++) {
			//System.out.println("gen enemies n "+i);   //stampa per vedere se genera i nemici [ ok ]
			Enemy e = new Enemy(i);
			enemies.add(e);
		}
	}

	private void playerTurn() {
	        System.out.println("Enter Input : w=UP // s=DOWN // a=LEFT // d=RIGHT ");
        	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	        try {
	            String s = br.readLine();

	            switch(s) {
	            	case("w"):
	            		playerMouvement.up();
	            		g.update();
	            		System.out.println("Move UP");
	            	break;
	            	case("s"):
	            		playerMouvement.down();
	            		g.update();
	            		System.out.println("Move DOWN");
	            	break;
	            	case("a"):
	            		playerMouvement.left();
	            		g.update();
	            		System.out.println("Move LEFT");
	            	break;
	            	case("d"):
	            		playerMouvement.right();
	            		g.update();
	            		System.out.println("Move RIGHT");
	            	break;
					case("e"):
						playerAttack.attack(player.getPlayerPosition());
            			System.out.println("Attack...");

						g.update();
						break;
					case("1"):
						player.getAbility(0);
        				System.out.println("Use ability");

						break;
					case("2"):
						//TODO: da cambiare
						player.getAbility(1);
    					System.out.println("Use ability");

						break;
	            	default:
	            		playerMouvement.stop();
	            		g.update();
	            	break;
	            }
	            	
	            //System.out.println(s);
			}catch(Exception e) {
	            System.out.println(e);
	        }

	}


	private void enemyTurn() {
		// TODO Auto-generated method stub
		for(var item:enemyposwithID) {
			if(skipenemy.contains(item.getX())) {
				// � bruttissimo ma quando muoiono vengono teletrasportati ad una posizione fuore schermo a (99,99)
				// e vengono pure disattivati quindi non agiranno MAI PIU' !! 
				enemyposwithID.set(item.getX(), new Pair<>(item.getX(),new Pair<Integer,Integer>(99,99)));
			}else {
				int id= item.getX();
					 // stampa del turno (si ripete per ogni nemico )
				int actionpt=0;
				
				while (actionpt<2) {
					advance(item,actionpt);	
					item=enemyposwithID.get(id);
					actionpt++;
				}
			}	
		}
	}
	
	/**
	 * 
	 * @param position to check
	 * @return true: if position is empty
	 * false: if position has and obstacle
	 */  
	public boolean checkObstaclesPos(Pair<Integer, Integer> position) {
		boolean success = true;
		//sostituisci con un for
		obstacles.forEach(item -> {
			if(item.getObstaclePos().equals(position)) {
				if(success) {}
				return;
			}
		});
		return success;
	}
	
	
	/**
	 * 
	 * @param position to check
	 * @return true: if position is empty
	 * false: if position has an enemy
	 */  
	public boolean checkEnemyPos(Pair<Integer, Integer> position) {

		//scorre la lista dei nemici e
		//filtro i nemici i nemici che potrebbero essere in quella positiona
		//ritorno un optional
		Optional<Enemy> enemy = enemies
				.stream()
				.filter(e -> e.getEnemyPos().equals(position))
				.findFirst();
		//correggi lo stile
		if(enemy.isPresent()) {
			return false;
		}else {
			return true;
		}
	}

	
	/**
	 * 
	 * @param position to check
	 * @return true: if position is empty
	 * false: if position is occupied by the player
	 */  
	 
	public boolean checkPlayerPos(Pair<Integer, Integer> position) {
		if(player.getPlayerPosition().equals(position)) {
			return false;
		}
		return true;
	}
	
	
	@SuppressWarnings("deprecation")
	private void advance(Pair<Integer, Pair<Integer, Integer>> item, int actionpt) {
		Enemy_move_control.nextMove(item,actionpt);
		
		//---- wait in between actions ---
		long end = System.currentTimeMillis()+300;
		while (end>System.currentTimeMillis()) {
			//System.out.println("Waiting ...");
			if(System.currentTimeMillis()>end) {
				break;
			}
		}
		//---------------------------------
		//--- then update enemy pos ---
		g.update();

	}
	
	public Pair<Integer, Integer> randPosition(final int GRID_SIZE){
		Pair<Integer,Integer> pos = new Pair<>(0, 0);
		Random r = new Random();
		boolean success = false;
		while(!success){

			int x = r.nextInt(GRID_SIZE);
			int y = r.nextInt(GRID_SIZE);
			pos = new Pair<>(x,y);

			if(checkObstaclesPos(pos) && checkPlayerPos(pos) && checkEnemyPos(pos)){
				success = true;	
			}
		}
		return pos;
	}
	
	public Pair<Integer, Integer> rand_pos_player(int GRID_SIZE) {
		// TODO Auto-generated method stub
		Pair<Integer,Integer> pos2 = new Pair<>(0, 0);
		Random r = new Random();
		int x = r.nextInt(GRID_SIZE);
		int y = r.nextInt(GRID_SIZE);
		pos2 = new Pair<>(x,y);
		return pos2;

	}


}
