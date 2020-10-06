package com.ey.task_1.file;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.List;
import java.util.stream.Collectors;

import static com.ey.task_1.util.Constants.ANSI_CYAN;
import static com.ey.task_1.util.Constants.ANSI_RESET;

/*
* This class represents the text file that we generated
* and want to operate on.
* */
public class TextFile {
    private final File file;

    public TextFile(File file){
        this.file = file;
    }

    // Get lines of this file.
    public List<String> lines() throws IOException {
        return Files.lines(this.file.toPath()).collect(Collectors.toList());
    }

    // Merge this file with another one.
    public void mergeWith(Path src) throws IOException {
        // Read bytes from the source and append to the current file.
        Files.write(
                this.file.toPath(),
                Files.readAllBytes(src),
                StandardOpenOption.APPEND
        );
    }

    // Remove lines that contain the specified string.
    public void removeLinesIfContain(String str) throws IOException {
        // Read lines from the file.
        List<String> lines = this.lines();
        // Get lines amount.
        int linesAmount = lines.size();
        // Iterate through the lines and remove any that contains the string.
        lines.removeIf(l -> l.contains(str));
        // Rewrite data to the file.
        Files.write(this.file.toPath(), lines);

        // Log information about the number of the removed lines.
        System.out.printf(ANSI_CYAN + "Strings removed: %d\n" + ANSI_RESET, linesAmount - lines.size());
    }
}
