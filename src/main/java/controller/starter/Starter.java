package controller.starter;

import controller.globalGenerator.Global_Generator;

public class Starter {

	
	// ESEGUIMI PER FAR PARTIRE TUTTO !!
	
	public static void main(final String[] args) {
		Global_Generator gg = Global_Generator.getInstance();
		gg.play();
	}
}

