package com.ruthlessgames.univndout;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Event;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;

public class TileInputListener extends InputListener{

	int type;
	
	public TileInputListener(int tile_type){
		type = tile_type;
	}
	
	@Override
	public void enter (InputEvent event, float x, float y, int pointer, Actor fromActor) {
	}

	
	@Override
	public void exit (InputEvent event, float x, float y, int pointer, Actor toActor) {
	}
	
	
	@Override
	public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
		return false;
	}

	
	@Override
	public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
	}
}
