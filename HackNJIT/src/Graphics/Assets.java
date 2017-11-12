package Graphics;

import java.awt.image.BufferedImage;

public class Assets {
	public BufferedImage level,levelTiles, playerSpriteSheet, playerSpriteSheetflipped,player2SpriteSheet, player2SpriteSheetflipped,tileSet;
	public BufferedImage background;
	public BufferedImage dust;
	///guns
	public BufferedImage gunSpritesRight;
	public BufferedImage gunSpritesLeft;
	public BufferedImage ak47Right, ak47Left;
	public BufferedImage explosion;
	
	public BufferedImage [] player1Sprites = new BufferedImage[6];
	public BufferedImage [] player1SpritesFlipped = new BufferedImage[6];
	public BufferedImage [] player2Sprites = new BufferedImage[6];
	public BufferedImage [] player2SpritesFlipped = new BufferedImage[6];
	public BufferedImage [] tiles = new BufferedImage[18];
	public BufferedImage [] dustEffect = new BufferedImage[11];
	public BufferedImage [] explosionEffect = new BufferedImage[8];
	
	public void loadgraphics(){
		level = imageLoader.loadImage("/level.png");
		levelTiles = imageLoader.loadImage("/levelTiles.png");
		tileSet = imageLoader.loadImage("/tileset.png");
		playerSpriteSheet = imageLoader.loadImage("/player1.png");
		player2SpriteSheet = imageLoader.loadImage("/player2.png");
		playerSpriteSheetflipped = imageLoader.loadImage("/player1flipped.png");
		player2SpriteSheetflipped = imageLoader.loadImage("/player2flipped.png");
		background = imageLoader.loadImage("/dust.png").getSubimage(0, 0, 144,400);
		dust = imageLoader.loadImage("/landingDust.png");
		explosion = imageLoader.loadImage("/explosion.png");
		
		//player1 sprites								//x, y, width, height
		player1Sprites[0] = playerSpriteSheet.getSubimage(0, 0, 118,150 );
		player1Sprites[1] = playerSpriteSheet.getSubimage(0, 150, 118, 150);
		player1Sprites[2] = playerSpriteSheet.getSubimage(0, 300, 118, 150);
		player1Sprites[3] = playerSpriteSheet.getSubimage(0, 450, 118, 150);
		player1Sprites[4] = playerSpriteSheet.getSubimage(0, 600, 118, 150);
		player1Sprites[5] = playerSpriteSheet.getSubimage(0, 750, 118, 150);
		
		player1SpritesFlipped[0] = playerSpriteSheetflipped.getSubimage(0, 0, 118,150 );
		player1SpritesFlipped[1] = playerSpriteSheetflipped.getSubimage(0, 150, 118, 150);
		player1SpritesFlipped[2] = playerSpriteSheetflipped.getSubimage(0, 300, 118, 150);
		player1SpritesFlipped[3] = playerSpriteSheetflipped.getSubimage(0, 450, 118, 150);
		player1SpritesFlipped[4] = playerSpriteSheetflipped.getSubimage(0, 600, 118, 150);
		player1SpritesFlipped[5] = playerSpriteSheetflipped.getSubimage(0, 750, 118, 150);
		
		//player2 sprites								//x, y, width, height
		player2Sprites[0] = player2SpriteSheet.getSubimage(0, 0, 118,150 );
		player2Sprites[1] = player2SpriteSheet.getSubimage(0, 150, 118, 150);
		player2Sprites[2] = player2SpriteSheet.getSubimage(0, 300, 118, 150);
		player2Sprites[3] = player2SpriteSheet.getSubimage(0, 450, 118, 150);
		player2Sprites[4] = player2SpriteSheet.getSubimage(0, 600, 118, 150);
		player2Sprites[5] = player2SpriteSheet.getSubimage(0, 750, 118, 150);
		
		player2SpritesFlipped[0] = player2SpriteSheetflipped.getSubimage(0, 0, 118,150 );
		player2SpritesFlipped[1] = player2SpriteSheetflipped.getSubimage(0, 150, 118, 150);
		player2SpritesFlipped[2] = player2SpriteSheetflipped.getSubimage(0, 300, 118, 150);
		player2SpritesFlipped[3] = player2SpriteSheetflipped.getSubimage(0, 450, 118, 150);
		player2SpritesFlipped[4] = player2SpriteSheetflipped.getSubimage(0, 600, 118, 150);
		player2SpritesFlipped[5] = player2SpriteSheetflipped.getSubimage(0, 750, 118, 150);
		
		//tiles
		tiles[0] = tileSet.getSubimage(0, 0, 32, 32);		///grass
		tiles[1] = tileSet.getSubimage(1*32, 0, 32, 32);	///grass tube
		tiles[2] = tileSet.getSubimage(2*32, 0, 32, 32);	///rock
		tiles[3] = tileSet.getSubimage(3*32, 0, 32, 32);	/// marble ball
		tiles[4] = tileSet.getSubimage(4*32, 0, 32, 32);	///right arrow
		tiles[5] = tileSet.getSubimage(5*32, 0, 32, 32);	///left arrow
		tiles[6] = tileSet.getSubimage(6*32, 0, 32, 32);	/// up arrow
		tiles[7] = tileSet.getSubimage(7*32, 0, 32, 32);	/// flowers
		tiles[8] = tileSet.getSubimage(8*32, 0, 32, 32);	/// rock tube
		
		tiles[9] = tileSet.getSubimage(0*32, 1*32, 32, 32);	/// left grass corner
		tiles[10] = tileSet.getSubimage(1*32, 1*32, 32, 32);/// middle grass
		tiles[11] = tileSet.getSubimage(2*32, 1*32, 32, 32);/// right grass
		tiles[12] = tileSet.getSubimage(3*32, 1*32, 32, 32);/// one grass tile 
		tiles[13] = tileSet.getSubimage(3*32, 2*32, 32, 32);/// one grass tile middle
		tiles[14] = tileSet.getSubimage(0*32, 2*32, 32, 32);/// left corner middle
		tiles[15] = tileSet.getSubimage(0*32, 3*32, 32, 32);/// left corner bottom
		tiles[16] = tileSet.getSubimage(2*32, 2*32, 32, 32);/// right corner
		tiles[17] = tileSet.getSubimage(1*32, 2*32, 32, 32);/// middle dirt

		//guns///
		gunSpritesRight = imageLoader.loadImage("/guns.png");
		gunSpritesLeft = imageLoader.loadImage("/gunsFlipped.png");
		
		ak47Right = gunSpritesRight.getSubimage(0, 0, 120, 64);
		ak47Left = gunSpritesLeft.getSubimage(645, 0, 120, 64);
		
		///landing affects
		dustEffect[0] = dust.getSubimage(2*127, 0*127, 127, 127);
		dustEffect[1] = dust.getSubimage(3*127, 1*127, 127, 127);
		dustEffect[2] = dust.getSubimage(0*127, 1*127, 127, 127);
		dustEffect[3] = dust.getSubimage(1*127, 1*127, 127, 127);
		dustEffect[4] = dust.getSubimage(2*127, 1*127, 127, 127);
		dustEffect[5] = dust.getSubimage(3*127, 1*127, 127, 127);
		dustEffect[6] = dust.getSubimage(0*127, 2*127, 127, 127);
		dustEffect[7] = dust.getSubimage(1*127, 2*127, 127, 127);
		dustEffect[8] = dust.getSubimage(2*127, 2*127, 127, 127);
		dustEffect[9] = dust.getSubimage(3*127, 2*127, 127, 127);
		dustEffect[10] = dust.getSubimage(0*127, 3*127, 127, 127);
		
		///explosion
		explosionEffect[0] = explosion.getSubimage(0, 0, 100, 100);
		explosionEffect[1] = explosion.getSubimage(1*128, 0*127, 128, 127);
		explosionEffect[2] = explosion.getSubimage(2*128, 0*127, 128, 127);
		explosionEffect[3] = explosion.getSubimage(3*128, 0*127, 128, 127);
		explosionEffect[4] = explosion.getSubimage(0*128, 1*127, 128, 127);
		explosionEffect[5] = explosion.getSubimage(1*128, 1*127, 128, 127);
		explosionEffect[6] = explosion.getSubimage(2*128, 1*127, 128, 127);
		explosionEffect[7] = explosion.getSubimage(3*128, 1*127, 128, 127);
	}
}
