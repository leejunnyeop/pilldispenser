package gist.pilldispenser.drug.drugInfo.controller;

import gist.pilldispenser.common.security.UsersDetails;
import gist.pilldispenser.drug.drugInfo.domain.dto.DrugInfoRequestBase;
import gist.pilldispenser.drug.drugInfo.domain.dto.DrugInfoResponse;
import gist.pilldispenser.drug.drugInfo.service.DrugInfoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@Tag(name = "DrugInfo", description = "약 정보 관리 API")
@RestController
@RequestMapping("/api/drug-info")
@RequiredArgsConstructor
public class DrugInfoController {

    private final DrugInfoService drugInfoService;

    @Operation(summary = "직접 약 정보 등록  ", description = "사용자가 직접 약 정보를 등록합니다. (직접 약 등록 DrugInfo 테이블) ")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "직접 약 정보가 성공적으로 저장되었습니다."),
            @ApiResponse(responseCode = "400", description = "잘못된 요청입니다."),
            @ApiResponse(responseCode = "500", description = "서버 오류가 발생했습니다.")
    })
    @PostMapping("/register/manual")
    public ResponseEntity<DrugInfoResponse> registerDrugInfoManually(
            @RequestBody DrugInfoRequestBase drugInfoRequest,
            @AuthenticationPrincipal UsersDetails userDetails) {
        Long userId = userDetails.getId();
        DrugInfoResponse response = drugInfoService.createDrugInfoManually(userId, drugInfoRequest);
        return ResponseEntity.ok(response);
    }
}
