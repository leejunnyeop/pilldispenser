package gist.pilldispenser.drug.api.drugIdentificationAPI.controller;

import gist.pilldispenser.drug.api.drugIdentificationAPI.domain.dto.DrugSizeCategory;
import gist.pilldispenser.drug.api.drugIdentificationAPI.service.DrugIdentificationService;
import gist.pilldispenser.drug.api.drugIdentificationAPI.service.DrugIdentificationSizeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;


@Tag(name = "DrugIdentification", description = "약물 식별 정보를 관리하는 API를 제공합니다.")
@RestController
@RequestMapping("/api/drug-identification")
@RequiredArgsConstructor
public class DrugIdentificationController {

    private final DrugIdentificationService drugIdentificationService;

    private final DrugIdentificationSizeService drugIdentificationSizeService;

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


        @Operation(summary = "약물 크기 조회", description = "품목 기준 코드(itemSeq)를 사용하여 약물의 크기를 조회합니다.")
        @ApiResponses(value = {
                @ApiResponse(responseCode = "200", description = "약물 크기 조회 성공"),
                @ApiResponse(responseCode = "404", description = "해당 itemSeq에 대한 약물 정보를 찾을 수 없음"),
                @ApiResponse(responseCode = "500", description = "서버 오류")
        })
    @GetMapping("/{itemSeq}")
    public ResponseEntity<DrugSizeCategory> getDrugSizeByItemSeq(
            @PathVariable(name = "itemSeq") String itemSeq) {
        Optional<DrugSizeCategory> drugSizeCategory = drugIdentificationSizeService.findDrugSizeByItemSeq(itemSeq);
        return drugSizeCategory.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}