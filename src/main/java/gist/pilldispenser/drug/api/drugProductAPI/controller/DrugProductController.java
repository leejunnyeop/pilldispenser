package gist.pilldispenser.drug.api.drugProductAPI.controller;


import gist.pilldispenser.drug.api.drugProductAPI.service.DrugProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping
@RequiredArgsConstructor
public class DrugProductController {

    private final DrugProductService drugProductService;

    @GetMapping("/fetch-and-save-drug-products")
    public ResponseEntity<String> fetchAndSaveDrugProducts() {
        try {
            String result = drugProductService.fetchAndSaveDrugProducts();
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            return ResponseEntity.status(500).body("서버 오류: " + e.getMessage());
        }
    }


    @GetMapping("/api/drug-info/autocomplete")
    public ResponseEntity<List<String>> autocompleteMtralNm(@RequestParam String partialName) {
        List<String> results = drugProductService.searchMtralNmByPartialName(partialName);
        return ResponseEntity.ok(results);
    }
}
