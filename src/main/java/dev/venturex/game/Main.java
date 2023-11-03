package dev.venturex.game;

public class Main {

    public static void main(String[] args) {
        try {
            new Game().run();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
