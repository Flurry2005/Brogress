package se.aljr.application.settings;

import jnafilechooser.api.JnaFileChooser;
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
import se.aljr.application.programplanner.SearchPanel;
import se.aljr.application.settings.custom.SteelCheckBoxUI;


import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.plaf.basic.BasicButtonUI;


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
    ArrayList<Integer> agesList = (ArrayList<Integer>) IntStream.rangeClosed(0, 200).boxed().collect(Collectors.toList());
    String[] activityList = {"~0   ", "1-3", "3-5", "6-7", ">7"};
    ArrayList<String> themeList = new ArrayList<>(Arrays.asList("Light","Dark"));

    public static boolean lightMode = false;
    public static String currentTheme = "dark";

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
    JPanel rightRightPanel = new JPanel();

    //Button Menu
    JPanel settingsLabelPanel = new JPanel();
    JLabel settingsLabel = new JLabel("Settings");
    JPanel buttonPanel = new JPanel();

    //Search panel
    JPanel searchPanel = new JPanel();
    JPanel searchScrollPanel = new JPanel();
    JScrollPane searchScroll = new JScrollPane(searchScrollPanel, JScrollPane.VERTICAL_SCROLLBAR_NEVER, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
    JPanel searchFieldPanel = new JPanel();
    JTextField searchField = new JTextField();
    JPanel searchedPanels = new JPanel();
    private ArrayList<JPanel> allPanels = new ArrayList<>();

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
    JComboBox themeDropDown = new JComboBox(themeList.toArray());

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
    JScrollPane accountScroll = new JScrollPane(accountScrollPanel, JScrollPane.VERTICAL_SCROLLBAR_NEVER, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

    JPanel accountAgePanel = new JPanel();
    JPanel accountWeightPanel = new JPanel();
    JPanel accountHeightPanel = new JPanel();
    JPanel accountActivityPanel = new JPanel();
    JPanel accountNamePanel = new JPanel();

    JPanel ageLabelCenteringPanel = new JPanel();
    JPanel weightLabelCenteringPanel = new JPanel();
    JPanel heightLabelCenteringPanel = new JPanel();
    JPanel activityLabelCenteringPanel = new JPanel();
    JPanel usernameLabelCenteringPanel = new JPanel();
    JPanel pfpLabelCenteringPanel = new JPanel();

    JLabel ageLabel = new JLabel("Age: ");
    JLabel weightLabel = new JLabel("Weight: ");
    JLabel heightLabel = new JLabel("Height: ");
    JLabel accountActivityLabel = new JLabel("Workouts/week");
    JLabel changeUsernameLabel= new JLabel("Change Username");
    JLabel choosePFPLabel = new JLabel("Avatar");

    JTextField changeUsernameField = new JTextField();
    JPanel accountPFPPanel = new JPanel();


    JTextField changeUsernameFieldD = new JTextField();

    JComboBox ageDropDown = new JComboBox(agesList.toArray(new Integer[0]));
    JComboBox weightDropDown = new JComboBox(agesList.toArray(new Integer[0]));
    JComboBox heightDropDown = new JComboBox(agesList.toArray(new Integer[0]));
    JComboBox activityDropDown = new JComboBox(activityList);

    JComboBox ageDropDownD = new JComboBox(agesList.toArray(new Integer[0]));
    JComboBox weightDropDownD = new JComboBox(agesList.toArray(new Integer[0]));
    JComboBox heightDropDownD = new JComboBox(agesList.toArray(new Integer[0]));

    //Privacy Panel
    JPanel privacyPanel = new JPanel();

    //Duplicate Panels-----------------------------------------------------------------
    //Theme panels
    JPanel themeSelectionPanelD = new JPanel();
    JLabel themeSelectionLabelD = new JLabel("Select theme");
    JComboBox themeDropDownD = new JComboBox(themeList.toArray());

    //Account panels
    JPanel accountAgePanelD = new JPanel();
    JPanel accountWeightPanelD = new JPanel();
    JPanel accountHeightPanelD = new JPanel();
    JPanel accountNamePanelD = new JPanel();
    JPanel accountPFPPanelD = new JPanel();

    JPanel ageLabelCenteringPanelD = new JPanel();
    JPanel weightLabelCenteringPanelD = new JPanel();
    JPanel heightLabelCenteringPanelD = new JPanel();
    JPanel activityLabelCenteringPanelD = new JPanel();
    JPanel usernameLabelCenteringPanelD = new JPanel();
    JPanel pfpLabelCenteringPanelD = new JPanel();

    JLabel ageLabelD = new JLabel("Age: ");
    JLabel weightLabelD = new JLabel("Weight: ");
    JLabel heightLabelD = new JLabel("Height: ");
    JLabel changeUsernameLabelD = new JLabel("Change Username");

    private static SettingsPanel instance;

    public static int currentPage = 0;

    public int width;
    public int height;


    public SettingsPanel(int width, int height){
        this.setPreferredSize(new Dimension(width, height));
        this.width = width;
        this.height = height;

        instance = this;

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

        System.out.println("WIDDDDDDTHHHTHTHTHTHTHT:" + width);
        System.out.println("HAIGHTHTHTHTHTHTHTHTHTH:" + height);
        buildBackgroundPanels();
        buildButtonPanel();
        buildButtons();

        buildSearchPanel();
        buildGeneralPanel();
        buildThemePanel();
        buildNotificationsPanel();
        buildAccountPanel();
        buildPrivacyPanel();

        buildDuplicatePanels();

        this.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                SwingUtilities.invokeLater(()->{

                    //Resize background panels
                    leftPanel.setPreferredSize(new Dimension(getWidth()/4, getHeight()));
                    buttonPanel.setPreferredSize(new Dimension(getWidth()/4,getHeight()));

                    rightPanel.setPreferredSize(new Dimension(getWidth()-getWidth()/10*7, getHeight()));
                    rightRightPanel.setPreferredSize(new Dimension(getWidth()-getWidth()/10*7, getHeight()));

                    settingsLabelPanel.setPreferredSize(new Dimension(getWidth(), getHeight()/9));

                    settingsLabel.setFont(new Font("Arial", Font.PLAIN,getHeight()/15));
                    settingsLabel.setPreferredSize(new Dimension(getWidth(), getHeight()/9));

                    rightPanel.setBorder(BorderFactory.createMatteBorder(getHeight()/20, 0, getHeight()/20, 0, AppThemeColors.PRIMARY));
                    rightRightPanel.setBorder(BorderFactory.createMatteBorder(getHeight()/20, 0, getHeight()/20, 0, AppThemeColors.PRIMARY));



                    generalScroll.setPreferredSize(new Dimension(getWidth()-getWidth()/2, getHeight()));



                    //Resize search window panels-------------------------------------------------------------------------------------------------------
                    searchScroll.setPreferredSize(new Dimension(getWidth()-getWidth()/2, getHeight()));
                    searchFieldPanel.setPreferredSize(new Dimension(getWidth()-getWidth()/10*7, getHeight()/15));
                    searchField.setPreferredSize(new Dimension(getWidth()-getWidth()/10*7, getHeight()/15));
                    searchedPanels.setPreferredSize(new Dimension(getWidth()-getWidth()/10*7, getHeight()));

                    //theme selection panel
                    themeSelectionPanelD.setPreferredSize(new Dimension(getWidth()-getWidth()/10*7, getHeight()/15));

                    //Account selection panel
                    accountAgePanelD.setPreferredSize(new Dimension(getWidth()-getWidth()/10*7, getHeight()/15));
                    accountWeightPanelD.setPreferredSize(new Dimension(getWidth()-getWidth()/10*7, getHeight()/15));
                    accountHeightPanelD.setPreferredSize(new Dimension(getWidth()-getWidth()/10*7, getHeight()/15));
                    accountNamePanelD.setPreferredSize(new Dimension(getWidth()-getWidth()/10*7, getHeight()/15));

                    ageLabelCenteringPanelD.setPreferredSize(new Dimension(getWidth()/7, accountAgePanelD.getPreferredSize().height));
                    weightLabelCenteringPanelD.setPreferredSize(new Dimension(getWidth()/7, accountWeightPanelD.getPreferredSize().height));
                    heightLabelCenteringPanelD.setPreferredSize(new Dimension(getWidth()/7, accountHeightPanelD.getPreferredSize().height));
                    activityLabelCenteringPanelD.setPreferredSize(new Dimension(getWidth()/7, accountAgePanel.getPreferredSize().height));
                    usernameLabelCenteringPanelD.setPreferredSize(new Dimension(getWidth()/7, accountNamePanelD.getPreferredSize().height));
                    pfpLabelCenteringPanelD.setPreferredSize(new Dimension(getWidth()/7, accountAgePanel.getPreferredSize().height));

                    ageLabelD.setFont(new Font("Arial", Font.BOLD,getHeight()/50));
                    ageLabelD.setPreferredSize(new Dimension(getWidth()/20, accountAgePanelD.getPreferredSize().height));
                    weightLabelD.setFont(new Font("Arial", Font.BOLD,getHeight()/50));
                    weightLabelD.setPreferredSize(new Dimension(getWidth()/20, accountWeightPanel.getPreferredSize().height));
                    heightLabelD.setFont(new Font("Arial", Font.BOLD,getHeight()/50));
                    heightLabelD.setPreferredSize(new Dimension(getWidth()/20, accountHeightPanelD.getPreferredSize().height));

                    changeUsernameLabelD.setFont(new Font("Arial", Font.BOLD,getHeight()/50));
                    changeUsernameLabelD.setPreferredSize(new Dimension(getWidth()/8, accountNamePanelD.getPreferredSize().height));

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

                    //SteelCheckBoxUI.SIZE = new Dimension(getWidth()/20, getWidth()/40);
                    //themeSelectionPanel.setPreferredSize(new Dimension(getWidth()-getWidth()/10*7, getHeight()/15));

                    test33.setPreferredSize(new Dimension(getWidth()-getWidth()/10*7, getHeight()/10));
                    test44.setPreferredSize(new Dimension(getWidth()-getWidth()/10*7, getHeight()/10));
                    test55.setPreferredSize(new Dimension(getWidth()-getWidth()/10*7, getHeight()/10));

                    //Resize theme panel panels
                    themeSelectionPanel.setPreferredSize(new Dimension(getWidth()-getWidth()/10*7, getHeight()/15));

                    //Resize account panel panels
                    accountAgePanel.setPreferredSize(new Dimension(getWidth()-getWidth()/10*7, getHeight()/15));
                    accountWeightPanel.setPreferredSize(new Dimension(getWidth()-getWidth()/10*7, getHeight()/15));
                    accountHeightPanel.setPreferredSize(new Dimension(getWidth()-getWidth()/10*7, getHeight()/15));
                    accountActivityPanel.setPreferredSize(new Dimension(getWidth()-getWidth()/10*7, getHeight()/15));
                    accountNamePanel.setPreferredSize(new Dimension(getWidth()-getWidth()/10*7, getHeight()/15));

                    ageLabelCenteringPanel.setPreferredSize(new Dimension(getWidth()/7, accountAgePanel.getPreferredSize().height));
                    weightLabelCenteringPanel.setPreferredSize(new Dimension(getWidth()/7, accountAgePanel.getPreferredSize().height));
                    heightLabelCenteringPanel.setPreferredSize(new Dimension(getWidth()/7, accountAgePanel.getPreferredSize().height));
                    activityLabelCenteringPanel.setPreferredSize(new Dimension(getWidth()/7, accountAgePanel.getPreferredSize().height));
                    usernameLabelCenteringPanel.setPreferredSize(new Dimension(getWidth()/7, accountAgePanel.getPreferredSize().height));
                    pfpLabelCenteringPanel.setPreferredSize(new Dimension(getWidth()/7, accountAgePanel.getPreferredSize().height));

                    ageDropDown.setFont(new Font("Arial", Font.BOLD,getHeight()/50));
                    ageLabel.setFont(new Font("Arial", Font.BOLD,getHeight()/50));
                    ageLabel.setPreferredSize(new Dimension(getWidth()/20, accountWeightPanel.getPreferredSize().height));
                    weightDropDown.setFont(new Font("Arial", Font.BOLD,getHeight()/50));
                    weightLabel.setFont(new Font("Arial", Font.BOLD,getHeight()/50));
                    weightLabel.setPreferredSize(new Dimension(getWidth()/20, accountWeightPanel.getPreferredSize().height));
                    heightDropDown.setFont(new Font("Arial", Font.BOLD,getHeight()/50));
                    heightLabel.setFont(new Font("Arial", Font.BOLD,getHeight()/50));
                    heightLabel.setPreferredSize(new Dimension(getWidth()/20, accountWeightPanel.getPreferredSize().height));
                    activityDropDown.setFont(new Font("Arial", Font.BOLD,getHeight()/50));
                    accountActivityLabel.setFont(new Font("Arial", Font.BOLD,getHeight()/50));
                    accountActivityLabel.setPreferredSize(new Dimension(getWidth()/10, accountActivityPanel.getPreferredSize().height));
                    changeUsernameLabel.setFont(new Font("Arial", Font.BOLD,getHeight()/50));
                    changeUsernameLabel.setPreferredSize(new Dimension(getWidth()/8, accountWeightPanel.getPreferredSize().height));
                    changeUsernameField.setFont(new Font("Arial", Font.BOLD,getHeight()/50));
                    choosePFPLabel.setFont(new Font("Arial", Font.BOLD,getHeight()/50));
                    choosePFPLabel.setPreferredSize(new Dimension(getWidth()/20, accountWeightPanel.getPreferredSize().height));

                    changeUsernameField.setMinimumSize(new Dimension(getWidth()/1000*104, getHeight()/20));
                    changeUsernameField.setPreferredSize(new Dimension(getWidth()/1000*104, getHeight()/20));
                    changeUsernameField.setMaximumSize(new Dimension(getWidth()/1000*104, getHeight()/20));


                    accountPFPPanel.setPreferredSize(new Dimension(getWidth()-getWidth()/10*7, getHeight()/15));

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

        rightRightPanel.setOpaque(true);
        rightRightPanel.setLayout(new BoxLayout(rightRightPanel, BoxLayout.X_AXIS));
        rightRightPanel.setPreferredSize(new Dimension(width-width/10*7, height));
        rightRightPanel.setBorder(BorderFactory.createMatteBorder(height/20,0,height/20,0,AppThemeColors.PRIMARY));
        rightRightPanel.setBackground(new Color(30,30,30));

        this.add(containerPanel);
        containerPanel.add(leftPanel);
        containerPanel.add(rightPanel);
        containerPanel.add(Box.createHorizontalGlue());
        containerPanel.add(Box.createHorizontalGlue());
        containerPanel.add(rightRightPanel);
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

    public void buildSearchPanel(){
        //Search Panel
        searchPanel.setBackground(AppThemeColors.panelColor);
        searchPanel.setPreferredSize(new Dimension(width/2, height));
        searchPanel.setVisible(true);
        searchPanel.setLayout(new BoxLayout(searchPanel, BoxLayout.X_AXIS));
        rightRightPanel.add(searchPanel);

        //Panel within the scrollPane
        searchScrollPanel.setPreferredSize(new Dimension(width-width/10*7, height));
        searchScrollPanel.setBackground(AppThemeColors.panelColor);
        searchScrollPanel.setLayout(new FlowLayout());
        searchPanel.add(searchScroll, BorderLayout.CENTER);

        //Panel to hold the searchfield
        searchFieldPanel.setOpaque(true);
        searchFieldPanel.setBackground(Color.GREEN);
        searchFieldPanel.setPreferredSize(new Dimension(width-width/10*7, height/15));
        searchScrollPanel.add(searchFieldPanel);

        searchedPanels.setPreferredSize(new Dimension(width-width/10*7, height));
        searchedPanels.setBackground(AppThemeColors.panelColor);
        searchedPanels.setLayout(new FlowLayout());
        searchScrollPanel.add(searchedPanels);

        //Search field
        searchField.setPreferredSize(new Dimension(width-width/10*7, height/15));
        searchField.setHorizontalAlignment(JTextField.CENTER);
        searchField.setText("Search...");
        searchField.setFont(new Font("Arial", Font.PLAIN, height/40));
        searchField.setForeground(Color.BLACK);

        searchFieldPanel.add(searchField);

        searchField.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (searchField.getText().equals("Search...")) {
                    searchField.setText("");
                    searchField.setFont(new Font("Arial", Font.PLAIN, height/40));
                    searchField.setForeground(Color.BLACK);
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (searchField.getText().isEmpty()) {
                    searchField.setText("Search...");
                    searchField.setFont(new Font("Arial", Font.PLAIN, height/40));
                    searchField.setForeground(Color.BLACK);
                }
            }
        });
        searchField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
                    unfocusSearchField();
                }
            }
        });

        searchField.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                filterPanels();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                filterPanels();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                filterPanels();
            }

            private void filterPanels(){
                String query = searchField.getText().toLowerCase();
                searchedPanels.removeAll();
                for (JPanel panel : allPanels) {
                    if (panel.getName() != null && !searchField.getText().isEmpty() && panel.getName().toLowerCase().contains(query)) {
                        searchedPanels.add(panel);
                    }
                }
                searchedPanels.revalidate();
                searchedPanels.repaint();
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

        themeDropDown.setPreferredSize(new Dimension(width/15, height/15));
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
                        if(themeDropDownD.getSelectedIndex()!=0){
                            themeDropDownD.setSelectedIndex(0);
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
                        if(themeDropDownD.getSelectedIndex()!=1){
                            themeDropDownD.setSelectedIndex(1);
                        }
                    }
                }

                rightPanel.setBorder(BorderFactory.createMatteBorder(getHeight()/20, 0, getHeight()/20, 0, AppThemeColors.PRIMARY));
                rightRightPanel.setBorder(BorderFactory.createMatteBorder(getHeight()/20, 0, getHeight()/20, 0, AppThemeColors.PRIMARY));
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

        //Account settings panels, age, weight and height settings
        accountAgePanel.setBackground(AppThemeColors.SECONDARY);
        accountAgePanel.setLayout(new BorderLayout());
        accountAgePanel.setPreferredSize(new Dimension(getWidth()-getWidth()/10*7, getHeight()/15));

        ageLabelCenteringPanel.setBackground(Color.green);
        ageLabelCenteringPanel.setLayout(new BoxLayout(ageLabelCenteringPanel, BoxLayout.X_AXIS));
        ageLabelCenteringPanel.setPreferredSize(new Dimension(width/7, accountAgePanel.getPreferredSize().height));
        ageLabelCenteringPanel.setOpaque(false);

        ageLabel.setPreferredSize(new Dimension(width/20, accountAgePanel.getPreferredSize().height));
        ageLabel.setFont(new Font("Arial", Font.BOLD,height/50));

        ageDropDown.setSelectedIndex(UserData.getUserAge());
        ageDropDown.setEditable(true);
        ageDropDown.setPreferredSize(new Dimension(width/15, height/15));
        ageDropDown.setFont(new Font("Arial", Font.BOLD,height/50));
        ageDropDown.addItemListener(_ -> {
            UserData.setUserAge(ageDropDown.getSelectedIndex()); //Updates the local user age in the userdata
            try {
                FirebaseManager.writeDBUser(UserData.getEmail()); //Updates the user data on the database
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
            if(ageDropDownD.getSelectedIndex()!=ageDropDown.getSelectedIndex()){
                ageDropDownD.setSelectedIndex(ageDropDown.getSelectedIndex());
            }
            HomePanel.updateUserInfo(); //Updates the userdata on the home panel
        });


        accountWeightPanel.setBackground(AppThemeColors.SECONDARY);
        accountWeightPanel.setLayout(new BorderLayout());
        accountWeightPanel.setPreferredSize(new Dimension(getWidth()-getWidth()/10*7, getHeight()/15));

        weightLabelCenteringPanel.setBackground(Color.green);
        weightLabelCenteringPanel.setLayout(new BoxLayout(weightLabelCenteringPanel, BoxLayout.X_AXIS));
        weightLabelCenteringPanel.setPreferredSize(new Dimension(width/7, accountWeightPanel.getPreferredSize().height));
        weightLabelCenteringPanel.setOpaque(false);

        weightLabel.setPreferredSize(new Dimension(width/20, accountWeightPanel.getPreferredSize().height));
        weightLabel.setFont(new Font("Arial", Font.BOLD,height/50));

        weightDropDown.setEditable(true);
        weightDropDown.setSelectedIndex((int)(UserData.getUserWeight()));
        weightDropDown.setPreferredSize(new Dimension(width/15, height/15));
        weightDropDown.setFont(new Font("Arial", Font.BOLD,height/50));
        weightDropDown.addItemListener(_ -> {
            UserData.setUserWeight(weightDropDown.getSelectedIndex()); //Updates the local user age in the userdata
            try {
                FirebaseManager.writeDBUser(UserData.getEmail()); //Updates the user data on the database
                System.out.println(weightDropDown.getEditor().getItem().toString());
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
            if(weightDropDownD.getSelectedIndex()!=weightDropDown.getSelectedIndex()){
                weightDropDownD.setSelectedIndex(weightDropDown.getSelectedIndex());
            }
            HomePanel.updateUserInfo(); //Updates the userdata on the home panel
        });


        accountHeightPanel.setBackground(AppThemeColors.SECONDARY);
        accountHeightPanel.setLayout(new BorderLayout());
        accountHeightPanel.setPreferredSize(new Dimension(getWidth()-getWidth()/10*7, getHeight()/15));

        heightLabelCenteringPanel.setBackground(Color.green);
        heightLabelCenteringPanel.setLayout(new BoxLayout(heightLabelCenteringPanel, BoxLayout.X_AXIS));
        heightLabelCenteringPanel.setPreferredSize(new Dimension(width/7, accountHeightPanel.getPreferredSize().height));
        heightLabelCenteringPanel.setOpaque(false);

        heightLabel.setPreferredSize(new Dimension(width/20, accountHeightPanel.getPreferredSize().height));
        heightLabel.setFont(new Font("Arial", Font.BOLD,height/50));

        heightDropDown.setSelectedIndex((int)(UserData.getUserHeight()));
        heightDropDown.setEditable(true);
        heightDropDown.setPreferredSize(new Dimension(width/15, height/15));
        heightDropDown.setFont(new Font("Arial", Font.BOLD,height/50));
        heightDropDown.addItemListener(_ -> {
            UserData.setUserHeight(heightDropDown.getSelectedIndex()); //Updates the local user age in the userdata
            try {
                FirebaseManager.writeDBUser(UserData.getEmail()); //Updates the user data on the database
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
            if(heightDropDownD.getSelectedIndex()!=heightDropDown.getSelectedIndex()){
                heightDropDownD.setSelectedIndex(heightDropDown.getSelectedIndex());
            }
            HomePanel.updateUserInfo(); //Updates the userdata on the home panel
        });

        accountActivityPanel.setBackground(AppThemeColors.SECONDARY);
        accountActivityPanel.setLayout(new BorderLayout());
        accountActivityPanel.setPreferredSize(new Dimension(getWidth()-getWidth()/10*7, getHeight()/15));

        activityLabelCenteringPanel.setBackground(Color.green);
        activityLabelCenteringPanel.setLayout(new BoxLayout(activityLabelCenteringPanel, BoxLayout.X_AXIS));
        activityLabelCenteringPanel.setPreferredSize(new Dimension(width/7, accountActivityPanel.getPreferredSize().height));
        activityLabelCenteringPanel.setOpaque(false);

        accountActivityLabel.setPreferredSize(new Dimension(width/10, accountActivityPanel.getPreferredSize().height));
        accountActivityLabel.setFont(new Font("Arial", Font.BOLD,height/50));

        activityDropDown.setFont(new Font("Arial", Font.BOLD,height/50));
        Float[] temp = {1.2f, 1.375f, 1.55f, 1.725f, 1.9f};
        activityDropDown.setSelectedIndex(Arrays.stream(temp).toList().indexOf(UserData.getActivityFactor()));
        activityDropDown.addItemListener(_ -> {
            int index = activityDropDown.getSelectedIndex();
            switch (index){
                case 0->UserData.setActivityFactor(1.2f);
                case 1->UserData.setActivityFactor(1.375f);
                case 2->UserData.setActivityFactor(1.55f);
                case 3->UserData.setActivityFactor(1.725f);
                case 4->UserData.setActivityFactor(1.9f);
            }
            try {
                FirebaseManager.writeDBUser(UserData.getEmail());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            HomePanel.updateUserInfo();
        });

        accountAgePanel.add(ageLabelCenteringPanel, BorderLayout.WEST);
        ageLabelCenteringPanel.add(Box.createHorizontalGlue());
        ageLabelCenteringPanel.add(ageLabel);
        ageLabelCenteringPanel.add(Box.createHorizontalGlue());
        accountAgePanel.add(ageDropDown, BorderLayout.EAST);


        accountWeightPanel.add(weightLabelCenteringPanel, BorderLayout.WEST);
        weightLabelCenteringPanel.add(Box.createHorizontalGlue());
        weightLabelCenteringPanel.add(weightLabel);
        weightLabelCenteringPanel.add(Box.createHorizontalGlue());
        accountWeightPanel.add(weightDropDown, BorderLayout.EAST);


        accountHeightPanel.add(heightLabelCenteringPanel, BorderLayout.WEST);
        heightLabelCenteringPanel.add(Box.createHorizontalGlue());
        heightLabelCenteringPanel.add(heightLabel);
        heightLabelCenteringPanel.add(Box.createHorizontalGlue());
        accountHeightPanel.add(heightDropDown, BorderLayout.EAST);


        accountActivityPanel.add(activityLabelCenteringPanel, BorderLayout.WEST);
        activityLabelCenteringPanel.add(Box.createHorizontalGlue());
        activityLabelCenteringPanel.add(accountActivityLabel);
        activityLabelCenteringPanel.add(Box.createHorizontalGlue());
        accountActivityPanel.add(activityDropDown, BorderLayout.EAST);

        accountPFPPanel.setBackground(AppThemeColors.SECONDARY);
        accountPFPPanel.setLayout(new BorderLayout());
        accountPFPPanel.setPreferredSize(new Dimension(width-width/10*9, height/10));

        pfpLabelCenteringPanel.setBackground(Color.green);
        pfpLabelCenteringPanel.setLayout(new BoxLayout(pfpLabelCenteringPanel, BoxLayout.X_AXIS));
        pfpLabelCenteringPanel.setPreferredSize(new Dimension(width/7, accountActivityPanel.getPreferredSize().height));
        pfpLabelCenteringPanel.setOpaque(false);

        choosePFPLabel.setPreferredSize(new Dimension(width/20, accountPFPPanel.getPreferredSize().height));
        choosePFPLabel.setFont(new Font("Arial", Font.BOLD,height/50));

        JButton chooseProfilePictureButton = new JButton("Choose Avatar");
        chooseProfilePictureButton.setPreferredSize(new Dimension(width/1000*120, height/24));
        chooseProfilePictureButton.setMinimumSize(chooseProfilePictureButton.getPreferredSize());
        chooseProfilePictureButton.setMaximumSize(chooseProfilePictureButton.getPreferredSize());
        chooseProfilePictureButton.setUI(new BasicButtonUI() {
            @Override
            public void paint(Graphics g, JComponent c) {
                // Måla bakgrunden med rundade hörn
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(new Color(70, 208, 71)); // Grön färg
                g2.fillRoundRect(0, 0, c.getWidth(), c.getHeight(), instance.getHeight()/60, instance.getHeight()/60); // Rundade hörn

                // Måla texten (den kommer att målas av Swing, så vi ser till att inte skriva över den)
                super.paint(g, c);

                g2.dispose(); // Frigör Graphics2D
            }
            @Override
            protected void paintButtonPressed(Graphics g, AbstractButton b) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(new Color(64, 136, 65)); // Pressed button color
                g2.fillRoundRect(0, 0, b.getWidth(), b.getHeight(), instance.getHeight()/60, instance.getHeight()/60); // Rounded corners
                g2.dispose();
            }
        });
        chooseProfilePictureButton.setFocusPainted(false);
        chooseProfilePictureButton.setFocusable(false);
        chooseProfilePictureButton.setBorderPainted(false);
        chooseProfilePictureButton.setContentAreaFilled(false);
        chooseProfilePictureButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                // Skapa huvudfönstret för att visa filväljaren
                JFrame frame = new JFrame("Select an Image");
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setSize(400, 400);

                profilePicture = selectImage();

                if(profilePicture!=null){HomePanel.updateProfilePicture(profilePicture);}
            }
        });



        accountNamePanel.setBackground(AppThemeColors.SECONDARY);
        accountNamePanel.setLayout(new BorderLayout());
        accountNamePanel.setPreferredSize(new Dimension(width-width/10*9, height/10));

        usernameLabelCenteringPanel.setBackground(Color.green);
        usernameLabelCenteringPanel.setLayout(new BoxLayout(usernameLabelCenteringPanel, BoxLayout.X_AXIS));
        usernameLabelCenteringPanel.setPreferredSize(new Dimension(width/7, accountActivityPanel.getPreferredSize().height));
        usernameLabelCenteringPanel.setOpaque(false);

        changeUsernameLabel.setPreferredSize(new Dimension(width/8, accountWeightPanel.getPreferredSize().height));
        changeUsernameLabel.setFont(new Font("Arial", Font.BOLD,height/50));

        changeUsernameField.setMinimumSize(new Dimension(width/1000*104, height/20));
        changeUsernameField.setPreferredSize(new Dimension(width/1000*104, height/20));
        changeUsernameField.setMaximumSize(new Dimension(width/1000*104, height/20));
        changeUsernameField.setFont(new Font("Arial", Font.BOLD,height/50));
        changeUsernameField.setText(UserData.getUserName());

        changeUsernameField.addActionListener(_ -> {
            UserData.setUserName(changeUsernameField.getText());
            try {
                FirebaseManager.writeDBUser(UserData.getEmail()); //Updates the user data on the database
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
            if(!changeUsernameFieldD.getText().equals(changeUsernameField.getText())){
                changeUsernameFieldD.setText(changeUsernameField.getText());
            }
            HomePanel.updateUserInfo();
        });


        accountNamePanel.add(usernameLabelCenteringPanel, BorderLayout.WEST);
        usernameLabelCenteringPanel.add(Box.createHorizontalGlue());
        usernameLabelCenteringPanel.add(changeUsernameLabel);
        usernameLabelCenteringPanel.add(Box.createHorizontalGlue());
        accountNamePanel.add(changeUsernameField, BorderLayout.EAST);


        accountPFPPanel.add(pfpLabelCenteringPanel, BorderLayout.WEST);
        pfpLabelCenteringPanel.add(Box.createHorizontalGlue());
        pfpLabelCenteringPanel.add(choosePFPLabel);
        pfpLabelCenteringPanel.add(Box.createHorizontalGlue());
        accountPFPPanel.add(chooseProfilePictureButton, BorderLayout.EAST);

        accountScrollPanel.add(accountAgePanel);
        accountScrollPanel.add(accountWeightPanel);
        accountScrollPanel.add(accountHeightPanel);
        accountScrollPanel.add(accountActivityPanel);
        accountScrollPanel.add(accountNamePanel);
        accountScrollPanel.add(accountPFPPanel);
    }

    public void buildPrivacyPanel(){
        privacyPanel.setBackground(AppThemeColors.panelColor);
        privacyPanel.setVisible(false);
    }

    public void buildDuplicatePanels(){
        //Theme selection panel-----------------------------------------------------------------------------------------------------


        //Panels within the scrollable window that will hold various settings
        themeSelectionPanelD.setBackground(AppThemeColors.SECONDARY);
        themeSelectionPanelD.setLayout(new BoxLayout(themeSelectionPanelD, BoxLayout.X_AXIS));
        themeSelectionPanelD.setPreferredSize(new Dimension(width-width/10*7, height/15));
        themeSelectionPanelD.setName("themeselectionpanel");
        themeSelectionLabelD.setForeground(AppThemeColors.foregroundColor);


        themeDropDownD.setPreferredSize(new Dimension(width/15, height/10));
        themeDropDownD.addItemListener(_-> {
            switch (themeDropDownD.getSelectedIndex()){
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
                    if(themeDropDown.getSelectedIndex()!=0){
                        themeDropDown.setSelectedIndex(0);
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
                    if(themeDropDown.getSelectedIndex()!=1){
                        themeDropDown.setSelectedIndex(1);
                    }
                }
            }

            rightPanel.setBorder(BorderFactory.createMatteBorder(getHeight()/20, 0, getHeight()/20, 0, AppThemeColors.PRIMARY));
            rightRightPanel.setBorder(BorderFactory.createMatteBorder(getHeight()/20, 0, getHeight()/20, 0, AppThemeColors.PRIMARY));
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

        themeSelectionPanelD.add(Box.createHorizontalGlue());
        themeSelectionPanelD.add(themeSelectionLabelD);
        themeSelectionPanelD.add(Box.createHorizontalGlue());
        themeSelectionPanelD.add(Box.createHorizontalGlue());
        themeSelectionPanelD.add(Box.createHorizontalGlue());
        themeSelectionPanelD.add(Box.createHorizontalGlue());
        themeSelectionPanelD.add(Box.createHorizontalGlue());
        themeSelectionPanelD.add(themeDropDownD);

        themeDropDownD.setSelectedIndex(-1);
        if(lightMode){
            themeDropDownD.setSelectedIndex(0);
        }else{
            themeDropDownD.setSelectedIndex(1);
        }
        allPanels.add(themeSelectionPanelD);
        //--------------------------------------------------------------------------------------------------------------------------------------

        //Account Settings Panels---------------------------------------------------------------------------------------------------------------

        //Panels within the scrollable window that will hold various settings--------

        accountAgePanelD.setBackground(AppThemeColors.SECONDARY);
        accountAgePanelD.setLayout(new BorderLayout());
        accountAgePanelD.setPreferredSize(new Dimension(width-width/10*7, height/15));
        accountAgePanelD.setName("changeagepanelchange age");

        ageLabelCenteringPanelD.setBackground(Color.green);
        ageLabelCenteringPanelD.setLayout(new BoxLayout(ageLabelCenteringPanelD, BoxLayout.X_AXIS));
        ageLabelCenteringPanelD.setPreferredSize(new Dimension(width/7, accountAgePanel.getPreferredSize().height));
        ageLabelCenteringPanelD.setOpaque(false);

        //GeneralSettingsPanel1, age, weight and height settings
        ageLabelD.setPreferredSize(new Dimension(width/20, accountAgePanelD.getPreferredSize().height));
        ageLabelD.setFont(new Font("Arial", Font.BOLD,height/50));

        ageDropDownD.setSelectedIndex(UserData.getUserAge());
        ageDropDownD.setEditable(true);
        ageDropDownD.setPreferredSize(new Dimension(width/15, height/15));
        ageDropDownD.addItemListener(_ -> {
            UserData.setUserAge(ageDropDownD.getSelectedIndex()); //Updates the local user age in the userdata
            try {
                FirebaseManager.writeDBUser(UserData.getEmail()); //Updates the user data on the database
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
            if(ageDropDown.getSelectedIndex()!=ageDropDownD.getSelectedIndex()){
                ageDropDown.setSelectedIndex(ageDropDownD.getSelectedIndex());
            }
            HomePanel.updateUserInfo(); //Updates the userdata on the home panel
        });


        accountWeightPanelD.setBackground(AppThemeColors.SECONDARY);
        accountWeightPanelD.setLayout(new BorderLayout());
        accountWeightPanelD.setPreferredSize(new Dimension(width-width/10*7, height/15));
        accountWeightPanelD.setName("changeweightpanelchange weight");

        weightLabelCenteringPanelD.setBackground(Color.green);
        weightLabelCenteringPanelD.setLayout(new BoxLayout(weightLabelCenteringPanelD, BoxLayout.X_AXIS));
        weightLabelCenteringPanelD.setPreferredSize(new Dimension(width/7, accountWeightPanelD.getPreferredSize().height));
        weightLabelCenteringPanelD.setOpaque(false);

        weightLabelD.setPreferredSize(new Dimension(width/20, accountWeightPanel.getPreferredSize().height));
        weightLabelD.setFont(new Font("Arial", Font.BOLD,height/50));

        weightDropDownD.setEditable(true);
        weightDropDownD.setSelectedIndex((int)(UserData.getUserWeight()));
        weightDropDownD.setPreferredSize(new Dimension(width/15, height/15));
        weightDropDownD.addItemListener(_ -> {
            UserData.setUserWeight(weightDropDownD.getSelectedIndex()); //Updates the local user age in the userdata
            try {
                FirebaseManager.writeDBUser(UserData.getEmail()); //Updates the user data on the database
                System.out.println(weightDropDownD.getEditor().getItem().toString());
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
            if(weightDropDown.getSelectedIndex()!=weightDropDownD.getSelectedIndex()){
                weightDropDown.setSelectedIndex(weightDropDownD.getSelectedIndex());
            }
            HomePanel.updateUserInfo(); //Updates the userdata on the home panel
        });

        accountHeightPanelD.setBackground(AppThemeColors.SECONDARY);
        accountHeightPanelD.setLayout(new BorderLayout());
        accountHeightPanelD.setPreferredSize(new Dimension(width-width/10*7, height/15));
        accountHeightPanelD.setName("changeheightpanelchange height");

        heightLabelCenteringPanelD.setBackground(Color.green);
        heightLabelCenteringPanelD.setLayout(new BoxLayout(heightLabelCenteringPanelD, BoxLayout.X_AXIS));
        heightLabelCenteringPanelD.setPreferredSize(new Dimension(width/7, accountHeightPanel.getPreferredSize().height));
        heightLabelCenteringPanelD.setOpaque(false);

        heightLabelD.setPreferredSize(new Dimension(width/20, accountHeightPanelD.getPreferredSize().height));
        heightLabelD.setFont(new Font("Arial", Font.BOLD,height/50));

        heightDropDownD.setSelectedIndex((int)(UserData.getUserHeight()));
        heightDropDownD.setEditable(true);
        heightDropDownD.setPreferredSize(new Dimension(width/15, height/15));
        heightDropDownD.addItemListener(_ -> {
            UserData.setUserHeight(heightDropDownD.getSelectedIndex()); //Updates the local user age in the userdata
            try {
                FirebaseManager.writeDBUser(UserData.getEmail()); //Updates the user data on the database
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
            if(heightDropDown.getSelectedIndex()!=heightDropDownD.getSelectedIndex()){
                heightDropDown.setSelectedIndex(heightDropDownD.getSelectedIndex());
            }
            HomePanel.updateUserInfo(); //Updates the userdata on the home panel
        });


        accountAgePanelD.add(ageLabelCenteringPanelD, BorderLayout.WEST);
        ageLabelCenteringPanelD.add(Box.createHorizontalGlue());
        ageLabelCenteringPanelD.add(ageLabelD);
        ageLabelCenteringPanelD.add(Box.createHorizontalGlue());
        accountAgePanelD.add(ageDropDownD, BorderLayout.EAST);


        accountWeightPanelD.add(weightLabelCenteringPanelD, BorderLayout.WEST);
        weightLabelCenteringPanelD.add(Box.createHorizontalGlue());
        weightLabelCenteringPanelD.add(weightLabelD);
        weightLabelCenteringPanelD.add(Box.createHorizontalGlue());
        accountWeightPanelD.add(weightDropDownD, BorderLayout.EAST);

        JButton chooseProfilePictureButtonD = new JButton("Open Image File Chooser");
        chooseProfilePictureButtonD.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                // Skapa huvudfönstret för att visa filväljaren
                JFrame frame = new JFrame("Choose Image");
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setSize(400, 400);

                profilePicture = selectImage();

                if(profilePicture!=null){
                    HomePanel.updateProfilePicture(profilePicture);
                }}
        });





        accountHeightPanelD.add(heightLabelCenteringPanelD, BorderLayout.WEST);
        heightLabelCenteringPanelD.add(Box.createHorizontalGlue());
        heightLabelCenteringPanelD.add(heightLabelD);
        heightLabelCenteringPanelD.add(Box.createHorizontalGlue());
        accountHeightPanelD.add(heightDropDownD, BorderLayout.EAST);



        accountNamePanelD.setBackground(AppThemeColors.SECONDARY);
        accountNamePanelD.setLayout(new BorderLayout());
        accountNamePanelD.setPreferredSize(new Dimension(width-width/10*7, height/15));

        usernameLabelCenteringPanelD.setBackground(Color.green);
        usernameLabelCenteringPanelD.setLayout(new BoxLayout(usernameLabelCenteringPanelD, BoxLayout.X_AXIS));
        usernameLabelCenteringPanelD.setPreferredSize(new Dimension(width/7, accountNamePanelD.getPreferredSize().height));
        usernameLabelCenteringPanelD.setOpaque(false);

        changeUsernameLabelD.setPreferredSize(new Dimension(width/8, accountNamePanelD.getPreferredSize().height));
        changeUsernameLabelD.setFont(new Font("Arial", Font.BOLD,height/50));

        changeUsernameFieldD.setMinimumSize(new Dimension(width/1000*104, height/20));
        changeUsernameFieldD.setPreferredSize(new Dimension(width/1000*104, height/20));
        changeUsernameFieldD.setMaximumSize(new Dimension(width/1000*104, height/20));
        changeUsernameFieldD.setBorder(BorderFactory.createMatteBorder(0,0,0,accountNamePanel.getPreferredSize().width/15, AppThemeColors.SECONDARY));
        changeUsernameFieldD.setText(UserData.getUserName());

        changeUsernameFieldD.addActionListener(_ -> {
            UserData.setUserName(changeUsernameFieldD.getText());
            try {
                FirebaseManager.writeDBUser(UserData.getEmail()); //Updates the user data on the database
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
            if(!changeUsernameField.getText().equals(changeUsernameFieldD.getText())){
                changeUsernameField.setText(changeUsernameFieldD.getText());
            }
            HomePanel.updateUserInfo();
        });

        accountNamePanelD.add(usernameLabelCenteringPanelD, BorderLayout.WEST);
        usernameLabelCenteringPanelD.add(Box.createHorizontalGlue());
        usernameLabelCenteringPanelD.add(changeUsernameLabelD);
        usernameLabelCenteringPanelD.add(Box.createHorizontalGlue());
        accountNamePanelD.add(changeUsernameFieldD, BorderLayout.EAST);
        accountNamePanelD.setName("changeusernamepanelchange username");

        accountPFPPanelD.setBackground(AppThemeColors.SECONDARY);
        accountPFPPanelD.setPreferredSize(new Dimension(new Dimension(width-width/10*7, height/10)));
        accountPFPPanelD.setName("pfppanelprofilepictureprofile picture");

        accountPFPPanelD.add(chooseProfilePictureButtonD);


        allPanels.add(accountAgePanelD);
        allPanels.add(accountWeightPanelD);
        allPanels.add(accountHeightPanelD);
        allPanels.add(accountNamePanelD);
        allPanels.add(accountPFPPanelD);
    }

    public void unfocusSearchField(){
        searchField.setText("Search...");
        searchField.setFont(new Font("Arial", Font.PLAIN, height/40));
        searchField.setForeground(Color.BLACK);
        searchedPanels.removeAll();
        rightPanel.requestFocusInWindow();
    }

    //TODO Need to fix so the buttons update colors properly
    public void updateColors(){

        settingsLabel.setForeground(AppThemeColors.foregroundColor);

        //Search Panel
        searchPanel.setBackground(AppThemeColors.panelColor);
        searchScrollPanel.setBackground(AppThemeColors.panelColor);
        searchFieldPanel.setBackground(AppThemeColors.SECONDARY);

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

        themeSelectionPanelD.setBackground(AppThemeColors.SECONDARY);
        themeSelectionLabelD.setForeground(AppThemeColors.foregroundColor);

        //Notifications Settings
        notificationsSettingsButton.setIcon(scaledDarkNotificationsSettingsIcon);
        notificationsSettingsButton.setForeground(AppThemeColors.foregroundColor);

        //Account Settings
        accountSettingsButton.setIcon(scaledDarkAccountSettingsIcon);
        accountSettingsButton.setForeground(AppThemeColors.foregroundColor);
        ageLabel.setForeground(AppThemeColors.foregroundColor);
        weightLabel.setForeground(AppThemeColors.foregroundColor);
        heightLabel.setForeground(AppThemeColors.foregroundColor);
        accountActivityLabel.setForeground(AppThemeColors.foregroundColor);
        changeUsernameLabel.setForeground(AppThemeColors.foregroundColor);
        choosePFPLabel.setForeground(AppThemeColors.foregroundColor);

        ageLabelD.setForeground(AppThemeColors.foregroundColor);
        weightLabelD.setForeground(AppThemeColors.foregroundColor);
        heightLabelD.setForeground(AppThemeColors.foregroundColor);
        changeUsernameLabelD.setForeground(AppThemeColors.foregroundColor);

        //Account Settings Panel
        accountScrollPanel.setBackground(AppThemeColors.panelColor);
        accountAgePanel.setBackground(AppThemeColors.SECONDARY);
        accountWeightPanel.setBackground(AppThemeColors.SECONDARY);
        accountHeightPanel.setBackground(AppThemeColors.SECONDARY);
        accountActivityPanel.setBackground(AppThemeColors.SECONDARY);
        accountNamePanel.setBackground(AppThemeColors.SECONDARY);
        accountPFPPanel.setBackground(AppThemeColors.SECONDARY);

        accountAgePanelD.setBackground(AppThemeColors.SECONDARY);
        accountWeightPanelD.setBackground(AppThemeColors.SECONDARY);
        accountHeightPanelD.setBackground(AppThemeColors.SECONDARY);
        accountNamePanelD.setBackground(AppThemeColors.SECONDARY);
        accountPFPPanelD.setBackground(AppThemeColors.SECONDARY);

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

        SearchPanel.updateColors();

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

    public static ImageIcon selectImage() {

        JnaFileChooser filechooser = new JnaFileChooser();

        filechooser.addFilter("Image files", "jpg", "png", "gif", "bmp", "jpeg");

        boolean result = filechooser.showOpenDialog(ApplicationWindow.instance);
        if (result) {
            // Hämta den valda filen
            File selectedFile = filechooser.getSelectedFile();

            // Skapa en ImageIcon från filen
            return new ImageIcon(selectedFile.getAbsolutePath());
        }

        // Om ingen fil valdes eller användaren avbröt
        return null;
    }



}