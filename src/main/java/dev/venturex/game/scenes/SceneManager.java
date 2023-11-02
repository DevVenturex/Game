package dev.venturex.game.scenes;

import dev.venturex.game.input.Inputs;

import java.lang.management.ManagementFactory;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.lwjgl.glfw.GLFW.GLFW_KEY_1;
import static org.lwjgl.glfw.GLFW.GLFW_KEY_2;

public class SceneManager {

    private List<Scene> scenes;
    private Scene currentScene;

    public SceneManager() {
        scenes = new ArrayList<>();
        currentScene = new DefaultScene();
    }

    public SceneManager(Scene currentScene) {
        scenes = new ArrayList<>();
        this.currentScene = currentScene;
    }

    public void init() throws Exception {
        setCurrentScene(new DefaultScene());
    }

    int scene = 0;
    boolean isDebug = ManagementFactory.getRuntimeMXBean().getInputArguments().toString().indexOf("-agentlib:jdwp") > 0;
    public void updateCurrentScene() throws Exception {
        if (isDebug) {
            if (Inputs.keyPressed(GLFW_KEY_1) && scene != 0) {
                scene = 0;
                setCurrentScene(new DefaultScene());
            }
            if (Inputs.keyPressed(GLFW_KEY_2) && scene != 1) {
                scene = 1;
                setCurrentScene(new TestScene());
            }
        }

        currentScene.update();
    }

    public <T extends Scene> void initScene(T scene) throws Exception {
        if (!scenes.contains(scene)) addScene(scene);
        scene.init(this);
    }


    public <T extends Scene> void setCurrentScene(T scene) throws Exception {
        if (!scenes.contains(scene)) addScene(scene);
        currentScene = scene;
        initScene(currentScene);
    }

    public <T extends Scene> void addScene(T scene) throws Exception {
        if (scenes.contains(scene)) return;
        scenes.add(scene);
    }

    public <T extends Scene> void removeScene(T scene) throws Exception {
        if (!scenes.contains(scene)) return;
        scenes.remove(scene);
    }
}
