package se.aljr.application.settings;

import se.aljr.application.AppThemeColors;
import se.aljr.application.ApplicationWindow;
import se.aljr.application.ResourcePath;
import se.aljr.application.UserData;
import se.aljr.application.exercise.CreateExerciseModule;
import se.aljr.application.homepage.HomePanel;
import se.aljr.application.homepage.MenuPanel;
import se.aljr.application.homepage.TopBar;
import se.aljr.application.loginpage.FirebaseManager;
import se.aljr.application.programplanner.ProgramPanel;
import se.aljr.application.settings.custom.SteelCheckBoxUI;


import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.filechooser.FileNameExtensionFilter;


public class SettingsPanel extends JPanel{

    protected static ImageIcon profilePicture;

    ImageIcon settingsPanelBackground;
    Image scaleSettingsPanelBackground;
    ImageIcon scaledSettingsPanelBackgroundIcon;

    ImageIcon lightSettingsPanelBackground;
    Image lightScaleSettingsPanelBackground;
    ImageIcon lightScaledSettingsPanelBackgroundIcon;

    Font font;

    //Icons for the settings buttons
    ImageIcon generalSettingsIcon;
    ImageIcon themeSettingsIcon;
    ImageIcon notificationsSettingsIcon;
    ImageIcon accountSettingsIcon;
    ImageIcon privacySettingsIcon;

    ImageIcon darkGeneralSettingsIcon;
    ImageIcon darkThemeSettingsIcon;
    ImageIcon darkNotificationsSettingsIcon;
    ImageIcon darkAccountSettingsIcon;
    ImageIcon darkPrivacySettingsIcon;

    private ImageIcon scaledGeneralSettingsIcon;
    private ImageIcon scaledThemeSettingsIcon;
    private ImageIcon scaledNotificationsSettingsIcon;
    private ImageIcon scaledAccountSettingsIcon;
    private ImageIcon scaledPrivacySettingsIcon;

    private ImageIcon scaledDarkGeneralSettingsIcon;
    private ImageIcon scaledDarkThemeSettingsIcon;
    private ImageIcon scaledDarkNotificationsSettingsIcon;
    private ImageIcon scaledDarkAccountSettingsIcon;
    private ImageIcon scaledDarkPrivacySettingsIcon;


    //Array lists for the various dropdown menus
    ArrayList<Integer> agesList = new ArrayList<>();
    ArrayList<String> themeList = new ArrayList<>();

    public static boolean lightMode = false;
    public static String currentTheme = "Dark";

    //Buttons
    JButton generalSettingsButton = new JButton("General", scaledGeneralSettingsIcon);
    JButton themeSettingsButton = new JButton("Theme", scaledThemeSettingsIcon);
    JButton notificationsSettingsButton = new JButton("Notifications", scaledNotificationsSettingsIcon);
    JButton accountSettingsButton = new JButton("Account", scaledAccountSettingsIcon);
    JButton privacySettingsButton = new JButton("Privacy", scaledPrivacySettingsIcon);

    //Container for all panels
    JPanel containerPanel = new JPanel();
    //Left/Right panel within container panel
    JPanel leftPanel = new JPanel();
    JPanel rightPanel = new JPanel();

    //Button Menu
    JPanel settingsLabelPanel = new JPanel();
    JLabel settingsLabel = new JLabel("Settings");
    JPanel buttonPanel = new JPanel();

