package controller.enemies;

import controller.globalGenerator.Global_Generator;
import controller.player.PlayerAttack;
import controller.player.PlayerAttackImpl;
import model.enemies.Enemy;
import model.player.Pair;
import model.player.PlayerImpl;

public class Enemy_move_control {

	// MY PS
	// se voglio rivedere i vecchi apppunti di altre funzioni su questo codice vedi le altre versioni salvate nella cartella prima 
	
	// MY TO DO 
	// controllo di altri nemici nella stessa posizione, (fatto)
	// controllo con ostacoli (fatto)
	// cambiare il termine di system exit 0 (fatto)
	// aggiungere il modo per infliggere danno (fatto)
	// controllare il movmiento in diagonale con 2 nemici che si sovrappongono e anche col giocatore ( fatto )
	
	private static int dx;
	private static int dy;
	
	static Pair<Integer,Integer> newenemyPos;
	private static int enemyID;
	private static int act_pt;
	private static boolean collide;
	private static boolean moved_diagonally=false;
	private static boolean wait= false;
	private static boolean previously_acted=false;


	private static Global_Generator gg = Global_Generator.getInstance();
	private static PlayerImpl player = gg.player;
	private static PlayerAttack playerAtt = gg.playerAttack;
	
	public static void nextMove(Pair<Integer, Pair<Integer, Integer>> elem, int actionpt) {

		act_pt=actionpt;
		// calcolo della differenza tra X e Y di nemico attuale ed eroe
		diffX(elem.getY().getX());
		diffY(elem.getY().getY());
		//System.out.println("diffx = "+dx+" diffy= "+dy);
		
		// ID del nemico attuale 
		enemyID = elem.getX();
		
		// non mi ricordo se serve ma lo lascio qui per ora 
		var item = new Pair<>(elem.getX(),elem.getY());
		//----------------//
		
		if(wait) {
			//System.out.println("sono dentro wait");
			wait=false;
			moved_diagonally=false;
			previously_acted=false;
			gg.enemyposwithID.set(elem.getX(), new Pair<>(elem.getX(),elem.getY()));
		}else {
			if(dx<dy) {
				move_orizz(elem.getY());
				gg.enemyposwithID.set(elem.getX(), new Pair<>(elem.getX(),newenemyPos));
				previously_acted=true;
			}else {
				move_vert(elem.getY());	
				gg.enemyposwithID.set(elem.getX(), new Pair<>(elem.getX(),newenemyPos));
				previously_acted=true;
			}
		}

		System.out.println("enemy: ID["+enemyID+"], Pos["+newenemyPos.getX()+", "+newenemyPos.getY()+"]");
	}

	
	private static void attckHero() {
		var correct_enemy=gg.enemies.get(enemyID);
		System.out.println("Enemy " + enemyID + " attacking hero - damage on hero of "+correct_enemy.GetATK());
		//System.out.println("THAT'S A LOT OF DAMAGE !!");
		playerAtt.getHit(enemyID,correct_enemy.GetATK());   //changed atk con getATK()		
	}
	
	// movimento in verticale la X rimane la stessa ma la Y cambia 
	private static void move_orizz(Pair<Integer, Integer> elem) {
		
		var old_pos=elem;
		int y=elem.getY();
		if(elem.getY()<player.getPlayerPosition().getX()) {
			y++;
		}else {
			y--;
		}
		newenemyPos = new Pair<>(elem.getX(),y);
		
		checkwait(old_pos,newenemyPos,false);
	}
	
	//				  _______ X ______			
	// 1   2         |				  |
	//   H 			Y|				  |  schema della griglia
	// 3   4		 |________________|
	
	
	// new version // but wit some problems 
	
