package es.cadox8.deud.input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyManager implements KeyListener {

    private final boolean[] keys;
    private final boolean[] justPressed;
    private final boolean[] cantPress;
    public boolean up, down, left, right, shift, space, drop, esc;
    public boolean debug, tests;

    public KeyManager() {
        keys = new boolean[256];
        justPressed = new boolean[keys.length];
        cantPress = new boolean[keys.length];
    }

    public void tick() {
        for (int i = 0; i < keys.length; i++) {
            if (cantPress[i] && !keys[i]) {
                cantPress[i] = false;
            } else {
                if (justPressed[i]) {
                    cantPress[i] = true;
                    justPressed[i] = false;
                }
            }
            if (!cantPress[i] && keys[i]) justPressed[i] = true;
        }

        up = keys[KeyEvent.VK_W];
        down = keys[KeyEvent.VK_S];
        left = keys[KeyEvent.VK_A];
        right = keys[KeyEvent.VK_D];

        shift = keys[KeyEvent.VK_SHIFT];

        space = keys[KeyEvent.VK_SPACE];
        drop = keys[KeyEvent.VK_Q];

        debug = keys[KeyEvent.VK_F1];
        tests = keys[KeyEvent.VK_G];

        esc = keys[KeyEvent.VK_ESCAPE];
    }

    public boolean keyJustPressed(int keyCode) {
        if (keyCode < 0 || keyCode >= keys.length) return false;
        return justPressed[keyCode];
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() < 0 || e.getKeyCode() >= keys.length) return;
        keys[e.getKeyCode()] = true;
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() < 0 || e.getKeyCode() >= keys.length) return;
        keys[e.getKeyCode()] = false;
    }

    @Override
    public void keyTyped(KeyEvent e) {}
}
