package model.actions;

/**
 * Manages the Action Points. 
 */
public interface Action {
	
	/**
	 * Set the number of available actions for an Entity.
	 */
	void setActions(int actions);
	
	/**
	 * @return The default number of actions for each turn.
	 */
	public int getMaxActions();
	
	/**
	 * 
	 * @return Number of available actions.
	 */
	int getAvailableActions();
	
	/**
	 * Remove one action from the available actions.
	 */
	void removeAction();

	/**
	 * Add one action from the available actions.
	 */
	void addAction();
	
	/**
	 * Reset actions to default number of actions.
	 */
	void resetActions();

}
