package dev.venturex.game.scenes;

import dev.venturex.game.Utils;
import dev.venturex.game.gameobjects.MeshObject;
import dev.venturex.game.gfx.Mesh;
import dev.venturex.game.gfx.Shader;
import dev.venturex.game.input.Inputs;

import static org.lwjgl.glfw.GLFW.GLFW_KEY_SPACE;

public class DefaultScene extends Scene {

    private Shader shader;
    private MeshObject meshObj;

    @Override
    protected void init(SceneManager mng) throws Exception {
        super.init(manager);
        shader = new Shader();
        shader.createVertShader(Utils.readFile("src/main/resources/shaders/vDefault.vert"));
        shader.createFragShader(Utils.readFile("src/main/resources/shaders/fDefault.frag"));
        shader.link();
        meshObj = new MeshObject();
        addGameObject(meshObj);
    }

    @Override
    protected void update() throws Exception {
        super.update();
        shader.bind();
    }

    @Override
    protected void clear() {
        super.clear();

    }
}
