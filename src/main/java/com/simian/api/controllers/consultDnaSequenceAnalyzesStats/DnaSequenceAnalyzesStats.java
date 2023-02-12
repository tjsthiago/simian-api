package com.simian.api.controllers.consultDnaSequenceAnalyzesStats;

public class DnaSequenceAnalyzesStats {

    private Long count_mutant_dna;

    private Long count_human_dna;

    private Double ratio;

    public DnaSequenceAnalyzesStats(Long count_mutant_dna, Long count_human_dna, Double ratio) {
        this.count_mutant_dna = count_mutant_dna;
        this.count_human_dna = count_human_dna;
        this.ratio = ratio;
    }

    public Long getCount_mutant_dna() {
        return count_mutant_dna;
    }

    public Long getCount_human_dna() {
        return count_human_dna;
    }

    public Double getRatio() {
        return ratio;
    }

}
