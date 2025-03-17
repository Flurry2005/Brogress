package se.aljr.application.exercise;

import org.checkerframework.checker.units.qual.C;
import se.aljr.application.AppThemeColors;
import se.aljr.application.CustomFont;
import se.aljr.application.ResourcePath;
import se.aljr.application.UserData;
import se.aljr.application.exercise.Excercise.*;
import se.aljr.application.exercise.Muscle.Muscle;
import se.aljr.application.exercise.Muscle.MuscleList;
import se.aljr.application.exercise.Program.Exercises;
import se.aljr.application.loginpage.FirebaseManager;
import se.aljr.application.settings.SettingsPanel;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import java.util.concurrent.ExecutionException;
import javax.sound.sampled.*;
import javax.swing.text.AbstractDocument;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;

public class ExercisePanel extends JPanel {
    private JTextArea titleLabel;
    private JLabel musclesWorkedLabel;
    private Exercise selectedExercise;
    private static int statusDelayCounter;
    private static DefaultListModel<Exercise> exerciseModel;
    private static JList<Exercise> menuList;
    private static JPanel statusPanel;
    private static Timer shrinkStatusPanel;
    private static JLabel statusText;
    private static JPanel mainPanel;
    private static DefaultListModel<Exercise> myExerciseModel;
    private static DefaultListModel<Exercise> favExerciseModel;
    private boolean editState = false;
    DefaultListModel<Muscle> muscleModel = new DefaultListModel<>();
    JList<Muscle> muscleJList = new JList<>(muscleModel);
    private final JPanel northPanel = new JPanel();
    private final JPanel northWestPanel = new JPanel();
    private final JPanel eastPanel = new JPanel();
    private final JPanel aboutContainer = new JPanel();
    private final JLabel aboutLabel = new JLabel();
    private final JLabel formLabel = new JLabel();
    private final JTextArea aboutText = new JTextArea();
    private final JTextPane formText = new JTextPane();
    private final JButton sortMuscleButton = new JButton("Muscle");
    private final JButton showFavorites = new JButton("Favorites");
    private final JButton myExercises = new JButton("Created");
    private final JTextField searchField = new JTextField("Search for exercise...");
    private final JPanel centerPanel = new JPanel();
    public static JButton createExerciseButton = new JButton();
    private final JLabel imageLabel = new JLabel();
    private final JButton editButton = new JButton("\uD83D\uDCDD");;
    Font font;
    protected ImageIcon homePanelBackground;
    protected ImageIcon scaledContentBackgroundPanel;
    Image scaledContentBackground;

    protected ImageIcon lightHomePanelBackground;
    protected ImageIcon scaledLightContentBackgroundPanel;
    Image scaledLightContentBackground;

    public static int selectedTab;

    private Image scaledTest;

    public static ExercisePanel instance;

    public ExercisePanel(int width, int height) {
        homePanelBackground = new ImageIcon(ResourcePath.getResourcePath("bottom_right_bar.png"));
        scaledContentBackground = homePanelBackground.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);
        scaledContentBackgroundPanel = new ImageIcon(scaledContentBackground);

