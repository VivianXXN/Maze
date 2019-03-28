package com.maze.core.beans;

import com.maze.core.GameObject;
import com.maze.core.Utils;
import com.maze.core.operation.impl.ActorOperationImpl;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Actor extends GameObject{
	
//	private enum Direction {  
//        Left, Right, Up, Down  
//    };
	
	private static Actor instance = new Actor();
	

	private Actor(){
		
	}
	
	public static Actor getInstance() {
		return instance;
	}

	private int xCoordinate = 1;
	private int yCoordinate = 0;
	private int direction;
	private int step;
	//private Direction direction = Direction.Down; 
	
	public int getxCoordinate() {
		return xCoordinate;
	}

	public int getyCoordinate() {
		return yCoordinate;
	}

	private int actorImage = 0;
	
	@Override
	public void draw(GraphicsContext gc) {
		if(xCoordinate == 1&&yCoordinate == 0){
			gc.drawImage(Utils.IMAGE_BIGFACE,this.xCoordinate*Utils.ESIZE, this.yCoordinate*Utils.ESIZE
					, Utils.ESIZE, Utils.ESIZE);
			return;
		}	
		gc.drawImage(Utils.IMAGE_CAT[direction][step%3],this.xCoordinate*Utils.ESIZE, this.yCoordinate*Utils.ESIZE
				, Utils.ESIZE, Utils.ESIZE);
				
	}
	
	@Override
	public void update() {
		new ActorOperationImpl().autoFindWay();
	}

	@Override
	public void init() {
		super.init();
		this.xCoordinate = 1;
		this.yCoordinate = 0;
		direction=0;
		step=0;
		this.setUpdate(false);
	}

	public int getActorImage() {
		return actorImage;
	}

	public void setActorImage(int actorImage) {
		this.actorImage = actorImage;
	}

	public boolean moveLeft() {
		if(this.xCoordinate-1>=0 && MazeMap.getInstance().getMapPoint(this.xCoordinate-1, this.yCoordinate)){
			this.xCoordinate--;
			direction=2;
			step++;
			return true;
		}
		return false;
	}

	public boolean moveRight() {
		if(this.xCoordinate+1<MazeMap.getInstance().getMapWidth() && 
				MazeMap.getInstance().getMapPoint(this.xCoordinate+1, this.yCoordinate)){
			this.xCoordinate++;
			direction=3;
			step++;
			return true;
		}
		return false;
	}

	public boolean moveUp() {
		if(this.yCoordinate-1>=0 &&
				MazeMap.getInstance().getMapPoint(this.xCoordinate, this.yCoordinate-1)){
			this.yCoordinate--;
			direction=0;
			step++;
			return true;
		}
		return false;
	}

	public boolean moveDown() {
		if(this.yCoordinate+1<=MazeMap.getInstance().getMapHeight() && 
				MazeMap.getInstance().getMapPoint(this.xCoordinate, this.yCoordinate+1)){
			this.yCoordinate++;
			direction=1;
			step++;
			return true;
		}
		return false;
	}

}
