package com.simian.api.external.repository.in.memory;

import com.simian.api.entities.AnalysisData;
import com.simian.api.external.repository.AnalysisDataRepository;

import java.util.List;

public class InMemoryAnalysisDataRepository {

    List<AnalysisData> analyzes;

    public InMemoryAnalysisDataRepository(List<AnalysisData> analyzes) {
        this.analyzes = analyzes;
    }

    public AnalysisData save(AnalysisData analysis) {
        boolean exists = this.exists(analysis);

        if(!exists) {
            this.analyzes.add(analysis);
        }

        return analysis;
    }

    public List<AnalysisData> findAll() {
        return this.analyzes;
    }

    public Boolean exists(AnalysisData analysis) {
        return this.analyzes
                .stream()
                .anyMatch(d -> d.getHash().equalsIgnoreCase(analysis.getHash()));
    }

    public long count() {
        return this.analyzes.size();
    }

    public Long countByIsSimian(Boolean isSimian) {
        return this.analyzes.stream().filter(AnalysisData::isSimianDna).count();
    }

}
