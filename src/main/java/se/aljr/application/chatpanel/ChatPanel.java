package se.aljr.application.chatpanel;

import se.aljr.application.*;
import se.aljr.application.Friends.Friend;
import se.aljr.application.Friends.FriendsList;
import se.aljr.application.loginpage.FirebaseManager;
import se.aljr.application.settings.SettingsPanel;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ChatPanel extends JPanel {

    public static int currentPage = 0;

    ImageIcon settingsPanelBackground;
    Image scaleSettingsPanelBackground;
    ImageIcon scaledSettingsPanelBackgroundIcon;

    ImageIcon lightSettingsPanelBackground;
    Image lightScaleSettingsPanelBackground;
    ImageIcon lightScaledSettingsPanelBackgroundIcon;

    private static final JPanel requestsPanel = new JPanel();
    private static final JScrollPane friendsScrollPane = new JScrollPane();
    private static final JScrollPane requestScrollPane = new JScrollPane();
    private static volatile ChatPanel instance;

    private static ImageIcon scaledProfilePictureIcon;
    private static ImageAvatar leftAvatar;
    private static ImageAvatar rightAvatar;
    private static ImageIcon profilePictureIcon;

    public static Friend selectedFriend;

    private static final JPanel mainRightPanel = new JPanel();
    private static JPanel messageStorage = new JPanel();
    private static final JScrollPane messagesScrollPane = new JScrollPane();
    public static final JPanel friendsPanel = new JPanel();

    public static boolean canSelectChat = false;

    public static JButton clickToSendButton = new JButton("✉");

    JPanel leftPanelThatHoldsEverything;
    JPanel belowPanel;
    JTextArea messengerTextBox;
    JPanel belowRightPanel;
    JPanel mainMiddlePanel;
    JPanel addPanel;
    JTextField friendRequestMailText;
    JButton addbutton;
    JLabel friendsText;
    JPanel mainMiddleTopPanel;
    JPanel mainLeftPanel;
    JPanel mainPanel;
    JPanel rightSideTopPanel;
    JPanel leftBottomPanel;
    JLabel requestText;
    JLabel sendRequestText;
    JPanel leftTopPanel;

    private boolean canSendMessage = true;


    public ChatPanel(int width, int height) {
        settingsPanelBackground = new ImageIcon(ResourcePath.getResourcePath() + "emptyBackground.png");
        scaleSettingsPanelBackground = settingsPanelBackground.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);
        scaledSettingsPanelBackgroundIcon = new ImageIcon(scaleSettingsPanelBackground);

        lightSettingsPanelBackground = new ImageIcon(ResourcePath.getResourcePath() +"lightEmptyBackground.png");
        lightScaleSettingsPanelBackground = lightSettingsPanelBackground.getImage().getScaledInstance(width,height,Image.SCALE_SMOOTH);
        lightScaledSettingsPanelBackgroundIcon = new ImageIcon(lightScaleSettingsPanelBackground);


        this.setPreferredSize(new Dimension(width, height));
        this.setBackground(AppThemeColors.SECONDARY);
        this.setOpaque(false);
        this.setLayout(new BorderLayout(0, 0));
        this.setBorder(new EmptyBorder(15, 15, 15, 15));


        instance = this;
        init();
    }

    private void init() {



        /*--------------------Main panel--------------------*/
        mainPanel = new JPanel();
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


        //Panel where the textfield for the chat is in
        belowPanel = new JPanel();
        belowPanel.setLayout(new BoxLayout(belowPanel, BoxLayout.X_AXIS));
        belowPanel.setOpaque(true);
        belowPanel.setBackground(AppThemeColors.SECONDARY);
        belowPanel.setPreferredSize(new Dimension(mainRightPanel.getPreferredSize().width, mainRightPanel.getPreferredSize().height / 8));
        belowPanel.setMinimumSize(mainRightPanel.getPreferredSize());
        belowPanel.setMaximumSize(mainRightPanel.getPreferredSize());


        rightSideTopPanel = new JPanel();
        rightSideTopPanel.setOpaque(false);
        rightSideTopPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        rightSideTopPanel.setLayout(new BoxLayout(rightSideTopPanel, BoxLayout.X_AXIS));
        rightSideTopPanel.setPreferredSize(new Dimension((int) (mainRightPanel.getPreferredSize().width / 1.2), mainRightPanel.getPreferredSize().height / 10));
        rightSideTopPanel.setMinimumSize(rightSideTopPanel.getPreferredSize());
        rightSideTopPanel.setMaximumSize(rightSideTopPanel.getPreferredSize());
        rightSideTopPanel.setBackground(Color.BLACK);



        messageStorage.setLayout(new BoxLayout(messageStorage, BoxLayout.Y_AXIS));
        messageStorage.setOpaque(true);
//        messageStorage.setBackground(Color.YELLOW);
        messageStorage.setBackground(AppThemeColors.textFieldColor);
        messageStorage.setPreferredSize(null);


        messagesScrollPane.setOpaque(true);
        messagesScrollPane.setPreferredSize(new Dimension(mainRightPanel.getPreferredSize().width, (int) (mainRightPanel.getPreferredSize().height / 1.05 - belowPanel.getPreferredSize().height)));
        messagesScrollPane.setMinimumSize(messagesScrollPane.getPreferredSize());
        messagesScrollPane.setMaximumSize(messagesScrollPane.getPreferredSize());
        messagesScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        messagesScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        messagesScrollPane.getVerticalScrollBar().setPreferredSize(new Dimension(0, 0));
        messagesScrollPane.getVerticalScrollBar().setUnitIncrement(6);
        messagesScrollPane.getViewport().setBackground(Color.GREEN);
        messagesScrollPane.setBorder(new LineBorder(AppThemeColors.textFieldColor));


        messengerTextBox = new JTextArea();
        messengerTextBox.setFont(new Font("Arial", Font.PLAIN, (int) (getPreferredSize().width / 80f)));
        messengerTextBox.setPreferredSize(new Dimension((int) (belowPanel.getPreferredSize().width / 1.2), (int) (belowPanel.getPreferredSize().height / (1.2*4))));
        messengerTextBox.setMinimumSize(messengerTextBox.getPreferredSize());
        messengerTextBox.setMaximumSize(messengerTextBox.getPreferredSize());
        messengerTextBox.setForeground(AppThemeColors.foregroundColor);
        messengerTextBox.setBackground(AppThemeColors.buttonBG);
        messengerTextBox.setLineWrap(true);
        messengerTextBox.setWrapStyleWord(true);
        messengerTextBox.setBorder(new LineBorder(Color.WHITE));
        messengerTextBox.setFocusable(true);
        messengerTextBox.setAlignmentX(JComponent.CENTER_ALIGNMENT);
        messengerTextBox.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if(e.getKeyCode() == KeyEvent.VK_ENTER){
                    //Makes sure a new line isn't created when pressing enter to send a message.
                    e.consume();
                }
            }
        });
        //messengerTextBox.setRows(1);
        messengerTextBox.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {

                if(e.getKeyCode() == KeyEvent.VK_ENTER){
                    clickToSendButton.doClick();
                }
            }
        });
        messengerTextBox.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                FontMetrics metrics = messengerTextBox.getFontMetrics(messengerTextBox.getFont());
                int lineHeight = metrics.getHeight();

                int areaWidth = (int) (belowPanel.getPreferredSize().width / 1.2);


                String text = messengerTextBox.getText();
                int totalTextWidth = metrics.stringWidth(text);
                if(totalTextWidth == areaWidth){
                    totalTextWidth++;
                }
                System.out.println("areawidth: "+areaWidth+ " text width: "+totalTextWidth);
                int numLines = (int) Math.ceil((double) totalTextWidth / areaWidth);
                if((areaWidth*numLines-totalTextWidth)<metrics.charWidth('M')){
                    numLines = (int) Math.ceil((double) totalTextWidth / areaWidth)+1;
                }

                System.out.println("numLines: " +numLines);

                int totalHeight = numLines * lineHeight;

                if (totalHeight < metrics.getHeight()) {
                    totalHeight = metrics.getHeight();
                }

                if (totalHeight < (int) (belowPanel.getPreferredSize().height / (1.2))) {
                    messengerTextBox.setPreferredSize(new Dimension(messengerTextBox.getPreferredSize().width, totalHeight));
                    messengerTextBox.setMinimumSize(messengerTextBox.getPreferredSize());
                    messengerTextBox.setMaximumSize(messengerTextBox.getPreferredSize());
                }
                messengerTextBox.setAlignmentX(JComponent.CENTER_ALIGNMENT);
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                FontMetrics metrics = messengerTextBox.getFontMetrics(messengerTextBox.getFont());
                int lineHeight = metrics.getHeight();

                int areaWidth = (int) (belowPanel.getPreferredSize().width / 1.2);


                String text = messengerTextBox.getText();
                int totalTextWidth = metrics.stringWidth(text);


                int numLines = (int) Math.ceil((double) totalTextWidth / areaWidth);

                int totalHeight = numLines * lineHeight;

                if (totalHeight < metrics.getHeight()) {
                    totalHeight = metrics.getHeight();
                }

                if (totalHeight < (int) (belowPanel.getPreferredSize().height / (1.2))) {
                    messengerTextBox.setPreferredSize(new Dimension(messengerTextBox.getPreferredSize().width, totalHeight));
                    messengerTextBox.setMinimumSize(messengerTextBox.getPreferredSize());
                    messengerTextBox.setMaximumSize(messengerTextBox.getPreferredSize());
                }

            }

            @Override
            public void changedUpdate(DocumentEvent e) {

            }
        });


        belowRightPanel = new JPanel();
        belowRightPanel.setLayout(new BoxLayout(belowRightPanel, BoxLayout.X_AXIS));
        belowRightPanel.setOpaque(false);
        belowRightPanel.setBackground(AppThemeColors.textFieldColor);
        belowRightPanel.setPreferredSize(new Dimension(belowPanel.getPreferredSize().width / 10, (int) (belowPanel.getPreferredSize().height / 1.2)));
        belowRightPanel.setMinimumSize(belowRightPanel.getPreferredSize());
        belowRightPanel.setMaximumSize(belowRightPanel.getPreferredSize());


        clickToSendButton.setFont(new Font("Ariel", Font.BOLD, (int) (getPreferredSize().width / 50f)));
        clickToSendButton.setMargin(new Insets(0, 0, 0, 0));
