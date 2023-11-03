package dev.venturex.game;

public class Game extends IGame {

    @Override
    public void loop() {
        float delta;
        float acc = 0f;
        float interval = 1f / TARGET_UPS;
        float alpha;

        while (running) {
            if (window.isClosing()) {
                running = false;
            }

            delta = timer.getDelta();
            acc += delta;

            input();

            while (acc >= interval) {
                update();
                timer.updateUPS();
                acc -= interval;
            }

            alpha = acc / interval;

            render(alpha);
            timer.updateFPS();

            timer.update();
            window.update();

            if (!window.isVSyncEnabled()) {
                sync(TARGET_FPS);
            }
        }
    }
}
