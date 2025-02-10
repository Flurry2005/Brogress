package se.aljr.application.homepage;

import com.google.cloud.storage.Acl;
import se.aljr.application.UserData;

import javax.swing.*;
import java.awt.*;

public class HomePanel extends JPanel {

    protected ImageIcon homePanelBackground;
    ImageIcon moduleIcon;
    protected ImageIcon scaledContentBackgroundPanel;
    Image scaledContentBackground;
    private String resourcePath;
    public HomePanel(int width, int height){
        this.setSize(new Dimension(width, height));
        resourcePath = getClass().getClassLoader().getResource("resource.path").getPath().replace("resource.path","");
        homePanelBackground = new ImageIcon(resourcePath+ "bottom_right_bar.png");
        moduleIcon = new ImageIcon(resourcePath+"module.png");
        scaledContentBackground = homePanelBackground.getImage().getScaledInstance(width,height,Image.SCALE_SMOOTH);
        scaledContentBackgroundPanel = new ImageIcon(scaledContentBackground);


        init();
    }

    private void init(){
        this.setOpaque(false);
        this.setBackground(Color.BLACK);
        this.setLayout(new BorderLayout(0,0));

        JPanel userInfoPanel = new JPanel(){
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                // Draw the image to fill the entire panel
                if (homePanelBackground != null) {
                    g.drawImage(moduleIcon.getImage(), 0, 0, moduleIcon.getIconWidth(), moduleIcon.getIconHeight(), this);
                }
                else{
                    System.out.println("Error");
                }
            }
        };
        userInfoPanel.setOpaque(false);
        userInfoPanel.setPreferredSize(new Dimension(moduleIcon.getIconWidth(),moduleIcon.getIconHeight()));
        userInfoPanel.setMaximumSize(new Dimension(moduleIcon.getIconWidth(),moduleIcon.getIconHeight()));
        userInfoPanel.setLayout(new BoxLayout(userInfoPanel, BoxLayout.Y_AXIS));

        JLabel usernameLabel = new JLabel();
        usernameLabel.setText(UserData.getUserName());
        usernameLabel.setForeground(Color.WHITE);
        JLabel userAge = new JLabel();
        userAge.setText("Age: "+UserData.getUserAge());
        userAge.setForeground(Color.WHITE);
        JLabel userHeight = new JLabel();
        userHeight.setText("Height: "+ UserData.getUserHeight()+" Cm");
        userHeight.setForeground(Color.WHITE);
        JLabel userWeight = new JLabel();
        userWeight.setText("Weight: "+ UserData.getUserWeight()+" Kg");
        userWeight.setForeground(Color.WHITE);

        userInfoPanel.add(Box.createVerticalGlue());
        userInfoPanel.add(usernameLabel);
        userInfoPanel.add(userAge);
        userInfoPanel.add(userHeight);
        userInfoPanel.add(userWeight);
        userInfoPanel.add(Box.createVerticalGlue());


        JPanel userMacrosPanel = new JPanel(){
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                // Draw the image to fill the entire panel
                if (homePanelBackground != null) {
                    g.drawImage(moduleIcon.getImage(), 0, 0, moduleIcon.getIconWidth(), moduleIcon.getIconHeight(), this);
                }
                else{
                    System.out.println("Error");
                }
            }
        };
        userMacrosPanel.setOpaque(false);
        userMacrosPanel.setPreferredSize(new Dimension(moduleIcon.getIconWidth(),moduleIcon.getIconHeight()));
        userMacrosPanel.setMaximumSize(new Dimension(moduleIcon.getIconWidth(),moduleIcon.getIconHeight()));



        JPanel topPanel = new JPanel();
        topPanel.setPreferredSize(new Dimension(getWidth(),(int)(getHeight()/3.171974)+getHeight()/20));
        topPanel.setOpaque(false);
        topPanel.setLayout(new BoxLayout(topPanel,BoxLayout.X_AXIS));



        topPanel.add(Box.createHorizontalGlue());
        topPanel.add(userInfoPanel);
        topPanel.add(Box.createHorizontalGlue());
        topPanel.add(Box.createHorizontalGlue());
        topPanel.add(Box.createHorizontalGlue());
        topPanel.add(Box.createHorizontalGlue());
        topPanel.add(Box.createHorizontalGlue());
        topPanel.add(Box.createHorizontalGlue());
        topPanel.add(Box.createHorizontalGlue());
        topPanel.add(Box.createHorizontalGlue());
        topPanel.add(userMacrosPanel);
        topPanel.add(Box.createHorizontalGlue());



        this.add(topPanel, BorderLayout.NORTH);
    }
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        // Draw the image to fill the entire panel
        if (homePanelBackground != null) {
            g.drawImage(scaledContentBackgroundPanel.getImage(), 0, 0, getWidth(), getHeight(), this);
        }
        else{
            System.out.println("Error");
        }
    }
    public void reScaleBackground(){
        scaledContentBackground = homePanelBackground.getImage().getScaledInstance(getWidth(),getHeight(),Image.SCALE_SMOOTH);
        scaledContentBackgroundPanel = new ImageIcon(scaledContentBackground);

    }
}
