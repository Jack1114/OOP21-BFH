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
	
	boolean check_advancement();
}
