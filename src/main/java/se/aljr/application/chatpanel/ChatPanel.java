package se.aljr.application.chatpanel;

import se.aljr.application.AppThemeColors;
import se.aljr.application.CustomFont;
import se.aljr.application.Friends.Friend;
import se.aljr.application.Friends.FriendsList;
import se.aljr.application.ImageAvatar;
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

    private Image scaledProfilePicture;
    private static ImageIcon scaledProfilePictureIcon;
    private static ImageAvatar leftAvatar;
    private static ImageAvatar rightAvatar;
    private static ImageIcon profilePictureIcon;



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
        mainRightPanel.setForeground(AppThemeColors.foregroundColor);
        mainRightPanel.setBackground(AppThemeColors.textFieldColor);
        mainRightPanel.setPreferredSize(new Dimension(getPreferredSize().width / 2, getPreferredSize().height));
        mainRightPanel.setMinimumSize(mainRightPanel.getPreferredSize());
        mainRightPanel.setMaximumSize(mainRightPanel.getPreferredSize());


        JPanel belowPanel = new JPanel();
        belowPanel.setLayout(new BoxLayout(belowPanel, BoxLayout.X_AXIS));
        belowPanel.setOpaque(true);
        belowPanel.setForeground(AppThemeColors.foregroundColor);
        belowPanel.setBackground(AppThemeColors.textFieldColor);
        belowPanel.setPreferredSize(new Dimension((int)(mainRightPanel.getPreferredSize().width), mainRightPanel.getPreferredSize().height/8));
        belowPanel.setMinimumSize(mainRightPanel.getPreferredSize());
        belowPanel.setMaximumSize(mainRightPanel.getPreferredSize());




        JPanel rightSideTopPanel = new JPanel();
        rightSideTopPanel.setOpaque(false);
        rightSideTopPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        rightSideTopPanel.setLayout(new BoxLayout(rightSideTopPanel, BoxLayout.X_AXIS));
        rightSideTopPanel.setPreferredSize(new Dimension((int) (mainRightPanel.getPreferredSize().width / 1.2), (int) (mainRightPanel.getPreferredSize().height / 10)));
        rightSideTopPanel.setMinimumSize(rightSideTopPanel.getPreferredSize());
        rightSideTopPanel.setMaximumSize(rightSideTopPanel.getPreferredSize());
        rightSideTopPanel.setBackground(Color.BLACK);




        JPanel messageStorage = new JPanel();
        messageStorage.setLayout(new BoxLayout(messageStorage, BoxLayout.Y_AXIS));
        messageStorage.setOpaque(true);
        messageStorage.setBackground(Color.YELLOW);
        messageStorage.setPreferredSize(null);

        JScrollPane rightSideMessagesData = new JScrollPane();
        rightSideMessagesData.setOpaque(true);
        rightSideMessagesData.setPreferredSize(new Dimension((int)(mainRightPanel.getPreferredSize().width), (int)(mainRightPanel.getPreferredSize().height-belowPanel.getPreferredSize().height)));
        rightSideMessagesData.setMinimumSize(rightSideMessagesData.getPreferredSize());
        rightSideMessagesData.setMaximumSize(rightSideMessagesData.getPreferredSize());
        rightSideMessagesData.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        rightSideMessagesData.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        rightSideMessagesData.getVerticalScrollBar().setPreferredSize(new Dimension(0,0));
        rightSideMessagesData.getVerticalScrollBar().setUnitIncrement(6);
        rightSideMessagesData.getViewport().setBackground(Color.GREEN);
        rightSideMessagesData.setBorder(new LineBorder(Color.BLUE));



        JTextArea messengerTextBox = new JTextArea();
        messengerTextBox.setFont(new Font("Arial", Font.BOLD, (int) (getPreferredSize().width / 100f)));
        messengerTextBox.setPreferredSize(new Dimension((int)(belowPanel.getPreferredSize().width / 1.2),(int)(belowPanel.getPreferredSize().height / 1.2)));
        messengerTextBox.setMinimumSize(messengerTextBox.getPreferredSize());
        messengerTextBox.setMaximumSize(messengerTextBox.getPreferredSize());
        messengerTextBox.setForeground(AppThemeColors.foregroundColor);
        messengerTextBox.setBackground(AppThemeColors.buttonBG);



        JPanel belowRightPanel = new JPanel();
        belowRightPanel.setLayout(new BoxLayout(belowRightPanel, BoxLayout.X_AXIS));
        belowRightPanel.setOpaque(true);
        belowRightPanel.setForeground(AppThemeColors.foregroundColor);
        belowRightPanel.setBackground(AppThemeColors.textFieldColor);
        belowRightPanel.setPreferredSize(new Dimension((int)(belowPanel.getPreferredSize().width / 10), (int)(belowPanel.getPreferredSize().height/ 1.2)));
        belowRightPanel.setMinimumSize(belowRightPanel.getPreferredSize());
        belowRightPanel.setMaximumSize(belowRightPanel.getPreferredSize());


        JButton clickToSendButton = new JButton("✉");
        clickToSendButton.setFont(new Font("Ariel", Font.BOLD, 50));
        clickToSendButton.setMargin(new Insets(0, 0, 0, 0));
        clickToSendButton.setBackground(Color.RED);
        clickToSendButton.setForeground(AppThemeColors.foregroundColor);
        clickToSendButton.setPreferredSize(new Dimension((int)(belowRightPanel.getPreferredSize().width/1.6),(int)(belowRightPanel.getPreferredSize().height/1.6)));
        clickToSendButton.setAlignmentY(Component.CENTER_ALIGNMENT);
        clickToSendButton.setMinimumSize(clickToSendButton.getPreferredSize());
        clickToSendButton.setMaximumSize(clickToSendButton.getPreferredSize());
        clickToSendButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                {
                    {
                        if (messengerTextBox.getText().isEmpty()){

                        }else {
                            /*--------------------Left side of chat--------------------*/
                            JPanel leftEachMessageSent = new JPanel();
                            leftEachMessageSent.setAlignmentX(Component.CENTER_ALIGNMENT);
                            leftEachMessageSent.setLayout(new BoxLayout(leftEachMessageSent, BoxLayout.X_AXIS));
                            leftEachMessageSent.setOpaque(true);
                            leftEachMessageSent.setBackground(Color.PINK);
                            leftEachMessageSent.setPreferredSize(new Dimension((int) (mainRightPanel.getPreferredSize().width / 1.2), (int) (mainRightPanel.getPreferredSize().height / 10)));
                            leftEachMessageSent.setMinimumSize(leftEachMessageSent.getPreferredSize());
                            leftEachMessageSent.setMaximumSize(leftEachMessageSent.getPreferredSize());


                            JPanel leftOwnProfilePictureInTextMessage = new JPanel();
                            leftOwnProfilePictureInTextMessage.setAlignmentY(Component.TOP_ALIGNMENT);
                            leftOwnProfilePictureInTextMessage.setLayout(new BoxLayout(leftOwnProfilePictureInTextMessage, BoxLayout.X_AXIS));
                            leftOwnProfilePictureInTextMessage.setOpaque(true);
                            leftOwnProfilePictureInTextMessage.setBackground(Color.BLACK);
                            leftOwnProfilePictureInTextMessage.setPreferredSize(new Dimension((int) (leftEachMessageSent.getPreferredSize().width / 8), (int) (leftEachMessageSent.getPreferredSize().height)));
                            leftOwnProfilePictureInTextMessage.setMinimumSize(leftOwnProfilePictureInTextMessage.getPreferredSize());
                            leftOwnProfilePictureInTextMessage.setMaximumSize(leftOwnProfilePictureInTextMessage.getPreferredSize());


                            profilePictureIcon = FirebaseManager.readDBprofilePicture(UserData.getEmail());
                            leftAvatar = new ImageAvatar();
                            leftAvatar.setAlignmentX(Component.RIGHT_ALIGNMENT);
                            leftAvatar.setPreferredSize(new Dimension(getPreferredSize().width/25,getPreferredSize().width/25));
                            scaledProfilePicture = profilePictureIcon.getImage().getScaledInstance(getPreferredSize().width/25,getPreferredSize().width/25,Image.SCALE_SMOOTH);
                            scaledProfilePictureIcon = new ImageIcon(scaledProfilePicture);
                            leftAvatar.setImage(scaledProfilePictureIcon);

                            leftOwnProfilePictureInTextMessage.add(Box.createHorizontalGlue());
                            leftOwnProfilePictureInTextMessage.add(leftAvatar);

                            JPanel sizeToCalculateNumberLenght = new JPanel();
                            sizeToCalculateNumberLenght.setPreferredSize(new Dimension((int) (leftEachMessageSent.getPreferredSize().width - leftOwnProfilePictureInTextMessage.getPreferredSize().width), (int) (leftEachMessageSent.getPreferredSize().height)));

                            JPanel leftMessageOutPrintedOnScreen = new JPanel();
                            leftMessageOutPrintedOnScreen.setAlignmentY(Component.TOP_ALIGNMENT);
                            leftMessageOutPrintedOnScreen.setLayout(new BoxLayout(leftMessageOutPrintedOnScreen, BoxLayout.Y_AXIS));
                            leftMessageOutPrintedOnScreen.setOpaque(true);
                            leftMessageOutPrintedOnScreen.setBackground(Color.BLUE);
                            leftMessageOutPrintedOnScreen.setPreferredSize(new Dimension((int) (leftEachMessageSent.getPreferredSize().width - leftOwnProfilePictureInTextMessage.getPreferredSize().width)- sizeToCalculateNumberLenght.getPreferredSize().width +messengerTextBox.getText().length()*11, (int) (leftEachMessageSent.getPreferredSize().height/4)));
                            leftMessageOutPrintedOnScreen.setMinimumSize(leftMessageOutPrintedOnScreen.getPreferredSize());
                            leftMessageOutPrintedOnScreen.setMaximumSize(leftMessageOutPrintedOnScreen.getPreferredSize());


                            /*--------------------right side of chat--------------------*/
                            JPanel rightEachMessageSent = new JPanel();
                            rightEachMessageSent.setAlignmentX(Component.CENTER_ALIGNMENT);
                            rightEachMessageSent.add(Box.createHorizontalGlue());
                            rightEachMessageSent.setLayout(new BoxLayout(rightEachMessageSent, BoxLayout.X_AXIS));
                            rightEachMessageSent.setOpaque(true);
                            rightEachMessageSent.setBackground(Color.PINK);
                            rightEachMessageSent.setPreferredSize(new Dimension((int) (mainRightPanel.getPreferredSize().width / 1.2), (int) (mainRightPanel.getPreferredSize().height / 10)));
                            rightEachMessageSent.setMinimumSize(rightEachMessageSent.getPreferredSize());
                            rightEachMessageSent.setMaximumSize(rightEachMessageSent.getPreferredSize());

                            JPanel rightOwnProfilePictureInTextMessage = new JPanel();
                            rightOwnProfilePictureInTextMessage.setAlignmentY(Component.TOP_ALIGNMENT);
                            rightOwnProfilePictureInTextMessage.setLayout(new BoxLayout(rightOwnProfilePictureInTextMessage, BoxLayout.X_AXIS));
                            rightOwnProfilePictureInTextMessage.setOpaque(true);
                            rightOwnProfilePictureInTextMessage.setBackground(Color.BLACK);
                            rightOwnProfilePictureInTextMessage.setPreferredSize(new Dimension((int) (leftEachMessageSent.getPreferredSize().width / 8), (int) (leftEachMessageSent.getPreferredSize().height)));
                            rightOwnProfilePictureInTextMessage.setMinimumSize(rightOwnProfilePictureInTextMessage.getPreferredSize());
                            rightOwnProfilePictureInTextMessage.setMaximumSize(rightOwnProfilePictureInTextMessage.getPreferredSize());

                            rightAvatar = new ImageAvatar();
                            rightAvatar.setAlignmentY(Component.CENTER_ALIGNMENT);
                            rightAvatar.setPreferredSize(new Dimension(getPreferredSize().width/25,getPreferredSize().width/25));
                            scaledProfilePicture = profilePictureIcon.getImage().getScaledInstance(getPreferredSize().width/25,getPreferredSize().width/25,Image.SCALE_SMOOTH);
                            scaledProfilePictureIcon = new ImageIcon(scaledProfilePicture);
                            rightAvatar.setImage(scaledProfilePictureIcon);

                            rightOwnProfilePictureInTextMessage.add(rightAvatar);
                            rightOwnProfilePictureInTextMessage.add(Box.createHorizontalGlue());

                            JPanel rightMessageOutPrintedOnScreen = new JPanel();
                            rightMessageOutPrintedOnScreen.setAlignmentY(Component.TOP_ALIGNMENT);
                            rightMessageOutPrintedOnScreen.setLayout(new BoxLayout(rightMessageOutPrintedOnScreen, BoxLayout.Y_AXIS));
                            rightMessageOutPrintedOnScreen.setOpaque(true);
                            rightMessageOutPrintedOnScreen.setBackground(Color.BLUE);
                            rightMessageOutPrintedOnScreen.setPreferredSize(new Dimension((int) (leftEachMessageSent.getPreferredSize().width - leftOwnProfilePictureInTextMessage.getPreferredSize().width)- sizeToCalculateNumberLenght.getPreferredSize().width +messengerTextBox.getText().length()*11, (int) (leftEachMessageSent.getPreferredSize().height/4)));
                            rightMessageOutPrintedOnScreen.setMinimumSize(rightMessageOutPrintedOnScreen.getPreferredSize());
                            rightMessageOutPrintedOnScreen.setMaximumSize(rightMessageOutPrintedOnScreen.getPreferredSize());



                            /*--------------------text recived from TextBox--------------------*/

                            JLabel label = new JLabel(messengerTextBox.getText());
                            label.setFont(new Font("Arial", Font.BOLD, 20));
                            label.setForeground(Color.WHITE);
                            leftMessageOutPrintedOnScreen.add(label);
                            messengerTextBox.setText("");


                            leftEachMessageSent.add(leftOwnProfilePictureInTextMessage);
                            leftEachMessageSent.add(leftMessageOutPrintedOnScreen);
                            messageStorage.add(Box.createRigidArea(new Dimension(leftEachMessageSent.getPreferredSize().width, leftEachMessageSent.getPreferredSize().height/10)));
                            messageStorage.add(leftEachMessageSent);


                            rightEachMessageSent.add(rightMessageOutPrintedOnScreen);
                            rightEachMessageSent.add(rightOwnProfilePictureInTextMessage);
                            messageStorage.add(Box.createRigidArea(new Dimension(rightEachMessageSent.getPreferredSize().width,rightEachMessageSent.getPreferredSize().height/10)));
                            messageStorage.add(rightEachMessageSent);



                            messageStorage.revalidate();
                            messageStorage.repaint();
                            SwingUtilities.invokeLater(() -> rightSideMessagesData.getVerticalScrollBar().setValue(rightSideMessagesData.getVerticalScrollBar().getMaximum()));
                        }
                    }
                };
            }
        });





















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





        /*--------------------(Right panel) Chat--------------------*/
        mainRightPanel.add(belowPanel, BorderLayout.SOUTH);
        mainRightPanel.add(rightSideTopPanel);

        /*--------------------(Right panel) Chatwrite--------------------*/
        belowPanel.add(Box.createHorizontalGlue());
        belowPanel.add(messengerTextBox);
        belowPanel.add(Box.createHorizontalGlue());
        belowPanel.add(belowRightPanel);
        belowPanel.add(Box.createHorizontalGlue());
        belowRightPanel.add(Box.createHorizontalGlue());
        belowRightPanel.add(clickToSendButton);
        belowRightPanel.add(Box.createHorizontalGlue());




        /*--------------------(Right panel) ChatBox--------------------*/
        mainRightPanel.add(rightSideMessagesData, BorderLayout.NORTH);
        rightSideMessagesData.setViewportView(messageStorage);






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
            acceptFriendRequestButton.setFont(new Font("SansSerif", Font.BOLD, 20));
            acceptFriendRequestButton.setMargin(new Insets(0, 0, 0, 0));
            acceptFriendRequestButton.setBackground(Color.GREEN);
            acceptFriendRequestButton.setForeground(AppThemeColors.foregroundColor);
            acceptFriendRequestButton.setPreferredSize(new Dimension(30,25));
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

            JButton denyFriendRequestButton = new JButton("X");
            denyFriendRequestButton.setFont(new Font("Ariel", Font.BOLD, 20));
            denyFriendRequestButton.setMargin(new Insets(0, 0, 0, 0));
            denyFriendRequestButton.setBackground(Color.RED);
            denyFriendRequestButton.setForeground(AppThemeColors.foregroundColor);
            denyFriendRequestButton.setPreferredSize(new Dimension(30,25));
            denyFriendRequestButton.setAlignmentY(Component.CENTER_ALIGNMENT);
            denyFriendRequestButton.setMinimumSize(denyFriendRequestButton.getPreferredSize());
            denyFriendRequestButton.setMaximumSize(denyFriendRequestButton.getPreferredSize());
            denyFriendRequestButton.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    {
                        {
                            System.out.println("HelloBabyGIRL");
                        }
                    };
                }
            });


            friendAvatarPanel.add(Box.createHorizontalGlue());
            friendAvatarPanel.add(FriendRequestList.getFriendRequestList().get(FriendRequestList.getFriendRequestList().indexOf(friendRequest)).getImageAvatarFriendRequest());
            friendAvatarPanel.add(Box.createHorizontalGlue());

            friendRequestPanel.add(friendAvatarPanel);
            friendRequestPanel.add(friendNameLabel);
            friendRequestPanel.add(Box.createHorizontalGlue());
            friendRequestPanel.add(acceptFriendRequestButton);
            friendRequestPanel.add(denyFriendRequestButton);

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
