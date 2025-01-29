package se.aljr.application;

import se.aljr.application.exercise.ExercisePanel;
import se.aljr.application.homepage.*;
import se.aljr.application.programplanner.ProgramPanel;
import se.aljr.application.settings.SettingsPanel;

import javax.swing.*;
import java.awt.*;

public class ApplicationWindow extends JFrame  {
    private static boolean menu = true;
    final String applicationIconPath = "src/main/resources/agile_small_icon.png";
    private static int pageSelector;

    ImageIcon applicationIcon = new ImageIcon(applicationIconPath);

    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    int screenWidth = screenSize.width;
    int screenHeight = screenSize.height;

    ApplicationWindow(int width, int height, String applicationTitle) throws InterruptedException {

        this.setUndecorated(true);
        this.setTitle(applicationTitle); //Sätter titeln av fönstret
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //Terminerar programet vid stängning.
        this.setSize(width,height); //Sätter bredd och höjd för fönstret
         //Sätter fönstret till synlig
        setApplicationLogo();
        this.setLocationRelativeTo(null);
        init();
        }

    private void init() throws InterruptedException {
        this.setLayout(new BorderLayout(0,0)); //Sätter Fönstrets layout till BorderLayout

        MenuPanel menuPanel = new MenuPanel(); //Skapar Meny panelen

        TopBar top_bar = new TopBar(this); //Skapar toppen baren

        ContentPanel content_panel = new ContentPanel(this.getWidth()-menuPanel.getWidth(), this.getHeight()-top_bar.getHeight()-41); //Skapar innehålls panelen

        ExercisePanel exercisePanel = new ExercisePanel(this.getWidth()-menuPanel.getWidth(), this.getHeight()-top_bar.getHeight()-41);
        exercisePanel.setVisible(false);

        ProgramPanel programPanel = new ProgramPanel(this.getWidth()-menuPanel.getWidth(), this.getHeight()-top_bar.getHeight()-41);
        programPanel.setVisible(false);

        SettingsPanel settingsPanel = new SettingsPanel(this.getWidth()-menuPanel.getWidth(), this.getHeight()-top_bar.getHeight()-41);
        settingsPanel.setVisible(false);

        System.out.println(this.getWidth()-menuPanel.getWidth());
        LeftPanel left_panel = new LeftPanel(); //Skapar den vänstra sektionen för fönstret

        RightPanel right_panel = new RightPanel(); //Skapar den högra sektionen för fönstret
        right_panel.setPreferredSize(new Dimension(this.getWidth()-menuPanel.getWidth(), this.getHeight()));




        left_panel.add(menuPanel);

        right_panel.add(top_bar,BorderLayout.NORTH);
        right_panel.add(content_panel, BorderLayout.SOUTH);


        this.add(right_panel, BorderLayout.EAST);
        this.add(left_panel, BorderLayout.WEST);
        this.pack();
        this.setResizable(false);
        this.setVisible(true);
        while(true){
            Thread.sleep(100);

            switch(pageSelector){
                /**Home Panel*/
                case 1->{
                    right_panel.remove(exercisePanel);
                    right_panel.remove(programPanel);
                    right_panel.remove(settingsPanel);
                    right_panel.add(content_panel, BorderLayout.SOUTH);
                    exercisePanel.setVisible(false);
                    settingsPanel.setVisible(false);
                    programPanel.setVisible(false);
                    content_panel.setVisible(true);
                }
                /**Exercises Panel*/
                case 2->{
                    right_panel.remove(content_panel);
                    right_panel.remove(programPanel);
                    right_panel.remove(settingsPanel);
                    right_panel.add(exercisePanel, BorderLayout.SOUTH);
                    content_panel.setVisible(false);
                    settingsPanel.setVisible(false);
                    programPanel.setVisible(false);
                    exercisePanel.setVisible(true);
                }
                /**Program Panel*/
                case 3->{
                    right_panel.remove(content_panel);
                    right_panel.remove(exercisePanel);
                    right_panel.remove(settingsPanel);
                    right_panel.add(programPanel, BorderLayout.SOUTH);
                    content_panel.setVisible(false);
                    exercisePanel.setVisible(false);
                    settingsPanel.setVisible(false);
                    programPanel.setVisible(true);
                }
                /**Settings Panel*/
                case 4->{
                    right_panel.remove(content_panel);
                    right_panel.remove(exercisePanel);
                    right_panel.remove(programPanel);
                    right_panel.add(settingsPanel, BorderLayout.SOUTH);
                    content_panel.setVisible(false);
                    exercisePanel.setVisible(false);
                    programPanel.setVisible(false);
                    settingsPanel.setVisible(true);
                }
            }
        }
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
        }
    }
    private void setApplicationLogo(){
        this.setIconImage(applicationIcon.getImage());
    }






}
