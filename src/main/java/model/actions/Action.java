package model.actions;

public interface Action {
	
	/**
	 * set the number of available actions for an entity
	 */
	void setActions(int actions);
	
	/**
	 * @return the default number of actions for each turn
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
	
	/**
	 * reset actions to default number of actions
	 */
	void resetActions();

}
