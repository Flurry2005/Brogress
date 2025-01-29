package se.aljr.application.exercise;

import se.aljr.application.exercise.Excercise.*;


import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.io.File;

public class ExercisePanel extends JPanel {
    private JList<Exercise> menuList;
    private JTextArea infoTextArea;
    private JLabel titleLabel;
    private JLabel musclesWorkedLabel;
    private String resourcePath;
    Font font;


    public ExercisePanel(int width, int height) throws InterruptedException {
        resourcePath = getClass().getClassLoader().getResource("resource.path").getPath().replace("resource.path","");
        try{
            font=Font.createFont(Font.TRUETYPE_FONT, new File(resourcePath+"BebasNeue-Regular.otf"));
            font = font.deriveFont(40f);
        }catch(Exception e){
            font = new Font("Arial", Font.BOLD, 40);
            e.printStackTrace();
        }
        // Set the layout for the panel
        setLayout(new BorderLayout());
        this.setPreferredSize(new Dimension(width,height));

        // Initialize components
        titleLabel = new JLabel("Exercise Details");
        titleLabel.setFont(font);

        musclesWorkedLabel = new JLabel("");
        musclesWorkedLabel.setFont(font.deriveFont(20f));
        infoTextArea = new JTextArea(10, 30);
        infoTextArea.setEditable(false);
        infoTextArea.setWrapStyleWord(true);
        infoTextArea.setLineWrap(true);
        infoTextArea.setFont(new Font("Arial", Font.TRUETYPE_FONT, 15));
        infoTextArea.setFocusable(false);
        menuList = new JList<>();
        JScrollPane exerciseScrollPanel = new JScrollPane(menuList);

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

        // Add selection listener
        menuList.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                Exercise selected = menuList.getSelectedValue();
                if (selected != null) {
                    infoTextArea.setText(selected.getInfo());
                    titleLabel.setText(selected.getName());
                    musclesWorkedLabel.setText(selected.getMusclesUsed());
                }
            }
        });

        // Create layout for the panel
        JPanel detailsPanel = new JPanel();
        detailsPanel.setLayout(new BoxLayout(detailsPanel, BoxLayout.Y_AXIS));
        detailsPanel.setPreferredSize(new Dimension(this.getWidth()-50,this.getHeight()));

        exerciseScrollPanel.setPreferredSize(new Dimension(200, this.getHeight()));

        JPanel topBar = new JPanel(new BorderLayout(0,0));
        topBar.setPreferredSize(new Dimension(960-exerciseScrollPanel.getWidth(), 80));
        topBar.setMaximumSize(new Dimension(960-exerciseScrollPanel.getWidth(), 80));

        JPanel topBarWestContainer = new JPanel();
        topBarWestContainer.setLayout(new BoxLayout(topBarWestContainer, BoxLayout.Y_AXIS));



        topBarWestContainer.add(titleLabel);
        topBarWestContainer.add(musclesWorkedLabel);
        topBar.add(topBarWestContainer,BorderLayout.WEST);



        detailsPanel.add(topBar, BorderLayout.NORTH);

        detailsPanel.add(infoTextArea, BorderLayout.SOUTH);

        add(exerciseScrollPanel, BorderLayout.WEST); // List on the left
        add(detailsPanel, BorderLayout.CENTER);     // Details on the center
    }


}
