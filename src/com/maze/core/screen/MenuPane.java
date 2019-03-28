package com.maze.core.screen;
import com.maze.core.Utils;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class MenuPane{
	private Stage stage = new Stage();
	TextField tf1;
	TextField tf2;
	TextField tf3;
	public MenuPane(){
		ToggleGroup group = new ToggleGroup();
		StackPane pane = new StackPane();
		RadioButton bt1 = new RadioButton("初级");
		bt1.setToggleGroup(group);
		bt1.setFont(Font.font("Helvetica", FontWeight.BOLD, 14));
		RadioButton bt2 = new RadioButton("中级");
		bt2.setToggleGroup(group);
		bt2.setSelected(true);
		bt2.setFont(Font.font("Helvetica", FontWeight.BOLD, 14));
		RadioButton bt3 = new RadioButton("高级");
		bt3.setToggleGroup(group);
		bt3.setFont(Font.font("Helvetica", FontWeight.BOLD, 14));
		RadioButton bt4 = new RadioButton("自定义");
		bt4.setToggleGroup(group);
		bt4.setFont(Font.font("Helvetica", FontWeight.BOLD, 14));
		
		stage.setAlwaysOnTop(true);
		Button bt5 = new Button("确定");
		GridPane g = new GridPane();
		g.setAlignment(Pos.CENTER);
		g.setVgap(10);
		g.setHgap(10);
		g.add(new Label("行数"), 0, 0);
		g.add(new Label("列数"), 0, 1);
		g.add(new Label("迷雾范围"), 0, 2);
			
		g.add(tf1 = new TextField(), 1, 0);
		g.add(tf2 = new TextField(), 1, 1);
		g.add(tf3 = new TextField(), 1, 2);
		
	    VBox vbox = new VBox();
	    vbox.setSpacing(10);
	    vbox.setAlignment(Pos.CENTER);
	    vbox.getChildren().addAll(bt1,bt2,bt3,bt4,g,bt5);
			
	    pane.getChildren().add(vbox);
		bt5.setOnAction(e->{
			if(group.getSelectedToggle()==bt1){
				Utils.ROWCOUNT=10;
				Utils.COLCOUNT=10;
				Utils.FOGRANGE=5;
			}else if(group.getSelectedToggle()==bt2){
				Utils.ROWCOUNT=15;
				Utils.COLCOUNT=15;
				Utils.FOGRANGE=4;
			}else if(group.getSelectedToggle()==bt3){
				Utils.ROWCOUNT=20;
				Utils.COLCOUNT=20;
				Utils.FOGRANGE=3;
			}else if(group.getSelectedToggle()==bt4){
				Utils.ROWCOUNT=Integer.valueOf(tf1.getText());
				Utils.COLCOUNT=Integer.valueOf(tf2.getText());
				Utils.FOGRANGE=Integer.valueOf(tf3.getText());
			}			
			stage.close();
		});	
			
		Scene scene = new Scene(pane, 300, 250);
		scene.getStylesheets().add("/com/maze/core/screen/application1.css");
		stage.setTitle("难度选择");
		stage.setScene(scene);
		stage.showAndWait();
		
	}
	
}
