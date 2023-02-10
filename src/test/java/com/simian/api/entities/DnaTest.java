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
    public void shouldReturnTrueWhenASimianSequenceIsPresentInAVerticalWayInTheBeginningOfTheSequence() {
        String[] simianSequenceOnTheFirstSequencePart = {"AAAACC", "CTGACT", "TGCACT", "GACTGA", "ACTGAC", "TGACTG"};
        String[] simianSequenceOnTheSecondSequencePart = {"CTGACT", "AAAACC", "TGCACT", "GACTGA", "ACTGAC", "TGACTG"};
        String[] simianSequenceOnTheThirdSequencePart = {"CTGACT", "CTGACT", "AAAACC", "GACTGA", "ACTGAC", "TGACTG"};
        String[] simianSequenceOnTheFourthSequencePart = {"CTGACT", "CTGACT", "TGCACT", "AAAACC", "ACTGAC", "TGACTG"};
        String[] simianSequenceOnTheFifthSequencePart = {"CTGACT", "CTGACT", "TGCACT", "GACTGA", "AAAACC", "TGACTG"};
        String[] simianSequenceOnTheSixthSequencePart = {"CTGACT", "CTGACT", "TGCACT", "GACTGA", "ACTGAC", "AAAACC"};

        assertTrue(dna.isSimian(simianSequenceOnTheFirstSequencePart));
        assertTrue(dna.isSimian(simianSequenceOnTheSecondSequencePart));
        assertTrue(dna.isSimian(simianSequenceOnTheThirdSequencePart));
        assertTrue(dna.isSimian(simianSequenceOnTheFourthSequencePart));
        assertTrue(dna.isSimian(simianSequenceOnTheFifthSequencePart));
        assertTrue(dna.isSimian(simianSequenceOnTheSixthSequencePart));
    }

    @Test
    public void shouldReturnTrueWhenASimianSequenceIsPresentInAVerticalWayInTheEndOfTheSequence() {
        String[] simianSequenceOnTheFirstSequencePart = {"CCAAAA", "CTGACT", "TGCACT", "GACTGA", "ACTGAC", "TGACTG"};
        String[] simianSequenceOnTheSecondSequencePart = {"CTGACT", "CCAAAA", "TGCACT", "GACTGA", "ACTGAC", "TGACTG"};
        String[] simianSequenceOnTheThirdSequencePart = {"CTGACT", "CTGACT", "CCAAAA", "GACTGA", "ACTGAC", "TGACTG"};
        String[] simianSequenceOnTheFourthSequencePart = {"CTGACT", "CTGACT", "TGCACT", "CCAAAA", "ACTGAC", "TGACTG"};
        String[] simianSequenceOnTheFifthSequencePart = {"CTGACT", "CTGACT", "TGCACT", "GACTGA", "CCAAAA", "TGACTG"};
        String[] simianSequenceOnTheSixthSequencePart = {"CTGACT", "CTGACT", "TGCACT", "GACTGA", "ACTGAC", "CCAAAA"};

        assertTrue(dna.isSimian(simianSequenceOnTheFirstSequencePart));
        assertTrue(dna.isSimian(simianSequenceOnTheSecondSequencePart));
        assertTrue(dna.isSimian(simianSequenceOnTheThirdSequencePart));
        assertTrue(dna.isSimian(simianSequenceOnTheFourthSequencePart));
        assertTrue(dna.isSimian(simianSequenceOnTheFifthSequencePart));
        assertTrue(dna.isSimian(simianSequenceOnTheSixthSequencePart));
    }

    @Test
    public void shouldReturnTrueWhenASimianSequenceIsPresentInAVerticalWayStartingInSecondIndexTheOfTheSequence() {
        String[] simianSequenceOnTheFirstSequencePart = {"CAAAAC", "CTGACT", "TGCACT", "GACTGA", "ACTGAC", "TGACTG"};
        String[] simianSequenceOnTheSecondSequencePart = {"CTGACT", "CAAAAC", "TGCACT", "GACTGA", "ACTGAC", "TGACTG"};
        String[] simianSequenceOnTheThirdSequencePart = {"CTGACT", "CTGACT", "CAAAAC", "GACTGA", "ACTGAC", "TGACTG"};
        String[] simianSequenceOnTheFourthSequencePart = {"CTGACT", "CTGACT", "TGCACT", "CAAAAC", "ACTGAC", "TGACTG"};
        String[] simianSequenceOnTheFifthSequencePart = {"CTGACT", "CTGACT", "TGCACT", "GACTGA", "CAAAAC", "TGACTG"};
        String[] simianSequenceOnTheSixthSequencePart = {"CTGACT", "CTGACT", "TGCACT", "GACTGA", "ACTGAC", "CAAAAC"};

        assertTrue(dna.isSimian(simianSequenceOnTheFirstSequencePart));
        assertTrue(dna.isSimian(simianSequenceOnTheSecondSequencePart));
        assertTrue(dna.isSimian(simianSequenceOnTheThirdSequencePart));
        assertTrue(dna.isSimian(simianSequenceOnTheFourthSequencePart));
        assertTrue(dna.isSimian(simianSequenceOnTheFifthSequencePart));
        assertTrue(dna.isSimian(simianSequenceOnTheSixthSequencePart));
    }

    @Test
    public void shouldReturnFalseWhenASimianSequenceIsNotPresentInDnaSequencePart() {
        String[] dnaSequence = {"ACTGAC", "CCGACT", "TTTACT", "GACTAA", "ACTCCC", "CTGGGT"};
        assertFalse(dna.isSimian(dnaSequence));
    }

}
