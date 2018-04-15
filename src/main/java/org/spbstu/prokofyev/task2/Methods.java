package org.spbstu.prokofyev.task2;

import java.io.File;
import java.util.ArrayList;

public class Methods {
    private ArrayList<String> searchFile(File directory, String fName) {
        ArrayList<String> result = new ArrayList<String>();
        if (directory.isDirectory()) {
            File[] paths = directory.listFiles();
            for (File path : paths) {
                result.addAll(this.searchFile(path, fName));
            }
        } else {
            if (directory.getName().equals(fName))
                result.add(directory.getAbsolutePath());
        }

        return result;
    }

    public String inFile(File directory, String fName) {
        for (File file : directory.listFiles()) {
            if (file.getName().equals(fName))
                return file.getAbsolutePath();
        }
        return null;
    }
}




