package se.aljr.application.settings;

import se.aljr.application.UserData;
import se.aljr.application.homepage.HomePanel;
import se.aljr.application.loginpage.FirebaseManager;
import se.aljr.application.settings.custom.SteelCheckBox;
import se.aljr.application.settings.custom.SteelCheckBoxUI;
import se.aljr.application.settings.tools.ColorDef;


import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.event.*;

public class SettingsPanel extends JPanel{

    ImageIcon settingsPanelBackground;
    Image scaleSettingsPanelBackground;
    ImageIcon scaledSettingsPanelBackgroundIcon;
    Font font;
    private String resourcePath;

    ImageIcon generalSettingsIcon;
    ImageIcon themeSettingsIcon;
    ImageIcon notificationsSettingsIcon;
    ImageIcon accountSettingsIcon;
    ImageIcon privacySettingsIcon;

    private ImageIcon scaledGeneralSettingsIcon;
    private ImageIcon scaledThemeSettingsIcon;
    private ImageIcon scaledNotificationsSettingsIcon;
    private ImageIcon scaledAccountSettingsIcon;
    private ImageIcon scaledPrivacySettingsIcon;

    public static int currentPage = 0;


    public SettingsPanel(int width, int height){
        resourcePath = getClass().getClassLoader().getResource("resource.path").getPath().replace("resource.path","");
        settingsPanelBackground = new ImageIcon(resourcePath+"emptyBackground.png");
        scaleSettingsPanelBackground = settingsPanelBackground.getImage().getScaledInstance(width,height, Image.SCALE_SMOOTH);
        scaledSettingsPanelBackgroundIcon = new ImageIcon(scaleSettingsPanelBackground);

        generalSettingsIcon = new ImageIcon(resourcePath + "settings_general.png");
        themeSettingsIcon = new ImageIcon(resourcePath+"settings_theme.png");
        notificationsSettingsIcon = new ImageIcon(resourcePath+"settings_notifications.png");
        accountSettingsIcon = new ImageIcon(resourcePath+"settings_account.png");
        privacySettingsIcon = new ImageIcon(resourcePath+"settings_privacy.png");

        this.setPreferredSize(new Dimension(width, height));
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

        //------------------------------------------------------------------------------------------------------------------------------------------------------------------------




        //
        //Set the SIZE of the CheckBoxes
       SteelCheckBoxUI.SIZE = new Dimension(width/20, width/40);








        /**Container for all the panels*/
        JPanel containerPanel = new JPanel();
        containerPanel.setLayout(new BoxLayout(containerPanel, BoxLayout.X_AXIS));
        containerPanel.setSize(new Dimension(width, height));
        containerPanel.setOpaque(false);

        /**Creating the left panel*/
        JPanel leftPanel = new JPanel();
        leftPanel.setLayout(new BorderLayout(0,0));
        leftPanel.setPreferredSize(new Dimension(width/4, height));
        leftPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
        leftPanel.setBackground(Color.black);
        leftPanel.setOpaque(false);

        /**Right panel*/
        JPanel rightPanel = new JPanel();
        rightPanel.setOpaque(true);
        rightPanel.setLayout(new BorderLayout(0,0));
        rightPanel.setPreferredSize(new Dimension(width-width/4, height));
        rightPanel.setBorder(BorderFactory.createMatteBorder(0, 20, 0, 0, new Color(51,51,51)));
        rightPanel.setBackground(new Color(30,30,51));

        /**Label Panel*/
        JPanel labelPanel = new JPanel();
        labelPanel.setPreferredSize(new Dimension(width, height/9));
        labelPanel.setOpaque(false);

        Color settingsPanelColor = new Color(81,81,81);

        /**General Panel*/
        JPanel generalPanel = new JPanel();
        generalPanel.setBackground(settingsPanelColor);
        generalPanel.setVisible(true);
        generalPanel.setLayout(new BorderLayout());


        //Panel within the scrollPane
        JPanel generalScrollPanel = new JPanel();
        generalScrollPanel.setPreferredSize(new Dimension(width/5, height));
        generalScrollPanel.setBackground(settingsPanelColor);
        generalScrollPanel.setLayout(new FlowLayout());

        JScrollPane generalScroll = new JScrollPane(generalScrollPanel, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        generalScroll.setPreferredSize(new Dimension(100, 500));

        generalPanel.add(generalScroll, BorderLayout.CENTER);

        //Panels within the scrollable window that will hold various settings--------
        JPanel test2 = new JPanel();
        test2.setBackground(Color.pink);
        test2.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));
        test2.setPreferredSize(new Dimension(width-width/2, height/10));

