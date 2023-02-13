package com.simian.api.useCase.ports.repository;

import com.simian.api.entities.AnalysisData;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IRepository {

    public AnalysisData save(AnalysisData analysis);

    public List<AnalysisData> findAll();

    public Boolean exists(AnalysisData analysis);

    public int count();

    public Long countByIsSimian(Boolean isSimian);

}
