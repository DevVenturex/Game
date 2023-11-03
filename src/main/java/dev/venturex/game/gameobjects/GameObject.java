package dev.venturex.game.gameobjects;

import dev.venturex.game.components.Transform;

public abstract class GameObject {

    public String name;

    public Transform transform;

    public void init() {
        init(getClass().getSimpleName());
    }
    public void init(String name) {
        this.name = name;
    }
    public void update() {}
    public void clear() {}

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
