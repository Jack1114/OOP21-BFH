/**
 * 
 */
package controller.entities;

import battleforhonor.Global_Generator;
import model.obstacles.Obstacle;
import model.obstacles.ObstacleImpl;

/**
 * @author Olivia
 *
 */
public class PlayerMouvementsControllerImpl implements PlayerMouvementsController {

	private boolean success;
	private Pair<Integer,Integer> new_player_pos;
	PlayerControllerImpl player;
	
	public PlayerMouvementsControllerImpl(PlayerControllerImpl newPlayer) {
		this.player = newPlayer;
	}
	
	
	/**
	 * the player move to the left
	 */
	public void left() {
		new_player_pos=new Pair<>(player.getPlayerPosition().getX()-1,player.getPlayerPosition().getY());
		move(new_player_pos);
	}

	/**
	 * the player move to the right
	 */
	public void right() {
		new_player_pos=new Pair<>(player.getPlayerPosition().getX()+1,player.getPlayerPosition().getY());
		move(new_player_pos);
	}

	/**
	 * the player move to down
	 */
	public void down() {
		new_player_pos=new Pair<>(player.getPlayerPosition().getX(),player.getPlayerPosition().getY()-1);
		move(new_player_pos);
	}

	/**
	 * the player move to up
	 */
	public void up() {
		new_player_pos=new Pair<>(player.getPlayerPosition().getX(),player.getPlayerPosition().getY()+1);
		move(new_player_pos);
	}

	
	/**
	 * the player can not mot because of an obstacle or something else
	 */
	public void stop() {
		new_player_pos=new Pair<>(player.getPlayerPosition().getX(),player.getPlayerPosition().getY());	
	}
	
	
	private void move(Pair<Integer, Integer> newPos) {
		if(check_advancement(newPos)) {
			player.setPlayerPosition(newPos);
		}
	}
	
	/**
	 * @return true or false
	 * before moving, the player check if there is an ennemie and if the is an obstacle 
	 * if there is an obstacle, he check if the obstacle's type is the one that can be cross
	 */
	
	public boolean check_advancement(Pair<Integer, Integer> new_player_pos) {
		success=true;
		//global generator da sistemare col conflitto di obstacles e obstacles_ps
		if(Global_Generator.obstacles.contains(new_player_pos)) {// verifico se tra la lista degli ostacoli, c'è un ostacolo dove il player vuole spostarsi
			ObstacleImpl.Type type = Global_Generator.obstacles.getObstaclesType();
			switch(type) {
				case type.POOL:
					player.player_action.removeAction();
					success=true;
				case type.ROCK:
					success=false; 
			}
		}
			success=false ;
		 Global_Generator.enemyposwithID.forEach(item->{
		     if(item.getY().getX()==player.getPlayerPosition().getX() && item.getY().getX()==player.getPlayerPosition().getY())
			 {
			     PlayerAttackControlerImpl playerAttackControlerImpl=new PlayerAttackControlerImpl(player);
			     playerAttackControlerImpl.attack(new_player_pos);
			     success=false;
			 }
		  });

		return success;
	}


}
