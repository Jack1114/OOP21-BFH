package model.abilities;

public interface Ability {
	
	enum Type{
		DOUBLE_ATTACK,
		ELIXIR_OF_LIFE;
	}
	
	/**
	 * Executes the selected ability on the player.
	 */
	void apply();
	
	String getName();
}
