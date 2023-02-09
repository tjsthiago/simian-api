package com.simian.api.useCase.dnaSequenceAnalyzes;

import com.simian.api.entities.AnalysisData;
import com.simian.api.useCase.dnaSequenceAnalyzes.ports.DnaSequenceAnalysisRepository;

public class DnaSequenceAnalysis {

    private DnaSequenceAnalysisRepository repository;

    public DnaSequenceAnalysis(DnaSequenceAnalysisRepository repository){
        this.repository = repository;
    }

    public Boolean perform(String[] dna) {
        this.repository.save(new AnalysisData("", Boolean.TRUE));
        return Boolean.TRUE;
    }

}
