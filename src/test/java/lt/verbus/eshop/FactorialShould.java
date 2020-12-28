package lt.verbus.eshop;

import org.junit.jupiter.api.Test;

import static lt.verbus.eshop.Factorial.factorial;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * TDD (Test Driven Development) != Tests
 * Rašau testą, kuris fail'ina.
 * Rašau implementaciją, kuri padaro testą veikiančiu, pačiu paprasčiausiu būdu.
 * Refactorinu, kad gaučiau gražų testą ir / arba implementaciją
 * 1! = 1
 * 2! = 2 * 1 = 2
 * 3! = 3 * 2 * 1 = 6
 * 5! = 5 * 4 * 3 * 2 * 1 = 120
 * 0! = 1
 * -! = neegzustuojanti aibė
 */

public class FactorialShould {

    @Test
    void return_1_when_1_passed()  {
        // given
        int input = 1;

        // when
        int result = factorial(input);

        // then
        assertEquals(1, result);
    }

    @Test
    void return_2_when_2_passed() {
        // when
        int result = factorial(2);

        // then
        assertEquals(2, result);
    }

    @Test
    void return_6_when_3_passed() {
        // when
        int result = factorial(3);

        // then
        assertEquals(6, result);
    }

    @Test
    void return_120_when_5_passed() {
        // when
        int result = factorial(5);

        // then
        assertEquals(120, result);
    }

    @Test
    void return_big_number_when_10_passed() {
        // when
        int result = factorial(10);

        // then
        assertEquals(10 * 9 * 8 * 7 * 6 * 5 * 4 * 3 * 2, result);
    }


    @Test
    void return_1_when_0_passed() {
        // when
        int result = factorial(0);

        // then
        assertEquals(1, result);
    }

    @Test
    void throws_exception_when_negative_passed() {
        // when, then
        assertThrows(IllegalArgumentException.class, () -> factorial(-5));
    }



}
