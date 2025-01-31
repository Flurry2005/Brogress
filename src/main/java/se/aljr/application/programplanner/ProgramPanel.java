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
    private static boolean emptyLog = true;
    private Workout currentWorkout = new Workout();
    private String workoutTitle = currentWorkout.getName();
    private static int totalHeight;
    Map<Integer, JPanel> exercisePanels = new HashMap<>();
    Map<Integer, Integer> exerciseSetCount = new HashMap<>();

    public ProgramPanel(int width, int height){
        this.setSize(width,height);
        this.setBackground(Color.WHITE);
        this.setLayout(new FlowLayout(FlowLayout.CENTER,0,0));
        init();
    }
    private void init() {

        // Main panel holding everything
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setOpaque(true);
        mainPanel.setBackground(Color.WHITE);
        mainPanel.setMaximumSize(new Dimension(getWidth(),getHeight()));
        mainPanel.setPreferredSize(new Dimension(getWidth(),getHeight()));

        //Panel holding the name of the workout
        JPanel headerPanel = new JPanel();
        headerPanel.setOpaque(true);
        headerPanel.setBackground(Color.WHITE);
        headerPanel.setMaximumSize(new Dimension(getWidth(),getHeight()/10));
        headerPanel.setPreferredSize(new Dimension(getWidth(),getHeight()/10));
        headerPanel.setBackground(new Color(245,245,245));

        //Label holding workout title
        JLabel headerTitle = new JLabel();
        headerTitle.setText(workoutTitle);
        headerTitle.setForeground(Color.BLACK);
        headerTitle.setFont(new Font("Arial", Font.BOLD, 22));
        headerTitle.setHorizontalAlignment(SwingConstants.CENTER);

        //Scrollable window
        JScrollPane workoutScrollPane = new JScrollPane();
        workoutScrollPane.setPreferredSize(new Dimension(getWidth(), getHeight()/2));
        workoutScrollPane.setMaximumSize(new Dimension(getWidth(), getHeight()/2));
        workoutScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        workoutScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        workoutScrollPane.getVerticalScrollBar().setPreferredSize(new Dimension(0,0));
        workoutScrollPane.getVerticalScrollBar().setUnitIncrement(6);

        //Panel containing log and workout data
        JPanel logContainer = new JPanel();
        logContainer.setLayout(new BoxLayout(logContainer, BoxLayout.Y_AXIS));
        logContainer.setOpaque(true);
        logContainer.setBackground(Color.WHITE);
        logContainer.setMaximumSize(new Dimension(getWidth()-20, getHeight()/10));
        logContainer.setPreferredSize(new Dimension(getWidth()-20, getHeight()/10));
        workoutScrollPane.setViewportView(logContainer); //workoutScrollPane will show the content of logContainer

        // Button panel to hold search and list horizontally
        JPanel exercisesPanel = new JPanel();
        exercisesPanel.setLayout(new BoxLayout(exercisesPanel, BoxLayout.Y_AXIS));
        exercisesPanel.setOpaque(true);

        //Search and add exercise
        DefaultListModel <Exercise> exerciseModel = new DefaultListModel<Exercise>();
        Exercises list = new Exercises();
        for (Exercise exercise : list.getList()) {
            exerciseModel.addElement(exercise);
        }

        JList <Exercise> searchExerciseResult = new JList(exerciseModel);
        searchExerciseResult.setFixedCellHeight(20);
        searchExerciseResult.setPreferredSize(new Dimension(200,searchExerciseResult.getFixedCellHeight()*searchExerciseResult.getModel().getSize()));

        JTextField searchExercise = new JTextField();
        searchExercise.setText("Search for exercise...");
        searchExercise.setFont(new Font("Arial", Font.PLAIN, 12));
        searchExercise.setPreferredSize(new Dimension(200,30));
        searchExercise.getDocument().addDocumentListener(new DocumentListener() {

            @Override
            public void insertUpdate(DocumentEvent e) { filterList(); }
            @Override
            public void removeUpdate(DocumentEvent e) { filterList(); }
            @Override
            public void changedUpdate(DocumentEvent e) {}

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
        scrollPane2.getVerticalScrollBar().setPreferredSize(new Dimension(0,0));

        //Panel to hold add new exercise button and set
        JPanel addExerciseAndSetPanel = new JPanel();
        addExerciseAndSetPanel.setBackground(new Color(245,245,245));

        //Change workout title button
        JButton changeTitle = new JButton();
        changeTitle.setText("Change workout title");
        changeTitle.setBackground(Color.white);

        changeTitle.setVisible(false);

        //Label to display text when log is empty
        JLabel isEmpty = new JLabel();
        isEmpty.setFont( new Font("Arial", Font.ITALIC, 20));
        isEmpty.setText("No exercises added yet.");

        //"Add exercise"-button
        JButton newExcerciseButton = new JButton();
        newExcerciseButton.setText("[+] Exercise");

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

                addExercise(searchExerciseResult.getSelectedValue(),logContainer);
                logContainer.revalidate();
                logContainer.repaint();
                ProgramPanel.this.revalidate();
                ProgramPanel.this.repaint();
            }

        });

        mainPanel.add(headerPanel);
        headerPanel.add(headerTitle, SwingConstants.CENTER);
        mainPanel.add(changeTitle);
        mainPanel.add(workoutScrollPane);
        logContainer.add(isEmpty);
        exercisesPanel.add(searchExercise);
        addExerciseAndSetPanel.add(newExcerciseButton);
        mainPanel.add(exercisesPanel);
        mainPanel.add(addExerciseAndSetPanel);
        this.add(mainPanel);

    }
    private JPanel addExercise(Exercise currentExercise, JPanel logContainer) {

        // Exerciseid to identify
        int exerciseId = exercisePanels.size() + 1;

        JPanel mainExercisePanel = new JPanel();
        mainExercisePanel.setLayout(new BoxLayout(mainExercisePanel, BoxLayout.Y_AXIS));
        mainExercisePanel.setBackground(Color.WHITE);

        // Track set cunt for each panel
        exercisePanels.put(exerciseId, mainExercisePanel);
        exerciseSetCount.put(exerciseId, 0);

        // Panel to display exercise name
        JPanel exerciseNameTitlePanel = new JPanel();
        exerciseNameTitlePanel.setLayout(new BoxLayout(exerciseNameTitlePanel, BoxLayout.Y_AXIS));
        exerciseNameTitlePanel.setOpaque(false);

        // Label to hold name of exercise
        JLabel exerciseName = new JLabel();
        exerciseName.setText(currentExercise.getName());
        exerciseName.setFont( new Font("Arial", Font.BOLD, 20));
        exerciseNameTitlePanel.add(exerciseName);
        mainExercisePanel.add(exerciseNameTitlePanel);

        // Panel to hold the titles of Set, Rep, Weight, RIR
        JPanel setRepWeightRirTitleNPanel = new JPanel();
        setRepWeightRirTitleNPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
        setRepWeightRirTitleNPanel.setMaximumSize(new Dimension(getWidth(), getHeight()/18));
        setRepWeightRirTitleNPanel.setOpaque(true);
        setRepWeightRirTitleNPanel.setLayout(new BorderLayout());
        mainExercisePanel.add(setRepWeightRirTitleNPanel);

        // Title Panel to align Set title to left
        JPanel leftPanel = new JPanel();
        leftPanel.setOpaque(false);
        leftPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
        leftPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        setRepWeightRirTitleNPanel.add(leftPanel, BorderLayout.WEST);

        // Label to hold "Set"
        JLabel setLabel = new JLabel();
        setLabel.setText("Set");
        setLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        leftPanel.add(setLabel);

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
        addSet.setText("[+]");
        addSet.setMaximumSize(new Dimension(50,30));

        totalHeight+=3*getHeight()/19; //Lägger till höjden för de 3 paneler som skapas när en övning läggs till

        addSet.addActionListener(e -> {
            totalHeight+=getHeight()/19; //Lägger till höjden settet som läggs till
            addSet(exerciseId);
            logContainer.setPreferredSize(new Dimension(logContainer.getWidth(), totalHeight));
            logContainer.revalidate();
            logContainer.repaint();

        });

        mainExercisePanel.add(addSet);
        ProgramPanel.this.revalidate();
        ProgramPanel.this.repaint();
        Map<JButton, Exercise> buttonToExerciseMap = new HashMap<>();
        logContainer.add(mainExercisePanel);
        logContainer.setPreferredSize(new Dimension(logContainer.getWidth(), totalHeight));
        logContainer.revalidate();
        logContainer.repaint();

        return logContainer;
    }
    public void addSet(int exerciseId) {

        JPanel parentPanel = exercisePanels.get(exerciseId);

        int previousSet = exerciseSetCount.getOrDefault(exerciseId, 0);
        exerciseSetCount.put(exerciseId, previousSet + 1);

        JPanel setPanel = new JPanel();
        setPanel.setOpaque(true);
        setPanel.setLayout(new BorderLayout());
        setPanel.setBackground(Color.WHITE);
        setPanel.setMaximumSize(new Dimension(getWidth(),getHeight()/19));
        setPanel.setPreferredSize(new Dimension(getWidth(),getHeight()/19));
        setPanel.setAlignmentX(SwingConstants.CENTER);

        JPanel leftPanel = new JPanel();
        leftPanel.setOpaque(false);
        leftPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        setPanel.add(leftPanel, BorderLayout.WEST);

        JLabel setLabel = new JLabel();
        setLabel.setText(previousSet + 1 + ".");
        leftPanel.add(setLabel);
        setPanel.add(leftPanel, BorderLayout.WEST);

        JPanel rightPanel = new JPanel();
        rightPanel.setOpaque(false);
        rightPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));

        JTextField repsAmount = new JTextField();
        repsAmount.setText("12");
        repsAmount.setPreferredSize(new Dimension(35,20));
        rightPanel.add(repsAmount);

        JTextField weightAmount = new JTextField();
        weightAmount.setText("100");
        weightAmount.setPreferredSize(new Dimension(35,20));
        rightPanel.add(weightAmount);

        JTextField rirAmount = new JTextField();
        rirAmount.setText("100");
        rirAmount.setPreferredSize(new Dimension(35,20));
        rightPanel.add(rirAmount);

        setPanel.add(rightPanel, BorderLayout.EAST);

        parentPanel.add(setPanel);
        parentPanel.revalidate();
        parentPanel.repaint();

    }
}