		@SuppressWarnings("unlikely-arg-type")
		private static void checkwait(Pair<Integer, Integer> old_pos, Pair<Integer, Integer> newPos, boolean horizontal_movement) {
			
			if (checkenemypos(newPos)) {
				System.out.println("NEMICO rimango fermo");
				newenemyPos = new Pair<>(old_pos.getX(),old_pos.getY());
			} else if(newPos.equals(player.getPlayerPosition())) {
				attckHero();
				newenemyPos = new Pair<>(old_pos.getX(),old_pos.getY());
			} else if (gg.obstacles.stream().anyMatch(o -> o.getObstaclePos().equals(newPos))){
				
				if(!moved_diagonally && act_pt==0) {
					    diagonalmovement(old_pos,newPos,horizontal_movement);
					    
					    // controllo finale per vedere se l'avanzamento in diagonale non coincide con giocatore e nemico 
					    gg.enemyposwithID.forEach(elem->{
					    	if( elem.getY().equals(newenemyPos) || newenemyPos.equals(player.getPlayerPosition())) {
					    		newenemyPos=old_pos;
					    	}
					    });
					    
				}else {
						System.out.println("sono dentro la fine di check wait e setto wait a true");
						//wait=true;
						newenemyPos = new Pair<>(old_pos.getX(),old_pos.getY());
				}
			}
			
		}
	
	@SuppressWarnings("unlikely-arg-type")
	private static void diagonalmovement(Pair<Integer, Integer> old_pos, Pair<Integer, Integer> newPos, boolean horizontal_movement) {
	
		Pair<Integer,Integer> p1 = new Pair<Integer,Integer>(old_pos.getX()-1,old_pos.getY()-1);
		Pair<Integer,Integer> p2 = new Pair<Integer,Integer>(old_pos.getX()+1,old_pos.getY()-1);
		Pair<Integer,Integer> p3 = new Pair<Integer,Integer>(old_pos.getX()-1,old_pos.getY()+1);
		Pair<Integer,Integer> p4 = new Pair<Integer,Integer>(old_pos.getX()+1,old_pos.getY()+1);
		
		if(horizontal_movement ) {
			
			System.out.println("Ho incontrato un ostacolo");	
			
			// movimento in diagonale andando a dx o sx
			
				if(old_pos.getX()>newPos.getX()) {
					//caso 1 e 3
					System.out.println("caso 1 e 3 orizzontale ");
					if(upfree(new Pair<Integer,Integer>(old_pos.getX(),old_pos.getY()-1)) 
							&&  !gg.obstacles
							.stream()
							.anyMatch(o -> o.getObstaclePos().equals(p1))){ //1
						System.out.println("caso 1");
						newenemyPos = new Pair<>(old_pos.getX()-1,old_pos.getY()-1);
						moved_diagonally=true;
						wait=true;
						//stop=true;
						
					} else if(downfree(new Pair<Integer,Integer>(old_pos.getX(),old_pos.getY()+1))  
							&& !gg.obstacles
							.stream()
							.anyMatch(o -> o.getObstaclePos().equals(p3))){ //3
						System.out.println("caso 3");
						newenemyPos = new Pair<>(old_pos.getX()-1,old_pos.getY()+1);
						moved_diagonally=true;
						wait=true;
						//stop=true;
						
					} else {
						System.out.println("è rimasto fermo per davvero");
						newenemyPos = new Pair<>(old_pos.getX(),old_pos.getY());
					}
				} else {
					// caso 2 e 4
					System.out.println("caso 2 e 4 orizzontale ");
					if(upfree(new Pair<Integer,Integer>(old_pos.getX(),old_pos.getY()-1))  
							&& !gg.obstacles
							.stream()
							.anyMatch(o -> o.getObstaclePos().equals(p2))){ //2
						System.out.println("caso 2");
						newenemyPos = new Pair<>(old_pos.getX()+1,old_pos.getY()-1);
						moved_diagonally=true;
						wait=true;
						//stop=true;
						
					} else if(downfree(new Pair<Integer,Integer>(old_pos.getX(),old_pos.getY()+1)) 
							&& !gg.obstacles
							.stream()
							.anyMatch(o -> o.getObstaclePos().equals(p4))){ //4
						System.out.println("caso 4");
						newenemyPos = new Pair<>(old_pos.getX()+1,old_pos.getY()+1);
						moved_diagonally=true;
						wait=true;
						//stop=true;
						
					} else {
						System.out.println("è rimasto fermo per davvero");
						newenemyPos = new Pair<>(old_pos.getX(),old_pos.getY());
					}
				}
			} else {
			// movimento in diagonale andando sù o giù
			if(old_pos.getY()>newPos.getY()) {
						//caso 1 e 2
						System.out.println("caso 1 e 2 verticale ");
						if(leftfree(new Pair<Integer,Integer>(old_pos.getX()-1,old_pos.getY()))  && !gg.obstacles.contains(p1)){ //1
							System.out.println("caso 1");
							newenemyPos = new Pair<>(old_pos.getX()-1,old_pos.getY()-1);
							moved_diagonally=true;
							wait=true;
							//stop=true;
						} else if(rightfree(new Pair<Integer,Integer>(old_pos.getX()+1,old_pos.getY()))  && !gg.obstacles.contains(p2)){ //2
							System.out.println("caso 2");
							newenemyPos = new Pair<>(old_pos.getX()+1,old_pos.getY()-1);
							moved_diagonally=true;
							wait=true;
							//stop=true;
						} else {
							System.out.println("è rimasto fermo per davvero");
							newenemyPos = new Pair<>(old_pos.getX(),old_pos.getY());
						}
					} else {
						// caso 3 e 4
						System.out.println("caso 3 e 4 verticale ");
						if(leftfree(new Pair<Integer,Integer>(old_pos.getX()-1,old_pos.getY()))  && !gg.obstacles.contains(p3)){ //3
							System.out.println("caso 3");
							newenemyPos = new Pair<>(old_pos.getX()-1,old_pos.getY()+1);
							moved_diagonally=true;
							wait=true;
							//stop=true;
						} else if(rightfree(new Pair<Integer,Integer>(old_pos.getX()+1,old_pos.getY()))  && !gg.obstacles.contains(p4)){ //4
							System.out.println("caso 4");
							newenemyPos = new Pair<>(old_pos.getX()+1,old_pos.getY()+1);
							moved_diagonally=true;
							wait=true;
							//stop=true;
						} else {
							System.out.println("è rimasto fermo per davvero");
							newenemyPos = new Pair<>(old_pos.getX(),old_pos.getY());
						}
					}
				}
		
	}
	
	
	
