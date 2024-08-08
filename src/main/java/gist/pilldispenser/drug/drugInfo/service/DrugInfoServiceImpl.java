package gist.pilldispenser.drug.drugInfo.service;

import gist.pilldispenser.drug.drugInfo.domain.dto.DrugInfoMapper;
import gist.pilldispenser.drug.drugInfo.repository.DrugInfoRepository;
import gist.pilldispenser.drug.drugInfo.domain.dto.DrugInfoRequest;
import gist.pilldispenser.drug.drugInfo.domain.entity.DrugInfo;
import gist.pilldispenser.users.domain.entity.Users;
import gist.pilldispenser.users.repository.UsersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DrugInfoServiceImpl implements DrugInfoService {


    private final DrugInfoRepository drugInfoRepository;
    private final UsersRepository usersRepository;

    @Override
    @Transactional
    public void createDrugInfo(Long userId, DrugInfoRequest drugInfoRequest) {
        Optional<Users> optionalUser = usersRepository.findById(userId);
        if (optionalUser.isEmpty()) {
            throw new IllegalArgumentException("해당 userId에 대한 사용자 정보를 찾을 수 없습니다: " + userId);
        }

        DrugInfo drugInfo = DrugInfoMapper.toDrugInfo(drugInfoRequest);
        drugInfoRepository.save(drugInfo);
    }

}
