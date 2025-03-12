package se.aljr.application.homepage;

import se.aljr.application.*;
import se.aljr.application.Friends.Friend;
import se.aljr.application.Friends.FriendsList;
import se.aljr.application.loginpage.FirebaseManager;
import se.aljr.application.settings.SettingsPanel;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.io.IOException;

public class HomePanel extends JPanel {

    protected ImageIcon homePanelBackground;
    ImageIcon moduleIcon;
    protected ImageIcon scaledContentBackgroundPanel;
    Image scaledContentBackground;

    protected ImageIcon lightHomePanelBackground;
    protected ImageIcon scaledLightContentBackgroundPanel;
    Image scaledLightContentBackground;

    private Image scaledProfilePicture;
    private static ImageIcon profilePictureIcon;
    private static ImageIcon scaledProfilePictureIcon;

    private static ImageAvatar avatar;

    private static JLabel userAge;
    private static JLabel userHeight;
    private static JLabel userWeight;
    private static JLabel bmiLabel;
    private static JLabel bmrLabel;
    private static JLabel tdeeLabel;
    private static JLabel proteinNeedLabel;
    private static JPanel friendsPanel = new JPanel();
    private static JPanel friendsPanel1 = new JPanel();
    private static JScrollPane friendsListScrollPane;
    private static JScrollPane friendsListScrollPane1;
    private static HomePanel instance;
    private static final JLabel usernameLabel = new JLabel();

    public HomePanel(int width, int height){

        this.setPreferredSize(new Dimension(width, height));
        instance = this;
        homePanelBackground = new ImageIcon(ResourcePath.getResourcePath() + "bottom_right_bar.png");
        moduleIcon = new ImageIcon(ResourcePath.getResourcePath()+"module.png");
        scaledContentBackground = homePanelBackground.getImage().getScaledInstance(width,height,Image.SCALE_SMOOTH);
        scaledContentBackgroundPanel = new ImageIcon(scaledContentBackground);

        lightHomePanelBackground = new ImageIcon(ResourcePath.getResourcePath()+"bottom_right_bar_light.png");
        scaledLightContentBackground = lightHomePanelBackground.getImage().getScaledInstance(width,height,Image.SCALE_SMOOTH);
        scaledLightContentBackgroundPanel = new ImageIcon(scaledLightContentBackground);

        friendsListScrollPane = new JScrollPane(friendsPanel){
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                // Draw the image to fill the entire panel
                if (moduleIcon != null) {
                    Image scale = moduleIcon.getImage().getScaledInstance((int) (HomePanel.this.getPreferredSize().width/3.7695035461),
                            (int)(HomePanel.this.getPreferredSize().height/3.22292993631),
                            Image.SCALE_SMOOTH);
                    ImageIcon scaleImage = new ImageIcon(scale);
                    g.drawImage(scaleImage.getImage(), 0, 0, (int) (HomePanel.this.getPreferredSize().width/3.7695035461), (int)(HomePanel.this.getPreferredSize().height/3.22292993631), this);
                }
                else{
                    System.out.println("Error");
                }
            }
        };



        //profilePictureIcon = new ImageIcon(resourcePath + "Johan.png");
        profilePictureIcon = FirebaseManager.readDBprofilePicture(UserData.getEmail());
        if(profilePictureIcon==null){
            profilePictureIcon = new ImageIcon(ResourcePath.getResourcePath() + "agile_small_icon.png");
        }
        avatar = new ImageAvatar();
        avatar.setPreferredSize(new Dimension(getPreferredSize().width/25,getPreferredSize().width/25));
        scaledProfilePicture = profilePictureIcon.getImage().getScaledInstance(getPreferredSize().width/25,getPreferredSize().width/25,Image.SCALE_SMOOTH);
        scaledProfilePictureIcon = new ImageIcon(scaledProfilePicture);
        avatar.setImage(scaledProfilePictureIcon);


