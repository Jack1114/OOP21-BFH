package model.abilities;

import controller.player.PlayerAttack;
import model.player.*;

public class DoubleAttack implements Ability{

	private final PlayerAttack playerAttack;
	private final String descr;
	private final String name;

	
	public DoubleAttack(PlayerAttack playerAttack) {
		this.playerAttack = playerAttack;
		this.descr = "Attack your enemies with double power";
		this.name = "Double Attack";
	}

	public String getDescription() {
		return this.descr;
	}
	
	public String getName() {
		return this.name;
	}


	public void apply() {
		// TODO Auto-generated method stub
		playerAttack.setAttackPoints(playerAttack.getAttackPoints() * 2);
	}

}

