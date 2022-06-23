package battleForHonorTest;

import static org.junit.Assert.assertTrue;
import java.util.Optional;
import org.junit.Test;
import controller.globalGenerator.GlobalGenerator;
import model.obstacles.Obstacle;
import model.player.Pair;
import model.player.Player;
import model.player.PlayerImpl;

public class PlayerTest {
	private static final int MAX_LIFE_POINTS = 50;
	private Player player=new PlayerImpl(new Pair<>(1,1));;
	private GlobalGenerator gg = GlobalGenerator.getInstance();
	
	@Test
	public void testPlayerLife() {
		 assertTrue(Integer.valueOf(this.player.getLife().getMaxLifePoints()).equals(MAX_LIFE_POINTS));
	}
	
	@Test
	public void testPlayerMouvement() {
		 /*
		  * Test of right movement of player
		  */
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
	public void testCollisionWithObstacles(Pair<Integer, Integer> new_player_pos) {
		//Checks for Obstacles
		Optional<Obstacle> type = gg.obstacles
				.stream()
				.filter(o -> o.getObstaclePos().equals(new_player_pos))
				.findFirst();
		if (type.isPresent()) {
			switch(type.get().getObstacleType()) {
				case POOL:
					if(player.getPlayer_action().getAvailableActions() >= 2) {
						assertTrue(Integer.valueOf(this.player.getPlayer_action().getMaxActions()).equals(1));
					}
					
				case ROCK:
					assertTrue(Integer.valueOf(this.player.getLife().getLifePoints()).equals(MAX_LIFE_POINTS-2));

			}
		}

	}

}
