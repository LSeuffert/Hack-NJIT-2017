package Graphics;

import java.awt.image.BufferedImage;

public class Assets {
	public  BufferedImage playerSpriteSheet;
	public void loadgraphics(){
		playerSpriteSheet = imageLoader.loadImage("/playerSheet.png");
	}
}
