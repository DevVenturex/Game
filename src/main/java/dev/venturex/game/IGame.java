package dev.venturex.game;


import dev.venturex.game.gfx.Renderer;
import dev.venturex.game.scenes.DefaultScene;
import dev.venturex.game.scenes.SceneManager;
import dev.venturex.game.utils.Timer;
import org.lwjgl.glfw.GLFWErrorCallback;
import org.lwjgl.opengl.GL;

import java.util.logging.Level;
import java.util.logging.Logger;

import static org.lwjgl.glfw.GLFW.*;

public abstract class IGame {

    public static final int TARGET_FPS = 75;
    public static final int TARGET_UPS = 30;

    private GLFWErrorCallback errorCallback;
    protected boolean running;
    protected Timer timer;
    public static Renderer renderer;
    public static SceneManager sceneManager;
    protected Window window;


    public IGame() {
        timer = new Timer();
        renderer = new Renderer();
        sceneManager = new SceneManager();
    }

    public void run() throws Exception {
        init();
        loop();
        dispose();
    }

    public void dispose() {
        renderer.dispose();
        sceneManager.clear();
        window.destroy();

        glfwTerminate();
        errorCallback.free();
    }

    public void init() throws Exception {
        errorCallback = GLFWErrorCallback.createPrint();
        glfwSetErrorCallback(errorCallback);

        if (!glfwInit()) throw new IllegalStateException("Unable to initialize GLFW!");

        window = new Window(640, 480, "Hello, world!", true);
        timer.init();
        sceneManager.init(new DefaultScene());
        renderer.init();
        running = true;
    }

    public abstract void loop();

    public void input() {
        sceneManager.input();
    }

    public void update() {
        sceneManager.update();
    }

    public void update(float delta) {
        sceneManager.update(delta);
    }

    public void render(float alpha) {
        sceneManager.render(alpha);
    }

    public void sync(int fps) {
        double lastLoopTime = timer.getLastLoopTime();
        double now = timer.getTime();
        float targetTime = 1f / fps;

        while (now - lastLoopTime < targetTime) {
            Thread.yield();

            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                Logger.getLogger(IGame.class.getName()).log(Level.SEVERE, null, e);
            }

            now = timer.getTime();
        }
    }

    public static boolean isDefaultContext() {
        return GL.getCapabilities().OpenGL32;
    }
}
