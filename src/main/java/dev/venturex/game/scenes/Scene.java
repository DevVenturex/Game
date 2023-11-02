package dev.venturex.game.scenes;

import dev.venturex.game.gameobjects.GameObject;

import java.util.ArrayList;
import java.util.List;

public abstract class Scene {

    public SceneManager manager;
    public List<GameObject> objects;

    protected void init(SceneManager manager) throws Exception {
        this.manager = manager;
        objects = new ArrayList<>();
        System.out.println("New Scene: " + this.getClass().getSimpleName());
    }

    protected void update() throws Exception {
        objects.forEach(GameObject::update);
    }

    protected void clear() {}

    public <T extends GameObject> void addGameObject(T obj) {
        if (objects.contains(obj)) return;
        objects.add(obj);
        obj.init(obj.getClass().getSimpleName());
    }

    public <T extends GameObject> void removeGameObject(T obj) {
        if (!objects.contains(obj)) return;
        obj.clear();
        objects.remove(obj);
    }
}
