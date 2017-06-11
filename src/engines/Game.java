package engines;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

import Interfacelike.GameObject;
import spielobjekte.DefaultBlock;
import spielobjekte.Enemy;
import spielobjekte.Food;
import spielobjekte.Player;
import spielobjekte.PushBlock;
import spielobjekte.Star;
import spielobjekte.Wallcreeper;

public class Game extends Canvas implements Runnable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private boolean running = false;
	private Thread thread;
	public static int WIDTH;
	public static int HEIGHT;
	private BufferedImage level;
	private Imageloader loader = new Imageloader(); 
	private Game_Object_List objectlist;
	private Kamera kamera;
	
	
	public Game(){}
	
	public void init() {
		WIDTH = getWidth();
		HEIGHT = getHeight(); //Gets container width and height
		level = loader.loadImage("/level.png");
		objectlist = new Game_Object_List();
		kamera = new Kamera(0,0);
		
		loadLevel(level);
		this.addKeyListener(new Keyreader(objectlist));
	}

	/**Creates game- loop using a thread
	 * Tutorial used: http://zetcode.com/tutorials/javagamestutorial/animation/ */
	public void run() {
		
		init();
		
		//this.requestFocus();
		long beforeTime = System.currentTimeMillis();
		double timeDiff = 0;
		
		while (running){
			

			timeDiff += (System.currentTimeMillis() - beforeTime) / ((double)(1000 / 60));
			beforeTime = System.currentTimeMillis();
			
			//thread sleeps
			while(timeDiff >= 1){
				tick(); //Calls the tick() method of the object- list, witch refreshes the objects in the list
				timeDiff--;
			}
			draw_frame();


		}
	}
	
	
	private void tick(){
		GameObject player = objectlist.fetchPlayer();	
		objectlist.tick();
		kamera.tick(player);
	}
	
	private void draw_frame(){
		GameObject player = objectlist.fetchPlayer();
		BufferStrategy bs = this.getBufferStrategy();
		if(bs == null)
		{
			this.createBufferStrategy(4);
			return;
		}
		
		Graphics g = bs.getDrawGraphics();
		
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, getWidth(), getHeight());//Black background
		//for Camera
		Graphics2D g2d = (Graphics2D) g;
		g2d.translate(kamera.getX_pos(), kamera.getY_pos());
		objectlist.render(g); /**
								* Method iterates through every single object in the list and calls the "GameObject.paint()" Method
								* to of those objects, determine how it must be displayed in the respective frame.
								*/
		g2d.translate(-kamera.getX_pos(), -kamera.getY_pos());

		if (player.getLife() <= 0 || player.getRestart() == true){
			g.drawString("Restart Game?: Y / N", 360, 250);
			if(player.getRestart()==true){
			    while (!objectlist.objectList.isEmpty()) {
			        objectlist.objectList.remove(0);
			    }
			    
				loadLevel(level);
			}
		}
		
		g.dispose();
		bs.show();
	}
	
	
	
	
	public synchronized void start(){
		if(running){
			return;
		}else{
			running = true;
			thread = new Thread(this);
			thread.start();
		}
		
	}
	private void loadLevel(BufferedImage image){ /**Passed on image gets "scanned" pixel-wise to identify rgb-value of each pixel. 
												If a rgb-value specified in the code is detected on a pixel, the Object associated with it is loaded into the level
												For comparison visit: http://stackoverflow.com/questions/4801366/convert-rgb-values-to-integer**/
		int width = image.getWidth();
		int height = image.getHeight();

		for(int i = 0; i < width;i++){
			for(int j = 0; j < height; j++){
				int pixel = image.getRGB(i, j);
				int red = (pixel >> 16) & 0xff;;
				int green = (pixel >> 8) & 0xff;
				int blue = (pixel) & 0xff;
				
				/**
				 * Adds every game- object to the object- list
				 */
				if(red == 255 && green == 255 && blue == 255) objectlist.addObject(new DefaultBlock(i*32, j*32, 2)); //Blocks
				if(red == 255 && green == 255 & blue == 000) objectlist.addObject(new Enemy(i*32, (j*32)-32, objectlist, 3, 2)); //Enemys
				if(red == 38 && green == 127 && blue == 000) objectlist.addObject(new PushBlock(i*32, (j*32)-32, objectlist, 10)); //PUSHBLOCK
				if(red == 255 && green == 000 && blue == 000) objectlist.addObject(new Wallcreeper(i*32, j*32, objectlist, 4)); //Walltraps
				if(red == 255 && green == 000 && blue == 110) objectlist.addObject(new Star(i*32, j*32, 5)); //Points
				if(red == 000 && green == 255 && blue == 000) objectlist.addObject(new Food(i*32, j*32, 6)); //Bananas	
			}
			}
		for(int i = 0; i < width;i++){
			for(int j = 0; j < height; j++){
				int pixel = image.getRGB(i, j);
				int red = (pixel >> 16) & 0xff;;
				int green = (pixel >> 8) & 0xff;
				int blue = (pixel) & 0xff;
	
				if(red == 000 && green == 000 && blue == 255) objectlist.addObject(new Player(i*32, (j*32)-32, objectlist, 1)); //Player
				}
			}
		}
	
	
	
	public static void main(String[] args){
		new Window(800, 600, "Telefonmann", new Game());
	}
}
