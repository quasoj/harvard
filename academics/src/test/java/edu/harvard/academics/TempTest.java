package edu.harvard.academics;

import org.junit.jupiter.api.Test;

import java.sql.Timestamp;

public class TempTest {
    @Test
    void timestampTest() {
        System.out.println(new Timestamp(System.currentTimeMillis()));
    }

    @Test
    void stringTest() {
        System.out.println("".length());
    }
}
