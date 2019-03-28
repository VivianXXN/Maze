package com.maze.core.beans;

import com.maze.core.GameObject;
import com.maze.core.Utils;
import com.maze.core.operation.MapOperation;
import com.maze.core.operation.impl.MapOperationImpl;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class MazeMap extends GameObject{
	
	private static MazeMap instance = new MazeMap();
	
	public static MazeMap getInstance() {
		return instance;
	}

	private int mapWidth;
	private int mapHeight;
	private boolean[][] maze_map;
	
	private int wallImage;
	private int roadImage;
	private boolean isMaze;
	
	public boolean isMaze() {
		return isMaze;
	}

	public void setMaze(boolean isMaze) {
		this.isMaze = isMaze;
	}

	private MazeMap() {
		
	}
	
	public int getWallImage() {
		return wallImage;
	}
	
	public int getRoadImage() {
		return roadImage;
	}
	
	public boolean getMapPoint(int x,int y){
		return maze_map[x][y];
	}

	public void setWallImage(int wallImage) {
		this.wallImage = wallImage;
	}

	public void setRoadImage(int roadImage) {
		this.roadImage = roadImage;
	}
	
	public int getMapWidth() {
		return mapWidth;
	}

	public void setMapWidth(int mapWidth) {
		this.mapWidth = mapWidth;
	}

	public int getMapHeight() {
		return mapHeight;
	}

	public void setMapHeight(int mapHeight) {
		this.mapHeight = mapHeight;
	}

	public boolean[][] getMaze_map() {
		return maze_map;
	}

	public void setMaze_map(boolean[][] maze_map) {
		this.maze_map = maze_map;
	}
	
	@Override
	public void init() {
		super.init();
		MapOperation mOperation = new MapOperationImpl();
		mOperation.initMap(Utils.ROWCOUNT, Utils.COLCOUNT);
		this.isMaze = true;
	}

	@Override
	public void draw(GraphicsContext gc) {
		if(this.isMaze){
			Actor myActor = Actor.getInstance();
			for (int x = myActor.getxCoordinate()-Utils.FOGRANGE; x < myActor.getxCoordinate()+Utils.FOGRANGE+1; x++) {
				for (int y = myActor.getyCoordinate()-Utils.FOGRANGE; y < myActor.getyCoordinate()+Utils.FOGRANGE+1; y++) {
					if(x<0 || y<0 || x>=this.mapWidth ||y>=this.mapHeight){
						continue;
					}
					int px,py;
					if(!this.maze_map[x][y]){
						px = this.wallImage % Utils.MAP_COLS;
						py = this.wallImage / Utils.MAP_COLS;
					}else{
						px = this.roadImage % Utils.MAP_COLS;
						py = this.roadImage / Utils.MAP_COLS;	
					}

					gc.drawImage(Utils.IMAGE_MAP, px * Utils.MAPESIZE, py * Utils.MAPESIZE, Utils.MAPESIZE, Utils.MAPESIZE
							, x * Utils.ESIZE,y * Utils.ESIZE, Utils.ESIZE, Utils.ESIZE);
				}
			} 
		}else{
			for (int x = 0; x < this.mapWidth; x++) {
				for (int y = 0; y < this.mapHeight; y++) {
					int px,py;
					if(!this.maze_map[x][y]){
						px = this.wallImage % Utils.MAP_COLS;
						py = this.wallImage / Utils.MAP_COLS;
					}else{
						px = this.roadImage % Utils.MAP_COLS;
						py = this.roadImage / Utils.MAP_COLS;	
					}
					
					gc.drawImage(Utils.IMAGE_MAP, px * Utils.MAPESIZE, py * Utils.MAPESIZE, Utils.MAPESIZE, Utils.MAPESIZE
							, x * Utils.ESIZE,y * Utils.ESIZE, Utils.ESIZE, Utils.ESIZE);
				}
			}  					
		}
		gc.setFill(Color.PINK);
		gc.fillOval((getMapWidth()-2)*Utils.ESIZE,(getMapHeight()-1)*Utils.ESIZE, Utils.ESIZE, Utils.ESIZE);
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}


}
