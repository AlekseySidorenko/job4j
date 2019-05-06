package ru.job4j.io.archiver;

import java.io.File;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Class ExtensionChecker | Task Solution: Archive a project [#861]
 * @author @author Aleksey Sidorenko (mailto:sidorenko.aleksey@gmail.com)
 * @since 06.05.2019
 */
public class ExtensionChecker {

    /**
     * Get list of files with set extension from target folder.
     * @param parent Target folder.
     * @param exts List of file extensions.
     * @return List of files.
     */
    public List<File> getFiles(String parent, List<String> exts) {
        Queue<File> queue = new LinkedList<>();
        List<File> result = new ArrayList<>();
        File file = new File(parent);
        queue.offer(file);
        while (!queue.isEmpty()) {
            File nextFile = queue.poll();
            if (nextFile.isDirectory() && (nextFile.listFiles() != null)) {
                for (File listFile : nextFile.listFiles()) {
                    queue.offer(listFile);
                }
            }
            if (checkExtension(nextFile.getName(), exts)) {
                result.add(nextFile);
            }
        }
        return result;
    }

    /**
     * Check file extension for containing in list of needless extensions.
     * @param fileName File name.
     * @param extensions List of files extensions.
     * @return True if file extension not contains in list of extensions.
     */
    boolean checkExtension(String fileName, List<String> extensions) {
        boolean result = true;
        for (String extension : extensions) {
            if (fileName.endsWith(extension)) {
                result = false;
                break;
            }
        }
        return result;
    }
}