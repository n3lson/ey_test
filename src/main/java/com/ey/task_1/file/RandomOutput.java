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

public class RandomOutput {
    private final StringBuilder output;
    private final Random random;

    public RandomOutput() {
        this.output = new StringBuilder();
        this.random = new Random();
    }

    public String getOutput() {
        return this.output.toString();
    }

    public void clear() {
        this.output.delete(0, this.output.length());
    }

    private void appendLetters(List<Integer> codes) {
        for (int i = 0; i < SEQUENCE_LENGTH; ++i) {
            this.output.appendCodePoint(codes.get(this.random.nextInt(codes.size() - 1)));
        }
    }

    public RandomOutput appendDelimiter() {
        this.output.append(DELIMITER);
        return this;
    }

    public RandomOutput setDate() {
        Calendar past = Calendar.getInstance();
        past.set(Calendar.YEAR, past.get(Calendar.YEAR) - PAST_YEARS);
        this.output.append(
                new SimpleDateFormat(DATE_PATTERN).format(
                        new Date(
                                this.random.longs(
                                        past.getTime().getTime(),
                                        new Date().getTime()
                                ).findAny().getAsLong()
                        )
                )
        );
        return this;
    }

    public RandomOutput setLatinSequence() {
        IntStream stream = IntStream.concat(
                this.random.ints(LATIN_UPPER_ORIGIN, LATIN_UPPER_BOUND + 1).limit(LATIN_SIZE),
                this.random.ints(LATIN_LOWER_ORIGIN, LATIN_LOWER_BOUND + 1).limit(LATIN_SIZE)
        );
        List<Integer> latinCodes = stream.boxed().collect(Collectors.toList());
        appendLetters(latinCodes);
        return this;
    }

    public RandomOutput setCyrillicSequence() {
        List<Integer> cyrillicCodes = this.random.ints(CYRILLIC_ORIGIN, CYRILLIC_BOUND + 1)
                                                 .boxed()
                                                 .limit(CYRILLIC_SIZE)
                                                 .collect(Collectors.toList());
        appendLetters(cyrillicCodes);
        return this;
    }

    public RandomOutput setInteger() {
        this.output.append(
                this.random.nextInt(INTEGER_BOUND - INTEGER_ORIGIN) + INTEGER_ORIGIN
        );
        return this;
    }

    public RandomOutput setFloating() {
        String f = Double.toString(
                ThreadLocalRandom.current().nextDouble(DOUBLE_ORIGIN, DOUBLE_BOUND)
        );
        this.output.append(f, 0, f.indexOf(FLOATING_POINT) + 1 + PRECISION);
        return this;
    }

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