    //General Panel
    JPanel generalPanel = new JPanel();
    JPanel generalScrollPanel = new JPanel();
    JScrollPane generalScroll = new JScrollPane(generalScrollPanel, JScrollPane.VERTICAL_SCROLLBAR_NEVER, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
    JPanel test2 = new JPanel();
    JPanel test3 = new JPanel();
    JPanel test4 = new JPanel();
    JPanel test5 = new JPanel();

    //Theme panel
    JPanel themePanel = new JPanel();
    JPanel themeScrollPanel = new JPanel();
    JScrollPane themeScroll = new JScrollPane(themeScrollPanel, JScrollPane.VERTICAL_SCROLLBAR_NEVER, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

    JPanel themeSelectionPanel = new JPanel();
    JLabel themeSelectionLabel = new JLabel("Select theme");

    JPanel test33 = new JPanel();
    JPanel test44 = new JPanel();
    JPanel test55 = new JPanel();

    //Notifications Panel
    JPanel notificationsPanel = new JPanel();

    //Account Panel
    JPanel accountPanel = new JPanel();
    JPanel accountScrollPanel = new JPanel();
    JScrollPane accountScroll = new JScrollPane(accountScrollPanel, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

    JPanel accountAgePanel = new JPanel();
    JPanel accountWeightPanel = new JPanel();
    JPanel accountHeightPanel = new JPanel();
    JPanel accountNamePanel = new JPanel();
    JLabel changeUsernameLabel= new JLabel("Change Username");
    JTextField changeUsernameField = new JTextField();
    JPanel accountPFPPanel = new JPanel();

    //Privacy Panel
    JPanel privacyPanel = new JPanel();

    public static int currentPage = 0;

    public int width;
    public int height;


    public SettingsPanel(int width, int height){
        this.setPreferredSize(new Dimension(width, height));
        this.width = width;
        this.height = height;
        
        settingsPanelBackground = new ImageIcon(ResourcePath.getResourcePath() +"emptyBackground.png");
        scaleSettingsPanelBackground = settingsPanelBackground.getImage().getScaledInstance(width,height, Image.SCALE_SMOOTH);
        scaledSettingsPanelBackgroundIcon = new ImageIcon(scaleSettingsPanelBackground);

        lightSettingsPanelBackground = new ImageIcon(ResourcePath.getResourcePath() +"lightEmptyBackground.png");
        lightScaleSettingsPanelBackground = lightSettingsPanelBackground.getImage().getScaledInstance(width,height,Image.SCALE_SMOOTH);
        lightScaledSettingsPanelBackgroundIcon = new ImageIcon(lightScaleSettingsPanelBackground);

        generalSettingsIcon = new ImageIcon(ResourcePath.getResourcePath()  + "settings_general.png");
        themeSettingsIcon = new ImageIcon(ResourcePath.getResourcePath() +"settings_theme.png");
        notificationsSettingsIcon = new ImageIcon(ResourcePath.getResourcePath() +"settings_notifications.png");
        accountSettingsIcon = new ImageIcon(ResourcePath.getResourcePath() +"settings_account.png");
        privacySettingsIcon = new ImageIcon(ResourcePath.getResourcePath() +"settings_privacy.png");

        Image scaledgeneralSettingsIcon = generalSettingsIcon.getImage().getScaledInstance(width-width/5*4,height/12, Image.SCALE_SMOOTH);
        scaledGeneralSettingsIcon = new ImageIcon(scaledgeneralSettingsIcon);
        Image scaledthemeSettingsIcon = themeSettingsIcon.getImage().getScaledInstance(width-width/5*4, height/12, Image.SCALE_SMOOTH);
        scaledThemeSettingsIcon = new ImageIcon(scaledthemeSettingsIcon);
        Image scalednotificationsSettingsIcon = notificationsSettingsIcon.getImage().getScaledInstance(width-width/5*4, height/12, Image.SCALE_SMOOTH);
        scaledNotificationsSettingsIcon = new ImageIcon(scalednotificationsSettingsIcon);
        Image scaledaccountSettingsIcon = accountSettingsIcon.getImage().getScaledInstance(width-width/5*4, height/12, Image.SCALE_SMOOTH);
        scaledAccountSettingsIcon = new ImageIcon(scaledaccountSettingsIcon);
        Image scaledprivacySettingsIcon = privacySettingsIcon.getImage().getScaledInstance(width-width/5*4, height/12, Image.SCALE_SMOOTH);
        scaledPrivacySettingsIcon = new ImageIcon(scaledprivacySettingsIcon);

        darkGeneralSettingsIcon = new ImageIcon(ResourcePath.getResourcePath()  + "settings_general_dark.png");
        darkThemeSettingsIcon = new ImageIcon(ResourcePath.getResourcePath() +"settings_theme_dark.png");
        darkNotificationsSettingsIcon = new ImageIcon(ResourcePath.getResourcePath() +"settings_notifications_dark.png");
        darkAccountSettingsIcon = new ImageIcon(ResourcePath.getResourcePath() +"settings_account_dark.png");
        darkPrivacySettingsIcon = new ImageIcon(ResourcePath.getResourcePath() +"settings_privacy_dark.png");

        Image scaleddarkGeneralSettingsIcon = darkGeneralSettingsIcon.getImage().getScaledInstance(width-width/5*4,height/12, Image.SCALE_SMOOTH);
        scaledDarkGeneralSettingsIcon = new ImageIcon(scaleddarkGeneralSettingsIcon);
        Image scaleddarkThemeSettingsIcon = darkThemeSettingsIcon.getImage().getScaledInstance(width-width/5*4, height/12, Image.SCALE_SMOOTH);
        scaledDarkThemeSettingsIcon = new ImageIcon(scaleddarkThemeSettingsIcon);
        Image scaleddarkNotificationsSettingsIcon = darkNotificationsSettingsIcon.getImage().getScaledInstance(width-width/5*4, height/12, Image.SCALE_SMOOTH);
        scaledDarkNotificationsSettingsIcon = new ImageIcon(scaleddarkNotificationsSettingsIcon);
        Image scaleddarkAccountSettingsIcon = darkAccountSettingsIcon.getImage().getScaledInstance(width-width/5*4, height/12, Image.SCALE_SMOOTH);
        scaledDarkAccountSettingsIcon = new ImageIcon(scaleddarkAccountSettingsIcon);
        Image scaleddarkPrivacySettingsIcon = darkPrivacySettingsIcon.getImage().getScaledInstance(width-width/5*4, height/12, Image.SCALE_SMOOTH);
        scaledDarkPrivacySettingsIcon = new ImageIcon(scaleddarkPrivacySettingsIcon);

        switch (UserData.getTheme()){
            case "dark"-> lightMode = false;
            case "light"-> lightMode = true;
        }

        this.setOpaque(false);
        this.setLayout(new BorderLayout(0,0));
        this.setBorder(new EmptyBorder(15,15,15,15));
        init(width, height);
    }

    private void init(int width, int height){
        try{
            font=Font.createFont(Font.TRUETYPE_FONT, new File(ResourcePath.getResourcePath()+"BebasNeue-Regular.otf"));
            font = font.deriveFont((float) (height/17));
        }catch(Exception e){
            font = new Font("Arial", Font.BOLD, 40);
            e.printStackTrace();
        }
        buildBackgroundPanels();
        buildButtonPanel();
        buildButtons();

        buildGeneralPanel();
        buildThemePanel();
        buildNotificationsPanel();
        buildAccountPanel();
        buildPrivacyPanel();

        this.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                SwingUtilities.invokeLater(()->{
                    leftPanel.setPreferredSize(new Dimension(getWidth()/4, getHeight()));
                    buttonPanel.setPreferredSize(new Dimension(getWidth()/4,getHeight()));

                    rightPanel.setPreferredSize(new Dimension(getWidth()-getWidth()/10*7, getHeight()));

                    generalScroll.setPreferredSize(new Dimension(getWidth()-getWidth()/2, getHeight()));
                    settingsLabelPanel.setPreferredSize(new Dimension(getWidth(), getHeight()/9));


                    settingsLabel.setFont(new Font("Arial", Font.PLAIN,getHeight()/15));
                    settingsLabel.setPreferredSize(new Dimension(getWidth(), getHeight()/9));

                    rightPanel.setBorder(BorderFactory.createMatteBorder(getHeight()/20, 0, getHeight()/20, 0, AppThemeColors.PRIMARY));


                    if(!lightMode){
                        Image scaledgeneralSettingsIcon = generalSettingsIcon.getImage().getScaledInstance(getWidth()-getWidth()/5*4,getHeight()/12, Image.SCALE_SMOOTH);
                        scaledGeneralSettingsIcon = new ImageIcon(scaledgeneralSettingsIcon);
                        generalSettingsButton.setIcon(scaledGeneralSettingsIcon);
                        Image scaledthemeSettingsIcon = themeSettingsIcon.getImage().getScaledInstance(getWidth()-getWidth()/5*4, getHeight()/12, Image.SCALE_SMOOTH);
                        scaledThemeSettingsIcon = new ImageIcon(scaledthemeSettingsIcon);
                        themeSettingsButton.setIcon(scaledThemeSettingsIcon);
                        Image scalednotificationsSettingsIcon = notificationsSettingsIcon.getImage().getScaledInstance(getWidth()-getWidth()/5*4, getHeight()/12, Image.SCALE_SMOOTH);
                        scaledNotificationsSettingsIcon = new ImageIcon(scalednotificationsSettingsIcon);
                        notificationsSettingsButton.setIcon(scaledNotificationsSettingsIcon);
                        Image scaledaccountSettingsIcon = accountSettingsIcon.getImage().getScaledInstance(getWidth()-getWidth()/5*4, getHeight()/12, Image.SCALE_SMOOTH);
                        scaledAccountSettingsIcon = new ImageIcon(scaledaccountSettingsIcon);
                        accountSettingsButton.setIcon(scaledAccountSettingsIcon);
                        Image scaledprivacySettingsIcon = privacySettingsIcon.getImage().getScaledInstance(getWidth()-getWidth()/5*4, getHeight()/12, Image.SCALE_SMOOTH);
                        scaledPrivacySettingsIcon = new ImageIcon(scaledprivacySettingsIcon);
                        privacySettingsButton.setIcon(scaledPrivacySettingsIcon);
                    }else{
                        Image scaleddarkGeneralSettingsIcon = darkGeneralSettingsIcon.getImage().getScaledInstance(getWidth()-getWidth()/5*4,getHeight()/12, Image.SCALE_SMOOTH);
                        scaledDarkGeneralSettingsIcon = new ImageIcon(scaleddarkGeneralSettingsIcon);
                        generalSettingsButton.setIcon(scaledDarkGeneralSettingsIcon);
                        Image scaleddarkThemeSettingsIcon = darkThemeSettingsIcon.getImage().getScaledInstance(getWidth()-getWidth()/5*4, getHeight()/12, Image.SCALE_SMOOTH);
                        scaledDarkThemeSettingsIcon = new ImageIcon(scaleddarkThemeSettingsIcon);
                        themeSettingsButton.setIcon(scaledDarkThemeSettingsIcon);
                        Image scaleddarkNotificationsSettingsIcon = darkNotificationsSettingsIcon.getImage().getScaledInstance(getWidth()-getWidth()/5*4, getHeight()/12, Image.SCALE_SMOOTH);
                        scaledDarkNotificationsSettingsIcon = new ImageIcon(scaleddarkNotificationsSettingsIcon);
                        notificationsSettingsButton.setIcon(scaledDarkNotificationsSettingsIcon);
                        Image scaleddarkAccountSettingsIcon = darkAccountSettingsIcon.getImage().getScaledInstance(getWidth()-getWidth()/5*4, getHeight()/12, Image.SCALE_SMOOTH);
                        scaledDarkAccountSettingsIcon = new ImageIcon(scaleddarkAccountSettingsIcon);
                        accountSettingsButton.setIcon(scaledDarkAccountSettingsIcon);
                        Image scaleddarkPrivacySettingsIcon = darkPrivacySettingsIcon.getImage().getScaledInstance(getWidth()-getWidth()/5*4, getHeight()/12, Image.SCALE_SMOOTH);
                        scaledDarkPrivacySettingsIcon = new ImageIcon(scaleddarkPrivacySettingsIcon);
                        privacySettingsButton.setIcon(scaledDarkPrivacySettingsIcon);
                    }

                    generalSettingsButton.setFont(new Font("Arial", Font.PLAIN,getHeight()/30));
                    generalSettingsButton.setPreferredSize(new Dimension(getWidth(), getHeight()/12));
                    generalSettingsButton.setMaximumSize(new Dimension(getWidth()-getWidth()/5*4, getHeight()/3));

                    themeSettingsButton.setFont(new Font("Arial", Font.PLAIN,getHeight()/30));
                    themeSettingsButton.setPreferredSize(new Dimension(getWidth(), getHeight()/12));
                    themeSettingsButton.setMaximumSize(new Dimension(getWidth()-getWidth()/5*4, getHeight()/3));

                    notificationsSettingsButton.setFont(new Font("Arial", Font.PLAIN,getHeight()/30));
                    notificationsSettingsButton.setPreferredSize(new Dimension(getWidth(), getHeight()/12));
                    notificationsSettingsButton.setMaximumSize(new Dimension(getWidth()-getWidth()/5*4, getHeight()/3));

                    accountSettingsButton.setFont(new Font("Arial", Font.PLAIN,getHeight()/30));
                    accountSettingsButton.setPreferredSize(new Dimension(getWidth(), getHeight()/12));
                    accountSettingsButton.setMaximumSize(new Dimension(getWidth()-getWidth()/5*4, getHeight()/3));

                    privacySettingsButton.setFont(new Font("Arial", Font.PLAIN,getHeight()/30));
                    privacySettingsButton.setPreferredSize(new Dimension(getWidth(), getHeight()/12));
                    privacySettingsButton.setMaximumSize(new Dimension(getWidth()-getWidth()/5*4, getHeight()/3));

                    test2.setPreferredSize(new Dimension(getWidth()-getWidth()/10*7, getHeight()/10));
                    test3.setPreferredSize(new Dimension(getWidth()-getWidth()/10*7, getHeight()/10));
                    test4.setPreferredSize(new Dimension(getWidth()-getWidth()/10*7, getHeight()/10));
                    test5.setPreferredSize(new Dimension(getWidth()-getWidth()/10*7, getHeight()/10));

                    SteelCheckBoxUI.SIZE = new Dimension(getWidth()/20, getWidth()/40);
                    themeSelectionPanel.setPreferredSize(new Dimension(getWidth()-getWidth()/10*7, getHeight()/15));

                    test33.setPreferredSize(new Dimension(getWidth()-getWidth()/10*7, getHeight()/10));
                    test44.setPreferredSize(new Dimension(getWidth()-getWidth()/10*7, getHeight()/10));
                    test55.setPreferredSize(new Dimension(getWidth()-getWidth()/10*7, getHeight()/10));

                    accountAgePanel.setPreferredSize(new Dimension(getWidth()-getWidth()/10*7, getHeight()/15));
                    accountWeightPanel.setPreferredSize(new Dimension(getWidth()-getWidth()/10*7, getHeight()/15));
                    accountHeightPanel.setPreferredSize(new Dimension(getWidth()-getWidth()/10*7, getHeight()/15));
                    accountNamePanel.setPreferredSize(new Dimension(getWidth()-getWidth()/10*7, getHeight()/15));
                    accountPFPPanel.setPreferredSize(new Dimension(getWidth()-getWidth()/10*7, getHeight()/15));
                });
            }
        });
    }

