package se.aljr.application.chatpanel;

import se.aljr.application.AppThemeColors;
import se.aljr.application.CustomFont;
import se.aljr.application.Friends.Friend;
import se.aljr.application.Friends.FriendsList;
import se.aljr.application.UserData;
import se.aljr.application.loginpage.FirebaseManager;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.HashMap;
import java.util.Map;

public class ChatPanel extends JPanel {

    ImageIcon settingsPanelBackground;
    Image scaleSettingsPanelBackground;
    ImageIcon scaledSettingsPanelBackgroundIcon;

    private String resourcePath;
    private boolean addPanelIsActive;
    private static JPanel requestsPanel = new JPanel();
    private static JScrollPane friendsScrollPane = new JScrollPane();
    private static ChatPanel instance;

    public ChatPanel(int width, int height) {
        resourcePath = getClass().getClassLoader().getResource("resource.path").getPath().replace("resource.path","");
        settingsPanelBackground = new ImageIcon(resourcePath+"emptyBackground.png");
        scaleSettingsPanelBackground = settingsPanelBackground.getImage().getScaledInstance(width,height, Image.SCALE_SMOOTH);
        scaledSettingsPanelBackgroundIcon = new ImageIcon(scaleSettingsPanelBackground);


        this.setPreferredSize(new Dimension(width,height));
        this.setBackground(new Color(31, 31, 31));
        this.setLayout(new BorderLayout(0,0));
        this.setBorder(new EmptyBorder(15,15,15,15));


        instance = this;
        init();
    }

