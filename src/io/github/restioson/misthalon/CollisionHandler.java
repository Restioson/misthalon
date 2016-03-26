//Package declaration
package io.github.restioson.misthalon;

//Imports
import com.badlogic.gdx.math.Rectangle;

//Collision handler class
public class CollisionHandler {
	
	//Fields
	private Main mainclass;
	
	//Constructor
	public CollisionHandler(Main mainclass) {
		
		this.mainclass = mainclass;
		
	}
	
	//Method called to handle collisions
	public void handleMapCollisions() {
		
		//Player-map collisions
		for (Rectangle rect : this.mainclass.getMapHandler().getObstacles()) {
			
			//Collision!
			if (this.mainclass.getPlayer().getRect().overlaps(rect)) {
				this.mainclass.getPlayer().stopMoving();
			}
		}
		
		//Entity-map collisions
		for (Entity curentity : this.mainclass.getEntities()) {
			for (Rectangle rect : this.mainclass.getMapHandler().getObstacles()) {
					
					//Collision! (map)
					if (curentity.getRect().overlaps(rect)) {
						curentity.stopMoving();
					}
					
					//Collision! (player)
					if (curentity.getRect().overlaps(this.mainclass.getPlayer().getRect())) {
						this.mainclass.getPlayer().stopMoving();
				}	
			}
		}
	}
}
	
