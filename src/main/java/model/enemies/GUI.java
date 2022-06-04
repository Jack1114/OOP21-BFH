package model.enemies;
import javax.swing.*;

import controller.globalGenerator.Global_Generator;
import controller.obstacles.Obstacle;
import model.player.Pair;
import model.player.Player;
import model.player.PlayerImpl;

import java.util.*;
import java.util.List;
import java.awt.*;
import java.awt.event.ActionListener;


public class GUI extends JFrame {
	
	Map<JButton,Pair<Integer,Integer>> mapjbtopos = new HashMap<>();
	Map<Pair<Integer,Integer>,JButton> mappostojb = new HashMap<>();
	Player player;
	Global_Generator gg = Global_Generator.getInstance();
	List<Pair<Integer, Pair<Integer, Integer>>> En_With_ID;
	List<Obstacle> obstacles;

	JButton score=new JButton();
	JButton gold=new JButton();
	JButton HP=new JButton();
	JButton ATK=new JButton();
	JButton LV=new JButton();

	
	private static final long serialVersionUID = -6218820567019985015L;
    //private final List<JButton> cells = new ArrayList<>();
    
    public GUI(int size) {
    	this.player = gg.player;
    	player.toString();	
    	int HeroX = player.getPlayerPosition().getX();
    	int HeroY = player.getPlayerPosition().getY();
    	score.setText("Experience = "+player.getExperience().getExpPoints());
    	gold.setText("Gold = "+player.getGold().getGold_points() );
    	HP.setText("Life = "+player.getLife().getLifePoints() +" / "+player.getLife().getMaxLifePoints() );
    	ATK.setText("Attack Points = "+Integer.toString(player.getAttackPoints()));
    	LV.setText("Level = "+player.getExperience().getLevel() );

        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(50*size, 50*size);
        
        JPanel panel = new JPanel(new GridLayout(size,size));
        //this.getContentPane().add(panel);
        
		this.getContentPane().add(panel, BorderLayout.CENTER);
		
		JPanel Hero_Stats = new JPanel(new GridLayout(5,1));
		
		Hero_Stats.add(HP);
		Hero_Stats.add(ATK);
		Hero_Stats.add(LV);
		Hero_Stats.add(gold);
		Hero_Stats.add(score);
		
		
		this.getContentPane().add(Hero_Stats, BorderLayout.NORTH);
        
        
       //this.getContentPane().add
        
        ActionListener al = e -> {
        	//this.cells.get(counter).setText(String.valueOf(counter++));
        };
                
        for (int i=0; i<size; i++){
            for (int j=0; j<size; j++){
            	var pos = new Pair<>(j,i);
                final JButton jb = new JButton(" ");
                mapjbtopos.put(jb, pos);
                mappostojb.put(pos,jb);
                //this.cells.add(jb);
                jb.addActionListener(al);
                panel.add(jb);
            }
        }
        
        update();
        
        this.setVisible(true);
    }
    
    
	public void update() {
		//System.out.println("entro dentro update");

		updateHeroStats();
				
		int HeroX = player.getPlayerPosition().getX();
		int HeroY = player.getPlayerPosition().getY();
		int ID=0;
		En_With_ID = gg.getInstance().enemyposwithID;
		obstacles = gg.getInstance().obstacles;	
		mappostojb.forEach((pos,jb)->{
	
				jb.setText("X"+pos.getX()+"Y"+pos.getY());
				// calcolo rapido per avere l' ID solo per visualizzarlo a schermo 
				En_With_ID.forEach(pair->{
					//System.out.println("ho trovato un nemico da aggiungere");
					if(pair.getY().getX()==pos.getX() && pair.getY().getY()==pos.getY()){
						//System.out.println("setto il contenuto del pulsante");
						jb.setText(""+pair.getX());
					}
				});
				
				//non ho capito cosa fa qui
				obstacles.forEach(obst->{
					if(obst.getObstaclePos().getX()==pos.getX() && obst.getObstaclePos().getY()==pos.getY()){
						if(obst.getObstacleType().equals(Obstacle.Type.POOL)) {
							jb.setText("O");

						}
						if(obst.getObstacleType().equals(Obstacle.Type.ROCK)) {
							jb.setText("R");

						}
					}
				});
				
				if(HeroX==pos.getX() && HeroY==pos.getY()){
					jb.setText("H");
				}
				
				
				
				
		});
	}


	private void updateHeroStats() {
    	score.setText("Experience = " + player.getExperience().getExpPoints());
    	gold.setText("Gold = " + player.getGold().getGold_points() );
    	HP.setText("Life = " + player.getLife().getLifePoints() +" / "+player.getLife().getMaxLifePoints() );
    	ATK.setText("Attack Points = " + Integer.toString(player.getAttackPoints()));
    	LV.setText("Level = " + player.getExperience().getLevel() );
	}
	
}