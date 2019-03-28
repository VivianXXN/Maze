package com.maze.core.screen;	
import com.maze.core.GameApplication;
import com.maze.core.Utils;

import javafx.stage.Stage;

public class Main extends GameApplication {
	
	protected void after() {
		
		MainCanvas mCanvas = new MainCanvas(Utils.MAPWIDTH+Utils.CONTROLWIDTH, Utils.MAPHEIGHT);
		ButtonPanel bp = new ButtonPanel(mCanvas);
		this.getRoot().getChildren().addAll(mCanvas,bp);

		mCanvas.start();
		mCanvas.initEvents();
	}
	
	@Override
	protected void showStage(Stage stage) {
		super.showStage(stage);
		stage.setTitle("JavaFX游戏开发 迷宫");
	}

	
	public static void main(String[] args) {
		launch(args);
	}
}
