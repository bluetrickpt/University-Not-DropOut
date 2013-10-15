package com.ruthlessgames.univndout;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;

public class View implements Screen,TileClickListener{
	private Stage stage;
	private Table env_container,pl_container;
	
	int env_tiles[][],pl_tiles[][],walk_tiles[][];
	int xlim,ylim;
	static float tileWidth;
	static float tileHeight;
	
	BitmapFont font;
	ShapeRenderer shape_r;
	
	public View(int xlim,int ylim){
		setupStage();
		setupContent(xlim,ylim);
	}
	
	private void setupStage(){
		stage = new Stage();
		env_container = new Table();
		env_container.setFillParent(true);
		pl_container = new Table();
		pl_container.setFillParent(true);
		stage.addActor(env_container);
		stage.addActor(pl_container);

		font = new BitmapFont();
		shape_r = Univndout.shape_r;
	}
	
	private void setupContent(int xlim,int ylim){
		this.xlim = xlim;
		this.ylim = ylim;
		
		//create the tile grid
		env_tiles = new int[xlim][ylim];
		pl_tiles = new int[xlim][ylim];
		walk_tiles = new int[xlim][ylim];
		
		tileWidth = Univndout.w / xlim;
		tileHeight = Univndout.h / ylim;
	}
	
	public void addTile(int type,Vector2 pos,boolean walkable){
		this.addTile(type, (int)(pos.x),(int)(pos.y), walkable);
	}
	
	public void addTile(int type,int x,int y,boolean walkable){
		this.env_tiles[x][y] = type;
		
		if(walkable){
			this.walk_tiles[x][y] = 1;
		}
		else this.walk_tiles[x][y] = 0;
		
		Vector2 isoPos = Univndout.cartToIso(new Vector2(x,y));
		Tile test = new Tile(type,this);
		test.setPosition(isoPos.x * tileWidth,isoPos.y *  tileHeight);
		env_container.addActor(test);
		
		LabelStyle lblstyle = new LabelStyle();
		lblstyle.font = font;
		
		Label lbl = new Label("("+x+";"+y+")",lblstyle);
		lbl.setPosition(isoPos.x * tileWidth,isoPos.y *  tileHeight);
		env_container.addActor(lbl);
	}
	
	@Override
	public void render(float delta) {
		// TODO Auto-generated method stub
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT); 
		//update stage
		stage.act(delta);
		stage.draw();
		
		shape_r.begin(ShapeType.Line);
		this.debugIsoView();
		shape_r.end();
		this.debugPos();
	}

	private void debugPos(){
		//debug matrix pos
		Vector2 cartPos = Univndout.isoToCart(View.getTileCoordinates(new Vector2(Gdx.input.getX(),Univndout.h - Gdx.input.getY())));
		System.out.println("(" + (int)cartPos.x + ";" + (int)cartPos.y + ")");
	}
	
	private void debugIsoView(){
		for(int i=0;i<ylim;i++){
			//env_tiles[i][0] -> env_tiles[i][xlim-1];
			Vector2 from = Univndout.cartToIso(new Vector2(i,0));
			Vector2 to = Univndout.cartToIso(new Vector2(i,xlim-1));

			shape_r.line(from.x * tileWidth, from.y * tileHeight, to.x * tileWidth, to.y * tileHeight);
		}
		
		for(int i=0;i<xlim;i++){
			//env_tiles[0][i] -> env_tiles[ylim -1,i];
			Vector2 from = Univndout.cartToIso(new Vector2(0,i));
			Vector2 to = Univndout.cartToIso(new Vector2(ylim-1,i));

			shape_r.line(from.x * tileWidth, from.y * tileHeight, to.x * tileWidth, to.y * tileHeight);
		}
	}
	
	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void show() {
		// TODO Auto-generated method stub
		Gdx.input.setInputProcessor(stage);
	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub
		Gdx.input.setInputProcessor(null);
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}
	
	public static Vector2 getTileCoordinates(Vector2 isoPos){
		float x = (float) (isoPos.x / tileWidth);
		float y = (float) (isoPos.y / tileHeight);
		return new Vector2(x,y);
	}

	@Override
	public void tileClickDown(int x, int y, Tile tile) {
		// TODO Auto-generated method stub
		//Gdx.app.log("DOWN", tile.type + "");
	}

	@Override
	public void tileEnter(int x, int y, Tile tile) {
		// TODO Auto-generated method stub
		//Gdx.app.log("Enter", tile.type + "");
	}

	@Override
	public void tileClickUp(int x, int y, Tile tile) {
		// TODO Auto-generated method stub
		//Gdx.app.log("UP", tile.type + "");
	}

	@Override
	public void tileExit(int x, int y, Tile tile) {
		// TODO Auto-generated method stub
		//Gdx.app.log("Exit", tile.type + "");
	}

}
