package com.simian.api.entities;

import com.simian.api.entities.errors.InvalidDnaSequenceError;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Service
public class Dna {

    private static final String ALLOWED_NITROGEN_BASE_SYMBOL_REGEX = "\\w[ATCG]";

    private static final int IS_SIMAN_SEQUENCE_TARGET = 4;

    private final Pattern pattern = Pattern.compile(ALLOWED_NITROGEN_BASE_SYMBOL_REGEX, Pattern.CASE_INSENSITIVE);

    public Boolean isSimian(String[] dna) {
        validateDnaSequence(dna);

        char[][] dnaMatrix = initDnaMatrix(dna);
        printDnaMatrix(dnaMatrix);

        return isSimian(dnaMatrix);
    }

    private Boolean isSimian(char[][] dnaMatrix) {
        if(validateHorizontally(dnaMatrix)) {
            return true;
        }

        return false;
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
            }

        }

        return sameSymbolOccurrences >= IS_SIMAN_SEQUENCE_TARGET;
    }

    private Map<String, Long> mapNitrogenBaseSymbolOccurrences(List<String> dnaMatrixLineAsList) {
        return dnaMatrixLineAsList
                .stream()
                .collect(
                    Collectors.groupingBy(
                        Function.identity(),
                        Collectors.counting()
                    )
                );
    }

    private char[][] initDnaMatrix(String[] dna) {
        char[][] dnaMatrix = new char[6][6];

        for (int i = 0; i < dna.length; i++) {
            char[] dnaNitrogenBaseList = dna[i].toCharArray();

            for (int j = 0; j < dnaNitrogenBaseList.length; j++) {
                dnaMatrix[i][j] = dnaNitrogenBaseList[j];
            }
        }

        return dnaMatrix;
    }

    private void printDnaMatrix(char[][] dnaMatrix){
        for (char[] dnaMatrixLine : dnaMatrix) {
            List<String> dnaMatrixLineAsList = new ArrayList<>();
            for (char dataMatrixItem: dnaMatrixLine) {
                dnaMatrixLineAsList.add(String.valueOf(dataMatrixItem));
            }
            System.out.println(dnaMatrixLineAsList);
        }
    }

    private void validateDnaSequence(String[] dna) {
        if(dna == null){
            throw new InvalidDnaSequenceError("DNA sequence must not be empty");
        }

        if(dna.length != 6){
            throw new InvalidDnaSequenceError("DNA sequence must have 6 parts");
        }

        for (String dnaSequencePart : dna) {
            if(dnaSequencePart.length() != 6){
                throw new InvalidDnaSequenceError("DNA sequence part must have 6 digits");
            }

            if(!isDnaSequencePartValid(dnaSequencePart)) {
                throw new InvalidDnaSequenceError("DNA ["+ dnaSequencePart +"] sequence part has invalid symbols");
            }

        }
    }

    private boolean isDnaSequencePartValid(String dnaSequencePart) {
        Matcher matcher = pattern.matcher(dnaSequencePart);
        return matcher.find();
    }
}
