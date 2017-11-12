package Objects;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import Graphics.Animation;
import Main.Handler;
import Main.ID;
import Main.Main;

public class Player2 extends Object{
	Main game;
	Ak47 gun;
	private Handler handler;
	public int facing;
	private double accx,accy;
	private double gravity,maxDy;
	private boolean falling,canJump;
	private Animation animLeft;
	private Animation animRight;
	int health = 100;
	public boolean equipped = false;

	
	public Player2(Main game, int x, int y, int width, int height, ID id, Handler handler) {
		super(game, x, y, width, height, id);
		this.handler = handler;
		this.game = game;
		//set velocity//
		gravity = 0.5;
		maxDy = 10;
		
		//Animation
		animLeft = new Animation(90,game.asset.player2SpritesFlipped);
		animRight = new Animation(90, game.asset.player2Sprites);
	}

	@Override
	public void tick() {
		collision();
		move();
		fall();
		shoot();
		
		//Animation
		animLeft.tick();
		animRight.tick();
	}

	@Override
	public void render(Graphics g) {
		Graphics2D g2d = (Graphics2D)g;
		/*g2d.setColor(Color.white);
		g2d.fill(getBounds());
		g2d.setColor(Color.blue);
		g2d.draw(getBounds());
		g2d.setColor(Color.green);
		g2d.draw(getBoundsTop());
		g2d.setColor(Color.white);
		g2d.draw(getBoundsBottom());
		g2d.setColor(Color.yellow);
		g2d.draw(getBoundsRight());*/
		g2d.setColor(Color.green);
		g2d.fill(new Rectangle((int)x,(int)y-20,health,10));
		g2d.draw(new Rectangle((int)x,(int)y-20,100,10));
		g2d.drawImage(getCurrAnimFrame(), (int)x, (int)y, width, height, null);
	}

	@Override
	public Rectangle getBounds() {
		return new Rectangle((int)x, (int)y, width, height);
	}
	public Rectangle getBoundsBottom(){
		return new Rectangle((int)(x+width/4), (int)y+height-height/4, (int)(width-width/2), height/4);
	}
	public Rectangle getBoundsTop(){
		return new Rectangle((int)(x+width/4), (int)y, (int)(width-width/2), height/4);
	}
	public Rectangle getBoundsRight(){
		return new Rectangle((int)x+width-width/4, (int)y+height/4, width/4, height-height/2);
	}
	public Rectangle getBoundsLeft(){
		return new Rectangle((int)x, (int)y+height/4, width/4, height-height/2);
	}
	
	public void move(){
		//check final velocity
		if(velx >= XVELOCITY){
			velx = XVELOCITY;
		}
		else if (velx <= -XVELOCITY){
			velx = -XVELOCITY;
		}
		if(vely >= YVELOCITY){
			vely = YVELOCITY;
		}
		else if (vely <= -YVELOCITY){
			vely = -YVELOCITY;
		}
		
		//gravity
		
		///update position
		x+=velx;
		y+=accy;
		
		///move right
		if(handler.isRight2()){
			facing = 1;
			velx += 1.5;
		}
		///move left
		else if(handler.isLeft2()){
			facing = -1;
			velx -= 1.5;
		}
		else{
			velx = 0;
		}
		
		///jump
		if(handler.isUp2()){
			jump(12,height*4);
		}
		
	}

	int released = 0;
	int pressed = 1;
	public void shoot(){
		if(handler.isShoot2() && equipped){
			if(pressed == 1 && released == 1){
				//decrease ammo
				gun.ammo--;
				handler.shake = true;
				handler.addObject(new Bullet(game, (int)x+width/2, (int) ((int)y+height*.75), 16, 2, facing, ID.Bullet2,handler));
			}
			released = 0;
		}
		else{
			released = 1;
			handler.shake = false;
		}
	}
	
	public void collision(){
		for(int i =0; i<handler.getGameObjects().size();i++){
			Object temp = handler.getGameObjects().get(i);
			if(temp.id == ID.Platform){
				///bottom collision
				if(getBoundsBottom().intersects(temp.getBounds()) &&  accy > 0){
					falling = false;
					canJump  = true;
					accy = 0;
					y = temp.y - height;
				}
				else{
					falling = true;
				}
				///top collision 
				if(getBoundsTop().intersects(temp.getBounds()) &&  accy < 0){
					y = temp.y + temp.height;
				}
				
				///right collision 
				if(getBoundsRight().intersects(temp.getBounds()) && velx > 0){
					x = temp.x - width;
				}
				///left collision 
				if(getBoundsLeft().intersects(temp.getBounds()) && velx < 0){
					x = temp.x + temp.width;
				}
			}
			///fire damage
			if(temp.id == ID.Bullet1){
				if(temp.getBounds().intersects(getBounds())){
					if(temp.velx>0)
						x+=5;
					else{
						x-=5;
					}
					handler.removeObject(temp);
					health-=10;	
				}
			}
			///pick up gun
			if(temp.id == ID.AK47){
				gun = (Ak47)temp;
				if(temp.getBounds().intersects(getBounds())){
					if(handler.isDown2()){
						equipped = true;
						gun.parent = ID.Player2;
					}
				}
				///position the gun
				if(equipped){
					gun.x = x+facing * (width/2);
					gun.y = y+(height/2);
				}
				
			}
		}
	}
	
	public void fall(){
		if(falling){
			accy+= gravity;
			if(accy >maxDy){
				accy = maxDy;
			}
		}
	}
	public void jump(double jumpHeight, int jumpleap){
		if(canJump){
			accy -= jumpHeight;
		}
		if(accy > jumpleap){
			accy = jumpleap;
		}
		canJump = false;
	}
	
	public BufferedImage getCurrAnimFrame(){
		if(velx > 0){
			return animRight.getCurrentFrame();
		}
		else if(velx < 0){
			return animLeft.getCurrentFrame();
		}
		if(facing >0) 
			return animRight.frames[1];
		else{
			return animLeft.frames[1];
		}
		
	}
}
