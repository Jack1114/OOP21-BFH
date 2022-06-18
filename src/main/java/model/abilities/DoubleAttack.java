package model.abilities;

import controller.player.PlayerAttack;
import model.player.*;

public class DoubleAttack implements Ability{

	private final PlayerAttack playerAttack;
	private final String name;
	private final Type type;

	public DoubleAttack(PlayerAttack playerAttack) {
		this.playerAttack = playerAttack;
		this.name = "Double Attack";
		this.type = Ability.Type.DOUBLE_ATTACK;
	}

	@Override
	public String getName() {
		return this.name;
	}

	@Override
	public void apply() {
		playerAttack.setAttackPoints(playerAttack.getAttackPoints() * 2);
	}

}

