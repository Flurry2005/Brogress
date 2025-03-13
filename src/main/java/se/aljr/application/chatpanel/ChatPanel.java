package se.aljr.application.chatpanel;

import se.aljr.application.*;
import se.aljr.application.Friends.Friend;
import se.aljr.application.Friends.FriendsList;
import se.aljr.application.loginpage.FirebaseManager;
import se.aljr.application.settings.SettingsPanel;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.plaf.basic.BasicButtonUI;
import javax.swing.plaf.basic.BasicTextFieldUI;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ChatPanel extends JPanel {

    ImageIcon settingsPanelBackground;
    Image scaleSettingsPanelBackground;
    ImageIcon scaledSettingsPanelBackgroundIcon;

    ImageIcon lightSettingsPanelBackground;
    Image lightScaleSettingsPanelBackground;
    ImageIcon lightScaledSettingsPanelBackgroundIcon;

    public static JButton clickToSendButton = new JButton("✉");

    private static final JPanel messageStorage = new JPanel();
    private static final JPanel requestsPanel = new JPanel();
    private static final JPanel mainRightPanel = new JPanel();
    public static final JPanel friendsPanel = new JPanel();

    private static final JScrollPane friendsScrollPane = new JScrollPane();
    private static final JScrollPane requestScrollPane = new JScrollPane();
    private static final JScrollPane messagesScrollPane = new JScrollPane();

    private static volatile ChatPanel instance;

    private static ImageIcon scaledProfilePictureIcon;
    private static ImageAvatar userAvatar;
    private static ImageAvatar friendAvatar;
    private static ImageIcon profilePictureIcon;

    public static Friend selectedFriend;

    public static boolean canSelectChat = false;

    private boolean canSendMessage = true;

    JPanel leftPanelThatHoldsEverything;
    JPanel belowPanel;
    JPanel belowRightPanel;
    JPanel mainMiddlePanel;
    JPanel addPanel;
    JPanel mainMiddleTopPanel;
    JPanel mainLeftPanel;
    JPanel mainPanel;
    JPanel leftBottomPanel;
    JPanel leftTopPanel;

    JButton clickToSendRequestButton;

    JLabel friendsText;
    JLabel requestsText;
    JLabel sendRequestText;

    JTextArea messengerTextBox;

    JTextField friendRequestMailText;

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
        this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));

        instance = this;
        init();
    }

    private void init() {
        /*--------------------Main panel--------------------*/

        mainPanel = new JPanel();
        mainPanel.setOpaque(false);
        mainPanel.setPreferredSize(new Dimension((int) (getPreferredSize().width*0.98), (int) (getPreferredSize().height*0.98)));
        mainPanel.setMinimumSize(mainPanel.getPreferredSize());
        mainPanel.setMaximumSize(mainPanel.getPreferredSize());
        mainPanel.setAlignmentY(Component.CENTER_ALIGNMENT);
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.X_AXIS));

        /*--------------------Right panel--------------------*/

        mainRightPanel.setLayout(new BoxLayout(mainRightPanel, BoxLayout.Y_AXIS));
        mainRightPanel.setOpaque(true);
        mainRightPanel.setBackground(AppThemeColors.panelColor);
        mainRightPanel.setPreferredSize(new Dimension(getPreferredSize().width / 2, getPreferredSize().height));
        mainRightPanel.setMinimumSize(mainRightPanel.getPreferredSize());
        mainRightPanel.setMaximumSize(mainRightPanel.getPreferredSize());

        //Panel where the textfield for the chat is in
        belowPanel = new JPanel();
        belowPanel.setLayout(new BoxLayout(belowPanel, BoxLayout.X_AXIS));
        belowPanel.setOpaque(true);
        belowPanel.setBackground(AppThemeColors.SECONDARY);
        belowPanel.setPreferredSize(new Dimension(mainRightPanel.getPreferredSize().width, getPreferredSize().height/ 8));
        belowPanel.setMinimumSize(belowPanel.getPreferredSize());
        belowPanel.setMaximumSize(belowPanel.getPreferredSize());
        belowPanel.setBorder(new LineBorder(new Color(70, 70, 70),getPreferredSize().width/663));

        belowRightPanel = new JPanel();
        belowRightPanel.setLayout(new BoxLayout(belowRightPanel, BoxLayout.X_AXIS));
        belowRightPanel.setOpaque(false);
        belowRightPanel.setBackground(AppThemeColors.textFieldColor);
        belowRightPanel.setPreferredSize(new Dimension(belowPanel.getPreferredSize().width / 10, (int) (belowPanel.getPreferredSize().height / 1.2)));
        belowRightPanel.setMinimumSize(belowRightPanel.getPreferredSize());
        belowRightPanel.setMaximumSize(belowRightPanel.getPreferredSize());


        clickToSendButton.setFont(new Font("Ariel", Font.BOLD, (int) (getPreferredSize().width / 50f)));
        clickToSendButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        clickToSendButton.setUI(new BasicButtonUI() {
            @Override
            public void paint(Graphics g, JComponent c) {
                // Måla bakgrunden med rundade hörn
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(new Color(51, 137, 177)); // Grön färg
                g2.fillRoundRect(0, 0, c.getWidth(), c.getHeight(), instance.getHeight()/40, instance.getHeight()/40); // Rundade hörn

                // Måla texten (den kommer att målas av Swing, så vi ser till att inte skriva över den)
                super.paint(g, c);

                g2.dispose(); // Frigör Graphics2D
            }
            @Override
            protected void paintButtonPressed(Graphics g, AbstractButton b) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(new Color(47, 125, 161)); // Pressed button color
                g2.fillRoundRect(0, 0, b.getWidth(), b.getHeight(), instance.getHeight()/40, instance.getHeight()/40); // Rounded corners
                g2.dispose();
            }
        });
        clickToSendButton.setMargin(new Insets(0, 0, 0, 0));
//        clickToSendButton.setBackground(Color.RED);
        clickToSendButton.setForeground(AppThemeColors.foregroundColor);
        clickToSendButton.setPreferredSize(new Dimension((int) (belowRightPanel.getPreferredSize().width / 1.7), (int) (belowRightPanel.getPreferredSize().width / 1.7)));
        clickToSendButton.setAlignmentY(Component.CENTER_ALIGNMENT);
        clickToSendButton.setMinimumSize(clickToSendButton.getPreferredSize());
        clickToSendButton.setMaximumSize(clickToSendButton.getPreferredSize());
        clickToSendButton.setBorderPainted(false);
        clickToSendButton.setFocusable(false);
        clickToSendButton.setOpaque(false);
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

        messageStorage.setLayout(new BoxLayout(messageStorage, BoxLayout.Y_AXIS));
        messageStorage.setOpaque(true);
