package se.aljr.application.exercise;

import se.aljr.application.UserData;
import se.aljr.application.exercise.Excercise.*;


import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;

public class ExercisePanel extends JPanel {
    private JList<Exercise> menuList;
    private JTextArea infoTextArea;
    private JLabel titleLabel;
    private JLabel musclesWorkedLabel;
    private String resourcePath;
    private static boolean isFavourite = false;
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
        JButton favouriteButton = new JButton("\uD83D\uDCAA\n");
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
        favouriteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if (menuList.getSelectedValue() != null) {
                    if (!isFavourite) {
                        UserData.setFavoriteExercises(menuList.getSelectedValue());
                        favouriteButton.setForeground(new Color(196, 196, 49));
                        isFavourite = true;
                    }
                    else {
                        UserData.removeFavoriteExercises(menuList.getSelectedValue());
                        favouriteButton.setForeground(new Color(22, 22, 22));
                        isFavourite = false;
                    }
                }

            }
        });

        titleLabel = new JLabel("Exercise Details");
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

        JPanel searchFieldandExercisesContainer = new JPanel();
        searchFieldandExercisesContainer.setLayout(new BoxLayout(searchFieldandExercisesContainer, BoxLayout.Y_AXIS));
        searchFieldandExercisesContainer.add(searchField);
        searchFieldandExercisesContainer.setBackground(new Color(21, 21, 21));


        menuList = new JList<>();
        JScrollPane exerciseScrollPanel = new JScrollPane(menuList);
        exerciseScrollPanel.setBorder(new LineBorder(new Color(80, 73, 69)));
        exerciseScrollPanel.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);

        // Populate the JList with exercise data
        DefaultListModel<Exercise> model = new DefaultListModel<>();
        model.addElement(new BarbellRow());
        model.addElement(new BenchPress());
        model.addElement(new BentOverRow());
        model.addElement(new CableRow());
        model.addElement(new CableSideLateralRaises());
        model.addElement(new ChestFly());
        model.addElement(new Deadlift());
        model.addElement(new DumbbellCurl());
        model.addElement(new DumbbellPress());
        model.addElement(new DumbbellRow());
        model.addElement(new DumbbellSideLateralRaises());
        model.addElement(new InclineBenchPress());
        model.addElement(new InclineDumbbellPress());
        model.addElement(new LatPullDown());
        model.addElement(new LegPress());
        model.addElement(new Lunge());
        model.addElement(new OverheadPress());
        model.addElement(new Plank());
        model.addElement(new PullUp());
        model.addElement(new PushUp());
        model.addElement(new RomanianDeadlift());
        model.addElement(new SeatedCalfRaise());
        model.addElement(new SidePlank());
        model.addElement(new Squat());
        model.addElement(new StepUp());
        model.addElement(new SumoDeadlift());
        model.addElement(new TricepsCableOverheadExtensions());
        model.addElement(new TricepsCablePushdowns());
        model.addElement(new TricepsFrenchPress());

        menuList.setModel(model);
        menuList.setFont(font.deriveFont(17f));
        menuList.setBackground(new Color(21, 21, 21));
        menuList.setForeground(new Color(204, 204, 204));

        // Add selection listener
        menuList.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                Exercise selected = menuList.getSelectedValue();
                if (selected != null) {
                    infoTextArea.setText(selected.getInfo());
                    titleLabel.setText(selected.getName());
                    musclesWorkedLabel.setText(selected.getMusclesUsed());
                    if (UserData.getFavoriteExercises().contains(selected)) {
                        isFavourite = true;
                        favouriteButton.setForeground(new Color(196, 196, 49));
                    } else {
                        isFavourite = false;
                        favouriteButton.setForeground(new Color(22, 22, 22));
                    }

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

        // Set a default exercise to be shown when entering
        if (menuList.getSelectedValue() == null) {
            menuList.setSelectedIndex(0);
            titleLabel.setText(menuList.getSelectedValue().getName());
            musclesWorkedLabel.setText(menuList.getSelectedValue().getMusclesUsed());
            infoTextArea.setText(menuList.getSelectedValue().getInfo());
        }
    }


}
