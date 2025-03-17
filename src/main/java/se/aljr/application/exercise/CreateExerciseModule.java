package se.aljr.application.exercise;

import se.aljr.application.AppThemeColors;
import se.aljr.application.CustomFont;
import se.aljr.application.UserData;
import se.aljr.application.exercise.Excercise.Exercise;
import se.aljr.application.exercise.Muscle.Muscle;
import se.aljr.application.exercise.Muscle.MuscleList;
import se.aljr.application.loginpage.FirebaseManager;

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
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class CreateExerciseModule extends JPanel {

    JPanel headerPanel = new JPanel();
    JLabel headerLabel = new JLabel("Create new exercise");
    JPanel eastPanel = new JPanel();
    JPanel textPanel = new JPanel();
    JPanel nameAndFavPanel = new JPanel();
    JTextField exerciseName = new JTextField();
    JCheckBox setFav = new JCheckBox("Add to favorites");
    JTextArea exerciseInfo = new JTextArea();
    JPanel previewPanel = new JPanel();
    JTextArea infoPreviewLabel = new JTextArea();
    JTextPane musclePreviewLabel = new JTextPane();
    DefaultListModel<Muscle> muscleListModel = new DefaultListModel<>();
    JList<Muscle> muscleJlist = new JList<>(muscleListModel);
    JLabel muscleLabel = new JLabel("Muscles Used");
    JLabel namePreviewLabel = new JLabel();

    public static CreateExerciseModule instance;

    public CreateExerciseModule(JPanel parentPanel, ExercisePanel exercisePanel) {
        setBackground(AppThemeColors.PRIMARY);
        instance = this;

        setLayout(new BorderLayout());
        init(parentPanel, exercisePanel);
    }

    public void init(JPanel parentPanel, ExercisePanel exercisePanel) {

        //---------------INITIALIZE COMPONENTS-----

        headerPanel.setLayout(new BorderLayout());
        headerPanel.setPreferredSize(new Dimension(parentPanel.getPreferredSize().width, parentPanel.getPreferredSize().height / 18));
        headerPanel.setMaximumSize(headerPanel.getPreferredSize());
        headerPanel.setMinimumSize(headerPanel.getPreferredSize());
        headerPanel.setBackground(AppThemeColors.PRIMARY);
        headerPanel.setBorder(new LineBorder(new Color(80, 73, 69)));

        headerLabel.setPreferredSize(new Dimension(parentPanel.getPreferredSize().width, parentPanel.getPreferredSize().height / 18));
        headerLabel.setMaximumSize(new Dimension(parentPanel.getPreferredSize().width, parentPanel.getPreferredSize().height / 18));
        headerLabel.setMinimumSize(new Dimension(parentPanel.getPreferredSize().width, parentPanel.getPreferredSize().height / 18));
        headerLabel.setForeground(AppThemeColors.foregroundColor);
        headerLabel.setFont(CustomFont.getFont().deriveFont(exercisePanel.getHeight() / 30f));
        headerLabel.setAlignmentX(Component.RIGHT_ALIGNMENT);
        headerLabel.setHorizontalAlignment(SwingConstants.CENTER);

        eastPanel.setLayout(new BoxLayout(eastPanel, BoxLayout.Y_AXIS));
        eastPanel.setBackground(AppThemeColors.panelColor);
        eastPanel.setPreferredSize(new Dimension(parentPanel.getPreferredSize().width / 3, (int) (parentPanel.getPreferredSize().height /1.2)));
        eastPanel.setMaximumSize(eastPanel.getPreferredSize());
        eastPanel.setMinimumSize(eastPanel.getPreferredSize());
        eastPanel.setBorder(new LineBorder(new Color(80, 73, 69)));

        textPanel.setLayout(new BoxLayout(textPanel, BoxLayout.Y_AXIS));
        textPanel.setBackground(AppThemeColors.PRIMARY);
        textPanel.setPreferredSize(new Dimension(parentPanel.getPreferredSize().width, parentPanel.getPreferredSize().height - headerPanel.getPreferredSize().height));
        textPanel.setMaximumSize(textPanel.getPreferredSize());
        textPanel.setMinimumSize(textPanel.getPreferredSize());
        textPanel.setBorder(new EmptyBorder(0, 0, 0, parentPanel.getPreferredSize().width / 50));

        JLabel detailsLabel = new JLabel("Details");
        detailsLabel.setPreferredSize(new Dimension(parentPanel.getPreferredSize().width, parentPanel.getPreferredSize().height / 22));
        detailsLabel.setMaximumSize(new Dimension(parentPanel.getPreferredSize().width, parentPanel.getPreferredSize().height / 22));
        detailsLabel.setMinimumSize(detailsLabel.getPreferredSize());
        detailsLabel.setFont(CustomFont.getFont().deriveFont(exercisePanel.getHeight() / 35f));
        detailsLabel.setHorizontalAlignment(SwingConstants.CENTER);
        detailsLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        detailsLabel.setForeground(Color.WHITE);
        detailsLabel.setBackground(new Color(49, 84, 122));
        detailsLabel.setBorder(new LineBorder(new Color(80, 73, 69)));
        detailsLabel.setOpaque(true);

        JLabel previewLabel = new JLabel("Preview");
        previewLabel.setMaximumSize(new Dimension(parentPanel.getPreferredSize().width, parentPanel.getPreferredSize().height / 22));
        previewLabel.setPreferredSize(new Dimension(parentPanel.getPreferredSize().width, parentPanel.getPreferredSize().height / 22));
        previewLabel.setMinimumSize(previewLabel.getPreferredSize());
        previewLabel.setHorizontalAlignment(SwingConstants.CENTER);
        previewLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        previewLabel.setFont(CustomFont.getFont().deriveFont(exercisePanel.getHeight() / 35f));
        previewLabel.setForeground(Color.WHITE);
        previewLabel.setBackground(new Color(49, 84, 122));
        previewLabel.setOpaque(true);
        previewLabel.setBorder(new LineBorder(new Color(80, 73, 69)));

        nameAndFavPanel.setLayout(new BorderLayout());
        nameAndFavPanel.setBackground(AppThemeColors.PRIMARY);
        nameAndFavPanel.setPreferredSize(new Dimension(textPanel.getPreferredSize().width, textPanel.getPreferredSize().height/15));
        nameAndFavPanel.setMinimumSize(nameAndFavPanel.getPreferredSize());
        nameAndFavPanel.setMaximumSize(nameAndFavPanel.getPreferredSize());

        exerciseName.setText("Enter exercise name...");
        exerciseName.setBorder(new LineBorder(new Color(80, 73, 69)));
        exerciseName.setPreferredSize(new Dimension(parentPanel.getPreferredSize().width / 3, parentPanel.getPreferredSize().height / 20));
        exerciseName.setMinimumSize(exerciseName.getPreferredSize());
        exerciseName.setMaximumSize(exerciseName.getPreferredSize());
        exerciseName.setBackground(AppThemeColors.panelColor);
        exerciseName.setForeground(AppThemeColors.foregroundColor);
        exerciseName.setFont(new Font("Arial", Font.ITALIC, (int) (exercisePanel.getHeight() / 55.25)));

        setFav.setBackground(AppThemeColors.PRIMARY);
        setFav.setBorder(null);
        setFav.setForeground(AppThemeColors.foregroundColor);
        setFav.setFont(new Font("Arial", Font.ITALIC, (int) (exercisePanel.getHeight() / 55.25)));

        exerciseInfo.setText("Enter exercise info (optional)");
        exerciseInfo.setFont(new Font("Arial", Font.PLAIN, (int) (exercisePanel.getHeight() / 55.25)));
        exerciseInfo.setForeground(AppThemeColors.foregroundColor);
        exerciseInfo.setBorder(new LineBorder(new Color(80, 73, 69)));
        exerciseInfo.setPreferredSize(new Dimension(parentPanel.getPreferredSize().width, parentPanel.getPreferredSize().height / 5));
        exerciseInfo.setMaximumSize(new Dimension(parentPanel.getPreferredSize().width, parentPanel.getPreferredSize().height / 5));
        exerciseInfo.setMinimumSize(new Dimension(parentPanel.getPreferredSize().width, parentPanel.getPreferredSize().height / 5));
        exerciseInfo.setAlignmentX(Component.CENTER_ALIGNMENT);
        exerciseInfo.setBackground(AppThemeColors.panelColor);
        exerciseInfo.setLineWrap(true);

        muscleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        muscleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        muscleLabel.setFont(CustomFont.getFont().deriveFont(exercisePanel.getHeight() / 35f));
        muscleLabel.setPreferredSize(new Dimension(parentPanel.getPreferredSize().width, parentPanel.getPreferredSize().height / 22));
        muscleLabel.setMaximumSize(new Dimension(parentPanel.getPreferredSize().width, parentPanel.getPreferredSize().height / 22));
        muscleLabel.setMinimumSize(muscleLabel.getPreferredSize());
        muscleLabel.setForeground(Color.WHITE);
        muscleLabel.setBackground(new Color(49, 84, 122));
        muscleLabel.setOpaque(true);
        muscleLabel.setBorder(new LineBorder(new Color(80, 73, 69)));

        MuscleList muscleList = new MuscleList();
        for (Muscle muscle : muscleList) {
            muscleListModel.addElement(muscle);
        }

        previewPanel.setLayout(new BoxLayout(previewPanel, BoxLayout.Y_AXIS));
        previewPanel.setBackground(AppThemeColors.panelColor);
        previewPanel.setPreferredSize(new Dimension(textPanel.getPreferredSize().width, (int) (textPanel.getPreferredSize().height/3)));
        previewPanel.setMaximumSize(previewPanel.getPreferredSize());
        previewPanel.setMinimumSize(previewPanel.getPreferredSize());
        previewPanel.setBorder(new LineBorder(new Color(80, 73, 69)));

        namePreviewLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        namePreviewLabel.setHorizontalAlignment(SwingConstants.CENTER);
        namePreviewLabel.setFont(CustomFont.getFont().deriveFont(exercisePanel.getHeight() / 20f));
        namePreviewLabel.setForeground(AppThemeColors.foregroundColor);
        namePreviewLabel.setPreferredSize(new Dimension(textPanel.getPreferredSize().width, parentPanel.getPreferredSize().height / 10));
        namePreviewLabel.setMaximumSize(new Dimension(textPanel.getPreferredSize().width, parentPanel.getPreferredSize().height / 10));
        namePreviewLabel.setMinimumSize(new Dimension(textPanel.getPreferredSize().width, parentPanel.getPreferredSize().height / 10));
        namePreviewLabel.setBackground(AppThemeColors.panelColor);

        infoPreviewLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        infoPreviewLabel.setFont(CustomFont.getFont().deriveFont(getHeight() / 30f));
        infoPreviewLabel.setForeground(AppThemeColors.foregroundColor);
        infoPreviewLabel.setPreferredSize(new Dimension(textPanel.getPreferredSize().width, parentPanel.getPreferredSize().height / 4));
        infoPreviewLabel.setMaximumSize(new Dimension(textPanel.getPreferredSize().width, parentPanel.getPreferredSize().height / 4));
        infoPreviewLabel.setMinimumSize(new Dimension(textPanel.getPreferredSize().width, parentPanel.getPreferredSize().height / 4));
        infoPreviewLabel.setEditable(false);
        infoPreviewLabel.setBackground(AppThemeColors.panelColor);
        infoPreviewLabel.setLineWrap(true);

        musclePreviewLabel.setEditable(false);
        musclePreviewLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        musclePreviewLabel.setFont(CustomFont.getFont().deriveFont(exercisePanel.getHeight() / 35f));
        musclePreviewLabel.setForeground(AppThemeColors.foregroundColor);
        musclePreviewLabel.setBackground(AppThemeColors.panelColor);
        musclePreviewLabel.setPreferredSize(new Dimension(textPanel.getPreferredSize().width, parentPanel.getPreferredSize().height / 3));
        musclePreviewLabel.setMaximumSize(musclePreviewLabel.getPreferredSize());
        musclePreviewLabel.setMinimumSize(musclePreviewLabel.getPreferredSize());

        muscleJlist.setForeground(AppThemeColors.foregroundColor);
        muscleJlist.setBackground(AppThemeColors.panelColor);
        muscleJlist.setBorder(null);
        muscleJlist.setPreferredSize(new Dimension(eastPanel.getPreferredSize().width, (int) (eastPanel.getPreferredSize().height)));
        muscleJlist.setMaximumSize(muscleJlist.getPreferredSize());
        muscleJlist.setMinimumSize(muscleJlist.getPreferredSize());
        muscleJlist.setFont(CustomFont.getFont().deriveFont(exercisePanel.getHeight() / 39f));

        JButton addExercise = new JButton("Create new exercise");
        addExercise.setMaximumSize(new Dimension(textPanel.getPreferredSize().width, parentPanel.getPreferredSize().height / 15));
        addExercise.setPreferredSize(new Dimension(textPanel.getPreferredSize().width, parentPanel.getPreferredSize().height / 15));
        addExercise.setFocusable(false);
        addExercise.setMinimumSize(addExercise.getPreferredSize());
        addExercise.setAlignmentX(Component.CENTER_ALIGNMENT);
        addExercise.setBackground(new Color(46, 148, 76));
        addExercise.setForeground(Color.WHITE);
        addExercise.setBorder(new LineBorder(new Color(80, 73, 69), 1));
        addExercise.setFont(new Font("Arial", Font.PLAIN, (int) (exercisePanel.getHeight() / 55.25)));

        //---------------ADD COMPONENTS---------------------

        headerPanel.add(headerLabel, BorderLayout.CENTER);

        textPanel.add(detailsLabel);
        nameAndFavPanel.add(exerciseName, BorderLayout.WEST);
        nameAndFavPanel.add(setFav, BorderLayout.EAST);
        textPanel.add(nameAndFavPanel);
        textPanel.add(exerciseInfo);
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

        this.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                super.componentResized(e);
                SwingUtilities.invokeLater(() -> {


                    headerPanel.setPreferredSize(new Dimension(parentPanel.getPreferredSize().width, parentPanel.getPreferredSize().height / 18));
                    headerPanel.setMaximumSize(headerPanel.getPreferredSize());
                    headerPanel.setMinimumSize(headerPanel.getPreferredSize());

                    headerLabel.setPreferredSize(new Dimension(parentPanel.getPreferredSize().width, parentPanel.getPreferredSize().height / 18));
                    headerLabel.setMaximumSize(new Dimension(parentPanel.getPreferredSize().width, parentPanel.getPreferredSize().height / 18));
                    headerLabel.setMinimumSize(headerLabel.getPreferredSize());
                    headerLabel.setFont(CustomFont.getFont().deriveFont(exercisePanel.getHeight() / 30f));

                    textPanel.setLayout(new BoxLayout(textPanel, BoxLayout.Y_AXIS));
                    textPanel.setPreferredSize(new Dimension(parentPanel.getPreferredSize().width, parentPanel.getPreferredSize().height - headerPanel.getPreferredSize().height));
                    textPanel.setMaximumSize(textPanel.getPreferredSize());
                    textPanel.setMinimumSize(textPanel.getPreferredSize());
                    textPanel.setBorder(new EmptyBorder(0, 0, 0, parentPanel.getPreferredSize().width / 50));

                    nameAndFavPanel.setPreferredSize(new Dimension(textPanel.getPreferredSize().width, textPanel.getPreferredSize().height/15));
                    nameAndFavPanel.setMinimumSize(nameAndFavPanel.getPreferredSize());
                    nameAndFavPanel.setMaximumSize(nameAndFavPanel.getPreferredSize());

                    detailsLabel.setPreferredSize(new Dimension(parentPanel.getPreferredSize().width, parentPanel.getPreferredSize().height / 22));
                    detailsLabel.setMaximumSize(new Dimension(parentPanel.getPreferredSize().width, parentPanel.getPreferredSize().height / 22));
                    detailsLabel.setMinimumSize(detailsLabel.getPreferredSize());
                    detailsLabel.setFont(CustomFont.getFont().deriveFont(exercisePanel.getHeight() / 35f));

                    previewLabel.setPreferredSize(new Dimension(parentPanel.getPreferredSize().width, parentPanel.getPreferredSize().height / 20));
                    previewLabel.setMaximumSize(new Dimension(parentPanel.getPreferredSize().width, parentPanel.getPreferredSize().height / 20));
                    previewLabel.setFont(CustomFont.getFont().deriveFont(exercisePanel.getHeight() / 35f));

                    exerciseName.setPreferredSize(new Dimension(parentPanel.getPreferredSize().width / 3, parentPanel.getPreferredSize().height / 20));
                    exerciseName.setMinimumSize(exerciseName.getPreferredSize());
                    exerciseName.setMaximumSize(exerciseName.getPreferredSize());

                    exerciseInfo.setPreferredSize(new Dimension(parentPanel.getPreferredSize().width, parentPanel.getPreferredSize().height / 5));
                    exerciseInfo.setMaximumSize(new Dimension(parentPanel.getPreferredSize().width, parentPanel.getPreferredSize().height / 5));
                    exerciseInfo.setMinimumSize(new Dimension(parentPanel.getPreferredSize().width, parentPanel.getPreferredSize().height / 5));

                    eastPanel.setPreferredSize(new Dimension(parentPanel.getPreferredSize().width / 3, (int) (parentPanel.getPreferredSize().height/1.2)));
                    eastPanel.setMaximumSize(eastPanel.getPreferredSize());
                    eastPanel.setMinimumSize(eastPanel.getPreferredSize());

                    muscleLabel.setPreferredSize(new Dimension(parentPanel.getPreferredSize().width, parentPanel.getPreferredSize().height / 22));
                    muscleLabel.setMaximumSize(new Dimension(parentPanel.getPreferredSize().width, parentPanel.getPreferredSize().height / 22));
                    muscleLabel.setMinimumSize(muscleLabel.getPreferredSize());
                    muscleLabel.setFont(CustomFont.getFont().deriveFont(exercisePanel.getHeight() / 35f));

                    muscleJlist.setPreferredSize(new Dimension(eastPanel.getPreferredSize().width, (int) (eastPanel.getPreferredSize().height)));
                    muscleJlist.setMaximumSize(muscleJlist.getPreferredSize());
                    muscleJlist.setMinimumSize(muscleJlist.getPreferredSize());


                    previewPanel.setPreferredSize(new Dimension(textPanel.getPreferredSize().width, (int) (textPanel.getPreferredSize().height/2)));
                    previewPanel.setMaximumSize(previewPanel.getPreferredSize());
                    previewPanel.setMinimumSize(previewPanel.getPreferredSize());

                    previewLabel.setPreferredSize(new Dimension(parentPanel.getPreferredSize().width, parentPanel.getPreferredSize().height / 22));
                    previewLabel.setMaximumSize(previewLabel.getPreferredSize());
                    previewLabel.setMinimumSize(previewLabel.getPreferredSize());

                    namePreviewLabel.setPreferredSize(new Dimension(textPanel.getPreferredSize().width, parentPanel.getPreferredSize().height / 10));
                    namePreviewLabel.setMaximumSize(new Dimension(textPanel.getPreferredSize().width, parentPanel.getPreferredSize().height / 10));
                    namePreviewLabel.setMinimumSize(new Dimension(textPanel.getPreferredSize().width, parentPanel.getPreferredSize().height / 10));

                    infoPreviewLabel.setPreferredSize(new Dimension(textPanel.getPreferredSize().width, parentPanel.getPreferredSize().height / 4));
                    infoPreviewLabel.setMaximumSize(new Dimension(textPanel.getPreferredSize().width, parentPanel.getPreferredSize().height / 4));
                    infoPreviewLabel.setMinimumSize(new Dimension(textPanel.getPreferredSize().width, parentPanel.getPreferredSize().height / 4));

                    musclePreviewLabel.setPreferredSize(new Dimension(textPanel.getPreferredSize().width, parentPanel.getPreferredSize().height / 3));
                    musclePreviewLabel.setMaximumSize(musclePreviewLabel.getPreferredSize());
                    musclePreviewLabel.setMinimumSize(musclePreviewLabel.getPreferredSize());

                    addExercise.setMaximumSize(new Dimension(textPanel.getPreferredSize().width, parentPanel.getPreferredSize().height / 15));
                    addExercise.setPreferredSize(new Dimension(textPanel.getPreferredSize().width, parentPanel.getPreferredSize().height / 15));
                    addExercise.setMinimumSize(addExercise.getPreferredSize());

                    headerLabel.setFont(CustomFont.getFont().deriveFont(exercisePanel.getHeight() / 30f));
                    detailsLabel.setFont(CustomFont.getFont().deriveFont(exercisePanel.getHeight() / 35f));
                    muscleLabel.setFont(CustomFont.getFont().deriveFont(exercisePanel.getHeight() / 35f));
                    previewLabel.setFont(CustomFont.getFont().deriveFont(exercisePanel.getHeight() / 35f));
                    muscleJlist.setFont(CustomFont.getFont().deriveFont(exercisePanel.getHeight() / 39f));
                    exerciseName.setFont(new Font("Arial", Font.ITALIC, (int) (exercisePanel.getHeight() / 55.25)));
                    setFav.setFont(new Font("Arial", Font.ITALIC, (int) (exercisePanel.getHeight() / 55.25)));
                    exerciseInfo.setFont(new Font("Arial", Font.PLAIN, (int) (exercisePanel.getHeight() / 55.25)));
                    namePreviewLabel.setFont(CustomFont.getFont().deriveFont(exercisePanel.getHeight() / 20f));
                    addExercise.setFont(new Font("Arial", Font.PLAIN, (int) (exercisePanel.getHeight() / 55.25)));
                    infoPreviewLabel.setFont(CustomFont.getFont().deriveFont(exercisePanel.getHeight() / 25f));

                        int maxLength = 40;
                        if (musclePreviewLabel.getText().length() > maxLength) {
                            musclePreviewLabel.setFont(CustomFont.getFont().deriveFont(exercisePanel.getHeight() / 55f));
                            muscleJlist.setForeground(AppThemeColors.foregroundColor);
                        } else {
                            muscleJlist.setForeground(AppThemeColors.foregroundColor);
                            musclePreviewLabel.setFont(CustomFont.getFont().deriveFont(exercisePanel.getHeight() / 35f));
                        }

                    if (exerciseInfo.getText().length() > 90) {
                        infoPreviewLabel.setFont(CustomFont.getFont().deriveFont(getHeight() / 30f));
                    } else {
                        infoPreviewLabel.setFont(CustomFont.getFont().deriveFont(getHeight() / 25f));
                    }
                    revalidate();
                    repaint();

                });
            }
        });


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
                exerciseName.setForeground(AppThemeColors.foregroundColor);
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
                if (exerciseInfo.getText().length() > 40) {
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
                if (exerciseInfo.getText().length() > 40) {
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
                if (exerciseInfo.getText().length() > 40) {
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

        muscleJlist.addListSelectionListener(_ -> {
            if (muscleJlist.getSelectedIndex() != -1) {
                int maxLength = 40;
                if (musclePreviewLabel.getText().length() > maxLength) {
                    musclePreviewLabel.setFont(CustomFont.getFont().deriveFont(exercisePanel.getHeight() / 55f));
                    muscleJlist.setForeground(AppThemeColors.foregroundColor);
                    musclePreviewLabel.setText(muscleJlist.getSelectedValuesList().toString());
                } else {
                    muscleJlist.setForeground(AppThemeColors.foregroundColor);
                    musclePreviewLabel.setFont(CustomFont.getFont().deriveFont(exercisePanel.getHeight() / 35f));
                    musclePreviewLabel.setText(muscleJlist.getSelectedValuesList().toString());
                }
            } else {
                musclePreviewLabel.setText("");
            }
        });

        // ADD TO PUBLIC ARRAYLIST
        addExercise.addActionListener(_ -> {
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
                ExercisePanel.updateMenuList("favExerciseModel");
                //WRITE TO DB
                try {
                    FirebaseManager.writeDBCreatedExercises(UserData.getCreatedExercises());
                    FirebaseManager.writeDBFavoriteExercises(UserData.getFavoriteExercises());
                } catch (Exception f) {
                    f.printStackTrace();
                }
                exercisePanel.activateStatus(new Color(46, 148, 76), "New exercise " + exercise.getName() + " has been created!");

            } else {

                if (exerciseName.getText().isEmpty() || exerciseName.getText().equals("Enter exercise name...")) {

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
        });
    }

    public void updateColors() {
        headerPanel.setBackground(AppThemeColors.PRIMARY);
        headerLabel.setForeground(AppThemeColors.foregroundColor);
        eastPanel.setBackground(AppThemeColors.panelColor);
        textPanel.setBackground(AppThemeColors.PRIMARY);
        nameAndFavPanel.setBackground(AppThemeColors.PRIMARY);
        exerciseName.setBackground(AppThemeColors.panelColor);
        setFav.setBackground(AppThemeColors.PRIMARY);
        exerciseInfo.setBackground(AppThemeColors.panelColor);
        previewPanel.setBackground(AppThemeColors.panelColor);
        infoPreviewLabel.setBackground(AppThemeColors.panelColor);
        musclePreviewLabel.setBackground(AppThemeColors.panelColor);
        muscleJlist.setBackground(AppThemeColors.panelColor);

        namePreviewLabel.setForeground(AppThemeColors.foregroundColor);
        setFav.setForeground(AppThemeColors.foregroundColor);
        exerciseInfo.setForeground(AppThemeColors.foregroundColor);
        exerciseName.setForeground(AppThemeColors.foregroundColor);
        infoPreviewLabel.setForeground(AppThemeColors.foregroundColor);
        musclePreviewLabel.setForeground(AppThemeColors.foregroundColor);
        muscleJlist.setForeground(AppThemeColors.foregroundColor);

        this.setBackground(AppThemeColors.PRIMARY);

    }

    @Override
    protected void paintComponent(Graphics g) {
        updateColors();
    }
}


