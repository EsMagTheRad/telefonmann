package spielobjekte;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Window;
import java.awt.image.BufferedImage;
import java.util.LinkedList;
import Interfacelike.GameObject;
import engines.Game_Object_List;
import engines.Imageloader;

public class Player extends GameObject{

	private float width, height; //may need: width_jump, height_jump, width_crouch, height_crouch etc
	private float gravity = 0.4f;	
	private float max_speed = 8f;
	private int points = 0, maxPoints = 0, bullets = 10;
	
	private Game_Object_List objectlist;
	private Game_Object_List attacklist = new Game_Object_List();
	private BufferedImage imageRight;
	private BufferedImage imageLeft;
	private BufferedImage grave;
	private BufferedImage slimeball;
	private Imageloader loader = new Imageloader();
	LinkedList<GameObject> ojl;


	public Player(float x_pos, float y_pos, Game_Object_List objectlist, int id) {
		super(x_pos, y_pos, 1);
		this.objectlist = objectlist;

		life = 20;
		imageRight = loader.loadImage("/heroright.png");
		imageLeft = loader.loadImage("/heroleft.png");
		grave = loader.loadImage("/grave.png");
		slimeball = loader.loadImage("/fire.png");
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
		ojl = objectList;
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
		if (currentObject.getId()== 10 && getBoundsRight().intersects(currentObject.getBoundsLeft())){
			x_pos = currentObject.getX_pos()-66;
				currentObject.setX_pos(currentObject.getX_pos()+1);
			}
		
			//left Collision
		if (currentObject.getId()== 10 && getBoundsLeft().
			intersects(currentObject.getBoundsRight())){x_pos = currentObject.getX_pos()+46;
				currentObject.setX_pos(currentObject.getX_pos()-1);
		}
			//Top Collision
		if (currentObject.getId()== 10 && getBoundsBottom().intersects(currentObject.getBoundsTop())){
				setY_pos(currentObject.getY_pos()-imageLeft.getHeight()+12);
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
			float temp = this.getX_pos();
			x_pos= temp;
			y_pos = currentObject.getY_pos() + (height/2)-(10+vel_y);
			vel_y=0;
		}
		if (currentObject.getId()== 2 && getBoundsLeft().intersects(currentObject.getBounds())){
			x_pos = currentObject.getX_pos() +12;
		}
		if (currentObject.getId()== 2 && getBoundsRight().intersects(currentObject.getBounds())){
			x_pos = currentObject.getX_pos()-width +20;
		}
		if (currentObject.getId()== 3 &&  getBoundsBottom().intersects(currentObject.getBoundsTop())){
			jumping = true;
			setVel_Y(-6);
		}
		if (currentObject.getId()== 5 &&  getBounds().intersects(currentObject.getBounds())){
			objectList.remove(currentObject);
			points++;
		}if (currentObject.getId()== 6 &&  getBounds().intersects(currentObject.getBounds())){
			objectList.remove(currentObject);
			setLife(getLife()+5);
			bullets = bullets +5;
		}

		if (currentObject.getId()== 2 && getBoundsBottom().intersects(currentObject.getBounds())){
			setY_pos(currentObject.getY_pos()-imageLeft.getHeight()+12);
			vel_y=  0;
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
		if(lookingLeft == false && life > 0){
			g.drawImage(imageRight, (int) x_pos, (int) y_pos, imageRight.getHeight(), imageRight.getWidth(), null);
			}
			else if(lookingLeft == true && life > 0){
				g.drawImage(imageLeft, (int) x_pos, (int) y_pos, imageLeft.getHeight(), imageLeft.getWidth(), null);
			}else if (lookingLeft == true && life <= 0 ||lookingLeft == false && life <= 0  ){
				g.drawImage(grave, (int) x_pos, (int)y_pos, 64, 64, null);
			}
		if (life > 5){
		g.setColor(Color.green);
		}else{
			g.setColor(Color.red);
		}
		if(life >=0 ){
			g.drawString(("Life: "), (int)this.getX_pos()-360, (int)this.getY_pos()-275);
			g.drawString(("ESC to restart "), (int)this.getX_pos()-360, (int)this.getY_pos()-255);
			g.drawString(("SPACE = Schuss: " +  bullets), (int)this.getX_pos()-80, (int)this.getY_pos()-275);
			g.fillRect((int)this.getX_pos()-300, (int)this.getY_pos()-290, (int) (life*5), 20);
			g.drawString(("Score: " +  points + " / " + maxPoints), (int)this.getX_pos()+300, (int)this.getY_pos()-275);
		}
		if(life <= 0){
			g.drawString(("GAME OVER "), (int)this.getX_pos()-10, (int)this.getY_pos()-20);
			this.vel_y=0;
			this.vel_x=0;
		}
		if(points >= maxPoints){
			g.drawString(("YOU WIN!!!!! "), (int)this.getX_pos()-10, (int)this.getY_pos()-20);
			this.vel_y=0;
			this.vel_x=0;
			//setRestart(true);
		}
		for (int i = 0; i<= attacklist.objectList.size(); i++){
			try{
			Attack atk = (Attack) attacklist.objectList.get(i);
			atk.move(lookingLeft);
			g.drawImage(slimeball, (int) atk.getX_pos(), (int) atk.getY_pos(), 16, 16, null);
			checkAttack(atk);
			}catch (Exception e) {
				// TODO: handle exception
			}

		}
		/**
		Graphics2D g2d = (Graphics2D) g;
		g.setColor(Color.red);
		g2d.fill(getBoundsBottom());
		g2d.fill(getBoundsRight());
		g2d.fill(getBoundsLeft());
		g2d.draw(getBoundsTop());
	**/
	}

	private void checkAttack(Attack atk) {
		for (int i = 0; i <= ojl.size(); i++){
			GameObject currentObject = objectlist.objectList.get(i);
			if (currentObject.getId()== 10 && atk.getBounds().intersects(currentObject.getBounds())
					||currentObject.getId()== 2 && atk.getBounds().intersects(currentObject.getBounds())){
			attacklist.rmObject(atk);
			}
			if (currentObject.getId()== 3 && atk.getBounds().intersects(currentObject.getBounds())){
				attacklist.rmObject(atk);
				Enemy enm = (Enemy) objectlist.objectList.get(i);
				enm.loseLife();
			}
		}
	}
		
	


	/**Methods to create the proper collision bounds of the player**/
	public Rectangle getBoundsBottom() {
		return new Rectangle((int) ((int)x_pos+(width/2)-((width/2)/2))+6, (int)((int)y_pos+height-28), (int)width/3, (int)height/5);
	}
	public Rectangle getBoundsTop() {
		return new Rectangle((int) ((int)x_pos+(width/2)-((width/2)/2))+6, (int)y_pos+10, (int)width/3, (int)height/7);
	}
	public Rectangle getBoundsRight() {
		return new Rectangle((int)((int)x_pos+width- 28), (int)y_pos+20, (int)5, (int)height-40);
	}
	public Rectangle getBoundsLeft() {
		return new Rectangle((int)x_pos+20, (int)y_pos+20, (int)5, (int)height-40);
	}

	@Override
	public Rectangle getBounds() {
		return new Rectangle((int)x_pos, (int)y_pos, (int)width, (int)height);
	}

	public void attack(){
		 if (bullets <= 0){
				bullets = 0;
			}
		if (bullets > 0){
			
			if (lookingLeft == true){
			attacklist.addObject(new Attack(this.x_pos,this.y_pos+height/2,6, -4, 0));
			bullets+=-1;
			} else
				attacklist.addObject(new Attack(this.x_pos+width,this.y_pos+height/2,6, 4, 0));
			bullets+=-1;
			}
		}
	public void setMaxPoints(int maxPoints){
		this.maxPoints = maxPoints;
	}
	}


