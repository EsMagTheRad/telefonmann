package engines;

import java.awt.Graphics;
import java.util.LinkedList;
import Interfacelike.GameObject;
import spielobjekte.Player;

public class Game_Object_List {
	/**
	 * Each Object in game will be saved in a LikedList.
	 * Every single time the scene is drawn, the whole object list
	 * gets iterated to check the state of every single object
	 * to draw them properly. 
	 */
	public LinkedList<GameObject> objectList = new LinkedList<GameObject>();
	private GameObject currentObject;
	
	public void tick(){
		for (int i = 0; i < objectList.size(); i++){
			currentObject = objectList.get(i);
			currentObject.refresh(objectList);
		}
	}
	/**
	 * Checks the whole object list to determine how each object should be displayed in the frame 
	 * */
	public void render(Graphics g){
		for(int i = 0; i < objectList.size(); i++){
			currentObject = objectList.get(i);
			currentObject.paint(g);
		}
	}
	//Used to add new objects to the scene
	public void addObject(GameObject object){
		objectList.add(object);
	}
	//Enemies destroyed and objects consumed (like buffs or coins) will be removed from the scene
	public void rmObject(GameObject object){ //"rm"... to much shell
		objectList.remove(object);
	}
	public Player fetchPlayer(){
		Player player = null;
		for(int i = 0; i < objectList.size(); i++) {
			currentObject = objectList.get(i);
			if (currentObject.getId() == 1){
				player = (Player) currentObject;
				
			}
		}
		return player;
	}
}
