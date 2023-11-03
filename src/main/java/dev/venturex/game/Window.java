package dev.venturex.game;

import dev.venturex.game.input.KeyboardHandler;
import org.lwjgl.glfw.GLFWKeyCallback;
import org.lwjgl.glfw.GLFWVidMode;
import org.lwjgl.opengl.GL;
import org.lwjgl.opengl.GLCapabilities;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.system.MemoryUtil.NULL;

public class Window {

    private final long glfwWindow;
    private boolean vsync;

    public Window(int width, int height, String title, boolean vsync) {
        this.vsync = vsync;

        glfwDefaultWindowHints();
        glfwWindowHint(GLFW_VISIBLE, GLFW_FALSE);
        long temp = glfwCreateWindow(1, 1, "", NULL, NULL);
        glfwMakeContextCurrent(temp);
        GL.createCapabilities();
        GLCapabilities caps = GL.getCapabilities();
        glfwDestroyWindow(temp);

        glfwDefaultWindowHints();
        if (caps.OpenGL32) {
            glfwWindowHint(GLFW_CONTEXT_VERSION_MAJOR, 3);
            glfwWindowHint(GLFW_CONTEXT_VERSION_MINOR, 2);
            glfwWindowHint(GLFW_OPENGL_PROFILE, GLFW_OPENGL_CORE_PROFILE);
            glfwWindowHint(GLFW_OPENGL_FORWARD_COMPAT, GLFW_TRUE);
        } else if (caps.OpenGL21) {
            glfwWindowHint(GLFW_CONTEXT_VERSION_MAJOR, 2);
            glfwWindowHint(GLFW_CONTEXT_VERSION_MINOR, 1);
        } else
            throw new RuntimeException("Neither OpenGL 3.2 nor OpenGL 2.1 is " +
                    "supported, you may want to update your graphics driver.");
        glfwWindowHint(GLFW_RESIZABLE, GLFW_FALSE);

        glfwWindow = glfwCreateWindow(width, height, title, NULL, NULL);
        if (glfwWindow == NULL) {
            glfwTerminate();
            throw new RuntimeException("Failed to create GLFW window!");
        }

        GLFWVidMode vidMode = glfwGetVideoMode(glfwGetPrimaryMonitor());
        glfwSetWindowPos(glfwWindow,
                (vidMode.width() - width) / 2,
                (vidMode.height() - height) / 2
        );

        glfwMakeContextCurrent(glfwWindow);
        GL.createCapabilities();

        if (vsync) glfwSwapInterval(1);
        ;
        glfwSetKeyCallback(glfwWindow, KeyboardHandler::keyCallback);
    }

    public void destroy() {
        glfwDestroyWindow(glfwWindow);
    }

    public void setTitle(String title) {
        glfwSetWindowTitle(glfwWindow, title);
    }

    public boolean isClosing() {
        return glfwWindowShouldClose(glfwWindow);
    }

    public void update() {
        glfwSwapBuffers(glfwWindow);
        glfwPollEvents();
    }

    public boolean isVSyncEnabled() {
        return this.vsync;
    }

    public void setVsync(boolean vsync) {
        this.vsync = vsync;
        if (vsync) {
            glfwSwapInterval(1);
        } else
            glfwSwapInterval(0);
    }
}