//        clickToSendButton.setBackground(Color.RED);
        clickToSendButton.setForeground(AppThemeColors.foregroundColor);
        clickToSendButton.setBackground(AppThemeColors.buttonBG);
        clickToSendButton.setPreferredSize(new Dimension((int) (belowRightPanel.getPreferredSize().width / 1.6), (int) (belowRightPanel.getPreferredSize().height / 1.8)));
        clickToSendButton.setAlignmentY(Component.CENTER_ALIGNMENT);
        clickToSendButton.setMinimumSize(clickToSendButton.getPreferredSize());
        clickToSendButton.setMaximumSize(clickToSendButton.getPreferredSize());
        clickToSendButton.setBorderPainted(false);
        clickToSendButton.setFocusable(false);
        clickToSendButton.addActionListener(_ -> {
           if(selectedFriend!=null&&canSendMessage&&!messengerTextBox.getText().isEmpty()){
               messengerTextBox.setEditable(false);
               canSendMessage = false;
               FirebaseManager.writeDBwriteMessageHistory(selectedFriend.getFriendEmail(),UserData.getEmail(),messengerTextBox.getText());
               messengerTextBox.setText("");
               SwingUtilities.invokeLater(()->{
                   messengerTextBox.setCaretPosition(0);
                   messengerTextBox.requestFocusInWindow();
                   messengerTextBox.setEditable(true);
               });
               new Timer(500, _ -> canSendMessage=true){
                   {
                       setRepeats(false);
                   }
               }.start();

           }
        });


                /*--------------------Middle panel--------------------*/
        mainMiddlePanel = new JPanel();
        mainMiddlePanel.setOpaque(false);
        mainMiddlePanel.setLayout(new BoxLayout(mainMiddlePanel, BoxLayout.Y_AXIS));
        mainMiddlePanel.setPreferredSize(new Dimension(getPreferredSize().width / 5, getPreferredSize().height));
        mainMiddlePanel.setMinimumSize(mainMiddlePanel.getPreferredSize());
        mainMiddlePanel.setMaximumSize(mainMiddlePanel.getPreferredSize());


        friendsScrollPane.setPreferredSize(new Dimension(mainMiddlePanel.getPreferredSize().width, (int)(mainMiddlePanel.getPreferredSize().height/1.05)));
        friendsScrollPane.setMinimumSize(friendsScrollPane.getPreferredSize());
        friendsScrollPane.setMaximumSize(friendsScrollPane.getPreferredSize());
        friendsScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        friendsScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        friendsScrollPane.getVerticalScrollBar().setPreferredSize(new Dimension(0, 0));
        friendsScrollPane.getVerticalScrollBar().setUnitIncrement(6);
        friendsScrollPane.getViewport().setBackground(AppThemeColors.panelColor);
        friendsScrollPane.setBorder(null);


        friendsPanel.setLayout(new BoxLayout(friendsPanel, BoxLayout.Y_AXIS));
        friendsPanel.setOpaque(false);

        try {
            FirebaseManager.readDBlistenToClientChats();
        }catch (Exception _){

        }

        updateFriends();
        updateRequestsPanel();

        addPanel = new JPanel();
        addPanel.setLayout(new BoxLayout(addPanel, BoxLayout.Y_AXIS));
        addPanel.setPreferredSize(new Dimension(mainMiddlePanel.getPreferredSize().width, mainMiddlePanel.getPreferredSize().height / 4));
        addPanel.setMinimumSize(addPanel.getPreferredSize());
        addPanel.setMaximumSize(addPanel.getPreferredSize());
        addPanel.setBackground(AppThemeColors.panelColor);
        addPanel.setAlignmentY(Component.CENTER_ALIGNMENT);


        mainMiddleTopPanel = new JPanel();
        mainMiddleTopPanel.setOpaque(true);
        mainMiddleTopPanel.setLayout(new BoxLayout(mainMiddleTopPanel, BoxLayout.Y_AXIS));
        mainMiddleTopPanel.setBackground(AppThemeColors.panelColor);
        mainMiddleTopPanel.setPreferredSize(new Dimension((int)(mainMiddlePanel.getPreferredSize().width), (int)(mainMiddlePanel.getPreferredSize().height-friendsScrollPane.getPreferredSize().height)));
        mainMiddleTopPanel.setMinimumSize(mainMiddleTopPanel.getPreferredSize());
        mainMiddleTopPanel.setMaximumSize(mainMiddleTopPanel.getPreferredSize());
        mainMiddleTopPanel.setAlignmentX(Component.CENTER_ALIGNMENT);


        friendsText = new JLabel("Friends");
        friendsText.setFont(new Font("Arial", Font.BOLD, (int) (getPreferredSize().width / 40f)));
        friendsText.setOpaque(true);
        friendsText.setBackground(AppThemeColors.panelColor);
        friendsText.setForeground(AppThemeColors.foregroundColor);
        friendsText.setAlignmentX(Component.CENTER_ALIGNMENT);




        /*--------------------Add panel components--------------------*/


        friendRequestMailText = new JTextField();
        friendRequestMailText.setFont(new Font("Arial", Font.BOLD, (int) (getPreferredSize().width / 100f)));
        friendRequestMailText.setPreferredSize(new Dimension((int) (addPanel.getPreferredSize().width * 0.9), (int) (addPanel.getPreferredSize().height * 0.15)));
        friendRequestMailText.setMinimumSize(friendRequestMailText.getPreferredSize());
        friendRequestMailText.setMaximumSize(friendRequestMailText.getPreferredSize());
        friendRequestMailText.setForeground(AppThemeColors.foregroundColor);
        friendRequestMailText.setBackground(AppThemeColors.textFieldColor);


        addbutton = new JButton("ADD");
        addbutton.setFont(new Font("Arial", Font.BOLD, 50));
        addbutton.setBorder(new LineBorder(Color.BLACK));
        addbutton.setBackground(AppThemeColors.buttonBG);
        addbutton.setForeground(AppThemeColors.foregroundColor);
        addbutton.setPreferredSize(new Dimension(friendsScrollPane.getPreferredSize().width / 3, 50));
        addbutton.setMinimumSize(addbutton.getPreferredSize());
        addbutton.setMaximumSize(addbutton.getPreferredSize());
        addbutton.setAlignmentX(Component.CENTER_ALIGNMENT);
        addbutton.setBorderPainted(false);
        addbutton.setFocusable(false);
        addbutton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
                Pattern pattern = Pattern.compile(emailRegex);
                Matcher matcher = pattern.matcher(friendRequestMailText.getText());
                if(matcher.matches()){
                    try {
                        FirebaseManager.writeDBsendFriendRequest(friendRequestMailText.getText());
                    } catch (ExecutionException | InterruptedException ex) {
                        throw new RuntimeException(ex);
                    }
                    friendRequestMailText.setText("");
                }else{
                    friendRequestMailText.setText("Invalid email adress");
                    new Timer(1000,_-> friendRequestMailText.setText("")){
                        {
                            setRepeats(false);
                        }
                    }.start();

                }



            }
        });

        /*--------------------Left panel--------------------*/
        mainLeftPanel = new JPanel();
        mainLeftPanel.setOpaque(false);
        mainLeftPanel.setBackground(Color.WHITE);
        mainLeftPanel.setPreferredSize(new Dimension(getPreferredSize().width / 5, getPreferredSize().height));
        mainLeftPanel.setMinimumSize(mainLeftPanel.getPreferredSize());
        mainLeftPanel.setMaximumSize(mainLeftPanel.getPreferredSize());
        mainLeftPanel.setLayout(new BorderLayout());





        /*--------------------(Left panel) Buttons Panel--------------------*/
        leftPanelThatHoldsEverything = new JPanel();
        leftPanelThatHoldsEverything.setOpaque(false);
        leftPanelThatHoldsEverything.setBackground(AppThemeColors.panelColor);
        leftPanelThatHoldsEverything.setLayout(new BoxLayout(leftPanelThatHoldsEverything, BoxLayout.Y_AXIS));
        leftPanelThatHoldsEverything.setPreferredSize(new Dimension(mainLeftPanel.getPreferredSize().width, mainLeftPanel.getPreferredSize().height));
        leftPanelThatHoldsEverything.setMinimumSize(leftPanelThatHoldsEverything.getPreferredSize());
        leftPanelThatHoldsEverything.setMaximumSize(leftPanelThatHoldsEverything.getPreferredSize());


        leftBottomPanel = new JPanel();
        leftBottomPanel.setOpaque(true);
        leftBottomPanel.setBackground(AppThemeColors.panelColor);
        leftBottomPanel.setLayout(new BoxLayout(leftBottomPanel, BoxLayout.Y_AXIS));
        leftBottomPanel.setPreferredSize(new Dimension(mainLeftPanel.getPreferredSize().width, mainLeftPanel.getPreferredSize().height/5));
        leftBottomPanel.setMinimumSize(leftBottomPanel.getPreferredSize());
        leftBottomPanel.setMaximumSize(leftBottomPanel.getPreferredSize());
        leftBottomPanel.setAlignmentX(Component.CENTER_ALIGNMENT);

        requestScrollPane.setOpaque(true);
        requestScrollPane.setPreferredSize(new Dimension(mainMiddlePanel.getPreferredSize().width, (int)(mainMiddlePanel.getPreferredSize().height/1.15-leftBottomPanel.getPreferredSize().height)));
        requestScrollPane.setMinimumSize(requestScrollPane.getPreferredSize());
        requestScrollPane.setMaximumSize(requestScrollPane.getPreferredSize());
        requestScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        requestScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        requestScrollPane.getVerticalScrollBar().setPreferredSize(new Dimension(0, 0));
        requestScrollPane.getVerticalScrollBar().setUnitIncrement(6);
        requestScrollPane.getViewport().setBackground(AppThemeColors.panelColor);
        requestScrollPane.setBorder(null);

        requestsPanel.setLayout(new BoxLayout(requestsPanel, BoxLayout.Y_AXIS));
        requestsPanel.setOpaque(false);



        requestText = new JLabel("Requests");
        requestText.setFont(new Font("Arial", Font.BOLD, (int) (getPreferredSize().width / 40f)));
        requestText.setOpaque(true);
        requestText.setBackground(AppThemeColors.panelColor);
        requestText.setForeground(AppThemeColors.foregroundColor);
        requestText.setAlignmentX(Component.CENTER_ALIGNMENT);

        sendRequestText = new JLabel("Send Request");
        sendRequestText.setFont(new Font("Arial", Font.BOLD, (int) (getPreferredSize().width / 40f)));
        sendRequestText.setOpaque(true);
        sendRequestText.setBackground(AppThemeColors.panelColor);
        sendRequestText.setForeground(AppThemeColors.foregroundColor);
        sendRequestText.setAlignmentX(Component.CENTER_ALIGNMENT);

        leftTopPanel = new JPanel();
        leftTopPanel.setOpaque(true);
        leftTopPanel.setLayout(new BoxLayout(leftTopPanel, BoxLayout.Y_AXIS));
        leftTopPanel.setPreferredSize(new Dimension(mainLeftPanel.getPreferredSize().width, requestText.getPreferredSize().height));
        leftTopPanel.setMinimumSize(leftTopPanel.getPreferredSize());
        leftTopPanel.setMaximumSize(leftTopPanel.getPreferredSize());
        leftTopPanel.setBackground(AppThemeColors.panelColor);


        /*--------------------ALL PANELS ADDS--------------------*/

        /*--------------------(Left panel)--------------------*/

        mainLeftPanel.add(leftPanelThatHoldsEverything);

        leftPanelThatHoldsEverything.add(leftTopPanel);
        leftPanelThatHoldsEverything.add(requestScrollPane);
        leftPanelThatHoldsEverything.add(Box.createVerticalGlue());
        leftPanelThatHoldsEverything.add(leftBottomPanel);

        leftTopPanel.add(requestText);

        requestScrollPane.add(requestsPanel);
        requestScrollPane.setViewportView(requestsPanel);

        leftBottomPanel.add(sendRequestText);
        leftBottomPanel.add(Box.createVerticalGlue());
        leftBottomPanel.add(friendRequestMailText);
        leftBottomPanel.add(addbutton);
        leftBottomPanel.add(Box.createVerticalGlue());
        leftBottomPanel.add(Box.createVerticalGlue());

        /*--------------------(Middle panel)--------------------*/

        mainMiddlePanel.add(mainMiddleTopPanel);
        mainMiddlePanel.add(Box.createVerticalGlue());
        mainMiddlePanel.add(friendsScrollPane);

        friendsScrollPane.setViewportView(friendsPanel);
        mainMiddleTopPanel.add(friendsText);

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
    }

    public static void updateFriends(){
        friendsPanel.removeAll();
        if(instance!=null){
            for (Friend friend : FriendsList.getFriendArrayList()) {
                ImageIcon userIcon = FirebaseManager.readDBprofilePicture(friend.getFriendEmail());

                Image scaledFriendProfilePicture = userIcon.getImage().getScaledInstance(instance.getPreferredSize().width / 25, instance.getPreferredSize().width / 25, Image.SCALE_SMOOTH);
                ImageIcon scaledFriendProfilePictureIcon = new ImageIcon(scaledFriendProfilePicture);

                friend.getImageAvatarSocial().setPreferredSize(new Dimension(instance.getPreferredSize().width / 25, instance.getPreferredSize().width / 25));
                friend.getImageAvatarSocial().setMinimumSize(friend.getImageAvatarSocial().getPreferredSize());
                friend.getImageAvatarSocial().setMaximumSize(friend.getImageAvatarSocial().getPreferredSize());
                friend.getImageAvatarSocial().setImage(scaledFriendProfilePictureIcon);
                friend.getImageAvatarSocial().setAlignmentY(Component.CENTER_ALIGNMENT);
                friend.getImageAvatarSocial().setBorderSize(instance.getPreferredSize().height/221);
                friend.getImageAvatarSocial().setBorderSpace((int) (instance.getPreferredSize().height/331.5));

                friend.setMessageStorage(new JPanel(){
                    {
                        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
                        setOpaque(true);
                        setBackground(AppThemeColors.textFieldColor);
                        setPreferredSize(null);
                    }
                });


                JPanel friendPanel = new JPanel();
                friendPanel.setOpaque(false);
                friendPanel.setPreferredSize(new Dimension(friendsScrollPane.getPreferredSize().width, (int) (friend.getImageAvatarSocial().getPreferredSize().height * 1.1)));
                friendPanel.setLayout(new BoxLayout(friendPanel, BoxLayout.X_AXIS));
                friendPanel.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        super.mouseClicked(e);
                        if(canSelectChat){
                            selectedFriend = friend;
                            updateChat();
                            System.out.println(friend.getFriendName());
                        }
                    }
                });

                JLabel friendNameLabel = new JLabel(FriendsList.getFriendArrayList().get(FriendsList.getFriendArrayList().indexOf(friend)).getFriendName());
                friendNameLabel.setFont(CustomFont.getFont().deriveFont(instance.getPreferredSize().width / 65f));
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
            acceptFriendRequestButton.setFocusPainted(false);
            acceptFriendRequestButton.setFocusable(false);
            acceptFriendRequestButton.setBorderPainted(false);
            acceptFriendRequestButton.setForeground(AppThemeColors.foregroundColor);
            acceptFriendRequestButton.setPreferredSize(new Dimension(30, 25));
            acceptFriendRequestButton.setAlignmentY(Component.CENTER_ALIGNMENT);
            acceptFriendRequestButton.setMinimumSize(acceptFriendRequestButton.getPreferredSize());
            acceptFriendRequestButton.setMaximumSize(acceptFriendRequestButton.getPreferredSize());
            acceptFriendRequestButton.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    FriendsList.getFriendArrayList().add(new Friend() {
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
            denyFriendRequestButton.setFocusPainted(false);
            denyFriendRequestButton.setFocusable(false);
            denyFriendRequestButton.setBorderPainted(false);
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
                        instance.repaint();
                        instance.revalidate();
                    }
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
    }

    public static void updateChat(){

        //System.out.println(selectedFriend);
        ArrayList<HashMap<String,String>> newMessages;
        if(selectedFriend.firstLoadIn){
            newMessages = selectedFriend.getChat();

        }else{
            newMessages =  new ArrayList<>(selectedFriend.getChat().subList((selectedFriend.getMessageStorage().getComponentCount()/2), selectedFriend.getChat().size()));
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
                leftSentMessageContainer.setPreferredSize(new Dimension((int) (mainRightPanel.getPreferredSize().width / 1.05), instance.getPreferredSize().width/25));
                leftSentMessageContainer.setMinimumSize(leftSentMessageContainer.getPreferredSize());
                leftSentMessageContainer.setMaximumSize(leftSentMessageContainer.getPreferredSize());
                leftSentMessageContainer.setAlignmentX(Component.CENTER_ALIGNMENT);


                JPanel leftProfilePictureContainer = new JPanel();
                leftProfilePictureContainer.setLayout(new BoxLayout(leftProfilePictureContainer, BoxLayout.X_AXIS));
                leftProfilePictureContainer.setOpaque(false);
                leftProfilePictureContainer.setBackground(AppThemeColors.textFieldColor);
                leftProfilePictureContainer.setPreferredSize(new Dimension(leftSentMessageContainer.getPreferredSize().width / 8, instance.getPreferredSize().width / 25));
                leftProfilePictureContainer.setMinimumSize(leftProfilePictureContainer.getPreferredSize());
                leftProfilePictureContainer.setMaximumSize(leftProfilePictureContainer.getPreferredSize());
                leftProfilePictureContainer.setAlignmentY(Component.TOP_ALIGNMENT);

                profilePictureIcon = FirebaseManager.readDBprofilePicture(UserData.getEmail());
                leftAvatar = new ImageAvatar();
                leftAvatar.setPreferredSize(new Dimension( instance.getPreferredSize().width / 25, instance.getPreferredSize().width / 25));
                Image scaledProfilePicture = profilePictureIcon.getImage().getScaledInstance(instance.getPreferredSize().width / 25, instance.getPreferredSize().width / 25, Image.SCALE_SMOOTH);
                scaledProfilePictureIcon = new ImageIcon(scaledProfilePicture);
                leftAvatar.setImage(scaledProfilePictureIcon);
                leftAvatar.setBorderSize(instance.getPreferredSize().height/221);
                leftAvatar.setBorderSpace((int) (instance.getPreferredSize().height/331.5));

                leftProfilePictureContainer.add(Box.createHorizontalGlue());
                leftProfilePictureContainer.add(leftAvatar);
                leftProfilePictureContainer.add(Box.createHorizontalGlue());

                JTextArea holdTextMessage = new JTextArea();
                holdTextMessage.setMinimumSize(new Dimension(new Dimension(leftSentMessageContainer.getPreferredSize().width - leftProfilePictureContainer.getPreferredSize().width,1)));
                holdTextMessage.setFont(new Font("Arial", Font.PLAIN, (int)(instance.getPreferredSize().width/65f)));
                holdTextMessage.setOpaque(false);
                holdTextMessage.setLineWrap(true);
                holdTextMessage.setWrapStyleWord(true);
                holdTextMessage.append(message);
                holdTextMessage.setForeground(new Color(212, 215, 218));
                //System.out.println(holdTextMessage.getText());
                //holdTextMessage.setBorder(new LineBorder(AppThemeColors.PRIMARY,1,true));
                holdTextMessage.setEditable(false);
                holdTextMessage.setAlignmentY(Component.TOP_ALIGNMENT);
                SwingUtilities.invokeLater(() -> {
                    FontMetrics metrics = holdTextMessage.getFontMetrics(holdTextMessage.getFont());
                    int lineHeight = metrics.getHeight();

                    int areaWidth = leftSentMessageContainer.getPreferredSize().width - leftProfilePictureContainer.getPreferredSize().width;

                    int charsPerLine = areaWidth / metrics.charWidth('m');

                    int totalChars = holdTextMessage.getText().length();

                    int totalLinesNeeded = (int) Math.ceil((double) totalChars / charsPerLine);

                    int totalHeight = totalLinesNeeded * lineHeight;
                    holdTextMessage.setSize(new Dimension(leftSentMessageContainer.getPreferredSize().width - leftProfilePictureContainer.getPreferredSize().width, totalHeight));
                    //holdTextMessage.setPreferredSize(holdTextMessage.getSize());
                    //System.out.println(new Dimension(leftSentMessageContainer.getPreferredSize().width - leftProfilePictureContainer.getPreferredSize().width, totalHeight));
                    holdTextMessage.setMinimumSize(holdTextMessage.getPreferredSize());
                    holdTextMessage.setMaximumSize(holdTextMessage.getPreferredSize());
                    holdTextMessage.setForeground(Color.WHITE);
                    holdTextMessage.setBackground(Color.green);
                    //System.out.println(holdTextMessage.getSize().height +" "+ leftSentMessageContainer.getPreferredSize().height);
                    if (holdTextMessage.getSize().height > leftSentMessageContainer.getPreferredSize().height) {
                        leftSentMessageContainer.setPreferredSize(new Dimension((int) (mainRightPanel.getPreferredSize().width / 1.05), holdTextMessage.getPreferredSize().height));
                        leftSentMessageContainer.setMinimumSize(leftSentMessageContainer.getPreferredSize());
                        leftSentMessageContainer.setMaximumSize(leftSentMessageContainer.getPreferredSize());
                    }
                });
                leftSentMessageContainer.add(leftProfilePictureContainer);
                leftSentMessageContainer.add(Box.createHorizontalGlue());
                leftSentMessageContainer.add(holdTextMessage);

                selectedFriend.getMessageStorage().add(leftSentMessageContainer);
                selectedFriend.getMessageStorage().add(Box.createRigidArea(new Dimension(leftSentMessageContainer.getPreferredSize().width, instance.getPreferredSize().height/20)));
            }else{
                /*--------------------right side of chat--------------------*/
                JPanel rightSentMessageContainer = new JPanel();
                rightSentMessageContainer.setAlignmentX(Component.CENTER_ALIGNMENT);
                rightSentMessageContainer.setLayout(new BoxLayout(rightSentMessageContainer, BoxLayout.X_AXIS));
                rightSentMessageContainer.setOpaque(false);
                rightSentMessageContainer.setBackground(Color.PINK);
                rightSentMessageContainer.setPreferredSize(new Dimension((int) (mainRightPanel.getPreferredSize().width / 1.05), instance.getPreferredSize().width/25));
                rightSentMessageContainer.setMinimumSize(rightSentMessageContainer.getPreferredSize());
                rightSentMessageContainer.setMaximumSize(rightSentMessageContainer.getPreferredSize());

                JPanel rightProfilePictureContainer = new JPanel();
                rightProfilePictureContainer.setLayout(new BoxLayout(rightProfilePictureContainer, BoxLayout.X_AXIS));
                rightProfilePictureContainer.setOpaque(false);
                rightProfilePictureContainer.setBackground(AppThemeColors.textFieldColor);
                rightProfilePictureContainer.setBackground(Color.BLUE);
                rightProfilePictureContainer.setPreferredSize(new Dimension(rightSentMessageContainer.getPreferredSize().width / 8, instance.getPreferredSize().width / 25));
                rightProfilePictureContainer.setMinimumSize(rightProfilePictureContainer.getPreferredSize());
                rightProfilePictureContainer.setMaximumSize(rightProfilePictureContainer.getPreferredSize());
                rightProfilePictureContainer.setAlignmentY(Component.TOP_ALIGNMENT);

                rightAvatar = new ImageAvatar();
                rightAvatar.setPreferredSize(new Dimension(instance.getPreferredSize().width / 25, instance.getPreferredSize().width / 25));
                ImageIcon profilePictureIconFriend = FirebaseManager.readDBprofilePicture(selectedFriend.getFriendEmail());
                Image scaledProfilePicture = profilePictureIconFriend.getImage().getScaledInstance(instance.getPreferredSize().width / 25, instance.getPreferredSize().width / 25, Image.SCALE_SMOOTH);
                scaledProfilePictureIcon = new ImageIcon(scaledProfilePicture);
                rightAvatar.setImage(scaledProfilePictureIcon);
                rightAvatar.setBorderSize(instance.getPreferredSize().height/221);
                rightAvatar.setBorderSpace((int) (instance.getPreferredSize().height/331.5));

                rightProfilePictureContainer.add(Box.createHorizontalGlue());
                rightProfilePictureContainer.add(rightAvatar);
                rightProfilePictureContainer.add(Box.createHorizontalGlue());






                /*--------------------text recived from TextBox--------------------*/




                JTextArea holdTextMessage1 = new JTextArea();
                holdTextMessage1.setMinimumSize(new Dimension(rightSentMessageContainer.getPreferredSize().width - rightProfilePictureContainer.getPreferredSize().width,1));
                holdTextMessage1.append(chatLog.get(selectedFriend.getFriendEmail()));
                holdTextMessage1.setFont(new Font("Arial", Font.PLAIN, (int)(instance.getPreferredSize().width/65f)));
                holdTextMessage1.setOpaque(false);
                holdTextMessage1.setLineWrap(true);
                holdTextMessage1.setWrapStyleWord(true);
                holdTextMessage1.setEditable(false);
                holdTextMessage1.setForeground(new Color(212, 215, 218));
                //holdTextMessage1.setBorder(new LineBorder(AppThemeColors.PRIMARY,1,true));
                holdTextMessage1.setAlignmentY(Component.TOP_ALIGNMENT);
                SwingUtilities.invokeLater(() -> {
                    FontMetrics metrics = holdTextMessage1.getFontMetrics(holdTextMessage1.getFont());
                    int lineHeight = metrics.getHeight();

                    int areaWidth = rightSentMessageContainer.getPreferredSize().width - rightProfilePictureContainer.getPreferredSize().width;

                    int charsPerLine = areaWidth / metrics.charWidth('m');

                    int totalChars = holdTextMessage1.getText().length();

                    int totalLinesNeeded = (int) Math.ceil((double) totalChars / charsPerLine);

                    int totalHeight = totalLinesNeeded * lineHeight;
                    holdTextMessage1.setSize(new Dimension(rightSentMessageContainer.getPreferredSize().width - rightProfilePictureContainer.getPreferredSize().width, totalHeight));
                    //System.out.println(new Dimension(rightSentMessageContainer.getPreferredSize().width - rightProfilePictureContainer.getPreferredSize().width, totalHeight));
                    holdTextMessage1.setMinimumSize(holdTextMessage1.getPreferredSize());
                    holdTextMessage1.setMaximumSize(holdTextMessage1.getPreferredSize());
                    holdTextMessage1.setForeground(Color.WHITE);
                    holdTextMessage1.setBackground(Color.green);

                    //System.out.println(holdTextMessage1.getSize().height +" "+ rightSentMessageContainer.getPreferredSize().height);

                    if (holdTextMessage1.getSize().height > rightSentMessageContainer.getPreferredSize().height) {
                        System.out.println("Max size set");
                        rightSentMessageContainer.setPreferredSize(new Dimension((int) (mainRightPanel.getPreferredSize().width / 1.05), holdTextMessage1.getPreferredSize().height));
                        rightSentMessageContainer.setMinimumSize(rightSentMessageContainer.getPreferredSize());
                        rightSentMessageContainer.setMaximumSize(rightSentMessageContainer.getPreferredSize());
                    }
                });

                rightSentMessageContainer.add(rightProfilePictureContainer);
                rightSentMessageContainer.add(Box.createHorizontalGlue());
                rightSentMessageContainer.add(holdTextMessage1);

                selectedFriend.getMessageStorage().add(rightSentMessageContainer);
                selectedFriend.getMessageStorage().add(Box.createRigidArea(new Dimension(rightSentMessageContainer.getPreferredSize().width, instance.getPreferredSize().height/20)));
            }








            selectedFriend.getMessageStorage().revalidate();
            selectedFriend.getMessageStorage().repaint();


        }
        if(!selectedFriend.firstLoadIn){
            messagesScrollPane.setViewportView(selectedFriend.getMessageStorage());
        }

        SwingUtilities.invokeLater(() -> messagesScrollPane.getVerticalScrollBar().setValue(messagesScrollPane.getVerticalScrollBar().getMaximum()));
        selectedFriend.firstLoadIn = false;
    }

    public void updateColors(){
        leftPanelThatHoldsEverything.setBackground(AppThemeColors.panelColor);
        belowPanel.setBackground(AppThemeColors.SECONDARY);
        messengerTextBox.setForeground(AppThemeColors.foregroundColor);
        messengerTextBox.setBackground(AppThemeColors.buttonBG);
        belowRightPanel.setBackground(AppThemeColors.textFieldColor);
        clickToSendButton.setForeground(AppThemeColors.foregroundColor);
        clickToSendButton.setBackground(AppThemeColors.buttonBG);
        addPanel.setBackground(AppThemeColors.panelColor);
        friendRequestMailText.setForeground(AppThemeColors.foregroundColor);
        friendRequestMailText.setBackground(AppThemeColors.textFieldColor);
        addbutton.setBackground(AppThemeColors.buttonBG);
        addbutton.setForeground(AppThemeColors.foregroundColor);
        mainRightPanel.setBackground(AppThemeColors.textFieldColor);
        messagesScrollPane.setBackground(AppThemeColors.textFieldColor);
        friendsScrollPane.getViewport().setBackground(AppThemeColors.panelColor);
        messageStorage.setBackground(AppThemeColors.textFieldColor);
    }


    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        // Draw the image to fill the entire panel
        if (scaledSettingsPanelBackgroundIcon != null || lightScaledSettingsPanelBackgroundIcon != null) {

            if(SettingsPanel.currentTheme.equals("dark")){
                g.drawImage(scaledSettingsPanelBackgroundIcon.getImage(), 0, 0, getWidth(), getHeight(), this);
            }else{
                g.drawImage(lightScaledSettingsPanelBackgroundIcon.getImage(), 0, 0, getWidth(), getHeight(), this);
            }
        }
        else{
            System.out.println("Error");
        }
        updateColors();
    }
}
