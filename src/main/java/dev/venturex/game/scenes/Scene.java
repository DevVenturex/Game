package dev.venturex.game.scenes;

import dev.venturex.game.Game;
import dev.venturex.game.IGame;
import dev.venturex.game.gameobjects.Camera;
import dev.venturex.game.gameobjects.GameObject;
import dev.venturex.game.gameobjects.Sprite;
import dev.venturex.game.gfx.RenderBatch;

import java.util.HashMap;
import java.util.Map;

public interface Scene {

    Map<String, GameObject> gameObjects = new HashMap<>();
    Camera camera = new Camera();

    public default Map<String, GameObject> getGameObjects() {
        return gameObjects;
    }

    public default GameObject getGameObject(String name) {
        return gameObjects.get(name);
    }

    public default void addGameObject(GameObject obj) {
        if (gameObjects.containsKey(obj.getName())) return;
        gameObjects.put(obj.getName(), obj);
        obj.init();
        if (obj instanceof Sprite) {
            Sprite sprite = (Sprite) obj;
            Game.renderer.add(sprite);
        }
    }

    public default void removeGameObject(GameObject obj) {
        if (!gameObjects.containsKey(obj.getName())) return;
        obj.clear();
        gameObjects.remove(obj.getName());
    }

    public void init();

    public default void update() {
        update(1f / IGame.TARGET_UPS);
    }


    public void update(float delta);

    public void render(float alpha);

    public void input();

    public void clear();
}
