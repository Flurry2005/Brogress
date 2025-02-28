package se.aljr.application.programplanner;

import se.aljr.application.AppThemeColors;
import se.aljr.application.CustomFont;
import se.aljr.application.ResourcePath;
import se.aljr.application.UserData;
import se.aljr.application.exercise.Excercise.Exercise;
import se.aljr.application.exercise.Program.Exercises;
import se.aljr.application.loginpage.FirebaseManager;
import se.aljr.application.settings.SettingsPanel;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.util.*;
import java.util.List;
import javax.swing.Timer;

public class ProgramPanel extends JPanel {
    public static int programPanelHeight;
    public static int programPanelWidth;
    private Workout workoutContainer;
    public static int setPanelHeight;
    private JLabel headerTitle;
    private JTextField workoutTitle;

    private static boolean emptyLog;
    private ImageIcon emptyBackground;
    private Image scaledEmptyBackground;
    private ImageIcon scaledEmptyBackgroundIcon;

    private ImageIcon lightEmptyBackground;
    private Image scaledLightEmptyBackground;
    private ImageIcon scaledLightEmptyBackgroundIcon;

    private ImageIcon addButton;
    private Image scaledAddButton;
    private ImageIcon scaledAddButtonIcon;

    private ImageIcon saveButton;
    private Image scaledsaveButton;
    private ImageIcon scaledsaveButtonIcon;

    private ImageIcon removeWorkoutButtonImage;
    private Image scaledRemoveWorkoutButtonImage;
    private ImageIcon scaledRemoveWorkoutIcon;

    private ImageIcon newWorkoutButtonImage;
    private Image scaledNewWorkoutButtonImage;
    private ImageIcon scaledNewWorkoutIcon;

    private JButton newWorkoutButton;

    private ImageIcon removeExerciseButtonImage;
    private Image scaledRemoveExerciseButtonImage;
    public static ImageIcon scaledRemoveExerciseIcon;

    private ImageIcon newSetButtonImage;
    private Image scaledNewSetButtonImage;
    public static ImageIcon scaledNewSetIcon;

    private ImageIcon removeSetButtonImage;
    private Image scaledRemoveSetButtonImage;
    public static ImageIcon scaledRemoveSetIcon;

    private ImageIcon moveSetUpButtonImage;
    private Image scaledMoveSetUpButtonImage;
    public static ImageIcon scaledMoveSetUpIcon;

    private ImageIcon moveSetDownButtonImage;
    private Image scaledMoveSetDownButtonImage;
    public static ImageIcon scaledMoveSetDownIcon;

    private int statusDelayCounter;
    private final StringBuilder status = new StringBuilder();

    JPanel mainPanel = new JPanel();
    JLabel statusText = new JLabel(status.toString());
    static JPanel workoutPanel = new JPanel();
    static JPanel exercisesPanel = new JPanel();
    JTextField searchExercise = new JTextField();
    JScrollPane workoutScrollPane = new JScrollPane();
    JScrollPane savedWorkoutsScrollPane = new JScrollPane();
    DefaultListModel<Exercise> exerciseModel = new DefaultListModel<>();
    JList<Exercise> searchExerciseResult = new JList<>(exerciseModel);
    JScrollPane exercisesScrollPane = new JScrollPane(searchExerciseResult);
    JPanel addExerciseAndSetPanel = new JPanel();
    JPanel workoutPanelTop = new JPanel();
    JPanel savedWorkoutsPanel = new JPanel();
    DefaultListModel<String> workoutTitleDefaultListModel = new DefaultListModel<>();
    JList<String> savedWorkoutsList = new JList<>(workoutTitleDefaultListModel);
    WorkoutsList workoutsList;

    public static Color settingsPanelColor;
    public static Color workoutPanelTextColor;


    public static ProgramPanel instance;


    public ProgramPanel(int width, int height) {
        emptyBackground = new ImageIcon(ResourcePath.getResourcePath() + "emptyBackground.png");
        scaledEmptyBackground = emptyBackground.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);
        scaledEmptyBackgroundIcon = new ImageIcon(scaledEmptyBackground);

