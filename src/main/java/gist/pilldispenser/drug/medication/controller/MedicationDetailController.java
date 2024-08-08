package gist.pilldispenser.drug.medication.controller;

import gist.pilldispenser.drug.medication.domain.MedicationDetail;
import gist.pilldispenser.drug.medication.service.MedicationDetailService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/medications")
@RequiredArgsConstructor
public class MedicationDetailController {

    private final MedicationDetailService medicationDetailService;

    @PostMapping("/save")
    public ResponseEntity<MedicationDetail> saveMedicationDetail(@RequestParam(name = "itemSeq") String itemSeq) {
        MedicationDetail savedMedicationDetail = medicationDetailService.saveMedicationDetailByItemSeq(itemSeq);
        return ResponseEntity.ok(savedMedicationDetail);
    }

    @GetMapping("/details")
    public ResponseEntity<MedicationDetail> getMedicationDetailByItemSeq(@RequestParam(name = "itemSeq") String itemSeq) {
        MedicationDetail medicationDetail = medicationDetailService.findMedicationDetailByItemSeq(itemSeq);
        return ResponseEntity.ok(medicationDetail);
    }
}