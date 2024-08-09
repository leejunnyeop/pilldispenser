package gist.pilldispenser.drug.api.drugSummaryAPI.controller;

import gist.pilldispenser.common.security.UsersDetails;
import gist.pilldispenser.drug.api.drugSummaryAPI.domain.dto.response.DrugSummaryDTO;
import gist.pilldispenser.drug.api.drugSummaryAPI.domain.dto.response.PrecautionResponseDto;
import gist.pilldispenser.drug.api.drugSummaryAPI.service.DrugSummarySearchService;
import gist.pilldispenser.drug.api.drugSummaryAPI.service.DrugSummaryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Tag(name = "DrugSummary Controller", description = "약물 상세 정보를 검색하는 API를 제공합니다.")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/drug-info")
public class DrugSummaryController {

    private final DrugSummarySearchService drugSummarySearchService;
    private final DrugSummaryService drugSummaryService;

    @GetMapping("/fetch-and-save-drugs")
    public ResponseEntity<String> fetchAndSaveDrugs() throws Exception {
        String result = drugSummaryService.fetchAndSaveDrugs();
        return ResponseEntity.ok(result);
    }

    @Operation(summary = "자동완성 검색", description = "주어진 약 이름으로 약물 정보를 검색합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "검색된 약물 정보 리스트"),
            @ApiResponse(responseCode = "500", description = "서버 오류")
    })
    @GetMapping("/search-drugs")
    public ResponseEntity<List<DrugSummaryDTO>> searchDrugsByName(
            @Parameter(description = "검색할 약 이름") @RequestParam(name = "itemName") String itemName) {
        try {
            List<DrugSummaryDTO> drugSummaries = drugSummarySearchService.searchDrugsByName(itemName);
            return ResponseEntity.ok(drugSummaries);
        } catch (Exception e) {
            return ResponseEntity.status(500).body(null);
        }
    }

    @Operation(summary = "복용하지 말아야 하는 약 조회", description = "사용자가 복용하지 말아야 하는 약 정보를 조회합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "복용하지 말아야 하는 약 정보 조회 성공"),
            @ApiResponse(responseCode = "404", description = "사용자 정보가 존재하지 않음"),
            @ApiResponse(responseCode = "500", description = "서버 오류")
    })

    @GetMapping("/contraindicated")
    public ResponseEntity<PrecautionResponseDto> getContraindicationsForDrug(
            @Parameter(description = "조회할 약물의 고유 번호", example = "123456789")
            @RequestParam(name = "itemSeq") String itemSeq,
            @AuthenticationPrincipal UsersDetails userDetails) {
        Long userId = userDetails.getId();
        PrecautionResponseDto precautionResponseDto = drugSummarySearchService.findContraindicationsForDrug(userId, itemSeq);
        return ResponseEntity.ok(precautionResponseDto);
    }
}