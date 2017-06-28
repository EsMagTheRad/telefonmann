package spielobjekte;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.Date;
import java.util.LinkedList;
import java.util.Random;

import Interfacelike.GameObject;
import engines.Game_Object_List;
import engines.Imageloader;

public class Enemy extends GameObject{

	private float width, height; //may need: width_jump, height_jump, width_crouch, height_crouch etc
	private float gravity = 0.4f;	
	private float max_speed =3f;
	private int life, lifebar = 50, bullets = 1;
	private boolean hitL = true;
	private Game_Object_List objectlist, attacklist;
	private BufferedImage imageLeft, imageRight;
	private Imageloader loader = new Imageloader();
	float hitX=0, hitY=0;

	public Enemy(float x_pos, float y_pos, Game_Object_List objectlist, int id, int lifeV) {
		super(x_pos, y_pos, 3);
		this.objectlist = objectlist;
		attacklist = new Game_Object_List();
		life = lifeV;
		imageRight = loader.loadImage("/enemy.gif");
		imageLeft = loader.loadImage("/enemy_1.png");
		width = imageRight.getWidth();
		height = imageRight.getWidth();
		/** In case other images for player-actions will be used in future versions: 
		 * may need: width_jump, height_jump, width_crouch, height_crouch etc
		 */
	}
	
	
	/**
	 * Updates position and status of the player object
	 * after each frame drawn
	 * 
	 */
	
	
	public void loseLife(){
		 life--;
		 lifebar = lifebar -25;
		 if (life <= 0){
			 destroyMe();
		 }
		 
	 }
	public void destroyMe()
	{

		this.objectlist.rmObject(this);
		imageRight = null;
		imageLeft= null;
		
	}
	private void killPlayer(GameObject currentObject)
	{
		currentObject.setY_pos(currentObject.getY_pos()-10);
		currentObject.setLife(currentObject.getLife()-1);
		hitX = currentObject.getX_pos()-20;
		hitY = currentObject.getY_pos()+20;	
	}
	
	public void move()
	{
		if(hitL)
		{
			setVel_X(+1.5f);
			setLookingLeft(false);
		}
		else
		{
			setVel_X(-1.5f);
			setLookingLeft(true);
		}
	}
	public void refresh(LinkedList<GameObject> objectList){
	//Unsere Gegnerbewegung
		move();
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
						
						if(currentObject.getId() == 2){
							if(currentObject.getBounds().intersects(getBoundsBottom())){
								int rd = (int) (Math.random() * (9 - 6) + 6);
								setVel_Y(-rd);
							}
						}
						if(currentObject.getId() == 10){
							if(currentObject.getBounds().intersects(getBoundsTop())){
								setVel_Y(-8);
							}
						}

						if(currentObject.getId() == 2 || currentObject.getId() == 10 || currentObject.getId() == 4){	
							if(currentObject.getBounds().intersects(getBoundsRight())){
								hitL = false;
							}
						}
						if(currentObject.getId() == 2 ||currentObject.getId() == 10 ||currentObject.getId() == 4){
							if(currentObject.getBounds().intersects(getBoundsLeft())){
								hitL = true;
							}
						}
						//Collision Player
						
						if(currentObject.getId() == 1){
							if(currentObject.getBoundsBottom().intersects(getBoundsTop())){
								loseLife();
								vel_y = 10;							}
						}
						if(currentObject.getId() == 1){	
							if(currentObject.getBoundsLeft().intersects(getBoundsRight())){
								currentObject.setX_pos(currentObject.getX_pos()+10);
								killPlayer(currentObject);
								hitL = false;
							}
						}
						if(currentObject.getId() == 1){	
							if(currentObject.getBoundsLeft().intersects(getBoundsBottom())){
								currentObject.setX_pos(this.getX_pos());
								currentObject.setFalling(true);
								killPlayer(currentObject);
							}
						}
						if(currentObject.getId() == 1){
							if(currentObject.getBoundsRight().intersects(getBoundsRight())){
								currentObject.setX_pos(currentObject.getX_pos()+10);
								killPlayer(currentObject);
								hitL = true;
								}
						}
						if(currentObject.getId() == 1){	
							if(currentObject.getBoundsLeft().intersects(getBoundsLeft())){
								currentObject.setX_pos(currentObject.getX_pos()-10);
								killPlayer(currentObject);
								hitL = false;
							}
						}
						if(currentObject.getId() == 1){	
							if(currentObject.getBoundsRight().intersects(getBoundsLeft())){
								currentObject.setX_pos(currentObject.getX_pos()-10);
								killPlayer(currentObject);
								hitL = true;
							}
						}
						/**
						if(currentObject.getId() == 1){	
							if(currentObject.getBounds().intersects(LeftTargetRange())||currentObject.getBounds().intersects(RightTargetRange())){
								attack();
							}
						}
						*/
					}
				}
				//From here: collision- handling for default block  	
				

	
		//from here on: collision- handling for enemy type1
		//from here on: collision- handling for enemy type2
		//from here on: collision- handling for buff type1
		//...


	@Override
	public void paint(Graphics g) {
		// TODO Auto-generated method stub
		if(lookingLeft == false){
			g.drawImage(imageRight, (int) x_pos, (int) y_pos, 48, 48, null);
			}
			else if(lookingLeft == true){
				g.drawImage(imageLeft, (int) x_pos, (int) y_pos, 48, 48, null);
			}

	}
	
	

	/**Methods to create the proper collision bounds of the player**/
	public Rectangle getBoundsBottom() {
		return new Rectangle((int) ((int)x_pos+(10)), (int)((int)y_pos+38), (int)width-20, ((int)6));
	}
	public Rectangle getBoundsTop() {
		return new Rectangle((int) ((int)x_pos+(10)), (int)((int)y_pos+5), (int)width-20, (int)height/8);
	}
	public Rectangle getBoundsRight() {
		return new Rectangle((int)((int)x_pos+(width)-14), (int)y_pos+9, (int)5, (int)height-21);
	}
	public Rectangle getBoundsLeft() {
		return new Rectangle((int)x_pos+5, (int)y_pos+9, (int)5, (int)height-21);
	}
/**	
	public Rectangle LeftTargetRange() {
		return new Rectangle((int)x_pos-180, (int)y_pos+9, (int)180, (int)height);
	}
	public Rectangle RightTargetRange() {
		return new Rectangle((int)x_pos+48, (int)y_pos+9, (int)180, (int)height);
	}
*/

	@Override
	public Rectangle getBounds() {
		// TODO Auto-generated method stub
		return new Rectangle((int) ((int)x_pos+(width/2)-((width/2)/2))+6, (int)y_pos+10, (int)width/3, (int)height/2);
	}
/**
	public void attack(){
		 if (bullets <= 0){
				bullets = 0;
			}
		if (bullets > 0){
				attacklist.addObject(new Attack(this.x_pos+width,this.y_pos+height/2,6, -1, 0));
				attacklist.addObject(new Attack(this.x_pos,this.y_pos+height/2, 6, 1, 0));
				attacklist.addObject(new Attack(this.x_pos+width/2,this.y_pos/2,6, 0, 1));
				attacklist.addObject(new Attack(this.x_pos+width/2,this.y_pos, 6, 0, -1));
			bullets+=-1;
			}
		}
*/
	}
