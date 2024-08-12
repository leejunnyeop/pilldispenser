package gist.pilldispenser.drug.drugInfo.domain.dto;


import gist.pilldispenser.drug.drugInfo.domain.entity.DrugShape;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class DrugManualInfoRequest {

    private String name;
    private String dosage;
    private String mtralNm;
    private Integer dailyDosage;
    private String shape;  // Enum 타입

    private Double longAxis;
    private Double shortAxis;

    private Long slotId;
    private String diskSize;

}
