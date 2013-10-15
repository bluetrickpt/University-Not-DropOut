package com.ruthlessgames.univndout;

import com.badlogic.gdx.scenes.scene2d.Event;
import com.badlogic.gdx.scenes.scene2d.EventListener;

public interface TileClickListener  {

	public void tileClickDown(int x,int y, Tile tile);
	public void tileEnter(int x,int y, Tile tile);
	public void tileExit(int x,int y, Tile tile);
	public void tileClickUp(int x,int y, Tile tile);
}
