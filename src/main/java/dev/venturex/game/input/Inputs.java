package dev.venturex.game.input;

public class Inputs {

    private static KeyboardHandler keyHandler = new KeyboardHandler();

    private static boolean keysPressed[] = keyHandler.getKeysPressed();

    public static boolean keyPressed(int key) {
        return keysPressed[key];
    }


}