    public void buildBackgroundPanels(){
        /**Container for all the panels*/
        containerPanel.setLayout(new BoxLayout(containerPanel, BoxLayout.X_AXIS));
        containerPanel.setSize(new Dimension(width, height));
        containerPanel.setOpaque(false);

        /**Creating the left panel*/
        leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.X_AXIS));
        leftPanel.setPreferredSize(new Dimension(width/4, height));
        leftPanel.setBackground(Color.black);
        leftPanel.setOpaque(false);

        /**Right panel*/
        rightPanel.setOpaque(true);
        rightPanel.setLayout(new BoxLayout(rightPanel, BoxLayout.X_AXIS));
        rightPanel.setPreferredSize(new Dimension(width-width/10*7, height));
        rightPanel.setBorder(BorderFactory.createMatteBorder(height/20, 0, height/20, 0, AppThemeColors.PRIMARY));
        rightPanel.setBackground(new Color(30,30,51));

        this.add(containerPanel);
        containerPanel.add(leftPanel);
        containerPanel.add(rightPanel);
        containerPanel.add(Box.createHorizontalGlue());
        containerPanel.add(Box.createHorizontalGlue());
        containerPanel.add(Box.createHorizontalGlue());
        containerPanel.add(Box.createHorizontalGlue());
        containerPanel.add(Box.createHorizontalGlue());
        containerPanel.add(Box.createHorizontalGlue());
        containerPanel.add(Box.createHorizontalGlue());
        containerPanel.add(Box.createHorizontalGlue());
        containerPanel.add(Box.createHorizontalGlue());
        containerPanel.add(Box.createHorizontalGlue());
        containerPanel.add(Box.createHorizontalGlue());
        containerPanel.add(Box.createHorizontalGlue());
        containerPanel.add(Box.createHorizontalGlue());
        containerPanel.add(Box.createHorizontalGlue());
    }

    public void buildButtonPanel(){
        //Settings Label
        settingsLabelPanel.setPreferredSize(new Dimension(width, height/9));
        settingsLabelPanel.setOpaque(false);

        settingsLabel = new JLabel("Settings");
        settingsLabel.setFont(new Font("Arial", Font.BOLD,height/15));
        settingsLabel.setHorizontalAlignment(SwingConstants.CENTER);
        settingsLabel.setVerticalAlignment(SwingConstants.CENTER);
        settingsLabel.setPreferredSize(new Dimension(width, height/9));
        settingsLabel.setForeground(AppThemeColors.foregroundColor);

        settingsLabelPanel.add(settingsLabel);

        //ButtonPanel
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));
        buttonPanel.setPreferredSize(new Dimension(width/10, height));
        buttonPanel.setBackground(new Color(10,10,10));
        buttonPanel.setOpaque(false);

        leftPanel.add(buttonPanel);

        buttonPanel.add(settingsLabelPanel);
        buttonPanel.add(generalSettingsButton);
        buttonPanel.add(themeSettingsButton);
        buttonPanel.add(notificationsSettingsButton);
        buttonPanel.add(accountSettingsButton);
        buttonPanel.add(privacySettingsButton);
        buttonPanel.add(Box.createVerticalGlue());
        buttonPanel.add(Box.createVerticalGlue());
        buttonPanel.add(Box.createVerticalGlue());
        buttonPanel.add(Box.createVerticalGlue());
        buttonPanel.add(Box.createVerticalGlue());
        buttonPanel.add(Box.createVerticalGlue());
        buttonPanel.add(Box.createVerticalGlue());
        buttonPanel.add(Box.createVerticalGlue());
        buttonPanel.add(Box.createVerticalGlue());


    }

    public void buildButtons(){
        generalSettingsButton.setFont(new Font("Arial", Font.PLAIN,height/30));
        generalSettingsButton.setHorizontalTextPosition(SwingConstants.CENTER);
        generalSettingsButton.setVerticalTextPosition(SwingConstants.CENTER);
        generalSettingsButton.setFocusPainted(false);
        generalSettingsButton.setFocusable(false);
        generalSettingsButton.setBorderPainted(false);
        generalSettingsButton.setContentAreaFilled(false);
        generalSettingsButton.setOpaque(true);
        generalSettingsButton.setBackground(AppThemeColors.buttonBGSelected);
        generalSettingsButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        generalSettingsButton.setForeground(AppThemeColors.foregroundColor);
        generalSettingsButton.setPreferredSize(new Dimension(width, height/9));
        generalSettingsButton.setMaximumSize(new Dimension(width-width/4*3, height/3));
        generalSettingsButton.addMouseListener(new MouseAdapter() {
            private boolean isHovered = false;
            @Override
            public void mousePressed(MouseEvent e) {
                generalSettingsButton.setBackground(AppThemeColors.buttonBGSelected);
            }
            @Override
            public void mouseReleased(MouseEvent e) {
                if(isHovered){
                    currentPage = 0;
                    generalSettingsButton.setBackground(AppThemeColors.buttonBGSelected);
                    themeSettingsButton.setBackground(AppThemeColors.buttonBG);
                    notificationsSettingsButton.setBackground(AppThemeColors.buttonBG);
                    accountSettingsButton.setBackground(AppThemeColors.buttonBG);
                    privacySettingsButton.setBackground(AppThemeColors.buttonBG);
                    rightPanel.remove(privacyPanel);
                    rightPanel.remove(accountPanel);
                    rightPanel.remove(notificationsPanel);
                    rightPanel.remove(themePanel);
                    rightPanel.add(generalPanel);
                    privacyPanel.setVisible(false);
                    accountPanel.setVisible(false);
                    notificationsPanel.setVisible(false);
                    themePanel.setVisible(false);
                    generalPanel.setVisible(true);
                }else{
                    generalSettingsButton.setBackground(AppThemeColors.buttonBG);
                }
            }
            @Override
            public void mouseEntered(MouseEvent e) {
                isHovered = true;
                if(currentPage != 0 ){
                    generalSettingsButton.setBackground(AppThemeColors.buttonBGHovered);
                }
            }
            @Override
            public void mouseExited(MouseEvent e) {
                isHovered = false;
                if(currentPage != 0){
                    generalSettingsButton.setBackground(AppThemeColors.buttonBG);
                }
            }
        });

        themeSettingsButton.setFont(new Font("Arial", Font.PLAIN,height/30));
        themeSettingsButton.setHorizontalTextPosition(SwingConstants.CENTER);
        themeSettingsButton.setVerticalTextPosition(SwingConstants.CENTER);
        themeSettingsButton.setFocusPainted(false);
        themeSettingsButton.setFocusable(false);
        themeSettingsButton.setBorderPainted(false);
        themeSettingsButton.setContentAreaFilled(false);
        themeSettingsButton.setOpaque(true);
        themeSettingsButton.setBackground(AppThemeColors.buttonBG);
        themeSettingsButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        themeSettingsButton.setForeground(AppThemeColors.foregroundColor);
        themeSettingsButton.setPreferredSize(new Dimension(width, height/9));
        themeSettingsButton.setMaximumSize(new Dimension(width-width/4*3, height/3));
        themeSettingsButton.addMouseListener(new MouseAdapter() {
            private boolean isHovered = false;
            @Override
            public void mousePressed(MouseEvent e) {
                themeSettingsButton.setBackground(AppThemeColors.buttonBGSelected);
            }
            @Override
            public void mouseReleased(MouseEvent e) {
                if(isHovered){
                    currentPage = 1;
                    generalSettingsButton.setBackground(AppThemeColors.buttonBG);
                    themeSettingsButton.setBackground(AppThemeColors.buttonBGSelected);
                    notificationsSettingsButton.setBackground(AppThemeColors.buttonBG);
                    accountSettingsButton.setBackground(AppThemeColors.buttonBG);
                    privacySettingsButton.setBackground(AppThemeColors.buttonBG);
                    rightPanel.remove(privacyPanel);
                    rightPanel.remove(accountPanel);
                    rightPanel.remove(notificationsPanel);
                    rightPanel.remove(generalPanel);
                    rightPanel.add(themePanel);
                    privacyPanel.setVisible(false);
                    accountPanel.setVisible(false);
                    notificationsPanel.setVisible(false);
                    generalPanel.setVisible(false);
                    themePanel.setVisible(true);
                }else{
                    themeSettingsButton.setBackground(AppThemeColors.buttonBG);
                }
            }
            @Override
            public void mouseEntered(MouseEvent e) {
                isHovered = true;
                if(currentPage != 1){
                    themeSettingsButton.setBackground(AppThemeColors.buttonBGHovered);
                }
            }
            @Override
            public void mouseExited(MouseEvent e) {
                isHovered = false;
                if(currentPage != 1){
                    themeSettingsButton.setBackground(AppThemeColors.buttonBG);
                }
            }
        });

        notificationsSettingsButton.setFont(new Font("Arial", Font.PLAIN,height/30));
        notificationsSettingsButton.setHorizontalTextPosition(SwingConstants.CENTER);
        notificationsSettingsButton.setVerticalTextPosition(SwingConstants.CENTER);
        notificationsSettingsButton.setFocusPainted(false);
        notificationsSettingsButton.setFocusable(false);
        notificationsSettingsButton.setBorderPainted(false);
        notificationsSettingsButton.setContentAreaFilled(false);
        notificationsSettingsButton.setOpaque(true);
        notificationsSettingsButton.setBackground(AppThemeColors.buttonBG);
        notificationsSettingsButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        notificationsSettingsButton.setForeground(AppThemeColors.foregroundColor);
        notificationsSettingsButton.setPreferredSize(new Dimension(width, height/9));
        notificationsSettingsButton.setMaximumSize(new Dimension(width-width/4*3, height/3));
        notificationsSettingsButton.addMouseListener(new MouseAdapter() {
            private boolean isHovered = false;
            @Override
            public void mousePressed(MouseEvent e) {
                notificationsSettingsButton.setBackground(AppThemeColors.buttonBGSelected);
            }
            @Override
            public void mouseReleased(MouseEvent e) {
                if(isHovered){
                    currentPage = 2;
                    generalSettingsButton.setBackground(AppThemeColors.buttonBG);
                    themeSettingsButton.setBackground(AppThemeColors.buttonBG);
                    notificationsSettingsButton.setBackground(AppThemeColors.buttonBGSelected);
                    accountSettingsButton.setBackground(AppThemeColors.buttonBG);
                    privacySettingsButton.setBackground(AppThemeColors.buttonBG);
                    rightPanel.remove(privacyPanel);
                    rightPanel.remove(accountPanel);
                    rightPanel.remove(generalPanel);
                    rightPanel.remove(themePanel);
                    rightPanel.add(notificationsPanel);
                    privacyPanel.setVisible(false);
                    accountPanel.setVisible(false);
                    generalPanel.setVisible(false);
                    themePanel.setVisible(false);
                    notificationsPanel.setVisible(true);
                }else{
                    notificationsSettingsButton.setBackground(AppThemeColors.buttonBG);
                }
            }
            @Override
            public void mouseEntered(MouseEvent e) {
                isHovered = true;
                if(currentPage!=2)
                    notificationsSettingsButton.setBackground(AppThemeColors.buttonBGHovered);
            }
            @Override
            public void mouseExited(MouseEvent e) {
                isHovered = false;
                if(currentPage!=2){
                    notificationsSettingsButton.setBackground(AppThemeColors.buttonBG);
                }
            }
        });

        accountSettingsButton.setFont(new Font("Arial", Font.PLAIN,height/30));
        accountSettingsButton.setHorizontalTextPosition(SwingConstants.CENTER);
        accountSettingsButton.setVerticalTextPosition(SwingConstants.CENTER);
        accountSettingsButton.setFocusPainted(false);
        accountSettingsButton.setFocusable(false);
        accountSettingsButton.setBorderPainted(false);
        accountSettingsButton.setContentAreaFilled(false);
        accountSettingsButton.setOpaque(true);
        accountSettingsButton.setBackground(AppThemeColors.buttonBG);
        accountSettingsButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        accountSettingsButton.setForeground(AppThemeColors.foregroundColor);
        accountSettingsButton.setPreferredSize(new Dimension(width, height/9));
        accountSettingsButton.setMaximumSize(new Dimension(width-width/4*3, height/3));
        accountSettingsButton.addMouseListener(new MouseAdapter() {
            private boolean isHovered = false;
            @Override
            public void mousePressed(MouseEvent e) {
                accountSettingsButton.setBackground(AppThemeColors.buttonBGSelected);
            }
            @Override
            public void mouseReleased(MouseEvent e) {
                if(isHovered){
                    currentPage = 3;
                    generalSettingsButton.setBackground(AppThemeColors.buttonBG);
                    themeSettingsButton.setBackground(AppThemeColors.buttonBG);
                    notificationsSettingsButton.setBackground(AppThemeColors.buttonBG);
                    accountSettingsButton.setBackground(AppThemeColors.buttonBGSelected);
                    privacySettingsButton.setBackground(AppThemeColors.buttonBG);
                    rightPanel.remove(privacyPanel);
                    rightPanel.remove(generalPanel);
                    rightPanel.remove(themePanel);
                    rightPanel.remove(notificationsPanel);
                    rightPanel.add(accountPanel);
                    privacyPanel.setVisible(false);
                    generalPanel.setVisible(false);
                    themePanel.setVisible(false);
                    notificationsPanel.setVisible(false);
                    accountPanel.setVisible(true);
                }else{
                    accountSettingsButton.setBackground(AppThemeColors.buttonBG);
                }
            }
            @Override
            public void mouseEntered(MouseEvent e) {
                isHovered = true;
                if(currentPage!=3){
                    accountSettingsButton.setBackground(AppThemeColors.buttonBGHovered);
                }
            }
            @Override
            public void mouseExited(MouseEvent e) {
                isHovered = false;
                if(currentPage!=3){
                    accountSettingsButton.setBackground(AppThemeColors.buttonBG);
                }
            }
        });

        privacySettingsButton.setFont(new Font("Arial", Font.PLAIN,height/30));
        privacySettingsButton.setHorizontalTextPosition(SwingConstants.CENTER);
        privacySettingsButton.setVerticalTextPosition(SwingConstants.CENTER);
        privacySettingsButton.setFocusPainted(false);
        privacySettingsButton.setFocusable(false);
        privacySettingsButton.setBorderPainted(false);
        privacySettingsButton.setContentAreaFilled(false);
        privacySettingsButton.setOpaque(true);
        privacySettingsButton.setBackground(AppThemeColors.buttonBG);
        privacySettingsButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        privacySettingsButton.setForeground(AppThemeColors.foregroundColor);
        privacySettingsButton.setPreferredSize(new Dimension(width, height/9));
        privacySettingsButton.setMaximumSize(new Dimension(width-width/4*3, height/3));
        privacySettingsButton.addMouseListener(new MouseAdapter() {
            private boolean isHovered = false;
            @Override
            public void mousePressed(MouseEvent e) {
                privacySettingsButton.setBackground(AppThemeColors.buttonBGSelected);
            }
            @Override
            public void mouseReleased(MouseEvent e) {
                if(isHovered){
                    currentPage = 4;
                    generalSettingsButton.setBackground(AppThemeColors.buttonBG);
                    themeSettingsButton.setBackground(AppThemeColors.buttonBG);
                    notificationsSettingsButton.setBackground(AppThemeColors.buttonBG);
                    accountSettingsButton.setBackground(AppThemeColors.buttonBG);
                    privacySettingsButton.setBackground(AppThemeColors.buttonBGSelected);
                    rightPanel.remove(generalPanel);
                    rightPanel.remove(themePanel);
                    rightPanel.remove(notificationsPanel);
                    rightPanel.remove(accountPanel);
                    rightPanel.add(privacyPanel);
                    generalPanel.setVisible(false);
                    themePanel.setVisible(false);
                    notificationsPanel.setVisible(false);
                    accountPanel.setVisible(false);
                    privacyPanel.setVisible(true);
                }else{
                    privacySettingsButton.setBackground(AppThemeColors.buttonBG);
                }
            }
            @Override
            public void mouseEntered(MouseEvent e) {
                isHovered = true;
                if(currentPage != 4){
                    privacySettingsButton.setBackground(AppThemeColors.buttonBGHovered);
                }
            }
            @Override
            public void mouseExited(MouseEvent e) {
                isHovered = false;
                if(currentPage!=4){
                    privacySettingsButton.setBackground(AppThemeColors.buttonBG);
                }
            }
        });
    }

    public void buildGeneralPanel(){
        /**General Panel*/
        generalPanel.setBackground(AppThemeColors.panelColor);
        generalPanel.setPreferredSize(new Dimension(width/2, height));
        generalPanel.setVisible(true);
        generalPanel.setLayout(new BoxLayout(generalPanel, BoxLayout.X_AXIS));

        rightPanel.add(generalPanel);

        //Panel within the scrollPane
        generalScrollPanel.setPreferredSize(new Dimension(width-width/10*7, height));
        generalScrollPanel.setBackground(AppThemeColors.panelColor);
        generalScrollPanel.setLayout(new FlowLayout());

        generalPanel.add(generalScroll, BorderLayout.CENTER);

        //Panels within the scrollable window that will hold various settings--------

        test2.setBackground(AppThemeColors.SECONDARY);
        test2.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));
        test2.setPreferredSize(new Dimension(width-width/10*7, height/15));
        JLabel testLabel = new JLabel("Testink");
        test2.add(testLabel);

        test3.setBackground(AppThemeColors.SECONDARY);
        test3.setPreferredSize(new Dimension(width-width/10*7, height/10));

        test4.setBackground(AppThemeColors.SECONDARY);
        test4.setPreferredSize(new Dimension(width-width/10*7, height/10));

        test5.setBackground(AppThemeColors.SECONDARY);
        test5.setPreferredSize(new Dimension(width-width/10*7, height/10));

        generalScrollPanel.add(test2);
        generalScrollPanel.add(test3);
        generalScrollPanel.add(test4);
        generalScrollPanel.add(test5);

    }

    public void buildThemePanel(){
        /**Theme Panel*/
        themePanel.setBackground(AppThemeColors.panelColor);
        themePanel.setLayout(new BoxLayout(themePanel, BoxLayout.X_AXIS));
        themePanel.setVisible(false);

        //Panel within the scrollPane
        themeScrollPanel.setPreferredSize(new Dimension(width/15, height));
        themeScrollPanel.setBackground(AppThemeColors.panelColor);
        themeScrollPanel.setLayout(new FlowLayout());

        themePanel.add(themeScroll, BorderLayout.CENTER);

        //Panels within the scrollable window that will hold various settings
        themeSelectionPanel.setBackground(AppThemeColors.SECONDARY);
        themeSelectionPanel.setLayout(new BoxLayout(themeSelectionPanel, BoxLayout.X_AXIS));
        themeSelectionPanel.setPreferredSize(new Dimension(width-width/10*7, height/15));
        themeSelectionLabel.setForeground(Color.WHITE);

        themeList.add("Light");
        themeList.add("Dark");

        JComboBox themeDropDown = new JComboBox(themeList.toArray());
        themeDropDown.setPreferredSize(new Dimension(width/15, height/10));
        themeDropDown.addItemListener(_-> {
                switch (themeDropDown.getSelectedIndex()){
                    //Light Mode
                    case 0->{
                        lightMode = true;
                        currentTheme = "light";
                        AppThemeColors.updateThemeColors();
                        updateColors();
                        UserData.setTheme("light");
                        try {
                            FirebaseManager.writeDBUser(UserData.getEmail());
                        } catch (IOException ex) {
                            throw new RuntimeException(ex);
                        }
                    }
                    //Dark Mode
                    case 1->{
                        lightMode = false;
                        currentTheme = "dark";
                        AppThemeColors.updateThemeColors();
                        updateColors();
                        UserData.setTheme("dark");
                        try {
                            FirebaseManager.writeDBUser(UserData.getEmail());
                        } catch (IOException ex) {
                            throw new RuntimeException(ex);
                        }
                    }
                }

                rightPanel.setBorder(BorderFactory.createMatteBorder(getHeight()/20, 0, getHeight()/20, 0, AppThemeColors.PRIMARY));
                generalSettingsButton.setBackground(AppThemeColors.buttonBG);
                themeSettingsButton.setBackground(AppThemeColors.buttonBG);
                notificationsSettingsButton.setBackground(AppThemeColors.buttonBG);
                accountSettingsButton.setBackground(AppThemeColors.buttonBG);
                privacySettingsButton.setBackground(AppThemeColors.buttonBG);

                switch (currentPage){
                    case 0-> generalSettingsButton.setBackground(AppThemeColors.buttonBGSelected);

                    case 1-> themeSettingsButton.setBackground(AppThemeColors.buttonBGSelected);

                    case 2-> notificationsSettingsButton.setBackground(AppThemeColors.buttonBGSelected);

                    case 3-> accountSettingsButton.setBackground(AppThemeColors.buttonBGSelected);

                    case 4-> privacySettingsButton.setBackground(AppThemeColors.buttonBGSelected);
                }
        });

        test33.setBackground(AppThemeColors.SECONDARY);
        test33.setLayout(new BoxLayout(test33, BoxLayout.Y_AXIS));
        test33.setPreferredSize(new Dimension(width-width/10*7, height/10));


        test44.setBackground(AppThemeColors.SECONDARY);
        test44.setPreferredSize(new Dimension(width-width/10*7, height/10));


        test55.setBackground(AppThemeColors.SECONDARY);
        test55.setPreferredSize(new Dimension(width-width/10*7, height/10));

        themeScrollPanel.add(themeSelectionPanel);
        themeScrollPanel.add(test33);
        themeScrollPanel.add(test44);
        themeScrollPanel.add(test55);

        themeSelectionPanel.add(Box.createHorizontalGlue());
        themeSelectionPanel.add(themeSelectionLabel);
        themeSelectionPanel.add(Box.createHorizontalGlue());
        themeSelectionPanel.add(Box.createHorizontalGlue());
        themeSelectionPanel.add(Box.createHorizontalGlue());
        themeSelectionPanel.add(Box.createHorizontalGlue());
        themeSelectionPanel.add(Box.createHorizontalGlue());
        themeSelectionPanel.add(themeDropDown);

        themeDropDown.setSelectedIndex(-1);
        if(lightMode){
            themeDropDown.setSelectedIndex(0);
        }else{
            themeDropDown.setSelectedIndex(1);
        }

    }

    public void buildNotificationsPanel(){
        notificationsPanel.setBackground(AppThemeColors.panelColor);
        notificationsPanel.setVisible(false);
    }

    public void buildAccountPanel(){
        /**Acount Panel*/
        accountPanel.setBackground(AppThemeColors.panelColor);
        accountPanel.setLayout(new BoxLayout(accountPanel, BoxLayout.X_AXIS));
        accountPanel.setVisible(false);

        //Panel within the scrollPane
        accountScrollPanel.setPreferredSize(new Dimension(width-width/10*9, height));
        accountScrollPanel.setBackground(AppThemeColors.panelColor);
        accountScrollPanel.setLayout(new FlowLayout());

        accountScroll.setPreferredSize(new Dimension(100, 500));
        accountPanel.add(accountScroll, BorderLayout.CENTER);

        //Panels within the scrollable window that will hold various settings--------
        accountAgePanel.setBackground(AppThemeColors.SECONDARY);
        accountAgePanel.setLayout(new BoxLayout(accountAgePanel, BoxLayout.X_AXIS));
        accountAgePanel.setPreferredSize(new Dimension(getWidth()-getWidth()/10*7, getHeight()/15));

        //GeneralSettingsPanel1, age, weight and height settings
        JLabel ageLabel = new JLabel("Age: ");
        ageLabel.setPreferredSize(new Dimension(50, test2.getPreferredSize().height));

        JLabel weightLabel = new JLabel("Weight: ");
        weightLabel.setPreferredSize(new Dimension(50, test2.getPreferredSize().height));

        JLabel heightLabel = new JLabel("Height: ");
        heightLabel.setPreferredSize(new Dimension(50, test2.getPreferredSize().height));

        for (Integer i=0; i<200;i++){
            agesList.add(i);
        }

        JComboBox ageDropDown = new JComboBox(agesList.toArray(new Integer[0]));
        ageDropDown.setSelectedIndex(UserData.getUserAge());
        ageDropDown.setEditable(true);
        ageDropDown.setPreferredSize(new Dimension(width/15, height/15));
        ageDropDown.addItemListener(_ -> {
            UserData.setUserAge(ageDropDown.getSelectedIndex()); //Updates the local user age in the userdata
            try {
                FirebaseManager.writeDBUser(UserData.getEmail()); //Updates the user data on the database
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
            HomePanel.updateUserInfo(); //Updates the userdata on the home panel
        });

        JComboBox weightDropDown = new JComboBox(agesList.toArray(new Integer[0]));
        weightDropDown.setEditable(true);
        weightDropDown.setSelectedIndex((int)(UserData.getUserWeight()));
        weightDropDown.setPreferredSize(new Dimension(width/15, height/15));
        weightDropDown.addItemListener(_ -> {
            UserData.setUserWeight(weightDropDown.getSelectedIndex()); //Updates the local user age in the userdata
            try {
                FirebaseManager.writeDBUser(UserData.getEmail()); //Updates the user data on the database
                System.out.println(weightDropDown.getEditor().getItem().toString());
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
            HomePanel.updateUserInfo(); //Updates the userdata on the home panel
        });

        JComboBox heightDropDown = new JComboBox(agesList.toArray(new Integer[0]));
        heightDropDown.setSelectedIndex((int)(UserData.getUserHeight()));
        heightDropDown.setPreferredSize(new Dimension(width/15, height/15));
        heightDropDown.addItemListener(_ -> {
            UserData.setUserHeight(heightDropDown.getSelectedIndex()); //Updates the local user age in the userdata
            try {
                FirebaseManager.writeDBUser(UserData.getEmail()); //Updates the user data on the database
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
            HomePanel.updateUserInfo(); //Updates the userdata on the home panel
        });

        accountAgePanel.add(Box.createHorizontalGlue());
        accountAgePanel.add(ageLabel);
        accountAgePanel.add(Box.createHorizontalGlue());
        accountAgePanel.add(Box.createHorizontalGlue());
        accountAgePanel.add(Box.createHorizontalGlue());
        accountAgePanel.add(Box.createHorizontalGlue());
        accountAgePanel.add(Box.createHorizontalGlue());
        accountAgePanel.add(ageDropDown);

        accountWeightPanel.setBackground(AppThemeColors.SECONDARY);
        accountWeightPanel.setLayout(new BoxLayout(accountWeightPanel, BoxLayout.X_AXIS));
        accountWeightPanel.setPreferredSize(new Dimension(getWidth()-getWidth()/10*7, getHeight()/15));

        accountWeightPanel.add(Box.createHorizontalGlue());
        accountWeightPanel.add(weightLabel);
        accountWeightPanel.add(Box.createHorizontalGlue());
        accountWeightPanel.add(Box.createHorizontalGlue());
        accountWeightPanel.add(Box.createHorizontalGlue());
        accountWeightPanel.add(Box.createHorizontalGlue());
        accountWeightPanel.add(Box.createHorizontalGlue());
        accountWeightPanel.add(weightDropDown);

        JButton chooseProfilePictureButton = new JButton("Open Image File Chooser");
        chooseProfilePictureButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                // Skapa huvudfönstret för att visa filväljaren
                JFrame frame = new JFrame("Choose Image");
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setSize(400, 400);

                profilePicture = selectImage(frame);

                HomePanel.updateProfilePicture(profilePicture);
            }
        });




        accountHeightPanel.setBackground(AppThemeColors.SECONDARY);
        accountHeightPanel.setLayout(new BoxLayout(accountHeightPanel, BoxLayout.X_AXIS));
        accountHeightPanel.setPreferredSize(new Dimension(getWidth()-getWidth()/10*7, getHeight()/15));

        accountHeightPanel.add(Box.createHorizontalGlue());
        accountHeightPanel.add(heightLabel);
        accountHeightPanel.add(Box.createHorizontalGlue());
        accountHeightPanel.add(Box.createHorizontalGlue());
        accountHeightPanel.add(Box.createHorizontalGlue());
        accountHeightPanel.add(Box.createHorizontalGlue());
        accountHeightPanel.add(Box.createHorizontalGlue());
        accountHeightPanel.add(heightDropDown);


        changeUsernameField.setMinimumSize(new Dimension(width/11, height/20));
        changeUsernameField.setPreferredSize(new Dimension(width/11, height/20));
        changeUsernameField.setMaximumSize(new Dimension(width/11, height/20));
        changeUsernameField.setText(UserData.getUserName());

        changeUsernameField.addActionListener(_ -> {
            UserData.setUserName(changeUsernameField.getText());
            try {
                FirebaseManager.writeDBUser(UserData.getEmail()); //Updates the user data on the database
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
            HomePanel.updateUserInfo();
        });

        accountNamePanel.setBackground(AppThemeColors.SECONDARY);
        accountNamePanel.setLayout(new BoxLayout(accountNamePanel, BoxLayout.X_AXIS));
        accountNamePanel.setPreferredSize(new Dimension(width-width/10*9, height/10));

        accountNamePanel.add(changeUsernameLabel);
        accountNamePanel.add(Box.createHorizontalGlue());
        accountNamePanel.add(changeUsernameField);

        accountPFPPanel.setBackground(AppThemeColors.SECONDARY);
        accountPFPPanel.setPreferredSize(new Dimension(width-width/10*9, height/10));

        accountPFPPanel.add(chooseProfilePictureButton);


        accountScrollPanel.add(accountAgePanel);
        accountScrollPanel.add(accountWeightPanel);
        accountScrollPanel.add(accountHeightPanel);
        accountScrollPanel.add(accountNamePanel);
        accountScrollPanel.add(accountPFPPanel);
    }

    public void buildPrivacyPanel(){
        privacyPanel.setBackground(AppThemeColors.panelColor);
        privacyPanel.setVisible(false);
    }

    //TODO Need to fix so the buttons update colors properly
    public void updateColors(){

        settingsLabel.setForeground(AppThemeColors.foregroundColor);

        //General Settings
        generalSettingsButton.setIcon(scaledDarkGeneralSettingsIcon);
        generalSettingsButton.setForeground(AppThemeColors.foregroundColor);

        //General Settings panel
        generalScrollPanel.setBackground(AppThemeColors.panelColor);
        test2.setBackground(AppThemeColors.SECONDARY);
        test3.setBackground(AppThemeColors.SECONDARY);
        test4.setBackground(AppThemeColors.SECONDARY);
        test5.setBackground(AppThemeColors.SECONDARY);

        //Theme Settings
        themeSettingsButton.setIcon(scaledDarkThemeSettingsIcon);
        themeSettingsButton.setForeground(AppThemeColors.foregroundColor);

        //Theme Settings panel
        themeScrollPanel.setBackground(AppThemeColors.panelColor);
        themeSelectionPanel.setBackground(AppThemeColors.SECONDARY);
        themeSelectionLabel.setForeground(AppThemeColors.foregroundColor);
        test33.setBackground(AppThemeColors.SECONDARY);
        test44.setBackground(AppThemeColors.SECONDARY);
        test55.setBackground(AppThemeColors.SECONDARY);

        //Notifications Settings
        notificationsSettingsButton.setIcon(scaledDarkNotificationsSettingsIcon);
        notificationsSettingsButton.setForeground(AppThemeColors.foregroundColor);

        //Account Settings
        accountSettingsButton.setIcon(scaledDarkAccountSettingsIcon);
        accountSettingsButton.setForeground(AppThemeColors.foregroundColor);

        //Account Settings Panel
        accountScrollPanel.setBackground(AppThemeColors.panelColor);
        accountAgePanel.setBackground(AppThemeColors.SECONDARY);
        accountWeightPanel.setBackground(AppThemeColors.SECONDARY);
        accountHeightPanel.setBackground(AppThemeColors.SECONDARY);
        accountNamePanel.setBackground(AppThemeColors.SECONDARY);
        accountPFPPanel.setBackground(AppThemeColors.SECONDARY);

        //Privacy Settings
        privacySettingsButton.setIcon(scaledDarkPrivacySettingsIcon);
        privacySettingsButton.setForeground(AppThemeColors.foregroundColor);

        switch(currentTheme){
            case "dark"->{
                generalSettingsButton.setIcon(scaledGeneralSettingsIcon);
                themeSettingsButton.setIcon(scaledThemeSettingsIcon);
                notificationsSettingsButton.setIcon(scaledNotificationsSettingsIcon);
                accountSettingsButton.setIcon(scaledAccountSettingsIcon);
                privacySettingsButton.setIcon(scaledPrivacySettingsIcon);
            }
            case "light"->{
                generalSettingsButton.setIcon(scaledDarkGeneralSettingsIcon);
                themeSettingsButton.setIcon(scaledDarkThemeSettingsIcon);
                notificationsSettingsButton.setIcon(scaledDarkNotificationsSettingsIcon);
                accountSettingsButton.setIcon(scaledDarkAccountSettingsIcon);
                privacySettingsButton.setIcon(scaledDarkPrivacySettingsIcon);
            }
        }

        MenuPanel.instance.repaint();
        MenuPanel.instance.revalidate();

        TopBar.instance.repaint();
        TopBar.instance.revalidate();

        ProgramPanel.instance.repaint();
        ProgramPanel.instance.revalidate();

        CreateExerciseModule.instance.repaint();
        CreateExerciseModule.instance.revalidate();

        ApplicationWindow.updateBackground();

        repaint();
        revalidate();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        // Draw the image to fill the entire panel
        if (scaledSettingsPanelBackgroundIcon != null || lightScaledSettingsPanelBackgroundIcon != null) {

            if(!lightMode){
                g.drawImage(scaledSettingsPanelBackgroundIcon.getImage(), 0, 0, getWidth(), getHeight(), this);
            }else{
                g.drawImage(lightScaledSettingsPanelBackgroundIcon.getImage(), 0, 0, getWidth(), getHeight(), this);
            }
        }
        else{
            System.out.println("Error");
        }
    }

    public static ImageIcon selectImage(JFrame parentFrame) {
        // Skapa en JFileChooser
        JFileChooser fileChooser = new JFileChooser();

        // Sätt filtret så att endast bildfiler visas
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Image files", "jpg", "png", "gif", "bmp", "jpeg");
        fileChooser.setFileFilter(filter);

        // Öppna JFileChooser dialogen i ett nytt fönster
        int result = fileChooser.showOpenDialog(parentFrame);

        if (result == JFileChooser.APPROVE_OPTION) {
            // Hämta den valda filen
            File selectedFile = fileChooser.getSelectedFile();

            // Skapa en ImageIcon från filen
            return new ImageIcon(selectedFile.getAbsolutePath());
        }

        // Om ingen fil valdes eller användaren avbröt
        return null;
    }



}