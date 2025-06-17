package com.swaglabs.utils;

import java.io.File;

public class FilesUtils {
    private FilesUtils() {
        super();
    }


    public static File getLatestFileFromDirectory(String directory) {
        File dir = new File(directory);
        if (!dir.exists() || !dir.isDirectory()) {
            LogsManager.error("Directory does not exist or is not a directory: " + directory);
            return null;
        }

        File[] files = dir.listFiles();
        if (files == null || files.length == 0) {
            LogsManager.warn("No files found in directory: " + directory);
            return null;
        }

        File latestFile = null;
        long lastModified = Long.MIN_VALUE;

        for (File file : files) {
            if (file.isFile() && file.lastModified() > lastModified) {
                lastModified = file.lastModified();
                latestFile = file;
            }
        }

        if (latestFile != null) {
            LogsManager.info("Latest file found: " + latestFile.getAbsolutePath());
        } else {
            LogsManager.warn("No valid files found in directory: " + directory);
        }

        return latestFile;
    }

    /*
     * This method deletes all files in the specified directory.
     * */
    public static void DeleteFiles(File filePath) {
        if (filePath == null || !filePath.exists()) {
            LogsManager.warn("File does not exist: " + filePath);
            return;
        }
        File[] files = filePath.listFiles();
        if (files != null) {
            for (File file : files) {
                if (file.isDirectory()) {
                    DeleteFiles(file); // Recursively delete files in the directory
                } else {
                    if (file.delete()) {
                        LogsManager.info("Deleted file: " + file.getAbsolutePath());
                    } else {
                        LogsManager.error("Failed to delete file: " + file.getAbsolutePath());
                    }
                }
            }
        } else {
            LogsManager.warn("No files found in directory: " + filePath);
        }
    }
    public static void CleanDirectory(File directory) {
        if (directory == null || !directory.exists() || !directory.isDirectory()) {
            LogsManager.error("Invalid directory: " + directory);
            return;
        }
        File[] files = directory.listFiles();
        if (files != null) {
            for (File file : files) {
                if (file.isFile()) {
                    if (file.delete()) {
                        LogsManager.info("Deleted file: " + file.getAbsolutePath());
                    } else {
                        LogsManager.error("Failed to delete file: " + file.getAbsolutePath());
                    }
                } else if (file.isDirectory()) {
                    CleanDirectory(file); // Recursively clean subdirectories
                }
            }
        } else {
            LogsManager.warn("No files found in directory: " + directory);
        }
    }
}