	private static boolean upfree(Pair<Integer,Integer> up) {
		// TODO Auto-generated method stub
		if(!checkenemypos(up) && !gg.obstacles.contains(up)) {
			return true;
		} else {
			return false;
		}
	}
	
	@SuppressWarnings("unlikely-arg-type")
	private static boolean downfree(Pair<Integer,Integer> down) {
		// TODO Auto-generated method stub
		if(!checkenemypos(down) && !gg.obstacles.contains(down)) {
			return true;
		} else {
			return false;
		}
	}
	
	@SuppressWarnings("unlikely-arg-type")
	private static boolean leftfree(Pair<Integer,Integer> left) {
		// TODO Auto-generated method stub
		if(!checkenemypos(left) && !gg.obstacles.contains(left)) {
			return true;
		} else {
			return false;
		}
	}
	
	@SuppressWarnings("unlikely-arg-type")
	private static boolean rightfree(Pair<Integer,Integer> right) {
		// TODO Auto-generated method stub
		if(!checkenemypos(right) && !gg.obstacles.contains(right)) {
			return true;
		} else {
			return false;
		}
	}
	


	// è inefficiente lo so ma almeno controlla se tra tutte le posizioni dei nemici c'è già qualcuno 
	private static boolean checkenemypos(Pair<Integer, Integer> newPos) {
		// TODO Auto-generated method stub
		collide = false;
		gg.enemyposwithID.forEach(item->{
			if(item.getY().getX()==newPos.getX() && item.getY().getY()==newPos.getY()) {
				collide=true;
			}
		});
		return collide;
	}

	// movimento in orizzont la Y rimane la stessa ma la X cambia 
	private static void move_vert(Pair<Integer, Integer> elem) {
		var old_pos=elem;
		int x=elem.getX();
		if(elem.getX()<player.getPlayerPosition().getX()) {
			x++;
		}else {
			x--;
		}
		newenemyPos = new Pair<>(x,elem.getY());
		
		checkwait(old_pos,newenemyPos,true);	
	}

	// calcolo delle differenze !! 
	
	private static void diffX(Integer enX) {
		int diff = Math.abs(enX-player.getPlayerPosition().getX());
		setdx(diff);
	}

	private static void setdx(int diff) {
		dx=diff;
	}

	private static void diffY(Integer enY) {
		int diff = Math.abs(enY-player.getPlayerPosition().getY());
		setdy(diff);
	}
	
	private static void setdy(int diff) {
		dy=diff;
	}

}
