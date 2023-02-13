package com.simian.api.external.repository;

import com.simian.api.entities.AnalysisData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface AnalysisDataRepository extends JpaRepository<AnalysisData, Long> {

    @Query(value ="SELECT COUNT(a) FROM AnalysisData a WHERE a.isSimianDna =:isSimianDna")
    public Long countByIsSimian(Boolean isSimianDna);

    @Query(value ="SELECT COUNT(a) FROM AnalysisData a WHERE a.hash =:hash")
    public Long countByHash(String hash);
}
