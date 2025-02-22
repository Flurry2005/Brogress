package se.aljr.application.exercise;

import se.aljr.application.AppThemeColors;
import se.aljr.application.ResourcePath;
import se.aljr.application.UserData;
import se.aljr.application.exercise.Excercise.*;
import se.aljr.application.exercise.Muscle.Muscle;
import se.aljr.application.exercise.Muscle.MuscleList;
import se.aljr.application.exercise.Program.Exercises;
import se.aljr.application.loginpage.FirebaseManager;
import se.aljr.application.settings.SettingsPanel;

import javax.swing.*;
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
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.text.AbstractDocument;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;

public class ExercisePanel extends JPanel {
    private JTextArea titleLabel;
    private JLabel musclesWorkedLabel;
    private String resourcePath;
    private Exercise selectedExercise;
    private static int statusDelayCounter;
    private static StringBuilder status = new StringBuilder();
    private static DefaultListModel<Exercise> exerciseModel;
    private static JList<Exercise> menuList;
    private static JPanel statusPanel;
    private static Timer shrinkStatusPanel;
    private static JLabel statusText;
    private static JPanel mainPanel;
    private static DefaultListModel<Exercise> myExerciseModel;
    private static DefaultListModel<Exercise> favExerciseModel;
    private boolean editState = false;
    private boolean isFavourite = false;
    DefaultListModel<Muscle> muscleModel = new DefaultListModel<>();
    JList<Muscle> muscleJList = new JList(muscleModel);
    JPanel northPanel = new JPanel();
    JPanel northWestPanel = new JPanel();
    JPanel eastPanel = new JPanel();
    JPanel aboutContainer = new JPanel();
    JLabel aboutLabel = new JLabel();
    JLabel formLabel = new JLabel();
    JTextArea aboutText = new JTextArea();
    JTextPane formText = new JTextPane();
    JButton sortMuscleButton = new JButton("Muscle");
    JButton showFavorites = new JButton("Favorites");
    JButton myExercises = new JButton("Created");
    JTextField searchField = new JTextField("Search for exercise...");
    JPanel centerPanel = new JPanel();
    JLabel imageLabel = new JLabel();

    Font font;

    protected ImageIcon homePanelBackground;
    protected ImageIcon scaledContentBackgroundPanel;
    Image scaledContentBackground;

    protected ImageIcon lightHomePanelBackground;
    protected ImageIcon scaledLightContentBackgroundPanel;
    Image scaledLightContentBackground;


    public static ExercisePanel instance;

    public ExercisePanel(int width, int height) throws InterruptedException {
        resourcePath = getClass().getClassLoader().getResource("resource.path").getPath().replace("resource.path", "");
        homePanelBackground = new ImageIcon(resourcePath + "bottom_right_bar.png");
        scaledContentBackground = homePanelBackground.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);
        scaledContentBackgroundPanel = new ImageIcon(scaledContentBackground);

