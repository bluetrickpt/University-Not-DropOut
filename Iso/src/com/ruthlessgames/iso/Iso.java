package com.ruthlessgames.iso;


import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;

public class Iso extends Game {
	public static OrthographicCamera camera;
	public static ShapeRenderer shape_r;
	View testView;
	public static float w,h;
	
	@Override
	public void create() {		
		w = Gdx.graphics.getWidth();
		h = Gdx.graphics.getHeight();
		
		camera = new OrthographicCamera(1, h/w);

		shape_r = new ShapeRenderer();
		
		new Assets();//generate assets
		testView = new View(10,10);
		setScreen(testView);
	}

	@Override
	public void dispose() {
	
	}


	@Override
	public void resize(int width, int height) {
	}

	@Override
	public void pause() {
	}

	@Override
	public void resume() {
	}
	
	//handle conversions
	public static Vector2 cartToIso(Vector2 cart){
		float isoX = cart.x - cart.y;
		float isoY = (cart.x + cart.y) / 2;
		
		return new Vector2(isoX,isoY);
	}
	
	public static Vector2 isoToCart(Vector2 iso){
		float cartX = (2 * iso.y + iso.x) / 2;
		float cartY = (2 * iso.y - iso.x) / 2;
		
		return new Vector2(cartX,cartY);
	}
}

