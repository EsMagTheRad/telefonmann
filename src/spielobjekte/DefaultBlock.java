package spielobjekte;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.LinkedList;
import Interfacelike.GameObject;

public class DefaultBlock extends GameObject{

	public DefaultBlock(float x_pos, float y_pos, int id) {
		super(x_pos, y_pos, id);
		setId(2);
	}

	public void refresh(LinkedList<GameObject> object) {
		//Its the default block, it doesn't change its state
	}

	//Default block is not a png_image but directly drawn by Graphics as a grey 32x32 Block with black border
	//Replace it with image 
	public void paint(Graphics g) {
		g.setColor(Color.GRAY);
		g.fillRect((int)x_pos, (int)y_pos, 32, 32);
		g.setColor(Color.BLACK);
		g.drawRect((int)x_pos, (int)y_pos, 32, 32);
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
