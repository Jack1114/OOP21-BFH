

public class HeroMovement {
	
	static Pair<Integer, Integer> new_heropos;
	private static boolean success;
	
	
	public static void left(Pair<Integer, Integer> heropos) {
		// TODO Auto-generated method stub
		//System.out.println("left");
		new_heropos = new Pair<>(heropos.getX()-1,heropos.getY());
		if(check_advancement(new_heropos)) {
			heropos=new_heropos;
			Hero.setX(heropos.getX());
		}
	}
	public static void right(Pair<Integer, Integer> heropos) {
		// TODO Auto-generated method stub
		//System.out.println("right");
		new_heropos = new Pair<>(heropos.getX()+1,heropos.getY());
		if(check_advancement(new_heropos)) {
			heropos=new_heropos;
			Hero.setX(heropos.getX());
		}
	}
	public static void up(Pair<Integer, Integer> heropos) {
		// TODO Auto-generated method stub
		//System.out.println("up");
		new_heropos = new Pair<>(heropos.getX(),heropos.getY()-1);
		if(check_advancement(new_heropos)) {
			heropos=new_heropos;
			Hero.setY(heropos.getY());
		}
	}
	public static void down(Pair<Integer, Integer> heropos) {
		// TODO Auto-generated method stub
		//System.out.println("down");
		new_heropos = new Pair<>(heropos.getX(),heropos.getY()+1);
		if(check_advancement(new_heropos)) {
			heropos=new_heropos;
			Hero.setY(heropos.getY());
		}
	}
	public static void stop(Pair<Integer, Integer> heropos) {
		// TODO Auto-generated method stub
		//new_heropos = new Pair<>(heropos.getX(),heropos.getY());
		System.out.println("stop");
	}
	
	
	private static boolean check_advancement(Pair<Integer, Integer> new_heropos) {
		// TODO Auto-generated method stub
		success=true;
		// controllo se sbatto contro un ostacolo 
		if(Global_Generator.obstacles_pos.contains(new_heropos)){
			//System.out.println("discarded action 1");
			success=false;
		}
		// controllo se va fuori dai bordi schermo
		if( (new_heropos.getX()<0 || new_heropos.getX()>Global_Generator.GRID_SIZE-1) || (new_heropos.getY()<0 || new_heropos.getY()>Global_Generator.GRID_SIZE-1) ){
			System.out.println("vado fuori dai bordi !!!!!!!");
			success=false;
		}	
		// controllo se la nuova posizione coincide con la pos di un nemico + attaco
		Global_Generator.enemyposwithID.forEach(item->{
			if(item.getY().getX()==new_heropos.getX() && item.getY().getY()==new_heropos.getY()) {
				//System.out.println("discarded action 2");
				Attack(new_heropos);
				success=false;
			}
		});
		System.out.println(success);
		return success;
	}
	private static void Attack(Pair<Integer, Integer> new_heropos2) {
		// TODO Auto-generated method stub
		System.out.println("Attacking the ENEMY !!");
		Global_Generator.enemyposwithID.forEach(item->{
			if(item.getY().getX()==new_heropos2.getX() && item.getY().getY()==new_heropos2.getY()) {
				Global_Generator.enemies.get(item.getX()).GetHit(Hero.ATK);
			}
		});
	}
	

}
