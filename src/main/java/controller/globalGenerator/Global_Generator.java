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
	
	public static final int GRID_SIZE = 15;
	private static final int MAX_ROUNDS = 50;
	public int NUM_ENEMIES = 3;
    				// ID           POS
	public List<Pair<Integer,Pair<Integer,Integer>>> enemyposwithID = new ArrayList<>();
	//ostacoli
	public List<Obstacle> obstacles = new ArrayList<>();
	//mappa di ostacoli
	public Map<Ability.Type, Integer> abilities = new HashMap<>();
	//ability manager
	public AbilityManager abilityManager;
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
		this.player = new PlayerImpl(rand_pos_player(GRID_SIZE));

		this.playerAttack = new PlayerAttackImpl(player);
		this.playerMouvement = new PlayerMouvementsImpl(player);
		this.enemies = new ArrayList<Enemy>();
		this.skipenemy = new ArrayList<>(); 
		g = new GUI(15);
	
		abilities.put(Ability.Type.ELIXIR_OF_LIFE, 2);
		abilities.put(Ability.Type.DOUBLE_ATTACK, 3);
		this.abilityManager = new AbilityManager(abilities);
		//dare il controllo all'ability manager
		abilityManager.generateAbilities();
		obstacleGenerator.generateObstacles();
		generate_enemies();

		g.update();
		System.out.println("Genarated obstacles, enemies and player");
		System.out.println("You can now move using: w=UP | s=DOWN | a=LEFT | d=RIGHT");
		System.out.println("You can use abilities with 1 and 2");
		
		int round = 0;
		
		while(true && round < MAX_ROUNDS) {
			System.out.println("---- Round "+ round + " ----");	
			System.out.println("skipped enemy size = "+skipenemy.size());
			
			if(skipenemy.size()==NUM_ENEMIES) {
				reset();
				player.recoverPlayer();
				generate_enemies();
			} 
			
			playerTurn();
			enemyTurn();
			
			round++;
		}
		
	}
	
	private void reset() {
		enemyposwithID = new ArrayList<>();
		enemies= new ArrayList<Enemy>();
		skipenemy=new ArrayList<Integer>();
		obstacles = new ArrayList<>(); 
	}

	private void generate_enemies() {
		for(int i=0; i<NUM_ENEMIES;i++) {
			Enemy e = new Enemy(i);
			enemies.add(e);
		}
	}

	private void playerTurn() {
		while(player.getPlayer_action().getAvailableActions() > 0) {
			player.getPlayer_action().removeAction();
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
					//al posto dei tasti ci saranno i bottoni
					//nel caso non si possano usare pi� abilit� perch� finite non si deve fare la removeAction 
					case("1"):
						if (abilityManager.isAvailable(Ability.Type.ELIXIR_OF_LIFE)) {
							abilityManager.getAbilityOfType(Ability.Type.ELIXIR_OF_LIFE).apply();
	        				System.out.println("Using " + abilityManager.getAbilityOfType(Ability.Type.ELIXIR_OF_LIFE).getName());
							abilityManager.remove(Ability.Type.ELIXIR_OF_LIFE);
	        				System.out.println("Now you have " + abilityManager.getSize(Ability.Type.ELIXIR_OF_LIFE) + " left");   			
						}else {
							System.out.println("You don't have any Elixir Of Life left");
						}
						g.update();
						break;
					case("2"):
						if (abilityManager.isAvailable(Ability.Type.DOUBLE_ATTACK)) {
							abilityManager.getAbilityOfType(Ability.Type.DOUBLE_ATTACK).apply();
	        				System.out.println("Using " + abilityManager.getAbilityOfType(Ability.Type.DOUBLE_ATTACK).getName());
							abilityManager.remove(Ability.Type.DOUBLE_ATTACK);
	        				System.out.println("Now you have " + abilityManager.getSize(Ability.Type.DOUBLE_ATTACK) + " left"); 
						}else {
							System.out.println("You don't have any Double Attack left");

						}
        				g.update();
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
		player.getPlayer_action().resetActions();
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
		Random r = new Random();
		int x = r.nextInt(GRID_SIZE);
		int y = r.nextInt(GRID_SIZE);
		return new Pair<>(x,y);
	}


}
