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
        put("rbr", REMOVE_BRIDGE);
        put("rsy", REMOVE_SYNTHETIC);
        put("din", DECOMPILE_INNER);
        put("dc4", DECOMPILE_CLASS_1_4);
        put("das", DECOMPILE_ASSERTIONS);
        put("hes", HIDE_EMPTY_SUPER);
        put("hdc", HIDE_DEFAULT_CONSTRUCTOR);
        put("dgs", DECOMPILE_GENERIC_SIGNATURES);
        put("ner", NO_EXCEPTIONS_RETURN);
        put("den", DECOMPILE_ENUM);
        put("rgn", REMOVE_GET_CLASS_NEW);
        put("lit", LITERALS_AS_IS);
        put("asc", ASCII_STRING_CHARACTERS);
        put("bto", BOOLEAN_TRUE_ONE);
        put("nns", SYNTHETIC_NOT_SET);
        put("uto", UNDEFINED_PARAM_TYPE_OBJECT);
        put("udv", USE_DEBUG_VAR_NAMES);
        put("rer", REMOVE_EMPTY_RANGES);
        put("fdi", FINALLY_DEINLINE);
        put("mpm", MAX_PROCESSING_METHOD);
        put("ren", RENAME_ENTITIES);
        put("inn", IDEA_NOT_NULL_ANNOTATION);
        put("lac", LAMBDA_TO_ANONYMOUS_CLASS);
        put("nls", NEW_LINE_SEPARATOR);
        put("ind", INDENT_STRING);
        put("log", LOG_LEVEL);
    }});

    public static final BooleanOption REMOVE_BRIDGE = new BooleanOption("rbr", "hide bridge methods", true);
    public static final BooleanOption REMOVE_SYNTHETIC = new BooleanOption("rsy", "hide synthetic class members", false);
    public static final BooleanOption DECOMPILE_INNER = new BooleanOption("din", "decompile inner classes", true);
    public static final BooleanOption DECOMPILE_CLASS_1_4 = new BooleanOption("dc4", "collapse 1.4 class references", true);
    public static final BooleanOption DECOMPILE_ASSERTIONS = new BooleanOption("das", "decompile assertions", true);
    public static final BooleanOption HIDE_EMPTY_SUPER = new BooleanOption("hes", "hide empty super invocation", true);
    public static final BooleanOption HIDE_DEFAULT_CONSTRUCTOR = new BooleanOption("hdc", "hide empty default constructor", true);
    public static final BooleanOption DECOMPILE_GENERIC_SIGNATURES = new BooleanOption("dgs", "decompile generic signatures", false);
    public static final BooleanOption NO_EXCEPTIONS_RETURN = new BooleanOption("ner", "assume return not throwing exceptions", true);
    public static final BooleanOption ENSURE_SYNCHRONIZED_MONITOR = new BooleanOption("esm", "ensure synchronized monitor", true);
    public static final BooleanOption DECOMPILE_ENUM = new BooleanOption("den", "decompile enumerations", true);
    public static final BooleanOption REMOVE_GET_CLASS_NEW = new BooleanOption("rgn", "remove getClass() invocation", "remove getClass() invocation, when it is part of a qualified new statement", true);
    public static final BooleanOption LITERALS_AS_IS = new BooleanOption("lit", "output numeric literals \"as-is\"", false);
    public static final BooleanOption BOOLEAN_TRUE_ONE = new BooleanOption("bto", "interpret int 1 as boolean true", "interpret int 1 as boolean true (workaround to a compiler bug)", true);
    public static final BooleanOption ASCII_STRING_CHARACTERS = new BooleanOption("asc", "encode non-ASCII characters", "encode non-ASCII characters in string and character literals as Unicode escapes", false);
    public static final BooleanOption SYNTHETIC_NOT_SET = new BooleanOption("nns", "allow for not set synthetic attribute", "allow for not set synthetic attribute (workaround to a compiler bug)", false);
    public static final BooleanOption UNDEFINED_PARAM_TYPE_OBJECT = new BooleanOption("uto", "consider nameless types as Object", "consider nameless types as java.lang.Object (workaround to a compiler architecture flaw)", true);
    public static final BooleanOption USE_DEBUG_VAR_NAMES = new BooleanOption("udv", "reconstruct variable names from debug information, if present", true);
    public static final BooleanOption USE_METHOD_PARAMETERS = new BooleanOption("ump", "use method parameters", true);
    public static final BooleanOption REMOVE_EMPTY_RANGES = new BooleanOption("rer", "remove empty exception ranges", true);
    public static final BooleanOption FINALLY_DEINLINE = new BooleanOption("fdi", "de-inline finally structures", true);
    public static final BooleanOption IDEA_NOT_NULL_ANNOTATION = new BooleanOption("inn", "check for IntelliJ IDEA-specific @NotNull", "check for IntelliJ IDEA-specific @NotNull annotation and remove inserted code if found", true);
    public static final BooleanOption LAMBDA_TO_ANONYMOUS_CLASS = new BooleanOption("lac", "decompile lambda expressions to anonymous classes", false);
    public static final BooleanOption BYTECODE_SOURCE_MAPPING = new BooleanOption("bsm", "bytecode source mapping", false);
    public static final BooleanOption IGNORE_INVALID_BYTECODE = new BooleanOption("iib", "ignore invalid bytecode", false);
    public static final BooleanOption VERIFY_ANONYMOUS_CLASSES = new BooleanOption("vac", "verify anonymous classes", false);

    public static final EnumOption<LogLevel> LOG_LEVEL = new EnumOption<>("log", "a logging level", LogLevel.INFO);
    public static final IntegerOption MAX_PROCESSING_METHOD = new IntegerOption("mpm", "maximum allowed processing time per decompiled method", "maximum allowed processing time per decompiled method, in seconds. 0 means no upper limit", 0);
    public static final BooleanOption RENAME_ENTITIES = new BooleanOption("ren", "rename ambiguous classes and class elements", "rename ambiguous (resp. obfuscated) classes and class elements", false);
    public static final ClassOption USER_RENAMER_CLASS = new ClassOption("urc", "user-supplied class implementing IIdentifierRenamer interface", "full name of a user-supplied class implementing IIdentifierRenamer interface. It is used to determine which class identifiers should be renamed and provides new identifier names (see \"Renaming identifiers\")", (Class<?>)null);
    public static final EnumOption<LineEnding> NEW_LINE_SEPARATOR = new EnumOption<>("nls", "define new line character to be used for output", LineEnding.OS_DEPENDENT);
    public static final StringOption INDENT_STRING = new StringOption("ind", "indentation string", "indentation string (default is 3 spaces)", "   ");
    public static final StringOption BANNER = new StringOption("ban", "banner", "");

    public static Map<String, Object> convertToFernflower(Map<String, FernflowerOption<?>> options) {
        Map<String, Object> result = new HashMap<>();
        for (Map.Entry<String, FernflowerOption<?>> option : options.entrySet()) {
            result.put(option.getKey(), option.getValue().internalDisplayValue());
        }
        return result;
    }
}
