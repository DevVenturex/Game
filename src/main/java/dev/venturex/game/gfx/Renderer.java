package dev.venturex.game.gfx;

import dev.venturex.game.Game;
import dev.venturex.game.gameobjects.GameObject;
import dev.venturex.game.gameobjects.Sprite;
import dev.venturex.game.scenes.Scene;

import java.util.List;

public class Renderer {
    private final int MAX_BATCH_SIZE = 1024;
    private List<RenderBatch> batches;

    public void dispose() {
    }

    public void add(Sprite sprite) {
        boolean added = false;
        if (batches == null) batches.add(new RenderBatch(MAX_BATCH_SIZE));
        for (RenderBatch batch : batches) {
            if (batch.hasRoom()) {
                batch.addSprite(sprite);
                added = true;
                break;
            }
        }

        if (!added) {
            RenderBatch newBatch = new RenderBatch(MAX_BATCH_SIZE);
            newBatch.begin();
            batches.add(newBatch);
            newBatch.addSprite(sprite);
        }
    }

    public void render(float alpha) {
        for (RenderBatch batch : batches) {
            batch.render(alpha);
        }
    }

    public void init() {

    }
}