package controller.enemies;

import controller.globalGenerator.GlobalGenerator;
import controller.playerAttack.PlayerAttack;
import controller.playerAttack.PlayerAttackImpl;
import model.enemies.Enemy;
import model.player.Pair;
import model.player.Player;

/**
 * Used to manage Enemy movement, collisions and actions.
 *
 */
public class Enemy_move_control {
	
	private static int dx;
	private static int dy;
	
	static Pair<Integer,Integer> newenemyPos;
	private static int enemyID;
	private static int act_pt;
	private static boolean collide;
	private static boolean moved_diagonally=false;
	private static boolean wait= false;
	private static boolean previously_acted=false;

	private static GlobalGenerator gg = GlobalGenerator.getInstance();
	private static Player player = gg.player;
	private static PlayerAttack playerAtt = gg.playerAttack;
	
	public static void nextMove(Pair<Integer, Pair<Integer, Integer>> elem, int actionpt) {

		act_pt=actionpt;
		diffX(elem.getY().getX());
		diffY(elem.getY().getY());
		enemyID = elem.getX();
		
		if(wait) {
			wait = false;
			moved_diagonally = false;
			previously_acted = false;
			gg.enemyposwithID.set(elem.getX(), new Pair<>(elem.getX(), elem.getY()));
		}else {
			if(dx<dy) {
				move_horizon(elem.getY());
				gg.enemyposwithID.set(elem.getX(), new Pair<>(elem.getX(), newenemyPos));
				previously_acted=true;
			}else {
				move_vert(elem.getY());	
				gg.enemyposwithID.set(elem.getX(), new Pair<>(elem.getX(), newenemyPos));
				previously_acted=true;
			}
		}
	}

	
	/**
	 * Enemy attack action.
	 */
	private static void attackHero() {
		var correct_enemy = gg.enemies.get(enemyID);
		System.out.println("Enemy " + enemyID + " attacking hero - damage on hero of " + correct_enemy.GetATK());
		playerAtt.getHit(enemyID,correct_enemy.GetATK());  	
	}
	
	/**
	 * Manages the basic movements of the Enemy, left or right.
	 * @param elem Used to track the enemy position prior its movement.
	 */
	private static void move_horizon(Pair<Integer, Integer> elem) {
		
		var old_pos=elem;
		int y=elem.getY();
		
		if(elem.getY() < player.getPlayerPosition().getY()) {
			y++;
		}else {
			y--;
		}
		newenemyPos = new Pair<>(elem.getX(), y);
		
		checkwait(old_pos, newenemyPos, false);
	}
	
	/**
	 * Manages the basic movements of the Enemy, up or down.
	 * @param elem Used to track the enemy position prior its movement.
	 */
	private static void move_vert(Pair<Integer, Integer> elem) {
		
		var old_pos=elem;
		int x=elem.getX();
		
		if(elem.getX() < player.getPlayerPosition().getX()) {
			x++;
		}else {
			x--;
		}
		newenemyPos = new Pair<>(x, elem.getY());
		
		checkwait(old_pos, newenemyPos, true);	
	}
	
