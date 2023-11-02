package dev.venturex.game.gameobjects;

import dev.venturex.game.gfx.Mesh;

public class MeshObject extends GameObject {

    public Mesh mesh;

    public void onRender() {
        mesh = new Mesh();
    }

    @Override
    public void init(String name) {
        super.init(name);
    }

    @Override
    public void update() {
        super.update();
        onRender();
    }

    @Override
    public void clear() {
        super.clear();
        mesh.clear();
    }
}
