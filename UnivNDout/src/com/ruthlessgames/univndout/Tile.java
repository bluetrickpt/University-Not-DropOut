package com.ruthlessgames.univndout;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class Tile extends Image{

	int type;
	
	public Tile(int type) {
		super((Drawable)null);
	
		this.changeDrawable(type);
		this.type = type;
		
		//update size
		this.setSize(2*View.tileWidth, 2*View.tileHeight);
		
		
	}
	

	public void changeDrawable(int t){
		this.setDrawable(new TextureRegionDrawable(Assets.tiles_rep.get(t)));

	}

	public Vector2 getCartPos(){
		return Univndout.isoToCart(View.getTileCoordinates(new Vector2(this.getX(),this.getY())));
	}
	
	public Vector2 getIsoPos(){
		return View.getTileCoordinates(new Vector2(this.getX(),this.getY()));
	}
}
