package Object;

import java.awt.Graphics;
import java.awt.Rectangle;

import Main.ID;
import Main.Main;

public abstract class Object {
	
	protected double x, y;
	protected int width;
	protected int height;
	protected float velx = 0,vely = 0;
	protected float accx = 0,accy = 0;
	protected ID id;
	protected final int XVELOCITY  = 2;
	protected final int YVELOCITY  = 2;
	
	public Object(Main game,int x,int y, int width, int height, ID id){
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.id = id;
		
	}
	
	public abstract void tick();
	public abstract void render(Graphics g);
	public abstract Rectangle getBounds();
	
	public double getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public double getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public float getVelx() {
		return velx;
	}

	public void setVelx(float velx) {
		this.velx = velx;
	}

	public float getVely() {
		return vely;
	}

	public void setVely(float vely) {
		this.vely = vely;
	}

	public ID getId() {
		return id;
	}

	public void setId(ID id) {
		this.id = id;
	}

	
	
}
