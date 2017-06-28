package engines;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import Interfacelike.GameObject;
import spielobjekte.Player;



public class Keyreader extends KeyAdapter{
	private Game_Object_List objectlist;
	
	public Keyreader (Game_Object_List objectlist){
		this.objectlist = objectlist;
	}
	

	public void keyPressed(KeyEvent e){
		int key = e.getKeyCode();
		Player player = objectlist.fetchPlayer();
		
				if(key == KeyEvent.VK_D){
					player.setVel_X(5);
					player.setLookingLeft(false);
				}
				if(key == KeyEvent.VK_A){
					player.setVel_X(-5);
					player.setLookingLeft(true);
				}	
				if(key == KeyEvent.VK_SPACE && player.isJumping() == false ){
					player.setJumping(true);
					player.setVel_Y(-12);
				}
				if(key == KeyEvent.VK_S){
					player.setVel_Y(0);	
				}
				if(key == KeyEvent.VK_W )player.setVel_Y(-5);{
					player.setFalling(true);
				}
				if(key == KeyEvent.VK_Y && player.getLife()<=0){
					player.setRestart(true);
				}
				if(key == KeyEvent.VK_ESCAPE){
					player.setRestart(true);
				}
				if(key == KeyEvent.VK_ENTER ){
					player.attack();
				}
			}
		
	

public void keyReleased(KeyEvent e){
			int key = e.getKeyCode();
			GameObject player = objectlist.fetchPlayer();
									
				if(key == KeyEvent.VK_D || key == KeyEvent.VK_RIGHT)player.setVel_X(0);
				if(key == KeyEvent.VK_A || key == KeyEvent.VK_LEFT)player.setVel_X(0);	
				if(key == KeyEvent.VK_W || key == KeyEvent.VK_UP)player.setVel_Y(0);					
				if(key == KeyEvent.VK_S || key == KeyEvent.VK_DOWN)player.setVel_Y(0);	
					
		}
	}

