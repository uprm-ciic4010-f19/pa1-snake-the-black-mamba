package Resources;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * Created by AlexVR on 7/1/2018.
 */
public class Images {


    public static BufferedImage[] butstart;
    public static BufferedImage[] butreStart;
    public static BufferedImage title;
    public static BufferedImage Pause;
    public static BufferedImage GameOver;
    public static BufferedImage[] Resume;
    public static BufferedImage[] BTitle;
    public static BufferedImage[] Options;
    public static BufferedImage[] reStart;
    
    
    public static ImageIcon icon;


    
    public Images() {
    	
    	butreStart = new BufferedImage[3];
        butstart = new BufferedImage[3];
        Resume = new BufferedImage[2];
        BTitle = new BufferedImage[2];
        Options = new BufferedImage[2];
        reStart = new BufferedImage[2];
        


        try {
        	
        	reStart[0]= ImageIO.read(getClass().getResourceAsStream("/Buttons/restar1.png"));
        	reStart[1]= ImageIO.read(getClass().getResourceAsStream("/Buttons/restar1.png"));
        	GameOver= ImageIO.read(getClass().getResourceAsStream("/Buttons/gameover.png"));
            title = ImageIO.read(getClass().getResourceAsStream("/Buttons/wp2315932.png"));
            Pause = ImageIO.read(getClass().getResourceAsStream("/Buttons/202306.png"));
            Resume[0] = ImageIO.read(getClass().getResourceAsStream("/Buttons/Resume.png"));
            Resume[1] = ImageIO.read(getClass().getResourceAsStream("/Buttons/ResumeP.png"));
            BTitle[0] = ImageIO.read(getClass().getResourceAsStream("/Buttons/BTitle.png"));
            BTitle[1] = ImageIO.read(getClass().getResourceAsStream("/Buttons/BTitleP.png"));
            Options[0] = ImageIO.read(getClass().getResourceAsStream("/Buttons/Options.png"));
            Options[1] = ImageIO.read(getClass().getResourceAsStream("/Buttons/OptionsP.png"));
            butstart[0]= ImageIO.read(getClass().getResourceAsStream("/Buttons/NormBut.png"));//normbut
            butstart[1]= ImageIO.read(getClass().getResourceAsStream("/Buttons/HoverBut.png"));//hoverbut
            butstart[2]= ImageIO.read(getClass().getResourceAsStream("/Buttons/ClickedBut.png"));//clickbut
            butreStart[0]= ImageIO.read(getClass().getResourceAsStream("/Buttons/BTitle.png"));//normbutrestart
            butreStart[1]= ImageIO.read(getClass().getResourceAsStream("/Buttons/BTitleP.png"));//hoverbutrestart
            icon =  new ImageIcon(ImageIO.read(getClass().getResourceAsStream("/Sheets/icon.png")));


        }catch (IOException e) {
        e.printStackTrace();
    }


    }

    public static BufferedImage loadImage(String path) {
        try {
            return ImageIO.read(Images.class.getResourceAsStream(path));
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }
        return null;
    }

}
