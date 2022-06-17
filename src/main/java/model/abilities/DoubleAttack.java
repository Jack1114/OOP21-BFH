package model.abilities;

import controller.player.PlayerAttack;
import model.player.*;

public class DoubleAttack implements Ability{

	private final PlayerAttack playerAttack;
	private final String descr;
	private final Type type;

	public DoubleAttack(PlayerAttack playerAttack) {
		this.playerAttack = playerAttack;
		this.descr = "Attack your enemies with double power";
		this.type = Ability.Type.DOUBLE_ATTACK;
	}

	public String getDescription() {
		return this.descr;
	}
	
	public String getName() {
		return "Double Attack";
	}

	public void apply() {
		playerAttack.setAttackPoints(playerAttack.getAttackPoints() * 2);
	}

}

