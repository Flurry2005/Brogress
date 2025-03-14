package se.aljr.application.programplanner;

import se.aljr.application.AppThemeColors;
import se.aljr.application.CustomFont;
import se.aljr.application.ResourcePath;
import se.aljr.application.UserData;
import se.aljr.application.exercise.Excercise.Exercise;
import se.aljr.application.exercise.Program.Exercises;
import se.aljr.application.loginpage.FirebaseManager;
import se.aljr.application.settings.SettingsPanel;

import javax.imageio.ImageIO;
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
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.concurrent.ExecutionException;

public class ProgramPanel extends JPanel {
    public static int programPanelHeight;
    public static int programPanelWidth;

    private Workout workoutContainer;
    public static int setPanelHeight;
    private JLabel headerTitle;

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

    private JButton deleteDefaultWorkout = new JButton();

    private ImageIcon newWorkoutButtonImage;
    private Image scaledNewWorkoutButtonImage;
    private ImageIcon scaledNewWorkoutIcon;

    private JButton newWorkoutButton;
    private JButton deleteWorkout = new JButton();

    private JCheckBox saveAsDefault = new JCheckBox();
    private JTextArea setDefaultInfo = new JTextArea();
    private JLabel getDefaultInfo = new JLabel();

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
    JPanel mainPanel = new JPanel();
    JLabel statusText = new JLabel();
    static JPanel workoutPanel = new JPanel();
    static JPanel exercisesPanel = new JPanel();

    static JPanel statusPanel = new JPanel();
    static Timer shrinkStatusTimer;

    JTextField workoutTitle = new JTextField();
    JTextField searchExercise = new JTextField();
    JScrollPane workoutScrollPane = new JScrollPane();
    JScrollPane savedWorkoutsScrollPane = new JScrollPane();
    JScrollPane defaultWorkoutsScrollPane = new JScrollPane();
    DefaultListModel<Exercise> exerciseModel = new DefaultListModel<>();
    JList<Exercise> searchExerciseResult = new JList<>(exerciseModel);
    JScrollPane exercisesScrollPane = new JScrollPane(searchExerciseResult);
    JPanel addExerciseAndSetPanel = new JPanel();
    JPanel workoutPanelTop = new JPanel();
    JPanel savedWorkoutsPanel = new JPanel();
    DefaultListModel<String> workoutTitleDefaultListModel = new DefaultListModel<>();
    DefaultListModel<String> defaultWorkoutTitleDefaultListModel = new DefaultListModel<>();
    JList<String> savedWorkoutsList = new JList<>(workoutTitleDefaultListModel);
    JList<String> defaultWorkoutList = new JList<>(defaultWorkoutTitleDefaultListModel);
    WorkoutsList workoutsList;
    WorkoutsList defaultWorkoutslist;

    JLabel savedWorkoutsLabel;
    JButton saveWorkoutButton = new JButton();

    JButton exportWorkoutButton = new JButton();

    private SearchPanel searchPanel;

    public static Color settingsPanelColor;
    public static Color workoutPanelTextColor;

    public static ProgramPanel instance;

    private ImageIcon exportIcon;
    private Image scaledExportIcon;

    public ProgramPanel(int width, int height) {
        emptyBackground = new ImageIcon(ResourcePath.getResourcePath("emptyBackground.png"));
        scaledEmptyBackground = emptyBackground.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);
        scaledEmptyBackgroundIcon = new ImageIcon(scaledEmptyBackground);

