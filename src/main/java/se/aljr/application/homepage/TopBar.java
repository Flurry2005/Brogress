package se.aljr.application.homepage;

import se.aljr.application.AppThemeColors;
import se.aljr.application.ResourcePath;
import se.aljr.application.settings.SettingsPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

public class TopBar extends JPanel {

    ImageIcon topBarBackground;
    ImageIcon lightTopBarBackground;
    ImageIcon exitButtonIcon;
    ImageIcon lightExitButtonicon;
    private Point initialClick;
    private final JFrame parent;
    Image scaledexitButtonIcon;
    Image scaledlightExitButtonicon;
    ImageIcon scaledExitButtonIcon;
    ImageIcon scaledLightExitButtonIcon;

    JButton exitButton = new JButton("");
    JButton minimizeButton = new JButton("—");
    JButton fullscreenButton = new JButton("⬜");

    private boolean isFullscreen = false;
    private Dimension previousSize;
    private Point previousLocation;
    private Color bg = (new Color(51,51,51));
    public static TopBar instance;

    public TopBar(final JFrame parent){
        instance = this;

        topBarBackground = new ImageIcon(ResourcePath.getResourcePath("top_bar.png"));
        lightTopBarBackground = new ImageIcon(ResourcePath.getResourcePath("top_bar_light.png"));

        exitButtonIcon = new ImageIcon(ResourcePath.getResourcePath("exit_button_icon.png"));
        scaledexitButtonIcon = exitButtonIcon.getImage().getScaledInstance(16, 16, Image.SCALE_SMOOTH);
        scaledExitButtonIcon = new ImageIcon(scaledexitButtonIcon);

        lightExitButtonicon = new ImageIcon(ResourcePath.getResourcePath("exit_button_icon_light.png"));
        scaledlightExitButtonicon = lightExitButtonicon.getImage().getScaledInstance(16,16, Image.SCALE_SMOOTH);
        scaledLightExitButtonIcon = new ImageIcon(scaledlightExitButtonicon);

        exitButton.setIcon(scaledExitButtonIcon);

        this.parent = parent;
        addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                initialClick = e.getPoint();
                getComponentAt(initialClick);
            }
        });

        addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {

                // get location of Window
                int thisX = parent.getLocation().x;
                int thisY = parent.getLocation().y;

                // Determine how much the mouse moved since the initial click
                int xMoved = e.getX() - initialClick.x;
                int yMoved = e.getY() - initialClick.y;

                // Move window to this position
                int X = thisX + xMoved;
                int Y = thisY + yMoved;
                parent.setLocation(X, Y);
            }
        });
        init();
    }
    private void init(){
        this.setLayout(new BorderLayout(0,0));
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 0, 0));
        buttonPanel.setOpaque(false);




        // Exit button
        exitButton.setFont(new Font("Arial", Font.BOLD, 14));
        exitButton.setForeground(Color.BLACK);
        exitButton.setBackground(bg);
        exitButton.setFocusPainted(false);
        exitButton.setBorderPainted(false);
        exitButton.setOpaque(true);
        exitButton.setPreferredSize(new Dimension(50, 30));
        exitButton.addActionListener(_ -> parent.setExtendedState(JFrame.ICONIFIED));
        exitButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                exitButton.setBackground(AppThemeColors.buttonBGHovered);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                exitButton.setBackground(AppThemeColors.SECONDARY);
            }
        });
        exitButton.addActionListener(_ -> System.exit(0));


        // Mini button

        minimizeButton.setFont(new Font("Arial", Font.BOLD, 14));
        minimizeButton.setForeground(Color.BLACK);
        minimizeButton.setBackground(new Color(51,51,51));
        minimizeButton.setFocusPainted(false);
        minimizeButton.setBorderPainted(false);
        minimizeButton.setOpaque(true);
        minimizeButton.setPreferredSize(new Dimension(50, 30));
        minimizeButton.addActionListener(_ -> parent.setExtendedState(JFrame.ICONIFIED));
        minimizeButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                minimizeButton.setBackground(AppThemeColors.buttonBGHovered);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                minimizeButton.setBackground(AppThemeColors.SECONDARY);
            }
        });


        // Fullscreen button

        fullscreenButton.setFont(new Font("Arial", Font.BOLD, 14));
        fullscreenButton.setForeground(Color.BLACK);
        fullscreenButton.setBackground(new Color(51,51,51));
        fullscreenButton.setFocusPainted(false);
        fullscreenButton.setBorderPainted(false);
        fullscreenButton.setOpaque(true);
        fullscreenButton.setPreferredSize(new Dimension(50, 30));
        fullscreenButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                fullscreenButton.setBackground(AppThemeColors.buttonBGHovered);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                fullscreenButton.setBackground(AppThemeColors.SECONDARY);
            }
        });
        fullscreenButton.addActionListener(_ -> toggleFullscreen());


        buttonPanel.add(minimizeButton);
        buttonPanel.add(fullscreenButton);
        buttonPanel.add(exitButton);
        this.add(buttonPanel, BorderLayout.EAST);
    }

    private void toggleFullscreen() {
        if (isFullscreen) {
            // reverse it back now yall
            parent.setExtendedState(JFrame.NORMAL);
            parent.setSize(previousSize);
            parent.setLocation(previousLocation);
            isFullscreen = false;
        } else {
            // fullscreen
            previousSize = parent.getSize();
            previousLocation = parent.getLocation();
            parent.setExtendedState(JFrame.MAXIMIZED_BOTH);
            isFullscreen = true;
        }
    }

    public void updateColors(){
        minimizeButton.setBackground(AppThemeColors.SECONDARY);
        fullscreenButton.setBackground(AppThemeColors.SECONDARY);
        exitButton.setBackground(AppThemeColors.SECONDARY);
        minimizeButton.setForeground(AppThemeColors.foregroundColor);
        fullscreenButton.setForeground(AppThemeColors.foregroundColor);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        //draw image from top left corner
        updateColors();
        if(!SettingsPanel.lightMode){
            exitButton.setIcon(scaledLightExitButtonIcon);
            g.drawImage(topBarBackground.getImage(), 0, 0,getWidth(),getHeight(), this);
        }else{
            exitButton.setIcon(scaledExitButtonIcon);
            g.drawImage(lightTopBarBackground.getImage(), 0, 0,getWidth(),getHeight(), this);
        }

    }
}