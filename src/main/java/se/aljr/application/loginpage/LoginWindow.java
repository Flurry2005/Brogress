package se.aljr.application.loginpage;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import se.aljr.application.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;

/**
 * Login window for application
 */
public class LoginWindow extends JFrame {

    protected String emailAdress;
    protected String password;
    protected String userName;

    private final ImageIcon logoIcon;
    private final JFrame thisFrame;

    private Point initialClick;

    private final Font font;

    private boolean registerMode = false;


    public LoginWindow(int width, int height) {

        logoIcon = new ImageIcon(ResourcePath.getResourcePath("agile300.png"));
        font = CustomFont.getFont();
        thisFrame = this;

        ResizeHandler resizeHandler = new ResizeHandler(this, (width / height)); // Aspect ratio (t.ex. 4:3)
        this.addMouseListener(resizeHandler);
        this.addMouseMotionListener(resizeHandler);

        this.setUndecorated(true); //Ingen övre program bar
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //Terminerar programet vid stängning.'
        this.setSize(width, height); //Sätter bredd och höjd för fönstret
        this.setLayout(new BorderLayout(0, 0));
        setLocationRelativeTo(null); //Ser till att fönstret placeras i mitten av skärmen

        init();
    }

    private void init() {
        //Holds the content on the left side of the window
        JPanel leftPanel = new JPanel();
        leftPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));
        leftPanel.setPreferredSize(new Dimension(getWidth() / 2, getHeight()));

        //Holds the content on the right side of the window
        JPanel rightPanel = new JPanel();
        rightPanel.setLayout(new BorderLayout(0, 0));
        rightPanel.setPreferredSize(new Dimension(getWidth() / 2, getHeight()));

        //Holds the exit button and lets the frame be moved on the screen
        JPanel topBar = new JPanel();
        topBar.setAlignmentY(Component.TOP_ALIGNMENT);
        topBar.setOpaque(true);
        topBar.setBackground(Color.WHITE);
        topBar.setPreferredSize(new Dimension(getWidth() / 2, getHeight() / 13));
        topBar.setLayout(new BorderLayout(0, 0));
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
        loginPanel.setPreferredSize(new Dimension(getWidth(), getHeight() - getHeight() / 13));
        loginPanel.setLayout(new BorderLayout(0, 0));
        loginPanel.setAlignmentX(Component.CENTER_ALIGNMENT);

        //Holds the login menu
        JPanel loginMenuPanel = new JPanel();
        loginMenuPanel.setLayout(new BoxLayout(loginMenuPanel, BoxLayout.Y_AXIS));
        loginMenuPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        loginMenuPanel.setPreferredSize(new Dimension(loginPanel.getWidth(), getHeight() * 6 / 9));

        //Container for smallLogoLabel
        JPanel smallLogoLabelContainer = new JPanel(new BorderLayout(0, 0));


        Image scaledBigLogo = logoIcon.getImage().getScaledInstance(getWidth() / 2, getHeight(), Image.SCALE_SMOOTH);
        ImageIcon scaledBigLogoIcon = new ImageIcon(scaledBigLogo);

        JLabel bigLogoLabel = new JLabel(scaledBigLogoIcon);

        Image scaledSmallLogo = new ImageIcon(ResourcePath.getResourcePath("agile_small_icon.png")).getImage().getScaledInstance((getHeight() - getHeight() / 13) - (getHeight() * 3 / 4), (getHeight() - getHeight() / 13) - (getHeight() * 3 / 4), Image.SCALE_SMOOTH);
        ImageIcon scaledSmallLogoIcon = new ImageIcon(scaledSmallLogo);


        JLabel smallLogoLabel = new JLabel(scaledSmallLogoIcon);

        smallLogoLabel.setPreferredSize(new Dimension((getHeight() - getHeight() / 13) - (getHeight() * 3 / 5), (getHeight() - getHeight() / 13) - (getHeight() * 6 / 9)));
        smallLogoLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel incorrectUserCredentialsLabel = new JLabel("", SwingConstants.CENTER);
        incorrectUserCredentialsLabel.setPreferredSize((new Dimension(getWidth(), getHeight() / 22)));
        incorrectUserCredentialsLabel.setMinimumSize((new Dimension(getWidth(), getHeight() / 22)));
        incorrectUserCredentialsLabel.setMaximumSize((new Dimension(getWidth(), getHeight() / 22)));
        incorrectUserCredentialsLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        incorrectUserCredentialsLabel.setForeground(Color.RED);
        incorrectUserCredentialsLabel.setFont((font.deriveFont((float) getHeight() / 23)));
        incorrectUserCredentialsLabel.setVisible(true);

        JTextField userNameField = new JTextField("");
        userNameField.setAlignmentX(Component.CENTER_ALIGNMENT);
        userNameField.setMinimumSize(new Dimension(getWidth() / 5, getHeight() / 15));
        userNameField.setMaximumSize(new Dimension(new Dimension(getWidth() / 5, getHeight() / 15)));

        JTextField emailField = new JTextField("Enter email adress");
        emailField.setAlignmentX(Component.CENTER_ALIGNMENT);
        emailField.setMinimumSize(new Dimension(getWidth() / 5, getHeight() / 15));
        emailField.setMaximumSize(new Dimension(new Dimension(getWidth() / 5, getHeight() / 15)));

        emailField.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (emailField.getText().equals("Enter email adress"))
                emailField.setText("");
            }
            @Override
            public void focusLost(FocusEvent e) {
                if (emailField.getText().isEmpty()) {
                    emailField.setText("Enter email adress");
                }
            }
        });

        HashMap<String, String> userLoginDetails = new HashMap<>();
        Gson gson = new Gson();
        String userLoginDetailsPathJson;
        if(ResourcePath.isRunningFromJar()){
            URL location = ResourcePath.class.getProtectionDomain().getCodeSource().getLocation();

            String jarFilePath = location.getPath();

            userLoginDetailsPathJson = jarFilePath.substring(0,jarFilePath.lastIndexOf("/")+1)+"loginDetails.json";
        }else{
            userLoginDetailsPathJson = ResourcePath.class.getClassLoader().getResource("resource.path").getPath().replace("resource.path","")+"loginDetails.json";
        }
        try (FileReader reader = new FileReader(userLoginDetailsPathJson)) {
            userLoginDetails = gson.fromJson(reader, HashMap.class);
            if (userLoginDetails.get("email") != null) {
                emailField.setText(userLoginDetails.get("email"));
            }
        } catch (Exception _) {

        }

        JPasswordField passwordField = new JPasswordField("Enter password");
        passwordField.setMinimumSize(new Dimension(getWidth() / 5, getHeight() / 15));
        passwordField.setMaximumSize(new Dimension(new Dimension(getWidth() / 5, getHeight() / 15)));
        passwordField.setAlignmentX(Component.CENTER_ALIGNMENT);

        if (userLoginDetails.get("password") != null) {
            passwordField.setText(userLoginDetails.get("password"));
        }else{
            passwordField.setEchoChar('\u0000');
        }
        passwordField.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                if (String.valueOf(passwordField.getPassword()).equals("Enter password")) {
                    passwordField.setText("");
                    passwordField.setEchoChar((Character) UIManager.get("PasswordField.echoChar"));
                }
            }
            @Override
            public void focusLost(FocusEvent e){
                if(String.valueOf(passwordField.getPassword()).isEmpty()){
                    passwordField.setEchoChar('\u0000');
                    passwordField.setText("Enter password");
                }
            }
        });

        JButton loginButton = new JButton("Login");
        loginButton.setMinimumSize(new Dimension(new Dimension(getWidth() / 5, (int) (getHeight() / 7.5))));
        loginButton.setPreferredSize(new Dimension(new Dimension(getWidth() / 5, (int) (getHeight() / 7.5))));
        loginButton.setMaximumSize(new Dimension(new Dimension(getWidth() / 5, (int) (getHeight() / 7.5))));
        loginButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        loginButton.setFont(font.deriveFont((float) (getHeight() / 17)));
        loginButton.addActionListener(_ -> {
            password = String.valueOf(passwordField.getPassword());
            emailAdress = emailField.getText().trim();
            userName = userNameField.getText().trim();
            try {
                if (!registerMode) {
                    if (FirebaseManager.authenticateUser(emailAdress, password)) {
                        Thread.sleep(1000);
                        FirebaseManager.readDBUserInfo(emailAdress);
                        Launcher.isLoggedIn = true; //Sätter Launcherns status till inloggad.
                    } else {
                        incorrectUserCredentialsLabel.setText("INVALID CREDENTIALS");
                    }
                } else {
                    if (FirebaseManager.registerUser(emailAdress, password) == 0) {
                        Thread.sleep(2000);
                        if (FirebaseManager.authenticateUser(emailAdress, password)) {
                            FirebaseManager.writeDBnewUser(userName, emailAdress);
                            Thread.sleep(2000);
                            FirebaseManager.readDBUserInfo(emailAdress);

                            HashMap<String, String> userLoginDeatils1 = new HashMap<>();
                            userLoginDeatils1.put("email", emailAdress);
                            userLoginDeatils1.put("password", password);

                            Gson gson1 = new GsonBuilder().setPrettyPrinting().create();
                            String userLoginDetailsPathJson1;
                            if(ResourcePath.isRunningFromJar()){
                                URL location = ResourcePath.class.getProtectionDomain().getCodeSource().getLocation();

                                String jarFilePath = location.getPath();

                                userLoginDetailsPathJson1 = jarFilePath.substring(0,jarFilePath.lastIndexOf("/")+1)+"loginDetails.json";

                            }else{
                                userLoginDetailsPathJson1 = LoginWindow.class.getClassLoader().getResource("resource.path").getPath().replace("resource.path","")+"loginDetails.json";
                            }
                            try (FileWriter writer = new FileWriter(userLoginDetailsPathJson1)) {
                                gson1.toJson(userLoginDeatils1, writer);
                            } catch (IOException es) {
                                es.printStackTrace();
                            }


                            Launcher.isLoggedIn = true;
                        }
                    }


                }
            } catch (InterruptedException | IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        JButton register = new JButton("<html><u>REGISTER</u></html>");
        register.setFont(font.deriveFont((float) (getHeight() / 30)));
        register.setMaximumSize(new Dimension(getWidth() / 6, getHeight() / 10));
        register.setAlignmentX(Component.CENTER_ALIGNMENT);
        register.setBorderPainted(false);
        register.setFocusPainted(false);
        register.setContentAreaFilled(false);
        register.setForeground(Color.BLUE);


        JButton exitButton = new JButton("X");
        exitButton.setMaximumSize(new Dimension(Monitorsize.getWidth() / 96, Monitorsize.getHeight() / 108));
        exitButton.setBackground(Color.LIGHT_GRAY);
        exitButton.setFocusPainted(false);
        exitButton.setAlignmentY(Component.TOP_ALIGNMENT);
        exitButton.addActionListener(_ -> System.exit(0));

        JLabel fullNameText = new JLabel("Full name: ");
        fullNameText.setAlignmentX(Component.CENTER_ALIGNMENT);
        fullNameText.setFont(font.deriveFont((float) (getHeight() / 17)));

        JLabel emailText = new JLabel("Email: ");
        emailText.setAlignmentX(Component.CENTER_ALIGNMENT);
        emailText.setFont(font.deriveFont((float) (getHeight() / 17)));

        JLabel passwordText = new JLabel("Password: ");
        passwordText.setAlignmentX(Component.CENTER_ALIGNMENT);
        passwordText.setFont(font.deriveFont((float) (getHeight() / 17)));

        loginMenuPanel.add(Box.createVerticalGlue());
        loginMenuPanel.add(emailText);
        loginMenuPanel.add(emailField);
        loginMenuPanel.add(Box.createVerticalGlue());
        loginMenuPanel.add(Box.createVerticalGlue());
        loginMenuPanel.add(Box.createVerticalGlue());
        loginMenuPanel.add(passwordText);
        loginMenuPanel.add(passwordField);
        loginMenuPanel.add(Box.createVerticalGlue());
        loginMenuPanel.add(incorrectUserCredentialsLabel);
        loginMenuPanel.add(Box.createVerticalGlue());
        loginMenuPanel.add(loginButton);
        loginMenuPanel.add(register);
        loginMenuPanel.add(Box.createVerticalGlue());

        register.addActionListener(_ -> {
            if (!registerMode) {
                loginMenuPanel.removeAll();
                loginMenuPanel.add(fullNameText);
                loginMenuPanel.add(userNameField);
                loginMenuPanel.add(Box.createVerticalGlue());
                loginMenuPanel.add(emailText);
                loginMenuPanel.add(emailField);
                loginMenuPanel.add(Box.createVerticalGlue());
                loginMenuPanel.add(passwordText);
                loginMenuPanel.add(passwordField);
                loginMenuPanel.add(Box.createVerticalGlue());
                loginMenuPanel.add(incorrectUserCredentialsLabel);
                loginMenuPanel.add(Box.createVerticalGlue());
                loginMenuPanel.add(loginButton);
                loginMenuPanel.add(register);
                loginMenuPanel.add(Box.createVerticalGlue());
                loginButton.setText("SIGN UP");
                register.setText("<html><u>LOGIN</u></html>");
                registerMode = true;
                incorrectUserCredentialsLabel.setText("");
                repaint();
            } else {
                loginMenuPanel.removeAll();
                loginMenuPanel.add(Box.createVerticalGlue());
                loginMenuPanel.add(emailText);
                loginMenuPanel.add(emailField);
                loginMenuPanel.add(Box.createVerticalGlue());
                loginMenuPanel.add(Box.createVerticalGlue());
                loginMenuPanel.add(Box.createVerticalGlue());
                loginMenuPanel.add(passwordText);
                loginMenuPanel.add(passwordField);
                loginMenuPanel.add(Box.createVerticalGlue());
                loginMenuPanel.add(incorrectUserCredentialsLabel);
                loginMenuPanel.add(Box.createVerticalGlue());
                loginMenuPanel.add(loginButton);
                loginMenuPanel.add(register);
                loginMenuPanel.add(Box.createVerticalGlue());
                loginButton.setText("LOGIN");
                registerMode = false;
                register.setText("<html><u>SIGN UP</u></html>");
                repaint();
            }
        });

        smallLogoLabelContainer.add(smallLogoLabel, BorderLayout.NORTH);

        loginPanel.add(smallLogoLabelContainer, BorderLayout.NORTH);
        loginPanel.add(loginMenuPanel, BorderLayout.CENTER);

        loginMenuPanel.setBackground(Color.BLACK);
        loginMenuPanel.setOpaque(false);

        topBar.add(exitButton, BorderLayout.EAST);

        rightPanel.add(topBar, BorderLayout.NORTH);
        rightPanel.add(loginPanel, BorderLayout.SOUTH);

        leftPanel.add(bigLogoLabel);

        this.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                loginButton.doClick();
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    loginButton.doClick();
                }
            }
        });
        this.setFocusable(true);
        this.add(leftPanel, BorderLayout.WEST);
        this.add(rightPanel, BorderLayout.EAST);

        //Handles the resizing of the components
        this.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {

                leftPanel.setPreferredSize(new Dimension(getWidth() / 2, getHeight()));
                rightPanel.setPreferredSize(new Dimension(getWidth() / 2, getHeight()));
                Image scaledBigLogo = logoIcon.getImage().getScaledInstance(getWidth() / 2, getHeight(), Image.SCALE_SMOOTH);
                bigLogoLabel.setIcon(new ImageIcon(scaledBigLogo));
                loginPanel.setPreferredSize(new Dimension(rightPanel.getWidth(), (rightPanel.getHeight() - topBar.getHeight())));
                loginMenuPanel.setPreferredSize(new Dimension(loginPanel.getWidth(), getHeight() * 6 / 9));
                Image scaledSmallLogo = new ImageIcon(ResourcePath.getResourcePath("agile_small_icon.png")).getImage().getScaledInstance((rightPanel.getHeight() - topBar.getHeight()) - (getHeight() * 3 / 4), (rightPanel.getHeight() - topBar.getHeight()) - (getHeight() * 3 / 4), Image.SCALE_SMOOTH);
                smallLogoLabel.setIcon(new ImageIcon(scaledSmallLogo));
                smallLogoLabel.setPreferredSize(new Dimension((rightPanel.getHeight() - topBar.getHeight()) - (getHeight() * 3 / 5), (rightPanel.getHeight() - topBar.getHeight()) - (getHeight() * 6 / 9)));
                register.setFont(font.deriveFont((float) (getHeight() / 30)));
                register.setMaximumSize(new Dimension(getWidth() / 6, getHeight() / 10));
                userNameField.setPreferredSize(new Dimension(getWidth() / 5, getHeight() / 15));
                userNameField.setMinimumSize(new Dimension(getWidth() / 5, getHeight() / 15));
                userNameField.setMaximumSize(new Dimension(new Dimension(getWidth() / 5, getHeight() / 15)));
                emailField.setPreferredSize(new Dimension(getWidth() / 5, getHeight() / 15));
                emailField.setMinimumSize(new Dimension(getWidth() / 5, getHeight() / 15));
                emailField.setMaximumSize(new Dimension(new Dimension(getWidth() / 5, getHeight() / 15)));
                passwordField.setPreferredSize(new Dimension(getWidth() / 5, getHeight() / 15));
                passwordField.setMinimumSize(new Dimension(getWidth() / 5, getHeight() / 15));
                passwordField.setMaximumSize(new Dimension(new Dimension(getWidth() / 5, getHeight() / 15)));
                emailText.setFont(font.deriveFont((float) (getHeight() / 17)));
                passwordText.setFont(font.deriveFont((float) (getHeight() / 17)));
                fullNameText.setFont(font.deriveFont((float) (getHeight() / 17)));
                loginButton.setMinimumSize(new Dimension(new Dimension(getWidth() / 5, (int) (getHeight() / 7.5))));
                loginButton.setPreferredSize(new Dimension(new Dimension(getWidth() / 5, (int) (getHeight() / 7.5))));
                loginButton.setMaximumSize(new Dimension(new Dimension(getWidth() / 5, (int) (getHeight() / 7.5))));
                loginButton.setFont(font.deriveFont((float) (getHeight() / 17)));
                incorrectUserCredentialsLabel.setPreferredSize((new Dimension(getWidth(), getHeight() / 22)));
                incorrectUserCredentialsLabel.setMinimumSize((new Dimension(getWidth(), getHeight() / 22)));
                incorrectUserCredentialsLabel.setMaximumSize((new Dimension(getWidth(), getHeight() / 22)));
                incorrectUserCredentialsLabel.setFont(new Font("Arial", Font.BOLD, getHeight() / 27));


            }
        });
        this.setVisible(true);

    }
}
