package com.simian.api.useCase.dnaSequenceAnalyzes.repository;

import com.simian.api.entities.AnalysisData;
import com.simian.api.useCase.dnaSequenceAnalyzes.ports.DnaSequenceAnalysisRepository;

import java.util.List;

public class InMemoryDnaSequenceAnalysisRepository implements DnaSequenceAnalysisRepository {

    List<AnalysisData> analyzes;

    public InMemoryDnaSequenceAnalysisRepository(List<AnalysisData> analyzes) {
        this.analyzes = analyzes;
    }

    @Override
    public AnalysisData save(AnalysisData analysis) {
        Boolean exists = this.exists(analysis);

        if(!exists) {
            this.analyzes.add(analysis);
        }

        return analysis;
    }

    @Override
    public List<AnalysisData> findAll() {
        return this.analyzes;
    }

    @Override
    public Boolean exists(AnalysisData analysis) {
        return this.analyzes
                .stream()
                .anyMatch(d -> d.getHash().equalsIgnoreCase(analysis.getHash()));
    }

}
