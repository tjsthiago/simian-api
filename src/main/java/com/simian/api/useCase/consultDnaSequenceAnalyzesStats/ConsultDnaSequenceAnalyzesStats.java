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
        List<AnalysisData> analyzes = repository.findAll();

        long totalOfSimians = analyzes.stream().filter(AnalysisData::isSimianDna).count();
        long totalOfHumans = analyzes.size() - totalOfSimians;

        double ratio = 0;

        if (totalOfHumans == 0) {
            ratio = 0;
        } else {
            ratio = (double) totalOfSimians / totalOfHumans;
        }

        return new DnaSequenceAnalyzesStats(
                totalOfSimians,
                totalOfHumans,
                ratio
        );

    }
}
