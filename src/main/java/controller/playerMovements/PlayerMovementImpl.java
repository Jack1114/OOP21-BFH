/**
 * 
 */
package controller.playerMovements;

import java.util.Optional;
import controller.globalGenerator.GlobalGenerator;
import controller.playerAttack.PlayerAttack;
import model.enemies.Enemy;
import model.obstacles.Obstacle;
import model.player.Pair;
import model.player.Player;

/**
 * The implementation of the {@link PlayerMovement}.
 *
 */
public class PlayerMovementImpl implements PlayerMovement {

	
	private Pair<Integer,Integer> new_player_pos;
	private final Player player;
	private GlobalGenerator gg = GlobalGenerator.getInstance();
	public PlayerMovementImpl(Player player) {
		this.player = player;
	}
	
	/**
     * {@inheritDoc}
     */
	public void left() {
		new_player_pos = new Pair<>(player.getPlayerPosition().getX()-1, player.getPlayerPosition().getY());
		if(check_advancement(new_player_pos)) {
			move(new_player_pos);
		}		
	}

	/**
     * {@inheritDoc}
     */
	public void right() {
		new_player_pos = new Pair<>(player.getPlayerPosition().getX()+1, player.getPlayerPosition().getY());
		if(check_advancement(new_player_pos)) {
			move(new_player_pos);
		}
	}

	/**
     * {@inheritDoc}
     */
	public void down() {
		new_player_pos = new Pair<>(player.getPlayerPosition().getX(), player.getPlayerPosition().getY()+1);
		if(check_advancement(new_player_pos)) {
			move(new_player_pos);		
		}
	}

	/**
     * {@inheritDoc}
     */
	public void up() {
		new_player_pos = new Pair<>(player.getPlayerPosition().getX(), player.getPlayerPosition().getY()-1);
		if(check_advancement(new_player_pos)) {
			move(new_player_pos);
		}
	}

	
	/**
     * {@inheritDoc}
     */
	public void stop() {
		new_player_pos = new Pair<>(player.getPlayerPosition().getX(), player.getPlayerPosition().getY());	
	}
	
	
	private void move(Pair<Integer, Integer> newPos) {
			player.setPlayerPosition(newPos);
	}

	
	/**
     * {@inheritDoc}
     */
	public boolean check_advancement(Pair<Integer, Integer> new_player_pos) {
		//Checks for Obstacles
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
						System.out.println("You need at least 2 action points to cross this mud pool.");
						return false;
					}
					
				case ROCK:
					player.getLife().setLifePoints(player.getLife().getLifePoints()-2);
					System.out.println("These rocks are sharp, don't hit them, it's going to hurt you!");
					return false;
			}
		}

		//Checks for Player fleeing
		if( (new_player_pos.getX()<0 || new_player_pos.getX()>gg.getGridSizeX()-1 ) || new_player_pos.getY()<0 || new_player_pos.getY()>gg.getGridSizeY()-1 ){
			System.out.println("You can't leave the arena, don't give up like that!");
			return false;
		}

		//Checks for Enemies. If there is an Enemy, it triggers an attack.
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
