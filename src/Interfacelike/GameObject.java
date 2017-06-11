package Interfacelike;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.LinkedList;

public abstract class GameObject {
	/**
	 * Each object in the game will implement this class and therefore is able to be 
	 * located, repositioned and its state can be tracked
	 */
	protected float x_pos;
	protected float y_pos;
	protected float life;
	protected int id;
	protected float vel_x = 0, vel_y = 0;
	protected boolean falling, jumping, lookingLeft ,moveable, restart;
	/**Id List, expand for better documentation
	 * 1 = player
	 * 2 = default block
	 * 3 =
	 * 4 =
	 * ...
	 * */
	public GameObject(float x_pos, float y_pos, int id){
		this.x_pos = x_pos;
		this.y_pos = y_pos;
		this.id = id;
		falling = true;
		jumping = true;
		moveable = true;
		lookingLeft = true; //Checks if player or an enemy is heading left or right to load the proper image
	}
	//Abstract methods
	public abstract void refresh(LinkedList<GameObject> object);
	public abstract void paint(Graphics g);
	public abstract Rectangle getBounds();
	public abstract Rectangle getBoundsBottom();
	public abstract Rectangle getBoundsTop();
	public abstract Rectangle getBoundsLeft();
	public abstract Rectangle getBoundsRight();
	
	//from here on: auto- generated getter and setters
	public float getX_pos(){return x_pos;};
	public float getY_pos(){return y_pos;};
	public void setX_pos(float x){this.x_pos = x;};
	public void setY_pos(float y){this.y_pos = y;};
	
	public int getId(){return id;}
	public float getLife(){return life;}
	public void setId(int id){this.id = id;}
	public void setLife(float f){this.life = f;}
	
	public float getVel_X(){return vel_x;};
	public float getVel_Y(){return vel_y;};
	public void setVel_X(float velX){this.vel_x = velX;};
	public void setVel_Y(float velY){this.vel_y = velY;};
	
	public boolean isFalling() {return falling;}
	public void setFalling(boolean falling) {this.falling = falling;}
	public boolean isJumping() {return jumping;}
	public void setJumping(boolean jumping) {this.jumping = jumping;}
	public boolean isLookingLeft() {return lookingLeft;}
	public void setLookingLeft(boolean lookingLeft) {this.lookingLeft = lookingLeft;}
	public boolean isMoveable(){return moveable;}
	public void setMoveable (boolean moveable){this.moveable = moveable;}
	public void setRestart(boolean restart) {this.restart = restart;}
	public boolean getRestart(){return restart;}
}

