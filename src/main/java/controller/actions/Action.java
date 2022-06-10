package controller.actions;

public interface Action {
	/**
	 * set the number of available actions for an entity
	 */
	void setActions(int actions);
	/**
	 * @return the max number of available actions
	 */
	public int getMaxActions();
	
	/**
	 * 
	 * @return number of available actions
	 */
	int getAvailableActions();
	
	/**
	 * remove one action from the available actions
	 */
	void removeAction();

	/**
	 * add one action from the available actions
	 */
	void addAction();
	void resetActions();

}
