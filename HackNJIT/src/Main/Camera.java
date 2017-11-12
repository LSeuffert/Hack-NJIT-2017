package Main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

public class Camera {
	Handler handler;
	public int x, y, width, height, x1, y1, x2, y2;
	public Camera(Handler handler, int x1, int y1, int x2, int y2){
		this.handler = handler;
		this.x1 = x1;
		this.y1 = y1;
		this.x2 = x2;
		this.y2 = y2;
	}
	
	public void tick(){
		for(int i =0; i < handler.getGameObjects().size(); i++){
			if(handler.getGameObjects().get(i).getId() == ID.Player1){
				x1 = (int)handler.getGameObjects().get(i).getX();
				y1 = (int)handler.getGameObjects().get(i).getY();
			}
			else if(handler.getGameObjects().get(i).getId() == ID.Player2){
				x2 = (int)handler.getGameObjects().get(i).getX();
				y2 = (int)handler.getGameObjects().get(i).getY();
			}
		}
		///find start x of camera
		if(x1<x2){
			x = x1-250;
			width = x2+250-x;
		}else{
			x = x2-150;
			width = x1+250-x;
		}
		///find start y of camera
		if(y1<y2){
			y = y1-250;
			height  = y2+250 - y;
		}else{
			y = y2-250;
			height  = y1+250 - y;
		}
		
		
	}
	
	public void render(Graphics g){
		Graphics2D g2d = (Graphics2D)g;
		g2d.setColor(Color.blue);
		g2d.draw(getBounds());
	}
	
	public Rectangle getBounds(){
		return new Rectangle(x,y,width,height);
	}
}
