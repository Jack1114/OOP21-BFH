package battleforhonor;
import javax.swing.*;

import controller.entities.Pair;

import java.util.*;
import java.util.List;
import java.awt.*;
import java.awt.event.ActionListener;


public class GUI extends JFrame {
	
	Map<JButton,Pair<Integer,Integer>> mapjbtopos = new HashMap<>();
	Map<Pair<Integer,Integer>,JButton> mappostojb = new HashMap<>();

	
	List<Pair<Integer, Pair<Integer, Integer>>> En_With_ID;
	List<Pair<Integer, Integer>> Obs_Pos;
	int HeroX = Hero.getX();
	int HeroY = Hero.getY();
	
	JButton score=new JButton("EXP = "+Hero.getCurrentEXP());
	JButton gold=new JButton("Gold = "+Hero.GetGold());
	JButton HP=new JButton("HP = "+Hero.GetHP()+" / "+Hero.GetMaxHP());
	JButton ATK=new JButton("ATK = "+Hero.GetATK());
	JButton LV=new JButton("LV = "+Hero.GetLV());

	
	private static final long serialVersionUID = -6218820567019985015L;
    //private final List<JButton> cells = new ArrayList<>();
    
    public GUI(int size) {

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
		
		//score.setText("EXP = "+Hero.getCurrentEXP());
		
		int HeroX = Hero.getX();
		int HeroY = Hero.getY();
		int ID=0;
		En_With_ID = Global_Generator.enemyposwithID;
		Obs_Pos = Global_Generator.obstacles_pos;	
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
				
				Obs_Pos.forEach(obst->{
					if(obst.getX()==pos.getX() && obst.getY()==pos.getY()){
						jb.setText("X");
					}
				});
				
				if(HeroX==pos.getX() && HeroY==pos.getY()){
					jb.setText("H");
				}
				
				
				
				
		});
	}


	private void updateHeroStats() {
		// TODO Auto-generated method stub
		score.setText("EXP = "+Hero.getCurrentEXP());
		gold.setText("Gold = "+Hero.GetGold());
		HP.setText("HP = "+Hero.GetHP()+" / "+Hero.GetMaxHP());
		ATK.setText("ATK = "+Hero.GetATK());
		LV.setText("LV = "+Hero.GetLV());
	}
	
}