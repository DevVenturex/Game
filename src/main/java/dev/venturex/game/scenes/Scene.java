package dev.venturex.game.scenes;

public abstract class Scene {

    public SceneManager manager;

    protected void init(SceneManager manager) throws Exception {
        this.manager = manager;
        System.out.println("New Scene: " + this.getClass().getName());
    }

    protected void update() throws Exception {}
}
