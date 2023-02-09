package com.simian.api.entities;

public class AnalysisData {

    private String hash;

    private Boolean isSimianDna;

    public AnalysisData(String hash, Boolean isSimianDna) {
        this.hash = hash;
        this.isSimianDna = isSimianDna;
    }

    public String getHash() {
        return hash;
    }

    public Boolean getSimianDna() {
        return isSimianDna;
    }

}
