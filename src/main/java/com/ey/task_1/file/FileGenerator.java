package com.ey.task_1.file;

import java.io.*;

import static com.ey.task_1.util.Constants.FILE_TYPE;
import static com.ey.task_1.util.Constants.LINES_AMOUNT;


/*
* This class generates files and writes random output to them.
* */
public class FileGenerator {
    private final String dir;

    public FileGenerator(String dir) {
        this.dir = dir;
    }

    // Make directory if it doesn't exist.
    private boolean makeDirectory() {
        File outDir = new File(this.dir);
        if (outDir.exists()) {
            return true;
        } else {
            return outDir.mkdir();
        }
    }

    // Generate files.
    public void generateFiles(int amount) throws IOException {
        // If the directory doesn't exist or making directory failed.
        if (!makeDirectory()) {
            return;
        }

        // Create an instance of RandomOutput.
        RandomOutput output = new RandomOutput();
        /*
         * Create the amount of files passed to this method
         * and write random output to them.
         * */
        for (int i = 0; i < amount; ++i) {
            // Create a file in the directory we want with the type we want.
            File file = new File(this.dir + i + FILE_TYPE);
            file.createNewFile();
            // Create an instance of PrintWriter to write to the file.
            PrintWriter printer = new PrintWriter(new BufferedWriter(new FileWriter(file.toString(), true)));

            // Write random output to the file.
            for (int j = 0; j < LINES_AMOUNT; ++j) {
                output.set();
                printer.println(output.getOutput());
                output.clear();
            }
            printer.close();
        }
    }
}
