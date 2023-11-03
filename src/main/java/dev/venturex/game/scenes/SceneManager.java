package dev.venturex.game.scenes;

import dev.venturex.game.Game;
import dev.venturex.game.input.Inputs;

import java.lang.management.ManagementFactory;
import java.util.ArrayList;
import java.util.List;

import static org.lwjgl.glfw.GLFW.GLFW_KEY_1;
import static org.lwjgl.glfw.GLFW.GLFW_KEY_2;

public class SceneManager {

    private List<Scene> scenes;
    private Scene currentScene;

    public SceneManager() {
        this.scenes = new ArrayList<>();
    }

    public void init(Scene mainScene) {
        currentScene = mainScene;
        currentScene.init();
    }

    public void setScene(Scene scene) {
        if (currentScene != null) currentScene.clear();
        if (scene == null) return;
        if (!scenes.contains(scene)) scenes.add(scene);
        currentScene = scene;
    }

    public void input() {
        currentScene.input();
    }

    public void update() {
        currentScene.update();
    }


    public void update(float delta) {
        currentScene.update(delta);
    }

    public void render(float alpha) {
        Game.renderer.render(alpha);
    }

    public void clear() {
        currentScene.clear();
    }

    public Scene getCurrentScene() {
        return currentScene;
    }
}
