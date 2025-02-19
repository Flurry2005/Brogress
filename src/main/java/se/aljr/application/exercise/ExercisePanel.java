package se.aljr.application.exercise;

import com.google.cloud.storage.Acl;
import se.aljr.application.ResourcePath;
import se.aljr.application.UserData;
import se.aljr.application.exercise.Excercise.*;
import se.aljr.application.exercise.Muscle.Muscle;
import se.aljr.application.exercise.Muscle.MuscleList;
import se.aljr.application.exercise.Program.Exercises;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.sql.SQLOutput;
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
    private static DefaultListModel<Exercise> myExerciseModel;
    private static DefaultListModel<Exercise> favExerciseModel;
    private static boolean editButtonEnabled = false;
    private static boolean createdTabEnabled = false;


    Font font;

    protected ImageIcon homePanelBackground;
    protected ImageIcon scaledContentBackgroundPanel;
    Image scaledContentBackground;

    public ExercisePanel(int width, int height) throws InterruptedException {
        resourcePath = getClass().getClassLoader().getResource("resource.path").getPath().replace("resource.path", "");
        homePanelBackground = new ImageIcon(resourcePath + "bottom_right_bar.png");
        scaledContentBackground = homePanelBackground.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);
        scaledContentBackgroundPanel = new ImageIcon(scaledContentBackground);

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

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        mainPanel.setBackground(new Color(51, 51, 51));
        mainPanel.setPreferredSize(this.getPreferredSize());
        mainPanel.setBorder(new EmptyBorder(height / 45, width / 45, height / 45, width / 45));

        Font emojiFont = new Font("Segoe UI Emoji", Font.PLAIN, 35);

        JButton editButton = new JButton("\uD83D\uDCDD");
        editButton.setFocusPainted(false);
        editButton.setContentAreaFilled(false);
        editButton.setBackground(new Color(51, 51, 51));
        editButton.setForeground(new Color(21, 21, 21));
        editButton.setFont(emojiFont);
        editButton.setBorder(null);
        editButton.setPreferredSize(new Dimension(100, 60));
        editButton.setVisible(false);

        JButton favouriteButton = new JButton("\uD83D\uDCAA");
        favouriteButton.setFocusPainted(false);
        favouriteButton.setContentAreaFilled(false);
        favouriteButton.setBackground(new Color(51, 51, 51));
        favouriteButton.setForeground(new Color(21, 21, 21));
        favouriteButton.setFont(emojiFont);
        favouriteButton.setBorder(null);
        favouriteButton.setPreferredSize(new Dimension(100, 60));

        titleLabel = new JTextArea();
        titleLabel.setFont(font.deriveFont(65.f));
        titleLabel.setForeground(new Color(204, 204, 204));
        titleLabel.setOpaque(false);
        titleLabel.setEditable(false);

        musclesWorkedLabel = new JLabel();
        musclesWorkedLabel.setFont(font.deriveFont(24f));
        musclesWorkedLabel.setForeground(new Color(204, 204, 204));
        musclesWorkedLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JTextField searchField = new JTextField("Search for exercise...");
        searchField.setFont(new Font("Arial", Font.ITALIC, 12));
        searchField.setBorder(new LineBorder(new Color(80, 73, 69)));
        searchField.setBackground(new Color(21, 21, 21));
        searchField.setForeground(new Color(204, 204, 204));
        searchField.setPreferredSize(new Dimension(130, 30));

        favExerciseModel = new DefaultListModel<>();
        myExerciseModel = new DefaultListModel<>();

        DefaultListModel<Muscle> muscleModel = new DefaultListModel<>();
        MuscleList muscleList = new MuscleList();
        for (Muscle muscle : muscleList) {
            muscleModel.addElement(muscle);
        }
        // Display muscles when using filter option
        JList<Muscle> muscleJList = new JList(muscleModel);
        muscleJList.setBackground(new Color(51, 51, 51));
        muscleJList.setForeground(new Color(204, 204, 204));

        JScrollPane muscleScroll = new JScrollPane(muscleJList);
        muscleScroll.setBorder(null);
        muscleScroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
        muscleScroll.setPreferredSize(new Dimension(getPreferredSize().width, getPreferredSize().height / 4));
        muscleScroll.setMaximumSize(new Dimension(getPreferredSize().width, getPreferredSize().height / 4));
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

        JButton sortMuscleButton = new JButton();
        sortMuscleButton.setText("Muscle");
        sortMuscleButton.setBackground(new Color(51, 51, 51));
        sortMuscleButton.setForeground(new Color(204, 204, 204));
        sortMuscleButton.setPreferredSize(new Dimension((int) (searchContainer.getPreferredSize().getWidth() / 4), getPreferredSize().height / 2));
        sortMuscleButton.setBorder(new LineBorder(new Color(80, 73, 69), 1, true));

        JButton showFavorites = new JButton("Favorites");
        showFavorites.setForeground(new Color(204, 204, 204));
        showFavorites.setBackground(new Color(51, 51, 51));
        showFavorites.setPreferredSize(new Dimension((int) (searchContainer.getPreferredSize().width / 4), getPreferredSize().height / 2));
        showFavorites.setBorder(new LineBorder(new Color(80, 73, 69), 1, true));

        JButton myExercises = new JButton("Created");
        myExercises.setForeground(new Color(204, 204, 204));
        myExercises.setBackground(new Color(51, 51, 51));
        myExercises.setPreferredSize(new Dimension((int) (searchContainer.getPreferredSize().width / 4), getPreferredSize().height / 2));
        myExercises.setBorder(new LineBorder(new Color(80, 73, 69), 1, true));

        JButton createExerciseButton = new JButton("Add exercise");
        createExerciseButton.setBackground(new Color(46, 148, 76));
        createExerciseButton.setForeground(new Color(204, 204, 204));
        createExerciseButton.setBorder(new LineBorder(new Color(80, 73, 69), 1, true));
        createExerciseButton.setPreferredSize(new Dimension(westPanel.getPreferredSize().width, getPreferredSize().height / 24));

        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));
        centerPanel.setBackground(new Color(51, 51, 51));
        centerPanel.setPreferredSize(new Dimension((int) (mainPanel.getPreferredSize().width / 2.5365), (int) (mainPanel.getPreferredSize().height / 1.1714)));
        centerPanel.setBorder(new EmptyBorder(0, centerPanel.getPreferredSize().width / 20, 0, centerPanel.getPreferredSize().width / 20));

        //Label displaying the exercise image
        JLabel imageLabel = new JLabel();
        imageLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JPanel eastPanel = new JPanel();
        eastPanel.setLayout(new BoxLayout(eastPanel, BoxLayout.Y_AXIS));
        eastPanel.setBackground(new Color(51, 51, 51));
        eastPanel.setPreferredSize(new Dimension(mainPanel.getPreferredSize().width / 4, mainPanel.getPreferredSize().height));
        eastPanel.setMinimumSize(new Dimension(mainPanel.getPreferredSize().width / 4, mainPanel.getPreferredSize().height));
        eastPanel.setMaximumSize(new Dimension(mainPanel.getPreferredSize().width / 4, mainPanel.getPreferredSize().height));

        JPanel aboutContainer = new JPanel();
        aboutContainer.setLayout(new BorderLayout());
        aboutContainer.setForeground(new Color(204, 204, 204));
        aboutContainer.setBackground(new Color(51, 51, 51));
        aboutContainer.setPreferredSize(new Dimension(eastPanel.getPreferredSize().width, eastPanel.getPreferredSize().height / 2));
        aboutContainer.setMinimumSize(new Dimension(eastPanel.getPreferredSize().width, eastPanel.getPreferredSize().height / 2));
        aboutContainer.setMaximumSize(new Dimension(eastPanel.getPreferredSize().width, eastPanel.getPreferredSize().height / 2));
        aboutContainer.setBorder(new EmptyBorder(0, 0, 10, 0));

        // Displays "About"
        JLabel aboutLabel = new JLabel();
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
        JLabel formLabel = new JLabel();
        formLabel.setText("How to");
        formLabel.setForeground(new Color(204, 204, 204));
        formLabel.setBackground(new Color(51, 51, 51));
        formLabel.setOpaque(true);
        formLabel.setHorizontalAlignment(JLabel.CENTER);
        formLabel.setVerticalAlignment(JLabel.BOTTOM);
        formLabel.setVerticalTextPosition(JLabel.BOTTOM);
        formLabel.setFont(font.deriveFont(24f));
        formLabel.setBorder(new LineBorder(new Color(80, 73, 69), 1, true));

        JTextArea aboutText = new JTextArea();
        aboutText.setBackground(new Color(21, 21, 21));
        aboutText.setForeground(new Color(204, 204, 204));
        aboutText.setFont(new Font("Arial", Font.TRUETYPE_FONT, 15));
        aboutText.setEditable(false);
        aboutText.setBorder(new LineBorder(new Color(80, 73, 69), 1, true));
        aboutText.setLineWrap(true);

        JTextPane formText = new JTextPane();
        formText.setBackground(new Color(21, 21, 21));
        formText.setForeground(new Color(204, 204, 204));
        formText.setEditable(false);
        formText.setFont(new Font("Arial", Font.TRUETYPE_FONT, 15));
        formText.setFocusable(false);
        formText.setBorder(new LineBorder(new Color(80, 73, 69), 1, true));

        JPanel northPanel = new JPanel();
        northPanel.setBackground(new Color(51, 51, 51));
        northPanel.setLayout(new BorderLayout());
        northPanel.setPreferredSize(new Dimension(mainPanel.getPreferredSize().width, mainPanel.getPreferredSize().height / 8));

        JPanel northEastPanel = new JPanel();
        northEastPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        northEastPanel.setOpaque(false);

        JPanel northWestPanel = new JPanel();
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
        northEastPanel.add(favouriteButton);


        northWestPanel.add(titleLabel);
        northWestPanel.add(musclesWorkedLabel);

        northPanel.add(northWestPanel, BorderLayout.WEST);
        northPanel.add(northEastPanel, BorderLayout.EAST);

        //CENTER
        CreateExerciseModule createExerciseModule = new CreateExerciseModule(centerPanel);
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
            Exercise defaultExercise = exerciseModel.getElementAt(0);
            selectedExercise = defaultExercise;
            menuList.setSelectedValue(defaultExercise, true);
            titleLabel.setText(defaultExercise.getName());
            musclesWorkedLabel.setText(defaultExercise.getMusclesUsed());
            aboutText.setText(defaultExercise.getInfo());
            formText.setText(defaultExercise.getForm());
        }

        editButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!editButtonEnabled && menuList.getSelectedIndex()!=-1) {
                    editButton.setText("✅");
                    editButton.setForeground(new Color(46, 148, 76));
                    titleLabel.setEditable(true);
                    titleLabel.setOpaque(true);
                    titleLabel.setBackground(new Color(49, 84, 122));
                    aboutText.setEditable(true);
                    aboutText.setOpaque(true);
                    aboutText.setBackground(new Color(49, 84, 122));
                    editButtonEnabled = true;

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
                } else if(menuList.getSelectedIndex()!=-1){
                    int selectedIndex = menuList.getSelectedIndex();
                    String aboutTextString = aboutText.getText();
                    UserData.getCreatedExercises().get(menuList.getSelectedIndex()).setName(titleLabel.getText());
                    // selectedExercise.setName(titleLabel.getText());
                    editButton.setForeground(new Color(204, 204, 204));
                    editButton.setText("\uD83D\uDCDD");
                    titleLabel.setOpaque(false);
                    titleLabel.setEditable(false);
                    aboutText.setBackground(new Color(21, 21, 21));
                    aboutText.setEditable(false);
                    menuList.setSelectedIndex(selectedIndex);
                    UserData.getCreatedExercises().get(menuList.getSelectedIndex()).setInfo(aboutTextString);
                    ((AbstractDocument) titleLabel.getDocument()).setDocumentFilter(null);
                    editButtonEnabled = false;
                    updateMenuList("myExerciseModel");
                    menuList.setSelectedIndex(selectedIndex);
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
        favouriteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if (selectedExercise != null) {
                    if (!UserData.getFavoriteExercises().contains(selectedExercise)) {
                        UserData.setFavoriteExercises(selectedExercise);
                        String addedStatus = (selectedExercise.getName() + " has been added to favorites!");
                        statusPanel.setBackground(new Color(46, 148, 76));
                        activateStatus(new Color(46, 148, 76), addedStatus);
                        status.setLength(0);
                        favouriteButton.setForeground(new Color(196, 196, 49));
                        updateMenuList("favExerciseModel");
                    } else {
                        UserData.removeFavoriteExercises(selectedExercise);
                        String removedStatus = (selectedExercise.getName() + " has been removed from favorites!");
                        statusPanel.setBackground(new Color(204, 20, 20));
                        activateStatus(new Color(204, 20, 20), removedStatus);
                        status.setLength(0);
                        favouriteButton.setForeground(new Color(22, 22, 22));
                        updateMenuList("favExerciseModel");
                    }
                }
            }
        });
        // VISUAL INTERACTION WITH "ADD TO FAVOURITE"-BUTTON
        favouriteButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                super.mouseEntered(e);
                favouriteButton.setForeground(new Color(196, 196, 49));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                super.mouseExited(e);
                if (UserData.getFavoriteExercises().contains(selectedExercise)) {
                    favouriteButton.setForeground(new Color(196, 196, 49));
                } else {
                    favouriteButton.setForeground(new Color(22, 22, 22));
                }
            }
        });
        // DISPLAYS MUSCLE LIST FOR FILTERING SEARCH BY MUSCLE
        sortMuscleButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (editButtonEnabled) {
                    editButton.doClick();
                }
                createdTabEnabled=false;
                editButton.setVisible(false);

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
                if (editButtonEnabled) {
                    editButton.doClick();
                }
                createdTabEnabled=false;
                editButton.setVisible(false);

                muscleScroll.setVisible(false);
                showFavorites.setBackground(new Color(49, 84, 122));
                sortMuscleButton.setBackground(new Color(51, 51, 51));
                myExercises.setBackground(new Color(51, 51, 51));
                favExerciseModel = new DefaultListModel<>();
                menuList.setModel(favExerciseModel);
                for (Exercise exercise : UserData.getFavoriteExercises()) {
                    favExerciseModel.addElement(exercise);
                }
                repaint();
                revalidate();
            }
        });
        // EVENT TRIGGERS FOR "MY EXERCISES"-BUTTON
        myExercises.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                createdTabEnabled=true;
                if(UserData.getCreatedExercises().contains(selectedExercise)){
                    editButton.setVisible(true);
                }

                myExercises.setBackground(new Color(49, 84, 122));
                myExerciseModel = new DefaultListModel<>();
                for (Exercise exercise : UserData.getCreatedExercises()) {
                    myExerciseModel.addElement(exercise);
                }
                menuList.setModel(myExerciseModel);
                sortMuscleButton.setBackground(new Color(51, 51, 51));
                showFavorites.setBackground(new Color(51, 51, 51));
                muscleScroll.setVisible(false);
                repaint();
                revalidate();
            }
        });
        // VISUAL INTERACTION WITH SEARCH FIELD
        searchField.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
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
        // SET ALL INFORMATION BASED ON SELECTED EXERCISE
        menuList.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (menuList.getSelectedIndex() != -1) {
                    selectedExercise = menuList.getSelectedValue();
                    if (selectedExercise != null) {
                        createExerciseModule.setVisible(false);
                        if (selectedExercise.getInfo().isEmpty() || selectedExercise.getInfo() == null) {
                            aboutText.setText("No information added...");
                        } else {
                            aboutText.setText(selectedExercise.getInfo());
                        }
                        imageLabel.setVisible(true);
                        try {
                            ImageIcon test = selectedExercise.getImageIcon();
                            Image scaledTest = test.getImage().getScaledInstance(centerPanel.getPreferredSize().width, centerPanel.getPreferredSize().height, Image.SCALE_DEFAULT);
                            ImageIcon scaledTestIcon = new ImageIcon(scaledTest);
                            formInfoContainer.setVisible(true);
                            imageLabel.setIcon(scaledTestIcon);

                        } catch (Exception f) {
                            ImageIcon test = new ImageIcon(ResourcePath.getResourcePath() + "bottom_right_bar.png");
                            Image scaledTest = test.getImage().getScaledInstance(centerPanel.getPreferredSize().width, centerPanel.getPreferredSize().height, Image.SCALE_SMOOTH);
                            ImageIcon scaledTestIcon = new ImageIcon(scaledTest);
                            formInfoContainer.setVisible(false);
                            imageLabel.setIcon(scaledTestIcon);
                        }
                        formText.setText(selectedExercise.getInfo());
                        formText.setText(selectedExercise.getForm());
                        titleLabel.setText(selectedExercise.getName());

                        if (selectedExercise.getMusclesUsed().toString().length() > 45) {
                            String muscles = selectedExercise.getMusclesUsed().toString();
                            muscles = muscles.substring(0, 45);
                            musclesWorkedLabel.setText(muscles + "...");
                        } else {
                            musclesWorkedLabel.setText(selectedExercise.getMusclesUsed());
                        }
                        if (UserData.getFavoriteExercises().contains(selectedExercise)) {
                            favouriteButton.setForeground(new Color(196, 196, 49));
                        } else {
                            favouriteButton.setForeground(new Color(22, 22, 22));
                        }
                        if (UserData.getCreatedExercises().contains(selectedExercise)&&createdTabEnabled) {
                            System.out.println("Ja den finns ");
                            editButton.setVisible(true);
                        } else {
                            editButton.setVisible(false);
                            titleLabel.setEditable(false);
                            titleLabel.setOpaque(false);
                            aboutText.setEditable(false);
                            aboutText.setBackground(new Color(21, 21, 21));
                            editButton.setText("\uD83D\uDCDD");
                            editButton.setForeground(new Color(204, 204, 204));
                        }
                        repaint();
                        revalidate();
                    }
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
        }
        menuList.revalidate();
        menuList.repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        // Draw the image to fill the entire panel
        if (homePanelBackground != null) {
            g.drawImage(scaledContentBackgroundPanel.getImage(), 0, 0, getWidth(), getHeight(), this);
        } else {
            System.out.println("Error");
        }
    }

    public void reScaleBackground() {
        scaledContentBackground = homePanelBackground.getImage().getScaledInstance(getWidth(), getHeight(), Image.SCALE_SMOOTH);
        scaledContentBackgroundPanel = new ImageIcon(scaledContentBackground);
    }
}




