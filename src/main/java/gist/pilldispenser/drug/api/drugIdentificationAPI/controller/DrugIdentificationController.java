package gist.pilldispenser.drug.api.drugIdentificationAPI.controller;

import gist.pilldispenser.drug.api.drugIdentificationAPI.service.DrugIdentificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class DrugIdentificationController {

    private final DrugIdentificationService drugIdentificationService;


    /**
     * API를 통해 의약품 식별 정보를 가져와서 저장합니다.
     *
     * @return 가져온 데이터 저장 결과 메시지
     */
    @GetMapping("/fetch-and-save")
    public ResponseEntity<String> fetchAndSaveDrugIdentifications() {
        try {
            String result = drugIdentificationService.fetchAndSaveDrugIdentifications();
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            return ResponseEntity.status(500).body("데이터를 가져오는 동안 오류가 발생했습니다: " + e.getMessage());
        }
    }
}