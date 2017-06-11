package spielobjekte;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.LinkedList;

import org.omg.Messaging.SyncScopeHelper;

import Interfacelike.GameObject;
import engines.Game_Object_List;
import engines.Imageloader;

public class Wallcreeper extends GameObject{
	
	private Game_Object_List objectlist;
	private BufferedImage image;
	private Imageloader loader = new Imageloader();
	private boolean isLifting = true;
	
	public Wallcreeper(float x_pos, float y_pos, Game_Object_List objectlist, int id) {
		super(x_pos, y_pos, 4);
		this.objectlist = objectlist;
		image = loader.loadImage("/block_brick.png");
		setVel_Y(2f);
	}

	@Override
	public void refresh(LinkedList<GameObject> object) {
		move();
		y_pos += vel_y;
	
		 
		check_collision(objectlist);//Checks if player collided with an object
		
	
	}

	private void check_collision(Game_Object_List objectlist2) {
		for (int i=0; i<objectlist.objectList.size(); i++){
			GameObject currentObject = objectlist.objectList.get(i);
				
				if(currentObject.getId() == 2){
					if(currentObject.getBounds().intersects(getBoundsBottom())){

						setVel_Y(-1.5f);
						isLifting = true;
					}
				}
				if(currentObject.getId() == 2){
					if(currentObject.getBounds().intersects(getBoundsTop())){

						setVel_Y(4);
						isLifting = false;
					}
				}
				if(currentObject.getId() == 1){
					if(currentObject.getBoundsRight().intersects(getBoundsLeft())||currentObject.getBoundsRight().intersects(getBoundsRight())
							||currentObject.getBoundsLeft().intersects(getBoundsRight())||currentObject.getBoundsLeft().intersects(getBoundsLeft())){

						currentObject.setVel_X(0);
					}
				}
				if(currentObject.getId() == 1){
					if(currentObject.getBoundsTop().intersects(getBoundsBottom())){
						setVel_Y(-1.5f);
						currentObject.setVel_Y(4);
						if (isLifting == false){
						currentObject.setLife(currentObject.getLife()-2);
						}
					}
				}
				if(currentObject.getId() == 1){
					if(currentObject.getBoundsBottom().intersects(getBoundsTop())){
						currentObject.setY_pos(currentObject.getY_pos()-1);
						currentObject.setVel_Y(this.getVel_Y());

						currentObject.setFalling(false);
						currentObject.setJumping(false);
					}
				}
				if(currentObject.getId() == 10){
					if(currentObject.getBoundsTop().intersects(getBoundsBottom())){

						setVel_Y(-1.5f);
					}
				}
				if(currentObject.getId() == 10){
					if(currentObject.getBoundsRight().intersects(getBoundsLeft())||currentObject.getBoundsRight().intersects(getBoundsRight())
							||currentObject.getBoundsLeft().intersects(getBoundsRight())||currentObject.getBoundsLeft().intersects(getBoundsLeft())){

						currentObject.setVel_X(0);
						GameObject player = objectlist.fetchPlayer();
						player.setVel_X(0);
					}
				}
			}
		
	}

	private void move() {
		
	}

	@Override
	public void paint(Graphics g) {
			g.drawImage(image, (int) x_pos, (int) y_pos-32, 64, 64, null);
	}

	@Override
	public Rectangle getBoundsBottom() {
		return new Rectangle((int) ((int)x_pos), (int)y_pos+22, (int)64, (int)10);
	}
	public Rectangle getBoundsTop() {
		return new Rectangle((int) ((int)x_pos), (int)y_pos-32, (int)64, (int)10);

	}
	public Rectangle getBoundsRight() {
		return new Rectangle((int) ((int)x_pos+59), (int)y_pos-32, (int)5, (int)64);
	}
	public Rectangle getBoundsLeft() {
		return new Rectangle((int) ((int)x_pos), (int)y_pos-32, (int)5, (int)64);
	}

	@Override
	public Rectangle getBounds() {
		// TODO Auto-generated method stub
		return new Rectangle((int) ((int)x_pos), (int)y_pos-32, (int)64, (int)64);
	}

	
}
