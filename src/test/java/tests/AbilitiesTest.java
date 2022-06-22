package tests;


import controller.abilities.AbilityManager;
import controller.playerAttack.PlayerAttack;
import controller.playerAttack.PlayerAttackImpl;
import model.abilities.Ability;
import model.player.Pair;
import model.player.Player;
import model.player.PlayerImpl;

import static org.junit.Assert.assertTrue;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

/**
 * Test for abilities.
 */
public class AbilitiesTest {

	private final Player player;
	private final PlayerAttack playerAttackCtrl;
	final Map<Ability.Type, Integer> abilities;
	public AbilityManager abilityManager;

	
	public AbilitiesTest() {
		this.player = new PlayerImpl(new Pair<>(4, 5));
		this.playerAttackCtrl = new PlayerAttackImpl(this.player);
		abilities = new HashMap<>();
		this.abilityManager = new AbilityManager(abilities);
	}
	
	
	/**
	 * Test create abilities.
	 */
	@Test
	public void testGenerateAbilities() {	
		abilities.put(Ability.Type.ELIXIR_OF_LIFE, 2);
		abilities.put(Ability.Type.DOUBLE_ATTACK, 3);
		abilityManager.generateAbilities();
		assertTrue(this.abilityManager.getSize(Ability.Type.ELIXIR_OF_LIFE).equals(2));
		assertTrue(this.abilityManager.getSize(Ability.Type.DOUBLE_ATTACK).equals(3));
	}
	
	/**
	 * Test remove abilities.
	 */
	@Test
	public void testRemoveAbilities() {	
		abilityManager.remove(Ability.Type.DOUBLE_ATTACK);
		abilityManager.remove(Ability.Type.ELIXIR_OF_LIFE);
		assertTrue(this.abilityManager.getSize(Ability.Type.ELIXIR_OF_LIFE).equals(1));
		assertTrue(this.abilityManager.getSize(Ability.Type.DOUBLE_ATTACK).equals(2));
	}
	
	/**
	 * Test use double attack
	 */
	@Test
	public void testDoubleAttack() {
		abilityManager.getAbilityOfType(Ability.Type.DOUBLE_ATTACK).apply();
		assertTrue(Integer.valueOf(playerAttackCtrl.getAttackPoints()).equals(6));
	}
	
	/**
	 * Test use Elixir Of Life
	 */
	@Test
	public void testElixirOfLife() {
		player.getLife().setLifePoints(35);
		abilityManager.getAbilityOfType(Ability.Type.ELIXIR_OF_LIFE).apply();
		assertTrue(Integer.valueOf(player.getLife().getLifePoints()).equals(45));	
	}
	
}
