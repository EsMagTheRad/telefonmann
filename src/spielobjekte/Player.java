package spielobjekte;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.LinkedList;
import Interfacelike.GameObject;
import engines.Game_Object_List;
import engines.Imageloader;

public class Player extends GameObject{

	private float width, height; //may need: width_jump, height_jump, width_crouch, height_crouch etc
	private float gravity = 0.4f;	
	private float max_speed = 10f;
	Game_Object_List objectlist;
	BufferedImage imageRight;
	BufferedImage imageLeft;
	Imageloader loader = new Imageloader();

	public Player(float x_pos, float y_pos, Game_Object_List objectlist, int id) {
		super(x_pos, y_pos, 1);
		this.objectlist = objectlist;

		imageRight = loader.loadImage("/heroright.png");
		imageLeft = loader.loadImage("/heroleft.png");
		width = imageRight.getWidth();
		height = imageRight.getWidth();
		/** In case other images for player-actions will be used in future versions: 
		 * may need: width_jump, height_jump, width_crouch, height_crouch etc
		 */
	}
	
	
	/**
	 * Updates position and status of the player object
	 * after each frame drawn
	 */
	public void refresh(LinkedList<GameObject> objectList){
		x_pos += vel_x;
		y_pos += vel_y;
		if(falling||jumping ){
			vel_y+=gravity;
		}
		if(vel_y >= max_speed){
			vel_y = max_speed;
		}
		check_collision(objectList);//Checks if player collided with an object
	}
	private void check_collision(LinkedList<GameObject> objectList){
		//Iterates through every object in game to check if player collided with it or not and commits proper reaction
		for (int i=0; i<objectlist.objectList.size(); i++){
			GameObject currentObject = objectlist.objectList.get(i);
		//From here: collision- handling for pushable block  	
			//Right Collision
		if (currentObject.getId()== 10 && getBoundsRight().intersects(currentObject.getBounds())){
			x_pos = currentObject.getX_pos()-64;
			if (currentObject.isMoveable()==true){
				currentObject.setX_pos(currentObject.getX_pos()+1);
				}
			}
		
			//left Collision
		if (currentObject.getId()== 10 && getBoundsLeft().
			intersects(currentObject.getBounds())){x_pos = currentObject.getX_pos()+45;
			if (currentObject.isMoveable()==true){
				currentObject.setX_pos(currentObject.getX_pos()-1);
			}
		}
			//Top Collision
		if (currentObject.getId()== 10 && getBoundsBottom().intersects(currentObject.getBounds())){
				vel_y = 0;
				falling = false;
				jumping = false;
			}
			else{
				falling = true;
			}
		}
		
		for (int i=0; i<objectlist.objectList.size(); i++){
			GameObject currentObject = objectlist.objectList.get(i);
		//From here: collision- handling for default block 
		if (currentObject.getId()== 2 && getBoundsTop().intersects(currentObject.getBounds())){
			y_pos = currentObject.getY_pos() + (height/2)-5;
			vel_y=0;
		}
		if (currentObject.getId()== 2 && getBoundsLeft().intersects(currentObject.getBounds())){
			x_pos = currentObject.getX_pos() +12;
		}
		if (currentObject.getId()== 2 && getBoundsRight().intersects(currentObject.getBounds())){
			x_pos = currentObject.getX_pos()-width +20;
		}
		if (currentObject.getId()== 2 && getBoundsBottom().intersects(currentObject.getBounds())){
			float temppos = getY_pos();
			y_pos = temppos;
			vel_y= 0;
			falling = false;
			jumping = false;
			}else
				falling = true;
			}
		
		}
		//from here on: collision- handling for enemy type1
		//from here on: collision- handling for enemy type2
		//from here on: collision- handling for buff type1
		//...


	@Override
	public void paint(Graphics g) {
		// TODO Auto-generated method stub
		if(lookingLeft == false){
			g.drawImage(imageRight, (int) x_pos, (int) y_pos, imageRight.getHeight(), imageRight.getWidth(), null);
			}
			else if(lookingLeft == true){
				g.drawImage(imageLeft, (int) x_pos, (int) y_pos, imageLeft.getHeight(), imageLeft.getWidth(), null);
			}
		Graphics2D g2d = (Graphics2D) g;
		g.setColor(Color.red);
		g2d.draw(getBoundsBottom());
		g2d.fill(getBoundsRight());
		g2d.fill(getBoundsLeft());
		g2d.draw(getBoundsTop());
	}

	/**Methods to create the proper collision bounds of the player**/
	public Rectangle getBoundsBottom() {
		return new Rectangle((int) ((int)x_pos+(width/2)-((width/2)/2))+6, (int)((int)y_pos+(height/2))-5, (int)width/3, (int)height/2);
	}
	public Rectangle getBoundsTop() {
		return new Rectangle((int) ((int)x_pos+(width/2)-((width/2)/2))+6, (int)y_pos+10, (int)width/3, (int)height/2);
	}
	public Rectangle getBoundsRight() {
		return new Rectangle((int)((int)x_pos+width- 28), (int)y_pos+15, (int)5, (int)height-30);
	}
	public Rectangle getBoundsLeft() {
		return new Rectangle((int)x_pos+20, (int)y_pos+15, (int)5, (int)height-30);
	}

	@Override
	public Rectangle getBounds() {
		// TODO Auto-generated method stub
		return null;
	}


}
