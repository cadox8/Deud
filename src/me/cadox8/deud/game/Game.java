package me.cadox8.deud.game;

import lombok.Getter;
import lombok.Setter;
import me.cadox8.deud.Launcher;
import me.cadox8.deud.api.GameAPI;
import me.cadox8.deud.config.Config;
import me.cadox8.deud.display.Display;
import me.cadox8.deud.entities.EntityData;
import me.cadox8.deud.graphics.GameCamera;
import me.cadox8.deud.graphics.fonts.Fonts;
import me.cadox8.deud.graphics.textures.Assets;
import me.cadox8.deud.graphics.textures.GUI;
import me.cadox8.deud.graphics.textures.Models;
import me.cadox8.deud.input.KeyManager;
import me.cadox8.deud.input.MouseManager;
import me.cadox8.deud.managers.DamageManager;
import me.cadox8.deud.saves.FileUtils;
import me.cadox8.deud.entities.PlayerData;
import me.cadox8.deud.states.EditorState;
import me.cadox8.deud.states.GameState;
import me.cadox8.deud.states.MenuState;
import me.cadox8.deud.states.State;
import net.arikia.dev.drpc.DiscordRPC;

import java.awt.*;
import java.awt.image.BufferStrategy;

public class Game implements Runnable {

    @Getter private static Game instance;

    @Getter private Config config;

    @Getter private Display display;
    @Getter private final int width, height;
    private final String title;

    @Getter @Setter private PlayerData playerData;
    @Getter @Setter private EntityData entityData;

    @Getter @Setter private boolean running = false;
    private Thread thread;

    private BufferStrategy bs;
    private Graphics g;

    //States
    @Getter @Setter public State editorState;
    @Getter @Setter public State gameState;
    public State menuState;

    // DamageManager
    @Getter private DamageManager damageManager;

    //Input
    @Getter private final KeyManager keyManager;
    @Getter private final MouseManager mouseManager;

    //Camera
    @Getter private GameCamera gameCamera;

    //gameAPI
    @Getter private GameAPI gameAPI;

    public Game(String title, int width, int height) {
        instance = this;

        this.width = width;
        this.height = height;
        this.title = title;

        keyManager = new KeyManager();
        mouseManager = new MouseManager();
    }

    private void init() {
        config = FileUtils.loadConfig();

        display = new Display(title, width, height, config.isFullScreen());
        display.getFrame().addKeyListener(keyManager);
        display.getFrame().addMouseListener(mouseManager);
        display.getFrame().addMouseMotionListener(mouseManager);
        display.getCanvas().addMouseListener(mouseManager);
        display.getCanvas().addMouseMotionListener(mouseManager);
        display.getCanvas().addMouseWheelListener(mouseManager);

        Fonts.init();
        GUI.init();
        Assets.init();
        Models.init();

        display.changeCursor(GUI.pointer);

        gameAPI = new GameAPI(this);
        gameCamera = new GameCamera(gameAPI, 0, 0);

        editorState = new EditorState(gameAPI);
        gameState = new GameState(gameAPI, "springwood");
        menuState = new MenuState(gameAPI);

        damageManager = new DamageManager();

        State.setState(menuState);
        Launcher.getDiscord().createNewPresence(gameAPI.getWorld().getEntityManager().getPlayer());
    }

    private void tick() {
        keyManager.tick();
        if (State.getState() != null) State.getState().tick();
        DiscordRPC.discordRunCallbacks();
    }

    private void render() {
        bs = display.getCanvas().getBufferStrategy();
        if (bs == null) {
            display.getCanvas().createBufferStrategy(3);
            return;
        }
        g = bs.getDrawGraphics();
        g.clearRect(0, 0, width, height);

        if (State.getState() != null) State.getState().render(g);

        bs.show();
        g.dispose();
    }

    public void run() {
        init();

        int fps = 60;
        double timePerTick = 1000000000f / fps;
        double delta = 0;
        long now;
        long lastTime = System.nanoTime();
        long timer = 0;
        int ticks = 0;

        while (running) {
            now = System.nanoTime();
            delta += (now - lastTime) / timePerTick;
            timer += now - lastTime;
            lastTime = now;

            if (delta >= 1) {
                tick();
                render();
                ticks++;
                delta--;
            }

            if (timer >= 1000000000) {
                //Log.log("FPS: " + ticks);
                ticks = 0;
                timer = 0;
            }
        }
        stop();
    }

    public synchronized void start() {
        if (running) return;
        running = true;
        thread = new Thread(this);
        thread.start();
    }

    public synchronized void stop() {
        if (!running) return;
        running = false;
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
