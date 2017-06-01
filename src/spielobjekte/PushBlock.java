package spielobjekte;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.LinkedList;

import javax.naming.event.ObjectChangeListener;

import Interfacelike.GameObject;
import engines.Game_Object_List;

public class PushBlock extends GameObject{

	private Game_Object_List objectlist;
	private float gravity = 0.4f, max_speed = 1;
	
	public PushBlock(float x_pos, float y_pos, Game_Object_List objectlist, int id) {
		super(x_pos, y_pos, id);
		this.objectlist = objectlist;
		setId(10);
	}

	@Override
	public void refresh(LinkedList<GameObject> object) {
		
		x_pos += vel_x;
		y_pos += vel_y;
		if(falling||jumping ){
			vel_y+=gravity;
			setMoveable(true);
		}
		if(vel_y >= max_speed){
			vel_y = max_speed;
		}
		
		checkCollision(objectlist);
	}

	private void checkCollision(Game_Object_List objectlist2) {
		for (int i=0; i<objectlist.objectList.size(); i++){
			GameObject currentObject = objectlist.objectList.get(i);
			if(currentObject.getId() == 2 && getBoundsRight().intersects(currentObject.getBounds())){
				setMoveable(false);
			}
			if(currentObject.getId() == 2 && getBoundsLeft().intersects(currentObject.getBounds())){
				setMoveable(false);
			}
			if(currentObject.getId() == 2 && getBounds().intersects(currentObject.getBounds())){
				vel_y = 0;
				vel_x = 0;
			
				jumping = false;
				falling = false;
				
			}else{ 
					jumping = true;
					falling = true;
				}
			}

		}
	public Rectangle getBoundsRight() {
		return new Rectangle((int)((int)x_pos+62), (int)y_pos+15, (int)5, (int)64-30);
	}
	public Rectangle getBoundsLeft() {
		return new Rectangle((int)x_pos - 2, (int)y_pos+15, (int)5, (int)64-30);
	}

	@Override
	public void paint(Graphics g) {
		g.setColor(Color.LIGHT_GRAY);
		g.fillRect((int)x_pos, (int)y_pos, 64, 64);
		g.setColor(Color.BLACK);
		g.drawRect((int)x_pos, (int)y_pos, 64, 64);
		
		Graphics2D g2d = (Graphics2D) g;
		g.setColor(Color.red);
		g2d.fill(getBoundsRight());
		g2d.fill(getBoundsLeft());
		
	}

	@Override
	public Rectangle getBounds() {
		// TODO Auto-generated method stub
		return new Rectangle((int)x_pos, (int)y_pos, 66, 66);
	}

}
