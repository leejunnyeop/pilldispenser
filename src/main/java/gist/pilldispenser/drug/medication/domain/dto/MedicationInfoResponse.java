package gist.pilldispenser.drug.medication.domain.dto;


import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class MedicationInfoResponse {

    private String drugName;
    private String mainIngredient;
    private String shape;
    private String size;
}

