package io.github.gaming32.fernflowergui.utils;

import java.io.File;
import java.util.Arrays;
import java.util.Map;

import org.jetbrains.java.decompiler.main.decompiler.ConsoleDecompiler;
import org.jetbrains.java.decompiler.main.decompiler.PrintStreamLogger;
import org.jetbrains.java.decompiler.main.extern.IFernflowerLogger;

public class FernflowerUtils {
    public static void decompile(File from, File to) {
        decompile(new File[] {from}, to, Map.of());
    }

    public static void decompile(File[] sources, File to) {
        decompile(sources, to, Map.of());
    }

    public static void decompile(File from, File to, Map<String, Object> options) {
        decompile(new File[] {from}, to, options);
    }

    public static void decompile(File[] sources, File to, Map<String, Object> options) {
        LibraryDecompiler decompiler = new LibraryDecompiler(to, options);
        decompile(decompiler, sources, to, options);
    }

    public static <T extends ConsoleDecompiler> void decompile(T decompiler, File[] sources, File to, Map<String, Object> options) {
        Arrays.stream(sources).forEach(decompiler::addSource);
        decompiler.decompileContext();
    }

    // Publicize constructor
    protected static class LibraryDecompiler extends ConsoleDecompiler {
        public LibraryDecompiler(File destination, Map<String, Object> options, IFernflowerLogger logger) {
            super(destination, options, logger);
        }

        public LibraryDecompiler(File destination, Map<String, Object> options) {
            super(destination, options, new PrintStreamLogger(System.out));
        }
    }
}
