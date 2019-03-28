package com.maze.core;

import javafx.scene.image.Image;
import javafx.scene.media.AudioClip;

public class Utils {

	//迷雾范围
	public static int FOGRANGE = 4;
	// 单元格行数
	public static int ROWCOUNT =15;// 实际为x*2+1保证生成奇数 >3
	// 单元格列数
	public static int COLCOUNT = 15;

	// 地图宽度
	public static int MAPWIDTH =  640;
	
	// 地图高度
	public static int MAPHEIGHT = 640;
	// 单元格大小
	public static int ESIZE = MAPHEIGHT/(ROWCOUNT * 2 + 1);
	// 控制面板宽度
	public static int CONTROLWIDTH = 200;
	// 控制面板高度
	public static int CONTROLHEIGHT = MAPHEIGHT;
	// 游戏宽度
	public static int SCREENWIDTH = MAPWIDTH + CONTROLWIDTH;
	// 游戏高度
	public static int SCREENHEIGHT = MAPHEIGHT;
	
	public static int xOffset=50;
	public static int yOffset=50;

	// map颜色
	public static int WALLCOLOR = 16;
	public static int ROADCOLOR = 8;
	public static int MAPESIZE = 32;// 地图单元格大小
	
	// map图
	public static Image[][] IMAGE_CAT = {
				{new Image(Utils.class.getResourceAsStream("/com/maze/core/resource/images/up.png")),new Image(Utils.class.getResourceAsStream("/com/maze/core/resource/images/up1.png")),new Image(Utils.class.getResourceAsStream("/com/maze/core/resource/images/up2.png"))},
				{new Image(Utils.class.getResourceAsStream("/com/maze/core/resource/images/down.png")),new Image(Utils.class.getResourceAsStream("/com/maze/core/resource/images/down1.png")),new Image(Utils.class.getResourceAsStream("/com/maze/core/resource/images/down2.png"))},
				{new Image(Utils.class.getResourceAsStream("/com/maze/core/resource/images/left.png")),new Image(Utils.class.getResourceAsStream("/com/maze/core/resource/images/left1.png")),new Image(Utils.class.getResourceAsStream("/com/maze/core/resource/images/left2.png"))},
				{new Image(Utils.class.getResourceAsStream("/com/maze/core/resource/images/right.png")),new Image(Utils.class.getResourceAsStream("/com/maze/core/resource/images/right1.png")),new Image(Utils.class.getResourceAsStream("/com/maze/core/resource/images/right2.png"))}
	};
	public static Image IMAGE_MAP = new Image(Utils.class.getResourceAsStream("/com/maze/core/resource/images/map.png"));
	public static Image IMAGE_BK = new Image("/com/maze/core/resource/images/background.jpg");

	public static Image IMAGE_BIGFACE= new Image(Utils.class.getResourceAsStream("/com/maze/core/resource/images/BigFace.png"));
	public static int MAP_COLS = (int) (IMAGE_MAP.getWidth() / MAPESIZE);
	
	public static AudioClip MAZE_BEGIN_SOUND = new AudioClip(Utils.class.getResource("/com/maze/core/resource/music/startSound.wav").toString());
    public static AudioClip MAZE_COMPLETE_SOUND = new AudioClip(Utils.class.getResource("/com/maze/core/resource/music/winSound.wav").toString());
    
    
    public static void init(){
    	if(ROWCOUNT<COLCOUNT){
    		ESIZE = MAPWIDTH/(COLCOUNT * 2 + 1);
    	}else{
    		ESIZE = MAPHEIGHT/(ROWCOUNT * 2 + 1);
    		MAP_COLS = (int) (IMAGE_MAP.getWidth() / MAPESIZE);
    	}
    }

}
