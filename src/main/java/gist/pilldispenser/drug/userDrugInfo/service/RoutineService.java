package gist.pilldispenser.drug.userDrugInfo.service;

import gist.pilldispenser.drug.userDrugInfo.domain.dto.RoutineRequestDto;
import gist.pilldispenser.drug.userDrugInfo.domain.entity.Routine;
import gist.pilldispenser.drug.userDrugInfo.domain.entity.UserDrugInfo;
import gist.pilldispenser.drug.userDrugInfo.domain.enums.DayType;
import gist.pilldispenser.drug.userDrugInfo.repository.RoutineRepository;
import gist.pilldispenser.drug.userDrugInfo.repository.UserDrugInfoRepository;
import gist.pilldispenser.notification.service.CustomScheduleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RoutineService {

    private final RoutineRepository routineRepository;
    private final UserDrugInfoRepository userDrugInfoRepository;
    private final CustomScheduleService customScheduleService;

    @Transactional
    public List<Routine> createRoutine(Long userId, List<RoutineRequestDto> routineRequestDtos) {
        String itemSeq = routineRequestDtos.get(0).getItemSeq();

        // userId와 itemSeq를 이용해 UserDrugInfo를 찾음
        UserDrugInfo userDrugInfo = userDrugInfoRepository.findByUserIdAndFullMedicationInfoItemSeq(userId, itemSeq)
                .orElseThrow(() -> new IllegalArgumentException("유효하지 않은 사용자 또는 약물 정보입니다."));

        List<Routine> routines = new ArrayList<>();

        // Routine 엔티티 생성 및 저장
        // 매일 체크 되어있으면 1개의 루틴 생성, 매일 아니면 각 요일마다 1개의 루틴 생성
        for (RoutineRequestDto routineRequestDto : routineRequestDtos) {
            List<DayType> days = routineRequestDto.getDayOfWeek().size() == 7 ?
                    List.of(DayType.EVERYDAY) :
                    routineRequestDto.getDayOfWeek().stream()
                            .map(DayType::fromDayName)
                            .toList();

            for (DayType day : days) {
                Routine routine = Routine.builder()
                        .userDrugInfo(userDrugInfo)
                        .time(routineRequestDto.getTime())
                        .dosagePerTake(routineRequestDto.getDosagePerTake())
                        .dailyDosage(routineRequestDto.getDailyDosage())
                        .isActive(routineRequestDto.getIsActive())
                        .days(day)
                        .build();

                routines.add(routine);
                routineRepository.save(routine);
            }
        }
        return routines;
    }

    // 루틴 업데이트
    @Transactional
    public Routine updateRoutine(Long routineId, RoutineRequestDto routineRequestDto) {
        Routine routine = routineRepository.findById(routineId)
                .orElseThrow(() -> new IllegalArgumentException("해당 ID의 루틴을 찾을 수 없습니다."));

        routine.updateRoutine(routineRequestDto.getTime(), routineRequestDto.getDosagePerTake(),
                routineRequestDto.getDailyDosage(), routineRequestDto.getIsActive());

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
