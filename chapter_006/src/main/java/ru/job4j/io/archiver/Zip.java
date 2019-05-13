package ru.job4j.io.archiver;

import java.io.*;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * Class Zip | Task Solution: Archive a project [#861]
 * @author @author Aleksey Sidorenko (mailto:sidorenko.aleksey@gmail.com)
 * @since 06.05.2019
 */
public class Zip {

    /**
     * Pack source folder to zip-archive.
     * @param source Source folder.
     * @param target Zip-archive.
     */
    public void pack(String sourcePath, List<File> source, File target) {
        String parentPath = new File(sourcePath).getParent();
        try (ZipOutputStream zip = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(target)))) {
            for (File file : source) {
                if (!file.isDirectory()) {
                    try (BufferedInputStream in = new BufferedInputStream(new FileInputStream(file))) {
                        String path = new File(parentPath).toURI().relativize(new File(file.getPath()).toURI()).getPath();
                        zip.putNextEntry(new ZipEntry(path));
                        byte[] buffer = new byte[in.available()];
                        in.read(buffer);
                        zip.write(buffer);
                        zip.closeEntry();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}