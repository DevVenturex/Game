plugins {
    id("java")
}

group = "dev.venturex.game"
version = "1.0-SNAPSHOT"


repositories {
    mavenCentral()
}

dependencies {
    implementation(platform("org.lwjgl:lwjgl-bom:3.3.3"))

    implementation("org.lwjgl:lwjgl")
    implementation("org.lwjgl:lwjgl-nfd")
    implementation("org.lwjgl:lwjgl-assimp")
    implementation("org.lwjgl:lwjgl-glfw")
    implementation("org.lwjgl:lwjgl-nanovg")
    implementation("org.lwjgl:lwjgl-openal")
    implementation("org.lwjgl:lwjgl-opengl")
    implementation("org.lwjgl:lwjgl-stb")
    runtimeOnly ("org.lwjgl:lwjgl::natives-linux")
    runtimeOnly ("org.lwjgl:lwjgl-assimp::natives-linux")
    runtimeOnly("org.lwjgl:lwjgl-nfd::natives-linux")
    runtimeOnly ("org.lwjgl:lwjgl-glfw::natives-linux")
    runtimeOnly("org.lwjgl:lwjgl-nanovg::natives-linux")
    runtimeOnly ("org.lwjgl:lwjgl-openal::natives-linux")
    runtimeOnly ("org.lwjgl:lwjgl-opengl::natives-linux")
    runtimeOnly ("org.lwjgl:lwjgl-stb::natives-linux")
    implementation("org.joml:joml:1.10.5")
}