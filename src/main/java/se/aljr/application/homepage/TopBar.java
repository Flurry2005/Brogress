package se.aljr.application.homepage;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

public class TopBar extends JPanel {

    ImageIcon topBarBackground;
    ImageIcon exitButtonIcon;
    private Point initialClick;
    private JFrame parent;
    private String resourcePath;

    private boolean isFullscreen = false;
    private Dimension previousSize;
    private Point previousLocation;
    private Color bg = (new Color(51,51,51));

    public TopBar(final JFrame parent){
        resourcePath = getClass().getClassLoader().getResource("resource.path").getPath().replace("resource.path","");
        topBarBackground = new ImageIcon(resourcePath+"top_bar.png");
        exitButtonIcon = new ImageIcon(resourcePath+"exit_button_icon.png");


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
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 5, 0));
        buttonPanel.setOpaque(false);

        Image scaledexitButtonIcon = exitButtonIcon.getImage().getScaledInstance(16, 16, Image.SCALE_SMOOTH);
        ImageIcon scaledExitButtonIcon = new ImageIcon(scaledexitButtonIcon);


        // Exit button
        JButton exitButton = new JButton("",scaledExitButtonIcon);
        exitButton.setFont(new Font("Arial", Font.BOLD, 14));
        exitButton.setForeground(Color.BLACK);
        exitButton.setBackground(bg);
        exitButton.setFocusPainted(false);
        exitButton.setBorderPainted(false);
        exitButton.setOpaque(true);
        exitButton.setPreferredSize(new Dimension(50, 30));
        exitButton.addActionListener(e -> parent.setExtendedState(JFrame.ICONIFIED));
        exitButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                exitButton.setBackground(new Color(40,40,40));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                exitButton.setBackground(bg);
            }
        });
        exitButton.addActionListener(e -> System.exit(0));


        // Mini button
        JButton minimizeButton = new JButton("—");
        minimizeButton.setFont(new Font("Arial", Font.BOLD, 14));
        minimizeButton.setForeground(Color.BLACK);
        minimizeButton.setBackground(new Color(51,51,51));
        minimizeButton.setFocusPainted(false);
        minimizeButton.setBorderPainted(false);
        minimizeButton.setOpaque(true);
        minimizeButton.setPreferredSize(new Dimension(50, 30));
        minimizeButton.addActionListener(e -> parent.setExtendedState(JFrame.ICONIFIED));
        minimizeButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                minimizeButton.setBackground(new Color(40,40,40));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                minimizeButton.setBackground(bg);
            }
        });


        // Fullscreen button
        JButton fullscreenButton = new JButton("⬜");
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
                fullscreenButton.setBackground(new Color(40,40,40));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                fullscreenButton.setBackground(bg);
            }
        });
        fullscreenButton.addActionListener(e -> toggleFullscreen());


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
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        //draw image from top left corner
        g.drawImage(topBarBackground.getImage(), 0, 0,getWidth(),getHeight(), this);
    }
}