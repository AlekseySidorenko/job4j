package ru.job4j.io.archiver;

import org.junit.Test;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import static junit.framework.Assert.assertEquals;

/**
 * Class ExtensionCheckerTest | Task Solution: Archive a project [#861]
 * @author @author Aleksey Sidorenko (mailto:sidorenko.aleksey@gmail.com)
 * @since 17.05.2019
 */
public class ExtensionCheckerTest {

    @Test
    public void whenGetFolderThenGetFilteredFilesInFolder() {
        final String separator = File.separator;
        String rootPath = System.getProperty("java.io.tmpdir") + separator + "io_test_folder_zip";
        List<String> fileExtensions = Arrays.asList("log");
        ExtensionChecker checker = new ExtensionChecker();
        File innerFolder0  = new File(rootPath);
        File innerFolder1 = new File(rootPath + separator + "inner");
        innerFolder0.mkdir();
        innerFolder1.mkdir();
        File file0 = new File(innerFolder0.getPath() + separator + "file0.txt");
        File file1 = new File(innerFolder0.getPath() + separator + "file1.log");
        File file2 = new File(innerFolder1.getPath() + separator + "file2.log");
        File file3 = new File(innerFolder1.getPath() + separator + "file3.sql");
        try {
            file0.createNewFile();
            file1.createNewFile();
            file2.createNewFile();
            file3.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        List<File> expected = new ArrayList<>();
        expected.add(file0);
        expected.add(file3);
        List<File> result = checker.getFiles(rootPath, fileExtensions);
        // 2 inner folders + 2 files after filtering
        assertEquals(4, result.size());
        file0.delete();
        file1.delete();
        file2.delete();
        file3.delete();
        innerFolder1.delete();
        innerFolder0.delete();
    }
}