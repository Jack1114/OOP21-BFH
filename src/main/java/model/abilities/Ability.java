package model.abilities;

public interface Ability {
	
	enum Type{
		DOUBLE_ATTACK,
		ELIXIR_OF_LIFE;
	}
	
	/**
	 * apply the effect of the ability on the player
	 */
	void apply();
	
	/**
	 * 
	 * @return name of the ability
	 */
	String getName();
}
