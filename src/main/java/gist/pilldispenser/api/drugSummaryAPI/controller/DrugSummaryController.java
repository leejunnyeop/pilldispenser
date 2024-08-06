package gist.pilldispenser.api.drugSummaryAPI.controller;

import gist.pilldispenser.api.drugSummaryAPI.service.DrugSummaryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
class DrugSummaryController {

    private final DrugSummaryService drugSummaryService;

    @GetMapping("/fetch-and-save-drugs")
    public ResponseEntity<String> fetchAndSaveDrugs() throws Exception {
        String result = drugSummaryService.fetchAndSaveDrugs();
        return ResponseEntity.ok(result);
    }
}