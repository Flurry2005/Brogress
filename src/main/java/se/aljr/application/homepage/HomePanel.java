package se.aljr.application.homepage;

import com.google.cloud.storage.Acl;
import se.aljr.application.CustomFont;
import se.aljr.application.ImageAvatar;
import se.aljr.application.UserData;
import se.aljr.application.loginpage.FirebaseManager;

import javax.imageio.ImageIO;
import javax.imageio.stream.ImageInputStream;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class HomePanel extends JPanel {

    protected ImageIcon homePanelBackground;
    ImageIcon moduleIcon;
    protected ImageIcon scaledContentBackgroundPanel;
    Image scaledContentBackground;
    private String resourcePath;

    private Image scaledProfilePicture;
    private static ImageIcon profilePictureIcon;
    private static ImageIcon scaledProfilePictureIcon;

    private static ImageAvatar avatar;

    private static JLabel userAge;
    private static JLabel userHeight;
    private static JLabel userWeight;

    public HomePanel(int width, int height){
        this.setSize(new Dimension(width, height));
        resourcePath = getClass().getClassLoader().getResource("resource.path").getPath().replace("resource.path","");
        homePanelBackground = new ImageIcon(resourcePath+ "bottom_right_bar.png");
        moduleIcon = new ImageIcon(resourcePath+"module.png");
        scaledContentBackground = homePanelBackground.getImage().getScaledInstance(width,height,Image.SCALE_SMOOTH);
        scaledContentBackgroundPanel = new ImageIcon(scaledContentBackground);

        //profilePictureIcon = new ImageIcon(resourcePath + "Johan.png");
        profilePictureIcon = FirebaseManager.readDBprofilePicture();
        if(profilePictureIcon==null){
            profilePictureIcon = new ImageIcon(resourcePath + "agile_small_icon.png");
        }
        avatar = new ImageAvatar();
        avatar.setPreferredSize(new Dimension(getWidth()/25,getWidth()/25));
        scaledProfilePicture = profilePictureIcon.getImage().getScaledInstance(getWidth()/25,getWidth()/25,Image.SCALE_SMOOTH);
        scaledProfilePictureIcon = new ImageIcon(scaledProfilePicture);
        avatar.setImage(scaledProfilePictureIcon);


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
        userInfoPanel.setBorder(new EmptyBorder(0,userInfoPanel.getPreferredSize().width/16,0,0));

        JLabel usernameLabel = new JLabel();
        usernameLabel.setText(UserData.getUserName());
        usernameLabel.setFont(CustomFont.getFont().deriveFont(getWidth()/50f));
        usernameLabel.setForeground(Color.WHITE);
        //usernameLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        userAge = new JLabel();
        userAge.setText("Age: "+UserData.getUserAge());
        userAge.setFont(CustomFont.getFont().deriveFont(getWidth()/60f));
        userAge.setForeground(Color.WHITE);
        //userAge.setAlignmentX(Component.CENTER_ALIGNMENT);

        userHeight = new JLabel();
        userHeight.setText("Height: "+ UserData.getUserHeight()+" Cm");
        userHeight.setFont(CustomFont.getFont().deriveFont(getWidth()/60f));
        userHeight.setForeground(Color.WHITE);
        //userHeight.setAlignmentX(Component.CENTER_ALIGNMENT);

        userWeight = new JLabel();
        userWeight.setText("Weight: "+ UserData.getUserWeight()+" Kg");
        userWeight.setFont(CustomFont.getFont().deriveFont(getWidth()/60f));
        userWeight.setForeground(Color.WHITE);
        //userWeight.setAlignmentX(Component.CENTER_ALIGNMENT);


        avatar.setLayout(new FlowLayout(FlowLayout.LEFT,0,0));
        avatar.setAlignmentX(JComponent.LEFT_ALIGNMENT);

        userInfoPanel.add(Box.createVerticalGlue());
        userInfoPanel.add(Box.createVerticalGlue());
        userInfoPanel.add(avatar);
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
                userInfoPanel.setBorder(new EmptyBorder(0,userInfoPanel.getPreferredSize().width/16,0,0));

                topPanel.setPreferredSize(new Dimension(getWidth(),(int)(getHeight()/3.171974)+getHeight()/20));

                usernameLabel.setFont(CustomFont.getFont().deriveFont(getWidth()/50f));
                userAge.setFont(CustomFont.getFont().deriveFont(getWidth()/60f));
                userHeight.setFont(CustomFont.getFont().deriveFont(getWidth()/60f));
                userWeight.setFont(CustomFont.getFont().deriveFont(getWidth()/60f));


                avatar.setPreferredSize(new Dimension(getWidth()/25,getWidth()/25));
                scaledProfilePicture = profilePictureIcon.getImage().getScaledInstance(getWidth()/25,getWidth()/25,Image.SCALE_SMOOTH);
                scaledProfilePictureIcon = new ImageIcon(scaledProfilePicture);
                avatar.setImage(scaledProfilePictureIcon);
                avatar.repaint();
                //profilePicture.setIcon(scaledProfilePictureIcon);
            }
        });
    }

    public static void updateUserInfo(){
        userAge.setText("Age: "+UserData.getUserAge());
        userHeight.setText("Height: "+ UserData.getUserHeight()+" Cm");
        userWeight.setText("Weight: "+ UserData.getUserWeight()+" Kg");

    }

    public static void updateProfilePicture(ImageIcon profilePicture){
        profilePictureIcon = profilePicture;
        avatar.setImage(new ImageIcon(profilePicture.getImage().getScaledInstance(avatar.getWidth(),avatar.getWidth(),Image.SCALE_SMOOTH)));
        try {
            FirebaseManager.writeDBprofilePicture(avatar.getImage());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        avatar.repaint();
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
