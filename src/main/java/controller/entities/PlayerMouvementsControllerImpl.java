/**
 * 
 */
package controller.entities;

import java.util.Optional;

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
	 * @return 
	 * @return true or false
	 * before moving, the player check if there is an ennemie and if the is an obstacle 
	 * if there is an obstacle, he check if the obstacle's type is the one that can be cross
	 */
	
	public boolean check_advancement(Pair<Integer, Integer> new_player_pos) {
		//TODO: togliere l'if
		if(Global_Generator.obstacles.contains(new_player_pos)) {// verifico se tra la lista degli ostacoli, c'è un ostacolo dove il player vuole spostarsi
			//questa funzione non va bene, devo prendere il tipo di ostacolo che e' in quella posizione
			//filtro la lista finche' non trovo l'ostacolo in quella pos e poi gaccio getType di quell'ostacolo
			Optional<Obstacle> type = Global_Generator.obstacles
					.stream()
					.filter(o -> o.getObstaclePos().equals(new_player_pos))
					.findFirst();
			if (type.isPresent()) {
				switch(type.get().getObstacleType()) {
					case POOL:
						player.player_action.removeAction();
						return true;
					case ROCK:
						return false; 
				}
			}
		}
		//controllo se nella newPos ho un nemico e nel caso lo attacco
		//problema risolvibile con un for normale
		Global_Generator.enemyposwithID.forEach(item->{
			//TODO: cambiare la pos e mettere new_player
		     if(item.getY().getX()==player.getPlayerPosition().getX() && item.getY().getX()==player.getPlayerPosition().getY())
			 {
			     PlayerAttackControlerImpl playerAttackControlerImpl = new PlayerAttackControlerImpl(player);
			     playerAttackControlerImpl.attack();
			 }
		  });
		return true;
	}


}
