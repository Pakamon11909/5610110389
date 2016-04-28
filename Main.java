import javax.swing.*;
import java.awt.Color;
import java.awt.*;
import java.awt.event.*;
import javax.swing.JFrame;
import java.util.ArrayList;
  
  public class Main {
 
  	public static void main(String[] args){
  		JFrame frame = new JFrame("Space War");
  		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
  		frame.setSize(400, 650);
  		frame.getContentPane().setLayout(new BorderLayout());

 		SpaceShip v = new SpaceShip(180, 550, 20, 20);
 		GamePanel gp = new GamePanel();
 		gp.sprites.add(v);
  		gp.updateGameUI();

 		
 		GameEngine engine = new GameEngine(gp, v);
 		frame.addKeyListener(engine);
		frame.getContentPane().add(gp, BorderLayout.CENTER);
		frame.setVisible(true);

 
  		engine.start();
 	}
  
 } 
 	
