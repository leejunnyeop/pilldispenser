package gist.pilldispenser.drug.medication.controller;

import gist.pilldispenser.common.security.UsersDetails;
import gist.pilldispenser.drug.medication.service.FullMedicationInfoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@Tag(name = "MedicationDetailController", description = "사용자가 검색하여 등록 API")
@RestController
@RequestMapping("/api/drug-info")
@RequiredArgsConstructor
public class MedicationDetailController {

    private final FullMedicationInfoService medicationDetailService;

    @Operation(summary = "약 정보 저장", description = "사용자가 검색을 통해 알약을 저장합니다.")
    @PostMapping("/register/search")
    public ResponseEntity<String> registerDrugInfoBySearch(
            @RequestParam(name = "itemSeq") String itemSeq,
            @AuthenticationPrincipal UsersDetails userDetails) {
        Long userId = userDetails.getId();
        medicationDetailService.saveFullMedicationInfoByItemSeq(userId, itemSeq);
        return ResponseEntity.ok("약 정보가 성공적으로 저장되었습니다.");
    }


}