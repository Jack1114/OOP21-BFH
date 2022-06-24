package model.abilities;

import model.player.Player;


public class ElixirOfLife implements Ability{

	private final Player player;
	private final String name;
	private final Type type;
	
	public ElixirOfLife(Player player) {
		this.player = player;
		this.name = "Elixir of Life";
		this.type = Ability.Type.ELIXIR_OF_LIFE;
	}

	 /**
     * {@inheritDoc}
     */
	public void apply() {
		player.getLife().setLifePoints(player.getLife().getLifePoints() + 10);
	}

	@Override
	public String getName() {
		return this.name;
	}

}
