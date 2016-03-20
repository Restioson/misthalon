//Package declaration
package io.github.restioson.misthalon;

//Imports
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

//Item
public class Item {
	
	//Fields
	private Texture texture;
	private String itemtype;
	private Sprite sprite;
	private int itemid;
	
	//Default constructor
	public Item() {
		this.itemid = 0;
	}
	
	//Constructor
	public Item(Texture texture, String itemtype) {
		
		//Transfer argument values to fields
		this.texture = texture;
		this.itemtype = itemtype;
		this.sprite = new Sprite(texture);
	}
	
	//Method to get item type
	public String getType() {
		return this.itemtype;
	}
	
	//Method to get sprite
	public Sprite getSprite() {
		return this.sprite;
	}
	
	//Method to get itemid
	public int getId() {
		return this.itemid;
	}

	//Method to dispose
	public void dispose() {
		this.texture.dispose();
	}
	
}
