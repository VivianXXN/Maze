package com.maze.core.beans;

import com.maze.core.GameObject;
import com.maze.core.Utils;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Tips extends GameObject {
	
	private static Tips instance = new Tips();
	private boolean gameOver = false;
	
	public boolean isGameOver() {
		return gameOver;
	}

	public void setGameOver(boolean gameOver) {
		this.gameOver = gameOver;
	}

	public static Tips getInstance() {
		return instance;
	}

	private Tips() {
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void init() {
		super.init();
		gameOver = false;
	}

	@Override
	public void draw(GraphicsContext gc) {				
		if(gameOver){
			gc.setFill(Color.RED);
			gc.fillText("You reach the end"+"\n", Utils.MAPWIDTH+0.8*Utils.xOffset, 1.5*Utils.yOffset);
		}else{
			gc.setFill(Color.BLUEVIOLET);
			gc.fillText("Pink point : EndPoint"+"\n"
			+"Big Cat Face  : StartPoint", Utils.MAPWIDTH+0.8*Utils.xOffset,1.5*Utils.yOffset);
		}	
	}
	
	

	@Override
	public void update() {
		
	}

}
