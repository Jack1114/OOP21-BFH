package model.abilities;

public interface Ability {
	
	enum Type{
		DOUBLE_ATTACK,
		ELIXIR_OF_LIFE;
	}
	

	void apply();
	

	String getName();
}
