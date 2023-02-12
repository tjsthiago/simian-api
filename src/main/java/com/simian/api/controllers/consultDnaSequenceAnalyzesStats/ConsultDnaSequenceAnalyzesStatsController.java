package com.simian.api.controllers.consultDnaSequenceAnalyzesStats;

import com.simian.api.useCase.consultDnaSequenceAnalyzesStats.ConsultDnaSequenceAnalyzesStats;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ConsultDnaSequenceAnalyzesStatsController {

    private final ConsultDnaSequenceAnalyzesStats useCase;

    public ConsultDnaSequenceAnalyzesStatsController(ConsultDnaSequenceAnalyzesStats useCase) {
        this.useCase = useCase;
    }

    @GetMapping("/stats")
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public DnaSequenceAnalyzesStats stats() {
        return useCase.getDnaSequenceAnalyzesStats();
    }
}
