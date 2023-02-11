package com.simian.api.useCase.dnaSequenceAnalyzes;

import com.simian.api.Application;
import com.simian.api.entities.AnalysisData;
import com.simian.api.useCase.dnaSequenceAnalyzes.ports.DnaSequenceAnalysisRepository;
import com.simian.api.useCase.dnaSequenceAnalyzes.repository.InMemoryDnaSequenceAnalysisRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class DnaSequenceAnalysisTest {

    @Autowired
    private DnaSequenceAnalysis useCase;

    @MockBean
    private DnaSequenceAnalysisRepository repository;

    @Test
    public void shouldReturnTrueWhenDnaSequenceIsSimian(){
        String[] dna = {"ATGCGA", "CAGTGC", "TTATGT", "AGAAGG", "CCCCTA", "TCACTG"};

        Boolean isSimian = useCase.perform(dna);

        assertTrue(isSimian);
    }

}
