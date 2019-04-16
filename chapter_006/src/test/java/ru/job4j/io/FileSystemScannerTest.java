package ru.job4j.io;

import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;

/**
 * Class FileSystemScannerTest | File system scan [#106929]
 * @author Aleksey Sidorenko (mailto:sidorenko.aleksey@gmail.com)
 * @since 12.04.2019
 */
public class FileSystemScannerTest {

    /**
     * Test getFiles()
     */
    @Test
    public void when4TargetFilesInFoldersThenFind3Files() {

        FileSystemScanner fsc = new FileSystemScanner();
        String rootPath = System.getProperty("java.io.tmpdir");
        final String separator = File.separator;

        File innerFolder0  = new File(rootPath + separator + "io_test_folder");
        File innerFolder1 = new File(rootPath + separator + "io_test_folder" + separator + "inner");
        innerFolder0.mkdir();
        innerFolder1.mkdir();
        File file0 = new File(innerFolder0.getPath() + separator + "file0.txt");
        File file1 = new File(innerFolder0.getPath() + separator + "file1.log");
        File file2 = new File(innerFolder0.getPath() + separator + "file2.txt");
        File file3 = new File(innerFolder1.getPath() + separator + "file3.log");
        File file4 = new File(innerFolder1.getPath() + separator + "file4.sql");
        try {
            file0.createNewFile();
            file1.createNewFile();
            file2.createNewFile();
            file3.createNewFile();
            file4.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }

        List<String> fileExtensions = Arrays.asList("log", "txt");
        List<File> expected = new ArrayList<>();
        expected.add(file0);
        expected.add(file1);
        expected.add(file2);
        expected.add(file3);
        List<File> result = fsc.getFiles(rootPath + separator + "io_test_folder", fileExtensions);

        assertEquals(4, result.size());
        result.forEach(item -> assertTrue(item.getName().endsWith(".txt")
                || item.getName().endsWith(".log")));
    }
}