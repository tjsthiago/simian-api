package com.simian.api.entities;

import com.simian.api.entities.errors.InvalidDnaSequenceError;
import org.springframework.stereotype.Service;
import org.yaml.snakeyaml.util.ArrayUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class Dna {

    private static final String ALLOWED_NITROGEN_BASE_SYMBOL_REGEX = "\\w[ATCG]";

    private static final int IS_SIMAN_SEQUENCE_TARGET = 4;

    private static final int DNA_MATRIX_SIZE_LIMIT = 6;

    private final Pattern NITROGEN_BASE_PATTERN = Pattern.compile(
        ALLOWED_NITROGEN_BASE_SYMBOL_REGEX,
        Pattern.CASE_INSENSITIVE
    );

    public Boolean isSimian(String[] dna) {
        validateDnaSequence(dna);

        char[][] dnaMatrix = initDnaMatrix(dna);

        return isSimian(dnaMatrix);
    }

    private Boolean isSimian(char[][] dnaMatrix) {
        if(validateHorizontally(dnaMatrix)) {
            return true;
        }

        if(validateVertically(dnaMatrix)) {
            return true;
        }

        if(validateDiagonalFromRight(dnaMatrix)) {
            return true;
        }

        return false;
    }

    public boolean validateDiagonalFromRight(char[][] dnaMatrix) {
        int dnaMatrixIndexesQuantity = dnaMatrix.length -1;

        for (int matrixRowIndex = 0; matrixRowIndex < dnaMatrix.length; matrixRowIndex++) {
            char[] topDiagonalDnaNitrogenBaseLineSequence = new char[matrixRowIndex + 1];
            char[] bottomDiagonalDnaNitrogenBaseLineSequence = new char[matrixRowIndex + 1];

            for (int matrixColumnIndex = 0; matrixColumnIndex <= matrixRowIndex; matrixColumnIndex++) {
                int topRowIndex = matrixRowIndex - matrixColumnIndex;
                int topColumnIndex = matrixColumnIndex;
                char topDiagonalItem = dnaMatrix[topRowIndex][topColumnIndex];

                int bottomRowIndex = dnaMatrixIndexesQuantity - topRowIndex;
                int bottomColumnIndex = dnaMatrixIndexesQuantity - topColumnIndex;
                char bottomDiagonalItem = dnaMatrix[bottomRowIndex][bottomColumnIndex];

                topDiagonalDnaNitrogenBaseLineSequence[matrixColumnIndex] = topDiagonalItem;
                bottomDiagonalDnaNitrogenBaseLineSequence[matrixColumnIndex] = bottomDiagonalItem;
            }

            if(topDiagonalDnaNitrogenBaseLineSequence.length >= 4 && bottomDiagonalDnaNitrogenBaseLineSequence.length >= 4) {
                if(hasSimianSequenceOccurrences(topDiagonalDnaNitrogenBaseLineSequence)){
                    return true;
                }

                if(hasSimianSequenceOccurrences(bottomDiagonalDnaNitrogenBaseLineSequence)){
                    return true;
                }
            }

        }

        return false;
    }

    private boolean validateVertically(char[][] dnaMatrix) {
        int dnaMatrixNumberOfColumns = dnaMatrix.length;

        for (int dnaMatrixSequenceColumnIndex = 0; dnaMatrixSequenceColumnIndex < dnaMatrixNumberOfColumns; dnaMatrixSequenceColumnIndex++) {
            char[] verticalDnaNitrogenBaseLineSequence = getVerticalDnaNitrogenBaseLineSequenceByDnaSequenceColumnIndex(
                dnaMatrix,
                dnaMatrixSequenceColumnIndex
            );

            if(hasSimianSequenceOccurrences(verticalDnaNitrogenBaseLineSequence)){
                return true;
            }
        }

        return false;
    }

    private char[] getVerticalDnaNitrogenBaseLineSequenceByDnaSequenceColumnIndex(char[][] dnaMatrix, int dnaMatrixSequenceColumnIndex) {
        int dnaMatrixNumberOfRows = dnaMatrix[0].length;

        char[] verticalDnaNitrogenBaseLineSequence = new char[dnaMatrixNumberOfRows];

        for (int dnaMatrixSequenceLineIndex = 0; dnaMatrixSequenceLineIndex < dnaMatrixNumberOfRows; dnaMatrixSequenceLineIndex++) {
            verticalDnaNitrogenBaseLineSequence[dnaMatrixSequenceLineIndex] = dnaMatrix[dnaMatrixSequenceLineIndex][dnaMatrixSequenceColumnIndex];
        }

        return verticalDnaNitrogenBaseLineSequence;
    }

    private boolean validateHorizontally(char[][] dnaMatrix) {
        for (char[] dnaNitrogenBaseLineSequence : dnaMatrix) {
            if(hasSimianSequenceOccurrences(dnaNitrogenBaseLineSequence)){
               return true;
            }
        }

        return false;
    }

    private boolean hasSimianSequenceOccurrences(char[] dnaNitrogenBaseSequence) {
        int sameSymbolOccurrences = 0;
        char lastSymbol = dnaNitrogenBaseSequence[0];

        for (int i = 0; i < dnaNitrogenBaseSequence.length; i++) {
            if(i > 0) {
                lastSymbol = dnaNitrogenBaseSequence[i -1];
            }

            char currentSymbol = dnaNitrogenBaseSequence[i];

            if (currentSymbol == lastSymbol) {
                sameSymbolOccurrences++;

                if(sameSymbolOccurrences >= IS_SIMAN_SEQUENCE_TARGET) {
                    return true;
                }
            } else {
                sameSymbolOccurrences = 1;
            }

        }

        return false;
    }

    private char[][] initDnaMatrix(String[] dna) {
        char[][] dnaMatrix = new char[DNA_MATRIX_SIZE_LIMIT][DNA_MATRIX_SIZE_LIMIT];

        for (int i = 0; i < dna.length; i++) {
            char[] dnaNitrogenBaseList = dna[i].toCharArray();

            for (int j = 0; j < dnaNitrogenBaseList.length; j++) {
                dnaMatrix[i][j] = dnaNitrogenBaseList[j];
            }
        }

        return dnaMatrix;
    }

    private void validateDnaSequence(String[] dna) {
        if(dna == null){
            throw new InvalidDnaSequenceError("DNA sequence must not be empty");
        }

        if(dna.length != DNA_MATRIX_SIZE_LIMIT){
            throw new InvalidDnaSequenceError("DNA sequence must have 6 parts");
        }

        for (String dnaSequencePart : dna) {
            if(dnaSequencePart.length() != DNA_MATRIX_SIZE_LIMIT){
                throw new InvalidDnaSequenceError("DNA sequence part must have 6 digits");
            }

            if(!isDnaSequencePartValid(dnaSequencePart)) {
                throw new InvalidDnaSequenceError("DNA ["+ dnaSequencePart +"] sequence part has invalid symbols");
            }

        }
    }

    private boolean isDnaSequencePartValid(String dnaSequencePart) {
        Matcher matcher = NITROGEN_BASE_PATTERN.matcher(dnaSequencePart);
        return matcher.find();
    }
}
