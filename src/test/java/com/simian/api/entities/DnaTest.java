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
        String[] dnaSequence = {"AAAACC", "CTGACT", "TGCACT", "GACTGA", "ACTGAC", "TGACTG"};
        assertTrue(dna.isSimian(dnaSequence));
    }

    @Test
    public void shouldReturnTrueWhenASimianSequenceIsPresentInAHorizontalWayInTheBeginningOfTheSequence() {
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
    public void shouldReturnTrueWhenASimianSequenceIsPresentInAHorizontalWayInTheEndOfTheSequence() {
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
    public void shouldReturnTrueWhenASimianSequenceIsPresentInAHorizontalWayStartingInSecondIndexTheOfTheSequence() {
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
    public void shouldReturnTrueWhenASimianSequenceIsPresentInAVerticalWayInTheBeginningOfTheSequence() {
        String[] simianSequenceOnTheFirstDnaMatrixColumn = {"ATATAT", "AGCGCG", "AATATA", "ACGCGC", "CTATAT", "CGCGCG"};
        String[] simianSequenceOnTheSecondDnaMatrixColumn = {"ATATAT", "CTCGCG", "GTGATA", "GTGCGC", "ACATAT", "CGCGCG"};
        String[] simianSequenceOnTheThirdDnaMatrixColumn = {"ATATAT", "CGAGCG", "TTATTA", "GCACGC", "ATCTAT", "CGCGCG"};
        String[] simianSequenceOnTheFourthDnaMatrixColumn = {"ATATAT", "CGCTCG", "TACTCA", "GCGTGC", "ATACAT", "CGCGCG"};
        String[] simianSequenceOnTheFifthDnaMatrixColumn = {"ATATAT", "CGCGAG", "TATCAC", "GCGCAC", "ATATCT", "CGCGCG"};
        String[] simianSequenceOnTheSixthDnaMatrixColumn = {"ATATAT", "CGCGCT", "TATAAT", "GCGCGT", "ATATAC", "CGCGCG"};

        assertTrue(dna.isSimian(simianSequenceOnTheFirstDnaMatrixColumn));
        assertTrue(dna.isSimian(simianSequenceOnTheSecondDnaMatrixColumn));
        assertTrue(dna.isSimian(simianSequenceOnTheThirdDnaMatrixColumn));
        assertTrue(dna.isSimian(simianSequenceOnTheFourthDnaMatrixColumn));
        assertTrue(dna.isSimian(simianSequenceOnTheFifthDnaMatrixColumn));
        assertTrue(dna.isSimian(simianSequenceOnTheSixthDnaMatrixColumn));
    }

    @Test
    public void shouldReturnTrueWhenASimianSequenceIsPresentInAVerticalWayInTheEndOfTheSequence() {
        String[] simianSequenceOnTheFirstDnaMatrixColumn = {"ATATAT", "CGCGCG", "TATATA", "TCGCGC", "TTATAT", "TGCGCG"};
        String[] simianSequenceOnTheSecondDnaMatrixColumn = {"ATATAT", "CACGCG", "TGTATA", "CGCCGC", "AGATAT", "CGCGCG"};
        String[] simianSequenceOnTheThirdDnaMatrixColumn = {"ATATAT", "CGCGCG", "TATATA", "GCTCGC", "AATAAT", "CGTGCG"};
        String[] simianSequenceOnTheFourthDnaMatrixColumn = {"ATATAT", "CGCGCG", "TATATA", "GCGAGC", "ATTATT", "CGCACG"};
        String[] simianSequenceOnTheFifthDnaMatrixColumn = {"ATATAT", "CGCGCG", "TATATA", "GCGCTC", "ATAATA", "CGCGTG"};
        String[] simianSequenceOnTheSixthDnaMatrixColumn = {"ATATAT", "CGCGCG", "TATATA", "GCGCGA", "ATATAA", "CGCGCA"};

        assertTrue(dna.isSimian(simianSequenceOnTheFirstDnaMatrixColumn));
        assertTrue(dna.isSimian(simianSequenceOnTheSecondDnaMatrixColumn));
        assertTrue(dna.isSimian(simianSequenceOnTheThirdDnaMatrixColumn));
        assertTrue(dna.isSimian(simianSequenceOnTheFourthDnaMatrixColumn));
        assertTrue(dna.isSimian(simianSequenceOnTheFifthDnaMatrixColumn));
        assertTrue(dna.isSimian(simianSequenceOnTheSixthDnaMatrixColumn));
    }

    @Test
    public void shouldReturnTrueWhenASimianSequenceIsPresentInAVerticalWayStartingInSecondIndexTheOfTheSequence() {
        String[] simianSequenceOnTheFirstDnaMatrixColumn = {"ATATAT", "CGCGCG", "CATATA", "CTGCGC", "CTATAT", "TGCGCG"};
        String[] simianSequenceOnTheSecondDnaMatrixColumn = {"ATATAT", "CGCGCG", "TGTATA", "CGCCGC", "AGATAT", "CTCGCG"};
        String[] simianSequenceOnTheThirdDnaMatrixColumn = {"ATATAT", "CGCGCG", "TACATA", "GTCTGC", "ATCTAT", "CGAGCG"};
        String[] simianSequenceOnTheFourthDnaMatrixColumn = {"ATATAT", "CGCGCG", "TATGTA", "GCTGTC", "ATAGAT", "CGCACG"};
        String[] simianSequenceOnTheFifthDnaMatrixColumn = {"ATATAT", "CGCGCG", "TATACA", "GCGACA", "ATATCT", "CGCGAG"};
        String[] simianSequenceOnTheSixthDnaMatrixColumn = {"ATATAT", "CGCGCG", "TATATG", "GCGCTG", "ATATAG", "CGCGCT"};

        assertTrue(dna.isSimian(simianSequenceOnTheFirstDnaMatrixColumn));
        assertTrue(dna.isSimian(simianSequenceOnTheSecondDnaMatrixColumn));
        assertTrue(dna.isSimian(simianSequenceOnTheThirdDnaMatrixColumn));
        assertTrue(dna.isSimian(simianSequenceOnTheFourthDnaMatrixColumn));
        assertTrue(dna.isSimian(simianSequenceOnTheFifthDnaMatrixColumn));
        assertTrue(dna.isSimian(simianSequenceOnTheSixthDnaMatrixColumn));
    }

    @Test
    public void shouldReturnTrueWhenASimianSequenceIsPresentInTheRightDiagonalsInTheBeginningOfTheSequence() {
        String[] simianSequenceOnTheFirstPossibleTopRightDnaMatrixDiagonal = {"GACAGA", "GCACTG", "GACTGA", "ACTGAC", "TGACTG", "ACTGAC"};
        String[] simianSequenceOnTheSecondPossibleTopRightDnaMatrixDiagonal = {"GACGCA", "GCACTG", "GACTGA", "ACTGAC", "TGACTG", "ACTGAC"};
        String[] simianSequenceOnTheThirdPossibleTopRightDnaMatrixDiagonal = {"GACAGT", "GCACTG", "GACTGA", "ACTGAC", "TGACTG", "ACTGAC"};

        String[] simianSequenceOnTheFirstPossibleBottomRightDnaMatrixDiagonal = {"GACGGA", "GCACTG", "GACTGA", "ACTGAC", "TGAATG", "ACAGAC"};
        String[] simianSequenceOnTheSecondPossibleBottomRightDnaMatrixDiagonal = {"GACGGA", "GCACTG", "GACTGA", "ACTGAC", "TGGGTG", "ACAGAC"};
        String[] simianSequenceOnTheThirdPossibleBottomRightDnaMatrixDiagonal = {"GACGGT", "GCACTG", "GACTGA", "ACTGAC", "TGACTG", "ACAGAC"};

        assertTrue(dna.isSimian(simianSequenceOnTheFirstPossibleTopRightDnaMatrixDiagonal));
        assertTrue(dna.isSimian(simianSequenceOnTheSecondPossibleTopRightDnaMatrixDiagonal));
        assertTrue(dna.isSimian(simianSequenceOnTheThirdPossibleTopRightDnaMatrixDiagonal));
        assertTrue(dna.isSimian(simianSequenceOnTheFirstPossibleBottomRightDnaMatrixDiagonal));
        assertTrue(dna.isSimian(simianSequenceOnTheSecondPossibleBottomRightDnaMatrixDiagonal));
        assertTrue(dna.isSimian(simianSequenceOnTheThirdPossibleBottomRightDnaMatrixDiagonal));
    }

    @Test
    public void shouldReturnFalseWhenASimianSequenceIsPresentInAVerticalWay() {
        String[] simianSequenceOnTheFirstDnaMatrixColumn = {"CCTGAC", "AGACTG", "ACTGAC", "AGACTG", "TCTGAC", "TCACTG"};

        assertFalse(dna.isSimian(simianSequenceOnTheFirstDnaMatrixColumn));
    }

    @Test
    public void shouldReturnFalseWhenASimianSequenceIsNotPresentInDnaSequencePart() {
        String[] dnaSequence = {"ACTGAC", "CCGACT", "TTTACT", "GACTAA", "ACTCCC", "CTGGGT"};
        assertFalse(dna.isSimian(dnaSequence));
    }

}
