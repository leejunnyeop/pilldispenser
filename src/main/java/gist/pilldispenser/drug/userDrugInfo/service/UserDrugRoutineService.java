package gist.pilldispenser.drug.userDrugInfo.service;

import gist.pilldispenser.drug.userDrugInfo.domain.dto.UserDrugRoutineResponse;
import gist.pilldispenser.drug.userDrugInfo.domain.entity.UserDrugInfo;
import gist.pilldispenser.drug.userDrugInfo.repository.UserDrugInfoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserDrugRoutineService {

    private final UserDrugInfoRepository userDrugInfoRepository;

    @Transactional(readOnly = true)
    public List<UserDrugRoutineResponse> getUserDrugRoutines(Long userId) {
        List<UserDrugInfo> userDrugInfos = userDrugInfoRepository.findByUserId(userId);

        return userDrugInfos.stream()
                .flatMap(userDrugInfo -> userDrugInfo.getRoutines().stream().map(routine -> {
                    String drugName = userDrugInfo.getDrugInfo() != null
                            ? userDrugInfo.getDrugInfo().getName()
                            : userDrugInfo.getFullMedicationInfo().getDrugSummary().getItemName();
                    String drugType = userDrugInfo.getDrugInfo() != null ? "direct" : "search";

                    return UserDrugRoutineResponse.builder()
                            .drugName(drugName)
                            .drugType(drugType)
                            .time(routine.getTime())
                            .dosagePerTake(routine.getDosagePerTake())
                            .dailyDosage(routine.getDailyDosage())
                            .dayOfWeek(routine.getDayOfWeek())
                            .isActive(routine.isActive())
                            .build();
                }))
                .collect(Collectors.toList());
    }
}