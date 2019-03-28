package com.maze.core.beans;

import com.maze.core.GameObject;
import com.maze.core.Utils;

import javafx.scene.canvas.GraphicsContext;

public class Background extends GameObject {
	
	private Background() {
	}
	private static Background instance = new Background();
	
	public static Background getInstance() {
		return instance;
	}

	@Override
	public void draw(GraphicsContext gc) {
		gc.drawImage(Utils.IMAGE_BK, 0, 0, Utils.SCREENWIDTH,Utils.SCREENHEIGHT);
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub

	}

}
