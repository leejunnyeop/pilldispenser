package gist.pilldispenser.drug.userDrugInfo.service;

import com.querydsl.jpa.impl.JPAQueryFactory;
import gist.pilldispenser.drug.userDrugInfo.domain.dto.UserDrugRoutineResponse;
import gist.pilldispenser.drug.userDrugInfo.domain.entity.UserDrugInfo;
import gist.pilldispenser.drug.userDrugInfo.repository.RoutineRepository;
import gist.pilldispenser.drug.userDrugInfo.repository.UserDrugInfoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserDrugRoutineService {

    private final JPAQueryFactory queryFactory;
    private final UserDrugInfoRepository userDrugInfoRepository;
    private final RoutineRepository routineRepository;

    @Transactional(readOnly = true)
    public List<UserDrugRoutineResponse> getUserDrugRoutines(Long userId) {
        List<UserDrugInfo> userDrugInfos = userDrugInfoRepository.findByUserId(userId);

        return userDrugInfos.stream().map(userDrugInfo -> {
            String drugName = userDrugInfo.getDrugInfo() != null
                ? userDrugInfo.getDrugInfo().getName()
                : userDrugInfo.getFullMedicationInfo().getDrugSummary().getItemName();

            String entrps = userDrugInfo.getDrugInfo() != null
                ? null
                : userDrugInfo.getFullMedicationInfo().getDrugSummary().getEntpName();

            String image = userDrugInfo.getDrugInfo() != null
                    ? null
                    : userDrugInfo.getFullMedicationInfo().getDrugSummary().getItemImage();

            return UserDrugRoutineResponse.builder()
                .id(userDrugInfo.getId())
                .drugName(drugName)
                .entrps(entrps)
                .image(image)
                .build();
        }).collect(Collectors.toList());
    }
}