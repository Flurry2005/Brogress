package se.aljr.application.exercise;

import se.aljr.application.UserData;
import se.aljr.application.exercise.Excercise.*;
import se.aljr.application.exercise.Program.Exercises;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.InputStream;
import javax.sound.sampled.*;

public class ExercisePanel extends JPanel {
    private JList<Exercise> menuList;
    private JTextArea infoTextArea;
    private JLabel titleLabel;
    private JLabel musclesWorkedLabel;
    private String resourcePath;
    private Exercise selectedExercise;
    private boolean isFavourite = false;
    private int statusDelayCounter;
    private StringBuilder status = new StringBuilder();
    Font font;


    public ExercisePanel(int width, int height) throws InterruptedException {
        resourcePath = getClass().getClassLoader().getResource("resource.path").getPath().replace("resource.path", "");
        try {
            font = Font.createFont(Font.TRUETYPE_FONT, new File(resourcePath + "BebasNeue-Regular.otf"));
            font = font.deriveFont(40f);
        } catch (Exception e) {
            font = new Font("Arial", Font.BOLD, 40);
            e.printStackTrace();
        }
        // Set the layout for the panel
        setLayout(new BorderLayout());
        this.setPreferredSize(new Dimension(width, height));

        // Initialize components
        Font emojiFont = new Font("Segoe UI Emoji", Font.PLAIN, 45);
        JButton favouriteButton = new JButton("\uD83D\uDCAA");
        favouriteButton.setFocusPainted(false);
        favouriteButton.setBorder(BorderFactory.createEmptyBorder());
        favouriteButton.setContentAreaFilled(false);
        favouriteButton.setBackground(new Color(51, 51, 51));
        favouriteButton.setFont(emojiFont);
        favouriteButton.setPreferredSize(new Dimension(100, 100));
        favouriteButton.setMaximumSize(new Dimension(100, 100));

        // Add mouselistener
        favouriteButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                super.mouseEntered(e);
                favouriteButton.setForeground(new Color(196, 196, 49));

            }

