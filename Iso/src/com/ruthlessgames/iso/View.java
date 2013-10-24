package com.ruthlessgames.iso;

import java.util.HashMap;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Application.ApplicationType;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.input.GestureDetector.GestureListener;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.EventListener;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ActorGestureListener;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;

public class View implements Screen{
	private Stage stage;
	private Table env_container;
	private CameraController controller;
	private GestureDetector gestureDetector;
	
	int env_tiles[][];
	int xlim,ylim;
	static float tileWidth;
	static float tileHeight;
	
	BitmapFont font;
	ShapeRenderer shape_r;
	
	Tile selected = null;
	Tile moveable = null;
	HashMap<String,Tile> hitHash;
	
	public View(int xlim,int ylim){
		setupStage();
		setupContent(xlim,ylim);
	}
	
	private void setupStage(){
		stage = new Stage();
		
		env_container = new Table();
		env_container.setFillParent(true);
		
		stage.addActor(env_container);
		controller= new CameraController();
		gestureDetector = new GestureDetector(10, 0.5f, 0.5f, 0.15f, controller);
		
		font = new BitmapFont();
		shape_r = Iso.shape_r;
		
		hitHash = new HashMap<String,Tile>();
	}
	
	private void setupContent(int xlim,int ylim){
		this.xlim = xlim;
		this.ylim = ylim;
		
		//create the tile grid
		env_tiles = new int[xlim][ylim];
		
		tileWidth = Iso.w / xlim;
		tileHeight = Iso.h / ylim;
		
		this.addGround();
		
		if(Inflater.hasLocalMatrix()){
			Gdx.app.log("Inflater", "Inflated");
			env_tiles = Inflater.inflateMatrix(xlim, ylim);
			
			for(int i = 0;i<ylim;i++){
				for(int j = 0;j<xlim;j++){
					if(env_tiles[i][j] != 0){
						addTile(env_tiles[i][j],j,i,true);
					}
				}
			}
		}
		else
			Gdx.app.log("Inflater", "Not inflated");
	}
	
	public void addTile(int type,Vector2 pos,boolean isActive){
		this.addTile(type, (int)(pos.x),(int)(pos.y),isActive);
	}
	
	public void addTile(int type,int x,int y,boolean isActive){
		this.env_tiles[x][y] = type;
		
		Vector2 isoPos = Iso.cartToIso(new Vector2(x-0.5f,y+0.5f));
		Tile newTile = new Tile(type,isActive);
		
		newTile.setPosition((isoPos.x)* tileWidth,(isoPos.y)*  tileHeight);
		
		env_container.addActor(newTile);
		hitHash.put(getKey(x,y), newTile);
	}
	
	@Override
	public void render(float delta) {
		// TODO Auto-generated method stub
		Gdx.gl.glClearColor(0.5f, 0.6f, 0, 1);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT); 
		
		//update camera and its controller
		((OrthographicCamera)this.stage.getCamera()).update();
		controller.update();
		
		//this.debugIsoView();
		
