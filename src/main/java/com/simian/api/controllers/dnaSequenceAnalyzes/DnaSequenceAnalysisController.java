package com.simian.api.controllers.dnaSequenceAnalyzes;

import com.simian.api.useCase.dnaSequenceAnalyzes.DnaSequenceAnalysis;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DnaSequenceAnalysisController {

    private final DnaSequenceAnalysis useCase;

    public DnaSequenceAnalysisController(DnaSequenceAnalysis useCase) {
        this.useCase = useCase;
    }


    @PostMapping("/simian")
    public ResponseEntity<Object> isSimian(@RequestBody DnaSequenceAnalysisRequest request) {
        Boolean isSimian = useCase.perform(request.getDna());

        if(isSimian) {
            return new ResponseEntity<>(HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.FORBIDDEN);
    }

}
