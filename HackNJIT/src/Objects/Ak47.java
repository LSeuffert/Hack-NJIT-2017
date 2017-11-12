package Objects;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

import Main.Handler;
import Main.ID;
import Main.Main;

public class Ak47 extends Object{
	
	Main game;
	public int ammo;
	Player player;
	public ID parent;
	public boolean equipped= false;
	public Ak47(Main game, int x, int y, int width, int height, ID id) {
		super(game, x, y, width, height, id);
		this.game = game;
		ammo = 32;
	}
	
	public void tick(){
		if(ammo<=0){
			if(parent == ID.Player1){
				game.handler.player.equipped = false;
			}
			else if(this.parent == ID.Player2){
				game.handler.player2.equipped = false;
			}
			game.handler.removeObject(this);
		}
	}
	
	public Rectangle getBounds(){
		return new Rectangle((int)x, (int)y, width, height);
	}

	@Override
	public void render(Graphics g) {
		Graphics2D g2d = (Graphics2D)g;
		if (equipped){
			if(parent == ID.Player1 && game.handler.player.facing > 0 && game.handler.player.equipped){
				g2d.drawImage(game.asset.ak47Right, (int)x, (int)y, width, height, null);
			}
			else if(parent == ID.Player2 && game.handler.player2.facing > 0 && game.handler.player2.equipped){
				g2d.drawImage(game.asset.ak47Right, (int)x, (int)y, width, height, null);
			}
			else{
				g2d.drawImage(game.asset.ak47Left, (int)x, (int)y, width, height, null);
			}
		}
		else{
			g2d.drawImage(game.asset.ak47Left, (int)x, (int)y, width, height, null);
		}
	}
}
