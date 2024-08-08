package gist.pilldispenser.drug.medication.controller;

import gist.pilldispenser.common.security.UsersDetails;
import gist.pilldispenser.drug.medication.domain.MedicationDetail;
import gist.pilldispenser.drug.medication.service.MedicationDetailService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/medications")
@RequiredArgsConstructor
public class MedicationDetailController {

    private final MedicationDetailService medicationDetailService;

    @PostMapping("/save")
    public ResponseEntity<String> saveMedicationDetail(
            @RequestParam(name = "itemSeq") String itemSeq,
            @AuthenticationPrincipal UsersDetails userDetails) {
        Long userId = userDetails.getId();
        medicationDetailService.saveMedicationDetailByItemSeq(userId, itemSeq);
        return ResponseEntity.ok("약 정보가 성공적으로 저장되었습니다.");
    }

    @GetMapping("/details")
    public ResponseEntity<MedicationDetail> getMedicationDetailByItemSeq(@RequestParam(name = "itemSeq") String itemSeq) {
        MedicationDetail medicationDetail = medicationDetailService.findMedicationDetailByItemSeq(itemSeq);
        return ResponseEntity.ok(medicationDetail);
    }
}