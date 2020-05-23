package me.cadox8.deud.input;

import lombok.Getter;
import lombok.Setter;
import me.cadox8.deud.ui.NysvaManager;

import java.awt.event.*;

public class MouseManager implements MouseListener, MouseMotionListener, MouseWheelListener {

    @Getter private boolean leftPressed, rightPressed;
    private int mouseX, mouseY, mouseXClick, mouseYClick;
    @Setter @Getter private NysvaManager nysvaUI;

    //Mouse
    @Override
    public void mousePressed(MouseEvent e) {
        if (e.getButton() == MouseEvent.BUTTON1) leftPressed = true;
        if (e.getButton() == MouseEvent.BUTTON3) rightPressed = true;

        mouseXClick = e.getX();
        mouseYClick = e.getY();
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if (e.getButton() == MouseEvent.BUTTON1) leftPressed = false;
        if (e.getButton() == MouseEvent.BUTTON3) rightPressed = false;

        if (nysvaUI != null) nysvaUI.onMouseClicked(e);

        mouseXClick = 0;
        mouseYClick = 0;
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        mouseX = e.getX();
        mouseY = e.getY();

        if (nysvaUI != null) nysvaUI.onMouseMove(e);
    }

    @Override
    public void mouseDragged(MouseEvent e) {
    }

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

    //Wheel
    public void mouseWheelMoved(MouseWheelEvent e) {
    }
}
