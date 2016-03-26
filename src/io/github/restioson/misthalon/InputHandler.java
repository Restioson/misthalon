package io.github.restioson.misthalon;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.utils.Array;

public class InputHandler implements InputProcessor {
	
	//Fields
	private Main mainclass;
	private Array<Integer> keysdown;
	 
	//Constructor
	public InputHandler(Main mainclass) {
		this.mainclass = mainclass;
		this.keysdown = new Array<Integer>();
	}
	
	//Keyboard controls
	@Override
	public boolean keyDown(int keycode) {
		//Add key to array if doesnt
		this.keysdown.add(keycode);
		
		//Return
		return false;
	}

	@Override
	public boolean keyUp(int keycode) {
			
		//Remove key from array
		this.keysdown.removeValue(keycode, false);
		
		//Return
		return false;
	}
	
	//Method to process input
	public void processInput() {
		
		//Check if currently is moving
		if (!this.mainclass.getPlayer().getMovingDirection().equalsIgnoreCase("none")) {
			
			//Switch movingdir
			switch (this.mainclass.getPlayer().getMovingDirection()) {
				
				//Up
				case "up":
					
					//W released
					if (!this.keysdown.contains(Input.Keys.W, false)) {
						
						//Stop moving
						this.mainclass.getPlayer().stopMoving();
						
					}
					
					//A pressed
					if (this.keysdown.contains(Input.Keys.A, false)) {
						
						//Change movingdir
						this.mainclass.getPlayer().startMoving("up-left");
						
					}
					
					//D pressed
					if (this.keysdown.contains(Input.Keys.D, false)) {
						
						//Change movingdir
						this.mainclass.getPlayer().startMoving("up-right");
						
					}
					
					break;
					
				case "down":
					
					//S released
					if (!this.keysdown.contains(Input.Keys.S, false)) {
						
						//Stop moving
						this.mainclass.getPlayer().stopMoving();
						
					}
					
					//A pressed
					if (this.keysdown.contains(Input.Keys.A, false)) {
						
						//Change movingdir
						this.mainclass.getPlayer().startMoving("down-left");
						
					}
					
					//D pressed
					if (this.keysdown.contains(Input.Keys.D, false)) {
						
						//Change movingdir
						this.mainclass.getPlayer().startMoving("down-right");
						
					}
					
					break;
					
				case "left":
					
					//A released
					if (!this.keysdown.contains(Input.Keys.A, false)) {
						
						//Stop moving
						this.mainclass.getPlayer().stopMoving();
						
					}
					
					//W pressed
					if (this.keysdown.contains(Input.Keys.W, false)) {
						
						//Change movingdir
						this.mainclass.getPlayer().startMoving("up-left");
						
					}
					
					//S pressed
					if (this.keysdown.contains(Input.Keys.S, false)) {
						
						//Change movingdir
						this.mainclass.getPlayer().startMoving("down-left");
						
					}
					
					break;
				
				case "right":
					
					//D released
					if (!this.keysdown.contains(Input.Keys.D, false)) {
						
						//Stop moving
						this.mainclass.getPlayer().stopMoving();
						
					}
					
					//W pressed
					if (this.keysdown.contains(Input.Keys.W, false)) {
						
						//Change movingdir
						this.mainclass.getPlayer().startMoving("up-right");
						
					}
					
					//S pressed
					if (this.keysdown.contains(Input.Keys.S, false)) {
						
						//Change movingdir
						this.mainclass.getPlayer().startMoving("down-right");
						
					}
					
					break;
				
				case "up-left":
					
					//W released
					if (!this.keysdown.contains(Input.Keys.W, false)) {
						
						//Change moving dir
						this.mainclass.getPlayer().startMoving("left");
						
					}
					
					
					
					//A released
					if (!this.keysdown.contains(Input.Keys.A, false)) {
						
						//Change moving dir
						this.mainclass.getPlayer().startMoving("up");
	
						
					}
					
					//BOTH released
					if ((!this.keysdown.contains(Input.Keys.A, false)) && (!this.keysdown.contains(Input.Keys.W, false))) {
						
						//Stop moving
						this.mainclass.getPlayer().stopMoving();
						
					}
					
					break;
					
				case "up-right": //Get up stand up, stand UP for your RIGHTS!
								 //Nevermind, I'll let myself out...
					
					//W released
					if (!this.keysdown.contains(Input.Keys.W, false)) {
						
						//Change moving dir
						this.mainclass.getPlayer().startMoving("right");
						
					}
					
					//D released
					if (!this.keysdown.contains(Input.Keys.D, false)) {
						
						//Change moving dir
						this.mainclass.getPlayer().startMoving("up");
						
					}
					
					//BOTH released
					if ( (!this.keysdown.contains(Input.Keys.W, false)) && (!this.keysdown.contains(Input.Keys.D, false))) {
						
						//Stop moving
						this.mainclass.getPlayer().stopMoving();
						
					}
				
				case "down-left":
					
					//S released
					if (!this.keysdown.contains(Input.Keys.S, false)) {
						
						//Change moving dir
						this.mainclass.getPlayer().startMoving("left");
						
					}
					
					//A released
					if (!this.keysdown.contains(Input.Keys.A, false)) {
						
						//Change moving dir
						this.mainclass.getPlayer().startMoving("up");
						
					}
					
					//BOTH released
					if ((!this.keysdown.contains(Input.Keys.S, false)) && (!this.keysdown.contains(Input.Keys.A, false))) {
						
						//Stop moving
						this.mainclass.getPlayer().stopMoving();
						
					}
					
					break;
					
				case "down-right" :
					
					//S released
					if (!this.keysdown.contains(Input.Keys.S, false)) {
						
						//Change moving dir
						this.mainclass.getPlayer().startMoving("right");
						
					}
					
					//D released
					if (!this.keysdown.contains(Input.Keys.D, false)) {
						
						//Change moving dir
						this.mainclass.getPlayer().startMoving("down");
						
					}
					
					//BOTH released
					if ((!this.keysdown.contains(Input.Keys.S, false)) && (!this.keysdown.contains(Input.Keys.D, false))) {
						
						//Stop moving
						this.mainclass.getPlayer().stopMoving();
						
					}
					
					break;
					
			}
			
			
		}
		
		//Isn't moving
		else {
		
			//Check for up-left diagonal
			if (this.keysdown.contains(Input.Keys.W, false) && this.keysdown.contains(Input.Keys.A, false)) {
				
				//Start moving
				this.mainclass.getPlayer().startMoving("up-left");
				
			}
			
			//Check for up-right diagonal
			if (this.keysdown.contains(Input.Keys.W, false) && this.keysdown.contains(Input.Keys.A, false)) {
				
				//Start moving
				this.mainclass.getPlayer().startMoving("up-right");
				
			}
			
			//Check for down-left diagonal
			if (this.keysdown.contains(Input.Keys.S, false) && this.keysdown.contains(Input.Keys.D, false)) {
				
				//Start moving
				this.mainclass.getPlayer().startMoving("down-left");
				
			}
			
			//Check for down-right diagonal
			if (this.keysdown.contains(Input.Keys.S, false) && this.keysdown.contains(Input.Keys.D, false)) {
			
				//Start moving
				this.mainclass.getPlayer().startMoving("down-right");
				
			}
			
			//Check for up
			if (this.keysdown.contains(Input.Keys.W, false)) {
				
				//Start moving
				this.mainclass.getPlayer().startMoving("up");
				
			}
			
			//Check for down
			if (this.keysdown.contains(Input.Keys.S, false)) {
				
				//Start moving
				this.mainclass.getPlayer().startMoving("down");
				
			}
			
			//Check for left
			if (this.keysdown.contains(Input.Keys.A, false)) {
				
				//Start moving
				this.mainclass.getPlayer().startMoving("left");
				
			}
			
			//Check for right
			if (this.keysdown.contains(Input.Keys.D, false)){
				
				//Start moving
				this.mainclass.getPlayer().startMoving("right");
				
			}
			
			
		}
	}

	@Override
	public boolean keyTyped(char character) {
		return false;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		return false;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		return false;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		return false;
	}

	@Override
	public boolean scrolled(int amount) {
		return false;
	}

}