        init();
    }

    private void init(){
        this.setOpaque(false);
        this.setBackground(Color.BLACK);
        this.setLayout(new BorderLayout(0,0));

        System.out.println("HomePanel Width: "+getPreferredSize().width+" HomePanel Height :"+getPreferredSize().height);
        JPanel userInfoPanel = new JPanel(){
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                // Draw the image to fill the entire panel
                if (moduleIcon != null) {
                    Image scale = moduleIcon.getImage().getScaledInstance((int) (HomePanel.this.getPreferredSize().width/3.7695035461),
                            (int)(HomePanel.this.getPreferredSize().height/4.22292993631),
                            Image.SCALE_SMOOTH);
                    ImageIcon scaleImage = new ImageIcon(scale);
                    g.drawImage(scaleImage.getImage(), 0, 0, (int) (HomePanel.this.getPreferredSize().width/3.7695035461), (int)(HomePanel.this.getPreferredSize().height/4.22292993631), this);
                }
                else{
                    System.out.println("Error");
                }
            }
        };
        userInfoPanel.setOpaque(false);
        userInfoPanel.setPreferredSize(new Dimension((int) (getPreferredSize().width/3.7695035461),(int)(getPreferredSize().height/4.22292993631)));
        userInfoPanel.setMaximumSize(userInfoPanel.getPreferredSize());
        userInfoPanel.setLayout(new BoxLayout(userInfoPanel, BoxLayout.Y_AXIS));
        userInfoPanel.setBorder(new EmptyBorder(0,userInfoPanel.getPreferredSize().width/16,0,0));


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
        avatar.setBorderSize(instance.getPreferredSize().height/221);
        avatar.setBorderSpace((int) (instance.getPreferredSize().height/331.5));

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
                if (moduleIcon != null) {
                    Image scale = moduleIcon.getImage().getScaledInstance((int) (HomePanel.this.getPreferredSize().width/3.7695035461),
                            (int)(HomePanel.this.getPreferredSize().height/4.22292993631),
                            Image.SCALE_SMOOTH);
                    ImageIcon scaleImage = new ImageIcon(scale);
                    g.drawImage(scaleImage.getImage(), 0, 0, (int) (HomePanel.this.getPreferredSize().width/3.7695035461), (int)(HomePanel.this.getPreferredSize().height/4.22292993631), this);
                }
                else{
                    System.out.println("Error");
                }
            }
        };
        userMacrosPanel.setOpaque(false);
        userMacrosPanel.setLayout(new BoxLayout(userMacrosPanel,BoxLayout.Y_AXIS));
        userMacrosPanel.setPreferredSize(new Dimension((int) (HomePanel.this.getWidth()/3.7695035461),(int)(HomePanel.this.getHeight()/4.22292993631)));
        userMacrosPanel.setMaximumSize(userInfoPanel.getPreferredSize());
        userMacrosPanel.setBorder(new EmptyBorder(0,userInfoPanel.getPreferredSize().width/16,0,0));

        bmiLabel = new JLabel();
        bmiLabel.setText("BMI: "+ NutritionCalculator.getBmi(UserData.getUserWeight(), UserData.getUserHeight()));
        bmiLabel.setFont(CustomFont.getFont().deriveFont(getWidth()/50f));
        bmiLabel.setForeground(Color.WHITE);

        bmrLabel = new JLabel();
        bmrLabel.setText("BMR: "+ NutritionCalculator.getBMR(UserData.getUserWeight(), UserData.getUserHeight(),UserData.getUserAge())+" kcal");
        bmrLabel.setFont(CustomFont.getFont().deriveFont(getWidth()/50f));
        bmrLabel.setForeground(Color.WHITE);

        tdeeLabel = new JLabel();
        tdeeLabel.setText("TDEE: "+ NutritionCalculator.getTDEE(UserData.getUserWeight(), UserData.getUserHeight(),UserData.getUserAge(),UserData.getActivityFactor())+" kcal");
        tdeeLabel.setFont(CustomFont.getFont().deriveFont(getWidth()/50f));
        tdeeLabel.setForeground(Color.WHITE);

        proteinNeedLabel = new JLabel();
        proteinNeedLabel.setText("Protein: "+ NutritionCalculator.getProteinNeed(UserData.getUserWeight()));
        proteinNeedLabel.setFont(CustomFont.getFont().deriveFont(getWidth()/50f));
        proteinNeedLabel.setForeground(Color.WHITE);

        JLabel macroLabel = new JLabel();
        macroLabel.setText("MACROS");
        macroLabel.setFont(CustomFont.getFont().deriveFont(getWidth()/40f));
        macroLabel.setForeground(Color.WHITE);
        //macroLabel.setAlignmentX(Component.CENTER_ALIGNMENT);



        userMacrosPanel.add(Box.createVerticalGlue());
        userMacrosPanel.add(Box.createVerticalGlue());
        userMacrosPanel.add(macroLabel);
        userMacrosPanel.add(Box.createVerticalGlue());
        userMacrosPanel.add(bmiLabel);
        userMacrosPanel.add(bmrLabel);
        userMacrosPanel.add(tdeeLabel);
        userMacrosPanel.add(proteinNeedLabel);
        userMacrosPanel.add(Box.createVerticalGlue());

        JPanel topPanel = new JPanel();
        topPanel.setPreferredSize(new Dimension(getPreferredSize().width,(int)(getPreferredSize().height/3.171974)+getPreferredSize().height/20));
        topPanel.setOpaque(false);
        topPanel.setLayout(new BoxLayout(topPanel,BoxLayout.X_AXIS));

        JPanel bottomPanel = new JPanel();
        bottomPanel.setPreferredSize(new Dimension(getPreferredSize().width,getPreferredSize().height-topPanel.getPreferredSize().height));
        bottomPanel.setOpaque(false);
        bottomPanel.setBackground(Color.green);
        bottomPanel.setLayout(new BoxLayout(bottomPanel,BoxLayout.X_AXIS));


        friendsPanel = new JPanel();
        friendsPanel.setLayout(new BoxLayout(friendsPanel, BoxLayout.Y_AXIS));
        friendsPanel.setOpaque(false);


        //friendsListScrollPane.setBackground(new Color(104, 8, 218, 255));
        friendsListScrollPane.setPreferredSize(new Dimension((int) (HomePanel.this.getPreferredSize().width/3.7695035461), (int)(HomePanel.this.getPreferredSize().height/3.22292993631)));
        friendsListScrollPane.setOpaque(false);
        friendsListScrollPane.setMinimumSize(friendsListScrollPane.getPreferredSize());
        friendsListScrollPane.setMaximumSize(friendsListScrollPane.getPreferredSize());
        friendsListScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        friendsListScrollPane.getVerticalScrollBar().setPreferredSize(new Dimension(0, 0));
        friendsListScrollPane.getViewport().setOpaque(false);
        friendsListScrollPane.setBorder(null);
        friendsListScrollPane.getVerticalScrollBar().setUnitIncrement(getPreferredSize().width/140);

        updateFriends();
        try {
            FirebaseManager.readDBlistenToClientUserFriendsList();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        friendsPanel1 = new JPanel();
        friendsPanel1.setLayout(new BoxLayout(friendsPanel1, BoxLayout.Y_AXIS));
        friendsPanel1.setOpaque(false);


        friendsListScrollPane1 = new JScrollPane(friendsPanel1){
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                // Draw the image to fill the entire panel
                if (moduleIcon != null) {
                    Image scale = moduleIcon.getImage().getScaledInstance((int) (HomePanel.this.getPreferredSize().width/3.7695035461),
                            (int)(HomePanel.this.getPreferredSize().height/3.22292993631),
                            Image.SCALE_SMOOTH);
                    ImageIcon scaleImage = new ImageIcon(scale);
                    g.drawImage(scaleImage.getImage(), 0, 0, (int) (HomePanel.this.getPreferredSize().width/3.7695035461), (int)(HomePanel.this.getPreferredSize().height/3.22292993631), this);
                }
                else{
                    System.out.println("Error");
                }
            }
        };
        friendsListScrollPane1.setPreferredSize(new Dimension((int) (HomePanel.this.getPreferredSize().width/3.7695035461), (int)(HomePanel.this.getPreferredSize().height/3.22292993631)));
        friendsListScrollPane1.setOpaque(false);
        friendsListScrollPane1.setMinimumSize(friendsListScrollPane1.getPreferredSize());
        friendsListScrollPane1.setMaximumSize(friendsListScrollPane1.getPreferredSize());
        friendsListScrollPane1.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        friendsListScrollPane1.getVerticalScrollBar().setPreferredSize(new Dimension(0, 0));
        friendsListScrollPane1.getViewport().setOpaque(false);
        friendsListScrollPane1.setBorder(null);
        friendsListScrollPane1.getVerticalScrollBar().setUnitIncrement(getPreferredSize().width/140);
        friendsListScrollPane1.repaint();


        bottomPanel.add(Box.createHorizontalGlue());
        bottomPanel.add(friendsListScrollPane);
        bottomPanel.add(Box.createHorizontalGlue());
        bottomPanel.add(Box.createHorizontalGlue());
        bottomPanel.add(Box.createHorizontalGlue());
        bottomPanel.add(Box.createHorizontalGlue());
        bottomPanel.add(Box.createHorizontalGlue());
        bottomPanel.add(Box.createHorizontalGlue());
        bottomPanel.add(Box.createHorizontalGlue());
        bottomPanel.add(Box.createHorizontalGlue());
        bottomPanel.add(friendsListScrollPane1);
        bottomPanel.add(Box.createHorizontalGlue());




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
        this.add(bottomPanel, BorderLayout.SOUTH);
        this.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                   SwingUtilities.invokeLater(()->{
                       userInfoPanel.repaint();
                       userInfoPanel.setPreferredSize(new Dimension((int) (HomePanel.this.getWidth()/3.7695035461),(int)(HomePanel.this.getHeight()/4.22292993631)));
                       userInfoPanel.setMaximumSize(userInfoPanel.getPreferredSize());
                       userInfoPanel.setBorder(new EmptyBorder(0,userInfoPanel.getPreferredSize().width/16,0,0));

                       topPanel.setPreferredSize(new Dimension(getWidth(),(int)(getHeight()/3.171974)+getHeight()/20));

                       usernameLabel.setFont(CustomFont.getFont().deriveFont(getWidth()/50f));
                       userAge.setFont(CustomFont.getFont().deriveFont(getWidth()/60f));
                       userHeight.setFont(CustomFont.getFont().deriveFont(getWidth()/60f));
                       userWeight.setFont(CustomFont.getFont().deriveFont(getWidth()/60f));

                       userMacrosPanel.repaint();
                       userMacrosPanel.setPreferredSize(new Dimension((int) (HomePanel.this.getWidth()/3.7695035461),(int)(HomePanel.this.getHeight()/4.22292993631)));
                       userMacrosPanel.setMaximumSize(userMacrosPanel.getPreferredSize());
                       userMacrosPanel.setBorder(new EmptyBorder(0,userMacrosPanel.getPreferredSize().width/16,0,0));

                       bmiLabel.setFont(CustomFont.getFont().deriveFont(getWidth()/50f));
                       bmrLabel.setFont(CustomFont.getFont().deriveFont(getWidth()/50f));
                       tdeeLabel.setFont(CustomFont.getFont().deriveFont(getWidth()/50f));
                       proteinNeedLabel.setFont(CustomFont.getFont().deriveFont(getWidth()/50f));
                       macroLabel.setFont(CustomFont.getFont().deriveFont(getWidth()/40f));



                       avatar.setPreferredSize(new Dimension(getWidth()/25,getWidth()/25));
                       scaledProfilePicture = profilePictureIcon.getImage().getScaledInstance(getWidth()/25,getWidth()/25,Image.SCALE_SMOOTH);
                       scaledProfilePictureIcon = new ImageIcon(scaledProfilePicture);
                       avatar.setImage(scaledProfilePictureIcon);
                       avatar.repaint();
                       //profilePicture.setIcon(scaledProfilePictureIcon);

                       friendsListScrollPane1.repaint();
                       friendsListScrollPane1.setPreferredSize(new Dimension((int) (HomePanel.this.getPreferredSize().width/3.7695035461), (int)(HomePanel.this.getPreferredSize().height/3.22292993631)));
                       friendsListScrollPane1.setMinimumSize(friendsListScrollPane1.getPreferredSize());
                       friendsListScrollPane1.setMaximumSize(friendsListScrollPane1.getPreferredSize());
                       friendsListScrollPane1.getVerticalScrollBar().setUnitIncrement(getPreferredSize().width/140);

                       friendsListScrollPane.repaint();
                       friendsListScrollPane.setPreferredSize(new Dimension((int) (HomePanel.this.getPreferredSize().width/3.7695035461), (int)(HomePanel.this.getPreferredSize().height/3.22292993631)));
                       friendsListScrollPane.setMinimumSize(friendsListScrollPane1.getPreferredSize());
                       friendsListScrollPane.setMaximumSize(friendsListScrollPane1.getPreferredSize());
                       friendsListScrollPane.getVerticalScrollBar().setUnitIncrement(getPreferredSize().width/140);

                       int friendCounter=0;
                       for(Component comp: friendsPanel.getComponents()){
                           if(comp.getName()!=null){
                               if(comp.getName().equals("friendPanel")){
                                   JPanel friendPanel = (JPanel) comp;
                                   friendPanel.setPreferredSize(new Dimension(friendsListScrollPane.getPreferredSize().width, (int) (FriendsList.getFriendArrayList().get(friendCounter).getImageAvatar().getPreferredSize().height*1.1)));
                                   for(Component comp1 : friendPanel.getComponents()){
                                       if(comp1.getName()!=null){
                                           if(comp1.getName().equals("friendAvatarPanel")){
                                               JPanel friendAvatarPanel = (JPanel) comp1;
                                               friendAvatarPanel.setPreferredSize(new Dimension((int) (FriendsList.getFriendArrayList().get(friendCounter).getImageAvatar().getPreferredSize().width*1.3),
                                                       FriendsList.getFriendArrayList().get(friendCounter).getImageAvatar().getPreferredSize().height));
                                               friendAvatarPanel.setMaximumSize(friendAvatarPanel.getPreferredSize());

                                           }
                                           if(comp1.getName().equals("friendNameLabel")){
                                               JLabel friendNameLabel = (JLabel) comp1;
                                               friendNameLabel.setFont(CustomFont.getFont().deriveFont(instance.getPreferredSize().width/50f));
                                               friendNameLabel.setMaximumSize(new Dimension(friendsListScrollPane.getPreferredSize().width-FriendsList.getFriendArrayList().get(friendCounter).getImageAvatar().getPreferredSize().width,friendPanel.getPreferredSize().height));
                                           }
                                       }
                                   }
                               }
                               Friend friend = FriendsList.getFriendArrayList().get(friendCounter);
                               Image scaledFriendProfilePicture = friend.getProfilePicture().getImage().getScaledInstance(instance.getWidth()/25,instance.getWidth()/25,Image.SCALE_SMOOTH);
                               ImageIcon scaledFriendProfilePictureIcon = new ImageIcon(scaledFriendProfilePicture);

                               friend.getImageAvatar().setPreferredSize(new Dimension(instance.getWidth()/25,instance.getWidth()/25));
                               friend.getImageAvatar().setMinimumSize(friend.getImageAvatar().getPreferredSize());
                               friend.getImageAvatar().setMaximumSize(friend.getImageAvatar().getPreferredSize());
                               friend.getImageAvatar().setImage(scaledFriendProfilePictureIcon);
                               friend.getImageAvatar().setBorderSize(instance.getHeight()/221);
                               friend.getImageAvatar().setBorderSpace((int) (instance.getHeight()/331.5));
                           }
                           friendCounter++;
                       }
                   });

            }
        });
    }

    public static void updateUserInfo(){

        usernameLabel.setText(UserData.getUserName());
        userAge.setText("Age: "+UserData.getUserAge());
        userHeight.setText("Height: "+ UserData.getUserHeight()+" Cm");
        userWeight.setText("Weight: "+ UserData.getUserWeight()+" Kg");
        bmiLabel.setText("BMI: "+ NutritionCalculator.getBmi(UserData.getUserWeight(), UserData.getUserHeight()));
        bmrLabel.setText("BMR: "+ NutritionCalculator.getBMR(UserData.getUserWeight(), UserData.getUserHeight(),UserData.getUserAge())+" kcal");
        tdeeLabel.setText("TDEE: "+ NutritionCalculator.getTDEE(UserData.getUserWeight(), UserData.getUserHeight(),UserData.getUserAge(),UserData.getActivityFactor())+" kcal");
        proteinNeedLabel.setText("Protein: "+ NutritionCalculator.getProteinNeed(UserData.getUserWeight()));


    }

    public static void updateFriends(){
        friendsPanel.removeAll();
        FriendsList.getFriendArrayList().clear();
        FirebaseManager.readDBfriends(UserData.getEmail(),false);
        try {
            FirebaseManager.readDBlistenToFriendsOnlineStatus();
        } catch (Exception e) {
            e.printStackTrace();
        }

        for(Friend friend:FriendsList.getFriendArrayList()){
            ImageIcon userIcon = FirebaseManager.readDBprofilePicture(friend.getFriendEmail());
            friend.setProfilePicture(userIcon);
            Image scaledFriendProfilePicture = userIcon.getImage().getScaledInstance(instance.getPreferredSize().width/25,instance.getPreferredSize().width/25,Image.SCALE_SMOOTH);
            ImageIcon scaledFriendProfilePictureIcon = new ImageIcon(scaledFriendProfilePicture);

            friend.getImageAvatar().setPreferredSize(new Dimension(instance.getPreferredSize().width/25,instance.getPreferredSize().width/25));
            friend.getImageAvatar().setMinimumSize(friend.getImageAvatar().getPreferredSize());
            friend.getImageAvatar().setMaximumSize(friend.getImageAvatar().getPreferredSize());
            friend.getImageAvatar().setImage(scaledFriendProfilePictureIcon);
            friend.getImageAvatar().setAlignmentY(Component.CENTER_ALIGNMENT);
            friend.getImageAvatar().setBorderSize(instance.getPreferredSize().height/221);
            friend.getImageAvatar().setBorderSpace((int) (instance.getPreferredSize().height/331.5));



            JPanel friendPanel = new JPanel();
            friendPanel.setOpaque(false);
            friendPanel.setName("friendPanel");
            friendPanel.setPreferredSize(new Dimension(friendsListScrollPane.getPreferredSize().width, (int) (friend.getImageAvatar().getPreferredSize().height*1.1)));
            friendPanel.setLayout(new BoxLayout(friendPanel,BoxLayout.X_AXIS));

            JLabel friendNameLabel = new JLabel(FriendsList.getFriendArrayList().get(FriendsList.getFriendArrayList().indexOf(friend)).getFriendName());
            friendNameLabel.setName("friendNameLabel");
            friendNameLabel.setFont(CustomFont.getFont().deriveFont(instance.getPreferredSize().width/50f));
            friendNameLabel.setMaximumSize(new Dimension(friendsListScrollPane.getPreferredSize().width-friend.getImageAvatar().getPreferredSize().width,friendPanel.getPreferredSize().height));
            friendNameLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
            friendNameLabel.setForeground(Color.WHITE);

            JPanel friendAvatarPanel = new JPanel();
            friendAvatarPanel.setName("friendAvatarPanel");
            friendAvatarPanel.setOpaque(false);
            friendAvatarPanel.setLayout(new BoxLayout(friendAvatarPanel,BoxLayout.X_AXIS));
            friendAvatarPanel.setPreferredSize(new Dimension((int) (friend.getImageAvatar().getPreferredSize().width*1.3),friend.getImageAvatar().getPreferredSize().height));
            friendAvatarPanel.setMaximumSize(friendAvatarPanel.getPreferredSize());
            friendAvatarPanel.setAlignmentY(Component.CENTER_ALIGNMENT);

            friendAvatarPanel.add(Box.createHorizontalGlue());
            friendAvatarPanel.add(FriendsList.getFriendArrayList().get(FriendsList.getFriendArrayList().indexOf(friend)).getImageAvatar());
            friendAvatarPanel.add(Box.createHorizontalGlue());

            friendPanel.add(friendAvatarPanel);
            friendPanel.add(friendNameLabel);
            friendPanel.add(Box.createHorizontalGlue());

            friendsPanel.add(friendPanel);
            System.out.println("ADDED");


        }
        friendsListScrollPane.setViewportView(friendsPanel);
    }

    public static void updateProfilePicture(ImageIcon profilePicture){
        profilePictureIcon = profilePicture;
        avatar.setImage(new ImageIcon(profilePicture.getImage().getScaledInstance(avatar.getWidth(),avatar.getWidth(),Image.SCALE_SMOOTH)));
        try {
            FirebaseManager.writeDBprofilePicture(new ImageIcon(profilePicture.getImage().getScaledInstance(43,43,Image.SCALE_SMOOTH)));
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
            if(!SettingsPanel.lightMode){


                g.drawImage(scaledContentBackgroundPanel.getImage(), 0, 0, getWidth(), getHeight(), this);
            }else{

                g.drawImage(scaledLightContentBackgroundPanel.getImage(), 0, 0, getWidth(), getHeight(), this);
            }

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
