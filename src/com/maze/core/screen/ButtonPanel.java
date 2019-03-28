package com.maze.core.screen;
import com.maze.core.Utils;


import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Tooltip;
import javafx.scene.effect.DropShadow;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Window;

public class ButtonPanel extends VBox{
	private MainCanvas canvas;

	public ButtonPanel(final MainCanvas canvas) {		
		this.canvas = canvas;
		Button bt1 = createButton("自动巡航","Automatic routing(R)");
		bt1.setOnMouseClicked(e->{
			canvas.run();
		});
		
		Button bt2 =createButton("重新开始","Restart game(N)");
		bt2.setOnMouseClicked(e->{
			Utils.MAZE_BEGIN_SOUND.play();
			canvas.newGame();
		});

		Button bt3 =createButton("展开地图","Open Maze's Fog(S)");	
		bt3.setOnMouseClicked(e->{
			canvas.setAll();
		});
		
		Button bt4 =createButton("暂停","Pause(P)");	
		bt4.setOnMouseClicked(e->{
			canvas.pause();
		});	
		
		Button bt5 = createButton("修改难度","Change difficulty(C)");//修改难度按钮
		bt5.setOnMouseClicked(e->{
			MenuPane mp = new MenuPane();
			Utils.init();
			ButtonPanel.this.canvas.newGame();
		});	

		getChildren().addAll(bt1, bt2, bt3, bt4,bt5);
		setLayoutX(Utils.MAPWIDTH);
		setLayoutY(30);
	}

	private Button createButton(String text,String tiptool) {  //按钮统一设置
		// TODO Auto-generated method stub
		final Button button=new Button(text);
		final DropShadow shadow = new DropShadow();
		
		Tooltip tooltip = new Tooltip(tiptool);  
		button.setFont(new Font("Times Roman",15));
		button.setPrefSize(150, 50);//按钮的大小
		
		setAlignment(Pos.CENTER);
		setPrefSize(Utils.CONTROLWIDTH,Utils.CONTROLHEIGHT);
		setMargin(button, new Insets(20));//按钮之间恶距离	

		//当鼠标进入按钮时添加阴影特效
		button.addEventHandler(MouseEvent.MOUSE_ENTERED,new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent arg0) {
				button.setEffect(shadow); 
				Window window = canvas.getScene().getWindow();  
				double x = window.getX()+getLayoutX()+button.getLayoutX()+30;  
		        double y = window.getY()+getLayoutY()+button.getLayoutY()+button.getHeight()+tooltip.getHeight()-10;									          
		        tooltip.show(window, x, y);
			}  
	    });  
		
	    //当鼠标离开按钮时移除阴影效果
	    button.addEventHandler(MouseEvent.MOUSE_EXITED,new EventHandler<MouseEvent>() {  
	            @Override 
	            public void handle(MouseEvent e) {  
	            	button.setEffect(null);  
	            	tooltip.hide();  
	            }  
	    });
		return button;
	}

}
