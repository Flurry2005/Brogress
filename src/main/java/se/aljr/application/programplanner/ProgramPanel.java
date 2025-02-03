package se.aljr.application.programplanner;

import se.aljr.application.exercise.Excercise.Exercise;
import se.aljr.application.exercise.Program.Exercises;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.*;
import java.util.HashMap;
import java.util.Map;

public class ProgramPanel extends JPanel {
    private static int totalHeight;
    private WorkoutData currentWorkout = new WorkoutData();
    private String workoutTitle = currentWorkout.getName();
    private static boolean emptyLog = true;
    Map<Exercise, Integer> idToExercise = new HashMap<>();
    Map<Integer, JPanel> exercisePanels = new HashMap<>();
    Map<Integer, JPanel> setPanels = new HashMap<>();
    Map<Integer, Integer> exerciseSetCount = new HashMap<>();

    public ProgramPanel(int width, int height) {
        this.setSize(width, height);
        this.setBackground(Color.WHITE);
        this.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));
        init();
    }

    private void init() {
        // Main panel holding everything
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setOpaque(true);
        mainPanel.setBackground(Color.WHITE);
        mainPanel.setMaximumSize(new Dimension(getWidth(), getHeight()));
        mainPanel.setPreferredSize(new Dimension(getWidth(), getHeight()));

        //Panel holding the name of the workout
        JPanel headerPanel = new JPanel();
        headerPanel.setOpaque(false);
        headerPanel.setMaximumSize(new Dimension(getWidth(), getHeight() / 10));
        headerPanel.setPreferredSize(new Dimension(getWidth(), getHeight() / 10));
        headerPanel.setBackground(new Color(245, 245, 245));

        //Label holding workout title
        JLabel headerTitle = new JLabel();
        headerTitle.setText(workoutTitle);
        headerTitle.setForeground(Color.BLACK);
        headerTitle.setFont(new Font("Arial", Font.BOLD, 22));
        headerTitle.setHorizontalAlignment(SwingConstants.CENTER);

        //Scrollable window
        JScrollPane workoutScrollPane = new JScrollPane();
        workoutScrollPane.setPreferredSize(new Dimension(getWidth(), getHeight() / 2));
        workoutScrollPane.setMaximumSize(new Dimension(getWidth(), getHeight() / 2));
        workoutScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        workoutScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        workoutScrollPane.getVerticalScrollBar().setPreferredSize(new Dimension(0, 0));
        workoutScrollPane.getVerticalScrollBar().setUnitIncrement(6);

        //Panel containing log and workout data
        JPanel logContainer = new JPanel();
        logContainer.setLayout(new BoxLayout(logContainer, BoxLayout.Y_AXIS));
        logContainer.setOpaque(true);
        logContainer.setBackground(Color.WHITE);
        logContainer.setMaximumSize(new Dimension(getWidth() - 20, getHeight() / 10));
        logContainer.setPreferredSize(new Dimension(getWidth() - 20, getHeight() / 10));
        workoutScrollPane.setViewportView(logContainer); //workoutScrollPane will show the content of logContainer

        // Button panel to hold search and list horizontally
        JPanel exercisesPanel = new JPanel();
        exercisesPanel.setLayout(new BoxLayout(exercisesPanel, BoxLayout.Y_AXIS));
        exercisesPanel.setOpaque(true);

        //Search and add exercise
        DefaultListModel<Exercise> exerciseModel = new DefaultListModel<Exercise>();
        Exercises list = new Exercises();
        for (Exercise exercise : list.getList()) {
            exerciseModel.addElement(exercise);
        }

        JList<Exercise> searchExerciseResult = new JList(exerciseModel);
        searchExerciseResult.setFixedCellHeight(20);
        searchExerciseResult.setPreferredSize(new Dimension(200, searchExerciseResult.getFixedCellHeight() * searchExerciseResult.getModel().getSize()));

        JTextField searchExercise = new JTextField();
        searchExercise.setText("Search for exercise...");
        searchExercise.setFont(new Font("Arial", Font.PLAIN, 12));
        searchExercise.setPreferredSize(new Dimension(200, 30));
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
        JScrollPane scrollPane2 = new JScrollPane(searchExerciseResult);
        exercisesPanel.add(scrollPane2);
        scrollPane2.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane2.getVerticalScrollBar().setPreferredSize(new Dimension(0, 0));

        //Panel to hold add new exercise button and set
        JPanel addExerciseAndSetPanel = new JPanel();
        addExerciseAndSetPanel.setBackground(new Color(245, 245, 245));

        //Change workout title button
        JButton changeTitle = new JButton();
        changeTitle.setText("Change workout title");
        changeTitle.setBackground(new Color(40, 129, 201));
        ;
        changeTitle.setForeground(Color.WHITE);
        changeTitle.setVisible(true);

        //Label to display text when log is empty
        JLabel isEmpty = new JLabel();
        isEmpty.setFont(new Font("Arial", Font.ITALIC, 20));
        isEmpty.setText("No exercises added yet.");

        //"Add exercise"-button
        JButton newExcerciseButton = new JButton();
        newExcerciseButton.setBackground(new Color(40, 129, 201));
        newExcerciseButton.setText("[+] Exercise");
        newExcerciseButton.setForeground(Color.WHITE);

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
        //Save workoutbutton
        JButton saveWorkoutButton = new JButton();
        saveWorkoutButton.setText("Save workout");
        saveWorkoutButton.setBackground(new Color(40, 129, 201));
        saveWorkoutButton.setForeground(Color.WHITE);
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

        mainPanel.add(headerPanel);
        headerPanel.add(headerTitle, SwingConstants.CENTER);
        mainPanel.add(workoutScrollPane);
        logContainer.add(isEmpty);
        exercisesPanel.add(searchExercise);
        addExerciseAndSetPanel.add(newExcerciseButton);
        addExerciseAndSetPanel.add(saveWorkoutButton);
        addExerciseAndSetPanel.add(changeTitle);
        mainPanel.add(exercisesPanel);
        mainPanel.add(addExerciseAndSetPanel);
        this.add(mainPanel);

    }

    private JPanel addExercise(Exercise currentExercise, JPanel logContainer) {
        JPanel mainExercisePanel = new JPanel();
        mainExercisePanel.setLayout(new BoxLayout(mainExercisePanel, BoxLayout.Y_AXIS));
        mainExercisePanel.setBackground(Color.WHITE);

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
        exerciseName.setPreferredSize(new Dimension(getWidth(),getHeight()/19));
        exerciseName.setText(currentExercise.getName());
        exerciseName.setFont(new Font("Arial", Font.BOLD, 20));
        exerciseNameTitlePanel.add(exerciseName);
        mainExercisePanel.add(exerciseNameTitlePanel);

        // Panel to hold the titles of Set, Rep, Weight, RIR
        JPanel setRepWeightRirTitleNPanel = new JPanel();
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
        leftPanel.setPreferredSize(new Dimension(getWidth(),getHeight()/19));
        setRepWeightRirTitleNPanel.add(leftPanel, BorderLayout.WEST);

        // Label to hold "Set"
        JLabel setLabel = new JLabel();
        setLabel.setText("Set");
        setLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        leftPanel.add(setLabel);

        //Remove exercise-button
        JButton removeExercise = new JButton();
        removeExercise.setPreferredSize(new Dimension(getWidth()/15,getHeight()/19));
        removeExercise.setMaximumSize(new Dimension(getWidth()/15,getHeight()/19));
        removeExercise.setMargin(new Insets(0, 0, 0, 0));
        removeExercise.setForeground(Color.white);
        removeExercise.setText("remove");
        removeExercise.setFont(new Font("Arial", Font.BOLD, 12));
        removeExercise.setBackground(Color.red);
        removeExercise.setBorderPainted(false);
        removeExercise.setFocusPainted(false);
        removeExercise.addActionListener(e -> {
            totalHeight-=4*getHeight()/19;
            int i = 1;// For settings the numbers of the sets correctly
            for(Component comp : mainExercisePanel.getComponents()){
                if("setPanel".equals(comp.getName())){
                    totalHeight-=getHeight()/19;
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
        rightPanel.setOpaque(false);
        rightPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
        rightPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
        setRepWeightRirTitleNPanel.add(rightPanel, BorderLayout.EAST);

        // Label to hold "Reps"
        JLabel repsLabel = new JLabel();
        repsLabel.setText("Reps");
        repsLabel.setAlignmentX(Component.RIGHT_ALIGNMENT);
        rightPanel.add(repsLabel);

        // Label to hold "Weight"
        JLabel weightLabel = new JLabel();
        weightLabel.setText("Weight");
        weightLabel.setAlignmentX(Component.RIGHT_ALIGNMENT);
        rightPanel.add(weightLabel);
        //Label to hold "RIR"
        JLabel rirLabel = new JLabel();
        rirLabel.setText("RIR");
        rirLabel.setAlignmentX(Component.RIGHT_ALIGNMENT);
        rightPanel.add(rirLabel);

        // "Add set"- button
        JButton addSet = new JButton();
        addSet.setPreferredSize(new Dimension(getWidth()/35,getHeight()/19));
        addSet.setMaximumSize(new Dimension(getWidth()/35,getHeight()/19));
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
            addSet(exerciseId, currentExercise, mainExercisePanel,getHeight()/19, logContainer);
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
        setPanel.setBackground(Color.WHITE);
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
        leftPanel.add(setLabel);
        setPanel.add(leftPanel, BorderLayout.WEST);

        JButton deleteSet = new JButton();
        deleteSet.setBackground(Color.RED);
        deleteSet.setForeground(Color.WHITE);
        deleteSet.setText("x");
        deleteSet.setFont(new Font("Arial", Font.BOLD, 8));
        deleteSet.setBorderPainted(false);
        deleteSet.setFocusPainted(false);
        deleteSet.setMargin(new Insets(0, 0, 0, 0));
        leftPanel.add(deleteSet);

        // delete set
        deleteSet.addActionListener(e -> {
            System.out.println("Deleting a set and putting the correct number.");
            currentWorkout.deleteSet(exerciseId,workoutSet.getNumber());
            parentPanel.remove(setPanel);
            int i = 1;// For settings the numbers of the sets correctly
            for(Component comp : parentPanel.getComponents()){
                if("setPanel".equals(comp.getName())){
                    JPanel compSetPanel = (JPanel) comp;
                    for(Component comp1 : compSetPanel.getComponents()){
                        if("leftPanel".equals(comp1.getName())){
                            JPanel compLeftPanel = (JPanel) comp1;
                            for(Component comp2 : compLeftPanel.getComponents()){
                                if("setLabel".equals(comp2.getName())){
                                    JLabel setLabelRe = (JLabel) comp2;
                                    setLabelRe.setText(i + ".");
                                    i++;
                                }

                            }
                        }
                    }
                }
            }
            totalHeight-=heightToRemove;
            logContainer.setPreferredSize(new Dimension(logContainer.getWidth(), totalHeight));
            ProgramPanel.this.repaint();
            ProgramPanel.this.revalidate();
            parentPanel.revalidate();
            parentPanel.repaint();


        });

        JPanel rightPanel = new JPanel();
        rightPanel.setOpaque(false);
        rightPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));

        JTextField repsAmount = new JTextField();
        repsAmount.setPreferredSize(new Dimension(35, 20));
        rightPanel.add(repsAmount);
        repsAmount.setBorder(null);
        repsAmount.setText("0");

        JTextField weightAmount = new JTextField();
        weightAmount.setPreferredSize(new Dimension(35, 20));
        rightPanel.add(weightAmount);
        weightAmount.setBorder(null);
        weightAmount.setText("0");

        JTextField rirAmount = new JTextField();
        rirAmount.setPreferredSize(new Dimension(35, 20));
        rightPanel.add(rirAmount);
        rirAmount.setBorder(null);
        rirAmount.setText("0");

        setPanel.add(rightPanel, BorderLayout.EAST);
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
    }



