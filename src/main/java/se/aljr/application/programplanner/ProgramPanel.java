package se.aljr.application.programplanner;

import org.checkerframework.checker.units.qual.A;
import se.aljr.application.exercise.Excercise.Exercise;
import se.aljr.application.exercise.Program.Exercises;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.util.List;

public class ProgramPanel extends JPanel {
    private static int totalHeight;
    private WorkoutData currentWorkout = new WorkoutData();
    private String workoutTitle = currentWorkout.getTitle();
    private String resourcePath;
    private static boolean emptyLog = true;
    private ImageIcon emptyBackground;
    private Image scaledEmptyBackground;
    private ImageIcon scaledEmptyBackgroundIcon;
    private ImageIcon addButton;
    private Image scaledAddButton;
    private ImageIcon scaledAddButtonIcon;

    private ImageIcon saveButton;
    private Image scaledsaveButton;
    private ImageIcon scaledsaveButtonIcon;

    Map<Exercise, Integer> idToExercise = new HashMap<>();
    Map<Integer, JPanel> exercisePanels = new HashMap<>();
    Map<Integer, JPanel> setPanels = new HashMap<>();
    Map<Integer, Integer> exerciseSetCount = new HashMap<>();

    public ProgramPanel(int width, int height) {
        resourcePath = getClass().getClassLoader().getResource("resource.path").getPath().replace("resource.path", "");
        emptyBackground = new ImageIcon(resourcePath + "emptyBackground.png");
        scaledEmptyBackground = emptyBackground.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);
        scaledEmptyBackgroundIcon = new ImageIcon(scaledEmptyBackground);

        addButton = new ImageIcon(resourcePath + "add_button.png");
        scaledAddButton = addButton.getImage().getScaledInstance(75, 29, Image.SCALE_SMOOTH);
        scaledAddButtonIcon = new ImageIcon(scaledAddButton);

        saveButton = new ImageIcon(resourcePath + "save_workout_button.png");
        scaledsaveButton = saveButton.getImage().getScaledInstance(140, 29, Image.SCALE_SMOOTH);
        scaledsaveButtonIcon = new ImageIcon(scaledsaveButton);

