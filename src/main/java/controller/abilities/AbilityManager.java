package controller.abilities;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import controller.globalGenerator.GlobalGenerator;
import model.abilities.Ability;
import model.abilities.DoubleAttack;
import model.abilities.ElixirOfLife;

/** Used for the management of the hero's skills, from generation to check 
 * their availability during the game.
*/

public class AbilityManager {

	private GlobalGenerator gg = GlobalGenerator.getInstance();
	
	private Map<Ability.Type, Integer> listOfAbilities;
	private Map<Ability.Type, List<Ability>> abilities;
	
	public AbilityManager(Map<Ability.Type, Integer> abilities) {
		this.listOfAbilities = abilities;
		this.abilities = new HashMap<>();
	}
	
	public void generateAbilities() {
		listOfAbilities.forEach((a, i) -> abilities.put(a, generate(a, i))  );	
	}
	
	private List<Ability> generate(Ability.Type type, Integer maxNumbers){
		List<Ability> abilitiesOfType = new LinkedList<>();
		for(int i = 0; i < maxNumbers; i++) {
			switch(type) {
				case DOUBLE_ATTACK:
					abilitiesOfType.add(new DoubleAttack(gg.playerAttack));
					break;
				case ELIXIR_OF_LIFE:
					abilitiesOfType.add(new ElixirOfLife(gg.player));
			}
		}
		return abilitiesOfType;
	}
	
	public boolean isAvailable(Ability.Type abilityType) {
		return !(abilities.get(abilityType).isEmpty());
	}
	
	public void remove(Ability.Type abilityType) {
		if(isAvailable(abilityType)) {
			abilities.get(abilityType).remove(0);		
		}
	}
	
	public Ability getAbilityOfType(Ability.Type abilityType) {
		return abilities.get(abilityType).get(0);
	}

	public Integer getSize(Ability.Type abilityType) {
		return abilities.get(abilityType).size();
	}	
	
}
