package se.aljr.application.chatpanel;

import com.google.cloud.storage.Acl;
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
import java.util.ArrayList;
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

    public static Friend selectedFriend;

    private static final JPanel mainRightPanel = new JPanel();
    private static final JPanel messageStorage = new JPanel();
    private static final JScrollPane messagesScrollPane = new JScrollPane();
    private static boolean isFirstRun = true;

    public ChatPanel(int width, int height) {
        resourcePath = getClass().getClassLoader().getResource("resource.path").getPath().replace("resource.path", "");
        settingsPanelBackground = new ImageIcon(resourcePath + "emptyBackground.png");
        scaleSettingsPanelBackground = settingsPanelBackground.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);
        scaledSettingsPanelBackgroundIcon = new ImageIcon(scaleSettingsPanelBackground);


        this.setPreferredSize(new Dimension(width, height));
        this.setBackground(new Color(31, 31, 31));
        this.setLayout(new BorderLayout(0, 0));
        this.setBorder(new EmptyBorder(15, 15, 15, 15));


        instance = this;
        init();
    }

    private void init() {



        /*--------------------Main panel--------------------*/
        JPanel mainPanel = new JPanel();
        mainPanel.setOpaque(false);
        mainPanel.setPreferredSize(new Dimension(getPreferredSize().width, getPreferredSize().height));
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.X_AXIS));




        /*--------------------Right panel--------------------*/

        mainRightPanel.setLayout(new BorderLayout());
        mainRightPanel.setOpaque(true);
        mainRightPanel.setBackground(AppThemeColors.textFieldColor);
        mainRightPanel.setPreferredSize(new Dimension(getPreferredSize().width / 2, getPreferredSize().height));
        mainRightPanel.setMinimumSize(mainRightPanel.getPreferredSize());
        mainRightPanel.setMaximumSize(mainRightPanel.getPreferredSize());


        JPanel belowPanel = new JPanel();
        belowPanel.setLayout(new BoxLayout(belowPanel, BoxLayout.X_AXIS));
        belowPanel.setOpaque(true);
        belowPanel.setBackground(AppThemeColors.textFieldColor);
        belowPanel.setPreferredSize(new Dimension((int) (mainRightPanel.getPreferredSize().width), mainRightPanel.getPreferredSize().height / 8));
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



        messageStorage.setLayout(new BoxLayout(messageStorage, BoxLayout.Y_AXIS));
        messageStorage.setOpaque(true);
//        messageStorage.setBackground(Color.YELLOW);
        messageStorage.setBackground(AppThemeColors.textFieldColor);
        messageStorage.setPreferredSize(null);


        messagesScrollPane.setOpaque(true);
        messagesScrollPane.setPreferredSize(new Dimension((int) (mainRightPanel.getPreferredSize().width), (int) (mainRightPanel.getPreferredSize().height / 1.05 - belowPanel.getPreferredSize().height)));
        messagesScrollPane.setMinimumSize(messagesScrollPane.getPreferredSize());
        messagesScrollPane.setMaximumSize(messagesScrollPane.getPreferredSize());
        messagesScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        messagesScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        messagesScrollPane.getVerticalScrollBar().setPreferredSize(new Dimension(0, 0));
        messagesScrollPane.getVerticalScrollBar().setUnitIncrement(6);
        messagesScrollPane.getViewport().setBackground(Color.GREEN);
        messagesScrollPane.setBorder(new LineBorder(AppThemeColors.textFieldColor));


        JTextArea messengerTextBox = new JTextArea();
        messengerTextBox.setFont(new Font("Arial", Font.BOLD, (int) (getPreferredSize().width / 100f)));
        messengerTextBox.setPreferredSize(new Dimension((int) (belowPanel.getPreferredSize().width / 1.2), (int) (belowPanel.getPreferredSize().height / 1.2)));
        messengerTextBox.setMinimumSize(messengerTextBox.getPreferredSize());
        messengerTextBox.setMaximumSize(messengerTextBox.getPreferredSize());
        messengerTextBox.setForeground(AppThemeColors.foregroundColor);
        messengerTextBox.setBackground(AppThemeColors.buttonBG);
        messengerTextBox.setLineWrap(true);
        messengerTextBox.setWrapStyleWord(true);


        JPanel belowRightPanel = new JPanel();
        belowRightPanel.setLayout(new BoxLayout(belowRightPanel, BoxLayout.X_AXIS));
        belowRightPanel.setOpaque(true);
        belowRightPanel.setBackground(AppThemeColors.textFieldColor);
        belowRightPanel.setPreferredSize(new Dimension((int) (belowPanel.getPreferredSize().width / 10), (int) (belowPanel.getPreferredSize().height / 1.2)));
        belowRightPanel.setMinimumSize(belowRightPanel.getPreferredSize());
        belowRightPanel.setMaximumSize(belowRightPanel.getPreferredSize());


        JButton clickToSendButton = new JButton("✉");
        clickToSendButton.setFont(new Font("Ariel", Font.BOLD, 50));
        clickToSendButton.setMargin(new Insets(0, 0, 0, 0));
