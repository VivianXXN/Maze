package com.maze.core;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;

public abstract class GameApplication extends Application {
	
	private Group root;
	private Scene mScene;
	@Override
	public void start(Stage primaryStage) throws Exception {
		before();
		root = new Group();
		mScene = new Scene(root, Utils.SCREENWIDTH,Utils.SCREENHEIGHT);
		mScene.getStylesheets().add("/com/maze/core/screen/application.css");
		after();
		showStage(primaryStage);
	}

	protected void before() {

	}

	protected void after() {

	}

	protected void showStage(Stage stage) {
		stage.setScene(mScene);
		stage.show();
	}

	protected Scene getScene() {
		return mScene;
	}
	
	public Group getRoot() {
		return root;
	}
	
}
