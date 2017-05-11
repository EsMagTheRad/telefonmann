package engines;

import Interfacelike.GameObject;

public class Kamera {
	private float x_pos, y_pos;
	
	public Kamera(float x_pos,float y_pos){
		this.x_pos = x_pos;
		this.y_pos = y_pos;
	}
	//traces the player- objects position and centers the view around it
	public void tick(GameObject player){
		x_pos = -player.getX_pos() + Game.WIDTH/2;
		y_pos = -player.getY_pos() + Game.HEIGHT/2;
	}
	//auto- generated getters and setters
	public float getX_pos() {
		return x_pos;
	}

	public void setX_pos(float x_pos) {
		this.x_pos = x_pos;
	}

	public float getY_pos() {
		return y_pos;
	}

	public void setY_pos(float y_pos) {
		this.y_pos = y_pos;
	}
	
}
