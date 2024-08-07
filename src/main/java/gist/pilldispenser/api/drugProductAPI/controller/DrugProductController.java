package gist.pilldispenser.api.drugProductAPI.controller;


import gist.pilldispenser.api.drugProductAPI.service.DrugProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
