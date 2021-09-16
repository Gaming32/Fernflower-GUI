package io.github.gaming32.fernflowergui.utils;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import io.github.gaming32.fernflowergui.utils.OptionEnums.DisplayableEnum;
import io.github.gaming32.fernflowergui.utils.OptionEnums.LineEnding;
import io.github.gaming32.fernflowergui.utils.OptionEnums.LogLevel;

public class FernflowerOptions {
    public static abstract class FernflowerOption<T> {
        public final String internalName;
        public final String prettyName;
        public final String longDescription;

        protected FernflowerOption(String internalName, String prettyName) {
            this.internalName = internalName;
            this.prettyName = prettyName;
            this.longDescription = prettyName;
        }

        protected FernflowerOption(String internalName, String prettyName, String longDescription) {
            this.internalName = internalName;
            this.prettyName = prettyName;
            this.longDescription = longDescription;
        }

        public abstract T getValue();
        public abstract FernflowerOption<T> withValue(T value);
        protected abstract String internalDisplayValue();
    }

    public static final class BooleanOption extends FernflowerOption<Boolean> {
        private final boolean value;

        public BooleanOption(String internalName, String prettyName, boolean value) {
            super(internalName, prettyName);
            this.value = value;
        }

        public BooleanOption(String internalName, String prettyName, String longDescription, boolean value) {
            super(internalName, prettyName, longDescription);
            this.value = value;
        }

        @Override
        public Boolean getValue() {
            return value;
        }

        @Override
        public BooleanOption withValue(Boolean value) {
            return new BooleanOption(internalName, prettyName, value);
        }

        @Override
        protected String internalDisplayValue() {
            return value ? "1" : "0";
        }
    }

    public static final class IntegerOption extends FernflowerOption<Integer> {
        private final int value;

        public IntegerOption(String internalName, String prettyName, int value) {
            super(internalName, prettyName);
            this.value = value;
        }

        public IntegerOption(String internalName, String prettyName, String longDescription, int value) {
            super(internalName, prettyName, longDescription);
            this.value = value;
        }

        @Override
        public Integer getValue() {
            return value;
        }

        @Override
        public IntegerOption withValue(Integer value) {
            return new IntegerOption(internalName, prettyName, value);
        }

        @Override
        protected String internalDisplayValue() {
            return Integer.toString(value);
        }
    }

    public static final class StringOption extends FernflowerOption<String> {
        private final String value;

        public StringOption(String internalName, String prettyName, String value) {
            super(internalName, prettyName);
            this.value = value;
        }

        public StringOption(String internalName, String prettyName, String longDescription, String value) {
            super(internalName, prettyName, longDescription);
            this.value = value;
        }

        @Override
        public String getValue() {
            return value;
        }

        @Override
        public StringOption withValue(String value) {
            return new StringOption(internalName, prettyName, value);
        }

        @Override
        protected String internalDisplayValue() {
            return value;
        }
    }

    public static final class EnumOption<T extends DisplayableEnum> extends FernflowerOption<T> {
        private final T value;

        public EnumOption(String internalName, String prettyName, T value) {
            super(internalName, prettyName);
            this.value = value;
        }

        public EnumOption(String internalName, String prettyName, String longDescription, T value) {
            super(internalName, prettyName, longDescription);
            this.value = value;
        }

        @Override
        public T getValue() {
            return value;
        }

        @Override
        public EnumOption<T> withValue(T value) {
            return new EnumOption<>(internalName, prettyName, value);
        }

        @Override
        protected String internalDisplayValue() {
            return value.toString();
        }
    }

    public static final class ClassOption extends FernflowerOption<Class<?>> {
        private final Class<?> value;
        
        public ClassOption(String internalName, String prettyName, Class<?> value) {
            super(internalName, prettyName);
            this.value = value;
        }

        public ClassOption(String internalName, String prettyName, String longDescription, Class<?> value) {
            super(internalName, prettyName, longDescription);
            this.value = value;
        }

        public ClassOption(String internalName, String prettyName, String className) throws ClassNotFoundException {
            super(internalName, prettyName);
            this.value = Class.forName(className);
        }

