package gist.pilldispenser.drug.drugInfo.controller;

import gist.pilldispenser.common.security.UsersDetails;
import gist.pilldispenser.drug.drugInfo.domain.dto.DrugInfoRequest;
import gist.pilldispenser.drug.drugInfo.service.DrugInfoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/drug-info")
@RequiredArgsConstructor
public class DrugInfoController {

    private final DrugInfoService drugInfoService;

    @PostMapping("/register/manual")
    public ResponseEntity<String> registerDrugInfoManually(
            @RequestBody DrugInfoRequest drugInfoRequest,
            @AuthenticationPrincipal UsersDetails userDetails) {
        Long userId = userDetails.getId();
        drugInfoService.createDrugInfoManually(userId, drugInfoRequest);
        return ResponseEntity.ok("직접 약 정보가 성공적으로 저장되었습니다.");
    }


}
