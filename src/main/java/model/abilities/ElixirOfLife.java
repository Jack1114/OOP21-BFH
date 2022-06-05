package model.abilities;

import model.player.PlayerImpl;

public class ElixirOfLife implements Ability{

	private final PlayerImpl player;
	private final String descr;
	private final String name;
	
	public ElixirOfLife(PlayerImpl player) {
		this.player = player;
		this.descr = "Drink the Elixir of Life to get more life";
		this.name = "Elixir of Life";
	}
	
	public String getDescription() {
		return this.descr;
	}

	public void apply() {
		// TODO Auto-generated method stub
		player.getLife().setLifePoints(player.getLife().getLifePoints() + 10);
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return this.name;
	}

}
