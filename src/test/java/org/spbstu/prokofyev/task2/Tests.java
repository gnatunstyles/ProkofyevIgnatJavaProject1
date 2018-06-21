package org.spbstu.prokofyev.task2;

import org.junit.jupiter.api.Test;

import java.io.File;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;


class Tests {
    Methods method;

    @Test
    void testGetTime() {
        method = new Methods(false, true, false, false, null);
        File file = new File("src/test/java/expected/fileString.txt");
        String actual = method.getTime(file);
        SimpleDateFormat time = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss ");
        String expected = time.format(file.lastModified());
        assertEquals(expected, actual);
    }

    @Test
    void testGetFile() {
        method = new Methods(false, true, false, false, null);
        File file = new File("src/test/java/expected/fileString.txt");
        ArrayList<File> actual = method.getFile(file);
        ArrayList<File> expected = new ArrayList<>();
        expected.add(file);
        assertEquals(expected, actual);
    }

    @Test
    void testGetFile2() {
        method = new Methods(false, true, false, false, null);
        File file = new File("src/test/java/expected");
        ArrayList<File> actual = method.getFile(file);
        ArrayList<File> expected = new ArrayList<>();
        Collections.addAll(expected, file.listFiles());
        assertEquals(expected, actual);
    }

    @Test
    void testGetRights() {
        method = new Methods(false, true, false, false, null);
        File file = new File("src/test/java/expected");
        String actual = method.getRights(file);
        String expected = "rwx";
        assertEquals(expected, actual);
    }

    @Test
    void testGetRights2() {
        method = new Methods(true, false, false, false, null);
        File file = new File("src/test/java/expected");
        String actual = method.getRights(file);
        String expected = "111";
        assertEquals(expected, actual);
    }

    @Test
    void testHumanReadable() {
        method = new Methods(true, true, false, false, null);
        File file = new File("src/test/java/expected");
        String actual = method.getHumanReadableLength(file);
        String expected = "4KB ";
        assertEquals(expected, actual);
    }

    @Test
    void testHumanReadable2() {
        method = new Methods(true, false, false, false, null);
        File file = new File("src/test/java/expected");
        String actual = method.getHumanReadableLength(file);
        String expected = "4096B ";
        assertEquals(expected, actual);
    }

    @Test
    void testGetFileInfo() {
        method = new Methods(false, false, false, false, null);
        File file = new File("src/test/java/expected");
        ArrayList<String> actual = method.getFileInfo(file);
        ArrayList<String> expected = new ArrayList<>();
        expected.add("fileList.txt file");
        expected.add("fileOutput.txt file");
        expected.add("fileString.txt file");
        assertEquals(expected, actual);
    }
}
