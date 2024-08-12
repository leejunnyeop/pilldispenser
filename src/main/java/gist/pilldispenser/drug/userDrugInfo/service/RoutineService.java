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
import java.util.Optional;

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
    @Transactional(readOnly = true)
    public List<Routine> getRoutinesByUserId(Long userId) {
        return  routineRepository.findByUserDrugInfoUserId(userId);
    }

    // 특정 루틴 조회
    @Transactional(readOnly = true)
    public Optional<Routine> getRoutineById(Long routineId) {
        return routineRepository.findById(routineId);
    }

    // 루틴 업데이트
    @Transactional
    public Routine updateRoutine(Long routineId, RoutineRequestDto routineRequestDto) {
        Routine routine = routineRepository.findById(routineId)
                .orElseThrow(() -> new IllegalArgumentException("해당 ID의 루틴을 찾을 수 없습니다."));

        routine.updateRoutine(routineRequestDto.getTime(), routineRequestDto.getDosagePerTake(),
                routineRequestDto.getDailyDosage(), routineRequestDto.isActive(), routineRequestDto.getDayOfWeek());

        return routineRepository.save(routine);
    }

    // 루틴 삭제
    @Transactional
    public void deleteRoutine(Long routineId) {
        Routine routine = routineRepository.findById(routineId)
                .orElseThrow(() -> new IllegalArgumentException("해당 ID의 루틴을 찾을 수 없습니다."));

        routineRepository.delete(routine);
    }
}
