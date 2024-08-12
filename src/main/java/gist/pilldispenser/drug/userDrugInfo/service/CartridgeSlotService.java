package gist.pilldispenser.drug.userDrugInfo.service;

import gist.pilldispenser.drug.api.drugIdentificationAPI.domain.entity.DrugIdentification;
import gist.pilldispenser.drug.drugInfo.domain.entity.DrugInfo;
import gist.pilldispenser.drug.drugInfo.repository.DrugInfoRepository;
import gist.pilldispenser.drug.medication.domain.entity.FullMedicationInfo;
import gist.pilldispenser.drug.medication.repository.FullMedicationInfoRepository;
import gist.pilldispenser.drug.userDrugInfo.domain.entity.CartridgeSlot;
import gist.pilldispenser.drug.userDrugInfo.domain.entity.UserDrugInfo;
import gist.pilldispenser.drug.userDrugInfo.repository.CartridgeSlotRepository;
import gist.pilldispenser.drug.userDrugInfo.repository.UserDrugInfoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CartridgeSlotService {

    private final CartridgeSlotRepository cartridgeSlotRepository;
    private final UserDrugInfoRepository userDrugInfoRepository;
    private final FullMedicationInfoRepository fullMedicationInfoRepository;
    private final DrugInfoRepository drugInfoRepository;


    // 약물 정보를 조회하고, 가장 낮은 번호의 비어있는 슬롯에 할당 후 저장하는 메서드

// 약물 정보를 조회하고, 가장 낮은 번호의 비어있는 슬롯에 할당 후 저장하는 메서드
//    @Transactional
//    public DrugAssignmentResponseDto assignDrugToLowestAvailableSlot(Long userId) {
//        // 유저의 약물 정보를 조회
//        UserDrugInfo userDrugInfo = userDrugInfoRepository.findByUserId(userId)
//                .stream().findFirst()
//                .orElseThrow(() -> new IllegalArgumentException("해당 유저의 약물 정보를 찾을 수 없습니다."));
//
//        // 비어있는 슬롯을 번호 순으로 조회하여 가장 낮은 슬롯 선택
//        CartridgeSlot availableSlot = cartridgeSlotRepository.findByIsOccupiedFalseOrderBySlotNumberAsc()
//                .stream().findFirst()
//                .orElseThrow(() -> new IllegalStateException("사용 가능한 슬롯이 없습니다."));
//
//        // 슬롯에 약물 정보 할당 및 사용 중으로 상태 업데이트
//        CartridgeSlot updatedSlot = CartridgeSlot.builder()
//                .id(availableSlot.getId())
//                .userDrugInfo(userDrugInfo)  // 조회한 약물 정보를 슬롯에 할당
//                .slotNumber(availableSlot.getSlotNumber())
//                .isOccupied(true)  // 슬롯을 사용 중으로 업데이트
//                .size(availableSlot.getSize())
//                .build();
//
//        // 슬롯 정보 저장
//        CartridgeSlot savedSlot = cartridgeSlotRepository.save(updatedSlot);
//        return CartridgeSlotMapper.toDrugAssignmentResponseDto(savedSlot);
//    }

    @Transactional
    public Long findLowestAvailableSlotId(Long userId) {
        // 비어있는 슬롯을 번호 순으로 조회하여 가장 낮은 슬롯 선택
        CartridgeSlot availableSlot = cartridgeSlotRepository.findFirstByUserIdAndIsOccupiedFalseOrderBySlotNumberAsc(userId)
                .stream().findFirst()
                .orElseThrow(() -> new IllegalStateException("사용 가능한 슬롯이 없습니다."));
        return availableSlot.getId();
    }

    // 알약 고유번호를 기반으로 디스크 슬롯을 배정하는 메서드
//    @Transactional
//    public CartridgeSlotResponseDto assignDiskByItemSeq(Long userId, String itemSeq) {
//        // 유저의 약물 정보를 itemSeq를 사용해 조회
//        UserDrugInfo userDrugInfo = userDrugInfoRepository.findByUserIdAndFullMedicationInfoItemSeq(userId, itemSeq)
//                .orElseThrow(() -> new IllegalArgumentException("해당 유저의 약물 정보를 찾을 수 없습니다."));
//
//        // 알약의 크기와 형태를 기반으로 디스크 크기를 결정
//        DrugIdentification drugIdentification = userDrugInfo.getFullMedicationInfo().getDrugIdentification();
//        String diskSize = determineDiskSize(drugIdentification);
//
//        // 결정된 디스크 크기에 맞는 비어있는 슬롯을 조회
//        CartridgeSlot availableSlot = cartridgeSlotRepository.findBySizeAndIsOccupiedFalse(diskSize)
//                .orElseThrow(() -> new IllegalArgumentException("해당 크기에 맞는 빈 슬롯이 없습니다."));
//
//        // 슬롯에 약물 정보 할당 및 사용 중으로 상태 업데이트
//        CartridgeSlot size = availableSlot.updateSize(diskSize);
//
//        // 슬롯 정보 저장
//        CartridgeSlot savedSlot = cartridgeSlotRepository.save(size);
//        return CartridgeSlotMapper.toCartridgeSlotResponseDto(savedSlot);
//    }

    public String assignDiskByItemSeq(Long userId, String itemSeq){
        FullMedicationInfo fullMedicationInfo = fullMedicationInfoRepository.findByItemSeq(itemSeq)
                .orElseThrow(() -> new RuntimeException("해당 일련번호의 약이 존재하지 않습니다."));

        // 알약의 크기와 형태를 기반으로 디스크 크기를 결정
        DrugIdentification drugIdentification = fullMedicationInfo.getDrugIdentification();
        String diskSize = determineDiskSize(drugIdentification.getDrugShape(),
                Double.parseDouble(drugIdentification.getLengLong()));

        if (cartridgeSlotRepository.existsByUserIdAndIsOccupiedFalse(userId)){
            return diskSize;
        } else {
            throw new RuntimeException("빈 슬롯이 없습니다.");
        }
    }

    public String assignDiskByDrugInfoId(Long userId, String drugShape, Double drugLeng) {

        String diskSize = determineDiskSize(drugShape, drugLeng);
        if (cartridgeSlotRepository.existsByUserIdAndIsOccupiedFalse(userId)) {
            return diskSize;
        } else {
            throw new RuntimeException("빈 슬롯이 없습니다.");
        }
    }

    // 알약의 크기와 형태를 기반으로 디스크 크기를 결정하는 메서드
    private String determineDiskSize(String drugShape, double lengLong) {

        if ("원형".equals(drugShape)) {
            if (lengLong >= 11.2) {
                return "L";
            } else if (lengLong >= 9.5) {
                return "M";
            } else {
                return "S";
            }
        } else {
            if (lengLong >= 18.0) {
                return "L";
            } else if (lengLong >= 17.0) {
                return "M";
            } else {
                return "S";
            }
        }
    }
}
