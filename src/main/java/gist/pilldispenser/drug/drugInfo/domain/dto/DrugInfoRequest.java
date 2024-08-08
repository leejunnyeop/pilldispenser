package gist.pilldispenser.drug.drugInfo.domain.dto;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Builder
@Getter
@Setter
@NoArgsConstructor
@Schema(description = "약 정보 요청 DTO")
public class DrugInfoRequest {



        @Schema(description = "약 이름", example = "타이레놀")
        private String name;  // 약 이름


        @Schema(description = "복용량", example = "500mg")
        private String dosage;  // 복용량

        @Schema(description = "성분 리스트", example = "[\"아세트아미노펜\", \"카페인\"]")
        private List<String> ingredients;  // 성분 리스트

        @Schema(description = "사용 용법", example = "두통 시 복용")
        private String usage;  // 사용 용법

        @Schema(description = "하루 복용 횟수", example = "3")
        private int dailyDosage;  // 하루 복용 횟수

        @Schema(description = "복용 시간대 (아침, 점심, 저녁)", example = "[\"아침\", \"점심\", \"저녁\"]")
        private List<String> timeOfDay;  // 복용 시간대 (아침, 점심, 저녁)

        @Schema(description = "모양 (예: 원형, 타원형)", example = "원형")
        private String shape;  // 모양 (예: 원형, 타원형)

        @Schema(description = "직경 (mm)", example = "8.0")
        private double diameter;  // 직경 (mm)

        @Schema(description = "두께 (mm)", example = "3.0")
        private double thickness;  // 두께 (mm)

        @Schema(description = "색상", example = "빨강")
        private String color;  // 색상

}