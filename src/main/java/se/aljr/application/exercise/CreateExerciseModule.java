package se.aljr.application.exercise;

import se.aljr.application.CustomFont;
import se.aljr.application.UserData;
import se.aljr.application.exercise.Excercise.Exercise;
import se.aljr.application.exercise.Muscle.Muscle;
import se.aljr.application.exercise.Muscle.MuscleList;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.AbstractDocument;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class CreateExerciseModule extends JPanel {

    public CreateExerciseModule(JPanel parentPanel) {
        setBackground(new Color(51, 51, 51));

        setLayout(new BorderLayout());
        init(parentPanel);
    }

    public void init(JPanel parentPanel) {

        //---------------INITIALIZE COMPONENTS-----

        JPanel headerPanel = new JPanel();
        headerPanel.setLayout(new BorderLayout());
        headerPanel.setPreferredSize(new Dimension(parentPanel.getPreferredSize().width, parentPanel.getPreferredSize().height / 18));
        headerPanel.setMaximumSize(new Dimension(parentPanel.getPreferredSize().width, parentPanel.getPreferredSize().height / 18));
        headerPanel.setBackground(new Color(51, 51, 51));
        headerPanel.setBorder(new LineBorder(new Color(80, 73, 69)));


        JLabel headerLabel = new JLabel("Create new exercise");
        headerLabel.setMaximumSize(new Dimension(parentPanel.getPreferredSize().width, parentPanel.getPreferredSize().height / 18));
        headerLabel.setForeground(new Color(204, 204, 204));
        headerLabel.setFont(CustomFont.getFont().deriveFont(24f));
        headerLabel.setAlignmentX(Component.RIGHT_ALIGNMENT);
        headerLabel.setHorizontalAlignment(SwingConstants.CENTER);

        JPanel eastPanel = new JPanel();
        eastPanel.setLayout(new BoxLayout(eastPanel, BoxLayout.Y_AXIS));
        eastPanel.setBackground(new Color(51, 51, 51));

        JPanel textPanel = new JPanel();
        textPanel.setLayout(new BoxLayout(textPanel, BoxLayout.Y_AXIS));
        textPanel.setBackground(new Color(51, 51, 51));
        textPanel.setPreferredSize(new Dimension(parentPanel.getPreferredSize().width, parentPanel.getPreferredSize().height / 20));
        textPanel.setMaximumSize(new Dimension(parentPanel.getPreferredSize().width, parentPanel.getPreferredSize().height / 20));
        textPanel.setBorder(new EmptyBorder(0, 0, 0, parentPanel.getPreferredSize().width / 50));

        JLabel detailsLabel = new JLabel("Details");
        detailsLabel.setMaximumSize(new Dimension(parentPanel.getPreferredSize().width, parentPanel.getPreferredSize().height / 20));
        detailsLabel.setHorizontalAlignment(SwingConstants.CENTER);
        detailsLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        detailsLabel.setFont(CustomFont.getFont().deriveFont(18f));
        detailsLabel.setForeground(new Color(204, 204, 204));
        detailsLabel.setBackground(new Color(49, 84, 122));
        detailsLabel.setBorder(new LineBorder(new Color(80, 73, 69)));
        detailsLabel.setOpaque(true);

        JLabel previewLabel = new JLabel("Preview");
        previewLabel.setMaximumSize(new Dimension(parentPanel.getPreferredSize().width, parentPanel.getPreferredSize().height / 20));
        previewLabel.setHorizontalAlignment(SwingConstants.CENTER);
        previewLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        previewLabel.setFont(CustomFont.getFont().deriveFont(18f));
        previewLabel.setForeground(new Color(204, 204, 204));
        previewLabel.setBackground(new Color(49, 84, 122));
        previewLabel.setOpaque(true);
        previewLabel.setBorder(new LineBorder(new Color(80, 73, 69)));

        JPanel nameAndFavPanel = new JPanel();
        nameAndFavPanel.setLayout(new BorderLayout());
        nameAndFavPanel.setBackground(new Color(51, 51, 51));

        JTextField exerciseName = new JTextField();
        exerciseName.setText("Enter exercise name...");
        exerciseName.setBorder(new LineBorder(new Color(80, 73, 69)));
        exerciseName.setMaximumSize(new Dimension(parentPanel.getPreferredSize().width / 3, parentPanel.getPreferredSize().height / 20));
        exerciseName.setPreferredSize(new Dimension(parentPanel.getPreferredSize().width / 3, parentPanel.getPreferredSize().height / 20));
        exerciseName.setMinimumSize(new Dimension(parentPanel.getPreferredSize().width / 3, parentPanel.getPreferredSize().height / 20));
        exerciseName.setBackground(new Color(21, 21, 21));
        exerciseName.setForeground(new Color(204, 204, 204));

        JCheckBox setFav = new JCheckBox("Add to favorites");
        setFav.setBackground(new Color(51, 51, 51));
        setFav.setBorder(null);
        setFav.setForeground(new Color(204, 204, 204));

        JTextArea exerciseInfo = new JTextArea();
        exerciseInfo.setText("Enter exercise info (optional)");
        exerciseInfo.setForeground(new Color(204, 204, 204));
        exerciseInfo.setBorder(new LineBorder(new Color(80, 73, 69)));
        exerciseInfo.setPreferredSize(new Dimension(parentPanel.getPreferredSize().width, parentPanel.getPreferredSize().height / 5));
        exerciseInfo.setMaximumSize(new Dimension(parentPanel.getPreferredSize().width, parentPanel.getPreferredSize().height / 5));
        exerciseInfo.setMinimumSize(new Dimension(parentPanel.getPreferredSize().width, parentPanel.getPreferredSize().height / 5));
        exerciseInfo.setAlignmentX(Component.CENTER_ALIGNMENT);
        exerciseInfo.setBackground(new Color(21, 21, 21));
        exerciseInfo.setLineWrap(true);

        JLabel muscleLabel = new JLabel("Muscles Used");
        muscleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        muscleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        muscleLabel.setFont(CustomFont.getFont().deriveFont(18f));
        muscleLabel.setMaximumSize(new Dimension(parentPanel.getPreferredSize().width, parentPanel.getPreferredSize().height / 20));
        muscleLabel.setForeground(new Color(204, 204, 204));
        muscleLabel.setBackground(new Color(49, 84, 122));
        muscleLabel.setOpaque(true);
        muscleLabel.setBorder(new LineBorder(new Color(80, 73, 69)));

        DefaultListModel<Muscle> muscleListModel = new DefaultListModel<>();
        MuscleList muscleList = new MuscleList();
        for (Muscle muscle : muscleList) {
            muscleListModel.addElement(muscle);
        }

        JPanel previewPanel = new JPanel();
        previewPanel.setLayout(new BoxLayout(previewPanel, BoxLayout.Y_AXIS));
        previewPanel.setBackground(new Color(21, 21, 21));
        previewPanel.setMaximumSize(new Dimension(textPanel.getPreferredSize().width, parentPanel.getPreferredSize().height));
        previewPanel.setBorder(new LineBorder(new Color(80, 73, 69)));

        JLabel namePreviewLabel = new JLabel();
        namePreviewLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        namePreviewLabel.setHorizontalAlignment(SwingConstants.CENTER);
        namePreviewLabel.setFont(CustomFont.getFont().deriveFont(34f));
        namePreviewLabel.setForeground(new Color(204, 204, 204));
        namePreviewLabel.setPreferredSize(new Dimension(textPanel.getPreferredSize().width, parentPanel.getPreferredSize().height / 10));
        namePreviewLabel.setMaximumSize(new Dimension(textPanel.getPreferredSize().width, parentPanel.getPreferredSize().height / 10));
        namePreviewLabel.setMinimumSize(new Dimension(textPanel.getPreferredSize().width, parentPanel.getPreferredSize().height / 10));
        namePreviewLabel.setBackground(new Color(21, 21, 21));

        JTextArea infoPreviewLabel = new JTextArea();
        infoPreviewLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        infoPreviewLabel.setFont(CustomFont.getFont().deriveFont(24f));
        infoPreviewLabel.setForeground(new Color(204, 204, 204));
        infoPreviewLabel.setPreferredSize(new Dimension(textPanel.getPreferredSize().width, parentPanel.getPreferredSize().height / 4));
        infoPreviewLabel.setMaximumSize(new Dimension(textPanel.getPreferredSize().width, parentPanel.getPreferredSize().height / 4));
        infoPreviewLabel.setMinimumSize(new Dimension(textPanel.getPreferredSize().width, parentPanel.getPreferredSize().height / 4));
        infoPreviewLabel.setEditable(false);
        infoPreviewLabel.setBackground(new Color(21, 21, 21));
        infoPreviewLabel.setLineWrap(true);

        JTextPane musclePreviewLabel = new JTextPane();
        musclePreviewLabel.setEditable(false);
        musclePreviewLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        musclePreviewLabel.setFont(CustomFont.getFont().deriveFont(24f));
        musclePreviewLabel.setForeground(new Color(204, 204, 204));
        musclePreviewLabel.setBackground(new Color(21, 21, 21));
        musclePreviewLabel.setPreferredSize(new Dimension(textPanel.getPreferredSize().width, parentPanel.getPreferredSize().height / 7));
        musclePreviewLabel.setMaximumSize(new Dimension(textPanel.getPreferredSize().width, parentPanel.getPreferredSize().height / 7));
        musclePreviewLabel.setMinimumSize(new Dimension(textPanel.getPreferredSize().width, parentPanel.getPreferredSize().height / 7));

        JList<Muscle> muscleJlist = new JList<>(muscleListModel);
        muscleJlist.setForeground(new Color(204, 204, 204));
        muscleJlist.setBackground(new Color(21, 21, 21));
        muscleJlist.setBorder(new LineBorder(new Color(80, 73, 69)));

        JButton addExercise = new JButton("Create new exercise");
        addExercise.setMaximumSize(new Dimension(textPanel.getPreferredSize().width, parentPanel.getPreferredSize().height / 18));
        addExercise.setPreferredSize(new Dimension(textPanel.getPreferredSize().width, parentPanel.getPreferredSize().height / 18));
        addExercise.setAlignmentX(Component.CENTER_ALIGNMENT);
        addExercise.setBackground(new Color(46, 148, 76));
        addExercise.setForeground(new Color(204, 204, 204));
        addExercise.setBorder(new LineBorder(new Color(80, 73, 69), 1));


        //---------------ADD COMPONENTS---------------------
        headerPanel.add(headerLabel, BorderLayout.CENTER);

        textPanel.add(detailsLabel);
        textPanel.add(Box.createRigidArea(new Dimension(0, textPanel.getPreferredSize().height / 3)));

        nameAndFavPanel.add(exerciseName, BorderLayout.WEST);
        nameAndFavPanel.add(setFav, BorderLayout.EAST);

        textPanel.add(nameAndFavPanel);
        textPanel.add(Box.createRigidArea(new Dimension(0, textPanel.getPreferredSize().height / 3)));
        textPanel.add(exerciseInfo);
        textPanel.add(Box.createRigidArea(new Dimension(0, textPanel.getPreferredSize().height / 3)));
        textPanel.add(previewLabel);

        previewPanel.add(namePreviewLabel);
        previewPanel.add(infoPreviewLabel);
        previewPanel.add(musclePreviewLabel);

        textPanel.add(previewPanel);
        textPanel.add(addExercise);

        eastPanel.add(muscleLabel);
        eastPanel.add(muscleJlist);

        add(headerPanel, BorderLayout.NORTH);
        add(textPanel, BorderLayout.CENTER);
        add(eastPanel, BorderLayout.EAST);

        //---------------------METHODS AND LISTENERS-----------------
        muscleJlist.setSelectionModel(new DefaultListSelectionModel() {
            private boolean gestureStarted = false;

            @Override
            public void setSelectionInterval(int index0, int index1) {
                if (!gestureStarted) {
                    if (isSelectedIndex(index0)) {
                        super.removeSelectionInterval(index0, index1);
                    } else {
                        super.addSelectionInterval(index0, index1);
                    }
                }
                gestureStarted = true;
            }

            @Override
            public void setValueIsAdjusting(boolean isAdjusting) {
                if (!isAdjusting) {
                    gestureStarted = false;
                }
                super.setValueIsAdjusting(isAdjusting);
            }
        });

        exerciseName.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                exerciseName.setForeground(new Color(204, 204, 204));
                exerciseName.setText("");
            }
        });

        AbstractDocument document = (AbstractDocument) exerciseName.getDocument();
        document.setDocumentFilter(new DocumentFilter() {
            @Override
            public void insertString(FilterBypass fb, int offset, String string, AttributeSet attr) throws BadLocationException {
                if (fb.getDocument().getLength() + string.length() <= 22) {
                    super.insertString(fb, offset, string, attr);
                }
            }

            @Override
            public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs) throws BadLocationException {
                if (fb.getDocument().getLength() + text.length() <= 22) {
                    super.replace(fb, offset, length, text, attrs);
                }
            }

        });

        AbstractDocument document2 = (AbstractDocument) exerciseInfo.getDocument();
        document2.setDocumentFilter(new DocumentFilter() {
            @Override
            public void insertString(FilterBypass fb, int offset, String string, AttributeSet attr) throws BadLocationException {
                if (fb.getDocument().getLength() + string.length() <= 800) {
                    super.insertString(fb, offset, string, attr);
                }
            }

            @Override
            public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs) throws BadLocationException {
                if (fb.getDocument().getLength() + text.length() <= 800) {
                    super.replace(fb, offset, length, text, attrs);
                }
            }
        });

        exerciseName.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                namePreviewLabel.setText(exerciseName.getText());
                previewPanel.revalidate();
                previewPanel.repaint();

            }

            @Override
            public void removeUpdate(DocumentEvent e) {

                namePreviewLabel.setText(exerciseName.getText());
                previewPanel.revalidate();
                previewPanel.repaint();

            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                namePreviewLabel.setText(exerciseName.getText());
                previewPanel.revalidate();
                previewPanel.repaint();
            }
        });

        exerciseInfo.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (exerciseInfo.getText().equals("Enter exercise info (optional)")) {
                    exerciseInfo.setText("");
                }
            }
        });
        exerciseInfo.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                if (exerciseInfo.getText().length() > 125) {
                    infoPreviewLabel.setFont(CustomFont.getFont().deriveFont(14f));
                    infoPreviewLabel.setText(exerciseInfo.getText());
                } else {
                    infoPreviewLabel.setFont(CustomFont.getFont().deriveFont(24f));
                    infoPreviewLabel.setText(exerciseInfo.getText());
                }
                previewPanel.revalidate();
                previewPanel.repaint();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                if (exerciseInfo.getText().length() > 125) {
                    infoPreviewLabel.setFont(CustomFont.getFont().deriveFont(14f));
                    infoPreviewLabel.setText(exerciseInfo.getText());
                } else {
                    infoPreviewLabel.setFont(CustomFont.getFont().deriveFont(24f));
                    infoPreviewLabel.setText(exerciseInfo.getText());
                }
                previewPanel.revalidate();
                previewPanel.repaint();

            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                if (exerciseInfo.getText().length() > 140) {
                    infoPreviewLabel.setFont(CustomFont.getFont().deriveFont(14f));
                    infoPreviewLabel.setText(exerciseInfo.getText());
                } else {
                    infoPreviewLabel.setFont(CustomFont.getFont().deriveFont(24f));
                    infoPreviewLabel.setText(exerciseInfo.getText());
                }
                previewPanel.revalidate();
                previewPanel.repaint();

            }
        });

        muscleJlist.addListSelectionListener(e -> {
            if (muscleJlist.getSelectedIndex() != -1) {
                int maxLength = 60;
                if (musclePreviewLabel.getText().length() > maxLength) {
                    musclePreviewLabel.setFont(CustomFont.getFont().deriveFont(14f));
                    muscleJlist.setForeground(new Color(204, 204, 204));
                    musclePreviewLabel.setText(muscleJlist.getSelectedValuesList().toString());
                } else {
                    muscleJlist.setForeground(new Color(204, 204, 204));
                    musclePreviewLabel.setFont(CustomFont.getFont().deriveFont(24f));
                    musclePreviewLabel.setText(muscleJlist.getSelectedValuesList().toString());
                }
            }
            else {
                musclePreviewLabel.setText("");
            }
        });
        // ADD TO PUBLIC ARRAYLIST
        addExercise.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!exerciseName.getText().isEmpty() && !exerciseName.getText().equals("Name required") && !exerciseName.getText().equals("Enter exercise name...") && !muscleJlist.getSelectedValuesList().isEmpty()) {
                    Exercise exercise = new Exercise();
                    exercise.createExercise(exerciseName.getText(), exerciseInfo.getText(), (ArrayList<Muscle>) muscleJlist.getSelectedValuesList());
                    UserData.setCreatedExercises(exercise);
                    if (setFav.isSelected()) {
                        UserData.setFavoriteExercises(exercise);
                    }
                    exerciseName.setText("");
                    exerciseInfo.setText("");
                    muscleJlist.clearSelection();
                    musclePreviewLabel.setText("");
                    setFav.setSelected(false);
                    repaint();
                    revalidate();
                    ExercisePanel.updateMenuList("myExerciseModel");
                    ExercisePanel.activateStatus(new Color(46, 148, 76), "New exercise " + exercise.getName()+ " has been created!");
                } else {
                    if (exerciseName.getText().isEmpty() || exerciseName.getText().equals("Enter exercise name...")) {
                        exerciseName.setForeground(Color.RED);
                        exerciseName.setText("Name required");
                        exerciseName.revalidate();
                        exerciseName.repaint();
                    }
                    if (muscleJlist.getSelectedValuesList().isEmpty()) {
                        musclePreviewLabel.setText(("Select at least one muscle..."));
                        musclePreviewLabel.revalidate();
                        musclePreviewLabel.repaint();
                    }

                }
            }
        });
    }
}