            @Override
            public void mouseExited(MouseEvent e) {
                super.mouseExited(e);
                if (isFavourite) {
                    favouriteButton.setForeground(new Color(196, 196, 49));
                } else {
                    favouriteButton.setForeground(new Color(22, 22, 22));
                }
            }

        });

        titleLabel = new JLabel();
        titleLabel.setFont(font);
        titleLabel.setForeground(new Color(204, 204, 204));

        musclesWorkedLabel = new JLabel("");
        musclesWorkedLabel.setFont(font.deriveFont(20f));
        musclesWorkedLabel.setForeground(new Color(204, 204, 204));

        infoTextArea = new JTextArea(10, 30);
        infoTextArea.setAlignmentY(Component.TOP_ALIGNMENT);
        infoTextArea.setBackground(new Color(21, 21, 21));
        infoTextArea.setForeground(new Color(204, 204, 204));
        infoTextArea.setEditable(false);
        infoTextArea.setWrapStyleWord(true);
        infoTextArea.setLineWrap(true);
        infoTextArea.setFont(new Font("Arial", Font.TRUETYPE_FONT, 15));
        infoTextArea.setFocusable(false);

        JPanel searchPanel = new JPanel(new BorderLayout());
        searchPanel.setBackground(new Color(51, 51, 51));

        JTextField searchField = new JTextField("Search for exercise...");
        searchField.setFont(new Font("Arial", Font.ITALIC, 12));
        searchField.setBorder(new LineBorder(new Color(80, 73, 69)));
        searchField.setBackground(new Color(21, 21, 21));
        searchField.setForeground(new Color(204, 204, 204));
        searchField.setPreferredSize(new Dimension(130, 30));
        searchField.setFocusable(true);

        // Focuslistener
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

        JCheckBox showFavorites = new JCheckBox("Show Favorites");
        showFavorites.setBackground(new Color(51, 51, 51));
        showFavorites.setForeground(new Color(204, 204, 204));


        JPanel searchFieldandExercisesContainer = new JPanel();
        searchFieldandExercisesContainer.setLayout(new BoxLayout(searchFieldandExercisesContainer, BoxLayout.Y_AXIS));
        searchFieldandExercisesContainer.add(searchField);
        searchFieldandExercisesContainer.add(showFavorites);
        searchFieldandExercisesContainer.setBackground(new Color(51, 51, 51));


        // Populate the JList with exercise data
        DefaultListModel<Exercise> exerciseModel = new DefaultListModel<>();
        Exercises exercises = new Exercises();
        for (Exercise exercise : exercises.getList()) {
            exerciseModel.addElement(exercise);
        }
        JList<Exercise> menuList = new JList<>(exerciseModel);
        menuList.setFont(font.deriveFont(17f));
        menuList.setBackground(new Color(21, 21, 21));
        menuList.setForeground(new Color(204, 204, 204));

        JScrollPane exerciseScrollPanel = new JScrollPane(menuList);
        exerciseScrollPanel.setBorder(new LineBorder(new Color(80, 73, 69)));
        exerciseScrollPanel.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);


        // Add selection listener
        menuList.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (menuList.getSelectedIndex() != -1) {
                    selectedExercise = menuList.getSelectedValue();
                    if (selectedExercise != null) {
                        infoTextArea.setText(selectedExercise.getInfo());
                        titleLabel.setText(selectedExercise.getName());
                        musclesWorkedLabel.setText(selectedExercise.getMusclesUsed());
                        if (UserData.getFavoriteExercises().contains(selectedExercise)) {
                            isFavourite = true;
                            favouriteButton.setForeground(new Color(196, 196, 49));
                        } else {
                            isFavourite = false;
                            favouriteButton.setForeground(new Color(22, 22, 22));
                        }

                    }
                }
            }
        });

        showFavorites.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                if (showFavorites.isSelected()) {
                    DefaultListModel<Exercise> favExerciseModel = new DefaultListModel<>();
                    for (Exercise exercise : UserData.getFavoriteExercises()) {
                        favExerciseModel.addElement(exercise);
                    }
                    menuList.setModel(favExerciseModel);
                } else {
                    menuList.setModel(exerciseModel);
                }
            }
        });

        // Create layout for the panel
        JPanel detailsPanel = new JPanel();
        detailsPanel.setBackground(new Color(21, 21, 21));
        detailsPanel.setLayout(new BoxLayout(detailsPanel, BoxLayout.Y_AXIS));
        detailsPanel.setPreferredSize(new Dimension(this.getWidth() - 50, this.getHeight()));
        exerciseScrollPanel.setPreferredSize(new Dimension(200, this.getHeight()));

        JPanel topBar = new JPanel(new BorderLayout(0, 0));
        topBar.setPreferredSize(new Dimension(960 - exerciseScrollPanel.getWidth(), 80));
        topBar.setMaximumSize(new Dimension(960 - exerciseScrollPanel.getWidth(), 80));
        topBar.setBackground(new Color(51, 51, 51));
        JPanel topBarWestContainer = new JPanel();
        topBarWestContainer.setLayout(new BoxLayout(topBarWestContainer, BoxLayout.Y_AXIS));
        topBarWestContainer.setBackground(new Color(51, 51, 51));

        JPanel statusPanel = new JPanel();
        statusPanel.setPreferredSize(new Dimension(this.getWidth(), 50));
        statusPanel.setVisible(false);
        JLabel statusText = new JLabel(status.toString());
        statusText.setForeground(new Color(204, 204, 204));
        statusPanel.setBorder(new LineBorder(new Color(46, 148, 76),1,true));

        Timer timer = new Timer((int) (60000 / 126), new ActionListener() {
            Color[] colors = {Color.RED, Color.BLUE, Color.GREEN, Color.ORANGE, Color.MAGENTA, Color.CYAN};
            final int[] index = {0};

            @Override
            public void actionPerformed(ActionEvent e) {
                favouriteButton.setText("\uD83E\uDD84");detailsPanel.setBackground(colors[index[0]]);topBar.setBackground(colors[index[0]]);
                topBarWestContainer.setBackground(colors[index[0]]);menuList.setBackground(colors[index[0]]);infoTextArea.setBackground(colors[index[0]]);
                menuList.setForeground(Color.white);infoTextArea.setForeground(Color.white);titleLabel.setForeground(Color.white);
                favouriteButton.setForeground(Color.white);
                detailsPanel.repaint();
                index[0] = (index[0] + 1) % colors.length;
            }
        });


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
                    }
                    catch (Exception ex) {
                        ex.printStackTrace();
                    }
                    timer.start();
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
        Timer shrinkStatusPanel = new Timer(30, new ActionListener() {
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

        statusPanel.add(statusText);
        topBarWestContainer.add(titleLabel);
        topBarWestContainer.add(musclesWorkedLabel);
        topBar.add(topBarWestContainer, BorderLayout.WEST);
        topBar.add(favouriteButton, BorderLayout.EAST);
        searchPanel.add(searchFieldandExercisesContainer, BorderLayout.NORTH);
        searchPanel.add(exerciseScrollPanel, BorderLayout.CENTER);

        detailsPanel.add(topBar, BorderLayout.NORTH);
        detailsPanel.add(infoTextArea, BorderLayout.SOUTH);// List on the left

        add(detailsPanel, BorderLayout.CENTER);     // Details on the center
        add(searchPanel, BorderLayout.WEST);
        add(statusPanel, BorderLayout.SOUTH);

        // Set a default exercise to be shown when entering
        if (menuList.getSelectedValue() == null) {
            Exercise defaultExercise = exerciseModel.getElementAt(0);
            menuList.setSelectedValue(defaultExercise, true);
            titleLabel.setText(defaultExercise.getName());
            musclesWorkedLabel.setText(defaultExercise.getMusclesUsed());
            infoTextArea.setText(defaultExercise.getInfo());
        }
        favouriteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if (selectedExercise != null) {
                    if (!isFavourite) {
                        UserData.setFavoriteExercises(selectedExercise);
                        status.append(selectedExercise.getName()).append(" has been added to favorites!");
                        statusPanel.setBackground(new Color(46, 148, 76));
                        activateStatus(statusPanel, shrinkStatusPanel, statusText);
                        status.setLength(0);
                        favouriteButton.setForeground(new Color(196, 196, 49));
                        isFavourite = true;
                    } else {
                        UserData.removeFavoriteExercises(selectedExercise);
                        status.append(selectedExercise.getName()).append(" has been removed from favorites!");
                        statusPanel.setBackground(new Color(204, 20, 20));
                        activateStatus(statusPanel, shrinkStatusPanel, statusText);
                        status.setLength(0);
                        favouriteButton.setForeground(new Color(22, 22, 22));
                        isFavourite = false;
                    }
                }

            }
        });
    }

    public void activateStatus(JPanel statusPanel, Timer shrinkStatusPanel, JLabel statusText) {
        statusDelayCounter = 20;
        statusPanel.setVisible(true);
        statusText.setText(status.toString());
        shrinkStatusPanel.start();
        shrinkStatusPanel.restart();
        statusPanel.setPreferredSize(new Dimension(statusPanel.getWidth(), 50));
    }
}



