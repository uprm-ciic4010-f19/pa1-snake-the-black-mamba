package Game.GameStates;


import Main.Handler;
import Resources.Images;
import UI.ClickListlener;
import UI.UIImageButton;
import UI.UIManager;

import java.awt.*;

/**
 * Created by AlexVR on 7/1/2018.
 */
public class GameOverState extends State {

    private UIManager uiManager;

    public GameOverState(Handler handler) {
        super(handler);
        uiManager = new UIManager(handler);
        handler.getMouseManager().setUimanager(uiManager);
        uiManager.addObjects(new UIImageButton(325, 350, 140, 80, Images.butreStart, new ClickListlener() {
            @Override
            public void onClick() {
                handler.getMouseManager().setUimanager(null);
                handler.getGame().reStart();
                State.setState(handler.getGame().menuState);   
            }
        }));
        
        uiManager.addObjects(new UIImageButton(325, 425, 140, 80,  Images.reStart, new ClickListlener(){
			@Override
			public void onClick() {
            handler.getMouseManager().setUimanager(null);
            handler.getGame().reStart();
            State.setState(handler.getGame().gameState);
			}
            
        }));
        
    }
       


	@Override
    public void tick() {
        handler.getMouseManager().setUimanager(uiManager);
        uiManager.tick();

    }

    @Override
    public void render(Graphics g) {
        g.setColor(Color.darkGray);
        g.fillRect(0,0,handler.getWidth(),handler.getHeight());
        g.drawImage(Images.GameOver,0,0,handler.getWidth(),handler.getHeight(),null);
        uiManager.Render(g);
        g.setColor(Color.RED);
		g.setFont(new Font("Showcard Gothic", Font.BOLD, 55 ));
        g.drawString("Game Over ", handler.getWidth() - 365, handler.getHeight() - 600);
        g.setColor(Color.green);
		g.setFont(new Font("Showcard Gothic", Font.BOLD, 55 ));
        g.drawString("Score: " + String.valueOf(handler.getWorld().player.getTailValue()), handler.getWidth() - 365, handler.getHeight() - 500);  
        
    }


}
