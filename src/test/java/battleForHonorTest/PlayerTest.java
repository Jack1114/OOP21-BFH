package battleForHonorTest;

import static org.junit.Assert.assertTrue;

import java.util.Optional;

import org.junit.Test;

import controller.globalGenerator.GlobalGenerator;
import model.enemies.Enemy;
import model.obstacles.Obstacle;
import model.player.Pair;
import model.player.Player;
import model.player.PlayerImpl;


public class PlayerTest {
	private static final int MAX_LIFE_POINTS = 50; 
	private GlobalGenerator gg = GlobalGenerator.getInstance();
	private Pair<Integer, Integer> playerPosition=gg.rand_pos_player(gg.getGridSizeX(), gg.getGridSizeY());
	private final Player player = new PlayerImpl(playerPosition);
	
	@Test
	public void testPlayerMovement() {
		 assertTrue(Integer.valueOf(this.player.getLife().getMaxLifePoints()).equals(MAX_LIFE_POINTS));
		 
		 /*
		  * Test of right movement of player
		  */
		gg.player.setPlayerPosition(playerPosition);
		final Pair<Integer, Integer> new_player_pos_r = new Pair<>(player.getPlayerPosition().getX()+1, player.getPlayerPosition().getY());
		assertTrue(player.getPlayerPosition().equals(new_player_pos_r));

	    /*
	     * Test of left movement of player
	     */
		final Pair<Integer, Integer> new_player_pos_l = new Pair<>(player.getPlayerPosition().getX()-1, player.getPlayerPosition().getY());	
		assertTrue(player.getPlayerPosition().equals(new_player_pos_l));


	    /*
	     * Test of up movement of player
	     */
		final Pair<Integer, Integer> new_player_pos_u = new Pair<>(player.getPlayerPosition().getX(), player.getPlayerPosition().getY()-1);	
		assertTrue(player.getPlayerPosition().equals(new_player_pos_u));

	    /*
	     * Test of down movement of player
	     */
		final Pair<Integer, Integer> new_player_pos_d = new Pair<>(player.getPlayerPosition().getX(), player.getPlayerPosition().getY()+1);
		assertTrue(player.getPlayerPosition().equals(new_player_pos_d));

			
	}
	@Test
	public void testCollision() {
		//Checks for Obstacles
		Optional<Obstacle> type = gg.obstacles
				.stream()
				.filter(o -> o.getObstaclePos().equals(new_player_pos))
				.findFirst();
		if (type.isPresent()) {
			switch(type.get().getObstacleType()) {
				case POOL:
					if(player.getPlayer_action().getAvailableActions() >= 2) {
						assertTrue(Integer.valueOf(this.player.getPlayer_action().getAvailableActions()).equals(player.getPlayer_action().removeAction());

					}else {
						System.out.println("You need at least 2 action points to cross this mud pool.");

					}
					
				case ROCK:
					assertTrue(Integer.valueOf(this.player.getLife().setLifePoints()).equals(player.getLife().getLifePoints()-2));
					System.out.println("These rocks are sharp, don't hit them, it's going to hurt you!");

			}
		}

		//Checks for Player fleeing
		if( (new_player_pos.getX()<0 || new_player_pos.getX()>gg.getGridSizeX()-1 ) || new_player_pos.getY()<0 || new_player_pos.getY()>gg.getGridSizeY()-1 ){
			System.out.println("You can't leave the arena, don't give up like that!");

		}

		//Checks for Enemies. If there is an Enemy, it triggers an attack.
		for(var item : gg.enemyposwithID) {
		     if(item.getY().equals(new_player_pos)){
		    	int enemyID = item.getX();
		    	Enemy enemy = gg.enemies.stream().filter(e -> e.getID() == enemyID).findFirst().get();
				assertTrue(gg.playerAttack.attack(enemy));

			 }
		}

	}
	public void move(Pair<Integer, Integer> newPos) {
		player.setPlayerPosition(newPos);
	}
	 
}
