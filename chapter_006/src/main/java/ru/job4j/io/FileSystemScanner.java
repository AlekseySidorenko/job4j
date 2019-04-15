package ru.job4j.io;

import java.io.File;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Class FileSystemScanner | File system scan [#106929]
 * @author Aleksey Sidorenko (mailto:sidorenko.aleksey@gmail.com)
 * @since 12.04.2019
 */
public class FileSystemScanner {

    /**
     * Get list of files with set extension from target folder.
     * @param parent Target folder.
     * @param exts List of file extensions.
     * @return List of files.
     */
    List<File> getFiles(String parent, List<String> exts) {
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
            } else {
                for (String ext : exts) {
                    if (nextFile.getName().endsWith(ext)) {
                        result.add(nextFile);
                    }
                }
            }
        }
        return result;
    }
}