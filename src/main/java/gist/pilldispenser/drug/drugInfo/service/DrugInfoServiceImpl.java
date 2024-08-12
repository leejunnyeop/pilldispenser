package gist.pilldispenser.drug.drugInfo.service;

import gist.pilldispenser.drug.drugInfo.domain.DrugInfoMapper;
import gist.pilldispenser.drug.drugInfo.domain.dto.DrugInfoRequestBase;
import gist.pilldispenser.drug.drugInfo.domain.dto.DrugInfoResponse;
import gist.pilldispenser.drug.drugInfo.domain.dto.OvalDrugInfoRequest;
import gist.pilldispenser.drug.drugInfo.domain.dto.RoundDrugInfoRequest;
import gist.pilldispenser.drug.drugInfo.domain.entity.DrugShape;
import gist.pilldispenser.drug.drugInfo.repository.DrugInfoRepository;
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

    @Transactional
    public DrugInfoResponse createDrugInfoManually(Long userId, DrugInfoRequestBase drugInfoRequest) {
        Users user = usersRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("해당 userId에 대한 사용자 정보를 찾을 수 없습니다: " + userId));

        DrugInfo drugInfo;

        // DrugInfoRequest의 구체적인 타입에 따라 DrugInfo 객체 생성
        if (drugInfoRequest instanceof RoundDrugInfoRequest) {
            drugInfo = DrugInfoMapper.toDrugInfo((RoundDrugInfoRequest) drugInfoRequest);
        } else if (drugInfoRequest instanceof OvalDrugInfoRequest) {
            drugInfo = DrugInfoMapper.toDrugInfo((OvalDrugInfoRequest) drugInfoRequest);
        } else {
            throw new IllegalArgumentException("알 수 없는 약물 정보 요청 타입입니다.");
        }

        drugInfoRepository.save(drugInfo);

        UserDrugInfo userDrugInfo = UserDrugInfo.create(user, drugInfo, null);
        userDrugInfoRepository.save(userDrugInfo);

        // DrugInfoResponse DTO로 변환
        String shape = drugInfo.getShape().name();
        String size;

        if (drugInfo.getShape() == DrugShape.ROUND) {
            size = "직경 " + drugInfo.getLongAxis() + "mm";
        } else if (drugInfo.getShape() == DrugShape.OVAL) {
            size = "장축 " + drugInfo.getLongAxis() + "mm, 단축 " + drugInfo.getShortAxis() + "mm";
        } else {
            size = "알 수 없음";
        }

        return DrugInfoResponse.builder()
                .drugName(drugInfo.getName())
                .dosageInstructions("하루 " + drugInfo.getDailyDosage() + "번 ")
                .shape(shape)
                .size(size)
                .build();
    }
}
