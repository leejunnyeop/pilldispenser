package gist.pilldispenser.drug.userDrugInfo.domain.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class UserDrugRoutineResponse {

    private Long id;
    private String drugName;       // 약물 이름
    private String entrps;          // 업체명
    private String image;
}