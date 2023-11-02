package dev.venturex.game.input;


import static org.lwjgl.glfw.GLFW.GLFW_PRESS;
import static org.lwjgl.glfw.GLFW.GLFW_RELEASE;

public class KeyboardHandler {

    private static boolean keysPressed[] = new boolean[350];

    public static void keyCallback(long window, int key, int scancode, int action, int mods) {
        if (action == GLFW_PRESS) {
            keysPressed[key] = true;
        } else if (action == GLFW_RELEASE) {
            keysPressed[key] = false;
        }
    }

    public boolean[] getKeysPressed() {
        return keysPressed;
    }
}
