package model.abilities;

import battleforhonor.Enemy;
import controller.entities.PlayerAttackControlerImpl;

public class DoubleAttack implements Ability{

	private final PlayerAttackControlerImpl playerAttack;
	
	public DoubleAttack(PlayerAttackControlerImpl playerAttack) {
		this.playerAttack = playerAttack;
	}
	@Override
	public void apply() {
		// TODO Auto-generated method stub
		playerAttack.setAttackPoints(playerAttack.getAttackPoints() * 2);
	}

}
