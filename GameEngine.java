import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.geom.Rectangle2D;
import javax.swing.Timer;
import java.util.ArrayList;
import java.util.Iterator;

public class GameEngine implements KeyListener, GameReporter{

 		GamePanel gp;
 		private ArrayList<Enemy> enemies = new ArrayList<Enemy>();
 		private SpaceShip v;	
 		private Timer time;
 		private long score;

 		private double difficult = 0.1;
 		private double easy = difficult;
 
 		public GameEngine(GamePanel gp, SpaceShip v) {
 			this.gp = gp;
 			this.v = v;		
 		
 			gp.sprites.add(v);
 			
 			time = new Timer(50, new ActionListener() {
 			
 				@Override
 				public void actionPerformed(ActionEvent arg0) {
 					process();
 				}
 			});
 			time.setRepeats(true);
 
 		}
 
 		public void start(){
 			time.start();
 		}
 		private void generateEnemy(){
 			Enemy e = new Enemy((int)(Math.random()*390), 30);
 			gp.sprites.add(e);
 			enemies.add(e);
 		}
 
 		private void process(){
 			if(Math.random() < difficult) {

 				generateEnemy();
 			}

 			
 
 			Iterator<Enemy> e_iter = enemies.iterator();
 			while(e_iter.hasNext()){
				Enemy e = e_iter.next();
 				e.proceed();
 			
 				if(!e.isAlive()){
 					e_iter.remove();
 					gp.sprites.remove(e);
 					score += 100;
 				}
 			}
 			gp.updateGameUI(this);

 			Rectangle2D.Double vr = v.getRectangle();
 			Rectangle2D.Double er;
 			for(Enemy e : enemies){
 				er = e.getRectangle();
 				if(er.intersects(vr)){
 					die();
 					return;
 				}
 			}
 		}
 		public void die(){
 			time.stop();
 		}
 
 		void controlVehicle(KeyEvent e) {
 			switch (e.getKeyCode()) {
 			case KeyEvent.VK_LEFT:
 				v.move(-1);
 				break;
 			case KeyEvent.VK_RIGHT:
 				v.move(1);
 				break;
 			case KeyEvent.VK_D:
 					difficult += 0.1;
 					break;
 				case KeyEvent.VK_F:
 					easy -= 0.1;
 					break;
 			}
 		}
 	
 	public long getScore(){
 		return score;
 	}

 	@Override
 	public void keyPressed(KeyEvent e) {
 		controlVehicle(e);
 		
 	}
 
 	@Override
 	public void keyReleased(KeyEvent e) {
 		//do nothing
 	}
 
 	@Override
 	public void keyTyped(KeyEvent e) {
 		//do nothing		
 	}
 	
 } 