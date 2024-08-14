package gist.pilldispenser.drug.api.drugSummaryAPI.controller;

import gist.pilldispenser.common.security.UsersDetails;
import gist.pilldispenser.drug.api.drugSummaryAPI.domain.dto.response.DrugSummaryDTO;
import gist.pilldispenser.drug.api.drugSummaryAPI.domain.dto.response.PrecautionResponseDto;
import gist.pilldispenser.drug.api.drugSummaryAPI.service.DrugSummarySearchService;
import gist.pilldispenser.drug.api.drugSummaryAPI.service.DrugSummarySearchServiceImpl;
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

@Tag(name = "OPEN API - Drug Summary", description = "OpenApi에서 약물 혼용불가성분 정보를 가져옵니다.")
@RestController
@RequiredArgsConstructor
public class DrugSummaryController {

    private final DrugSummarySearchService drugSummarySearchService;
    private final DrugSummaryService drugSummaryService;
    private final DrugSummarySearchServiceImpl drugSummarySearchServiceImpl;

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
    @GetMapping("/api/medications/search-drugs")
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
    /**
     * itemSeq로 혼용이 안 되는 성분을 포함하는 약물 정보 조회
     *
     * @param itemSeq 약의 고유번호
     * @return 혼용 금지 성분이 포함된 약물 이름 리스트 또는 null
     */
    @GetMapping("/api/medications/checkConflicts")
    public ResponseEntity<List<String>> findDrugsByConflictingComponents(@RequestParam(name = "itemSeq") String itemSeq, @AuthenticationPrincipal UsersDetails usersDetails) {
        try {
            Long userId = usersDetails.getId();
            List<String> result = drugSummarySearchServiceImpl.findDrugsByConflictingComponents(itemSeq, userId);
            return ResponseEntity.ok(result.isEmpty() ? null : result);
        } catch (Exception e) {
            return ResponseEntity.status(500).body(null);
        }
    }

    /**
     * 특정 성분으로 혼용 금지 성분을 포함하는 약물 정보 조회
     *
     * @param componentName 검색할 성분 이름
     * @return 혼용 금지 성분이 포함된 약물 이름 리스트 또는 null
     */

    @Operation(summary = "특정 성분으로 혼용 금지 성분을 포함하는 약물 정보 조회", description = "특정 성분으로 혼용 금지 성분을 포함하는 약물 정보를 조회합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "혼용 금지 성분이 포함된 약물 이름 리스트 조회 성공"),
            @ApiResponse(responseCode = "500", description = "서버 오류")
    })
    @GetMapping("/api/drug-info/componentConflicts")
    public ResponseEntity<List<String>> findDrugsByComponentName(@Parameter(description = "검색할 성분 이름") @RequestParam String componentName, @AuthenticationPrincipal UsersDetails usersDetails) {
        try {
            Long userId = usersDetails.getId();
            List<String> result = drugSummarySearchServiceImpl.findDrugsByComponentName(componentName, userId);

            return ResponseEntity.ok(result.isEmpty() ? null : result);
        } catch (Exception e) {
            return ResponseEntity.status(500).body(null);
        }
    }


}