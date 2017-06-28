package spielobjekte;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.LinkedList;
import Interfacelike.GameObject;
import engines.Imageloader;

public class DefaultBlock extends GameObject{
	BufferedImage wall;
	Imageloader loader = new Imageloader();

	public DefaultBlock(float x_pos, float y_pos, int id) {
		super(x_pos, y_pos, id);
		setId(2);
		wall = loader.loadImage("/Cellar_Wall.png");
	}

	public void refresh(LinkedList<GameObject> object) {
		//Its the default block, it doesn't change its state
	}

	//Default block is not a png_image but directly drawn by Graphics as a grey 32x32 Block with black border
	//Replace it with image 
	public void paint(Graphics g) {
		g.drawImage(wall, (int) x_pos, (int)y_pos, 32, 32, null);
	}

	public Rectangle getBounds() {
		// TODO Auto-generated method stub
		return new Rectangle((int)x_pos, (int)y_pos, 32, 32);
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
