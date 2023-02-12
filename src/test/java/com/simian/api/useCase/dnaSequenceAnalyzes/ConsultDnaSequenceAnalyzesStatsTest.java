package com.simian.api.useCase.dnaSequenceAnalyzes;

import com.simian.api.Application;
import com.simian.api.controllers.consultDnaSequenceAnalyzesStats.DnaSequenceAnalyzesStats;
import com.simian.api.entities.AnalysisData;
import com.simian.api.useCase.consultDnaSequenceAnalyzesStats.ConsultDnaSequenceAnalyzesStats;
import com.simian.api.useCase.ports.repository.IRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class ConsultDnaSequenceAnalyzesStatsTest {

    @Autowired
    private ConsultDnaSequenceAnalyzesStats useCase;

    @MockBean
    private IRepository repository;

    @Test
    public void shouldNotThrowExceptionWhenAnalyzesIsEmpty(){
        Mockito.when(repository.findAll()).thenReturn(new ArrayList<>());
        assertDoesNotThrow(() -> useCase.getDnaSequenceAnalyzesStats());
    }

    @Test
    public void shouldReturnARatioEqualToOneForTheSameNumberOfMutantsAndHumans() {
        Mockito.when(repository.findAll()).thenReturn(mockStoredAnalyzes(1, 1));

        DnaSequenceAnalyzesStats response = useCase.getDnaSequenceAnalyzesStats();

        assertEquals(1.0, response.getRatio());
    }

    @Test
    public void shouldReturnARatioEqualToZeroDotFourForTheSameNumberOfFortyMutantsAndOneHundredHumans() {
        Mockito.when(repository.findAll()).thenReturn(mockStoredAnalyzes(40, 100));

        DnaSequenceAnalyzesStats response = useCase.getDnaSequenceAnalyzesStats();

        assertEquals(0.4, response.getRatio());
    }

    private List<AnalysisData> mockStoredAnalyzes(int quantityOfSimians, int quantityOfHumans) {
        List<AnalysisData> simians = new ArrayList<>(quantityOfSimians);
        List<AnalysisData> humans = new ArrayList<>(quantityOfHumans);

        for (int i = 0; i < quantityOfSimians; i++) {
            simians.add(new AnalysisData("hash-" + String.valueOf(i), true));
        }

        for (int i = 0; i < quantityOfHumans; i++) {
            humans.add(new AnalysisData("hash-" + String.valueOf(i), false));
        }

        return Stream.concat(simians.stream(), humans.stream()).collect(Collectors.toList());
    }

}
