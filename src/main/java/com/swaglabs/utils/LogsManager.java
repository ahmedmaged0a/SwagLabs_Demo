package com.swaglabs.utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class LogsManager {
    public static final String LOGS_PATH = "test-outputs/logs";

    private LogsManager() {
        super();
        // Private constructor to prevent instantiation
    }

    public static Logger logger() {
        return LogManager.getLogger(Thread.currentThread().getStackTrace()[3].getClassName());
    }

    // Log methods for different log levels
    //LogsManager.trace("This is a trace message", "with multiple", "arguments");
    public static void trace(String... message) {
        logger().trace(String.join(" ", message));
    }

    //LogsManager.debug("This is a debug message", "with multiple", "arguments");
    public static void debug(String... message) {
        logger().debug(String.join(" ", message));
    }

    //LogsManager.info("This is an info message", "with multiple", "arguments");
    public static void info(String... message) {
        logger().info(String.join(" ", message));
    }

    //LogsManager.warn("This is a warning message", "with multiple", "arguments");
    public static void warn(String... message) {
        logger().warn(String.join(" ", message));
    }

    //LogsManager.error("This is an error message", "with multiple", "arguments");
    public static void error(String... message) {
        logger().error(String.join(" ", message));
    }

    //LogsManager.fatal("This is a fatal message", "with multiple", "arguments");
    public static void fatal(String... message) {
        logger().fatal(String.join(" ", message));
    }
}