        public ClassOption(String internalName, String prettyName, String longDescription, String className) throws ClassNotFoundException {
            super(internalName, prettyName, longDescription);
            this.value = Class.forName(className);
        }

        @Override
        public Class<?> getValue() {
            return value;
        }

        @Override
        public ClassOption withValue(Class<?> value) {
            return new ClassOption(internalName, prettyName, value);
        }

        @Override
        protected String internalDisplayValue() {
            return value == null ? "" : value.getName();
        }
    }

    public static final Map<String, FernflowerOption<?>> DEFAULT_OPTIONS = Collections.unmodifiableMap(new HashMap<>() {{
        put("rbr", new BooleanOption("rbr", "hide bridge methods", true));
        put("rsy", new BooleanOption("rsy", "hide synthetic class members", false));
        put("din", new BooleanOption("din", "decompile inner classes", true));
        put("dc4", new BooleanOption("dc4", "collapse 1.4 class references", true));
        put("das", new BooleanOption("das", "decompile assertions", true));
        put("hes", new BooleanOption("hes", "hide empty super invocation", true));
        put("hdc", new BooleanOption("hdc", "hide empty default constructor", true));
        put("dgs", new BooleanOption("dgs", "decompile generic signatures", false));
        put("ner", new BooleanOption("ner", "assume return not throwing exceptions", true));
        put("den", new BooleanOption("den", "decompile enumerations", true));
        put("rgn", new BooleanOption("rgn", "remove getClass() invocation", "remove getClass() invocation, when it is part of a qualified new statement", true));
        put("lit", new BooleanOption("lit", "output numeric literals \"as-is\"", false));
        put("asc", new BooleanOption("asc", "encode non-ASCII characters", "encode non-ASCII characters in string and character literals as Unicode escapes", false));
        put("bto", new BooleanOption("bto", "interpret int 1 as boolean true", "interpret int 1 as boolean true (workaround to a compiler bug)", true));
        put("nns", new BooleanOption("nns", "allow for not set synthetic attribute", "allow for not set synthetic attribute (workaround to a compiler bug)", false));
        put("uto", new BooleanOption("uto", "consider nameless types as Object", "consider nameless types as java.lang.Object (workaround to a compiler architecture flaw)", true));
        put("udv", new BooleanOption("udv", "reconstruct variable names from debug information, if present", true));
        put("rer", new BooleanOption("rer", "remove empty exception ranges", true));
        put("fdi", new BooleanOption("fdi", "de-inline finally structures", true));
        put("mpm", new IntegerOption("mpm", "maximum allowed processing time per decompiled method", "maximum allowed processing time per decompiled method, in seconds. 0 means no upper limit", 0));
        put("ren", new BooleanOption("ren", "rename ambiguous classes and class elements", "rename ambiguous (resp. obfuscated) classes and class elements", false));
        put("urc", new ClassOption("urc", "user-supplied class implementing IIdentifierRenamer interface", "full name of a user-supplied class implementing IIdentifierRenamer interface. It is used to determine which class identifiers should be renamed and provides new identifier names (see \"Renaming identifiers\")", (Class<?>)null));
        put("inn", new BooleanOption("inn", "check for IntelliJ IDEA-specific @NotNull", "check for IntelliJ IDEA-specific @NotNull annotation and remove inserted code if found", true));
        put("lac", new BooleanOption("lac", "decompile lambda expressions to anonymous classes", false));
        put("nls", new EnumOption<LineEnding>("nls", "define new line character to be used for output", LineEnding.OS_DEPENDENT));
        put("ind", new StringOption("ind", "indentation string (default is 3 spaces)", "   "));
        put("log", new EnumOption<LogLevel>("log", "a logging level", LogLevel.INFO));
    }});

    public static Map<String, Object> convertToFernflower(Map<String, FernflowerOption<?>> options) {
        Map<String, Object> result = new HashMap<>();
        for (Map.Entry<String, FernflowerOption<?>> option : options.entrySet()) {
            result.put(option.getKey(), option.getValue().internalDisplayValue());
        }
        return result;
    }
}
