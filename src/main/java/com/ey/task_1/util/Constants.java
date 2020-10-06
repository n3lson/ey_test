package com.ey.task_1.util;

public class Constants {
    public static final String DELIMITER = "//";

    public static final String DATE_PATTERN = "dd.MM.yyyy";

    public static final int SEQUENCE_LENGTH = 10;
    public static final int PAST_YEARS = 5;

    public static final int LATIN_SIZE = 26;
    public static final int LATIN_UPPER_ORIGIN = 0x41;
    public static final int LATIN_UPPER_BOUND = 0x5a;
    public static final int LATIN_LOWER_ORIGIN = 0x61;
    public static final int LATIN_LOWER_BOUND = 0x7a;

    public static final int CYRILLIC_ORIGIN = 0x410;
    public static final int CYRILLIC_BOUND = 0x44f;
    public static final int CYRILLIC_SIZE = CYRILLIC_BOUND - CYRILLIC_ORIGIN + 1;

    public static final int INTEGER_ORIGIN = 1;
    public static final int INTEGER_BOUND = 100_000_000;
    public static final double DOUBLE_ORIGIN = 1.0;
    public static final double DOUBLE_BOUND = 20.0;
    public static final int PRECISION = 8;
    public static final String FLOATING_POINT = ".";

    public static final String OUTPUT_DIRECTORY = System.getProperty("user.dir") + "\\generated_output\\";
    public static final String FILE_TYPE = ".txt";
    public static final int FILES_AMOUNT = 2;
    public static final int LINES_AMOUNT = 1000;

    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_CYAN = "\u001B[36m";

    private Constants() { }
}
