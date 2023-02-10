package com.simian.api.entities;

import com.simian.api.Application;
import com.simian.api.entities.errors.InvalidDnaSequenceError;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class DnaTest {

    @Autowired
    Dna dna;

    @Test
    public void shouldThrowInvalidDnaSequenceErrorWhenDnaSequenceIsEmpty() {
        Exception exception = assertThrows(InvalidDnaSequenceError.class, () -> {
            dna.isSimian(null);
        });

        assertEquals("DNA sequence must not be empty", exception.getMessage());
    }

    @Test
    public void shouldThrowInvalidDnaSequenceErrorWhenDnaSequenceHasLasThanSixParts() {
        String[] dnaSequence = {"CCCCTA"};

        Exception exception = assertThrows(InvalidDnaSequenceError.class, () -> {
            dna.isSimian(dnaSequence);
        });

        assertEquals("DNA sequence must have 6 parts", exception.getMessage());
    }

    @Test
    public void shouldThrowInvalidDnaSequenceErrorWhenDnaSequenceHasMoreThanSixParts() {
        String[] dnaSequence = {"CCCCTA", "CCCCTA", "CCCCTA", "CCCCTA", "CCCCTA", "CCCCTA", "CCCCTA"};

        Exception exception = assertThrows(InvalidDnaSequenceError.class, () -> {
            dna.isSimian(dnaSequence);
        });

        assertEquals("DNA sequence must have 6 parts", exception.getMessage());
    }

    @Test
    public void shouldThrowInvalidDnaSequenceErrorWhenDnaSequencePartIsInvalid() {
        String[] dnaSequence = {"CCCCTA", "CCCCTA", "CCCCTA", "CCCCTA", "CCCCTA", "CCCCTACCCCTA"};

        Exception exception = assertThrows(InvalidDnaSequenceError.class, () -> {
            dna.isSimian(dnaSequence);
        });

        assertEquals("DNA sequence part must have 6 digits", exception.getMessage());
    }

    @Test
    public void shouldThrowInvalidDnaSequenceErrorWhenDnaSequencePartHasInvalidNitrogenBaseSymbol() {
        String[] dnaSequence = {"CCCCTA", "CCCCTA", "CCCCTA", "CCCCTA", "CCCCTA", "FFFFFF"};

        Exception exception = assertThrows(InvalidDnaSequenceError.class, () -> {
            dna.isSimian(dnaSequence);
        });

        assertEquals("DNA [FFFFFF] sequence part has invalid symbols", exception.getMessage());
    }

    @Test
    public void shouldNotThrowInvalidDnaSequenceErrorWhenDnaSequencePartHasNotInvalidNitrogenBaseSymbol() {
        String[] dnaSequence = {"CCCCTA", "CCCCTA", "CCCCTA", "CCCCTA", "CCCCTA", "CCCCTA"};
        assertDoesNotThrow(() -> dna.isSimian(dnaSequence));
    }

    @Test
    public void shouldReturnTrueWhenASimianSequenceIsPresentInOneDnaSequencePart() {
        String[] dnaSequence = {"CCCCTA", "CTGAGA", "CTGAGC", "TATTGT", "AGAGGG", "TCACTG"};
        assertTrue(dna.isSimian(dnaSequence));
    }

    @Test
    public void shouldReturnTrueWhenASimianSequenceIsPresentInAVerticalWay() {
        String[] dnaSequence = {"CCCCAA", "CTGAGC", "CTGAGC", "CTGAGC", "CTGAGC", "CTGAGC"};
        assertTrue(dna.isSimian(dnaSequence));
    }

    @Test
    public void shouldReturnFalseWhenASimianSequenceIsNotPresentInOneDnaSequencePart() {
        String[] dnaSequence = {"CCCAGC", "CTGAGC", "CTGAGC", "CTGAGC", "CTGAGC", "CTGAGC"};
        assertFalse(dna.isSimian(dnaSequence));
    }

}
