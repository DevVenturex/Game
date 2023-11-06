package dev.venturex.game.scenes;

import dev.venturex.game.gfx.Mesh;

public class DefaultScene implements Scene {

    private Mesh mesh;

    @Override
    public void init()  {
        mesh = new Mesh();
    }

    @Override
    public void update(float delta) {

    }

    @Override
    public void render(float alpha) {
        mesh.render();
    }

    @Override
    public void input() {

    }

    @Override
    public void clear() {
        mesh.clear();
    }
}
