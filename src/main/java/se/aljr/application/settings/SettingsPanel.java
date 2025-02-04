package se.aljr.application.settings;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.io.File;

public class SettingsPanel extends JPanel {

    ImageIcon settingsPanelBackground;
    Image scaleSettingsPanelBackground;
    ImageIcon scaledSettingsPanelBackgroundIcon;
    Font font;
    private String resourcePath;


    public SettingsPanel(int width, int height){
        resourcePath = getClass().getClassLoader().getResource("resource.path").getPath().replace("resource.path","");
        settingsPanelBackground = new ImageIcon(resourcePath+"emptyBackground.png");
        scaleSettingsPanelBackground = settingsPanelBackground.getImage().getScaledInstance(width,height, Image.SCALE_SMOOTH);
        scaledSettingsPanelBackgroundIcon = new ImageIcon(scaleSettingsPanelBackground);

        this.setPreferredSize(new Dimension(width, height));
        this.setOpaque(false);
        this.setLayout(new BorderLayout(0,0));
        this.setBorder(new EmptyBorder(0,11,0,11));
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

        /**Container for all the panels*/
        JPanel containerPanel = new JPanel();
        containerPanel.setLayout(new BoxLayout(containerPanel, BoxLayout.X_AXIS));
        containerPanel.setSize(new Dimension(width, height));
        containerPanel.setOpaque(false);

        /**Creating the left panel*/
        JPanel leftPanel = new JPanel();
        leftPanel.setLayout(new BorderLayout(0,0));
        leftPanel.setPreferredSize(new Dimension(width/3, height));
        leftPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
        leftPanel.setBackground(Color.black);

        /**Right panel*/
        JPanel rightPanel = new JPanel();
        rightPanel.setLayout(new BorderLayout(0,0));
        rightPanel.setPreferredSize(new Dimension(width-width/3, height));
        rightPanel.setBackground(new Color(51,51,51));

        /**Padding panel*/
        JPanel paddingPanel = new JPanel();
        paddingPanel.setPreferredSize(new Dimension(width/10, height));
        paddingPanel.setBackground(new Color(31,31,31));

        /**Button Panel*/
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));
        buttonPanel.setPreferredSize(new Dimension(width/3, height));
        buttonPanel.setBackground(new Color(51,51,51));


        /**Buttons*/
        JButton button1 = new JButton("General");
        button1.setFont(font.deriveFont(20f));
        Color bg1 = new Color(200, 200, 200,255);
        button1.setFocusPainted(false);
        button1.setFocusable(false);
        button1.setBorderPainted(false);
        button1.setContentAreaFilled(true);
        button1.setBackground(bg1);
        button1.setAlignmentX(Component.CENTER_ALIGNMENT);

        JButton button2 = new JButton("Theme");
        button2.setFont(font.deriveFont(20f));
        button2.setFocusPainted(false);
        button2.setFocusable(false);
        button2.setBorderPainted(false);
        button2.setContentAreaFilled(true);
        button2.setBackground(bg1);
        button2.setAlignmentX(Component.CENTER_ALIGNMENT);

        JButton button3 = new JButton("Notifications");
        button3.setFont(font.deriveFont(20f));
        button3.setFocusPainted(false);
        button3.setFocusable(false);
        button3.setBorderPainted(false);
        button3.setContentAreaFilled(true);
        button3.setBackground(bg1);
        button3.setAlignmentX(Component.CENTER_ALIGNMENT);

        JButton button4 = new JButton("Account");
        button4.setFont(font.deriveFont(20f));
        button4.setFocusPainted(false);
        button4.setFocusable(false);
        button4.setBorderPainted(false);
        button4.setContentAreaFilled(true);
        button4.setBackground(bg1);
        button4.setAlignmentX(Component.CENTER_ALIGNMENT);

        JButton button5 = new JButton("Privacy");
        button5.setFont(font.deriveFont(20f));
        button5.setFocusPainted(false);
        button5.setFocusable(false);
        button5.setBorderPainted(false);
        button5.setContentAreaFilled(true);
        button5.setBackground(bg1);
        button5.setAlignmentX(Component.CENTER_ALIGNMENT);

        button1.setPreferredSize(new Dimension(width, height/8));
        button1.setMaximumSize(new Dimension(width-width/3*2, height/3));

        button2.setPreferredSize(new Dimension(width, height/8));
        button2.setMaximumSize(new Dimension(width-width/3*2, height/3));

        button3.setPreferredSize(new Dimension(width, height/8));
        button3.setMaximumSize(new Dimension(width-width/3*2, height/3));

        button4.setPreferredSize(new Dimension(width, height/8));
        button4.setMaximumSize(new Dimension(width-width/3*2, height/3));

        button5.setPreferredSize(new Dimension(width, height/8));
        button5.setMaximumSize(new Dimension(width-width/3*2, height/3));



        buttonPanel.add(Box.createVerticalGlue());
        buttonPanel.add(Box.createVerticalGlue());
        buttonPanel.add(Box.createVerticalGlue());
        buttonPanel.add(Box.createVerticalGlue());
        buttonPanel.add(Box.createVerticalGlue());
        buttonPanel.add(Box.createVerticalGlue());
        buttonPanel.add(Box.createVerticalGlue());
        buttonPanel.add(Box.createVerticalGlue());
        buttonPanel.add(Box.createVerticalGlue());
        buttonPanel.add(button1);
        buttonPanel.add(Box.createVerticalGlue());
        buttonPanel.add(Box.createVerticalGlue());
        buttonPanel.add(Box.createVerticalGlue());
        buttonPanel.add(button2);
        buttonPanel.add(Box.createVerticalGlue());
        buttonPanel.add(Box.createVerticalGlue());
        buttonPanel.add(Box.createVerticalGlue());
        buttonPanel.add(button3);
        buttonPanel.add(Box.createVerticalGlue());
        buttonPanel.add(Box.createVerticalGlue());
        buttonPanel.add(Box.createVerticalGlue());
        buttonPanel.add(button4);
        buttonPanel.add(Box.createVerticalGlue());
        buttonPanel.add(Box.createVerticalGlue());
        buttonPanel.add(Box.createVerticalGlue());
        buttonPanel.add(button5);
        buttonPanel.add(Box.createVerticalGlue());
        buttonPanel.add(Box.createVerticalGlue());



        this.add(containerPanel);
        containerPanel.add(leftPanel);
        containerPanel.add(rightPanel);
        rightPanel.add(paddingPanel, BorderLayout.WEST);

        leftPanel.add(buttonPanel);

        this.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                SwingUtilities.invokeLater(()->{
                    leftPanel.setPreferredSize(new Dimension(getWidth()/3, getHeight()));
                    buttonPanel.setPreferredSize(new Dimension(getWidth()/3,getHeight()));
                    rightPanel.setPreferredSize(new Dimension(getWidth()-getWidth()/3, getHeight()));
                    paddingPanel.setPreferredSize(new Dimension(getWidth()/10, getHeight()));


                    button1.setPreferredSize(new Dimension(getWidth(), getHeight()/8));
                    button1.setMaximumSize(new Dimension(getWidth()-getWidth()/3*2, getHeight()/3));

                    button2.setPreferredSize(new Dimension(getWidth(), getHeight()/8));
                    button2.setMaximumSize(new Dimension(getWidth()-getWidth()/3*2, getHeight()/3));

                    button3.setPreferredSize(new Dimension(getWidth(), getHeight()/8));
                    button3.setMaximumSize(new Dimension(getWidth()-getWidth()/3*2, getHeight()/3));

                    button4.setPreferredSize(new Dimension(getWidth(), getHeight()/8));
                    button4.setMaximumSize(new Dimension(getWidth()-getWidth()/3*2, getHeight()/3));

                    button5.setPreferredSize(new Dimension(getWidth(), getHeight()/8));
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
