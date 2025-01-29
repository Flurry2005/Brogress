package se.aljr.application.homepage;

import se.aljr.application.ApplicationWindow;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;


/**
 * Meny panel
 */
public class MenuPanel extends JPanel{
    ImageIcon menuBackground;
    ImageIcon logoIcon;
    ImageIcon buttonIcon;
    ImageIcon buttonIconExercise;
    ImageIcon buttonIconSettings;
    Font font;
    private String resourcePath;
    public MenuPanel(){
        resourcePath = getClass().getClassLoader().getResource("resource.path").getPath().replace("resource.path","");
        menuBackground = new ImageIcon(resourcePath+"side_bar.png");
        logoIcon = new ImageIcon(resourcePath+"agile_small_icon.png");
        buttonIcon = new ImageIcon(resourcePath+"button.png");
        buttonIconExercise = new ImageIcon(resourcePath+"button_exercise.png");
        buttonIconSettings = new ImageIcon(resourcePath+"button_settings.png");
        try{
            font=Font.createFont(Font.TRUETYPE_FONT, new File(resourcePath+"BebasNeue-Regular.otf"));
            font = font.deriveFont(30f);
        }catch(Exception e){
            font = new Font("Arial", Font.BOLD, 40);
            e.printStackTrace();
        }
        init();
    }

