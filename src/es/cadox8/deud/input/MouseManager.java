package es.cadox8.deud.input;

import es.cadox8.deud.ui.UiManager;
import lombok.Getter;
import lombok.Setter;

import java.awt.event.*;

public class MouseManager implements MouseListener, MouseMotionListener, MouseWheelListener {

    @Getter private boolean leftPressed, rightPressed;
    @Getter private int mouseX, mouseY, mouseXClick, mouseYClick;
    @Setter @Getter private UiManager uiManager;

    //Mouse
    @Override
    public void mousePressed(MouseEvent e) {
        if (e.getButton() == MouseEvent.BUTTON1) this.leftPressed = true;
        if (e.getButton() == MouseEvent.BUTTON3) this.rightPressed = true;

        this.mouseXClick = e.getX();
        this.mouseYClick = e.getY();
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if (e.getButton() == MouseEvent.BUTTON1) this.leftPressed = false;
        if (e.getButton() == MouseEvent.BUTTON3) this.rightPressed = false;

        if (this.uiManager != null) this.uiManager.onMouseClicked(e);

        this.mouseXClick = 0;
        this.mouseYClick = 0;
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        this.mouseX = e.getX();
        this.mouseY = e.getY();

        if (this.uiManager != null) this.uiManager.onMouseMove(e);
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