        lightHomePanelBackground = new ImageIcon(ResourcePath.getResourcePath("lightEmptyBackground.png"));
        scaledLightContentBackground = lightHomePanelBackground.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);
        scaledLightContentBackgroundPanel = new ImageIcon(scaledLightContentBackground);

        instance = this;

        try {
            font = Font.createFont(Font.TRUETYPE_FONT, new File(ResourcePath.getResourcePath("BebasNeue-Regular.otf")));
            font = font.deriveFont(40f);
        } catch (Exception e) {
            font = new Font("Arial", Font.BOLD, 40);
            e.printStackTrace();
        }
        // Set the layout for the panel
        this.setPreferredSize(new Dimension(width, height));
        this.setMaximumSize(this.getPreferredSize());
        this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        init(width, height);
    }

    public void init(int width, int height) {

        //-------------------Initialize components---------------------------

        mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        mainPanel.setBackground(AppThemeColors.PRIMARY);
        mainPanel.setPreferredSize(this.getPreferredSize());
        mainPanel.setBorder(new EmptyBorder(20,20,20,20));

        Font emojiFont = new Font("Segoe UI Emoji", Font.PLAIN, 35);

        searchField.setFont(new Font("Arial", Font.ITALIC, 12));
        searchField.setBorder(new LineBorder(new Color(80, 73, 69)));
        searchField.setBackground(AppThemeColors.panelColor);
        searchField.setForeground(AppThemeColors.foregroundColor);

        favExerciseModel = new DefaultListModel<>();
        myExerciseModel = new DefaultListModel<>();

        MuscleList muscleList = new MuscleList();
        for (Muscle muscle : muscleList) {
            muscleModel.addElement(muscle);
        }
        // Display muscles when using filter option

        muscleJList.setBackground(AppThemeColors.PRIMARY);
        muscleJList.setForeground(AppThemeColors.foregroundColor);

        JScrollPane muscleScroll = new JScrollPane(muscleJList);
        muscleScroll.setBorder(null);
        muscleScroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
        muscleScroll.setPreferredSize(new Dimension(getPreferredSize().width, getPreferredSize().height / 3));
        muscleScroll.setMaximumSize(new Dimension(getPreferredSize().width, getPreferredSize().height / 3));
        muscleScroll.setMinimumSize(new Dimension(getPreferredSize().width, getPreferredSize().height / 3));
        muscleScroll.setVisible(false);

        // Populate the JList with exercises
        exerciseModel = new DefaultListModel<>();
        Exercises exercises = new Exercises();
        for (Exercise exercise : exercises.getList()) {
            exerciseModel.addElement(exercise);
        }
        for (Exercise userExercise : UserData.getFavoriteExercises()) {
            exerciseModel.addElement(userExercise);
        }

        menuList = new JList<>(exerciseModel);
        menuList.setBackground(AppThemeColors.panelColor);
        menuList.setForeground(AppThemeColors.foregroundColor);
        menuList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        menuList.setSelectionBackground(new Color(49, 84, 122));
        menuList.setSelectionForeground(AppThemeColors.foregroundColor);

        // Create layout for the panel
        JPanel westPanel = new JPanel();
        westPanel.setLayout(new BoxLayout(westPanel, BoxLayout.Y_AXIS));
        westPanel.setPreferredSize(new Dimension((int)(mainPanel.getPreferredSize().width/4), (int) (mainPanel.getPreferredSize().height / 1.2)));
        westPanel.setMaximumSize(westPanel.getPreferredSize());
        westPanel.setBackground(new Color(22,22,22));
        westPanel.setBorder(new LineBorder(new Color(80, 73, 69), 1, true));

        JPanel searchContainer = new JPanel();
        searchContainer.setBorder(null);
        searchContainer.setLayout(new BorderLayout());
        searchContainer.setBackground(AppThemeColors.panelColor);
        searchContainer.setPreferredSize(new Dimension(westPanel.getPreferredSize().width, getPreferredSize().height / 7));
        searchContainer.setMaximumSize(searchContainer.getPreferredSize());
        searchContainer.setMinimumSize(searchContainer.getPreferredSize());
        searchContainer.setBorder(null);


        JScrollPane exerciseScrollPanel = new JScrollPane(menuList);
        exerciseScrollPanel.setBorder(null);
        exerciseScrollPanel.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);


        searchField.setPreferredSize(new Dimension(searchContainer.getPreferredSize().width, searchContainer.getPreferredSize().height/3));

        sortMuscleButton.setBackground(AppThemeColors.PRIMARY);
        sortMuscleButton.setForeground(AppThemeColors.foregroundColor);
        sortMuscleButton.setPreferredSize(new Dimension((int) (searchContainer.getPreferredSize().getWidth() / 4), searchContainer.getPreferredSize().height/3));
        sortMuscleButton.setFocusable(false);
        sortMuscleButton.setBorder(new LineBorder(new Color(80, 73, 69), 1, true));

        showFavorites.setForeground(AppThemeColors.foregroundColor);
        showFavorites.setBackground(AppThemeColors.PRIMARY);
        showFavorites.setPreferredSize(new Dimension(searchContainer.getPreferredSize().width / 4, searchContainer.getPreferredSize().height/3));
        showFavorites.setFocusable(false);
        showFavorites.setBorder(new LineBorder(new Color(80, 73, 69), 1, true));

        myExercises.setForeground(AppThemeColors.foregroundColor);
        myExercises.setBackground(AppThemeColors.PRIMARY);
        myExercises.setPreferredSize(new Dimension(searchContainer.getPreferredSize().width / 4, searchContainer.getPreferredSize().height/3));
        myExercises.setFocusable(false);
        myExercises.setBorder(new LineBorder(new Color(80, 73, 69), 1, true));

        createExerciseButton.setBackground(new Color(46, 148, 76));
        createExerciseButton.setText("New exercise");
        createExerciseButton.setForeground(Color.white);
        createExerciseButton.setBorder(new LineBorder(new Color(80, 73, 69), 1, true));
        createExerciseButton.setFocusable(false);
        createExerciseButton.setPreferredSize(new Dimension(searchContainer.getPreferredSize().width / 4, searchContainer.getPreferredSize().height/3));
        createExerciseButton.setMaximumSize(createExerciseButton.getPreferredSize());
        createExerciseButton.setMinimumSize(createExerciseButton.getPreferredSize());

        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.X_AXIS));
        centerPanel.setBackground(AppThemeColors.PRIMARY);
        centerPanel.setOpaque(true);
        centerPanel.setBorder(new EmptyBorder(0, 20, 0, 20));
        centerPanel.setPreferredSize(new Dimension((int) (mainPanel.getPreferredSize().width / 2), (int) (mainPanel.getPreferredSize().height / 1.2)));
        centerPanel.setMaximumSize(centerPanel.getPreferredSize());
        centerPanel.setMinimumSize(centerPanel.getPreferredSize());

        //Label displaying the exercise image
        imageLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        imageLabel.setPreferredSize(new Dimension(centerPanel.getPreferredSize().width, (int) (mainPanel.getPreferredSize().height*0.86)));
        imageLabel.setMaximumSize(imageLabel.getPreferredSize());
        imageLabel.setMinimumSize(imageLabel.getPreferredSize());

        eastPanel.setLayout(new BoxLayout(eastPanel, BoxLayout.Y_AXIS));
        eastPanel.setBackground(AppThemeColors.PRIMARY);
        eastPanel.setPreferredSize(new Dimension((int)(mainPanel.getPreferredSize().width/4), (int) (mainPanel.getPreferredSize().height / 1.2)));
        eastPanel.setMinimumSize(eastPanel.getPreferredSize());
        eastPanel.setMaximumSize(eastPanel.getPreferredSize());
        eastPanel.setBorder(null);


        aboutContainer.setLayout(new BorderLayout());
        aboutContainer.setForeground(AppThemeColors.foregroundColor);
        aboutContainer.setBackground(AppThemeColors.PRIMARY);
        aboutContainer.setPreferredSize(new Dimension(eastPanel.getPreferredSize().width, eastPanel.getPreferredSize().height / 2));
        aboutContainer.setMinimumSize(new Dimension(eastPanel.getPreferredSize().width, eastPanel.getPreferredSize().height / 2));
        aboutContainer.setMaximumSize(new Dimension(eastPanel.getPreferredSize().width, eastPanel.getPreferredSize().height / 2));
        aboutContainer.setBorder(null);

        // Displays "About"

        aboutLabel.setText("About");
        aboutLabel.setOpaque(false);
        aboutLabel.setHorizontalAlignment(JLabel.CENTER);
        aboutLabel.setFont(font.deriveFont(24f));
        aboutLabel.setBorder(new LineBorder(new Color(80, 73, 69), 1, true));
        aboutLabel.setPreferredSize(new Dimension(eastPanel.getPreferredSize().width, eastPanel.getPreferredSize().height/18));

        JPanel formInfoContainer = new JPanel();
        formInfoContainer.setLayout(new BorderLayout());
        formInfoContainer.setBackground(new Color(22, 22, 22));
        formInfoContainer.setPreferredSize(new Dimension(eastPanel.getPreferredSize().width, (int) (eastPanel.getPreferredSize().height / 2.3)));
        formInfoContainer.setMaximumSize(formInfoContainer.getPreferredSize());
        formInfoContainer.setMinimumSize(formInfoContainer.getPreferredSize());
        formInfoContainer.setBorder(null);

        // Displays "How to"

        formLabel.setText("How to");
        formLabel.setBackground(AppThemeColors.SECONDARY);
        formLabel.setOpaque(true);
        formLabel.setHorizontalAlignment(JLabel.CENTER);
        formLabel.setFont(font.deriveFont(getHeight()/35f));
        formLabel.setBorder(new LineBorder(new Color(80, 73, 69), 1, true));
        formLabel.setPreferredSize(new Dimension(eastPanel.getPreferredSize().width, eastPanel.getPreferredSize().height/18));

        aboutText.setBackground(AppThemeColors.panelColor);
        aboutText.setPreferredSize(new Dimension(eastPanel.getPreferredSize().width, eastPanel.getPreferredSize().height/2));
        aboutText.setForeground(AppThemeColors.foregroundColor);
        aboutText.setFont(new Font("Arial", Font.PLAIN, 15));
        aboutText.setEditable(false);
        aboutText.setLineWrap(true);
        aboutText.setWrapStyleWord(true);
        aboutText.setBorder(new LineBorder(new Color(80, 73, 69), 1, true));

        formText.setBackground(AppThemeColors.panelColor);
        formText.setForeground(AppThemeColors.foregroundColor);
        formText.setEditable(false);
        formText.setFont(new Font("Arial", Font.PLAIN, 15));
        formText.setFocusable(false);
        formText.setBorder(new LineBorder(new Color(80, 73, 69), 1, true));
        formText.setPreferredSize(new Dimension(formInfoContainer.getPreferredSize().width, (int) (formInfoContainer.getPreferredSize().height/4)));
        formText.setMaximumSize(formText.getPreferredSize());
        formText.setMinimumSize(formText.getPreferredSize());

        northPanel.setBackground(AppThemeColors.PRIMARY);
        northPanel.setLayout(new BorderLayout());
        northPanel.setPreferredSize(new Dimension(mainPanel.getPreferredSize().width, (int) (mainPanel.getPreferredSize().height / 10)));
        northPanel.setMaximumSize(northPanel.getPreferredSize());
        northPanel.setMinimumSize(northPanel.getPreferredSize());
        northPanel.setBorder(null);

        JPanel wrapper = new JPanel();
        wrapper.setLayout(new BorderLayout());
        wrapper.setOpaque(false);
        wrapper.setBorder(null);
        wrapper.setAlignmentX(Component.LEFT_ALIGNMENT);

        titleLabel = new JTextArea();
        titleLabel.setForeground(AppThemeColors.foregroundColor);
        titleLabel.setOpaque(false);
        titleLabel.setEditable(false);
        titleLabel.setFont(font.deriveFont(getHeight()/10f));
        titleLabel.setAlignmentY(Component.CENTER_ALIGNMENT);

        musclesWorkedLabel = new JLabel();
        musclesWorkedLabel.setFont(font.deriveFont(getHeight()/20f));
        musclesWorkedLabel.setForeground(AppThemeColors.foregroundColor);
        musclesWorkedLabel.setHorizontalAlignment(SwingConstants.LEFT);

        JPanel northEastPanel = new JPanel();
        northEastPanel.setLayout(new BoxLayout(northEastPanel, BoxLayout.X_AXIS));
        northEastPanel.setOpaque(false);
        northEastPanel.setPreferredSize(new Dimension(eastPanel.getPreferredSize().width, northPanel.getPreferredSize().height));

        JButton favoriteButton = new JButton("\uD83D\uDCAA");
        favoriteButton.setFocusPainted(false);
        favoriteButton.setContentAreaFilled(false);
        favoriteButton.setBackground(AppThemeColors.PRIMARY);
        favoriteButton.setForeground(AppThemeColors.panelColor);
        favoriteButton.setFont(emojiFont.deriveFont(getHeight()/20f));
        favoriteButton.setPreferredSize(new Dimension(northEastPanel.getPreferredSize().width/4, northEastPanel.getPreferredSize().height));
        favoriteButton.setMaximumSize(favoriteButton.getPreferredSize());
        favoriteButton.setMinimumSize(favoriteButton.getPreferredSize());
        favoriteButton.setBorder(null);
        favoriteButton.setForeground(new Color(21,21,21));

        editButton.setFocusPainted(false);
        editButton.setContentAreaFilled(false);
        editButton.setBackground(AppThemeColors.PRIMARY);
        editButton.setFont(emojiFont);
        editButton.setBorder(null);
        editButton.setPreferredSize(favoriteButton.getPreferredSize());
        editButton.setMaximumSize(editButton.getPreferredSize());
        editButton.setMinimumSize(editButton.getPreferredSize());
        editButton.setVisible(false);

        JButton removeButton = new JButton("\uD83D\uDDD1");
        removeButton.setFocusPainted(false);
        removeButton.setContentAreaFilled(false);
        removeButton.setBackground(AppThemeColors.PRIMARY);
        removeButton.setForeground(new Color(230, 39, 83));
        removeButton.setFont(emojiFont);
        removeButton.setBorder(null);
        removeButton.setPreferredSize(favoriteButton.getPreferredSize());
        removeButton.setMaximumSize(removeButton.getPreferredSize());
        removeButton.setMinimumSize(removeButton.getPreferredSize());
        removeButton.setVisible(false);

        northWestPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        northWestPanel.setBackground(AppThemeColors.PRIMARY);
        northWestPanel.setBorder(new LineBorder(Color.yellow));
        northWestPanel.setPreferredSize(new Dimension((int) (northPanel.getPreferredSize().width/1.5), northPanel.getPreferredSize().height));
        northWestPanel.setMaximumSize(northWestPanel.getPreferredSize());

        // Notice window
        statusPanel = new JPanel();
        statusPanel.setPreferredSize(new Dimension(this.getWidth(), 50));
        statusPanel.setVisible(false);
        statusPanel.setBorder(new LineBorder(new Color(46, 148, 76), 1, true));

        statusText = new JLabel();
        statusText.setForeground(AppThemeColors.foregroundColor);

        // Functionality for the notice window
        shrinkStatusPanel = new Timer(30, _ -> {
            if (statusPanel.getHeight() == 0) {
                return;
            }
            if (statusDelayCounter > 0) {
                statusPanel.setVisible(true);
                statusDelayCounter -= 1;
            } else {
                statusPanel.setPreferredSize(new Dimension(statusPanel.getWidth(), statusPanel.getHeight() - 1));
                statusPanel.repaint();
                statusPanel.revalidate();
            }
        });

        //-------------------ADD COMPONENTS--------------------------

        // WEST
        searchContainer.add(searchField, BorderLayout.NORTH);
        searchContainer.add(sortMuscleButton, BorderLayout.WEST);
        searchContainer.add(showFavorites, BorderLayout.CENTER);
        searchContainer.add(myExercises, BorderLayout.EAST);
        searchContainer.add(myExercises, BorderLayout.EAST);
        searchContainer.add(createExerciseButton, BorderLayout.SOUTH);

        westPanel.add(searchContainer);
        westPanel.add(muscleScroll);
        westPanel.add(exerciseScrollPanel);

        // NORTH
        northEastPanel.add(editButton);
        northEastPanel.add(removeButton);
        northEastPanel.add(Box.createHorizontalGlue());
        northEastPanel.add(favoriteButton);

        northPanel.add(titleLabel, BorderLayout.WEST);
        northPanel.add(musclesWorkedLabel, BorderLayout.CENTER);
        northPanel.add(northEastPanel, BorderLayout.EAST);


        //CENTER
        CreateExerciseModule createExerciseModule = new CreateExerciseModule(centerPanel, this);
        createExerciseModule.setVisible(false);
        centerPanel.add(imageLabel);
        centerPanel.add(createExerciseModule);

        //EAST
        aboutContainer.add(aboutLabel, BorderLayout.NORTH);
        aboutContainer.add(aboutText, BorderLayout.CENTER);
        eastPanel.add(aboutContainer);

        formInfoContainer.add(formLabel, BorderLayout.NORTH);
        formInfoContainer.add(formText, BorderLayout.CENTER);
        eastPanel.add(Box.createVerticalGlue());
        eastPanel.add(formInfoContainer);

        //SOUTH
        statusPanel.add(statusText);

        // MAIN STRUCTURE
        mainPanel.add(northPanel, BorderLayout.NORTH);
        mainPanel.add(westPanel, BorderLayout.WEST);
        mainPanel.add(centerPanel, BorderLayout.CENTER);
        mainPanel.add(eastPanel, BorderLayout.EAST);
        mainPanel.add(statusPanel, BorderLayout.SOUTH);

        this.add(mainPanel);
        this.setOpaque(false);

        //-------------Methods and listeners-------------------

        // SET DEFAULT EXERCISE WHEN ENTERING PANEL
        if (menuList.getSelectedValue() == null) {
            selectedExercise = exerciseModel.getElementAt(0);
            titleLabel.setText(selectedExercise.getName());
            musclesWorkedLabel.setText(selectedExercise.getMusclesUsed());
            aboutText.setText(selectedExercise.getInfo());
            formText.setText(selectedExercise.getForm());
            ImageIcon exerciseImageIcon = selectedExercise.getImageIcon();
            scaledTest = exerciseImageIcon.getImage().getScaledInstance(centerPanel.getPreferredSize().width, (int) (centerPanel.getPreferredSize().height), Image.SCALE_DEFAULT);
            ImageIcon scaledExerciseIcon = new ImageIcon(scaledTest);
            imageLabel.setIcon(scaledExerciseIcon);
        }

        editButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!editState) {
                    createExerciseButton.setEnabled(false);
                    menuList.setEnabled(false);
                    myExercises.setEnabled(false);
                    searchField.setEnabled(false);
                    searchField.setFocusable(false);
                    searchField.setEditable(false);
                    searchField.setText("");
                    searchField.setBackground(AppThemeColors.PRIMARY);
                    sortMuscleButton.setEnabled(false);
                    showFavorites.setEnabled(false);
                    editState = true;
                    removeButton.setVisible(true);
                    editButton.setText("✅");
                    editButton.setForeground(new Color(46, 148, 76));
                    titleLabel.setEditable(true);
                    titleLabel.setOpaque(true);
                    titleLabel.setBackground(new Color(49, 84, 122));
                    aboutText.setEditable(true);
                    aboutText.setOpaque(true);
                    aboutText.setBackground(new Color(49, 84, 122));
                    System.out.println("edit mode on");


                    // RESTRICTS FROM EXCEEDING CHARACTER LIMIT WHEN EDITING EXERCISE NAME
                    AbstractDocument document = (AbstractDocument) titleLabel.getDocument();
                    document.setDocumentFilter(new DocumentFilter() {
                        @Override
                        public void insertString(FilterBypass fb, int offset, String text, AttributeSet attr) throws BadLocationException {
                            if (text != null) {
                                text = text.replace("\n","");
                                text = text.replace("\r","");
                                text = text.replace("\t","");

                            }

                            if (fb.getDocument().getLength() + text.length() <= 22) {
                                super.insertString(fb, offset, text, attr);
                            }
                        }

                        @Override
                        public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs) throws BadLocationException {
                            if (text != null) {
                                text = text.replace("\n","");
                                text = text.replace("\r","");
                                text = text.replace("\t","");
                            }

                            if (fb.getDocument().getLength() + text.length() <= 22) {
                                super.replace(fb, offset, length, text, attrs);
                            }
                        }
                    });
                } else {
                    editState = false;
                    menuList.setEnabled(true);
                    createExerciseButton.setEnabled(true);
                    myExercises.setEnabled(true);
                    searchField.setText("Search for exercise...");
                    searchField.setEnabled(true);
                    searchField.setFocusable(true);
                    searchField.setEditable(true);
                    searchField.setBackground(new Color(21, 21, 21));
                    searchField.setForeground(new Color(214, 214, 214));
                    sortMuscleButton.setEnabled(true);
                    showFavorites.setEnabled(true);
                    int selectedIndex = menuList.getSelectedIndex();
                    String aboutTextString = aboutText.getText();
                    selectedExercise.setName(titleLabel.getText());
                    removeButton.setVisible(false);
                    editButton.setText("\uD83D\uDCDD");
                    titleLabel.setOpaque(false);
                    titleLabel.setEditable(false);
                    aboutText.setBackground(new Color(21, 21, 21));
                    aboutText.setEditable(false);
                    menuList.setSelectedIndex(selectedIndex);
                    selectedExercise.setInfo(aboutTextString);
                    ((AbstractDocument) titleLabel.getDocument()).setDocumentFilter(null);
                    updateMenuList("myExerciseModel");
                    menuList.setSelectedIndex(selectedIndex);
                    System.out.println("edit mode off");
                    // SETS BUTTON MATCH USER THEME
                    if (UserData.getTheme().equals("light")) {
                        editButton.setForeground(new Color(22,22,22));
                    }
                    else {
                        editButton.setForeground(AppThemeColors.foregroundColor);
                    }
                }
                try {
                    FirebaseManager.writeDBCreatedExercises(UserData.getCreatedExercises());
                    FirebaseManager.writeDBFavoriteExercises(UserData.getFavoriteExercises());
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
                revalidate();
                repaint();
            }
        });

        // RESTRICTS FROM EXCEEDING CHARACTER LIMIT WHEN EDITING EXERCISE INFO
        AbstractDocument document2 = (AbstractDocument) aboutText.getDocument();
        document2.setDocumentFilter(new DocumentFilter() {
            @Override
            public void insertString(FilterBypass fb, int offset, String string, AttributeSet attr) throws BadLocationException {
                if (fb.getDocument().getLength() + string.length() <= 800) {
                    super.insertString(fb, offset, string, attr);
                }
            }

            @Override
            public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs) throws BadLocationException {
                if (fb.getDocument().getLength() + text.length() <= 800) {
                    super.replace(fb, offset, length, text, attrs);
                }
            }
        });

        // EVENT TRIGGERS FOR "ADD TO FAVOURITES"-BUTTON
        favoriteButton.addActionListener(_ -> {

            if (selectedExercise != null) {
                if (UserData.getFavoriteExercises().add(selectedExercise)) {
                    String addedStatus = (selectedExercise.getName() + " has been added to favorites!");
                    activateStatus(new Color(46, 148, 76), addedStatus);
                    favoriteButton.setForeground(new Color(196, 196, 49));

                } else {
                    UserData.removeFavoriteExercises(selectedExercise);
                    String removedStatus = (selectedExercise.getName() + " has been removed from favorites!");
                    activateStatus(new Color(204, 20, 20), removedStatus);
                    favoriteButton.setForeground(new Color(22, 22, 22));
                }
                updateMenuList("favExerciseModel");
                try {
                    FirebaseManager.writeDBFavoriteExercises(UserData.getFavoriteExercises());
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });

        // VISUAL INTERACTION WITH "ADD TO FAVOURITE"-BUTTON
        favoriteButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                super.mouseEntered(e);
                favoriteButton.setForeground(new Color(196, 196, 49));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                super.mouseExited(e);
                if (UserData.getFavoriteExercises().contains(selectedExercise)) {
                    favoriteButton.setForeground(new Color(196, 196, 49));
                } else {
                    favoriteButton.setForeground(new Color(22, 22, 22));
                }
            }
        });

        // DISPLAYS MUSCLE LIST FOR FILTERING SEARCH BY MUSCLE
        sortMuscleButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!muscleScroll.isVisible()) {
                    selectedTab = 0;
                    muscleJList.clearSelection();
                    menuList.setModel(new DefaultListModel<>());
                    sortMuscleButton.setBackground(new Color(49, 84, 122));
                    showFavorites.setBackground(AppThemeColors.PRIMARY);
                    myExercises.setBackground(AppThemeColors.PRIMARY);
                    muscleScroll.setVisible(true);
                } else {
                    muscleScroll.setVisible(false);
                    sortMuscleButton.setBackground(AppThemeColors.PRIMARY);
                    menuList.setModel(exerciseModel);
                }
                repaint();
                revalidate();

}
        });

        // TRIGGERS THE EXERCISE CREATION MODULE
        createExerciseButton.addActionListener(_ -> {
            imageLabel.setVisible(false);
            createExerciseModule.setVisible(true);
            centerPanel.revalidate();
            centerPanel.repaint();
        });

        // FILTERS SEARCH RESULTS BASED ON MUSCLE SELECTION
        muscleJList.addListSelectionListener(_ -> {
            DefaultListModel<Exercise> model = new DefaultListModel<>();
            if (muscleJList.getSelectedValue() != null) {
                Muscle selectedMuscle = muscleJList.getSelectedValue();
                for (Exercise exercise : exercises.getList()) {
                    if (exercise.getMusclesUsed().contains(selectedMuscle.toString())) {
                        model.addElement(exercise);
                    }
                }
            }
            menuList.setModel(model);
        });

        // EVENT TRIGGERS FOR "SHOW FAVORITES"-BUTTON
        showFavorites.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!menuList.getModel().equals(favExerciseModel)) {
                    selectedTab = 1;
                    menuList.setModel(favExerciseModel);
                    muscleScroll.setVisible(false);
                    showFavorites.setBackground(new Color(49, 84, 122));
                    sortMuscleButton.setBackground(AppThemeColors.PRIMARY);
                    myExercises.setBackground(AppThemeColors.PRIMARY);
                    menuList.setSelectionBackground(new Color(49, 84, 122));
                    try {
                        UserData.updateFavoriteExercises();
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    } catch (ExecutionException ex) {
                        throw new RuntimeException(ex);
                    } catch (InterruptedException ex) {
                        throw new RuntimeException(ex);
                    } catch (ClassNotFoundException ex) {
                        throw new RuntimeException(ex);
                    }
                    updateMenuList("favExerciseModel");
                }
                else {
                    showFavorites.setBackground(AppThemeColors.PRIMARY);
                    menuList.setModel(exerciseModel);
                }
                repaint();
                revalidate();
            }
        });

        // EVENT TRIGGERS FOR "MY EXERCISES"-BUTTON
        myExercises.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                selectedTab = 2;
                if (!menuList.getModel().equals(myExerciseModel)) {
                    // READ FROM DB AND UPDATE MY EXERCISES
                    try {
                        UserData.updateCreatedExercise();
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    } catch (ExecutionException ex) {
                        throw new RuntimeException(ex);
                    } catch (ClassNotFoundException ex) {
                        throw new RuntimeException(ex);
                    } catch (InterruptedException ex) {
                        throw new RuntimeException(ex);
                    }
                    myExercises.setBackground(new Color(49, 84, 122));
                    sortMuscleButton.setBackground(AppThemeColors.PRIMARY);
                    showFavorites.setBackground(AppThemeColors.PRIMARY);
                    muscleScroll.setVisible(false);
                    updateMenuList("myExerciseModel");
                    menuList.setModel(myExerciseModel);
                }
                else {
                    myExercises.setBackground(AppThemeColors.PRIMARY);
                    menuList.setModel(exerciseModel);
                }
                repaint();
                revalidate();
            }
        });

        // VISUAL INTERACTION WITH SEARCH FIELD
        searchField.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                if (editState) {
                    return;
                }
                updateMenuList("exerciseModel");
                muscleScroll.setVisible(false);
                menuList.setModel(exerciseModel);
                sortMuscleButton.setBackground(AppThemeColors.PRIMARY);
                showFavorites.setBackground(AppThemeColors.PRIMARY);
                myExercises.setBackground(AppThemeColors.PRIMARY);
                revalidate();
                repaint();
            }

        });

        searchField.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                super.focusLost(e);
                if (searchField.getText().isEmpty()) {
                    searchField.setText("Search for exercise...");
                }
            }

            @Override
            public void focusGained(FocusEvent e) {
                if (searchField.getText().equals("Search for exercise...")) {
                    searchField.setText("");
                }
            }
        });

        // FILTERS SEARCH LIST
        searchField.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                if (!searchField.getText().equals("Search for exercise...")) {
                    filterList();
                }
                if (searchField.getText().equals("/YMCA")) {

                    File ymca = new File("src/clip.wav");
                    try {
                        AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(ymca);
                        Clip clip = AudioSystem.getClip();
                        clip.open(audioInputStream);
                        clip.start();
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }
            }

            public void changedUpdate(DocumentEvent e) {
                filterList();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                filterList();
            }

            private void filterList() {
                SwingUtilities.invokeLater(() -> {
                    String searchText = searchField.getText().toLowerCase();
                    exerciseModel.clear();
                    for (Exercise exercise : exercises.getList()) {
                        if (exercise.getName().toLowerCase().contains(searchText)) {
                            exerciseModel.addElement(exercise);
                        }
                    }
                });
            }
        });

        // EVENT ACTIONS FOR REMOVEBUTTON
        removeButton.addActionListener(_ -> {
            int currentIndex = menuList.getSelectedIndex();
            UserData.getCreatedExercises().remove(selectedExercise);
            if (UserData.getFavoriteExercises().contains(selectedExercise)) {
                UserData.removeFavoriteExercises(selectedExercise);
                updateMenuList("favExerciseModel");
            }
            updateMenuList("myExerciseModel");

            // RESET AFTER REMOVAL
            editButton.doClick();
            activateStatus(Color.RED, "Exercise removed!");
            revalidate();
            repaint();
            if (!UserData.getCreatedExercises().isEmpty()) {
                menuList.setSelectedIndex(currentIndex);
            } else {
                titleLabel.setText("");
                musclesWorkedLabel.setText("");
                aboutText.setText("");
                formText.setText("");
                editButton.setVisible(false);
                favoriteButton.setVisible(false);
                createExerciseButton.doClick();
                selectedExercise = null;
            }
            try {
                FirebaseManager.writeDBCreatedExercises(UserData.getCreatedExercises());
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }

        });

        // SET ALL INFORMATION BASED ON SELECTED EXERCISE
        menuList.addListSelectionListener(_ -> {
            if (menuList.getSelectedIndex() != -1) {
                selectedExercise = menuList.getSelectedValue();
                favoriteButton.setVisible(true);
                imageLabel.setVisible(true);
                // SHOW IMAGE
                try {
                    ImageIcon exerciseImageIcon = selectedExercise.getImageIcon();
                    scaledTest = exerciseImageIcon.getImage().getScaledInstance(imageLabel.getPreferredSize().width, (int) (imageLabel.getPreferredSize().height), Image.SCALE_DEFAULT);
                    ImageIcon scaledExerciseIcon = new ImageIcon(scaledTest);
                    formInfoContainer.setVisible(true);
                    imageLabel.setIcon(scaledExerciseIcon);
                    // DISPLAY DEFAULT IF IMAGE NOT FOUND
                } catch (Exception ex) {
                    ImageIcon temp = new ImageIcon(ResourcePath.getResourcePath("bottom_right_bar.png"));
                    scaledTest = temp.getImage().getScaledInstance(imageLabel.getPreferredSize().width, (int) (imageLabel.getPreferredSize().height), Image.SCALE_SMOOTH);
                    ImageIcon scaledTestIcon = new ImageIcon(scaledTest);
                    imageLabel.setIcon(scaledTestIcon);
                    formInfoContainer.setVisible(false);
                }
                // UPDATE EXERCISE CONTENT
                aboutText.setText(selectedExercise.getInfo());
                formText.setText(selectedExercise.getForm());
                titleLabel.setText(selectedExercise.getName());
                // DISPLAY IF INFO IS EMPTY
                if (selectedExercise.getInfo().isEmpty() || selectedExercise.getInfo() == null) {
                    aboutText.setText("No information added...");
                } else {
                    aboutText.setText(selectedExercise.getInfo());
                }
                // RESTRICT CHARACTER LENGTH IN MUSCLES USED
                if (selectedExercise.getMusclesUsed().length() > 32) {
                    String muscles = selectedExercise.getMusclesUsed();
                    muscles = muscles.substring(0, 32);
                    musclesWorkedLabel.setText(muscles + "...");
                } else {
                    musclesWorkedLabel.setText(selectedExercise.getMusclesUsed());
                }
                // DISPLAY FAVORITE BUTTON COLOR DEPENDING ON EXERCISE
                if (UserData.getFavoriteExercises().contains(selectedExercise)) {
                    favoriteButton.setForeground(new Color(196, 196, 49));
                } else {
                    favoriteButton.setForeground(new Color(22, 22, 22));
                }
                // DISPLAY EDIT BUTTON DEPENDING ON EXERCISE
                editButton.setVisible(UserData.getCreatedExercises().contains(selectedExercise));

                repaint();
                revalidate();

            }
        });

        this.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                super.componentResized(e);
                SwingUtilities.invokeLater(()->{

                    mainPanel.setPreferredSize(new Dimension(getWidth(), (int) (getHeight())));
                    mainPanel.setMaximumSize(mainPanel.getPreferredSize());
                    mainPanel.setMaximumSize(mainPanel.getPreferredSize());

                    northPanel.setPreferredSize(new Dimension(mainPanel.getPreferredSize().width, (int) (mainPanel.getPreferredSize().height/10)));
                    northPanel.setMaximumSize(northPanel.getPreferredSize());
                    northPanel.setMinimumSize(northPanel.getPreferredSize());

                    northEastPanel.setPreferredSize(new Dimension(eastPanel.getPreferredSize().width, northPanel.getPreferredSize().height));
                    northEastPanel.setMaximumSize(northEastPanel.getPreferredSize());
                    northWestPanel.setPreferredSize(new Dimension((int) (northPanel.getPreferredSize().width/1.5), northPanel.getPreferredSize().height));
                    northWestPanel.setPreferredSize(northWestPanel.getPreferredSize());

                    favoriteButton.setPreferredSize(new Dimension(northEastPanel.getPreferredSize().width/4, northEastPanel.getPreferredSize().height));
                    removeButton.setPreferredSize(new Dimension(northEastPanel.getPreferredSize().width/4, northEastPanel.getPreferredSize().height));
                    editButton.setPreferredSize(new Dimension(northEastPanel.getPreferredSize().width/4, northEastPanel.getPreferredSize().height));

                    westPanel.setPreferredSize(new Dimension((int)(mainPanel.getPreferredSize().width/4), (int) (mainPanel.getPreferredSize().height /1.2)));
                    westPanel.setMaximumSize(westPanel.getPreferredSize());
                    westPanel.setMinimumSize(westPanel.getPreferredSize());

                    searchContainer.setPreferredSize(new Dimension(westPanel.getPreferredSize().width, westPanel.getPreferredSize().height / 7));
                    searchContainer.setMaximumSize(searchContainer.getPreferredSize());
                    searchContainer.setMinimumSize(searchContainer.getPreferredSize());

                    searchField.setPreferredSize(new Dimension((int) (getWidth() / 1.5), (int) (getHeight() / 22.1)));
                    searchField.setMinimumSize(searchField.getPreferredSize());
                    searchField.setMaximumSize(searchField.getPreferredSize());

                    sortMuscleButton.setPreferredSize(new Dimension((int) (searchContainer.getPreferredSize().getWidth() / 4), searchContainer.getPreferredSize().height/3));
                    sortMuscleButton.setMaximumSize(sortMuscleButton.getPreferredSize());
                    sortMuscleButton.setMinimumSize(sortMuscleButton.getPreferredSize());

                    showFavorites.setPreferredSize(new Dimension(searchContainer.getPreferredSize().width / 4, searchContainer.getPreferredSize().height/3));
                    showFavorites.setMaximumSize(showFavorites.getPreferredSize());
                    showFavorites.setMinimumSize(showFavorites.getPreferredSize());

                    myExercises.setPreferredSize(new Dimension(searchContainer.getPreferredSize().width / 4, searchContainer.getPreferredSize().height/3));
                    myExercises.setMaximumSize(myExercises.getPreferredSize());
                    myExercises.setMinimumSize(myExercises.getPreferredSize());

                    createExerciseButton.setPreferredSize(new Dimension(searchContainer.getPreferredSize().width / 4, searchContainer.getPreferredSize().height/3));
                    createExerciseButton.setMaximumSize(createExerciseButton.getPreferredSize());
                    createExerciseButton.setMinimumSize(createExerciseButton.getPreferredSize());

                    centerPanel.setLayout(new BoxLayout(centerPanel,BoxLayout.Y_AXIS));
                    centerPanel.setPreferredSize(new Dimension((int) (mainPanel.getPreferredSize().width / 2), (int) (mainPanel.getPreferredSize().height / 1.2)));
                    centerPanel.setMaximumSize(centerPanel.getPreferredSize());
                    centerPanel.setMinimumSize(centerPanel.getPreferredSize());
                    centerPanel.setBorder(new EmptyBorder(0, centerPanel.getPreferredSize().width / 50, 0, centerPanel.getPreferredSize().width / 50));

                    imageLabel.setPreferredSize(new Dimension(centerPanel.getPreferredSize().width, (int) (mainPanel.getPreferredSize().height*0.86)));
                    imageLabel.setMaximumSize(imageLabel.getPreferredSize());
                    imageLabel.setMinimumSize(imageLabel.getPreferredSize());
                    imageLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
                    imageLabel.repaint();
                    imageLabel.revalidate();
                    if (selectedExercise != null) {
                        if (selectedExercise.getImageIcon() != null) {
                            ImageIcon exerciseImageIcon = selectedExercise.getImageIcon();
                            scaledTest = exerciseImageIcon.getImage().getScaledInstance(imageLabel.getPreferredSize().width, (int) (imageLabel.getPreferredSize().height), Image.SCALE_DEFAULT);
                            ImageIcon scaledExerciseIcon = new ImageIcon(scaledTest);
                            imageLabel.setIcon(scaledExerciseIcon);
                        } else {
                            ImageIcon temp = new ImageIcon(ResourcePath.getResourcePath("bottom_right_bar.png"));
                            scaledTest = temp.getImage().getScaledInstance(imageLabel.getPreferredSize().width, (int) (imageLabel.getPreferredSize().height), Image.SCALE_SMOOTH);
                            ImageIcon scaledTestIcon = new ImageIcon(scaledTest);
                            imageLabel.setIcon(scaledTestIcon);

                        }
                    }


                    eastPanel.setPreferredSize(new Dimension((int)(mainPanel.getPreferredSize().width/4), (int) (mainPanel.getPreferredSize().height / 1.2)));
                    eastPanel.setMinimumSize(eastPanel.getPreferredSize());
                    eastPanel.setMaximumSize(eastPanel.getPreferredSize());

                    aboutContainer.setPreferredSize(new Dimension(eastPanel.getPreferredSize().width, eastPanel.getPreferredSize().height/ 2));
                    aboutContainer.setMinimumSize(aboutContainer.getPreferredSize());
                    aboutContainer.setMaximumSize(aboutContainer.getPreferredSize());

                    aboutLabel.setPreferredSize(new Dimension(eastPanel.getPreferredSize().width, eastPanel.getPreferredSize().height/18));

                    aboutText.setPreferredSize(new Dimension(aboutContainer.getPreferredSize().width,aboutContainer.getPreferredSize().height/2));
                    aboutText.setMaximumSize(aboutText.getPreferredSize());
                    aboutText.setMinimumSize(aboutText.getPreferredSize());

                    formInfoContainer.setPreferredSize(new Dimension(eastPanel.getPreferredSize().width, (int) (eastPanel.getPreferredSize().height / 2.3)));
                    formInfoContainer.setMaximumSize(formInfoContainer.getPreferredSize());
                    formInfoContainer.setMinimumSize(formInfoContainer.getPreferredSize());

                    formLabel.setPreferredSize(new Dimension(eastPanel.getPreferredSize().width, eastPanel.getPreferredSize().height/18));

                    formText.setPreferredSize(new Dimension(formInfoContainer.getPreferredSize().width, (int) (formInfoContainer.getPreferredSize().height/4)));
                    formText.setMaximumSize(formText.getPreferredSize());
                    formText.setMinimumSize(formText.getPreferredSize());

                    titleLabel.setFont(font.deriveFont(getHeight()/10f));
                    musclesWorkedLabel.setFont(font.deriveFont(getHeight()/20f));
                    aboutText.setFont(new Font("Arial", Font.PLAIN, (int) (getHeight()/45.25)));
                    formText.setFont(new Font("Arial", Font.PLAIN, (int) (getHeight()/45.25)));
                    aboutLabel.setFont(font.deriveFont(getHeight()/30f));
                    formLabel.setFont(font.deriveFont(getHeight()/30f));
                    menuList.setFont(font.deriveFont(getHeight()/39f));
                    muscleJList.setFont(font.deriveFont(getHeight()/39f));
                    sortMuscleButton.setFont(new Font("Arial", Font.BOLD, (int) (getHeight()/55.25)));
                    showFavorites.setFont(new Font("Arial", Font.BOLD, (int) (getHeight()/55.25)));
                    myExercises.setFont(new Font("Arial", Font.BOLD, (int) (getHeight()/55.25)));
                    createExerciseButton.setFont(new Font("Arial", Font.BOLD, (int) (getHeight()/55.25)));
                    searchField.setFont(new Font("Arial", Font.ITALIC, (int) (getHeight() / 55.25)));
                    favoriteButton.setFont(emojiFont.deriveFont(getHeight()/20f));
                    removeButton.setFont(emojiFont.deriveFont(getHeight()/20f));
                    editButton.setFont(emojiFont.deriveFont(getHeight()/20f));

                });
            }
        });
    }

    // NOTICE WINDOW TRIGGER
    public static void activateStatus(Color color, String text) {
        statusDelayCounter = 20;
        statusPanel.setVisible(true);
        statusPanel.setBackground(color);
        statusText.setText(text);
        shrinkStatusPanel.start();
        shrinkStatusPanel.restart();
        statusPanel.setPreferredSize(new Dimension(statusPanel.getWidth(), 50));
    }

    // UPDATE THE MENU LIST IN REAL TIME
    public static void updateMenuList(String modelName) {
        if (modelName.equals("myExerciseModel")) {
            myExerciseModel.clear();
            for (Exercise exercise : UserData.getCreatedExercises()) {
                myExerciseModel.addElement(exercise);
            }
            menuList.setModel(myExerciseModel);
        } else if (modelName.equals("favExerciseModel")) {
            favExerciseModel.clear();
            for (Exercise exercise : UserData.getFavoriteExercises()) {
                favExerciseModel.addElement(exercise);
            }
        } else {
            exerciseModel.clear();
            Exercises exercises = new Exercises();
            for (Exercise exercise : exercises.getList()) {
                exerciseModel.addElement(exercise);
            }
        }
        menuList.revalidate();
        menuList.repaint();
    }

    public void updateColors() {

        mainPanel.setBackground(AppThemeColors.PRIMARY);
        menuList.setBackground(AppThemeColors.panelColor);
        muscleJList.setBackground(AppThemeColors.PRIMARY);
        northPanel.setBackground(AppThemeColors.PRIMARY);
        northWestPanel.setBackground(AppThemeColors.PRIMARY);
        eastPanel.setBackground(AppThemeColors.PRIMARY);
        aboutContainer.setBackground(AppThemeColors.PRIMARY);
        aboutLabel.setBackground(AppThemeColors.PRIMARY);
        searchField.setBackground(AppThemeColors.textFieldColor);
        centerPanel.setBackground(AppThemeColors.PRIMARY);
        formLabel.setBackground(AppThemeColors.PRIMARY);

        if (!editState) {
            aboutText.setBackground(AppThemeColors.panelColor);
        }
        formText.setBackground(AppThemeColors.panelColor);

        statusText.setForeground(AppThemeColors.foregroundColor);
        aboutText.setForeground(AppThemeColors.foregroundColor);
        formText.setForeground(AppThemeColors.foregroundColor);
        showFavorites.setForeground(AppThemeColors.foregroundColor);
        muscleJList.setForeground(AppThemeColors.foregroundColor);
        sortMuscleButton.setBackground(AppThemeColors.PRIMARY);
        sortMuscleButton.setForeground(AppThemeColors.foregroundColor);
        myExercises.setForeground(AppThemeColors.foregroundColor);
        aboutLabel.setForeground(AppThemeColors.foregroundColor);
        editButton.setForeground(AppThemeColors.foregroundColor);
        formLabel.setForeground(AppThemeColors.foregroundColor);
        menuList.setForeground(AppThemeColors.foregroundColor);
        titleLabel.setForeground(AppThemeColors.foregroundColor);
        musclesWorkedLabel.setForeground(AppThemeColors.foregroundColor);
        searchField.setForeground(AppThemeColors.foregroundColor);
        System.out.println("UPDAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAATING COLORESSS LOLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLL");

        switch(selectedTab){
            case 0 ->{
                showFavorites.setBackground(AppThemeColors.PRIMARY);
                myExercises.setBackground(AppThemeColors.PRIMARY);
            }
            case 1->{
                sortMuscleButton.setBackground(AppThemeColors.PRIMARY);
                myExercises.setBackground(AppThemeColors.PRIMARY);
            }
            case 2->{
                sortMuscleButton.setBackground(AppThemeColors.PRIMARY);
                showFavorites.setBackground(AppThemeColors.PRIMARY);
            }
            default->{
                sortMuscleButton.setBackground(AppThemeColors.PRIMARY);
                showFavorites.setBackground(AppThemeColors.PRIMARY);
                myExercises.setBackground(AppThemeColors.PRIMARY);
            }
        }
    }


    // THEME HANDLER
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        System.out.println("UPDAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAATING COLORESSS LOLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLL");
        updateColors();
        // Draw the image to fill the entire panel
        if (homePanelBackground != null) {
            if (!SettingsPanel.lightMode) {
                g.drawImage(scaledContentBackgroundPanel.getImage(), 0, 0, getWidth(), getHeight(), this);
                if (menuList.getSelectedIndex() != -1) {
                    selectedExercise = menuList.getSelectedValue();
                    imageLabel.setVisible(true);
                    // SHOW IMAGE
                    try {
                        ImageIcon exerciseImageIcon = selectedExercise.getImageIcon();
                        scaledTest = exerciseImageIcon.getImage().getScaledInstance(imageLabel.getPreferredSize().width, (int) (imageLabel.getPreferredSize().height), Image.SCALE_DEFAULT);
                        ImageIcon scaledExerciseIcon = new ImageIcon(scaledTest);
                        imageLabel.setIcon(scaledExerciseIcon);
                        // DISPLAY DEFAULT IF IMAGE NOT FOUND
                    } catch (Exception ex) {
                        ImageIcon temp = new ImageIcon(ResourcePath.getResourcePath("bottom_right_bar.png"));
                        scaledTest = temp.getImage().getScaledInstance(imageLabel.getPreferredSize().width, (int) (imageLabel.getPreferredSize().height), Image.SCALE_SMOOTH);
                        ImageIcon scaledTestIcon = new ImageIcon(scaledTest);
                        imageLabel.setIcon(scaledTestIcon);
                    }
                }
            } else {
                g.drawImage(scaledLightContentBackgroundPanel.getImage(), 0, 0, getWidth(), getHeight(), this);
                if (menuList.getSelectedIndex() != -1) {
                    selectedExercise = menuList.getSelectedValue();
                    imageLabel.setVisible(true);
                    // SHOW IMAGE
                    try {
                        ImageIcon exerciseImageIcon = selectedExercise.getImageIcon();
                        scaledTest = exerciseImageIcon.getImage().getScaledInstance(imageLabel.getPreferredSize().width, (int) (imageLabel.getPreferredSize().height), Image.SCALE_DEFAULT);
                        ImageIcon scaledExerciseIcon = new ImageIcon(scaledTest);
                        imageLabel.setIcon(scaledExerciseIcon);
                        // DISPLAY DEFAULT IF IMAGE NOT FOUND
                    } catch (Exception ex) {
                        ImageIcon temp = new ImageIcon(ResourcePath.getResourcePath("bottom_right_bar_light.png"));
                        scaledTest = temp.getImage().getScaledInstance(imageLabel.getPreferredSize().width, (int) (imageLabel.getPreferredSize().height), Image.SCALE_SMOOTH);
                        ImageIcon scaledTestIcon = new ImageIcon(scaledTest);
                        imageLabel.setIcon(scaledTestIcon);
                    }
                }
            }

        } else {
            System.out.println("Error");
        }
    }


    public void reScaleBackground() {
        scaledContentBackground = homePanelBackground.getImage().getScaledInstance(getWidth(), getHeight(), Image.SCALE_SMOOTH);
        scaledContentBackgroundPanel = new ImageIcon(scaledContentBackground);
    }
    public void updateWindow() {

    }
}





