package se.aljr.application;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class ChatPanel extends JPanel {

    ImageIcon settingsPanelBackground;
    Image scaleSettingsPanelBackground;
    ImageIcon scaledSettingsPanelBackgroundIcon;

    private String resourcePath;

    public ChatPanel(int width, int height) {
        resourcePath = getClass().getClassLoader().getResource("resource.path").getPath().replace("resource.path","");
        settingsPanelBackground = new ImageIcon(resourcePath+"emptyBackground.png");
        scaleSettingsPanelBackground = settingsPanelBackground.getImage().getScaledInstance(width,height, Image.SCALE_SMOOTH);
        scaledSettingsPanelBackgroundIcon = new ImageIcon(scaleSettingsPanelBackground);

        this.setPreferredSize(new Dimension(width,height));
        this.setOpaque(false);
        this.setLayout(new BorderLayout(0,0));
        this.setBorder(new EmptyBorder(15,15,15,15));

        init();
    }

    private void init(){
        /*___________Här skrivs programmet_______________*/

    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        // Draw the image to fill the entire panel
        if (scaledSettingsPanelBackgroundIcon != null) {
                g.drawImage(scaledSettingsPanelBackgroundIcon.getImage(), 0, 0, getWidth(), getHeight(), this);
        }
        else{
            System.out.println("Error");
        }
    }
}
