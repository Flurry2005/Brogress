package se.aljr.application.homepage;

import javax.swing.*;
import java.awt.*;


/**
 * Container för innehållet av den vänstra sektionen av fönstret
 */
public class LeftPanel extends JPanel {
    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    int screenWidth = screenSize.width;
    int screenHeight = screenSize.height;

    public LeftPanel(){
        init();
    }
    private void init(){
        this.setOpaque(false);

    }
}
