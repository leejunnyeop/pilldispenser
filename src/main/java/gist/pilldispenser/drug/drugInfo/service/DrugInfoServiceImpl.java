package gist.pilldispenser.drug.drugInfo.service;

import gist.pilldispenser.drug.drugInfo.domain.dto.DrugInfoMapper;
import gist.pilldispenser.drug.drugInfo.repository.DrugInfoRepository;
import gist.pilldispenser.drug.drugInfo.domain.dto.DrugInfoRequest;
import gist.pilldispenser.drug.drugInfo.domain.entity.DrugInfo;
import gist.pilldispenser.drug.userDrugInfo.domain.entity.UserDrugInfo;
import gist.pilldispenser.drug.userDrugInfo.repository.UserDrugInfoRepository;
import gist.pilldispenser.users.domain.entity.Users;
import gist.pilldispenser.users.repository.UsersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class DrugInfoServiceImpl implements DrugInfoService {


    private final DrugInfoRepository drugInfoRepository;
    private final UsersRepository usersRepository;
    private final UserDrugInfoRepository userDrugInfoRepository;

    @Override
    @Transactional
    public void createDrugInfoManually(Long userId, DrugInfoRequest drugInfoRequest) {
        Users user = usersRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("해당 userId에 대한 사용자 정보를 찾을 수 없습니다: " + userId));

        DrugInfo drugInfo = DrugInfoMapper.toDrugInfo(drugInfoRequest);
        drugInfoRepository.save(drugInfo);

        UserDrugInfo userDrugInfo = UserDrugInfo.create(user, drugInfo, null);
        userDrugInfoRepository.save(userDrugInfo);
    }

}
