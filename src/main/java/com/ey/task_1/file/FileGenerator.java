package com.ey.task_1.file;

import java.io.*;

import static com.ey.task_1.util.Constants.FILE_TYPE;
import static com.ey.task_1.util.Constants.LINES_AMOUNT;

public class FileGenerator {
    private final String dir;

    public FileGenerator(String dir) {
        this.dir = dir;
    }

    private boolean makeDirectory() {
        File outDir = new File(this.dir);
        if (outDir.exists()) {
            return true;
        } else {
            return outDir.mkdir();
        }
    }

    public void generateFiles(int amount) throws IOException {
        if (!makeDirectory()) {
            return;
        }

        RandomOutput output = new RandomOutput();
        for (int i = 0; i < amount; ++i) {
            File file = new File(this.dir + i + FILE_TYPE);
            file.createNewFile();
            PrintWriter printer = new PrintWriter(new BufferedWriter(new FileWriter(file.toString(), true)));

            for (int j = 0; j < LINES_AMOUNT; ++j) {
                output.set();
                printer.println(output.getOutput());
                output.clear();
            }
            printer.close();
        }
    }
}
