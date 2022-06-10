package model.abilities;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import controller.globalGenerator.Global_Generator;
import model.abilities.Ability.Type;

public class AbilityManager {

	//prende la mappa dal global generator
	private Global_Generator gg = Global_Generator.getInstance();
	
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
