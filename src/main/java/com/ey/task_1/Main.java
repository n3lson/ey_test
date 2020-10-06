package com.ey.task_1;

import com.ey.task_1.db.DatabaseImporter;
import com.ey.task_1.file.FileGenerator;
import com.ey.task_1.file.TextFile;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;

import static com.ey.task_1.util.Constants.FILES_AMOUNT;
import static com.ey.task_1.util.Constants.OUTPUT_DIRECTORY;

public class Main {
    public static void main(String[] args) throws IOException {
        /* Task 1
        * Generate 100 files with 100 000 lines of random output.
        * */
        new FileGenerator(OUTPUT_DIRECTORY).generateFiles(FILES_AMOUNT);

        /* Task 2
        * Merge 2 generated files into one.
        * Remove the lines that contain a specified string.
        * */
        TextFile file = new TextFile(new File(OUTPUT_DIRECTORY + "0.txt"));
        file.mergeWith(new File(OUTPUT_DIRECTORY + "1.txt").toPath());
        file.removeLinesIfContain("abc");

        /* Task 3
        *  Import data from the file to the database.
        * */
        try (DatabaseImporter importer = new DatabaseImporter()) {
            importer.importData(file);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            System.out.println("No DB driver found.");
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Importing failed.");
        } catch (ParseException e) {
            System.out.println("Failed to parse date.");
            e.printStackTrace();
        }
    }
}
