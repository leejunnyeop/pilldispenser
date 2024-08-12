package gist.pilldispenser.drug.userDrugInfo.service;

import gist.pilldispenser.drug.api.drugIdentificationAPI.domain.entity.DrugIdentification;
import gist.pilldispenser.drug.userDrugInfo.domain.CartridgeSlotMapper;
import gist.pilldispenser.drug.userDrugInfo.domain.dto.CartridgeSlotResponseDto;
import gist.pilldispenser.drug.userDrugInfo.domain.dto.DrugAssignmentResponseDto;
import gist.pilldispenser.drug.userDrugInfo.domain.entity.CartridgeSlot;
import gist.pilldispenser.drug.userDrugInfo.domain.entity.UserDrugInfo;
import gist.pilldispenser.drug.userDrugInfo.repository.CartridgeSlotRepository;
import gist.pilldispenser.drug.userDrugInfo.repository.UserDrugInfoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CartridgeSlotService {

    private final CartridgeSlotRepository cartridgeSlotRepository;
    private final UserDrugInfoRepository userDrugInfoRepository;


    // 약물 정보를 조회하고, 가장 낮은 번호의 비어있는 슬롯에 할당 후 저장하는 메서드
    @Transactional
    public DrugAssignmentResponseDto assignDrugToLowestAvailableSlot(Long userId) {
        // 유저의 약물 정보를 조회
        UserDrugInfo userDrugInfo = userDrugInfoRepository.findByUserId(userId)
                .stream().findFirst()
                .orElseThrow(() -> new IllegalArgumentException("해당 유저의 약물 정보를 찾을 수 없습니다."));

        // 비어있는 슬롯을 번호 순으로 조회하여 가장 낮은 슬롯 선택
        CartridgeSlot availableSlot = cartridgeSlotRepository.findByIsOccupiedFalseOrderBySlotNumberAsc()
                .stream().findFirst()
                .orElseThrow(() -> new IllegalStateException("사용 가능한 슬롯이 없습니다."));

        // 슬롯에 약물 정보 할당 및 사용 중으로 상태 업데이트
        CartridgeSlot updatedSlot = CartridgeSlot.builder()
                .id(availableSlot.getId())
                .userDrugInfo(userDrugInfo)  // 조회한 약물 정보를 슬롯에 할당
                .slotNumber(availableSlot.getSlotNumber())
                .isOccupied(true)  // 슬롯을 사용 중으로 업데이트
                .size(availableSlot.getSize())
                .build();

        // 슬롯 정보 저장
        CartridgeSlot savedSlot = cartridgeSlotRepository.save(updatedSlot);
        return CartridgeSlotMapper.toDrugAssignmentResponseDto(savedSlot);

    }

    // 알약 고유번호를 기반으로 디스크 슬롯을 배정하는 메서드
    @Transactional
    public CartridgeSlotResponseDto assignDiskByItemSeq(Long userId, String itemSeq) {
        // 유저의 약물 정보를 itemSeq를 사용해 조회
        UserDrugInfo userDrugInfo = userDrugInfoRepository.findByUserIdAndFullMedicationInfoItemSeq(userId, itemSeq)
                .orElseThrow(() -> new IllegalArgumentException("해당 유저의 약물 정보를 찾을 수 없습니다."));

        // 알약의 크기와 형태를 기반으로 디스크 크기를 결정
        DrugIdentification drugIdentification = userDrugInfo.getFullMedicationInfo().getDrugIdentification();
        String diskSize = determineDiskSize(drugIdentification);

        // 결정된 디스크 크기에 맞는 비어있는 슬롯을 조회
        CartridgeSlot availableSlot = cartridgeSlotRepository.findBySizeAndIsOccupiedFalse(diskSize)
                .orElseThrow(() -> new IllegalArgumentException("해당 크기에 맞는 빈 슬롯이 없습니다."));

        // 슬롯에 약물 정보 할당 및 사용 중으로 상태 업데이트
        CartridgeSlot size = availableSlot.updateSize(diskSize);

        // 슬롯 정보 저장
        CartridgeSlot savedSlot = cartridgeSlotRepository.save(size);
        return CartridgeSlotMapper.toCartridgeSlotResponseDto(savedSlot);
    }

    // 알약의 크기와 형태를 기반으로 디스크 크기를 결정하는 메서드
    private String determineDiskSize(DrugIdentification drugIdentification) {
        double lengLong = Double.parseDouble(drugIdentification.getLengLong());
        double lengShort = Double.parseDouble(drugIdentification.getLengShort());

        if ("원형".equals(drugIdentification.getDrugShape())) {
            double diameter = Math.max(lengLong, lengShort);
            if (diameter > 10.0) {
                return "L";
            } else if (diameter > 5.0) {
                return "M";
            } else {
                return "S";
            }
        } else {
            double averageLength = (lengLong + lengShort) / 2;
            if (averageLength > 15.0) {
                return "L";
            } else if (averageLength > 7.5) {
                return "M";
            } else {
                return "S";
            }
        }
    }
}
