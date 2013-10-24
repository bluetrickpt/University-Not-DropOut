package com.ruthlessgames.iso;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.input.GestureDetector.GestureListener;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.EventListener;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.ActorGestureListener;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class Tile extends Image{

	int type;
	boolean isActive;
	
	public Tile(int type,boolean isActive) {
		super((Drawable)null);
	
		this.changeDrawable(type);
		this.type = type;
		this.isActive = isActive;
		//update size
		switch(type){
		default:
			this.setSize(2*View.tileWidth, 2*View.tileHeight);
		}
		
	}
	

	public void changeDrawable(int t){
		this.setDrawable(new TextureRegionDrawable(Assets.tiles_rep.get(t)));
		
	}

	public Vector2 getCartPos(){
		return Iso.isoToCart(View.getTileCoordinates(new Vector2(this.getX(),this.getY())));
	}
	
	public Vector2 getIsoPos(){
		return View.getTileCoordinates(new Vector2(this.getX(),this.getY()));
	}
	
	public void select(){
		this.getColor().a = 0.7f;
	}
	
	public void move(){
		this.getColor().r = 0.7f;
		this.getColor().g = 1f;
		this.getColor().b = 1f;
		this.getColor().a = 1f;
	}
	
	public void deselect(){
		this.getColor().r = 1f;
		this.getColor().g = 1f;
		this.getColor().b = 1f;
		this.getColor().a = 1f;
	}
}
