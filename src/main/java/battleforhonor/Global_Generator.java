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
	
	public static List<Pair<Integer,Integer>> obstacles_pos = new ArrayList<>(); 
	
	//Lista degli ostacoli
	public List<Obstacle> obstacles = new ArrayList<>();
	private final ObstacleGenerator obstacleGenerator = new ObstacleGenerator(obstacles);

	
	public static List<Enemy> enemies= new ArrayList<Enemy>();

	public static List<Integer> skipenemy= new ArrayList<>(); //contiene gli ID dei vari nemici morti 
	
	int NUM_ENEMIES=3;

	private int totactions=0;
	GUI g = new GUI(15);
	//private int hero_actions=0;
	
	void generation() {
		
		//TODO: da sostituire
		Hero.spawn(15);
		
		//PlayerController player = new PlayerControllerImpl();
		obstacleGenerator.generateObstacles();
		generate_enemies();

		g.update();
		//System.out.println("geneneration done ok !! "); // stampa per vedere se finisce il ciclo [ ok ]
		/*   //stampa per vedere le statistiche pseudo-randomiche [ ok ]
		
		for(int i=0;i<NUM_ENEMIES;i++) {
			var elem=enemyposwithID.get(i);
			System.out.println("----------[ "+i+" ]-----------");
			System.out.println("pos x = "+elem.getX()+" pos y = "+elem.getY());
			
			var elem2=enemies.get(i);
			System.out.println(elem2.toString());
		}
		*/

		int turn=0;
		int totactions=0;
		while(true && totactions<80) {
			System.out.println("---- turn "+turn+" ----");	
			System.out.println("skipped enemy size = "+skipenemy.size());
			
			if(skipenemy.size()==NUM_ENEMIES) {
				reset();
				//TODO: da sostituire
				recover_HERO();
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
		obstacles_pos = new ArrayList<>(); 
	}


	//TODO: da sostituire
	private void recover_HERO() {
		// TODO Auto-generated method stub
		System.out.println("Ti senti pi� forte di prima e le tue ferite sono state curate !!");
		Hero.SetHP(Hero.MAX_HP);
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
		while(Hero.getActions() > 0) {
			Hero.removeAction();
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	        System.out.println("Enter Input : w=UP // s=DOWN // a=LEFT // d=RIGHT ");
	        try {
	            String s = br.readLine();
	            Pair<Integer,Integer> heropos= new Pair<>(Hero.getX(),Hero.getY());
	            switch(s) {
	            	case("w"):
	            		HeroMovement.up(heropos);
	            		g.update();
	            	break;
	            	case("s"):
	            		HeroMovement.down(heropos);
	            		g.update();
	            	break;
	            	case("a"):
	            		HeroMovement.left(heropos);
	            		g.update();
	            	break;
	            	case("d"):
	            		HeroMovement.right(heropos);
	            		g.update();
	            	break;
					case("e"):
						HeroMovement.attack();
						//g.update();
						break;
					case("1"):
						HeroMovement.attackWithAbility("1");
						break;
					case("2"):
						HeroMovement.attackWithAbility("2");
						break;
	            	default:
	            		HeroMovement.stop(heropos);
	            		g.update();
	            	break;
	            }
	            	
	            //System.out.println(s);
			}catch(Exception e) {
	            System.out.println(e);
	        }
		}
		Hero.resetActions();
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
	 * @return 
	 * @return true: if position is empty
	 * false: if position has and obstacle
	 */
	public boolean checkObstaclesPos(Pair<Integer, Integer> position) {
		obstacles.forEach(item -> {
			if(item.getObstaclePos().equals(position)) {
				return false;
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

}