        lightEmptyBackground = new ImageIcon(ResourcePath.getResourcePath() + "lightEmptyBackground.png");
        scaledLightEmptyBackground = lightEmptyBackground.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);
        scaledLightEmptyBackgroundIcon = new ImageIcon(scaledLightEmptyBackground);
                     
        addButton = new ImageIcon(ResourcePath.getResourcePath() + "add_button.png");
        scaledAddButton = addButton.getImage().getScaledInstance((int) (addButton.getIconWidth() / 1.5), (int) (addButton.getIconHeight()), Image.SCALE_SMOOTH);
        scaledAddButtonIcon = new ImageIcon(scaledAddButton);

        saveButton = new ImageIcon(ResourcePath.getResourcePath() + "save_workout_button.png");
        scaledsaveButton = saveButton.getImage().getScaledInstance((int)(width/7.59285714), (int)(height/22.862069), Image.SCALE_SMOOTH);
        scaledsaveButtonIcon = new ImageIcon(scaledsaveButton);

        removeWorkoutButtonImage = new ImageIcon(ResourcePath.getResourcePath() + "remove_workout_button.png");
        scaledRemoveWorkoutButtonImage = removeWorkoutButtonImage.getImage().getScaledInstance((int)(width/28.7297297), (int)(height/22.862069), Image.SCALE_SMOOTH);
        scaledRemoveWorkoutIcon = new ImageIcon(scaledRemoveWorkoutButtonImage);

        newWorkoutButtonImage = new ImageIcon(ResourcePath.getResourcePath() + "new_workout_button.png");
        scaledNewWorkoutButtonImage = newWorkoutButtonImage.getImage().getScaledInstance((int)(width/14.1733333), (int)(height/22.862069), Image.SCALE_SMOOTH);
        scaledNewWorkoutIcon = new ImageIcon(scaledNewWorkoutButtonImage);

        removeExerciseButtonImage = new ImageIcon(ResourcePath.getResourcePath() + "remove_exercise_button.png");
        scaledRemoveExerciseButtonImage = removeExerciseButtonImage.getImage().getScaledInstance((int)(width/14.971831), (int)(height/19.5), Image.SCALE_SMOOTH);
        scaledRemoveExerciseIcon = new ImageIcon(scaledRemoveExerciseButtonImage);

        newSetButtonImage = new ImageIcon(ResourcePath.getResourcePath() + "new_set_button.png");
        scaledNewSetButtonImage = newSetButtonImage.getImage().getScaledInstance((int)(width/35.4333333), (int)(height/19.5), Image.SCALE_SMOOTH);
        scaledNewSetIcon = new ImageIcon(scaledNewSetButtonImage);

        removeSetButtonImage = new ImageIcon(ResourcePath.getResourcePath() + "remove_set_button.png");
        scaledRemoveSetButtonImage = removeSetButtonImage.getImage().getScaledInstance((int)(width/46.2173913043), (int)(height/26.52), Image.SCALE_SMOOTH);
        scaledRemoveSetIcon = new ImageIcon(scaledRemoveSetButtonImage);

        moveSetUpButtonImage = new ImageIcon(ResourcePath.getResourcePath() + "move_set_up.png");
        scaledMoveSetUpButtonImage = moveSetUpButtonImage.getImage().getScaledInstance((int)(width/88.5833333), (int)(height/30.1363636), Image.SCALE_SMOOTH);
        scaledMoveSetUpIcon = new ImageIcon(scaledMoveSetUpButtonImage);

        moveSetDownButtonImage = new ImageIcon(ResourcePath.getResourcePath() + "move_set_down.png");
        scaledMoveSetDownButtonImage = moveSetDownButtonImage.getImage().getScaledInstance((int)(width/88.5833333), (int)(height/30.1363636), Image.SCALE_SMOOTH);
        scaledMoveSetDownIcon = new ImageIcon(scaledMoveSetDownButtonImage);

        instance = this;

        this.setSize(width, height);
        this.setOpaque(false);
        this.setBackground(new Color(31, 31, 31));
        this.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));
        init();
    }

    private void init() {

        setPanelHeight = getHeight() / 19;

        programPanelHeight = getHeight();
        programPanelWidth = getWidth();

        //Wrapper
        JLayeredPane wrapper = new JLayeredPane();
        wrapper.setPreferredSize(new Dimension(programPanelWidth, programPanelHeight));


        // Main panel holding everything

        mainPanel.setOpaque(true);
        mainPanel.setBackground(new Color(51, 51, 51));
        mainPanel.setBounds(0,0,programPanelWidth,programPanelHeight);
        //mainPanel.setBackground(Color.GREEN);
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.X_AXIS));

        //Scrollable window

        workoutScrollPane.setPreferredSize(new Dimension(getWidth() / 2, getHeight() * 8 / 10));
        workoutScrollPane.setMinimumSize(new Dimension(getWidth() / 2, getHeight() * 8 / 10));
        workoutScrollPane.setMaximumSize(new Dimension(getWidth() / 2, getHeight() * 8 / 10));
        workoutScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        workoutScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        workoutScrollPane.getVerticalScrollBar().setPreferredSize(new Dimension(0, 0));
        workoutScrollPane.getVerticalScrollBar().setUnitIncrement(6);
        workoutScrollPane.getViewport().setBackground(new Color(22, 22, 22));
        workoutScrollPane.setBorder(new LineBorder(new Color(80, 73, 69), 1));

        //Statuspanel
        JPanel statusPanel = new JPanel();
        statusPanel.setBounds(0,programPanelHeight-50,this.getWidth(),50);
        statusPanel.setPreferredSize(new Dimension(this.getWidth(), 50));
        statusPanel.setVisible(false);

        statusText.setForeground(new Color(204, 204, 204));
        statusPanel.add(statusText);
        Timer shrinkStatusTimer = new Timer(30, e -> {
            if (statusPanel.getHeight() == 0) {
                statusPanel.setVisible(false);
                ((Timer) e.getSource()).stop();
                return;
            }
            if (statusDelayCounter > 0) {
                statusDelayCounter -= 1;
            } else {
                statusPanel.setBounds(0,statusPanel.getBounds().y + 1,mainPanel.getWidth(),statusPanel.getHeight());
                statusPanel.repaint();
                statusPanel.revalidate();
            }
        });

        //Label to display text when log is empty
        JLabel isEmpty = new JLabel();

        //Panel containing log and workout data
        workoutsList = FirebaseManager.readDBworkout(this);
        if (workoutsList.isEmpty()) {
            workoutsList.add(new Workout());

        }
        workoutContainer = workoutsList.getFirst();
        //workoutContainer = FirebaseManager.readDBworkout(this);
        if (workoutContainer.getComponentCount() == 0) {
            emptyLog = true;
            isEmpty.setFont(new Font("Arial", Font.ITALIC, 20));
            isEmpty.setText("No exercises added yet.");
            workoutContainer.add(isEmpty);
        } else {
            emptyLog = false;
        }
        workoutContainer.setLayout(new BoxLayout(workoutContainer, BoxLayout.Y_AXIS));
        workoutContainer.setOpaque(false);
        workoutContainer.setForeground(new Color(204, 204, 204));
        workoutContainer.setBackground(new Color(22, 22, 22));
        workoutScrollPane.setViewportView(workoutContainer); //workoutScrollPane will show the content of workoutContainer


        savedWorkoutsPanel.setLayout(new BoxLayout(savedWorkoutsPanel, BoxLayout.Y_AXIS));
        savedWorkoutsPanel.setPreferredSize(new Dimension(getWidth() / 5, getHeight()));
        savedWorkoutsPanel.setMinimumSize(new Dimension(getWidth() / 5, getHeight()));
        savedWorkoutsPanel.setMaximumSize(new Dimension(getWidth() / 5, getHeight()));
        savedWorkoutsPanel.setOpaque(true);
        savedWorkoutsPanel.setBackground(new Color(51, 51, 51));

        JPanel savedWorkoutsPanelTop = new JPanel();
        savedWorkoutsPanelTop.setLayout(new BoxLayout(savedWorkoutsPanelTop, BoxLayout.X_AXIS));
        savedWorkoutsPanelTop.setOpaque(true);
        savedWorkoutsPanelTop.setPreferredSize(new Dimension(getWidth() / 5, getHeight() / 20));
        savedWorkoutsPanelTop.setMinimumSize(new Dimension(getWidth() / 5, getHeight() / 20));
        savedWorkoutsPanelTop.setMaximumSize(new Dimension(getWidth() / 5, getHeight() / 20));
        savedWorkoutsPanelTop.setBackground(new Color(51, 51, 51));
        savedWorkoutsPanelTop.setBorder(new LineBorder(new Color(80, 73, 69)));

        JLabel savedWorkoutsLabel = new JLabel("My workouts");
        savedWorkoutsLabel.setMaximumSize(new Dimension((int)(savedWorkoutsPanelTop.getPreferredSize().width/4),(int)(savedWorkoutsPanelTop.getPreferredSize().height)));
        savedWorkoutsLabel.setFont(CustomFont.getFont().deriveFont(24f));
        savedWorkoutsLabel.setForeground(Color.white);
        savedWorkoutsLabel.setOpaque(false);

        // Select saved workouts
        DefaultListModel<Workout> workoutDefaultListModel = new DefaultListModel<>();
        for (Workout workout : workoutsList) {
            workoutDefaultListModel.addElement(workout);
            workoutTitleDefaultListModel.addElement(workout.getWorkoutData().getTitle());
        }

        savedWorkoutsList.setForeground(Color.WHITE);
        savedWorkoutsList.setFixedCellHeight((int)(getHeight()/22.5));
        savedWorkoutsList.setBackground(new Color(22, 22, 22));
        savedWorkoutsList.setSelectedIndex(0);
        savedWorkoutsList.setFont(new Font("Arial", Font.BOLD, (int)(getHeight()/55.25)));
        savedWorkoutsList.addListSelectionListener(_ -> {

            if (savedWorkoutsList.getSelectedIndex() != -1) {
                Workout target = workoutDefaultListModel.getElementAt(savedWorkoutsList.getSelectedIndex());
                workoutContainer = target;

                workoutTitle.setText(target.getWorkoutData().getTitle());

                if (workoutContainer.getComponentCount() == 0) {
                    emptyLog = true;
                    isEmpty.setFont(new Font("Arial", Font.ITALIC, (int)(getHeight()/33.15)));
                    isEmpty.setText("No exercises added yet.");
                    workoutContainer.add(isEmpty);
                }
                workoutContainer.setLayout(new BoxLayout(workoutContainer, BoxLayout.Y_AXIS));
                workoutContainer.setOpaque(false);
                workoutContainer.setForeground(new Color(204, 204, 204));
                workoutContainer.setBackground(new Color(22, 22, 22));
                workoutContainer.setPreferredSize(new Dimension(target.getWidth(), target.getHeight()));
                workoutContainer.setMinimumSize(target.getPreferredSize());
                workoutContainer.setMaximumSize(target.getPreferredSize());

                workoutScrollPane.setViewportView(workoutContainer);
            }

        });

        newWorkoutButton = new JButton(scaledNewWorkoutIcon);
        newWorkoutButton.setContentAreaFilled(false);
        newWorkoutButton.setPreferredSize(new Dimension(scaledNewWorkoutIcon.getIconWidth(),scaledNewWorkoutIcon.getIconHeight()));
        newWorkoutButton.setMaximumSize(newWorkoutButton.getPreferredSize());
        newWorkoutButton.setBorder(null);
        newWorkoutButton.setFocusable(false);
        newWorkoutButton.setBorderPainted(false);
        newWorkoutButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                Workout newWorkout = new Workout();
                workoutsList.add(newWorkout);
                workoutDefaultListModel.addElement(newWorkout);
                workoutTitleDefaultListModel.addElement(newWorkout.getWorkoutData().getTitle());

            }
        });

        JButton deleteWorkout = new JButton();
        deleteWorkout.setBorder(null);
        deleteWorkout.setContentAreaFilled(false);
        deleteWorkout.setIcon(scaledRemoveWorkoutIcon);
        deleteWorkout.setFont(new Font("Arial", Font.BOLD, 10));
        deleteWorkout.setPreferredSize(new Dimension(scaledRemoveWorkoutIcon.getIconWidth(), scaledRemoveWorkoutIcon.getIconHeight()));
        deleteWorkout.setMaximumSize(deleteWorkout.getPreferredSize());
        deleteWorkout.setBorderPainted(true);
        deleteWorkout.setFocusable(false);
        deleteWorkout.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(!workoutsList.isEmpty()){
                    int selectedIndex = savedWorkoutsList.getSelectedIndex();
                    workoutDefaultListModel.remove(selectedIndex);
                    workoutTitleDefaultListModel.remove(selectedIndex);
                    workoutsList.remove(selectedIndex);
                    if(selectedIndex==workoutsList.size()){
                        selectedIndex--;
                    }
                    savedWorkoutsList.setSelectedIndex(selectedIndex);

                    status.setLength(0);
                    status.append("Workout removed!");
                    activateStatus(statusPanel,shrinkStatusTimer,statusText, mainPanel);
                }
            }
        });


        savedWorkoutsScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        savedWorkoutsScrollPane.setPreferredSize(new Dimension(getWidth() / 5, getHeight() * 8 / 10 - newWorkoutButton.getPreferredSize().height));
        savedWorkoutsScrollPane.setMinimumSize(savedWorkoutsScrollPane.getPreferredSize());
        savedWorkoutsScrollPane.setMaximumSize(savedWorkoutsScrollPane.getPreferredSize());
        savedWorkoutsScrollPane.getVerticalScrollBar().setPreferredSize(new Dimension(0, 0));
        savedWorkoutsScrollPane.setBorder(new LineBorder(new Color(80, 73, 69), 1));
        savedWorkoutsScrollPane.getViewport().setBackground(new Color(22, 22, 22));
        savedWorkoutsScrollPane.setViewportView(savedWorkoutsList);

        JPanel savedWorkoutsPanelBottom = new JPanel();
        savedWorkoutsPanelBottom.setLayout(new BoxLayout(savedWorkoutsPanelBottom, BoxLayout.X_AXIS));
        savedWorkoutsPanelBottom.setOpaque(false);


        //Label holding workout title
        headerTitle = new JLabel();
        headerTitle.setText("Untitled Workout");
        headerTitle.setForeground(Color.WHITE);
        headerTitle.setFont(new Font("Arial", Font.BOLD, 22));
        headerTitle.setHorizontalAlignment(SwingConstants.CENTER);


        workoutPanel.setLayout(new BoxLayout(workoutPanel, BoxLayout.Y_AXIS));
        workoutPanel.setPreferredSize(new Dimension(getWidth() / 2, getHeight()));
        workoutPanel.setMinimumSize(new Dimension(getWidth() / 2, getHeight()));
        workoutPanel.setMaximumSize(new Dimension(getWidth() / 2, getHeight()));
        workoutPanel.setOpaque(true);
        workoutPanel.setBackground(new Color(51, 51, 51));

        workoutPanelTop.setLayout(new BoxLayout(workoutPanelTop, BoxLayout.X_AXIS));
        workoutPanelTop.setOpaque(false);
        workoutPanelTop.setPreferredSize(new Dimension(getWidth() / 2, getHeight() / 20));
        workoutPanelTop.setMinimumSize(new Dimension(getWidth() / 2, getHeight() / 20));
        workoutPanelTop.setMaximumSize(new Dimension(getWidth() / 2, getHeight() / 20));

        workoutTitle = new JTextField();
        workoutTitle.setText("Workout title");
        workoutTitle.setForeground(new Color(204, 204, 204));
        workoutTitle.setBackground(new Color(22, 22, 22));
        workoutTitle.setFont(new Font("Arial", Font.PLAIN, (int)(getHeight()/55.25)));
        workoutTitle.setPreferredSize(new Dimension(getWidth() / 3, (int)(getHeight()/22.1)));
        workoutTitle.setMaximumSize(workoutTitle.getPreferredSize());
        workoutTitle.setBorder(new LineBorder(new Color(80, 73, 69)));

        JButton saveWorkoutButton = new JButton(scaledsaveButtonIcon);
        saveWorkoutButton.setContentAreaFilled(false);
        saveWorkoutButton.setFocusPainted(false);
        saveWorkoutButton.setBorderPainted(false);
        saveWorkoutButton.setPreferredSize(new Dimension(scaledsaveButtonIcon.getIconWidth(), scaledsaveButtonIcon.getIconHeight()));
        saveWorkoutButton.setMaximumSize(new Dimension(scaledsaveButtonIcon.getIconWidth(), scaledsaveButtonIcon.getIconHeight()));
        saveWorkoutButton.addActionListener(_ -> {
            try {

                System.out.println("name before : " + workoutDefaultListModel.get(savedWorkoutsList.getSelectedIndex()).getWorkoutData().getTitle());

                int index = savedWorkoutsList.getSelectedIndex();
                //Uppdaterar namnen på övningarna i listan

                workoutDefaultListModel.get(savedWorkoutsList.getSelectedIndex()).getWorkoutData().setTitle(workoutTitle.getText().trim());

                System.out.println("name before : " + workoutDefaultListModel.get(savedWorkoutsList.getSelectedIndex()).getWorkoutData().getTitle());

                workoutTitleDefaultListModel.clear();

                for (Workout workout : workoutsList) {
                    workoutTitleDefaultListModel.addElement(workout.getWorkoutData().getTitle());
                }
                savedWorkoutsList.setModel(workoutTitleDefaultListModel);
                System.out.println(savedWorkoutsList.getSelectedIndex() + " index after");
                savedWorkoutsList.setSelectedIndex(index);
                FirebaseManager.writeDBworkout(workoutsList);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
            status.append("Workout saved!");
            activateStatus(statusPanel,shrinkStatusTimer,statusText, mainPanel);
            status.setLength(0);
        });

        //Panel to hold search and exercieses list vertically

        exercisesPanel.setLayout(new BoxLayout(exercisesPanel, BoxLayout.Y_AXIS));
        exercisesPanel.setPreferredSize(new Dimension(getWidth() / 5, getHeight()));
        exercisesPanel.setMinimumSize(new Dimension(getWidth() / 5, getHeight()));
        exercisesPanel.setMaximumSize(new Dimension(getWidth() / 5, getHeight()));
        exercisesPanel.setOpaque(true);
        exercisesPanel.setBackground(new Color(51, 51, 51));


        JPanel exercisesPanelTop = new JPanel();
        exercisesPanelTop.setLayout(new BorderLayout(0, 0));
        exercisesPanelTop.setOpaque(false);
        exercisesPanelTop.setPreferredSize(new Dimension(getWidth() / 5, getHeight() / 20));
        exercisesPanelTop.setMinimumSize(new Dimension(getWidth() / 5, getHeight() / 20));
        exercisesPanelTop.setMaximumSize(new Dimension(getWidth() / 5, getHeight() / 20));

        //Search and add exercise

        Exercises list = new Exercises();
        list.removeGif();
        for (Exercise exercise : list.getList()) {
            exerciseModel.addElement(exercise);
        }

        searchExerciseResult.setFixedCellHeight((int)(getHeight()/25.5));
        searchExerciseResult.setFont(new Font("Arial", Font.BOLD, (int)(getHeight()/55.25)));
        searchExerciseResult.setBackground(new Color(22, 22, 22));
        searchExerciseResult.setForeground(new Color(204, 204, 204));
        searchExerciseResult.setPreferredSize(new Dimension(200, searchExerciseResult.getFixedCellHeight() * searchExerciseResult.getModel().getSize()));

        searchExercise.setText("Search for exercise...");
        searchExercise.setFont(new Font("Arial", Font.PLAIN, (int)(getHeight()/55.25)));
        searchExercise.setForeground(new Color(204, 204, 204));
        searchExercise.setPreferredSize(new Dimension((int)(getWidth()/8.17692308), (int)(getHeight()/22.1)));
        searchExercise.setMaximumSize(new Dimension(searchExercise.getPreferredSize()));
        searchExercise.setBackground(new Color(22, 22, 22));
        searchExercise.setBorder(new LineBorder(new Color(80, 73, 69)));
        //searchExercise.setAlignmentX(Component.LEFT_ALIGNMENT);

        searchExercise.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (searchExercise.getText().equals("Search for exercise...")) {
                    searchExercise.setText("");
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (searchExercise.getText().isEmpty()) {
                    searchExercise.setText("Search for exercise...");
                }
            }
        });
        searchExercise.getDocument().addDocumentListener(new DocumentListener() {

            @Override
            public void insertUpdate(DocumentEvent e) {
                if (!searchExercise.getText().equals("Search for exercise...")) {
                    filterList();
                }
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                filterList();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
            }

            private void filterList() {
                SwingUtilities.invokeLater(() -> {
                    String searchText = searchExercise.getText().toLowerCase();
                    exerciseModel.clear(); // Clear the current list

                    for (Exercise exercise : list.getList()) {
                        if (exercise.getName().toLowerCase().contains(searchText)) {
                            exerciseModel.addElement(exercise);//Add excercises back to list

                        }
                    }
                });
            }
        });

        //Scroll-Panel to hold all exercises

        exercisesScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        exercisesScrollPane.setPreferredSize(new Dimension(getWidth() / 5, getHeight() * 8 / 10));
        exercisesScrollPane.setMinimumSize(new Dimension(getWidth() / 5, getHeight() * 8 / 10));
        exercisesScrollPane.setMaximumSize(new Dimension(getWidth() / 5, getHeight() * 8 / 10));
        exercisesScrollPane.getVerticalScrollBar().setPreferredSize(new Dimension(0, 0));
        exercisesScrollPane.setBorder(new LineBorder(new Color(80, 73, 69), 1));

        //Panel to hold add new exercise button and set

        addExerciseAndSetPanel.setPreferredSize(new Dimension(getWidth() / 7, getHeight()));
        addExerciseAndSetPanel.setBackground(new Color(51, 51, 51));

        //Change workout title button
        JButton changeTitle = new JButton();
        changeTitle.setText("Change workout title");
        changeTitle.setBackground(new Color(40, 129, 201));
        changeTitle.setForeground(Color.WHITE);
        changeTitle.setVisible(true);


        //"Add exercise"-button
        JButton newExerciseButton = new JButton(scaledAddButtonIcon);
        newExerciseButton.setContentAreaFilled(false);
        newExerciseButton.setFocusPainted(false);
        newExerciseButton.setBorderPainted(false);
        newExerciseButton.setPreferredSize(new Dimension(scaledAddButtonIcon.getIconWidth(), scaledAddButtonIcon.getIconHeight()));
        newExerciseButton.setMaximumSize(new Dimension(scaledAddButtonIcon.getIconWidth(), scaledAddButtonIcon.getIconHeight()));

        //This part generates a new exercise
        newExerciseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Restrict from adding a non-selected exercise
                if (SearchPanel.getSelectedExercise() == null) {
                    return;
                }
                // Display if empty log
                if (emptyLog) {
                    workoutContainer.removeAll();
                    emptyLog = false;
                }

                addExercise(SearchPanel.getSelectedExercise(), workoutContainer);
                workoutContainer.revalidate();
                workoutContainer.repaint();
                ProgramPanel.this.revalidate();
                ProgramPanel.this.repaint();
            }

        });

        exercisesPanelTop.add(searchExercise, BorderLayout.WEST);

        exercisesPanel.add(Box.createVerticalGlue());
        exercisesPanel.add(exercisesPanelTop);
        exercisesPanel.add(Box.createVerticalGlue());
        exercisesPanel.add(exercisesScrollPane);
        exercisesPanel.add(Box.createVerticalGlue());

        //addExerciseAndSetPanel.add(newExcerciseButton);
        addExerciseAndSetPanel.add(saveWorkoutButton);
        addExerciseAndSetPanel.add(changeTitle);


        workoutPanelTop.add(workoutTitle);
        workoutPanelTop.add(Box.createHorizontalGlue());
        workoutPanelTop.add(saveWorkoutButton);


        workoutPanel.add(Box.createVerticalGlue());
        workoutPanel.add(workoutPanelTop);
        workoutPanel.add(Box.createVerticalGlue());
        workoutPanel.add(workoutScrollPane);
        workoutPanel.add(Box.createVerticalGlue());

        savedWorkoutsPanelTop.add(Box.createHorizontalGlue());
        savedWorkoutsPanelTop.add(savedWorkoutsLabel);
        savedWorkoutsPanelTop.add(Box.createHorizontalGlue());

        savedWorkoutsPanelBottom.add(Box.createHorizontalGlue());
        savedWorkoutsPanelBottom.add(newWorkoutButton);
        savedWorkoutsPanelBottom.add(Box.createHorizontalGlue());
        savedWorkoutsPanelBottom.add(deleteWorkout);
        savedWorkoutsPanelBottom.add(Box.createHorizontalGlue());


        savedWorkoutsPanel.add(Box.createVerticalGlue());
        savedWorkoutsPanel.add(savedWorkoutsPanelTop);
        savedWorkoutsPanel.add(Box.createVerticalGlue());
        savedWorkoutsPanel.add(savedWorkoutsScrollPane);
        savedWorkoutsPanel.add(savedWorkoutsPanelBottom);
        savedWorkoutsPanel.add(Box.createVerticalGlue());

        mainPanel.add(Box.createHorizontalGlue());
        mainPanel.add(new SearchPanel(this.getWidth(), this.getHeight(), newExerciseButton));
        mainPanel.add(Box.createHorizontalGlue());
        mainPanel.add(workoutPanel);
        mainPanel.add(Box.createHorizontalGlue());
        mainPanel.add(savedWorkoutsPanel);
        mainPanel.add(Box.createHorizontalGlue());

        mainPanel.add(statusPanel);
        mainPanel.add(Box.createHorizontalGlue());
        wrapper.add(mainPanel, JLayeredPane.DEFAULT_LAYER);
        wrapper.add(statusPanel, JLayeredPane.POPUP_LAYER);
        this.add(wrapper);

    }

    //Trigger the status panel
    public void activateStatus(JPanel statusPanel, Timer shrinkStatusTimer, JLabel statusText, JPanel mainPanel) {
        statusText.setText(status.toString());
        if(statusText.getText().equals("Workout removed!")){
            statusPanel.setBackground(Color.RED);
        }else{
            statusPanel.setBackground(new Color(46, 148, 76));
        }

        statusPanel.setBounds(0,programPanelHeight-50,mainPanel.getWidth(),50);
        statusPanel.revalidate();
        statusPanel.repaint();
        statusDelayCounter = 20;
        statusPanel.setVisible(true);
        statusText.setText(status.toString());
        shrinkStatusTimer.start();
        shrinkStatusTimer.restart();
    }


    private void addExercise(Exercise currentExercise, Workout workoutContainer) {
        JPanel mainExercisePanel = new JPanel();
        mainExercisePanel.setName("mainExercisePanel");
        mainExercisePanel.setLayout(new BoxLayout(mainExercisePanel, BoxLayout.Y_AXIS));
        mainExercisePanel.setBackground(AppThemeColors.panelColor);

        // Track in log
        int exerciseId = workoutContainer.getExercisePanels().size() + 1;
        workoutContainer.getExercisePanels().put(exerciseId, mainExercisePanel);
        workoutContainer.getExerciseSetCount().put(exerciseId, 0);
        workoutContainer.addIdToExercise().put(currentExercise, exerciseId);


        // Panel to display exercise name
        JPanel exerciseNameTitlePanel = new JPanel();
        exerciseNameTitlePanel.setLayout(new BoxLayout(exerciseNameTitlePanel, BoxLayout.Y_AXIS));
        exerciseNameTitlePanel.setOpaque(false);
        exerciseNameTitlePanel.setName("exerciseNameTitlePanel");

        // Label to hold name of exercise
        JLabel exerciseName = new JLabel();
        exerciseName.setName("exerciseName");
        exerciseName.setPreferredSize(new Dimension(getWidth(), setPanelHeight));
        exerciseName.setMinimumSize(exerciseName.getPreferredSize());
        exerciseName.setMaximumSize(exerciseName.getPreferredSize());
        exerciseName.setText(currentExercise.getName());
        exerciseName.setFont(new Font("Arial", Font.BOLD, 20));
        exerciseName.setForeground(workoutPanelTextColor);
        exerciseName.setAlignmentX(Component.LEFT_ALIGNMENT);
        exerciseNameTitlePanel.add(exerciseName);
        mainExercisePanel.add(exerciseNameTitlePanel);

        // Label to hold favorite symbol or whatever
        Font emojiFont = new Font("Segoe UI Emoji", Font.PLAIN, 15);
        JLabel favoriteLabel = new JLabel();
        favoriteLabel.setFont(emojiFont);
        favoriteLabel.setName("favoriteLabel");
        favoriteLabel.setText("\uD83D\uDCAA");
        favoriteLabel.setForeground(new Color(196, 196, 49));
        if (UserData.getFavoriteExercises().contains(currentExercise)) {
            exerciseName.setText(exerciseName.getText()+" *");
        }

        // Panel to hold the titles of Set, Rep, Weight, RIR
        JPanel setRepWeightRirTitleNPanel = new JPanel();
        setRepWeightRirTitleNPanel.setName("setRepWeightRirTitleNPanel");
        setRepWeightRirTitleNPanel.setBackground(settingsPanelColor);
        setRepWeightRirTitleNPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
        setRepWeightRirTitleNPanel.setPreferredSize(new Dimension(getWidth(), setPanelHeight));
        setRepWeightRirTitleNPanel.setMinimumSize(setRepWeightRirTitleNPanel.getPreferredSize());
        setRepWeightRirTitleNPanel.setMaximumSize(setRepWeightRirTitleNPanel.getPreferredSize());
        setRepWeightRirTitleNPanel.setOpaque(true);
        setRepWeightRirTitleNPanel.setLayout(new BorderLayout());
        mainExercisePanel.add(setRepWeightRirTitleNPanel);

        // Title Panel to align Set title to left
        JPanel leftPanel = new JPanel();
        leftPanel.setName("leftPanel");
        leftPanel.setOpaque(false);
        leftPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
        leftPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        leftPanel.setPreferredSize(new Dimension(getWidth(), setPanelHeight));
        leftPanel.setMinimumSize(leftPanel.getPreferredSize());
        leftPanel.setMaximumSize(leftPanel.getPreferredSize());
        setRepWeightRirTitleNPanel.add(leftPanel, BorderLayout.WEST);

        // Label to hold "Set"
        JLabel setLabel = new JLabel();
        setLabel.setName("setLabel");
        setLabel.setText("Set");
        setLabel.setForeground(workoutPanelTextColor);
        setLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        leftPanel.add(setLabel);

        //Remove exercise-button
        JButton removeExercise = new JButton(scaledRemoveExerciseIcon);
        removeExercise.setName("removeExercise");
        removeExercise.setPreferredSize(new Dimension(getWidth() / 15, setPanelHeight));
        removeExercise.setMinimumSize(removeExercise.getPreferredSize());
        removeExercise.setMaximumSize(removeExercise.getPreferredSize());
        removeExercise.setMargin(new Insets(0, 0, 0, 0));
        removeExercise.setForeground(Color.white);
        removeExercise.setContentAreaFilled(false);
        removeExercise.setFont(new Font("Arial", Font.BOLD, 12));
        //removeExercise.setBackground(Color.red);
        removeExercise.setBorderPainted(false);
        removeExercise.setFocusPainted(false);
        removeExercise.addActionListener(_ -> {
            workoutContainer.getWorkoutData().setTotalWorkoutHeight(workoutContainer.getWorkoutData().getTotalWorkoutHeight() - (4 * setPanelHeight));
            for (Component comp : mainExercisePanel.getComponents()) {
                if ("setPanel".equals(comp.getName())) {
                    workoutContainer.getWorkoutData().setTotalWorkoutHeight(workoutContainer.getWorkoutData().getTotalWorkoutHeight() - (setPanelHeight));
                }
            }
            workoutContainer.setPreferredSize(new Dimension(workoutContainer.getWidth(), workoutContainer.getWorkoutData().getTotalWorkoutHeight()));
            workoutContainer.getWorkoutData().deleteExercise(exerciseId);
            mainExercisePanel.removeAll();
            workoutContainer.repaint();
            workoutContainer.revalidate();


        });

        // Title Panel to align Rep, RIR and WEIGHT to right
        JPanel rightPanel = new JPanel();
        rightPanel.setName("rightPanel");
        rightPanel.setOpaque(false);
        rightPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
        rightPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
        setRepWeightRirTitleNPanel.add(rightPanel, BorderLayout.EAST);

        // Label to hold "Reps"
        JLabel repsLabel = new JLabel();
        repsLabel.setName("repsLabel");
        repsLabel.setText("Reps");
        repsLabel.setForeground(workoutPanelTextColor);
        repsLabel.setAlignmentX(Component.RIGHT_ALIGNMENT);
        rightPanel.add(repsLabel);

        // Label to hold "Weight"
        JLabel weightLabel = new JLabel();
        weightLabel.setName("weightLabel");
        weightLabel.setText("Weight");
        weightLabel.setForeground(workoutPanelTextColor);
        weightLabel.setAlignmentX(Component.RIGHT_ALIGNMENT);
        rightPanel.add(weightLabel);
        //Label to hold "RIR"
        JLabel rirLabel = new JLabel();
        rirLabel.setName("rirLabel");
        rirLabel.setText("RIR");
        rirLabel.setForeground(workoutPanelTextColor);
        rirLabel.setAlignmentX(Component.RIGHT_ALIGNMENT);
        rightPanel.add(rirLabel);

        // "Add set"- button
        JButton addSet = new JButton(scaledNewSetIcon);
        addSet.setName("addSet");
        addSet.setPreferredSize(new Dimension(getWidth() / 35, setPanelHeight));
        addSet.setMinimumSize(addSet.getPreferredSize());
        addSet.setMaximumSize(addSet.getPreferredSize());
        addSet.setMargin(new Insets(0, 0, 0, 0));
        addSet.setContentAreaFilled(false);
        addSet.setBorderPainted(false);
        addSet.setFocusPainted(false);
        addSet.setBorder(null);

        workoutContainer.getWorkoutData().setTotalWorkoutHeight(workoutContainer.getWorkoutData().getTotalWorkoutHeight() + (4 * setPanelHeight)); //Lägger till höjden för de 4 paneler som skapas när en övning läggs till

        addSet.addActionListener(_ -> {
            addSet(exerciseId, currentExercise, mainExercisePanel, setPanelHeight, workoutContainer);
            workoutContainer.setPreferredSize(new Dimension(workoutContainer.getWidth(), workoutContainer.getWorkoutData().getTotalWorkoutHeight()));
            workoutContainer.revalidate();
            workoutContainer.repaint();

        });

        exerciseNameTitlePanel.add(removeExercise);
        mainExercisePanel.add(addSet);
        ProgramPanel.this.revalidate();
        ProgramPanel.this.repaint();
        workoutContainer.add(mainExercisePanel);
        workoutContainer.setPreferredSize(new Dimension(workoutContainer.getWidth(), workoutContainer.getWorkoutData().getTotalWorkoutHeight()));
        workoutContainer.setMinimumSize(workoutContainer.getPreferredSize());
        workoutContainer.setMaximumSize(workoutContainer.getPreferredSize());
        workoutContainer.revalidate();
        workoutContainer.repaint();

    }

    public static void addSet(int exerciseId, Exercise currentExercise, JPanel mainExercisePanel, int heightToRemove, Workout workoutContainer) {

        workoutContainer.getWorkoutData().setTotalWorkoutHeight(workoutContainer.getWorkoutData().getTotalWorkoutHeight() + (setPanelHeight)); //Lägger till höjden settet som läggs till

        WorkoutSet workoutSet = new WorkoutSet();
        workoutSet.setExercise(currentExercise);
        workoutContainer.getWorkoutData().addSet(exerciseId, workoutSet);
        JPanel setPanel = new JPanel();

        workoutContainer.getSetPanels().put(exerciseId, setPanel);
        setPanel.setName("setPanel");

        workoutSet.setNumber(workoutContainer.getWorkoutData().getSetSize(exerciseId));


        JPanel parentPanel = workoutContainer.getExercisePanels().get(exerciseId);

        setPanel.setOpaque(true);
        setPanel.setLayout(new BorderLayout());
        setPanel.setBackground(settingsPanelColor);
        setPanel.setPreferredSize(new Dimension(ProgramPanel.programPanelWidth, setPanelHeight));
        setPanel.setMinimumSize(setPanel.getPreferredSize());
        setPanel.setMaximumSize(new Dimension(ProgramPanel.programPanelWidth, setPanelHeight));
        setPanel.setAlignmentX(SwingConstants.CENTER);

        JPanel leftPanel = new JPanel();
        leftPanel.setOpaque(false);
        leftPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        leftPanel.setName("leftPanel");
        setPanel.add(leftPanel, BorderLayout.WEST);


        JLabel setLabel = new JLabel();
        setLabel.setName("setLabel");
        setLabel.setText(workoutContainer.getWorkoutData().getSetSize(exerciseId) + ".");
        setLabel.setForeground(workoutPanelTextColor);
        leftPanel.add(setLabel);


        JButton deleteSet = new JButton(scaledRemoveSetIcon);
        deleteSet.setName("deleteSet");
        deleteSet.setContentAreaFilled(false);
        deleteSet.setBorder(null);
        deleteSet.setPreferredSize(new Dimension(ProgramPanel.programPanelWidth / 45, setPanelHeight));
        deleteSet.setMinimumSize(deleteSet.getPreferredSize());
        deleteSet.setMaximumSize(deleteSet.getPreferredSize());
        deleteSet.setBorderPainted(false);
        deleteSet.setFocusPainted(false);
        deleteSet.setMargin(new Insets(0, 0, 0, 0));

        // delete set & update log
        deleteSet.addActionListener(_ -> deleteSet(parentPanel, exerciseId, workoutSet, workoutContainer, heightToRemove, setPanel, workoutContainer));

        JButton moveSetUp = new JButton(scaledMoveSetUpIcon);
        moveSetUp.setName("moveSetUp");
        moveSetUp.setContentAreaFilled(false);
        //moveSetUp.setBorder(null);
        moveSetUp.setBorderPainted(false);
        moveSetUp.setFocusable(false);
        moveSetUp.setMargin(new Insets(0, 0, 0, 0));

        JButton moveSetDown = new JButton(scaledMoveSetDownIcon);
        moveSetDown.setName("moveSetDown");
        moveSetDown.setContentAreaFilled(false);
        //moveSetDown.setBorder(null);
        moveSetDown.setBorderPainted(false);
        moveSetDown.setFocusable(false);
        moveSetDown.setMargin(new Insets(0, 0, 0, 0));

        moveSetDown.addActionListener(_ -> moveDown(parentPanel, exerciseId, workoutSet, workoutContainer));


        // move the set up
        moveSetUp.addActionListener(_ -> moveUp(parentPanel, exerciseId, workoutSet, workoutContainer));

        JPanel rightPanel = new JPanel();
        JTextField weightAmount = new JTextField();
        JTextField rirAmount = new JTextField();

        rightPanel.setName("rightPanel");
        rightPanel.setOpaque(false);
        rightPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));

        JTextField repsAmount = new JTextField();
        repsAmount.setName("repsAmount");
        repsAmount.setForeground(new Color(204, 204, 204));
        repsAmount.setBackground(new Color(22, 22, 22));
        repsAmount.setPreferredSize(new Dimension(35, 20));
        rightPanel.add(repsAmount);
        repsAmount.setBorder(null);
        repsAmount.setText("0");
        repsAmount.setBorder(new LineBorder(new Color(129, 115, 103), 1));


        weightAmount.setBackground(new Color(22, 22, 22));
        weightAmount.setForeground(new Color(204, 204, 204));
        weightAmount.setPreferredSize(new Dimension(35, 20));
        rightPanel.add(weightAmount);
        weightAmount.setBorder(null);
        weightAmount.setText("0");
        weightAmount.setBorder(new LineBorder(new Color(129, 115, 103), 1));


        rirAmount.setPreferredSize(new Dimension(35, 20));
        rirAmount.setForeground(new Color(204, 204, 204));
        rirAmount.setBackground(new Color(22, 22, 22));
        rightPanel.add(rirAmount);
        rirAmount.setBorder(null);
        rirAmount.setText("0");
        rirAmount.setBorder(new LineBorder(new Color(129, 115, 103), 1));

        setPanel.add(rightPanel, BorderLayout.EAST);
        leftPanel.add(deleteSet);
        rightPanel.add(moveSetUp);
        rightPanel.add(moveSetDown);
        parentPanel.add(setPanel);
        parentPanel.revalidate();
        parentPanel.repaint();

        // Action Listeners to update the correct set
        final WorkoutSet finalWorkoutSet = workoutSet;
        repsAmount.addActionListener(_ -> {
            if (repsAmount.getText().isEmpty()) repsAmount.setText("0");
            repsAmount.setBackground(new Color(217, 217, 217));
            finalWorkoutSet.setReps(Integer.parseInt(repsAmount.getText()));
            workoutContainer.getWorkoutData().addSet(exerciseId, finalWorkoutSet);
        });

        weightAmount.addActionListener(_ -> {
            if (weightAmount.getText().isEmpty()) weightAmount.setText("0");
            weightAmount.setBackground(new Color(217, 217, 217));
            finalWorkoutSet.setWeight(Integer.parseInt(weightAmount.getText()));
            workoutContainer.getWorkoutData().addSet(exerciseId, finalWorkoutSet);
        });

        rirAmount.addActionListener(_ -> {
            if (rirAmount.getText().isEmpty()) rirAmount.setText("0");
            rirAmount.setBackground(new Color(217, 217, 217));
            finalWorkoutSet.setRir(Integer.parseInt(rirAmount.getText()));
            workoutContainer.getWorkoutData().addSet(exerciseId, finalWorkoutSet);
        });
    }

    public static void moveUp(JPanel parentPanel, int exerciseId, WorkoutSet workoutSet, Workout workoutContainer) {

        workoutContainer.getWorkoutData().moveSetUp(exerciseId, workoutSet);
        ArrayList<JPanel> temp = new ArrayList<>();
        HashMap<Integer, List<WorkoutSet>> updatedSet = workoutContainer.getWorkoutData().getExerciseSets();
        int i = 0;
        // Swap set number
        for (Component comp : parentPanel.getComponents()) {
            if ("setPanel".equals(comp.getName())) {
                JPanel setPanel = (JPanel) comp;
                for (Component comp1 : setPanel.getComponents()) {
                    if ("leftPanel".equals(comp1.getName())) {
                        JPanel leftPanel2 = (JPanel) comp1;
                        for (Component comp2 : leftPanel2.getComponents()) {
                            if ("setLabel".equals(comp2.getName())) {
                                {
                                    JLabel setLabel = (JLabel) comp2;

                                    if (workoutSet.getNumber() != 1) {
                                        //Om siffran i labeln inte är lika med the set nummret vi ska flytta, och om sifforna av
                                        // set labelns och settets differens är större än 2.
                                        if (!setLabel.getText().replace(".", "").equals(Integer.toString(workoutSet.getNumber()))
                                                && workoutSet.getNumber() - Integer.parseInt(setLabel.getText().replace(".", "")) > 1) {

                                            temp.add(setPanel);
                                        } else if (Integer.parseInt(setLabel.getText().replace(".", "")) - workoutSet.getNumber() == 1) {
                                            setLabel.setText(workoutSet.getNumber() + 1 + ".");
                                            temp.add(setPanel);
                                        } else if (setLabel.getText().replace(".", "").equals(Integer.toString(workoutSet.getNumber()))) {
                                            setLabel.setText(workoutSet.getNumber() - 1 + ".");
                                            temp.add(setPanel);
                                        } else {
                                            temp.add(setPanel);
                                        }

                                    }
                                    setLabel.setText(updatedSet.get(exerciseId).get(i).getNumber() + ".");
                                    i++;
                                }
                            }
                        }
                    }
                }
            }
        }

        if (workoutSet.getNumber() != 1) {
            for (Component comp : parentPanel.getComponents()) {
                if ("setPanel".equals(comp.getName())) {
                    parentPanel.remove(comp);
                }
            }
            Collections.swap(temp, workoutSet.getNumber() - 2, workoutSet.getNumber() - 1);
            for (JPanel panel : temp) {
                for (Map.Entry<Integer, List<WorkoutSet>> w : workoutContainer.getWorkoutData().getExerciseSets().entrySet()) {
                    int y = 1;
                    for (WorkoutSet z : w.getValue()) {
                        z.setNumber(y);
                        y++;
                    }
                }
                parentPanel.add(panel);
            }
        }


        parentPanel.repaint();
        parentPanel.revalidate();

    }

    public static void moveDown(JPanel parentPanel, int exerciseId, WorkoutSet workoutSet, Workout workoutContainer) {

        workoutContainer.getWorkoutData().moveSetDown(exerciseId, workoutSet, workoutContainer.getWorkoutData());

        ArrayList<JPanel> temp = new ArrayList<>();
        // Swap set number
        for (Component comp : parentPanel.getComponents()) {
            if ("setPanel".equals(comp.getName())) {
                JPanel setPanel = (JPanel) comp;
                for (Component comp1 : setPanel.getComponents()) {
                    if ("leftPanel".equals(comp1.getName())) {
                        JPanel leftPanel2 = (JPanel) comp1;
                        for (Component comp2 : leftPanel2.getComponents()) {
                            if ("setLabel".equals(comp2.getName())) {
                                {
                                    JLabel setLabel = (JLabel) comp2;

                                    if (workoutSet.getNumber() - 1 != workoutContainer.getWorkoutData().getSetSize(exerciseId) - 1) {

                                        if (Integer.parseInt(setLabel.getText().replace(".", "")) < workoutSet.getNumber()) {
                                            temp.add(setPanel);
                                        } else if (Integer.parseInt(setLabel.getText().replace(".", "")) == workoutSet.getNumber()) {
                                            setLabel.setText(workoutSet.getNumber() + 1 + ".");
                                            temp.add(setPanel);
                                        } else if (Integer.parseInt(setLabel.getText().replace(".", "")) == workoutSet.getNumber() + 1) {
                                            setLabel.setText(workoutSet.getNumber() + ".");
                                            temp.add(setPanel);
                                        } else {
                                            temp.add(setPanel);
                                        }

                                    }
                                    //setLabel.setText(updatedSet.get(exerciseId).get(i).getNumber() + ".");
                                }
                            }
                        }
                    }
                }
            }
        }

        if (workoutSet.getNumber() - 1 != workoutContainer.getWorkoutData().getSetSize(exerciseId) - 1) {
            for (Component comp : parentPanel.getComponents()) {
                if ("setPanel".equals(comp.getName())) {
                    parentPanel.remove(comp);
                }
            }
            Collections.swap(temp, workoutSet.getNumber() - 1, workoutSet.getNumber());
            for (JPanel panel : temp) {
                for (Map.Entry<Integer, List<WorkoutSet>> w : workoutContainer.getWorkoutData().getExerciseSets().entrySet()) {
                    int y = 1;
                    for (WorkoutSet z : w.getValue()) {
                        z.setNumber(y);
                        y++;
                    }
                }
                parentPanel.add(panel);
            }
        }

        parentPanel.repaint();
        parentPanel.revalidate();

    }

    public static void deleteSet(JPanel parentPanel, int exerciseId, WorkoutSet workoutSet, JPanel logContainer, int heightToRemove, JPanel setPanel, Workout workoutContainer) {
        parentPanel.remove(setPanel);
        int i = 0;
        // Delete the set from WorkoutData
        workoutContainer.getWorkoutData().deleteSet(exerciseId, workoutSet.getNumber());
        HashMap<Integer, List<WorkoutSet>> updatedSet = workoutContainer.getWorkoutData().getExerciseSets();
        List<WorkoutSet> updatedSets = updatedSet.get(exerciseId);

        for (Component comp : parentPanel.getComponents()) {
            if ("setPanel".equals(comp.getName())) {
                JPanel setPanel2 = (JPanel) comp;
                for (Component comp2 : setPanel2.getComponents()) {
                    if ("leftPanel".equals(comp2.getName())) {
                        JPanel leftPanel2 = (JPanel) comp2;

                        for (Component comp3 : leftPanel2.getComponents()) {
                            if ("setLabel".equals(comp3.getName())) {
                                {
                                    JLabel setLabel2 = (JLabel) comp3;
                                    setLabel2.setText(updatedSets.get(i).getNumber() + ".");
                                    i++;
                                }
                            }
                        }
                    }
                }
            }
        }
        workoutContainer.getWorkoutData().setTotalWorkoutHeight(workoutContainer.getWorkoutData().getTotalWorkoutHeight() - (setPanelHeight));
        workoutContainer.setPreferredSize(new Dimension(logContainer.getWidth(), workoutContainer.getWorkoutData().getTotalWorkoutHeight()));
        parentPanel.revalidate();
        parentPanel.repaint();

    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        // Draw the image to fill the entire panel
        if (emptyBackground != null) {
            if(!SettingsPanel.lightMode){
                workoutPanelTextColor = Color.WHITE;

                mainPanel.setBackground(AppThemeColors.PRIMARY);
                workoutContainer.setBackground(AppThemeColors.panelColor);
                workoutContainer.setForeground(Color.WHITE);
                workoutScrollPane.getViewport().setBackground(AppThemeColors.panelColor);
                workoutScrollPane.setForeground(Color.WHITE);
                workoutPanel.setBackground(AppThemeColors.PRIMARY);
                exercisesPanel.setBackground(AppThemeColors.PRIMARY);
                workoutTitle.setBackground(AppThemeColors.textFieldColor);
                workoutTitle.setForeground(Color.WHITE);
                searchExercise.setBackground(AppThemeColors.textFieldColor);
                searchExercise.setForeground(Color.WHITE);
                savedWorkoutsScrollPane.getViewport().setBackground(AppThemeColors.textFieldColor);
                searchExerciseResult.setBackground(AppThemeColors.panelColor);
                searchExerciseResult.setForeground(Color.WHITE);
                exercisesScrollPane.setBackground(AppThemeColors.textFieldColor);
                addExerciseAndSetPanel.setBackground(AppThemeColors.PRIMARY);
                savedWorkoutsPanel.setBackground(AppThemeColors.PRIMARY);
                savedWorkoutsList.setBackground(AppThemeColors.panelColor);
                savedWorkoutsList.setForeground(workoutPanelTextColor);

                for(Workout workout : workoutsList){
                    for (Component comp1 : workout.getComponents()) {
                        if(comp1.getName()!=null){
                            if (comp1.getName().equals("mainExercisePanel")) {
                                JPanel mainExercisePanel = (JPanel) comp1;
                                mainExercisePanel.setBackground(AppThemeColors.panelColor);
                                for (Component comp2 : mainExercisePanel.getComponents()) {
                                    if ("setPanel".equals(comp2.getName())) {
                                        JPanel setPanel = (JPanel) comp2;
                                        setPanel.setBackground(AppThemeColors.panelColor);


                                        for (Component compLeftPanel : setPanel.getComponents()) {
                                            if ("leftPanel".equals(compLeftPanel.getName())) {
                                                JPanel leftPanel = (JPanel) compLeftPanel;
                                                leftPanel.setBackground(AppThemeColors.panelColor);
                                                for(Component setLabelComp : leftPanel.getComponents()){
                                                    if(setLabelComp.getName().equals("setLabel")){
                                                        JLabel setLabel = (JLabel) setLabelComp;
                                                        setLabel.setForeground(workoutPanelTextColor);
                                                    }

                                                }
                                            }
                                        }
                                    }

                                    if (comp2.getName() != null) {

                                        if (comp2.getName().equals("exerciseNameTitlePanel")) {

                                            JPanel exerciseNameTitlePanel = (JPanel) comp2;
                                            exerciseNameTitlePanel.setBackground(AppThemeColors.panelColor);
                                            for(Component titleComp:exerciseNameTitlePanel.getComponents()){
                                                if(titleComp.getName().equals("exerciseName")){
                                                    JLabel exerciseName = (JLabel) titleComp;
                                                    exerciseName.setForeground(workoutPanelTextColor);
                                                }
                                            }

                                        }
                                        if(comp2.getName().equals("setRepWeightRirTitleNPanel")){
                                            JPanel setRepWeightRirTitleNPanel = (JPanel) comp2;
                                            setRepWeightRirTitleNPanel.setBackground(AppThemeColors.PRIMARY);
                                            for (Component compRight : setRepWeightRirTitleNPanel.getComponents()){
                                                if(compRight.getName()!=null){
                                                    if("rightPanel".equals(compRight.getName())){
                                                        JPanel rightPanel = (JPanel) compRight;
                                                        for(Component comp : rightPanel.getComponents()){
                                                            if(comp.getName()!=null){
                                                                if(comp.getName().equals("repsLabel")){
                                                                    JLabel repsLabel = (JLabel) comp;
                                                                    repsLabel.setForeground(workoutPanelTextColor);
                                                                }
                                                                if(comp.getName().equals("weightLabel")){
                                                                    JLabel repsLabel = (JLabel) comp;
                                                                    repsLabel.setForeground(workoutPanelTextColor);
                                                                }
                                                                if(comp.getName().equals("rirLabel")){
                                                                    JLabel repsLabel = (JLabel) comp;
                                                                    repsLabel.setForeground(workoutPanelTextColor);
                                                                }
                                                            }
                                                        }
                                                    }
                                                    for (Component compLeftPanel : setRepWeightRirTitleNPanel.getComponents()) {
                                                        if ("leftPanel".equals(compLeftPanel.getName())) {
                                                            JPanel leftPanel = (JPanel) compLeftPanel;
                                                            leftPanel.setBackground(AppThemeColors.panelColor);
                                                            for(Component setLabelComp : leftPanel.getComponents()){
                                                                if(setLabelComp.getName().equals("setLabel")){
                                                                    JLabel setLabel = (JLabel) setLabelComp;
                                                                    setLabel.setForeground(workoutPanelTextColor);
                                                                }

                                                            }
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }

                g.drawImage(scaledEmptyBackgroundIcon.getImage(), 0, 0, getWidth(), getHeight(), this);
            }else{
                workoutPanelTextColor = Color.BLACK;

                mainPanel.setBackground(AppThemeColors.PRIMARY);
                workoutContainer.setBackground(AppThemeColors.panelColor);
                workoutContainer.setForeground(Color.BLACK);
                workoutScrollPane.getViewport().setBackground(AppThemeColors.panelColor);
                workoutScrollPane.setForeground(Color.BLACK);
                workoutPanel.setBackground(AppThemeColors.PRIMARY);
                exercisesPanel.setBackground(AppThemeColors.PRIMARY);
                workoutTitle.setBackground(AppThemeColors.textFieldColor);
                workoutTitle.setForeground(Color.BLACK);
                searchExercise.setBackground(AppThemeColors.textFieldColor);
                searchExercise.setForeground(Color.BLACK);
                savedWorkoutsScrollPane.getViewport().setBackground(AppThemeColors.textFieldColor);
                searchExerciseResult.setBackground(AppThemeColors.panelColor);
                searchExerciseResult.setForeground(Color.BLACK);
                exercisesScrollPane.setBackground(AppThemeColors.textFieldColor);
                addExerciseAndSetPanel.setBackground(AppThemeColors.panelColor);
                workoutPanelTop.setBackground(AppThemeColors.panelColor);
                savedWorkoutsPanel.setBackground(AppThemeColors.PRIMARY);
                savedWorkoutsList.setBackground(AppThemeColors.panelColor);
                savedWorkoutsList.setForeground(workoutPanelTextColor);

                for(Workout workout : workoutsList){
                    for (Component comp1 : workout.getComponents()) {
                        if(comp1.getName()!=null){
                            if (comp1.getName().equals("mainExercisePanel")) {
                                JPanel mainExercisePanel = (JPanel) comp1;
                                mainExercisePanel.setBackground(AppThemeColors.panelColor);
                                for (Component comp2 : mainExercisePanel.getComponents()) {
                                    if ("setPanel".equals(comp2.getName())) {
                                        JPanel setPanel = (JPanel) comp2;
                                        setPanel.setBackground(AppThemeColors.panelColor);


                                        for (Component compLeftPanel : setPanel.getComponents()) {
                                            if ("leftPanel".equals(compLeftPanel.getName())) {
                                                JPanel leftPanel = (JPanel) compLeftPanel;
                                                leftPanel.setBackground(AppThemeColors.panelColor);
                                                for(Component setLabelComp : leftPanel.getComponents()){
                                                    if(setLabelComp.getName().equals("setLabel")){
                                                        JLabel setLabel = (JLabel) setLabelComp;
                                                        setLabel.setForeground(workoutPanelTextColor);
                                                    }

                                                }
                                            }
                                        }
                                    }

                                    if (comp2.getName() != null) {

                                        if (comp2.getName().equals("exerciseNameTitlePanel")) {

                                            JPanel exerciseNameTitlePanel = (JPanel) comp2;
                                            exerciseNameTitlePanel.setBackground(AppThemeColors.panelColor);
                                            for(Component titleComp:exerciseNameTitlePanel.getComponents()){
                                                if(titleComp.getName().equals("exerciseName")){
                                                    JLabel exerciseName = (JLabel) titleComp;
                                                    exerciseName.setForeground(workoutPanelTextColor);
                                                }
                                            }

                                        }
                                        if(comp2.getName().equals("setRepWeightRirTitleNPanel")){
                                            JPanel setRepWeightRirTitleNPanel = (JPanel) comp2;
                                            setRepWeightRirTitleNPanel.setBackground(AppThemeColors.PRIMARY);
                                            for (Component compRight : setRepWeightRirTitleNPanel.getComponents()){
                                                if(compRight.getName()!=null){
                                                    if("rightPanel".equals(compRight.getName())){
                                                        JPanel rightPanel = (JPanel) compRight;
                                                        for(Component comp : rightPanel.getComponents()){
                                                            if(comp.getName()!=null){
                                                                if(comp.getName().equals("repsLabel")){
                                                                    JLabel repsLabel = (JLabel) comp;
                                                                    repsLabel.setForeground(workoutPanelTextColor);
                                                                }
                                                                if(comp.getName().equals("weightLabel")){
                                                                    JLabel repsLabel = (JLabel) comp;
                                                                    repsLabel.setForeground(workoutPanelTextColor);
                                                                }
                                                                if(comp.getName().equals("rirLabel")){
                                                                    JLabel repsLabel = (JLabel) comp;
                                                                    repsLabel.setForeground(workoutPanelTextColor);
                                                                }
                                                            }
                                                        }
                                                    }
                                                    for (Component compLeftPanel : setRepWeightRirTitleNPanel.getComponents()) {
                                                        if ("leftPanel".equals(compLeftPanel.getName())) {
                                                            JPanel leftPanel = (JPanel) compLeftPanel;
                                                            leftPanel.setBackground(AppThemeColors.panelColor);
                                                            for(Component setLabelComp : leftPanel.getComponents()){
                                                                if(setLabelComp.getName().equals("setLabel")){
                                                                    JLabel setLabel = (JLabel) setLabelComp;
                                                                    setLabel.setForeground(workoutPanelTextColor);
                                                                }

                                                            }
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }

                g.drawImage(scaledLightEmptyBackgroundIcon.getImage(), 0, 0, getWidth(), getHeight(), this);
            }
        }
    }
}




