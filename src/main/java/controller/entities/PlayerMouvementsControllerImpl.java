/**
 * 
 */
package controller.entities;

/**
 * @author Olivia
 *
 */
public class PlayerMouvementsControllerImpl implements PlayerMouvementsController {

	private boolean success;
	private Pair<Integer,Integer> new_player_pos;
	PlayerControllerImpl player;
	
	public PlayerMouvementsControllerImpl(PlayerControllerImpl newPlayer) {
	this.player=newPlayer;
	}

	public void left() {
		new_player_pos=new Pair<>(player.getPlayerPosition().getX()-1,player.getPlayerPosition().getY());
		if(check_advancement(new_player_pos))
		player.setPlayerPosition(new_player_pos);

	}

	/*final JButton left= new JButton("left");
	 left.addActionListener(e -> {
	 public void left(Pair<Integer, Integer> player_pos);
	}
	*/

	
	public void right() {
		new_player_pos=new Pair<>(player.getPlayerPosition().getX()+1,player.getPlayerPosition().getY());
		if(check_advancement(new_player_pos)) {
			player.setPlayerPosition(new_player_pos);
		}
		
	}
	/*final JButton right= new JButton("right");
	 right.addActionListener(e -> {
	 public void right(Pair<Integer, Integer> player_pos);
	}
	*/
	
	public void down() {
		new_player_pos=new Pair<>(player.getPlayerPosition().getX(),player.getPlayerPosition().getY()-1);
		if(check_advancement(new_player_pos)) {
			player.setPlayerPosition(new_player_pos);
		}
		
	}
	/*final JButton down= new JButton("down");
	 down.addActionListener(e -> {
	 public void down(Pair<Integer, Integer> player_pos);
	}
	*/
	
	public void up() {
		new_player_pos=new Pair<>(player.getPlayerPosition().getX(),player.getPlayerPosition().getY()+1);
		if(check_advancement(new_player_pos)) {
			player.setPlayerPosition(new_player_pos);
		}
		
	}
	/*final JButton up= new JButton("left");
	 uo.addActionListener(e -> {
	 public void up(Pair<Integer, Integer> player_pos);
	}
	*/
	
	public boolean check_advancement(Pair<Integer, Integer> new_player_pos) {
		success=true;
		/*if(Global_Generator.obstacles_pos.contains(new_player_pos)) // verifico se tra la lista degli ostacoli, c'è un ostacolo dove il player vuole spostarsi
			succes=false ;
		  if() //per verificare se il palyer va fuori dal'atrena di gioco
		  succes =false;
		  Global_Generator.enemyposwithID.forEach(item->{
			  if(item.getX==new_player_pos.getX && item.getY==new_player_pos.getY)
		  	{
		  PlayerAttackControllerImpl playerAttackControllerImpl =new PlayerAttackControllerImpl();
		  playerAttackControllerImpl.attack(new_player_pos)
		  success=false;
		  	}
		  })
		  */
		
		return success;
	}


}
