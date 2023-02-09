package com.simian.api.useCase.dnaSequenceAnalyzes.ports;

import com.simian.api.entities.AnalysisData;

import java.util.List;
import java.util.Optional;

public interface DnaSequenceAnalysisRepository {

    public AnalysisData save(AnalysisData analysis);

    public List<AnalysisData> findAll();

    public Boolean exists(AnalysisData analysis);
}
