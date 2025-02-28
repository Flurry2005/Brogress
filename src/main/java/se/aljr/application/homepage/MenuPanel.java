package se.aljr.application.homepage;

import se.aljr.application.AppThemeColors;
import se.aljr.application.ApplicationWindow;
import se.aljr.application.ResourcePath;
import se.aljr.application.settings.SettingsPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;


/**
 * Meny panel
 */
public class MenuPanel extends JPanel{
    public ImageIcon menuBackground;
    public ImageIcon lightMenuBackground;
    ImageIcon logoIcon;
    private ImageIcon scaledLogoIcon;

    ImageIcon buttonIcon;
    ImageIcon buttonIconExercise;
    ImageIcon buttonIconProgram;
    ImageIcon buttonIconSettings;
    ImageIcon buttonIconChat;

    Image scaledbuttonHomeIcon;
    Image scaledButtonExercise;
    Image scaledButtonProgram;
    Image scaledButtonSettings;
    Image scaledButtonChat;

    private ImageIcon scaledButtonHomeIcon;
    private static ImageIcon scaledButtonExerciseIcon;
    private ImageIcon scaledButtonProgramIcon;
    private ImageIcon scaledButtonSettingsIcon;
    private ImageIcon scaledButtonChatIcon;


    ImageIcon darkButtonIcon;
    ImageIcon darkButtonIconExercise;
    ImageIcon darkButtonIconProgram;
    ImageIcon darkButtonIconSettings;
    ImageIcon darkButtonIconChat;

    Image scaleddarkButtonHomeIcon;
    Image scaleddarkButtonExerciseIcon;
    Image scaleddarkButtonProgramIcon;
    Image scaleddarkButtonSettingsIcon;
    Image scaleddarkButtonChatIcon;

    private ImageIcon scaledDarkButtonHomeIcon;
    private ImageIcon scaledDarkButtonExerciseIcon;
    private ImageIcon scaledDarkButtonProgramIcon;
    private ImageIcon scaledDarkButtonSettingsIcon;
    private ImageIcon scaledDarkButtonChatIcon;


    public boolean lightMode = false;
    public static MenuPanel instance;
    public int currentPage = 0;

    Font font;

    final JButton homeButton = new JButton("Home",scaledButtonHomeIcon);
    public static final JButton exercisesButton = new JButton("Exercises",scaledButtonExerciseIcon);
    final JButton programButton = new JButton("Workouts");
    JButton settingsButton = new JButton("Settings",scaledButtonSettingsIcon);
    JButton chatButton = new JButton("Social",scaledButtonSettingsIcon);

    JLabel logoLabelText = new JLabel("BROGRESS");

    public MenuPanel(int width, int height){
        this.setOpaque(false);
        instance = this;
        //this.setPreferredSize(new Dimension(width, height));
        menuBackground = new ImageIcon(ResourcePath.getResourcePath() +"side_bar.png");
        lightMenuBackground = new ImageIcon(ResourcePath.getResourcePath()+"side_bar_light.png");
        logoIcon = new ImageIcon(ResourcePath.getResourcePath()+"agile_small_icon.png");

        buttonIcon = new ImageIcon(ResourcePath.getResourcePath()+"button.png");
        buttonIconExercise = new ImageIcon(ResourcePath.getResourcePath()+"button_exercise.png");
        buttonIconProgram = new ImageIcon(ResourcePath.getResourcePath()+"button_program.png");
        buttonIconSettings = new ImageIcon(ResourcePath.getResourcePath()+"button_settings.png");
        buttonIconChat = new ImageIcon(ResourcePath.getResourcePath()+"button_chat.png");

        darkButtonIcon = new ImageIcon(ResourcePath.getResourcePath()+"button_dark.png");
        darkButtonIconExercise = new ImageIcon(ResourcePath.getResourcePath()+"button_exercise_dark.png");
        darkButtonIconProgram = new ImageIcon(ResourcePath.getResourcePath()+"button_program_dark.png");
        darkButtonIconSettings = new ImageIcon(ResourcePath.getResourcePath()+"button_settings_dark.png");
        darkButtonIconChat = new ImageIcon(ResourcePath.getResourcePath()+"button_chat_dark.png");

        try{
            font=Font.createFont(Font.TRUETYPE_FONT, new File(ResourcePath.getResourcePath()+"BebasNeue-Regular.otf"));
            font = font.deriveFont((float) (height/17));
        }catch(Exception e){
            font = new Font("Arial", Font.BOLD, 40);
            e.printStackTrace();
        }
        init(width,height);
    }