		//update stage
		stage.act(delta);
		stage.draw();
		
	}
	
	private void debugIsoView(){
		shape_r.setProjectionMatrix(stage.getSpriteBatch().getProjectionMatrix());
		shape_r.begin(ShapeType.Line);
		  for(int i=0;i<ylim;i++){
		   //env_tiles[i][0] -> env_tiles[i][xlim-1];
		   Vector2 from = Iso.cartToIso(new Vector2(i,0));
		   Vector2 to = Iso.cartToIso(new Vector2(i,xlim-1));

		   shape_r.line(from.x * tileWidth, from.y * tileHeight, to.x * tileWidth, to.y * tileHeight);
		  }
		  
		  for(int i=0;i<xlim;i++){
		   //env_tiles[0][i] -> env_tiles[ylim -1,i];
		   Vector2 from = Iso.cartToIso(new Vector2(0,i));
		   Vector2 to = Iso.cartToIso(new Vector2(ylim-1,i));

		   shape_r.line(from.x * tileWidth, from.y * tileHeight, to.x * tileWidth, to.y * tileHeight);
		  }
		  
		  shape_r.end();
		 }
	
	private void addGround(){
		for(int i = 0;i<ylim;i++){
			for(int j = 0;j<xlim;j++){
					addTile(7,i,j,false);
			}
		}
	}
	
	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void show() {
		// TODO Auto-generated method stub
		InputMultiplexer input_p = new InputMultiplexer();
		input_p.addProcessor(gestureDetector);
		input_p.addProcessor(stage);
		
		Gdx.input.setInputProcessor(input_p);
		
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
		float x = (isoPos.x / tileWidth);
		float y = (isoPos.y / tileHeight);
		return new Vector2(x,y);
	}
	
	private String getKey(int x,int y){
		return x+";"+y;
	}
	
	private void hitSelectTile(int x,int y){
		Tile act = hitHash.get(getKey(x,y));
		if(act != null && act.isActive){
			if (moveable == null) {
				if (selected != null)
					selected.deselect();
				selected = act;
				selected.select();
			}
			
		}
		else{
			if(moveable != null && !act.isActive){
			moveable.setPosition(act.getX(), act.getY());
			moveable.deselect();
			hitHash.remove(moveable);
			hitHash.put(getKey(x,y), moveable);
			moveable = null;
			}
		}
	}

	private void hitMoveableTile(int x,int y){
		Tile act = hitHash.get(getKey(x,y));
		if(act != null){
			if(moveable == null){
				if(selected != null)
				selected.deselect();
				
				selected = null;
				moveable = act;
				act.move();
			}
		}
	}
	
	class CameraController implements GestureListener {
		float velX, velY;
		boolean flinging = false;
		float initialScale = 1;
		OrthographicCamera camera = (OrthographicCamera)stage.getCamera();
		
		public boolean touchDown (float x, float y, int pointer, int button) {
			flinging = false;
			initialScale = camera.zoom;
			
			return false;
		}

		@Override
		public boolean tap (float x, float y, int count, int button) {
			 Vector3 worldCoordinates = new Vector3(x, y, 0);
			 ((OrthographicCamera)stage.getCamera()).unproject(worldCoordinates);
			Vector2 cartPos = Iso.isoToCart(View.getTileCoordinates(new Vector2(worldCoordinates.x,worldCoordinates.y)));
			View.this.hitSelectTile((int)cartPos.x, (int)cartPos.y);
			return false;
		}

		@Override
		public boolean longPress (float x, float y) {
			 Vector3 worldCoordinates = new Vector3(x, y, 0);
			 ((OrthographicCamera)stage.getCamera()).unproject(worldCoordinates);
			Vector2 cartPos = Iso.isoToCart(View.getTileCoordinates(new Vector2(worldCoordinates.x,worldCoordinates.y)));
			View.this.hitMoveableTile((int)cartPos.x, (int)cartPos.y);
			return false;
		}

		@Override
		public boolean fling (float velocityX, float velocityY, int button) {
			Gdx.app.log("GestureDetectorTest", "fling " + velocityX + ", " + velocityY);
			flinging = true;
			velX = camera.zoom * velocityX * 0.5f;
			velY = camera.zoom * velocityY * 0.5f;
			return false;
		}

		@Override
		public boolean pan (float x, float y, float deltaX, float deltaY) {
			// Gdx.app.log("GestureDetectorTest", "pan at " + x + ", " + y);
			camera.position.add(-deltaX * camera.zoom, deltaY * camera.zoom, 0);
			return false;
		}

		@Override
		public boolean zoom (float originalDistance, float currentDistance) {
			float ratio = originalDistance / currentDistance;
			camera.zoom = initialScale * ratio;
			if(camera.zoom > 2.5) camera.zoom = 2.5f;
			else if(camera.zoom < 0.4f) camera.zoom = 0.4f;
			System.out.println(camera.zoom);
			return false;
		}

		@Override
		public boolean pinch (Vector2 initialFirstPointer, Vector2 initialSecondPointer, Vector2 firstPointer, Vector2 secondPointer) {
			return false;
		}

		public void update () {
			if (flinging) {
				velX *= 0.98f;
				velY *= 0.98f;
				camera.position.add(-velX * Gdx.graphics.getDeltaTime(), velY * Gdx.graphics.getDeltaTime(), 0);
				if (Math.abs(velX) < 0.01f) velX = 0;
				if (Math.abs(velY) < 0.01f) velY = 0;
			}
		}
	}
}
