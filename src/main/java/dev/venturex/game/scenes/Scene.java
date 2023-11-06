package dev.venturex.game.scenes;

import dev.venturex.game.IGame;

import java.util.ArrayList;
import java.util.List;

public interface Scene {

    public void init();

    public default void update() {
        update(1f / IGame.TARGET_UPS);
    }

    public void update(float delta);

    public void render(float alpha);

    public void input();

    public void clear();
}