        this.setSize(width, height);
        this.setBackground(new Color(31, 31, 31));
        this.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));
        init();
    }

    private void init() {
        // Main panel holding everything
        JPanel mainPanel = new JPanel();
        mainPanel.setOpaque(true);
        mainPanel.setBackground(new Color(51, 51, 51));
        mainPanel.setMaximumSize(new Dimension(getWidth() - 20, getHeight()));
        mainPanel.setPreferredSize(new Dimension(getWidth() - 20, getHeight()));
        //mainPanel.setBackground(Color.GREEN);
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.X_AXIS));

        JPanel savedWorkoutsPanel = new JPanel();
        savedWorkoutsPanel.setLayout(new BoxLayout(savedWorkoutsPanel, BoxLayout.Y_AXIS));
        savedWorkoutsPanel.setPreferredSize(new Dimension(getWidth() / 5, getHeight()));
        savedWorkoutsPanel.setMinimumSize(new Dimension(getWidth() / 5, getHeight()));
        savedWorkoutsPanel.setMaximumSize(new Dimension(getWidth() / 5, getHeight()));
        //savedWorkoutsPanel.setBackground(Color.BLUE);
        savedWorkoutsPanel.setOpaque(true);
        savedWorkoutsPanel.setBackground(new Color(51, 51, 51));

        JPanel savedWorkoutsPanelTop = new JPanel();
        savedWorkoutsPanelTop.setLayout(new BorderLayout(0, 0));
        savedWorkoutsPanelTop.setOpaque(false);
        savedWorkoutsPanelTop.setPreferredSize(new Dimension(getWidth() / 5, getHeight() / 20));
        savedWorkoutsPanelTop.setMinimumSize(new Dimension(getWidth() / 5, getHeight() / 20));
        savedWorkoutsPanelTop.setMaximumSize(new Dimension(getWidth() / 5, getHeight() / 20));

        JLabel savedWorkoutsLabel = new JLabel("Saved Workouts",JLabel.CENTER);
        savedWorkoutsLabel.setFont(new Font("Arial", Font.PLAIN, 20));
        savedWorkoutsLabel.setForeground(Color.CYAN);
        savedWorkoutsLabel.setPreferredSize(new Dimension(getWidth()/5, getHeight()/20));
        savedWorkoutsLabel.setMaximumSize(new Dimension(getWidth() / 5, getHeight()/20));
        savedWorkoutsLabel.setOpaque(false);


        JScrollPane savedWorkoutsScrollPane = new JScrollPane();
        savedWorkoutsScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        savedWorkoutsScrollPane.setPreferredSize(new Dimension(getWidth() / 5, getHeight() * 8 / 10));
        savedWorkoutsScrollPane.setMinimumSize(new Dimension(getWidth() / 5, getHeight() * 8 / 10));
        savedWorkoutsScrollPane.setMaximumSize(new Dimension(getWidth() / 5, getHeight() * 8 / 10));
        savedWorkoutsScrollPane.getVerticalScrollBar().setPreferredSize(new Dimension(0, 0));
        savedWorkoutsScrollPane.setBorder(new LineBorder(new Color(80, 73, 69), 1));

        //Label holding workout title
        JLabel headerTitle = new JLabel();
        headerTitle.setText(workoutTitle);
        headerTitle.setForeground(Color.WHITE);
        headerTitle.setFont(new Font("Arial", Font.BOLD, 22));
        headerTitle.setHorizontalAlignment(SwingConstants.CENTER);

        JPanel workoutPanel = new JPanel();
        workoutPanel.setLayout(new BoxLayout(workoutPanel, BoxLayout.Y_AXIS));
        workoutPanel.setPreferredSize(new Dimension((int) (getWidth() / 2), getHeight()));
        workoutPanel.setMinimumSize(new Dimension((int) (getWidth() / 2), getHeight()));
        workoutPanel.setMaximumSize(new Dimension((int) (getWidth() / 2), getHeight()));
        workoutPanel.setOpaque(true);
        workoutPanel.setBackground(new Color(51, 51, 51));

        JPanel workoutPanelTop = new JPanel();
        workoutPanelTop.setLayout(new BorderLayout(0, 0));
        workoutPanelTop.setOpaque(false);
        workoutPanelTop.setPreferredSize(new Dimension((int) (getWidth() / 2), getHeight() / 20));
        workoutPanelTop.setMinimumSize(new Dimension((int) (getWidth() / 2), getHeight() / 20));
        workoutPanelTop.setMaximumSize(new Dimension((int) (getWidth() / 2), getHeight() / 20));

        JButton saveWorkoutButton = new JButton(scaledsaveButtonIcon);
        saveWorkoutButton.setContentAreaFilled(false);
        saveWorkoutButton.setFocusPainted(false);
        saveWorkoutButton.setBorderPainted(false);
        saveWorkoutButton.setPreferredSize(new Dimension(scaledsaveButtonIcon.getIconWidth(), scaledsaveButtonIcon.getIconHeight()));
        saveWorkoutButton.setMaximumSize(new Dimension(scaledsaveButtonIcon.getIconWidth(), scaledsaveButtonIcon.getIconHeight()));
        saveWorkoutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame fuckyoujagvilhaenframe = new JFrame();
                fuckyoujagvilhaenframe.setSize(500, 500);
                fuckyoujagvilhaenframe.setVisible(true);
                JTextArea textArea = new JTextArea();
                textArea.setFont(new Font("Arial", Font.PLAIN, 20));
                fuckyoujagvilhaenframe.add(textArea);
                textArea.setText(currentWorkout.getData());
                textArea.setEditable(false);
                textArea.setLineWrap(true);
                textArea.setWrapStyleWord(true);
                textArea.setOpaque(false);
            }


        });

        JTextField workoutTitle = new JTextField();
        workoutTitle.setText("Workout title");
        workoutTitle.setForeground(new Color(204, 204, 204));
        workoutTitle.setBackground(new Color(22, 22, 22));
        workoutTitle.setFont(new Font("Arial", Font.PLAIN, 12));
        workoutTitle.setPreferredSize(new Dimension(getWidth() / 3, 30));
        workoutTitle.setMaximumSize(new Dimension(getWidth() / 3, 30));
        workoutTitle.setBorder(new LineBorder(new Color(80, 73, 69)));

        //Scrollable window
        JScrollPane workoutScrollPane = new JScrollPane();
        workoutScrollPane.setPreferredSize(new Dimension((int) (getWidth() / 2), getHeight() * 8 / 10));
        workoutScrollPane.setMinimumSize(new Dimension((int) (getWidth() / 2), getHeight() * 8 / 10));
        workoutScrollPane.setMaximumSize(new Dimension((int) (getWidth() / 2), getHeight() * 8 / 10));
        workoutScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        workoutScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        workoutScrollPane.getVerticalScrollBar().setPreferredSize(new Dimension(0, 0));
        workoutScrollPane.getVerticalScrollBar().setUnitIncrement(6);
        workoutScrollPane.setBorder(new LineBorder(new Color(80, 73, 69), 1));



        //Panel containing log and workout data
        JPanel logContainer = new JPanel();
        logContainer.setLayout(new BoxLayout(logContainer, BoxLayout.Y_AXIS));
        logContainer.setOpaque(true);
        logContainer.setForeground(new Color(204, 204, 204));
        logContainer.setBackground(new Color(22, 22, 22));
        logContainer.setMaximumSize(new Dimension((int) (getWidth() / 2), getHeight() * 8 / 10));
        logContainer.setPreferredSize(new Dimension((int) (getWidth() / 2), getHeight() * 8 / 10));
        workoutScrollPane.setViewportView(logContainer); //workoutScrollPane will show the content of logContainer

        //Panel to hold search and exercieses list vertically
        JPanel exercisesPanel = new JPanel();
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
        DefaultListModel<Exercise> exerciseModel = new DefaultListModel<Exercise>();
        Exercises list = new Exercises();
        for (Exercise exercise : list.getList()) {
            exerciseModel.addElement(exercise);
        }

        JList<Exercise> searchExerciseResult = new JList(exerciseModel);
        searchExerciseResult.setFixedCellHeight(26);
        searchExerciseResult.setBackground(new Color(22, 22, 22));
        searchExerciseResult.setForeground(new Color(204, 204, 204));
        searchExerciseResult.setPreferredSize(new Dimension(200, searchExerciseResult.getFixedCellHeight() * searchExerciseResult.getModel().getSize()));


        JTextField searchExercise = new JTextField();
        searchExercise.setText("Search for exercise...");
        searchExercise.setFont(new Font("Arial", Font.PLAIN, 12));
        searchExercise.setForeground(new Color(204, 204, 204));
        searchExercise.setPreferredSize(new Dimension(130, 30));
        searchExercise.setMaximumSize(new Dimension(getWidth() / 3, 30));
        searchExercise.setBackground(new Color(22, 22, 22));
        searchExercise.setBorder(new LineBorder(new Color(80, 73, 69)));
        //searchExercise.setAlignmentX(Component.LEFT_ALIGNMENT);
        searchExercise.getDocument().addDocumentListener(new DocumentListener() {

            @Override
            public void insertUpdate(DocumentEvent e) {
                filterList();
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
        JScrollPane exercisesScrollPane = new JScrollPane(searchExerciseResult);
        exercisesScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        exercisesScrollPane.setPreferredSize(new Dimension(getWidth() / 5, getHeight() * 8 / 10));
        exercisesScrollPane.setMinimumSize(new Dimension(getWidth() / 5, getHeight() * 8 / 10));
        exercisesScrollPane.setMaximumSize(new Dimension(getWidth() / 5, getHeight() * 8 / 10));
        exercisesScrollPane.getVerticalScrollBar().setPreferredSize(new Dimension(0, 0));
        exercisesScrollPane.setBorder(new LineBorder(new Color(80, 73, 69), 1));

        //Panel to hold add new exercise button and set
        JPanel addExerciseAndSetPanel = new JPanel();
        addExerciseAndSetPanel.setPreferredSize(new Dimension(getWidth() / 7, getHeight()));
        addExerciseAndSetPanel.setBackground(new Color(51, 51, 51));

        //Change workout title button
        JButton changeTitle = new JButton();
        changeTitle.setText("Change workout title");
        changeTitle.setBackground(new Color(40, 129, 201));
        changeTitle.setForeground(Color.WHITE);
        changeTitle.setVisible(true);

        //Label to display text when log is empty
        JLabel isEmpty = new JLabel();
        isEmpty.setFont(new Font("Arial", Font.ITALIC, 20));
        isEmpty.setText("No exercises added yet.");

        //"Add exercise"-button
        JButton newExcerciseButton = new JButton(scaledAddButtonIcon);
        newExcerciseButton.setContentAreaFilled(false);
        newExcerciseButton.setFocusPainted(false);
        newExcerciseButton.setBorderPainted(false);
        newExcerciseButton.setPreferredSize(new Dimension(scaledAddButtonIcon.getIconWidth(), scaledAddButtonIcon.getIconHeight()));
        newExcerciseButton.setMaximumSize(new Dimension(scaledAddButtonIcon.getIconWidth(), scaledAddButtonIcon.getIconHeight()));


        //This part generates a new exercise
        newExcerciseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Restrict from adding a non-selected exercise
                if (searchExerciseResult.getSelectedValue() == null) {
                    return;
                }
                // Display if empty log
                if (emptyLog) {
                    logContainer.removeAll();
                    emptyLog = false;
                }

                addExercise(searchExerciseResult.getSelectedValue(), logContainer);
                logContainer.revalidate();
                logContainer.repaint();
                ProgramPanel.this.revalidate();
                ProgramPanel.this.repaint();
            }

        });



        exercisesPanelTop.add(searchExercise, BorderLayout.WEST);
        exercisesPanelTop.add(newExcerciseButton, BorderLayout.EAST);
        logContainer.add(isEmpty);

        exercisesPanel.add(Box.createVerticalGlue());
        exercisesPanel.add(exercisesPanelTop);
        exercisesPanel.add(Box.createVerticalGlue());
        exercisesPanel.add(exercisesScrollPane);
        exercisesPanel.add(Box.createVerticalGlue());

        //addExerciseAndSetPanel.add(newExcerciseButton);
        addExerciseAndSetPanel.add(saveWorkoutButton);
        addExerciseAndSetPanel.add(changeTitle);

        workoutPanelTop.add(workoutTitle, BorderLayout.WEST);
        workoutPanelTop.add(saveWorkoutButton, BorderLayout.EAST);


        workoutPanel.add(Box.createVerticalGlue());
        workoutPanel.add(workoutPanelTop);
        workoutPanel.add(Box.createVerticalGlue());
        workoutPanel.add(workoutScrollPane);
        workoutPanel.add(Box.createVerticalGlue());

        savedWorkoutsPanelTop.add(savedWorkoutsLabel, BorderLayout.CENTER);


        savedWorkoutsPanel.add(Box.createVerticalGlue());
        savedWorkoutsPanel.add(savedWorkoutsPanelTop);
        savedWorkoutsPanel.add(Box.createVerticalGlue());
        savedWorkoutsPanel.add(savedWorkoutsScrollPane);
        savedWorkoutsPanel.add(Box.createVerticalGlue());

        mainPanel.add(Box.createHorizontalGlue());
        mainPanel.add(exercisesPanel);
        mainPanel.add(Box.createHorizontalGlue());
        mainPanel.add(workoutPanel);
        mainPanel.add(Box.createHorizontalGlue());
        mainPanel.add(savedWorkoutsPanel);
        mainPanel.add(Box.createHorizontalGlue());
        this.add(mainPanel);

    }

    private JPanel addExercise(Exercise currentExercise, JPanel logContainer) {
        JPanel mainExercisePanel = new JPanel();
        mainExercisePanel.setLayout(new BoxLayout(mainExercisePanel, BoxLayout.Y_AXIS));
        mainExercisePanel.setBackground(new Color(22, 22, 22));

        // Track in log
        int exerciseId = exercisePanels.size() + 1;
        exercisePanels.put(exerciseId, mainExercisePanel);
        exerciseSetCount.put(exerciseId, 0);
        idToExercise.put(currentExercise, exerciseId);


        // Panel to display exercise name
        JPanel exerciseNameTitlePanel = new JPanel();
        exerciseNameTitlePanel.setLayout(new BoxLayout(exerciseNameTitlePanel, BoxLayout.Y_AXIS));
        exerciseNameTitlePanel.setOpaque(false);

        // Label to hold name of exercise
        JLabel exerciseName = new JLabel();
        exerciseName.setPreferredSize(new Dimension(getWidth(), getHeight() / 19));
        exerciseName.setText(currentExercise.getName());
        exerciseName.setFont(new Font("Arial", Font.BOLD, 20));
        exerciseName.setForeground(new Color(204, 204, 204));
        exerciseNameTitlePanel.add(exerciseName);
        mainExercisePanel.add(exerciseNameTitlePanel);

        // Panel to hold the titles of Set, Rep, Weight, RIR
        JPanel setRepWeightRirTitleNPanel = new JPanel();
        setRepWeightRirTitleNPanel.setBackground(new Color(38, 38, 38));
        setRepWeightRirTitleNPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
        setRepWeightRirTitleNPanel.setPreferredSize(new Dimension(getWidth(), getHeight() / 19));
        setRepWeightRirTitleNPanel.setMaximumSize(new Dimension(getWidth(), getHeight() / 19));
        setRepWeightRirTitleNPanel.setOpaque(true);
        setRepWeightRirTitleNPanel.setLayout(new BorderLayout());
        mainExercisePanel.add(setRepWeightRirTitleNPanel);

        // Title Panel to align Set title to left
        JPanel leftPanel = new JPanel();
        leftPanel.setOpaque(false);
        leftPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
        leftPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        leftPanel.setPreferredSize(new Dimension(getWidth(), getHeight() / 19));
        setRepWeightRirTitleNPanel.add(leftPanel, BorderLayout.WEST);

        // Label to hold "Set"
        JLabel setLabel = new JLabel();
        setLabel.setText("Set");
        setLabel.setForeground(new Color(225, 219, 217));
        setLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        leftPanel.add(setLabel);

        //Remove exercise-button
        JButton removeExercise = new JButton();
        removeExercise.setPreferredSize(new Dimension(getWidth() / 15, getHeight() / 19));
        removeExercise.setMaximumSize(new Dimension(getWidth() / 15, getHeight() / 19));
        removeExercise.setMargin(new Insets(0, 0, 0, 0));
        removeExercise.setForeground(Color.white);
        removeExercise.setText("remove");
        removeExercise.setFont(new Font("Arial", Font.BOLD, 12));
        removeExercise.setBackground(Color.red);
        removeExercise.setBorderPainted(false);
        removeExercise.setFocusPainted(false);
        removeExercise.addActionListener(e -> {
            totalHeight -= 4 * getHeight() / 19;
            int i = 1;// For settings the numbers of the sets correctly
            for (Component comp : mainExercisePanel.getComponents()) {
                if ("setPanel".equals(comp.getName())) {
                    totalHeight -= getHeight() / 19;
                }
            }
            logContainer.setPreferredSize(new Dimension(logContainer.getWidth(), totalHeight));
            currentWorkout.deleteExercise(exerciseId);
            mainExercisePanel.removeAll();
            logContainer.repaint();
            logContainer.revalidate();


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
        repsLabel.setText("Reps");
        repsLabel.setForeground(new Color(225, 219, 217));
        repsLabel.setAlignmentX(Component.RIGHT_ALIGNMENT);
        rightPanel.add(repsLabel);

        // Label to hold "Weight"
        JLabel weightLabel = new JLabel();
        weightLabel.setText("Weight");
        weightLabel.setForeground(new Color(225, 219, 217));
        weightLabel.setAlignmentX(Component.RIGHT_ALIGNMENT);
        rightPanel.add(weightLabel);
        //Label to hold "RIR"
        JLabel rirLabel = new JLabel();
        rirLabel.setText("RIR");
        rirLabel.setForeground(new Color(225, 219, 217));
        rirLabel.setAlignmentX(Component.RIGHT_ALIGNMENT);
        rightPanel.add(rirLabel);

        // "Add set"- button
        JButton addSet = new JButton();
        addSet.setPreferredSize(new Dimension(getWidth() / 35, getHeight() / 19));
        addSet.setMaximumSize(new Dimension(getWidth() / 35, getHeight() / 19));
        addSet.setMargin(new Insets(0, 0, 0, 0));
        addSet.setForeground(Color.white);
        addSet.setText("+");
        addSet.setFont(new Font("Arial", Font.BOLD, 12));
        addSet.setBackground(new Color(40, 129, 201));
        addSet.setBorderPainted(false);
        addSet.setFocusPainted(false);

        totalHeight += 4 * getHeight() / 19; //Lägger till höjden för de 4 paneler som skapas när en övning läggs till

        addSet.addActionListener(e -> {
            totalHeight += getHeight() / 19; //Lägger till höjden settet som läggs till
            addSet(exerciseId, currentExercise, mainExercisePanel, getHeight() / 19, logContainer);
            logContainer.setPreferredSize(new Dimension(logContainer.getWidth(), totalHeight));
            logContainer.revalidate();
            logContainer.repaint();

        });

        exerciseNameTitlePanel.add(removeExercise);
        mainExercisePanel.add(addSet);
        ProgramPanel.this.revalidate();
        ProgramPanel.this.repaint();
        logContainer.add(mainExercisePanel);
        logContainer.setPreferredSize(new Dimension(logContainer.getWidth(), totalHeight));
        logContainer.revalidate();
        logContainer.repaint();

        return logContainer;
    }

    public void addSet(int exerciseId, Exercise currentExercise, JPanel mainExercisePanel, int heightToRemove, JPanel logContainer) {

        WorkoutSet workoutSet = new WorkoutSet();
        workoutSet.setExercise(currentExercise);
        currentWorkout.addSet(exerciseId, workoutSet);

        JPanel setPanel = new JPanel();
        setPanels.put(exerciseId, setPanel);
        setPanel.setName("setPanel");

        workoutSet.setNumber(currentWorkout.getSetSize(exerciseId));


        JPanel parentPanel = exercisePanels.get(exerciseId);
        setPanel.setOpaque(true);
        setPanel.setLayout(new BorderLayout());
        setPanel.setBackground(new Color(22, 22, 22));
        setPanel.setMaximumSize(new Dimension(getWidth(), getHeight() / 19));
        setPanel.setPreferredSize(new Dimension(getWidth(), getHeight() / 19));
        setPanel.setAlignmentX(SwingConstants.CENTER);

        JPanel leftPanel = new JPanel();
        leftPanel.setOpaque(false);
        leftPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        leftPanel.setName("leftPanel");
        setPanel.add(leftPanel, BorderLayout.WEST);


        JLabel setLabel = new JLabel();
        setLabel.setName("setLabel");
        setLabel.setText(currentWorkout.getSetSize(exerciseId) + ".");
        setLabel.setForeground(new Color(204, 204, 204));
        leftPanel.add(setLabel);


        JButton deleteSet = new JButton();
        deleteSet.setBackground(Color.RED);
        deleteSet.setForeground(Color.WHITE);
        deleteSet.setText("-");
        deleteSet.setPreferredSize(new Dimension(getWidth() / 45, getHeight() / 19));
        deleteSet.setMaximumSize(new Dimension(getWidth() / 45, getHeight() / 19));
        deleteSet.setFont(new Font("Arial", Font.BOLD, 12));
        deleteSet.setBorderPainted(false);
        deleteSet.setFocusPainted(false);
        deleteSet.setMargin(new Insets(0, 0, 0, 0));

        // delete set & update log
        deleteSet.addActionListener(e -> {
            deleteSet(parentPanel, exerciseId, workoutSet, logContainer, heightToRemove, setPanel);

        });

        JButton moveSetUp = new JButton();
        moveSetUp.setText("↑");
        moveSetUp.setBackground(new Color(40, 129, 201));
        moveSetUp.setForeground(Color.white);
        moveSetUp.setMargin(new Insets(0, 0, 0, 0));

        JButton moveSetDown = new JButton();
        moveSetDown.setText("↓");
        moveSetDown.setBackground(new Color(40, 129, 201));
        moveSetDown.setForeground(Color.white);
        moveSetDown.setMargin(new Insets(0, 0, 0, 0));

        moveSetDown.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                moveDown(parentPanel, exerciseId, workoutSet);
            }
        });


        // move the set up
        moveSetUp.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                moveUp(parentPanel, exerciseId, workoutSet);
            }
        });

        JPanel rightPanel = new JPanel();
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

        JTextField weightAmount = new JTextField();
        weightAmount.setBackground(new Color(22, 22, 22));
        weightAmount.setForeground(new Color(204, 204, 204));
        weightAmount.setPreferredSize(new Dimension(35, 20));
        rightPanel.add(weightAmount);
        weightAmount.setBorder(null);
        weightAmount.setText("0");
        weightAmount.setBorder(new LineBorder(new Color(129, 115, 103), 1));

        JTextField rirAmount = new JTextField();
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
        repsAmount.addActionListener(e -> {
            if (repsAmount.getText().isEmpty()) repsAmount.setText("0");
            repsAmount.setBackground(new Color(217, 217, 217));
            finalWorkoutSet.setReps(Integer.parseInt(repsAmount.getText()));
            currentWorkout.addSet(exerciseId, finalWorkoutSet);
        });

        weightAmount.addActionListener(e -> {
            if (weightAmount.getText().isEmpty()) weightAmount.setText("0");
            weightAmount.setBackground(new Color(217, 217, 217));
            finalWorkoutSet.setWeight(Integer.parseInt(weightAmount.getText()));
            currentWorkout.addSet(exerciseId, finalWorkoutSet);
        });

        rirAmount.addActionListener(e -> {
            if (rirAmount.getText().isEmpty()) rirAmount.setText("0");
            rirAmount.setBackground(new Color(217, 217, 217));
            finalWorkoutSet.setRir(Integer.parseInt(rirAmount.getText()));
            currentWorkout.addSet(exerciseId, finalWorkoutSet);
        });
    }

    public void moveUp(JPanel parentPanel, int exerciseId, WorkoutSet workoutSet) {

        currentWorkout.moveSetUp(exerciseId, workoutSet);
        ArrayList<JPanel> temp = new ArrayList<>();
        HashMap<Integer, List<WorkoutSet>> updatedSet = currentWorkout.getExerciseSets();
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
                                            System.out.println("Added set: " + setLabel.getText().replace(".", ""));

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
                for (Map.Entry<Integer, List<WorkoutSet>> w : currentWorkout.getExerciseSets().entrySet()) {
                    int y = 1;
                    for (WorkoutSet z : w.getValue()) {
                        z.setNumber(y);
                        y++;
                    }
                }
                parentPanel.add(panel);
            }
        }


        ProgramPanel.this.revalidate();
        ProgramPanel.this.repaint();
        parentPanel.repaint();
        parentPanel.revalidate();

    }

    public void moveDown(JPanel parentPanel, int exerciseId, WorkoutSet workoutSet) {

        currentWorkout.moveSetDown(exerciseId, workoutSet, currentWorkout);

        //currentWorkout.moveSetUp(exerciseId, workoutSet);
        ArrayList<JPanel> temp = new ArrayList<>();
        HashMap<Integer, List<WorkoutSet>> updatedSet = currentWorkout.getExerciseSets();
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

                                    if (workoutSet.getNumber() - 1 != currentWorkout.getSetSize(exerciseId) - 1) {

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
                                    i++;
                                }
                            }
                        }
                    }
                }
            }
        }

        if (workoutSet.getNumber() - 1 != currentWorkout.getSetSize(exerciseId) - 1) {
            for (Component comp : parentPanel.getComponents()) {
                if ("setPanel".equals(comp.getName())) {
                    parentPanel.remove(comp);
                }
            }
            Collections.swap(temp, workoutSet.getNumber() - 1, workoutSet.getNumber());
            for (JPanel panel : temp) {
                for (Map.Entry<Integer, List<WorkoutSet>> w : currentWorkout.getExerciseSets().entrySet()) {
                    int y = 1;
                    for (WorkoutSet z : w.getValue()) {
                        z.setNumber(y);
                        y++;
                    }
                }
                parentPanel.add(panel);
            }
        }


        ProgramPanel.this.revalidate();
        ProgramPanel.this.repaint();
        parentPanel.repaint();
        parentPanel.revalidate();

    }

    public void deleteSet(JPanel parentPanel, int exerciseId, WorkoutSet workoutSet, JPanel logContainer, int heightToRemove, JPanel setPanel) {
        parentPanel.remove(setPanel);
        int i = 0;
        // Delete the set from WorkoutData
        currentWorkout.deleteSet(exerciseId, workoutSet.getNumber());
        HashMap<Integer, List<WorkoutSet>> updatedSet = currentWorkout.getExerciseSets();
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
        totalHeight -= heightToRemove;
        logContainer.setPreferredSize(new Dimension(logContainer.getWidth(), totalHeight));
        ProgramPanel.this.repaint();
        ProgramPanel.this.revalidate();
        parentPanel.revalidate();
        parentPanel.repaint();

    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        // Draw the image to fill the entire panel
        if (emptyBackground != null) {
            g.drawImage(scaledEmptyBackgroundIcon.getImage(), 0, 0, getWidth(), getHeight(), this);
        }
    }
}




