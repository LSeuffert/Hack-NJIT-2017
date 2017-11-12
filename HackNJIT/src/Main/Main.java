package Main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

import Graphics.Assets;
import Graphics.Tile;
import Input.KeyInput;
import Input.Mouse;
import Objects.Ak47;
import Objects.Platform;
import Objects.Player;
import Objects.Player2;

public class Main implements Runnable{
	
	public int Width,Height;
	Display display;
	BufferStrategy bs;
	////assets//
	public Assets asset;
	private boolean isRunning = false;
	private Thread thread;
	
	////Handler////
	public Handler handler;
	
	
	public Mouse mouse;

	public Main(String gameName, int Width, int Height){
		this.Width=Width;
		this.Height=Height;
		display = new Display(gameName,Width,Height);
		start();
		init();
	}
	
	public void init(){
		mouse = new Mouse(this);
		handler = new Handler(this);
		//Camera//
		display.canvas.addKeyListener(new KeyInput(handler));
		display.canvas.addMouseMotionListener(mouse);
		display.canvas.addMouseListener(mouse);
		//load assets///
		asset = new Assets();
		asset.loadgraphics();
		
		//load level//
		loadLevel(asset.level);
		loadLevelTiles(asset.levelTiles);
	}

	public void start(){
		isRunning = true;
		Thread thread = new Thread(this);
		thread.start();
	}
	
	public void stop(){
		isRunning = false;
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public void run() {
		long lastTime = System.nanoTime();
		double amountOfTicks = 60.0;
		double ns = 1000000000 / amountOfTicks;
		double delta = 0;
		long timer = System.currentTimeMillis();
		int updates = 0;
		int frames = 0;
		while(isRunning){
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			while(delta >= 1){
				tick();
				updates++;
				delta--;
			}
			render();
			frames++;
					
			if(System.currentTimeMillis() - timer > 1000){
				timer += 1000;
				//System.out.println("FPS: " + frames + " TICKS: " + updates);
				frames = 0;
				updates = 0;
			}
		}
		stop();
		
	}

	private void render() {
		bs = display.getCanvas().getBufferStrategy();
		if(bs==null){
			display.getCanvas().createBufferStrategy(3);
			return;
		}
		Graphics g = bs.getDrawGraphics();
		Graphics2D g2d = (Graphics2D)g;
		
		//////////////////////////////////
		/*g.setColor(Color.black);
		g.fillRect(0, 0, Width, Height);*/
		g2d.drawImage(asset.background, 0, 0, Width,Height, null);
		//BufferedImage player = asset.playerSpriteSheet;
		//g2d.drawImage(player, 0, 0, null);
		
		////draw objects///
		handler.render(g2d);
		
		
		//////////////////////////////////

		g.dispose();
		bs.show();
		
	}

	private void tick() {
		handler.tick();
		
	}
	
	public void loadLevel(BufferedImage image){
		int w = image.getWidth();
		int h = image.getHeight();
		for(int i =0;i<w;i++){
			for(int j=0;j<h;j++){
				int pixel = image.getRGB(i, j);
				int red = (pixel >> 16) & 0xff;
				int green = (pixel >> 8) & 0xff;
				int blue = (pixel) & 0xff;
				
				if(red == 255 && green == 0 && blue == 0){
					////platforms--> red
					handler.addObject(new Platform(this, i*32,j*32,32,32,ID.Platform));
				}
				else if(red == 0 && green == 0 && blue == 255 ){
					////player---> blue
					handler.addObject(new Player(this, i*32,j*32,64,64,ID.Player1,handler));
				}
				
				else if(red == 0 && green == 255 && blue == 0 ){
					////player2---> green
					handler.addObject(new Player2(this, i*32,j*32,64,64,ID.Player2,handler));
				}
				else if(red == 255 && green == 255 && blue == 255 ){
					////player2---> green
					handler.addObject(new Ak47(this, i*32,j*32,64,64,ID.AK47));
				}
			}
		}
	}
	
	public void loadLevelTiles(BufferedImage image){
		int w = image.getWidth();
		int h = image.getHeight();
		for(int i =0;i<w;i++){
			for(int j=0;j<h;j++){
				int pixel = image.getRGB(i, j);
				int red = (pixel >> 16) & 0xff;
				int green = (pixel >> 8) & 0xff;
				int blue = (pixel) & 0xff;
				
				if(red == 255 && green == 0 && blue == 0){
					////middle grass--> red
					handler.addTile(new Tile(asset.tiles[10], i*32,j*32,32,32));
				}
				else if(red == 0 && green == 0 && blue == 255 ){
					////left grass corner---> blue
					handler.addTile(new Tile(asset.tiles[9], i*32,j*32,32,32));
				}
				
				else if(red == 0 && green == 255 && blue == 0 ){
					////right grass corner---> green
					handler.addTile(new Tile(asset.tiles[11], i*32,j*32,32,32));
				}
				else if(red == 255 && green == 255 && blue == 255 ){
					////one grass tile---> white
					handler.addTile(new Tile(asset.tiles[12], i*32,j*32,32,32));
				}
				else if(red == 100 && green == 50 && blue == 0 ){
					////one grass tile middle---> white
					handler.addTile(new Tile(asset.tiles[13], i*32,j*32,32,32));
				}
				else if(red == 120 && green == 25 && blue == 0 ){
					////left corner middle
					handler.addTile(new Tile(asset.tiles[14], i*32,j*32,32,32));
				}
				else if(red == 0 && green == 71 && blue == 136 ){
					////right corner 
					handler.addTile(new Tile(asset.tiles[16], i*32,j*32,32,32));
				}
				else if(red == 136 && green == 96 && blue == 0 ){
					////middle dirt 
					handler.addTile(new Tile(asset.tiles[17], i*32,j*32,32,32));
				}
			}
		}
	}
		
}
