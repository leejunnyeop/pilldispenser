package gist.pilldispenser.drug.drugInfo.domain.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RoundDrugInfoRequest extends DrugInfoRequestBase {

    private Double diameter;  // 원형일 때 사용
}
