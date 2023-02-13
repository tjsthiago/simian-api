package com.simian.api.useCase.consultDnaSequenceAnalyzesStats;

import com.simian.api.controllers.consultDnaSequenceAnalyzesStats.DnaSequenceAnalyzesStats;
import com.simian.api.external.repository.AnalysisDataRepository;
import org.springframework.stereotype.Service;

@Service
public class ConsultDnaSequenceAnalyzesStats {

    private final AnalysisDataRepository repository;

    public ConsultDnaSequenceAnalyzesStats(AnalysisDataRepository repository) {
        this.repository = repository;
    }

    public DnaSequenceAnalyzesStats getDnaSequenceAnalyzesStats() {
        long totalOfAnalyzes = this.repository.count();
        long totalOfSimians = this.repository.countByIsSimian(true);
        long totalOfHumans = totalOfAnalyzes - totalOfSimians;
        double ratio = totalOfHumans == 0 ? 0 : (double) totalOfSimians / totalOfHumans;

        return new DnaSequenceAnalyzesStats(
                totalOfSimians,
                totalOfHumans,
                ratio
        );

    }
}
