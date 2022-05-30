package model.abilities;

import controller.entities.PlayerControllerImpl;

public class ElixirOfLife extends Ability{

	private final PlayerControllerImpl player;
	private final String descr;
	
	public ElixirOfLife(PlayerControllerImpl player) {
		this.player = player;
		this.descr = "Drink the Elixir of Life to get more life";
	}
	
	public String getDescription() {
		return this.descr;
	}

	public void apply() {
		// TODO Auto-generated method stub
		player.getLife().setLifePoints(player.getLife().getLifePoints() + 10);
	}

}
