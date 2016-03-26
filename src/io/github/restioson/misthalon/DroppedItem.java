//Package declaration
package io.github.restioson.misthalon;

//Imports
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;

//DroppedItem class
public class DroppedItem extends Entity {
	
	//Fields
	private Item item;
	
	//Constructor
	public DroppedItem(Texture texture, Rectangle rect, Array<Float> coords, Array<Item> inventory, Item item, Main mainclass) {
		
		//Init superobj
		super(texture, coords, inventory, 1, 0, mainclass);
		
		//Transfer argument values to fields
		this.item = item;
		
		//Add item to inv
		this.addToInv(item, 1);
	}
	
	//Actions to perform when interacted with
	public void interact(Entity interactor) {
		interactor.addToInv(this.popItemIndex(1), 1);
	} 
	
	//Method to dispose
	@Override
	public void dispose() {
		
		//Dispose of super-object
		super.dispose();
		
		//Dispose of item
		this.item.dispose();
	}
}
