package com.simian.api.useCase.dnaSequenceAnalyzes;

import com.simian.api.Application;
import com.simian.api.controllers.consultDnaSequenceAnalyzesStats.DnaSequenceAnalyzesStats;
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
        Mockito.when(repository.count()).thenReturn(2);
        Mockito.when(repository.countByIsSimian(true)).thenReturn(1L);

        DnaSequenceAnalyzesStats response = useCase.getDnaSequenceAnalyzesStats();

        assertEquals(1.0, response.getRatio());
    }

    @Test
    public void shouldReturnARatioEqualToZeroDotFourForTheSameNumberOfFortyMutantsAndOneHundredHumans() {
        Mockito.when(repository.count()).thenReturn(140);
        Mockito.when(repository.countByIsSimian(true)).thenReturn(40L);

        DnaSequenceAnalyzesStats response = useCase.getDnaSequenceAnalyzesStats();

        assertEquals(0.4, response.getRatio());
    }

}
