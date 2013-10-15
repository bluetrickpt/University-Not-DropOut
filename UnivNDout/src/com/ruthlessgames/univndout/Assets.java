package com.ruthlessgames.univndout;

import java.util.HashMap;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Assets {
	public static HashMap<Integer,Sprite> tiles_rep;
	
	public Assets(){
		tiles_rep = new HashMap<Integer,Sprite>();
		this.loadTiles();
	}
	
	private void loadTiles(){
		Sprite tile1 = new Sprite(new Texture(Gdx.files.internal("data/t1.png")));
		Sprite tile2 = new Sprite(new Texture(Gdx.files.internal("data/t2.png")));
		
		tiles_rep.put( 1, tile1);
		tiles_rep.put( 2, tile2);
	}
}
