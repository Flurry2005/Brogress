package se.aljr.application.homepage;

import com.google.cloud.storage.Acl;
import se.aljr.application.CustomFont;
import se.aljr.application.UserData;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

public class HomePanel extends JPanel {

    protected ImageIcon homePanelBackground;
    ImageIcon moduleIcon;
    protected ImageIcon scaledContentBackgroundPanel;
    Image scaledContentBackground;
    private String resourcePath;

    private Image scaledPofilePicture;
    private ImageIcon profilePictureIcon;
    private ImageIcon scaledProfilePictureIcon;

    private static JLabel userAge;

    public HomePanel(int width, int height){
        this.setSize(new Dimension(width, height));
        resourcePath = getClass().getClassLoader().getResource("resource.path").getPath().replace("resource.path","");
        homePanelBackground = new ImageIcon(resourcePath+ "bottom_right_bar.png");
        moduleIcon = new ImageIcon(resourcePath+"module.png");
        scaledContentBackground = homePanelBackground.getImage().getScaledInstance(width,height,Image.SCALE_SMOOTH);
        scaledContentBackgroundPanel = new ImageIcon(scaledContentBackground);

        profilePictureIcon = new ImageIcon(resourcePath + "Johan.png");
        scaledPofilePicture = profilePictureIcon.getImage().getScaledInstance(getWidth()/25,getWidth()/25,Image.SCALE_SMOOTH);
        scaledProfilePictureIcon = new ImageIcon(scaledPofilePicture);

        init();
    }

    private void init(){
        this.setOpaque(false);
        this.setBackground(Color.BLACK);
        this.setLayout(new BorderLayout(0,0));

        System.out.println("HomePanel Width: "+getWidth()+" HomePanel Height :"+getHeight());
        JPanel userInfoPanel = new JPanel(){
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                // Draw the image to fill the entire panel
                if (moduleIcon != null) {
                    Image scale = moduleIcon.getImage().getScaledInstance((int) (HomePanel.this.getWidth()/3.7695035461),
                            (int)(HomePanel.this.getHeight()/4.22292993631),
                            Image.SCALE_SMOOTH);
                    ImageIcon scaleImage = new ImageIcon(scale);
                    g.drawImage(scaleImage.getImage(), 0, 0, (int) (HomePanel.this.getWidth()/3.7695035461), (int)(HomePanel.this.getHeight()/4.22292993631), this);
                }
                else{
                    System.out.println("Error");
                }
            }
        };
        userInfoPanel.setOpaque(false);
        userInfoPanel.setPreferredSize(new Dimension((int) (HomePanel.this.getWidth()/3.7695035461),(int)(HomePanel.this.getHeight()/4.22292993631)));
        userInfoPanel.setMaximumSize(userInfoPanel.getPreferredSize());
        userInfoPanel.setLayout(new BoxLayout(userInfoPanel, BoxLayout.Y_AXIS));

        JLabel usernameLabel = new JLabel();
        usernameLabel.setText(UserData.getUserName());
        usernameLabel.setFont(CustomFont.getFont().deriveFont(getWidth()/50f));
        usernameLabel.setForeground(Color.WHITE);
        usernameLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        userAge = new JLabel();
        userAge.setText("Age: "+UserData.getUserAge());
        userAge.setFont(CustomFont.getFont().deriveFont(getWidth()/60f));
        userAge.setForeground(Color.WHITE);
        userAge.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel userHeight = new JLabel();
        userHeight.setText("Height: "+ UserData.getUserHeight()+" Cm");
        userHeight.setFont(CustomFont.getFont().deriveFont(getWidth()/60f));
        userHeight.setForeground(Color.WHITE);
        userHeight.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel userWeight = new JLabel();
        userWeight.setText("Weight: "+ UserData.getUserWeight()+" Kg");
        userWeight.setFont(CustomFont.getFont().deriveFont(getWidth()/60f));
        userWeight.setForeground(Color.WHITE);
        userWeight.setAlignmentX(Component.CENTER_ALIGNMENT);


        JLabel profilePicture = new JLabel(scaledProfilePictureIcon);
        profilePicture.setAlignmentX(Component.CENTER_ALIGNMENT);

        userInfoPanel.add(Box.createVerticalGlue());
        userInfoPanel.add(profilePicture);
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
        this.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                userInfoPanel.repaint();
                userInfoPanel.setPreferredSize(new Dimension((int) (HomePanel.this.getWidth()/3.7695035461),(int)(HomePanel.this.getHeight()/4.22292993631)));
                userInfoPanel.setMaximumSize(userInfoPanel.getPreferredSize());

                topPanel.setPreferredSize(new Dimension(getWidth(),(int)(getHeight()/3.171974)+getHeight()/20));

                usernameLabel.setFont(CustomFont.getFont().deriveFont(getWidth()/50f));
                userAge.setFont(CustomFont.getFont().deriveFont(getWidth()/60f));
                userHeight.setFont(CustomFont.getFont().deriveFont(getWidth()/60f));
                userWeight.setFont(CustomFont.getFont().deriveFont(getWidth()/60f));

                scaledPofilePicture = profilePictureIcon.getImage().getScaledInstance(getWidth()/25,getWidth()/25,Image.SCALE_SMOOTH);
                scaledProfilePictureIcon = new ImageIcon(scaledPofilePicture);
                profilePicture.setIcon(scaledProfilePictureIcon);
            }
        });
    }

    public static void updateUserInfo(){
        userAge.setText("Age: "+UserData.getUserAge());
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
