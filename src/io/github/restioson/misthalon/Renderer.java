package io.github.restioson.misthalon;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
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
	
	//Method to translate camera
	public void translateCamera(int x, int y) {
		camera.translate(x, y);
	}
}
