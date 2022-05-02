/**
 * 
 */
package controller.entities;

/**
 * @author Olivia
 *
 */
public interface PlayerMouvementsController {
	void left(Pair<Integer, Integer> player_pos);
	
	void right(Pair<Integer, Integer> player_pos);
	
	void down(Pair<Integer, Integer> player_pos);
	
	void up(Pair<Integer, Integer> player_pos);
	
	boolean check_advancement(Pair<Integer, Integer> player_pos);
}
