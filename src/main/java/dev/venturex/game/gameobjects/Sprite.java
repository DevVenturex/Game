package dev.venturex.game.gameobjects;

import dev.venturex.game.gfx.Texture;
import org.joml.Vector2f;
import org.joml.Vector4f;

public class Sprite extends GameObject {

    private Vector4f color;
    private Vector2f[] texCoords;
    private Texture texture;

    @Override
    public void init() {
        super.init();

        this.color = new Vector4f(1,1,1,1);
        this.texture = null;
    }

    @Override
    public void update() {
        super.update();
    }


    @Override
    public void clear() {
        super.clear();
    }

    public void setColor(Vector4f color) {
        this.color = color;
    }

    public Vector4f getColor() {
        return color;
    }

    public Texture getTexture() {
        return texture;
    }

    public Vector2f[] getTexCoords() {
        texCoords = new Vector2f[]{
                new Vector2f(1, 1),
                new Vector2f(1, 0),
                new Vector2f(0, 0),
                new Vector2f(0, 1),
        };
        return texCoords;
    }
}