        lightEmptyBackground = new ImageIcon(ResourcePath.getResourcePath("lightEmptyBackground.png"));
        scaledLightEmptyBackground = lightEmptyBackground.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);
        scaledLightEmptyBackgroundIcon = new ImageIcon(scaledLightEmptyBackground);

        addButton = new ImageIcon(ResourcePath.getResourcePath("add_button.png"));
        scaledAddButton = addButton.getImage().getScaledInstance((int) (addButton.getIconWidth() / 1.5), (int) (addButton.getIconHeight()), Image.SCALE_SMOOTH);
        scaledAddButtonIcon = new ImageIcon(scaledAddButton);

        saveButton = new ImageIcon(ResourcePath.getResourcePath("save_workout_button.png"));
        scaledsaveButton = saveButton.getImage().getScaledInstance((int) (width / 7.59285714), (int) (height / 22.862069), Image.SCALE_SMOOTH);
        scaledsaveButtonIcon = new ImageIcon(scaledsaveButton);

        removeWorkoutButtonImage = new ImageIcon(ResourcePath.getResourcePath("remove_workout_button.png"));
        scaledRemoveWorkoutButtonImage = removeWorkoutButtonImage.getImage().getScaledInstance((int) (width / 28.7297297), (int) (height / 22.862069), Image.SCALE_SMOOTH);
        scaledRemoveWorkoutIcon = new ImageIcon(scaledRemoveWorkoutButtonImage);

        newWorkoutButtonImage = new ImageIcon(ResourcePath.getResourcePath("new_workout_button.png"));
        scaledNewWorkoutButtonImage = newWorkoutButtonImage.getImage().getScaledInstance((int) (width / 14.1733333), (int) (height / 22.862069), Image.SCALE_SMOOTH);
        scaledNewWorkoutIcon = new ImageIcon(scaledNewWorkoutButtonImage);

        removeExerciseButtonImage = new ImageIcon(ResourcePath.getResourcePath("remove_exercise_button.png"));
        scaledRemoveExerciseButtonImage = removeExerciseButtonImage.getImage().getScaledInstance((int) (width / 14.971831), (int) (height / 19.5), Image.SCALE_SMOOTH);
        scaledRemoveExerciseIcon = new ImageIcon(scaledRemoveExerciseButtonImage);

        newSetButtonImage = new ImageIcon(ResourcePath.getResourcePath("new_set_button.png"));
        scaledNewSetButtonImage = newSetButtonImage.getImage().getScaledInstance((int) (width / 35.4333333), (int) (height / 19.5), Image.SCALE_SMOOTH);
        scaledNewSetIcon = new ImageIcon(scaledNewSetButtonImage);

        removeSetButtonImage = new ImageIcon(ResourcePath.getResourcePath("remove_set_button.png"));
        scaledRemoveSetButtonImage = removeSetButtonImage.getImage().getScaledInstance((int) (width / 46.2173913043), (int) (height / 26.52), Image.SCALE_SMOOTH);
        scaledRemoveSetIcon = new ImageIcon(scaledRemoveSetButtonImage);

        moveSetUpButtonImage = new ImageIcon(ResourcePath.getResourcePath("move_set_up.png"));
        scaledMoveSetUpButtonImage = moveSetUpButtonImage.getImage().getScaledInstance((int) (width / 88.5833333), (int) (height / 30.1363636), Image.SCALE_SMOOTH);
        scaledMoveSetUpIcon = new ImageIcon(scaledMoveSetUpButtonImage);

        moveSetDownButtonImage = new ImageIcon(ResourcePath.getResourcePath("move_set_down.png"));
        scaledMoveSetDownButtonImage = moveSetDownButtonImage.getImage().getScaledInstance((int) (width / 88.5833333), (int) (height / 30.1363636), Image.SCALE_SMOOTH);
        scaledMoveSetDownIcon = new ImageIcon(scaledMoveSetDownButtonImage);

        exportIcon = new ImageIcon(ResourcePath.getResourcePath("exportIcon.png"));
        scaledExportIcon = exportIcon.getImage().getScaledInstance(width / 30, height / 20, Image.SCALE_SMOOTH);
        exportIcon = new ImageIcon(scaledExportIcon);

        instance = this;

        this.setSize(width, height);
        this.setOpaque(false);
        this.setBackground(AppThemeColors.SECONDARY);
        this.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));
        init();
    }

    private void init() {

        setPanelHeight = getHeight() / 19;

        programPanelHeight = getHeight();
        programPanelWidth = getWidth();

        //Wrapper for Statuspanel and Mainpanel to keep consistent positions
        JLayeredPane wrapper = new JLayeredPane();
        wrapper.setPreferredSize(new Dimension(programPanelWidth, programPanelHeight));

        // Main panel holding everything

        mainPanel.setOpaque(false);
        mainPanel.setBackground(AppThemeColors.PRIMARY);

        mainPanel.setBounds(0, 0, programPanelWidth, programPanelHeight);
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
        workoutScrollPane.getViewport().setBackground(AppThemeColors.panelColor);
        workoutScrollPane.setBorder(new LineBorder(new Color(80, 73, 69), 1));

        //Statuspanel
        statusPanel = new JPanel();
        statusPanel.setBounds(0, (int) (getPreferredSize().height - (getPreferredSize().height / 13.26)), this.getWidth(), (int) (getPreferredSize().height / 13.26));
        statusPanel.setPreferredSize(new Dimension(this.getWidth(), (int) (getPreferredSize().height / 13.26)));
        statusPanel.setVisible(false);
        statusPanel.setLayout(new BoxLayout(statusPanel, BoxLayout.X_AXIS));

        statusText.setForeground(new Color(204, 204, 204));
        statusText.setAlignmentY(Component.CENTER_ALIGNMENT);

        statusPanel.add(Box.createHorizontalGlue());
        statusPanel.add(statusText);
        statusPanel.add(Box.createHorizontalGlue());
        shrinkStatusTimer = new Timer(30, e -> {
            if (statusPanel.getHeight() == 0) {
                statusPanel.setVisible(false);
                ((Timer) e.getSource()).stop();
                return;
            }
            if (statusDelayCounter > 0) {
                statusDelayCounter -= 1;
            } else {
                statusPanel.setBounds(0, statusPanel.getBounds().y + 1, mainPanel.getWidth(), statusPanel.getHeight());
                statusPanel.repaint();
                statusPanel.revalidate();
            }
        });

        //"Add exercise"-button
        JButton newExerciseButton = new JButton();
        newExerciseButton.setIcon(scaledAddButtonIcon);
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
                if (SearchPanel.getSelectedExercise() == null || savedWorkoutsList.getSelectedIndex() == -1 && defaultWorkoutList.getSelectedIndex() == -1) {
                    return;
                }
                addExercise(SearchPanel.getSelectedExercise(), workoutContainer);
                workoutContainer.revalidate();
                workoutContainer.repaint();
                ProgramPanel.this.revalidate();
                ProgramPanel.this.repaint();
            }

        });

        //Panel containing log and workout data
        defaultWorkoutslist = FirebaseManager.readDBDefaultWorkouts(this);
        workoutsList = FirebaseManager.readDBworkout(this);

        if (workoutsList.isEmpty()) {
            if (!defaultWorkoutslist.isEmpty()) {
                workoutContainer = defaultWorkoutslist.getFirst();
                newExerciseButton.setEnabled(false);
                workoutTitle.setEditable(false);
                workoutTitle.setText(workoutContainer.getWorkoutData().getTitle());
                deleteWorkout.setEnabled(false);
                if (UserData.isUserAdmin()) {
                    deleteDefaultWorkout.setVisible(true);
                }
            }

            else {
                workoutContainer = new Workout();
            }

        } else {
            workoutContainer = workoutsList.getFirst();
            workoutTitle.setText(workoutContainer.getWorkoutData().getTitle());
            if (UserData.isUserAdmin()) {
                deleteDefaultWorkout.setVisible(false);
            }
        }

        workoutContainer.setLayout(new BoxLayout(workoutContainer, BoxLayout.Y_AXIS));
        workoutContainer.setOpaque(false);
        workoutContainer.setForeground(new Color(204, 204, 204));
        workoutContainer.setBackground(AppThemeColors.panelColor);
        workoutScrollPane.setViewportView(workoutContainer); //workoutScrollPane will show the content of workoutContainer

        savedWorkoutsPanel.setLayout(new BoxLayout(savedWorkoutsPanel, BoxLayout.Y_AXIS));
        savedWorkoutsPanel.setPreferredSize(new Dimension(getWidth() / 5, (int) (getHeight())));
        savedWorkoutsPanel.setMinimumSize(savedWorkoutsPanel.getPreferredSize());
        savedWorkoutsPanel.setMaximumSize(savedWorkoutsPanel.getPreferredSize());
        savedWorkoutsPanel.setOpaque(true);
        savedWorkoutsPanel.setBackground(AppThemeColors.PRIMARY);

        JPanel savedWorkoutsPanelTop = new JPanel();
        savedWorkoutsPanelTop.setLayout(new BoxLayout(savedWorkoutsPanelTop, BoxLayout.X_AXIS));
        savedWorkoutsPanelTop.setOpaque(false);
        savedWorkoutsPanelTop.setPreferredSize(new Dimension(getWidth() / 5, getHeight() / 20));
        savedWorkoutsPanelTop.setMinimumSize(new Dimension(getWidth() / 5, getHeight() / 20));
        savedWorkoutsPanelTop.setMaximumSize(new Dimension(getWidth() / 5, getHeight() / 20));
        savedWorkoutsPanelTop.setBackground(new Color(49, 84, 122));
        savedWorkoutsPanelTop.setBorder(null);

        savedWorkoutsLabel = new JLabel("My workouts");
        savedWorkoutsLabel.setMaximumSize(new Dimension((int) (savedWorkoutsPanelTop.getPreferredSize().width / 4), (int) (savedWorkoutsPanelTop.getPreferredSize().height)));
        savedWorkoutsLabel.setFont(CustomFont.getFont().deriveFont(24f));
        savedWorkoutsLabel.setForeground(AppThemeColors.foregroundColor);
        savedWorkoutsLabel.setOpaque(false);

        // Select saved workouts
        DefaultListModel<Workout> workoutDefaultListModel = new DefaultListModel<>();
        for (Workout workout : workoutsList) {
            workoutDefaultListModel.addElement(workout);
            workoutTitleDefaultListModel.addElement(workout.getWorkoutData().getTitle());
        }

        //Default programs
        DefaultListModel<Workout> defaultWorkoutModel = new DefaultListModel<>();
        for (Workout workout : defaultWorkoutslist) {
            defaultWorkoutModel.addElement(workout);
            defaultWorkoutTitleDefaultListModel.addElement(workout.getWorkoutData().getTitle());
        }

        defaultWorkoutList.setForeground(Color.WHITE);
        defaultWorkoutList.setFixedCellHeight((int) (getHeight() / 22.5));
        defaultWorkoutList.setBackground(new Color(22, 22, 22));
        defaultWorkoutList.setSelectedIndex(0);
        defaultWorkoutList.setSelectionMode(0);
        defaultWorkoutList.setFont(new Font("Arial", Font.BOLD, (int) (getHeight() / 55.25)));
        defaultWorkoutList.clearSelection();
        defaultWorkoutList.addListSelectionListener(_ -> {
            if (defaultWorkoutList.getSelectedIndex() != -1) {

                savedWorkoutsList.clearSelection();

                if (UserData.isUserAdmin()) {
                    deleteDefaultWorkout.setVisible(true);
                    newExerciseButton.setEnabled(true);
                    saveWorkoutButton.setEnabled(true);
                    setDefaultInfo.setVisible(true);

                }
                else {
                    newExerciseButton.setEnabled(false);
                    deleteWorkout.setEnabled(false);
                    saveWorkoutButton.setEnabled(false);
                }

                Workout target = defaultWorkoutslist.get(defaultWorkoutList.getSelectedIndex());
                workoutContainer = target;

                workoutTitle.setText(target.getWorkoutData().getTitle());


                workoutContainer.setLayout(new BoxLayout(workoutContainer, BoxLayout.Y_AXIS));
                workoutContainer.setOpaque(false);
                workoutContainer.setForeground(AppThemeColors.foregroundColor);
                workoutContainer.setBackground(new Color(22, 22, 22));
                workoutContainer.setPreferredSize(new Dimension(target.getWidth(), target.getHeight()));
                workoutContainer.setMinimumSize(target.getPreferredSize());
                workoutContainer.setMaximumSize(target.getPreferredSize());

                workoutScrollPane.setViewportView(workoutContainer);
            }
        });


        savedWorkoutsList.setForeground(Color.WHITE);
        savedWorkoutsList.setFixedCellHeight((int) (getHeight() / 22.5));
        savedWorkoutsList.setBackground(new Color(22, 22, 22));
        savedWorkoutsList.setSelectedIndex(0);
        savedWorkoutsList.setSelectionMode(0);
        savedWorkoutsList.setFont(new Font("Arial", Font.BOLD, (int) (getHeight() / 55.25)));
        savedWorkoutsList.addListSelectionListener(_ -> {

            if (savedWorkoutsList.getSelectedIndex() != -1) {

                defaultWorkoutList.clearSelection();
                if (UserData.isUserAdmin()) {
                    deleteDefaultWorkout.setVisible(false);
                    setDefaultInfo.setVisible(false);

                }

                Workout target = workoutDefaultListModel.getElementAt(savedWorkoutsList.getSelectedIndex());
                workoutContainer = target;

                defaultWorkoutList.clearSelection();
                deleteWorkout.setEnabled(true);
                newExerciseButton.setEnabled(true);
                workoutTitle.setEditable(true);
                saveWorkoutButton.setEnabled(true);

                workoutTitle.setText(target.getWorkoutData().getTitle());

                workoutContainer.setLayout(new BoxLayout(workoutContainer, BoxLayout.Y_AXIS));
                workoutContainer.setOpaque(false);
                workoutContainer.setForeground(AppThemeColors.foregroundColor);
                workoutContainer.setBackground(new Color(22, 22, 22));
                workoutContainer.setPreferredSize(new Dimension(target.getWidth(), target.getWorkoutData().getTotalWorkoutHeight()));
                workoutContainer.setMinimumSize(target.getPreferredSize());
                workoutContainer.setMaximumSize(target.getPreferredSize());

                workoutScrollPane.setViewportView(workoutContainer);
            }

        });

        newWorkoutButton = new JButton(scaledNewWorkoutIcon);
        newWorkoutButton.setContentAreaFilled(false);
        newWorkoutButton.setPreferredSize(new Dimension(scaledNewWorkoutIcon.getIconWidth(), scaledNewWorkoutIcon.getIconHeight()));
        newWorkoutButton.setMaximumSize(newWorkoutButton.getPreferredSize());
        newWorkoutButton.setBorder(null);
        newWorkoutButton.setFocusable(false);
        newWorkoutButton.setBorderPainted(false);
        newWorkoutButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {

                // Remove possible "empty"-label
                if (workoutsList.isEmpty() && !workoutContainer.isWorkoutDefault()) {
                    workoutContainer.removeAll();
                }

                defaultWorkoutList.clearSelection();

                if (saveAsDefault.isSelected()) {
                    getDefaultInfo.setName("getDefaultInfo");
                    getDefaultInfo.setForeground(Color.white);
                    getDefaultInfo.setFont(new Font("Arial", Font.PLAIN, (int) (getHeight()/33.15)));
                    getDefaultInfo.setBackground(new Color(6, 6, 52));
                    getDefaultInfo.setOpaque(true);
                    Workout newWorkout = new Workout();

                    newWorkout.setWorkoutInfo(setDefaultInfo.getText());
                    getDefaultInfo.setText(newWorkout.getWorkoutInfo());

                    newWorkout.add(getDefaultInfo, 0);

                    defaultWorkoutslist.add(newWorkout);

                    try {
                        FirebaseManager.writeDBdefaultWorkout(defaultWorkoutslist);
                    } catch (Exception gyat) {
                        gyat.getCause();

                    }

                    defaultWorkoutModel.addElement(newWorkout);
                    defaultWorkoutTitleDefaultListModel.addElement(newWorkout.getWorkoutData().getTitle());

                    defaultWorkoutList.setSelectedIndex(defaultWorkoutslist.size() - 1);

                    workoutTitle.setText(newWorkout.getWorkoutData().getTitle());

                    workoutTitle.setEnabled(true);
                    saveWorkoutButton.setEnabled(true);
                    exportWorkoutButton.setEnabled(true);
                    newExerciseButton.setEnabled(true);
                    deleteWorkout.setEnabled(true);
                    workoutContainer = newWorkout;
                }else{
                    Workout newWorkout = new Workout();
                    workoutsList.add(newWorkout);

                    workoutDefaultListModel.addElement(newWorkout);
                    workoutTitleDefaultListModel.addElement(newWorkout.getWorkoutData().getTitle());

                    savedWorkoutsList.setSelectedIndex(workoutsList.size() - 1);

                    workoutTitle.setText(newWorkout.getWorkoutData().getTitle());

                    workoutTitle.setEnabled(true);
                    saveWorkoutButton.setEnabled(true);
                    exportWorkoutButton.setEnabled(true);
                    newExerciseButton.setEnabled(true);
                    deleteWorkout.setEnabled(true);
                    workoutContainer = newWorkout;
                }

                try {
                    if(!saveAsDefault.isSelected()){
                        FirebaseManager.writeDBworkout(workoutsList);
                    }
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
                activateStatus("New workout created!");

            }
        });

        deleteWorkout.setBorder(null);
        deleteWorkout.setContentAreaFilled(false);
        deleteWorkout.setIcon(scaledRemoveWorkoutIcon);
        deleteWorkout.setFont(new Font("Arial", Font.BOLD, (int) (getHeight() / 66.3)));
        deleteWorkout.setPreferredSize(new Dimension(scaledRemoveWorkoutIcon.getIconWidth(), scaledRemoveWorkoutIcon.getIconHeight()));
        deleteWorkout.setMaximumSize(deleteWorkout.getPreferredSize());
        deleteWorkout.setBorderPainted(true);
        deleteWorkout.setFocusable(false);
        deleteWorkout.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {

                if (!workoutsList.isEmpty()) {
                    if (savedWorkoutsList.getSelectedIndex() != -1) {

                        int selectedIndex = savedWorkoutsList.getSelectedIndex();
                        workoutsList.remove(selectedIndex);
                        activateStatus("Workout removed!");

                        workoutDefaultListModel.clear();
                        workoutTitleDefaultListModel.clear();

                        for (Workout workout : workoutsList) {
                            workoutDefaultListModel.addElement(workout);
                            workoutTitleDefaultListModel.addElement(workout.getWorkoutData().getTitle());
                        }

                        // IF ANY WORKOUTS LEFT THEN SWITCH TO ADJACENT
                        if (!workoutsList.isEmpty()) {

                            savedWorkoutsList.setSelectedIndex(workoutsList.size() - 1);
                            workoutTitle.setText(workoutDefaultListModel.getElementAt(workoutsList.size() - 1).getWorkoutData().getTitle());
                            workoutContainer = workoutsList.get(workoutsList.size() - 1);

                        } else {
                            workoutContainer.removeAll();
                            workoutTitle.setEnabled(false);
                            saveWorkoutButton.setEnabled(false);
                            exportWorkoutButton.setEnabled(false);
                            exportWorkoutButton.setEnabled(false);
                            newExerciseButton.setEnabled(false);
                            deleteWorkout.setEnabled(false);

                            workoutTitle.setText("");
                            workoutContainer.add(new JLabel("Select or create new workout")).setFont(CustomFont.getFont());
                        }

                        try {
                            FirebaseManager.writeDBworkout(workoutsList);
                        } catch (IOException ex) {
                            throw new RuntimeException(ex);
                        }
                    }
                }
            }


        });

        savedWorkoutsScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        savedWorkoutsScrollPane.setPreferredSize(new Dimension(getWidth() / 5, (int) (getHeight() * 8 / 11.28)));
        savedWorkoutsScrollPane.setMinimumSize(savedWorkoutsScrollPane.getPreferredSize());
        savedWorkoutsScrollPane.setMaximumSize(savedWorkoutsScrollPane.getPreferredSize());
        savedWorkoutsScrollPane.getVerticalScrollBar().setPreferredSize(new Dimension(0, 0));
        savedWorkoutsScrollPane.setBorder(new LineBorder(new Color(80, 73, 69), 1));
        savedWorkoutsScrollPane.getViewport().setBackground(new Color(22, 22, 22));
        savedWorkoutsScrollPane.setViewportView(savedWorkoutsList);
        savedWorkoutsScrollPane.setAlignmentY(SwingConstants.BOTTOM);

        defaultWorkoutsScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        defaultWorkoutsScrollPane.setPreferredSize(new Dimension(getWidth() / 5, (int) (getHeight() * (8 / 11.28)) / 2));
        defaultWorkoutsScrollPane.setMinimumSize(savedWorkoutsScrollPane.getPreferredSize());
        defaultWorkoutsScrollPane.setMaximumSize(savedWorkoutsScrollPane.getPreferredSize());
        defaultWorkoutsScrollPane.getVerticalScrollBar().setPreferredSize(new Dimension(0, 0));
        defaultWorkoutsScrollPane.setBorder(new LineBorder(new Color(80, 73, 69), 1));
        defaultWorkoutsScrollPane.getViewport().setBackground(new Color(22, 22, 22));
        defaultWorkoutsScrollPane.setViewportView(defaultWorkoutList);
        defaultWorkoutsScrollPane.setAlignmentY(SwingConstants.BOTTOM);

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

        workoutTitle.setForeground(new Color(204, 204, 204));
        workoutTitle.setBackground(new Color(22, 22, 22));
        workoutTitle.setFont(new Font("Arial", Font.PLAIN, (int) (getHeight() / 55.25)));
        workoutTitle.setPreferredSize(new Dimension(getWidth() / 3, (int) (getHeight() / 22.1)));
        workoutTitle.setMaximumSize(workoutTitle.getPreferredSize());
        workoutTitle.setBorder(new LineBorder(new Color(80, 73, 69)));

        exportWorkoutButton.setIcon(exportIcon);
        exportWorkoutButton.setPreferredSize(new Dimension(exportIcon.getIconWidth(), exportIcon.getIconHeight()));
        exportWorkoutButton.setContentAreaFilled(false);
        exportWorkoutButton.setBackground(new Color(22, 22, 22));
        exportWorkoutButton.setForeground(Color.WHITE);
        exportWorkoutButton.setBorder(null);
        exportWorkoutButton.setFocusable(false);

        exportWorkoutButton.addActionListener(_ -> {
            BufferedImage workoutImage = new BufferedImage(workoutContainer.getWidth(), workoutContainer.getHeight(), BufferedImage.TYPE_INT_RGB);
            Graphics2D g2 = workoutImage.createGraphics();
            workoutContainer.paint(g2);
            g2.dispose();

            File imagePath = new File("myworkout.png");

            try {
                ImageIO.write(workoutImage, "png", imagePath);
            } catch (IOException e) {
                e.printStackTrace();
            }
            new EmailSender().sendEmail(UserData.getEmail(), workoutTitle.getText(), workoutImage, imagePath);
            imagePath.delete();

            activateStatus("Workout exported and sent to " + UserData.getEmail() + "!");
        });

        saveWorkoutButton.setIcon(scaledsaveButtonIcon);
        saveWorkoutButton.setContentAreaFilled(false);
        saveWorkoutButton.setFocusPainted(false);
        saveWorkoutButton.setBorderPainted(false);
        saveWorkoutButton.setPreferredSize(new Dimension(scaledsaveButtonIcon.getIconWidth(), scaledsaveButtonIcon.getIconHeight()));
        saveWorkoutButton.setMaximumSize(new Dimension(scaledsaveButtonIcon.getIconWidth(), scaledsaveButtonIcon.getIconHeight()));
        saveWorkoutButton.addActionListener(_ -> {

            if (savedWorkoutsList.getSelectedIndex() != -1 || defaultWorkoutList.getSelectedIndex() != -1) {

                try {

                    if (saveAsDefault.isSelected() && defaultWorkoutList.getSelectedIndex() != -1) {
                        Workout savedWorkout = defaultWorkoutModel.get(defaultWorkoutList.getSelectedIndex());

                        int index = defaultWorkoutList.getSelectedIndex();

                        savedWorkout.getWorkoutData().setTitle(workoutTitle.getText());
                        workoutTitle.setText(savedWorkout.getWorkoutData().getTitle());
                        savedWorkout.setWorkoutInfo("<html>" + setDefaultInfo.getText() + "</html>");
                        savedWorkout.setDefault(true);

                        try {
                            if (savedWorkout.getComponent(0).getName().equals("getDefaultInfo")) {
                                System.out.println("hitta den");
                                JLabel getDefaultInfo = (JLabel) savedWorkout.getComponent(0);
                                getDefaultInfo.setText(savedWorkout.getWorkoutInfo());
                            }
                        }
                        catch (NullPointerException e) {
                            System.out.println("Hittar inte");

                        }
                        defaultWorkoutModel.get(defaultWorkoutList.getSelectedIndex()).setWorkoutInfo(setDefaultInfo.getText());
                        defaultWorkoutModel.clear();
                        defaultWorkoutTitleDefaultListModel.clear();

                        for (Workout workout : defaultWorkoutslist) {
                            defaultWorkoutModel.addElement(workout);
                            defaultWorkoutTitleDefaultListModel.addElement(workout.getWorkoutData().getTitle());
                        }
                        defaultWorkoutList.setModel(defaultWorkoutTitleDefaultListModel);
                        defaultWorkoutList.setSelectedIndex(index);

                        FirebaseManager.writeDBdefaultWorkout(defaultWorkoutslist);
                        activateStatus("Default workout saved!");

                    } else {
                       if (savedWorkoutsList.getSelectedIndex() != -1) {
                           Workout savedWorkout = workoutsList.get(savedWorkoutsList.getSelectedIndex());

                           int index = savedWorkoutsList.getSelectedIndex();

                           savedWorkout.getWorkoutData().setTitle(workoutTitle.getText());

                           workoutDefaultListModel.clear();
                           workoutTitleDefaultListModel.clear();

                           for (Workout workout : workoutsList) {
                               workoutDefaultListModel.addElement(workout);
                               workoutTitleDefaultListModel.addElement(workout.getWorkoutData().getTitle());
                           }
                           savedWorkoutsList.setModel(workoutTitleDefaultListModel);
                           savedWorkoutsList.setSelectedIndex(index);

                           FirebaseManager.writeDBworkout(workoutsList);
                           activateStatus("Workout saved!");
                       }

                    }

                    revalidate();
                    repaint();


                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

        });

        saveAsDefault.setText("Default");
        saveAsDefault.setContentAreaFilled(true);
        saveAsDefault.setBackground(new Color(159, 10, 186));
        saveAsDefault.setForeground(Color.white);
        saveAsDefault.setOpaque(true);

        deleteDefaultWorkout.setText("x");
        deleteDefaultWorkout.setPreferredSize(new Dimension(deleteDefaultWorkout.getWidth()/2, deleteDefaultWorkout.getHeight()/2));
        deleteDefaultWorkout.setMaximumSize(deleteDefaultWorkout.getPreferredSize());
        deleteDefaultWorkout.setFont(new Font("Arial", Font.BOLD, 5));
        deleteDefaultWorkout.setContentAreaFilled(false);
        deleteDefaultWorkout.setBackground(new Color(159, 10, 186));
        deleteDefaultWorkout.setForeground(Color.white);
        deleteDefaultWorkout.setOpaque(true);

        deleteDefaultWorkout.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if (!defaultWorkoutslist.isEmpty()) {
                    if (defaultWorkoutList.getSelectedIndex() != -1 && defaultWorkoutList.getSelectedIndex() != defaultWorkoutslist.size()) {

                        int selectedIndex = defaultWorkoutList.getSelectedIndex();
                        defaultWorkoutslist.remove(selectedIndex);

                        defaultWorkoutModel.clear();
                        defaultWorkoutTitleDefaultListModel.clear();

                        for (Workout workout : defaultWorkoutslist) {
                            defaultWorkoutModel.addElement(workout);
                            defaultWorkoutTitleDefaultListModel.addElement(workout.getWorkoutData().getTitle());
                        }
                        defaultWorkoutList.setModel(defaultWorkoutTitleDefaultListModel);

                        try {
                            FirebaseManager.writeDBdefaultWorkout(defaultWorkoutslist);
                        } catch (IOException | ExecutionException | InterruptedException ex) {
                            throw new RuntimeException(ex);
                        }
                        activateStatus("Default workout removed!");
                    }
                }
            }

        });


        setDefaultInfo.setText(("Default workout info here"));
        setDefaultInfo.setVisible(false);
        setDefaultInfo.setLineWrap(true);
        setDefaultInfo.setBackground(new Color(159, 10, 186));
        setDefaultInfo.setForeground(Color.white);
        setDefaultInfo.setFont(new Font("Arial", Font.BOLD, 12));
        setDefaultInfo.setBorder((new LineBorder(new Color(80, 73, 69))));

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

        searchExerciseResult.setFixedCellHeight((int) (getHeight() / 25.5));
        searchExerciseResult.setFont(new Font("Arial", Font.BOLD, (int) (getHeight() / 55.25)));
        searchExerciseResult.setBackground(new Color(22, 22, 22));
        searchExerciseResult.setForeground(new Color(204, 204, 204));
        searchExerciseResult.setPreferredSize(new Dimension(200, searchExerciseResult.getFixedCellHeight() * searchExerciseResult.getModel().getSize()));

        searchExercise.setText("Search for exercise...");
        searchExercise.setFont(new Font("Arial", Font.PLAIN, (int) (getHeight() / 55.25)));
        searchExercise.setForeground(new Color(204, 204, 204));
        searchExercise.setPreferredSize(new Dimension((int) (getWidth() / 8.17692308), (int) (getHeight() / 22.1)));
        searchExercise.setMaximumSize(new Dimension(searchExercise.getPreferredSize()));
        searchExercise.setBackground(new Color(22, 22, 22));
        searchExercise.setBorder(new LineBorder(new Color(80, 73, 69)));

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

        addExerciseAndSetPanel.setPreferredSize(new Dimension(getWidth() / 7, getHeight()));
        addExerciseAndSetPanel.setBackground(new Color(51, 51, 51));

        //Change workout title button
        JButton changeTitle = new JButton();
        changeTitle.setText("Change workout title");
        changeTitle.setBackground(new Color(40, 129, 201));
        changeTitle.setForeground(Color.WHITE);
        changeTitle.setVisible(true);

        addExerciseAndSetPanel.add(saveWorkoutButton);

        workoutPanelTop.add(workoutTitle);
        workoutPanelTop.add(Box.createHorizontalGlue());
        workoutPanelTop.add(saveWorkoutButton);
        workoutPanelTop.add(Box.createHorizontalGlue());
        workoutPanelTop.add(exportWorkoutButton);
        if (UserData.isUserAdmin()) {
            workoutPanelTop.add(saveAsDefault);
        }

        workoutPanel.add(Box.createVerticalGlue());
        workoutPanel.add(workoutPanelTop);
        workoutPanel.add(Box.createVerticalGlue());
        workoutPanel.add(setDefaultInfo);
        workoutPanel.add(workoutScrollPane);
        workoutPanel.add(Box.createVerticalGlue());

        savedWorkoutsPanelTop.add(Box.createHorizontalGlue());
        savedWorkoutsPanelTop.add(savedWorkoutsLabel);
        savedWorkoutsPanelTop.add(Box.createHorizontalGlue());

        savedWorkoutsPanelBottom.add(Box.createHorizontalGlue());
        savedWorkoutsPanelBottom.add(newWorkoutButton);
        savedWorkoutsPanelBottom.add(Box.createHorizontalGlue());
        savedWorkoutsPanelBottom.add(deleteWorkout);
        if (UserData.isUserAdmin()) {
            savedWorkoutsPanelBottom.add(Box.createHorizontalGlue());
            savedWorkoutsPanelBottom.add(deleteDefaultWorkout);
        }
        savedWorkoutsPanelBottom.add(Box.createHorizontalGlue());


        savedWorkoutsPanel.add(Box.createVerticalGlue());
        savedWorkoutsPanel.add(Box.createVerticalGlue());
        savedWorkoutsPanel.add(savedWorkoutsPanelTop);
        savedWorkoutsPanel.add(Box.createVerticalGlue());
        savedWorkoutsPanel.add(Box.createVerticalGlue());
        savedWorkoutsPanel.add(savedWorkoutsScrollPane);
        savedWorkoutsPanel.add(Box.createVerticalGlue());
        savedWorkoutsPanel.add(defaultWorkoutsScrollPane);
        savedWorkoutsPanel.add(Box.createVerticalGlue());
        savedWorkoutsPanel.add(savedWorkoutsPanelBottom);
        savedWorkoutsPanel.add(Box.createVerticalGlue());
        savedWorkoutsPanel.add(Box.createVerticalGlue());

        searchPanel = new SearchPanel(this.getWidth(), this.getHeight(), newExerciseButton);

        mainPanel.add(Box.createHorizontalGlue());
        mainPanel.add(searchPanel);
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
        this.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                super.componentResized(e);
                SwingUtilities.invokeLater(() -> {
                    ProgramPanel.setPanelHeight = getHeight() / 19;

                    wrapper.setPreferredSize(new Dimension(getWidth(), getHeight()));
                    mainPanel.setBounds(0, 0, getWidth(), getHeight());
                    statusPanel.setBounds(0, getHeight(), getWidth(), (int) (getWidth() / 13.26));
                    statusPanel.setPreferredSize(new Dimension(getWidth(), (int) (getWidth() / 13.26)));
                    statusText.setFont(new Font("Arial", Font.BOLD, (int) (getHeight() / 55.25)));
                    ;

                    workoutPanel.setPreferredSize(new Dimension(getWidth() / 2, getHeight()));
                    workoutPanel.setMinimumSize(new Dimension(getWidth() / 2, getHeight()));
                    workoutPanel.setMaximumSize(new Dimension(getWidth() / 2, getHeight()));

                    savedWorkoutsPanel.setPreferredSize(new Dimension(getWidth() / 5, (int) (getHeight())));
                    savedWorkoutsPanel.setMinimumSize(savedWorkoutsPanel.getPreferredSize());
                    savedWorkoutsPanel.setMaximumSize(savedWorkoutsPanel.getPreferredSize());

                    searchPanel.setPreferredSize(new Dimension(getWidth() / 5, getHeight()));
                    searchPanel.setMaximumSize(searchPanel.getPreferredSize());
                    searchPanel.setMinimumSize(searchPanel.getPreferredSize());

                    savedWorkoutsPanelTop.setPreferredSize(new Dimension(getWidth() / 5, getHeight() / 20));
                    savedWorkoutsPanelTop.setMinimumSize(new Dimension(getWidth() / 5, getHeight() / 20));
                    savedWorkoutsPanelTop.setMaximumSize(new Dimension(getWidth() / 5, getHeight() / 20));
                    savedWorkoutsLabel.setMaximumSize(new Dimension((int) (savedWorkoutsPanelTop.getPreferredSize().width / 4), (int) (savedWorkoutsPanelTop.getPreferredSize().height)));
                    savedWorkoutsLabel.setFont(CustomFont.getFont().deriveFont(getHeight() / 27.62500f));

                    savedWorkoutsScrollPane.setPreferredSize(new Dimension(getWidth() / 5, (int) (getHeight() * (8 / 11.28) / 2)));
                    savedWorkoutsScrollPane.setMinimumSize(savedWorkoutsScrollPane.getPreferredSize());
                    savedWorkoutsScrollPane.setMaximumSize(savedWorkoutsScrollPane.getPreferredSize());
                    savedWorkoutsScrollPane.getVerticalScrollBar().setPreferredSize(new Dimension(0, 0));
                    savedWorkoutsScrollPane.setBorder(new LineBorder(new Color(80, 73, 69), getHeight() / 663));

                    defaultWorkoutsScrollPane.setPreferredSize(new Dimension(getWidth() / 5, (int) (getHeight() * (8 / 11.28) / 2)));
                    defaultWorkoutsScrollPane.setMinimumSize(savedWorkoutsScrollPane.getPreferredSize());
                    defaultWorkoutsScrollPane.setMaximumSize(savedWorkoutsScrollPane.getPreferredSize());
                    defaultWorkoutsScrollPane.getVerticalScrollBar().setPreferredSize(new Dimension(0, 0));
                    defaultWorkoutsScrollPane.setBorder(new LineBorder(new Color(80, 73, 69), getHeight() / 663));

                    savedWorkoutsList.setFixedCellHeight((int) (getHeight() / 22.5));
                    savedWorkoutsList.setFont(new Font("Arial", Font.BOLD, (int) (getHeight() / 55.25)));

                    defaultWorkoutList.setFixedCellHeight((int) (getHeight() / 22.5));
                    defaultWorkoutList.setFont(new Font("Arial", Font.BOLD, (int) (getHeight() / 55.25)));

                    newWorkoutButtonImage = new ImageIcon(ResourcePath.getResourcePath("new_workout_button.png"));
                    scaledNewWorkoutButtonImage = newWorkoutButtonImage.getImage().getScaledInstance((int) (getWidth() / 14.1733333), (int) (getHeight() / 22.862069), Image.SCALE_SMOOTH);
                    scaledNewWorkoutIcon = new ImageIcon(scaledNewWorkoutButtonImage);
                    newWorkoutButton.setIcon(scaledNewWorkoutIcon);
                    newWorkoutButton.setPreferredSize(new Dimension(scaledNewWorkoutIcon.getIconWidth(), scaledNewWorkoutIcon.getIconHeight()));
                    newWorkoutButton.setMaximumSize(newWorkoutButton.getPreferredSize());

                    removeWorkoutButtonImage = new ImageIcon(ResourcePath.getResourcePath("remove_workout_button.png"));
                    scaledRemoveWorkoutButtonImage = removeWorkoutButtonImage.getImage().getScaledInstance((int) (getWidth() / 28.7297297), (int) (getHeight() / 22.862069), Image.SCALE_SMOOTH);
                    scaledRemoveWorkoutIcon = new ImageIcon(scaledRemoveWorkoutButtonImage);
                    deleteWorkout.setIcon(scaledRemoveWorkoutIcon);
                    deleteWorkout.setFont(new Font("Arial", Font.BOLD, (int) (getHeight() / 66.3)));
                    deleteWorkout.setPreferredSize(new Dimension(scaledRemoveWorkoutIcon.getIconWidth(), scaledRemoveWorkoutIcon.getIconHeight()));
                    deleteWorkout.setMaximumSize(deleteWorkout.getPreferredSize());

                    deleteDefaultWorkout.setFont(new Font("Arial", Font.BOLD, (int) (getHeight() / 66.3)));
                    deleteDefaultWorkout.setPreferredSize(new Dimension(scaledRemoveWorkoutIcon.getIconWidth(), scaledRemoveWorkoutIcon.getIconHeight()));
                    deleteDefaultWorkout.setMaximumSize(deleteWorkout.getPreferredSize());

                    workoutPanelTop.setPreferredSize(new Dimension(getWidth() / 2, getHeight() / 20));
                    workoutPanelTop.setMinimumSize(new Dimension(getWidth() / 2, getHeight() / 20));
                    workoutPanelTop.setMaximumSize(new Dimension(getWidth() / 2, getHeight() / 20));

                    workoutScrollPane.setPreferredSize(new Dimension(getWidth() / 2, getHeight() * 8 / 10));
                    workoutScrollPane.setMinimumSize(new Dimension(getWidth() / 2, getHeight() * 8 / 10));
                    workoutScrollPane.setMaximumSize(new Dimension(getWidth() / 2, getHeight() * 8 / 10));
                    workoutScrollPane.getVerticalScrollBar().setUnitIncrement((int) (getHeight() / 110.5));
                    workoutScrollPane.setBorder(new LineBorder(new Color(80, 73, 69), getHeight() / 663));

                    workoutTitle.setFont(new Font("Arial", Font.PLAIN, (int) (getHeight() / 55.25)));
                    workoutTitle.setPreferredSize(new Dimension(getWidth() / 3, (int) (getHeight() / 22.1)));
                    workoutTitle.setMaximumSize(workoutTitle.getPreferredSize());

                    saveButton = new ImageIcon(ResourcePath.getResourcePath("save_workout_button.png"));
                    scaledsaveButton = saveButton.getImage().getScaledInstance((int) (getWidth() / 7.59285714), (int) (getHeight() / 22.862069), Image.SCALE_SMOOTH);
                    scaledsaveButtonIcon = new ImageIcon(scaledsaveButton);
                    saveWorkoutButton.setIcon(scaledsaveButtonIcon);
                    saveWorkoutButton.setPreferredSize(new Dimension(scaledsaveButtonIcon.getIconWidth(), scaledsaveButtonIcon.getIconHeight()));
                    saveWorkoutButton.setMaximumSize(new Dimension(scaledsaveButtonIcon.getIconWidth(), scaledsaveButtonIcon.getIconHeight()));

                    exportIcon = new ImageIcon(ResourcePath.getResourcePath("exportIcon.png"));
                    scaledExportIcon = exportIcon.getImage().getScaledInstance(getWidth() / 30, getHeight() / 20, Image.SCALE_SMOOTH);
                    exportIcon = new ImageIcon(scaledExportIcon);
                    exportWorkoutButton.setIcon(exportIcon);
                    exportWorkoutButton.setPreferredSize(new Dimension(exportIcon.getIconWidth(), exportIcon.getIconHeight()));

                    rescaleWorkoutPanels();
                });
            }
        });
    }

    //Trigger the status panel
    public void activateStatus(String text) {
        statusText.setText(text);
        if (text.contains("removed")) {
            statusPanel.setBackground(Color.RED);
        } else if (text.contains("created") || text.contains("exported") || text.contains("saved")) {
            statusPanel.setBackground(new Color(46, 148, 76));
        }

        statusPanel.setBounds(0, getHeight() - (int) (getHeight() / 13.26), mainPanel.getWidth(), (int) (getHeight() / 13.26));
        statusPanel.revalidate();
        statusPanel.repaint();
        statusDelayCounter = 20;
        statusPanel.setVisible(true);
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
        exerciseName.setPreferredSize(new Dimension(getWidth() / 2, ProgramPanel.setPanelHeight));
        exerciseName.setMinimumSize(exerciseName.getPreferredSize());
        exerciseName.setMaximumSize(exerciseName.getPreferredSize());
        exerciseName.setText(currentExercise.getName());
        exerciseName.setFont(new Font("Arial", Font.BOLD, (int) (getHeight() / 33.15)));
        exerciseName.setForeground(workoutPanelTextColor);
        exerciseName.setAlignmentX(Component.LEFT_ALIGNMENT);
        exerciseNameTitlePanel.add(exerciseName);
        mainExercisePanel.add(exerciseNameTitlePanel);

        // Label to hold favorite symbol
        Font emojiFont = new Font("Segoe UI Emoji", Font.PLAIN, 15);
        JLabel favoriteLabel = new JLabel();
        favoriteLabel.setFont(emojiFont);
        favoriteLabel.setName("favoriteLabel");
        favoriteLabel.setText("\uD83D\uDCAA");
        favoriteLabel.setForeground(new Color(196, 196, 49));
        if (UserData.getFavoriteExercises().contains(currentExercise)) {
            exerciseName.setText(exerciseName.getText() + " *");
        }

        // Panel to hold the titles of Set, Rep, Weight, RIR
        JPanel setRepWeightRirTitleNPanel = new JPanel();
        setRepWeightRirTitleNPanel.setName("setRepWeightRirTitleNPanel");
        setRepWeightRirTitleNPanel.setBackground(Color.GREEN);
        setRepWeightRirTitleNPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
        setRepWeightRirTitleNPanel.setPreferredSize(new Dimension(getWidth() / 2, ProgramPanel.setPanelHeight));
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
        leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));
        leftPanel.setPreferredSize(new Dimension(getWidth() / 2, ProgramPanel.setPanelHeight));
        leftPanel.setMinimumSize(leftPanel.getPreferredSize());
        leftPanel.setMaximumSize(leftPanel.getPreferredSize());
        setRepWeightRirTitleNPanel.add(leftPanel, BorderLayout.WEST);

        // Label to hold "Set"
        JLabel setLabel = new JLabel();
        setLabel.setName("setLabel");
        setLabel.setText(" Set");
        setLabel.setForeground(workoutPanelTextColor);
        setLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        leftPanel.add(Box.createVerticalGlue());
        leftPanel.add(setLabel);
        leftPanel.add(Box.createVerticalGlue());

        //Remove exercise-button
        JButton removeExercise = new JButton(scaledRemoveExerciseIcon);
        removeExercise.setName("removeExercise");
        removeExercise.setPreferredSize(new Dimension(getWidth() / 15, ProgramPanel.setPanelHeight));
        removeExercise.setMinimumSize(removeExercise.getPreferredSize());
        removeExercise.setMaximumSize(removeExercise.getPreferredSize());
        removeExercise.setMargin(new Insets(0, 0, 0, 0));
        removeExercise.setForeground(Color.white);
        removeExercise.setContentAreaFilled(false);
        removeExercise.setFont(new Font("Arial", Font.BOLD, 12));
        removeExercise.setBorderPainted(false);
        removeExercise.setFocusPainted(false);
        removeExercise.addActionListener(_ -> {
            workoutContainer.getWorkoutData().setTotalWorkoutHeight(workoutContainer.getWorkoutData().getTotalWorkoutHeight() - (4 * ProgramPanel.setPanelHeight));
            for (Component comp : mainExercisePanel.getComponents()) {
                if ("setPanel".equals(comp.getName())) {
                    workoutContainer.getWorkoutData().setTotalWorkoutHeight(workoutContainer.getWorkoutData().getTotalWorkoutHeight() - (ProgramPanel.setPanelHeight));
                }
            }
            workoutContainer.setPreferredSize(new Dimension(workoutContainer.getWidth(), workoutContainer.getWorkoutData().getTotalWorkoutHeight()));
            workoutContainer.getWorkoutData().deleteExercise(exerciseId);
            mainExercisePanel.removeAll();
            workoutContainer.repaint();
            workoutContainer.revalidate();
        });
        if (workoutContainer.isWorkoutDefault()) {
            removeExercise.setVisible(false);
        }

        // Title Panel to align Rep, RIR and WEIGHT to right
        JPanel rightPanel = new JPanel();
        rightPanel.setName("rightPanel");
        rightPanel.setOpaque(false);
        //rightPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        rightPanel.setLayout(new BoxLayout(rightPanel, BoxLayout.X_AXIS));
        rightPanel.setPreferredSize(new Dimension((int) (getWidth() * 0.155172414), (int) (getHeight() / 55.25)));
        rightPanel.setMinimumSize(rightPanel.getPreferredSize());
        rightPanel.setMaximumSize(rightPanel.getPreferredSize());
        setRepWeightRirTitleNPanel.add(rightPanel, BorderLayout.EAST);

        // Label to hold "Reps"
        JLabel repsLabel = new JLabel();
        repsLabel.setName("repsLabel");
        repsLabel.setText("Reps");
        repsLabel.setForeground(workoutPanelTextColor);
        repsLabel.setAlignmentY(Component.CENTER_ALIGNMENT);
        repsLabel.setPreferredSize(new Dimension(repsLabel.getFontMetrics(repsLabel.getFont()).stringWidth(repsLabel.getText()),
                repsLabel.getFontMetrics(repsLabel.getFont()).getHeight()));
        repsLabel.setMinimumSize(repsLabel.getPreferredSize());
        repsLabel.setMaximumSize(repsLabel.getPreferredSize());
        rightPanel.add(repsLabel);
        rightPanel.add(Box.createHorizontalGlue());

        // Label to hold "Weight"
        JLabel weightLabel = new JLabel();
        weightLabel.setName("weightLabel");
        weightLabel.setText("Weight");
        weightLabel.setForeground(workoutPanelTextColor);
        weightLabel.setPreferredSize(new Dimension(weightLabel.getFontMetrics(weightLabel.getFont()).stringWidth(weightLabel.getText()),
                weightLabel.getFontMetrics(weightLabel.getFont()).getHeight()));
        weightLabel.setMinimumSize(weightLabel.getPreferredSize());
        weightLabel.setMaximumSize(weightLabel.getPreferredSize());
        weightLabel.setAlignmentY(Component.CENTER_ALIGNMENT);
        rightPanel.add(weightLabel);
        rightPanel.add(Box.createHorizontalGlue());
        //Label to hold "RIR"
        JLabel rirLabel = new JLabel();
        rirLabel.setName("rirLabel");
        rirLabel.setText("RIR");
        rirLabel.setForeground(workoutPanelTextColor);
        rirLabel.setPreferredSize(new Dimension(rirLabel.getFontMetrics(rirLabel.getFont()).stringWidth(rirLabel.getText()),
                rirLabel.getFontMetrics(rirLabel.getFont()).getHeight()));
        rirLabel.setMinimumSize(rirLabel.getPreferredSize());
        rirLabel.setMaximumSize(rirLabel.getPreferredSize());
        rirLabel.setAlignmentY(Component.CENTER_ALIGNMENT);
        rightPanel.add(rirLabel);
        rightPanel.add(Box.createHorizontalGlue());
        rightPanel.add(Box.createHorizontalGlue());
        rightPanel.add(Box.createHorizontalGlue());
        rightPanel.add(Box.createHorizontalGlue());
        rightPanel.add(Box.createHorizontalGlue());
        rightPanel.add(Box.createHorizontalGlue());
        rightPanel.add(Box.createHorizontalGlue());

        // "Add set"- button
        JButton addSet = new JButton(scaledNewSetIcon);
        addSet.setName("addSet");
        addSet.setPreferredSize(new Dimension(getWidth() / 35, ProgramPanel.setPanelHeight));
        addSet.setMinimumSize(addSet.getPreferredSize());
        addSet.setMaximumSize(addSet.getPreferredSize());
        addSet.setMargin(new Insets(0, 0, 0, 0));
        addSet.setContentAreaFilled(false);
        addSet.setBorderPainted(false);
        addSet.setFocusPainted(false);
        addSet.setBorder(null);

        workoutContainer.getWorkoutData().setTotalWorkoutHeight(workoutContainer.getWorkoutData().getTotalWorkoutHeight() + (4 * ProgramPanel.setPanelHeight)); //Lägger till höjden för de 4 paneler som skapas när en övning läggs till

        addSet.addActionListener(_ -> {
            addSet(exerciseId, currentExercise, mainExercisePanel, ProgramPanel.setPanelHeight, workoutContainer);
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

        workoutContainer.getWorkoutData().setTotalWorkoutHeight(workoutContainer.getWorkoutData().getTotalWorkoutHeight() + (ProgramPanel.setPanelHeight)); //Lägger till höjden settet som läggs till

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
        setPanel.setPreferredSize(new Dimension(instance.getWidth() / 2, ProgramPanel.setPanelHeight));
        setPanel.setMinimumSize(setPanel.getPreferredSize());
        setPanel.setMaximumSize(setPanel.getPreferredSize());
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
        setLabel.setFont(new Font("Arial", Font.PLAIN, (int) (instance.getHeight() / 55.25)));
        leftPanel.add(setLabel);

        JButton deleteSet = new JButton(scaledRemoveSetIcon);
        deleteSet.setName("deleteSet");
        deleteSet.setContentAreaFilled(false);
        deleteSet.setBorder(null);
        deleteSet.setPreferredSize(new Dimension(instance.getWidth() / 45, ProgramPanel.setPanelHeight));
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
        rightPanel.setOpaque(false);
        rightPanel.setName("rightPanel");
        rightPanel.setLayout(new BoxLayout(rightPanel, BoxLayout.X_AXIS));
        rightPanel.setPreferredSize(new Dimension((int) (instance.getWidth() * 0.155172414), (int) (instance.getHeight() / 55.25)));
        rightPanel.setMinimumSize(rightPanel.getPreferredSize());
        rightPanel.setMaximumSize(rightPanel.getPreferredSize());


        JTextField weightAmount = new JTextField();
        weightAmount.setName("weightAmount");
        JTextField rirAmount = new JTextField();
        rirAmount.setName("rirAmount");

        JTextField repsAmount = new JTextField();
        repsAmount.setName("repsAmount");

        repsAmount.setForeground(new Color(204, 204, 204));
        repsAmount.setBackground(new Color(22, 22, 22));
        repsAmount.setPreferredSize(new Dimension((int) (instance.getWidth() / 30.3714286), (int) (instance.getHeight() / 33.15)));
        repsAmount.setMaximumSize(repsAmount.getPreferredSize());

        repsAmount.setBorder(null);
        repsAmount.setText("0");
        repsAmount.setBorder(new LineBorder(new Color(129, 115, 103), 1));


        weightAmount.setBackground(new Color(22, 22, 22));
        weightAmount.setForeground(new Color(204, 204, 204));
        weightAmount.setPreferredSize(new Dimension((int) (instance.getWidth() / 30.3714286), (int) (instance.getHeight() / 33.15)));
        weightAmount.setMaximumSize(weightAmount.getPreferredSize());
        weightAmount.setBorder(null);
        weightAmount.setText("0");
        weightAmount.setBorder(new LineBorder(new Color(129, 115, 103), 1));


        rirAmount.setPreferredSize(new Dimension((int) (instance.getWidth() / 30.3714286), (int) (instance.getHeight() / 33.15)));
        rirAmount.setMaximumSize(rirAmount.getPreferredSize());
        rirAmount.setForeground(new Color(204, 204, 204));
        rirAmount.setBackground(new Color(22, 22, 22));
        rirAmount.setBorder(null);
        rirAmount.setText("0");
        rirAmount.setBorder(new LineBorder(new Color(129, 115, 103), 1));

        repsAmount.setFont(new Font("Arial", Font.BOLD, (int) (instance.getHeight() / 55.25)));
        weightAmount.setFont(new Font("Arial", Font.BOLD, (int) (instance.getHeight() / 55.25)));
        rirAmount.setFont(new Font("Arial", Font.BOLD, (int) (instance.getHeight() / 55.25)));

        setPanel.add(rightPanel, BorderLayout.EAST);

        rightPanel.add(repsAmount);
        rightPanel.add(Box.createHorizontalGlue());
        rightPanel.add(weightAmount);
        rightPanel.add(Box.createHorizontalGlue());
        rightPanel.add(rirAmount);
        rightPanel.add(Box.createHorizontalGlue());
        rightPanel.add(moveSetUp);
        rightPanel.add(Box.createHorizontalGlue());
        rightPanel.add(moveSetDown);
        rightPanel.add(Box.createHorizontalGlue());

        leftPanel.add(deleteSet);
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
        workoutContainer.getWorkoutData().setTotalWorkoutHeight(workoutContainer.getWorkoutData().getTotalWorkoutHeight() - (ProgramPanel.setPanelHeight));
        workoutContainer.setPreferredSize(new Dimension(logContainer.getWidth(), workoutContainer.getWorkoutData().getTotalWorkoutHeight()));
        parentPanel.revalidate();
        parentPanel.repaint();

    }

    public void rescaleWorkoutPanels() {

        for (Workout workout : workoutsList) {
            workout.getWorkoutData().setTotalWorkoutHeight(0);
            for (Component comp1 : workout.getComponents()) {
                if (comp1.getName() != null) {
                    if (comp1.getName().equals("mainExercisePanel")) {
                        workout.getWorkoutData().setTotalWorkoutHeight(workout.getWorkoutData().getTotalWorkoutHeight() + (4 * ProgramPanel.setPanelHeight));
                        JPanel mainExercisePanel = (JPanel) comp1;

                        for (Component comp2 : mainExercisePanel.getComponents()) {
                            if (comp2.getName().equals("setRepWeightRirTitleNPanel")) {
                                JPanel setRepWeightRirTitleNPanel = (JPanel) comp2;
                                setRepWeightRirTitleNPanel.setPreferredSize(new Dimension(getWidth() / 2, ProgramPanel.setPanelHeight));
                                setRepWeightRirTitleNPanel.setMinimumSize(setRepWeightRirTitleNPanel.getPreferredSize());
                                setRepWeightRirTitleNPanel.setMaximumSize(setRepWeightRirTitleNPanel.getPreferredSize());
                                for (Component component : setRepWeightRirTitleNPanel.getComponents()) {
                                    if (component.getName().equals("leftPanel")) {
                                        JPanel leftPanel = (JPanel) component;
                                        for (Component leftPanelComp : leftPanel.getComponents()) {
                                            if (leftPanelComp.getName() != null) {
                                                if (leftPanelComp.getName().equals("setLabel")) {
                                                    JLabel setLabel = (JLabel) leftPanelComp;
                                                    setLabel.setFont(new Font("Arial", Font.PLAIN, (int) (getHeight() / 55.25)));
                                                }
                                            }
                                        }
                                    }
                                    if (component.getName().equals("rightPanel")) {
                                        JPanel rightPanel = (JPanel) component;
                                        rightPanel.setPreferredSize(new Dimension((int) (getWidth() * 0.155172414), (int) (getHeight() / 55.25)));
                                        rightPanel.setMinimumSize(rightPanel.getPreferredSize());
                                        rightPanel.setMaximumSize(rightPanel.getPreferredSize());
                                        for (Component labelsComp : rightPanel.getComponents()) {
                                            if (labelsComp.getName() != null) {
                                                JLabel labels = (JLabel) labelsComp;
                                                labels.setFont(new Font("Arial", Font.BOLD, (int) (getHeight() / 55.25)));
                                                labels.setPreferredSize(new Dimension(labels.getFontMetrics(labels.getFont()).stringWidth(labels.getText()),
                                                        labels.getFontMetrics(labels.getFont()).getHeight()));
                                                labels.setMinimumSize(labels.getPreferredSize());
                                                labels.setMaximumSize(labels.getPreferredSize());
                                            }
                                        }
                                    }
                                }
                            }

                            if ("addSet".equals(comp2.getName())) {
                                JButton addSet = (JButton) comp2;

                                scaledNewSetButtonImage = newSetButtonImage.getImage().getScaledInstance((int) (getWidth() / 35.4333333), (int) (getHeight() / 19.5), Image.SCALE_SMOOTH);
                                scaledNewSetIcon = new ImageIcon(scaledNewSetButtonImage);

                                addSet.setIcon(scaledNewSetIcon);
                                addSet.setPreferredSize(new Dimension(getWidth() / 35, ProgramPanel.setPanelHeight));
                                addSet.setMinimumSize(addSet.getPreferredSize());
                                addSet.setMaximumSize(addSet.getPreferredSize());
                            }
                            if ("setPanel".equals(comp2.getName())) {
                                JPanel setPanel = (JPanel) comp2;
                                setPanel.setPreferredSize(new Dimension(getWidth() / 2, ProgramPanel.setPanelHeight));
                                setPanel.setMinimumSize(setPanel.getPreferredSize());
                                setPanel.setMaximumSize(setPanel.getPreferredSize());

                                for (Component compRight : setPanel.getComponents()) {
                                    if (compRight.getName() != null) {
                                        if ("rightPanel".equals(compRight.getName())) {
                                            JPanel rightPanel = (JPanel) compRight;
                                            rightPanel.setPreferredSize(new Dimension((int) (instance.getWidth() * 0.155172414), (int) (instance.getHeight() / 55.25)));
                                            rightPanel.setMinimumSize(rightPanel.getPreferredSize());
                                            rightPanel.setMaximumSize(rightPanel.getPreferredSize());
                                            for (Component compMoveSetUp : rightPanel.getComponents()) {
                                                if (compMoveSetUp.getName() != null) {
                                                    if (compMoveSetUp.getName().equals("repsAmount")) {
                                                        JTextField repsAmount = (JTextField) compMoveSetUp;
                                                        repsAmount.setFont(new Font("Arial", Font.BOLD, (int) (getHeight() / 55.25)));
                                                        repsAmount.setPreferredSize(new Dimension((int) (instance.getWidth() / 30.3714286), (int) (instance.getHeight() / 33.15)));
                                                        repsAmount.setMaximumSize(repsAmount.getPreferredSize());
                                                    }
                                                    if (compMoveSetUp.getName().equals("weightAmount")) {
                                                        JTextField weightAmount = (JTextField) compMoveSetUp;
                                                        weightAmount.setFont(new Font("Arial", Font.BOLD, (int) (getHeight() / 55.25)));
                                                        weightAmount.setPreferredSize(new Dimension((int) (instance.getWidth() / 30.3714286), (int) (instance.getHeight() / 33.15)));
                                                        weightAmount.setMaximumSize(weightAmount.getPreferredSize());
                                                    }
                                                    if (compMoveSetUp.getName().equals("rirAmount")) {
                                                        JTextField rirAmount = (JTextField) compMoveSetUp;
                                                        rirAmount.setFont(new Font("Arial", Font.BOLD, (int) (getHeight() / 55.25)));
                                                        rirAmount.setPreferredSize(new Dimension((int) (instance.getWidth() / 30.3714286), (int) (instance.getHeight() / 33.15)));
                                                        rirAmount.setMaximumSize(rirAmount.getPreferredSize());
                                                    }
                                                    if ("moveSetUp".equals(compMoveSetUp.getName())) {
                                                        JButton moveSetUp = (JButton) compMoveSetUp;

                                                        scaledMoveSetUpButtonImage = moveSetUpButtonImage.getImage().getScaledInstance((int) (getWidth() / 88.5833333), (int) (getHeight() / 30.1363636), Image.SCALE_SMOOTH);
                                                        scaledMoveSetUpIcon = new ImageIcon(scaledMoveSetUpButtonImage);

                                                        moveSetUp.setIcon(scaledMoveSetUpIcon);
                                                    }
                                                    if ("moveSetDown".equals(compMoveSetUp.getName())) {
                                                        JButton moveSetDown = (JButton) compMoveSetUp;

                                                        scaledMoveSetDownButtonImage = moveSetDownButtonImage.getImage().getScaledInstance((int) (getWidth() / 88.5833333), (int) (getHeight() / 30.1363636), Image.SCALE_SMOOTH);
                                                        scaledMoveSetDownIcon = new ImageIcon(scaledMoveSetDownButtonImage);

                                                        moveSetDown.setIcon(scaledMoveSetDownIcon);
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }

                                for (Component compLeftPanel : setPanel.getComponents()) {
                                    if ("leftPanel".equals(compLeftPanel.getName())) {
                                        JPanel leftPanel = (JPanel) compLeftPanel;

                                        leftPanel.setPreferredSize(new Dimension(getWidth(), ProgramPanel.setPanelHeight));
                                        leftPanel.setMinimumSize(leftPanel.getPreferredSize());
                                        leftPanel.setMaximumSize(leftPanel.getPreferredSize());
                                        for (Component compDeleteSet : leftPanel.getComponents()) {
                                            if (compDeleteSet.getName() != null) {
                                                if ("deleteSet".equals(compDeleteSet.getName())) {
                                                    JButton deleteSet = (JButton) compDeleteSet;

                                                    scaledRemoveSetButtonImage = removeSetButtonImage.getImage().getScaledInstance((int) (getWidth() / 46.2173913043), (int) (getHeight() / 26.52), Image.SCALE_SMOOTH);
                                                    scaledRemoveSetIcon = new ImageIcon(scaledRemoveSetButtonImage);

                                                    deleteSet.setIcon(scaledRemoveSetIcon);
                                                    deleteSet.setPreferredSize(new Dimension(getWidth() / 45, ProgramPanel.setPanelHeight));
                                                    deleteSet.setMinimumSize(deleteSet.getPreferredSize());
                                                    deleteSet.setMaximumSize(deleteSet.getPreferredSize());
                                                }
                                                if (compDeleteSet.getName().equals("setLabel")) {
                                                    JLabel setLabel = (JLabel) compDeleteSet;

                                                    setLabel.setFont(new Font("Arial", Font.PLAIN, (int) (getHeight() / 55.25)));
                                                }
                                            }
                                        }
                                    }
                                }
                                workout.getWorkoutData().setTotalWorkoutHeight(workout.getWorkoutData().getTotalWorkoutHeight() + (ProgramPanel.setPanelHeight));
                            }

                            if (comp2.getName() != null) {
                                if (comp2.getName().equals("exerciseNameTitlePanel")) {
                                    JPanel exerciseNameTitlePanel = (JPanel) comp2;
                                    for (Component comp3 : exerciseNameTitlePanel.getComponents()) {
                                        if (comp3.getName().equals("removeExercise")) {
                                            JButton removeExercise = (JButton) comp3;

                                            scaledRemoveExerciseButtonImage = removeExerciseButtonImage.getImage().getScaledInstance((int) (getWidth() / 14.971831),
                                                    (int) (getHeight() / 19.5), Image.SCALE_SMOOTH);
                                            scaledRemoveExerciseIcon = new ImageIcon(scaledRemoveExerciseButtonImage);

                                            removeExercise.setIcon(scaledRemoveExerciseIcon);
                                            removeExercise.setPreferredSize(new Dimension(getWidth() / 15, ProgramPanel.setPanelHeight));
                                            removeExercise.setMinimumSize(removeExercise.getPreferredSize());
                                            removeExercise.setMaximumSize(removeExercise.getPreferredSize());

                                        }
                                        if (comp3.getName().equals("exerciseName")) {
                                            JLabel exerciseName = (JLabel) comp3;
                                            exerciseName.setPreferredSize(new Dimension(getWidth(), ProgramPanel.setPanelHeight));
                                            exerciseName.setMinimumSize(exerciseName.getPreferredSize());
                                            exerciseName.setMaximumSize(exerciseName.getPreferredSize());
                                            exerciseName.setFont(new Font("Arial", Font.BOLD, (int) (getHeight() / 33.15)));
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
            workout.setPreferredSize(new Dimension(workout.getWidth(), workout.getWorkoutData().getTotalWorkoutHeight()));
            workout.setMinimumSize(new Dimension(workout.getWidth(), workout.getWorkoutData().getTotalWorkoutHeight()));
            workout.setMaximumSize(new Dimension(workout.getWidth(), workout.getWorkoutData().getTotalWorkoutHeight()));

            workout.repaint();
            workout.revalidate();

            System.out.println(workout.getPreferredSize() + ":" + workout.getWorkoutData().getTotalWorkoutHeight());

        }
        for (Workout workout : defaultWorkoutslist) {
            workout.getWorkoutData().setTotalWorkoutHeight(0);
            for (Component comp1 : workout.getComponents()) {
                if (comp1.getName() != null) {
                    if (comp1.getName().equals("getDefaultInfo")) {
                        JLabel getDefaultInfo = (JLabel) comp1;
                        getDefaultInfo.setFont(new Font("Arial", Font.PLAIN, (int) (getHeight() / 33.15)));
                    }
                    if (comp1.getName().equals("mainExercisePanel")) {
                        workout.getWorkoutData().setTotalWorkoutHeight(workout.getWorkoutData().getTotalWorkoutHeight() + (4 * ProgramPanel.setPanelHeight));
                        JPanel mainExercisePanel = (JPanel) comp1;

                        for (Component comp2 : mainExercisePanel.getComponents()) {
                            if (comp2.getName().equals("setRepWeightRirTitleNPanel")) {
                                JPanel setRepWeightRirTitleNPanel = (JPanel) comp2;
                                setRepWeightRirTitleNPanel.setPreferredSize(new Dimension(getWidth() / 2, ProgramPanel.setPanelHeight));
                                setRepWeightRirTitleNPanel.setMinimumSize(setRepWeightRirTitleNPanel.getPreferredSize());
                                setRepWeightRirTitleNPanel.setMaximumSize(setRepWeightRirTitleNPanel.getPreferredSize());
                                for (Component component : setRepWeightRirTitleNPanel.getComponents()) {
                                    if (component.getName().equals("leftPanel")) {
                                        JPanel leftPanel = (JPanel) component;
                                        for (Component leftPanelComp : leftPanel.getComponents()) {
                                            if (leftPanelComp.getName() != null) {
                                                if (leftPanelComp.getName().equals("setLabel")) {
                                                    JLabel setLabel = (JLabel) leftPanelComp;
                                                    setLabel.setFont(new Font("Arial", Font.PLAIN, (int) (getHeight() / 55.25)));
                                                }
                                            }
                                        }
                                    }
                                    if (component.getName().equals("rightPanel")) {
                                        JPanel rightPanel = (JPanel) component;
                                        rightPanel.setPreferredSize(new Dimension((int) (getWidth() * 0.155172414), (int) (getHeight() / 55.25)));
                                        rightPanel.setMinimumSize(rightPanel.getPreferredSize());
                                        rightPanel.setMaximumSize(rightPanel.getPreferredSize());
                                        for (Component labelsComp : rightPanel.getComponents()) {
                                            if (labelsComp.getName() != null) {
                                                JLabel labels = (JLabel) labelsComp;
                                                labels.setFont(new Font("Arial", Font.BOLD, (int) (getHeight() / 55.25)));
                                                labels.setPreferredSize(new Dimension(labels.getFontMetrics(labels.getFont()).stringWidth(labels.getText()),
                                                        labels.getFontMetrics(labels.getFont()).getHeight()));
                                                labels.setMinimumSize(labels.getPreferredSize());
                                                labels.setMaximumSize(labels.getPreferredSize());
                                            }
                                        }
                                    }
                                }
                            }

                            if ("addSet".equals(comp2.getName())) {
                                JButton addSet = (JButton) comp2;

                                scaledNewSetButtonImage = newSetButtonImage.getImage().getScaledInstance((int) (getWidth() / 35.4333333), (int) (getHeight() / 19.5), Image.SCALE_SMOOTH);
                                scaledNewSetIcon = new ImageIcon(scaledNewSetButtonImage);

                                addSet.setIcon(scaledNewSetIcon);
                                addSet.setPreferredSize(new Dimension(getWidth() / 35, ProgramPanel.setPanelHeight));
                                addSet.setMinimumSize(addSet.getPreferredSize());
                                addSet.setMaximumSize(addSet.getPreferredSize());
                            }
                            if ("setPanel".equals(comp2.getName())) {
                                JPanel setPanel = (JPanel) comp2;
                                setPanel.setPreferredSize(new Dimension(getWidth() / 2, ProgramPanel.setPanelHeight));
                                setPanel.setMinimumSize(setPanel.getPreferredSize());
                                setPanel.setMaximumSize(setPanel.getPreferredSize());

                                for (Component compRight : setPanel.getComponents()) {
                                    if (compRight.getName() != null) {
                                        if ("rightPanel".equals(compRight.getName())) {
                                            JPanel rightPanel = (JPanel) compRight;
                                            rightPanel.setPreferredSize(new Dimension((int) (instance.getWidth() * 0.155172414), (int) (instance.getHeight() / 55.25)));
                                            rightPanel.setMinimumSize(rightPanel.getPreferredSize());
                                            rightPanel.setMaximumSize(rightPanel.getPreferredSize());
                                            for (Component compMoveSetUp : rightPanel.getComponents()) {
                                                if (compMoveSetUp.getName() != null) {
                                                    if (compMoveSetUp.getName().equals("repsAmount")) {
                                                        JTextField repsAmount = (JTextField) compMoveSetUp;
                                                        repsAmount.setFont(new Font("Arial", Font.BOLD, (int) (getHeight() / 55.25)));
                                                        repsAmount.setPreferredSize(new Dimension((int) (instance.getWidth() / 30.3714286), (int) (instance.getHeight() / 33.15)));
                                                        repsAmount.setMaximumSize(repsAmount.getPreferredSize());
                                                    }
                                                    if (compMoveSetUp.getName().equals("weightAmount")) {
                                                        JTextField weightAmount = (JTextField) compMoveSetUp;
                                                        weightAmount.setFont(new Font("Arial", Font.BOLD, (int) (getHeight() / 55.25)));
                                                        weightAmount.setPreferredSize(new Dimension((int) (instance.getWidth() / 30.3714286), (int) (instance.getHeight() / 33.15)));
                                                        weightAmount.setMaximumSize(weightAmount.getPreferredSize());
                                                    }
                                                    if (compMoveSetUp.getName().equals("rirAmount")) {
                                                        JTextField rirAmount = (JTextField) compMoveSetUp;
                                                        rirAmount.setFont(new Font("Arial", Font.BOLD, (int) (getHeight() / 55.25)));
                                                        rirAmount.setPreferredSize(new Dimension((int) (instance.getWidth() / 30.3714286), (int) (instance.getHeight() / 33.15)));
                                                        rirAmount.setMaximumSize(rirAmount.getPreferredSize());
                                                    }
                                                    if ("moveSetUp".equals(compMoveSetUp.getName())) {
                                                        JButton moveSetUp = (JButton) compMoveSetUp;

                                                        scaledMoveSetUpButtonImage = moveSetUpButtonImage.getImage().getScaledInstance((int) (getWidth() / 88.5833333), (int) (getHeight() / 30.1363636), Image.SCALE_SMOOTH);
                                                        scaledMoveSetUpIcon = new ImageIcon(scaledMoveSetUpButtonImage);

                                                        moveSetUp.setIcon(scaledMoveSetUpIcon);
                                                    }
                                                    if ("moveSetDown".equals(compMoveSetUp.getName())) {
                                                        JButton moveSetDown = (JButton) compMoveSetUp;

                                                        scaledMoveSetDownButtonImage = moveSetDownButtonImage.getImage().getScaledInstance((int) (getWidth() / 88.5833333), (int) (getHeight() / 30.1363636), Image.SCALE_SMOOTH);
                                                        scaledMoveSetDownIcon = new ImageIcon(scaledMoveSetDownButtonImage);

                                                        moveSetDown.setIcon(scaledMoveSetDownIcon);
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }

                                for (Component compLeftPanel : setPanel.getComponents()) {
                                    if ("leftPanel".equals(compLeftPanel.getName())) {
                                        JPanel leftPanel = (JPanel) compLeftPanel;

                                        leftPanel.setPreferredSize(new Dimension(getWidth(), ProgramPanel.setPanelHeight));
                                        leftPanel.setMinimumSize(leftPanel.getPreferredSize());
                                        leftPanel.setMaximumSize(leftPanel.getPreferredSize());
                                        for (Component compDeleteSet : leftPanel.getComponents()) {
                                            if (compDeleteSet.getName() != null) {
                                                if ("deleteSet".equals(compDeleteSet.getName())) {
                                                    JButton deleteSet = (JButton) compDeleteSet;

                                                    scaledRemoveSetButtonImage = removeSetButtonImage.getImage().getScaledInstance((int) (getWidth() / 46.2173913043), (int) (getHeight() / 26.52), Image.SCALE_SMOOTH);
                                                    scaledRemoveSetIcon = new ImageIcon(scaledRemoveSetButtonImage);

                                                    deleteSet.setIcon(scaledRemoveSetIcon);
                                                    deleteSet.setPreferredSize(new Dimension(getWidth() / 45, ProgramPanel.setPanelHeight));
                                                    deleteSet.setMinimumSize(deleteSet.getPreferredSize());
                                                    deleteSet.setMaximumSize(deleteSet.getPreferredSize());
                                                }
                                                if (compDeleteSet.getName().equals("setLabel")) {
                                                    JLabel setLabel = (JLabel) compDeleteSet;

                                                    setLabel.setFont(new Font("Arial", Font.PLAIN, (int) (getHeight() / 55.25)));
                                                }
                                            }
                                        }
                                    }
                                }
                                workout.getWorkoutData().setTotalWorkoutHeight(workout.getWorkoutData().getTotalWorkoutHeight() + (ProgramPanel.setPanelHeight));
                            }

                            if (comp2.getName() != null) {
                                if (comp2.getName().equals("exerciseNameTitlePanel")) {
                                    JPanel exerciseNameTitlePanel = (JPanel) comp2;
                                    for (Component comp3 : exerciseNameTitlePanel.getComponents()) {
                                        if (comp3.getName().equals("removeExercise")) {
                                            JButton removeExercise = (JButton) comp3;

                                            scaledRemoveExerciseButtonImage = removeExerciseButtonImage.getImage().getScaledInstance((int) (getWidth() / 14.971831),
                                                    (int) (getHeight() / 19.5), Image.SCALE_SMOOTH);
                                            scaledRemoveExerciseIcon = new ImageIcon(scaledRemoveExerciseButtonImage);

                                            removeExercise.setIcon(scaledRemoveExerciseIcon);
                                            removeExercise.setPreferredSize(new Dimension(getWidth() / 15, ProgramPanel.setPanelHeight));
                                            removeExercise.setMinimumSize(removeExercise.getPreferredSize());
                                            removeExercise.setMaximumSize(removeExercise.getPreferredSize());

                                        }
                                        if (comp3.getName().equals("exerciseName")) {
                                            JLabel exerciseName = (JLabel) comp3;
                                            exerciseName.setPreferredSize(new Dimension(getWidth(), ProgramPanel.setPanelHeight));
                                            exerciseName.setMinimumSize(exerciseName.getPreferredSize());
                                            exerciseName.setMaximumSize(exerciseName.getPreferredSize());
                                            exerciseName.setFont(new Font("Arial", Font.BOLD, (int) (getHeight() / 33.15)));
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
            workout.setPreferredSize(new Dimension(workout.getWidth(), workout.getWorkoutData().getTotalWorkoutHeight()));
            workout.setMinimumSize(new Dimension(workout.getWidth(), workout.getWorkoutData().getTotalWorkoutHeight()));
            workout.setMaximumSize(new Dimension(workout.getWidth(), workout.getWorkoutData().getTotalWorkoutHeight()));

            workout.repaint();
            workout.revalidate();

            System.out.println(workout.getPreferredSize() + ":" + workout.getWorkoutData().getTotalWorkoutHeight());

        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        // Draw the image to fill the entire panel
        if (emptyBackground != null) {
            if (!SettingsPanel.lightMode) {
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
                savedWorkoutsList.setForeground(AppThemeColors.foregroundColor);
                savedWorkoutsLabel.setForeground(AppThemeColors.foregroundColor);
                defaultWorkoutList.setBackground(AppThemeColors.panelColor);
                defaultWorkoutList.setForeground(AppThemeColors.foregroundColor);

                for (Workout workout : workoutsList) {
                    for (Component comp1 : workout.getComponents()) {
                        if (comp1.getName() != null) {
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
                                                for (Component setLabelComp : leftPanel.getComponents()) {
                                                    if (setLabelComp.getName().equals("setLabel")) {
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
                                            for (Component titleComp : exerciseNameTitlePanel.getComponents()) {
                                                if (titleComp.getName().equals("exerciseName")) {
                                                    JLabel exerciseName = (JLabel) titleComp;
                                                    exerciseName.setForeground(workoutPanelTextColor);
                                                }
                                            }

                                        }
                                        if (comp2.getName().equals("setRepWeightRirTitleNPanel")) {
                                            JPanel setRepWeightRirTitleNPanel = (JPanel) comp2;
                                            setRepWeightRirTitleNPanel.setBackground(AppThemeColors.PRIMARY);
                                            for (Component compRight : setRepWeightRirTitleNPanel.getComponents()) {
                                                if (compRight.getName() != null) {
                                                    if ("rightPanel".equals(compRight.getName())) {
                                                        JPanel rightPanel = (JPanel) compRight;
                                                        for (Component comp : rightPanel.getComponents()) {
                                                            if (comp.getName() != null) {
                                                                if (comp.getName().equals("repsLabel")) {
                                                                    JLabel repsLabel = (JLabel) comp;
                                                                    repsLabel.setForeground(workoutPanelTextColor);
                                                                }
                                                                if (comp.getName().equals("weightLabel")) {
                                                                    JLabel repsLabel = (JLabel) comp;
                                                                    repsLabel.setForeground(workoutPanelTextColor);
                                                                }
                                                                if (comp.getName().equals("rirLabel")) {
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
                                                            for (Component setLabelComp : leftPanel.getComponents()) {
                                                                if (setLabelComp.getName() != null) {
                                                                    if (setLabelComp.getName().equals("setLabel")) {
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
                }
                for (Workout workout : defaultWorkoutslist) {
                    for (Component comp1 : workout.getComponents()) {
                        if (comp1.getName() != null) {
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
                                                for (Component setLabelComp : leftPanel.getComponents()) {
                                                    if (setLabelComp.getName().equals("setLabel")) {
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
                                            for (Component titleComp : exerciseNameTitlePanel.getComponents()) {
                                                if (titleComp.getName().equals("exerciseName")) {
                                                    JLabel exerciseName = (JLabel) titleComp;
                                                    exerciseName.setForeground(workoutPanelTextColor);
                                                }
                                            }

                                        }
                                        if (comp2.getName().equals("setRepWeightRirTitleNPanel")) {
                                            JPanel setRepWeightRirTitleNPanel = (JPanel) comp2;
                                            setRepWeightRirTitleNPanel.setBackground(AppThemeColors.PRIMARY);
                                            for (Component compRight : setRepWeightRirTitleNPanel.getComponents()) {
                                                if (compRight.getName() != null) {
                                                    if ("rightPanel".equals(compRight.getName())) {
                                                        JPanel rightPanel = (JPanel) compRight;
                                                        for (Component comp : rightPanel.getComponents()) {
                                                            if (comp.getName() != null) {
                                                                if (comp.getName().equals("repsLabel")) {
                                                                    JLabel repsLabel = (JLabel) comp;
                                                                    repsLabel.setForeground(workoutPanelTextColor);
                                                                }
                                                                if (comp.getName().equals("weightLabel")) {
                                                                    JLabel repsLabel = (JLabel) comp;
                                                                    repsLabel.setForeground(workoutPanelTextColor);
                                                                }
                                                                if (comp.getName().equals("rirLabel")) {
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
                                                            for (Component setLabelComp : leftPanel.getComponents()) {
                                                                if (setLabelComp.getName() != null) {
                                                                    if (setLabelComp.getName().equals("setLabel")) {
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
                }

                g.drawImage(scaledEmptyBackgroundIcon.getImage(), 0, 0, getWidth(), getHeight(), this);
            } else {
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
                defaultWorkoutsScrollPane.getViewport().setBackground(AppThemeColors.textFieldColor);
                searchExerciseResult.setBackground(AppThemeColors.panelColor);
                searchExerciseResult.setForeground(Color.BLACK);
                exercisesScrollPane.setBackground(AppThemeColors.textFieldColor);
                addExerciseAndSetPanel.setBackground(AppThemeColors.panelColor);
                workoutPanelTop.setBackground(AppThemeColors.panelColor);
                savedWorkoutsPanel.setBackground(AppThemeColors.PRIMARY);
                savedWorkoutsList.setBackground(AppThemeColors.panelColor);
                savedWorkoutsList.setForeground(workoutPanelTextColor);
                savedWorkoutsLabel.setForeground(AppThemeColors.foregroundColor);
                defaultWorkoutList.setBackground(AppThemeColors.panelColor);
                defaultWorkoutList.setForeground(Color.BLACK);


                for (Workout workout : workoutsList) {
                    for (Component comp1 : workout.getComponents()) {
                        if (comp1.getName() != null) {
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
                                                for (Component setLabelComp : leftPanel.getComponents()) {
                                                    if (setLabelComp.getName().equals("setLabel")) {
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
                                            for (Component titleComp : exerciseNameTitlePanel.getComponents()) {
                                                if (titleComp.getName().equals("exerciseName")) {
                                                    JLabel exerciseName = (JLabel) titleComp;
                                                    exerciseName.setForeground(workoutPanelTextColor);
                                                }
                                            }

                                        }
                                        if (comp2.getName().equals("setRepWeightRirTitleNPanel")) {
                                            JPanel setRepWeightRirTitleNPanel = (JPanel) comp2;
                                            setRepWeightRirTitleNPanel.setBackground(AppThemeColors.PRIMARY);
                                            for (Component compRight : setRepWeightRirTitleNPanel.getComponents()) {
                                                if (compRight.getName() != null) {
                                                    if ("rightPanel".equals(compRight.getName())) {
                                                        JPanel rightPanel = (JPanel) compRight;
                                                        for (Component comp : rightPanel.getComponents()) {
                                                            if (comp.getName() != null) {
                                                                if (comp.getName().equals("repsLabel")) {
                                                                    JLabel repsLabel = (JLabel) comp;
                                                                    repsLabel.setForeground(workoutPanelTextColor);
                                                                }
                                                                if (comp.getName().equals("weightLabel")) {
                                                                    JLabel repsLabel = (JLabel) comp;
                                                                    repsLabel.setForeground(workoutPanelTextColor);
                                                                }
                                                                if (comp.getName().equals("rirLabel")) {
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
                                                            for (Component setLabelComp : leftPanel.getComponents()) {
                                                                if(setLabelComp.getName()!=null){
                                                                    if (setLabelComp.getName().equals("setLabel")) {
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
                }
                for (Workout workout : defaultWorkoutslist) {
                    for (Component comp1 : workout.getComponents()) {
                        if (comp1.getName() != null) {
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
                                                for (Component setLabelComp : leftPanel.getComponents()) {
                                                    if (setLabelComp.getName().equals("setLabel")) {
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
                                            for (Component titleComp : exerciseNameTitlePanel.getComponents()) {
                                                if (titleComp.getName().equals("exerciseName")) {
                                                    JLabel exerciseName = (JLabel) titleComp;
                                                    exerciseName.setForeground(workoutPanelTextColor);
                                                }
                                            }

                                        }
                                        if (comp2.getName().equals("setRepWeightRirTitleNPanel")) {
                                            JPanel setRepWeightRirTitleNPanel = (JPanel) comp2;
                                            setRepWeightRirTitleNPanel.setBackground(AppThemeColors.PRIMARY);
                                            for (Component compRight : setRepWeightRirTitleNPanel.getComponents()) {
                                                if (compRight.getName() != null) {
                                                    if ("rightPanel".equals(compRight.getName())) {
                                                        JPanel rightPanel = (JPanel) compRight;
                                                        for (Component comp : rightPanel.getComponents()) {
                                                            if (comp.getName() != null) {
                                                                if (comp.getName().equals("repsLabel")) {
                                                                    JLabel repsLabel = (JLabel) comp;
                                                                    repsLabel.setForeground(workoutPanelTextColor);
                                                                }
                                                                if (comp.getName().equals("weightLabel")) {
                                                                    JLabel repsLabel = (JLabel) comp;
                                                                    repsLabel.setForeground(workoutPanelTextColor);
                                                                }
                                                                if (comp.getName().equals("rirLabel")) {
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
                                                            for (Component setLabelComp : leftPanel.getComponents()) {
                                                                if (setLabelComp.getName() != null) {
                                                                    if (setLabelComp.getName().equals("setLabel")) {
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
                }

                g.drawImage(scaledLightEmptyBackgroundIcon.getImage(), 0, 0, getWidth(), getHeight(), this);
            }
        }
    }
}



