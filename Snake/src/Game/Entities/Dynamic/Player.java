package Game.Entities.Dynamic;

import Main.Handler;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.Random;

import Game.GameStates.GameOverState;
import Game.GameStates.State;

/**
 * Created by AlexVR on 7/2/2018.
 */
public class Player {

	public int lenght;
	public boolean justAte;
	private Handler handler;

	private int tailValue = 0;

	public int getTailValue() {
		return tailValue;
	}

	public void setTailValue(int tailValue) {
		this.tailValue = tailValue;
	}

	public int xCoord;
	public int yCoord;

	public int moveCounter;
	public double default_counter;

	public String direction;//is your first name one?

	public Player(Handler handler){
		default_counter= 5.0;
		this.handler = handler;
		xCoord = 0;
		yCoord = 0;
		moveCounter = 0;
		direction= "Right";
		justAte = false;
		lenght= 1;

	}

	public void tick(){
		moveCounter++;
		if(moveCounter>default_counter) {
			checkCollisionAndMove();
			moveCounter=0;
		}
		if(handler.getKeyManager().keyJustPressed(KeyEvent.VK_UP)){
			direction="Up";
		}if(handler.getKeyManager().keyJustPressed(KeyEvent.VK_DOWN)){
			direction="Down";
		}if(handler.getKeyManager().keyJustPressed(KeyEvent.VK_LEFT)){
			direction="Left";
		}if(handler.getKeyManager().keyJustPressed(KeyEvent.VK_RIGHT)){
			direction="Right";
		}//New feature adds a new apple randomly
		if(handler.getKeyManager().keyJustPressed(KeyEvent.VK_A)){
			lenght++;
			handler.getWorld().appleLocation[xCoord][yCoord]=false;
			handler.getWorld().appleOnBoard=false;
		}if(handler.getKeyManager().keyJustPressed(KeyEvent.VK_N)){
		handler.getWorld().body.addFirst(new Tail(xCoord, yCoord,handler));	
		}if(handler.getKeyManager().keyJustPressed(KeyEvent.VK_ESCAPE)){
			State.setState(handler.getGame().pauseState);
		}if (handler.getKeyManager().keyJustPressed(KeyEvent.VK_MINUS) || handler.getKeyManager().keyJustPressed(KeyEvent.VK_SUBTRACT)) {
			default_counter++;
		}if (handler.getKeyManager().keyJustPressed(KeyEvent.VK_EQUALS) || handler.getKeyManager().keyJustPressed(KeyEvent.VK_ADD)) {
			default_counter--;
		}


	}

	public void checkCollisionAndMove(){
		handler.getWorld().playerLocation[xCoord][yCoord]=false;
		int x = xCoord;
		int y = yCoord;
		switch (direction){
		case "Left":
			if(xCoord==0){
				xCoord = handler.getWorld().GridWidthHeightPixelCount-1;
			}else{
				xCoord--;
			}
			break;
		case "Right": 
			if(xCoord==handler.getWorld().GridWidthHeightPixelCount-1){
				xCoord = 0;
			}else{
				xCoord++;
			}
			break;
		case "Up":
			if(yCoord==0){
				yCoord = handler.getWorld().GridWidthHeightPixelCount-1;
			}else{
				yCoord--;
			}
			break;
		case "Down":
			if(yCoord==handler.getWorld().GridWidthHeightPixelCount-1){
				yCoord = 0;
			}else{
				yCoord++;
			}
			break;
		}
			handler.getWorld().playerLocation[xCoord][yCoord]=true;


			if(handler.getWorld().appleLocation[xCoord][yCoord]){
				Eat();
				default_counter = default_counter - 0.05;
			}

			if(!handler.getWorld().body.isEmpty()) {
				handler.getWorld().playerLocation[handler.getWorld().body.getLast().x][handler.getWorld().body.getLast().y] = false;
				handler.getWorld().body.removeLast();
				handler.getWorld().body.addFirst(new Tail(x, y,handler));
			}
			for (int i = 0; i<handler.getWorld().body.size(); i++) {
				Tail tailPositions = handler.getWorld().body.get(i);
				if((this.getxCoord() == tailPositions.x) && (this.getyCoord() == tailPositions.y)) {
					kill();
				}
			}
		}
		private int getxCoord() {
			return xCoord; 
		}

		private int getyCoord() {
			return yCoord;
		}

		public void render(Graphics g,Boolean[][] playeLocation){
			Random r = new Random();
			for (int i = 0; i < handler.getWorld().GridWidthHeightPixelCount; i++) {
				for (int j = 0; j < handler.getWorld().GridWidthHeightPixelCount; j++) {
					if(playeLocation[i][j]||handler.getWorld().appleLocation[i][j]){
						g.setColor(Color.green);
						g.fillOval((i*handler.getWorld().GridPixelsize),
								(j*handler.getWorld().GridPixelsize),
								handler.getWorld().GridPixelsize,
								handler.getWorld().GridPixelsize);
					}
					if(handler.getWorld().appleLocation[i][j]){
						g.setColor(Color.red);
						g.fillOval((i*handler.getWorld().GridPixelsize),
								(j*handler.getWorld().GridPixelsize),
								handler.getWorld().GridPixelsize,
								handler.getWorld().GridPixelsize);
						
				
					}

				}
			}
			g.setColor(Color.WHITE);
			g.setFont(new Font("TimesRoman", Font.BOLD, 30 ));
			g.drawString("Score: " + String.valueOf(tailValue), handler.getWidth() - 465, handler.getHeight() - 750); 

		}

