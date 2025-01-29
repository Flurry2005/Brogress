package se.aljr.application.loginpage;

import org.apache.commons.logging.Log;
import se.aljr.application.CustomFont;
import se.aljr.application.Launcher;
import se.aljr.application.Monitorsize;
import se.aljr.application.ResizeHandler;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.*;

/**
 * Login window for application
 */
public class LoginWindow extends JFrame {

    protected String emailAdress;
    protected String password;
    private String resourcePath;

    private final ImageIcon logoIcon;
    private final JFrame thisFrame;

    private Point initialClick;

    private FirebaseAuthenticationManager firebaseAuthenticationManager;

    private Font font;

    private boolean registerMode = false;


    public LoginWindow(int width, int height) {
        resourcePath = getClass().getClassLoader().getResource("resource.path").getPath().replace("resource.path","");

        logoIcon = new ImageIcon(resourcePath+"agile300.png");
        font = CustomFont.getFont();
        thisFrame = this;
        this.firebaseAuthenticationManager = new FirebaseAuthenticationManager();

        ResizeHandler resizeHandler = new ResizeHandler(this, (width/height)); // Aspect ratio (t.ex. 4:3)
        this.addMouseListener(resizeHandler);
        this.addMouseMotionListener(resizeHandler);

        this.setUndecorated(true); //Ingen övre program bar
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //Terminerar programet vid stängning.'
        this.setSize(width,height); //Sätter bredd och höjd för fönstret
        this.setLayout(new BorderLayout(0,0));
        setLocationRelativeTo(null); //Ser till att fönstret placeras i mitten av skärmen

        init();
    }
    private void init() {
        //Holds the content on the left side of the window
        JPanel leftPanel = new JPanel();
        leftPanel.setLayout(new FlowLayout(FlowLayout.CENTER,0,0));
        leftPanel.setPreferredSize(new Dimension(getWidth()/2,getHeight()));

        //Holds the content on the right side of the window
        JPanel rightPanel = new JPanel();
        rightPanel.setLayout(new BorderLayout(0,0));
        rightPanel.setPreferredSize(new Dimension(getWidth()/2,getHeight()));

        //Holds the exit button and lets the frame be moved on the screen
        JPanel topBar = new JPanel();
        topBar.setAlignmentY(Component.TOP_ALIGNMENT);
        topBar.setOpaque(true);
        topBar.setBackground(Color.WHITE);
        topBar.setPreferredSize(new Dimension(getWidth()/2,getHeight()/13));
        topBar.setLayout(new BorderLayout(0,0));
        topBar.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                initialClick = e.getPoint();
                getComponentAt(initialClick);
            }
        });
        topBar.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                int thisX = thisFrame.getLocation().x;
                int thisY = thisFrame.getLocation().y;

                int xMoved = e.getX() - initialClick.x;
                int yMoved = e.getY() - initialClick.y;

                int X = thisX + xMoved;
                int Y = thisY + yMoved;
                thisFrame.setLocation(X, Y);
            }
        });

        //Holds the small logo icon and the login menu
        JPanel loginPanel = new JPanel();
        loginPanel.setPreferredSize(new Dimension(getWidth(),getHeight()-getHeight()/13));
        loginPanel.setLayout(new BorderLayout(0,0));
        loginPanel.setAlignmentX(Component.CENTER_ALIGNMENT);

        //Holds the login menu
        JPanel loginMenuPanel = new JPanel();
        loginMenuPanel.setLayout(new BoxLayout(loginMenuPanel, BoxLayout.Y_AXIS));
        loginMenuPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        loginMenuPanel.setPreferredSize(new Dimension(loginPanel.getWidth(),getHeight()*6/9));

        //Container for smallLogoLabel
        JPanel smallLogoLabelContainer = new JPanel(new BorderLayout(0,0));


        Image scaledBigLogo = logoIcon.getImage().getScaledInstance(getWidth()/2,getHeight(),Image.SCALE_SMOOTH);
        ImageIcon scaledBigLogoIcon = new ImageIcon(scaledBigLogo);

        JLabel bigLogoLabel = new JLabel(scaledBigLogoIcon);
        //bigLogoLabel.setPreferredSize(new Dimension(getWidth()/2,getHeight()));

        Image scaledSmallLogo = new ImageIcon(resourcePath+"agile_small_icon.png").getImage().getScaledInstance((getHeight()-getHeight()/13)-(getHeight()*3/4), (getHeight()-getHeight()/13)-(getHeight()*3/4), Image.SCALE_SMOOTH);
        ImageIcon scaledSmallLogoIcon = new ImageIcon(scaledSmallLogo);

        JLabel smallLogoLabel = new JLabel(scaledSmallLogoIcon);

        smallLogoLabel.setPreferredSize(new Dimension((getHeight()-getHeight()/13)-(getHeight()*3/5), (getHeight()-getHeight()/13)-(getHeight()*3/5)));
        smallLogoLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel incorrectUserCredentialsLabel = new JLabel("");
        incorrectUserCredentialsLabel.setPreferredSize(new Dimension((int)(Monitorsize.getWidth()/38.4),(int)(Monitorsize.getHeight()/108)));
        incorrectUserCredentialsLabel.setMaximumSize(new Dimension((int)(Monitorsize.getWidth()/38.4),(int)(Monitorsize.getHeight()/108)));
        incorrectUserCredentialsLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        incorrectUserCredentialsLabel.setForeground(Color.RED);
        incorrectUserCredentialsLabel.setFont(new Font("Arial", Font.BOLD, (int)(Monitorsize.getHeight()/108)));
        incorrectUserCredentialsLabel.setVisible(true);

        JTextField emailField = new JTextField("");
        emailField.setAlignmentX(Component.CENTER_ALIGNMENT);
        emailField.setMinimumSize(new Dimension((int)(Monitorsize.getWidth()/19.2),(int)(Monitorsize.getHeight()/54)));
        emailField.setMaximumSize(new Dimension(new Dimension((int)(Monitorsize.getWidth()/19.2),(int)(Monitorsize.getHeight()/54))));

        JPasswordField passwordField = new JPasswordField("");
        passwordField.setMinimumSize(new Dimension(new Dimension((int)(Monitorsize.getWidth()/19.2),(int)(Monitorsize.getHeight()/54))));
        passwordField.setMaximumSize(new Dimension(new Dimension((int)(Monitorsize.getWidth()/19.2),(int)(Monitorsize.getHeight()/54))));
        passwordField.setAlignmentX(Component.CENTER_ALIGNMENT);

        JButton loginButton = new JButton("Login");
        loginButton.setMinimumSize(new Dimension(new Dimension((int)(Monitorsize.getWidth()/19.2),(int)(Monitorsize.getHeight()/27))));
        loginButton.setPreferredSize(new Dimension(new Dimension((int)(Monitorsize.getWidth()/19.2),(int)(Monitorsize.getHeight()/27))));
        loginButton.setMaximumSize(new Dimension(new Dimension((int)(Monitorsize.getWidth()/19.2),(int)(Monitorsize.getHeight()/27))));
        loginButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        loginButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                password = String.valueOf(passwordField.getPassword());
                emailAdress = emailField.getText();
                try {
                    if(!registerMode){
                        if(FirebaseAuthenticationManager.authenticateUser(emailAdress, password)){
                            Thread.sleep(1000);
                            Launcher.isLoggedIn = true; //Sätter Launcherns status till inloggad.
                        }
                        else{
                            incorrectUserCredentialsLabel.setText("Incorrect.");
                        }
                    }else{
                        FirebaseAuthenticationManager.registerUser(emailAdress, password);
                        Thread.sleep(2000);
                        if(FirebaseAuthenticationManager.authenticateUser(emailAdress, password)){
                            Launcher.isLoggedIn = true;
                        }
                    }
                } catch (InterruptedException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });

        JButton register = new JButton("<html><u>REGISTER</u></html>");
        register.setFont(font.deriveFont((float)(Monitorsize.getHeight()/108)));
        register.setMaximumSize(new Dimension((int)(Monitorsize.getWidth()/19.2),(int)(Monitorsize.getHeight()/27)));
        register.setAlignmentX(Component.CENTER_ALIGNMENT);
        register.setBorderPainted(false);
        register.setFocusPainted(false);
        register.setContentAreaFilled(false);
        register.setForeground(Color.BLUE);
        register.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                if(!registerMode){
                    loginButton.setText("SIGN UP");
                    register.setText("<html><u>LOGIN</u></html>");
                    registerMode = true;
                    incorrectUserCredentialsLabel.setText("");
                    repaint();
                }else{
                    loginButton.setText("LOGIN");
                    registerMode = false;
                    register.setText("<html><u>SIGN UP</u></html>");
                    repaint();
                }
            }
        });

        JButton exitButton = new JButton("X");
        exitButton.setMaximumSize(new Dimension((int)(Monitorsize.getWidth()/96),(int)(Monitorsize.getHeight()/108)));
        exitButton.setBackground(Color.LIGHT_GRAY);
        exitButton.setFocusPainted(false);
        exitButton.setAlignmentY(Component.TOP_ALIGNMENT);
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        JLabel emailText = new JLabel("Email: ");
        emailText.setAlignmentX(Component.CENTER_ALIGNMENT);
        emailText.setFont(font.deriveFont((float)(Monitorsize.getHeight()/72)));

        JLabel passwordText = new JLabel("Password: ");
        passwordText.setAlignmentX(Component.CENTER_ALIGNMENT);
        passwordText.setFont(font.deriveFont((float)(Monitorsize.getHeight()/72)));



        loginMenuPanel.add(Box.createVerticalStrut((int)(Monitorsize.getHeight()/216)));
        loginMenuPanel.add(emailText);
        loginMenuPanel.add(emailField);
        loginMenuPanel.add(Box.createVerticalStrut((int)(Monitorsize.getHeight()/108)));
        loginMenuPanel.add(passwordText);
        loginMenuPanel.add(passwordField);
        loginMenuPanel.add(Box.createVerticalStrut((int)(Monitorsize.getHeight()/158)));
        loginMenuPanel.add(incorrectUserCredentialsLabel);
        loginMenuPanel.add(Box.createVerticalStrut((int)(Monitorsize.getHeight()/158)));
        loginMenuPanel.add(loginButton);
        loginMenuPanel.add(register);

        smallLogoLabelContainer.add(smallLogoLabel, BorderLayout.NORTH);

        loginPanel.add(smallLogoLabelContainer,BorderLayout.NORTH);
        loginPanel.add(loginMenuPanel,BorderLayout.CENTER);

        loginMenuPanel.setBackground(Color.BLACK);
        loginMenuPanel.setOpaque(false);

        //loginPanel.setBackground(Color.GREEN);


        topBar.add(exitButton,BorderLayout.EAST);

        rightPanel.add(topBar,BorderLayout.NORTH);
        rightPanel.add(loginPanel,BorderLayout.SOUTH);

        leftPanel.add(bigLogoLabel);

        this.add(leftPanel, BorderLayout.WEST);
        this.add(rightPanel, BorderLayout.EAST);
        this.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {

                leftPanel.setPreferredSize(new Dimension(getWidth()/2,getHeight()));
                rightPanel.setPreferredSize(new Dimension(getWidth()/2,getHeight()));
                Image scaledBigLogo = logoIcon.getImage().getScaledInstance(getWidth()/2,getHeight(),Image.SCALE_SMOOTH);
                bigLogoLabel.setIcon(new ImageIcon(scaledBigLogo));
                loginPanel.setPreferredSize(new Dimension(rightPanel.getWidth(),(rightPanel.getHeight()-topBar.getHeight())));
                loginMenuPanel.setPreferredSize(new Dimension(loginPanel.getWidth(),getHeight()*6/9));
                Image scaledSmallLogo = new ImageIcon(resourcePath+"agile_small_icon.png").getImage().getScaledInstance((rightPanel.getHeight()-topBar.getHeight())-(getHeight()*3/4), (rightPanel.getHeight()-topBar.getHeight())-(getHeight()*3/4), Image.SCALE_SMOOTH);
                smallLogoLabel.setIcon(new ImageIcon(scaledSmallLogo));
                smallLogoLabel.setPreferredSize(new Dimension((rightPanel.getHeight()-topBar.getHeight())-(getHeight()*3/5), (rightPanel.getHeight()-topBar.getHeight())-(getHeight()*3/5)));
                System.out.println(new Dimension((rightPanel.getHeight()-topBar.getHeight())-(getHeight()*3/5), (rightPanel.getHeight()-topBar.getHeight())-(getHeight()*3/5)));
                //LoginWindow.this.pack();

            }
        });
        this.setVisible(true);

    }
}
