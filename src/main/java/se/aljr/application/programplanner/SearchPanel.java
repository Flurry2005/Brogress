package se.aljr.application.programplanner;

import se.aljr.application.CustomFont;
import se.aljr.application.ResourcePath;
import se.aljr.application.UserData;
import se.aljr.application.exercise.Excercise.Exercise;
import se.aljr.application.exercise.ExercisePanel;
import se.aljr.application.exercise.Muscle.Muscle;
import se.aljr.application.exercise.Muscle.MuscleList;
import se.aljr.application.exercise.Program.Exercises;
import se.aljr.application.homepage.MenuPanel;
import java.awt.event.*;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;
import java.util.concurrent.ExecutionException;

public class SearchPanel extends JPanel {
    static DefaultListModel<Exercise> exerciseModel;
    static DefaultListModel<Muscle> muscleModel = new DefaultListModel<>();
    static DefaultListModel<Exercise> favExerciseModel;
    static DefaultListModel<Exercise> myExerciseModel;
    static JList<Exercise> menuList;
    static JList<Muscle> muscleJList = new JList(muscleModel);
    static JButton sortMuscleButton = new JButton("Muscle");
    static JButton showFavorites = new JButton("Favorites");
    static JButton myExercises = new JButton("Created");
    static Font font = CustomFont.getFont();
    private ImageIcon addButton;
    private Image scaledAddButton;
    private ImageIcon scaledAddButtonIcon;

    public SearchPanel(int width, int height, JButton newExerciseButton) {
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.setOpaque(false);
        this.setPreferredSize(new Dimension(width/5, height));
        this.setMaximumSize(this.getPreferredSize());
        this.setMinimumSize(this.getPreferredSize());
        init(newExerciseButton);
    }

