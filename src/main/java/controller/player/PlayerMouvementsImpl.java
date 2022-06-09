/**
 * 
 */
package controller.player;

import java.util.Optional;

import controller.globalGenerator.Global_Generator;
import controller.obstacles.Obstacle;
import controller.obstacles.ObstacleImpl;
import model.enemies.Enemy;
import model.player.Pair;
import model.player.PlayerImpl;

/**
 * @author Olivia
 *
 */
public class PlayerMouvementsImpl implements PlayerMouvement {

	private Pair<Integer,Integer> new_player_pos;
	private final PlayerImpl player;
	private Global_Generator gg = Global_Generator.getInstance();
	
	public PlayerMouvementsImpl(PlayerImpl newPlayer) {
		this.player = newPlayer;
	}
	
	
	/**
	 * the player move to the left
	 */
	public void left() {
		new_player_pos=new Pair<>(player.getPlayerPosition().getX()-1,player.getPlayerPosition().getY());
		if(check_advancement(new_player_pos)) {
			move(new_player_pos);
		}		
	}

	/**
	 * the player move to the right
	 */
	public void right() {
		new_player_pos=new Pair<>(player.getPlayerPosition().getX()+1,player.getPlayerPosition().getY());
		if(check_advancement(new_player_pos)) {
			move(new_player_pos);
		}
	}

	/**
	 * the player move to down
	 */
	public void down() {
		new_player_pos=new Pair<>(player.getPlayerPosition().getX(),player.getPlayerPosition().getY()+1);
		if(check_advancement(new_player_pos)) {
			move(new_player_pos);		
		}
	}

	/**
	 * the player move to up
	 */
	public void up() {
		new_player_pos=new Pair<>(player.getPlayerPosition().getX(),player.getPlayerPosition().getY()-1);
		if(check_advancement(new_player_pos)) {
			move(new_player_pos);
		}
	}

	
	/**
	 * the player can not move because of an obstacle or something else
	 */
	public void stop() {
		new_player_pos=new Pair<>(player.getPlayerPosition().getX(),player.getPlayerPosition().getY());	
	}
	
	
	private void move(Pair<Integer, Integer> newPos) {
			player.setPlayerPosition(newPos);
	}

	
	/**
	 * 
	 * @return true or false
	 * before moving, the player check if there is an enemy and if there is an obstacle 
	 * if there is an obstacle, he check if the obstacle's type is the one that can be cross
	 */	
	public boolean check_advancement(Pair<Integer, Integer> new_player_pos) {
		//check sugli ostacoli	
		Optional<Obstacle> type = gg.obstacles
				.stream()
				.filter(o -> o.getObstaclePos().equals(new_player_pos))
				.findFirst();
		if (type.isPresent()) {
			switch(type.get().getObstacleType()) {
				case POOL:
					if(player.getPlayer_action().getAvailableActions() >= 2) {
						player.getPlayer_action().removeAction();
						return true;
					}else {
						System.out.println("You don't have enough actions to cross the pool");
						return false;
					}
					
				case ROCK:
					player.getLife().setLifePoints(player.getLife().getLifePoints()-2);
					return false;
			}
		}

		//check sui bordi dello schermo
		if( (new_player_pos.getX()<0 || new_player_pos.getX()>gg.GRID_SIZE-1) || new_player_pos.getY()<0 || new_player_pos.getY()>gg.GRID_SIZE-1 ){
			System.out.println("vado fuori dai bordi!");
			return false;
		}

		//check sui nemici
		for(var item : gg.enemyposwithID) {
		     if(item.getY().equals(new_player_pos)){
		    	int enemyID = item.getX();
		    	Enemy enemy = gg.enemies.stream().filter(e -> e.getID() == enemyID).findFirst().get();
				gg.playerAttack.attack(enemy);
			    return false;
			 }
		}
		return true;
	}


}
