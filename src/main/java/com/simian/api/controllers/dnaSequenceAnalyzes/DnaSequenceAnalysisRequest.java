package com.simian.api.controllers.dnaSequenceAnalyzes;

public class DnaSequenceAnalysisRequest {
    String[] dna;

    public DnaSequenceAnalysisRequest() {

    }

    public String[] getDna() {
        return dna;
    }

    public void setDna(String[] dna) {
        this.dna = dna;
    }
}
