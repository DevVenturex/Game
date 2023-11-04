package dev.venturex.game.scenes;

import dev.venturex.game.Game;
import dev.venturex.game.IGame;
import dev.venturex.game.gameobjects.Camera;
import dev.venturex.game.gameobjects.GameObject;
import dev.venturex.game.gameobjects.Sprite;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface Scene {

    List<GameObject> gameObjects = new ArrayList<>();
    Camera camera = new Camera();

    public default List<GameObject> getGameObjects() {
        return gameObjects;
    }

    public default Camera getCamera() {
        return camera;
    }

    public default GameObject getGameObject(String name) {
        for (GameObject go : gameObjects) {
            if (go.getName().equals(name)) return go;
        }
        return null;
    }

    public default void addGameObject(GameObject obj) throws Exception {
        if (gameObjects.contains(obj)) return;
        gameObjects.add(obj);
        obj.init();
        if (obj instanceof Sprite) {
            Sprite sprite = (Sprite) obj;
            Game.renderer.add(sprite);
        }
    }

    public default void removeGameObject(GameObject obj) {
        if (!gameObjects.contains(obj)) return;
        obj.clear();
        gameObjects.remove(obj.getName());
    }

    public void init() throws Exception;

    public default void update() {
        update(1f / IGame.TARGET_UPS);
    }


    public void update(float delta);

    public void render(float alpha);

    public void input();

    public void clear();
}