    public void init(JButton newExcerciseButton) {

        JPanel exercisesPanelTop = new JPanel();
        exercisesPanelTop.setLayout(new BoxLayout(exercisesPanelTop, BoxLayout.X_AXIS));
        exercisesPanelTop.setOpaque(true);
        exercisesPanelTop.setBackground(new Color(51, 51, 51));
        exercisesPanelTop.setPreferredSize(new Dimension(ProgramPanel.programPanelWidth / 5, ProgramPanel.programPanelHeight / 20));
        exercisesPanelTop.setMinimumSize(exercisesPanelTop.getPreferredSize());
        exercisesPanelTop.setMaximumSize(exercisesPanelTop.getPreferredSize());

        JTextField searchField = new JTextField();
        searchField.setFont(new Font("Arial", Font.ITALIC, (int) (ProgramPanel.programPanelHeight / 55.25)));
        searchField.setBorder(new LineBorder(new Color(80, 73, 69)));
        searchField.setBackground(new Color(21, 21, 21));
        searchField.setForeground(new Color(204, 204, 204));
        searchField.setPreferredSize(new Dimension((int) (getPreferredSize().width / 1.5), (int) (ProgramPanel.programPanelHeight / 22.1)));
        searchField.setMinimumSize(searchField.getPreferredSize());
        searchField.setMaximumSize(searchField.getPreferredSize());
        searchField.setAlignmentY(Component.CENTER_ALIGNMENT);
        searchField.setText("Search for exercise...");

        addButton = new ImageIcon(ResourcePath.getResourcePath() + "add_button.png");
        scaledAddButton = addButton.getImage().getScaledInstance((int) (addButton.getIconWidth() / 1.5), (int) (addButton.getIconHeight()), Image.SCALE_SMOOTH);
        scaledAddButtonIcon = new ImageIcon(scaledAddButton);

        newExcerciseButton.setContentAreaFilled(false);
        newExcerciseButton.setFocusPainted(false);
        newExcerciseButton.setBorderPainted(false);
        newExcerciseButton.setPreferredSize(new Dimension(scaledAddButtonIcon.getIconWidth(), scaledAddButtonIcon.getIconHeight()));
        newExcerciseButton.setMaximumSize(new Dimension(scaledAddButtonIcon.getIconWidth(), scaledAddButtonIcon.getIconHeight()));
        newExcerciseButton.setAlignmentY(Component.CENTER_ALIGNMENT);

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

        // Create layout for the panel
        JPanel searchContainer = new JPanel();
        searchContainer.setBorder(null);
        searchContainer.setLayout(new BorderLayout());
        searchContainer.setBackground(new Color(21, 21, 21));
        searchContainer.setPreferredSize(new Dimension(this.getPreferredSize().width, (int) (getPreferredSize().height / 12.75)));
        searchContainer.setMaximumSize(searchContainer.getPreferredSize());
        searchContainer.setMinimumSize(searchContainer.getPreferredSize());

        JScrollPane exerciseScrollPanel = new JScrollPane(menuList);
        exerciseScrollPanel.setBorder(new LineBorder(new Color(80, 73, 69)));
        exerciseScrollPanel.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
        exerciseScrollPanel.setPreferredSize(new Dimension(getPreferredSize().width, getPreferredSize().height * 8 / 10 - searchContainer.getPreferredSize().height));
        exerciseScrollPanel.setMaximumSize(exerciseScrollPanel.getPreferredSize());

        sortMuscleButton.setBackground(new Color(51, 51, 51));
        sortMuscleButton.setForeground(new Color(204, 204, 204));
        sortMuscleButton.setPreferredSize(new Dimension((int) (searchContainer.getPreferredSize().getWidth() / 4), getPreferredSize().height / 10));
        sortMuscleButton.setMaximumSize(new Dimension((int) (searchContainer.getPreferredSize().getWidth() / 4), getPreferredSize().height / 10));
        sortMuscleButton.setMinimumSize(new Dimension((int) (searchContainer.getPreferredSize().getWidth() / 4), getPreferredSize().height / 10));
        sortMuscleButton.setBorder(new LineBorder(new Color(80, 73, 69), 1, true));

        showFavorites.setForeground(new Color(204, 204, 204));
        showFavorites.setBackground(new Color(51, 51, 51));
        showFavorites.setPreferredSize(new Dimension((int) (searchContainer.getPreferredSize().width / 4), getPreferredSize().height / 12));
        showFavorites.setMaximumSize(new Dimension((int) (searchContainer.getPreferredSize().width / 4), getPreferredSize().height / 12));
        showFavorites.setMinimumSize(new Dimension((int) (searchContainer.getPreferredSize().width / 4), getPreferredSize().height / 12));
        showFavorites.setBorder(new LineBorder(new Color(80, 73, 69), 1, true));

        myExercises.setForeground(new Color(204, 204, 204));
        myExercises.setBackground(new Color(51, 51, 51));
        myExercises.setPreferredSize(new Dimension((int) (searchContainer.getPreferredSize().width / 4), getPreferredSize().height / 10));
        myExercises.setMaximumSize(new Dimension((int) (searchContainer.getPreferredSize().width / 4), getPreferredSize().height / 10));
        myExercises.setMinimumSize(new Dimension((int) (searchContainer.getPreferredSize().width / 4), getPreferredSize().height / 10));
        myExercises.setBorder(new LineBorder(new Color(80, 73, 69), 1, true));

        JButton createExerciseButton = new JButton("New exercise");
        createExerciseButton.setBackground(new Color(46, 148, 76));
        createExerciseButton.setForeground(new Color(204, 204, 204));
        createExerciseButton.setBorder(new LineBorder(new Color(80, 73, 69), 1, true));
        createExerciseButton.setPreferredSize(new Dimension(this.getPreferredSize().width, getPreferredSize().height / 24));
        createExerciseButton.setMaximumSize(new Dimension(this.getPreferredSize().width, getPreferredSize().height / 24));
        createExerciseButton.setMinimumSize(new Dimension(this.getPreferredSize().width, getPreferredSize().height / 24));

        MouseEvent pressEvent = new MouseEvent(MenuPanel.exercisesButton, MouseEvent.MOUSE_PRESSED, System.currentTimeMillis(), 0, 10, 10, 1, false);

        JPanel scrollSortWrapper = new JPanel();
        scrollSortWrapper.setLayout(new BorderLayout());
        scrollSortWrapper.setPreferredSize(exerciseScrollPanel.getPreferredSize());
        scrollSortWrapper.setMaximumSize(exerciseScrollPanel.getPreferredSize());
        scrollSortWrapper.setMinimumSize(exerciseScrollPanel.getPreferredSize());

        //------------------ADD COMPONENTS----------------------

        exercisesPanelTop.add(searchField);
        exercisesPanelTop.add(Box.createHorizontalGlue());
        exercisesPanelTop.add(newExcerciseButton);

        searchContainer.add(sortMuscleButton, BorderLayout.WEST);
        searchContainer.add(showFavorites, BorderLayout.CENTER);
        searchContainer.add(myExercises, BorderLayout.EAST);
        searchContainer.add(createExerciseButton, BorderLayout.SOUTH);

        scrollSortWrapper.add(muscleScroll, BorderLayout.NORTH);
        scrollSortWrapper.add(exerciseScrollPanel, BorderLayout.CENTER);

        this.add(Box.createVerticalGlue());
        this.add(exercisesPanelTop);
        this.add(Box.createVerticalGlue());
        this.add(searchContainer);
        this.add(scrollSortWrapper);
        this.add(Box.createVerticalGlue());


        // ---------------------METHODS AND LISTENERS-----------------------

        // SWITCH TO CREATE EXERCISE SECTION
        createExerciseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                MenuPanel.exercisesButton.getMouseListeners()[0].mousePressed(pressEvent);
                ExercisePanel.createExerciseButton.doClick();
            }
        });
        // DISPLAYS MUSCLE LIST FOR FILTERING SEARCH BY MUSCLE
        sortMuscleButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!muscleScroll.isVisible()) {
                    muscleJList.clearSelection();
                    menuList.setModel(new DefaultListModel<>());
                    sortMuscleButton.setBackground(new Color(49, 84, 122));
                    showFavorites.setBackground(new Color(51, 51, 51));
                    myExercises.setBackground(new Color(51, 51, 51));
                    muscleScroll.setVisible(true);
                } else {
                    muscleScroll.setVisible(false);
                    sortMuscleButton.setBackground(new Color(51, 51, 51));
                }
                repaint();
                revalidate();

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
                if (!menuList.getModel().equals(favExerciseModel)) {
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
                } else {
                    showFavorites.setBackground(new Color(51, 51, 51));
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
                    sortMuscleButton.setBackground(new Color(51, 51, 51));
                    showFavorites.setBackground(new Color(51, 51, 51));
                    muscleScroll.setVisible(false);
                    updateMenuList("myExerciseModel");
                    menuList.setModel(myExerciseModel);
                } else {
                    myExercises.setBackground(new Color(51, 51, 51));
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

    public static Exercise getSelectedExercise() {
        return menuList.getSelectedValue();
    }
}