        JPanel test3 = new JPanel();
        test3.setBackground(Color.pink);
        test3.setPreferredSize(new Dimension(width-width/2, height/10));

        JPanel test4 = new JPanel();
        test4.setBackground(Color.pink);
        test4.setPreferredSize(new Dimension(width-width/2, height/10));

        JPanel test5 = new JPanel();
        test5.setBackground(Color.pink);
        test5.setPreferredSize(new Dimension(width-width/2, height/10));

        generalScrollPanel.add(test2);
        generalScrollPanel.add(test3);
        generalScrollPanel.add(test4);
        generalScrollPanel.add(test5);




        /**Theme Panel*/
        //-------------------------------------------------------------------------------------------
        JPanel themePanel = new JPanel();
        themePanel.setBackground(settingsPanelColor);
        themePanel.setLayout(new BorderLayout());
        themePanel.setVisible(false);

        //Panel within the scrollPane
        JPanel themeScrollPanel = new JPanel();
        themeScrollPanel.setPreferredSize(new Dimension(width/5, height));
        themeScrollPanel.setBackground(settingsPanelColor);
        themeScrollPanel.setLayout(new FlowLayout());

        JScrollPane themeScroll = new JScrollPane(themeScrollPanel, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        themeScroll.setPreferredSize(new Dimension(100, 500));

        themePanel.add(themeScroll, BorderLayout.CENTER);

        //Panels within the scrollable window that will hold various settings----------------------------------------------------
        JPanel lightModeDarkModePanel = new JPanel();
        lightModeDarkModePanel.setBackground(Color.pink);
        lightModeDarkModePanel.setLayout(new BoxLayout(lightModeDarkModePanel, BoxLayout.Y_AXIS));
        lightModeDarkModePanel.setPreferredSize(new Dimension(width-width/2, height/10));

        //Panel used to center the light mode / dark mode label and switch
        JPanel lightModeDarkModeCenteringPanel = new JPanel();
        lightModeDarkModeCenteringPanel.setOpaque(false);
        lightModeDarkModeCenteringPanel.setLayout(new FlowLayout(FlowLayout.RIGHT, width/40, 0));
        lightModeDarkModeCenteringPanel.setPreferredSize(new Dimension(width-width/2, height/70));

        JLabel lightModeDarkModeLabel = new JLabel("Light Mode / Dark Mode");
        lightModeDarkModeLabel.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, width/50*11));

        //Custom "library" to create the switch
        SteelCheckBox lightModeDarkModeSwitch = new SteelCheckBox();
        lightModeDarkModeSwitch.setSelectedColor(ColorDef.YELLOW);
        lightModeDarkModeSwitch.setRised(false);
        lightModeDarkModeSwitch.setText(" "); //For some reason the entire Brogress app starts acting up if I dont include this lmao
        lightModeDarkModeSwitch.setOpaque(false);
        lightModeDarkModeSwitch.setPreferredSize(new Dimension(width/20,width/40));

        lightModeDarkModeSwitch.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if(e.getStateChange() == ItemEvent.SELECTED){
                    System.out.println("Checkbox is ON!!");
                }else{
                    System.out.println("Checkbox is OFF!!");
                }
            }
        });

        //Centers the panel that holds the lightmode / darkmode switch and label
        lightModeDarkModePanel.add(Box.createVerticalGlue());
        lightModeDarkModePanel.add(lightModeDarkModeCenteringPanel, BorderLayout.CENTER);
        lightModeDarkModePanel.add(Box.createVerticalGlue());

        //Adds the switch and label for Lightmode / Darkmode
        lightModeDarkModeCenteringPanel.add(lightModeDarkModeLabel);
        lightModeDarkModeCenteringPanel.add(lightModeDarkModeSwitch);

        JPanel test33 = new JPanel();
        test33.setBackground(Color.pink);
        test33.setPreferredSize(new Dimension(width-width/2, height/10));

        JPanel test44 = new JPanel();
        test44.setBackground(Color.pink);
        test44.setPreferredSize(new Dimension(width-width/2, height/10));

        JPanel test55 = new JPanel();
        test55.setBackground(Color.pink);
        test55.setPreferredSize(new Dimension(width-width/2, height/10));

        themeScrollPanel.add(lightModeDarkModePanel);
        themeScrollPanel.add(test33);
        themeScrollPanel.add(test44);
        themeScrollPanel.add(test55);
        //-------------------------------------------------------------------------------------------------------------------------

        /**Notifications Panel*/
        JPanel notificationsPanel = new JPanel();
        notificationsPanel.setBackground(settingsPanelColor);
        notificationsPanel.setVisible(false);

        /**Acount Panel*/
        JPanel accountPanel = new JPanel();
        accountPanel.setBackground(settingsPanelColor);
        accountPanel.setLayout(new BorderLayout());
        accountPanel.setVisible(false);

        //Panel within the scrollPane
        JPanel accountScrollPanel = new JPanel();
        accountScrollPanel.setPreferredSize(new Dimension(width/5, height));
        accountScrollPanel.setBackground(settingsPanelColor);
        accountScrollPanel.setLayout(new FlowLayout());

        JScrollPane accountScroll = new JScrollPane(accountScrollPanel, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        accountScroll.setPreferredSize(new Dimension(100, 500));

        accountPanel.add(accountScroll, BorderLayout.CENTER);



        //Panels within the scrollable window that will hold various settings--------
        JPanel test222 = new JPanel();
        test222.setBackground(Color.pink);
        test222.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));
        test222.setPreferredSize(new Dimension(width-width/2, height/10));

        //GeneralSettingsPanel1, age, weight and height settings
        JLabel ageLabel = new JLabel("Age: ");
        ageLabel.setPreferredSize(new Dimension(50, test2.getPreferredSize().height));

        JLabel weightLabel = new JLabel("Weight: ");
        weightLabel.setPreferredSize(new Dimension(50, test2.getPreferredSize().height));

        JLabel heightLabel = new JLabel("Height: ");
        heightLabel.setPreferredSize(new Dimension(50, test2.getPreferredSize().height));

        ArrayList<Integer> agesList = new ArrayList<>();
        for (Integer i=0; i<100;i++){
            agesList.add(i);
        }

        String[] arrTest = {"dog","cat","bird"};
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
        weightDropDown.setPreferredSize(new Dimension(width/15, height/15));

        JComboBox heightDropDown = new JComboBox(agesList.toArray(new Integer[0]));
        heightDropDown.setPreferredSize(new Dimension(width/15, height/15));

        test222.add(ageLabel);
        test222.add(ageDropDown);
        test222.add(weightLabel);
        test222.add(weightDropDown);
        test222.add(heightLabel);
        test222.add(heightDropDown);
        //-------------------------------------------------------------------------------------------

        JPanel test333 = new JPanel();
        test333.setBackground(Color.pink);
        test333.setPreferredSize(new Dimension(width-width/2, height/10));

        JPanel test444 = new JPanel();
        test444.setBackground(Color.pink);
        test444.setPreferredSize(new Dimension(width-width/2, height/10));

        JPanel test555 = new JPanel();
        test555.setBackground(Color.pink);
        test555.setPreferredSize(new Dimension(width-width/2, height/10));

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
        buttonPanel.setPreferredSize(new Dimension(width/4, height));
        buttonPanel.setBackground(new Color(10,10,10));
        buttonPanel.setOpaque(false);

        this.add(containerPanel);
        containerPanel.add(leftPanel);
        containerPanel.add(rightPanel);
        leftPanel.add(buttonPanel);

        rightPanel.add(generalPanel);

        //------------------------------------------------------------------------------------------------------------------------------------------------------------------------

        Image scaledgeneralSettingsIcon = generalSettingsIcon.getImage().getScaledInstance(width-width/2*3,height/8, Image.SCALE_SMOOTH);
        scaledGeneralSettingsIcon = new ImageIcon(scaledgeneralSettingsIcon);

        Image scaledthemeSettingsIcon = themeSettingsIcon.getImage().getScaledInstance(width-width/2*3, height/8, Image.SCALE_SMOOTH);
        scaledThemeSettingsIcon = new ImageIcon(scaledthemeSettingsIcon);

        Image scalednotificationsSettingsIcon = notificationsSettingsIcon.getImage().getScaledInstance(width-width/2*3, height/8, Image.SCALE_SMOOTH);
        scaledNotificationsSettingsIcon = new ImageIcon(scalednotificationsSettingsIcon);

        Image scaledaccountSettingsIcon = accountSettingsIcon.getImage().getScaledInstance(width-width/2*3, height/8, Image.SCALE_SMOOTH);
        scaledAccountSettingsIcon = new ImageIcon(scaledaccountSettingsIcon);

        Image scaledprivacySettingsIcon = privacySettingsIcon.getImage().getScaledInstance(width-width/2*3, height/8, Image.SCALE_SMOOTH);
        scaledPrivacySettingsIcon = new ImageIcon(scaledprivacySettingsIcon);

        Color buttonBG = new Color(51, 51, 51,255);
        Color buttonBGHovered = new Color(40,40,40);
        Color buttonBGPressed = new Color(30,30,30);

        JLabel settingsLabel = new JLabel("Settings");
        settingsLabel.setFont(new Font("Arial", Font.BOLD,height/15));
        settingsLabel.setHorizontalAlignment(SwingConstants.CENTER);
        settingsLabel.setVerticalAlignment(SwingConstants.CENTER);
        settingsLabel.setPreferredSize(new Dimension(width, height/9));
        settingsLabel.setForeground(Color.white);

        /**Buttons*/

        JButton button1 = new JButton("General", scaledGeneralSettingsIcon);
        JButton button2 = new JButton("Theme", scaledThemeSettingsIcon);
        JButton button3 = new JButton("Notifications", scaledNotificationsSettingsIcon);
        JButton button4 = new JButton("Account", scaledAccountSettingsIcon);
        JButton button5 = new JButton("Privacy", scaledPrivacySettingsIcon);

        button1.setFont(new Font("Arial", Font.BOLD,height/25));
        button1.setHorizontalTextPosition(SwingConstants.CENTER);
        button1.setVerticalTextPosition(SwingConstants.CENTER);
        button1.setFocusPainted(false);
        button1.setFocusable(false);
        button1.setBorderPainted(false);
        button1.setContentAreaFilled(false);
        button1.setOpaque(true);
        button1.setBackground(buttonBGPressed);
        button1.setAlignmentX(Component.CENTER_ALIGNMENT);
        button1.setForeground(Color.WHITE);

        button1.addMouseListener(new MouseAdapter() {
            private boolean isHovered = false;
            @Override
            public void mousePressed(MouseEvent e) {
                button1.setBackground(buttonBGPressed);
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                if(isHovered){
                    currentPage = 0;
                    button1.setBackground(buttonBGPressed);
                    button2.setBackground(buttonBG);
                    button3.setBackground(buttonBG);
                    button4.setBackground(buttonBG);
                    button5.setBackground(buttonBG);
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
                    button1.setBackground(buttonBG);
                }
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                isHovered = true;
                if(currentPage != 0 ){
                    button1.setBackground(buttonBGHovered);
                }

            }

            @Override
            public void mouseExited(MouseEvent e) {
                isHovered = false;
                if(currentPage != 0){
                    button1.setBackground(buttonBG);
                }

            }
        });

        button2.setFont(new Font("Arial", Font.BOLD,height/25));
        button2.setHorizontalTextPosition(SwingConstants.CENTER);
        button2.setVerticalTextPosition(SwingConstants.CENTER);
        button2.setFocusPainted(false);
        button2.setFocusable(false);
        button2.setBorderPainted(false);
        button2.setContentAreaFilled(false);
        button2.setOpaque(true);
        button2.setBackground(buttonBG);
        button2.setAlignmentX(Component.CENTER_ALIGNMENT);
        button2.setForeground(Color.WHITE);

        button2.addMouseListener(new MouseAdapter() {
            private boolean isHovered = false;
            @Override
            public void mousePressed(MouseEvent e) {
                button2.setBackground(buttonBGPressed);
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                if(isHovered){
                    currentPage = 1;
                    button1.setBackground(buttonBG);
                    button2.setBackground(buttonBGPressed);
                    button3.setBackground(buttonBG);
                    button4.setBackground(buttonBG);
                    button5.setBackground(buttonBG);
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
                    button2.setBackground(buttonBG);
                }


            }

            @Override
            public void mouseEntered(MouseEvent e) {
                isHovered = true;
                if(currentPage != 1){
                    button2.setBackground(buttonBGHovered);
                }

            }

            @Override
            public void mouseExited(MouseEvent e) {
                isHovered = false;
                if(currentPage != 1){
                    button2.setBackground(buttonBG);
                }

            }
        });

        button3.setFont(new Font("Arial", Font.BOLD,height/25));
        button3.setHorizontalTextPosition(SwingConstants.CENTER);
        button3.setVerticalTextPosition(SwingConstants.CENTER);
        button3.setFocusPainted(false);
        button3.setFocusable(false);
        button3.setBorderPainted(false);
        button3.setContentAreaFilled(false);
        button3.setOpaque(true);
        button3.setBackground(buttonBG);
        button3.setAlignmentX(Component.CENTER_ALIGNMENT);
        button3.setForeground(Color.WHITE);

        button3.addMouseListener(new MouseAdapter() {
            private boolean isHovered = false;
            @Override
            public void mousePressed(MouseEvent e) {
                button3.setBackground(buttonBGPressed);
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                if(isHovered){
                    currentPage = 2;
                    button1.setBackground(buttonBG);
                    button2.setBackground(buttonBG);
                    button3.setBackground(buttonBGPressed);
                    button4.setBackground(buttonBG);
                    button5.setBackground(buttonBG);
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
                    button3.setBackground(buttonBG);
                }


            }

            @Override
            public void mouseEntered(MouseEvent e) {
                isHovered = true;
                if(currentPage!=2)
                button3.setBackground(buttonBGHovered);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                isHovered = false;
                if(currentPage!=2){
                    button3.setBackground(buttonBG);
                }


            }
        });

        button4.setFont(new Font("Arial", Font.BOLD,height/25));
        button4.setHorizontalTextPosition(SwingConstants.CENTER);
        button4.setVerticalTextPosition(SwingConstants.CENTER);
        button4.setFocusPainted(false);
        button4.setFocusable(false);
        button4.setBorderPainted(false);
        button4.setContentAreaFilled(false);
        button4.setOpaque(true);
        button4.setBackground(buttonBG);
        button4.setAlignmentX(Component.CENTER_ALIGNMENT);
        button4.setForeground(Color.WHITE);

        button4.addMouseListener(new MouseAdapter() {
            private boolean isHovered = false;
            @Override
            public void mousePressed(MouseEvent e) {
                button4.setBackground(buttonBGPressed);
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                if(isHovered){
                    currentPage = 3;
                    button1.setBackground(buttonBG);
                    button2.setBackground(buttonBG);
                    button3.setBackground(buttonBG);
                    button4.setBackground(buttonBGPressed);
                    button5.setBackground(buttonBG);
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
                    button4.setBackground(buttonBG);
                }


            }

            @Override
            public void mouseEntered(MouseEvent e) {
                isHovered = true;
                if(currentPage!=3){
                    button4.setBackground(buttonBGHovered);
                }

            }

            @Override
            public void mouseExited(MouseEvent e) {
                isHovered = false;
                if(currentPage!=3){
                    button4.setBackground(buttonBG);
                }

            }
        });

        button5.setFont(new Font("Arial", Font.BOLD,height/25));
        button5.setHorizontalTextPosition(SwingConstants.CENTER);
        button5.setVerticalTextPosition(SwingConstants.CENTER);
        button5.setFocusPainted(false);
        button5.setFocusable(false);
        button5.setBorderPainted(false);
        button5.setContentAreaFilled(false);
        button5.setOpaque(true);
        button5.setBackground(buttonBG);
        button5.setAlignmentX(Component.CENTER_ALIGNMENT);
        button5.setForeground(Color.WHITE);

        button5.addMouseListener(new MouseAdapter() {
            private boolean isHovered = false;
            @Override
            public void mousePressed(MouseEvent e) {
                button5.setBackground(buttonBGPressed);
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                if(isHovered){
                    currentPage = 4;
                    button1.setBackground(buttonBG);
                    button2.setBackground(buttonBG);
                    button3.setBackground(buttonBG);
                    button4.setBackground(buttonBG);
                    button5.setBackground(buttonBGPressed);
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
                    button5.setBackground(buttonBG);
                }


            }

            @Override
            public void mouseEntered(MouseEvent e) {
                isHovered = true;
                if(currentPage != 4){
                    button5.setBackground(buttonBGHovered);
                }

            }

            @Override
            public void mouseExited(MouseEvent e) {
                isHovered = false;
                if(currentPage!=4){
                    button5.setBackground(buttonBG);
                }

            }
        });

        button1.setPreferredSize(new Dimension(width, height/9));
        button1.setMaximumSize(new Dimension(width-width/4*3, height/3));

        button2.setPreferredSize(new Dimension(width, height/9));
        button2.setMaximumSize(new Dimension(width-width/4*3, height/3));

        button3.setPreferredSize(new Dimension(width, height/9));
        button3.setMaximumSize(new Dimension(width-width/4*3, height/3));

        button4.setPreferredSize(new Dimension(width, height/9));
        button4.setMaximumSize(new Dimension(width-width/4*3, height/3));

        button5.setPreferredSize(new Dimension(width, height/9));
        button5.setMaximumSize(new Dimension(width-width/4*3, height/3));

        buttonPanel.add(labelPanel);
        labelPanel.add(settingsLabel);
        buttonPanel.add(button1);
        buttonPanel.add(Box.createVerticalGlue());
        buttonPanel.add(button2);
        buttonPanel.add(Box.createVerticalGlue());
        buttonPanel.add(button3);
        buttonPanel.add(Box.createVerticalGlue());
        buttonPanel.add(button4);
        buttonPanel.add(Box.createVerticalGlue());
        buttonPanel.add(button5);
        buttonPanel.add(Box.createVerticalGlue());
        buttonPanel.add(Box.createVerticalGlue());
        buttonPanel.add(Box.createVerticalGlue());
        buttonPanel.add(Box.createVerticalGlue());
        buttonPanel.add(Box.createVerticalGlue());
        buttonPanel.add(Box.createVerticalGlue());
        buttonPanel.add(Box.createVerticalGlue());


        this.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                SwingUtilities.invokeLater(()->{
                    leftPanel.setPreferredSize(new Dimension(getWidth()/4, getHeight()));
                    buttonPanel.setPreferredSize(new Dimension(getWidth()/4,getHeight()));
                    rightPanel.setPreferredSize(new Dimension(getWidth()-getWidth()/4, getHeight()));
                    labelPanel.setPreferredSize(new Dimension(getWidth(), getHeight()/9));


                    settingsLabel.setFont(new Font("Arial", Font.BOLD,getHeight()/15));
                    settingsLabel.setPreferredSize(new Dimension(getWidth(), getHeight()/9));
                    //paddingPanel.setPreferredSize(new Dimension(getWidth()/10, getHeight()));

                    Image scaledgeneralSettingsIcon = generalSettingsIcon.getImage().getScaledInstance(getWidth()-getWidth()/4*3,getHeight()/10, Image.SCALE_SMOOTH);
                    scaledGeneralSettingsIcon = new ImageIcon(scaledgeneralSettingsIcon);
                    button1.setIcon(scaledGeneralSettingsIcon);
                    button1.setFont(new Font("Arial", Font.BOLD,getHeight()/25));
                    button1.setPreferredSize(new Dimension(getWidth(), getHeight()/10));
                    button1.setMaximumSize(new Dimension(getWidth()-getWidth()/3*2, getHeight()/3));

                    Image scaledthemeSettingsIcon = themeSettingsIcon.getImage().getScaledInstance(getWidth()-getWidth()/4*3, getHeight()/10, Image.SCALE_SMOOTH);
                    scaledThemeSettingsIcon = new ImageIcon(scaledthemeSettingsIcon);
                    button2.setIcon(scaledThemeSettingsIcon);
                    button2.setFont(new Font("Arial", Font.BOLD,getHeight()/25));
                    button2.setPreferredSize(new Dimension(getWidth(), getHeight()/10));
                    button2.setMaximumSize(new Dimension(getWidth()-getWidth()/3*2, getHeight()/3));

                    Image scalednotificationsSettingsIcon = notificationsSettingsIcon.getImage().getScaledInstance(getWidth()-getWidth()/4*3, getHeight()/10, Image.SCALE_SMOOTH);
                    scaledNotificationsSettingsIcon = new ImageIcon(scalednotificationsSettingsIcon);
                    button3.setIcon(scaledNotificationsSettingsIcon);
                    button3.setFont(new Font("Arial", Font.BOLD,getHeight()/25));
                    button3.setPreferredSize(new Dimension(getWidth(), getHeight()/10));
                    button3.setMaximumSize(new Dimension(getWidth()-getWidth()/3*2, getHeight()/3));

                    Image scaledaccountSettingsIcon = accountSettingsIcon.getImage().getScaledInstance(getWidth()-getWidth()/4*3, getHeight()/10, Image.SCALE_SMOOTH);
                    scaledAccountSettingsIcon = new ImageIcon(scaledaccountSettingsIcon);
                    button4.setIcon(scaledAccountSettingsIcon);
                    button4.setFont(new Font("Arial", Font.BOLD,getHeight()/25));
                    button4.setPreferredSize(new Dimension(getWidth(), getHeight()/10));
                    button4.setMaximumSize(new Dimension(getWidth()-getWidth()/3*2, getHeight()/3));


                    Image scaledprivacySettingsIcon = privacySettingsIcon.getImage().getScaledInstance(getWidth()-getWidth()/4*3, getHeight()/10, Image.SCALE_SMOOTH);
                    scaledPrivacySettingsIcon = new ImageIcon(scaledprivacySettingsIcon);
                    button5.setIcon(scaledPrivacySettingsIcon);
                    button5.setFont(new Font("Arial", Font.BOLD,getHeight()/25));
                    button5.setPreferredSize(new Dimension(getWidth(), getHeight()/10));
                    button5.setMaximumSize(new Dimension(getWidth()-getWidth()/3*2, getHeight()/3));

                });

            }
        });
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
