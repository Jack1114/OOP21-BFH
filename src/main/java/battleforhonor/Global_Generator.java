package battleforhonor;
import java.io.BufferedReader;

import java.io.InputStreamReader;
import java.util.*;

import controller.entities.*;
import model.obstacles.*;

// si occupa tutto lui di generare i nemici e le loro statistiche, L' eroe � statico per ora con una sola posizione fissa 

public class Global_Generator {
	

	public static final int GRID_SIZE=15;
	//public static List<Pair<Integer,Integer>> enemypos = new ArrayList<>();
						      // ID           POS
	public static List<Pair<Integer,Pair<Integer,Integer>>> enemyposwithID = new ArrayList<>();
	
	//public static List<Pair<Integer,Pair<Integer,Integer>>> NEWenemyposwithID = new ArrayList<>(); // alla fine ho risolto con solo quella principale 
	
	//Lista degli ostacoli
	public static List<Obstacle> obstacles = new ArrayList<>();
	 // il player
	PlayerControllerImpl player=new PlayerControllerImpl() ;
	private final ObstacleGenerator obstacleGenerator = new ObstacleGenerator(obstacles);
	PlayerAttackController playerAttack= new PlayerAttackControlerImpl(player);
	PlayerMouvementsController playerMouvement= new PlayerMouvementsControllerImpl(player);
	public static List<Enemy> enemies= new ArrayList<Enemy>();

	public static List<Integer> skipenemy= new ArrayList<>(); //contiene gli ID dei vari nemici morti 
	
	int NUM_ENEMIES = 3;

	private int totactions = 0;
	GUI g = new GUI(15);
	//private int hero_actions=0;
	
	void generation() {
		
		//posizione iniziale del player
		player.setPlayerPosition(randPosition(GRID_SIZE));
		
		obstacleGenerator.generateObstacles();
		generate_enemies();

		g.update();
		System.out.println("Genarated obstacles, enemies and player");
		/*   //stampa per vedere le statistiche pseudo-randomiche [ ok ]
		
		for(int i=0;i<NUM_ENEMIES;i++) {
			var elem=enemyposwithID.get(i);
			System.out.println("----------[ "+i+" ]-----------");
			System.out.println("pos x = "+elem.getX()+" pos y = "+elem.getY());
			
			var elem2=enemies.get(i);
			System.out.println(elem2.toString());
		}
		*/
		

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
		// TODO Auto-generated method stub
		//va bene qualsiasi cosa � solo per creare dello stacco tra player e nemico 
		//hero_actions = 0;
		while(player.player_action.getAvailableActions() > 0) {
			player.player_action.removeAction();
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	        System.out.println("Enter Input : w=UP // s=DOWN // a=LEFT // d=RIGHT ");
	        try {
	            String s = br.readLine();
	            switch(s) {
	            	case("w"):
	            		playerMouvement.up();
	            		g.update();
	            	break;
	            	case("s"):
	            		playerMouvement.down();
	            		g.update();
	            	break;
	            	case("a"):
	            		playerMouvement.left();
	            		g.update();
	            	break;
	            	case("d"):
	            		playerMouvement.right();
	            		g.update();
	            	break;
					case("e"):
						playerAttack.attack();
						//g.update();
						break;
					case("1"):
						playerAttack.attackWithAbility("1");
						break;
					case("2"):
						playerAttack.attackWithAbility("2");
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
		player.player_action.resetActions();
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
		obstacles.forEach(item -> {
			if(item.getObstaclePos().equals(position)) {
				success = false;
				return;
			}
		});
		return success;
	}
	public boolean checkEnemyPos(Pair<Integer, Integer> position) {
		enemies.forEach(item -> {
			if(item.getEnemyPos().equals(position)) {
				boolean success = false;
			}
		});
		return true;
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
		Random r = new Random();
		Pair<Integer,Integer> pos = new Pair<>(0, 0);
		boolean success = false;
		while(!success){

			int x = r.nextInt(GRID_SIZE);
			int y = r.nextInt(GRID_SIZE);
			pos = new Pair<>(x,y);

			if(checkObstaclesPos(pos) && checkPlayerPos(pos) && checkEnemyPos(pos)){
				success = true;	
			}

		return pos;
	}

	/**
	 * 
	 * @param position to check
	 * @return true: if position is empty
	 * false: if position is occupied by the player
	 */  
	private boolean checkEnemyPos(Pair<Integer, Integer> pos) {
		boolean success = true;
		enemyposwithID.forEach(item->{
			     if(item.getY().getX() == pos.getX() && item.getY().getX() == pos.getY()) {
			    	 success = false;
			    	 return;
			     }	
			});
		return success;
=======
		return null;
>>>>>>> olivia_develop
	}

}