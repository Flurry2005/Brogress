package se.aljr.application.programplanner;

import org.checkerframework.checker.units.qual.A;
import se.aljr.application.exercise.Excercise.Exercise;
import se.aljr.application.exercise.Program.Exercises;
import se.aljr.application.loginpage.FirebaseManager;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.util.*;
import java.util.List;

public class ProgramPanel extends JPanel {
    public static int totalHeight;
    public static int programPanelHeight;
    public static int programPanelWidth;
    private Workout workoutContainer;
    public static int setPanelHeight;
    private JLabel headerTitle;

    private String resourcePath;
    private static boolean emptyLog;
    private ImageIcon emptyBackground;
    private Image scaledEmptyBackground;
    private ImageIcon scaledEmptyBackgroundIcon;
    private ImageIcon addButton;
    private Image scaledAddButton;
    private ImageIcon scaledAddButtonIcon;

    private ImageIcon saveButton;
    private Image scaledsaveButton;
    private ImageIcon scaledsaveButtonIcon;


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

        setPanelHeight = getHeight() / 19;

        programPanelHeight = getHeight();
        programPanelWidth = getWidth();
        // Main panel holding everything
        JPanel mainPanel = new JPanel();
        mainPanel.setOpaque(true);
        mainPanel.setBackground(new Color(51, 51, 51));
        mainPanel.setMaximumSize(new Dimension(getWidth() - 20, getHeight()));
        mainPanel.setPreferredSize(new Dimension(getWidth() - 20, getHeight()));
        //mainPanel.setBackground(Color.GREEN);
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.X_AXIS));

        //Scrollable window
        JScrollPane workoutScrollPane = new JScrollPane();
        workoutScrollPane.setPreferredSize(new Dimension((int) (getWidth() / 2), getHeight() * 8 / 10));
        workoutScrollPane.setMinimumSize(new Dimension((int) (getWidth() / 2), getHeight() * 8 / 10));
        workoutScrollPane.setMaximumSize(new Dimension((int) (getWidth() / 2), getHeight() * 8 / 10));
        workoutScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        workoutScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        workoutScrollPane.getVerticalScrollBar().setPreferredSize(new Dimension(0, 0));
        workoutScrollPane.getVerticalScrollBar().setUnitIncrement(6);
        workoutScrollPane.getViewport().setBackground(new Color(22, 22, 22));
        workoutScrollPane.setBorder(new LineBorder(new Color(80, 73, 69), 1));


        //Label to display text when log is empty
        JLabel isEmpty = new JLabel();


        //Panel containing log and workout data
        WorkoutsList workoutsList = FirebaseManager.readDBworkout(this);
        if(workoutsList.size()==0){
            workoutsList.add(new Workout());

        }
        workoutContainer = workoutsList.get(0);
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

        JLabel savedWorkoutsLabel = new JLabel("Workouts");
        savedWorkoutsLabel.setFont(new Font("Arial", Font.PLAIN, 15));
        savedWorkoutsLabel.setForeground(Color.CYAN);
        savedWorkoutsLabel.setPreferredSize(new Dimension(getWidth() / 5, getHeight() / 20));
        savedWorkoutsLabel.setMaximumSize(new Dimension(getWidth() / 5, getHeight() / 20));
        savedWorkoutsLabel.setOpaque(false);


        // Select saved workouts
        DefaultListModel<String> workoutTitleDefaultListModel = new DefaultListModel<>();

        DefaultListModel<Workout> workoutDefaultListModel = new DefaultListModel<>();
        for (Workout workout : workoutsList) {
            workoutDefaultListModel.addElement(workout);
            workoutTitleDefaultListModel.addElement(workout.getWorkoutData().getTitle());
        }

        JList<String> savedWorkoutsList = new JList<>(workoutTitleDefaultListModel);
        savedWorkoutsList.setForeground(Color.WHITE);
        savedWorkoutsList.setBackground(new Color(22,22,22));
        savedWorkoutsList.setSelectedIndex(0);
        savedWorkoutsList.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {

                if(savedWorkoutsList.getSelectedIndex()!=-1){
                    Workout target = workoutDefaultListModel.getElementAt(savedWorkoutsList.getSelectedIndex());
                    workoutContainer = target;

                    headerTitle.setText(target.getWorkoutData().getTitle());

                    if (workoutContainer.getComponentCount() == 0) {
                        emptyLog = true;
                        isEmpty.setFont(new Font("Arial", Font.ITALIC, 20));
                        isEmpty.setText("No exercises added yet.");
                        workoutContainer.add(isEmpty);
                    }
                    workoutContainer.setLayout(new BoxLayout(workoutContainer, BoxLayout.Y_AXIS));
                    workoutContainer.setOpaque(false);
                    workoutContainer.setForeground(new Color(204, 204, 204));
                    workoutContainer.setBackground(new Color(22, 22, 22));
                    workoutContainer.setPreferredSize(new Dimension(target.getWidth(),target.getHeight()));
                    workoutContainer.setMinimumSize(target.getPreferredSize());
                    workoutContainer.setMaximumSize(target.getPreferredSize());

                    workoutScrollPane.setViewportView(workoutContainer);
                }


            }


        });

        JButton newWorkoutButton = new JButton("New workout");
        newWorkoutButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                Workout newWorkout = new Workout();
                workoutsList.add(newWorkout);
                workoutDefaultListModel.addElement(newWorkout);
                workoutTitleDefaultListModel.addElement(newWorkout.getWorkoutData().getTitle());


            }
        });

        JScrollPane savedWorkoutsScrollPane = new JScrollPane();
        savedWorkoutsScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        savedWorkoutsScrollPane.setPreferredSize(new Dimension(getWidth() / 5, getHeight() * 8 / 10));
        savedWorkoutsScrollPane.setMinimumSize(new Dimension(getWidth() / 5, getHeight() * 8 / 10));
        savedWorkoutsScrollPane.setMaximumSize(new Dimension(getWidth() / 5, getHeight() * 8 / 10));
        savedWorkoutsScrollPane.getVerticalScrollBar().setPreferredSize(new Dimension(0, 0));
        savedWorkoutsScrollPane.setBorder(new LineBorder(new Color(80, 73, 69), 1));
        savedWorkoutsScrollPane.getViewport().setBackground(new Color(22, 22, 22));
        savedWorkoutsScrollPane.setViewportView(savedWorkoutsList);

        //Label holding workout title
        headerTitle = new JLabel();
        headerTitle.setText("Untitled Workout");
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

        JTextField workoutTitle = new JTextField();
        workoutTitle.setText("Workout title");
        workoutTitle.setForeground(new Color(204, 204, 204));
        workoutTitle.setBackground(new Color(22, 22, 22));
        workoutTitle.setFont(new Font("Arial", Font.PLAIN, 12));
        workoutTitle.setPreferredSize(new Dimension(getWidth() / 3, 30));
        workoutTitle.setMaximumSize(new Dimension(getWidth() / 3, 30));
        workoutTitle.setBorder(new LineBorder(new Color(80, 73, 69)));

        JButton saveWorkoutButton = new JButton(scaledsaveButtonIcon);
        saveWorkoutButton.setContentAreaFilled(false);
        saveWorkoutButton.setFocusPainted(false);
        saveWorkoutButton.setBorderPainted(false);
        saveWorkoutButton.setPreferredSize(new Dimension(scaledsaveButtonIcon.getIconWidth(), scaledsaveButtonIcon.getIconHeight()));
        saveWorkoutButton.setMaximumSize(new Dimension(scaledsaveButtonIcon.getIconWidth(), scaledsaveButtonIcon.getIconHeight()));
        saveWorkoutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {

                    System.out.println("name before : "+workoutDefaultListModel.get(savedWorkoutsList.getSelectedIndex()).getWorkoutData().getTitle());

                    int index =  savedWorkoutsList.getSelectedIndex();
                    //Uppdaterar namnen på övningarna i listan

                    workoutDefaultListModel.get(savedWorkoutsList.getSelectedIndex()).getWorkoutData().setTitle(workoutTitle.getText().trim());

                    System.out.println("name before : "+workoutDefaultListModel.get(savedWorkoutsList.getSelectedIndex()).getWorkoutData().getTitle());

                    workoutTitleDefaultListModel.clear();

                    for (Workout workout : workoutsList) {
                        workoutTitleDefaultListModel.addElement(workout.getWorkoutData().getTitle());
                    }
                    savedWorkoutsList.setModel(workoutTitleDefaultListModel);
                    System.out.println(savedWorkoutsList.getSelectedIndex()+ " index after");
                    savedWorkoutsList.setSelectedIndex(index);
                    FirebaseManager.writeDBworkout(workoutsList);
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }


        });



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
                    workoutContainer.removeAll();
                    emptyLog = false;
                }

                addExercise(searchExerciseResult.getSelectedValue(), workoutContainer);
                workoutContainer.revalidate();
                workoutContainer.repaint();
                ProgramPanel.this.revalidate();
                ProgramPanel.this.repaint();
            }

        });


        exercisesPanelTop.add(searchExercise, BorderLayout.WEST);
        exercisesPanelTop.add(newExcerciseButton, BorderLayout.EAST);


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

        savedWorkoutsPanelTop.add(savedWorkoutsLabel, BorderLayout.WEST);
        savedWorkoutsPanelTop.add(newWorkoutButton, BorderLayout.EAST);


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

    private JPanel addExercise(Exercise currentExercise, Workout workoutContainer) {
        JPanel mainExercisePanel = new JPanel();
        mainExercisePanel.setName("mainExercisePanel");
        mainExercisePanel.setLayout(new BoxLayout(mainExercisePanel, BoxLayout.Y_AXIS));
        mainExercisePanel.setBackground(new Color(22, 22, 22));

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
        exerciseName.setForeground(new Color(204, 204, 204));
        exerciseNameTitlePanel.add(exerciseName);
        mainExercisePanel.add(exerciseNameTitlePanel);

        // Panel to hold the titles of Set, Rep, Weight, RIR
        JPanel setRepWeightRirTitleNPanel = new JPanel();
        setRepWeightRirTitleNPanel.setBackground(new Color(38, 38, 38));
        setRepWeightRirTitleNPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
        setRepWeightRirTitleNPanel.setPreferredSize(new Dimension(getWidth(), setPanelHeight));
        setRepWeightRirTitleNPanel.setMinimumSize(setRepWeightRirTitleNPanel.getPreferredSize());
        setRepWeightRirTitleNPanel.setMaximumSize(setRepWeightRirTitleNPanel.getPreferredSize());
        setRepWeightRirTitleNPanel.setOpaque(true);
        setRepWeightRirTitleNPanel.setLayout(new BorderLayout());
        mainExercisePanel.add(setRepWeightRirTitleNPanel);

        // Title Panel to align Set title to left
        JPanel leftPanel = new JPanel();
        leftPanel.setOpaque(false);
        leftPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
        leftPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        leftPanel.setPreferredSize(new Dimension(getWidth(), setPanelHeight));
        leftPanel.setMinimumSize(leftPanel.getPreferredSize());
        leftPanel.setMaximumSize(leftPanel.getPreferredSize());
        setRepWeightRirTitleNPanel.add(leftPanel, BorderLayout.WEST);

        // Label to hold "Set"
        JLabel setLabel = new JLabel();
        setLabel.setText("Set");
        setLabel.setForeground(new Color(225, 219, 217));
        setLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        leftPanel.add(setLabel);

        //Remove exercise-button
        JButton removeExercise = new JButton();
        removeExercise.setName("removeExercise");
        removeExercise.setPreferredSize(new Dimension(getWidth() / 15, setPanelHeight));
        removeExercise.setMinimumSize(removeExercise.getPreferredSize());
        removeExercise.setMaximumSize(removeExercise.getPreferredSize());
        removeExercise.setMargin(new Insets(0, 0, 0, 0));
        removeExercise.setForeground(Color.white);
        removeExercise.setText("remove");
        removeExercise.setFont(new Font("Arial", Font.BOLD, 12));
        removeExercise.setBackground(Color.red);
        removeExercise.setBorderPainted(false);
        removeExercise.setFocusPainted(false);
        removeExercise.addActionListener(e -> {
            workoutContainer.getWorkoutData().setTotalWorkoutHeight(workoutContainer.getWorkoutData().getTotalWorkoutHeight()-(4 * setPanelHeight));
            int i = 1;// For settings the numbers of the sets correctly
            for (Component comp : mainExercisePanel.getComponents()) {
                if ("setPanel".equals(comp.getName())) {
                    workoutContainer.getWorkoutData().setTotalWorkoutHeight(workoutContainer.getWorkoutData().getTotalWorkoutHeight()-( setPanelHeight));
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
        addSet.setName("addSet");
        addSet.setPreferredSize(new Dimension(getWidth() / 35, setPanelHeight));
        addSet.setMinimumSize(addSet.getPreferredSize());
        addSet.setMaximumSize(new Dimension(addSet.getPreferredSize()));
        addSet.setMargin(new Insets(0, 0, 0, 0));
        addSet.setForeground(Color.white);
        addSet.setText("+");
        addSet.setFont(new Font("Arial", Font.BOLD, 12));
        addSet.setBackground(new Color(40, 129, 201));
        addSet.setBorderPainted(false);
        addSet.setFocusPainted(false);

        workoutContainer.getWorkoutData().setTotalWorkoutHeight(workoutContainer.getWorkoutData().getTotalWorkoutHeight()+(4 * setPanelHeight)); //Lägger till höjden för de 4 paneler som skapas när en övning läggs till

        addSet.addActionListener(e -> {
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

        return workoutContainer;
    }

    public static void addSet(int exerciseId, Exercise currentExercise, JPanel mainExercisePanel, int heightToRemove, Workout workoutContainer) {

        workoutContainer.getWorkoutData().setTotalWorkoutHeight(workoutContainer.getWorkoutData().getTotalWorkoutHeight()+(setPanelHeight)); //Lägger till höjden settet som läggs till

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
        setPanel.setBackground(new Color(22, 22, 22));
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
        setLabel.setForeground(new Color(204, 204, 204));
        leftPanel.add(setLabel);


        JButton deleteSet = new JButton();
        deleteSet.setName("deleteSet");
        deleteSet.setBackground(Color.RED);
        deleteSet.setForeground(Color.WHITE);
        deleteSet.setText("-");
        deleteSet.setPreferredSize(new Dimension(ProgramPanel.programPanelWidth / 45, setPanelHeight));
        deleteSet.setMinimumSize(deleteSet.getPreferredSize());
        deleteSet.setMaximumSize(deleteSet.getPreferredSize());
        deleteSet.setFont(new Font("Arial", Font.BOLD, 12));
        deleteSet.setBorderPainted(false);
        deleteSet.setFocusPainted(false);
        deleteSet.setMargin(new Insets(0, 0, 0, 0));

        // delete set & update log
        deleteSet.addActionListener(e -> {
            deleteSet(parentPanel, exerciseId, workoutSet, workoutContainer, heightToRemove, setPanel, workoutContainer);

        });

        JButton moveSetUp = new JButton();
        moveSetUp.setName("moveSetUp");
        moveSetUp.setText("↑");
        moveSetUp.setBackground(new Color(40, 129, 201));
        moveSetUp.setForeground(Color.white);
        moveSetUp.setMargin(new Insets(0, 0, 0, 0));

        JButton moveSetDown = new JButton();
        moveSetDown.setName("moveSetDown");
        moveSetDown.setText("↓");
        moveSetDown.setBackground(new Color(40, 129, 201));
        moveSetDown.setForeground(Color.white);
        moveSetDown.setMargin(new Insets(0, 0, 0, 0));

        moveSetDown.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                moveDown(parentPanel, exerciseId, workoutSet, workoutContainer);
            }
        });


        // move the set up
        moveSetUp.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                moveUp(parentPanel, exerciseId, workoutSet, workoutContainer);
            }
        });

        JPanel rightPanel = new JPanel();
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
            workoutContainer.getWorkoutData().addSet(exerciseId, finalWorkoutSet);
        });

        weightAmount.addActionListener(e -> {
            if (weightAmount.getText().isEmpty()) weightAmount.setText("0");
            weightAmount.setBackground(new Color(217, 217, 217));
            finalWorkoutSet.setWeight(Integer.parseInt(weightAmount.getText()));
            workoutContainer.getWorkoutData().addSet(exerciseId, finalWorkoutSet);
        });

        rirAmount.addActionListener(e -> {
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

        //currentWorkout.moveSetUp(exerciseId, workoutSet);
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
                                    i++;
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
        workoutContainer.getWorkoutData().setTotalWorkoutHeight(workoutContainer.getWorkoutData().getTotalWorkoutHeight()-(setPanelHeight));
        logContainer.setPreferredSize(new Dimension(logContainer.getWidth(), workoutContainer.getWorkoutData().getTotalWorkoutHeight()));
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




