//MAJOR TODO MAKE INVENTORY 3D ARRAY!

//Package declaration
package io.github.restioson.misthalon;

//Imports
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Pool.Poolable;

import java.math.BigInteger;

//Most basic entity class
public class Entity implements Poolable{
	
	//Fields
	private Rectangle rect;
	private Array<Item> inv;
	private Sprite sprite;
	private int invmaxsize;
	private Array<Float> lastpos;
	private boolean solid;
	private Main mainclass;
	private BigInteger lastMovedTick;
	
	//Default constructor
	public Entity() {
	}
	
	//Constructor
	public Entity(Texture texture, Array<Float> coords, Array<Item> inventory, int invsize, Main mainclass) {
		
		//Transfer argument values to fields
		this.rect = new Rectangle();
		this.inv = inventory;
		this.sprite = new Sprite(texture);
		this.invmaxsize = invsize;
		this.lastpos = new Array<Float>();
		this.lastpos.add(coords.get(0));
		this.lastpos.add(coords.get(1));
		this.mainclass = mainclass;
		this.lastMovedTick = this.mainclass.getTickCount();
		
		
		//Set dimensions of rect to fit that of texture
		this.rect.setWidth(texture.getWidth());
		this.rect.setHeight(texture.getHeight());
		
		//Set coords of rect
		this.rect.setX(coords.get(0));
		this.rect.setY(coords.get(1));
		
		//Set solidity
		this.solid = false;
		
	}

	//Constructor
	public Entity(Texture texture, Array<Float> coords, Array<Item> inventory, int invsize, boolean solid, Main mainclass) {
		
		//Transfer argument values to fields
		this.rect = new Rectangle();
		this.inv = inventory;
		this.sprite = new Sprite(texture);
		this.invmaxsize = invsize;
		this.lastpos = new Array<Float>();
		this.lastpos.add(coords.get(0));
		this.lastpos.add(coords.get(1));
		
		
		//Set dimensions of rect to fit that of texture
		this.rect.setWidth(texture.getWidth());
		this.rect.setHeight(texture.getHeight());
		
		//Set coords of rect
		this.rect.setX(coords.get(0));
		this.rect.setY(coords.get(1));
		
		//Set solidity
		this.solid = solid;
		
	}
	
	
	//Constructor
	public Entity(Texture texture, Array<Item> inventory, int invsize, boolean solid, Main mainclass) {
		
		//Transfer argument values to fields
		this.rect = new Rectangle();
		this.inv = inventory;
		this.sprite = new Sprite(texture);
		this.invmaxsize = invsize;
		
		//Set dimensions of rect to fit that of texture
		this.rect.setWidth(texture.getWidth());
		this.rect.setHeight(texture.getHeight());
		
		//Default lastpos
		this.lastpos = new Array<Float>();
		this.lastpos.add(0f);
		this.lastpos.add(0f);
		
		//Set coords of rect
		this.rect.setX(0);
		this.rect.setY(0);
		
		//Set solidity
		this.solid = solid;
	}
	
	//Constructor
	public Entity(Texture texture, Array<Item> inventory, int invsize, Main mainclass) {
		
		//Transfer argument values to fields
		this.rect = new Rectangle();
		this.inv = inventory;
		this.sprite = new Sprite(texture);
		this.invmaxsize = invsize;
		
		//Set dimensions of rect to fit that of texture
		this.rect.setWidth(texture.getWidth());
		this.rect.setHeight(texture.getHeight());
		
		//Set coords of rect
		this.rect.setX(0);
		this.rect.setY(0);
	}
	
	//Translate rect & sprite -- manual movement
	public void translate(Float x, Float y) {
		this.lastpos.clear();
		this.lastpos.add(this.rect.getX());
		this.lastpos.add(this.rect.getY());
		this.rect.setX(this.rect.getX() + x);
		this.rect.setY(this.rect.getY() + y);
		this.sprite.translate(x, y);
	}
	
	//Method to set X
	public void setX(float x) {
		this.rect.setX(x);
		this.sprite.setX(x);
	}
	
	//Method to set Y
	public void setY(float y) {
		this.rect.setY(y);
		this.sprite.setY(y);
	}
	
	//Method to get X
	public float getX() {
		return this.getRect().getX();
	}
	
	//Method to get Y
	public float getY() {
		return this.getRect().getY();
	}
	
	//Actions to perform every tick
	public void tick() {
		this.lastMovedTick = this.mainclass.getTickCount();
	}
	
	//Actions to perform when interacted with
	public void interact() {
	}
	
	//Method to get sprite
	public Sprite getSprite() {
		return this.sprite;
	}
	
	//Method to get texture
	public Texture getTexture() {
		return this.getSprite().getTexture();
	}
	
	//Method to get inv
	public Array<Item> getInv() {
		return this.inv;
	}
	
	//Method to get rect
	public Rectangle getRect(){
		return this.rect;
	}
	
	//Method to add items to inv
	public boolean addToInv(Item item, int amount) {
		
		//Check if inv is full
		if (this.invmaxsize <= this.inv.size) {
			//For loop to add desired amount of item type
			for (int i = 0; i < amount; i++) {
				this.getInv().add(item);
			}
			
			//Return
			return true;
		}
		
		//Inv is full
		else {
			//Return
			return false;
		}
		
	}
	
	//Method to remove items from inv
	public int remFromInv(Item item, int amount) {
		
		//Loop vars
		boolean changedinv = false;
		
		if (this.getInv().size == 0) {
		
			//For loop to add desired amount of item type
			for (int i = 0; i < amount; i++) {
				
				//Test if any items of type in inv
				for (int i2 = 0; i2 < this.getInv().size; i2++) {
					
					//Check if model item type is same as item in current iteration type
					if (this.getInv().get(i2).getId() == item.getId())
					
					//Check if model item is same as item in current iteration
					if (this.getInv().get(i2).equals(item)) {
						
						//Remove item and set changedinv to true
						this.inv.removeIndex(i2);
						changedinv = true;
					}
				}
			}
			
			//No items of type found, return code 3
			if (changedinv == false) {
				return 3;
			}
			
			//Items were found and removed, return code 1
			else {
				return 1;
			}		
		}
		
		//Nothing in inventory, return code 2
		else {
			return 2;
		}
	}
	
	//Method to remove an item from inventory by index
	public void remFromInvIndex(int index) {
		this.inv.removeIndex(index);
	}
	
	//Poolable req method
	public void reset() {
		
	}
	
	
	//Method to get item in inv by slot index
	public Item getItemIndex(int index) { 
		return this.getInv().get(index);
	}
	
	//Method to get item in inv by slot index and remove it
	public Item popItemIndex(int index) {
		
		//Get item
		Item itemtopop = this.getInv().get(index);
		this.remFromInvIndex(index);
		return itemtopop;
	}
	
	//Method to call when colliding with another entity
	public void overlaps(Entity other) {
	}
	
	//Method to get solidity
	public boolean getSolidity() {
		return this.solid;
	}
	
	//Method to get last position
	public Array<Float> getLastpos() {
		return this.lastpos;
	}
	
	//Method to get when last moved
	public BigInteger getLastMoved() {
		return this.lastMovedTick;
	}	
	
	//Method to go to last pos
	public void goToLastPos() {
		//Move rect
		this.rect.x = this.lastpos.get(0);
		this.rect.y = this.lastpos.get(1);
		
		//Move sprite
		this.sprite.setX(this.lastpos.get(0));
		this.sprite.setY(this.lastpos.get(1));
		
	}
	
	
	//Dispose
	public void dispose() {
	}
}
