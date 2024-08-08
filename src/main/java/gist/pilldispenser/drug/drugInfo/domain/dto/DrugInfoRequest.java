package gist.pilldispenser.drug.drugInfo.domain.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Builder
@Getter
@Setter
@NoArgsConstructor
public class DrugInfoRequest {

        private Long id;  // 약 ID
        private String name;  // 약 이름
        private String brand;  // 브랜드명
        private String dosage;  // 복용량
        private List<String> ingredients;  // 성분 리스트
        private String usage;  // 사용 용법
        private int dailyDosage;  // 하루 복용 횟수
        private List<String> timeOfDay;  // 복용 시간대 (아침, 점심, 저녁)
        private BeforeAfterMeal beforeAfterMeal;  // 복용 시점 (식전, 식후, 공복, 취침 전)
        private boolean mixable;  // 혼용 가능 여부
        private String shape;  // 모양 (예: 원형, 타원형)
        private double diameter;  // 직경 (mm)
        private double thickness;  // 두께 (mm)
        private String color;  // 색상

}
