package com.ruthlessgames.univndout;

import java.io.IOException;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.XmlReader;
import com.badlogic.gdx.utils.XmlReader.Element;

public class Inflater {
	static XmlReader xr;
	static int ylim;
	static int xlim;
	

	public static int[][] inflateMatrix(int x_bound,int y_bound){
		xr = new XmlReader();
		xlim = x_bound;
		ylim = y_bound;
		int matrix[][] = new int[xlim][ylim];
		
		try {
			 int i = 0;
			 int j = 0;
			
			Element rootEl = xr.parse(Gdx.files.internal("matrix.xml"));
			
			for( i=0;i<ylim;i++){
				Element curRow = rootEl.getChildByName("line" + i);
				
				for( j=0;j<xlim;j++){
					Element curTile = curRow.getChildByName("col" + j);
					matrix[j][i] = curTile.getInt("tile_type");
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return matrix;
	}
	
	public static boolean hasLocalMatrix(){
		return Gdx.files.internal("matrix.xml").exists();
	}
}
