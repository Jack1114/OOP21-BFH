package model.abilities;

import model.player.PlayerImpl;

public class ElixirOfLife extends Ability{

	private final PlayerImpl player;
	private final String descr;
	
	public ElixirOfLife(PlayerImpl player) {
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
