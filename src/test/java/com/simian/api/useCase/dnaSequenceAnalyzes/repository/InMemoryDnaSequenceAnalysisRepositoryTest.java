package com.simian.api.useCase.dnaSequenceAnalyzes.repository;

import com.simian.api.Application;
import com.simian.api.entities.AnalysisData;
import com.simian.api.useCase.ports.repository.IRepository;
import com.simian.api.useCase.ports.repository.InMemoryRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class InMemoryDnaSequenceAnalysisRepositoryTest {

    @Test
    public void shouldReturnFalseIfAnalysisIsNotFound() {
        List<AnalysisData> analyzes = new ArrayList<>();
        IRepository repository = new InMemoryRepository(analyzes);

        AnalysisData analysis = new AnalysisData("analysis-hash", Boolean.TRUE);

        assertFalse(repository.exists(analysis));
    }

    @Test
    public void shouldReturnTrueIfAnalysisIsFound() {
        List<AnalysisData> analyzes = new ArrayList<>();
        IRepository repository = new InMemoryRepository(analyzes);

        AnalysisData analysis = new AnalysisData("analysis-hash", Boolean.TRUE);
        repository.save(analysis);

        assertTrue(repository.exists(analysis));
    }

    @Test
    public void shouldReturnAllAnalyzes() {
        List<AnalysisData> analyzes = new ArrayList<>();
        IRepository repository = new InMemoryRepository(analyzes);

        repository.save(new AnalysisData("analysis-01-hash", Boolean.TRUE));
        repository.save(new AnalysisData("analysis-02-hash", Boolean.TRUE));

        assertEquals(2, repository.findAll().size());
    }

    @Test
    public void shouldNotSaveDuplicatedAnalysis() {
        List<AnalysisData> analyzes = new ArrayList<>();
        IRepository repository = new InMemoryRepository(analyzes);

        repository.save(new AnalysisData("duplicated-hash", Boolean.TRUE));
        repository.save(new AnalysisData("duplicated-hash", Boolean.TRUE));

        assertEquals(1, repository.findAll().size());
    }

}
