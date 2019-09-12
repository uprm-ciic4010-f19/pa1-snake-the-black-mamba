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
        uiManager.addObjects(new UIImageButton(300, 350, 192, 96, Images.butreStart, new ClickListlener() {
            @Override
            public void onClick() {
                handler.getMouseManager().setUimanager(null);
                handler.getGame().reStart();
                State.setState(handler.getGame().menuState);   
            }
        }));
        
        uiManager.addObjects(new UIImageButton(325, 425, 128, 70,  Images.reStart, new ClickListlener(){
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
		g.setFont(new Font("TimesRoman", Font.BOLD, 55 ));
        g.drawString("Game Over ", handler.getWidth() - 365, handler.getHeight() - 600); 
        
    }


}
