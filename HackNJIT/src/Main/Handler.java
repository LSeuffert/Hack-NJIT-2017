package Main;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.LinkedList;
import java.util.Random;

import Graphics.Tile;
import Objects.Object;
import Objects.Player;
import Objects.Player2;

public class Handler {
	private boolean up, down, left, right,shoot,up2, down2, left2, right2,shoot2;
	Main game;
	private LinkedList<Object> gameObjects;
	private LinkedList<Tile> tiles;
	public Player player;
	public Player2 player2;
	
	//Camera///
	Camera cam;
	public boolean shake = false;
		
	public Handler(Main game){
		this.game = game;
		gameObjects = new LinkedList<Object>();
		tiles = new LinkedList<Tile>();
		cam = new Camera(this,0,0,0,0);
		
	}
	
	public void tick(){
		for(int i = 0;i<gameObjects.size(); i++){
			Object gameObj = gameObjects.get(i);
			gameObj.tick();
		}
		//camera//
		cam.tick();
	}
	
	Random rand = new Random();
	int tx,ty;
	public void render(Graphics g){
		Graphics2D g2d = (Graphics2D)g;
		if(shake){
			tx = rand.nextInt(6);
			ty = rand.nextInt(6);
		}
		
		g2d.translate(tx, ty);
		for(int i = 0;i<gameObjects.size(); i++){
			Object gameObj = gameObjects.get(i);
			///render object in camera bounds
			//if(gameObj.getBounds().intersects(cam.getBounds())){
				gameObj.render(g2d);
			//
				if(gameObj.getId() == ID.Player1){
					player = (Player) gameObj;
				}
				else if(gameObj.getId() == ID.Player2){
					player2 = (Player2) gameObj;
				}
		}
		
		for(int i = 0;i<tiles.size(); i++){
			Tile tile = tiles.get(i);
			///render object in camera bounds
			//if(tile.getBounds().intersects(cam.getBounds())){
				tile.render(g2d);
			//}
		}
		g2d.translate(-tx, -ty);
		//camera//
		//cam.render(g);
	}
	
	public void addTile(Tile object){
		tiles.add(object);
	}
	
	public void addObject(Object object){
		gameObjects.add(object);
	}
	
	public void removeObject(Object object){
		gameObjects.remove(object);
	}

	public LinkedList<Object> getGameObjects() {
		return gameObjects;
	}

	public void setGameObjects(LinkedList<Object> gameObjects) {
		this.gameObjects = gameObjects;
	}
	
	public boolean isUp() {
		return up;
	}

	public void setUp(boolean up) {
		this.up = up;
	}

	public boolean isDown() {
		return down;
	}

	public void setDown(boolean down) {
		this.down = down;
	}

	public boolean isRight() {
		return right;
	}

	public void setRight(boolean right) {
		this.right = right;
	}

	public boolean isLeft() {
		return left;
	}

	public void setLeft(boolean left) {
		this.left = left;
	}

	public boolean isShoot1() {
		return shoot;
	}

	public void setShoot1(boolean shoot) {
		this.shoot = shoot;
	}

	public boolean isUp2() {
		return up2;
	}

	public void setUp2(boolean up2) {
		this.up2 = up2;
	}

	public boolean isDown2() {
		return down2;
	}

	public void setDown2(boolean down2) {
		this.down2 = down2;
	}

	public boolean isLeft2() {
		return left2;
	}

	public void setLeft2(boolean left2) {
		this.left2 = left2;
	}

	public boolean isRight2() {
		return right2;
	}

	public void setRight2(boolean right2) {
		this.right2 = right2;
	}

	public boolean isShoot2() {
		return shoot2;
	}

	public void setShoot2(boolean shoot2) {
		this.shoot2 = shoot2;
	}
	
}
