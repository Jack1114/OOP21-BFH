/**
 * 
 */
package controller.entities;

/**
 * @author Olivia
 *
 */
public class PlayerMouvementsControllerImpl implements PlayerMouvementsController {

	private static boolean success;
	static Pair<Integer,Integer> new_player_pos;
	
	public PlayerMouvementsControllerImpl() {
	
	}

	public void left(Pair<Integer, Integer> player_pos) {
		new_player_pos=new Pair<>(player_pos.getX()-1,player_pos.getY());
		if(check_advancement(new_player_pos)) {
			player_pos=new_player_pos;
			PlayerControllerImpl playerControllerImpl = new PlayerControllerImpl();
			playerControllerImpl.setX(player_pos.getX());
			
		}

	}
	
	/*final JButton left= new JButton("left");
	 left.addActionListener(e -> {
	 public void left(Pair<Integer, Integer> player_pos);
	}
	*/

	
	public void right(Pair<Integer, Integer> player_pos) {
		new_player_pos=new Pair<>(player_pos.getX()+1,player_pos.getY());
		if(check_advancement(new_player_pos)) {
			player_pos=new_player_pos;
			PlayerControllerImpl playerControllerImpl = new PlayerControllerImpl();
			playerControllerImpl.setX(player_pos.getX());
			
		}
		
	}
	/*final JButton right= new JButton("left");
	 right.addActionListener(e -> {
	 public void right(Pair<Integer, Integer> player_pos);
	}
	*/
	
	public void down(Pair<Integer, Integer> player_pos) {
		new_player_pos=new Pair<>(player_pos.getX(),player_pos.getY()-1);
		if(check_advancement(new_player_pos)) {
			player_pos=new_player_pos;
			PlayerControllerImpl playerControllerImpl = new PlayerControllerImpl();
			playerControllerImpl.setY(player_pos.getY());
			
		}
		
	}
	/*final JButton down= new JButton("left");
	 down.addActionListener(e -> {
	 public void down(Pair<Integer, Integer> player_pos);
	}
	*/
	
	public void up(Pair<Integer, Integer> player_pos) {
		new_player_pos=new Pair<>(player_pos.getX(),player_pos.getY()+1);
		if(check_advancement(new_player_pos)) {
			player_pos=new_player_pos;
			PlayerControllerImpl playerControllerImpl = new PlayerControllerImpl();
			playerControllerImpl.setY(player_pos.getY());
			
		}
		
	}
	/*final JButton up= new JButton("left");
	 uo.addActionListener(e -> {
	 public void up(Pair<Integer, Integer> player_pos);
	}
	*/
	
	public boolean check_advancement(Pair<Integer, Integer> player_pos) {
		success=true;
		/*if(Global_Generator.obstacles_pos.contains(player_pos)) // verifico se tra la lista degli ostacoli, c'è un ostacolo dove il player vuole spostarsi
			succes=false ;
		  if() //per verificare se il palyer va fuori dal'atrena di gioco
		  succes =false;
		  Global_Generator.enemyposwithID.forEach(item->{
			  if(item.getX==player_pos.getX && item.getY==player_pos.getY)
		  	{
		  PlayerAttackControllerImpl playerAttackControllerImpl =new PlayerAttackControllerImpl();
		  playerAttackControllerImpl.attack(palyer_pos)
		  success=false;
		  	}
		  })
		  */
		
		return success;
	}

}
