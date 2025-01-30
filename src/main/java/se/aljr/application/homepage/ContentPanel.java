package se.aljr.application.homepage;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class ContentPanel extends JPanel {

    protected ImageIcon contentPanelBackground;
    ImageIcon moduleIcon = new ImageIcon("src/main/resources/module.png");
    protected ImageIcon scaledContentBackgroundPanel;
    Image scaledContentBackground;
    private String resourcePath;
    public ContentPanel(int width, int height){
        this.setPreferredSize(new Dimension(width, height));
        resourcePath = getClass().getClassLoader().getResource("resource.path").getPath().replace("resource.path","");
        contentPanelBackground = new ImageIcon(resourcePath+ "bottom_right_bar.png");

        scaledContentBackground = contentPanelBackground.getImage().getScaledInstance(width,height,Image.SCALE_SMOOTH);
        scaledContentBackgroundPanel = new ImageIcon(scaledContentBackground);


        init();
    }

    private void init(){
        this.setOpaque(false);
        this.setBackground(Color.BLACK);
        this.setLayout(new BorderLayout(0,0));

        JLabel text = new JLabel("Hello");

        this.add(text, BorderLayout.CENTER);

    }
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        // Draw the image to fill the entire panel
        if (contentPanelBackground != null) {
            g.drawImage(scaledContentBackgroundPanel.getImage(), 0, 0, getWidth(), getHeight(), this);
        }
        else{
            System.out.println("Error");
        }
    }
    public void reScaleBackground(){
        scaledContentBackground = contentPanelBackground.getImage().getScaledInstance(getWidth(),getHeight(),Image.SCALE_SMOOTH);
        scaledContentBackgroundPanel = new ImageIcon(scaledContentBackground);

    }
}
