package model.abilities;

import controller.entities.*;

public class DoubleAttack extends Ability{

	private final PlayerAttackController playerAttack;
	private final String descr;

	
	public DoubleAttack(PlayerAttackController playerAttack) {
		this.playerAttack = playerAttack;
		this.descr = "Attack you enemies with double power";
	}

	public String getDescription() {
		return this.descr;
	}


	public void apply() {
		// TODO Auto-generated method stub
		playerAttack.setAttackPoints(playerAttack.getAttackPoints() * 2);
	}

}