	/**
	 * Checks if @param newPos is different from playerPos, obstaclePos or another enemyPos.
	 * If true, the enemy will move, if any of the checks return a false, the enemy will not
	 * update its position.
	 * @param old_pos Is previous position.
	 * @param newPos Is the new position after the Enemy turn.
	 * @param orientationMovement Check the boolean return from {@link #move_vert(Pair)} and {@link #move_horizon(Pair)}.
	 */
		private static void checkwait(Pair<Integer, Integer> old_pos, Pair<Integer, Integer> newPos, boolean orientationMovement) {
			
			if (checkenemypos(newPos)) {
				newenemyPos = new Pair<>(old_pos.getX(), old_pos.getY());
			} else if(newPos.equals(player.getPlayerPosition())) {
				attackHero();
				newenemyPos = new Pair<>(old_pos.getX(), old_pos.getY());
			} else if (gg.obstacles.stream().anyMatch(o -> o.getObstaclePos().equals(newPos))){
				
				if(!moved_diagonally && act_pt == 0) {
					    diagonalMovement(old_pos, newPos, orientationMovement);
					   
					    //Checking newPos does not equal playerPos. If true, the enemy will not move.
					    gg.enemyposwithID.forEach(elem->{
					    	if( elem.getY().equals(newenemyPos) || newenemyPos.equals(player.getPlayerPosition())) {
					    		newenemyPos = old_pos;
					    	}
					    });				    
				} else {
						newenemyPos = new Pair<>(old_pos.getX(), old_pos.getY());
				  }
			}
			if(newenemyPos.getX() >= gg.getGridSizeX() || newenemyPos.getY() >= gg.getGridSizeY()) {
				newenemyPos = old_pos;
			}
		}
	/**
	 * Handles all the diagonal movements the enemy might need to perform.
	 * @param old_pos Is previous position.
	 * @param newPos Is the new position after the Enemy turn.
	 * @param orientationMovement Checks if it is an horizontal movement.
	 */
	private static void diagonalMovement(Pair<Integer, Integer> old_pos, Pair<Integer, Integer> newPos, boolean orientationMovement) {
	
		Pair<Integer, Integer> p1 = new Pair<Integer, Integer>(old_pos.getX()-1,old_pos.getY()-1);
		Pair<Integer, Integer> p2 = new Pair<Integer, Integer>(old_pos.getX()+1,old_pos.getY()-1);
		Pair<Integer, Integer> p3 = new Pair<Integer, Integer>(old_pos.getX()-1,old_pos.getY()+1);
		Pair<Integer, Integer> p4 = new Pair<Integer, Integer>(old_pos.getX()+1,old_pos.getY()+1);
		
		/*
		 * Each if checks if the direction the enemy wants to move is free
		 * and for each case, it calculates the diagonal jump.
		 * 
		 */
		if(orientationMovement) {			
				if(old_pos.getX() > newPos.getX()) {
					if(upfree(new Pair<Integer, Integer>(old_pos.getX(), old_pos.getY()-1)) 
							&&  !gg.obstacles
							.stream()
							.anyMatch(o -> o.getObstaclePos().equals(p1))){ //1
						newenemyPos = new Pair<>(old_pos.getX()-1, old_pos.getY()-1);
						moved_diagonally = true;
						wait = true;
						
					} else if(downfree(new Pair<Integer,Integer>(old_pos.getX(), old_pos.getY()+1))  
							&& !gg.obstacles
							.stream()
							.anyMatch(o -> o.getObstaclePos().equals(p3))){ 
						newenemyPos = new Pair<>(old_pos.getX()-1, old_pos.getY()+1);
						moved_diagonally = true;
						wait = true;					
					} else {
						newenemyPos = new Pair<>(old_pos.getX(),old_pos.getY());
					}
				} else {
					if(upfree(new Pair<Integer, Integer>(old_pos.getX(), old_pos.getY( )-1))  
							&& !gg.obstacles
							.stream()
							.anyMatch(o -> o.getObstaclePos().equals(p2))){ 
						newenemyPos = new Pair<>(old_pos.getX()+1, old_pos.getY()-1);
						moved_diagonally = true;
						wait = true;						
					} else if(downfree(new Pair<Integer, Integer>(old_pos.getX(), old_pos.getY()+1)) 
							&& !gg.obstacles
							.stream()
							.anyMatch(o -> o.getObstaclePos().equals(p4))){ 
						newenemyPos = new Pair<>(old_pos.getX()+1, old_pos.getY()+1);
						moved_diagonally = true;
						wait = true;
					} else {
						newenemyPos = new Pair<>(old_pos.getX(), old_pos.getY());
					}
				}
			} else {
			if(old_pos.getY() > newPos.getY()) {
						if(leftfree(new Pair<Integer, Integer>(old_pos.getX()-1, old_pos.getY())) && !gg.obstacles
								.stream()
								.anyMatch(o -> o.getObstaclePos().equals(p1))){ 
							newenemyPos = new Pair<>(old_pos.getX()-1 ,old_pos.getY()-1);
							moved_diagonally = true;
							wait = true; 
						} else if(rightfree(new Pair<Integer, Integer>(old_pos.getX()+1, old_pos.getY())) && !gg.obstacles
								.stream()
								.anyMatch(o -> o.getObstaclePos().equals(p2))){ 
							newenemyPos = new Pair<>(old_pos.getX()+1, old_pos.getY()-1);
							moved_diagonally = true;
							wait = true;
						} else {
							newenemyPos = new Pair<>(old_pos.getX(), old_pos.getY());
						}
					} else {
						if(leftfree(new Pair<Integer, Integer>(old_pos.getX()-1, old_pos.getY())) && !gg.obstacles
								.stream()
								.anyMatch(o -> o.getObstaclePos().equals(p3))){ 
							newenemyPos = new Pair<>(old_pos.getX()-1, old_pos.getY()+1);
							moved_diagonally = true;
							wait = true;
						} else if(rightfree(new Pair<Integer, Integer>(old_pos.getX()+1, old_pos.getY()))  && !gg.obstacles
								.stream()
								.anyMatch(o -> o.getObstaclePos().equals(p4))){ 
							newenemyPos = new Pair<>(old_pos.getX()+1, old_pos.getY()+1);
							moved_diagonally = true;
							wait = true;
						} else {
							newenemyPos = new Pair<>(old_pos.getX(), old_pos.getY());
						}
					}
				}
		
	}

	private static boolean upfree(Pair<Integer, Integer> up) {
		if(!checkenemypos(up) && gg.checkObstaclesPos(up)) {
			return true;
		} else {
			return false;
		}
	}
	
	private static boolean downfree(Pair<Integer, Integer> down) {
		if(!checkenemypos(down) && gg.checkObstaclesPos(down)) {
			return true;
		} else {
			return false;
		}
	}
	
	private static boolean leftfree(Pair<Integer, Integer> left) {
		if(!checkenemypos(left) && gg.checkObstaclesPos(left)) {
			return true;
		} else {
			return false;
		}
	}
	
	private static boolean rightfree(Pair<Integer, Integer> right) {
		if(!checkenemypos(right) && gg.checkObstaclesPos(right)) {
			return true;
		} else {
			return false;
		}
	}

	private static boolean checkenemypos(Pair<Integer, Integer> newPos) {
		collide = false;
		gg.enemyposwithID.forEach(item -> {
			if(item.getY().getX() == newPos.getX() && item.getY().getY() == newPos.getY()) {
				collide = true;
			}
		});
		return collide;
	}

	private static void diffX(Integer enX) {
		int diff = Math.abs(enX-player.getPlayerPosition().getX());
		setdx(diff);
	}

	private static void setdx(int diff) {
		dx = diff;
	}

	private static void diffY(Integer enY) {
		int diff = Math.abs(enY-player.getPlayerPosition().getY());
		setdy(diff);
	}
	
	private static void setdy(int diff) {
		dy = diff;
	}

}
