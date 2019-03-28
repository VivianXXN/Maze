package com.maze.core.screen;

import com.maze.core.GameScreen;
import com.maze.core.Utils;
import com.maze.core.beans.*;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class MainCanvas extends GameScreen {

	private MazeMap myMap = MazeMap.getInstance();
	private Actor myActor = Actor.getInstance();
	private Tips myInf = Tips.getInstance();
	private ShortestPath sPath = ShortestPath.getInstance();	
	private Background myBack = Background.getInstance();	
	
	public MainCanvas(double width, double height) {
		super(width, height);
		addObject(myBack);
		addObject(myMap);
		addObject(sPath);
		addObject(myInf);
		addObject(myActor);

		mGameState = GameState.GAME_START;		
	}


	@Override
	public void onKeyPressed(KeyEvent event) {
		super.onKeyPressed(event);
		KeyCode tmpCode = event.getCode();
		if(mGameState == GameState.GAME_START){
			if (tmpCode == KeyCode.UP){
				myActor.moveUp();
			}else if(tmpCode == KeyCode.DOWN){
				myActor.moveDown();
			}else if(tmpCode == KeyCode.LEFT){
				myActor.moveLeft();
			}else if(tmpCode == KeyCode.RIGHT){
				myActor.moveRight();
			}
			if(myActor.getxCoordinate()==Utils.COLCOUNT*2-1&&myActor.getyCoordinate()==Utils.ROWCOUNT*2){
				Utils.MAZE_COMPLETE_SOUND.play();
				myInf.setGameOver(true);
				myMap.setMaze(false);
				super.draw(getGraphicsContext2D());
				mGameState = GameState.GAME_PAUSE;
			}			
		}
	}

	@Override
	
	
	public void onKeyReleased(KeyEvent event) {	
		super.onKeyReleased(event);
		KeyCode tmpCode = event.getCode();
		if(tmpCode==KeyCode.P){
			pause();
		}else if(tmpCode == KeyCode.N){
			newGame();
		}else if(tmpCode == KeyCode.R){
			run();
		}else if(tmpCode == KeyCode.S){
			setAll();
		}else if(tmpCode == KeyCode.C){
			MenuPane mp = new MenuPane();
			Utils.init();
			newGame();
		}
	}

	
	@Override
	public void draw(GraphicsContext gc) {
		// 暂停
		if (mGameState == GameState.GAME_PAUSE) {
			return;
		}
		super.draw(gc);
	}
	
	@Override
	public void update() {
		super.update();
		if(myActor.getxCoordinate()==Utils.COLCOUNT*2-1&&myActor.getyCoordinate()==Utils.ROWCOUNT*2 && myActor.isUpdate()){
			myActor.setUpdate(false);
			myMap.setMaze(false);
			myInf.setGameOver(true);
			Utils.MAZE_COMPLETE_SOUND.play();
			super.draw(getGraphicsContext2D());
			mGameState = GameState.GAME_PAUSE;
		}
	}
	
	public void pause(){
		if (mGameState == GameState.GAME_START) {
			mGameState = GameState.GAME_PAUSE;
		}else{
			mGameState = GameState.GAME_START;
		}
	}

	public void run(){
		if(myActor.isUpdate()){
			sPath.setVisible(false);
			myActor.setUpdate(false);
		}else{
			myActor.setUpdate(true);
		}
	}
	
	public void newGame(){
		this.initObject();
		mGameState = GameState.GAME_START;
	}
	
	public void setAll(){
		myMap.setMaze(myMap.isMaze()^true);
	}

}
