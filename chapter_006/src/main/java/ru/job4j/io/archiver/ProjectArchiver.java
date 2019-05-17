package ru.job4j.io.archiver;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Class ProjectArchiver | Task Solution: Archive a project [#861]
 * @author @author Aleksey Sidorenko (mailto:sidorenko.aleksey@gmail.com)
 * @since 06.05.2019
 */
public class ProjectArchiver {

    ArgsParser parser;
    ExtensionChecker checker = new ExtensionChecker();
    Zip zip = new Zip();

    public ProjectArchiver(String[] args) {
        parser = new ArgsParser(args);
    }

    /**
     * Archive the project folder.
     */
    public void archive() {
        List<String> extensions = new ArrayList<>();
        String sourcePath = parser.getDirectory();
        String extension = parser.getExclude();
        String targetPath = parser.getOutput();
        extensions.add(extension);
        List<File> files = checker.getFiles(sourcePath, extensions);
        zip.pack(sourcePath, files, new File(targetPath));
    }

    public static void main(String[] args) {
        ProjectArchiver archiver = new ProjectArchiver(args);
        archiver.archive();
    }
}