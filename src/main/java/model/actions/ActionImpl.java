 package model.actions;

/**
  * The implementation of the {@link Action}.
  *
  */
public class ActionImpl implements Action{

	
	private final static int DEFAULT_N_OF_ACTIONS = 3;
	private int actions;
	
	/**
	 * Basic constructor to set the max action points available. 
	 */
	public ActionImpl() {
		this.actions = DEFAULT_N_OF_ACTIONS;
	}
	
	/**
     * {@inheritDoc}
     */
	@Override
	public void setActions(int actions) {
		this.actions = actions;
	}
	
	/**
     * {@inheritDoc}
     */
	@Override
	public int getMaxActions() {
		return DEFAULT_N_OF_ACTIONS;
	}
	
	/**
     * {@inheritDoc}
     */
	@Override
	public int getAvailableActions() {
		return this.actions;
	}
	
	/**
     * {@inheritDoc}
     */
	@Override
	public void removeAction() {
		if(isActionAvailable(this.actions)) {
			this.actions--;
		}
	}

	private boolean isActionAvailable(final int actions) {
		if(this.actions > 0) {
			return true;
		}
		return false;
	}
	
	/**
     * {@inheritDoc}
     */
	@Override
	public void addAction() {
		this.actions++;
	}
	
	/**
     * {@inheritDoc}
     */
	@Override
	public void resetActions() {
		this.actions = DEFAULT_N_OF_ACTIONS;
	}

}
