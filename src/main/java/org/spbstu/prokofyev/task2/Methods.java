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
     * the creation of the flags necessary to us by condition
     */
    private boolean lFlag, hFlag, rFlag, oFlag;
    private String outputFile;

    public Methods(boolean lFlag, boolean hFlag, boolean rFlag, boolean oFlag,
                   String outputFile) {
        this.lFlag = lFlag;
        this.rFlag = rFlag;
        this.hFlag = hFlag;
        this.oFlag = oFlag;
        this.outputFile = outputFile;
    }

    /**
     * get information about the command line
     *
     * @param directories command line
     */
    void getInfo(List<String> directories) {
        ArrayList<String> result = new ArrayList<>();
        for (String directory : directories) {
            result.addAll(getFileInfo(new File(directory)));
        }
        if (oFlag) {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(new File(outputFile)))) {
                for (String info : result) {
                    writer.write(info.trim());
                    writer.newLine();
                }
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            for (String info : result) {
                System.out.println(info.trim());
            }
        }
    }


    /**
     * putting files to the list of files
     *
     * @param file this file
     * @return list of files
     */
    public ArrayList<File> getFile(File file) {
        ArrayList<File> listOfFiles = new ArrayList<>();
        if (file.isDirectory()) {
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
    public String getRights(File file) {
        String rights = "";
        if (!hFlag) {
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
    public String getTime(File file) {
        SimpleDateFormat time = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss ");
        return time.format(file.lastModified());
    }

    /**
     * get the size of file
     *
     * @param file this file
     * @return the size of file
     */
    public String getHumanReadableLength(File file) {
        String result = "";
        if (!hFlag) {
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
     * @param file this file
     * @return information about the file
     */
    public ArrayList<String> getFileInfo(File file) {
        ArrayList<File> listOfFiles = getFile(file);
        ArrayList<String> info = new ArrayList<>();
        if (lFlag) {
            for (File f1le : listOfFiles) {
                if (f1le.isDirectory()) {
                    info.add(f1le.getName() + " directory");
                } else {
                    info.add(f1le.getName() + " file " + getRights(f1le) +
                            " " + getTime(f1le) + " " +
                            getHumanReadableLength(f1le));
                }
            }
        } else {
            for (File f1le : listOfFiles) {
                if (f1le.isDirectory()) {
                    info.add(f1le.getName() + " directory");
                } else {
                    info.add(f1le.getName() + " file");
                }
            }
        }
        if (rFlag) {
            Collections.reverse(info);
        }
        return info;
    }



}



