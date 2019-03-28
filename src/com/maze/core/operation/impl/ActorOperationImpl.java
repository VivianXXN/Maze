package com.maze.core.operation.impl;

import java.awt.Point;

import com.maze.core.beans.Actor;
import com.maze.core.beans.MazeMap;
import com.maze.core.beans.ShortestPath;
import com.maze.core.operation.ActorOperation;
import com.maze.core.operation.MapOperation;

public class ActorOperationImpl implements ActorOperation {
	
	private Actor myActor = Actor.getInstance();
	private MazeMap myMap = MazeMap.getInstance();
	private ShortestPath sPath = ShortestPath.getInstance();
	private MapOperation mo = new MapOperationImpl();
	
	@Override
	public void autoFindWay() {
		if(myActor.getxCoordinate() == myMap.getMapWidth()-2 
				&& myActor.getyCoordinate() == myMap.getMapHeight()-1){
			myActor.setUpdate(false);
			sPath.setVisible(false);
			return ;
		}
		sPath.setPath(mo.getStack(myActor.getxCoordinate(), myActor.getyCoordinate()));
		sPath.setVisible(true);
		Point p = sPath.getPath().get(1);
		if(p.getX()==myActor.getxCoordinate()){
			if(p.getY()==myActor.getyCoordinate()-1){
				myActor.moveUp();
			}else if(p.getY()==myActor.getyCoordinate()+1){
				myActor.moveDown();
			}
		}else if(p.getY()==myActor.getyCoordinate()){
			if(p.getX()==myActor.getxCoordinate()-1){
				myActor.moveLeft();
			}else if(p.getX()==myActor.getxCoordinate()+1){
				myActor.moveRight();
			}
		}
	}

}
