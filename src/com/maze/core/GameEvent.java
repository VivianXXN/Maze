package com.maze.core;

import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

public interface GameEvent {
	/**
	 * 初始化事件
	 * 
	 */
	default public void init() {
	}

	/**
	 * 默认按键按下事件
	 * 
	 * @param event
	 */
	default public void onKeyPressed(KeyEvent event) {
	}

	/**
	 * 默认按键释放事件
	 * 
	 * @param event
	 */
	default public void onKeyReleased(KeyEvent event) {
	}

	/**
	 * 默认鼠标移动事件
	 * 
	 * @param event
	 */
	default public void onMouseMoved(MouseEvent event) {
	}
	
	default public void onMouseClicked(MouseEvent event) {
	}

}