//        messageStorage.setBackground(Color.YELLOW);
        messageStorage.setBackground(AppThemeColors.panelColor);
        messageStorage.setPreferredSize(null);

        messagesScrollPane.setOpaque(true);
        messagesScrollPane.setPreferredSize(new Dimension(mainRightPanel.getPreferredSize().width, getPreferredSize().height - getPreferredSize().height/ 8));
        messagesScrollPane.setMinimumSize(messagesScrollPane.getPreferredSize());
        messagesScrollPane.setMaximumSize(messagesScrollPane.getPreferredSize());
        messagesScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        messagesScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        messagesScrollPane.getVerticalScrollBar().setPreferredSize(new Dimension(0, 0));
        messagesScrollPane.getVerticalScrollBar().setUnitIncrement((int) (getHeight()/110.5));
        messagesScrollPane.getViewport().setBackground(Color.GREEN);
        messagesScrollPane.setBorder(new LineBorder(new Color(70, 70, 70),getHeight()/663));

        messengerTextBox = new JTextArea();
        messengerTextBox.setFont(new Font("Arial", Font.PLAIN, (int) (getPreferredSize().width / 65f)));
        messengerTextBox.setPreferredSize(new Dimension((int) (belowPanel.getPreferredSize().width / 1.2),
                (int) (messengerTextBox.getFontMetrics(messengerTextBox.getFont()).getHeight()*1.3)));
        messengerTextBox.setMinimumSize(messengerTextBox.getPreferredSize());
        messengerTextBox.setMaximumSize(messengerTextBox.getPreferredSize());
        messengerTextBox.setForeground(AppThemeColors.foregroundColor);
        messengerTextBox.setBackground(AppThemeColors.buttonBG);
        messengerTextBox.setLineWrap(true);
        messengerTextBox.setWrapStyleWord(true);
        messengerTextBox.setBorder(new LineBorder(Color.WHITE));
        messengerTextBox.setFocusable(true);
        messengerTextBox.setAlignmentY(JComponent.CENTER_ALIGNMENT);
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

                if (totalHeight < (int) (belowPanel.getPreferredSize().height)) {
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

        /*--------------------Middle panel--------------------*/

        mainMiddlePanel = new JPanel();
        mainMiddlePanel.setOpaque(false);
        mainMiddlePanel.setLayout(new BoxLayout(mainMiddlePanel, BoxLayout.Y_AXIS));
        mainMiddlePanel.setPreferredSize(new Dimension(getPreferredSize().width / 5, getPreferredSize().height));
        mainMiddlePanel.setMinimumSize(mainMiddlePanel.getPreferredSize());
        mainMiddlePanel.setMaximumSize(mainMiddlePanel.getPreferredSize());

        mainMiddleTopPanel = new JPanel();
        mainMiddleTopPanel.setOpaque(true);
        mainMiddleTopPanel.setLayout(new BoxLayout(mainMiddleTopPanel, BoxLayout.Y_AXIS));
        mainMiddleTopPanel.setBackground(AppThemeColors.panelColor);
        mainMiddleTopPanel.setPreferredSize(new Dimension(mainMiddlePanel.getPreferredSize().width, mainMiddlePanel.getPreferredSize().height-friendsScrollPane.getPreferredSize().height));
        mainMiddleTopPanel.setMinimumSize(mainMiddleTopPanel.getPreferredSize());
        mainMiddleTopPanel.setMaximumSize(mainMiddleTopPanel.getPreferredSize());
        mainMiddleTopPanel.setAlignmentX(Component.CENTER_ALIGNMENT);

        friendsText = new JLabel("Friends",SwingConstants.CENTER);
        friendsText.setFont(new Font("Arial", Font.BOLD, (int) (getPreferredSize().width / 40f)));
        friendsText.setOpaque(true);
        friendsText.setPreferredSize(new Dimension(mainMiddlePanel.getPreferredSize().width, (int) (1.5*friendsText.getFontMetrics(friendsText.getFont()).getHeight())));
        friendsText.setMinimumSize(friendsText.getPreferredSize());
        friendsText.setMaximumSize(friendsText.getPreferredSize());
        friendsText.setBackground(AppThemeColors.panelColor);
        friendsText.setForeground(AppThemeColors.foregroundColor);
        friendsText.setAlignmentX(Component.CENTER_ALIGNMENT);
        friendsText.setBorder(new LineBorder(new Color(70, 70, 70),getHeight()/663));

        friendsScrollPane.setPreferredSize(new Dimension(mainMiddlePanel.getPreferredSize().width, mainMiddlePanel.getPreferredSize().height-friendsText.getPreferredSize().height));
        friendsScrollPane.setMinimumSize(friendsScrollPane.getPreferredSize());
        friendsScrollPane.setMaximumSize(friendsScrollPane.getPreferredSize());
        friendsScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        friendsScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        friendsScrollPane.getVerticalScrollBar().setPreferredSize(new Dimension(0, 0));
        friendsScrollPane.getVerticalScrollBar().setUnitIncrement((int) (getHeight()/110.5));
        friendsScrollPane.getViewport().setBackground(AppThemeColors.panelColor);
        friendsScrollPane.setBorder(new LineBorder(new Color(70, 70, 70),getHeight()/663));

        friendsPanel.setLayout(new BoxLayout(friendsPanel, BoxLayout.Y_AXIS));
        friendsPanel.setOpaque(false);

        try {
            FirebaseManager.readDBlistenToClientChats();
        }catch (Exception _){
        }

        updateFriends();
        updateRequestsPanel();

        /*--------------------Left panel--------------------*/

        mainLeftPanel = new JPanel();
        mainLeftPanel.setOpaque(false);
        mainLeftPanel.setBackground(Color.WHITE);
        mainLeftPanel.setPreferredSize(new Dimension(getPreferredSize().width / 5, getPreferredSize().height));
        mainLeftPanel.setMinimumSize(mainLeftPanel.getPreferredSize());
        mainLeftPanel.setMaximumSize(mainLeftPanel.getPreferredSize());
        mainLeftPanel.setLayout(new BorderLayout());

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
        leftBottomPanel.setBorder(new LineBorder(new Color(70, 70, 70),getHeight()/663));

        sendRequestText = new JLabel("Send Request");
        sendRequestText.setFont(new Font("Arial", Font.BOLD, (int) (getPreferredSize().width / 50f)));
        sendRequestText.setOpaque(true);
        sendRequestText.setBackground(AppThemeColors.panelColor);
        sendRequestText.setForeground(AppThemeColors.foregroundColor);
        sendRequestText.setAlignmentX(Component.CENTER_ALIGNMENT);

        addPanel = new JPanel();
        addPanel.setLayout(new BoxLayout(addPanel, BoxLayout.Y_AXIS));
        addPanel.setPreferredSize(new Dimension(mainMiddlePanel.getPreferredSize().width, mainMiddlePanel.getPreferredSize().height / 4));
        addPanel.setMinimumSize(addPanel.getPreferredSize());
        addPanel.setMaximumSize(addPanel.getPreferredSize());
        addPanel.setBackground(AppThemeColors.panelColor);
        addPanel.setAlignmentY(Component.CENTER_ALIGNMENT);

        friendRequestMailText = new JTextField();
        friendRequestMailText.setUI(new BasicTextFieldUI());
        friendRequestMailText.setFont(new Font("Arial", Font.BOLD, (int) (getPreferredSize().width / 80f)));
        friendRequestMailText.setPreferredSize(new Dimension((int) (addPanel.getPreferredSize().width * 0.9), (int) (addPanel.getPreferredSize().height * 0.15)));
        friendRequestMailText.setMinimumSize(friendRequestMailText.getPreferredSize());
        friendRequestMailText.setMaximumSize(friendRequestMailText.getPreferredSize());
        friendRequestMailText.setForeground(AppThemeColors.foregroundColor);
        friendRequestMailText.setBackground(AppThemeColors.SECONDARY);
        friendRequestMailText.setBorder(null);
        friendRequestMailText.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    e.consume(); // Prevents a new line from being created
                    clickToSendRequestButton.doClick(); // Simulates a button click
                }
            }
        });

        clickToSendRequestButton = new JButton("ADD");
        clickToSendRequestButton.setUI(new BasicButtonUI() {
            @Override
            public void paint(Graphics g, JComponent c) {
                // Måla bakgrunden med rundade hörn
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(new Color(70, 208, 71)); // Grön färg
                g2.fillRoundRect(0, 0, c.getWidth(), c.getHeight(), instance.getHeight()/40, instance.getHeight()/40); // Rundade hörn

                // Måla texten (den kommer att målas av Swing, så vi ser till att inte skriva över den)
                super.paint(g, c);

                g2.dispose(); // Frigör Graphics2D
            }
            @Override
            protected void paintButtonPressed(Graphics g, AbstractButton b) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(new Color(64, 136, 65)); // Pressed button color
                g2.fillRoundRect(0, 0, b.getWidth(), b.getHeight(), instance.getHeight()/40, instance.getHeight()/40); // Rounded corners
                g2.dispose();
            }
        });
        clickToSendRequestButton.setFont(new Font("Arial", Font.BOLD, (int) (getPreferredSize().height/55f)));
        //addbutton.setBorder(new LineBorder(new Color(70, 208, 71),3,true));
        //addbutton.setBackground(new Color(70, 208, 71));
        clickToSendRequestButton.setForeground(AppThemeColors.foregroundColor);
        clickToSendRequestButton.setPreferredSize(new Dimension(friendsScrollPane.getPreferredSize().width / 3, (int) (getPreferredSize().height/16.57500)));
        clickToSendRequestButton.setMinimumSize(clickToSendRequestButton.getPreferredSize());
        clickToSendRequestButton.setMaximumSize(clickToSendRequestButton.getPreferredSize());
        clickToSendRequestButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        clickToSendRequestButton.setFocusable(false);
        clickToSendRequestButton.setFocusPainted(false);
        clickToSendRequestButton.setContentAreaFilled(false);
        clickToSendRequestButton.setBorderPainted(false);
        clickToSendRequestButton.addActionListener(_->{
            String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
            Pattern pattern = Pattern.compile(emailRegex);
            Matcher matcher = pattern.matcher(friendRequestMailText.getText());
            if(matcher.matches()){
                try {
                    FirebaseManager.writeDBsendFriendRequest(friendRequestMailText.getText());
                } catch (ExecutionException | InterruptedException ex) {
                    throw new RuntimeException(ex);
                }
                friendRequestMailText.setForeground(Color.GREEN);
                friendRequestMailText.setText("Friend request sent");
                friendRequestMailText.setEditable(false);
                new Timer(1000, _ -> {
                    friendRequestMailText.setText("");
                    friendRequestMailText.setForeground(AppThemeColors.foregroundColor);
                    friendRequestMailText.setEditable(true);
                }) {
                    {
                        setRepeats(false);
                    }
                }.start();

            }else{
                friendRequestMailText.setForeground(Color.RED);
                friendRequestMailText.setText("Invalid email adress");
                friendRequestMailText.setEditable(false);
                new Timer(1000, _ -> {
                    friendRequestMailText.setText("");
                    friendRequestMailText.setForeground(AppThemeColors.foregroundColor);
                    friendRequestMailText.setEditable(true);
                }) {
                    {
                        setRepeats(false);
                    }
                }.start();
            }
        });

        requestScrollPane.setOpaque(true);
        requestScrollPane.setPreferredSize(new Dimension(mainMiddlePanel.getPreferredSize().width, (int)(mainMiddlePanel.getPreferredSize().height/1.10-leftBottomPanel.getPreferredSize().height)));
        requestScrollPane.setMinimumSize(requestScrollPane.getPreferredSize());
        requestScrollPane.setMaximumSize(requestScrollPane.getPreferredSize());
        requestScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        requestScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        requestScrollPane.getVerticalScrollBar().setPreferredSize(new Dimension(0, 0));
        requestScrollPane.getVerticalScrollBar().setUnitIncrement((int) (getHeight()/110.5));
        requestScrollPane.getViewport().setBackground(AppThemeColors.panelColor);
        requestScrollPane.setBorder(new LineBorder(new Color(70, 70, 70),getHeight()/663));

        requestsPanel.setLayout(new BoxLayout(requestsPanel, BoxLayout.Y_AXIS));
        requestsPanel.setOpaque(false);

        requestsText = new JLabel("Requests",SwingConstants.CENTER);
        requestsText.setFont(new Font("Arial", Font.BOLD, (int) (getPreferredSize().width / 40f)));
        requestsText.setOpaque(true);
        requestsText.setPreferredSize(new Dimension(leftPanelThatHoldsEverything.getPreferredSize().width, (int) (1.5* requestsText.getFontMetrics(requestsText.getFont()).getHeight())));
        requestsText.setMinimumSize(requestsText.getPreferredSize());
        requestsText.setMaximumSize(requestsText.getPreferredSize());
        requestsText.setBackground(AppThemeColors.panelColor);
        requestsText.setForeground(AppThemeColors.foregroundColor);
        requestsText.setAlignmentX(Component.CENTER_ALIGNMENT);
        requestsText.setBorder(new LineBorder(new Color(70, 70, 70),getHeight()/663));

        leftTopPanel = new JPanel();
        leftTopPanel.setOpaque(true);
        leftTopPanel.setLayout(new BoxLayout(leftTopPanel, BoxLayout.Y_AXIS));
        leftTopPanel.setPreferredSize(new Dimension(requestScrollPane.getPreferredSize().width, requestsText.getPreferredSize().height));
        leftTopPanel.setMinimumSize(leftTopPanel.getPreferredSize());
        leftTopPanel.setMaximumSize(leftTopPanel.getPreferredSize());
        leftTopPanel.setBackground(AppThemeColors.panelColor);

        this.add(Box.createHorizontalGlue());
        this.add(mainPanel);
        this.add(Box.createHorizontalGlue());
        this.revalidate();
        this.repaint();
        this.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                super.componentResized(e);
                SwingUtilities.invokeLater(()->{
                    mainPanel.setPreferredSize(new Dimension((int) (getPreferredSize().width*0.98), (int) (getPreferredSize().height*0.98)));
                    mainPanel.setMinimumSize(mainPanel.getPreferredSize());
                    mainPanel.setMaximumSize(mainPanel.getPreferredSize());

                    mainLeftPanel.setPreferredSize(new Dimension(mainPanel.getPreferredSize().width / 5, mainPanel.getPreferredSize().height));
                    mainLeftPanel.setMinimumSize(mainLeftPanel.getPreferredSize());
                    mainLeftPanel.setMaximumSize(mainLeftPanel.getPreferredSize());

                    mainMiddlePanel.setPreferredSize(new Dimension(mainPanel.getPreferredSize().width / 5, mainPanel.getPreferredSize().height));
                    mainMiddlePanel.setMinimumSize(mainMiddlePanel.getPreferredSize());
                    mainMiddlePanel.setMaximumSize(mainMiddlePanel.getPreferredSize());

                    mainRightPanel.setPreferredSize(new Dimension(mainPanel.getPreferredSize().width / 2, mainPanel.getPreferredSize().height));
                    mainRightPanel.setMinimumSize(mainRightPanel.getPreferredSize());
                    mainRightPanel.setMaximumSize(mainRightPanel.getPreferredSize());

                    belowPanel.setPreferredSize(new Dimension(mainRightPanel.getPreferredSize().width, mainPanel.getPreferredSize().height/ 8));
                    belowPanel.setMinimumSize(belowPanel.getPreferredSize());
                    belowPanel.setMaximumSize(belowPanel.getPreferredSize());
                    belowPanel.setBorder(new LineBorder(new Color(70, 70, 70),getHeight()/663));

                    messagesScrollPane.setPreferredSize(new Dimension(mainRightPanel.getPreferredSize().width, mainPanel.getPreferredSize().height - mainPanel.getPreferredSize().height/ 8));
                    messagesScrollPane.setMinimumSize(messagesScrollPane.getPreferredSize());
                    messagesScrollPane.setMaximumSize(messagesScrollPane.getPreferredSize());
                    messagesScrollPane.getVerticalScrollBar().setUnitIncrement((int) (getHeight()/110.5));
                    messagesScrollPane.setBorder(new LineBorder(new Color(70, 70, 70),getHeight()/663));

                    //Rescale message box depending on content
                    if(messengerTextBox.getText().isEmpty()){
                        messengerTextBox.setFont(new Font("Arial", Font.PLAIN, (int) (getWidth() / 65f)));
                        messengerTextBox.setPreferredSize(new Dimension((int) (belowPanel.getPreferredSize().width / 1.2),
                                (int) (messengerTextBox.getFontMetrics(messengerTextBox.getFont()).getHeight()*1.3)));
                        messengerTextBox.setMinimumSize(messengerTextBox.getPreferredSize());
                        messengerTextBox.setMaximumSize(messengerTextBox.getPreferredSize());
                    }
                    else{
                        messengerTextBox.setFont(new Font("Arial", Font.PLAIN, (int) (getWidth() / 65f)));

                        messengerTextBox.setPreferredSize(new Dimension((int) (belowPanel.getPreferredSize().width / 1.2),
                                0));

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

                        if (totalHeight < belowPanel.getPreferredSize().height) {
                            messengerTextBox.setPreferredSize(new Dimension(messengerTextBox.getPreferredSize().width, totalHeight));
                            messengerTextBox.setMinimumSize(messengerTextBox.getPreferredSize());
                            messengerTextBox.setMaximumSize(messengerTextBox.getPreferredSize());
                        }
                        messengerTextBox.setAlignmentY(JComponent.CENTER_ALIGNMENT);
                    }

                    belowRightPanel.setPreferredSize(new Dimension(belowPanel.getPreferredSize().width / 10, (int) (belowPanel.getPreferredSize().height / 1.2)));
                    belowRightPanel.setMinimumSize(belowRightPanel.getPreferredSize());
                    belowRightPanel.setMaximumSize(belowRightPanel.getPreferredSize());

                    clickToSendButton.setFont(new Font("Ariel", Font.BOLD, (int) (getPreferredSize().width / 50f)));
                    clickToSendButton.setPreferredSize(new Dimension((int) (belowRightPanel.getPreferredSize().width / 1.7), (int) (belowRightPanel.getPreferredSize().width / 1.7)));
                    clickToSendButton.setMinimumSize(clickToSendButton.getPreferredSize());
                    clickToSendButton.setMaximumSize(clickToSendButton.getPreferredSize());
                    clickToSendButton.repaint();

                    friendsText.setFont(new Font("Arial", Font.BOLD, (int) (getWidth() / 40f)));
                    friendsText.setPreferredSize(new Dimension(mainMiddlePanel.getWidth(), (int) (1.5*friendsText.getFontMetrics(friendsText.getFont()).getHeight())));
                    friendsText.setMinimumSize(friendsText.getPreferredSize());
                    friendsText.setMaximumSize(friendsText.getPreferredSize());
                    friendsText.setBorder(new LineBorder(new Color(70, 70, 70),getHeight()/663));
                    System.out.println(friendsText.getPreferredSize());

                    friendsScrollPane.setPreferredSize(new Dimension(mainMiddlePanel.getWidth(), mainMiddlePanel.getHeight()-friendsText.getHeight()));
                    friendsScrollPane.setMinimumSize(friendsScrollPane.getPreferredSize());
                    friendsScrollPane.setMaximumSize(friendsScrollPane.getPreferredSize());
                    friendsScrollPane.getVerticalScrollBar().setUnitIncrement((int) (getHeight()/110.5));
                    friendsScrollPane.setBorder(new LineBorder(new Color(70, 70, 70),getHeight()/663));

                    mainMiddleTopPanel.setPreferredSize(new Dimension((int)(mainMiddlePanel.getWidth()), friendsText.getHeight()));
                    mainMiddleTopPanel.setMinimumSize(mainMiddleTopPanel.getPreferredSize());
                    mainMiddleTopPanel.setMaximumSize(mainMiddleTopPanel.getPreferredSize());

                    leftPanelThatHoldsEverything.setPreferredSize(new Dimension(mainLeftPanel.getWidth(), mainLeftPanel.getHeight()));
                    leftPanelThatHoldsEverything.setMinimumSize(leftPanelThatHoldsEverything.getPreferredSize());
                    leftPanelThatHoldsEverything.setMaximumSize(leftPanelThatHoldsEverything.getPreferredSize());

                    leftBottomPanel.setPreferredSize(new Dimension(mainLeftPanel.getWidth(), mainLeftPanel.getHeight()/5));
                    leftBottomPanel.setMinimumSize(leftBottomPanel.getPreferredSize());
                    leftBottomPanel.setMaximumSize(leftBottomPanel.getPreferredSize());
                    leftBottomPanel.setBorder(new LineBorder(new Color(70, 70, 70),getHeight()/663));

                    requestScrollPane.setPreferredSize(new Dimension(mainMiddlePanel.getWidth(), (int)(mainMiddlePanel.getHeight()/1.10-leftBottomPanel.getHeight())));
                    requestScrollPane.setMinimumSize(requestScrollPane.getPreferredSize());
                    requestScrollPane.setMaximumSize(requestScrollPane.getPreferredSize());
                    requestScrollPane.getVerticalScrollBar().setUnitIncrement((int) (getHeight()/110.5));
                    requestScrollPane.setBorder(new LineBorder(new Color(70, 70, 70),getHeight()/663));

                    requestsText.setFont(new Font("Arial", Font.BOLD, (int) (getWidth() / 40f)));
                    requestsText.setPreferredSize(new Dimension(leftPanelThatHoldsEverything.getWidth(), (int) (1.5* requestsText.getFontMetrics(requestsText.getFont()).getHeight())));
                    requestsText.setMinimumSize(requestsText.getPreferredSize());
                    requestsText.setMaximumSize(requestsText.getPreferredSize());
                    requestsText.setBorder(new LineBorder(new Color(70, 70, 70),getHeight()/663));

                    leftTopPanel.setPreferredSize(new Dimension(requestScrollPane.getWidth(), requestsText.getHeight()));
                    leftTopPanel.setMinimumSize(leftTopPanel.getPreferredSize());
                    leftTopPanel.setMaximumSize(leftTopPanel.getPreferredSize());

                    sendRequestText.setFont(new Font("Arial", Font.BOLD, (int) (getWidth() / 50f)));

                    addPanel.setPreferredSize(new Dimension(mainMiddlePanel.getWidth(), mainMiddlePanel.getHeight() / 4));
                    addPanel.setMinimumSize(addPanel.getPreferredSize());
                    addPanel.setMaximumSize(addPanel.getPreferredSize());

                    friendRequestMailText.setFont(new Font("Arial", Font.BOLD, (int) (getWidth() / 80f)));
                    friendRequestMailText.setPreferredSize(new Dimension((int) (addPanel.getPreferredSize().width * 0.9), (int) (addPanel.getPreferredSize().height * 0.15)));
                    friendRequestMailText.setMinimumSize(friendRequestMailText.getPreferredSize());
                    friendRequestMailText.setMaximumSize(friendRequestMailText.getPreferredSize());

                    clickToSendRequestButton.setFont(new Font("Arial", Font.BOLD, (int) (getHeight()/55f)));
                    clickToSendRequestButton.setPreferredSize(new Dimension(friendsScrollPane.getWidth() / 3, (int) (getHeight()/16.57500)));
                    clickToSendRequestButton.setMinimumSize(clickToSendRequestButton.getPreferredSize());
                    clickToSendRequestButton.setMaximumSize(clickToSendRequestButton.getPreferredSize());
                    clickToSendRequestButton.repaint();

                    rescaleFriends();
                    rescaleChats();
                    rescaleRequests();

                });
            }
        });

        /*--------------------ALL PANELS ADDS--------------------*/

        /*--------------------(Main panel)--------------------*/
        mainPanel.add(Box.createHorizontalGlue());
        mainPanel.add(mainLeftPanel);
        mainPanel.add(Box.createHorizontalGlue());
        mainPanel.add(mainMiddlePanel);
        mainPanel.add(Box.createHorizontalGlue());
        mainPanel.add(mainRightPanel);
        mainPanel.add(Box.createHorizontalGlue());

        /*--------------------(Left panel)--------------------*/

        mainLeftPanel.add(leftPanelThatHoldsEverything);

        leftPanelThatHoldsEverything.add(leftTopPanel);
        leftPanelThatHoldsEverything.add(requestScrollPane);
        leftPanelThatHoldsEverything.add(Box.createVerticalGlue());
        leftPanelThatHoldsEverything.add(leftBottomPanel);

        leftTopPanel.add(requestsText);

        requestScrollPane.add(requestsPanel);
        requestScrollPane.setViewportView(requestsPanel);

        leftBottomPanel.add(Box.createVerticalGlue());
        leftBottomPanel.add(sendRequestText);
        leftBottomPanel.add(Box.createVerticalGlue());
        leftBottomPanel.add(Box.createVerticalGlue());
        leftBottomPanel.add(friendRequestMailText);
        leftBottomPanel.add(Box.createVerticalGlue());
        leftBottomPanel.add(clickToSendRequestButton);
        leftBottomPanel.add(Box.createVerticalGlue());
        leftBottomPanel.add(Box.createVerticalGlue());

        belowPanel.add(Box.createHorizontalGlue());
        belowPanel.add(messengerTextBox);
        belowPanel.add(Box.createHorizontalGlue());
        belowPanel.add(belowRightPanel);
        belowPanel.add(Box.createHorizontalGlue());
        belowRightPanel.add(Box.createHorizontalGlue());
        belowRightPanel.add(clickToSendButton);
        belowRightPanel.add(Box.createHorizontalGlue());

        /*--------------------(Middle panel)--------------------*/

        mainMiddlePanel.add(mainMiddleTopPanel);
        mainMiddlePanel.add(friendsScrollPane);

        mainMiddleTopPanel.add(friendsText);

        friendsScrollPane.setViewportView(friendsPanel);

        /*--------------------(Right panel)--------------------*/

        mainRightPanel.add(messagesScrollPane);
        mainRightPanel.add(belowPanel);
        messagesScrollPane.setViewportView(messageStorage);
    }

    private static void rescaleRequests(){
        int index = 0;
        for(Component comp : requestsPanel.getComponents()){
            if(comp.getName()!=null){
                if(comp.getName().equals("friendRequestPanel")){
                    JPanel friendRequestPanel = (JPanel) comp;
                    FriendRequest friendRequest = FriendRequestList.getFriendRequestList().get(index);

                    Image scaledFriendProfilePicture = friendRequest.getProfilePicture().getImage().getScaledInstance(instance.getPreferredSize().width / 25, instance.getPreferredSize().width / 25, Image.SCALE_SMOOTH);
                    ImageIcon scaledFriendProfilePictureIcon = new ImageIcon(scaledFriendProfilePicture);

                    friendRequest.getImageAvatarFriendRequest().setPreferredSize(new Dimension(instance.getPreferredSize().width / 25, instance.getPreferredSize().width / 25));
                    friendRequest.getImageAvatarFriendRequest().setMinimumSize(friendRequest.getImageAvatarFriendRequest().getPreferredSize());
                    friendRequest.getImageAvatarFriendRequest().setMaximumSize(friendRequest.getImageAvatarFriendRequest().getPreferredSize());
                    friendRequest.getImageAvatarFriendRequest().setBorderSize(instance.getPreferredSize().height/221);
                    friendRequest.getImageAvatarFriendRequest().setBorderSpace((int) (instance.getPreferredSize().height/331.5));
                    friendRequest.getImageAvatarFriendRequest().setImage(scaledFriendProfilePictureIcon);
                    friendRequest.getImageAvatarFriendRequest().setAlignmentY(Component.CENTER_ALIGNMENT);

                    friendRequestPanel.setPreferredSize(new Dimension(friendsScrollPane.getPreferredSize().width,
                            (int) (friendRequest.getImageAvatarFriendRequest().getPreferredSize().height * 1.1)));

                    for(Component comp1 : friendRequestPanel.getComponents()){
                        if(comp1.getName()!=null){
                            if(comp1.getName().equals("friendAvatarPanel")){
                                JPanel friendAvatarPanel = (JPanel) comp1;
                                friendAvatarPanel.setPreferredSize(new Dimension((int) (friendRequest.getImageAvatarFriendRequest().getPreferredSize().width * 1.3), friendRequest.getImageAvatarFriendRequest().getPreferredSize().height));
                                friendAvatarPanel.setMaximumSize(friendAvatarPanel.getPreferredSize());
                            }
                            if(comp1.getName().equals("friendNameLabel")){
                                JLabel friendNameLabel = (JLabel) comp1;
                                friendNameLabel.setFont(CustomFont.getFont().deriveFont(instance.getPreferredSize().width / 65f));
                                friendNameLabel.setMaximumSize(new Dimension(friendsScrollPane.getPreferredSize().width - friendRequest.getImageAvatarFriendRequest().getPreferredSize().width,
                                        friendRequestPanel.getPreferredSize().height));
                            }
                            if(comp1.getName().equals("acceptFriendRequestButton")){
                                JButton acceptFriendRequestButton = (JButton) comp1;

                                acceptFriendRequestButton.setFont(new Font("SansSerif", Font.BOLD, (int) (instance.getHeight()/33.15)));
                                acceptFriendRequestButton.setPreferredSize(new Dimension((int) (instance.getWidth()/35.4333333), (int) (instance.getHeight()/26.52)));
                                acceptFriendRequestButton.setMinimumSize(acceptFriendRequestButton.getPreferredSize());
                                acceptFriendRequestButton.setMaximumSize(acceptFriendRequestButton.getPreferredSize());
                            }
                            if(comp1.getName().equals("denyFriendRequestButton")){
                                JButton acceptFriendRequestButton = (JButton) comp1;

                                acceptFriendRequestButton.setFont(new Font("Arial", Font.BOLD, (int) (instance.getHeight()/33.15)));
                                acceptFriendRequestButton.setPreferredSize(new Dimension((int) (instance.getWidth()/35.4333333), (int) (instance.getHeight()/26.52)));
                                acceptFriendRequestButton.setMinimumSize(acceptFriendRequestButton.getPreferredSize());
                                acceptFriendRequestButton.setMaximumSize(acceptFriendRequestButton.getPreferredSize());
                            }
                        }
                    }
                }
            }
            index++;
        }
    }

    private static void rescaleChats(){
        SwingUtilities.invokeLater(()->{
            for(Friend friend : FriendsList.getFriendArrayList()){
                int index = 0;
                for(Component comp : friend.getMessageStorage().getComponents()){

                    if(comp.getName()!=null){
                        if(comp.getName().equals("userMessagePanel")||comp.getName().equals("friendMessagePanel")){
                            JPanel friendMessagePanel = (JPanel) comp;

                            friendMessagePanel.setPreferredSize(new Dimension((int) (mainRightPanel.getWidth() / 1.05), instance.getWidth()/25));
                            friendMessagePanel.setMinimumSize(friendMessagePanel.getPreferredSize());
                            friendMessagePanel.setMaximumSize(friendMessagePanel.getPreferredSize());

                            JPanel friendProfilePicturePanel = new JPanel();
                            for(Component comp1 : friendMessagePanel.getComponents()){
                                if(comp1.getName()!=null){
                                    if(comp1.getName().equals("friendProfilePicturePanel")||comp1.getName().equals("userProfilePicturePanel")){
                                        friendProfilePicturePanel = (JPanel) comp1;
                                        friendProfilePicturePanel.setPreferredSize(new Dimension(friendMessagePanel.getPreferredSize().width / 8, instance.getWidth() / 25));
                                        friendProfilePicturePanel.setMinimumSize(friendProfilePicturePanel.getPreferredSize());
                                        friendProfilePicturePanel.setMaximumSize(friendProfilePicturePanel.getPreferredSize());

                                        for(Component comp2 : friendProfilePicturePanel.getComponents()){
                                            if(comp2.getName()!=null){
                                                if(comp2.getName().equals("friendAvatar")){
                                                    ImageAvatar friendAvatar = (ImageAvatar) comp2;
                                                    friendAvatar.setPreferredSize(new Dimension( instance.getWidth() / 25, instance.getWidth() / 25));
                                                    Image scaledProfilePicture = friend.getProfilePicture().getImage().getScaledInstance(instance.getWidth() / 25, instance.getWidth() / 25, Image.SCALE_SMOOTH);
                                                    scaledProfilePictureIcon = new ImageIcon(scaledProfilePicture);
                                                    friendAvatar.setImage(scaledProfilePictureIcon);
                                                    friendAvatar.setBorderSize(instance.getPreferredSize().height/221);
                                                    friendAvatar.setBorderSpace((int) (instance.getPreferredSize().height/331.5));
                                                }
                                                if(comp2.getName().equals("userAvatar")){
                                                    ImageAvatar userAvatar = (ImageAvatar) comp2;
                                                    userAvatar.setPreferredSize(new Dimension( instance.getWidth() / 25, instance.getWidth() / 25));
                                                    Image scaledProfilePicture = profilePictureIcon.getImage().getScaledInstance(instance.getWidth() / 25, instance.getWidth() / 25, Image.SCALE_SMOOTH);
                                                    scaledProfilePictureIcon = new ImageIcon(scaledProfilePicture);
                                                    userAvatar.setImage(scaledProfilePictureIcon);
                                                    userAvatar.setBorderSize(instance.getPreferredSize().height/221);
                                                    userAvatar.setBorderSpace((int) (instance.getPreferredSize().height/331.5));
                                                }

                                            }
                                        }

                                    }
                                    if(comp1.getName().equals("userTextMessage") || comp1.getName().equals("friendTextMessage")){

                                        JTextArea friendTextMessage = (JTextArea) comp1;
                                        friendTextMessage.setFont(new Font("Arial", Font.PLAIN, (int)(instance.getPreferredSize().width/65f)));

                                        FontMetrics metrics = friendTextMessage.getFontMetrics(friendTextMessage.getFont());
                                        int lineHeight = metrics.getHeight();

                                        int areaWidth = friendMessagePanel.getPreferredSize().width - friendProfilePicturePanel.getPreferredSize().width;

                                        int charsPerLine = areaWidth / metrics.charWidth('m');

                                        int totalChars = friendTextMessage.getText().length();

                                        int totalLinesNeeded = (int) Math.ceil((double) totalChars / charsPerLine);

                                        int totalHeight = totalLinesNeeded * lineHeight;
                                        friendTextMessage.setSize(new Dimension(friendMessagePanel.getPreferredSize().width - friendProfilePicturePanel.getPreferredSize().width, totalHeight));
                                        //System.out.println(new Dimension(friendMessagePanel.getPreferredSize().width - friendProfilePicturePanel.getPreferredSize().width, totalHeight));
                                        friendTextMessage.setMinimumSize(friendTextMessage.getPreferredSize());
                                        friendTextMessage.setMaximumSize(friendTextMessage.getPreferredSize());
                                        friendTextMessage.setForeground(Color.WHITE);
                                        friendTextMessage.setBackground(Color.green);

                                        //System.out.println(friendTextMessage.getSize().height +" "+ friendMessagePanel.getPreferredSize().height);

                                        if (friendTextMessage.getSize().height > friendMessagePanel.getPreferredSize().height) {
                                            System.out.println("Max size set");
                                            friendMessagePanel.setPreferredSize(new Dimension((int) (mainRightPanel.getPreferredSize().width / 1.05), friendTextMessage.getPreferredSize().height));
                                            friendMessagePanel.setMinimumSize(friendMessagePanel.getPreferredSize());
                                            friendMessagePanel.setMaximumSize(friendMessagePanel.getPreferredSize());
                                        }
                                    }
                                }
                            }


                        }
                    }else{
                        //Removes the rigid area and replaces it with a new with new size.
                        friend.getMessageStorage().remove(index);
                        friend.getMessageStorage().add(Box.createRigidArea(new Dimension((int) (mainRightPanel.getWidth() / 1.05), instance.getHeight()/40)),index);
                    }
                    index++;
                }
            }
        });
    }

    private static void rescaleFriends(){
        int index = 0;
        for(Component comp : friendsPanel.getComponents()){
            if(comp.getName()!=null){
                if(comp.getName().equals("friendPanel")){
                    Friend friend = FriendsList.getFriendArrayList().get(index);
                    JPanel friendPanel = (JPanel) comp;
                    Image scaledFriendProfilePicture = friend.getProfilePicture().getImage().getScaledInstance(instance.getPreferredSize().width / 25, instance.getPreferredSize().width / 25, Image.SCALE_SMOOTH);
                    ImageIcon scaledFriendProfilePictureIcon = new ImageIcon(scaledFriendProfilePicture);

                    friend.getImageAvatarSocial().setPreferredSize(new Dimension(instance.getPreferredSize().width / 25, instance.getPreferredSize().width / 25));
                    friend.getImageAvatarSocial().setMinimumSize(friend.getImageAvatarSocial().getPreferredSize());
                    friend.getImageAvatarSocial().setMaximumSize(friend.getImageAvatarSocial().getPreferredSize());
                    friend.getImageAvatarSocial().setImage(scaledFriendProfilePictureIcon);
                    friend.getImageAvatarSocial().setAlignmentY(Component.CENTER_ALIGNMENT);
                    friend.getImageAvatarSocial().setBorderSize(instance.getPreferredSize().height/221);
                    friend.getImageAvatarSocial().setBorderSpace((int) (instance.getPreferredSize().height/331.5));
                    friendPanel.setPreferredSize(new Dimension(friendsScrollPane.getPreferredSize().width, (int) (friend.getImageAvatarSocial().getPreferredSize().height * 1.3)));

                    for(Component comp1 : friendPanel.getComponents()){
                        if(comp1.getName()!=null){
                            if(comp1.getName().equals("friendNameLabel")){
                                JLabel friendNameLabel = (JLabel) comp1;
                                friendNameLabel.setFont(CustomFont.getFont().deriveFont(instance.getPreferredSize().width / 65f));
                                friendNameLabel.setMaximumSize(new Dimension(friendsScrollPane.getPreferredSize().width - friend.getImageAvatarSocial().getPreferredSize().width, friendPanel.getPreferredSize().height));
                            }
                            if(comp1.getName().equals("friendAvatarPanel")){
                                JPanel friendAvatarPanel = (JPanel) comp1;
                                friendAvatarPanel.setPreferredSize(new Dimension((int) (friend.getImageAvatarSocial().getPreferredSize().width * 1.3), friend.getImageAvatarSocial().getPreferredSize().height));
                                friendAvatarPanel.setMaximumSize(friendAvatarPanel.getPreferredSize());

                            }
                        }
                    }
                }

            }
            index++;
        }
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
                        setBackground(AppThemeColors.panelColor);
                        setPreferredSize(null);
                    }
                });


                JPanel friendPanel = new JPanel();
                friendPanel.setName("friendPanel");
                friendPanel.setOpaque(false);
                friendPanel.setBackground(AppThemeColors.SECONDARY);
                friendPanel.setPreferredSize(new Dimension(friendsScrollPane.getPreferredSize().width, (int) (friend.getImageAvatarSocial().getPreferredSize().height * 1.3)));
                friendPanel.setLayout(new BoxLayout(friendPanel, BoxLayout.X_AXIS));
                friendPanel.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseReleased(MouseEvent e) {
                        super.mouseReleased(e);
                        if(canSelectChat){
                            selectedFriend = friend;
                            updateChat();
                            System.out.println(friend.getFriendName());
                            for (Friend friend1 : FriendsList.getFriendArrayList()){
                                if(!friend1.getFriendName().equals(friend.getFriendName())){
                                    for(Component comp : friendsPanel.getComponents()){
                                        JPanel friendPanel = (JPanel) comp;
                                        friendPanel.setOpaque(false);
                                        friendsPanel.repaint();
                                    }
                                }
                            }
                            friendPanel.setOpaque(true);
                        }
                    }
                });

                JLabel friendNameLabel = new JLabel(FriendsList.getFriendArrayList().get(FriendsList.getFriendArrayList().indexOf(friend)).getFriendName());
                friendNameLabel.setName("friendNameLabel");
                friendNameLabel.setFont(CustomFont.getFont().deriveFont(instance.getPreferredSize().width / 65f));
                friendNameLabel.setMaximumSize(new Dimension(friendsScrollPane.getPreferredSize().width - friend.getImageAvatarSocial().getPreferredSize().width, friendPanel.getPreferredSize().height));
                friendNameLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
                friendNameLabel.setForeground(AppThemeColors.foregroundColor);

                JPanel friendAvatarPanel = new JPanel();
                friendAvatarPanel.setName("friendAvatarPanel");
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
            friendRequest.setProfilePicture(userIcon);
            Image scaledFriendProfilePicture = userIcon.getImage().getScaledInstance(instance.getPreferredSize().width / 25, instance.getPreferredSize().width / 25, Image.SCALE_SMOOTH);
            ImageIcon scaledFriendProfilePictureIcon = new ImageIcon(scaledFriendProfilePicture);

            friendRequest.getImageAvatarFriendRequest().setPreferredSize(new Dimension(instance.getPreferredSize().width / 25, instance.getPreferredSize().width / 25));
            friendRequest.getImageAvatarFriendRequest().setMinimumSize(friendRequest.getImageAvatarFriendRequest().getPreferredSize());
            friendRequest.getImageAvatarFriendRequest().setMaximumSize(friendRequest.getImageAvatarFriendRequest().getPreferredSize());
            friendRequest.getImageAvatarFriendRequest().setBorderSize(instance.getPreferredSize().height/221);
            friendRequest.getImageAvatarFriendRequest().setBorderSpace((int) (instance.getPreferredSize().height/331.5));
            friendRequest.getImageAvatarFriendRequest().setImage(scaledFriendProfilePictureIcon);
            friendRequest.getImageAvatarFriendRequest().setAlignmentY(Component.CENTER_ALIGNMENT);


            JPanel friendRequestPanel = new JPanel();
            friendRequestPanel.setName("friendRequestPanel");
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




            JPanel friendAvatarPanel = new JPanel();
            friendAvatarPanel.setName("friendAvatarPanel");
            friendAvatarPanel.setOpaque(false);
            friendAvatarPanel.setLayout(new BoxLayout(friendAvatarPanel, BoxLayout.X_AXIS));
            friendAvatarPanel.setPreferredSize(new Dimension((int) (friendRequest.getImageAvatarFriendRequest().getPreferredSize().width * 1.3), friendRequest.getImageAvatarFriendRequest().getPreferredSize().height));
            friendAvatarPanel.setMaximumSize(friendAvatarPanel.getPreferredSize());
            friendAvatarPanel.setAlignmentY(Component.CENTER_ALIGNMENT);

            JButton acceptFriendRequestButton = new JButton("✓");
            acceptFriendRequestButton.setUI(new BasicButtonUI() {
                @Override
                public void paint(Graphics g, JComponent c) {
                    // Måla bakgrunden med rundade hörn
                    Graphics2D g2 = (Graphics2D) g.create();
                    g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                    g2.setColor(new Color(70, 208, 71)); // Grön färg
                    g2.fillRoundRect(0, 0, c.getWidth(), c.getHeight(), instance.getHeight()/50, instance.getHeight()/50); // Rundade hörn

                    // Måla texten (den kommer att målas av Swing, så vi ser till att inte skriva över den)
                    super.paint(g, c);

                    g2.dispose(); // Frigör Graphics2D
                }
                @Override
                protected void paintButtonPressed(Graphics g, AbstractButton b) {
                    Graphics2D g2 = (Graphics2D) g.create();
                    g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                    g2.setColor(new Color(64, 136, 65)); // Pressed button color
                    g2.fillRoundRect(0, 0, b.getWidth(), b.getHeight(), instance.getHeight()/50, instance.getHeight()/50); // Rounded corners
                    g2.dispose();
                }
            });
            acceptFriendRequestButton.setName("acceptFriendRequestButton");
            acceptFriendRequestButton.setFont(new Font("SansSerif", Font.BOLD, (int) (instance.getHeight()/33.15)));
            acceptFriendRequestButton.setMargin(new Insets(0, 0, 0, 0));
            acceptFriendRequestButton.setFocusPainted(false);
            acceptFriendRequestButton.setFocusable(false);
            acceptFriendRequestButton.setBorderPainted(false);
            acceptFriendRequestButton.setContentAreaFilled(false);
            acceptFriendRequestButton.setForeground(AppThemeColors.foregroundColor);
            acceptFriendRequestButton.setPreferredSize(new Dimension((int) (instance.getWidth()/35.4333333), (int) (instance.getHeight()/26.52)));
            acceptFriendRequestButton.setAlignmentY(Component.CENTER_ALIGNMENT);
            acceptFriendRequestButton.setMinimumSize(acceptFriendRequestButton.getPreferredSize());
            acceptFriendRequestButton.setMaximumSize(acceptFriendRequestButton.getPreferredSize());
            acceptFriendRequestButton.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    FriendsList.getFriendArrayList().add(new Friend() {
                        {
                            setChat(new ArrayList<>());
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
            denyFriendRequestButton.setUI(new BasicButtonUI() {
                @Override
                public void paint(Graphics g, JComponent c) {
                    // Måla bakgrunden med rundade hörn
                    Graphics2D g2 = (Graphics2D) g.create();
                    g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                    g2.setColor(new Color(201, 31, 31)); // Grön färg
                    g2.fillRoundRect(0, 0, c.getWidth(), c.getHeight(), instance.getHeight()/50, instance.getHeight()/50); // Rundade hörn

                    // Måla texten (den kommer att målas av Swing, så vi ser till att inte skriva över den)
                    super.paint(g, c);

                    g2.dispose(); // Frigör Graphics2D
                }
                @Override
                protected void paintButtonPressed(Graphics g, AbstractButton b) {
                    Graphics2D g2 = (Graphics2D) g.create();
                    g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                    g2.setColor(new Color(178, 27, 27)); // Pressed button color
                    g2.fillRoundRect(0, 0, b.getWidth(), b.getHeight(), instance.getHeight()/50, instance.getHeight()/50); // Rounded corners
                    g2.dispose();
                }
            });
            denyFriendRequestButton.setName("denyFriendRequestButton");
            denyFriendRequestButton.setFont(new Font("Arial", Font.BOLD, (int) (instance.getHeight()/33.15)));
            denyFriendRequestButton.setMargin(new Insets(0, 0, 0, 0));
            denyFriendRequestButton.setFocusPainted(false);
            denyFriendRequestButton.setFocusable(false);
            denyFriendRequestButton.setBorderPainted(false);
            denyFriendRequestButton.setContentAreaFilled(false);
            denyFriendRequestButton.setForeground(AppThemeColors.foregroundColor);
            denyFriendRequestButton.setPreferredSize(new Dimension((int) (instance.getWidth()/35.4333333), (int) (instance.getHeight()/26.52)));
            denyFriendRequestButton.setAlignmentY(Component.CENTER_ALIGNMENT);
            denyFriendRequestButton.setMinimumSize(denyFriendRequestButton.getPreferredSize());
            denyFriendRequestButton.setMaximumSize(denyFriendRequestButton.getPreferredSize());
            denyFriendRequestButton.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                        requestsPanel.remove(friendRequestPanel);
                        FirebaseManager.writeDBdenyFriendRequest(friendRequest.getFriendEmail());

                        instance.repaint();
                        instance.revalidate();

                }
            });




            JLabel friendNameLabel = new JLabel();
            friendNameLabel.setName("friendNameLabel");
            SwingUtilities.invokeLater(()->{
                String limitedText = getLimitedText(friendNameLabel,
                        FriendRequestList.getFriendRequestList().get(FriendRequestList.getFriendRequestList().indexOf(friendRequest)).getFriendName(),
                        (int) (friendRequestPanel.getPreferredSize().width-friendAvatarPanel.getPreferredSize().width-(instance.getPreferredSize().width/35.4333333)*2.1));
                System.out.println( (friendRequestPanel.getPreferredSize().width+" "+friendAvatarPanel.getPreferredSize().width+" "+(instance.getPreferredSize().width/35.4333333)*2.1));
                friendNameLabel.setText(limitedText);
            });
            friendNameLabel.setFont(CustomFont.getFont().deriveFont(instance.getPreferredSize().width / 65f));
            friendNameLabel.setMaximumSize(new Dimension((int) (friendsScrollPane.getPreferredSize().width - friendRequest.getImageAvatarFriendRequest().getPreferredSize().width-denyFriendRequestButton.getPreferredSize().width*2.1),
                    friendRequestPanel.getPreferredSize().height));
            friendNameLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
            friendNameLabel.setForeground(AppThemeColors.foregroundColor);



            friendAvatarPanel.add(Box.createHorizontalGlue());
            friendAvatarPanel.add(FriendRequestList.getFriendRequestList().get(FriendRequestList.getFriendRequestList().indexOf(friendRequest)).getImageAvatarFriendRequest());
            friendAvatarPanel.add(Box.createHorizontalGlue());

            friendRequestPanel.add(friendAvatarPanel);
            friendRequestPanel.add(friendNameLabel);
            friendRequestPanel.add(Box.createHorizontalGlue());
            friendRequestPanel.add(acceptFriendRequestButton);
            friendRequestPanel.add(denyFriendRequestButton);


            requestsPanel.add(friendRequestPanel);

            requestScrollPane.setViewportView(requestsPanel);


        }
    }

    public static String getLimitedText(JLabel label, String text, int maxWidth) {
        FontMetrics metrics = label.getFontMetrics(label.getFont());
        int textWidth = metrics.stringWidth(text);

        if (textWidth <= maxWidth) {
            return text; // Om texten får plats, returnera originaltexten
        }

        // Trunkera texten och lägg till "..."
        String ellipsis = "...";
        int ellipsisWidth = metrics.stringWidth(ellipsis);
        String truncatedText = text;

        while (metrics.stringWidth(truncatedText) + ellipsisWidth > maxWidth && truncatedText.length() > 1) {
            truncatedText = truncatedText.substring(0, truncatedText.length() - 1);
        }

        return truncatedText + ellipsis; // Lägg till "..."
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
                JPanel userMessagePanel = new JPanel();
                userMessagePanel.setName("userMessagePanel");
                userMessagePanel.setAlignmentX(Component.CENTER_ALIGNMENT);
                userMessagePanel.setLayout(new BoxLayout(userMessagePanel, BoxLayout.X_AXIS));
                userMessagePanel.setOpaque(false);
                userMessagePanel.setBackground(Color.PINK);
                userMessagePanel.setPreferredSize(new Dimension((int) (mainRightPanel.getPreferredSize().width / 1.05), instance.getPreferredSize().width/25));
                userMessagePanel.setMinimumSize(userMessagePanel.getPreferredSize());
                userMessagePanel.setMaximumSize(userMessagePanel.getPreferredSize());
                userMessagePanel.setAlignmentX(Component.CENTER_ALIGNMENT);



                JPanel userProfilePicturePanel = new JPanel();
                userProfilePicturePanel.setName("userProfilePicturePanel");
                userProfilePicturePanel.setLayout(new BoxLayout(userProfilePicturePanel, BoxLayout.X_AXIS));
                userProfilePicturePanel.setOpaque(false);
                userProfilePicturePanel.setBackground(AppThemeColors.textFieldColor);
                userProfilePicturePanel.setPreferredSize(new Dimension(userMessagePanel.getPreferredSize().width / 8, instance.getPreferredSize().width / 25));
                userProfilePicturePanel.setMinimumSize(userProfilePicturePanel.getPreferredSize());
                userProfilePicturePanel.setMaximumSize(userProfilePicturePanel.getPreferredSize());
                userProfilePicturePanel.setAlignmentY(Component.TOP_ALIGNMENT);

                profilePictureIcon = FirebaseManager.readDBprofilePicture(UserData.getEmail());
                userAvatar = new ImageAvatar();
                userAvatar.setName("userAvatar");
                userAvatar.setPreferredSize(new Dimension( instance.getPreferredSize().width / 25, instance.getPreferredSize().width / 25));
                Image scaledProfilePicture = profilePictureIcon.getImage().getScaledInstance(instance.getPreferredSize().width / 25, instance.getPreferredSize().width / 25, Image.SCALE_SMOOTH);
                scaledProfilePictureIcon = new ImageIcon(scaledProfilePicture);
                userAvatar.setImage(scaledProfilePictureIcon);
                userAvatar.setBorderSize(instance.getPreferredSize().height/221);
                userAvatar.setBorderSpace((int) (instance.getPreferredSize().height/331.5));

                userProfilePicturePanel.add(Box.createHorizontalGlue());
                userProfilePicturePanel.add(userAvatar);
                userProfilePicturePanel.add(Box.createHorizontalGlue());

                JTextArea userTextMessage = new JTextArea();
                userTextMessage.setName("userTextMessage");
                userTextMessage.setMinimumSize(new Dimension(new Dimension(userMessagePanel.getPreferredSize().width - userProfilePicturePanel.getPreferredSize().width,1)));
                userTextMessage.setFont(new Font("Arial", Font.PLAIN, (int)(instance.getPreferredSize().width/65f)));
                userTextMessage.setOpaque(false);
                userTextMessage.setLineWrap(true);
                userTextMessage.setWrapStyleWord(true);
                userTextMessage.append(message);
                userTextMessage.setForeground(new Color(212, 215, 218));
                //System.out.println(userTextMessage.getText());
                //userTextMessage.setBorder(new LineBorder(AppThemeColors.PRIMARY,1,true));
                userTextMessage.setEditable(false);
                userTextMessage.setAlignmentY(Component.TOP_ALIGNMENT);
                SwingUtilities.invokeLater(() -> {
                    FontMetrics metrics = userTextMessage.getFontMetrics(userTextMessage.getFont());
                    int lineHeight = metrics.getHeight();

                    int areaWidth = userMessagePanel.getPreferredSize().width - userProfilePicturePanel.getPreferredSize().width;

                    int charsPerLine = areaWidth / metrics.charWidth('m');

                    int totalChars = userTextMessage.getText().length();

                    int totalLinesNeeded = (int) Math.ceil((double) totalChars / charsPerLine);

                    int totalHeight = totalLinesNeeded * lineHeight;
                    userTextMessage.setSize(new Dimension(userMessagePanel.getPreferredSize().width - userProfilePicturePanel.getPreferredSize().width, totalHeight));
                    //userTextMessage.setPreferredSize(userTextMessage.getSize());
                    //System.out.println(new Dimension(userMessagePanel.getPreferredSize().width - userProfilePicturePanel.getPreferredSize().width, totalHeight));
                    userTextMessage.setMinimumSize(userTextMessage.getPreferredSize());
                    userTextMessage.setMaximumSize(userTextMessage.getPreferredSize());
                    userTextMessage.setForeground(Color.WHITE);
                    userTextMessage.setBackground(Color.green);
                    //System.out.println(userTextMessage.getSize().height +" "+ userMessagePanel.getPreferredSize().height);
                    if (userTextMessage.getSize().height > userMessagePanel.getPreferredSize().height) {
                        userMessagePanel.setPreferredSize(new Dimension((int) (mainRightPanel.getPreferredSize().width / 1.05), userTextMessage.getPreferredSize().height));
                        userMessagePanel.setMinimumSize(userMessagePanel.getPreferredSize());
                        userMessagePanel.setMaximumSize(userMessagePanel.getPreferredSize());
                    }
                });
                userMessagePanel.add(userProfilePicturePanel);
                userMessagePanel.add(Box.createHorizontalGlue());
                userMessagePanel.add(userTextMessage);

                selectedFriend.getMessageStorage().add(Box.createRigidArea(new Dimension(userMessagePanel.getPreferredSize().width, instance.getPreferredSize().height/40)));
                selectedFriend.getMessageStorage().add(userMessagePanel);
            }else{
                /*--------------------right side of chat--------------------*/
                JPanel friendMessagePanel = new JPanel();
                friendMessagePanel.setName("friendMessagePanel");
                friendMessagePanel.setAlignmentX(Component.CENTER_ALIGNMENT);
                friendMessagePanel.setLayout(new BoxLayout(friendMessagePanel, BoxLayout.X_AXIS));
                friendMessagePanel.setOpaque(false);
                friendMessagePanel.setBackground(Color.PINK);
                friendMessagePanel.setPreferredSize(new Dimension((int) (mainRightPanel.getPreferredSize().width / 1.05), instance.getPreferredSize().width/25));
                friendMessagePanel.setMinimumSize(friendMessagePanel.getPreferredSize());
                friendMessagePanel.setMaximumSize(friendMessagePanel.getPreferredSize());

                JPanel friendProfilePicturePanel = new JPanel();
                friendProfilePicturePanel.setName("friendProfilePicturePanel");
                friendProfilePicturePanel.setLayout(new BoxLayout(friendProfilePicturePanel, BoxLayout.X_AXIS));
                friendProfilePicturePanel.setOpaque(false);
                friendProfilePicturePanel.setBackground(AppThemeColors.textFieldColor);
                friendProfilePicturePanel.setBackground(Color.BLUE);
                friendProfilePicturePanel.setPreferredSize(new Dimension(friendMessagePanel.getPreferredSize().width / 8, instance.getPreferredSize().width / 25));
                friendProfilePicturePanel.setMinimumSize(friendProfilePicturePanel.getPreferredSize());
                friendProfilePicturePanel.setMaximumSize(friendProfilePicturePanel.getPreferredSize());
                friendProfilePicturePanel.setAlignmentY(Component.TOP_ALIGNMENT);

                friendAvatar = new ImageAvatar();
                friendAvatar.setName("friendAvatar");
                friendAvatar.setPreferredSize(new Dimension(instance.getPreferredSize().width / 25, instance.getPreferredSize().width / 25));
                ImageIcon profilePictureIconFriend = FirebaseManager.readDBprofilePicture(selectedFriend.getFriendEmail());
                Image scaledProfilePicture = profilePictureIconFriend.getImage().getScaledInstance(instance.getPreferredSize().width / 25, instance.getPreferredSize().width / 25, Image.SCALE_SMOOTH);
                scaledProfilePictureIcon = new ImageIcon(scaledProfilePicture);
                friendAvatar.setImage(scaledProfilePictureIcon);
                friendAvatar.setBorderSize(instance.getPreferredSize().height/221);
                friendAvatar.setBorderSpace((int) (instance.getPreferredSize().height/331.5));

                friendProfilePicturePanel.add(Box.createHorizontalGlue());
                friendProfilePicturePanel.add(friendAvatar);
                friendProfilePicturePanel.add(Box.createHorizontalGlue());






                /*--------------------text recived from TextBox--------------------*/




                JTextArea friendTextMessage = new JTextArea();
                friendTextMessage.setName("friendTextMessage");
                friendTextMessage.setMinimumSize(new Dimension(friendMessagePanel.getPreferredSize().width - friendProfilePicturePanel.getPreferredSize().width,1));
                friendTextMessage.append(chatLog.get(selectedFriend.getFriendEmail()));
                friendTextMessage.setFont(new Font("Arial", Font.PLAIN, (int)(instance.getPreferredSize().width/65f)));
                friendTextMessage.setOpaque(false);
                friendTextMessage.setLineWrap(true);
                friendTextMessage.setWrapStyleWord(true);
                friendTextMessage.setEditable(false);
                friendTextMessage.setForeground(new Color(212, 215, 218));
                //friendTextMessage.setBorder(new LineBorder(AppThemeColors.PRIMARY,1,true));
                friendTextMessage.setAlignmentY(Component.TOP_ALIGNMENT);
                SwingUtilities.invokeLater(() -> {
                    FontMetrics metrics = friendTextMessage.getFontMetrics(friendTextMessage.getFont());
                    int lineHeight = metrics.getHeight();

                    int areaWidth = friendMessagePanel.getPreferredSize().width - friendProfilePicturePanel.getPreferredSize().width;

                    int charsPerLine = areaWidth / metrics.charWidth('m');

                    int totalChars = friendTextMessage.getText().length();

                    int totalLinesNeeded = (int) Math.ceil((double) totalChars / charsPerLine);

                    int totalHeight = totalLinesNeeded * lineHeight;
                    friendTextMessage.setSize(new Dimension(friendMessagePanel.getPreferredSize().width - friendProfilePicturePanel.getPreferredSize().width, totalHeight));
                    //System.out.println(new Dimension(friendMessagePanel.getPreferredSize().width - friendProfilePicturePanel.getPreferredSize().width, totalHeight));
                    friendTextMessage.setMinimumSize(friendTextMessage.getPreferredSize());
                    friendTextMessage.setMaximumSize(friendTextMessage.getPreferredSize());
                    friendTextMessage.setForeground(Color.WHITE);
                    friendTextMessage.setBackground(Color.green);

                    //System.out.println(friendTextMessage.getSize().height +" "+ friendMessagePanel.getPreferredSize().height);

                    if (friendTextMessage.getSize().height > friendMessagePanel.getPreferredSize().height) {
                        System.out.println("Max size set");
                        friendMessagePanel.setPreferredSize(new Dimension((int) (mainRightPanel.getPreferredSize().width / 1.05), friendTextMessage.getPreferredSize().height));
                        friendMessagePanel.setMinimumSize(friendMessagePanel.getPreferredSize());
                        friendMessagePanel.setMaximumSize(friendMessagePanel.getPreferredSize());
                    }
                });

                friendMessagePanel.add(friendProfilePicturePanel);
                friendMessagePanel.add(Box.createHorizontalGlue());
                friendMessagePanel.add(friendTextMessage);

                selectedFriend.getMessageStorage().add(Box.createRigidArea(new Dimension(friendMessagePanel.getPreferredSize().width, instance.getPreferredSize().height/40)));
                selectedFriend.getMessageStorage().add(friendMessagePanel);

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
        leftTopPanel.setBackground(AppThemeColors.PRIMARY);
        leftBottomPanel.setBackground(AppThemeColors.panelColor);
        belowPanel.setBackground(AppThemeColors.SECONDARY);
        messengerTextBox.setForeground(AppThemeColors.foregroundColor);
        messengerTextBox.setBackground(AppThemeColors.buttonBG);
        belowRightPanel.setBackground(AppThemeColors.textFieldColor);
        clickToSendButton.setForeground(AppThemeColors.foregroundColor);
        clickToSendButton.setBackground(AppThemeColors.buttonBG);
        addPanel.setBackground(AppThemeColors.panelColor);
        friendRequestMailText.setForeground(AppThemeColors.foregroundColor);
        //friendRequestMailText.setBackground(AppThemeColors.textFieldColor);
        clickToSendRequestButton.setForeground(AppThemeColors.foregroundColor);
        mainRightPanel.setBackground(AppThemeColors.panelColor);
        messagesScrollPane.setBackground(AppThemeColors.panelColor);
        friendsScrollPane.getViewport().setBackground(AppThemeColors.panelColor);
        messageStorage.setBackground(AppThemeColors.panelColor);
        requestsPanel.setBackground(AppThemeColors.SECONDARY);
        requestScrollPane.getViewport().setBackground(AppThemeColors.panelColor);
        requestsText.setBackground(AppThemeColors.panelColor);
        requestsText.setForeground(AppThemeColors.foregroundColor);
        sendRequestText.setBackground(AppThemeColors.panelColor);
        sendRequestText.setForeground(AppThemeColors.foregroundColor);
        friendRequestMailText.setBackground(AppThemeColors.textFieldColor);
        friendRequestMailText.setForeground(AppThemeColors.foregroundColor);
        friendsText.setBackground(AppThemeColors.panelColor);
        friendsText.setForeground(AppThemeColors.foregroundColor);
        mainMiddlePanel.setBackground(AppThemeColors.PRIMARY);
        mainMiddleTopPanel.setBackground(AppThemeColors.PRIMARY);
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

