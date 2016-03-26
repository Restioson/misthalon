package io.github.restioson.misthalon;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.utils.Array;

public class MapClampedEntity extends Entity {
	
	//Fields
	private Main mainclass;
	
	//Default constructor
	public MapClampedEntity() {
	}
	
	//Constructor
	public MapClampedEntity(Texture texture, Array<Float> coords, Array<Item> inventory, int invsize, int speed, Main mainclass) {
		
		super(texture, coords, inventory, invsize, speed, mainclass);
		this.mainclass = mainclass;
		
	}
	
	//Constructor
	public MapClampedEntity(Texture texture, Array<Float> coords, Array<Item> inventory, int invsize, boolean solid, int speed, Main mainclass) {
		
		super(texture, coords, inventory, invsize, solid, speed, mainclass);
		this.mainclass = mainclass;
		
	}
	
	
	//Constructor
	public MapClampedEntity(Texture texture, Array<Item> inventory, int invsize, boolean solid, int speed, Main mainclass) {
		
		super(texture, inventory, invsize, solid, speed, mainclass);
		this.mainclass = mainclass;
		
	}
	
	//Constructor
	public MapClampedEntity(Texture texture, Array<Item> inventory, int invsize, int speed, Main mainclass) {
		
		super(texture, inventory, invsize, speed, mainclass);
		this.mainclass = mainclass;
		
	}
	
	//Method to prevent entity leaving map
	public void clampMap() {
		
		//Get main layer of map
		MapHandler maphandler = this.mainclass.getMapHandler();
		TiledMapTileLayer mainlayer = (TiledMapTileLayer) maphandler.getLayer(0);
		
		//Max height & width
		int maxheight = mainlayer.getHeight() * 32;
		int maxwidth = mainlayer.getWidth() * 32;
		
		//Clamp X: max
		if (this.getX() > maxwidth) {
			this.setX(maxwidth);
		}
		
		//Clamp X: min
		if (this.getX() < 0) {
			this.setX(0);
		}
		
		//Clamp Y: max
		if (this.getY() > maxheight) {
			this.setY(maxheight);
		}
		
		//Clamp Y: min
		if (this.getY() < 0) {
			this.setY(0);
		}
 		
	}
	
}
