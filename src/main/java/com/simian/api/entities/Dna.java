package com.simian.api.entities;

import com.simian.api.entities.errors.InvalidDnaSequenceError;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class Dna {

    private String allowedNitrogenBaseSymbolRegex = "\\w[ATCG]";

    private Pattern pattern = Pattern.compile(allowedNitrogenBaseSymbolRegex, Pattern.CASE_INSENSITIVE);

    public Boolean isSimian(String[] dna) {
        validateDnaSequence(dna);

        return true;
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
