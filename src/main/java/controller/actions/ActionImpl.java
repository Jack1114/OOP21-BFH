 package controller.actions;

public class ActionImpl implements Action{

	private final static int DEFAULT_N_OF_ACTIONS =3;
	private int actions;
	
	public ActionImpl() {
		this.actions = DEFAULT_N_OF_ACTIONS;
	}
	
	
	@Override
	public void setActions(int actions) {
		this.actions = actions;
	}
	public int getMaxActions() {
		return DEFAULT_N_OF_ACTIONS;
	}

	@Override
	public int getAvailableActions() {
		// TODO Auto-generated method stub
		return this.actions;
	}

	@Override
	public void removeAction() {
		// TODO Auto-generated method stub
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


	@Override
	public void addAction() {
		this.actions++;
	}

	@Override
	public void resetActions() {
		this.actions = DEFAULT_N_OF_ACTIONS;
	}

}
