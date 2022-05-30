package model.abilities;

import controller.entities.PlayerAttackControlerImpl;

public class DoubleAttack extends Ability{

	private final PlayerAttackControlerImpl playerAttack;
	private final String descr;

	
	public DoubleAttack(PlayerAttackControlerImpl playerAttack) {
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

