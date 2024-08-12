package gist.pilldispenser.drug.drugInfo.domain.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OvalDrugInfoRequest extends DrugInfoRequestBase {

    private Double longAxis;  // 타원형일 때 사용
    private Double shortAxis; // 타원형일 때 사용
}
