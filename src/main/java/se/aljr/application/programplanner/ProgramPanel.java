package se.aljr.application.programplanner;

import javax.swing.*;
import java.awt.*;

public class ProgramPanel extends JPanel {

    public ProgramPanel(int width, int height){
        this.setPreferredSize(new Dimension(width, height));
        this.setBackground(Color.cyan);
    }
}
