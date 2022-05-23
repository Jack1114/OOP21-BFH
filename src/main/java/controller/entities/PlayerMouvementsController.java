/**
 * 
 */
package controller.entities;

/**
 * @author Olivia
 *
 */
public interface PlayerMouvementsController {
	void left();
	
	void right();
	
	void down();
	
	void up();
	
	void stop();
	
	boolean check_advancement(Pair<Integer, Integer> player_pos);
}
