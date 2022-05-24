package model.abilities;

import controller.entities.PlayerControllerImpl;

public class ElixirOfLife implements Ability{

	private final PlayerControllerImpl player;
	
	public ElixirOfLife(PlayerControllerImpl player) {
		this.player = player;
	}
	
	@Override
	public void apply() {
		// TODO Auto-generated method stub
		player.life.setLifePoints(player.life.getLifePoints() + 10);
	}

}
