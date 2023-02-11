package com.simian.api.useCase.dnaSequenceAnalyzes.ports;

import com.simian.api.entities.AnalysisData;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DnaSequenceAnalysisRepository {

    public AnalysisData save(AnalysisData analysis);

    public List<AnalysisData> findAll();

    public Boolean exists(AnalysisData analysis);
}
