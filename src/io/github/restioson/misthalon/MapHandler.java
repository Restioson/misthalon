//Package declaration
package io.github.restioson.misthalon;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
//Imports
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;

//Class to handle interactions with map
public class MapHandler {

	//Fields
	private TiledMap tiledMap;
	private OrthogonalTiledMapRenderer tiledMapRenderer;
	private Main mainclass; 
	private MapLayer collisionLayer;
	
	//Constructor
	public MapHandler(Main mainclass, String mapname) {
		
		//Configure tiledmap
		this.tiledMap = new TmxMapLoader().load("lolxd.tmx");
		this.tiledMapRenderer = new OrthogonalTiledMapRenderer(tiledMap);
		this.mainclass = mainclass;
		
		//Get collision layer
	    this.collisionLayer = this.tiledMap.getLayers().get("collision");
		
	}
	
	//Set map
	public void setMap(TiledMap tiledmap) {
		this.tiledMap = tiledmap;
		this.tiledMapRenderer = new OrthogonalTiledMapRenderer(tiledMap);
	}
	
	//Method to get layer of map
	public MapLayer getLayer(int index) {
		return this.tiledMap.getLayers().get(index);
	}
	
	//Set camera view
	public void setView(OrthographicCamera camera) {
		this.tiledMapRenderer.setView(camera);
	}
	
	//Get map
	public TiledMap getMap() {
		return this.tiledMap;
	}
	
	//Get map renderer
	public OrthogonalTiledMapRenderer getTiledMapRenderer() {
		return this.tiledMapRenderer;
	}
	
	//Method to get obstacles
	public Array<Rectangle> getObstacles() {
		
		//Array containing collidable rectangleMapObjects
		Array<Rectangle> collidableObjects = new Array<Rectangle>();
		
		//Loop through map objects to find collidable objects
		for (RectangleMapObject curobj : this.collisionLayer.getObjects().getByType(RectangleMapObject.class)) {
			
			//Add to list of collidable objects
			collidableObjects.add(curobj.getRectangle());
		}
			
		
		//Loop through objects in entities array
		for (Entity curentity : this.mainclass.getEntities()) {
			
			//Check solidity of entity
			if (curentity.getSolidity()) {
				
				//Add to collidable objects array
				collidableObjects.add(curentity.getRect());
			}
		}
		
		//Return
		return collidableObjects;
	}

}
