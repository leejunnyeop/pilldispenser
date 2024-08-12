package gist.pilldispenser.drug.userDrugInfo.controller;

import gist.pilldispenser.common.security.UsersDetails;
import gist.pilldispenser.drug.userDrugInfo.service.CartridgeSlotService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cartridge-slots")
@RequiredArgsConstructor
public class CartridgeSlotController {

    private final CartridgeSlotService cartridgeSlotService;

//    @Operation(summary = "가장 낮은 번호의 비어있는 카트리지 슬롯에 약물 할당", description = "유저의 약물 정보를 조회하고, 가장 낮은 번호의 비어있는 슬롯에 할당합니다.")
//    @PostMapping("/assign")
//    public ResponseEntity<DrugAssignmentResponseDto> assignDrugToLowestAvailableSlot(
//            @Parameter(description = "유저 ID") @AuthenticationPrincipal UsersDetails usersDetails){
//
//            DrugAssignmentResponseDto assignedSlot =
//                    cartridgeSlotService.assignDrugToLowestAvailableSlot(usersDetails.getId());
//        return ResponseEntity.ok(assignedSlot);
//    }

    @GetMapping("/assign")
    public ResponseEntity<Long> findLowestCartridgeNo(@AuthenticationPrincipal UsersDetails usersDetails) {
        Long slotId = cartridgeSlotService.findLowestAvailableSlotId(usersDetails.getId());
        return ResponseEntity.ok(slotId);
    }

//    @Operation(summary = "알약 고유번호를 조회하여 디스크 슬롯에 저장", description = "유저의 약물 정보를 조회하고, 해당 알약에 맞는 디스크 슬롯을 찾아 저장합니다.")
//    @PostMapping("/assign-disk-by-item-seq")
//    public ResponseEntity<CartridgeSlotResponseDto> assignDiskByItemSeq(
//            @Parameter(description = "유저 ID") @AuthenticationPrincipal UsersDetails usersDetails,
//            @Parameter(description = "알약 고유번호") @RequestParam String itemSeq) {
//        CartridgeSlotResponseDto assignedSlot = cartridgeSlotService.assignDiskByItemSeq(usersDetails.getId(), itemSeq);
//        return ResponseEntity.ok(assignedSlot);
//    }

    @Operation(summary = "알약 고유번호를 조회하여 디스크 슬롯에 저장", description = "유저의 약물 정보를 조회하고, 해당 알약에 맞는 디스크 슬롯을 찾아 저장합니다.")
    @GetMapping("/assign-disk-by-item-seq")
    public ResponseEntity<String> assignDiskByItemSeq(
            @Parameter(description = "유저 ID") @AuthenticationPrincipal UsersDetails usersDetails,
            @Parameter(description = "알약 고유번호") @RequestParam(required = false) String itemSeq,
            @Parameter(description = "직접 등록한 약 id") @RequestParam(required = false) Long userDrugInfoId) {

        if (itemSeq != null && userDrugInfoId == null) {
            String availableSlot = cartridgeSlotService.assignDiskByItemSeq(usersDetails.getId(), itemSeq);
            return new ResponseEntity<>(availableSlot, HttpStatus.OK);
        } else if (itemSeq == null && userDrugInfoId != null) {
            String availableSlot = cartridgeSlotService.assignDiskByDrugInfoId(usersDetails.getId(), userDrugInfoId);
            return new ResponseEntity<>(availableSlot, HttpStatus.OK);
        }
        else throw new RuntimeException("디스크 슬롯을 찾을 수 없습니다.");
    }

}
