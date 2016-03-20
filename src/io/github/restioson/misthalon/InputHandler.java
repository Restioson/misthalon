package io.github.restioson.misthalon;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;

public class InputHandler implements InputProcessor {
	
	//Fields
	private Main mainclass;
	
	//Constructor
	public InputHandler(Main mainclass) {
		this.mainclass = mainclass;
	}
	

	//Keyboard controls
	@Override
	public boolean keyDown(int keycode) {
		if(keycode == Input.Keys.LEFT)
			this.mainclass.translatePlayer(-32, 0);
        if(keycode == Input.Keys.RIGHT)
        	this.mainclass.translatePlayer(32, 0);
        if(keycode == Input.Keys.UP)
        	this.mainclass.translatePlayer(0, 32);
        if(keycode == Input.Keys.DOWN)
        	this.mainclass.translatePlayer(0, -32);
        return false;
	}

	@Override
	public boolean keyUp(int keycode) {
		return false;
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
