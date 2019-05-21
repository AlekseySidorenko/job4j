package ru.job4j.io.archiver;

import org.junit.Test;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import static junit.framework.Assert.*;

/**
 * Class ZipTest | Task Solution: Archive a project [#861]
 * @author @author Aleksey Sidorenko (mailto:sidorenko.aleksey@gmail.com)
 * @since 20.05.2019
 */
public class ZipTest {

    @Test
    public void whenGetFolderThenGetZipArchive() {
        ExtensionChecker checker = new ExtensionChecker();
        Zip zip = new Zip();
        final String separator = File.separator;
        String rootPath = System.getProperty("java.io.tmpdir") + separator + "io_test_folder_zip";
        List<String> fileExtensions = Arrays.asList("log");
        File innerFolder0 = new File(rootPath);
        File innerFolder1 = new File(innerFolder0.getPath() + separator + "toZip");
        File innerFolder2 = new File(innerFolder1.getPath() + separator + "inner");
        innerFolder0.mkdir();
        innerFolder1.mkdir();
        innerFolder2.mkdir();
        File targetFile = new File(rootPath + separator + "archive.zip");
        File file0 = new File(innerFolder1.getPath() + separator + "file0.txt");
        File file1 = new File(innerFolder1.getPath() + separator + "file1.log");
        File file2 = new File(innerFolder2.getPath() + separator + "file2.log");
        File file3 = new File(innerFolder2.getPath() + separator + "file3.sql");
        try {
            targetFile.createNewFile();
            file0.createNewFile();
            file1.createNewFile();
            file2.createNewFile();
            file3.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        List<File> filesToZip = checker.getFiles(innerFolder0.getPath(), fileExtensions);
        zip.pack(innerFolder0.getPath(), filesToZip, targetFile);
        assertTrue(targetFile.length() > 0);
        file0.delete();
        file1.delete();
        file2.delete();
        file3.delete();
        targetFile.delete();
        innerFolder2.delete();
        innerFolder1.delete();
        innerFolder0.delete();
    }
}