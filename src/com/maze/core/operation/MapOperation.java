package com.maze.core.operation;

import java.awt.Point;
import java.util.Stack;

public interface MapOperation {//地图操作接口
	public void initMap(int height,int width);
	public void resetMap();
	public Stack<Point> getStack(int x,int y);
}
