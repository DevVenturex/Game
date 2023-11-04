package dev.venturex.game.scenes;

import dev.venturex.game.gameobjects.Sprite;
import org.joml.Vector4f;

public class DefaultScene implements Scene {

    @Override
    public void init() throws Exception {
        Sprite sprite = new Sprite();
        sprite.setColor(new Vector4f(1,0,0,1));
        addGameObject(sprite);
    }

    @Override
    public void update(float delta) {

    }

    @Override
    public void render(float alpha) {

    }

    @Override
    public void input() {

    }

    @Override
    public void clear() {

    }
}
