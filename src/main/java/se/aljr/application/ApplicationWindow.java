package se.aljr.application;

import se.aljr.application.exercise.ExercisePanel;
import se.aljr.application.homepage.*;
import se.aljr.application.programplanner.ProgramPanel;
import se.aljr.application.settings.SettingsPanel;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

public class ApplicationWindow extends JFrame  {
    private static boolean menu = true;
    final String applicationIconPath = "src/main/resources/agile_small_icon.png";
    static int pageSelector;

    ImageIcon applicationIcon = new ImageIcon(applicationIconPath);

    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    int screenWidth = screenSize.width;
    int screenHeight = screenSize.height;

    public ApplicationWindow(int width, int height, String applicationTitle) throws InterruptedException {
        this.setUndecorated(true);
        this.setTitle(applicationTitle); //Sätter titeln av fönstret
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //Terminerar programet vid stängning.
        this.setMinimumSize(new Dimension(width,height));
        this.setPreferredSize(new Dimension(width,height)); //Sätter bredd och höjd för fönstret
         //Sätter fönstret till synlig
        setApplicationLogo();


        this.setLocationRelativeTo(null);
        this.getContentPane().setBackground(new Color(31,31,31));

        ResizeHandler resizeHandler = new ResizeHandler(this, ((double) width /height)); // Aspect ratio (t.ex. 4:3)
        this.addMouseListener(resizeHandler);
        this.addMouseMotionListener(resizeHandler);
        init();
        }

