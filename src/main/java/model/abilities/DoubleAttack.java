package model.abilities;

import controller.player.PlayerAttack;
import model.player.*;

public class DoubleAttack extends Ability{

	private final PlayerAttack playerAttack;
	private final String descr;

	
	public DoubleAttack(PlayerAttack playerAttack) {
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