		public void Eat(){
			lenght++;
			Tail tail= null;
			handler.getWorld().appleLocation[xCoord][yCoord]=false;
			handler.getWorld().appleOnBoard=false;

			switch (direction){
			case "Left":
				if( handler.getWorld().body.isEmpty()){
					if(this.xCoord!=handler.getWorld().GridWidthHeightPixelCount-1){
						tail = new Tail(this.xCoord+1,this.yCoord,handler);
					}else{
						if(this.yCoord!=0){
							tail = new Tail(this.xCoord,this.yCoord-1,handler);
						}else{
							tail =new Tail(this.xCoord,this.yCoord+1,handler);
						}
					}
				}else{
					if(handler.getWorld().body.getLast().x!=handler.getWorld().GridWidthHeightPixelCount-1){
						tail=new Tail(handler.getWorld().body.getLast().x+1,this.yCoord,handler);
					}else{
						if(handler.getWorld().body.getLast().y!=0){
							tail=new Tail(handler.getWorld().body.getLast().x,this.yCoord-1,handler);
						}else{
							tail=new Tail(handler.getWorld().body.getLast().x,this.yCoord+1,handler);

						}
					}

				}
				break;
			case "Right":
				if( handler.getWorld().body.isEmpty()){
					if(this.xCoord!=0){
						tail=new Tail(this.xCoord-1,this.yCoord,handler);
					}else{
						if(this.yCoord!=0){
							tail=new Tail(this.xCoord,this.yCoord-1,handler);
						}else{
							tail=new Tail(this.xCoord,this.yCoord+1,handler);
						}
					}
				}else{
					if(handler.getWorld().body.getLast().x!=0){
						tail=(new Tail(handler.getWorld().body.getLast().x-1,this.yCoord,handler));
					}else{
						if(handler.getWorld().body.getLast().y!=0){
							tail=(new Tail(handler.getWorld().body.getLast().x,this.yCoord-1,handler));
						}else{
							tail=(new Tail(handler.getWorld().body.getLast().x,this.yCoord+1,handler));
						}
					}

				}
				break;
			case "Up":
				if( handler.getWorld().body.isEmpty()){
					if(this.yCoord!=handler.getWorld().GridWidthHeightPixelCount-1){
						tail=(new Tail(this.xCoord,this.yCoord+1,handler));
					}else{
						if(this.xCoord!=0){
							tail=(new Tail(this.xCoord-1,this.yCoord,handler));
						}else{
							tail=(new Tail(this.xCoord+1,this.yCoord,handler));
						}
					}
				}else{
					if(handler.getWorld().body.getLast().y!=handler.getWorld().GridWidthHeightPixelCount-1){
						tail=(new Tail(handler.getWorld().body.getLast().x,this.yCoord+1,handler));
					}else{
						if(handler.getWorld().body.getLast().x!=0){
							tail=(new Tail(handler.getWorld().body.getLast().x-1,this.yCoord,handler));
						}else{
							tail=(new Tail(handler.getWorld().body.getLast().x+1,this.yCoord,handler));
						}
					}

				}
				break;
			case "Down":
				if( handler.getWorld().body.isEmpty()){
					if(this.yCoord!=0){
						tail=(new Tail(this.xCoord,this.yCoord-1,handler));
					}else{
						if(this.xCoord!=0){
							tail=(new Tail(this.xCoord-1,this.yCoord,handler));
						}else{
							tail=(new Tail(this.xCoord+1,this.yCoord,handler));
						} System.out.println("Tu biscochito");
					}
				}else{
					if(handler.getWorld().body.getLast().y!=0){
						tail=(new Tail(handler.getWorld().body.getLast().x,this.yCoord-1,handler));
					}else{
						if(handler.getWorld().body.getLast().x!=0){
							tail=(new Tail(handler.getWorld().body.getLast().x-1,this.yCoord,handler));
						}else{
							tail=(new Tail(handler.getWorld().body.getLast().x+1,this.yCoord,handler));
						}
					}

				}
				break;
			}
			handler.getWorld().body.addLast(tail);
			handler.getWorld().playerLocation[tail.x][tail.y] = true;
			tailValue = tailValue + 1; 

		}
		

		public void kill() {
			GameOverState gameOver = new GameOverState(handler);
			lenght = 0;
			for (int i = 0; i < handler.getWorld().GridWidthHeightPixelCount; i++) {
				for (int j = 0; j < handler.getWorld().GridWidthHeightPixelCount; j++) {

					handler.getWorld().playerLocation[i][j]=false;     

				}
			}
			State.setState(gameOver);
		}

		public boolean isJustAte() {
			return justAte;
		}

		public void setJustAte(boolean justAte) {
			this.justAte = justAte;
		}
	}
