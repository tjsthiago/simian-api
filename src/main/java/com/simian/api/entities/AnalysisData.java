package com.simian.api.entities;

import jakarta.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "analysis_data", catalog="public")
public class AnalysisData {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;

    @Column(name="hash", length=36, nullable=false)
    private String hash;

    @Column(name="is_simian_dna")
    private Boolean isSimianDna;

    public AnalysisData() {

    }

    public AnalysisData(String hash, Boolean isSimianDna) {
        this.hash = hash;
        this.isSimianDna = isSimianDna;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }

    public Boolean isSimianDna() {
        return isSimianDna;
    }

    public void setSimianDna(Boolean simianDna) {
        isSimianDna = simianDna;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AnalysisData that = (AnalysisData) o;
        return Objects.equals(hash, that.hash);
    }

    @Override
    public int hashCode() {
        return Objects.hash(hash);
    }
}
