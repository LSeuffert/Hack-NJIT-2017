package Main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

import Graphics.Assets;
import Objects.Player;

public class Main implements Runnable{
	
	public int Width,Height;
	Display display;
	BufferStrategy bs;
	////assets//
	Assets asset;
	private boolean isRunning = false;
	private Thread thread;
	
	////player////
	Player player;

	public Main(String gameName, int Width, int Height){
		this.Width=Width;
		this.Height=Height;
		display = new Display(gameName,Width,Height);
		start();
		init();
	}
	
	public void init(){
		player = new Player(this, 300,300,40,40,ID.Player);
		//load assets///
		asset = new Assets();
		asset.loadgraphics();
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
				System.out.println("FPS: " + frames + " TICKS: " + updates);
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
		g.setColor(Color.black);
		g.fillRect(0, 0, Width, Height);
		
		//BufferedImage player = asset.playerSpriteSheet;
		//g2d.drawImage(player, 0, 0, null);
		
		player.render(g);
		
		//////////////////////////////////

		g.dispose();
		bs.show();
		
	}

	private void tick() {
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
				
				if(red ==255){
					
				}
				else if(blue==255){
				}
			}
		}
	}
		
}