    private void init(int width, int height){
        this.setLayout(new BorderLayout(0,0));

        JPanel logoContainer = new JPanel(new BorderLayout(0,0));
        logoContainer.setPreferredSize(new Dimension(width/2, height/6));
        logoContainer.setOpaque(false);

        System.out.println((int)(width/8.5));
        Image scaledlogoIcon = logoIcon.getImage().getScaledInstance(height/6, height/6, Image.SCALE_SMOOTH);
        scaledLogoIcon = new ImageIcon(scaledlogoIcon);

        JLabel logoLabel = new JLabel(scaledLogoIcon);

        JPanel logoLabelTextContainer = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));

        logoLabelTextContainer.setOpaque(false);

        logoLabelText.setFont(font.deriveFont(4f));
        logoLabelText.setForeground(Color.WHITE);
        logoLabelTextContainer.add(logoLabelText);
        logoContainer.add(logoLabel, BorderLayout.CENTER);
        logoContainer.add(logoLabelTextContainer, BorderLayout.SOUTH);

        JPanel buttonContainer = new JPanel();
        buttonContainer.setPreferredSize(new Dimension(200,20000));
        buttonContainer.setMinimumSize(new Dimension(200,20000));
        buttonContainer.add(Box.createVerticalStrut(30));

        buttonContainer.setLayout(new BoxLayout(buttonContainer, BoxLayout.Y_AXIS));
        buttonContainer.setOpaque(false);




        final boolean[] homePageIsActive = {true};
        final boolean[] exercisesPageIsActive = {false};
        final boolean[] programPageIsActive = {false};
        final boolean[] settingsPageIsActive = {false};
        final boolean[] chatPageIsActive = {false};




        homeButton.setFont(new Font("Arial", Font.PLAIN,height/35));
        homeButton.setForeground(Color.WHITE);
        homeButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        homeButton.setAlignmentY(Component.CENTER_ALIGNMENT);
        homeButton.setMaximumSize(new Dimension(width-1, 40));
        homeButton.setHorizontalTextPosition(SwingConstants.CENTER);
        homeButton.setVerticalTextPosition(SwingConstants.CENTER);
        homeButton.setFocusPainted(false);
        homeButton.setFocusable(false);
        homeButton.setBorderPainted(false);
        homeButton.setContentAreaFilled(true);
        //Color bg = Color.WHITE;
        Color bg = new Color(51,51,51);
        homeButton.setBackground(bg);

        homeButton.addMouseListener(new MouseAdapter() {

            private boolean isHovered = false;
            @Override
            public void mouseEntered(MouseEvent e) {
                isHovered = true;
                homeButton.setBackground(AppThemeColors.buttonBGHovered);
                homeButton.repaint();
            }

            @Override
            public void mouseExited(MouseEvent e) {
                isHovered = false;
                if(!homePageIsActive[0]){
                    homeButton.setBackground(AppThemeColors.buttonBG);
                }
                homeButton.repaint();
            }

            @Override
            public void mousePressed(MouseEvent e) {
                currentPage = 0;
                exercisesPageIsActive[0] = false;
                programPageIsActive[0] = false;
                settingsPageIsActive[0] = false;
                homePageIsActive[0] = true;
                chatPageIsActive[0] = false;

                if(lightMode){
                    settingsButton.setBackground(Color.WHITE);
                    programButton.setBackground(Color.WHITE);
                    exercisesButton.setBackground(Color.WHITE);
                    chatButton.setBackground(Color.WHITE);
                    homeButton.setBackground(new Color(220, 220, 220));
                }else{
                    settingsButton.setBackground(new Color(51,51,51));
                    programButton.setBackground(new Color(51,51,51));
                    exercisesButton.setBackground(new Color(51,51,51));
                    chatButton.setBackground(new Color(51,51,51));
                    homeButton.setBackground(new Color(30,30,30));
                }



                homeButton.repaint();
                ApplicationWindow.switchWindow("home");
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                if (isHovered) {
                    if(lightMode)
                        homeButton.setBackground(new Color(230, 230, 230));
                    else{
                        homeButton.setBackground(new Color(40,40,40));
                    }
                } else {
                    homeButton.setBackground(bg);
                }
                homeButton.repaint();
            }
        });
        homeButton.setUI(new javax.swing.plaf.basic.BasicButtonUI() {
            @Override
            public void paint(Graphics g, JComponent c) {
                g.setColor(homeButton.getBackground());
                g.fillRoundRect(0, 0, homeButton.getWidth(), homeButton.getHeight(), 10, 10);
                super.paint(g, c);
            }
        });



        /**Exercises button*/
        exercisesButton.setFont(new Font("Arial", Font.PLAIN,height/35));
        exercisesButton.setForeground(Color.WHITE);
        exercisesButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        exercisesButton.setAlignmentY(Component.CENTER_ALIGNMENT);
        exercisesButton.setMaximumSize(new Dimension(width, 40));
        exercisesButton.setHorizontalTextPosition(SwingConstants.CENTER);
        exercisesButton.setVerticalTextPosition(SwingConstants.CENTER);
        Color bg1 = new Color(51,51,51);
        exercisesButton.setFocusPainted(false);
        exercisesButton.setFocusable(false);
        exercisesButton.setBorderPainted(false);
        exercisesButton.setContentAreaFilled(true);
        exercisesButton.setBackground(new Color(40,40,40));
        exercisesButton.addActionListener(e -> {

        });

        exercisesButton.addMouseListener(new MouseAdapter() {
            private boolean isHovered = false;
            @Override
            public void mouseEntered(MouseEvent e) {
                isHovered = true;
                if(lightMode){
                    exercisesButton.setBackground(new Color(230, 230, 230));
                }else{
                    exercisesButton.setBackground(new Color(40,40,40));
                }
                exercisesButton.repaint();
            }

            @Override
            public void mouseExited(MouseEvent e) {
                isHovered = false;
                if(!exercisesPageIsActive[0]){
                    if(lightMode){
                        exercisesButton.setBackground(Color.WHITE);
                    }else{
                        exercisesButton.setBackground(bg1);
                    }
                }
                exercisesButton.repaint();
            }

            @Override
            public void mousePressed(MouseEvent e) {
                currentPage = 1;
                exercisesPageIsActive[0] = true;
                programPageIsActive[0] = false;
                settingsPageIsActive[0] = false;
                homePageIsActive[0] = false;
                chatPageIsActive[0] = false;

                if(lightMode){
                    settingsButton.setBackground(Color.WHITE);
                    programButton.setBackground(Color.WHITE);
                    homeButton.setBackground(Color.WHITE);
                    chatButton.setBackground(Color.WHITE);
                    exercisesButton.setBackground(new Color(220, 220, 220));
                }else{
                    settingsButton.setBackground(new Color(51,51,51));
                    programButton.setBackground(new Color(51,51,51));
                    homeButton.setBackground(new Color(51,51,51));
                    chatButton.setBackground(new Color(51,51,51));
                    exercisesButton.setBackground(new Color(30,30,30));
                }

                ApplicationWindow.switchWindow("exercises");
                exercisesButton.repaint();
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                if (isHovered) {
                    if(lightMode)
                        exercisesButton.setBackground(new Color(230, 230, 230));
                    else{
                        exercisesButton.setBackground(new Color(40,40,40));
                    }
                } else {
                    exercisesButton.setBackground(bg1);
                }
                exercisesButton.repaint();
            }
        });
        exercisesButton.setUI(new javax.swing.plaf.basic.BasicButtonUI() {
            @Override
            public void paint(Graphics g, JComponent c) {
                g.setColor(exercisesButton.getBackground());
                g.fillRoundRect(0, 0, exercisesButton.getWidth(), exercisesButton.getHeight(), 10, 10);
                super.paint(g, c);
            }
        });

        /**Program button*/
        programButton.setFont(new Font("Arial", Font.PLAIN,height/35));
        programButton.setForeground(Color.WHITE);
        programButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        programButton.setAlignmentY(Component.CENTER_ALIGNMENT);
        programButton.setMaximumSize(new Dimension(width,40));
        programButton.setHorizontalTextPosition(SwingConstants.CENTER);
        programButton.setVerticalTextPosition(SwingConstants.CENTER);
        Color bg2 = new Color(51,51,51);
        programButton.setFocusPainted(false);
        programButton.setFocusable(false);
        programButton.setBorderPainted(false);
        programButton.setContentAreaFilled(true);
        programButton.setBackground(bg2);

        programButton.addMouseListener(new MouseAdapter() {
            private boolean isHovered = false;
            @Override
            public void mouseEntered(MouseEvent e) {
                isHovered = true;
                if(lightMode){
                    programButton.setBackground(new Color(230, 230, 230));
                }else{
                    programButton.setBackground(new Color(40,40,40));
                }
                programButton.repaint();
            }

            @Override
            public void mouseExited(MouseEvent e) {
                isHovered = false;
                if(!programPageIsActive[0]){
                    if(lightMode){
                        programButton.setBackground(Color.WHITE);
                    }else{
                        programButton.setBackground(bg2);
                    }
                }
                programButton.repaint();
            }

            @Override
            public void mousePressed(MouseEvent e) {
                currentPage = 2;
                exercisesPageIsActive[0] = false;
                programPageIsActive[0] = true;
                settingsPageIsActive[0] = false;
                homePageIsActive[0] = false;
                chatPageIsActive[0] = false;

                if(lightMode){
                    settingsButton.setBackground(Color.WHITE);
                    homeButton.setBackground(Color.WHITE);
                    exercisesButton.setBackground(Color.WHITE);
                    chatButton.setBackground(Color.WHITE);
                    programButton.setBackground(new Color(220, 220, 220));
                }else{
                    settingsButton.setBackground(new Color(51,51,51));
                    homeButton.setBackground(new Color(51,51,51));
                    exercisesButton.setBackground(new Color(51,51,51));
                    chatButton.setBackground(new Color(51,51,51));
                    programButton.setBackground(new Color(30,30,30));
                }

                ApplicationWindow.switchWindow("program");
                programButton.repaint();
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                if (isHovered) {
                    if(lightMode)
                        programButton.setBackground(new Color(230, 230, 230));
                    else{
                        programButton.setBackground(new Color(40,40,40));
                    }
                } else {
                    programButton.setBackground(bg);
                }
                programButton.repaint();
            }
        });
        programButton.setUI(new javax.swing.plaf.basic.BasicButtonUI() {
            @Override
            public void paint(Graphics g, JComponent c) {
                g.setColor(programButton.getBackground());
                g.fillRoundRect(0, 0, programButton.getWidth(), programButton.getHeight(), 10, 10);
                super.paint(g, c);
            }
        });


        /**Settings Button*/

        settingsButton.setFont(new Font("Arial", Font.PLAIN,height/35));
        settingsButton.setForeground(Color.WHITE);
        settingsButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        settingsButton.setAlignmentY(Component.CENTER_ALIGNMENT);
        settingsButton.setMaximumSize(new Dimension(width, 40));
        settingsButton.setHorizontalTextPosition(SwingConstants.CENTER);
        settingsButton.setVerticalTextPosition(SwingConstants.CENTER);
        Color bg3 = new Color(51,51,51);
        settingsButton.setFocusPainted(false);
        settingsButton.setFocusable(false);
        settingsButton.setBorderPainted(false);
        settingsButton.setContentAreaFilled(true);
        settingsButton.setBackground(bg3);

        settingsButton.addMouseListener(new MouseAdapter() {
            private boolean isHovered = false;
            @Override
            public void mouseEntered(MouseEvent e) {
                isHovered = true;
                settingsButton.setBackground(AppThemeColors.buttonBGHovered);
                settingsButton.repaint();
            }

            @Override
            public void mouseExited(MouseEvent e) {
                isHovered = false;
                if(!settingsPageIsActive[0]){
                    settingsButton.setBackground(AppThemeColors.buttonBG);
                }
                settingsButton.repaint();
            }

            @Override
            public void mousePressed(MouseEvent e) {
                currentPage = 3;
                exercisesPageIsActive[0] = false;
                programPageIsActive[0] = false;
                settingsPageIsActive[0] = true;
                homePageIsActive[0] = false;
                chatPageIsActive[0] = false;

                if(lightMode){
                    homeButton.setBackground(Color.WHITE);
                    programButton.setBackground(Color.WHITE);
                    exercisesButton.setBackground(Color.WHITE);
                    chatButton.setBackground(Color.WHITE);
                    settingsButton.setBackground(new Color(220, 220, 220));
                }else{
                    homeButton.setBackground(new Color(51,51,51));
                    programButton.setBackground(new Color(51,51,51));
                    exercisesButton.setBackground(new Color(51,51,51));
                    chatButton.setBackground(new Color(51,51,51));
                    settingsButton.setBackground(new Color(30,30,30));
                }
                ApplicationWindow.switchWindow("settings");
                settingsButton.repaint();
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                if (isHovered) {
                    if(lightMode)
                        settingsButton.setBackground(new Color(230, 230, 230));
                    else{
                        settingsButton.setBackground(new Color(40,40,40));
                    }
                } else {
                    settingsButton.setBackground(bg);
                }
                settingsButton.repaint();
            }
        });
        settingsButton.setUI(new javax.swing.plaf.basic.BasicButtonUI() {
            @Override
            public void paint(Graphics g, JComponent c) {
                g.setColor(settingsButton.getBackground());
                g.fillRoundRect(0, 0, settingsButton.getWidth(), settingsButton.getHeight(), 10, 10);
                super.paint(g, c);
            }
        });

        /**Chat Button*/

        chatButton.setFont(new Font("Arial", Font.PLAIN,height/35));
        chatButton.setForeground(Color.WHITE);
        chatButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        chatButton.setAlignmentY(Component.CENTER_ALIGNMENT);
        chatButton.setMaximumSize(new Dimension(width, 40));
        chatButton.setHorizontalTextPosition(SwingConstants.CENTER);
        chatButton.setVerticalTextPosition(SwingConstants.CENTER);
        Color bg4 = new Color(51,51,51);
        chatButton.setFocusPainted(false);
        chatButton.setFocusable(false);
        chatButton.setBorderPainted(false);
        chatButton.setContentAreaFilled(true);
        chatButton.setBackground(AppThemeColors.buttonBG);

        chatButton.addMouseListener(new MouseAdapter() {
            private boolean isHovered = false;
            @Override
            public void mouseEntered(MouseEvent e) {
                isHovered = true;
                chatButton.setBackground(AppThemeColors.buttonBGHovered);
                chatButton.repaint();
            }

            @Override
            public void mouseExited(MouseEvent e) {
                isHovered = false;
                if(!chatPageIsActive[0]){
                    chatButton.setBackground(AppThemeColors.buttonBG);
                }
                chatButton.repaint();
            }

            @Override
            public void mousePressed(MouseEvent e) {
                currentPage = 4;
                exercisesPageIsActive[0] = false;
                programPageIsActive[0] = false;
                settingsPageIsActive[0] = false;
                homePageIsActive[0] = false;
                chatPageIsActive[0] = true;

                homeButton.setBackground(AppThemeColors.PRIMARY);
                programButton.setBackground(AppThemeColors.PRIMARY);
                exercisesButton.setBackground(AppThemeColors.PRIMARY);
                settingsButton.setBackground(AppThemeColors.PRIMARY);
                chatButton.setBackground(AppThemeColors.PRIMARY);
                ApplicationWindow.switchWindow("chat");
                settingsButton.repaint();
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                if (isHovered) {
                    if(lightMode)
                        chatButton.setBackground(new Color(230, 230, 230));
                    else{
                        chatButton.setBackground(new Color(40,40,40));
                    }
                } else {
                    chatButton.setBackground(bg);
                }
                chatButton.repaint();
            }
        });
        chatButton.setUI(new javax.swing.plaf.basic.BasicButtonUI() {
            @Override
            public void paint(Graphics g, JComponent c) {
                g.setColor(chatButton.getBackground());
                g.fillRoundRect(0, 0, chatButton.getWidth(), chatButton.getHeight(), 10, 10);
                super.paint(g, c);
            }
        });


        buttonContainer.add(homeButton);
       //buttonContainer.add(Box.createVerticalGlue());
        buttonContainer.add(exercisesButton);
        //buttonContainer.add(Box.createVerticalGlue());
        buttonContainer.add(programButton);
       // buttonContainer.add(Box.createVerticalGlue());
        buttonContainer.add(settingsButton);
        buttonContainer.add(chatButton);
        buttonContainer.add(Box.createVerticalGlue());
        buttonContainer.add(Box.createVerticalGlue());
        buttonContainer.add(Box.createVerticalGlue());
        this.add(logoContainer, BorderLayout.NORTH);
        this.add(buttonContainer, BorderLayout.CENTER);


        switch (SettingsPanel.currentTheme){
            case "dark"->{
                scaledbuttonHomeIcon = buttonIcon.getImage().getScaledInstance(151, 40, Image.SCALE_SMOOTH);
                scaledButtonHomeIcon = new ImageIcon(scaledbuttonHomeIcon);

                scaledButtonExercise = buttonIconExercise.getImage().getScaledInstance(151, 40, Image.SCALE_SMOOTH);
                scaledButtonExerciseIcon = new ImageIcon(scaledButtonExercise);

                scaledButtonSettings = buttonIconSettings.getImage().getScaledInstance(151, 40, Image.SCALE_SMOOTH);
                scaledButtonSettingsIcon = new ImageIcon(scaledButtonSettings);
            }
            case "light"->{
                scaleddarkButtonHomeIcon = darkButtonIcon.getImage().getScaledInstance(151,30, Image.SCALE_SMOOTH);
                scaledDarkButtonHomeIcon = new ImageIcon(scaleddarkButtonHomeIcon);

                scaleddarkButtonExerciseIcon = darkButtonIconExercise.getImage().getScaledInstance(151,30, Image.SCALE_SMOOTH);
                scaledDarkButtonExerciseIcon = new ImageIcon(scaleddarkButtonExerciseIcon);

                scaleddarkButtonSettingsIcon = darkButtonIconSettings.getImage().getScaledInstance(151,30, Image.SCALE_SMOOTH);
                scaledDarkButtonSettingsIcon = new ImageIcon(scaleddarkButtonSettingsIcon);

            }
        }

        //Handles the resizing of the components
        this.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                SwingUtilities.invokeLater(()->{
                    Image scaledlogoIcon = logoIcon.getImage().getScaledInstance(getHeight()/8, getHeight()/8, Image.SCALE_SMOOTH);
                    scaledLogoIcon = new ImageIcon(scaledlogoIcon);
                    logoLabel.setIcon(scaledLogoIcon);

                    logoContainer.setPreferredSize(new Dimension(getWidth(), getHeight()/4));

                    logoLabelText.setFont(font.deriveFont((float) (getHeight()/17)));




                    homeButton.setPreferredSize(new Dimension(getWidth(),(int)(getHeight()/13.5)));
                    homeButton.setMaximumSize(new Dimension(getWidth(),(int)(getHeight()/13.5)));
                    homeButton.setFont(new Font("Arial", Font.PLAIN,getHeight()/35));

                    exercisesButton.setPreferredSize(new Dimension(getWidth(),(int)(getHeight()/13.5)));
                    exercisesButton.setMaximumSize(new Dimension(getWidth(),(int)(getHeight()/13.5)));
                    exercisesButton.setFont(new Font("Arial", Font.PLAIN,getHeight()/35));

                    programButton.setPreferredSize(new Dimension(getWidth(),(int)(getHeight()/13.5)));
                    programButton.setMaximumSize(new Dimension(getWidth(),(int)(getHeight()/13.5)));
                    programButton.setFont(new Font("Arial", Font.PLAIN,getHeight()/35));

                    settingsButton.setPreferredSize(new Dimension(getWidth(),(int)(getHeight()/13.5)));
                    settingsButton.setMaximumSize(new Dimension(getWidth(),(int)(getHeight()/13.5)));
                    settingsButton.setFont(new Font("Arial", Font.PLAIN,getHeight()/35));



                    chatButton.setIcon(scaledButtonSettingsIcon);
                    chatButton.setPreferredSize(new Dimension(getWidth(),(int)(getHeight()/13.5)));
                    chatButton.setMaximumSize(new Dimension(getWidth(),(int)(getHeight()/13.5)));
                    chatButton.setFont(new Font("Arial", Font.PLAIN,getHeight()/35));


                    switch (SettingsPanel.currentTheme){
                        case "dark"->{
                            scaledbuttonHomeIcon = buttonIcon.getImage().getScaledInstance(getWidth(), (int)(getHeight()/13.5), Image.SCALE_SMOOTH);
                            scaledButtonHomeIcon = new ImageIcon(scaledbuttonHomeIcon);
                            scaledButtonExercise = buttonIconExercise.getImage().getScaledInstance(getWidth(), (int)(getHeight()/13.5), Image.SCALE_SMOOTH);
                            scaledButtonExerciseIcon = new ImageIcon(scaledButtonExercise);
                            scaledButtonProgram = buttonIconProgram.getImage().getScaledInstance(getWidth(),(int)(getHeight()/13.5), Image.SCALE_SMOOTH);
                            scaledButtonProgramIcon = new ImageIcon(scaledButtonProgram);
                            scaledButtonSettings = buttonIconSettings.getImage().getScaledInstance(getWidth(), (int)(getHeight()/13.5), Image.SCALE_SMOOTH);
                            scaledButtonSettingsIcon = new ImageIcon(scaledButtonSettings);
                            scaledButtonChat = buttonIconChat.getImage().getScaledInstance(getWidth(),(int)(getHeight()/13.5), Image.SCALE_SMOOTH);
                            scaledButtonChatIcon = new ImageIcon(scaledButtonChat);

                            homeButton.setIcon(scaledButtonHomeIcon);
                            exercisesButton.setIcon(scaledButtonExerciseIcon);
                            programButton.setIcon(scaledButtonProgramIcon);
                            settingsButton.setIcon(scaledButtonSettingsIcon);
                            chatButton.setIcon(scaledButtonChatIcon);
                        }
                        case "light"->{
                            scaleddarkButtonHomeIcon = darkButtonIcon.getImage().getScaledInstance(getWidth(),(int)(getHeight()/13.5), Image.SCALE_SMOOTH);
                            scaledDarkButtonHomeIcon = new ImageIcon(scaleddarkButtonHomeIcon);
                            scaleddarkButtonExerciseIcon = darkButtonIconExercise.getImage().getScaledInstance(getWidth(), (int)(getHeight()/13.5), Image.SCALE_SMOOTH);
                            scaledDarkButtonExerciseIcon = new ImageIcon(scaleddarkButtonExerciseIcon);
                            scaleddarkButtonProgramIcon = darkButtonIconProgram.getImage().getScaledInstance(getWidth(),(int)(getHeight()/13.5), Image.SCALE_SMOOTH);
                            scaledDarkButtonProgramIcon = new ImageIcon(scaleddarkButtonProgramIcon);
                            scaleddarkButtonSettingsIcon = darkButtonIconSettings.getImage().getScaledInstance(getWidth(), (int)(getHeight()/13.5), Image.SCALE_SMOOTH);
                            scaledDarkButtonSettingsIcon = new ImageIcon(scaleddarkButtonSettingsIcon);
                            scaleddarkButtonChatIcon = darkButtonIconChat.getImage().getScaledInstance(getWidth(),(int)(getHeight()/13.5), Image.SCALE_SMOOTH);
                            scaledDarkButtonChatIcon = new ImageIcon(scaleddarkButtonChatIcon);

                            homeButton.setIcon(scaledDarkButtonHomeIcon);
                            exercisesButton.setIcon(scaledDarkButtonExerciseIcon);
                            programButton.setIcon(scaledDarkButtonProgramIcon);
                            settingsButton.setIcon(scaledDarkButtonSettingsIcon);
                            chatButton.setIcon(scaledDarkButtonChatIcon);
                        }
                    }

                    revalidate();
                    repaint();
                });
            }
        });
    }

    public void updateButtonColor(){

        switch(SettingsPanel.currentTheme){
            case "dark"->{
                logoLabelText.setForeground(Color.WHITE);
                lightMode = false;

                homeButton.setForeground(AppThemeColors.foregroundColor);
                exercisesButton.setForeground(AppThemeColors.foregroundColor);
                programButton.setForeground(AppThemeColors.foregroundColor);
                settingsButton.setForeground(AppThemeColors.foregroundColor);
                chatButton.setForeground(AppThemeColors.foregroundColor);
            }

            case "light"->{
                logoLabelText.setForeground(AppThemeColors.foregroundColor);
                lightMode = true;

                homeButton.setForeground(AppThemeColors.foregroundColor);
                exercisesButton.setForeground(AppThemeColors.foregroundColor);
                programButton.setForeground(AppThemeColors.foregroundColor);
                settingsButton.setForeground(AppThemeColors.foregroundColor);
                chatButton.setForeground(AppThemeColors.foregroundColor);
            }
        }

        homeButton.setBackground(AppThemeColors.buttonBG);
        exercisesButton.setBackground(AppThemeColors.buttonBG);
        programButton.setBackground(AppThemeColors.buttonBG);
        settingsButton.setBackground(AppThemeColors.buttonBG);
        chatButton.setBackground(AppThemeColors.buttonBG);

        switch (currentPage){
            case 0-> homeButton.setBackground(AppThemeColors.buttonBGSelected);
            case 1-> exercisesButton.setBackground(AppThemeColors.buttonBGSelected);
            case 2-> programButton.setBackground(AppThemeColors.buttonBGSelected);
            case 3-> settingsButton.setBackground(AppThemeColors.buttonBGSelected);
            case 4-> chatButton.setBackground(AppThemeColors.buttonBGSelected);
        }

    }


    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        // Draw the image to fill the entire panel

        if (menuBackground != null) {
            if(!SettingsPanel.lightMode){
                g.drawImage(menuBackground.getImage(), 0, 0, getWidth(), getHeight(), this);
                updateButtonColor();
            }else{
                g.drawImage(lightMenuBackground.getImage(), 0, 0, getWidth(), getHeight(), this);
                updateButtonColor();
            }
        }

        switch (SettingsPanel.currentTheme){
            case "dark"->{
                scaledbuttonHomeIcon = buttonIcon.getImage().getScaledInstance(getWidth(), (int)(getHeight()/13.5), Image.SCALE_SMOOTH);
                scaledButtonHomeIcon = new ImageIcon(scaledbuttonHomeIcon);
                scaledButtonExercise = buttonIconExercise.getImage().getScaledInstance(getWidth(), (int)(getHeight()/13.5), Image.SCALE_SMOOTH);
                scaledButtonExerciseIcon = new ImageIcon(scaledButtonExercise);
                scaledButtonProgram = buttonIconProgram.getImage().getScaledInstance(getWidth(),(int)(getHeight()/13.5), Image.SCALE_SMOOTH);
                scaledButtonProgramIcon = new ImageIcon(scaledButtonProgram);
                scaledButtonSettings = buttonIconSettings.getImage().getScaledInstance(getWidth(), (int)(getHeight()/13.5), Image.SCALE_SMOOTH);
                scaledButtonSettingsIcon = new ImageIcon(scaledButtonSettings);
                scaledButtonChat = buttonIconChat.getImage().getScaledInstance(getWidth(),(int)(getHeight()/13.5), Image.SCALE_SMOOTH);
                scaledButtonChatIcon = new ImageIcon(scaledButtonChat);

                homeButton.setIcon(scaledButtonHomeIcon);
                exercisesButton.setIcon(scaledButtonExerciseIcon);
                programButton.setIcon(scaledButtonProgramIcon);
                settingsButton.setIcon(scaledButtonSettingsIcon);
                chatButton.setIcon(scaledButtonChatIcon);
            }
            case "light"->{
                scaleddarkButtonHomeIcon = darkButtonIcon.getImage().getScaledInstance(getWidth(),(int)(getHeight()/13.5), Image.SCALE_SMOOTH);
                scaledDarkButtonHomeIcon = new ImageIcon(scaleddarkButtonHomeIcon);
                scaleddarkButtonExerciseIcon = darkButtonIconExercise.getImage().getScaledInstance(getWidth(), (int)(getHeight()/13.5), Image.SCALE_SMOOTH);
                scaledDarkButtonExerciseIcon = new ImageIcon(scaleddarkButtonExerciseIcon);
                scaleddarkButtonProgramIcon = darkButtonIconProgram.getImage().getScaledInstance(getWidth(),(int)(getHeight()/13.5), Image.SCALE_SMOOTH);
                scaledDarkButtonProgramIcon = new ImageIcon(scaleddarkButtonProgramIcon);
                scaleddarkButtonSettingsIcon = darkButtonIconSettings.getImage().getScaledInstance(getWidth(), (int)(getHeight()/13.5), Image.SCALE_SMOOTH);
                scaledDarkButtonSettingsIcon = new ImageIcon(scaleddarkButtonSettingsIcon);
                scaleddarkButtonChatIcon = darkButtonIconChat.getImage().getScaledInstance(getWidth(),(int)(getHeight()/13.5), Image.SCALE_SMOOTH);
                scaledDarkButtonChatIcon = new ImageIcon(scaleddarkButtonChatIcon);

                homeButton.setIcon(scaledDarkButtonHomeIcon);
                exercisesButton.setIcon(scaledDarkButtonExerciseIcon);
                programButton.setIcon(scaledDarkButtonProgramIcon);
                settingsButton.setIcon(scaledDarkButtonSettingsIcon);
                chatButton.setIcon(scaledDarkButtonChatIcon);
            }
        }
    }
}
