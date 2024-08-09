package gist.pilldispenser.drug.medication.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FullMedicationInfoRequestDto {

    private Long userId;
    private String itemSeq;
}
