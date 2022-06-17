package model.abilities;

import model.player.PlayerImpl;

public class ElixirOfLife implements Ability{

	private final PlayerImpl player;
	private final String descr;
	private final Type type;
	
	public ElixirOfLife(PlayerImpl player) {
		this.player = player;
		this.descr = "Drink the Elixir of Life to get more life";
		this.type = Ability.Type.ELIXIR_OF_LIFE;
	}
	
	public String getDescription() {
		return this.descr;
	}

	public void apply() {
		player.getLife().setLifePoints(player.getLife().getLifePoints() + 10);
	}

	@Override
	public String getName() {
		return "Elixir of Life";
	}

}
