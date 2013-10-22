package com.ruthlessgames.iso;

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
		for(int i=0;i<8;i++){
			if(Gdx.files.internal(i+".png").exists()){
				tiles_rep.put( i, new Sprite(new Texture(Gdx.files.internal(i+".png"))));
			}
		}
		
	}
}
