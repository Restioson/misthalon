//Package declaration
package io.github.restioson.misthalon;

import java.math.BigInteger;

//Imports
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Array;

//Main class
public class Main extends ApplicationAdapter {
	
	//Initialise variables
	Player player;
	Array<Entity> entities;
	BigInteger tickCount;
	
	//Initialise handlers
	InputHandler inputhandler;
	CollisionHandler collisionhandler;
	MapHandler maphandler;
	
	//Initialise Renderer
	Renderer renderer;
	
	
	//Method called when game is launched
	@Override
	public void create() {
		
		//Create player
		player = new Player(new Texture(Gdx.files.internal("data/placeholder.png")), new Rectangle(), this);
		
		
		//Create renderer
		renderer = new Renderer(this);
		
		//Create handlers
		inputhandler = new InputHandler(this);
		Gdx.input.setInputProcessor(inputhandler);
		maphandler = new MapHandler(this, "lolxd.tmx");
		collisionhandler = new CollisionHandler(this, maphandler.getCollisionLayer());
		

		//Create entities array
		entities = new Array<Entity>();
		
		//Align player to center tile
		Vector3 screencenter = new Vector3(Gdx.graphics.getWidth() / 2, Gdx.graphics.getWidth() / 2, 0);
		Vector3 worldcenter = renderer.getCamera().unproject(screencenter);
		player.setX(worldcenter.x);
		player.setY(worldcenter.y);
		
		//Initialise tick count
		tickCount = BigInteger.valueOf(0L);
		
	}
	
	//Method called on tick
	@Override
	public void render() {
		
		/* Game logic */
		
		
		//Collision
		collisionhandler.handleMapCollisions();
		
		
		this.renderer.getCamera().position.set(this.player.getX(), this.player.getY(), 0);
		
		//Increment tickCount by 1
		tickCount.add(BigInteger.valueOf(1L));
		
		/* Render game */
		renderer.render();
		
	}
	
	//Dispose application
	@Override
	public void dispose() {
		
		//Dispose entities
		for (Entity curEntity : entities) {
			curEntity.dispose();
		}
		
		//Dispose player
		player.dispose();
		
	}
	
	/* Getters and setters */
	
	//Method to get entities
	public Array<Entity> getEntities() {
		return entities;
	}
	
	//Method to get tickCount
	public BigInteger getTickCount() {
		return this.tickCount;
	}
	
	//Method to get player
	public Player getPlayer() {
		return player;
	}
	
	//Method to get camera
	public OrthographicCamera getCamera() {
		return this.renderer.getCamera();
	}
	
	//Method to translate player & camera
	public void translatePlayer(int x, int y) {
		this.player.translate((float) x, (float) y);
	}
	
	//Method to get maphandler
	public MapHandler getMapHandler() {
		return this.maphandler;
	}
	
}
 