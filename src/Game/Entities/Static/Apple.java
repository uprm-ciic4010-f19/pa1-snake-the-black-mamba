package Game.Entities.Static;

import java.awt.Color;

import Main.Handler;

/**
 * Created by AlexVR on 7/2/2018.
 */
public class Apple{

	private Handler handler;

	public int counter;
	public Color color;
	public int xCoord;
	public int yCoord;

	public Apple(Handler handler,int x, int y){
		this.handler=handler;
		this.xCoord=x;
		this.yCoord=y;
		counter=0;
		color=Color.red;
	}

	public boolean isGood() {
		if(counter>360) {
			return false;
		}else {
			return true;
		}
	}

	public void tick() {
		if(this.isGood()) {
			counter++;
		} else {
			color = new Color(140,70,20);
		}
	}

}
