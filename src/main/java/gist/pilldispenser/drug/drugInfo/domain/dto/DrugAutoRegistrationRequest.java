package gist.pilldispenser.drug.drugInfo.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class DrugAutoRegistrationRequest {

    private String itemSeq;
    private Long slotId;
    private String diskSize;
}
