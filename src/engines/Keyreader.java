package engines;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import Interfacelike.GameObject;



public class Keyreader extends KeyAdapter{
	private Game_Object_List objectlist;
	
	public Keyreader (Game_Object_List objectlist){
		this.objectlist = objectlist;
	}
	
	public void keyPressed(KeyEvent e){
		int key = e.getKeyCode();
		
		//Player- movement
		for(int i = 0; i< objectlist.objectList.size(); i++){
			GameObject tempObject = objectlist.objectList.get(i);
			
			if(tempObject.getId() == 1){
				if(key == KeyEvent.VK_D || key == KeyEvent.VK_RIGHT){
					tempObject.setVel_X(5);
					tempObject.setLookingLeft(false);
				}
				if(key == KeyEvent.VK_A || key == KeyEvent.VK_LEFT){
					tempObject.setVel_X(-5);
					tempObject.setLookingLeft(true);
				}	
				if(key == KeyEvent.VK_SPACE && tempObject.isJumping() == false ){
					tempObject.setJumping(true);
					tempObject.setVel_Y(-12);
				}
				if(key == KeyEvent.VK_S || key == KeyEvent.VK_DOWN)tempObject.setVel_Y(0);	
				if(key == KeyEvent.VK_W || key == KeyEvent.VK_UP)tempObject.setVel_Y(-5); tempObject.setFalling(true);
			}
		}
	}

				public void keyReleased(KeyEvent e){
				int key = e.getKeyCode();
				
				for(int i=0; i< objectlist.objectList.size(); i++){
					GameObject tempObject = objectlist.objectList.get(i);
					if(tempObject.getId() == 1){
						if(key == KeyEvent.VK_D || key == KeyEvent.VK_RIGHT)tempObject.setVel_X(0);
						if(key == KeyEvent.VK_A || key == KeyEvent.VK_LEFT)tempObject.setVel_X(0);	
						if(key == KeyEvent.VK_W || key == KeyEvent.VK_UP)tempObject.setVel_Y(0);
						if(key == KeyEvent.VK_S || key == KeyEvent.VK_DOWN)tempObject.setVel_Y(0);	
					}
		}
	}
}
