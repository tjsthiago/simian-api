package com.simian.api.useCase.dnaSequenceAnalyzes;

import com.simian.api.entities.AnalysisData;
import com.simian.api.entities.Dna;
import com.simian.api.external.repository.AnalysisDataRepository;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.stream.Collectors;

@Service
public class DnaSequenceAnalysis {
    private final AnalysisDataRepository repository;

    private final Dna dnaEntity;

    public DnaSequenceAnalysis(AnalysisDataRepository repository, Dna dnaEntity){
        this.repository = repository;
        this.dnaEntity = dnaEntity;
    }

    public Boolean perform(String[] dna) {
        Boolean isSimian = dnaEntity.isSimian(dna);
        String analysisDataHash = Arrays.stream(dna).collect(Collectors.joining());

        if(notExists(analysisDataHash)){
            this.repository.save(new AnalysisData(analysisDataHash, isSimian));
        }

        return isSimian;
    }

    private boolean notExists(String analysisDataHash) {
        return this.repository.countByHash(analysisDataHash) == 0;
    }

}
