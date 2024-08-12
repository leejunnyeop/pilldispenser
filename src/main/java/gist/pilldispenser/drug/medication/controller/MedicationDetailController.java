package gist.pilldispenser.drug.medication.controller;

import gist.pilldispenser.common.security.UsersDetails;
import gist.pilldispenser.drug.medication.domain.dto.MedicationInfoResponse;
import gist.pilldispenser.drug.medication.service.FullMedicationInfoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@Tag(name = "MedicationDetailController", description = "사용자가 검색하여 등록 API")
@RestController
@RequestMapping("/api/medications")
@RequiredArgsConstructor
public class MedicationDetailController {

    private final FullMedicationInfoService fullMedicationDetailService;

//        @Operation(summary = "약 정보 등록", description = "사용자가 검색을 통해 알약을 저장합니다. (검색을 활용해서 등록)")
//        @PostMapping("/register")
//        public ResponseEntity<MedicationInfoResponse> registerMedicationInfo(
//                @AuthenticationPrincipal UsersDetails usersDetails, @RequestParam String itemSeq) {
//            MedicationInfoResponse response = fullMedicationDetailService.saveFullMedicationInfoByItemSeq(usersDetails.getId(), itemSeq);
//            return ResponseEntity.ok(response);
//        }

        // 등록 x

    }



