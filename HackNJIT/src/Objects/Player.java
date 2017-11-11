package Objects;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

import Main.ID;
import Main.Main;

public class Player extends Object  {

	Main game;
	public Player(Main game, int x, int y, int width, int height, ID id) {
		super(game, x, y, width, height, id);
	}

	@Override
	public void tick() {
		
	}

	@Override
	public void render(Graphics g) {
		Graphics2D g2d = (Graphics2D)g;
		g2d.setColor(Color.blue);
		g2d.draw(getBounds());
	}

	@Override
	public Rectangle getBounds() {
		
		return new Rectangle((int)x, (int)y, width, height);
	}
	
}
