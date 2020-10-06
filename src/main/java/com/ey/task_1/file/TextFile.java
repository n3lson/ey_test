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

public class TextFile {
    private final File file;

    public TextFile(File file){
        this.file = file;
    }

    public List<String> lines() throws IOException {
        return Files.lines(this.file.toPath()).collect(Collectors.toList());
    }

    public void mergeWith(Path src) throws IOException {
        Files.write(
                this.file.toPath(),
                Files.readAllBytes(src),
                StandardOpenOption.APPEND
        );
    }

    public void removeLinesIfContain(String str) throws IOException {
        List<String> lines = this.lines();
        int linesAmount = lines.size();
        lines.removeIf(l -> l.contains(str));
        Files.write(this.file.toPath(), lines);

        System.out.printf(ANSI_CYAN + "Strings removed: %d\n" + ANSI_RESET, linesAmount - lines.size());
    }
}
