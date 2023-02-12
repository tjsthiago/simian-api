package com.simian.api.useCase.dnaSequenceAnalyzes;

import com.simian.api.entities.AnalysisData;
import com.simian.api.entities.Dna;
import com.simian.api.useCase.ports.repository.IRepository;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.stream.Collectors;

@Service
public class DnaSequenceAnalysis {
    private final IRepository repository;

    private final Dna dnaEntity;

    public DnaSequenceAnalysis(IRepository repository, Dna dnaEntity){
        this.repository = repository;
        this.dnaEntity = dnaEntity;
    }

    public Boolean perform(String[] dna) {
        Boolean isSimian = dnaEntity.isSimian(dna);
        String analysisDataHash = Arrays.stream(dna).collect(Collectors.joining());

        this.repository.save(new AnalysisData(analysisDataHash, isSimian));

        return isSimian;
    }

}
