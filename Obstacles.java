import java.util.Random;

public class Obstacles {

	Random r=new Random();
	int x;
	int y;
	boolean success=false;
	
	public Obstacles(int sizeArena){
		success=false;
		while(!success) {
			generate_obst(sizeArena);
		}

	}

	private void generate_obst(int sizeArena) {
		// TODO Auto-generated method stub
		x=r.nextInt(sizeArena);
		y=r.nextInt(sizeArena);
		Pair<Integer,Integer> obst_pos = new Pair<>(x,y);
		if(!Global_Generator.obstacles_pos.contains(obst_pos) && ( obst_pos.getX()!=Hero.getX() && obst_pos.getY()!=Hero.getY()) ) {
			Global_Generator.obstacles_pos.add(obst_pos);
			success=true;
			System.out.println("generated obstacle in pos X = "+obst_pos.getX()+" Y = "+obst_pos.getY());
		}
	}

	


}
