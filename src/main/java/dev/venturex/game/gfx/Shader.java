package dev.venturex.game.gfx;

import org.joml.*;
import org.lwjgl.BufferUtils;

import javax.print.DocFlavor;
import java.io.IOException;
import java.nio.FloatBuffer;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.lwjgl.opengl.GL11.GL_FALSE;
import static org.lwjgl.opengl.GL20.*;
import static org.lwjgl.opengl.GL20.glGetShaderInfoLog;

public class Shader {
    private int programId;
    private int vertexId, fragmentId;
    private boolean beingUsed = false;
    private String shaderName;


    // /home/basti/Documents/Projects/Dev/Java/Game/src/main/resources/shaders/fDefault.frag

    public Shader() throws IOException {
        this("Default");
    }

    public Shader(String shaderName) throws IOException {
        this.shaderName = shaderName;
        this.programId = glCreateProgram();
        String vertexPath = "src/main/resources/shaders/v" + shaderName + ".vert";
        String fragmentPath = "src/main/resources/shaders/f" + shaderName + ".frag";
        createVertexShader(vertexPath);
        createFragmentShader(fragmentPath);
    }

    private void createVertexShader(String vertexPath) throws IOException {
        String shaderSource = new String(Files.readAllBytes(Paths.get(vertexPath)));
        vertexId = createShader(shaderSource, GL_VERTEX_SHADER);
    }

    private void createFragmentShader(String fragmentPath) throws IOException {
        String shaderSource = new String(Files.readAllBytes(Paths.get(fragmentPath)));
        fragmentId = createShader(shaderSource, GL_FRAGMENT_SHADER);
    }

    protected int createShader(String shaderSource, int shaderType) {
        int shaderId = glCreateShader(shaderType);
        glShaderSource(shaderId, shaderSource);
        glCompileShader(shaderId);

        int success = glGetShaderi(shaderId, GL_COMPILE_STATUS);
        if (success == GL_FALSE) {
            int len = glGetShaderi(shaderId, GL_COMPILE_STATUS);
            System.out.println("ERROR: '" + shaderName + "'\n\tShader compilation failed.");
            System.out.println(glGetShaderInfoLog(shaderId, len));
        }
        return shaderId;
    }

    public void link() {
        // Link shaders and check for errors
        glAttachShader(programId, vertexId);
        glAttachShader(programId, fragmentId);
        glLinkProgram(programId);

        // Check for linking errors
        int success = glGetProgrami(programId, GL_LINK_STATUS);
        if (success == GL_FALSE) {
            int len = glGetProgrami(programId, GL_INFO_LOG_LENGTH);
            System.out.println("ERROR: '" + shaderName + "'\n\tLinking of shaders failed.");
            System.out.println(glGetProgramInfoLog(programId, len));
            assert false : "";
        }
    }

    public void use() {
        link();
        if (!beingUsed) {
            // Bind shader program
            glUseProgram(programId);
            beingUsed = true;
        }
    }

    public void detach() {
        glUseProgram(0);
        beingUsed = false;
    }

    public void uploadMat4f(String varName, Matrix4f mat4) {
        int varLocation = glGetUniformLocation(programId, varName);
        use();
        FloatBuffer matBuffer = BufferUtils.createFloatBuffer(16);
        mat4.get(matBuffer);
        glUniformMatrix4fv(varLocation, false, matBuffer);
    }

    public void uploadMat3f(String varName, Matrix3f mat3) {
        int varLocation = glGetUniformLocation(programId, varName);
        use();
        FloatBuffer matBuffer = BufferUtils.createFloatBuffer(9);
        mat3.get(matBuffer);
        glUniformMatrix3fv(varLocation, false, matBuffer);
    }

    public void uploadVec4f(String varName, Vector4f vec) {
        int varLocation = glGetUniformLocation(programId, varName);
        use();
        glUniform4f(varLocation, vec.x, vec.y, vec.z, vec.w);
    }

    public void uploadVec3f(String varName, Vector3f vec) {
        int varLocation = glGetUniformLocation(programId, varName);
        use();
        glUniform3f(varLocation, vec.x, vec.y, vec.z);
    }

    public void uploadVec2f(String varName, Vector2f vec) {
        int varLocation = glGetUniformLocation(programId, varName);
        use();
        glUniform2f(varLocation, vec.x, vec.y);
    }

    public void uploadFloat(String varName, float val) {
        int varLocation = glGetUniformLocation(programId, varName);
        use();
        glUniform1f(varLocation, val);
    }

    public void uploadInt(String varName, int val) {
        int varLocation = glGetUniformLocation(programId, varName);
        use();
        glUniform1i(varLocation, val);
    }

    public void uploadTexture(String varName, int slot) {
        int varLocation = glGetUniformLocation(programId, varName);
        use();
        glUniform1i(varLocation, slot);
    }

    public void uploadIntArray(String varName, int[] array) {
        int varLocation = glGetUniformLocation(programId, varName);
        use();
        glUniform1iv(varLocation, array);
    }
}
