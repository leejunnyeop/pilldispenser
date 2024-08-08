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

    @PostMapping
    public ResponseEntity<String> createDrugInfo(@AuthenticationPrincipal UsersDetails usersDetails, @RequestBody DrugInfoRequest drugInfoRequest) {
        drugInfoService.createDrugInfo(usersDetails.getId(), drugInfoRequest);
        return ResponseEntity.ok("약 직접 등록이 완료되었습니다.");
    }


}