//        clickToSendButton.setBackground(Color.RED);
        clickToSendButton.setForeground(AppThemeColors.foregroundColor);
        clickToSendButton.setBackground(AppThemeColors.buttonBG);
        clickToSendButton.setPreferredSize(new Dimension((int) (belowRightPanel.getPreferredSize().width / 1.6), (int) (belowRightPanel.getPreferredSize().height / 1.6)));
        clickToSendButton.setAlignmentY(Component.CENTER_ALIGNMENT);
        clickToSendButton.setMinimumSize(clickToSendButton.getPreferredSize());
        clickToSendButton.setMaximumSize(clickToSendButton.getPreferredSize());
        clickToSendButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                        FirebaseManager.writeDBwriteMessageHistory(selectedFriend.getFriendEmail(),UserData.getEmail(),messengerTextBox.getText());
            }
        });





















        /*--------------------Middle panel--------------------*/
        JPanel mainMiddlePanel = new JPanel();
        mainMiddlePanel.setOpaque(false);
        mainMiddlePanel.setLayout(new BoxLayout(mainMiddlePanel, BoxLayout.X_AXIS));
        mainMiddlePanel.setPreferredSize(new Dimension(getPreferredSize().width / 5, getPreferredSize().height));
        mainMiddlePanel.setMinimumSize(mainMiddlePanel.getPreferredSize());
        mainMiddlePanel.setMaximumSize(mainMiddlePanel.getPreferredSize());









        /*--------------------(Middle panel) Different scrolls each button--------------------*/

        friendsScrollPane.setPreferredSize(new Dimension(mainMiddlePanel.getPreferredSize().width, mainMiddlePanel.getPreferredSize().height));
        friendsScrollPane.setMinimumSize(friendsScrollPane.getPreferredSize());
        friendsScrollPane.setMaximumSize(friendsScrollPane.getPreferredSize());
        friendsScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        friendsScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        friendsScrollPane.getVerticalScrollBar().setPreferredSize(new Dimension(0, 0));
        friendsScrollPane.getVerticalScrollBar().setUnitIncrement(6);
        friendsScrollPane.getViewport().setBackground(AppThemeColors.panelColor);
        friendsScrollPane.setBorder(new LineBorder(Color.BLACK));


        JPanel friendsPanel = new JPanel();
        friendsPanel.setLayout(new BoxLayout(friendsPanel, BoxLayout.Y_AXIS));
        friendsPanel.setOpaque(false);

        for (Friend friend : FriendsList.getFriendArrayList()) {
            ImageIcon userIcon = FirebaseManager.readDBprofilePicture(friend.getFriendEmail());
            Image scaledFriendProfilePicture = userIcon.getImage().getScaledInstance(getPreferredSize().width / 25, getPreferredSize().width / 25, Image.SCALE_SMOOTH);
            ImageIcon scaledFriendProfilePictureIcon = new ImageIcon(scaledFriendProfilePicture);

            friend.getImageAvatarSocial().setPreferredSize(new Dimension(getPreferredSize().width / 25, getPreferredSize().width / 25));
            friend.getImageAvatarSocial().setMinimumSize(friend.getImageAvatarSocial().getPreferredSize());
            friend.getImageAvatarSocial().setMaximumSize(friend.getImageAvatarSocial().getPreferredSize());
            friend.getImageAvatarSocial().setImage(scaledFriendProfilePictureIcon);
            friend.getImageAvatarSocial().setAlignmentY(Component.CENTER_ALIGNMENT);


            JPanel friendPanel = new JPanel();
            friendPanel.setOpaque(false);
            friendPanel.setPreferredSize(new Dimension(friendsScrollPane.getPreferredSize().width, (int) (friend.getImageAvatarSocial().getPreferredSize().height * 1.1)));
            friendPanel.setLayout(new BoxLayout(friendPanel, BoxLayout.X_AXIS));
            friendPanel.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    super.mouseClicked(e);
                    selectedFriend = friend;
                    updateChat();
                    System.out.println(friend.getFriendName());
                }
            });

            JLabel friendNameLabel = new JLabel(FriendsList.getFriendArrayList().get(FriendsList.getFriendArrayList().indexOf(friend)).getFriendName());
            friendNameLabel.setFont(CustomFont.getFont().deriveFont(getPreferredSize().width / 65f));
            friendNameLabel.setMaximumSize(new Dimension(friendsScrollPane.getPreferredSize().width - friend.getImageAvatarSocial().getPreferredSize().width, friendPanel.getPreferredSize().height));
            friendNameLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
            friendNameLabel.setForeground(AppThemeColors.foregroundColor);

            JPanel friendAvatarPanel = new JPanel();
            friendAvatarPanel.setOpaque(false);
            friendAvatarPanel.setLayout(new BoxLayout(friendAvatarPanel, BoxLayout.X_AXIS));
            friendAvatarPanel.setPreferredSize(new Dimension((int) (friend.getImageAvatarSocial().getPreferredSize().width * 1.3), friend.getImageAvatarSocial().getPreferredSize().height));
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
        addPanel.setPreferredSize(new Dimension(mainMiddlePanel.getPreferredSize().width, mainMiddlePanel.getPreferredSize().height / 4));
        addPanel.setMinimumSize(addPanel.getPreferredSize());
        addPanel.setMaximumSize(addPanel.getPreferredSize());
        addPanel.setBackground(AppThemeColors.panelColor);
        addPanel.setAlignmentY(Component.CENTER_ALIGNMENT);

        /*--------------------Add panel components--------------------*/


        JTextField friendRequestMailText = new JTextField();
        friendRequestMailText.setFont(new Font("Arial", Font.BOLD, (int) (getPreferredSize().width / 100f)));
        friendRequestMailText.setPreferredSize(new Dimension((int) (addPanel.getPreferredSize().width * 0.9), (int) (addPanel.getPreferredSize().height * 0.15)));
        friendRequestMailText.setMinimumSize(friendRequestMailText.getPreferredSize());
        friendRequestMailText.setMaximumSize(friendRequestMailText.getPreferredSize());
        friendRequestMailText.setForeground(AppThemeColors.foregroundColor);
        friendRequestMailText.setBackground(AppThemeColors.textFieldColor);


        JButton addbutton = new JButton("ADD");
        addbutton.setFont(new Font("Arial", Font.BOLD, 50));
        addbutton.setBorder(new LineBorder(Color.BLACK));
        addbutton.setBackground(AppThemeColors.buttonBG);
        addbutton.setForeground(AppThemeColors.foregroundColor);
        addbutton.setPreferredSize(new Dimension(friendsScrollPane.getPreferredSize().width / 3, 50));
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
        mainLeftPanel.setPreferredSize(new Dimension((int) (getPreferredSize().width / 5), getPreferredSize().height));
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
        friendsButton.setPreferredSize(new Dimension(buttonlayout.getPreferredSize().width, buttonlayout.getPreferredSize().height / 10));
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
        requestButton.setPreferredSize(new Dimension(buttonlayout.getPreferredSize().width, buttonlayout.getPreferredSize().height / 10));
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
        groupButton.setPreferredSize(new Dimension(buttonlayout.getPreferredSize().width, buttonlayout.getPreferredSize().height / 10));
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
        addButton.setPreferredSize(new Dimension(buttonlayout.getPreferredSize().width, buttonlayout.getPreferredSize().height / 10));
        addButton.setMinimumSize(addButton.getPreferredSize());
        addButton.setMaximumSize(addButton.getPreferredSize());
        addButton.setText("Add");
        addButton.setFont(new Font("Arial", Font.BOLD, 50));
        addButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (!addPanelIsActive) {
                    mainMiddlePanel.add(addPanel, 1);
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
        mainRightPanel.add(messagesScrollPane, BorderLayout.NORTH);
        messagesScrollPane.setViewportView(messageStorage);






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
        try {
            FirebaseManager.readDBlistenToClientChats();
        }catch (Exception e){

        }
    }


    public static void updateRequestsPanel() {
        requestsPanel.removeAll();
        FriendRequestList.getFriendRequestList().clear();
        HashMap<String, String> friendRequestHashMap = FirebaseManager.readDBgetFriendRequests(UserData.getEmail());
        for (Map.Entry<String, String> entry : friendRequestHashMap.entrySet()) {
            FriendRequestList.getFriendRequestList().add(new FriendRequest() {
                {
                    setFriendEmail(entry.getKey());
                    setFriendName(entry.getValue());
                }
            });
        }

        for (FriendRequest friendRequest : FriendRequestList.getFriendRequestList()) {
            ImageIcon userIcon = FirebaseManager.readDBprofilePicture(friendRequest.getFriendEmail());
            Image scaledFriendProfilePicture = userIcon.getImage().getScaledInstance(instance.getPreferredSize().width / 25, instance.getPreferredSize().width / 25, Image.SCALE_SMOOTH);
            ImageIcon scaledFriendProfilePictureIcon = new ImageIcon(scaledFriendProfilePicture);

            friendRequest.getImageAvatarFriendRequest().setPreferredSize(new Dimension(instance.getPreferredSize().width / 25, instance.getPreferredSize().width / 25));
            friendRequest.getImageAvatarFriendRequest().setMinimumSize(friendRequest.getImageAvatarFriendRequest().getPreferredSize());
            friendRequest.getImageAvatarFriendRequest().setMaximumSize(friendRequest.getImageAvatarFriendRequest().getPreferredSize());
            friendRequest.getImageAvatarFriendRequest().setImage(scaledFriendProfilePictureIcon);
            friendRequest.getImageAvatarFriendRequest().setAlignmentY(Component.CENTER_ALIGNMENT);


            JPanel friendRequestPanel = new JPanel();
            friendRequestPanel.setOpaque(false);
            friendRequestPanel.setPreferredSize(new Dimension(friendsScrollPane.getPreferredSize().width, (int) (friendRequest.getImageAvatarFriendRequest().getPreferredSize().height * 1.1)));
            friendRequestPanel.setLayout(new BoxLayout(friendRequestPanel, BoxLayout.X_AXIS));
            friendRequestPanel.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    super.mouseClicked(e);
                    System.out.println(friendRequest.getFriendName());
                }
            });

            JLabel friendNameLabel = new JLabel(FriendRequestList.getFriendRequestList().get(FriendRequestList.getFriendRequestList().indexOf(friendRequest)).getFriendName());
            friendNameLabel.setFont(CustomFont.getFont().deriveFont(instance.getPreferredSize().width / 65f));
            friendNameLabel.setMaximumSize(new Dimension(friendsScrollPane.getPreferredSize().width - friendRequest.getImageAvatarFriendRequest().getPreferredSize().width, friendRequestPanel.getPreferredSize().height));
            friendNameLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
            friendNameLabel.setForeground(AppThemeColors.foregroundColor);

            JPanel friendAvatarPanel = new JPanel();
            friendAvatarPanel.setOpaque(false);
            friendAvatarPanel.setLayout(new BoxLayout(friendAvatarPanel, BoxLayout.X_AXIS));
            friendAvatarPanel.setPreferredSize(new Dimension((int) (friendRequest.getImageAvatarFriendRequest().getPreferredSize().width * 1.3), friendRequest.getImageAvatarFriendRequest().getPreferredSize().height));
            friendAvatarPanel.setMaximumSize(friendAvatarPanel.getPreferredSize());
            friendAvatarPanel.setAlignmentY(Component.CENTER_ALIGNMENT);

            JButton acceptFriendRequestButton = new JButton("✓");
            acceptFriendRequestButton.setFont(new Font("SansSerif", Font.BOLD, 20));
            acceptFriendRequestButton.setMargin(new Insets(0, 0, 0, 0));
            acceptFriendRequestButton.setBackground(Color.GREEN);
            acceptFriendRequestButton.setForeground(AppThemeColors.foregroundColor);
            acceptFriendRequestButton.setPreferredSize(new Dimension(30, 25));
            acceptFriendRequestButton.setAlignmentY(Component.CENTER_ALIGNMENT);
            acceptFriendRequestButton.setMinimumSize(acceptFriendRequestButton.getPreferredSize());
            acceptFriendRequestButton.setMaximumSize(acceptFriendRequestButton.getPreferredSize());
            acceptFriendRequestButton.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    FriendsList.getFriendArrayList().add(new Friend(true) {
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
            denyFriendRequestButton.setPreferredSize(new Dimension(30, 25));
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
                    }
                    ;
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

    public static void updateChat(){
        //messageStorage.removeAll();
        System.out.println(selectedFriend);
        ArrayList<HashMap<String,String>> newMessages;
        if(isFirstRun){
            newMessages = selectedFriend.getChat();
        }else{
            newMessages =  new ArrayList(selectedFriend.getChat().subList((messageStorage.getComponentCount()/2), selectedFriend.getChat().size()));
        }

        for(HashMap<String,String> chatLog:newMessages){
            String message = chatLog.get(UserData.getEmail());
            if(message!=null){

                /*--------------------Left side of chat--------------------*/
                JPanel leftSentMessageContainer = new JPanel();
                leftSentMessageContainer.setAlignmentX(Component.CENTER_ALIGNMENT);
                leftSentMessageContainer.setLayout(new BoxLayout(leftSentMessageContainer, BoxLayout.X_AXIS));
                leftSentMessageContainer.setOpaque(false);
                leftSentMessageContainer.setBackground(Color.PINK);
                leftSentMessageContainer.setPreferredSize(new Dimension((int) (mainRightPanel.getPreferredSize().width / 1.1), (int) (instance.getPreferredSize().width/25)));
                leftSentMessageContainer.setMinimumSize(leftSentMessageContainer.getPreferredSize());
                leftSentMessageContainer.setMaximumSize(leftSentMessageContainer.getPreferredSize());
                leftSentMessageContainer.setAlignmentX(Component.CENTER_ALIGNMENT);


                JPanel leftProfilePictureContainer = new JPanel();
                leftProfilePictureContainer.setLayout(new BoxLayout(leftProfilePictureContainer, BoxLayout.X_AXIS));
                leftProfilePictureContainer.setOpaque(false);
                leftProfilePictureContainer.setBackground(AppThemeColors.textFieldColor);
                leftProfilePictureContainer.setPreferredSize(new Dimension((int) (leftSentMessageContainer.getPreferredSize().width / 8), (int) (instance.getPreferredSize().width / 25)));
                leftProfilePictureContainer.setMinimumSize(leftProfilePictureContainer.getPreferredSize());
                leftProfilePictureContainer.setMaximumSize(leftProfilePictureContainer.getPreferredSize());
                leftProfilePictureContainer.setAlignmentY(Component.TOP_ALIGNMENT);

                profilePictureIcon = FirebaseManager.readDBprofilePicture(UserData.getEmail());
                leftAvatar = new ImageAvatar();
                leftAvatar.setPreferredSize(new Dimension( instance.getPreferredSize().width / 25, instance.getPreferredSize().width / 25));
                Image scaledProfilePicture = profilePictureIcon.getImage().getScaledInstance(instance.getPreferredSize().width / 25, instance.getPreferredSize().width / 25, Image.SCALE_SMOOTH);
                scaledProfilePictureIcon = new ImageIcon(scaledProfilePicture);
                leftAvatar.setImage(scaledProfilePictureIcon);
                leftProfilePictureContainer.add(Box.createHorizontalGlue());
                leftProfilePictureContainer.add(leftAvatar);
                leftProfilePictureContainer.add(Box.createHorizontalGlue());

                JTextArea holdTextMessage = new JTextArea();
                holdTextMessage.setMinimumSize(new Dimension(new Dimension(leftSentMessageContainer.getPreferredSize().width - leftProfilePictureContainer.getPreferredSize().width,1)));
                holdTextMessage.setFont(new Font("Arial", Font.BOLD, 20));
                holdTextMessage.setOpaque(false);
                holdTextMessage.setLineWrap(true);
                holdTextMessage.setWrapStyleWord(true);
                holdTextMessage.append(message);
                System.out.println(holdTextMessage.getText());
                holdTextMessage.setBorder(new LineBorder(Color.CYAN));
                holdTextMessage.setEditable(false);
                holdTextMessage.setAlignmentY(Component.TOP_ALIGNMENT);
                SwingUtilities.invokeLater(() -> {
                    FontMetrics metrics = holdTextMessage.getFontMetrics(holdTextMessage.getFont());
                    int lineHeight = metrics.getHeight();

                    int areaWidth = leftSentMessageContainer.getPreferredSize().width - leftProfilePictureContainer.getPreferredSize().width;
                    int areaHeight = holdTextMessage.getHeight();

                    int charsPerLine = areaWidth / metrics.charWidth('m');

                    int numLines = areaHeight / lineHeight;

                    int totalChars = holdTextMessage.getText().length();

                    int totalLinesNeeded = (int) Math.ceil((double) totalChars / charsPerLine);

                    int totalHeight = totalLinesNeeded * lineHeight;
                    holdTextMessage.setSize(new Dimension(leftSentMessageContainer.getPreferredSize().width - leftProfilePictureContainer.getPreferredSize().width, totalHeight));
                    //holdTextMessage.setPreferredSize(holdTextMessage.getSize());
                    System.out.println(new Dimension(leftSentMessageContainer.getPreferredSize().width - leftProfilePictureContainer.getPreferredSize().width, totalHeight));
                    holdTextMessage.setMinimumSize(holdTextMessage.getPreferredSize());
                    holdTextMessage.setMaximumSize(holdTextMessage.getPreferredSize());
                    holdTextMessage.setForeground(Color.WHITE);
                    holdTextMessage.setBackground(Color.green);
                    System.out.println(holdTextMessage.getSize().height +" "+ leftSentMessageContainer.getPreferredSize().height);
                    if (holdTextMessage.getSize().height > leftSentMessageContainer.getPreferredSize().height) {
                        leftSentMessageContainer.setPreferredSize(new Dimension((int) (mainRightPanel.getPreferredSize().width / 1.1), holdTextMessage.getPreferredSize().height));
                        leftSentMessageContainer.setMinimumSize(leftSentMessageContainer.getPreferredSize());
                        leftSentMessageContainer.setMaximumSize(leftSentMessageContainer.getPreferredSize());
                    }
                });
                leftSentMessageContainer.add(leftProfilePictureContainer);
                leftSentMessageContainer.add(Box.createHorizontalGlue());
                leftSentMessageContainer.add(holdTextMessage);

                messageStorage.add(leftSentMessageContainer);
                messageStorage.add(Box.createRigidArea(new Dimension(leftSentMessageContainer.getPreferredSize().width, instance.getPreferredSize().height/20)));
            }else{
                /*--------------------right side of chat--------------------*/
                JPanel rightSentMessageContainer = new JPanel();
                rightSentMessageContainer.setAlignmentX(Component.CENTER_ALIGNMENT);
                rightSentMessageContainer.setLayout(new BoxLayout(rightSentMessageContainer, BoxLayout.X_AXIS));
                rightSentMessageContainer.setOpaque(false);
                rightSentMessageContainer.setBackground(Color.PINK);
                rightSentMessageContainer.setPreferredSize(new Dimension((int) (mainRightPanel.getPreferredSize().width / 1.1), (int) (instance.getPreferredSize().width/25)));
                rightSentMessageContainer.setMinimumSize(rightSentMessageContainer.getPreferredSize());
                rightSentMessageContainer.setMaximumSize(rightSentMessageContainer.getPreferredSize());

                JPanel rightProfilePictureContainer = new JPanel();
                rightProfilePictureContainer.setLayout(new BoxLayout(rightProfilePictureContainer, BoxLayout.X_AXIS));
                rightProfilePictureContainer.setOpaque(false);
                rightProfilePictureContainer.setBackground(AppThemeColors.textFieldColor);
                rightProfilePictureContainer.setBackground(Color.BLUE);
                rightProfilePictureContainer.setPreferredSize(new Dimension((int) (rightSentMessageContainer.getPreferredSize().width / 8), (int) (instance.getPreferredSize().width / 25)));
                rightProfilePictureContainer.setMinimumSize(rightProfilePictureContainer.getPreferredSize());
                rightProfilePictureContainer.setMaximumSize(rightProfilePictureContainer.getPreferredSize());
                rightProfilePictureContainer.setAlignmentY(Component.TOP_ALIGNMENT);

                rightAvatar = new ImageAvatar();
                rightAvatar.setPreferredSize(new Dimension(instance.getPreferredSize().width / 25, instance.getPreferredSize().width / 25));
                ImageIcon profilePictureIconFriend = FirebaseManager.readDBprofilePicture(selectedFriend.getFriendEmail());
                Image scaledProfilePicture = profilePictureIconFriend.getImage().getScaledInstance(instance.getPreferredSize().width / 25, instance.getPreferredSize().width / 25, Image.SCALE_SMOOTH);
                scaledProfilePictureIcon = new ImageIcon(scaledProfilePicture);
                rightAvatar.setImage(scaledProfilePictureIcon);

                rightProfilePictureContainer.add(Box.createHorizontalGlue());
                rightProfilePictureContainer.add(rightAvatar);
                rightProfilePictureContainer.add(Box.createHorizontalGlue());






                /*--------------------text recived from TextBox--------------------*/




                JTextArea holdTextMessage1 = new JTextArea();
                holdTextMessage1.setMinimumSize(new Dimension(rightSentMessageContainer.getPreferredSize().width - rightProfilePictureContainer.getPreferredSize().width,1));
                holdTextMessage1.append(chatLog.get(selectedFriend.getFriendEmail()));
                holdTextMessage1.setFont(new Font("Arial", Font.BOLD, 20));
                holdTextMessage1.setOpaque(false);
                holdTextMessage1.setLineWrap(true);
                holdTextMessage1.setWrapStyleWord(true);
                holdTextMessage1.setEditable(false);
                holdTextMessage1.setBorder(new LineBorder(Color.CYAN));
                holdTextMessage1.setAlignmentY(Component.TOP_ALIGNMENT);
                SwingUtilities.invokeLater(() -> {
                    FontMetrics metrics = holdTextMessage1.getFontMetrics(holdTextMessage1.getFont());
                    int lineHeight = metrics.getHeight();

                    int areaWidth = rightSentMessageContainer.getPreferredSize().width - rightProfilePictureContainer.getPreferredSize().width;
                    int areaHeight = holdTextMessage1.getHeight();

                    int charsPerLine = areaWidth / metrics.charWidth('m');

                    int numLines = areaHeight / lineHeight;

                    int totalChars = holdTextMessage1.getText().length();

                    int totalLinesNeeded = (int) Math.ceil((double) totalChars / charsPerLine);

                    int totalHeight = totalLinesNeeded * lineHeight;
                    holdTextMessage1.setSize(new Dimension(rightSentMessageContainer.getPreferredSize().width - rightProfilePictureContainer.getPreferredSize().width, totalHeight));
                    System.out.println(new Dimension(rightSentMessageContainer.getPreferredSize().width - rightProfilePictureContainer.getPreferredSize().width, totalHeight));
                    holdTextMessage1.setMinimumSize(holdTextMessage1.getPreferredSize());
                    holdTextMessage1.setMaximumSize(holdTextMessage1.getPreferredSize());
                    holdTextMessage1.setForeground(Color.WHITE);
                    holdTextMessage1.setBackground(Color.green);

                    System.out.println(holdTextMessage1.getSize().height +" "+ rightSentMessageContainer.getPreferredSize().height);

                    if (holdTextMessage1.getSize().height > rightSentMessageContainer.getPreferredSize().height) {
                        System.out.println("Max size set");
                        rightSentMessageContainer.setPreferredSize(new Dimension((int) (mainRightPanel.getPreferredSize().width / 1.1), holdTextMessage1.getPreferredSize().height));
                        rightSentMessageContainer.setMinimumSize(rightSentMessageContainer.getPreferredSize());
                        rightSentMessageContainer.setMaximumSize(rightSentMessageContainer.getPreferredSize());
                    }
                });

                rightSentMessageContainer.add(rightProfilePictureContainer);
                rightSentMessageContainer.add(Box.createHorizontalGlue());
                rightSentMessageContainer.add(holdTextMessage1);

                messageStorage.add(rightSentMessageContainer);
                messageStorage.add(Box.createRigidArea(new Dimension(rightSentMessageContainer.getPreferredSize().width, instance.getPreferredSize().height/20)));
            }








            messageStorage.revalidate();
            messageStorage.repaint();
            messagesScrollPane.setViewportView(messageStorage);
            SwingUtilities.invokeLater(() -> messagesScrollPane.getVerticalScrollBar().setValue(messagesScrollPane.getVerticalScrollBar().getMaximum()));
        }
    isFirstRun = false;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        // Draw the image to fill the entire panel
        if (scaledSettingsPanelBackgroundIcon != null) {
            g.drawImage(scaledSettingsPanelBackgroundIcon.getImage(), 0, 0, getWidth(), getHeight(), this);
        } else {
            System.out.println("Error");
        }
    }
}
