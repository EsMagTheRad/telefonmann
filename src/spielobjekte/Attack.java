package spielobjekte;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.LinkedList;

import Interfacelike.GameObject;
import engines.Imageloader;

public class Attack extends GameObject{
	private BufferedImage image;
	private Imageloader loader = new Imageloader();
	private int direction;
	
	public Attack(float x_pos, float y_pos, int id, int direction) {
		super(x_pos, y_pos, id);
		image = loader.loadImage("/fire.png");
		this.direction = direction;
	}

	@Override
	public void refresh(LinkedList<GameObject> objectList) {
	}
	public void move(boolean left){
		setX_pos(x_pos+direction);
	}

	@Override
	public void paint(Graphics g) {
		g.drawImage(image, (int) x_pos, (int) y_pos, 16, 16, null);
		
	}

	@Override
	public Rectangle getBounds() {
		return new Rectangle((int) x_pos, (int) y_pos, 15, 15);
	}

	@Override
	public Rectangle getBoundsBottom() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Rectangle getBoundsTop() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Rectangle getBoundsLeft() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Rectangle getBoundsRight() {
		// TODO Auto-generated method stub
		return null;
	}

}
