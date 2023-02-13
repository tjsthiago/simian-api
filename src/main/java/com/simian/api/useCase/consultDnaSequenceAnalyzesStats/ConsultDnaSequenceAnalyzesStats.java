package com.simian.api.useCase.consultDnaSequenceAnalyzesStats;

import com.simian.api.controllers.consultDnaSequenceAnalyzesStats.DnaSequenceAnalyzesStats;
import com.simian.api.entities.AnalysisData;
import com.simian.api.useCase.ports.repository.IRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ConsultDnaSequenceAnalyzesStats {

    private final IRepository repository;

    public ConsultDnaSequenceAnalyzesStats(IRepository repository) {
        this.repository = repository;
    }

    public DnaSequenceAnalyzesStats getDnaSequenceAnalyzesStats() {
        int totalOfAnalyzes = this.repository.count();
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
