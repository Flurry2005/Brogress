package se.aljr.application.settings.custom;

import se.aljr.application.AppThemeColors;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.plaf.ComboBoxUI;
import javax.swing.plaf.basic.BasicButtonUI;
import javax.swing.plaf.basic.BasicComboBoxUI;
import javax.swing.plaf.basic.ComboPopup;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class JComboBoxUICustom extends BasicComboBoxUI {

    private JComboBox comboBox;
    private ComboPopup comboPopup;
    private boolean isHovered = false;
    public JComboBoxUICustom(JComboBox comboBox){
        this.comboBox = comboBox;
    }

    @Override
    protected JButton createArrowButton() {
        JButton button = new JButton("▼"); // Standard pilikon
        button.setUI(new BasicButtonUI(){
            public void setHovered(boolean hovered) {
                isHovered = hovered;
            }

            public boolean isHovered() {
                return isHovered;
            }

            @Override
            public void paint(Graphics g, JComponent c) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                if(isHovered){
                    g2.setColor(AppThemeColors.buttonBGHovered);
                }else{
                    g2.setColor(AppThemeColors.buttonBG);
                }

                g2.fillRoundRect(0, 0, c.getWidth(), c.getHeight(), 0, 0);

                super.paint(g, c);

                g2.dispose();
            }
            @Override
            protected void paintButtonPressed(Graphics g, AbstractButton b) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(AppThemeColors.buttonBGSelected);
                g2.fillRoundRect(0, 0, b.getWidth(), b.getHeight(), 0, 0);
                g2.dispose();
            }

        });
        button.setBorder(new LineBorder(AppThemeColors.foregroundColor)); // Ta bort kantlinje
        button.setContentAreaFilled(true); // Ingen bakgrundsfärg
        button.setPreferredSize(new Dimension(20, 20)); // Rätt storlek
        button.setMinimumSize(button.getPreferredSize());
        button.setMaximumSize(button.getPreferredSize());
        button.setFocusable(false);
        button.setBackground(AppThemeColors.buttonBG);
        button.setForeground(Color.WHITE); // Färgen på pilen
        button.setFocusPainted(false);


        button.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
                button.setBackground(AppThemeColors.buttonBGSelected);
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                super.mouseReleased(e);
                button.setBackground(AppThemeColors.buttonBG);
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                super.mouseEntered(e);
                isHovered = true;

                button.setBackground(AppThemeColors.buttonBGHovered);
                button.repaint();
            }

            @Override
            public void mouseExited(MouseEvent e) {
                super.mouseExited(e);
                isHovered = false;
                button.setBackground(AppThemeColors.buttonBG);
            }
        });


        return button;
    }
    @Override
    protected ComboPopup createPopup() {
        ComboPopup popup = super.createPopup();
        this.comboPopup = popup;

        JList<?> list = popup.getList();

        list.setBackground(AppThemeColors.foregroundColor);
        list.setForeground(AppThemeColors.foregroundColor);


        list.setSelectionBackground(Color.DARK_GRAY);

        return popup;
    }

    @Override
    protected LayoutManager createLayoutManager() {
        return new BasicComboBoxUI.ComboBoxLayoutManager() {
            @Override
            public void layoutContainer(Container parent) {
                JComboBox<?> cb = (JComboBox<?>) parent;
                int width = cb.getWidth();
                int height = cb.getHeight();

                // Sätt storlek för pilknappen
                if (arrowButton != null) {
                    int buttonWidth = 20; // Exakt bredd på pilknappen
                    arrowButton.setBounds(width - buttonWidth, 0, buttonWidth, height);
                }

                // Sätt storlek på redigeringsrutan (där texten visas)
                if (editor != null) {
                    editor.setBounds(5, 5, width - 30, height - 10);
                }
            }
        };
    }

    @Override
    public void paintCurrentValueBackground(Graphics g, Rectangle bounds, boolean hasFocus) {
        g.fillRect(bounds.x, bounds.y, bounds.width, bounds.height);
        g.setColor(Color.GREEN);
    }

    @Override
    public void paintCurrentValue(Graphics g, Rectangle bounds, boolean hasFocus) {
        g.setFont(comboBox.getFont());
        g.setColor(AppThemeColors.foregroundColor);

        if (comboBox.getSelectedItem() != null) {
            String text = comboBox.getSelectedItem().toString();
            FontMetrics fm = g.getFontMetrics();
            int availableWidth = bounds.width - 25; // Lämna plats för pilknappen

            // Trunkera texten om den är för bred
            if (fm.stringWidth(text) > availableWidth) {
                text = truncateText(text, fm, (int)(availableWidth*1.8));
            }

            int y = bounds.y + (bounds.height - fm.getHeight()) / 2 + fm.getAscent();
            g.drawString(text, bounds.x + 5, y); // Rätt position för texten
        }
    }

    private String truncateText(String text, FontMetrics fm, int maxWidth) {
        String ellipsis = "...";
        int ellipsisWidth = fm.stringWidth(ellipsis);

        if (fm.stringWidth(text) <= maxWidth) {
            return text; // Ingen trunkering behövs
        }

        int low = 0, high = text.length();
        while (low < high) {
            int mid = (low + high + 1) / 2;
            if (fm.stringWidth(text.substring(0, mid)) + ellipsisWidth <= maxWidth) {
                low = mid; // Prova en längre sträng
            } else {
                high = mid - 1; // Förkorta texten
            }
        }

        return text.substring(0, low) + ellipsis;
    }

    @Override
    protected void installDefaults() {
        super.installDefaults();
    }
    public JButton getArrowButton() {
        return arrowButton;
    }


    public ComboPopup getComboPopup() {
        return comboPopup;
    }
}
