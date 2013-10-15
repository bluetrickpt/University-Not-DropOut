package com.ruthlessgames.univndout;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class Tile extends ImageButton{

	TileClickListener mListener;
	int type;
	
	public Tile(int type, TileClickListener listner) {
		super((Drawable)null);
		mListener = listner;
		this.createListener();
		this.changeDrawable(type);
		this.type = type;
		
		//update size
		this.setSize(View.tileWidth, View.tileHeight);
	}
	
	private void createListener(){
		this.addListener(new InputListener() {
	        public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
	        		Vector2 cartPos = Univndout.isoToCart(View.getTileCoordinates(new Vector2(x,y)));
	               // System.out.println("down at " + cartPos.x + "i + " + cartPos.y + "j");
	                
	                //chamar metodo no listener
	                mListener.tileClickDown((int)cartPos.x, (int)cartPos.y, Tile.this);
	                return true;
	        }
	        
	        public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
	        	Vector2 cartPos = Univndout.isoToCart(View.getTileCoordinates(new Vector2(x,y)));
                //System.out.println("up at " + cartPos.x + "i + " + cartPos.y + "j");
	               
              //chamar metodo no listener
                mListener.tileClickUp((int)cartPos.x, (int)cartPos.y, Tile.this);
	        }
	        
	        
	        public void enter (InputEvent event, float x, float y, int pointer, Actor fromActor) {
	        	mListener.tileEnter((int)x, (int)y, Tile.this);
	    	}
	        
	        public void exit (InputEvent event, float x, float y, int pointer, Actor fromActor) {
	        	mListener.tileExit((int)x, (int)y, Tile.this);
	    	}
			});
	}

	public void changeDrawable(int t){
		this.getStyle().imageUp = new TextureRegionDrawable(Assets.tiles_rep.get(t));

	}

	public Vector2 getCartPos(){
		return Univndout.isoToCart(View.getTileCoordinates(new Vector2(this.getX(),this.getY())));
	}
	
	public Vector2 getIsoPos(){
		return View.getTileCoordinates(new Vector2(this.getX(),this.getY()));
	}
}
