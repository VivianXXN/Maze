package com.maze.core.beans;

import java.awt.Point;
import java.util.Stack;

import com.maze.core.GameObject;
import com.maze.core.Utils;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class ShortestPath extends GameObject {
	
	
	private static ShortestPath instance = new ShortestPath();
	
	private Stack<Point> path = new Stack<Point>();
	
	private ShortestPath(){
		
	}

	public static ShortestPath getInstance() {
		return instance;
	}

	public Stack<Point> getPath() {
		return path;
	}

	public void setPath(Stack<Point> path) {
		this.path = path;
	}

	@Override
	public void draw(GraphicsContext gc) {
		gc.setFill(Color.ALICEBLUE);
		for(int i = 1; i < this.path.size(); i++){
			gc.fillRoundRect(this.path.get(i).getX()*Utils.ESIZE, this.path.get(i).getY()*Utils.ESIZE
					, Utils.ESIZE, Utils.ESIZE, 10, 10);			
		}
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
	}

	@Override
	public void init() {
		setVisible(false);
	}

}
