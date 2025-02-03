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
        Image scaledexitButtonIcon = exitButtonIcon.getImage().getScaledInstance(16, 16, Image.SCALE_SMOOTH);
        ImageIcon scaledExitButtonIcon = new ImageIcon(scaledexitButtonIcon);


        JButton exitButton = new JButton("",scaledExitButtonIcon);
        exitButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        exitButton.setAlignmentY(Component.CENTER_ALIGNMENT);
        exitButton.setMaximumSize(new Dimension(16, 16));
        exitButton.setHorizontalTextPosition(SwingConstants.CENTER);
        exitButton.setVerticalTextPosition(SwingConstants.CENTER);
        exitButton.setFocusPainted(false);
        exitButton.setFocusable(false);
        exitButton.setBorderPainted(false);
        exitButton.setContentAreaFilled(true);
        Color bg = new Color(51,51,51);
        exitButton.setBackground(bg);
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

        this.add(exitButton, BorderLayout.EAST);



    }
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        //draw image from top left corner
        g.drawImage(topBarBackground.getImage(), 0, 0,getWidth(),getHeight(), this);
    }
}
