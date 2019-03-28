package com.maze.core.operation.impl;

import java.awt.Point;
import java.util.Random;
import java.util.Stack;

import com.maze.core.Utils;
import com.maze.core.beans.MazeMap;
import com.maze.core.operation.MapOperation;

public class MapOperationImpl implements MapOperation {

	MazeMap myMap = MazeMap.getInstance();
	boolean a[][];
	private int currX, currY;
	private Stack<Point> stack = new Stack<Point>();// 保存已走路径
	public Stack<Point> getStack(int x,int y) {
		findPath(x,y);
		return stack;
	}
	

	private int[] X_GO1 = { -2, 2, 0, 0 };
	private int[] Y_GO1 = { 0, 0, 2, -2 };
	private int[] X_GO2 = { -1, 1, 0, 0 };
	private int[] Y_GO2 = { 0, 0, 1, -1 };

	@Override
	public void initMap(int height, int width) {
		myMap.setMapHeight(2 * height + 1);
		myMap.setMapWidth(2 * width + 1);
		myMap.setWallImage(Utils.WALLCOLOR);
		myMap.setRoadImage(Utils.ROADCOLOR);
		start();
		myMap.setMaze_map(a);
		
	}

	@Override
	public void resetMap() {
		start();
		myMap.setMaze_map(a);	
	}
	//寻路
	private void findPath(int x,int y){
		stack.clear();
		stack.push(new Point(x, y));
		int tmpMap[][] = getDFSMap(x, y);
		while(x!=myMap.getMapWidth()-2 || y!=myMap.getMapHeight()-1){
			if(tmpMap[x+2][y+1] == stack.size()){
				x++;
				stack.push(new Point(x, y));
				tmpMap[x+1][y+1] = -2;
				continue;
			}
			if(tmpMap[x+1][y+2] == stack.size()){
				y++;
				stack.push(new Point(x, y));
				tmpMap[x+1][y+1] = -2;
				continue;
			}
			if(tmpMap[x][y+1] == stack.size()){
				x--;
				stack.push(new Point(x, y));
				tmpMap[x+1][y+1] = -2;
				continue;
			}
			if(tmpMap[x+1][y] == stack.size()){
				y--;
				stack.push(new Point(x, y));
				tmpMap[x+1][y+1] = -2;
				continue;
			}
			stack.pop();
			x=(int) stack.lastElement().getX();
			y=(int) stack.lastElement().getY();
		}
	}
	
	private int[][]getDFSMap(int x, int y){
		boolean mazeMap[][] = myMap.getMaze_map();
		int tmpMap[][] = new int[myMap.getMapWidth()+2][myMap.getMapHeight()+2];
		for (int i = 0; i < myMap.getMapWidth()+2; i++) {
			for(int j = 0; j< myMap.getMapHeight()+2; j++){
				if(i==0 || j==0 ||i==myMap.getMapWidth()+1 || j==myMap.getMapHeight()+1){
					tmpMap[i][j] = -2;
				}else if(!mazeMap[i-1][j-1]){
					tmpMap[i][j] = -2;
				}else{
					tmpMap[i][j] = -1;
				}
			}
		}
		int n =0;
		tmpMap[x+1][y+1] = n;
		while(updateDFSMap(tmpMap, n)){
			n++;
		}
		return tmpMap;
	}
	
	private boolean updateDFSMap(int tmpMap[][],int n){
		boolean flag = false;
		for (int i = 1; i < myMap.getMapWidth()+1; i++) {
			for(int j = 1; j< myMap.getMapHeight()+1; j++){
				if(tmpMap[i][j] == n){
					if(tmpMap[i-1][j]==-1){
						tmpMap[i-1][j]=n+1;
						flag=true;
					}
					if(tmpMap[i+1][j]==-1){
						tmpMap[i+1][j]=n+1;
						flag=true;
					}
					if(tmpMap[i][j-1]==-1){
						tmpMap[i][j-1]=n+1;
						flag=true;
					}
					if(tmpMap[i][j+1]==-1){
						tmpMap[i][j+1]=n+1;
						flag=true;
					}
				}
			}
		}
		return flag;
	}
	
	
//生成
	
	
    private boolean check()// 判断是否全部走过。。。
    {
        for (int i = 1; i < myMap.getMapWidth(); i += 2)
        {
            for (int j = 1; j < myMap.getMapHeight(); j += 2)
            {
                if (a[i][j] == false)
                    return false;
            }
        }
        return true;
    }

    private void forward()// 后退一步
    {
        if (stack.size() > 0)
        {
            Point p = stack.pop();
            currX = (int) p.getX();
            currY = (int) p.getY();
        }
        else
        {
            goRandPosition();
        }
    }

    private void goRandPosition()
    {
        int i, j;
        while (true)
        {
            i = (int) (Math.random() * myMap.getMapWidth());
            j = (int) (Math.random() * myMap.getMapHeight());
            if (a[i][j] == true && i % 2 == 1 && j % 2 == 1)
            {
                stack.push(new Point(i, j));
                currX = i;
                currY = j;
                break;
            }
        }
    }

    private void start()// 具体操作
    {
    	a = new boolean[myMap.getMapWidth()][myMap.getMapHeight()];
		for (int i = 0; i < myMap.getMapWidth(); i++)
			for (int j = 0; j < myMap.getMapHeight(); j++)
				a[i][j] = false;
		currX = 1;
		currY = 1;
		stack.clear();
		stack.push(new Point(1, 1));
		a[1][0] = true;
		a[1][1] = true;
        while (!check())
        {
            go();
        }
        a[myMap.getMapWidth() - 2][myMap.getMapHeight() - 1] = true;
    }

    private void go()//随机选择方向并走到下一个点
    {
        int orders[] = getOrder(4);
        for (int i = 0; i < orders.length; i++)
        {
            if (isSafe(currX + X_GO1[orders[i]], currY + Y_GO1[orders[i]]))// 上
            {
                goNext(orders[i]);
                return;
            }
        }
        forward();
    }

    private void goNext(int i)// 下一步
    {
        a[currX + X_GO1[i]][currY + Y_GO1[i]] = true;
        a[currX + X_GO2[i]][currY + Y_GO2[i]] = true;
        stack.push(new Point(currX, currY));
        currX += X_GO1[i];
        currY += Y_GO1[i];
    }

    private int[] getOrder(int i)// 产生随机序列
    {
        int a[] = new int[i];
        Random ran = new Random();
        for (int j = 0; j < i; j++)
            a[j] = j;
        for (int k = 0; k < i; k++)
        {
            int r1 = ran.nextInt(i);
            int r2 = ran.nextInt(i);
            int b = a[r1];
            a[r1] = a[r2];
            a[r2] = b;
        }
        return a;
    }

    private boolean isSafe(int x, int y)
    {

        if (x < 0 || x >= myMap.getMapWidth() || y < 0 || y >= myMap.getMapHeight() || a[x][y] == true)
        {
            return false;
        }
        return true;
    }

}
