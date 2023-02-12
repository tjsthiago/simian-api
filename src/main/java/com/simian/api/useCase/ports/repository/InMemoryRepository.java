package com.simian.api.useCase.ports.repository;

import com.simian.api.entities.AnalysisData;

import java.util.List;

public class InMemoryRepository implements IRepository {

    List<AnalysisData> analyzes;

    public InMemoryRepository(List<AnalysisData> analyzes) {
        this.analyzes = analyzes;
    }

    @Override
    public AnalysisData save(AnalysisData analysis) {
        boolean exists = this.exists(analysis);

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
