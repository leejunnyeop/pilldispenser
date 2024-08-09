package gist.pilldispenser.drug.userDrugInfo.service;

import gist.pilldispenser.drug.userDrugInfo.domain.dto.RoutineRequestDto;
import gist.pilldispenser.drug.userDrugInfo.domain.entity.Routine;
import gist.pilldispenser.drug.userDrugInfo.domain.entity.UserDrugInfo;
import gist.pilldispenser.drug.userDrugInfo.repository.RoutineRepository;
import gist.pilldispenser.drug.userDrugInfo.repository.UserDrugInfoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RoutineService {

    private final RoutineRepository routineRepository;
    private final UserDrugInfoRepository userDrugInfoRepository;


    @Transactional
    public Routine createRoutine(Long userId, RoutineRequestDto routineRequestDto) {
        // userId와 itemSeq를 이용해 UserDrugInfo를 찾음
        UserDrugInfo userDrugInfo = userDrugInfoRepository.findByUserIdAndFullMedicationInfoItemSeq(userId, routineRequestDto.getItemSeq())
                .orElseThrow(() -> new IllegalArgumentException("유효하지 않은 사용자 또는 약물 정보입니다."));
        // Routine 엔티티 생성 및 저장
        Routine routine = Routine.builder()
                .userDrugInfo(userDrugInfo)
                .time(routineRequestDto.getTime())
                .dosagePerTake(routineRequestDto.getDosagePerTake())
                .dailyDosage(routineRequestDto.getDailyDosage())
                .isActive(routineRequestDto.isActive())
                .build();

        return routineRepository.save(routine);
    }

}
