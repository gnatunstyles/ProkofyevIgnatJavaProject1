package org.spbstu.prokofyev.task2;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.PrintStream;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.assertEquals;


class Tests {

    @Test
    void output() throws Exception {
        String[] command = "-l -r -h -o output testFiles".split(" ");
        ls.main(command);
        String expect = new String(Files.readAllBytes(Paths.get("expected/fileOutput.txt")));
        String actual = new String(Files.readAllBytes(Paths.get("output.txt")));
        assertEquals(expect, actual);
    }

    @Test
    void fileList() throws Exception {
        String[] command = "testFiles".split(" ");
        PrintStream o = new PrintStream(new File("fileListOutput.txt"));
        System.setOut(o);
        ls.main(command);
        String expect = new String(Files.readAllBytes(Paths.get("expected/fileList.txt")));
        String actual = new String(Files.readAllBytes(Paths.get("fileListOutput.txt")));

        assertEquals(expect, actual);
    }

    @Test
    void fileString() throws Exception {
        String[] command = "-l -r -h testFiles".split(" ");
        PrintStream o = new PrintStream(new File("fileStringOutput.txt"));
        System.setOut(o);
        ls.main(command);
        String expect = new String(Files.readAllBytes(Paths.get("expected/fileString.txt")));
        String actual = new String(Files.readAllBytes(Paths.get("fileStringOutput.txt")));
        assertEquals(expect, actual);
    }
}
