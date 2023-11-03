package dev.venturex.game.components;

import org.joml.Vector2f;

public class Transform {

    private Vector2f position;
    private Vector2f scale;
    private float rotation;

    public Transform() {
        init(new Vector2f(), 0f, new Vector2f());
    }

    public Transform(Vector2f position) {
        init(position, 0f, new Vector2f());
    }

    public Transform(Vector2f position, float rotation) {
        init(position, rotation, new Vector2f());
    }

    public Transform(Vector2f position, Vector2f scale) {
        init(position, 0f, scale);
    }

    public Transform(Vector2f position, float rotation, Vector2f scale) {
        init(position, rotation, scale);
    }

    public void init(Vector2f position, float rotation, Vector2f scale) {
        this.position = position;
        this.rotation = rotation;
        this.scale = scale;
    }

    public Vector2f getPosition() {
        return position;
    }

    public Vector2f getScale() {
        return scale;
    }

    public float getRotation() {
        return rotation;
    }
}