        lightHomePanelBackground = new ImageIcon(resourcePath + "lightEmptyBackground.png");
        scaledLightContentBackground = lightHomePanelBackground.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);
        scaledLightContentBackgroundPanel = new ImageIcon(scaledLightContentBackground);

        instance = this;

        resourcePath = getClass().getClassLoader().getResource("resource.path").getPath().replace("resource.path", "");
        try {
            font = Font.createFont(Font.TRUETYPE_FONT, new File(resourcePath + "BebasNeue-Regular.otf"));
            font = font.deriveFont(40f);
        } catch (Exception e) {
            font = new Font("Arial", Font.BOLD, 40);
            e.printStackTrace();
        }
        // Set the layout for the panel
        this.setPreferredSize(new Dimension(width, height));
        this.setMaximumSize(this.getPreferredSize());
        this.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));
        init(width, height);
    }

    public void init(int width, int height) {

        //-------------------Initialize components---------------------------

        mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        mainPanel.setBackground(AppThemeColors.PRIMARY);
        mainPanel.setPreferredSize(this.getPreferredSize());
        mainPanel.setBorder(new EmptyBorder(height / 45, width / 45, height / 45, width / 45));

        Font emojiFont = new Font("Segoe UI Emoji", Font.PLAIN, 35);

        JButton editButton = new JButton("\uD83D\uDCDD");
        editButton.setFocusPainted(false);
        editButton.setContentAreaFilled(false);
        editButton.setBackground(AppThemeColors.PRIMARY);
        editButton.setForeground(new Color(204, 204, 204));
        editButton.setFont(emojiFont);
        editButton.setBorder(null);
        editButton.setPreferredSize(new Dimension(mainPanel.getPreferredSize().width / 22, mainPanel.getPreferredSize().height / 10));
        editButton.setVisible(false);

        JButton removeButton = new JButton("\uD83D\uDDD1");
        removeButton.setFocusPainted(false);
        removeButton.setContentAreaFilled(false);
        removeButton.setBackground(new Color(51, 51, 51));
        removeButton.setForeground(new Color(230, 39, 83));
        removeButton.setFont(emojiFont);
        removeButton.setBorder(null);
        removeButton.setPreferredSize(new Dimension(mainPanel.getPreferredSize().width / 22, mainPanel.getPreferredSize().height / 10));
        removeButton.setVisible(false);

        JButton favoriteButton = new JButton("\uD83D\uDCAA");
        favoriteButton.setFocusPainted(false);
        favoriteButton.setContentAreaFilled(false);
        favoriteButton.setBackground(new Color(51, 51, 51));
        favoriteButton.setForeground(new Color(21, 21, 21));
        favoriteButton.setFont(emojiFont);
        favoriteButton.setBorder(null);
        favoriteButton.setPreferredSize(new Dimension(mainPanel.getPreferredSize().width / 22, mainPanel.getPreferredSize().height / 10));

        titleLabel = new JTextArea();
        titleLabel.setFont(font.deriveFont(65.f));
        titleLabel.setForeground(new Color(204, 204, 204));
        titleLabel.setOpaque(false);
        titleLabel.setEditable(false);

        musclesWorkedLabel = new JLabel();
        musclesWorkedLabel.setFont(font.deriveFont(24f));
        musclesWorkedLabel.setForeground(new Color(204, 204, 204));
        musclesWorkedLabel.setAlignmentX(Component.CENTER_ALIGNMENT);


        searchField.setFont(new Font("Arial", Font.ITALIC, 12));
        searchField.setBorder(new LineBorder(new Color(80, 73, 69)));
        searchField.setBackground(new Color(21, 21, 21));
        searchField.setForeground(new Color(204, 204, 204));
        searchField.setPreferredSize(new Dimension(130, 30));

        favExerciseModel = new DefaultListModel<>();
        myExerciseModel = new DefaultListModel<>();


        MuscleList muscleList = new MuscleList();
        for (Muscle muscle : muscleList) {
            muscleModel.addElement(muscle);
        }
        // Display muscles when using filter option

        muscleJList.setBackground(new Color(51, 51, 51));
        muscleJList.setForeground(new Color(204, 204, 204));

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
        menuList.setFont(font.deriveFont(17f));
        menuList.setBackground(new Color(21, 21, 21));
        menuList.setForeground(new Color(204, 204, 204));
        menuList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        menuList.setSelectionBackground(new Color(49, 84, 122));
        menuList.setSelectionForeground(new Color(204, 204, 204));

        JScrollPane exerciseScrollPanel = new JScrollPane(menuList);
        exerciseScrollPanel.setBorder(new LineBorder(new Color(80, 73, 69)));
        exerciseScrollPanel.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);

        // Create layout for the panel
        JPanel westPanel = new JPanel();
        westPanel.setLayout(new BoxLayout(westPanel, BoxLayout.Y_AXIS));
        westPanel.setBackground(new Color(51, 51, 51));
        westPanel.setPreferredSize(new Dimension(mainPanel.getPreferredSize().width / 4, mainPanel.getPreferredSize().height / 8));
        westPanel.setBorder(new LineBorder(new Color(80, 73, 69), 1 / 2, true));

        JPanel searchContainer = new JPanel();
        searchContainer.setBorder(null);
        searchContainer.setLayout(new BorderLayout());
        searchContainer.setBackground(new Color(21, 21, 21));
        searchContainer.setPreferredSize(new Dimension(westPanel.getPreferredSize().width, getPreferredSize().height / 8));
        searchContainer.setMaximumSize(new Dimension(westPanel.getPreferredSize().width, getPreferredSize().height / 8));
        searchContainer.setMinimumSize(new Dimension(westPanel.getPreferredSize().width, getPreferredSize().height / 8));

        sortMuscleButton.setBackground(new Color(51, 51, 51));
        sortMuscleButton.setForeground(new Color(204, 204, 204));
        sortMuscleButton.setPreferredSize(new Dimension((int) (searchContainer.getPreferredSize().getWidth() / 4), getPreferredSize().height / 20));
        sortMuscleButton.setMaximumSize(new Dimension((int) (searchContainer.getPreferredSize().getWidth() / 4), getPreferredSize().height / 20));
        sortMuscleButton.setMinimumSize(new Dimension((int) (searchContainer.getPreferredSize().getWidth() / 4), getPreferredSize().height / 20));
        sortMuscleButton.setBorder(new LineBorder(new Color(80, 73, 69), 1, true));

        showFavorites.setForeground(new Color(204, 204, 204));
        showFavorites.setBackground(new Color(51, 51, 51));
        showFavorites.setPreferredSize(new Dimension((int) (searchContainer.getPreferredSize().width / 4), getPreferredSize().height / 20));
        showFavorites.setMaximumSize(new Dimension((int) (searchContainer.getPreferredSize().width / 4), getPreferredSize().height / 20));
        showFavorites.setMinimumSize(new Dimension((int) (searchContainer.getPreferredSize().width / 4), getPreferredSize().height / 20));
        showFavorites.setBorder(new LineBorder(new Color(80, 73, 69), 1, true));

        myExercises.setForeground(new Color(204, 204, 204));
        myExercises.setBackground(new Color(51, 51, 51));
        myExercises.setPreferredSize(new Dimension((int) (searchContainer.getPreferredSize().width / 4), getPreferredSize().height / 20));
        myExercises.setMaximumSize(new Dimension((int) (searchContainer.getPreferredSize().width / 4), getPreferredSize().height / 20));
        myExercises.setMinimumSize(new Dimension((int) (searchContainer.getPreferredSize().width / 4), getPreferredSize().height / 20));
        myExercises.setBorder(new LineBorder(new Color(80, 73, 69), 1, true));

        JButton createExerciseButton = new JButton("New exercise");
        createExerciseButton.setBackground(new Color(46, 148, 76));
        createExerciseButton.setForeground(Color.WHITE);
        createExerciseButton.setBorder(new LineBorder(new Color(80, 73, 69), 1, true));
        createExerciseButton.setPreferredSize(new Dimension(westPanel.getPreferredSize().width, getPreferredSize().height / 24));
        createExerciseButton.setMaximumSize(new Dimension(westPanel.getPreferredSize().width, getPreferredSize().height / 24));
        createExerciseButton.setMinimumSize(new Dimension(westPanel.getPreferredSize().width, getPreferredSize().height / 24));

        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));
        centerPanel.setBackground(new Color(51, 51, 51));
        centerPanel.setPreferredSize(new Dimension((int) (mainPanel.getPreferredSize().width / 2.5365), (int) (mainPanel.getPreferredSize().height / 1.1714)));
        centerPanel.setBorder(new EmptyBorder(0, centerPanel.getPreferredSize().width / 20, 0, centerPanel.getPreferredSize().width / 20));

        //Label displaying the exercise image

        imageLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        imageLabel.setPreferredSize(new Dimension(centerPanel.getPreferredSize().width, centerPanel.getPreferredSize().height));
        imageLabel.setMaximumSize(new Dimension(centerPanel.getPreferredSize().width, centerPanel.getPreferredSize().height));
        imageLabel.setMinimumSize(new Dimension(centerPanel.getPreferredSize().width, centerPanel.getPreferredSize().height));


        eastPanel.setLayout(new BoxLayout(eastPanel, BoxLayout.Y_AXIS));
        eastPanel.setBackground(new Color(51, 51, 51));
        eastPanel.setPreferredSize(new Dimension(mainPanel.getPreferredSize().width / 4, mainPanel.getPreferredSize().height));
        eastPanel.setMinimumSize(new Dimension(mainPanel.getPreferredSize().width / 4, mainPanel.getPreferredSize().height));
        eastPanel.setMaximumSize(new Dimension(mainPanel.getPreferredSize().width / 4, mainPanel.getPreferredSize().height));

        exerciseScrollPanel.setPreferredSize(new Dimension(getPreferredSize().width, getPreferredSize().height));
        exerciseScrollPanel.setMaximumSize(new Dimension(getPreferredSize().width, getPreferredSize().height));

        aboutContainer.setLayout(new BorderLayout());
        aboutContainer.setForeground(new Color(204, 204, 204));
        aboutContainer.setBackground(new Color(51, 51, 51));
        aboutContainer.setPreferredSize(new Dimension(eastPanel.getPreferredSize().width, eastPanel.getPreferredSize().height / 2));
        aboutContainer.setMinimumSize(new Dimension(eastPanel.getPreferredSize().width, eastPanel.getPreferredSize().height / 2));
        aboutContainer.setMaximumSize(new Dimension(eastPanel.getPreferredSize().width, eastPanel.getPreferredSize().height / 2));
        aboutContainer.setBorder(new EmptyBorder(0, 0, 10, 0));

        // Displays "About"

        aboutLabel.setText("About");
        aboutLabel.setForeground(new Color(204, 204, 204));
        aboutLabel.setBackground(new Color(51, 51, 51));
        aboutLabel.setOpaque(true);
        aboutLabel.setHorizontalAlignment(JLabel.CENTER);
        aboutLabel.setFont(font.deriveFont(24f));
        aboutLabel.setBorder(new LineBorder(new Color(80, 73, 69), 1, true));

        JPanel formInfoContainer = new JPanel();
        formInfoContainer.setLayout(new BorderLayout());
        formInfoContainer.setBackground(new Color(22, 22, 22));
        formInfoContainer.setMaximumSize(new Dimension(eastPanel.getPreferredSize().width, eastPanel.getPreferredSize().height / 2));
        formInfoContainer.setPreferredSize(new Dimension(eastPanel.getPreferredSize().width, eastPanel.getPreferredSize().height / 2));
        formInfoContainer.setMinimumSize(new Dimension(eastPanel.getPreferredSize().width, eastPanel.getPreferredSize().height / 2));

        // Displays "How to"

        formLabel.setText("How to");
        formLabel.setForeground(new Color(204, 204, 204));
        formLabel.setBackground(new Color(51, 51, 51));
        formLabel.setOpaque(true);
        formLabel.setHorizontalAlignment(JLabel.CENTER);
        formLabel.setVerticalAlignment(JLabel.BOTTOM);
        formLabel.setVerticalTextPosition(JLabel.BOTTOM);
        formLabel.setFont(font.deriveFont(24f));
        formLabel.setBorder(new LineBorder(new Color(80, 73, 69), 1, true));


        aboutText.setBackground(new Color(21, 21, 21));
        aboutText.setForeground(new Color(204, 204, 204));
        aboutText.setFont(new Font("Arial", Font.TRUETYPE_FONT, 15));
        aboutText.setEditable(false);
        aboutText.setBorder(new LineBorder(new Color(80, 73, 69), 1, true));
        aboutText.setLineWrap(true);

        formText.setBackground(new Color(21, 21, 21));
        formText.setForeground(new Color(204, 204, 204));
        formText.setEditable(false);
        formText.setFont(new Font("Arial", Font.TRUETYPE_FONT, 15));
        formText.setFocusable(false);
        formText.setBorder(new LineBorder(new Color(80, 73, 69), 1, true));

        northPanel.setBackground(new Color(51, 51, 51));
        northPanel.setLayout(new BorderLayout());
        northPanel.setPreferredSize(new Dimension(mainPanel.getPreferredSize().width, mainPanel.getPreferredSize().height / 8));

        JPanel northEastPanel = new JPanel();
        northEastPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        northEastPanel.setOpaque(false);

        northWestPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        northWestPanel.setBackground(new Color(51, 51, 51));

        // Notice window
        statusPanel = new JPanel();
        statusPanel.setPreferredSize(new Dimension(this.getWidth(), 50));
        statusPanel.setVisible(false);
        statusText = new JLabel(status.toString());
        statusText.setForeground(new Color(204, 204, 204));
        statusPanel.setBorder(new LineBorder(new Color(46, 148, 76), 1, true));

        // Functionality for the notice window
        shrinkStatusPanel = new Timer(30, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
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
        northEastPanel.add(favoriteButton);

        northWestPanel.add(titleLabel);
        northWestPanel.add(musclesWorkedLabel);

        northPanel.add(northWestPanel, BorderLayout.WEST);
        northPanel.add(northEastPanel, BorderLayout.EAST);

        //CENTER
        CreateExerciseModule createExerciseModule = new CreateExerciseModule(centerPanel);
        createExerciseModule.setVisible(false);
        centerPanel.add(imageLabel);
        centerPanel.add(createExerciseModule);

        //EAST
        aboutContainer.add(aboutLabel, BorderLayout.NORTH);
        aboutContainer.add(aboutText, BorderLayout.CENTER);
        eastPanel.add(aboutContainer);

        formInfoContainer.add(formLabel, BorderLayout.NORTH);
        formInfoContainer.add(formText, BorderLayout.CENTER);
        eastPanel.add(formInfoContainer);

        //SOUTH
        statusPanel.add(statusText);

        // MAIN STRUCTURE
        mainPanel.add(eastPanel, BorderLayout.EAST);
        mainPanel.add(centerPanel, BorderLayout.CENTER);
        mainPanel.add(northPanel, BorderLayout.NORTH);
        mainPanel.add(westPanel, BorderLayout.WEST);
        mainPanel.add(statusPanel, BorderLayout.SOUTH);

        this.add(mainPanel);
        this.setOpaque(false);

        //-------------Methods and listeners-------------------

        // SET DEFAULT EXERCISE WHEN ENTERING PANEL
        if (menuList.getSelectedValue() == null) {
            selectedExercise = exerciseModel.getElementAt(0);
            titleLabel.setText(selectedExercise.getName());
            musclesWorkedLabel.setText(selectedExercise.getMusclesUsed().toString());
            aboutText.setText(selectedExercise.getInfo());
            formText.setText(selectedExercise.getForm());
            ImageIcon exerciseImageIcon = selectedExercise.getImageIcon();
            Image scaledTest = exerciseImageIcon.getImage().getScaledInstance(centerPanel.getPreferredSize().width, centerPanel.getPreferredSize().height, Image.SCALE_DEFAULT);
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
                    searchField.setBackground(new Color(51, 51, 51));
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
                        public void insertString(FilterBypass fb, int offset, String string, AttributeSet attr) throws BadLocationException {
                            if (fb.getDocument().getLength() + string.length() <= 22) {
                                super.insertString(fb, offset, string, attr);
                            }
                        }

                        @Override
                        public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs) throws BadLocationException {
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
                    editButton.setForeground(new Color(204, 204, 204));
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
        favoriteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if (selectedExercise != null) {
                    if (UserData.getFavoriteExercises().add(selectedExercise)) {
                        String addedStatus = (selectedExercise.getName() + " has been added to favorites!");
                        activateStatus(new Color(46, 148, 76), addedStatus);
                        statusPanel.setBackground(new Color(46, 148, 76));
                        status.setLength(0);
                        favoriteButton.setForeground(new Color(196, 196, 49));

                    } else {
                        UserData.removeFavoriteExercises(selectedExercise);
                        String removedStatus = (selectedExercise.getName() + " has been removed from favorites!");
                        activateStatus(new Color(204, 20, 20), removedStatus);
                        statusPanel.setBackground(new Color(204, 20, 20));
                        status.setLength(0);
                        favoriteButton.setForeground(new Color(22, 22, 22));
                    }
                    updateMenuList("favExerciseModel");
                    try {
                        FirebaseManager.writeDBFavoriteExercises(UserData.getFavoriteExercises());
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
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
                muscleJList.clearSelection();
                menuList.setModel(new DefaultListModel<>());
                sortMuscleButton.setBackground(new Color(49, 84, 122));
                showFavorites.setBackground(new Color(51, 51, 51));
                myExercises.setBackground(new Color(51, 51, 51));
                muscleScroll.setVisible(true);
                repaint();
                revalidate();

            }
        });

        // TRIGGERS THE EXERCISE CREATION MODULE
        createExerciseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                imageLabel.setVisible(false);
                createExerciseModule.setVisible(true);
                centerPanel.revalidate();
                centerPanel.repaint();
            }
        });
        // FILTERS SEARCH RESULTS BASED ON MUSCLE SELECTION
        muscleJList.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
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
            }
        });

        // EVENT TRIGGERS FOR "SHOW FAVORITES"-BUTTON
        showFavorites.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                menuList.setModel(favExerciseModel);
                muscleScroll.setVisible(false);
                showFavorites.setBackground(new Color(49, 84, 122));
                sortMuscleButton.setBackground(new Color(51, 51, 51));
                myExercises.setBackground(new Color(51, 51, 51));
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
                repaint();
                revalidate();
            }
        });

        // EVENT TRIGGERS FOR "MY EXERCISES"-BUTTON
        myExercises.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
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
                sortMuscleButton.setBackground(new Color(51, 51, 51));
                showFavorites.setBackground(new Color(51, 51, 51));
                muscleScroll.setVisible(false);
                updateMenuList("myExerciseModel");
                menuList.setModel(myExerciseModel);
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
                sortMuscleButton.setBackground(new Color(51, 51, 51));
                showFavorites.setBackground(new Color(51, 51, 51));
                myExercises.setBackground(new Color(51, 51, 51));
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
        removeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
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

            }
        });

        // SET ALL INFORMATION BASED ON SELECTED EXERCISE
        menuList.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (menuList.getSelectedIndex() != -1) {
                    selectedExercise = menuList.getSelectedValue();
                    favoriteButton.setVisible(true);
                    imageLabel.setVisible(true);
                    // SHOW IMAGE
                    try {
                        ImageIcon exerciseImageIcon = selectedExercise.getImageIcon();
                        Image scaledTest = exerciseImageIcon.getImage().getScaledInstance(centerPanel.getPreferredSize().width, centerPanel.getPreferredSize().height, Image.SCALE_DEFAULT);
                        ImageIcon scaledExerciseIcon = new ImageIcon(scaledTest);
                        formInfoContainer.setVisible(true);
                        imageLabel.setIcon(scaledExerciseIcon);
                    // DISPLAY DEFAULT IF IMAGE NOT FOUND
                    } catch (Exception ex) {
                        ImageIcon temp = new ImageIcon(ResourcePath.getResourcePath() + "bottom_right_bar.png");
                        Image scaledTest = temp.getImage().getScaledInstance(centerPanel.getPreferredSize().width, centerPanel.getPreferredSize().height, Image.SCALE_SMOOTH);
                        ImageIcon scaledTestIcon = new ImageIcon(scaledTest);
                        imageLabel.setIcon(scaledTestIcon);
                        formInfoContainer.setVisible(false);
                    }
                    // UPDATE EXERCISE CONTENT
                    formText.setText(selectedExercise.getInfo());
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
                    if (UserData.getCreatedExercises().contains(selectedExercise)) {
                        editButton.setVisible(true);
                    } else {
                        editButton.setVisible(false);
                    }

                    repaint();
                    revalidate();

                }
            }
        });
    }

    // NOTICE WINDOW TRIGGER
    public static void activateStatus(Color color, String status) {
        statusDelayCounter = 20;
        statusPanel.setVisible(true);
        statusPanel.setBackground(color);
        statusText.setText(status.toString());
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
        statusText.setForeground(AppThemeColors.foregroundColor);
        mainPanel.setBackground(AppThemeColors.PRIMARY);
        menuList.setBackground(AppThemeColors.panelColor);
        muscleJList.setBackground(AppThemeColors.PRIMARY);
        northPanel.setBackground(AppThemeColors.PRIMARY);
        northWestPanel.setBackground(AppThemeColors.PRIMARY);
        eastPanel.setBackground(AppThemeColors.PRIMARY);
        aboutContainer.setBackground(AppThemeColors.PRIMARY);
        aboutLabel.setBackground(AppThemeColors.PRIMARY);
        aboutText.setBackground(AppThemeColors.panelColor);
        formText.setBackground(AppThemeColors.panelColor);
        sortMuscleButton.setBackground(AppThemeColors.PRIMARY);
        showFavorites.setBackground(AppThemeColors.PRIMARY);
        myExercises.setBackground(AppThemeColors.PRIMARY);
        searchField.setBackground(AppThemeColors.textFieldColor);
        centerPanel.setBackground(AppThemeColors.PRIMARY);

        aboutText.setForeground(AppThemeColors.foregroundColor);
        formText.setForeground(AppThemeColors.foregroundColor);

        muscleJList.setForeground(AppThemeColors.foregroundColor);
        sortMuscleButton.setForeground(AppThemeColors.foregroundColor);
        showFavorites.setForeground(AppThemeColors.foregroundColor);
        myExercises.setForeground(AppThemeColors.foregroundColor);
        aboutLabel.setForeground(AppThemeColors.foregroundColor);
        formLabel.setBackground(AppThemeColors.PRIMARY);
        formLabel.setForeground(AppThemeColors.foregroundColor);
        menuList.setForeground(AppThemeColors.foregroundColor);
        titleLabel.setForeground(AppThemeColors.foregroundColor);
        musclesWorkedLabel.setForeground(AppThemeColors.foregroundColor);
        searchField.setForeground(AppThemeColors.foregroundColor);
    }

    // THEME HANDLER
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
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
                        Image scaledTest = exerciseImageIcon.getImage().getScaledInstance(centerPanel.getPreferredSize().width, centerPanel.getPreferredSize().height, Image.SCALE_DEFAULT);
                        ImageIcon scaledExerciseIcon = new ImageIcon(scaledTest);
                        imageLabel.setIcon(scaledExerciseIcon);
                        // DISPLAY DEFAULT IF IMAGE NOT FOUND
                    } catch (Exception ex) {
                        ImageIcon temp = new ImageIcon(ResourcePath.getResourcePath() + "bottom_right_bar.png");
                        Image scaledTest = temp.getImage().getScaledInstance(centerPanel.getPreferredSize().width, centerPanel.getPreferredSize().height, Image.SCALE_SMOOTH);
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
                        Image scaledTest = exerciseImageIcon.getImage().getScaledInstance(centerPanel.getPreferredSize().width, centerPanel.getPreferredSize().height, Image.SCALE_DEFAULT);
                        ImageIcon scaledExerciseIcon = new ImageIcon(scaledTest);
                        imageLabel.setIcon(scaledExerciseIcon);
                        // DISPLAY DEFAULT IF IMAGE NOT FOUND
                    } catch (Exception ex) {
                        ImageIcon temp = new ImageIcon(ResourcePath.getResourcePath() + "bottom_right_bar_light.png");
                        Image scaledTest = temp.getImage().getScaledInstance(centerPanel.getPreferredSize().width, centerPanel.getPreferredSize().height, Image.SCALE_SMOOTH);
                        ImageIcon scaledTestIcon = new ImageIcon(scaledTest);
                        imageLabel.setIcon(scaledTestIcon);
                    }
                }
            }

        } else {
            System.out.println("Error");
        }
    }

    // SCALING WAITING TO BE USED...
    public void reScaleBackground() {
        scaledContentBackground = homePanelBackground.getImage().getScaledInstance(getWidth(), getHeight(), Image.SCALE_SMOOTH);
        scaledContentBackgroundPanel = new ImageIcon(scaledContentBackground);
    }

    public void updateWindow() {

    }
}




