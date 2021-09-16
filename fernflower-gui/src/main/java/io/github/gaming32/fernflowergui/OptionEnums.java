package io.github.gaming32.fernflowergui;

public final class OptionEnums {
    protected static interface DisplayableEnum {
        public String toDisplay();
    }

    public static enum LineEnding implements DisplayableEnum {
        OS_DEPENDENT((String)null, "OS Dependent"),
        WINDOWS("0", "Windows"),
        UNIX("1", "Unix");

        private String fernflowerValue;
        private String display;

        private LineEnding(String fernflowerValue, String display) {
            this.fernflowerValue = fernflowerValue;
            this.display = display;
        }

        public String toString() {
            return fernflowerValue;
        }

        public String toDisplay() {
            return display;
        }
    }

    public static enum LogLevel implements DisplayableEnum {
        TRACE,
        INFO,
        WARN,
        ERROR;

        public String toDisplay() {
            return this.toString();
        }
    }
}
