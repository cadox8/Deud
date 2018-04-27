package me.cadox8.deud.input;

import lombok.Getter;
import me.cadox8.deud.ui.UIManager;

import java.awt.event.*;

public class MouseManager implements MouseListener, MouseMotionListener, MouseWheelListener {

    @Getter private boolean leftPressed, rightPressed;
    private int mouseX, mouseY, mouseXClick, mouseYClick;
    private UIManager uiManager;

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

        if (uiManager != null) uiManager.onMouseRelease(e);

        mouseXClick = 0;
        mouseYClick = 0;
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        mouseX = e.getX();
        mouseY = e.getY();

        if (uiManager != null) uiManager.onMouseMove(e);
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

    public void setUIManager(UIManager uiManager) {
        this.uiManager = uiManager;
    }
}
