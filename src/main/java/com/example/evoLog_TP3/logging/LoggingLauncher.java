package com.example.evoLog_TP3.logging;

import spoon.Launcher;

public class LoggingLauncher {
    public static void main(String[] args) {
        Launcher launcher = new Launcher();
        launcher.addInputResource("src/main/java/com/example/evoLog_TP3/controller"); // Specify the source directory
        launcher.addProcessor(new LoggingInjector());
        launcher.setSourceOutputDirectory("src/output/java"); // Specify the output directory
        launcher.run();
    }
}