package spielobjekte;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.LinkedList;

import Interfacelike.GameObject;
import engines.Imageloader;

public class Star extends GameObject{
	
	private BufferedImage image;
	private Imageloader loader = new Imageloader();

	public Star(float x_pos, float y_pos, int id) {
		super(x_pos, y_pos, id);
		image = loader.loadImage("/star.png");
	}

	@Override
	public void refresh(LinkedList<GameObject> object) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void paint(Graphics g) {
		g.drawImage(image, (int) x_pos, (int) y_pos, 32, 32, null);
		
	}

	@Override
	public Rectangle getBounds() {
		return new Rectangle((int) x_pos, (int) y_pos, 32, 32);
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
