package com.ey.task_1.file;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static com.ey.task_1.util.Constants.*;

/*
* This class generates random output according to the first task.
* */
public class RandomOutput {
    private final StringBuilder output;
    private final Random random;

    public RandomOutput() {
        this.output = new StringBuilder();
        this.random = new Random();
    }

    // Get string with random data.
    public String getOutput() {
        return this.output.toString();
    }

    // Clear string.
    public void clear() {
        this.output.delete(0, this.output.length());
    }

    // Append symbols to the output.
    private void appendLetters(List<Integer> codes) {
        for (int i = 0; i < SEQUENCE_LENGTH; ++i) {
            // Get random element from the list of symbol codes and append the corresponding symbol.
            this.output.appendCodePoint(codes.get(this.random.nextInt(codes.size() - 1)));
        }
    }

    // Append '//' to the output.
    public RandomOutput appendDelimiter() {
        this.output.append(DELIMITER);
        return this;
    }

    // Set random date within 5 last years.
    public RandomOutput setDate() {
        Calendar past = Calendar.getInstance();
        // Set date five years ago.
        past.set(Calendar.YEAR, past.get(Calendar.YEAR) - PAST_YEARS);

        // Append random date to output.
        this.output.append(
                // Format random date to 'dd.MM.yyyy' pattern.
                new SimpleDateFormat(DATE_PATTERN).format(
                        // Create date instance from number of milliseconds.
                        new Date(
                                /*
                                *  Generate random stream of long-type numbers
                                *  from five years ago date in milliseconds to
                                *  current date in milliseconds.
                                * */
                                this.random.longs(
                                        past.getTime().getTime(),
                                        new Date().getTime()
                                ).findAny().getAsLong()
                        )
                )
        );
        return this;
    }

    // Set random Latin symbols sequence.
    public RandomOutput setLatinSequence() {
        // Create stream of random integers that correspond to UNICODE codes of latin symbols.
        IntStream stream = IntStream.concat(
                /* Create two streams (for upper-case and lower-case latin codes), limit both
                *  with the size of latin alphabet and concatenate them. */
                this.random.ints(LATIN_UPPER_ORIGIN, LATIN_UPPER_BOUND + 1).limit(LATIN_SIZE),
                this.random.ints(LATIN_LOWER_ORIGIN, LATIN_LOWER_BOUND + 1).limit(LATIN_SIZE)
        );
        // Get List of integers from the stream.
        List<Integer> latinCodes = stream.boxed().collect(Collectors.toList());
        // Append latin letters to the output.
        appendLetters(latinCodes);
        return this;
    }

    // Set random Cyrillic symbols sequence.
    public RandomOutput setCyrillicSequence() {
        /*
        *  Get list of integers from the random stream of integers corresponding to
        *  UNICODE codes of Cyrillic symbols.
        * */
        List<Integer> cyrillicCodes = this.random.ints(CYRILLIC_ORIGIN, CYRILLIC_BOUND + 1)
                                                 .boxed()
                                                 .limit(CYRILLIC_SIZE)
                                                 .collect(Collectors.toList());

        // Append cyrillic letters to the output.
        appendLetters(cyrillicCodes);
        return this;
    }

    // Set random integer.
    public RandomOutput setInteger() {
        // Append random integer from 1 to 100 000 000 to the output.
        this.output.append(
                this.random.nextInt(INTEGER_BOUND - INTEGER_ORIGIN) + INTEGER_ORIGIN
        );
        return this;
    }

    // Set random number with floating point.
    public RandomOutput setFloating() {
        // Generate random double-type number from 1 to 20 and cast it to String.
        String f = Double.toString(
                ThreadLocalRandom.current().nextDouble(DOUBLE_ORIGIN, DOUBLE_BOUND)
        );
        /*
        * Get rid of the unnecessary tale (that's why we casted the number to String).
        * Find the '.' and cut the tale after 8 symbols.
        * */
        this.output.append(f, 0, f.indexOf(FLOATING_POINT) + 1 + PRECISION);
        return this;
    }

    // Set the output by calling one method.
    public void set() {
        this
                .setDate()
            .appendDelimiter()
                .setLatinSequence()
            .appendDelimiter()
                .setCyrillicSequence()
            .appendDelimiter()
                .setInteger()
            .appendDelimiter()
                .setFloating()
            .appendDelimiter();
    }
}
