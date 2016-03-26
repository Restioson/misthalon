package io.github.restioson.misthalon;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.utils.Array;

//Renderer
public class Renderer {
	
	//Fields
	private OrthographicCamera camera;
	private SpriteBatch spritebatch;
    private Main mainclass;
    
	//Constructor
	public Renderer(Main mainclass) {
		
		//Create and configure camera
		this.camera = new OrthographicCamera();
		this.camera.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		
		//Mainclass
		this.mainclass = mainclass;
		
		//Create SpriteBatches
		this.spritebatch = new SpriteBatch();
	}
	
	//Render
	public void render() {
		//Clear screen
        Gdx.gl.glClearColor(1, 0, 0, 1);
        Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		//Update camera
	    camera.update();
		
		/* Render */
	    this.spritebatch.setProjectionMatrix(this.camera.combined);
	    
	    //Draw map
        this.mainclass.getMapHandler().setView(camera);
        this.mainclass.getMapHandler().getTiledMapRenderer().render();
	    
	    //Begin drawing entities, particles, effects and the player
	    this.spritebatch.begin();
	    
	    //Draw player
	    Player player = mainclass.getPlayer();
	    player.getSprite().draw(spritebatch);
	    
	    //Draw entities
	    Array<Entity> entities = mainclass.getEntities();
	    for (Entity currentEntity : entities) {
	    	
	    	//Draw if has sprite
	    	if (!currentEntity.getSprite().equals(null)) {
		    	currentEntity.getSprite().draw(spritebatch);
	    	}
	    }
	    
	    //End drawing
	    this.spritebatch.end();
	}
	
	//Method to get camera
	public OrthographicCamera getCamera() {
		return this.camera;
	}
	
	//Method to clamp camera to map
	public void clampCamera() {
		
		//Get main layer of map
		MapHandler maphandler = this.mainclass.getMapHandler();
		TiledMapTileLayer mainlayer = (TiledMapTileLayer) maphandler.getLayer(0);
		
		//Max height & width for the camera to be at
		int maxheight = ((mainlayer.getHeight() * 32) - Gdx.graphics.getHeight()) - Gdx.graphics.getHeight() / 2;
		int maxwidth = (mainlayer.getWidth() * 32) - Gdx.graphics.getWidth();
		
		//Min height & width for the camera to be at
		int minheight = Gdx.graphics.getHeight() / 2;
		int minwidth = Gdx.graphics.getWidth() / 2;
		
		//Clamp x: max
		if (this.camera.position.x > maxwidth) {
			this.camera.position.set(maxwidth, this.camera.position.y, 0) ;
		}
		
		//Clamp x: min
		if (this.camera.position.x < minwidth) {
			this.camera.position.set(minwidth, this.camera.position.y, 0) ;
		}
		
		//Clamp y: max
		if (this.camera.position.y > maxheight) {
			this.camera.position.set(this.camera.position.x, maxheight , 0) ;
		}
		
		//Clamp y: min
		if (this.camera.position.y < minheight) {
			this.camera.position.set(this.camera.position.x, minheight, 0) ;
		}
	}
	
	//Method to translate camera
	public void translateCamera(int x, int y) {
		camera.translate(x, y);
	}
}
