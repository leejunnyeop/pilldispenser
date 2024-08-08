package gist.pilldispenser.drug.medication.controller;

import gist.pilldispenser.common.security.UsersDetails;
import gist.pilldispenser.drug.medication.domain.FullMedicationInfo;
import gist.pilldispenser.drug.medication.service.MedicationDetailService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Medication Detail", description = "사용자가 복용 중인 약 정보 관리 API")
@RestController
@RequestMapping("/api/medications")
@RequiredArgsConstructor
public class MedicationDetailController {

    private final MedicationDetailService medicationDetailService;

    @Operation(summary = "약 정보 저장", description = "사용자가 검색을 통해 알약을 저장합니다.")
    @PostMapping("/save")
    public ResponseEntity<String> saveMedicationDetail(
            @RequestParam(name = "itemSeq") String itemSeq,
            @AuthenticationPrincipal UsersDetails userDetails) {
        Long userId = userDetails.getId();
        medicationDetailService.saveMedicationDetailByItemSeq(userId, itemSeq);
        return ResponseEntity.ok("약 정보가 성공적으로 저장되었습니다.");
    }

    @GetMapping("/details")
    public ResponseEntity<FullMedicationInfo> getMedicationDetailByItemSeq(@RequestParam(name = "itemSeq") String itemSeq) {
        FullMedicationInfo fullMedicationInfo = medicationDetailService.findMedicationDetailByItemSeq(itemSeq);
        return ResponseEntity.ok(fullMedicationInfo);
    }
}