package gist.pilldispenser.drug.userDrugInfo.service;

import gist.pilldispenser.common.security.UsersDetails;
import gist.pilldispenser.drug.medication.repository.FullMedicationInfoRepository;

import gist.pilldispenser.drug.userDrugInfo.domain.dto.RoutineRequestDto;
import gist.pilldispenser.drug.userDrugInfo.domain.dto.RoutineResponse;
import gist.pilldispenser.drug.userDrugInfo.domain.entity.Routine;
import gist.pilldispenser.drug.userDrugInfo.domain.entity.UserDrugInfo;
import gist.pilldispenser.drug.userDrugInfo.domain.enums.DayType;
import gist.pilldispenser.drug.userDrugInfo.repository.RoutineRepository;
import gist.pilldispenser.drug.userDrugInfo.repository.UserDrugInfoRepository;
import gist.pilldispenser.notification.service.CustomScheduleService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalTime;
import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class RoutineService {

    private final RoutineRepository routineRepository;
    private final UserDrugInfoRepository userDrugInfoRepository;
    private final FullMedicationInfoRepository fullMedicationInfoRepository;
    private final CustomScheduleService customScheduleService;

    @Transactional
    public List<Routine> createRoutine(Long userId, List<RoutineRequestDto> routineRequestDtos) {
        Long userDrugInfoId = routineRequestDtos.get(0).getUserDrugInfoId();
        UserDrugInfo userDrugInfo = userDrugInfoRepository.findById(userDrugInfoId)
                .orElseThrow(() -> new RuntimeException("약이 등록되지 않았습니다."));

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
//    @Transactional
//    public Routine updateRoutine(Long routineId, RoutineRequestDto routineRequestDto) {
//        Routine routine = routineRepository.findById(routineId)
//                .orElseThrow(() -> new IllegalArgumentException("해당 ID의 루틴을 찾을 수 없습니다."));
//
//        routine.updateRoutine(routineRequestDto.getTime(), routineRequestDto.getDosagePerTake(),
//                routineRequestDto.getDailyDosage(), routineRequestDto.getIsActive());
//
//        return routineRepository.save(routine);
//    }

    // 루틴 조회
    public List<RoutineResponse> getRoutinesForUserDrugInfo(Long userDrugInfoId) {
        UserDrugInfo userDrugInfo = userDrugInfoRepository.findById(userDrugInfoId)
                .orElseThrow(() -> new RuntimeException("no such entry"));

        List<Routine> routines = routineRepository.findByUserDrugInfo(userDrugInfo);
        List<RoutineResponse> responses = new ArrayList<>();
        for (Routine routine : routines) {
            RoutineResponse routineResponse = RoutineResponse.builder()
                    .routineId(routine.getId())
                    .day(routine.getDays().getDayName())
                    .dailyDosage(String.valueOf(routine.getDailyDosage()))
                    .dosagePerTake(String.valueOf(routine.getDosagePerTake()))
                    .time(String.valueOf(routine.getTime()))
                    .build();
            responses.add(routineResponse);
        }
        return responses;
    }

}
