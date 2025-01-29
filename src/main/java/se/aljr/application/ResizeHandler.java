package se.aljr.application;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ResizeHandler extends MouseAdapter {
    private final JFrame frame;
    private final int BORDER_DRAG_THICKNESS = 8; // Bredd på "dragområde" vid kanten
    private final double aspectRatio;           // Fönstrets aspect ratio
    private boolean resizing = false;
    private Point prevPoint = null;
    private int cursorType = Cursor.DEFAULT_CURSOR;

    public ResizeHandler(JFrame frame, double aspectRatio) {
        this.frame = frame;
        this.aspectRatio = aspectRatio; // Förhållandet mellan bredd och höjd (t.ex. 4:3)
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        // Uppdatera muspekaren beroende på position nära kanten
        int newCursorType = getCursorType(e);
        if (newCursorType != cursorType) {
            cursorType = newCursorType;
            frame.setCursor(Cursor.getPredefinedCursor(cursorType));
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
        prevPoint = e.getPoint();

        // Kontrollera om musen är nära kanten för att starta resizing
        if (cursorType != Cursor.DEFAULT_CURSOR) {
            resizing = true;
        }
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        if (resizing && prevPoint != null) {
            Point currentPoint = e.getPoint();
            int dx = currentPoint.x - prevPoint.x;
            int dy = currentPoint.y - prevPoint.y;

            Rectangle bounds = frame.getBounds();

            // Ändra storlek baserat på cursorType och bibehåll aspect ratio
            if (cursorType == Cursor.SE_RESIZE_CURSOR || cursorType == Cursor.NW_RESIZE_CURSOR) {
                // Skala både bredd och höjd proportionellt
                int sizeDelta = Math.max(dx, dy);
                int newWidth = bounds.width + (cursorType == Cursor.SE_RESIZE_CURSOR ? sizeDelta : -sizeDelta);
                int newHeight = (int) (newWidth / aspectRatio);

                if (cursorType == Cursor.NW_RESIZE_CURSOR) {
                    bounds.x += bounds.width - newWidth;
                    bounds.y += bounds.height - newHeight;
                }

                bounds.width = newWidth;
                bounds.height = newHeight;

            } else if (cursorType == Cursor.SW_RESIZE_CURSOR || cursorType == Cursor.NE_RESIZE_CURSOR) {
                // Skala både bredd och höjd proportionellt
                int sizeDelta = Math.max(Math.abs(dx), Math.abs(dy));
                int newWidth = bounds.width + (cursorType == Cursor.NE_RESIZE_CURSOR ? sizeDelta : -sizeDelta);
                int newHeight = (int) (newWidth / aspectRatio);

                if (cursorType == Cursor.SW_RESIZE_CURSOR) {
                    bounds.x += bounds.width - newWidth;
                } else if (cursorType == Cursor.NE_RESIZE_CURSOR) {
                    bounds.y += bounds.height - newHeight;
                }

                bounds.width = newWidth;
                bounds.height = newHeight;

            } else if (cursorType == Cursor.E_RESIZE_CURSOR || cursorType == Cursor.W_RESIZE_CURSOR) {
                // Justera endast bredd och skala höjd
                int newWidth = bounds.width + (cursorType == Cursor.E_RESIZE_CURSOR ? dx : -dx);
                int newHeight = (int) (newWidth / aspectRatio);

                if (cursorType == Cursor.W_RESIZE_CURSOR) {
                    bounds.x += bounds.width - newWidth;
                }

                bounds.width = newWidth;
                bounds.height = newHeight;

            } else if (cursorType == Cursor.S_RESIZE_CURSOR || cursorType == Cursor.N_RESIZE_CURSOR) {
                // Justera endast höjd och skala bredd
                int newHeight = bounds.height + (cursorType == Cursor.S_RESIZE_CURSOR ? dy : -dy);
                int newWidth = (int) (newHeight * aspectRatio);

                if (cursorType == Cursor.N_RESIZE_CURSOR) {
                    bounds.y += bounds.height - newHeight;
                }

                bounds.width = newWidth;
                bounds.height = newHeight;
            }

            frame.setBounds(bounds);
            prevPoint = currentPoint;
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        resizing = false;
        prevPoint = null;
    }

    // Metod för att bestämma vilken pekarikon som ska användas
    private int getCursorType(MouseEvent e) {
        int x = e.getX();
        int y = e.getY();
        int width = frame.getWidth();
        int height = frame.getHeight();

        boolean nearLeft = x < BORDER_DRAG_THICKNESS;
        boolean nearRight = x > width - BORDER_DRAG_THICKNESS;
        boolean nearTop = y < BORDER_DRAG_THICKNESS;
        boolean nearBottom = y > height - BORDER_DRAG_THICKNESS;

        if (nearLeft && nearTop) return Cursor.NW_RESIZE_CURSOR;
        if (nearLeft && nearBottom) return Cursor.SW_RESIZE_CURSOR;
        if (nearRight && nearTop) return Cursor.NE_RESIZE_CURSOR;
        if (nearRight && nearBottom) return Cursor.SE_RESIZE_CURSOR;
        if (nearLeft) return Cursor.W_RESIZE_CURSOR;
        if (nearRight) return Cursor.E_RESIZE_CURSOR;
        if (nearTop) return Cursor.N_RESIZE_CURSOR;
        if (nearBottom) return Cursor.S_RESIZE_CURSOR;

        return Cursor.DEFAULT_CURSOR; // Standardpekare om inte nära kanten
    }
}
