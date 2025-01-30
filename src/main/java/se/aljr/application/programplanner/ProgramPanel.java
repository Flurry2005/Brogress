package se.aljr.application.programplanner;

import se.aljr.application.exercise.Excercise.Exercise;
import se.aljr.application.exercise.Program.Exercises;
import se.aljr.application.programplanner.workout.Workout;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ContainerEvent;
import java.awt.event.ContainerListener;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class ProgramPanel extends JPanel {
    private static boolean noExercises = true;
    private static int setCounter = 1;
    private static Workout currentWorkout = new Workout();
    private static Exercise currentExcercise;
    private static String workoutTitle;

    public ProgramPanel(int width, int height){
        this.setSize(width,height);
        this.setBackground(Color.cyan);
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


        //Panel holding the function title
        JPanel headerPanel = new JPanel();
        headerPanel.setOpaque(true);
        headerPanel.setBackground(Color.WHITE);
        headerPanel.setMaximumSize(new Dimension(getWidth(),getHeight()/10));
        headerPanel.setPreferredSize(new Dimension(getWidth(),getHeight()/10));
        headerPanel.setBackground(new Color(245,245,245));
        mainPanel.add(headerPanel);

        //Label holding workout title
        JLabel headerTitle = new JLabel();
        headerTitle.setText("Workout " + LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy/MM/dd")));
        headerTitle.setForeground(Color.BLACK);
        headerTitle.setFont(new Font("Arial", Font.BOLD, 45));
        headerTitle.setHorizontalAlignment(SwingConstants.CENTER);
        headerPanel.add(headerTitle, SwingConstants.CENTER);

        //Change workout title button
        JButton changeTitle = new JButton();
        changeTitle.setText("Change workout title");
        changeTitle.setBackground(Color.white);
        mainPanel.add(changeTitle);
        changeTitle.setVisible(false);

        //Scrollable window
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setPreferredSize(new Dimension(getWidth(), getHeight()/2));
        scrollPane.setMaximumSize(new Dimension(getWidth(), getHeight()/2));
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.getVerticalScrollBar().setPreferredSize(new Dimension(0,0));
        scrollPane.getVerticalScrollBar().setUnitIncrement(6);

        mainPanel.add(scrollPane);

        //Panel containing log and workout data
        JPanel logContainer = new JPanel();
        logContainer.setLayout(new BoxLayout(logContainer, BoxLayout.Y_AXIS));
        logContainer.setOpaque(true);
        logContainer.setBackground(Color.WHITE);
        scrollPane.setViewportView(logContainer);
        logContainer.setMaximumSize(new Dimension(getWidth()-20, getHeight()/10));
        logContainer.setPreferredSize(new Dimension(getWidth()-20, getHeight()/10));

        //Label to display text when log is empty
        JLabel isEmpty = new JLabel();
        isEmpty.setFont( new Font("Arial", Font.ITALIC, 20));
        isEmpty.setText("No exercises added yet.");
        logContainer.add(isEmpty);

        // "Add set"- button
        JButton addSet = new JButton();
        addSet.setText("[+] Set ");

        //Listener to generate new set-panel
        addSet.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                if (noExercises) {
                    return;
                }
                else {
                    JPanel newSetPanel = createSetPanel(setCounter++);
                    logContainer.add(newSetPanel);
                    logContainer.revalidate();
                    logContainer.repaint();
                    logContainer.setPreferredSize(new Dimension(getWidth() - 20, logContainer.getComponentCount() * getHeight() / 10));

                }
            }

            private JPanel createSetPanel(int setCounter) {

                JPanel setPanel = new JPanel();
                setPanel.setOpaque(true);
                setPanel.setLayout(new BorderLayout());
                setPanel.setBackground(Color.WHITE);
                setPanel.setMaximumSize(new Dimension(getWidth(),getHeight()/10));
                setPanel.setPreferredSize(new Dimension(getWidth(),getHeight()/10));
                setPanel.setAlignmentX(SwingConstants.CENTER);

                JPanel leftPanel = new JPanel();
                leftPanel.setOpaque(false);
                leftPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
                setPanel.add(leftPanel, BorderLayout.WEST);

                JLabel setLabel = new JLabel();
                setLabel.setText(Integer.toString(setCounter) + ".");
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
                return setPanel;
            }
        });

        // Button panel to hold search and list horizontally
        JPanel buttonPanel2 = new JPanel();
        buttonPanel2.setLayout(new BoxLayout(buttonPanel2, BoxLayout.Y_AXIS));
        buttonPanel2.setOpaque(true);

        //Search and add exercise
        DefaultListModel <Exercise> exerciseModel = new DefaultListModel<Exercise>();
        Exercises list = new Exercises();
        for (Exercise exercise : list.getList()) {
            exerciseModel.addElement(exercise);
        }

        JList <Exercise> searchExcerciseResult = new JList(exerciseModel);
        searchExcerciseResult.setPreferredSize(new Dimension(200,500));

        JTextField searchExcercise = new JTextField();
        searchExcercise.setText("Search for exercise...");
        searchExcercise.setFont(new Font("Arial", Font.PLAIN, 12));
        searchExcercise.setPreferredSize(new Dimension(200,30));
        buttonPanel2.add(searchExcercise);
        searchExcercise.getDocument().addDocumentListener(new DocumentListener() {

            @Override
            public void insertUpdate(DocumentEvent e) { filterList(); }
            @Override
            public void removeUpdate(DocumentEvent e) { filterList(); }
            @Override
            public void changedUpdate(DocumentEvent e) {}

            private void filterList() {
                String searchText = searchExcercise.getText().toLowerCase();
                exerciseModel.clear(); // Clear the current list

                for (Exercise exercise : list.getList()) {
                    if (exercise.getName().toLowerCase().contains(searchText)) {
                        exerciseModel.addElement(exercise);//Add excercises back to list
                    }
                }
            }
        });

        //Scroll-Panel to hold all exercises
        JScrollPane scrollPane2 = new JScrollPane(searchExcerciseResult);
        buttonPanel2.add(scrollPane2);
        scrollPane2.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane2.getVerticalScrollBar().setPreferredSize(new Dimension(0,0));

        //Panel to hold buttons horizontally
        JPanel buttonPanel3 = new JPanel();
        buttonPanel3.setBackground(new Color(245,245,245));


        //"Add exercise"-button
        JButton newExcerciseButton = new JButton();
        newExcerciseButton.setText("[+] Exercise");
        buttonPanel3.add(newExcerciseButton);

        //						This part generates a new exercise
        newExcerciseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (searchExcerciseResult.getSelectedValue() == null) {
                    return;
                }

                if (noExercises) {
                    logContainer.removeAll();
                    noExercises = false;
                    buttonPanel3.add(addSet);
                }

                currentExcercise = searchExcerciseResult.getSelectedValue();
                currentWorkout.setRep(currentExcercise,setCounter,12,100);

                JPanel newExercisePanel = createExercisePanel();
                setCounter = 1;
                logContainer.add(newExercisePanel);
                logContainer.revalidate();
                logContainer.repaint();
                logContainer.setPreferredSize(new Dimension(getWidth() - 20, logContainer.getComponentCount() * getHeight() / 10));
                ProgramPanel.this.revalidate();
                ProgramPanel.this.repaint();
            }
            private JPanel createExercisePanel() {

                // New container to hold every added panel
                JPanel logContainer = new JPanel();
                logContainer.setLayout(new BoxLayout(logContainer, BoxLayout.Y_AXIS));
                logContainer.setMaximumSize(new Dimension(getWidth(),getHeight()/10));
                logContainer.setOpaque(true);
                logContainer.setBackground(Color.WHITE);

                // Panel to display exercise name
                JPanel exerciseNameTitlePanel = new JPanel();
                exerciseNameTitlePanel.setLayout(new BoxLayout(exerciseNameTitlePanel, BoxLayout.Y_AXIS));
                exerciseNameTitlePanel.setOpaque(false);

                // Label to hold name of exercise
                JLabel exerciseName = new JLabel(searchExcerciseResult.getSelectedValue().getName());
                exerciseName.setFont( new Font("Arial", Font.BOLD, 20));
                exerciseNameTitlePanel.add(exerciseName);
                logContainer.add(exerciseNameTitlePanel);

                // Panel to hold the titles of Set, Rep, Weight, RIR
                JPanel setRepWeightRirTitleNPanel = new JPanel();
                setRepWeightRirTitleNPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
                setRepWeightRirTitleNPanel.setMaximumSize(new Dimension(getWidth(), getHeight()/10));
                setRepWeightRirTitleNPanel.setOpaque(true);
                setRepWeightRirTitleNPanel.setLayout(new BorderLayout());
                logContainer.add(setRepWeightRirTitleNPanel);

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
                //
                JLabel rirLabel = new JLabel();
                rirLabel.setText("RIR");
                rirLabel.setAlignmentX(Component.RIGHT_ALIGNMENT);
                rightPanel.add(rirLabel);

                ProgramPanel.this.revalidate();
                ProgramPanel.this.repaint();

                return logContainer;

            }
        });

        mainPanel.add(buttonPanel2);
        mainPanel.add(buttonPanel3);
        this.add(mainPanel);
    }
}