    private void init(){

        this.setLayout(new BorderLayout(0,0));
        this.setPreferredSize(new Dimension((int)((0.156770833*1920)/2), 1080/2));

        JPanel logoContainer = new JPanel(new BorderLayout(0,0));
        logoContainer.setPreferredSize(new Dimension(getWidth(), 1080/10));
        logoContainer.setOpaque(false);

        Image scaledlogoIcon = logoIcon.getImage().getScaledInstance(64, 64, Image.SCALE_SMOOTH);
        ImageIcon scaledLogoIcon = new ImageIcon(scaledlogoIcon);

        JLabel logoLabel = new JLabel(scaledLogoIcon);

        JPanel logoLabelTextContainer = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));

        logoLabelTextContainer.setOpaque(false);
        JLabel logoLabelText = new JLabel("BROGRESS");
        logoLabelText.setFont(font);
        logoLabelText.setForeground(Color.BLACK);
        logoLabelTextContainer.add(logoLabelText);
        logoContainer.add(logoLabel, BorderLayout.CENTER);
        logoContainer.add(logoLabelTextContainer, BorderLayout.SOUTH);




        JPanel buttonContainer = new JPanel();
        buttonContainer.add(Box.createVerticalStrut(30));

        buttonContainer.setLayout(new BoxLayout(buttonContainer, BoxLayout.Y_AXIS));
        buttonContainer.setOpaque(false);

        Image scaledbuttonHomeIcon = buttonIcon.getImage().getScaledInstance(151, 40, Image.SCALE_SMOOTH);
        ImageIcon scaledButtonHomeIcon = new ImageIcon(scaledbuttonHomeIcon);

        JButton button1 = new JButton("HOME",scaledButtonHomeIcon);
        button1.setFont(font.deriveFont(20f));
        button1.setForeground(Color.BLACK);
        button1.setAlignmentX(Component.CENTER_ALIGNMENT);
        button1.setAlignmentY(Component.CENTER_ALIGNMENT);
        button1.setMaximumSize(new Dimension((int)((0.156770833*1920)/2), 40));
        button1.setHorizontalTextPosition(SwingConstants.CENTER);
        button1.setVerticalTextPosition(SwingConstants.CENTER);
        button1.setFocusPainted(false);
        button1.setFocusable(false);
        button1.setBorderPainted(false);
        button1.setContentAreaFilled(true);
        Color bg = new Color(245, 245, 245,255);
        button1.setBackground(bg);

        button1.addMouseListener(new MouseAdapter() {
            private boolean isHovered = false;
            @Override
            public void mouseEntered(MouseEvent e) {
                isHovered = true;
                button1.setBackground(new Color(230, 230, 230));
                button1.repaint();
            }

            @Override
            public void mouseExited(MouseEvent e) {
                isHovered = false;
                button1.setBackground(bg);
                button1.repaint();
            }

            @Override
            public void mousePressed(MouseEvent e) {
                button1.setBackground(new Color(220,220,220));
                button1.repaint();
                ApplicationWindow.switchWindow("home");
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                if (isHovered) {
                    button1.setBackground(new Color(230, 230, 230));
                } else {
                    button1.setBackground(bg);
                }
                button1.repaint();
            }
        });
        button1.setUI(new javax.swing.plaf.basic.BasicButtonUI() {
            @Override
            public void paint(Graphics g, JComponent c) {
                g.setColor(button1.getBackground());
                g.fillRoundRect(0, 0, button1.getWidth(), button1.getHeight(), 10, 10);
                super.paint(g, c);
            }
        });

        Image scaledButtonExercise = buttonIconExercise.getImage().getScaledInstance(151, 40, Image.SCALE_SMOOTH);
        ImageIcon scaledButtonExerciseIcon = new ImageIcon(scaledButtonExercise);

        /**Exercises button*/
        JButton button2 = new JButton("EXERCISES",scaledButtonExerciseIcon);
        button2.setFont(font.deriveFont(20f));
        button2.setForeground(Color.BLACK);
        button2.setAlignmentX(Component.CENTER_ALIGNMENT);
        button2.setAlignmentY(Component.CENTER_ALIGNMENT);
        button2.setMaximumSize(new Dimension((int)((0.156770833*1920)/2), 40));
        button2.setHorizontalTextPosition(SwingConstants.CENTER);
        button2.setVerticalTextPosition(SwingConstants.CENTER);
        Color bg1 = new Color(245, 245, 245,255);
        button2.setFocusPainted(false);
        button2.setFocusable(false);
        button2.setBorderPainted(false);
        button2.setContentAreaFilled(true);
        button2.setBackground(bg1);

        button2.addMouseListener(new MouseAdapter() {
            private boolean isHovered = false;
            @Override
            public void mouseEntered(MouseEvent e) {
                isHovered = true;
                button2.setBackground(new Color(230, 230, 230));
                button2.repaint();
            }

            @Override
            public void mouseExited(MouseEvent e) {
                isHovered = false;
                button2.setBackground(bg1);
                button2.repaint();
            }

            @Override
            public void mousePressed(MouseEvent e) {
                button2.setBackground(new Color(220, 220, 220));
                ApplicationWindow.switchWindow("exercises");
                button2.repaint();
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                if (isHovered) {
                    button2.setBackground(new Color(230, 230, 230));
                } else {
                    button2.setBackground(bg1);
                }
                button2.repaint();
            }
        });
        button2.setUI(new javax.swing.plaf.basic.BasicButtonUI() {
            @Override
            public void paint(Graphics g, JComponent c) {
                g.setColor(button2.getBackground());
                g.fillRoundRect(0, 0, button2.getWidth(), button2.getHeight(), 10, 10);
                super.paint(g, c);
            }
        });

        /**Program button*/
        JButton button3 = new JButton("PROGRAM");
        button3.setFont(font.deriveFont(20f));
        button3.setForeground(Color.BLACK);
        button3.setAlignmentX(Component.CENTER_ALIGNMENT);
        button3.setAlignmentY(Component.CENTER_ALIGNMENT);
        button3.setMaximumSize(new Dimension((int)((0.156770833*1920)/2), 40));
        button3.setHorizontalTextPosition(SwingConstants.CENTER);
        button3.setVerticalTextPosition(SwingConstants.CENTER);
        Color bg2 = new Color(245, 245, 245,255);
        button3.setFocusPainted(false);
        button3.setFocusable(false);
        button3.setBorderPainted(false);
        button3.setContentAreaFilled(true);
        button3.setBackground(bg2);

        button3.addMouseListener(new MouseAdapter() {
            private boolean isHovered = false;
            @Override
            public void mouseEntered(MouseEvent e) {
                isHovered = true;
                button3.setBackground(new Color(230, 230, 230));
                button3.repaint();
            }

            @Override
            public void mouseExited(MouseEvent e) {
                isHovered = false;
                button3.setBackground(bg2);
                button3.repaint();
            }

            @Override
            public void mousePressed(MouseEvent e) {
                button3.setBackground(new Color(220, 220, 220));
                button3.repaint();
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                if (isHovered) {
                    button3.setBackground(new Color(230, 230, 230));
                } else {
                    button3.setBackground(bg2);
                }
                button3.repaint();
            }
        });
        button3.setUI(new javax.swing.plaf.basic.BasicButtonUI() {
            @Override
            public void paint(Graphics g, JComponent c) {
                g.setColor(button3.getBackground());
                g.fillRoundRect(0, 0, button3.getWidth(), button3.getHeight(), 10, 10);
                super.paint(g, c);
            }
        });

        /**Settings Button*/
        Image scaledButtonSettings = buttonIconSettings.getImage().getScaledInstance(151, 40, Image.SCALE_SMOOTH);
        ImageIcon scaledButtonSettingsIcon = new ImageIcon(scaledButtonSettings);

        JButton button4 = new JButton("SETTINGS",scaledButtonSettingsIcon);
        button4.setFont(font.deriveFont(20f));
        button4.setForeground(Color.BLACK);
        button4.setAlignmentX(Component.CENTER_ALIGNMENT);
        button4.setAlignmentY(Component.CENTER_ALIGNMENT);
        button4.setMaximumSize(new Dimension((int)((0.156770833*1920)/2), 40));
        button4.setHorizontalTextPosition(SwingConstants.CENTER);
        button4.setVerticalTextPosition(SwingConstants.CENTER);
        Color bg3 = new Color(245, 245, 245,255);
        button4.setFocusPainted(false);
        button4.setFocusable(false);
        button4.setBorderPainted(false);
        button4.setContentAreaFilled(false);
        button4.setBackground(bg3);

        button4.addMouseListener(new MouseAdapter() {
            private boolean isHovered = false;
            @Override
            public void mouseEntered(MouseEvent e) {
                isHovered = true;
                button4.setBackground(new Color(230, 230, 230));
                button4.repaint();
            }

            @Override
            public void mouseExited(MouseEvent e) {
                isHovered = false;
                button4.setBackground(bg3);
                button4.repaint();
            }

            @Override
            public void mousePressed(MouseEvent e) {
                button4.setBackground(new Color(220, 220, 220));
                button4.repaint();
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                if (isHovered) {
                    button4.setBackground(new Color(230, 230, 230));
                } else {
                    button4.setBackground(bg3);
                }
                button4.repaint();
            }
        });
        button4.setUI(new javax.swing.plaf.basic.BasicButtonUI() {
            @Override
            public void paint(Graphics g, JComponent c) {
                g.setColor(button4.getBackground());
                g.fillRoundRect(0, 0, button4.getWidth(), button4.getHeight(), 10, 10);
                super.paint(g, c);
            }
        });

        buttonContainer.add(button1);
        buttonContainer.add(Box.createVerticalStrut(30));
        buttonContainer.add(button2);
        buttonContainer.add(Box.createVerticalStrut(30));
        buttonContainer.add(button3);
        buttonContainer.add(Box.createVerticalStrut(30));
        buttonContainer.add(button4);
        buttonContainer.add(Box.createVerticalStrut(30));

        this.add(logoContainer, BorderLayout.NORTH);
        this.add(buttonContainer, BorderLayout.CENTER);

    }
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        // Draw the image to fill the entire panel
        if (menuBackground != null) {
            g.drawImage(menuBackground.getImage(), 0, 0, getWidth(), getHeight(), this);
        }
    }
}