    private void init() throws InterruptedException {
        this.setLayout(new BorderLayout(0,0)); //Sätter Fönstrets layout till BorderLayout

        LeftPanel left_panel = new LeftPanel(); //Skapar den vänstra sektionen för fönstret
        left_panel.setPreferredSize(new Dimension((int)((getWidth()/6.4)),getHeight()-getHeight()/18));
        left_panel.setMinimumSize(new Dimension((int)((getWidth()/6.4)), getHeight()/18));
        left_panel.setLayout(new FlowLayout(FlowLayout.LEFT, (int)((getWidth()/150)), (int)((getWidth()/150))));
        left_panel.setOpaque(false);


        RightPanel right_panel = new RightPanel(); //Skapar den högra sektionen för fönstret
        right_panel.setMaximumSize(new Dimension(this.getWidth()-(int)((getWidth()/6.4)), getHeight()-getHeight()/18));
        right_panel.setPreferredSize(new Dimension(this.getWidth()-(int)((getWidth()/6.4)), this.getHeight()));
        //right_panel.setLayout(new FlowLayout(FlowLayout.LEFT,(getWidth()/150),getWidth()/150));
        right_panel.setBorder(new EmptyBorder(getWidth()/150,0,getWidth()/150,getWidth()/150));

        MenuPanel menuPanel = new MenuPanel((int)(getWidth()/6.4-(2*getWidth()/150)),(int)(getHeight()-getHeight()/18-(2*getWidth()/150))); //Skapar Meny panelen
        menuPanel.setMinimumSize(new Dimension((int)(getWidth()/6.4-(2*getWidth()/150)), (int)(getHeight()-getHeight()/18-(2*getWidth()/150))));
        menuPanel.setPreferredSize(new Dimension((int)(getWidth()/6.4-(2*getWidth()/150)), (int)(getHeight()-getHeight()/18-(2*getWidth()/150))));

        TopBar top_bar = new TopBar(this); //Skapar toppen baren
        top_bar.setPreferredSize(new Dimension(getWidth(), (int)(getHeight()/18)));

        HomePanel content_panel = new HomePanel((int)(getWidth()-(getWidth()/6.4)-2*getWidth()/150), getHeight()-getHeight()/18-2*getWidth()/150); //Skapar innehålls panelen
        content_panel.setMinimumSize(new Dimension((int)(getWidth()-(getWidth()/6.4)-2*getWidth()/150), getHeight()-top_bar.getHeight()-2*getWidth()/150));
        content_panel.setMaximumSize(new Dimension((int)(getWidth()-(getWidth()/6.4)-2*getWidth()/150), getHeight()-top_bar.getHeight()-2*getWidth()/150));

        ExercisePanel exercisePanel = new ExercisePanel((int)(getWidth()-(getWidth()/6.4)-2*getWidth()/150), getHeight()-getHeight()/18-2*getWidth()/150);
        exercisePanel.setVisible(false);

        ProgramPanel programPanel = new ProgramPanel((int)(getWidth()-(getWidth()/6.4)-2*getWidth()/150), getHeight()-getHeight()/18-2*getWidth()/150);
        programPanel.setVisible(false);

        SettingsPanel settingsPanel = new SettingsPanel((int)(getWidth()-(getWidth()/6.4)), getHeight()-getHeight()/13);
        settingsPanel.setVisible(false);

        ChatPanel chatPanel = new ChatPanel((int)(getWidth()-(getWidth()/6.4)), getHeight()-getHeight()/13);
        chatPanel.setVisible(false);

        left_panel.add(menuPanel);


        right_panel.add(content_panel);

        //Handles the resizing of the components
        this.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                SwingUtilities.invokeLater(()->{
                    programPanel.setPreferredSize(new Dimension((int)(getWidth()-(getWidth()/6.4)-2*getWidth()/150), getHeight()-top_bar.getHeight()-2*getWidth()/150));

                    left_panel.setLayout(new FlowLayout(FlowLayout.LEFT, (int)((getWidth()/150)), (int)((getWidth()/150))));
                    left_panel.setPreferredSize(new Dimension((int)((getWidth()/6.4)),getHeight()-getHeight()/18));
                    left_panel.setMinimumSize(new Dimension((int)((getWidth()/6.4)), getHeight()/18));

                    right_panel.setMaximumSize(new Dimension(getWidth()-(int)((getWidth()/6.4)), getHeight()));
                    right_panel.setPreferredSize(new Dimension(getWidth()-(int)((getWidth()/6.4)), getHeight()));
                    right_panel.setBorder(new EmptyBorder(getWidth()/150,0,getWidth()/150,getWidth()/150));

                    menuPanel.setMinimumSize(new Dimension((int)(getWidth()/6.4-(2*getWidth()/150)), (int)(getHeight()-top_bar.getHeight()-(2*getWidth()/150))));
                    menuPanel.setPreferredSize(new Dimension((int)(getWidth()/6.4-(2*getWidth()/150)), (int)(getHeight()-top_bar.getHeight()-(2*getWidth()/150))));
                    content_panel.setPreferredSize(new Dimension((int)(getWidth()-(getWidth()/6.4)), getHeight()-top_bar.getHeight()));
                    content_panel.reScaleBackground();
                    settingsPanel.setPreferredSize(new Dimension((int)(getWidth()-(getWidth()/6.4)), getHeight()-top_bar.getHeight()));

                    settingsPanel.setPreferredSize(new Dimension((int)(getWidth()-(getWidth()/6.4)-2*getWidth()/150), getHeight()-top_bar.getHeight()-2*getWidth()/150));

                    exercisePanel.setPreferredSize(new Dimension((int)(getWidth()-(getWidth()/6.4)-2*getWidth()/150), getHeight()-top_bar.getHeight()-2*getWidth()/150));

                    revalidate();
                    repaint();

                });
            }
        });

        this.add(right_panel, BorderLayout.EAST);
        this.add(left_panel, BorderLayout.WEST);
        this.add(top_bar, BorderLayout.NORTH);
        this.setResizable(false);
        this.setVisible(true);
        new Timer(100, e -> {
            switch(pageSelector){

                /**Home Panel*/
                case 1->{
                    right_panel.remove(exercisePanel);
                    right_panel.remove(programPanel);
                    right_panel.remove(settingsPanel);
                    right_panel.add(content_panel);
                    exercisePanel.setVisible(false);
                    settingsPanel.setVisible(false);
                    programPanel.setVisible(false);
                    chatPanel.setVisible(false);
                    content_panel.setVisible(true);
                    repaint();
                }
                /**Exercises Panel*/
                case 2->{
                    right_panel.remove(content_panel);
                    right_panel.remove(programPanel);
                    right_panel.remove(settingsPanel);
                    right_panel.remove(chatPanel);
                    right_panel.add(exercisePanel, BorderLayout.SOUTH);
                    content_panel.setVisible(false);
                    settingsPanel.setVisible(false);
                    programPanel.setVisible(false);
                    chatPanel.setVisible(false);
                    exercisePanel.setVisible(true);
                }
                /**Program Panel*/
                case 3->{
                    right_panel.remove(content_panel);
                    right_panel.remove(exercisePanel);
                    right_panel.remove(settingsPanel);
                    right_panel.remove(chatPanel);
                    right_panel.add(programPanel, BorderLayout.SOUTH);
                    content_panel.setVisible(false);
                    exercisePanel.setVisible(false);
                    settingsPanel.setVisible(false);
                    chatPanel.setVisible(false);
                    programPanel.setVisible(true);
                }
                /**Settings Panel*/
                case 4->{
                    right_panel.remove(content_panel);
                    right_panel.remove(exercisePanel);
                    right_panel.remove(programPanel);
                    right_panel.remove(chatPanel);
                    right_panel.add(settingsPanel, BorderLayout.SOUTH);
                    content_panel.setVisible(false);
                    exercisePanel.setVisible(false);
                    programPanel.setVisible(false);
                    chatPanel.setVisible(false);
                    settingsPanel.setVisible(true);
                }
                /**Chat Panel*/
                case 5->{
                    right_panel.remove(content_panel);
                    right_panel.remove(exercisePanel);
                    right_panel.remove(programPanel);
                    right_panel.remove(settingsPanel);
                    right_panel.add(chatPanel, BorderLayout.SOUTH);
                    content_panel.setVisible(false);
                    exercisePanel.setVisible(false);
                    programPanel.setVisible(false);
                    settingsPanel.setVisible(false);
                    chatPanel.setVisible(true);
                }

            }
            pageSelector=0;
        }).start();

    }
    public static void switchWindow(String window){

        switch(window){
            case "home"->{
                pageSelector = 1;
            }
            case "exercises"->{
                pageSelector = 2;
            }
            case "program"->{
                pageSelector = 3;
            }
            case "settings"->{
                pageSelector = 4;
            }
            case "chat"->{
                pageSelector = 5;
            }
        }
    }
    private void setApplicationLogo(){
        this.setIconImage(applicationIcon.getImage());
    }






}