    private void init(){



        /*--------------------Main panel--------------------*/
        JPanel mainPanel = new JPanel();
        mainPanel.setOpaque(false);
        mainPanel.setPreferredSize(new Dimension(getPreferredSize().width, getPreferredSize().height));
        mainPanel.setLayout(new BoxLayout(mainPanel,BoxLayout.X_AXIS));




        /*--------------------Right panel--------------------*/
        JPanel mainRightPanel = new JPanel();
        mainRightPanel.setLayout(new BorderLayout());
        mainRightPanel.setOpaque(true);
        mainRightPanel.setBackground(Color.RED);
        mainRightPanel.setPreferredSize(new Dimension(getPreferredSize().width / 2, getPreferredSize().height));
        mainRightPanel.setMinimumSize(mainRightPanel.getPreferredSize());
        mainRightPanel.setMaximumSize(mainRightPanel.getPreferredSize());

        JPanel rightBelowPanel = new JPanel();
        rightBelowPanel.setLayout(new BoxLayout(rightBelowPanel, BoxLayout.Y_AXIS));
        rightBelowPanel.setOpaque(true);
        rightBelowPanel.setBackground(Color.ORANGE);
        rightBelowPanel.setPreferredSize(new Dimension((int)(mainRightPanel.getPreferredSize().width), mainRightPanel.getPreferredSize().height/8));
        rightBelowPanel.setMinimumSize(mainRightPanel.getPreferredSize());
        rightBelowPanel.setMaximumSize(mainRightPanel.getPreferredSize());

        JPanel leftSideBelowPanel = new JPanel();
        leftSideBelowPanel.setOpaque(true);
        leftSideBelowPanel.setBackground(Color.BLUE);
        leftSideBelowPanel.setPreferredSize(new Dimension((int)(rightBelowPanel.getPreferredSize().width/8), rightBelowPanel.getPreferredSize().height));
        leftSideBelowPanel.setMinimumSize(leftSideBelowPanel.getPreferredSize());
        leftSideBelowPanel.setMaximumSize(leftSideBelowPanel.getPreferredSize());

        JPanel rightSideBelowPanel = new JPanel();
        rightSideBelowPanel.setOpaque(true);
        rightSideBelowPanel.setBackground(Color.RED);
        rightSideBelowPanel.setPreferredSize(new Dimension((int)(rightBelowPanel.getPreferredSize().width/4), rightBelowPanel.getPreferredSize().height));
        rightSideBelowPanel.setMinimumSize(rightSideBelowPanel.getPreferredSize());
        rightSideBelowPanel.setMaximumSize(rightSideBelowPanel.getPreferredSize());

        JPanel middleBelowPanel = new JPanel();
        middleBelowPanel.setOpaque(true);
        middleBelowPanel.setBackground(Color.WHITE);
        middleBelowPanel.setPreferredSize(new Dimension((int)(rightBelowPanel.getPreferredSize().width/8), rightBelowPanel.getPreferredSize().height));
        middleBelowPanel.setMinimumSize(middleBelowPanel.getPreferredSize());
        middleBelowPanel.setMaximumSize(middleBelowPanel.getPreferredSize());


//        JTextField messengerTextBox = new JTextField();
//        messengerTextBox.setFont(new Font("Arial", Font.BOLD, (int) (getPreferredSize().width / 100f)));
//        messengerTextBox.setPreferredSize(new Dimension((int)(rightBelowPanel.getPreferredSize().width / 1.5),(int)(rightBelowPanel.getPreferredSize().height / 2)));
//        messengerTextBox.setMinimumSize(messengerTextBox.getPreferredSize());
//        messengerTextBox.setMaximumSize(messengerTextBox.getPreferredSize());
//        messengerTextBox.setForeground(AppThemeColors.foregroundColor);
//        messengerTextBox.setBackground(AppThemeColors.textFieldColor);


















        /*--------------------Middle panel--------------------*/
        JPanel mainMiddlePanel = new JPanel();
        mainMiddlePanel.setOpaque(false);
        mainMiddlePanel.setLayout(new BoxLayout(mainMiddlePanel, BoxLayout.X_AXIS));
        mainMiddlePanel.setPreferredSize(new Dimension(getPreferredSize().width/5,getPreferredSize().height));
        mainMiddlePanel.setMinimumSize(mainMiddlePanel.getPreferredSize());
        mainMiddlePanel.setMaximumSize(mainMiddlePanel.getPreferredSize());









        /*--------------------(Middle panel) Different scrolls each button--------------------*/

        friendsScrollPane.setPreferredSize(new Dimension(mainMiddlePanel.getPreferredSize().width,mainMiddlePanel.getPreferredSize().height));
        friendsScrollPane.setMinimumSize(friendsScrollPane.getPreferredSize());
        friendsScrollPane.setMaximumSize(friendsScrollPane.getPreferredSize());
        friendsScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        friendsScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        friendsScrollPane.getVerticalScrollBar().setPreferredSize(new Dimension(0,0));
        friendsScrollPane.getVerticalScrollBar().setUnitIncrement(6);
        friendsScrollPane.getViewport().setBackground(AppThemeColors.panelColor);
        friendsScrollPane.setBorder(new LineBorder(Color.BLACK));







        JPanel friendsPanel = new JPanel();
        friendsPanel.setLayout(new BoxLayout(friendsPanel, BoxLayout.Y_AXIS));
        friendsPanel.setOpaque(false);

        for(Friend friend: FriendsList.getFriendArrayList()){
            ImageIcon userIcon = FirebaseManager.readDBprofilePicture(friend.getFriendEmail());
            Image scaledFriendProfilePicture = userIcon.getImage().getScaledInstance(getPreferredSize().width/25,getPreferredSize().width/25,Image.SCALE_SMOOTH);
            ImageIcon scaledFriendProfilePictureIcon = new ImageIcon(scaledFriendProfilePicture);

            friend.getImageAvatarSocial().setPreferredSize(new Dimension(getPreferredSize().width/25,getPreferredSize().width/25));
            friend.getImageAvatarSocial().setMinimumSize(friend.getImageAvatarSocial().getPreferredSize());
            friend.getImageAvatarSocial().setMaximumSize(friend.getImageAvatarSocial().getPreferredSize());
            friend.getImageAvatarSocial().setImage(scaledFriendProfilePictureIcon);
            friend.getImageAvatarSocial().setAlignmentY(Component.CENTER_ALIGNMENT);



            JPanel friendPanel = new JPanel();
            friendPanel.setOpaque(false);
            friendPanel.setPreferredSize(new Dimension(friendsScrollPane.getPreferredSize().width, (int) (friend.getImageAvatarSocial().getPreferredSize().height*1.1)));
            friendPanel.setLayout(new BoxLayout(friendPanel,BoxLayout.X_AXIS));
            friendPanel.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    super.mouseClicked(e);
                    System.out.println(friend.getFriendName());
                }
            });

            JLabel friendNameLabel = new JLabel(FriendsList.getFriendArrayList().get(FriendsList.getFriendArrayList().indexOf(friend)).getFriendName());
            friendNameLabel.setFont(CustomFont.getFont().deriveFont(getPreferredSize().width/65f));
            friendNameLabel.setMaximumSize(new Dimension(friendsScrollPane.getPreferredSize().width-friend.getImageAvatarSocial().getPreferredSize().width,friendPanel.getPreferredSize().height));
            friendNameLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
            friendNameLabel.setForeground(AppThemeColors.foregroundColor);

            JPanel friendAvatarPanel = new JPanel();
            friendAvatarPanel.setOpaque(false);
            friendAvatarPanel.setLayout(new BoxLayout(friendAvatarPanel,BoxLayout.X_AXIS));
            friendAvatarPanel.setPreferredSize(new Dimension((int) (friend.getImageAvatarSocial().getPreferredSize().width*1.3),friend.getImageAvatarSocial().getPreferredSize().height));
            friendAvatarPanel.setMaximumSize(friendAvatarPanel.getPreferredSize());
            friendAvatarPanel.setAlignmentY(Component.CENTER_ALIGNMENT);

            friendAvatarPanel.add(Box.createHorizontalGlue());
            friendAvatarPanel.add(FriendsList.getFriendArrayList().get(FriendsList.getFriendArrayList().indexOf(friend)).getImageAvatarSocial());
            friendAvatarPanel.add(Box.createHorizontalGlue());

            friendPanel.add(friendAvatarPanel);
            friendPanel.add(friendNameLabel);
            friendPanel.add(Box.createHorizontalGlue());

            friendsPanel.add(friendPanel);


        }








        requestsPanel.setLayout(new BoxLayout(requestsPanel, BoxLayout.Y_AXIS));
        requestsPanel.setOpaque(false);

        updateRequestsPanel();








        JPanel groupsPanel = new JPanel();
        groupsPanel.setLayout(new BoxLayout(groupsPanel, BoxLayout.Y_AXIS));

        for (int i = 1; i <= 25; i++) {
            JButton allGroupButtons = new JButton("Group " + i);
            allGroupButtons.setFont(new Font("Arial", Font.BOLD, 50));
            allGroupButtons.setBorder(new LineBorder(Color.BLACK));
            allGroupButtons.setBackground(Color.LIGHT_GRAY);
            allGroupButtons.setPreferredSize(new Dimension(friendsScrollPane.getPreferredSize().width, 50));
            allGroupButtons.setMinimumSize(allGroupButtons.getPreferredSize());
            allGroupButtons.setMaximumSize(allGroupButtons.getPreferredSize());
            groupsPanel.add(allGroupButtons);
        }







        JPanel addPanel = new JPanel();
        addPanel.setLayout(new BoxLayout(addPanel, BoxLayout.Y_AXIS));
        addPanel.setPreferredSize(new Dimension(mainMiddlePanel.getPreferredSize().width,mainMiddlePanel.getPreferredSize().height / 4));
        addPanel.setMinimumSize(addPanel.getPreferredSize());
        addPanel.setMaximumSize(addPanel.getPreferredSize());
        addPanel.setBackground(AppThemeColors.panelColor);
        addPanel.setAlignmentY(Component.CENTER_ALIGNMENT);

        /*--------------------Add panel components--------------------*/



        JTextField friendRequestMailText = new JTextField();
        friendRequestMailText.setFont(new Font("Arial", Font.BOLD, (int) (getPreferredSize().width / 100f)));
        friendRequestMailText.setPreferredSize(new Dimension((int)(addPanel.getPreferredSize().width*0.9),(int)(addPanel.getPreferredSize().height*0.15)));
        friendRequestMailText.setMinimumSize(friendRequestMailText.getPreferredSize());
        friendRequestMailText.setMaximumSize(friendRequestMailText.getPreferredSize());
        friendRequestMailText.setForeground(AppThemeColors.foregroundColor);
        friendRequestMailText.setBackground(AppThemeColors.textFieldColor);



        JButton addbutton = new JButton("ADD");
        addbutton.setFont(new Font("Arial", Font.BOLD, 50));
        addbutton.setBorder(new LineBorder(Color.BLACK));
        addbutton.setBackground(AppThemeColors.buttonBG);
        addbutton.setForeground(AppThemeColors.foregroundColor);
        addbutton.setPreferredSize(new Dimension(friendsScrollPane.getPreferredSize().width/3, 50));
        addbutton.setMinimumSize(addbutton.getPreferredSize());
        addbutton.setMaximumSize(addbutton.getPreferredSize());
        addbutton.setAlignmentX(Component.CENTER_ALIGNMENT);
        addbutton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                FirebaseManager.writeDBsendFriendRequest(friendRequestMailText.getText());
                friendRequestMailText.setText("");
            }
        });



        addPanel.add(Box.createVerticalGlue());
        addPanel.add(friendRequestMailText);
        addPanel.add(addbutton);
        addPanel.add(Box.createVerticalGlue());







        /*--------------------Left panel--------------------*/
        JPanel mainLeftPanel = new JPanel();
        mainLeftPanel.setOpaque(true);
        //mainLeftPanel.setBackground(AppThemeColors.panelColor);
        mainLeftPanel.setPreferredSize(new Dimension((int)(getPreferredSize().width / 5), getPreferredSize().height));
        mainLeftPanel.setMinimumSize(mainLeftPanel.getPreferredSize());
        mainLeftPanel.setMaximumSize(mainLeftPanel.getPreferredSize());
        mainLeftPanel.setLayout(new BorderLayout());





        /*--------------------(Left panel) Buttons Panel--------------------*/
        JPanel buttonlayout = new JPanel();
        buttonlayout.setOpaque(true);
        buttonlayout.setBackground(AppThemeColors.panelColor);
        buttonlayout.setLayout(new BoxLayout(buttonlayout, BoxLayout.Y_AXIS));
        buttonlayout.setPreferredSize(new Dimension(mainLeftPanel.getPreferredSize().width, mainLeftPanel.getPreferredSize().height));
        buttonlayout.setMinimumSize(buttonlayout.getPreferredSize());
        buttonlayout.setMaximumSize(buttonlayout.getPreferredSize());



        /*--------------------(Left panel) Buttons--------------------*/
        JButton friendsButton = new JButton();
        friendsButton.setOpaque(true);
        friendsButton.setBackground(AppThemeColors.buttonBG);
        friendsButton.setForeground(AppThemeColors.foregroundColor);
        friendsButton.setPreferredSize(new Dimension(buttonlayout.getPreferredSize().width, buttonlayout.getPreferredSize().height/10));
        friendsButton.setMinimumSize(friendsButton.getPreferredSize());
        friendsButton.setMaximumSize(friendsButton.getPreferredSize());
        friendsButton.setText("Friends");
        friendsButton.setFont(new Font("Arial", Font.BOLD, 50));
        friendsButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                mainMiddlePanel.add(friendsScrollPane);
                mainMiddlePanel.remove(addPanel);
                friendsScrollPane.setViewportView(friendsPanel);
                addPanelIsActive = false;
                revalidate();
                repaint();
            }
        });

        JButton requestButton = new JButton();
        requestButton.setOpaque(true);
        requestButton.setBackground(AppThemeColors.buttonBG);
        requestButton.setForeground(AppThemeColors.foregroundColor);
        requestButton.setPreferredSize(new Dimension(buttonlayout.getPreferredSize().width, buttonlayout.getPreferredSize().height/10));
        requestButton.setMinimumSize(requestButton.getPreferredSize());
        requestButton.setMaximumSize(requestButton.getPreferredSize());
        requestButton.setText("Requests");
        requestButton.setFont(new Font("Arial", Font.BOLD, 50));
        requestButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                mainMiddlePanel.add(friendsScrollPane);
                mainMiddlePanel.remove(addPanel);
                friendsScrollPane.setViewportView(requestsPanel);
                addPanelIsActive = false;
                revalidate();
                repaint();
            }
        });

        JButton groupButton = new JButton();
        groupButton.setOpaque(true);
        groupButton.setBackground(AppThemeColors.buttonBG);
        groupButton.setForeground(AppThemeColors.foregroundColor);
        groupButton.setPreferredSize(new Dimension(buttonlayout.getPreferredSize().width, buttonlayout.getPreferredSize().height/10));
        groupButton.setMinimumSize(groupButton.getPreferredSize());
        groupButton.setMaximumSize(groupButton.getPreferredSize());
        groupButton.setText("Groups");
        groupButton.setFont(new Font("Arial", Font.BOLD, 50));
        groupButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                mainMiddlePanel.add(friendsScrollPane);
                mainMiddlePanel.remove(addPanel);
                friendsScrollPane.setViewportView(groupsPanel);
                addPanelIsActive = false;
                revalidate();
                repaint();
            }
        });

        JButton addButton = new JButton();
        addButton.setOpaque(true);
        addButton.setBackground(AppThemeColors.buttonBG);
        addButton.setForeground(AppThemeColors.foregroundColor);
        addButton.setPreferredSize(new Dimension(buttonlayout.getPreferredSize().width, buttonlayout.getPreferredSize().height/10));
        addButton.setMinimumSize(addButton.getPreferredSize());
        addButton.setMaximumSize(addButton.getPreferredSize());
        addButton.setText("Add");
        addButton.setFont(new Font("Arial", Font.BOLD, 50));
        addButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(!addPanelIsActive){
                    mainMiddlePanel.add(addPanel,1);
                    addPanelIsActive = true;
                }
                try {
                    mainMiddlePanel.remove(friendsScrollPane);
                } catch (Exception ex) {
                }
                revalidate();
                repaint();
            }
        });











        /*--------------------ALL PANELS ADDS--------------------*/




        /*--------------------(Left panel) add panels to Left panel--------------------*/
        mainLeftPanel.add(buttonlayout);
        addPanel.add(addButton);



        /*--------------------(Left panel) add buttons to buttonlayout--------------------*/
        buttonlayout.add(Box.createVerticalGlue());
        buttonlayout.add(friendsButton);
        buttonlayout.add(Box.createVerticalGlue());
        buttonlayout.add(requestButton);
        buttonlayout.add(Box.createVerticalGlue());
        buttonlayout.add(groupButton);
        buttonlayout.add(Box.createVerticalGlue());
        buttonlayout.add(addButton);
        buttonlayout.add(Box.createVerticalGlue());




        /*--------------------(Middle panel) add panels to Middle panel--------------------*/
        mainMiddlePanel.add(friendsScrollPane);







        /*--------------------(Right panel) add panels to Right panel--------------------*/





        /*--------------------(Right panel) Chatlog--------------------*/
        mainRightPanel.add(leftSideBelowPanel, BorderLayout.SOUTH);

        rightBelowPanel.add(leftSideBelowPanel);
        rightBelowPanel.add(middleBelowPanel);
        rightBelowPanel.add(rightSideBelowPanel);




        /*--------------------(Main panel) add panels to Main panel--------------------*/
        mainPanel.add(Box.createHorizontalGlue());
        mainPanel.add(mainLeftPanel);
        mainPanel.add(Box.createHorizontalGlue());
        mainPanel.add(mainMiddlePanel);
        mainPanel.add(Box.createHorizontalGlue());
        mainPanel.add(mainRightPanel);
        mainPanel.add(Box.createHorizontalGlue());
        /*--------------------add main panel to chatpanel--------------------*/
        this.add(mainPanel);
        this.revalidate();
        this.repaint();
    }


    public static void updateRequestsPanel(){
        requestsPanel.removeAll();
        FriendRequestList.getFriendRequestList().clear();
        HashMap<String,String> friendRequestHashMap = FirebaseManager.readDBgetFriendRequests(UserData.getEmail());
        for(Map.Entry<String,String> entry : friendRequestHashMap.entrySet()){
            FriendRequestList.getFriendRequestList().add(new FriendRequest(){
                {
                    setFriendEmail(entry.getKey());
                    setFriendName(entry.getValue());
                }
            });
        }

        for(FriendRequest friendRequest: FriendRequestList.getFriendRequestList()){
            ImageIcon userIcon = FirebaseManager.readDBprofilePicture(friendRequest.getFriendEmail());
            Image scaledFriendProfilePicture = userIcon.getImage().getScaledInstance(instance.getPreferredSize().width/25,instance.getPreferredSize().width/25,Image.SCALE_SMOOTH);
            ImageIcon scaledFriendProfilePictureIcon = new ImageIcon(scaledFriendProfilePicture);

            friendRequest.getImageAvatarFriendRequest().setPreferredSize(new Dimension(instance.getPreferredSize().width/25,instance.getPreferredSize().width/25));
            friendRequest.getImageAvatarFriendRequest().setMinimumSize(friendRequest.getImageAvatarFriendRequest().getPreferredSize());
            friendRequest.getImageAvatarFriendRequest().setMaximumSize(friendRequest.getImageAvatarFriendRequest().getPreferredSize());
            friendRequest.getImageAvatarFriendRequest().setImage(scaledFriendProfilePictureIcon);
            friendRequest.getImageAvatarFriendRequest().setAlignmentY(Component.CENTER_ALIGNMENT);



            JPanel friendRequestPanel = new JPanel();
            friendRequestPanel.setOpaque(false);
            friendRequestPanel.setPreferredSize(new Dimension(friendsScrollPane.getPreferredSize().width, (int) (friendRequest.getImageAvatarFriendRequest().getPreferredSize().height*1.1)));
            friendRequestPanel.setLayout(new BoxLayout(friendRequestPanel,BoxLayout.X_AXIS));
            friendRequestPanel.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    super.mouseClicked(e);
                    System.out.println(friendRequest.getFriendName());
                }
            });

            JLabel friendNameLabel = new JLabel(FriendRequestList.getFriendRequestList().get(FriendRequestList.getFriendRequestList().indexOf(friendRequest)).getFriendName());
            friendNameLabel.setFont(CustomFont.getFont().deriveFont(instance.getPreferredSize().width/65f));
            friendNameLabel.setMaximumSize(new Dimension(friendsScrollPane.getPreferredSize().width-friendRequest.getImageAvatarFriendRequest().getPreferredSize().width,friendRequestPanel.getPreferredSize().height));
            friendNameLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
            friendNameLabel.setForeground(AppThemeColors.foregroundColor);

            JPanel friendAvatarPanel = new JPanel();
            friendAvatarPanel.setOpaque(false);
            friendAvatarPanel.setLayout(new BoxLayout(friendAvatarPanel,BoxLayout.X_AXIS));
            friendAvatarPanel.setPreferredSize(new Dimension((int) (friendRequest.getImageAvatarFriendRequest().getPreferredSize().width*1.3),friendRequest.getImageAvatarFriendRequest().getPreferredSize().height));
            friendAvatarPanel.setMaximumSize(friendAvatarPanel.getPreferredSize());
            friendAvatarPanel.setAlignmentY(Component.CENTER_ALIGNMENT);

            JButton acceptFriendRequestButton = new JButton("✓");
            acceptFriendRequestButton.setFont(new Font("SansSerif", Font.BOLD, 10));
            acceptFriendRequestButton.setBackground(Color.GREEN);
            acceptFriendRequestButton.setForeground(AppThemeColors.foregroundColor);
            acceptFriendRequestButton.setPreferredSize(new Dimension(40,25));
            acceptFriendRequestButton.setAlignmentY(Component.CENTER_ALIGNMENT);
            acceptFriendRequestButton.setMinimumSize(acceptFriendRequestButton.getPreferredSize());
            acceptFriendRequestButton.setMaximumSize(acceptFriendRequestButton.getPreferredSize());
            acceptFriendRequestButton.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    FriendsList.getFriendArrayList().add(new Friend(true){
                        {
                            setFriendEmail(friendRequest.getFriendEmail());
                            setFriendName(friendRequest.getFriendName());


                            requestsPanel.remove(friendRequestPanel);
                        }
                    });
                    FirebaseManager.writeDBacceptFriendRequest(friendRequest.getFriendEmail());
                    instance.repaint();
                    instance.revalidate();
                }
            });


            friendAvatarPanel.add(Box.createHorizontalGlue());
            friendAvatarPanel.add(FriendRequestList.getFriendRequestList().get(FriendRequestList.getFriendRequestList().indexOf(friendRequest)).getImageAvatarFriendRequest());
            friendAvatarPanel.add(Box.createHorizontalGlue());

            friendRequestPanel.add(friendAvatarPanel);
            friendRequestPanel.add(friendNameLabel);
            friendRequestPanel.add(Box.createHorizontalGlue());
            friendRequestPanel.add(acceptFriendRequestButton);

            requestsPanel.add(friendRequestPanel);



        }
        friendsScrollPane.setViewportView(requestsPanel);
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
