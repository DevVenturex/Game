package dev.venturex.game.gameobjects;

import org.joml.Matrix4f;
import org.joml.Vector2f;
import org.joml.Vector3f;

public class Camera extends GameObject {

    private Matrix4f projectionMatrix, viewMatrix;

    public Camera() {
        this.projectionMatrix = new Matrix4f();
        this.viewMatrix = new Matrix4f();
        adjustProjection();
    }

    public void adjustProjection() {
        projectionMatrix.identity();
        projectionMatrix.ortho(0.0f, 32.0f * 40.0f, 0.0f, 32.0f * 21.0f, 0.0f, 100.0f);
    }

    public Matrix4f getViewMatrix() {
        Vector3f camFront = new Vector3f(0.0f, 0.0f, -1.0f);
        Vector3f camUp = new Vector3f(0.0f, 1.0f, 0.0f);
        this.viewMatrix.identity();
        Vector2f pos = transform.getPosition();
        viewMatrix.lookAt(new Vector3f(pos.x, pos.y, 20.0f),
                camFront.add(pos.x, pos.y, 0.0f),
                camUp);
        return this.viewMatrix;
    }

    public Matrix4f getProjectionMatrix() {
        return projectionMatrix;
    }
}
