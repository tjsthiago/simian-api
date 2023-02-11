package com.simian.api.controllers;

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
