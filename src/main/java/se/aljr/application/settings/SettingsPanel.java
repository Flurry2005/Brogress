package se.aljr.application.settings;

import se.aljr.application.ApplicationWindow;
import se.aljr.application.UserData;
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
import java.sql.Time;
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
    private String resourcePath;

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

    //Various colors
    Color settingsPanelBackgroundColor = new Color(51,51,51);
    Color settingsPanelColor = new Color(21,21,21);
    Color innerSettingPanelColor = new Color(31,31,31);

    Color buttonBG = new Color(51, 51, 51,255);
    Color buttonBGHovered = new Color(40,40,40);
    Color buttonBGPressed = new Color(30,30,30);

    //Buttons
    JButton generalSettingsButton = new JButton("General", scaledGeneralSettingsIcon);
    JButton themeSettingsButton = new JButton("Theme", scaledThemeSettingsIcon);
    JButton notificationsSettingsButton = new JButton("Notifications", scaledNotificationsSettingsIcon);
    JButton accountSettingsButton = new JButton("Account", scaledAccountSettingsIcon);
    JButton privacySettingsButton = new JButton("Privacy", scaledPrivacySettingsIcon);

    //General Panel
    JPanel test2 = new JPanel();
    JPanel test3 = new JPanel();
    JPanel test4 = new JPanel();
    JPanel test5 = new JPanel();

    //Theme panel
    JPanel themeSelectionPanel = new JPanel();
    JPanel test33 = new JPanel();
    JPanel test44 = new JPanel();
    JPanel test55 = new JPanel();

    //Notifications Panel
    JPanel notificationsPanel = new JPanel();




    //Account Panel
    JPanel accountPanel = new JPanel();
    JPanel accountScrollPanel = new JPanel();
    JPanel test222 = new JPanel();
    JPanel test333 = new JPanel();
    JPanel test444 = new JPanel();
    JPanel test555 = new JPanel();
    //Privacy Panel

    JLabel settingsLabel = new JLabel("Settings");

    public static int currentPage = 0;


    public SettingsPanel(int width, int height){
        this.setPreferredSize(new Dimension(width, height));
        resourcePath = getClass().getClassLoader().getResource("resource.path").getPath().replace("resource.path","");
        settingsPanelBackground = new ImageIcon(resourcePath+"emptyBackground.png");
        scaleSettingsPanelBackground = settingsPanelBackground.getImage().getScaledInstance(width,height, Image.SCALE_SMOOTH);
        scaledSettingsPanelBackgroundIcon = new ImageIcon(scaleSettingsPanelBackground);

        lightSettingsPanelBackground = new ImageIcon(resourcePath+"lightEmptyBackground.png");
        lightScaleSettingsPanelBackground = lightSettingsPanelBackground.getImage().getScaledInstance(width,height,Image.SCALE_SMOOTH);
        lightScaledSettingsPanelBackgroundIcon = new ImageIcon(lightScaleSettingsPanelBackground);

        generalSettingsIcon = new ImageIcon(resourcePath + "settings_general.png");
        themeSettingsIcon = new ImageIcon(resourcePath+"settings_theme.png");
        notificationsSettingsIcon = new ImageIcon(resourcePath+"settings_notifications.png");
        accountSettingsIcon = new ImageIcon(resourcePath+"settings_account.png");
        privacySettingsIcon = new ImageIcon(resourcePath+"settings_privacy.png");

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

        darkGeneralSettingsIcon = new ImageIcon(resourcePath + "settings_general_dark.png");
        darkThemeSettingsIcon = new ImageIcon(resourcePath+"settings_theme_dark.png");
        darkNotificationsSettingsIcon = new ImageIcon(resourcePath+"settings_notifications_dark.png");
        darkAccountSettingsIcon = new ImageIcon(resourcePath+"settings_account_dark.png");
        darkPrivacySettingsIcon = new ImageIcon(resourcePath+"settings_privacy_dark.png");

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
            case "dark"->{
                lightMode = false;
            }
            case "light"->{
                lightMode = true;
            }
        }


        this.setOpaque(false);
        this.setLayout(new BorderLayout(0,0));
        this.setBorder(new EmptyBorder(15,15,15,15));
        init(width, height);
    }


    private void init(int width, int height){
        try{
            font=Font.createFont(Font.TRUETYPE_FONT, new File(resourcePath+"BebasNeue-Regular.otf"));
            font = font.deriveFont((float) (height/17));
        }catch(Exception e){
            font = new Font("Arial", Font.BOLD, 40);
            e.printStackTrace();
        }
        //Set the SIZE of the CheckBoxes
       SteelCheckBoxUI.SIZE = new Dimension(width/20, width/40);
        //--------------------------------------------------------------------------------------------------------------------------------------------------------------
        /**Container for all the panels*/
        JPanel containerPanel = new JPanel();
        containerPanel.setLayout(new BoxLayout(containerPanel, BoxLayout.X_AXIS));
        containerPanel.setSize(new Dimension(width, height));
        containerPanel.setOpaque(false);



        /**Creating the left panel*/
        JPanel leftPanel = new JPanel();
        leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.X_AXIS));
        leftPanel.setPreferredSize(new Dimension(width/4, height));
        leftPanel.setBackground(Color.black);
        leftPanel.setOpaque(false);

        /**Right panel*/
        JPanel rightPanel = new JPanel();
        rightPanel.setOpaque(true);
        rightPanel.setLayout(new BoxLayout(rightPanel, BoxLayout.X_AXIS));
        rightPanel.setPreferredSize(new Dimension(width-width/10*7, height));
        rightPanel.setBorder(BorderFactory.createMatteBorder(height/20, 0, height/20, 0, settingsPanelBackgroundColor));
        rightPanel.setBackground(new Color(30,30,51));
        //----------------------------------------------------------------------------------------------------------------------------------------------------------------

        /**Label Panel*/
        JPanel settingsLabelPanel = new JPanel();
        settingsLabelPanel.setPreferredSize(new Dimension(width, height/9));
        settingsLabelPanel.setOpaque(false);

        /**General Panel*/
        //----------------------------------------------------------------------------------------------------------------------------------
        JPanel generalPanel = new JPanel();
        generalPanel.setBackground(settingsPanelColor);
        generalPanel.setPreferredSize(new Dimension(width/2, height));
        generalPanel.setVisible(true);
        generalPanel.setLayout(new BoxLayout(generalPanel, BoxLayout.X_AXIS));


        //Panel within the scrollPane
        JPanel generalScrollPanel = new JPanel();
        generalScrollPanel.setPreferredSize(new Dimension(width-width/10*7, height));
        generalScrollPanel.setBackground(settingsPanelColor);
        generalScrollPanel.setLayout(new FlowLayout());

        JScrollPane generalScroll = new JScrollPane(generalScrollPanel, JScrollPane.VERTICAL_SCROLLBAR_NEVER, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        generalPanel.add(generalScroll, BorderLayout.CENTER);

        //Panels within the scrollable window that will hold various settings--------

        test2.setBackground(innerSettingPanelColor);
        test2.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));
        test2.setPreferredSize(new Dimension(width-width/10*7, height/15));
        JLabel testLabel = new JLabel("Testink");
        test2.add(testLabel);

        test3.setBackground(innerSettingPanelColor);
        test3.setPreferredSize(new Dimension(width-width/10*7, height/10));

        test4.setBackground(innerSettingPanelColor);
        test4.setPreferredSize(new Dimension(width-width/10*7, height/10));

        test5.setBackground(innerSettingPanelColor);
        test5.setPreferredSize(new Dimension(width-width/10*7, height/10));

        generalScrollPanel.add(test2);
        generalScrollPanel.add(test3);
        generalScrollPanel.add(test4);
        generalScrollPanel.add(test5);

        /**Theme Panel*/
        //----------------------------------------------------------------------------------------------------------------------------------------------------
        JPanel themePanel = new JPanel();
        themePanel.setBackground(settingsPanelColor);
        themePanel.setLayout(new BoxLayout(themePanel, BoxLayout.X_AXIS));
        themePanel.setVisible(false);

        //Panel within the scrollPane
        JPanel themeScrollPanel = new JPanel();
        themeScrollPanel.setPreferredSize(new Dimension(width/15, height));
        themeScrollPanel.setBackground(settingsPanelColor);
        themeScrollPanel.setLayout(new FlowLayout());

        JScrollPane themeScroll = new JScrollPane(themeScrollPanel, JScrollPane.VERTICAL_SCROLLBAR_NEVER, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        themePanel.add(themeScroll, BorderLayout.CENTER);

        //Panels within the scrollable window that will hold various settings

        themeSelectionPanel.setBackground(innerSettingPanelColor);
        themeSelectionPanel.setLayout(new BoxLayout(themeSelectionPanel, BoxLayout.X_AXIS));
        themeSelectionPanel.setPreferredSize(new Dimension(width-width/10*7, height/15));

        JLabel themeSelectionLabel = new JLabel("Select theme");
        themeSelectionLabel.setForeground(Color.WHITE);

        themeList.add("Light");
        themeList.add("Dark");

        JComboBox themeDropDown = new JComboBox(themeList.toArray());

        themeDropDown.setPreferredSize(new Dimension(width/15, height/10));
        themeDropDown.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {

                    switch (themeDropDown.getSelectedIndex()){
                        //Light Mode
                        case 0->{
                            lightMode = true;

                            //Update the menu panel colors


                            //Change the theme colors of the panel
                            //Background color of the whole panel
                            settingsPanelBackgroundColor = new Color(255,255,255);
                            //Background color for the panels within the container panel
                            settingsPanelColor = new Color(195,195,195);
                            //Color of the individual settings panels within the scroll pane
                            innerSettingPanelColor = new Color(220,220,220);

                            buttonBG = new Color(255, 255, 255);
                            buttonBGHovered = new Color(240,240,240);
                            buttonBGPressed = new Color(210,210,210);

                            //Change the text colors to black
                            settingsLabel.setForeground(Color.BLACK);
                            generalSettingsButton.setForeground(Color.BLACK);
                            themeSettingsButton.setForeground(Color.BLACK);
                            notificationsSettingsButton.setForeground(Color.BLACK);
                            accountSettingsButton.setForeground(Color.BLACK);
                            privacySettingsButton.setForeground(Color.BLACK);

                            //change the button image to black
                            generalSettingsButton.setIcon(scaledDarkGeneralSettingsIcon);
                            themeSettingsButton.setIcon(scaledDarkThemeSettingsIcon);
                            notificationsSettingsButton.setIcon(scaledDarkNotificationsSettingsIcon);
                            accountSettingsButton.setIcon(scaledDarkAccountSettingsIcon);
                            privacySettingsButton.setIcon(scaledDarkPrivacySettingsIcon);


                            //General Settings panel
                            generalScrollPanel.setBackground(settingsPanelColor);
                            test2.setBackground(innerSettingPanelColor);
                            test3.setBackground(innerSettingPanelColor);
                            test4.setBackground(innerSettingPanelColor);
                            test5.setBackground(innerSettingPanelColor);

                            //Theme Settings panel
                            themeScrollPanel.setBackground(settingsPanelColor);
                            themeSelectionPanel.setBackground(innerSettingPanelColor);
                            themeSelectionLabel.setForeground(Color.BLACK);
                            test33.setBackground(innerSettingPanelColor);
                            test44.setBackground(innerSettingPanelColor);
                            test55.setBackground(innerSettingPanelColor);

                            themeSelectionLabel.setForeground(Color.BLACK);

                            //Notifications Settings Panel

                            //Account Settings Panel
                            accountScrollPanel.setBackground(settingsPanelColor);
                            test222.setBackground(innerSettingPanelColor);
                            test333.setBackground(innerSettingPanelColor);
                            test444.setBackground(innerSettingPanelColor);
                            test555.setBackground(innerSettingPanelColor);

                            System.out.println("Light Mode");



                            MenuPanel.instance.repaint();
                            MenuPanel.instance.revalidate();

                            TopBar.instance.repaint();
                            TopBar.instance.revalidate();


                            ProgramPanel.instance.repaint();
                            ProgramPanel.instance.revalidate();

                            UserData.setTheme("light");
                            try {
                                FirebaseManager.writeDBUser(UserData.getEmail());
                            } catch (IOException ex) {
                                throw new RuntimeException(ex);
                            }
                            ApplicationWindow.updateBackground();


                            repaint();
                            revalidate();
                        }

                        //Dark Mode
                        case 1->{
                            lightMode = false;

                            //Update the theme colors of the panel
                            settingsPanelBackgroundColor = new Color(51,51,51);
                            settingsPanelColor = new Color(21,21,21);
                            innerSettingPanelColor = new Color(31,31,31);

                            buttonBG = new Color(51, 51, 51,255);
                            buttonBGHovered = new Color(40,40,40);
                            buttonBGPressed = new Color(30,30,30);


                            //Change the Text color of the buttons to white
                            settingsLabel.setForeground(Color.WHITE);
                            generalSettingsButton.setForeground(Color.WHITE);
                            themeSettingsButton.setForeground(Color.WHITE);
                            notificationsSettingsButton.setForeground(Color.WHITE);
                            accountSettingsButton.setForeground(Color.WHITE);
                            privacySettingsButton.setForeground(Color.WHITE);

                            //Change the button image to white
                            generalSettingsButton.setIcon(scaledGeneralSettingsIcon);
                            themeSettingsButton.setIcon(scaledThemeSettingsIcon);
                            notificationsSettingsButton.setIcon(scaledNotificationsSettingsIcon);
                            accountSettingsButton.setIcon(scaledAccountSettingsIcon);
                            privacySettingsButton.setIcon(scaledPrivacySettingsIcon);

                            //General Settings panel
                            generalScrollPanel.setBackground(settingsPanelColor);
                            test2.setBackground(innerSettingPanelColor);
                            test3.setBackground(innerSettingPanelColor);
                            test4.setBackground(innerSettingPanelColor);
                            test5.setBackground(innerSettingPanelColor);

                            //Theme Settings panel
                            themeScrollPanel.setBackground(settingsPanelColor);
                            themeSelectionPanel.setBackground(innerSettingPanelColor);
                            themeSelectionLabel.setForeground(Color.WHITE);
                            test33.setBackground(innerSettingPanelColor);
                            test44.setBackground(innerSettingPanelColor);
                            test55.setBackground(innerSettingPanelColor);

                            //Notifications Settings Panel

                            //Account Settings Panel
                            accountScrollPanel.setBackground(settingsPanelColor);
                            test222.setBackground(innerSettingPanelColor);
                            test333.setBackground(innerSettingPanelColor);
                            test444.setBackground(innerSettingPanelColor);
                            test555.setBackground(innerSettingPanelColor);



                            MenuPanel.instance.repaint();
                            MenuPanel.instance.revalidate();

                            TopBar.instance.repaint();
                            TopBar.instance.revalidate();

                            ProgramPanel.instance.repaint();
                            ProgramPanel.instance.revalidate();

                            UserData.setTheme("dark");
                            try {
                                FirebaseManager.writeDBUser(UserData.getEmail());
                            } catch (IOException ex) {
                                throw new RuntimeException(ex);
                            }
                            ApplicationWindow.updateBackground();

                            System.out.println("Dark Mode");
                            repaint();
                            revalidate();
                        }
                    }

                    rightPanel.setBorder(BorderFactory.createMatteBorder(getHeight()/20, 0, getHeight()/20, 0, settingsPanelBackgroundColor));
                    generalSettingsButton.setBackground(buttonBG);
                    themeSettingsButton.setBackground(buttonBG);
                    notificationsSettingsButton.setBackground(buttonBG);
                    accountSettingsButton.setBackground(buttonBG);
                    privacySettingsButton.setBackground(buttonBG);




                    switch (currentPage){
                        case 0->{
                            generalSettingsButton.setBackground(buttonBGPressed);
                        }
                        case 1->{
                            themeSettingsButton.setBackground(buttonBGPressed);
                        }
                        case 2->{
                            notificationsSettingsButton.setBackground(buttonBGPressed);
                        }
                        case 3->{
                            generalSettingsButton.setBackground(buttonBGPressed);
                        }
                        case 4->{
                            generalSettingsButton.setBackground(buttonBGPressed);
                        }
                    }


            }
        });




        test33.setBackground(innerSettingPanelColor);
        test33.setLayout(new BoxLayout(test33, BoxLayout.Y_AXIS));
        test33.setPreferredSize(new Dimension(width-width/10*7, height/10));


        test44.setBackground(innerSettingPanelColor);
        test44.setPreferredSize(new Dimension(width-width/10*7, height/10));


        test55.setBackground(innerSettingPanelColor);
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
        //-------------------------------------------------------------------------------------------------------------------------

        /**Notifications Panel*/
        //----------------------------------------------------------------------------------------------------------------------------------

        notificationsPanel.setBackground(settingsPanelColor);
        notificationsPanel.setVisible(false);

        /**Acount Panel*/
        //----------------------------------------------------------------------------------------------------------------------------------

        accountPanel.setBackground(settingsPanelColor);
        accountPanel.setLayout(new BoxLayout(accountPanel, BoxLayout.X_AXIS));
        accountPanel.setVisible(false);

        //Panel within the scrollPane


        accountScrollPanel.setPreferredSize(new Dimension(width-width/10*9, height));
        accountScrollPanel.setBackground(settingsPanelColor);
        accountScrollPanel.setLayout(new FlowLayout());

        JScrollPane accountScroll = new JScrollPane(accountScrollPanel, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        accountScroll.setPreferredSize(new Dimension(100, 500));

        accountPanel.add(accountScroll, BorderLayout.CENTER);



        //Panels within the scrollable window that will hold various settings--------

        test222.setBackground(innerSettingPanelColor);
        test222.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));
        test222.setPreferredSize(new Dimension(width-width/10*9, height/10));

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
        ageDropDown.setPreferredSize(new Dimension(width/15, height/15));
        ageDropDown.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                UserData.setUserAge(ageDropDown.getSelectedIndex()); //Updates the local user age in the userdata
                try {
                    FirebaseManager.writeDBUser(UserData.getEmail()); //Updates the user data on the database
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
                HomePanel.updateUserInfo(); //Updates the userdata on the home panel
            }
        });

        JComboBox weightDropDown = new JComboBox(agesList.toArray(new Integer[0]));
        weightDropDown.setSelectedIndex((int)(UserData.getUserWeight()));
        weightDropDown.setPreferredSize(new Dimension(width/15, height/15));
        weightDropDown.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                UserData.setUserWeight(weightDropDown.getSelectedIndex()); //Updates the local user age in the userdata
                try {
                    FirebaseManager.writeDBUser(UserData.getEmail()); //Updates the user data on the database
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
                HomePanel.updateUserInfo(); //Updates the userdata on the home panel
            }
        });

        JComboBox heightDropDown = new JComboBox(agesList.toArray(new Integer[0]));
        heightDropDown.setSelectedIndex((int)(UserData.getUserHeight()));
        heightDropDown.setPreferredSize(new Dimension(width/15, height/15));
        heightDropDown.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                UserData.setUserHeight(heightDropDown.getSelectedIndex()); //Updates the local user age in the userdata
                try {
                    FirebaseManager.writeDBUser(UserData.getEmail()); //Updates the user data on the database
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
                HomePanel.updateUserInfo(); //Updates the userdata on the home panel
            }
        });

        test222.add(ageLabel);
        test222.add(ageDropDown);
        test222.add(weightLabel);
        test222.add(weightDropDown);
        test222.add(heightLabel);
        test222.add(heightDropDown);
        //-------------------------------------------------------------------------------------------


        test333.setBackground(innerSettingPanelColor);
        test333.setPreferredSize(new Dimension(width-width/10*9, height/10));

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

        test333.add(chooseProfilePictureButton);


        test444.setBackground(innerSettingPanelColor);
        test444.setPreferredSize(new Dimension(width-width/10*9, height/10));


        test555.setBackground(innerSettingPanelColor);
        test555.setPreferredSize(new Dimension(width-width/10*9, height/10));

        accountScrollPanel.add(test222);
        accountScrollPanel.add(test333);
        accountScrollPanel.add(test444);
        accountScrollPanel.add(test555);

        /**Privacy Panel*/
        JPanel privacyPanel = new JPanel();
        privacyPanel.setBackground(settingsPanelColor);
        privacyPanel.setVisible(false);

        /**Button Panel*/
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));
        buttonPanel.setPreferredSize(new Dimension(width/10, height));
        buttonPanel.setBackground(new Color(10,10,10));
        buttonPanel.setOpaque(false);

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
        leftPanel.add(buttonPanel);

        rightPanel.add(generalPanel);
        //------------------------------------------------------------------------------------------------------------------------------------------------------------------------





        settingsLabel = new JLabel("Settings");
        settingsLabel.setFont(new Font("Arial", Font.BOLD,height/15));
        settingsLabel.setHorizontalAlignment(SwingConstants.CENTER);
        settingsLabel.setVerticalAlignment(SwingConstants.CENTER);
        settingsLabel.setPreferredSize(new Dimension(width, height/9));
        settingsLabel.setForeground(Color.white);

        /**Buttons*/

        generalSettingsButton.setFont(new Font("Arial", Font.PLAIN,height/30));
        generalSettingsButton.setHorizontalTextPosition(SwingConstants.CENTER);
        generalSettingsButton.setVerticalTextPosition(SwingConstants.CENTER);
        generalSettingsButton.setFocusPainted(false);
        generalSettingsButton.setFocusable(false);
        generalSettingsButton.setBorderPainted(false);
        generalSettingsButton.setContentAreaFilled(false);
        generalSettingsButton.setOpaque(true);
        generalSettingsButton.setBackground(buttonBGPressed);
        generalSettingsButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        generalSettingsButton.setForeground(Color.WHITE);

        generalSettingsButton.addMouseListener(new MouseAdapter() {
            private boolean isHovered = false;
            @Override
            public void mousePressed(MouseEvent e) {
                generalSettingsButton.setBackground(buttonBGPressed);
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                if(isHovered){
                    currentPage = 0;
                    generalSettingsButton.setBackground(buttonBGPressed);
                    themeSettingsButton.setBackground(buttonBG);
                    notificationsSettingsButton.setBackground(buttonBG);
                    accountSettingsButton.setBackground(buttonBG);
                    privacySettingsButton.setBackground(buttonBG);
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
                    generalSettingsButton.setBackground(buttonBG);
                }
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                isHovered = true;
                if(currentPage != 0 ){
                    generalSettingsButton.setBackground(buttonBGHovered);
                }

            }

            @Override
            public void mouseExited(MouseEvent e) {
                isHovered = false;
                if(currentPage != 0){
                    generalSettingsButton.setBackground(buttonBG);
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
        themeSettingsButton.setBackground(buttonBG);
        themeSettingsButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        themeSettingsButton.setForeground(Color.WHITE);

        themeSettingsButton.addMouseListener(new MouseAdapter() {
            private boolean isHovered = false;
            @Override
            public void mousePressed(MouseEvent e) {
                themeSettingsButton.setBackground(buttonBGPressed);
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                if(isHovered){
                    currentPage = 1;
                    generalSettingsButton.setBackground(buttonBG);
                    themeSettingsButton.setBackground(buttonBGPressed);
                    notificationsSettingsButton.setBackground(buttonBG);
                    accountSettingsButton.setBackground(buttonBG);
                    privacySettingsButton.setBackground(buttonBG);
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
                    themeSettingsButton.setBackground(buttonBG);
                }


            }

            @Override
            public void mouseEntered(MouseEvent e) {
                isHovered = true;
                if(currentPage != 1){
                    themeSettingsButton.setBackground(buttonBGHovered);
                }

            }

            @Override
            public void mouseExited(MouseEvent e) {
                isHovered = false;
                if(currentPage != 1){
                    themeSettingsButton.setBackground(buttonBG);
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
        notificationsSettingsButton.setBackground(buttonBG);
        notificationsSettingsButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        notificationsSettingsButton.setForeground(Color.WHITE);

        notificationsSettingsButton.addMouseListener(new MouseAdapter() {
            private boolean isHovered = false;
            @Override
            public void mousePressed(MouseEvent e) {
                notificationsSettingsButton.setBackground(buttonBGPressed);
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                if(isHovered){
                    currentPage = 2;
                    generalSettingsButton.setBackground(buttonBG);
                    themeSettingsButton.setBackground(buttonBG);
                    notificationsSettingsButton.setBackground(buttonBGPressed);
                    accountSettingsButton.setBackground(buttonBG);
                    privacySettingsButton.setBackground(buttonBG);
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
                    notificationsSettingsButton.setBackground(buttonBG);
                }


            }

            @Override
            public void mouseEntered(MouseEvent e) {
                isHovered = true;
                if(currentPage!=2)
                notificationsSettingsButton.setBackground(buttonBGHovered);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                isHovered = false;
                if(currentPage!=2){
                    notificationsSettingsButton.setBackground(buttonBG);
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
        accountSettingsButton.setBackground(buttonBG);
        accountSettingsButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        accountSettingsButton.setForeground(Color.WHITE);

        accountSettingsButton.addMouseListener(new MouseAdapter() {
            private boolean isHovered = false;
            @Override
            public void mousePressed(MouseEvent e) {
                accountSettingsButton.setBackground(buttonBGPressed);
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                if(isHovered){
                    currentPage = 3;
                    generalSettingsButton.setBackground(buttonBG);
                    themeSettingsButton.setBackground(buttonBG);
                    notificationsSettingsButton.setBackground(buttonBG);
                    accountSettingsButton.setBackground(buttonBGPressed);
                    privacySettingsButton.setBackground(buttonBG);
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
                    accountSettingsButton.setBackground(buttonBG);
                }
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                isHovered = true;
                if(currentPage!=3){
                    accountSettingsButton.setBackground(buttonBGHovered);
                }
            }

            @Override
            public void mouseExited(MouseEvent e) {
                isHovered = false;
                if(currentPage!=3){
                    accountSettingsButton.setBackground(buttonBG);
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
        privacySettingsButton.setBackground(buttonBG);
        privacySettingsButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        privacySettingsButton.setForeground(Color.WHITE);

        privacySettingsButton.addMouseListener(new MouseAdapter() {
            private boolean isHovered = false;
            @Override
            public void mousePressed(MouseEvent e) {
                privacySettingsButton.setBackground(buttonBGPressed);
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                if(isHovered){
                    currentPage = 4;
                    generalSettingsButton.setBackground(buttonBG);
                    themeSettingsButton.setBackground(buttonBG);
                    notificationsSettingsButton.setBackground(buttonBG);
                    accountSettingsButton.setBackground(buttonBG);
                    privacySettingsButton.setBackground(buttonBGPressed);
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
                    privacySettingsButton.setBackground(buttonBG);
                }
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                isHovered = true;
                if(currentPage != 4){
                    privacySettingsButton.setBackground(buttonBGHovered);
                }
            }

            @Override
            public void mouseExited(MouseEvent e) {
                isHovered = false;
                if(currentPage!=4){
                    privacySettingsButton.setBackground(buttonBG);
                }

            }
        });

        generalSettingsButton.setPreferredSize(new Dimension(width, height/9));
        generalSettingsButton.setMaximumSize(new Dimension(width-width/4*3, height/3));

        themeSettingsButton.setPreferredSize(new Dimension(width, height/9));
        themeSettingsButton.setMaximumSize(new Dimension(width-width/4*3, height/3));

        notificationsSettingsButton.setPreferredSize(new Dimension(width, height/9));
        notificationsSettingsButton.setMaximumSize(new Dimension(width-width/4*3, height/3));

        accountSettingsButton.setPreferredSize(new Dimension(width, height/9));
        accountSettingsButton.setMaximumSize(new Dimension(width-width/4*3, height/3));

        privacySettingsButton.setPreferredSize(new Dimension(width, height/9));
        privacySettingsButton.setMaximumSize(new Dimension(width-width/4*3, height/3));

        buttonPanel.add(settingsLabelPanel);
        settingsLabelPanel.add(settingsLabel);
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

        //------------------------------------------------------------------------------------------------------------------------------------------------------------------------
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

                    test222.setPreferredSize(new Dimension(getWidth()-getWidth()/10*7, getHeight()/10));
                    test333.setPreferredSize(new Dimension(getWidth()-getWidth()/10*7, getHeight()/10));
                    test444.setPreferredSize(new Dimension(getWidth()-getWidth()/10*7, getHeight()/10));
                    test555.setPreferredSize(new Dimension(getWidth()-getWidth()/10*7, getHeight()/10));

                });

            }
        });

        themeDropDown.setSelectedIndex(-1);
        if(lightMode){
            themeDropDown.setSelectedIndex(0);
        }else{
            themeDropDown.setSelectedIndex(1);
        }
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
