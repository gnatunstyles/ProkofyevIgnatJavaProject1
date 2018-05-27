package org.spbstu.prokofyev.task2;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class Methods {
    /**
     * class Flag
     */
    private class Flag {
        private String directory;
        private Boolean bool;

        Flag(String directory, Boolean bool) {
            this.directory = directory;
            this.bool = bool;
        }

        Flag(Boolean bool) {
            this.bool = bool;
        }
    }

    /**
     * the creation of the flags necessary to us by condition
     */
    private Flag lFlag = new Flag(false);
    private Flag hFlag = new Flag(false);
    private Flag rFlag = new Flag(false);
    private Flag oFlag = new Flag("", false);

    /**
     * get information about the command line
     *
     * @param args command line
     */
    void getInfo(String[] args) {
        if (args.length > 6) throw new IllegalArgumentException("input exception");
        if (args.length == 0) throw new IllegalArgumentException("no arguments");
        File directory = new File(args[args.length - 1]);
        boolean dir = directory.isDirectory();
        checkFlags(args);
        if (oFlag.bool) {
            String output = oFlag.directory;
            if (!output.equals("-o")) {
                try (BufferedWriter result = new BufferedWriter(new FileWriter(output + ".txt"))) {
                    ArrayList<String> text = getFileInfo(directory, dir);
                    result.write(makeString(text));
                } catch (IOException ex) {
                    System.out.println(ex.getMessage());
                }
            } else {
                throw new IllegalArgumentException("input exception");
            }
        } else {
            ArrayList<String> result = getFileInfo(directory, dir);
            System.out.print(result);
        }
    }

    /**
     * checking the correctness of flags
     *
     * @param args command line
     */
    private void checkFlags(String[] args) {
        for (String flag : args) {
            if (flag.equals("-l")) lFlag.bool = true;
            if (flag.equals("-h")) hFlag.bool = true;
            if (flag.equals("-r")) rFlag.bool = true;
            if (flag.equals("-o")) {
                oFlag.bool = true;
                oFlag.directory = args[args.length - 2];
            }
        }
    }

    /**
     * putting files to the list of files
     *
     * @param file this file
     * @param dir  directory
     * @return list of files
     */
    private ArrayList getFile(File file, boolean dir) {
        ArrayList<File> listOfFiles = new ArrayList<>();
        if (dir) {
            Collections.addAll(listOfFiles, file.listFiles());
        } else {
            listOfFiles.add(file);
        }
        return listOfFiles;
    }

    /**
     * check the right to execute as rwx
     *
     * @param file this file
     * @return right to execute as rwx
     */
    private String getRights(File file) {
        String rights = "";
        if (!hFlag.bool) {
            rights += file.canRead() ? "1" : "0";
            rights += file.canWrite() ? "1" : "0";
            rights += file.canExecute() ? "1" : "0";
        } else {
            rights += file.canRead() ? "r" : "";
            rights += file.canWrite() ? "w" : "";
            rights += file.canExecute() ? "x" : "";
        }
        return rights;
    }

    /**
     * get the time of the last modification of the file
     *
     * @param file this file
     * @return time of the last modification of the file
     */
    private String getTime(File file) {
        SimpleDateFormat time = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss ");
        return time.format(file.lastModified());
    }

    /**
     * get the size of file
     *
     * @param file this file
     * @return the size of file
     */
    private String getHumanReadableLength(File file) {
        String result = "";
        if (!hFlag.bool) {
            result += file.length() + "B ";
        } else {
            if (file.length() < 1024) {
                result += file.length() + "B ";
            }
            if (file.length() >= 1024 && file.length() < 1048576) {
                result += (file.length() / 1024) + "KB ";
            }
            if (file.length() >= 1048576 && file.length() < 1073741824) {
                result += (file.length() / 1048576) + "MB ";
            }
            if (file.length() >= 1073741824) {
                result += (file.length() / 1073741824) + "GB ";
            }
        }
        return result;
    }

    /**
     * get information about the file
     *
     * @param file      this file
     * @param directory directory of the file
     * @return information about the file
     */
    private ArrayList<String> getFileInfo(File file, boolean directory) {
        String result;
        ArrayList<File> listOfFiles = getFile(file, directory);
        ArrayList<String> info = new ArrayList<>();
        if (lFlag.bool) {
            for (File f1le : listOfFiles) {
                if (f1le.isDirectory()) {
                    result = (f1le.getName() + " directory");
                    info.add(result);
                } else if (rFlag.bool) {
                    result = (getHumanReadableLength(f1le) + " " + getTime(f1le) +
                            " " + getRights(f1le) + " " + f1le.getName() + " file");
                    info.add(result);
                } else {
                    result = (f1le.getName() + " file " + getRights(f1le) + " " + getTime(f1le) + " " +
                            getHumanReadableLength(f1le));
                    info.add(result);
                }
            }
        } else {
            for (File f1le : listOfFiles) {
                if (f1le.isDirectory()) {
                    result = (f1le.getName() + " directory");
                    info.add(result);
                } else {
                    result = (f1le.getName() + " file");
                    info.add(result);
                }
            }
        }
        return info;
    }


    /**
     * converts string of files to list of files
     *
     * @param text text we need to transform
     * @return the list of files
     */
    private String makeString(List<String> text) {
        StringBuilder result = new StringBuilder();
        for (String subString : text) {
            result.append(subString).append("\r\n");
        }
        return result.toString();
    }


}



