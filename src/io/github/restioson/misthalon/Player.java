package io.github.restioson.misthalon;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;

@SuppressWarnings("unused")
public class Player extends Entity {
	
	//Constructor
	public Player(Texture texture, Rectangle rect, Array<Float> coords, Array<Item> inventory, int invmaxsize, Main mainclass) {
		
		//Init superobj
		super(texture, coords, inventory, invmaxsize, true, 8, mainclass);
	}
	
	//Constructor
	public Player(Texture texture, Rectangle rect, Main mainclass) {
		
		//Init superobj
		super(texture, new Array<Item>(), 32*64, true, 3, mainclass);
	}
}
