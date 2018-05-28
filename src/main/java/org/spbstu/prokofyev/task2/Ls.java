package org.spbstu.prokofyev.task2;


import org.apache.commons.cli.*;

import java.util.List;

public class Ls {
    public static void main(String[] args) throws Exception {
        Options options = new Options();
        options.addOption("l", false, "");
        options.addOption("h", false, "");
        options.addOption("r", false, "");
        options.addOption("o", true, "");

        CommandLineParser parser = new DefaultParser();
        CommandLine lines = null;
        try{
            lines = parser.parse(options, args);
        } catch (ParseException e) {
            System.exit(1);
        }

        boolean r = lines.hasOption("r");
        boolean l = lines.hasOption("l");
        boolean o = lines.hasOption("o");
        boolean h = lines.hasOption("h");

        String outputFile = o ? lines.getOptionValue("o") : null;
        List<String> directories = lines.getArgList();

        Methods Ls = new Methods(l,h,r,o,outputFile);
        Ls.getInfo(directories);
    }
}