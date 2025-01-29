package se.aljr.application.homepage;

import javax.swing.*;
import java.awt.*;

/**
 * Container för innehållet i den högra sektionen av fönstret
 */
public class RightPanel extends JPanel {

    public RightPanel(){
        init();
    }

    private void init(){
        this.setLayout(new BorderLayout(0,0));
        this.setOpaque(false);

    }
}
