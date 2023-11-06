package dev.venturex.game.gfx;

import dev.venturex.game.utils.Utils;

import static org.lwjgl.opengl.GL11.GL_FALSE;
import static org.lwjgl.opengl.GL20.*;
import static org.lwjgl.opengl.GL20.glGetProgramInfoLog;

public class Shader {

    private String vertexSource, fragmentSource;
    private int vertexId, fragmentId;
    private int programId;

    public Shader() {
        this("Default");
    }

    public Shader(String shaderName) {
        vertexSource = Utils.readFile("src/main/resources/shaders/v" + shaderName + ".vert");
        fragmentSource = Utils.readFile("src/main/resources/shaders/f" + shaderName + ".frag");

        // Vertex Shader
        vertexId = glCreateShader(GL_VERTEX_SHADER);
        glShaderSource(vertexId, vertexSource);
        glCompileShader(vertexId);

        int success = glGetShaderi(vertexId, GL_COMPILE_STATUS);
        if (success == GL_FALSE) {
            int len = glGetShaderi(vertexId, GL_INFO_LOG_LENGTH);
            System.out.println("ERROR: Vertex shader compilation failed");
            System.out.println(glGetShaderInfoLog(vertexId, len));
        }

        // Fragment Shader
        fragmentId = glCreateShader(GL_FRAGMENT_SHADER);
        glShaderSource(fragmentId, fragmentSource);
        glCompileShader(fragmentId);

        success = glGetShaderi(fragmentId, GL_COMPILE_STATUS);
        if (success == GL_FALSE) {
            int len = glGetShaderi(fragmentId, GL_INFO_LOG_LENGTH);
            System.out.println("ERROR: Fragment shader compilation failed");
            System.out.println(glGetShaderInfoLog(fragmentId, len));
        }

        programId = glCreateProgram();
        glAttachShader(programId, vertexId);
        glAttachShader(programId, fragmentId);
        glLinkProgram(programId);

        success = glGetProgrami(programId, GL_LINK_STATUS);
        if (success == GL_FALSE) {
            int len = glGetProgrami(programId, GL_INFO_LOG_LENGTH);
            System.out.println("ERROR: Linking of shaders failed");
            System.out.println(glGetProgramInfoLog(programId, len));
        }

        glDeleteShader(vertexId);
        glDeleteShader(fragmentId);
    }

    public void bind() {
        glUseProgram(programId);
    }

    public void unbind() {
        glUseProgram(0);
    }
}
